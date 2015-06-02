package mods.defeatedcrow.client.entity;

import mods.defeatedcrow.client.model.model.ModelBowlJP;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.entity.edible.PlaceableBowlJP;
import mods.defeatedcrow.handler.Util;
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
public class RenderBowlJPEntity extends Render {

	private static final ResourceLocation BowlJPTex1 = new ResourceLocation(Util.getEntityTexturePassNoAlt()
			+ "bowlJP_sakura.png");
	private static final ResourceLocation BowlJPTex2 = new ResourceLocation(Util.getEntityTexturePassNoAlt()
			+ "bowlJP_bluepattern.png");
	private static final ResourceLocation BowlJPTex3 = new ResourceLocation(Util.getEntityTexturePassNoAlt()
			+ "bowlJP_whiteporcelain.png");

	private ModelBowlJP model = new ModelBowlJP();

	public RenderBowlJPEntity() {
		this.shadowSize = 0.5F;
		this.model = new ModelBowlJP();
	}

	/**
	 * The render method used in RenderBoat that renders the boat model.
	 */
	public void render(PlaceableBowlJP entity, double posX, double posY, double posZ, float round, float yaw) {
		Tessellator tessellator = Tessellator.instance;

		this.bindTexture(BowlJPTex1);
		if (Util.getCupRender() == 1)
			this.bindTexture(BowlJPTex1);
		else if (Util.getCupRender() == 2)
			this.bindTexture(BowlJPTex2);
		else
			this.bindTexture(BowlJPTex3);
		int l = entity.getItemMetadata();

		// ボウル
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
		GL11.glTranslatef((float) posX, (float) posY + 1.25F, (float) posZ);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(round, 0.0F, 1.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, (byte) l, 0.0F, 0.0625F);
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

			IIcon iicon = DCsAppleMilk.bowlJP.getIcon(0, l);
			float f14 = iicon.getMinU();
			float f15 = iicon.getMaxU();
			float f4 = iicon.getMinV();
			float f5 = iicon.getMaxV();

			this.bindTexture(TextureMap.locationBlocksTexture);

			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			tessellator.addVertexWithUV(-0.2D, 0.45D, -0.2D, (double) f14, (double) f5);
			tessellator.addVertexWithUV(0.2D, 0.45D, -0.2D, (double) f15, (double) f5);
			tessellator.addVertexWithUV(0.2D, 0.45D, 0.2D, (double) f15, (double) f4);
			tessellator.addVertexWithUV(-0.2D, 0.45D, 0.2D, (double) f14, (double) f4);
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
				tessellator.addVertexWithUV(-0.2D, 0.45D, -0.2D, (double) f14, (double) f5);
				tessellator.addVertexWithUV(0.2D, 0.45D, -0.2D, (double) f15, (double) f5);
				tessellator.addVertexWithUV(0.1D, 0.3D, -0.1D, (double) f15, (double) f4);
				tessellator.addVertexWithUV(-0.1D, 0.3D, -0.1D, (double) f14, (double) f4);
				tessellator.draw();

				tessellator.startDrawingQuads();
				tessellator.setNormal(1.0F, 0.0F, 0.0F);
				tessellator.addVertexWithUV(0.2D, 0.45D, 0.2D, (double) f14, (double) f5);
				tessellator.addVertexWithUV(-0.2D, 0.45D, 0.2D, (double) f15, (double) f5);
				tessellator.addVertexWithUV(-0.1D, 0.3D, 0.1D, (double) f15, (double) f4);
				tessellator.addVertexWithUV(0.1D, 0.3D, 0.1D, (double) f14, (double) f4);
				tessellator.draw();

				tessellator.startDrawingQuads();
				tessellator.setNormal(1.0F, 0.0F, 0.0F);
				tessellator.addVertexWithUV(-0.2D, 0.45D, -0.2D, (double) f14, (double) f5);
				tessellator.addVertexWithUV(-0.1D, 0.3D, -0.1D, (double) f15, (double) f5);
				tessellator.addVertexWithUV(-0.1D, 0.3D, 0.1D, (double) f15, (double) f4);
				tessellator.addVertexWithUV(-0.2D, 0.45D, 0.2D, (double) f14, (double) f4);
				tessellator.draw();

				tessellator.startDrawingQuads();
				tessellator.setNormal(1.0F, 0.0F, 0.0F);
				tessellator.addVertexWithUV(0.1D, 0.3D, -0.1D, (double) f14, (double) f5);
				tessellator.addVertexWithUV(0.2D, 0.45D, -0.2D, (double) f15, (double) f5);
				tessellator.addVertexWithUV(0.2D, 0.45D, 0.2D, (double) f15, (double) f4);
				tessellator.addVertexWithUV(0.1D, 0.3D, 0.1D, (double) f14, (double) f4);
				tessellator.draw();
			}

			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
	}

	protected ResourceLocation getMelonTextures(PlaceableBowlJP par1Entity) {
		if (Util.getCupRender() == 1)
			return BowlJPTex1;
		else if (Util.getCupRender() == 2)
			return BowlJPTex2;
		else
			return BowlJPTex3;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return this.getMelonTextures((PlaceableBowlJP) par1Entity);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.render((PlaceableBowlJP) par1Entity, par2, par4, par6, par8, par9);
	}
}
