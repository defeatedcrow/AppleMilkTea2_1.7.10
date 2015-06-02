package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelAltDial;
import mods.defeatedcrow.client.model.model.ModelRotaryDial;
import mods.defeatedcrow.common.tile.TileRotaryDial;
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
public class TileEntityDialRenderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation dialTex = new ResourceLocation(Util.getEntityTexturePassNoAlt()
			+ "rotarydial.png");
	private static final ResourceLocation altTex = new ResourceLocation(Util.getEntityTexturePassAlt()
			+ "rotarydial_alt.png");
	public static TileEntityDialRenderer dialRenderer;
	private ModelRotaryDial dialModel = new ModelRotaryDial();
	private ModelAltDial altModel = new ModelAltDial();

	public void renderTileEntitySteakAt(TileRotaryDial par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation(par1Tile, (float) par2, (float) par4, (float) par6);
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		dialRenderer = this;
	}

	public void setRotation(TileRotaryDial par0Tile, float par1, float par2, float par3) {
		ModelRotaryDial model = this.dialModel;
		ModelAltDial modelAlt = this.altModel;
		byte m = (byte) par0Tile.getBlockMetadata();
		byte l = (byte) (m & 3);
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

		if (alt) {
			this.bindTexture(altTex);
		} else {
			this.bindTexture(dialTex);
		}

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
		if (alt) {
			modelAlt.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		} else {
			model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, l);
		}

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntitySteakAt((TileRotaryDial) par1TileEntity, par2, par4, par6, par8);
	}
}
