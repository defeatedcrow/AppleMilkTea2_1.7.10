package mods.defeatedcrow.client.model.tileentity;

import mods.defeatedcrow.common.block.container.BlockContainerBase;
import mods.defeatedcrow.common.tile.TileContainerBase;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityContainerBaseRenderer extends TileEntitySpecialRenderer {
	public static TileEntityContainerBaseRenderer thisRenderer;

	public void renderTileEntityCaseAt(TileContainerBase par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation(par1Tile, (float) par2, (float) par4, (float) par6);
	}

	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		thisRenderer = this;
	}

	public void setRotation(TileContainerBase tile, float par1, float par2, float par3) {
		// inner
		if (tile.getWorldObj() != null) {
			boolean isFancy = Minecraft.isFancyGraphicsEnabled();
			Block block = tile.getBlockType();
			int meta = tile.getBlockMetadata();
			if (block instanceof BlockContainerBase) {
				BlockContainerBase cont = (BlockContainerBase) block;
				ItemStack item = cont.returnItem();
				if (item != null) {
					int rem = meta & 7;
					boolean side = meta > 7;

					if (side) {
						for (int i = 0; i <= rem; i++) {
							float f1 = i * 0.1F;
							float f2 = (i & 1) * 0.4F;
							GL11.glPushMatrix();
							GL11.glTranslatef(par1 + 0.125F + f1, par2 + 0.35F, par3 + 0.3F + f2);
							GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
							this.renderInner(tile, item);
							if (!isFancy) {
								GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
								this.renderInner(tile, item);
							}
							GL11.glPopMatrix();
						}
					} else {
						for (int i = 0; i <= rem; i++) {
							float f1 = i * 0.1F;
							float f2 = (i & 1) * 0.4F;
							GL11.glPushMatrix();
							GL11.glTranslatef(par1 + 0.3F + f2, par2 + 0.35F, par3 + 0.125F + f1);
							GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
							this.renderInner(tile, item);
							if (!isFancy) {
								GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
								this.renderInner(tile, item);
							}
							GL11.glPopMatrix();
						}
					}

				}
			}
		}
	}

	private void renderInner(TileContainerBase tile, ItemStack item) {

		if (item != null) {
			EntityItem entityitem = new EntityItem(tile.getWorldObj(), 0.0D, 0.0D, 0.0D, item);
			entityitem.getEntityItem().stackSize = 1;
			entityitem.hoverStart = 0.0F;

			if (item.getItem() instanceof ItemBlock) {
				GL11.glScalef(1.2F, 1.2F, 1.2F);
			} else {
				GL11.glScalef(1.2F, 1.2F, 1.2F);
			}

			RenderItem.renderInFrame = true;
			RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			RenderItem.renderInFrame = false;

			GL11.glScalef(1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityCaseAt((TileContainerBase) par1TileEntity, par2, par4, par6, par8);
	}
}
