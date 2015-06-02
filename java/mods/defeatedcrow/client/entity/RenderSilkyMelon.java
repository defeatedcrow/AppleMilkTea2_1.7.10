package mods.defeatedcrow.client.entity;

import mods.defeatedcrow.client.model.model.ModelMelonBomb;
import mods.defeatedcrow.common.entity.EntitySilkyMelon;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSilkyMelon extends Render {

	private static final ResourceLocation melonTextures = new ResourceLocation(
			"defeatedcrow:textures/entity/compressedmelon_silky.png");

	/** instance of ModelBoat for rendering */
	protected ModelMelonBomb modelMelonBomb;

	public RenderSilkyMelon() {
		this.shadowSize = 0.5F;
		this.modelMelonBomb = new ModelMelonBomb();
	}

	public void renderMelonBomb(EntitySilkyMelon par1EntityMelon, double posX, double posY, double posZ, float round,
			float yaw) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) posX, (float) posY, (float) posZ);
		GL11.glRotatef(180.0F - round, 0.0F, 1.0F, 0.0F);
		float f2 = (float) par1EntityMelon.getTimeSinceHit() - yaw;
		float f3 = par1EntityMelon.getDamageTaken() - yaw;

		if (f3 < 0.0F) {
			f3 = 0.0F;
		}

		if (f2 > 0.0F) {
			GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * (float) par1EntityMelon.getForwardDirection(), 1.0F,
					0.0F, 0.0F);
		}

		float f4 = 0.75F;
		GL11.glScalef(f4, f4, f4);
		GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
		this.bindEntityTexture(par1EntityMelon);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.modelMelonBomb.render(par1EntityMelon, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	protected ResourceLocation getMelonTextures(EntitySilkyMelon par1Entity) {
		return melonTextures;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getMelonTextures((EntitySilkyMelon) par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.renderMelonBomb((EntitySilkyMelon) par1Entity, par2, par4, par6, par8, par9);
	}
}
