package mods.defeatedcrow.common.entity;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.config.DCsConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityYuzuBullet extends Entity implements IProjectile {

	/* 地中判定に使うもの */
	protected int xTile = -1;
	protected int yTile = -1;
	protected int zTile = -1;
	protected Block inTile;
	protected int inData;
	protected boolean inGround;

	/* この弾を撃ったエンティティ */
	public EntityLivingBase shootingEntity;

	/* 地中・空中にいる時間 */
	protected int ticksInGround;
	protected int ticksInAir;
	protected int livingTimeCount = 0;

	/* ダメージの大きさ */
	protected double damage = 5.0D;

	/* ノックバックの大きさ */
	protected int knockbackStrength = 1;

	public EntityYuzuBullet(World par1World) {
		super(par1World);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.5F, 0.5F);
		this.damage = 5.0D;
	}

	public EntityYuzuBullet(World par1World, EntityLivingBase par2EntityLivingBase, float speed, float speed2,
			float adjustX, float adjustZ, float adjustY) {
		super(par1World);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = par2EntityLivingBase;
		this.yOffset = 0.0F;
		this.setSize(0.5F, 0.5F);

		// 初期状態での向きの決定
		this.setLocationAndAngles(par2EntityLivingBase.posX,
				par2EntityLivingBase.posY + (double) par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ,
				par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);

		// 位置の調整
		this.posX += -(double) (MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * (1.0F + adjustZ))
				- (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * adjustX);
		this.posY += 0.05000000149011612D + adjustY;
		this.posZ += (double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * (1.0F + adjustZ))
				- (double) (MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * adjustX);
		this.setPosition(this.posX, this.posY, this.posZ);

		// 初速度
		this.motionX = ((double) (-MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper
				.cos(this.rotationPitch / 180.0F * (float) Math.PI)));
		this.motionZ = ((double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper
				.cos(this.rotationPitch / 180.0F * (float) Math.PI)));
		this.motionY = ((double) (-MathHelper.sin(this.rotationPitch / 180.0F * (float) Math.PI)));
		this.setThrowableHeading(this.motionX * speed, this.motionY * speed, this.motionZ * speed, speed, speed2);
	}

	protected void entityInit() {
	}

	public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8) {
		float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
		par1 /= (double) f2;
		par3 /= (double) f2;
		par5 /= (double) f2;
		par1 += this.rand.nextGaussian() * (double) (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D
				* (double) par8;
		par3 += this.rand.nextGaussian() * (double) (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D
				* (double) par8;
		par5 += this.rand.nextGaussian() * (double) (this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D
				* (double) par8;
		par1 *= (double) par7;
		par3 *= (double) par7;
		par5 *= (double) par7;
		this.motionX = par1;
		this.motionY = par3;
		this.motionZ = par5;
		float f3 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
		this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(par3, (double) f3) * 180.0D / Math.PI);
		this.ticksInGround = 0;
	}

	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
		this.setPosition(par1, par3, par5);
		this.setRotation(par7, par8);
	}

	@SideOnly(Side.CLIENT)
	public void setVelocity(double par1, double par3, double par5) {
		this.motionX = par1;
		this.motionY = par3;
		this.motionZ = par5;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(par3, (double) f) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.ticksInGround = 0;
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		livingTimeCount++;
		if (livingTimeCount > 300)
			this.setDead();

		double dx = -(double) (MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI));
		double dz = -(double) (MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI));
		for (int i = 0; i < 4; ++i) {
			this.worldObj.spawnParticle("crit", this.posX + dx, this.posY, this.posZ + dz, -this.motionX,
					-this.motionY + 0.2D, -this.motionZ);
		}

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(this.motionY, (double) f) * 180.0D / Math.PI);
		}

		Block i = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
		boolean air = this.worldObj.isAirBlock(xTile, yTile, zTile);

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

		if (this.inGround) {
			Block j = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
			int k = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);

			/*
			 * 前のTickに確認した埋まりブロックのIDとメタを照合している。違ったら埋まり状態を解除、一致したら埋まり状態を継続。
			 * /* 埋まり状態2tick継続でこのエンティティを消す
			 */
			if (j == this.inTile && k == this.inData) {
				++this.ticksInGround;

				int limit = 2;

				if (this.ticksInGround > limit) {
					this.setDead();
				}
			} else// 埋まり状態の解除処理
			{
				this.inGround = false;
				this.motionX *= (double) (this.rand.nextFloat() * 0.1F);
				this.motionY *= (double) (this.rand.nextFloat() * 0.1F);
				this.motionZ *= (double) (this.rand.nextFloat() * 0.1F);
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

			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this,
					this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double d0 = 0.0D;
			int l;
			float f1;

			MovingObjectPosition entityTarget = null;

			for (l = 0; l < list.size(); ++l) {
				Entity entity1 = (Entity) list.get(l);
				Entity entity = null;

				// ターゲットの場合
				if (entity1 instanceof EntityLivingBase) {
					f1 = 0.3F;
					AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand((double) f1, (double) f1, (double) f1);
					MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec3, vec31);

					if (movingobjectposition1 != null) {
						double d1 = vec3.distanceTo(movingobjectposition1.hitVec);

						if (d1 < d0 || d0 == 0.0D) {
							if (entity1 != null) {
								entityTarget = new MovingObjectPosition(entity1);
								d0 = d1;
								break;
							}
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
				} else {
					canAttack = true;
				}
			}

			float f2;
			float f3;

			// 当たったあとの処理
			// まずはリストから
			if (this.livingTimeCount > 1 && canAttack) {
				Entity target = entityTarget.entityHit;

				int i1 = MathHelper.ceiling_double_int(1.0D * this.damage);
				// 0~2程度の乱数値を上乗せ
				i1 += this.rand.nextInt(3);

				if (target.isImmuneToFire() && this.isBurning()) {
					i1 = 0;
				}

				DamageSource damagesource = null;

				// 別メソッドでダメージソースを確認
				damagesource = this.thisDamageSource(this.shootingEntity);

				if (target instanceof IProjectile) {
					// 対象が矢などの飛翔Entityの場合、打ち消すことが出来る
					target.setDead();
				} else {
					// ダメージを与える処理を呼ぶ
					if (target.attackEntityFrom(damagesource, (float) i1)) {
						// ダメージを与えることに成功したら以下の処理を行う
						if (target instanceof EntityLivingBase) {
							EntityLivingBase entitylivingbase = (EntityLivingBase) target;

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
						if (!(target instanceof EntityEnderman)) {
							this.setDead();
						}
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
				this.motionX = (double) ((float) (movingobjectposition.hitVec.xCoord - this.posX));
				this.motionY = (double) ((float) (movingobjectposition.hitVec.yCoord - this.posY));
				this.motionZ = (double) ((float) (movingobjectposition.hitVec.zCoord - this.posZ));
				f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ
						* this.motionZ);
				this.posX -= this.motionX / (double) f2 * 0.05000000074505806D;
				this.posY -= this.motionY / (double) f2 * 0.05000000074505806D;
				this.posZ -= this.motionZ / (double) f2 * 0.05000000074505806D;
				this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
				this.inGround = true;

				if (this.inTile != null) {
					// Block側に衝突を伝える
					this.inTile.onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, this);

					// 燃えている時
					if (this.isBurning()) {
						if (this.inTile == Blocks.ice)
							this.worldObj.setBlock(xTile, yTile, zTile, Blocks.water);
						if (this.inTile.getMaterial() == Material.snow) {
							this.worldObj.setBlockToAir(xTile, yTile, zTile);
							this.setDead();
						}
					}
				}
			}
		}

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(this.motionY, (double) f) * 180.0D / Math.PI);
		}

		// 改めてポジションに速度を加算。向きも更新。
		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
		this.rotationPitch = (float) (Math.atan2(this.motionY, (double) f2) * 180.0D / Math.PI);

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
			if (this.isBurning()) {
				this.extinguish();
			}

			// 泡パーティクルが出る
			for (int j1 = 0; j1 < 4; ++j1) {
				float f3 = 0.25F;
				this.worldObj
						.spawnParticle("bubble", this.posX - this.motionX * (double) f3, this.posY - this.motionY
								* (double) f3, this.posZ - this.motionZ * (double) f3, this.motionX, this.motionY,
								this.motionZ);
			}
		}

		this.setPosition(this.posX, this.posY, this.posZ);
		this.func_145775_I();
	}

	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		par1NBTTagCompound.setShort("xTile", (short) this.xTile);
		par1NBTTagCompound.setShort("yTile", (short) this.yTile);
		par1NBTTagCompound.setShort("zTile", (short) this.zTile);
		par1NBTTagCompound.setByte("inTile", (byte) (byte) Block.getIdFromBlock(this.inTile));
		par1NBTTagCompound.setByte("inData", (byte) this.inData);
		par1NBTTagCompound.setByte("inGround", (byte) (this.inGround ? 1 : 0));
		par1NBTTagCompound.setDouble("damage", this.damage);
	}

	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		this.xTile = par1NBTTagCompound.getShort("xTile");
		this.yTile = par1NBTTagCompound.getShort("yTile");
		this.zTile = par1NBTTagCompound.getShort("zTile");
		this.inTile = Block.getBlockById(par1NBTTagCompound.getByte("inTile") & 255);
		this.inData = par1NBTTagCompound.getByte("inData") & 255;
		this.inGround = par1NBTTagCompound.getByte("inGround") == 1;

		if (par1NBTTagCompound.hasKey("damage")) {
			this.damage = par1NBTTagCompound.getDouble("damage");
		}
	}

	protected boolean canTriggerWalking() {
		return false;
	}

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
		if (this.isBurning()) {
			return DamageSource.lava;
		} else {
			return entity != null ? EntityDamageSource.causeIndirectMagicDamage(entity, this) : DamageSource.magic;
		}
	}

}
