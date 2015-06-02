package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelIceMaker;
import mods.defeatedcrow.common.tile.appliance.TileIceMaker;
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
public class TileEntityIceMakerRenderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation iceTex = new ResourceLocation("defeatedcrow:textures/entity/icemaker.png");
	public static TileEntityIceMakerRenderer steakRenderer;
	private ModelIceMaker iceModel = new ModelIceMaker();

	public void renderTileEntityIceAt(TileIceMaker par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation(par1Tile, (float) par2, (float) par4, (float) par6, (byte) par1Tile.chargeAmount);
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		steakRenderer = this;
	}

	public void setRotation(TileIceMaker par0Tile, float par1, float par2, float par3, byte par4) {
		ModelIceMaker model = this.iceModel;
		boolean k = par0Tile.isCharged();
		boolean l = par0Tile.isBurning();

		this.bindTexture(iceTex);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, k, l);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityIceAt((TileIceMaker) par1TileEntity, par2, par4, par6, par8);
	}
}
