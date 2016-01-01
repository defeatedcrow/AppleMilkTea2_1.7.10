package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.api.appliance.SoupType;
import mods.defeatedcrow.client.model.model.ModelPanHandle;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.appliance.TileFilledSoupPan;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntitySoupPanRenderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation PanGTex = new ResourceLocation("textures/blocks/hardened_clay.png");
	private static ResourceLocation contentsTex = new ResourceLocation(
			"defeatedcrow:textures/blocks/contents/water.png");
	public static TileEntitySoupPanRenderer panRenderer;
	private ModelPanHandle PanGModel = new ModelPanHandle();

	public void renderTileEntityPanAt(TileFilledSoupPan par1TilePanG, double par2, double par4, double par6, float par8) {
		this.setRotation((float) par2, (float) par4, (float) par6, par1TilePanG);
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		panRenderer = this;
	}

	public void setRotation(float par1, float par2, float par3, TileFilledSoupPan tile) {
		ModelPanHandle model = this.PanGModel;
		String tex = tile.getCurrentTexture();
		boolean dir = tile.getDirection();
		byte rem = tile.getRemainByte();
		SoupType type = tile.getType();

		byte d = (byte) (dir ? 1 : 0);
		if (rem > 8)
			rem = 8;

		this.bindTexture(PanGTex);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef(par1, par2 + 1.0F, par3 + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		short short1 = 0;

		GL11.glTranslatef(0.0F, -1.0F, 0.0F);
		GL11.glRotatef(short1, 0.0F, -1.0F, 0.0F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, d, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		// 中身
		if (type != SoupType.EMPTY) {
			Tessellator tessellator = Tessellator.instance;
			contentsTex = new ResourceLocation(tex);

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(2.0F, 2.0F, 2.0F, 0.9F);
			GL11.glTranslatef(par1, par2 + 0.5F, par3);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);

			GL11.glPolygonOffset(-1, -1);
			GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);

			/*
			 * float f14 = 0F;
			 * float f15 = 1F;
			 * float f4 = 0F;
			 * float f5 = 0.03125F;
			 * this.bindTexture(contentsTex);
			 */

			IIcon iicon = DCsAppleMilk.filledSoupPan.getIcon(1, type.id);
			float f14 = iicon.getMinU();
			float f15 = iicon.getMaxU();
			float f4 = iicon.getMinV();
			float f5 = iicon.getMaxV();
			this.bindTexture(TextureMap.locationBlocksTexture);

			double y = 0.4D - 0.0375D * rem;

			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			tessellator.addVertexWithUV(0.16D, y, -0.84D, f14, f4);
			tessellator.addVertexWithUV(0.84D, y, -0.84D, f14, f5);
			tessellator.addVertexWithUV(0.84D, y, -0.16D, f15, f5);
			tessellator.addVertexWithUV(0.16D, y, -0.16D, f15, f4);
			tessellator.draw();

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
			GL11.glPopMatrix();
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityPanAt((TileFilledSoupPan) par1TileEntity, par2, par4, par6, par8);
	}
}
