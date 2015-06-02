package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelBarrel;
import mods.defeatedcrow.client.model.model.ModelBarrelBase;
import mods.defeatedcrow.common.tile.TileBrewingBarrel;
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
public class TileEntityBarrelRenderer extends TileEntitySpecialRenderer {

	private static ResourceLocation thisTex = new ResourceLocation(Util.getEntityTexturePassNoAlt() + "barrel.png");
	private static ResourceLocation baseTex = new ResourceLocation(Util.getEntityTexturePassNoAlt() + "barrelbase.png");
	public static TileEntityBarrelRenderer thisRenderer;
	private ModelBarrel thisModel = new ModelBarrel();
	private ModelBarrelBase baseModel = new ModelBarrelBase();

	public void renderTileEntityBarrelAt(TileBrewingBarrel par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation(par1Tile, (float) par2, (float) par4, (float) par6);
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		thisRenderer = this;
	}

	public void setRotation(TileBrewingBarrel par0Tile, float par1, float par2, float par3) {
		ModelBarrel model = this.thisModel;
		byte k = (byte) par0Tile.getAgingStage();

		boolean onCube = par0Tile.isOnNormalCube();
		boolean side = par0Tile.getSide();
		float r = 0.0F;
		float c = 1.0f - (k * 0.1F);
		if (side)
			r = 90.0F;

		this.bindTexture(thisTex);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(c, c, c, 1.0F);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, r, 0.0F, 0.0625F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glPolygonOffset(-1, -1);
		GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
		GL11.glColor4f(c, c, c, 1.0F);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		model.renderSide((Entity) null, 0.0F, 0.0F, 0.0F, r, 0.0F, 0.0625F);
		GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

		if (onCube) {
			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
			model.renderBase((Entity) null, 0.0F, 0.0F, 0.0F, r, 0.0F, 0.0625F);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		} else {
			this.bindTexture(baseTex);
			GL11.glPushMatrix();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
			GL11.glScalef(1.0F, -1.0F, -1.0F);
			if (side)
				GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
			else
				GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
			baseModel.render((Entity) null, 0.0F, 0.0F, 0.0F, r, 0.0F, 0.0625F);
			GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityBarrelAt((TileBrewingBarrel) par1TileEntity, par2, par4, par6, par8);
	}
}
