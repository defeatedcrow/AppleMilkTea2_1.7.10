package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelLargeBottle;
import mods.defeatedcrow.common.tile.TileLargeBottle;
import mods.defeatedcrow.handler.Util;
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
public class TileEntityBottleRenderer extends TileEntitySpecialRenderer {

	private static ResourceLocation bottleTex = new ResourceLocation("defeatedcrow:textures/entity/largebottle.png");
	private static final String[] type = new String[] { "_shothu", "_sake", "_beer", "_wine", "_gin", "_rum", "_vodka",
			"_whiskey", "_brandy", "_brandy", "_brandy", "_brandy", "_brandy", "_brandy", "_brandy" };
	public static TileEntityBottleRenderer bottleRenderer;
	private ModelLargeBottle bottleModel = new ModelLargeBottle();

	public void renderTileEntityBottleAt(TileLargeBottle par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation(par1Tile, (float) par2, (float) par4, (float) par6);
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		bottleRenderer = this;
	}

	public void setRotation(TileLargeBottle par0Tile, float par1, float par2, float par3) {
		ModelLargeBottle model = this.bottleModel;
		byte l = (byte) (par0Tile.getBlockMetadata() & 15);

		boolean side = par0Tile.getSide();
		float r = 0.0F;
		if (side)
			r = 90.0F;

		String texPass = new String(Util.getEntityTexturePassNoAlt() + "largebottle" + type[l] + ".png");
		bottleTex = new ResourceLocation(texPass);
		this.bindTexture(bottleTex);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, r, 0.0F, 0.0625F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityBottleAt((TileLargeBottle) par1TileEntity, par2, par4, par6, par8);
	}
}
