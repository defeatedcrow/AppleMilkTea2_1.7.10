package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelCordial;
import mods.defeatedcrow.common.tile.TileCordial;
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
public class TileEntityCordialRenderer extends TileEntitySpecialRenderer {

	private static ResourceLocation glassTex = new ResourceLocation("defeatedcrow:textures/blocks/blueglass.png");
	private static ResourceLocation contentsTex = new ResourceLocation("defeatedcrow:textures/blocks/cordial_drink.png");
	private static final String[] type = new String[] { "_apple", "_tea", "_cassis", "_plum", "_apricot" };
	public static TileEntityCordialRenderer bottleRenderer;
	private ModelCordial bottleModel = new ModelCordial();

	public void renderTileEntityBottleAt(TileCordial par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation(par1Tile, (float) par2, (float) par4, (float) par6, (byte) par1Tile.getAgingStage());
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		bottleRenderer = this;
	}

	public void setRotation(TileCordial par0Tile, float par1, float par2, float par3, byte stage) {
		ModelCordial model = this.bottleModel;
		byte l = (byte) par0Tile.getBlockMetadata();
		if (l > 4)
			l = 4;
		byte k = (byte) stage;
		boolean aged = par0Tile.getAged();

		String texPass = new String("defeatedcrow:textures/blocks/cordial_drink" + type[l] + ".png");
		contentsTex = new ResourceLocation(texPass);

		GL11.glPushMatrix();
		this.bindTexture(contentsTex);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		if (aged) {
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
		} else {
			switch (k) {
			case 0:
				GL11.glColor4f(2.0F, 2.0F, 2.0F, 0.2F);
				break;
			case 1:
				GL11.glColor4f(1.5F, 1.5F, 1.5F, 0.4F);
				break;
			default:
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
				break;
			}

		}

		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 - 0.83F, (float) par3 + 0.5F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);

		model.renderDrink((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();

		this.bindTexture(glassTex);
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		// GL11.glPolygonOffset(-1, -1);
		// GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
		//
		// GL11.glEnable(GL11.GL_STENCIL_TEST);
		// GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
		// GL11.glStencilFunc(GL11.GL_NOTEQUAL, 1, 1);
		// GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		// GL11.glDisable(GL11.GL_STENCIL_TEST);
		// GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();

	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityBottleAt((TileCordial) par1TileEntity, par2, par4, par6, par8);
	}
}
