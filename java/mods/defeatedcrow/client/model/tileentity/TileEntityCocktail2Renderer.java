package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelCocktail;
import mods.defeatedcrow.common.tile.TileCocktail2;
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
public class TileEntityCocktail2Renderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation cocktailTex = new ResourceLocation(
			"defeatedcrow:textures/entity/cocktail.png");
	public static TileEntityCocktail2Renderer cocktailRenderer;
	private ModelCocktail cocktailModel = new ModelCocktail();

	public void renderTileEntitySteakAt(TileCocktail2 par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation(par1Tile, (float) par2, (float) par4, (float) par6);
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		cocktailRenderer = this;
	}

	public void setRotation(TileCocktail2 par0Tile, float par1, float par2, float par3) {
		ModelCocktail model = this.cocktailModel;
		byte l = (byte) par0Tile.getBlockMetadata();

		byte type = 0;// 0:ロング、1:ショート、2:ワイングラス、4:フローズン
		if (l == 6 || l == 7)
			type = 1;
		else if (l == 1 || l == 2 || l == 9)
			type = 2;

		byte deco = 0;// デコはない

		this.bindTexture(cocktailTex);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		model.renderDeco((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, deco);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glPolygonOffset(-1, -1);
		GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);

		GL11.glEnable(GL11.GL_STENCIL_TEST);
		GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
		GL11.glStencilFunc(GL11.GL_NOTEQUAL, 1, 1);
		GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		model.renderGlass((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, type);

		GL11.glDisable(GL11.GL_STENCIL_TEST);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntitySteakAt((TileCocktail2) par1TileEntity, par2, par4, par6, par8);
	}
}
