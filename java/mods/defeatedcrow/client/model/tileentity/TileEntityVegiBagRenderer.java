package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.TileVegiBag;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityVegiBagRenderer extends TileEntitySpecialRenderer {

	public static TileEntityVegiBagRenderer bagRenderer;

	public void renderTileEntityBottleAt(TileVegiBag par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation(par1Tile, (float) par2, (float) par4, (float) par6);
	}

	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		bagRenderer = this;
	}

	public void setRotation(TileVegiBag tile, float par1, float par2, float par3) {
		byte meta = (byte) tile.getBlockMetadata();
		byte dir = tile.getDirectionByte();
		boolean b = tile.getSneaking();

		Tessellator tessellator = Tessellator.instance;

		float fY = b ? 0.0F : 0.5F;
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
		GL11.glTranslatef((float) par1, (float) par2 + fY, (float) par3);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);

		GL11.glPolygonOffset(-1, -1);
		GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);

		IIcon iicon = DCsAppleMilk.vegiBag.getIcon(1, meta);
		float f14 = iicon.getMinU();
		float f15 = iicon.getMaxU();
		float f4 = iicon.getMinV();
		float f5 = iicon.getMaxV();
		this.bindTexture(TextureMap.locationBlocksTexture);

		switch (dir) {
		case 0:
			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			tessellator.addVertexWithUV(0.0D, -0.5D, -1.0D, (double) f15, (double) f4);
			tessellator.addVertexWithUV(1.0D, -0.5D, -1.0D, (double) f14, (double) f4);
			tessellator.addVertexWithUV(1.0D, -0.5D, 0.0D, (double) f14, (double) f5);
			tessellator.addVertexWithUV(0.0D, -0.5D, 0.0D, (double) f15, (double) f5);
			tessellator.draw();
			break;
		case 1:
			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			tessellator.addVertexWithUV(0.0D, -0.5D, -1.0D, (double) f14, (double) f4);
			tessellator.addVertexWithUV(1.0D, -0.5D, -1.0D, (double) f14, (double) f5);
			tessellator.addVertexWithUV(1.0D, -0.5D, 0.0D, (double) f15, (double) f5);
			tessellator.addVertexWithUV(0.0D, -0.5D, 0.0D, (double) f15, (double) f4);
			tessellator.draw();
			break;
		case 2:
			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			tessellator.addVertexWithUV(0.0D, -0.5D, -1.0D, (double) f14, (double) f5);
			tessellator.addVertexWithUV(1.0D, -0.5D, -1.0D, (double) f15, (double) f5);
			tessellator.addVertexWithUV(1.0D, -0.5D, 0.0D, (double) f15, (double) f4);
			tessellator.addVertexWithUV(0.0D, -0.5D, 0.0D, (double) f14, (double) f4);
			tessellator.draw();
			break;
		case 4:
			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			tessellator.addVertexWithUV(0.0D, -0.5D, -1.0D, (double) f15, (double) f5);
			tessellator.addVertexWithUV(1.0D, -0.5D, -1.0D, (double) f15, (double) f4);
			tessellator.addVertexWithUV(1.0D, -0.5D, 0.0D, (double) f14, (double) f4);
			tessellator.addVertexWithUV(0.0D, -0.5D, 0.0D, (double) f14, (double) f5);
			tessellator.draw();
			break;
		}

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityBottleAt((TileVegiBag) par1TileEntity, par2, par4, par6, par8);
	}
}
