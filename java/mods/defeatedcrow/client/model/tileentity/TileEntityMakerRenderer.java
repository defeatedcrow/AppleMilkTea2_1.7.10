package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelMakerHandle;
import mods.defeatedcrow.common.tile.TileMakerHandle;
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
public class TileEntityMakerRenderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation makerHandleTex = new ResourceLocation(
			"defeatedcrow:textures/blocks/porcelain.png");
	public static TileEntityMakerRenderer makerRenderer;
	private ModelMakerHandle panHandleModel = new ModelMakerHandle();

	public void renderTileEntityMakerAt(TileMakerHandle par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation((float) par2, (float) par4, (float) par6, par1Tile.getDirectionByte());
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		makerRenderer = this;
	}

	public void setRotation(float par1, float par2, float par3, byte par4) {
		ModelMakerHandle model = this.panHandleModel;

		float j = 0;
		if (par4 == 0)
			j = 180.0F;
		if (par4 == 1)
			j = -90.0F;
		if (par4 == 2)
			j = 0.0F;
		if (par4 == 4)
			j = 90.0F;

		this.bindTexture(makerHandleTex);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) par1, (float) par2 + 1.0F, (float) par3 + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		short short1 = 0;

		GL11.glTranslatef(0.0F, -1.0F, 0.0F);
		GL11.glRotatef((float) short1, 0.0F, -1.0F, 0.0F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, j, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityMakerAt((TileMakerHandle) par1TileEntity, par2, par4, par6, par8);
	}
}
