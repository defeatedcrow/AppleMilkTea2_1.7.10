package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelCLamp;
import mods.defeatedcrow.common.tile.TileCLamp;
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
public class TileEntityCLampRenderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation DTex = new ResourceLocation(
			"defeatedcrow:textures/entity/x32/lamp_embrion.png");
	private static final ResourceLocation RTex = new ResourceLocation("defeatedcrow:textures/entity/x32/lamp_R13A.png");
	private static final ResourceLocation GTex = new ResourceLocation(
			"defeatedcrow:textures/entity/x32/lamp_markIII.png");
	private static final ResourceLocation ITex = new ResourceLocation("defeatedcrow:textures/entity/x32/lamp_sword.png");
	public static TileEntityCLampRenderer lampRenderer;
	private ModelCLamp lampModel = new ModelCLamp();

	public void renderTileEntityBreadAt(TileCLamp par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation((float) par2, (float) par4, (float) par6, par1Tile.getDirectionByte(), par1Tile);
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		lampRenderer = this;
	}

	public void setRotation(float par1, float par2, float par3, byte par4, TileCLamp tile) {
		ModelCLamp model = this.lampModel;
		byte l = (byte) tile.getBlockMetadata();
		float j = 0;
		if (par4 == 0)
			j = 180.0F;
		if (par4 == 1)
			j = -90.0F;
		if (par4 == 2)
			j = 0.0F;
		if (par4 == 4)
			j = 90.0F;

		float k = 0;
		k = tile.getAngle();

		if (l == 8) {
			this.bindTexture(DTex);
		} else if (l == 9) {
			this.bindTexture(RTex);
			model.render((Entity) null, 0.0F, 0.0F, 0.0F, l, 0.0F, 0.0625F);
		} else if (l == 10) {
			this.bindTexture(GTex);
		} else {
			this.bindTexture(ITex);
		}

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, j, 0.0F, 0.0625F, l, k);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);
		model.renderGlow((Entity) null, 0.0F, 0.0F, 0.0F, j, 0.0F, 0.0625F, l);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_BLEND);

		GL11.glPolygonOffset(-1, -1);
		GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);

		GL11.glEnable(GL11.GL_STENCIL_TEST);
		GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
		GL11.glStencilFunc(GL11.GL_NOTEQUAL, 1, 1);
		GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);

		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		GL11.glColor4f(2.0F, 2.0F, 2.0F, 0.5F);
		model.renderLucent((Entity) null, 0.0F, 0.0F, 0.0F, j, 0.0F, 0.0625F, l, k);

		GL11.glDisable(GL11.GL_STENCIL_TEST);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityBreadAt((TileCLamp) par1TileEntity, par2, par4, par6, par8);
	}
}
