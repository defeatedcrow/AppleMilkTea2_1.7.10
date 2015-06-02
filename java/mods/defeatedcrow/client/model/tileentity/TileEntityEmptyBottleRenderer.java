package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelLargeBottle;
import mods.defeatedcrow.common.tile.TileEmptyBottle;
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
public class TileEntityEmptyBottleRenderer extends TileEntitySpecialRenderer {

	private static ResourceLocation bottleTex = new ResourceLocation("defeatedcrow:textures/entity/largebottle.png");
	public static TileEntityEmptyBottleRenderer bottleRenderer;
	private ModelLargeBottle bottleModel = new ModelLargeBottle();

	public void renderTileEntityBottleAt(TileEmptyBottle par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation(par1Tile, (float) par2, (float) par4, (float) par6);
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		bottleRenderer = this;
	}

	public void setRotation(TileEmptyBottle par0Tile, float par1, float par2, float par3) {
		ModelLargeBottle model = this.bottleModel;

		String texPass = new String(Util.getEntityTexturePassNoAlt() + "largebottle.png");
		bottleTex = new ResourceLocation(texPass);
		this.bindTexture(bottleTex);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityBottleAt((TileEmptyBottle) par1TileEntity, par2, par4, par6, par8);
	}
}
