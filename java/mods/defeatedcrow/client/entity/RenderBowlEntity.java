package mods.defeatedcrow.client.entity;

import mods.defeatedcrow.client.entity.base.ModelWoodBowl;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.entity.edible.PlaceableBowl;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBowlEntity extends Render {

	private static final ResourceLocation woodTex = new ResourceLocation("defeatedcrow:textures/entity/woodbowl.png");

	/** instance of ModelBoat for rendering */
	protected ModelWoodBowl model;

	public RenderBowlEntity() {
		this.shadowSize = 0.5F;
		this.model = new ModelWoodBowl();
	}

	/**
	 * The render method used in RenderBoat that renders the boat model.
	 */
	public void render(PlaceableBowl entity, double posX, double posY, double posZ, float round, float yaw) {
		Tessellator tessellator = Tessellator.instance;

		this.bindTexture(woodTex);
		int l = entity.getItemMetadata();

		// ボウル
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
		GL11.glTranslatef((float) posX, (float) posY + 1.25F, (float) posZ);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(round, 0.0F, 1.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

		// 中身
		if (l != 15) {
			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
			GL11.glTranslatef((float) posX, (float) posY + 0.5F, (float) posZ);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glRotatef(round, 0.0F, 1.0F, 0.0F);

			IIcon iicon = DCsAppleMilk.bowlBlock.getIcon(0, l);
			float f14 = iicon.getMinU();
			float f15 = iicon.getMaxU();
			float f4 = iicon.getMinV();
			float f5 = iicon.getMaxV();

			this.bindTexture(TextureMap.locationBlocksTexture);

			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			tessellator.addVertexWithUV(-0.25D, 0.5D, -0.25D, (double) f14, (double) f5);
			tessellator.addVertexWithUV(0.25D, 0.5D, -0.25D, (double) f15, (double) f5);
			tessellator.addVertexWithUV(0.25D, 0.5D, 0.25D, (double) f15, (double) f4);
			tessellator.addVertexWithUV(-0.25D, 0.5D, 0.25D, (double) f14, (double) f4);
			tessellator.draw();

			if (l == 0 || l == 4) {
				tessellator.startDrawingQuads();
				tessellator.setNormal(1.0F, 0.0F, 0.0F);
				tessellator.addVertexWithUV(-0.1D, 0.3D, -0.1D, (double) f14, (double) f5);
				tessellator.addVertexWithUV(0.1D, 0.3D, -0.1D, (double) f15, (double) f5);
				tessellator.addVertexWithUV(0.1D, 0.3D, 0.1D, (double) f15, (double) f4);
				tessellator.addVertexWithUV(-0.1D, 0.3D, 0.1D, (double) f14, (double) f4);
				tessellator.draw();

				tessellator.startDrawingQuads();
				tessellator.setNormal(1.0F, 0.0F, 0.0F);
				tessellator.addVertexWithUV(-0.25D, 0.5D, -0.25D, (double) f14, (double) f5);
				tessellator.addVertexWithUV(0.25D, 0.5D, -0.25D, (double) f15, (double) f5);
				tessellator.addVertexWithUV(0.1D, 0.3D, -0.1D, (double) f15, (double) f4);
				tessellator.addVertexWithUV(-0.1D, 0.3D, -0.1D, (double) f14, (double) f4);
				tessellator.draw();

				tessellator.startDrawingQuads();
				tessellator.setNormal(1.0F, 0.0F, 0.0F);
				tessellator.addVertexWithUV(0.25D, 0.5D, 0.25D, (double) f14, (double) f5);
				tessellator.addVertexWithUV(-0.25D, 0.5D, 0.25D, (double) f15, (double) f5);
				tessellator.addVertexWithUV(-0.1D, 0.3D, 0.1D, (double) f15, (double) f4);
				tessellator.addVertexWithUV(0.1D, 0.3D, 0.1D, (double) f14, (double) f4);
				tessellator.draw();

				tessellator.startDrawingQuads();
				tessellator.setNormal(1.0F, 0.0F, 0.0F);
				tessellator.addVertexWithUV(-0.25D, 0.5D, -0.25D, (double) f14, (double) f5);
				tessellator.addVertexWithUV(-0.1D, 0.3D, -0.1D, (double) f15, (double) f5);
				tessellator.addVertexWithUV(-0.1D, 0.3D, 0.1D, (double) f15, (double) f4);
				tessellator.addVertexWithUV(-0.25D, 0.5D, 0.25D, (double) f14, (double) f4);
				tessellator.draw();

				tessellator.startDrawingQuads();
				tessellator.setNormal(1.0F, 0.0F, 0.0F);
				tessellator.addVertexWithUV(0.1D, 0.3D, -0.1D, (double) f14, (double) f5);
				tessellator.addVertexWithUV(0.25D, 0.5D, -0.25D, (double) f15, (double) f5);
				tessellator.addVertexWithUV(0.25D, 0.5D, 0.25D, (double) f15, (double) f4);
				tessellator.addVertexWithUV(0.1D, 0.3D, 0.1D, (double) f14, (double) f4);
				tessellator.draw();
			}

			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
	}

	protected ResourceLocation getMelonTextures(PlaceableBowl par1Entity) {
		return woodTex;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getMelonTextures((PlaceableBowl) par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.render((PlaceableBowl) par1Entity, par2, par4, par6, par8, par9);
	}
}
