package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelCharger;
import mods.defeatedcrow.common.tile.energy.TileChargerDevice;
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
public class TileEntityChargerRenderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation machineTex = new ResourceLocation("defeatedcrow:textures/entity/charger.png");
	public static TileEntityChargerRenderer renderer;
	private ModelCharger model = new ModelCharger();

	public void renderTileEntityModelAt(TileChargerDevice par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation(par1Tile, (float) par2, (float) par4, (float) par6);
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		renderer = this;
	}

	public void setRotation(TileChargerDevice par0Tile, float par1, float par2, float par3) {
		ModelCharger model = this.model;
		byte l = (byte) par0Tile.getBlockMetadata();
		boolean active = par0Tile.isActive();

		this.bindTexture(machineTex);

		float f = 0F;
		switch (l) {
		case 0:
			f = 180;
			break;
		case 1:
			f = -90;
			break;
		case 2:
			f = 0;
			break;
		case 3:
			f = 90;
			break;
		}

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef(par1 + 0.5F, par2 + 1.5F, par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, f, 0.0F, 0.0625F);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityModelAt((TileChargerDevice) par1TileEntity, par2, par4, par6, par8);
	}
}
