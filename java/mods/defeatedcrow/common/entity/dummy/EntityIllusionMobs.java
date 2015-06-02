package mods.defeatedcrow.common.entity.dummy;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityIllusionMobs extends Entity {

	private int livingTime = 0;

	public EntityIllusionMobs(World world) {
		super(world);
		this.preventEntitySpawning = true;
		this.setSize(0.6F, 2.0F);
		this.yOffset = this.height / 2.0F;
		this.livingTime = 0;
	}

	public EntityIllusionMobs(World par1World, double par2, double par4, double par6, float yaw) {
		this(par1World);
		this.setPosition(par2, par4 + (double) this.yOffset, par6);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = par2;
		this.prevPosY = par4;
		this.prevPosZ = par6;
		this.rotationYaw = yaw;
	}

	@Override
	protected boolean canTriggerWalking() {
		return true;
	}

	@Override
	protected void entityInit() {
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
		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		// 攻撃されると消えてしまう
		this.setDead();
		return true;
	}

	@Override
	public boolean canBeCollidedWith() {
		return !this.isDead;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (this.livingTime > 60) {
			this.setDead();
		} else {
			this.livingTime++;
		}

		byte b0 = 5;
		double d0 = 0.0D;

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
			}
		}

		if (d0 < 1.0D) {
			double d4 = d0 * 2.0D - 1.0D;
			this.motionY += 0.03999999910593033D * d4;
		} else {
			if (this.motionY < 0.0D) {
				this.motionY /= 2.0D;
			}

			this.motionY += 0.007000000216066837D;
		}

		if (this.onGround) {
			this.motionX *= 0.5D;
			this.motionY *= 0.5D;
			this.motionZ *= 0.5D;
		}
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
	}

}
