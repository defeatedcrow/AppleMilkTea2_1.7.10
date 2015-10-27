package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.common.tile.appliance.TileTeppanII;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityTeppanIIRenderer extends TileEntitySpecialRenderer {

	public static TileEntityTeppanIIRenderer teppanRenderer;

	public void renderTileEntityCaseAt(TileTeppanII par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation(par1Tile, (float) par2, (float) par4, (float) par6);
	}

	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		teppanRenderer = this;
	}

	public void setRotation(TileTeppanII par0Tile, float par1, float par2, float par3) {
		int i = par0Tile.getCookTime();
		boolean isFancy = Minecraft.isFancyGraphicsEnabled();

		float f = MathHelper.clamp_int(i, 0, 360);
		// inner
		if (par0Tile.getWorldObj() != null) {
			GL11.glPushMatrix();
			GL11.glTranslatef(par1 + 0.5F, par2 + 0.15F, par3 + 0.5F);
			GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
			this.renderInner(par0Tile);
			if (!isFancy) {
				GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
				this.renderInner(par0Tile);
			}
			GL11.glPopMatrix();
		}
	}

	private void renderInner(TileTeppanII par0Tile) {
		ItemStack item = null;
		if (par0Tile.isFailed()) {
			item = par0Tile.plateItems[2];
		} else if (par0Tile.isFinishCooking()) {
			item = par0Tile.plateItems[1];
		} else {
			item = par0Tile.plateItems[0];
		}

		if (item != null) {
			EntityItem entityitem = new EntityItem(par0Tile.getWorldObj(), 0.0D, 0.0D, 0.0D, item);
			entityitem.getEntityItem().stackSize = 1;
			entityitem.hoverStart = 0.0F;
			if (item.getItem() instanceof ItemBlock) {
				GL11.glScalef(1.2F, 1.2F, 1.2F);
			} else {
				GL11.glScalef(0.8F, 0.8F, 0.8F);
			}

			RenderItem.renderInFrame = true;
			RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			RenderItem.renderInFrame = false;
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityCaseAt((TileTeppanII) par1TileEntity, par2, par4, par6, par8);
	}
}
