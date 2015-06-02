package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.client.model.model.ModelSteak;
import mods.defeatedcrow.common.tile.TileSteak;
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
public class TileEntitySteakRenderer extends TileEntitySpecialRenderer {

	private static final ResourceLocation steakTex = new ResourceLocation("defeatedcrow:textures/entity/steak.png");
	private static final ResourceLocation chickenTex = new ResourceLocation(
			"defeatedcrow:textures/entity/roastedchicken.png");
	private static final ResourceLocation hamaguriTex = new ResourceLocation(
			"defeatedcrow:textures/entity/hamaguri.png");
	public static TileEntitySteakRenderer steakRenderer;
	private ModelSteak steakModel = new ModelSteak();

	public void renderTileEntitySteakAt(TileSteak par1TileSteak, double par2, double par4, double par6, float par8) {
		this.setRotation(par1TileSteak, (float) par2, (float) par4, (float) par6, par1TileSteak.getDirectionByte());
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		steakRenderer = this;
	}

	public void setRotation(TileSteak par0Tile, float par1, float par2, float par3, byte par4) {
		ModelSteak model = this.steakModel;
		byte l = (byte) par0Tile.getBlockMetadata();
		byte k = par4;
		float j = 0;
		if (k == 0)
			j = 180.0F;
		if (k == 1)
			j = -90.0F;
		if (k == 2)
			j = 0.0F;
		if (k == 4)
			j = 90.0F;

		if (l == 0 || l == 1) {
			this.bindTexture(steakTex);
		} else if (l == 2) {
			this.bindTexture(chickenTex);
		} else {
			this.bindTexture(hamaguriTex);
		}

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) par1 + 0.5F, (float) par2 + 1.5F, (float) par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, j, 0.0F, 0.0625F, l);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntitySteakAt((TileSteak) par1TileEntity, par2, par4, par6, par8);
	}
}
