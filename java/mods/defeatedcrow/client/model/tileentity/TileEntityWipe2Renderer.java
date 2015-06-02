package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelWipeBox2;
import mods.defeatedcrow.common.tile.TileWipeBox2;
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
public class TileEntityWipe2Renderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation wipeTex = new ResourceLocation(Util.getEntityTexturePassNoAlt()
			+ "wipebox2.png");
	public static TileEntityWipe2Renderer wipeRenderer;
	private ModelWipeBox2 wipeModel = new ModelWipeBox2();

	public void renderTileEntityWipeAt(TileWipeBox2 par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation((float) par2, (float) par4, (float) par6, par1Tile);
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		wipeRenderer = this;
	}

	public void setRotation(float par1, float par2, float par3, TileWipeBox2 tile) {
		ModelWipeBox2 model = this.wipeModel;

		this.bindTexture(wipeTex);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityWipeAt((TileWipeBox2) par1TileEntity, par2, par4, par6, par8);
	}
}
