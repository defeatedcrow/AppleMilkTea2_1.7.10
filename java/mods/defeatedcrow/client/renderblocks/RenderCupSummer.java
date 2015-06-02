package mods.defeatedcrow.client.renderblocks;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
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
public class RenderCupSummer implements ISimpleBlockRenderingHandler {

	private IIcon boxIIcon;
	private IIcon contentsIIcon;
	private IIcon waterIIcon;
	private IIcon contents2;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		int meta = metadata;
		if (meta > 13)
			meta = 13;
		this.boxIIcon = DCsAppleMilk.emptyCup.getBlockTextureFromSide(1);
		this.contentsIIcon = DCsAppleMilk.teacupBlock.getIcon(0, meta);
		this.waterIIcon = Blocks.water.getBlockTextureFromSide(1);
		this.contents2 = DCsAppleMilk.teaCup2.getIcon(0, meta);

		if (modelID == this.getRenderId()) {
			// bottom & side
			renderInvCuboid(renderer, block, 5.0F / 16.0F, 0.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 2.0F / 16.0F,
					11.0F / 16.0F, this.boxIIcon);

			renderInvCuboid(renderer, block, 5.0F / 16.0F, 2.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 9.0F / 16.0F,
					6.0F / 16.0F, this.boxIIcon);
			renderInvCuboid(renderer, block, 5.0F / 16.0F, 2.0F / 16.0F, 10.0F / 16.0F, 11.0F / 16.0F, 9.0F / 16.0F,
					11.0F / 16.0F, this.boxIIcon);
			renderInvCuboid(renderer, block, 5.0F / 16.0F, 2.0F / 16.0F, 6.0F / 16.0F, 6.0F / 16.0F, 9.0F / 16.0F,
					10.0F / 16.0F, this.boxIIcon);
			renderInvCuboid(renderer, block, 10.0F / 16.0F, 2.0F / 16.0F, 6.0F / 16.0F, 11.0F / 16.0F, 9.0F / 16.0F,
					10.0F / 16.0F, this.boxIIcon);

			// contents
			if (block == DCsAppleMilk.emptyCup) {
				renderInvCuboid(renderer, block, 6.0F / 16.0F, 2.0F / 16.0F, 6.0F / 16.0F, 10.0F / 16.0F, 7.0F / 16.0F,
						10.0F / 16.0F, this.waterIIcon);
			} else if (block == DCsAppleMilk.teacupBlock) {
				renderInvCuboid(renderer, block, 6.0F / 16.0F, 2.0F / 16.0F, 6.0F / 16.0F, 10.0F / 16.0F, 7.0F / 16.0F,
						10.0F / 16.0F, this.contentsIIcon);
			} else {
				renderInvCuboid(renderer, block, 6.0F / 16.0F, 2.0F / 16.0F, 6.0F / 16.0F, 10.0F / 16.0F, 7.0F / 16.0F,
						10.0F / 16.0F, this.contents2);
			}

		}

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		int meta = world.getBlockMetadata(x, y, z);
		this.boxIIcon = DCsAppleMilk.emptyCup.getBlockTextureFromSide(1);
		this.contentsIIcon = DCsAppleMilk.teacupBlock.getIcon(0, meta);
		this.contents2 = DCsAppleMilk.teaCup2.getIcon(0, meta);
		this.waterIIcon = Blocks.water.getBlockTextureFromSide(1);

		if (modelId == this.getRenderId()) {
			// if (DCsConfig.noUseCupDirection)
			// {
			// //bottom
			// renderer.setOverrideBlockTexture(this.boxIIcon);
			// block.setBlockBounds(5.0F/16.0F, 0.0F/16.0F, 5.0F/16.0F, 11.0F/16.0F, 2.0F/16.0F, 11.0F/16.0F);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);
			//
			// //box
			// renderer.setOverrideBlockTexture(this.boxIIcon);
			// block.setBlockBounds(5.0F/16.0F, 2.0F/16.0F, 6.0F/16.0F, 6.0F/16.0F, 9.0F/16.0F, 11.0F/16.0F);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);
			// renderer.setOverrideBlockTexture(this.boxIIcon);
			// block.setBlockBounds(5.0F/16.0F, 2.0F/16.0F, 5.0F/16.0F, 11.0F/16.0F, 9.0F/16.0F, 6.0F/16.0F);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);
			// renderer.setOverrideBlockTexture(this.boxIIcon);
			// block.setBlockBounds(10.0F/16.0F, 2.0F/16.0F, 6.0F/16.0F, 11.0F/16.0F, 9.0F/16.0F, 11.0F/16.0F);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);
			// renderer.setOverrideBlockTexture(this.boxIIcon);
			// block.setBlockBounds(6.0F/16.0F, 2.0F/16.0F, 10.0F/16.0F, 10.0F/16.0F, 9.0F/16.0F, 11.0F/16.0F);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);
			// }

			if (block == DCsAppleMilk.emptyCup) {
				renderer.setOverrideBlockTexture(this.waterIIcon);
				block.setBlockBounds(6.0F / 16.0F, 1.0F / 16.0F, 6.0F / 16.0F, 10.0F / 16.0F, 6.0F / 16.0F,
						10.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (block == DCsAppleMilk.teacupBlock) {
				renderer.setOverrideBlockTexture(this.contentsIIcon);
				block.setBlockBounds(6.0F / 16.0F, 1.0F / 16.0F, 6.0F / 16.0F, 10.0F / 16.0F, 6.0F / 16.0F,
						10.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			} else {
				renderer.setOverrideBlockTexture(this.contents2);
				block.setBlockBounds(6.0F / 16.0F, 1.0F / 16.0F, 6.0F / 16.0F, 10.0F / 16.0F, 6.0F / 16.0F,
						10.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
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

		return DCsAppleMilk.modelCupSummer;
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
