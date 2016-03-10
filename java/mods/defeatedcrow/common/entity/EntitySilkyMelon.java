package mods.defeatedcrow.common.entity;

import java.util.List;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.AchievementRegister;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.handler.CustomExplosion;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntitySilkyMelon extends Entity {
	// 何のフラグだか判らん
	protected boolean field_70279_a;
	// 騎乗Entityの速度計算に使用
	protected double speedMultiplier;
	// 加速度増加量
	protected int boatPosRotationIncrements;
	// 位置情報
	protected double boatX;
	protected double boatY;
	protected double boatZ;
	protected double boatYaw;
	protected double boatPitch;
	@SideOnly(Side.CLIENT)
	protected double velocityX;
	@SideOnly(Side.CLIENT)
	protected double velocityY;
	@SideOnly(Side.CLIENT)
	protected double velocityZ;

	public int readyTime;

	public EntitySilkyMelon(World par1World) {
		super(par1World);
		this.field_70279_a = true;
		this.speedMultiplier = 0.07D;
		this.preventEntitySpawning = true;
		this.setSize(0.6F, 0.6F);
		this.yOffset = this.height / 2.0F;
		this.readyTime = 80;
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(17, new Integer(0));
		this.dataWatcher.addObject(18, new Integer(1));
		this.dataWatcher.addObject(19, new Float(0.0F));
		this.dataWatcher.addObject(20, new Integer(0));
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity) {
		return par1Entity.boundingBox;
	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		return this.boundingBox;
	}

	@Override
	public boolean canBePushed() {
		return true;
	}

	public EntitySilkyMelon(World par1World, double par2, double par4, double par6) {
		this(par1World);
		this.setPosition(par2, par4 + this.yOffset, par6);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = par2;
		this.prevPosY = par4;
		this.prevPosZ = par6;
	}

	@Override
	public double getMountedYOffset() {
		return this.height * 0.0D + 0.06000001192092896D;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		if (this.isEntityInvulnerable()) {
			return false;
		} else if (!this.worldObj.isRemote && !this.isDead) {
			this.setForwardDirection(-this.getForwardDirection());
			// 短時間でのダメージの蓄積計算。連続で叩くと破壊される
			this.setTimeSinceHit(10);
			this.setDamageTaken(this.getDamageTaken() + par2 * 10.0F);
			this.setBeenAttacked();
			// ダメージソースを確認
			boolean flag = par1DamageSource.getEntity() instanceof EntityPlayer
					&& ((EntityPlayer) par1DamageSource.getEntity()).capabilities.isCreativeMode;
			// 起爆トリガー
			boolean explode = par1DamageSource.isExplosion() || par1DamageSource.isProjectile()
					|| !(par1DamageSource.getEntity() instanceof EntityPlayer);

			EntityLivingBase igniter = null;
			if (par1DamageSource instanceof EntityDamageSourceIndirect) {
				Entity ent = ((EntityDamageSourceIndirect) par1DamageSource).getEntity();
				if (ent instanceof EntityLivingBase) {
					igniter = (EntityLivingBase) ent;
				}

			}

			// 壊れるときの動作
			if ((flag || this.getDamageTaken() > 40.0F) && !explode) {
				// 起爆条件を満たさずに40ダメージ与えれば平和的にアイテム化する
				if (this.riddenByEntity != null) {
					this.riddenByEntity.mountEntity(this);
				}

				if (!flag) {
					this.func_145778_a(this.dropItem(), 1, 0.0F);
				}

				this.setDead();
			} else if (explode && this.getExploded() < 1) {
				// 起爆条件を満たしたので爆発
				this.explode(igniter);
			}

			return true;
		} else {
			return true;
		}
	}

	protected void explode(EntityLivingBase igniter) {
		this.setExploded(4);
		if (DCsConfig.canExplodeMelon) {
			float f = 3.0F;
			CustomExplosion explosion = new CustomExplosion(worldObj, this, igniter, this.posX, this.posY, this.posZ,
					f, CustomExplosion.Type.Melon, true);
			explosion.doExplosion();
		}

		if (igniter instanceof EntityPlayer) {
			((EntityPlayer) igniter).triggerAchievement(AchievementRegister.useSilkMelon);
		}

		int X = MathHelper.floor_double(this.posX);
		int Y = MathHelper.floor_double(this.posY);
		int Z = MathHelper.floor_double(this.posZ);

		if (!DCsConfig.melonBreakBlock) {
			for (int i = -2; i < 3; i++) {
				for (int k = -2; k < 3; k++) {
					for (int j = -2; j < 3; j++) {
						if (Y - j >= 0 && Y - j < 255 && !this.worldObj.isAirBlock(X + i, Y - j, Z + k)) {

							Block block = this.worldObj.getBlock(X + i, Y - j, Z + k);
							int meta = this.worldObj.getBlockMetadata(X + i, Y - j, Z + k);
							if (block != null) {
								boolean canBreak = true;
								if (block.getExplosionResistance(this) > 1000
										|| block.getBlockHardness(worldObj, X + i, Y - j, Z + k) < 0)
									canBreak = false;
								if (DCsConfig.fearMelon && Y - j > 0)
									canBreak = true;
								if (DCsConfig.completeFearMelon)
									canBreak = true;

								boolean c = block.hasTileEntity(meta) || !block.renderAsNormalBlock();

								if (canBreak) {
									if (block.hasTileEntity(meta)) {
										// this.worldObj.setBlockToAir(X + i, Y - j, Z + k);
										// 破壊しないようにしてみる
									} else if (block.getMaterial() == Material.water
											|| block.getMaterial() == Material.lava
											|| block instanceof BlockFluidClassic) {
										// 液体は消去
										this.worldObj.setBlockToAir(X + i, Y - j, Z + k);
									} else {
										// 非固形Blockの破壊はコンフィグで可否を設定
										if (!block.renderAsNormalBlock() || DCsConfig.completeFearMelon) {
											ItemStack drop = new ItemStack(block, 1, meta);
											this.worldObj.setBlockToAir(X + i, Y - j, Z + k);
											this.entityDropItem(drop, 1.0F);
										} else {
											this.worldObj.setBlockToAir(X + i, Y - j, Z + k);
										}
									}
								}
							}
						} else {
							continue;
						}
					}
				}
			}
		}

	}

	protected Item dropItem() {
		return Item.getItemFromBlock(DCsAppleMilk.silkyMelon);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void performHurtAnimation() {
		this.setForwardDirection(-this.getForwardDirection());
		this.setTimeSinceHit(10);
		this.setDamageTaken(this.getDamageTaken() * 11.0F);
	}

	@Override
	public boolean canBeCollidedWith() {
		return !this.isDead;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
		if (this.field_70279_a) {
			this.boatPosRotationIncrements = par9 + 5;
		} else {
			double d3 = par1 - this.posX;
			double d4 = par3 - this.posY;
			double d5 = par5 - this.posZ;
			double d6 = d3 * d3 + d4 * d4 + d5 * d5;

			if (d6 <= 1.0D) {
				return;
			}

			this.boatPosRotationIncrements = 3;
		}

		this.boatX = par1;
		this.boatY = par3;
		this.boatZ = par5;
		this.boatYaw = par7;
		this.boatPitch = par8;
		this.motionX = this.velocityX;
		this.motionY = this.velocityY;
		this.motionZ = this.velocityZ;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void setVelocity(double par1, double par3, double par5) {
		this.velocityX = this.motionX = par1;
		this.velocityY = this.motionY = par3;
		this.velocityZ = this.motionZ = par5;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		// 爆発の結果
		int exp = this.getExploded();
		if (this.getExploded() == 1) {
			this.setDead();
		}
		if (exp > 1) {
			AMTLogger.debugInfo("current explode int :" + exp);
			this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY + 1.0D, this.posZ, this.motionX,
					this.motionY, this.motionZ);
			exp--;
			this.setExploded(exp);
		}

		// 被ダメ計算
		if (this.getTimeSinceHit() > 0) {
			this.setTimeSinceHit(this.getTimeSinceHit() - 1);
		}

		if (this.getDamageTaken() > 0.0F) {
			this.setDamageTaken(this.getDamageTaken() - 1.0F);
		}

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		byte b0 = 5;
		double d0 = 0.0D;

		// 当たり判定が水中にあるか？
		for (int i = 0; i < b0; ++i) {
			double d1 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (i + 0) / b0 - 0.125D;
			double d2 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (i + 1) / b0 - 0.125D;
			AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox(this.boundingBox.minX, d1,
					this.boundingBox.minZ, this.boundingBox.maxX, d2, this.boundingBox.maxZ);

			// 浮力
			if (this.worldObj.isAABBInMaterial(axisalignedbb, Material.water)) {
				d0 += 1.0D / b0;
			}
		}

		double d3 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
		double d4;
		double d5;

		// 水しぶき生成
		if (d3 > 0.26249999999999996D && this.inWater) {
			d4 = Math.cos(this.rotationYaw * Math.PI / 180.0D);
			d5 = Math.sin(this.rotationYaw * Math.PI / 180.0D);

			for (int j = 0; j < 1.0D + d3 * 60.0D; ++j) {
				double d6 = this.rand.nextFloat() * 2.0F - 1.0F;
				double d7 = (this.rand.nextInt(2) * 2 - 1) * 0.7D;
				double d8;
				double d9;

				if (this.rand.nextBoolean()) {
					d8 = this.posX - d4 * d6 * 0.8D + d5 * d7;
					d9 = this.posZ - d5 * d6 * 0.8D - d4 * d7;
					this.worldObj.spawnParticle("splash", d8, this.posY - 0.125D, d9, this.motionX, this.motionY,
							this.motionZ);
				} else {
					d8 = this.posX + d4 + d5 * d6 * 0.7D;
					d9 = this.posZ + d5 - d4 * d6 * 0.7D;
					this.worldObj.spawnParticle("splash", d8, this.posY - 0.125D, d9, this.motionX, this.motionY,
							this.motionZ);
				}
			}
		}

		double d10;
		double d11;

		// 向きと加速度の変化？
		if (this.worldObj.isRemote && this.field_70279_a) {
			if (this.boatPosRotationIncrements > 0) {
				d4 = this.posX + (this.boatX - this.posX) / this.boatPosRotationIncrements;
				d5 = this.posY + (this.boatY - this.posY) / this.boatPosRotationIncrements;
				d11 = this.posZ + (this.boatZ - this.posZ) / this.boatPosRotationIncrements;
				d10 = MathHelper.wrapAngleTo180_double(this.boatYaw - this.rotationYaw);
				this.rotationYaw = (float) (this.rotationYaw + d10 / this.boatPosRotationIncrements);
				this.rotationPitch = (float) (this.rotationPitch + (this.boatPitch - this.rotationPitch)
						/ this.boatPosRotationIncrements);
				--this.boatPosRotationIncrements;
				this.setPosition(d4, d5, d11);
				this.setRotation(this.rotationYaw, this.rotationPitch);
			} else {
				d4 = this.posX + this.motionX;
				d5 = this.posY + this.motionY;
				d11 = this.posZ + this.motionZ;
				this.setPosition(d4, d5, d11);

				if (this.onGround) {
					this.motionX *= 0.5D;
					this.motionY *= 0.5D;
					this.motionZ *= 0.5D;
				}

				this.motionX *= 0.9900000095367432D;
				this.motionY *= 0.949999988079071D;
				this.motionZ *= 0.9900000095367432D;
			}
		} else {
			// 浮力によるY速度調整
			if (d0 < 1.0D) {
				d4 = d0 * 2.0D - 1.0D;
				this.motionY += 0.03999999910593033D * d4;
			} else {
				if (this.motionY < 0.0D) {
					this.motionY /= 2.0D;
				}

				this.motionY += 0.007000000216066837D;
			}

			// 乗っているEntityの前進速度
			if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase) {
				d4 = ((EntityLivingBase) this.riddenByEntity).moveForward;

				if (d4 > 0.0D) {
					d5 = -Math.sin(this.riddenByEntity.rotationYaw * (float) Math.PI / 180.0F);
					d11 = Math.cos(this.riddenByEntity.rotationYaw * (float) Math.PI / 180.0F);
					this.motionX += d5 * this.speedMultiplier * 0.05000000074505806D;
					this.motionZ += d11 * this.speedMultiplier * 0.05000000074505806D;
				}
			}

			d4 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
			double speedLimit = 0.35D;
			int speedMag = 0;

			if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase) {
				EntityLivingBase living = (EntityLivingBase) this.riddenByEntity;
				if (living.isPotionActive(Potion.moveSpeed)) {
					speedMag = living.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1;
				}
			}

			if (speedMag > 0) {
				speedLimit = 0.35D + 0.1D * speedMag;
			}

			// 速度制限
			if (d4 > speedLimit) {
				d5 = speedLimit / d4;
				this.motionX *= d5;
				this.motionZ *= d5;
				d4 = speedLimit;
			}

			// Entityの前進速度を加味した速度（speedMultipler）の加算
			if (d4 > d3 && this.speedMultiplier < speedLimit) {
				this.speedMultiplier += (speedLimit - this.speedMultiplier) / (speedLimit * 100);

				if (this.speedMultiplier > speedLimit) {
					this.speedMultiplier = speedLimit;
				}
			} else {
				this.speedMultiplier -= (this.speedMultiplier - 0.07D) / (speedLimit * 100);

				if (this.speedMultiplier < 0.07D) {
					this.speedMultiplier = 0.07D;
				}
			}

			// 地上では止まっている
			if (this.onGround) {
				this.motionX *= 0.5D;
				this.motionY *= 0.5D;
				this.motionZ *= 0.5D;
			}

			// 最終的にEntityを動かす
			this.moveEntity(this.motionX, this.motionY, this.motionZ);

			// 衝突時に壊れるか
			if (this.isCollidedHorizontally && d3 > 0.3D) {
				// 速度0.30D以上だとドロップ化・実績解除
				this.onCrash();
			} else {
				this.motionX *= 0.9900000095367432D;
				this.motionY *= 0.949999988079071D;
				this.motionZ *= 0.9900000095367432D;
			}

			// また向きを調整している？
			this.rotationPitch = 0.0F;
			d5 = this.rotationYaw;
			d11 = this.prevPosX - this.posX;
			d10 = this.prevPosZ - this.posZ;

			if (d11 * d11 + d10 * d10 > 0.001D) {
				d5 = ((float) (Math.atan2(d10, d11) * 180.0D / Math.PI));
			}

			double d12 = MathHelper.wrapAngleTo180_double(d5 - this.rotationYaw);

			if (d12 > 20.0D) {
				d12 = 20.0D;
			}

			if (d12 < -20.0D) {
				d12 = -20.0D;
			}

			this.rotationYaw = (float) (this.rotationYaw + d12);
			this.setRotation(this.rotationYaw, this.rotationPitch);

			// 当たり判定が乗り物の分拡大されているぽい
			if (!this.worldObj.isRemote) {
				List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this,
						this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
				int l;

				if (list != null && !list.isEmpty()) {
					for (l = 0; l < list.size(); ++l) {
						Entity entity = (Entity) list.get(l);

						if (entity != this.riddenByEntity && entity.canBePushed() && entity instanceof EntityMelonBomb) {
							entity.applyEntityCollision(this);
						}
					}
				}

				// 雪と睡蓮を破壊
				for (l = 0; l < 4; ++l) {
					int i1 = MathHelper.floor_double(this.posX + (l % 2 - 0.5D) * 0.8D);
					int j1 = MathHelper.floor_double(this.posZ + (l / 2 - 0.5D) * 0.8D);

					for (int k1 = 0; k1 < 2; ++k1) {
						int l1 = MathHelper.floor_double(this.posY) + k1;
						Block i2 = this.worldObj.getBlock(i1, l1, j1);

						if (i2 == Blocks.snow) {
							this.worldObj.setBlockToAir(i1, l1, j1);
						} else if (i2 == Blocks.waterlily) {
							this.worldObj.func_147480_a(i1, l1, j1, true);
						}
					}
				}

				// 乗っているEntityが死んだら騎乗を解除
				if (this.riddenByEntity != null && this.riddenByEntity.isDead) {
					this.riddenByEntity = null;
				}
			}
		}
	}

	// 乗っているEntityの位置調整
	@Override
	public void updateRiderPosition() {
		if (this.riddenByEntity != null) {
			double d0 = Math.cos(this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			double d1 = Math.sin(this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			this.riddenByEntity.setPosition(this.posX + d0,
					this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + d1);
		}
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
	}

	@SideOnly(Side.CLIENT)
	@Override
	public float getShadowSize() {
		return 0.0F;
	}

	@Override
	public boolean interactFirst(EntityPlayer par1EntityPlayer) {
		// 他人が乗っている場合は乗れない
		ItemStack item = par1EntityPlayer.inventory.getCurrentItem();
		if (item != null && item.getItem() == DCsAppleMilk.chopsticks) {
			if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer
					&& this.riddenByEntity != par1EntityPlayer) {
				return true;
			} else {
				if (!this.worldObj.isRemote) {
					par1EntityPlayer.mountEntity(this);
				}

				return true;
			}
		} else if (item != null
				&& (item.getItem() == DCsAppleMilk.firestarter || item.getItem() instanceof ItemFlintAndSteel)) {
			if (item.attemptDamageItem(1, this.worldObj.rand)) {
				item.stackSize = 0;
			}

			par1EntityPlayer.triggerAchievement(AchievementRegister.useSilkMelon);
			if (!this.worldObj.isRemote)
				this.explode(par1EntityPlayer);
			return true;
		} else if (item != null && item.getItem().getItemUseAction(item) == EnumAction.bow) {
			return false;
		} else {
			if (!par1EntityPlayer.inventory.addItemStackToInventory(new ItemStack(this.dropItem(), 1))) {
				if (!worldObj.isRemote)
					par1EntityPlayer.entityDropItem(new ItemStack(this.dropItem(), 1), 1.0F);
			}

			this.setDead();
			this.worldObj.playSoundAtEntity(par1EntityPlayer, "random.pop", 0.4F, 1.8F);
			return true;
		}

	}

	public void setDamageTaken(float par1) {
		this.dataWatcher.updateObject(19, Float.valueOf(par1));
	}

	public float getDamageTaken() {
		return this.dataWatcher.getWatchableObjectFloat(19);
	}

	public void setTimeSinceHit(int par1) {
		this.dataWatcher.updateObject(17, Integer.valueOf(par1));
	}

	public int getTimeSinceHit() {
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	public void setForwardDirection(int par1) {
		this.dataWatcher.updateObject(18, Integer.valueOf(par1));
	}

	public int getForwardDirection() {
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	public void setExploded(int par1) {
		this.dataWatcher.updateObject(20, Integer.valueOf(par1));
	}

	public int getExploded() {
		return this.dataWatcher.getWatchableObjectInt(20);
	}

	@SideOnly(Side.CLIENT)
	public void func_70270_d(boolean par1) {
		this.field_70279_a = par1;
	}

	protected void onCrash() {
		if (!this.worldObj.isRemote && !this.isDead) {
			EntityLivingBase rider = null;
			if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase) {
				rider = (EntityLivingBase) this.riddenByEntity;
			}
			this.setDead();
			this.explode(rider);
		}
	}
}
