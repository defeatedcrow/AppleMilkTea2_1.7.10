package mods.defeatedcrow.client.renderblocks;

import org.lwjgl.opengl.GL11;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
public class RenderSoupPan implements ISimpleBlockRenderingHandler {

	private IIcon boxIIcon;
	private IIcon contentsIIcon;
	private IIcon contentsIIcon2;
	private IIcon chainTex;

	public static int modelPan = -1;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		int meta = metadata;
		if (meta > 12)
			meta = 12;
		this.boxIIcon = Blocks.hardened_clay.getBlockTextureFromSide(1);
		// this.contentsIIcon = DCsAppleMilk.filledPan.getIcon(0, meta);
		// this.contentsIIcon2 = DCsAppleMilk.filledPan2.getIcon(0, meta);

		if (modelID == this.getRenderId()) {
			// box
			renderInvCuboid(renderer, block, 3.0F / 16.0F, 0.0F / 16.0F, 3.0F / 16.0F, 13.0F / 16.0F, 2.0F / 16.0F,
					13.0F / 16.0F, this.boxIIcon);

			renderInvCuboid(renderer, block, 0.0F / 16.0F, 7.0F / 16.0F, 7.0F / 16.0F, 2.0F / 16.0F, 8.0F / 16.0F,
					9.0F / 16.0F, this.boxIIcon);
			renderInvCuboid(renderer, block, 14.0F / 16.0F, 7.0F / 16.0F, 7.0F / 16.0F, 16.0F / 16.0F, 8.0F / 16.0F,
					9.0F / 16.0F, this.boxIIcon);

			renderInvCuboid(renderer, block, 2.0F / 16.0F, 0.0F / 16.0F, 2.0F / 16.0F, 3.0F / 16.0F, 8.0F / 16.0F,
					14.0F / 16.0F, this.boxIIcon);
			renderInvCuboid(renderer, block, 13.0F / 16.0F, 0.0F / 16.0F, 2.0F / 16.0F, 14.0F / 16.0F, 8.0F / 16.0F,
					14.0F / 16.0F, this.boxIIcon);
			renderInvCuboid(renderer, block, 3.0F / 16.0F, 0.0F / 16.0F, 2.0F / 16.0F, 13.0F / 16.0F, 8.0F / 16.0F,
					4.0F / 16.0F, this.boxIIcon);
			renderInvCuboid(renderer, block, 3.0F / 16.0F, 0.0F / 16.0F, 13.0F / 16.0F, 13.0F / 16.0F, 8.0F / 16.0F,
					14.0F / 16.0F, this.boxIIcon);

			// contents
			// if (block == DCsAppleMilk.filledPan)
			// {
			// renderInvCuboid(renderer, block, 3.0F/16.0F, 2.0F/16.0F, 3.0F/16.0F, 13.0F/16.0F, 6.0F/16.0F,
			// 13.0F/16.0F, this.contentsIIcon);
			// }
			// if (block == DCsAppleMilk.filledPan2)
			// {
			// renderInvCuboid(renderer, block, 3.0F/16.0F, 2.0F/16.0F, 3.0F/16.0F, 13.0F/16.0F, 6.0F/16.0F,
			// 13.0F/16.0F, this.contentsIIcon2);
			// }
		}

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		int meta = world.getBlockMetadata(x, y, z);
		if (meta > 12)
			meta = 12;
		this.boxIIcon = Blocks.hardened_clay.getBlockTextureFromSide(1);
		// this.contentsIIcon = DCsAppleMilk.filledPan.getIcon(0, meta);
		// this.contentsIIcon2 = DCsAppleMilk.filledPan2.getIcon(0, meta);
		this.chainTex = DCsAppleMilk.emptyPanGaiden.getBlockTextureFromSide(0);

