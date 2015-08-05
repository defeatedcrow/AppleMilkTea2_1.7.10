package mods.defeatedcrow.common.entity.edible;

import java.util.List;

import mods.defeatedcrow.api.edibles.IEdibleItem;
import mods.defeatedcrow.api.events.AMTFoodEntityRightClickEvent;
import mods.defeatedcrow.client.particle.EntityBlinkFX;
import mods.defeatedcrow.client.particle.EntityDCCloudFX;
import mods.defeatedcrow.client.particle.ParticleTex;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class PlaceableFoods extends Entity {

	protected int containerMeta = 0;
	protected boolean allowChops = true;

	private boolean field_70279_a;
	private double speedMultiplier;
	private int posRotationIncrements;
	private double X;
	private double Y;
	private double Z;
	private double yaw;
	private double pitch;
	@SideOnly(Side.CLIENT)
	private double velocityX;
	@SideOnly(Side.CLIENT)
	private double velocityY;
	@SideOnly(Side.CLIENT)
	private double velocityZ;

	public PlaceableFoods(World world) {
		super(world);
		this.field_70279_a = true;
		this.speedMultiplier = 0.07D;
		this.setSize(0.5F * getSize(), 0.3F * getSize());
		this.yOffset = this.height;
	}

	public PlaceableFoods(World world, boolean chops, ItemStack item) {
		this(world);
		this.allowChops = chops;
		this.setContainerMeta(item.getItemDamage());
	}

	public PlaceableFoods(World world, boolean chops, ItemStack item, double x, double y, double z) {
		this(world, chops, item);
		this.setPosition(x, y + this.yOffset, z);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	/*
	 * Entityとしての基礎情報
	 * ・押したり流したり出来、水には浮く
	 * ・左クリックではドロップアイテム化する
	 * ・右クリック回収できる
	 * ・箸を持って右クリックで食べられる
	 * ・乗れない
	 */

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(17, new Integer(0));
		this.dataWatcher.addObject(18, new Integer(1));
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {

		this.setContainerMeta(nbt.getShort("meta"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {

		nbt.setShort("meta", (short) this.getItemMetadata());
	}

	public int getItemMetadata() {
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	public void setContainerMeta(int m) {
		this.containerMeta = m;
		this.dataWatcher.updateObject(17, Integer.valueOf(m));
	}

	public void setForwardDirection(int par1) {
		this.dataWatcher.updateObject(18, Integer.valueOf(par1));
	}

	public int getForwardDirection() {
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return !this.isDead;
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

	@Override
	public double getMountedYOffset() {
		return (double) getScale() * 0.4D - 0.06D;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		if (this.isEntityInvulnerable()) {
			return false;
		} else if (!this.worldObj.isRemote && !this.isDead) {
			this.setBeenAttacked();
			if (par1DamageSource instanceof EntityDamageSource) {
				Entity by = ((EntityDamageSource) par1DamageSource).getEntity();
				if (by != null && by instanceof EntityPlayer) {
					if (DCsAppleMilk.debugMode) {
						EntityPlayer player = (EntityPlayer) by;
						player.addChatComponentMessage(new ChatComponentText("current metadata : "
								+ this.getItemMetadata() + ", chopsticks : " + this.allowChops));
					}

					ItemStack drop = this.returnItem();
					if (drop != null) {
						this.worldObj.playSoundAtEntity(this, "random.pop", 0.4F, 1.8F);
						this.entityDropItem(drop, 0.2F);

						if (this.riddenByEntity != null) {
							this.riddenByEntity.mountEntity(this);
						}

						this.setDead();
					}

				}

			}
			return true;
		} else {
			return true;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
		if (this.field_70279_a) {
			this.posRotationIncrements = par9 + 5;
		} else {
			double d3 = par1 - this.posX;
			double d4 = par3 - this.posY;
			double d5 = par5 - this.posZ;
			double d6 = d3 * d3 + d4 * d4 + d5 * d5;

			if (d6 <= 1.0D) {
				return;
			}

			this.posRotationIncrements = 3;
		}

		this.X = par1;
		this.Y = par3;
		this.Z = par5;
		this.yaw = (double) par7;
		this.pitch = (double) par8;
		this.motionX = this.velocityX;
		this.motionY = this.velocityY;
		this.motionZ = this.velocityZ;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setVelocity(double par1, double par3, double par5) {
		this.velocityX = this.motionX = par1;
		this.velocityY = this.motionY = par3;
		this.velocityZ = this.motionZ = par5;
	}

	// 基本的にはボートの流用。
	@Override
	public void onUpdate() {
		super.onUpdate();

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		byte b0 = 5;
		double d0 = 0.0D;

		boolean spl = false;

		// 当たり判定が水中にあるか？
		for (int i = 0; i < b0; ++i) {
			double d1 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double) (i + 0)
					/ (double) b0 - 0.125D;
			double d2 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double) (i + 1)
					/ (double) b0 - 0.125D;
			AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox(this.boundingBox.minX, d1,
					this.boundingBox.minZ, this.boundingBox.maxX, d2, this.boundingBox.maxZ);

			// 浮力
			if (this.worldObj.isAABBInMaterial(axisalignedbb, Material.water)) {
				d0 += 1.0D / (double) b0;
				spl = true;
			}
		}

		// ホッパーの上に来るとドロップアイテム化する機能をつけた
		if (!this.worldObj.isRemote) {
			int i = MathHelper.floor_double(posX);
			int j = MathHelper.floor_double(posY);
			int k = MathHelper.floor_double(posZ);

			if (!this.worldObj.isAirBlock(i, j - 1, k) && this.worldObj.getTileEntity(i, j - 1, k) != null) {
				TileEntity tile = this.worldObj.getTileEntity(i, j - 1, k);
				if (tile instanceof IHopper) {
					ItemStack drop = this.returnItem();
					this.entityDropItem(drop, 0.1F);
					this.setDead();
				}
			}
		}

		double d3 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
		double d4;
		double d5;

		// 水しぶき生成
		if (d3 > 0.26249999999999996D && spl) {
			d4 = Math.cos((double) this.rotationYaw * Math.PI / 180.0D);
			d5 = Math.sin((double) this.rotationYaw * Math.PI / 180.0D);

			for (int j = 0; (double) j < 1.0D + d3 * 60.0D; ++j) {
				double d6 = (double) (this.rand.nextFloat() * 2.0F - 1.0F);
				double d7 = (double) (this.rand.nextInt(2) * 2 - 1) * 0.7D;
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

		if (this.worldObj.isRemote && this.field_70279_a) {
			if (this.posRotationIncrements > 0) {
				d4 = this.posX + (this.X - this.posX) / (double) this.posRotationIncrements;
				d5 = this.posY + (this.Y - this.posY) / (double) this.posRotationIncrements;
				d11 = this.posZ + (this.Z - this.posZ) / (double) this.posRotationIncrements;
				d10 = MathHelper.wrapAngleTo180_double(this.yaw - (double) this.rotationYaw);
				this.rotationYaw = (float) ((double) this.rotationYaw + d10 / (double) this.posRotationIncrements);
				this.rotationPitch = (float) ((double) this.rotationPitch + (this.pitch - (double) this.rotationPitch)
						/ (double) this.posRotationIncrements);
				--this.posRotationIncrements;
				this.setPosition(d4, d5, d11);
				this.setRotation(this.rotationYaw, this.rotationPitch);
			} else {
				d4 = this.posX + this.motionX;
				d5 = this.posY + this.motionY;
				d11 = this.posZ + this.motionZ;
				this.setPosition(d4, d5, d11);

				this.motionX *= 0.5D;
				this.motionY *= 0.5D;
				this.motionZ *= 0.5D;

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
				d4 = (double) ((EntityLivingBase) this.riddenByEntity).moveForward;

				if (d4 > 0.0D) {
					d5 = -Math.sin((double) (this.riddenByEntity.rotationYaw * (float) Math.PI / 180.0F));
					d11 = Math.cos((double) (this.riddenByEntity.rotationYaw * (float) Math.PI / 180.0F));
					this.motionX += d5 * this.speedMultiplier * 0.05000000074505806D;
					this.motionZ += d11 * this.speedMultiplier * 0.05000000074505806D;
				}
			}

			d4 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

			// 速度制限
			if (d4 > 0.35D) {
				d5 = 0.35D / d4;
				this.motionX *= d5;
				this.motionZ *= d5;
				d4 = 0.35D;
			}

			// Entityの前進速度を加味した速度（speedMultipler）の加算
			if (d4 > d3 && this.speedMultiplier < 0.35D) {
				this.speedMultiplier += (0.10D - this.speedMultiplier) / 35.0D;

				if (this.speedMultiplier > 0.35D) {
					this.speedMultiplier = 0.35D;
				}
			} else {
				this.speedMultiplier -= (this.speedMultiplier - 0.07D) / 35.0D;

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

			this.motionX *= 0.9900000095367432D;
			this.motionY *= 0.949999988079071D;
			this.motionZ *= 0.9900000095367432D;

			// また向きを調整している？
			this.rotationPitch = 0.0F;
			d5 = (double) this.rotationYaw;
			d11 = this.prevPosX - this.posX;
			d10 = this.prevPosZ - this.posZ;

			if (d11 * d11 + d10 * d10 > 0.001D) {
				d5 = (double) ((float) (Math.atan2(d10, d11) * 180.0D / Math.PI));
			}

			double d12 = MathHelper.wrapAngleTo180_double(d5 - (double) this.rotationYaw);

			if (d12 > 20.0D) {
				d12 = 20.0D;
			}

			if (d12 < -20.0D) {
				d12 = -20.0D;
			}

			this.rotationYaw = (float) ((double) this.rotationYaw + d12);
			this.setRotation(this.rotationYaw, this.rotationPitch);

			// 当たり判定が乗り物の分拡大されているぽい
			if (!this.worldObj.isRemote) {
				List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this,
						this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
				int l;

				if (list != null && !list.isEmpty()) {
					for (l = 0; l < list.size(); ++l) {
						Entity entity = (Entity) list.get(l);

						if (entity != this.riddenByEntity && entity.canBePushed()) {
							entity.applyEntityCollision(this);
						}
					}
				}

				// 乗っているEntityが死んだら騎乗を解除
				if (this.riddenByEntity != null && this.riddenByEntity.isDead) {
					this.riddenByEntity = null;
				}
			}
		}

		if (this.worldObj.isRemote) {
			this.generateRandomParticles(this.particleNumber());
		}
	}

	// 乗っているEntityの位置調整
	@Override
	public void updateRiderPosition() {
		if (this.riddenByEntity != null) {
			double d0 = Math.cos((double) this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			double d1 = Math.sin((double) this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			this.riddenByEntity.setPosition(this.posX + d0,
					this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + d1);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.3F;
	}

	@Override
	public boolean interactFirst(EntityPlayer par1EntityPlayer) {
		ItemStack item = par1EntityPlayer.inventory.getCurrentItem();

		AMTFoodEntityRightClickEvent event = new AMTFoodEntityRightClickEvent(worldObj, par1EntityPlayer, item, this);
		MinecraftForge.EVENT_BUS.post(event);

		if (event.isCanceled()) {
			return true;
		}

		if (item != null && item.getItem() == DCsAppleMilk.chopsticks) {
			ItemStack has = this.returnItem();

			// ふつうはEdibleEntityItemBlockのはず
			if (has != null && has.getItem() instanceof IEdibleItem) {
				boolean flag = false;
				IEdibleItem edible = (IEdibleItem) has.getItem();

				has.getItem().onEaten(has.copy(), worldObj, par1EntityPlayer);
				flag = true;

				if (flag) {
					this.setDead();
					this.worldObj.playSoundAtEntity(par1EntityPlayer, "random.pop", 0.4F, 1.8F);
				}
				return true;
			} else {
				return false;
			}
		} else if (item != null && item.getItem() == Items.stick) {
			if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer
					&& this.riddenByEntity != par1EntityPlayer) {
				return true;
			} else {
				if (!this.worldObj.isRemote) {
					par1EntityPlayer.mountEntity(this);
				}

				return true;
			}
		} else {
			ItemStack has = this.returnItem();

			if (has != null && !par1EntityPlayer.inventory.addItemStackToInventory(has)) {
				if (!worldObj.isRemote)
					par1EntityPlayer.entityDropItem(has, 1.0F);
			}

			this.setDead();
			this.worldObj.playSoundAtEntity(par1EntityPlayer, "random.pop", 0.4F, 1.8F);
			return true;
		}

	}

	/* 必ずオーバーライドする。 */
	protected abstract ItemStack returnItem();

	@SideOnly(Side.CLIENT)
	public void func_70270_d(boolean par1) {
		this.field_70279_a = par1;
	}

	protected byte particleNumber() {
		return 0;
	}

	protected float getScale() {
		return 1.0F;
	}

	protected float getSize() {
		return 1.0F;
	}

	@SideOnly(Side.CLIENT)
	protected void generateRandomParticles(byte b) {
		double d0 = (double) (this.posX + this.rand.nextFloat() - 0.5D);
		double d1 = (double) (this.posY + 0.2F + this.rand.nextFloat() - 0.5D);
		double d2 = (double) (this.posZ + this.rand.nextFloat() - 0.5D);
		double d3 = 0.0099999988079071D;
		double d4 = 0.0099999988079071D;
		double d5 = 0.0099999988079071D;

		// とりあえずbyte型で分けてる
		if (this.worldObj.isRemote && !DCsConfig.noRenderFoodsSteam && this.worldObj.rand.nextInt(6) == 0) {
			if (b == 1) {
				EntityBlinkFX cloud = new EntityBlinkFX(this.worldObj, d0, d1, d2, 0.0D, d4, 0.0D);
				cloud.setParticleIcon(ParticleTex.getInstance().getIcon("blink"));
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
			} else if (b == 2) {
				EntityDCCloudFX cloud = new EntityDCCloudFX(this.worldObj, d0, d1, d2, 0.0D, d3, 0.0D);
				cloud.setParticleIcon(ParticleTex.getInstance().getIcon("cloud"));
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
			}
		}
	}

}
