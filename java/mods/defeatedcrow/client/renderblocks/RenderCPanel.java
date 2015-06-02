package mods.defeatedcrow.client.renderblocks;

import org.lwjgl.opengl.GL11;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCPanel implements ISimpleBlockRenderingHandler {

	private IIcon boxIcon;
	private IIcon topIcon;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		int meta = metadata;
		this.boxIcon = DCsAppleMilk.chalcenonyPanel.getIcon(0, meta);
		this.topIcon = DCsAppleMilk.chalcenonyPanel.getIcon(1, meta);

		if (modelID == this.getRenderId()) {
			// bottom
			renderInvCuboid(renderer, block, 0.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F, 2.0F / 16.0F,
					16.0F / 16.0F, this.boxIcon);
			if ((meta & 7) > 0) {
				renderInvCuboid(renderer, block, 0.0F / 16.0F, 2.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F,
						2.01F / 16.0F, 16.0F / 16.0F, this.topIcon);
			}

		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		int meta = world.getBlockMetadata(x, y, z);
		this.boxIcon = DCsAppleMilk.chalcenonyPanel.getIcon(0, meta);
		this.topIcon = DCsAppleMilk.chalcenonyPanel.getIcon(1, meta);

		if (modelId == this.getRenderId()) {
			if (meta < 8) {
				renderer.setOverrideBlockTexture(this.boxIcon);
				block.setBlockBounds(0.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F, 2.0F / 16.0F,
						16.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				if (meta > 0) {
					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(0.0F / 16.0F, 2.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F, 2.01F / 16.0F,
							16.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}
			} else {
				renderer.setOverrideBlockTexture(this.boxIcon);
				block.setBlockBounds(0.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F, 1.0F / 16.0F,
						16.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				if (meta > 0) {
					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(0.0F / 16.0F, 1.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F, 1.01F / 16.0F,
							16.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}
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

		return DCsAppleMilk.modelCPanel;
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