		if (modelId == this.getRenderId()) {
			if (!world.isAirBlock(x, y + 1, z) && world.getBlock(x, y + 1, z).getMaterial() != Material.water) {
				renderer.setOverrideBlockTexture(this.chainTex);
				block.setBlockBounds(0.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F, 16.0F / 16.0F,
						16.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderCrossedSquares(block, x, y, z);
			}

			// box
			renderer.setOverrideBlockTexture(this.boxIIcon);
			block.setBlockBounds(3.0F / 16.0F, 0.0F / 16.0F, 3.0F / 16.0F, 13.0F / 16.0F, 2.0F / 16.0F, 13.0F / 16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);

			// if (DCsConfig.noUseCupDirection){
			// renderer.setOverrideBlockTexture(this.boxIIcon);
			// block.setBlockBounds(0.0F/16.0F, 7.0F/16.0F, 6.0F/16.0F, 2.0F/16.0F, 8.0F/16.0F, 10.0F/16.0F);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);
			//
			// renderer.setOverrideBlockTexture(this.boxIIcon);
			// block.setBlockBounds(14.0F/16.0F, 7.0F/16.0F, 6.0F/16.0F, 16.0F/16.0F, 8.0F/16.0F, 10.0F/16.0F);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);
			// }

			// contents
			// if (block == DCsAppleMilk.filledPan)
			// {
			// if (meta < 4)
			// {
			// renderer.setOverrideBlockTexture(this.contentsIIcon);
			// block.setBlockBounds(3.0F/16.0F, 2.0F/16.0F, 3.0F/16.0F, 13.0F/16.0F, 3.0F/16.0F, 13.0F/16.0F);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);
			// }
			// else if (meta < 8)
			// {
			// renderer.setOverrideBlockTexture(this.contentsIIcon);
			// block.setBlockBounds(3.0F/16.0F, 2.0F/16.0F, 3.0F/16.0F, 13.0F/16.0F, 5.0F/16.0F, 13.0F/16.0F);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);
			// }
			// else
			// {
			// renderer.setOverrideBlockTexture(this.contentsIIcon);
			// block.setBlockBounds(3.0F/16.0F, 2.0F/16.0F, 3.0F/16.0F, 13.0F/16.0F, 7.0F/16.0F, 13.0F/16.0F);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);
			// }
			// }
			// if (block == DCsAppleMilk.filledPan2)
			// {
			// if (meta < 4)
			// {
			// renderer.setOverrideBlockTexture(this.contentsIIcon2);
			// block.setBlockBounds(3.0F/16.0F, 2.0F/16.0F, 3.0F/16.0F, 13.0F/16.0F, 3.0F/16.0F, 13.0F/16.0F);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);
			// }
			// else if (meta < 8)
			// {
			// renderer.setOverrideBlockTexture(this.contentsIIcon2);
			// block.setBlockBounds(3.0F/16.0F, 2.0F/16.0F, 3.0F/16.0F, 13.0F/16.0F, 5.0F/16.0F, 13.0F/16.0F);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);
			// }
			// else
			// {
			// renderer.setOverrideBlockTexture(this.contentsIIcon2);
			// block.setBlockBounds(3.0F/16.0F, 2.0F/16.0F, 3.0F/16.0F, 13.0F/16.0F, 7.0F/16.0F, 13.0F/16.0F);
			// renderer.setRenderBoundsFromBlock(block);
			// renderer.renderStandardBlock(block, x, y, z);
			// }
			// }

			renderer.setOverrideBlockTexture(this.boxIIcon);
			block.setBlockBounds(2.0F / 16.0F, 0.0F / 16.0F, 2.0F / 16.0F, 3.0F / 16.0F, 8.0F / 16.0F, 14.0F / 16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.setOverrideBlockTexture(this.boxIIcon);
			block.setBlockBounds(13.0F / 16.0F, 0.0F / 16.0F, 2.0F / 16.0F, 14.0F / 16.0F, 8.0F / 16.0F, 14.0F / 16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.setOverrideBlockTexture(this.boxIIcon);
			block.setBlockBounds(3.0F / 16.0F, 0.0F / 16.0F, 2.0F / 16.0F, 13.0F / 16.0F, 8.0F / 16.0F, 3.0F / 16.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.setOverrideBlockTexture(this.boxIIcon);
			block.setBlockBounds(3.0F / 16.0F, 0.0F / 16.0F, 13.0F / 16.0F, 13.0F / 16.0F, 8.0F / 16.0F, 14.0F / 16.0F);
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

		return DCsAppleMilk.modelPan;
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
