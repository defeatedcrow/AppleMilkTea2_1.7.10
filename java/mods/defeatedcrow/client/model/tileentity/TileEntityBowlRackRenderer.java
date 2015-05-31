package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelAltBowl;
import mods.defeatedcrow.common.tile.TileBowlRack;
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
public class TileEntityBowlRackRenderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation altTex = new ResourceLocation(Util.getEntityTexturePassAlt()
			+ "bowlrack_alt.png");

	public static TileEntityBowlRackRenderer altRenderer;
	private ModelAltBowl altModel = new ModelAltBowl();

	public void renderTileEntitySteakAt(TileBowlRack par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation(par1Tile, (float) par2, (float) par4, (float) par6);
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		altRenderer = this;
	}

	public void setRotation(TileBowlRack par0Tile, float par1, float par2, float par3) {

		ModelAltBowl modelAlt = this.altModel;
		byte m = (byte) par0Tile.getBlockMetadata();
		byte l = (byte) (m & 3);
		byte amo = par0Tile.getRemainByte();

		float j = 0;
		if (l == 0)
			j = 180.0F;
		if (l == 1)
			j = -90.0F;
		if (l == 2)
			j = 0.0F;
		if (l == 3)
			j = 90.0F;

		boolean alt = (m & 4) != 0;

		this.bindTexture(altTex);

		for (int i = 0; i < amo; i++) {
			float ajx = 0.5F;
			float ajz = 0.5F;
			if (l == 0)
				ajz = 0.15F + 0.25F * i;
			if (l == 1)
				ajx = 0.85F - 0.25F * i;
			if (l == 2)
				ajz = 0.10F + 0.25F * i;
			if (l == 3)
				ajx = 0.9F - 0.25F * i;

			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float) par1 + ajx, (float) par2 + 1.5F, (float) par3 + ajz);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
			modelAlt.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}

	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntitySteakAt((TileBowlRack) par1TileEntity, par2, par4, par6, par8);
	}
}
