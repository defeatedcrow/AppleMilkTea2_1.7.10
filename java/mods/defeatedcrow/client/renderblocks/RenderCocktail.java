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
public class RenderCocktail implements ISimpleBlockRenderingHandler {

	private IIcon boxIIcon;
	private IIcon contentsIIcon;
	private IIcon topIIcon;
	private IIcon bubbleIcon;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		int meta = metadata;
		this.boxIIcon = DCsAppleMilk.blockIcecream.getBlockTextureFromSide(0);
		this.contentsIIcon = block.getIcon(1, meta);
		this.topIIcon = block.getIcon(1, 5);
		this.bubbleIcon = Blocks.quartz_block.getBlockTextureFromSide(1);

		if (modelID == this.getRenderId()) {
			int glassType = 0;// long
			boolean frozen = false;
			if (block == DCsAppleMilk.cocktail) {
				if (meta < 5 || meta == 6 || meta == 10 || meta > 13)
					glassType = 1;
				if (meta < 2)
					frozen = true;
			}
			if (block == DCsAppleMilk.cocktail2) {
				if (meta == 6 || meta == 7 || meta == 1 || meta == 2 || meta == 9)
					glassType = 1;
			}

			if (glassType == 0) { // long type
				// bottom
				renderInvCuboid(renderer, block, 5.0F / 16.0F, 0.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 1.0F / 16.0F,
						11.0F / 16.0F, this.boxIIcon);
				renderInvCuboid(renderer, block, 4.0F / 16.0F, 1.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 9.0F / 16.0F,
						5.0F / 16.0F, this.boxIIcon);
				renderInvCuboid(renderer, block, 4.0F / 16.0F, 1.0F / 16.0F, 11.0F / 16.0F, 12.0F / 16.0F,
						9.0F / 16.0F, 12.0F / 16.0F, this.boxIIcon);
				renderInvCuboid(renderer, block, 4.0F / 16.0F, 1.0F / 16.0F, 5.0F / 16.0F, 5.0F / 16.0F, 9.0F / 16.0F,
						11.0F / 16.0F, this.boxIIcon);
				renderInvCuboid(renderer, block, 11.0F / 16.0F, 1.0F / 16.0F, 5.0F / 16.0F, 12.0F / 16.0F,
						9.0F / 16.0F, 11.0F / 16.0F, this.boxIIcon);

				// contents
				renderInvCuboid(renderer, block, 5.0F / 16.0F, 6.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 7.0F / 16.0F,
						11.0F / 16.0F, this.contentsIIcon);
			} else { // short type
						// bottom
				renderInvCuboid(renderer, block, 6.0F / 16.0F, 0.0F / 16.0F, 6.0F / 16.0F, 10.0F / 16.0F, 1.0F / 16.0F,
						10.0F / 16.0F, this.boxIIcon);
				renderInvCuboid(renderer, block, 7.5F / 16.0F, 1.0F / 16.0F, 7.5F / 16.0F, 8.50F / 16.0F, 3.0F / 16.0F,
						8.5F / 16.0F, this.boxIIcon);
				renderInvCuboid(renderer, block, 5.0F / 16.0F, 4.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 6.0F / 16.0F,
						11.0F / 16.0F, this.boxIIcon);

				renderInvCuboid(renderer, block, 4.0F / 16.0F, 6.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 8.0F / 16.0F,
						5.0F / 16.0F, this.boxIIcon);
				renderInvCuboid(renderer, block, 4.0F / 16.0F, 6.0F / 16.0F, 11.0F / 16.0F, 12.0F / 16.0F,
						8.0F / 16.0F, 12.0F / 16.0F, this.boxIIcon);
				renderInvCuboid(renderer, block, 4.0F / 16.0F, 6.0F / 16.0F, 5.0F / 16.0F, 5.0F / 16.0F, 8.0F / 16.0F,
						11.0F / 16.0F, this.boxIIcon);
				renderInvCuboid(renderer, block, 11.0F / 16.0F, 6.0F / 16.0F, 5.0F / 16.0F, 12.0F / 16.0F,
						8.0F / 16.0F, 11.0F / 16.0F, this.boxIIcon);

				// contents
				renderInvCuboid(renderer, block, 5.0F / 16.0F, 6.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 7.0F / 16.0F,
						11.0F / 16.0F, this.contentsIIcon);
				if (frozen) {// frozen
					renderInvCuboid(renderer, block, 5.5F / 16.0F, 7.0F / 16.0F, 5.5F / 16.0F, 10.5F / 16.0F,
							8.0F / 16.0F, 10.5F / 16.0F, this.contentsIIcon);
					renderInvCuboid(renderer, block, 6.0F / 16.0F, 8.0F / 16.0F, 6.0F / 16.0F, 10.0F / 16.0F,
							9.0F / 16.0F, 10.0F / 16.0F, this.contentsIIcon);
				}
			}
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		int meta = world.getBlockMetadata(x, y, z);
		this.boxIIcon = DCsAppleMilk.blockIcecream.getBlockTextureFromSide(0);
		this.contentsIIcon = block.getIcon(1, meta);
		this.topIIcon = block.getIcon(1, 5);
		this.bubbleIcon = Blocks.quartz_block.getBlockTextureFromSide(1);

		if (modelId == this.getRenderId()) {
			int glassType = 0;// long
			boolean frozen = false;
			boolean b = false;
			boolean c = false;

			if (block == DCsAppleMilk.cocktail) {
				if (meta < 5 || meta > 13)
					glassType = 1;
				else if (meta == 6 || meta == 10)
					glassType = 2;

				if (meta < 2)
					frozen = true;

				if (meta == 7)
					b = true;
			}

			if (block == DCsAppleMilk.cocktail2) {
				if (meta == 6 || meta == 7)
					glassType = 1;
				else if (meta == 1 || meta == 2 || meta == 9)
					glassType = 2;

				if (meta == 9)
					c = true;
			}

			if (b)// アメリカン・レモネードのみ特殊
			{
				renderer.setOverrideBlockTexture(this.topIIcon);
				block.setBlockBounds(5.0F / 16.0F, 6.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 8.0F / 16.0F,
						11.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.contentsIIcon);
				block.setBlockBounds(5.0F / 16.0F, 1.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 6.0F / 16.0F,
						11.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (frozen) {// frozen
				// contents
				renderer.setOverrideBlockTexture(this.contentsIIcon);
				block.setBlockBounds(5.0F / 16.0F, 6.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 7.5F / 16.0F,
						11.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.contentsIIcon);
				block.setBlockBounds(5.5F / 16.0F, 5.0F / 16.0F, 5.5F / 16.0F, 10.5F / 16.0F, 6.0F / 16.0F,
						10.5F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.contentsIIcon);
				block.setBlockBounds(5.5F / 16.0F, 7.5F / 16.0F, 5.5F / 16.0F, 10.5F / 16.0F, 8.5F / 16.0F,
						10.5F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.contentsIIcon);
				block.setBlockBounds(6.0F / 16.0F, 8.0F / 16.0F, 6.0F / 16.0F, 10.0F / 16.0F, 9.5F / 16.0F,
						10.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (glassType == 0) {// long
				renderer.setOverrideBlockTexture(this.contentsIIcon);
				block.setBlockBounds(5.0F / 16.0F, 6.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 8.0F / 16.0F,
						11.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.contentsIIcon);
				block.setBlockBounds(5.0F / 16.0F, 1.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 6.0F / 16.0F,
						11.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (glassType == 2) {// wine glass
				renderer.setOverrideBlockTexture(this.contentsIIcon);
				block.setBlockBounds(5.0F / 16.0F, 6.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 9.0F / 16.0F,
						11.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.contentsIIcon);
				block.setBlockBounds(5.5F / 16.0F, 5.0F / 16.0F, 5.5F / 16.0F, 10.5F / 16.0F, 6.0F / 16.0F,
						10.5F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				if (meta == 6)// pinacolada
				{
					renderer.setOverrideBlockTexture(this.contentsIIcon);
					block.setBlockBounds(5.5F / 16.0F, 9.0F / 16.0F, 5.5F / 16.0F, 10.5F / 16.0F, 9.5F / 16.0F,
							10.5F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.contentsIIcon);
					block.setBlockBounds(6.0F / 16.0F, 9.5F / 16.0F, 6.0F / 16.0F, 10.0F / 16.0F, 10.0F / 16.0F,
							10.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

			} else {// short
					// contents
				renderer.setOverrideBlockTexture(this.contentsIIcon);
				block.setBlockBounds(5.0F / 16.0F, 6.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 7.5F / 16.0F,
						11.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.contentsIIcon);
				block.setBlockBounds(5.5F / 16.0F, 5.0F / 16.0F, 5.5F / 16.0F, 10.5F / 16.0F, 6.0F / 16.0F,
						10.5F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			}

			if (c)// snow saronno
			{
				renderer.setOverrideBlockTexture(this.contentsIIcon);
				block.setBlockBounds(5.0F / 16.0F, 10.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 16.0F / 16.0F,
						11.0F / 16.0F);
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

		return DCsAppleMilk.modelCocktail;
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
