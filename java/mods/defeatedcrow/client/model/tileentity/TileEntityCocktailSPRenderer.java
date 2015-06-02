package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelCocktail;
import mods.defeatedcrow.common.block.edible.BlockCocktailSP;
import mods.defeatedcrow.common.block.edible.BlockCocktailSP.DecorationType;
import mods.defeatedcrow.common.block.edible.BlockCocktailSP.ModelType;
import mods.defeatedcrow.common.tile.TileCocktailSP;
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
public class TileEntityCocktailSPRenderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation cocktailTex = new ResourceLocation(
			"defeatedcrow:textures/entity/cocktail.png");
	public static TileEntityCocktailSPRenderer cocktailRenderer;
	private ModelCocktail cocktailModel = new ModelCocktail();

	public void renderTileEntitySteakAt(TileCocktailSP par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation(par1Tile, (float) par2, (float) par4, (float) par6);
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		cocktailRenderer = this;
	}

	public void setRotation(TileCocktailSP par0Tile, float par1, float par2, float par3) {
		ModelCocktail model = this.cocktailModel;
		byte l = (byte) par0Tile.getBlockMetadata();

		ModelType type = BlockCocktailSP.getGlassType(l);
		DecorationType deco = BlockCocktailSP.getDecoType(l);

		float[] color = BlockCocktailSP.getColorPropertySP(l);

		String innerTexPass = "defeatedcrow:textures/blocks/contents_cocktailbase.png";
		ResourceLocation innerTex = new ResourceLocation(innerTexPass);
		this.bindTexture(innerTex);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glColor4f(color[0], color[1], color[2], color[3]);
		GL11.glTranslatef((float) par1, (float) par2 + 1.25F, (float) par3);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		model.renderInnerSP((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, type);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();

		this.bindTexture(cocktailTex);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		model.renderDecoSP((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, deco);
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
		model.renderGlassSP((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, type);

		GL11.glDisable(GL11.GL_STENCIL_TEST);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntitySteakAt((TileCocktailSP) par1TileEntity, par2, par4, par6, par8);
	}
}
