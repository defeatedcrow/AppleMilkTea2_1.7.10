package mods.defeatedcrow.client.renderblocks;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBreadBasket implements ISimpleBlockRenderingHandler {

	private IIcon[] boxIIcon = new IIcon[3];
	private IIcon breadIIconS;
	private IIcon breadIIconT;

	private IIcon bottleIIcon[] = new IIcon[2];

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		int meta = metadata;
		if (meta > 15)
			meta = 15;
		this.boxIIcon[0] = DCsAppleMilk.Basket.getIcon(0, meta);// bottom
		this.boxIIcon[1] = DCsAppleMilk.Basket.getIcon(1, meta);// top
		this.boxIIcon[2] = DCsAppleMilk.Basket.getIcon(2, meta);// side
		this.breadIIconS = DCsAppleMilk.Basket.getBlockTextureFromSide(3);
		this.breadIIconT = DCsAppleMilk.Basket.getBlockTextureFromSide(4);

		this.bottleIIcon[0] = DCsAppleMilk.largeBottle.getIcon(1, 0);
		this.bottleIIcon[1] = DCsAppleMilk.blockIcecream.getBlockTextureFromSide(0);

		if (modelID == this.getRenderId()) {
			if (meta < 6) {
				// box
				renderInvCuboid(renderer, block, 1.0F / 16.0F, 0.0F / 16.0F, 1.0F / 16.0F, 15.0F / 16.0F, 1.0F / 16.0F,
						15.0F / 16.0F, this.boxIIcon[0]);
				renderInvCuboid(renderer, block, 0.0F / 16.0F, 7.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F, 8.0F / 16.0F,
						16.0F / 16.0F, this.boxIIcon[1]);

				renderInvCuboid(renderer, block, 0.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 1.0F / 16.0F, 8.0F / 16.0F,
						16.0F / 16.0F, this.boxIIcon[2]);
				renderInvCuboid(renderer, block, 15.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F,
						8.0F / 16.0F, 16.0F / 16.0F, this.boxIIcon[2]);
				renderInvCuboid(renderer, block, 1.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 15.0F / 16.0F, 8.0F / 16.0F,
						1.0F / 16.0F, this.boxIIcon[2]);
				renderInvCuboid(renderer, block, 1.0F / 16.0F, 0.0F / 16.0F, 15.0F / 16.0F, 15.0F / 16.0F,
						8.0F / 16.0F, 16.0F / 16.0F, this.boxIIcon[2]);

				// contents
				if (meta > 0) {
					renderInvCuboid(renderer, block, 3.0F / 16.0F, 1.0F / 16.0F, 3.0F / 16.0F, 13.0F / 16.0F,
							3.0F / 16.0F, 6.0F / 16.0F, this.breadIIconS);
					renderInvCuboid(renderer, block, 3.0F / 16.0F, 3.0F / 16.0F, 3.0F / 16.0F, 13.0F / 16.0F,
							5.0F / 16.0F, 6.0F / 16.0F, this.breadIIconT);
				}
				if (meta > 1) {
					renderInvCuboid(renderer, block, 3.0F / 16.0F, 1.0F / 16.0F, 7.0F / 16.0F, 13.0F / 16.0F,
							3.0F / 16.0F, 10.0F / 16.0F, this.breadIIconS);
					renderInvCuboid(renderer, block, 3.0F / 16.0F, 3.0F / 16.0F, 7.0F / 16.0F, 13.0F / 16.0F,
							5.0F / 16.0F, 10.0F / 16.0F, this.breadIIconT);
				}
				if (meta > 2) {
					renderInvCuboid(renderer, block, 3.0F / 16.0F, 1.0F / 16.0F, 11.0F / 16.0F, 13.0F / 16.0F,
							3.0F / 16.0F, 14.0F / 16.0F, this.breadIIconS);
					renderInvCuboid(renderer, block, 3.0F / 16.0F, 3.0F / 16.0F, 11.0F / 16.0F, 13.0F / 16.0F,
							5.0F / 16.0F, 14.0F / 16.0F, this.breadIIconT);
				}
				if (meta > 3) {
					renderInvCuboid(renderer, block, 3.0F / 16.0F, 5.0F / 16.0F, 5.0F / 16.0F, 13.0F / 16.0F,
							7.0F / 16.0F, 8.0F / 16.0F, this.breadIIconS);
					renderInvCuboid(renderer, block, 3.0F / 16.0F, 7.0F / 16.0F, 5.0F / 16.0F, 13.0F / 16.0F,
							9.0F / 16.0F, 8.0F / 16.0F, this.breadIIconT);
				}
				if (meta > 4) {
					renderInvCuboid(renderer, block, 3.0F / 16.0F, 5.0F / 16.0F, 9.0F / 16.0F, 13.0F / 16.0F,
							7.0F / 16.0F, 12.0F / 16.0F, this.breadIIconS);
					renderInvCuboid(renderer, block, 3.0F / 16.0F, 7.0F / 16.0F, 9.0F / 16.0F, 13.0F / 16.0F,
							9.0F / 16.0F, 12.0F / 16.0F, this.breadIIconT);
				}
			} else {

				// box
				renderInvCuboid(renderer, block, 1.0F / 16.0F, 0.0F / 16.0F, 1.0F / 16.0F, 15.0F / 16.0F, 2.0F / 16.0F,
						15.0F / 16.0F, this.boxIIcon[0]);
				renderInvCuboid(renderer, block, 0.0F / 16.0F, 11.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F,
						12.0F / 16.0F, 16.0F / 16.0F, this.boxIIcon[1]);

				renderInvCuboid(renderer, block, 0.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 1.0F / 16.0F, 12.0F / 16.0F,
						16.0F / 16.0F, this.boxIIcon[2]);
				renderInvCuboid(renderer, block, 15.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F,
						12.0F / 16.0F, 16.0F / 16.0F, this.boxIIcon[2]);
				renderInvCuboid(renderer, block, 1.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 15.0F / 16.0F,
						12.0F / 16.0F, 1.0F / 16.0F, this.boxIIcon[2]);
				renderInvCuboid(renderer, block, 1.0F / 16.0F, 0.0F / 16.0F, 15.0F / 16.0F, 15.0F / 16.0F,
						12.0F / 16.0F, 16.0F / 16.0F, this.boxIIcon[2]);

				// contents
				if (meta > 5) {
					renderInvCuboid(renderer, block, 1.0F / 16.0F, 2.0F / 16.0F, 1.0F / 16.0F, 5.0F / 16.0F,
							8.0F / 16.0F, 5.0F / 16.0F, this.bottleIIcon[0]);
					renderInvCuboid(renderer, block, 2.0F / 16.0F, 8.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F,
							10.0F / 16.0F, 4.0F / 16.0F, this.bottleIIcon[0]);
					renderInvCuboid(renderer, block, 2.0F / 16.0F, 10.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F,
							12.0F / 16.0F, 4.0F / 16.0F, this.bottleIIcon[1]);
				}
				if (meta > 6) {
					renderInvCuboid(renderer, block, 1.0F / 16.0F, 2.0F / 16.0F, 6.0F / 16.0F, 5.0F / 16.0F,
							8.0F / 16.0F, 10.0F / 16.0F, this.bottleIIcon[0]);
					renderInvCuboid(renderer, block, 2.0F / 16.0F, 8.0F / 16.0F, 7.0F / 16.0F, 4.0F / 16.0F,
							10.0F / 16.0F, 9.0F / 16.0F, this.bottleIIcon[0]);
					renderInvCuboid(renderer, block, 2.0F / 16.0F, 10.0F / 16.0F, 7.0F / 16.0F, 4.0F / 16.0F,
							12.0F / 16.0F, 9.0F / 16.0F, this.bottleIIcon[1]);
				}
				if (meta > 7) {
					renderInvCuboid(renderer, block, 1.0F / 16.0F, 2.0F / 16.0F, 11.0F / 16.0F, 5.0F / 16.0F,
							8.0F / 16.0F, 15.0F / 16.0F, this.bottleIIcon[0]);
					renderInvCuboid(renderer, block, 2.0F / 16.0F, 8.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F,
							10.0F / 16.0F, 14.0F / 16.0F, this.bottleIIcon[0]);
					renderInvCuboid(renderer, block, 2.0F / 16.0F, 10.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F,
							12.0F / 16.0F, 14.0F / 16.0F, this.bottleIIcon[1]);
				}
				if (meta > 8) {
					renderInvCuboid(renderer, block, 1.0F / 16.0F, 2.0F / 16.0F, 1.0F / 16.0F, 5.0F / 16.0F,
							8.0F / 16.0F, 5.0F / 16.0F, this.bottleIIcon[0]);
					renderInvCuboid(renderer, block, 7.0F / 16.0F, 8.0F / 16.0F, 2.0F / 16.0F, 9.0F / 16.0F,
							10.0F / 16.0F, 4.0F / 16.0F, this.bottleIIcon[0]);
					renderInvCuboid(renderer, block, 7.0F / 16.0F, 10.0F / 16.0F, 2.0F / 16.0F, 9.0F / 16.0F,
							12.0F / 16.0F, 4.0F / 16.0F, this.bottleIIcon[1]);
				}
				if (meta > 9) {
					renderInvCuboid(renderer, block, 6.0F / 16.0F, 2.0F / 16.0F, 6.0F / 16.0F, 10.0F / 16.0F,
							8.0F / 16.0F, 10.0F / 16.0F, this.bottleIIcon[0]);
					renderInvCuboid(renderer, block, 7.0F / 16.0F, 8.0F / 16.0F, 7.0F / 16.0F, 9.0F / 16.0F,
							10.0F / 16.0F, 9.0F / 16.0F, this.bottleIIcon[0]);
					renderInvCuboid(renderer, block, 7.0F / 16.0F, 10.0F / 16.0F, 7.0F / 16.0F, 9.0F / 16.0F,
							12.0F / 16.0F, 9.0F / 16.0F, this.bottleIIcon[1]);
				}
				if (meta > 10) {
					renderInvCuboid(renderer, block, 1.0F / 16.0F, 2.0F / 16.0F, 11.0F / 16.0F, 5.0F / 16.0F,
							8.0F / 16.0F, 15.0F / 16.0F, this.bottleIIcon[0]);
					renderInvCuboid(renderer, block, 7.0F / 16.0F, 8.0F / 16.0F, 12.0F / 16.0F, 9.0F / 16.0F,
							10.0F / 16.0F, 14.0F / 16.0F, this.bottleIIcon[0]);
					renderInvCuboid(renderer, block, 7.0F / 16.0F, 10.0F / 16.0F, 12.0F / 16.0F, 9.0F / 16.0F,
							12.0F / 16.0F, 14.0F / 16.0F, this.bottleIIcon[1]);
				}
				if (meta > 11) {
					renderInvCuboid(renderer, block, 1.0F / 16.0F, 2.0F / 16.0F, 1.0F / 16.0F, 5.0F / 16.0F,
							8.0F / 16.0F, 5.0F / 16.0F, this.bottleIIcon[0]);
					renderInvCuboid(renderer, block, 12.0F / 16.0F, 8.0F / 16.0F, 2.0F / 16.0F, 14.0F / 16.0F,
							10.0F / 16.0F, 4.0F / 16.0F, this.bottleIIcon[0]);
					renderInvCuboid(renderer, block, 12.0F / 16.0F, 10.0F / 16.0F, 2.0F / 16.0F, 14.0F / 16.0F,
							12.0F / 16.0F, 4.0F / 16.0F, this.bottleIIcon[1]);
				}
				if (meta > 12) {
					renderInvCuboid(renderer, block, 1.0F / 16.0F, 2.0F / 16.0F, 6.0F / 16.0F, 5.0F / 16.0F,
							8.0F / 16.0F, 10.0F / 16.0F, this.bottleIIcon[0]);
					renderInvCuboid(renderer, block, 12.0F / 16.0F, 8.0F / 16.0F, 7.0F / 16.0F, 14.0F / 16.0F,
							10.0F / 16.0F, 9.0F / 16.0F, this.bottleIIcon[0]);
					renderInvCuboid(renderer, block, 12.0F / 16.0F, 10.0F / 16.0F, 7.0F / 16.0F, 14.0F / 16.0F,
							12.0F / 16.0F, 9.0F / 16.0F, this.bottleIIcon[1]);
				}
				if (meta > 13) {
					renderInvCuboid(renderer, block, 1.0F / 16.0F, 2.0F / 16.0F, 11.0F / 16.0F, 5.0F / 16.0F,
							8.0F / 16.0F, 15.0F / 16.0F, this.bottleIIcon[0]);
					renderInvCuboid(renderer, block, 12.0F / 16.0F, 8.0F / 16.0F, 12.0F / 16.0F, 14.0F / 16.0F,
							10.0F / 16.0F, 14.0F / 16.0F, this.bottleIIcon[0]);
					renderInvCuboid(renderer, block, 12.0F / 16.0F, 10.0F / 16.0F, 12.0F / 16.0F, 14.0F / 16.0F,
							12.0F / 16.0F, 14.0F / 16.0F, this.bottleIIcon[1]);
				}
			}

		}

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		int meta = world.getBlockMetadata(x, y, z);
		if (meta > 15)
			meta = 15;
		this.boxIIcon[0] = DCsAppleMilk.Basket.getIcon(0, meta);// bottom
		this.boxIIcon[1] = DCsAppleMilk.Basket.getIcon(1, meta);// top
		this.boxIIcon[2] = DCsAppleMilk.Basket.getIcon(2, meta);// side
		this.breadIIconS = DCsAppleMilk.Basket.getBlockTextureFromSide(3);
		this.breadIIconT = DCsAppleMilk.Basket.getBlockTextureFromSide(4);

		this.bottleIIcon[0] = DCsAppleMilk.largeBottle.getIcon(1, 0);
		this.bottleIIcon[1] = DCsAppleMilk.blockIcecream.getBlockTextureFromSide(0);

		if (modelId == this.getRenderId()) {
			if (meta < 6) {
				// renderer.setOverrideBlockTexture(this.boxIIcon[0]);
				// block.setBlockBounds(1.0F/16.0F, 0.0F/16.0F, 1.0F/16.0F, 15.0F/16.0F, 1.0F/16.0F, 15.0F/16.0F);
				// renderer.setRenderBoundsFromBlock(block);
				// renderer.renderStandardBlock(block, x, y, z);
				//
				// renderer.setOverrideBlockTexture(this.boxIIcon[1]);
				// block.setBlockBounds(0.0F/16.0F, 7.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F, 8.0F/16.0F, 16.0F/16.0F);
				// renderer.setRenderBoundsFromBlock(block);
				// renderer.renderStandardBlock(block, x, y, z);
				//
				// renderer.setOverrideBlockTexture(this.boxIIcon[2]);
				// block.setBlockBounds(0.0F/16.0F, 0.0F/16.0F, 0.0F/16.0F, 1.0F/16.0F, 8.0F/16.0F, 16.0F/16.0F);
				// renderer.setRenderBoundsFromBlock(block);
				// renderer.renderStandardBlock(block, x, y, z);
				// renderer.setOverrideBlockTexture(this.boxIIcon[2]);
				// block.setBlockBounds(15.0F/16.0F, 0.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F, 8.0F/16.0F, 16.0F/16.0F);
				// renderer.setRenderBoundsFromBlock(block);
				// renderer.renderStandardBlock(block, x, y, z);
				// renderer.setOverrideBlockTexture(this.boxIIcon[2]);
				// block.setBlockBounds(1.0F/16.0F, 0.0F/16.0F, 0.0F/16.0F, 15.0F/16.0F, 8.0F/16.0F, 1.0F/16.0F);
				// renderer.setRenderBoundsFromBlock(block);
				// renderer.renderStandardBlock(block, x, y, z);
				// renderer.setOverrideBlockTexture(this.boxIIcon[2]);
				// block.setBlockBounds(1.0F/16.0F, 0.0F/16.0F, 15.0F/16.0F, 15.0F/16.0F, 8.0F/16.0F, 16.0F/16.0F);
				// renderer.setRenderBoundsFromBlock(block);
				// renderer.renderStandardBlock(block, x, y, z);

				// if (DCsConfig.noUseCupDirection)
				// {
				// //contents
				// if (meta > 0)
				// {
				// renderer.setOverrideBlockTexture(this.breadIIconS);
				// block.setBlockBounds(3.0F/16.0F, 1.0F/16.0F, 3.0F/16.0F, 13.0F/16.0F, 3.0F/16.0F, 6.0F/16.0F);
				// renderer.setRenderBoundsFromBlock(block);
				// renderer.renderStandardBlock(block, x, y, z);
				// renderer.setOverrideBlockTexture(this.breadIIconT);
				// block.setBlockBounds(3.0F/16.0F, 3.0F/16.0F, 3.0F/16.0F, 13.0F/16.0F, 5.0F/16.0F, 6.0F/16.0F);
				// renderer.setRenderBoundsFromBlock(block);
				// renderer.renderStandardBlock(block, x, y, z);
				// }
				// if (meta > 1)
				// {
				// renderer.setOverrideBlockTexture(this.breadIIconS);
				// block.setBlockBounds(3.0F/16.0F, 1.0F/16.0F, 7.0F/16.0F, 13.0F/16.0F, 3.0F/16.0F, 10.0F/16.0F);
				// renderer.setRenderBoundsFromBlock(block);
				// renderer.renderStandardBlock(block, x, y, z);
				// renderer.setOverrideBlockTexture(this.breadIIconT);
				// block.setBlockBounds(3.0F/16.0F, 3.0F/16.0F, 7.0F/16.0F, 13.0F/16.0F, 5.0F/16.0F, 10.0F/16.0F);
				// renderer.setRenderBoundsFromBlock(block);
				// renderer.renderStandardBlock(block, x, y, z);
				// }
				// if (meta > 2)
				// {
				// renderer.setOverrideBlockTexture(this.breadIIconS);
				// block.setBlockBounds(3.0F/16.0F, 1.0F/16.0F, 11.0F/16.0F, 13.0F/16.0F, 3.0F/16.0F, 14.0F/16.0F);
				// renderer.setRenderBoundsFromBlock(block);
				// renderer.renderStandardBlock(block, x, y, z);
				// renderer.setOverrideBlockTexture(this.breadIIconT);
				// block.setBlockBounds(3.0F/16.0F, 3.0F/16.0F, 11.0F/16.0F, 13.0F/16.0F, 5.0F/16.0F, 14.0F/16.0F);
				// renderer.setRenderBoundsFromBlock(block);
				// renderer.renderStandardBlock(block, x, y, z);
				// }
				// if (meta > 3)
				// {
				// renderer.setOverrideBlockTexture(this.breadIIconS);
				// block.setBlockBounds(3.0F/16.0F, 5.0F/16.0F, 5.0F/16.0F, 13.0F/16.0F, 7.0F/16.0F, 8.0F/16.0F);
				// renderer.setRenderBoundsFromBlock(block);
				// renderer.renderStandardBlock(block, x, y, z);
				// renderer.setOverrideBlockTexture(this.breadIIconT);
				// block.setBlockBounds(3.0F/16.0F, 7.0F/16.0F, 5.0F/16.0F, 13.0F/16.0F, 9.0F/16.0F, 8.0F/16.0F);
				// renderer.setRenderBoundsFromBlock(block);
				// renderer.renderStandardBlock(block, x, y, z);
				// }
				// if (meta > 4)
				// {
				// renderer.setOverrideBlockTexture(this.breadIIconS);
				// block.setBlockBounds(3.0F/16.0F, 5.0F/16.0F, 9.0F/16.0F, 13.0F/16.0F, 7.0F/16.0F, 12.0F/16.0F);
				// renderer.setRenderBoundsFromBlock(block);
				// renderer.renderStandardBlock(block, x, y, z);
				// renderer.setOverrideBlockTexture(this.breadIIconT);
				// block.setBlockBounds(3.0F/16.0F, 7.0F/16.0F, 9.0F/16.0F, 13.0F/16.0F, 9.0F/16.0F, 12.0F/16.0F);
				// renderer.setRenderBoundsFromBlock(block);
				// renderer.renderStandardBlock(block, x, y, z);
				// }
				// }
			} else {
				renderer.setOverrideBlockTexture(this.boxIIcon[0]);
				block.setBlockBounds(1.0F / 16.0F, 0.0F / 16.0F, 1.0F / 16.0F, 15.0F / 16.0F, 4.0F / 16.0F,
						15.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.boxIIcon[1]);
				block.setBlockBounds(0.0F / 16.0F, 11.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F, 12.0F / 16.0F,
						16.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.boxIIcon[2]);
				block.setBlockBounds(0.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 1.0F / 16.0F, 12.0F / 16.0F,
						16.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.boxIIcon[2]);
				block.setBlockBounds(15.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F, 12.0F / 16.0F,
						16.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.boxIIcon[2]);
				block.setBlockBounds(1.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 15.0F / 16.0F, 12.0F / 16.0F,
						1.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.boxIIcon[2]);
				block.setBlockBounds(1.0F / 16.0F, 0.0F / 16.0F, 15.0F / 16.0F, 15.0F / 16.0F, 12.0F / 16.0F,
						16.0F / 16.0F);
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

		return DCsAppleMilk.modelBasket;
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
