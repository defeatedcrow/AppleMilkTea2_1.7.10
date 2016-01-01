package mods.defeatedcrow.client.entity;

import mods.defeatedcrow.client.entity.base.ModelTeaCup;
import mods.defeatedcrow.common.block.edible.BlockFilledCup2;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.entity.edible.PlaceableCup2;
import mods.defeatedcrow.handler.Util;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCup2Entity extends Render {

	private static final ResourceLocation cupTex = new ResourceLocation("defeatedcrow:textures/blocks/porcelain.png");
	private static final ResourceLocation cupSummerTex = new ResourceLocation(
			"defeatedcrow:textures/blocks/blueglass.png");
	private ModelTeaCup model = new ModelTeaCup();

	public RenderCup2Entity() {
		this.shadowSize = 0.3F * Util.getCupSize();
		this.model = new ModelTeaCup();
	}

	/**
	 * The render method used in RenderBoat that renders the boat model.
	 */
	public void render(PlaceableCup2 entity, double posX, double posY, double posZ, float round, float yaw) {
		ModelTeaCup model = this.model;
		byte l = (byte) entity.getItemMetadata();
		float size = Util.getCupScale();
		float y = (float) posY + 1.55F * size - 0.10F * size;

		String innerTexPass = "defeatedcrow:textures/blocks/contents" + BlockFilledCup2.contents[l] + ".png";
		ResourceLocation innerTex = new ResourceLocation(innerTexPass);
		this.bindTexture(innerTex);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);

		GL11.glColor4f(1.5F, 1.5F, 1.5F, 0.8F);
		GL11.glTranslatef((float) posX, (float) y, (float) posZ);
		GL11.glScalef(size, size, size);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(round, 0.0F, 1.0F, 0.0F);
		model.renderContents((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

		if (DCsConfig.useSummerRender) {
			this.bindTexture(cupSummerTex);

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
			GL11.glTranslatef((float) posX, (float) y, (float) posZ);
			GL11.glScalef(size, size, size);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glRotatef(round, 0.0F, 1.0F, 0.0F);
			model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		} else {
			this.bindTexture(cupTex);

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float) posX, (float) y, (float) posZ);
			GL11.glScalef(size, size, size);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glRotatef(round, 0.0F, 1.0F, 0.0F);
			model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
	}

	protected ResourceLocation getMelonTextures(PlaceableCup2 par1Entity) {
		return cupTex;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getMelonTextures((PlaceableCup2) par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.render((PlaceableCup2) par1Entity, par2, par4, par6, par8, par9);
	}
}
