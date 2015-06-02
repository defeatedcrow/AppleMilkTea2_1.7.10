package mods.defeatedcrow.client.entity;

import mods.defeatedcrow.client.model.model.ModelYuzuBullet;
import mods.defeatedcrow.common.entity.EntityYuzuBullet;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderYuzuBullet extends Render {

	private static final ResourceLocation melonTextures = new ResourceLocation(
			"defeatedcrow:textures/entity/yuzubullet.png");

	/** instance of ModelBoat for rendering */
	protected ModelBase modelMissile;

	public RenderYuzuBullet(ModelBase par1ModelBase) {
		super();
		this.shadowSize = 0.5F;
		this.modelMissile = par1ModelBase;
	}

	public void renderMissile(EntityYuzuBullet par1Entity, double posX, double posY, double posZ, float round, float yaw) {
		ModelYuzuBullet model = (ModelYuzuBullet) this.modelMissile;
		boolean b = par1Entity.isBurning();

		this.bindEntityTexture(par1Entity);
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		if (b) {
			GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
		} else {
			GL11.glColor4f(3.0F, 1.0F, 1.0F, 1.0F);
		}
		GL11.glTranslatef((float) posX, (float) posY + 1.0F, (float) posZ);
		GL11.glRotatef(par1Entity.prevRotationYaw + (par1Entity.rotationYaw - par1Entity.prevRotationYaw) * yaw, 0.0F,
				1.0F, 0.0F);
		GL11.glRotatef(par1Entity.prevRotationPitch + (par1Entity.rotationPitch - par1Entity.prevRotationPitch) * yaw,
				-1.0F, 0.0F, 0.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	protected ResourceLocation getMelonTextures(EntityYuzuBullet par1Entity) {
		return melonTextures;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getMelonTextures((EntityYuzuBullet) par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.renderMissile((EntityYuzuBullet) par1Entity, par2, par4, par6, par8, par9);
	}
}
