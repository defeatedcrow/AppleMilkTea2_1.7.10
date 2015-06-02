package mods.defeatedcrow.client.renderblocks;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import org.lwjgl.opengl.GL11;

import mods.defeatedcrow.common.*;
import mods.defeatedcrow.common.block.BlockYuzuFence;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderYuzuFence implements ISimpleBlockRenderingHandler {

	private IIcon fenceIcon;
	private IIcon bottomIcon;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		int meta = metadata;
		int m = meta & 4;
		this.fenceIcon = DCsAppleMilk.yuzuFence.getIcon(0, 0);
		this.bottomIcon = Blocks.stone_slab.getIcon(1, 0);

		if (modelID == this.getRenderId()) {
			// bottom
			renderInvCuboid(renderer, block, 6.0F / 16.0F, 0.0F / 16.0F, 6.0F / 16.0F, 10.0F / 16.0F, 1.0F / 16.0F,
					10.0F / 16.0F, this.bottomIcon);
			// flower
			renderInvCuboid(renderer, block, 8.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 8.0F / 16.0F, 16.0F / 16.0F,
					16.0F / 16.0F, this.fenceIcon);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		this.fenceIcon = DCsAppleMilk.yuzuFence.getIcon(0, 0);
		this.bottomIcon = Blocks.stone_slab.getIcon(1, 0);

		if (modelId == this.getRenderId() && block instanceof BlockYuzuFence) {
			BlockYuzuFence fence = (BlockYuzuFence) block;
			boolean north = fence.canConnectBlock(world, x, y, z - 1, NORTH);
			boolean south = fence.canConnectBlock(world, x, y, z + 1, SOUTH);
			boolean west = fence.canConnectBlock(world, x - 1, y, z, WEST);
			boolean east = fence.canConnectBlock(world, x + 1, y, z, EAST);

			renderer.setOverrideBlockTexture(this.bottomIcon);
			block.setBlockBounds(6.0F / 16.0F, 0.0F / 16.0F, 6.0F / 16.0F, 10.0F / 16.0F, 2.0F / 16.0F, 10.0F / 16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			boolean flag = false;

			if (fence.canConnectBlock(world, x, y, z - 1, NORTH)) {

				renderer.setOverrideBlockTexture(this.fenceIcon);
				block.setBlockBounds(8.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 8.0F / 16.0F, 16.0F / 16.0F,
						8.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.bottomIcon);
				block.setBlockBounds(6.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 10.0F / 16.0F, 2.0F / 16.0F,
						6.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				flag = true;
			}

			if (fence.canConnectBlock(world, x, y, z + 1, SOUTH)) {

				renderer.setOverrideBlockTexture(this.fenceIcon);
				block.setBlockBounds(8.0F / 16.0F, 0.0F / 16.0F, 8.0F / 16.0F, 8.0F / 16.0F, 16.0F / 16.0F,
						16.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.bottomIcon);
				block.setBlockBounds(6.0F / 16.0F, 0.0F / 16.0F, 10.0F / 16.0F, 10.0F / 16.0F, 2.0F / 16.0F,
						16.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				flag = true;
			}

			if (fence.canConnectBlock(world, x - 1, y, z, WEST)) {

				renderer.setOverrideBlockTexture(this.fenceIcon);
				block.setBlockBounds(0.0F / 16.0F, 0.0F / 16.0F, 8.0F / 16.0F, 8.0F / 16.0F, 16.0F / 16.0F,
						8.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.bottomIcon);
				block.setBlockBounds(0.0F / 16.0F, 0.0F / 16.0F, 6.0F / 16.0F, 6.0F / 16.0F, 2.0F / 16.0F,
						10.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				flag = true;
			}

			if (fence.canConnectBlock(world, x + 1, y, z, EAST)) {

				renderer.setOverrideBlockTexture(this.fenceIcon);
				block.setBlockBounds(8.0F / 16.0F, 0.0F / 16.0F, 8.0F / 16.0F, 16.0F / 16.0F, 16.0F / 16.0F,
						8.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.bottomIcon);
				block.setBlockBounds(10.0F / 16.0F, 0.0F / 16.0F, 6.0F / 16.0F, 16.0F / 16.0F, 2.0F / 16.0F,
						10.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				flag = true;
			}

			if (!flag) {
				renderer.setOverrideBlockTexture(this.fenceIcon);
				block.setBlockBounds(8.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 8.0F / 16.0F, 16.0F / 16.0F,
						16.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.fenceIcon);
				block.setBlockBounds(0.0F / 16.0F, 0.0F / 16.0F, 8.0F / 16.0F, 16.0F / 16.0F, 16.0F / 16.0F,
						8.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			} else {

			}

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

		return DCsAppleMilk.modelYuzuFence;
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
