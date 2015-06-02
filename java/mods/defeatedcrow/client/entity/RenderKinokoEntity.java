package mods.defeatedcrow.client.entity;

import mods.defeatedcrow.client.model.model.ModelKinoko;
import mods.defeatedcrow.common.entity.EntityKinoko;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderKinokoEntity extends Render {

	private static final ResourceLocation redTex = new ResourceLocation("defeatedcrow:textures/entity/kinoko_red.png");
	private static final ResourceLocation brownTex = new ResourceLocation(
			"defeatedcrow:textures/entity/kinoko_brown.png");

	/** instance of ModelBoat for rendering */
	protected ModelKinoko model;

	public RenderKinokoEntity() {
		this.shadowSize = 0.5F;
		this.model = new ModelKinoko();
	}

	/**
	 * The render method used in RenderBoat that renders the boat model.
	 */
	public void render(EntityKinoko entity, double posX, double posY, double posZ, float round, float yaw) {
		ModelKinoko model = this.model;
		byte l = (byte) entity.getItemMetadata();

		if (l == 0) {
			this.bindTexture(redTex);
		} else {
			this.bindTexture(brownTex);
		}
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) posX, (float) posY + 1.25F, (float) posZ);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(round, 0.0F, 1.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

	}

	protected ResourceLocation getMelonTextures(EntityKinoko par1Entity) {
		return redTex;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getMelonTextures((EntityKinoko) par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.render((EntityKinoko) par1Entity, par2, par4, par6, par8, par9);
	}
}
