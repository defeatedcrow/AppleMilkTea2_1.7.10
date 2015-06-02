package mods.defeatedcrow.client.renderblocks;

import org.lwjgl.opengl.GL11;

import mods.defeatedcrow.common.DCsAppleMilk;
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
public class RenderFilledBowl implements ISimpleBlockRenderingHandler {

	private IIcon boxIIcon;
	private IIcon contentsIIcon;

	public static int modelBowl = -1;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		int meta = metadata;
		if (meta > 9 && meta != 15)
			meta = 9;
		this.boxIIcon = Blocks.planks.getIcon(0, 0);
		this.contentsIIcon = DCsAppleMilk.bowlBlock.getIcon(0, meta);

		if (modelID == this.getRenderId()) {
			// box
			renderInvCuboid(renderer, block, 6.0F / 16.0F, 0.0F / 16.0F, 6.0F / 16.0F, 10.0F / 16.0F, 1.0F / 16.0F,
					10.0F / 16.0F, this.boxIIcon);
			renderInvCuboid(renderer, block, 4.0F / 16.0F, 1.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 3.0F / 16.0F,
					12.0F / 16.0F, this.boxIIcon);

			renderInvCuboid(renderer, block, 3.0F / 16.0F, 2.0F / 16.0F, 3.0F / 16.0F, 13.0F / 16.0F, 5.0F / 16.0F,
					4.0F / 16.0F, this.boxIIcon);
			renderInvCuboid(renderer, block, 3.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F, 13.0F / 16.0F, 5.0F / 16.0F,
					13.0F / 16.0F, this.boxIIcon);
			renderInvCuboid(renderer, block, 3.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F, 5.0F / 16.0F, 5.0F / 16.0F,
					12.0F / 16.0F, this.boxIIcon);
			renderInvCuboid(renderer, block, 12.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F, 13.0F / 16.0F, 5.0F / 16.0F,
					12.0F / 16.0F, this.boxIIcon);

			// contents
			if (meta < 10) {
				if (meta == 0 || meta == 4) {
					renderInvCuboid(renderer, block, 4.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F,
							7.0F / 16.0F, 12.0F / 16.0F, this.contentsIIcon);
					renderInvCuboid(renderer, block, 5.0F / 16.0F, 7.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F,
							8.0F / 16.0F, 11.0F / 16.0F, this.contentsIIcon);
				}
				renderInvCuboid(renderer, block, 4.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F,
						12.0F / 16.0F, this.contentsIIcon);
			}

		}

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		int meta = world.getBlockMetadata(x, y, z);
		if (meta > 9 && meta != 15)
			meta = 9;
		this.boxIIcon = Blocks.planks.getIcon(0, 0);
		this.contentsIIcon = DCsAppleMilk.bowlBlock.getIcon(0, meta);

		if (modelId == this.getRenderId()) {
			// box
			renderer.setOverrideBlockTexture(this.boxIIcon);
			block.setBlockBounds(6.0F / 16.0F, 0.0F / 16.0F, 6.0F / 16.0F, 10.0F / 16.0F, 1.0F / 16.0F, 10.0F / 16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			renderer.setOverrideBlockTexture(this.boxIIcon);
			block.setBlockBounds(4.0F / 16.0F, 1.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			// contents
			if (meta == 0 || meta == 4) {
				renderer.setOverrideBlockTexture(this.contentsIIcon);
				block.setBlockBounds(4.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 7.0F / 16.0F,
						12.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.contentsIIcon);
				block.setBlockBounds(5.0F / 16.0F, 7.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 8.0F / 16.0F,
						11.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (meta < 10) {
				renderer.setOverrideBlockTexture(this.contentsIIcon);
				block.setBlockBounds(4.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F,
						12.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			}

			renderer.setOverrideBlockTexture(this.boxIIcon);
			block.setBlockBounds(3.0F / 16.0F, 2.0F / 16.0F, 3.0F / 16.0F, 13.0F / 16.0F, 5.0F / 16.0F, 4.0F / 16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.setOverrideBlockTexture(this.boxIIcon);
			block.setBlockBounds(3.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F, 13.0F / 16.0F, 5.0F / 16.0F, 13.0F / 16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.setOverrideBlockTexture(this.boxIIcon);
			block.setBlockBounds(3.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F, 4.0F / 16.0F, 5.0F / 16.0F, 12.0F / 16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.setOverrideBlockTexture(this.boxIIcon);
			block.setBlockBounds(12.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F, 13.0F / 16.0F, 5.0F / 16.0F, 12.0F / 16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

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

		return DCsAppleMilk.modelBowl;
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
