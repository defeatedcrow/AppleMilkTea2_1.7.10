package mods.defeatedcrow.client.entity;

import mods.defeatedcrow.client.model.model.ModelIceCream;
import mods.defeatedcrow.common.entity.edible.PlaceableIcecream;
import mods.defeatedcrow.handler.Util;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderIceCreamEntity extends Render {

	private static final ResourceLocation texture = new ResourceLocation("defeatedcrow:textures/entity/icecream.png");

	/** instance of ModelBoat for rendering */
	protected ModelIceCream model;

	public RenderIceCreamEntity() {
		this.shadowSize = 0.3F;
		this.model = new ModelIceCream();
	}

	/**
	 * The render method used in RenderBoat that renders the boat model.
	 */
	public void render(PlaceableIcecream par1Entity, double posX, double posY, double posZ, float round, float yaw) {
		ModelIceCream model = this.model;
		byte l = (byte) par1Entity.getItemMetadata();
		float size = Util.getCupScale();
		float y = (float) posY + 1.55F * size - 0.10F * size;

		this.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
		GL11.glTranslatef((float) posX, (float) y, (float) posZ);
		GL11.glScalef(size, size, size);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(180.0F - round, 0.0F, 1.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, l);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glPolygonOffset(-1, -1);
		GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);

		GL11.glEnable(GL11.GL_STENCIL_TEST);
		GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
		GL11.glStencilFunc(GL11.GL_NOTEQUAL, 1, 1);
		GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);

		GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
		GL11.glTranslatef((float) posX, (float) y, (float) posZ);
		GL11.glScalef(size, size, size);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(180.0F - round, 0.0F, 1.0F, 0.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F);
		model.renderClear((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, l);

		GL11.glDisable(GL11.GL_STENCIL_TEST);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	protected ResourceLocation getMelonTextures(PlaceableIcecream par1Entity) {
		return texture;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getMelonTextures((PlaceableIcecream) par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.render((PlaceableIcecream) par1Entity, par2, par4, par6, par8, par9);
	}
}
