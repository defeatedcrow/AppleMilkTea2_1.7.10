package mods.defeatedcrow.client.renderblocks;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderChopsticksBox implements ISimpleBlockRenderingHandler {

	private IIcon boxIIcon;
	private IIcon contentsIIcon;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		int meta = metadata;
		this.boxIIcon = Blocks.planks.getIcon(0, 0);
		this.contentsIIcon = Blocks.planks.getIcon(0, 1);

		if (modelID == this.getRenderId()) {
			// box
			renderInvCuboid(renderer, block, 6.0F / 16.0F, 0.0F / 16.0F, 6.0F / 16.0F, 10.0F / 16.0F, 5.0F / 16.0F,
					10.0F / 16.0F, this.boxIIcon);

			if (meta > 0) {
				renderInvCuboid(renderer, block, 7.0F / 16.0F, 2.0F / 16.0F, 7.0F / 16.0F, 8.0F / 16.0F, 11.0F / 16.0F,
						8.0F / 16.0F, this.contentsIIcon);
				if (meta > 1) {
					renderInvCuboid(renderer, block, 7.0F / 16.0F, 2.0F / 16.0F, 8.0F / 16.0F, 8.0F / 16.0F,
							11.0F / 16.0F, 9.0F / 16.0F, this.contentsIIcon);
					if (meta > 2) {
						renderInvCuboid(renderer, block, 8.0F / 16.0F, 2.0F / 16.0F, 7.0F / 16.0F, 9.0F / 16.0F,
								11.0F / 16.0F, 8.0F / 16.0F, this.contentsIIcon);
						if (meta > 3) {
							renderInvCuboid(renderer, block, 8.0F / 16.0F, 2.0F / 16.0F, 8.0F / 16.0F, 9.0F / 16.0F,
									11.0F / 16.0F, 9.0F / 16.0F, this.contentsIIcon);
						}
					}
				}
			}

		}

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		int meta = world.getBlockMetadata(x, y, z);
		this.boxIIcon = Blocks.planks.getIcon(0, 0);
		this.contentsIIcon = Blocks.planks.getIcon(0, 1);

		if (modelId == this.getRenderId()) {
			// if (DCsConfig.noUseCupDirection)
			// {
			// renderer.setOverrideBlockTexture(this.boxIIcon);
			// block.setBlockBounds(6.0F/16.0F, 0.0F/16.0F, 6.0F/16.0F, 10.0F/16.0F, 5.0F/16.0F, 10.0F/16.0F);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);
			//
			// //contents
			//
			// if (meta > 0)
			// {
			// renderer.setOverrideBlockTexture(this.contentsIIcon);
			// block.setBlockBounds(7.0F/16.0F, 2.0F/16.0F, 7.0F/16.0F, 8.0F/16.0F, 11.0F/16.0F, 8.0F/16.0F);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);
			//
			// if (meta > 1)
			// {
			// renderer.setOverrideBlockTexture(this.contentsIIcon);
			// block.setBlockBounds(7.0F/16.0F, 2.0F/16.0F, 8.0F/16.0F, 8.0F/16.0F, 11.0F/16.0F, 9.0F/16.0F);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);
			//
			// if (meta > 2)
			// {
			// renderer.setOverrideBlockTexture(this.contentsIIcon);
			// block.setBlockBounds(8.0F/16.0F, 2.0F/16.0F, 7.0F/16.0F, 9.0F/16.0F, 11.0F/16.0F, 8.0F/16.0F);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);
			//
			// if (meta > 3)
			// {
			// renderer.setOverrideBlockTexture(this.contentsIIcon);
			// block.setBlockBounds(8.0F/16.0F, 2.0F/16.0F, 8.0F/16.0F, 9.0F/16.0F, 11.0F/16.0F, 9.0F/16.0F);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);
			// }
			// }
			// }
			// }
			// }
			renderer.clearOverrideBlockTexture();
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			renderer.setRenderBoundsFromBlock(block);
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int a) {

		return true;
	}

	@Override
	public int getRenderId() {

		return DCsAppleMilk.modelChopsticks;
	}

	private void renderInvCuboid(RenderBlocks renderer, Block block, float minX, float minY, float minZ, float maxX,
			float maxY, float maxZ, IIcon icon) {
		Tessellator tessellator = Tessellator.instance;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		block.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		renderer.setRenderBoundsFromBlock(block);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
	}
}
