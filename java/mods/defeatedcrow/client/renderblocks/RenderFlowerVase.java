package mods.defeatedcrow.client.renderblocks;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
public class RenderFlowerVase implements ISimpleBlockRenderingHandler {

	private IIcon baseIcon;
	private IIcon underIcon;
	private IIcon topIcon;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		int meta = metadata;
		int dirMeta = (meta & 3);
		this.baseIcon = Blocks.quartz_block.getBlockTextureFromSide(0);
		this.underIcon = block.getIcon(1, meta);
		this.topIcon = block.getIcon(0, meta);

		if (modelID == this.getRenderId()) {
			// bottom
			renderInvCuboid(renderer, block, 4.0F, 0.0F, 4.0F, 12.0F, 1.0F, 12.0F, this.baseIcon);

			renderInvCuboid(renderer, block, 6.0F, 0.0F, 0.0F, 6.0F, 16.0F, 16.0F, this.underIcon);
			renderInvCuboid(renderer, block, 10.0F, 0.0F, 0.0F, 10.0F, 16.0F, 16.0F, this.underIcon);
			renderInvCuboid(renderer, block, 0.0F, 0.0F, 6.0F, 16.0F, 16.0F, 6.0F, this.underIcon);
			renderInvCuboid(renderer, block, 0.0F, 0.0F, 10.0F, 16.0F, 16.0F, 10.0F, this.underIcon);

			renderInvCuboid(renderer, block, 8.0F, 0.0F, 0.0F, 8.0F, 16.0F, 16.0F, this.topIcon);
			renderInvCuboid(renderer, block, 0.0F, 0.0F, 8.0F, 16.0F, 16.0F, 8.0F, this.topIcon);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		int meta = world.getBlockMetadata(x, y, z);
		int dir = (meta & 3);
		this.baseIcon = Blocks.quartz_block.getBlockTextureFromSide(0);
		this.underIcon = block.getIcon(1, meta);
		this.topIcon = block.getIcon(0, meta);
		float f = 0.0625F;

		if (world.getBlock(x, y - 1, z).getMaterial() == Material.ground
				|| world.getBlock(x, y - 1, z).getMaterial() == Material.grass) {
			this.baseIcon = Blocks.dirt.getBlockTextureFromSide(1);
		}

		if (modelId == this.getRenderId()) {
			// renderer.setOverrideBlockTexture(this.baseIcon);
			// block.setBlockBounds(4 * f, 0 * f, 4 * f, 12 * f, 1 * f, 12 * f);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);

			renderer.setOverrideBlockTexture(this.topIcon);
			block.setBlockBounds(0 * f, 0 * f, 0 * f, 16 * f, 16 * f, 16 * f);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderCrossedSquares(block, x, y, z);

			renderer.setOverrideBlockTexture(this.underIcon);
			block.setBlockBounds(5 * f, 0 * f, 0 * f, 5 * f, 16 * f, 16 * f);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			renderer.setOverrideBlockTexture(this.underIcon);
			block.setBlockBounds(0 * f, 0 * f, 5 * f, 16 * f, 16 * f, 5 * f);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			renderer.setOverrideBlockTexture(this.underIcon);
			block.setBlockBounds(11 * f, 0 * f, 0 * f, 11 * f, 16 * f, 16 * f);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			renderer.setOverrideBlockTexture(this.underIcon);
			block.setBlockBounds(0 * f, 0 * f, 11 * f, 16 * f, 16 * f, 11 * f);
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
		return DCsAppleMilk.modelFlowerVase;
	}

	private void renderInvCuboid(RenderBlocks renderer, Block block, float mX, float mY, float mZ, float xX, float xY,
			float xZ, IIcon icon) {
		float minX = mX * 0.0625F;
		float minY = mY * 0.0625F;
		float minZ = mZ * 0.0625F;
		float maxX = xX * 0.0625F;
		float maxY = xY * 0.0625F;
		float maxZ = xZ * 0.0625F;
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
