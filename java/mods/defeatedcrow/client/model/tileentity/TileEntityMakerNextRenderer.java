package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelMakerNext;
import mods.defeatedcrow.common.tile.appliance.TileMakerNext;
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
public class TileEntityMakerNextRenderer extends TileEntitySpecialRenderer {

	private static ResourceLocation makerTex = new ResourceLocation("defeatedcrow:textures/blocks/contents_milk.png");
	private static final ResourceLocation emptyTex = new ResourceLocation(
			"defeatedcrow:textures/blocks/contents_water.png");
	public static TileEntityMakerNextRenderer makerRenderer;
	private ModelMakerNext nextModel = new ModelMakerNext();

	public void renderTileEntityMakerAt(TileMakerNext par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation((float) par2, (float) par4, (float) par6, par1Tile);
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		makerRenderer = this;
	}

	public void setRotation(float par1, float par2, float par3, TileMakerNext tile) {
		ModelMakerNext model = this.nextModel;
		String tex = tile.getCurrentTexture();
		boolean hasItem = (tile.getItemStack() != null);
		boolean isMilked = tile.getMilked();

		makerTex = new ResourceLocation(tex);

		this.bindTexture(makerTex);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		if (isMilked) {
			GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
		} else {

			GL11.glColor4f(1.2F, 1.2F, 1.2F, 0.9F);
		}
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef((float) 0.0F, 0.0F, -1.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityMakerAt((TileMakerNext) par1TileEntity, par2, par4, par6, par8);
	}
}
