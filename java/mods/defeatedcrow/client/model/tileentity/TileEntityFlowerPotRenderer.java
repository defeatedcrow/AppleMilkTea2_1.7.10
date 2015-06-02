package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelFlowerPot;
import mods.defeatedcrow.common.tile.TileFlowerPot;
import mods.defeatedcrow.handler.Util;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TileEntityFlowerPotRenderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation RedTex = new ResourceLocation(Util.getEntityTexturePassNoAlt()
			+ "flowerpot_red.png");
	private static final ResourceLocation YellowTex = new ResourceLocation(Util.getEntityTexturePassNoAlt()
			+ "flowerpot_yellow.png");
	public static TileEntityFlowerPotRenderer renderer;
	private ModelFlowerPot model = new ModelFlowerPot();

	public void renderTileEntityBreadAt(TileFlowerPot par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation((float) par2, (float) par4, (float) par6, par1Tile);
	}

	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		renderer = this;
	}

	public void setRotation(float par1, float par2, float par3, TileFlowerPot tile) {
		ModelFlowerPot model = this.model;
		byte l = (byte) tile.getBlockMetadata();
		byte k = (byte) (l & 3);
		float j = 0;
		if (k == 0)
			j = 180.0F;
		if (k == 1)
			j = -90.0F;
		if (k == 2)
			j = 0.0F;
		if (k == 3)
			j = 90.0F;

		if (l < 4) {
			this.bindTexture(RedTex);
		} else {
			this.bindTexture(YellowTex);
		}

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, j, 0.0F, 0.0625F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityBreadAt((TileFlowerPot) par1TileEntity, par2, par4, par6, par8);
	}

}
