package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelBowlJP;
import mods.defeatedcrow.common.tile.TileJPBowl;
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
public class TileEntityBowlJPRenderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation BowlJPTex1 = new ResourceLocation(Util.getEntityTexturePassNoAlt()
			+ "bowlJP_sakura.png");
	private static final ResourceLocation BowlJPTex2 = new ResourceLocation(Util.getEntityTexturePassNoAlt()
			+ "bowlJP_bluepattern.png");
	private static final ResourceLocation BowlJPTex3 = new ResourceLocation(Util.getEntityTexturePassNoAlt()
			+ "bowlJP_whiteporcelain.png");
	public static TileEntityBowlJPRenderer BowlRenderer;
	private ModelBowlJP bowlJPModel = new ModelBowlJP();

	public void renderTileEntityBowlAt(TileJPBowl par1TileJPBowl, double par2, double par4, double par6, float par8) {
		this.setRotation((float) par2, (float) par4, (float) par6, par1TileJPBowl.blockMetadata, par1TileJPBowl);
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		BowlRenderer = this;
	}

	public void setRotation(float par1, float par2, float par3, int par4, TileJPBowl tile) {
		ModelBowlJP modelBowlJP = this.bowlJPModel;
		byte l = (byte) tile.getBlockMetadata();

		this.bindTexture(BowlJPTex1);
		if (Util.getCupRender() == 1)
			this.bindTexture(BowlJPTex1);
		else if (Util.getCupRender() == 2)
			this.bindTexture(BowlJPTex2);
		else
			this.bindTexture(BowlJPTex3);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		this.bowlJPModel.render((Entity) null, 0.0F, 0.0F, 0.0F, l, 0.0F, 0.0625F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityBowlAt((TileJPBowl) par1TileEntity, par2, par4, par6, par8);
	}
}
