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
public class RenderIceMaker implements ISimpleBlockRenderingHandler {

	private IIcon boxIIcon;
	private IIcon ironIIcon;
	private IIcon iceIIcon;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		int meta = metadata;
		this.boxIIcon = DCsAppleMilk.iceMaker.getBlockTextureFromSide(0);
		this.iceIIcon = Blocks.ice.getBlockTextureFromSide(1);
		this.ironIIcon = DCsAppleMilk.teppanII.getBlockTextureFromSide(1);

		if (modelID == this.getRenderId()) {
			// bottom
			renderInvCuboid(renderer, block, 2.0F / 16.0F, 0.0F / 16.0F, 2.0F / 16.0F, 14.0F / 16.0F, 1.0F / 16.0F,
					14.0F / 16.0F, this.boxIIcon);
			// legs
			renderInvCuboid(renderer, block, 4.0F / 16.0F, 1.0F / 16.0F, 4.0F / 16.0F, 5.0F / 16.0F, 6.0F / 16.0F,
					5.0F / 16.0F, this.boxIIcon);
			renderInvCuboid(renderer, block, 4.0F / 16.0F, 1.0F / 16.0F, 11.0F / 16.0F, 5.0F / 16.0F, 6.0F / 16.0F,
					12.0F / 16.0F, this.boxIIcon);
			renderInvCuboid(renderer, block, 11.0F / 16.0F, 1.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 6.0F / 16.0F,
					5.0F / 16.0F, this.boxIIcon);
			renderInvCuboid(renderer, block, 11.0F / 16.0F, 1.0F / 16.0F, 11.0F / 16.0F, 12.0F / 16.0F, 6.0F / 16.0F,
					12.0F / 16.0F, this.boxIIcon);
			// body
			renderInvCuboid(renderer, block, 4.0F / 16.0F, 6.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 10.0F / 16.0F,
					12.0F / 16.0F, this.boxIIcon);
			// handle
			renderInvCuboid(renderer, block, 7.5F / 16.0F, 10.0F / 16.0F, 7.5F / 16.0F, 8.5F / 16.0F, 12.0F / 16.0F,
					8.5F / 16.0F, this.ironIIcon);
			renderInvCuboid(renderer, block, 6F / 16.0F, 12.0F / 16.0F, 6F / 16.0F, 10F / 16.0F, 13.0F / 16.0F,
					10F / 16.0F, this.ironIIcon);

		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		if (modelId == this.getRenderId()) {
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

		return DCsAppleMilk.modelIceMaker;
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
