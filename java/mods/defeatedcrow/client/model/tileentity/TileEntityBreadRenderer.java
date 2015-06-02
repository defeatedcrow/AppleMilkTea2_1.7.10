package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelBasketL;
import mods.defeatedcrow.client.model.model.ModelBasketT;
import mods.defeatedcrow.client.model.model.ModelBreadAlt;
import mods.defeatedcrow.client.model.model.ModelBreads;
import mods.defeatedcrow.common.tile.TileBread;
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
public class TileEntityBreadRenderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation BreadTex = new ResourceLocation(Util.getEntityTexturePassNoAlt()
			+ "breads.png");
	private static final ResourceLocation BreadTex2 = new ResourceLocation(Util.getEntityTexturePassNoAlt()
			+ "breads2.png");
	private static final ResourceLocation BreadTex3 = new ResourceLocation(Util.getEntityTexturePassNoAlt()
			+ "breads3.png");
	private static final ResourceLocation BreadAltTex = new ResourceLocation(Util.getEntityTexturePassNoAlt()
			+ "breadalt.png");
	private static final ResourceLocation BreadAltTex2 = new ResourceLocation(Util.getEntityTexturePassNoAlt()
			+ "breadalt2.png");
	private static final ResourceLocation BreadAltTex3 = new ResourceLocation(Util.getEntityTexturePassNoAlt()
			+ "breadalt3.png");
	private static final ResourceLocation BasketTex = new ResourceLocation(Util.getEntityTexturePassNoAlt()
			+ "baskets.png");
	private static final ResourceLocation BottleTex = new ResourceLocation(Util.getEntityTexturePassNoAlt()
			+ "bottlebasket.png");
	public static TileEntityBreadRenderer BreadRenderer;
	private ModelBreads breadModel = new ModelBreads();
	private ModelBreadAlt altModel = new ModelBreadAlt();
	private ModelBasketL basketL = new ModelBasketL();
	private ModelBasketT basketT = new ModelBasketT();

	public void renderTileEntityBreadAt(TileBread par1TileBread, double par2, double par4, double par6, float par8) {
		this.setRotation((float) par2, (float) par4, (float) par6, par1TileBread.getDirectionByte(), par1TileBread);
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		BreadRenderer = this;
	}

	public void setRotation(float par1, float par2, float par3, byte par4, TileBread tile) {
		ModelBreads modelBread = this.breadModel;
		byte l = (byte) tile.getBlockMetadata();
		byte b = tile.getType();
		boolean t = tile.getTall();
		float j = 0;
		if (par4 == 0)
			j = 180.0F;
		if (par4 == 1)
			j = -90.0F;
		if (par4 == 2)
			j = 0.0F;
		if (par4 == 4)
			j = 90.0F;

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);

		if (l < 6) {
			if (t) {
				if (b == 0)
					this.bindTexture(BreadAltTex);
				else if (b == 1)
					this.bindTexture(BreadAltTex2);
				else
					this.bindTexture(BreadAltTex3);
				this.altModel.render((Entity) null, 0.0F, 0.0F, 0.0F, l, 0.0F, 0.0625F);
				this.bindTexture(BasketTex);
				this.basketT.render((Entity) null, 0.0F, 0.0F, 0.0F, l, 0.0F, 0.0625F);
			} else {
				if (b == 0)
					this.bindTexture(BreadTex);
				else if (b == 1)
					this.bindTexture(BreadTex2);
				else
					this.bindTexture(BreadTex3);
				this.breadModel.render((Entity) null, 0.0F, 0.0F, 0.0F, l, 0.0F, 0.0625F);
				this.bindTexture(BasketTex);
				this.basketL.render((Entity) null, 0.0F, 0.0F, 0.0F, l, 0.0F, 0.0625F);
			}
		} else {
			this.bindTexture(BottleTex);
			this.breadModel.renderBottle((Entity) null, 0.0F, 0.0F, 0.0F, l, 0.0F, 0.0625F);
		}

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityBreadAt((TileBread) par1TileEntity, par2, par4, par6, par8);
	}
}
