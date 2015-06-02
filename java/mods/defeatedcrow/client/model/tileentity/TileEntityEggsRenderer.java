package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelEggs;
import mods.defeatedcrow.common.tile.TileEggs;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityEggsRenderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation EggsTexWhite = new ResourceLocation(
			"defeatedcrow:textures/blocks/whitepanel.png");
	private static final ResourceLocation EggsTexBlack = new ResourceLocation(
			"defeatedcrow:textures/blocks/teppann.png");
	public static TileEntityEggsRenderer EggsRenderer;
	private ModelEggs eggsModel = new ModelEggs();

	public void renderTileEntityEggsAt(TileEggs par1TileEggs, double par2, double par4, double par6, float par8) {
		this.setRotation((float) par2, (float) par4, (float) par6, par1TileEggs.blockMetadata, par1TileEggs);
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		EggsRenderer = this;
	}

	public void setRotation(float par1, float par2, float par3, int par4, TileEggs tile) {
		ModelEggs modelEggs = this.eggsModel;
		byte l = (byte) tile.getBlockMetadata();

		if ((l & 1) == 0)
			this.bindTexture(EggsTexWhite);
		else
			this.bindTexture(EggsTexBlack);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		if (l > 1)
			GL11.glTranslatef(0.0F, -0.2F, 0.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		this.eggsModel.render((Entity) null, 0.0F, 0.0F, 0.0F, l, 0.0F, 0.0625F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityEggsAt((TileEggs) par1TileEntity, par2, par4, par6, par8);
	}
}
