package mods.defeatedcrow.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityOrbFX extends EntityFX {

	// 発生地点のY座標。消滅条件に利用する
	double orginalPosY;

	public EntityOrbFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
		super(par1World, par2, par4, par6);
		this.orginalPosY = par4;
		this.motionX = 0.0D;
		this.motionY = par10;
		this.motionZ = 0.0D;
		this.noClip = false;
	}

	@Override
	public void setAlphaF(float par1) {
		this.particleAlpha = 0.5F;
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		// 小さくする
		if (this.particleScale > 0.05F) {
			this.particleScale = this.particleScale - 0.05F;
		} else {
			this.setDead();
		}

		// 発生地点から1m以上上昇したら消滅する
		if (this.posY > orginalPosY + 1.0D && this.particleAge++ >= this.particleMaxAge) {
			this.setDead();
		}
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
	}

	@Override
	public int getFXLayer() {
		return 2;
	}

}
