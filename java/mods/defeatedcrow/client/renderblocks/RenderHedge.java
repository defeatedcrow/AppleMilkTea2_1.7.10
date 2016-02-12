package mods.defeatedcrow.client.renderblocks;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.block.container.BlockHedge;
import mods.defeatedcrow.common.block.container.BlockHedge.HedgeType;
import mods.defeatedcrow.handler.TimeHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHedge implements ISimpleBlockRenderingHandler {

	private IIcon baseIcon;
	private IIcon woodIcon;
	private IIcon topIcon;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		int meta = metadata & 7;
		BlockHedge hedge = null;
		if (block instanceof BlockHedge)
			hedge = (BlockHedge) block;
		else
			return;
		HedgeType type = hedge.getType(meta);
		if (type == HedgeType.LATTICE)
			this.baseIcon = block.getIcon(3, 0);
		else if (type == HedgeType.FENCE)
			this.baseIcon = block.getIcon(2, 0);
		else
			this.baseIcon = block.getIcon(1, 0);
		this.topIcon = block.getIcon(0, meta);
		this.woodIcon = Blocks.planks.getBlockTextureFromSide(1);

		if (modelID == this.getRenderId()) {
			// bottom
			if (type == HedgeType.LATTICE) {
				renderInvCuboid(renderer, block, 8.0F, 1.0F, 1.0F, 8.0F, 15.0F, 15.0F, this.baseIcon);

				renderInvCuboid(renderer, block, 7.5F, 0.0F, 0.0F, 8.5F, 1.0F, 16.0F, this.woodIcon);
				renderInvCuboid(renderer, block, 7.5F, 15.0F, 0.0F, 8.5F, 16.0F, 16.0F, this.woodIcon);
				renderInvCuboid(renderer, block, 7.5F, 1.0F, 0.0F, 8.5F, 15.0F, 1.0F, this.woodIcon);
				renderInvCuboid(renderer, block, 7.5F, 1.0F, 15.0F, 8.5F, 15.0F, 16.0F, this.woodIcon);

				renderInvCuboid(renderer, block, 7.0F, 0.0F, 0.0F, 7.0F, 16.0F, 16.0F, this.topIcon);
				renderInvCuboid(renderer, block, 9.0F, 0.0F, 0.0F, 9.0F, 16.0F, 16.0F, this.topIcon);
			} else if (type == HedgeType.FENCE) {
				renderInvCuboid(renderer, block, 8.0F, 1.0F, 1.0F, 8.0F, 15.0F, 15.0F, this.baseIcon);

				renderInvCuboid(renderer, block, 7.0F, 0.0F, 0.0F, 7.0F, 16.0F, 16.0F, this.topIcon);
				renderInvCuboid(renderer, block, 9.0F, 0.0F, 0.0F, 9.0F, 16.0F, 16.0F, this.topIcon);

				renderInvCuboid(renderer, block, 6.0F, 0.0F, 6.0F, 10.0F, 16.0F, 6.0F, this.topIcon);
				renderInvCuboid(renderer, block, 6.0F, 0.0F, 10.0F, 10.0F, 16.0F, 10.0F, this.topIcon);
			} else {
				renderInvCuboid(renderer, block, 5.0F, 0.0F, 1.0F, 11.0F, 15.0F, 15.0F, this.baseIcon);
				renderInvCuboid(renderer, block, 4.0F, 0.0F, 0.0F, 12.0F, 16.0F, 16.0F, this.topIcon);
			}
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		int metadata = world.getBlockMetadata(x, y, z);
		int meta = metadata & 7;
		boolean flag = metadata > 7;
		BlockHedge hedge = null;
		if (block instanceof BlockHedge)
			hedge = (BlockHedge) block;
		else
			return false;
		HedgeType type = hedge.getType(meta);
		if (type == HedgeType.LATTICE)
			this.baseIcon = block.getIcon(3, 0);
		else if (type == HedgeType.FENCE)
			this.baseIcon = block.getIcon(2, 0);
		else
			this.baseIcon = block.getIcon(1, 0);
		this.topIcon = block.getIcon(0, meta);
		if (meta == 3) {
			World forTime = DCsAppleMilk.proxy.getClientWorld();
			if (TimeHandler.getSeason(forTime) == 1 && !TimeHandler.isDayTime(forTime)) {
				this.topIcon = block.getIcon(5, 0);
			} else if (TimeHandler.getSeason(forTime) == 2) {
				this.topIcon = block.getIcon(4, 0);
			}
		}
		if (meta == 6) {
			World forTime = DCsAppleMilk.proxy.getClientWorld();
			if (TimeHandler.getSeason(forTime) < 2) {
				this.topIcon = block.getIcon(6, 0);
			} else if (TimeHandler.getSeason(forTime) == 3) {
				this.topIcon = block.getIcon(7, 0);
			}
		}
		if (meta == 0) {
			World forTime = DCsAppleMilk.proxy.getClientWorld();
			if (TimeHandler.getSeason(forTime) == 3) {
				this.topIcon = block.getIcon(8, 0);
			}
		}
		this.woodIcon = Blocks.planks.getBlockTextureFromSide(1);
		float f = 0.0625F;

		if (modelId == this.getRenderId()) {

			boolean[] adj = {
					false,
					false,
					false,
					false }; // n, s, e, w
			if (world.getBlock(x, y, z + 1) == block) {
				adj[0] = true;
			}
			if (world.getBlock(x, y, z - 1) == block) {
				adj[1] = true;
			}
			if (world.getBlock(x + 1, y, z) == block) {
				adj[2] = true;
			}
			if (world.getBlock(x - 1, y, z) == block) {
				adj[3] = true;
			}

			if (type == HedgeType.LATTICE) {
				if (!flag) {
					renderer.setOverrideBlockTexture(this.baseIcon);
					block.setBlockBounds(8 * f, 1 * f, 1 * f, 8 * f, 15 * f, 15 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(7.5F * f, 0 * f, 0 * f, 8.5F * f, 1 * f, 16 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(7.5F * f, 15 * f, 0 * f, 8.5F * f, 16 * f, 16 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(7.5F * f, 1 * f, 0 * f, 8.5F * f, 15 * f, 1 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(7.5F * f, 1 * f, 15 * f, 8.5F * f, 15 * f, 16 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(7 * f, 0 * f, 0 * f, 7 * f, 16 * f, 16 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(9 * f, 0 * f, 0 * f, 9 * f, 16 * f, 16 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

				} else {

					renderer.setOverrideBlockTexture(this.baseIcon);
					block.setBlockBounds(1 * f, 1 * f, 8 * f, 15 * f, 15 * f, 8 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(0 * f, 0 * f, 7.5F * f, 16 * f, 1 * f, 8.5F * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(0 * f, 15 * f, 7.5F * f, 16 * f, 16 * f, 8.5F * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(0 * f, 1 * f, 7.5F * f, 1 * f, 15 * f, 8.5F * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.woodIcon);
					block.setBlockBounds(15 * f, 1 * f, 7.5F * f, 16 * f, 15 * f, 8.5F * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(0 * f, 0 * f, 7 * f, 16 * f, 16 * f, 7 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(0 * f, 0 * f, 9 * f, 16 * f, 16 * f, 9 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

				}
			}

			// lattice以外
			else if (type == HedgeType.FENCE) {
				// 中央部
				renderer.setOverrideBlockTexture(this.topIcon);
				block.setBlockBounds(6 * f, 0 * f, 6 * f, 10 * f, 16 * f, 6 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.topIcon);
				block.setBlockBounds(6 * f, 0 * f, 10 * f, 10 * f, 16 * f, 10 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.topIcon);
				block.setBlockBounds(6 * f, 0 * f, 6 * f, 6 * f, 16 * f, 10 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.topIcon);
				block.setBlockBounds(10 * f, 0 * f, 6 * f, 10 * f, 16 * f, 10 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				if (adj[0]) {
					renderer.setOverrideBlockTexture(this.baseIcon);
					block.setBlockBounds(8 * f, 0 * f, 8 * f, 8 * f, 16 * f, 16 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(4 * f, 0 * f, 12 * f, 12 * f, 16 * f, 12 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(6 * f, 0 * f, 10 * f, 6 * f, 16 * f, 16 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(10 * f, 0 * f, 10 * f, 10 * f, 16 * f, 16 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

				if (adj[1]) {
					renderer.setOverrideBlockTexture(this.baseIcon);
					block.setBlockBounds(8 * f, 0 * f, 0 * f, 8 * f, 16 * f, 8 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(4 * f, 0 * f, 4 * f, 12 * f, 16 * f, 4 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(6 * f, 0 * f, 0 * f, 6 * f, 16 * f, 6 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(10 * f, 0 * f, 0 * f, 10 * f, 16 * f, 6 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

				if (adj[2]) {
					renderer.setOverrideBlockTexture(this.baseIcon);
					block.setBlockBounds(8 * f, 0 * f, 8 * f, 16 * f, 16 * f, 8 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(10 * f, 0 * f, 6 * f, 16 * f, 16 * f, 6 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(10 * f, 0 * f, 10 * f, 16 * f, 16 * f, 10 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(12 * f, 0 * f, 4 * f, 12 * f, 16 * f, 12 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

				if (adj[3]) {
					renderer.setOverrideBlockTexture(this.baseIcon);
					block.setBlockBounds(0 * f, 0 * f, 8 * f, 8 * f, 16 * f, 8 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(0 * f, 0 * f, 6 * f, 6 * f, 16 * f, 6 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(0 * f, 0 * f, 10 * f, 6 * f, 16 * f, 10 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(4 * f, 0 * f, 4 * f, 4 * f, 16 * f, 12 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

			} else if (type == HedgeType.LOW && world.getBlock(x, y + 1, z) != block) {
				renderer.setOverrideBlockTexture(this.baseIcon);
				block.setBlockBounds(5 * f, 0 * f, 5 * f, 11 * f, 7 * f, 11 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.topIcon);
				block.setBlockBounds(4 * f, 0 * f, 4 * f, 12 * f, 8 * f, 12 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				if (adj[0]) {
					renderer.setOverrideBlockTexture(this.baseIcon);
					block.setBlockBounds(5 * f, 0 * f, 11.01F * f, 11 * f, 7 * f, 15.99F * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(4 * f, 0 * f, 12.01F * f, 12 * f, 8 * f, 16 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

				if (adj[1]) {
					renderer.setOverrideBlockTexture(this.baseIcon);
					block.setBlockBounds(5 * f, 0 * f, 0.01F * f, 11 * f, 7 * f, 4.99F * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(4 * f, 0 * f, 0 * f, 12 * f, 8 * f, 3.99F * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

				if (adj[2]) {
					renderer.setOverrideBlockTexture(this.baseIcon);
					block.setBlockBounds(11.01F * f, 0 * f, 5 * f, 15.99F * f, 7 * f, 11 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(12.01F * f, 0 * f, 4 * f, 16 * f, 8 * f, 12 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

				if (adj[3]) {
					renderer.setOverrideBlockTexture(this.baseIcon);
					block.setBlockBounds(0.01F * f, 0 * f, 5 * f, 4.99F * f, 7 * f, 11 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(0 * f, 0 * f, 4 * f, 3.99F * f, 8 * f, 12 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

			} else {
				renderer.setOverrideBlockTexture(this.baseIcon);
				block.setBlockBounds(5 * f, 0 * f, 5 * f, 11 * f, 15.99F * f, 11 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.topIcon);
				block.setBlockBounds(4 * f, 0 * f, 4 * f, 12 * f, 16 * f, 12 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				if (adj[0]) {
					renderer.setOverrideBlockTexture(this.baseIcon);
					block.setBlockBounds(5 * f, 0 * f, 11.01F * f, 11 * f, 16F * f, 15.99F * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(4 * f, 0 * f, 12.01F * f, 12 * f, 16 * f, 16 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

				if (adj[1]) {
					renderer.setOverrideBlockTexture(this.baseIcon);
					block.setBlockBounds(5 * f, 0 * f, 0.01F * f, 11 * f, 15 * f, 4.99F * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(4 * f, 0 * f, 0 * f, 12 * f, 16 * f, 3.99F * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

				if (adj[2]) {
					renderer.setOverrideBlockTexture(this.baseIcon);
					block.setBlockBounds(11.01F * f, 0 * f, 5 * f, 15.99F * f, 15 * f, 11 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(12.01F * f, 0 * f, 4 * f, 16 * f, 16 * f, 12 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

				if (adj[3]) {
					renderer.setOverrideBlockTexture(this.baseIcon);
					block.setBlockBounds(0.01F * f, 0 * f, 5 * f, 4.99F * f, 15 * f, 11 * f);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.topIcon);
					block.setBlockBounds(0 * f, 0 * f, 4 * f, 3.99F * f, 16 * f, 12 * f);
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
		return DCsAppleMilk.modelHedge;
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
