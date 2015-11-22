package mods.defeatedcrow.common.entity;

import java.util.List;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.handler.CustomExplosion;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/*
 * このエンティティの動作
 * 射出時は重力落下するだけのエンティティで、頭の向きはプレイヤー方向、適当な方向に飛び出す。
 * 生成時にターゲットは決めておく。
 * 射出後、一定時間でモードが切り替わり、ターゲットに対して定期的に進行方向を修正するゆるい追尾弾になる。
 * 同時に羽を開いたり、尾部の炎が光るためDataWatcherを使用。
 * 
 * 着弾後、接触したエンティティには物理ダメージを与え、少し宙に浮かせて、その後爆発する。
 * 爆発はCustomExplosionを使用。対空特効のため浮かせたモブに大ダメージを与える。
 * 水中でも爆発できる。水中減速もなし。シーラカンスの得意技なので水中での挙動は優遇気味に。
 */

/*
 * 発射されるエンティティのクラス。
 */
public class EntityAnchorMissile extends Entity implements IProjectile {

	/* 地中判定に使うもの */
	protected int xTile = -1;
	protected int yTile = -1;
	protected int zTile = -1;
	protected Block inTile;
	protected int inData;
	protected boolean inGround;

	/* この弾を撃ったエンティティ */
	public EntityLivingBase shootingEntity;

	/* ターゲットエンティティ */
	public EntityLivingBase targetEntity;

	/* 地中・空中にいる時間 */
	protected int ticksInGround;
	protected int ticksInAir;
	protected int livingTimeCount = 0;

	/* 軌道修正のクールタイム */
	protected int coolingtime = 4;

	/* ダメージの大きさ */
	protected double damage = 35.0D;

	/* ノックバックの大きさ */
	protected int knockbackStrength = 1;

	/* 起動しているかどうか */
	protected boolean active = false;

