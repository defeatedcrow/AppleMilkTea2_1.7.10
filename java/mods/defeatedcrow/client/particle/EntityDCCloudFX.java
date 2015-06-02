package mods.defeatedcrow.client.particle;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityDCCloudFX extends EntityFX {

	float initialSize;

	public EntityDCCloudFX(World par1World, double par2, double par4, double par6, double par8, double par10,
			double par12) {
		super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
		float f = 2.5F;
		this.motionX *= 0.10000000149011612D;
		this.motionY *= 0.10000000149011612D;
		this.motionZ *= 0.10000000149011612D;
		this.motionX += par8;
		this.motionY += par10;
		this.motionZ += par12;
		this.particleScale *= 0.75F;
		this.particleScale *= f;
		this.initialSize = this.particleScale;
		this.particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.3D));
		this.particleMaxAge = (int) ((float) this.particleMaxAge * f);
		this.noClip = false;
	}

	@Override
	public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6,
			float par7) {
		float f6 = ((float) this.particleAge + par2) / (float) this.particleMaxAge * 32.0F;

		if (f6 < 0.0F) {
			f6 = 0.0F;
		}

		if (f6 > 1.0F) {
			f6 = 1.0F;
		}

		this.particleScale = this.initialSize * f6;
		super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.setDead();
		}

		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9599999785423279D;
		this.motionY *= 0.9599999785423279D;
		this.motionZ *= 0.9599999785423279D;

	}

	@Override
	public int getFXLayer() {
		return 2;
	}
}