	public EntityAnchorMissile(World par1World) {
		super(par1World);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.5F, 0.5F);
		this.damage = 50.0D;
	}

	/**
	 * 発射する弾を生成・初期パラメータの定義をする。
	 * 
	 * @param par1World
	 *            :このワールド
	 * @param par2EntityLivingBase
	 *            :弾源となるエンティティ。このModの場合、弾を撃ったプレイヤーがここに入る
	 * @param par3EntityLivingBase
	 *            :ターゲットのエンティティ。生成前に、予めAABBなどを使ってターゲットを得ておくこと
	 * @param speed
	 *            :弾の速度計算に使われる値
	 * @param speed2
	 *            :弾の速度計算に使われる値2
	 * @param initialYaw
	 *            :弾の初期射出方向
	 */
	public EntityAnchorMissile(World par1World, EntityLivingBase par2EntityLivingBase,
			EntityLivingBase par3EntityLivingBase, float speed, float speed2, float initialYaw, float adjustX,
			float adjustY, float adjustZ) {
		super(par1World);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = par2EntityLivingBase;
		this.targetEntity = par3EntityLivingBase;
		this.yOffset = 0.0F;
		this.setSize(0.5F, 0.5F);
		this.damage = 50.0D;

		float initialPitch = 0.0F;

		// 初期状態での向きは射手の向きに依存する
		this.setLocationAndAngles(par2EntityLivingBase.posX,
				par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ,
				par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);

		// 初速度
		this.posX += -(double) (MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * (1.0F + adjustZ))
				- MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * adjustX;
		this.posY += 0.05000000149011612D + adjustY;
		this.posZ += (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * (1.0F + adjustZ))
				- (double) (MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * adjustX);
		this.setPosition(this.posX, this.posY, this.posZ);

		// 初速度
		this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI)
				* MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
		this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI)
				* MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI);
		this.motionY = (-MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI));
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, speed * 1.5F, speed2);
	}

	/* dataWatcherを利用したサーバ・クライアント間の同期処理だと思う */
	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(16, Byte.valueOf((byte) 0));
		this.dataWatcher.addObject(17, Byte.valueOf((byte) 0));
	}

	/*
	 * IProjectileで実装が必要なメソッド。
	 * ディスペンサーによる発射メソッドなどで使用されている。
	 */
	@Override
	public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8) {
		float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
		par1 /= f2;
		par3 /= f2;
		par5 /= f2;
		par1 += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
		par3 += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
		par5 += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * par8;
		par1 *= par7;
		par3 *= par7;
		par5 *= par7;
		this.motionX = par1;
		this.motionY = par3;
		this.motionZ = par5;
		float f3 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
		this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(par3, f3) * 180.0D / Math.PI);
		this.ticksInGround = 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
		this.setPosition(par1, par3, par5);
		this.setRotation(par7, par8);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setVelocity(double par1, double par3, double par5) {
		this.motionX = par1;
		this.motionY = par3;
		this.motionZ = par5;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(par3, f) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.ticksInGround = 0;
		}
	}

	/*
	 * Tick毎に呼ばれる更新処理。
	 * 速度の更新、衝突判定などをここで行う。
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();

		livingTimeCount++;

		// その1、爆発処理
		byte exp = this.isExploded();
		if (exp == 1) {
			this.setDead();
		} else if (exp > 1) {
			AMTLogger.debugInfo("current explode int :" + exp);
			this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY + 1.0D, this.posZ, this.motionX,
					this.motionY, this.motionZ);
			exp--;
			this.setTimeCount(exp);
		}

		// その2、アクティブか否か
		if (livingTimeCount > 3 && !this.active) {
			this.active = true;
			this.setActive((byte) 1);
			this.playSound("defeatedcrow:knock", 0.5F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
		}

		boolean explode = false;

		// 以降、アクティブか否かで動作が変わる。
		if (this.active) {
			double dx = -(double) (MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI));
			double dz = -(double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI));
			for (int i = 0; i < 4; ++i) {
				this.worldObj.spawnParticle("crit", this.posX + dx, this.posY, this.posZ + dz, -this.motionX,
						-this.motionY + 0.2D, -this.motionZ);
			}
		}

		// 直前のパラメータと新パラメータを一致させているところ。
		// また、速度に応じてエンティティの向きを調整し、常に進行方向に前面が向くようにしている。
		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(this.motionY, f) * 180.0D / Math.PI);
		}

		// 激突したブロックを確認している
		Block i = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
		boolean air = this.worldObj.isAirBlock(xTile, yTile, zTile);

		// 空気じゃないブロックに当たった&ブロック貫通エンティティでない時
		if (i != null && i.getMaterial() != Material.air) {
			i.setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);
			AxisAlignedBB axisalignedbb = i.getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile,
					this.zTile);

			// 当たり判定に接触しているかどうか
			if (axisalignedbb != null
					&& axisalignedbb.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ))) {
				this.inGround = true;
			}
		}

		// 空気じゃないブロックに当たった
		if (this.inGround) {
			Block j = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
			int k = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);

			/*
			 * 前のTickに確認した埋まりブロックのIDとメタを照合している。違ったら埋まり状態を解除、一致したら埋まり状態を継続。
			 * /* 埋まり状態2tick継続でこのエンティティを消す
			 */
			if (j == this.inTile && k == this.inData) {
				++this.ticksInGround;
				// ブロック貫通の場合、20tick（1秒間）はブロック中にあっても消えないようになる。
				int limit = 2;

				if (this.ticksInGround > limit) {
					explode = true;
				}
			} else// 埋まり状態の解除処理
			{
				this.inGround = false;
				this.motionX *= this.rand.nextFloat() * 0.1F;
				this.motionY *= this.rand.nextFloat() * 0.1F;
				this.motionZ *= this.rand.nextFloat() * 0.1F;
				this.ticksInGround = 0;
				this.ticksInAir = 0;
			}
		} else// 埋まってない時。速度の更新。
		{
			++this.ticksInAir;
			// ブロックとの衝突判定
			Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			Vec3 vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ
					+ this.motionZ);
			MovingObjectPosition movingobjectposition = this.worldObj.func_147447_a(vec3, vec31, false, true, false);
			vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ
					+ this.motionZ);

			// ブロックに当たった
			if (movingobjectposition != null) {
				vec31 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord,
						movingobjectposition.hitVec.zCoord);
			}

			// Entityとの衝突判定。
			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this,
					this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double d0 = 0.0D;
			int l;
			float f1;

			MovingObjectPosition entityTarget = null;

			// 1ブロック分の範囲内にいるエンティティ全てに対して繰り返す
			for (l = 0; l < list.size(); ++l) {
				Entity entity1 = (Entity) list.get(l);
				Entity entity = null;

				// ターゲットの場合
				if (entity1 instanceof EntityLivingBase || entity1 instanceof EntityDragonPart) {
					f1 = 0.3F;
					AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand(f1, f1, f1);
					MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec3, vec31);

					if (movingobjectposition1 != null) {
						double d1 = vec3.distanceTo(movingobjectposition1.hitVec);

						if (d1 < d0 || d0 == 0.0D) {
							// arrowと異なり、あたったEntityすべてをリストに入れる
							entityTarget = new MovingObjectPosition(entity1);
							d0 = d1;
							break;
						}
					}
				}
			}

			/*
			 * 当たったエンティティそれそれについての判定部分。
			 * ここで特定の種類のエンティティに当たらないようにできる。
			 */
			boolean canAttack = false;
			if (entityTarget != null) {
				Entity target = entityTarget.entityHit;

				if (target instanceof EntityPlayer) {
					// プレイヤーに当たった時
					EntityPlayer entityplayer = (EntityPlayer) target;

					if (entityplayer.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer
							&& !((EntityPlayer) this.shootingEntity).canAttackPlayer(entityplayer)) {
						// PvPが許可されていないと当たらない
						canAttack = false;
					} else if (entityplayer == this.shootingEntity) {
						// 対象が撃った本人の場合も当たらない
						canAttack = false;
					} else if (DCsConfig.PvPProhibitionMode && entityplayer instanceof EntityPlayer) {
						canAttack = false;
					}
				} else if (target instanceof EntityTameable || target instanceof EntityHorse) {
					// 事故防止の為、EntityTameable（犬や猫などのペット）、馬にも当たらないようにする
					canAttack = false;
				} else {
					canAttack = true;
				}
			}

			float f2;
			float f3;

			// 当たったあとの処理
			// まずはリストから
			if (canAttack) {
				Entity target = entityTarget.entityHit;

				int i1 = MathHelper.ceiling_double_int(1.0D * this.damage);
				// 0~2程度の乱数値を上乗せ
				i1 += this.rand.nextInt(3);

				DamageSource damagesource = null;

				// 別メソッドでダメージソースを確認
				damagesource = this.thisDamageSource(this.shootingEntity);

				// バニラ矢と同様、このエンティティが燃えているなら対象に着火することも出来る
				if (this.isBurning() && !(target instanceof EntityEnderman)) {
					target.setFire(5);
				}

				else if (target instanceof IProjectile) {
					// 対象が矢などの飛翔Entityの場合、打ち消すことが出来る
					target.setDead();
				} else {
					// ダメージを与える処理を呼ぶ
					if (target.attackEntityFrom(damagesource, i1)) {
						// ダメージを与えることに成功したら以下の処理を行う
						if (target instanceof EntityLivingBase) {
							EntityLivingBase entitylivingbase = (EntityLivingBase) target;

							// ノックバック
							if (this.knockbackStrength > 0) {
								f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

								if (f3 > 0.0F) {
									// Y方向に大きめに打ち上げる
									target.addVelocity(
											this.motionX * this.knockbackStrength * 0.2000000238418579D / f3, 0.3D,
											this.motionZ * this.knockbackStrength * 0.2000000238418579D / f3);
								}
							}

							// 無敵時間はなし
							target.hurtResistantTime = 0;

							// マルチプレイ時に、両者がプレイヤーだった時のパケット送信処理
							if (this.shootingEntity != null && target != this.shootingEntity
									&& target instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP) {
								((EntityPlayerMP) this.shootingEntity).playerNetServerHandler
										.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
							}
						}

						// ここでヒット時の効果音がなる
						this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

						// 当たったあと、弾を消去する。エンティティ貫通がONの弾種はそのまま残す。
						explode = true;
					}
				}
			}

			if (movingobjectposition != null)// blockのみ
			{
				this.xTile = movingobjectposition.blockX;
				this.yTile = movingobjectposition.blockY;
				this.zTile = movingobjectposition.blockZ;
				this.inTile = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
				this.inData = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
				this.motionX = ((float) (movingobjectposition.hitVec.xCoord - this.posX));
				this.motionY = ((float) (movingobjectposition.hitVec.yCoord - this.posY));
				this.motionZ = ((float) (movingobjectposition.hitVec.zCoord - this.posZ));
				f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ
						* this.motionZ);
				this.posX -= this.motionX / f2 * 0.05000000074505806D;
				this.posY -= this.motionY / f2 * 0.05000000074505806D;
				this.posZ -= this.motionZ / f2 * 0.05000000074505806D;
				this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
				this.inGround = true;

				if (this.inTile != null) {
					// Block側に衝突を伝える
					this.inTile.onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, this);
				}
			}
		}

		// さいごに爆発処理
		int live = (targetEntity != null && targetEntity.isEntityAlive()) ? 10 : 600;

		if (explode || this.livingTimeCount > live) {
			if (this.isExploded() == 0) {
				AMTLogger.debugInfo("explosion");
				this.setExplosion();
				if (!DCsConfig.disableMissileExplosion && !worldObj.isRemote) {
					float f = 5.0F;
					CustomExplosion explosion = new CustomExplosion(worldObj, this, shootingEntity, this.posX,
							this.posY, this.posZ, f, CustomExplosion.Type.Anchor, true);
					explosion.doExplosion();
				}
			}

		}

		// 追尾
		if (active) {
			if (targetEntity != null && targetEntity.isEntityAlive()) {
				double dx = targetEntity.posX - this.posX;
				double dy = targetEntity.boundingBox.minY + (targetEntity.height / 2.0D) - this.posY;
				double dz = targetEntity.posZ - this.posZ;
				double d3 = MathHelper.sqrt_double(dx * dx + dz * dz);

				if (d3 >= 1.0E-7D) {
					float f4 = (float) d3 * 0.2F;
					double dy2 = dy + f4;

					float ff = MathHelper.sqrt_double(dx * dx + dy2 * dy2 + dz * dz);
					dx /= ff;
					dy /= ff;
					dz /= ff;

					this.motionX = (this.motionX + dx) / 2;
					this.motionY = (this.motionY + dy) / 2;
					this.motionZ = (this.motionZ + dz) / 2;

					this.motionX *= 1.25D;
					this.motionY *= 1.25D;
					this.motionZ *= 1.25D;
				}
			}
		}

		// 直前のパラメータと新パラメータを一致させているところ。
		// また、速度に応じてエンティティの向きを調整し、常に進行方向に前面が向くようにしている。
		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(this.motionY, f) * 180.0D / Math.PI);
		}

		// 改めてポジションに速度を加算。向きも更新。
		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
		this.rotationPitch = (float) (Math.atan2(this.motionY, f2) * 180.0D / Math.PI);

		while (this.rotationPitch - this.prevRotationPitch < -180.0F) {
			this.prevRotationPitch -= 360.0F;
		}

		while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
			this.prevRotationPitch += 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
			this.prevRotationYaw -= 360.0F;
		}

		while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
			this.prevRotationYaw += 360.0F;
		}

		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;

		// 水中に有る
		if (this.isInWater()) {
			// 泡パーティクルが出る
			for (int j1 = 0; j1 < 4; ++j1) {
				float f3 = 0.25F;
				this.worldObj.spawnParticle("bubble", this.posX - this.motionX * f3, this.posY - this.motionY * f3,
						this.posZ - this.motionZ * f3, this.motionX, this.motionY, this.motionZ);
			}
		}

		this.setPosition(this.posX, this.posY, this.posZ);
		this.func_145775_I();
	}

	/*
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		par1NBTTagCompound.setShort("xTile", (short) this.xTile);
		par1NBTTagCompound.setShort("yTile", (short) this.yTile);
		par1NBTTagCompound.setShort("zTile", (short) this.zTile);
		par1NBTTagCompound.setByte("inTile", (byte) Block.getIdFromBlock(this.inTile));
		par1NBTTagCompound.setByte("inData", (byte) this.inData);
		par1NBTTagCompound.setByte("inGround", (byte) (this.inGround ? 1 : 0));
		par1NBTTagCompound.setByte("active", (byte) (this.active ? 1 : 0));
		par1NBTTagCompound.setDouble("damage", this.damage);
	}

	/*
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		this.xTile = par1NBTTagCompound.getShort("xTile");
		this.yTile = par1NBTTagCompound.getShort("yTile");
		this.zTile = par1NBTTagCompound.getShort("zTile");
		this.inTile = Block.getBlockById(par1NBTTagCompound.getByte("inTile") & 255);
		this.inData = par1NBTTagCompound.getByte("inData") & 255;
		this.inGround = par1NBTTagCompound.getByte("inGround") == 1;
		this.active = par1NBTTagCompound.getByte("active") == 1;

		if (par1NBTTagCompound.hasKey("damage")) {
			this.damage = par1NBTTagCompound.getDouble("damage");
		}
	}

	/*
	 * プレイヤーと衝突した時のメソッド。今回は何もしない
	 */
	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {

	}

	/*
	 * ブロックに対し、上を歩いたかという判定の対象になるか、というEntityクラスのメソッド。
	 * 耕地を荒らしたりするのに使う。
	 */
	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.0F;
	}

	public void setDamage(double par1) {
		this.damage = par1;
	}

	public double getDamage() {
		return this.damage;
	}

	public void setKnockbackStrength(int par1) {
		this.knockbackStrength = par1;
	}

	@Override
	public boolean canAttackWithItem() {
		return false;
	}

	/* 落下速度 */
	public float fallSpeed() {
		return 0.0F;
	}

	/* ダメージソースのタイプ */
	public DamageSource thisDamageSource(Entity entity) {
		// 発射元のEntityがnullだった場合の対策を含む。
		if (entity instanceof EntityPlayer) {
			return EntityDamageSource.causePlayerDamage((EntityPlayer) entity);
		}
		return entity != null ? EntityDamageSource.causeIndirectMagicDamage(this, entity) : DamageSource.magic;
	}

	/* 爆発判定 */
	public byte isExploded() {
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);
		return b0;
	}

	// 爆発処理。パーティクル発生のため、クライアントとの同期が必要。
	public void setExplosion() {
		this.dataWatcher.updateObject(16, Byte.valueOf((byte) 4));
	}

	public void setTimeCount(byte i) {
		this.dataWatcher.updateObject(16, Byte.valueOf(i));
	}

	/* アクティブかどうか */
	public boolean isActive() {
		byte b0 = this.dataWatcher.getWatchableObjectByte(17);
		return b0 > 0;
	}

	public void setActive(byte b) {
		this.dataWatcher.updateObject(17, Byte.valueOf((byte) 1));
	}

}
