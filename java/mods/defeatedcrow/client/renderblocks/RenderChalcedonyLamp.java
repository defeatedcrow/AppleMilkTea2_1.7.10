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
public class RenderChalcedonyLamp implements ISimpleBlockRenderingHandler {

	private IIcon boxIIcon;// side_1 chalcedony
	private IIcon glassIIcon;// glass
	private IIcon IIcon0;// side_0 white inner , force rods, burst plate and lantern side
	private IIcon IIcon2;// side_2 force rods2, burst glow and lantern head
	private IIcon IIcon3;// side_3 burst panel, lantern rod
	private IIcon IIcon4;// teppann

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		int meta = metadata;
		if (meta > 11)
			meta = 11;
		this.boxIIcon = DCsAppleMilk.cLamp.getIcon(1, meta);
		this.glassIIcon = Blocks.glass.getBlockTextureFromSide(0);
		this.IIcon0 = DCsAppleMilk.cLamp.getIcon(0, meta);
		this.IIcon2 = DCsAppleMilk.cLamp.getIcon(2, meta);
		this.IIcon3 = DCsAppleMilk.cLamp.getIcon(3, meta);
		this.IIcon4 = DCsAppleMilk.teppanII.getBlockTextureFromSide(2);

		if (modelID == this.getRenderId()) {
			if (meta < 4) // for normal glass lamp
			{
				renderInvCuboidAlpha(renderer, block, 0.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F,
						16.0F / 16.0F, 16.0F / 16.0F, this.boxIIcon);
			} else if (meta > 3 && meta < 8) // for inner glass lamp
			{
				renderInvCuboidAlpha(renderer, block, 3.0F / 16.0F, 3.0F / 16.0F, 3.0F / 16.0F, 13.0F / 16.0F,
						13.0F / 16.0F, 13.0F / 16.0F, this.boxIIcon);
				renderInvCuboid(renderer, block, 0.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F,
						16.0F / 16.0F, 16.0F / 16.0F, this.glassIIcon);
			} else if (meta == 8) {

				// wing1
				renderInvCuboid(renderer, block, 7.0F / 16.0F, 9.0F / 16.0F, 1.0F / 16.0F, 9.0F / 16.0F, 15.0F / 16.0F,
						2.0F / 16.0F, this.IIcon2);
				renderInvCuboid(renderer, block, 7.0F / 16.0F, 9.0F / 16.0F, 14.0F / 16.0F, 9.0F / 16.0F,
						15.0F / 16.0F, 15.0F / 16.0F, this.IIcon2);
				renderInvCuboid(renderer, block, 1.0F / 16.0F, 9.0F / 16.0F, 7.0F / 16.0F, 2.0F / 16.0F, 15.0F / 16.0F,
						9.0F / 16.0F, this.IIcon2);
				renderInvCuboid(renderer, block, 14.0F / 16.0F, 9.0F / 16.0F, 7.0F / 16.0F, 15.0F / 16.0F,
						15.0F / 16.0F, 9.0F / 16.0F, this.IIcon2);

				renderInvCuboid(renderer, block, 6.0F / 16.0F, 6.0F / 16.0F, 1.0F / 16.0F, 10.0F / 16.0F, 8.0F / 16.0F,
						2.0F / 16.0F, this.IIcon2);
				renderInvCuboid(renderer, block, 6.0F / 16.0F, 6.0F / 16.0F, 14.0F / 16.0F, 10.0F / 16.0F,
						8.0F / 16.0F, 15.0F / 16.0F, this.IIcon2);
				renderInvCuboid(renderer, block, 1.0F / 16.0F, 6.0F / 16.0F, 6.0F / 16.0F, 2.0F / 16.0F, 8.0F / 16.0F,
						10.0F / 16.0F, this.IIcon2);
				renderInvCuboid(renderer, block, 14.0F / 16.0F, 6.0F / 16.0F, 6.0F / 16.0F, 15.0F / 16.0F,
						8.0F / 16.0F, 10.0F / 16.0F, this.IIcon2);

				renderInvCuboid(renderer, block, 7.0F / 16.0F, 1.0F / 16.0F, 1.0F / 16.0F, 9.0F / 16.0F, 3.0F / 16.0F,
						2.0F / 16.0F, this.IIcon2);
				renderInvCuboid(renderer, block, 7.0F / 16.0F, 1.0F / 16.0F, 14.0F / 16.0F, 9.0F / 16.0F, 3.0F / 16.0F,
						15.0F / 16.0F, this.IIcon2);
				renderInvCuboid(renderer, block, 1.0F / 16.0F, 1.0F / 16.0F, 7.0F / 16.0F, 2.0F / 16.0F, 3.0F / 16.0F,
						9.0F / 16.0F, this.IIcon2);
				renderInvCuboid(renderer, block, 14.0F / 16.0F, 1.0F / 16.0F, 7.0F / 16.0F, 15.0F / 16.0F,
						3.0F / 16.0F, 9.0F / 16.0F, this.IIcon2);
				// blade
				renderInvCuboidAlpha(renderer, block, 7.0F / 16.0F, 5.0F / 16.0F, 1.0F / 16.0F, 9.0F / 16.0F,
						9.0F / 16.0F, 2.0F / 16.0F, this.IIcon3);
				renderInvCuboidAlpha(renderer, block, 7.0F / 16.0F, 5.0F / 16.0F, 14.0F / 16.0F, 9.0F / 16.0F,
						9.0F / 16.0F, 15.0F / 16.0F, this.IIcon3);
				renderInvCuboidAlpha(renderer, block, 1.0F / 16.0F, 5.0F / 16.0F, 7.0F / 16.0F, 2.0F / 16.0F,
						9.0F / 16.0F, 9.0F / 16.0F, this.IIcon3);
				renderInvCuboidAlpha(renderer, block, 14.0F / 16.0F, 5.0F / 16.0F, 7.0F / 16.0F, 15.0F / 16.0F,
						9.0F / 16.0F, 9.0F / 16.0F, this.IIcon3);
				// plate
				renderInvCuboid(renderer, block, 5.0F / 16.0F, 3.0F / 16.0F, 1.0F / 16.0F, 11.0F / 16.0F, 5.0F / 16.0F,
						2.0F / 16.0F, this.IIcon0);
				renderInvCuboid(renderer, block, 5.0F / 16.0F, 3.0F / 16.0F, 14.0F / 16.0F, 11.0F / 16.0F,
						5.0F / 16.0F, 15.0F / 16.0F, this.IIcon0);
				renderInvCuboid(renderer, block, 1.0F / 16.0F, 3.0F / 16.0F, 5.0F / 16.0F, 2.0F / 16.0F, 5.0F / 16.0F,
						11.0F / 16.0F, this.IIcon0);
				renderInvCuboid(renderer, block, 14.0F / 16.0F, 3.0F / 16.0F, 5.0F / 16.0F, 15.0F / 16.0F,
						5.0F / 16.0F, 11.0F / 16.0F, this.IIcon0);

				renderInvCuboid(renderer, block, 7.0F / 16.0F, 3.0F / 16.0F, 0.0F / 16.0F, 9.0F / 16.0F, 5.0F / 16.0F,
						2.0F / 16.0F, this.IIcon0);
				renderInvCuboid(renderer, block, 7.0F / 16.0F, 3.0F / 16.0F, 14.0F / 16.0F, 9.0F / 16.0F, 5.0F / 16.0F,
						16.0F / 16.0F, this.IIcon0);
				renderInvCuboid(renderer, block, 0.0F / 16.0F, 3.0F / 16.0F, 7.0F / 16.0F, 2.0F / 16.0F, 5.0F / 16.0F,
						9.0F / 16.0F, this.IIcon0);
				renderInvCuboid(renderer, block, 14.0F / 16.0F, 3.0F / 16.0F, 7.0F / 16.0F, 16.0F / 16.0F,
						5.0F / 16.0F, 9.0F / 16.0F, this.IIcon0);
				// plate2
				renderInvCuboid(renderer, block, 4.0F / 16.0F, 3.0F / 16.0F, 1.0F / 16.0F, 5.0F / 16.0F, 5.0F / 16.0F,
						2.0F / 16.0F, this.IIcon2);
				renderInvCuboid(renderer, block, 4.0F / 16.0F, 3.0F / 16.0F, 14.0F / 16.0F, 5.0F / 16.0F, 5.0F / 16.0F,
						15.0F / 16.0F, this.IIcon2);
				renderInvCuboid(renderer, block, 1.0F / 16.0F, 3.0F / 16.0F, 4.0F / 16.0F, 2.0F / 16.0F, 5.0F / 16.0F,
						5.0F / 16.0F, this.IIcon2);
				renderInvCuboid(renderer, block, 14.0F / 16.0F, 3.0F / 16.0F, 4.0F / 16.0F, 15.0F / 16.0F,
						5.0F / 16.0F, 5.0F / 16.0F, this.IIcon2);
				// plate3
				renderInvCuboid(renderer, block, 11.0F / 16.0F, 3.0F / 16.0F, 1.0F / 16.0F, 12.0F / 16.0F,
						5.0F / 16.0F, 2.0F / 16.0F, this.IIcon2);
				renderInvCuboid(renderer, block, 11.0F / 16.0F, 3.0F / 16.0F, 14.0F / 16.0F, 12.0F / 16.0F,
						5.0F / 16.0F, 15.0F / 16.0F, this.IIcon2);
				renderInvCuboid(renderer, block, 1.0F / 16.0F, 3.0F / 16.0F, 11.0F / 16.0F, 2.0F / 16.0F, 5.0F / 16.0F,
						12.0F / 16.0F, this.IIcon2);
				renderInvCuboid(renderer, block, 14.0F / 16.0F, 3.0F / 16.0F, 11.0F / 16.0F, 15.0F / 16.0F,
						5.0F / 16.0F, 12.0F / 16.0F, this.IIcon2);

			} else if (meta == 9) {
				// force
				renderInvCuboidAlpha(renderer, block, 4.0F / 16.0F, 7.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F,
						15.0F / 16.0F, 12.0F / 16.0F, this.boxIIcon);

				// rod
				renderInvCuboid(renderer, block, 2.0F / 16.0F, 2.0F / 16.0F, 2.0F / 16.0F, 3.0F / 16.0F, 12.0F / 16.0F,
						3.0F / 16.0F, this.IIcon2);
				renderInvCuboid(renderer, block, 2.0F / 16.0F, 2.0F / 16.0F, 13.0F / 16.0F, 3.0F / 16.0F,
						12.0F / 16.0F, 14.0F / 16.0F, this.IIcon2);
				renderInvCuboid(renderer, block, 13.0F / 16.0F, 2.0F / 16.0F, 2.0F / 16.0F, 14.0F / 16.0F,
						12.0F / 16.0F, 3.0F / 16.0F, this.IIcon2);
				renderInvCuboid(renderer, block, 13.0F / 16.0F, 2.0F / 16.0F, 13.0F / 16.0F, 14.0F / 16.0F,
						12.0F / 16.0F, 14.0F / 16.0F, this.IIcon2);

				// rod 2
				renderInvCuboid(renderer, block, 3.0F / 16.0F, 11.0F / 16.0F, 3.0F / 16.0F, 4.0F / 16.0F,
						12.0F / 16.0F, 6.0F / 16.0F, this.IIcon0);
				renderInvCuboid(renderer, block, 3.0F / 16.0F, 11.0F / 16.0F, 10.0F / 16.0F, 4.0F / 16.0F,
						12.0F / 16.0F, 13.0F / 16.0F, this.IIcon0);
				renderInvCuboid(renderer, block, 12.0F / 16.0F, 11.0F / 16.0F, 3.0F / 16.0F, 13.0F / 16.0F,
						12.0F / 16.0F, 6.0F / 16.0F, this.IIcon0);
				renderInvCuboid(renderer, block, 12.0F / 16.0F, 11.0F / 16.0F, 10.0F / 16.0F, 13.0F / 16.0F,
						12.0F / 16.0F, 13.0F / 16.0F, this.IIcon0);
				renderInvCuboid(renderer, block, 4.0F / 16.0F, 11.0F / 16.0F, 3.0F / 16.0F, 6.0F / 16.0F,
						12.0F / 16.0F, 4.0F / 16.0F, this.IIcon0);
				renderInvCuboid(renderer, block, 4.0F / 16.0F, 11.0F / 16.0F, 12.0F / 16.0F, 6.0F / 16.0F,
						12.0F / 16.0F, 13.0F / 16.0F, this.IIcon0);
				renderInvCuboid(renderer, block, 10.0F / 16.0F, 11.0F / 16.0F, 3.0F / 16.0F, 12.0F / 16.0F,
						12.0F / 16.0F, 4.0F / 16.0F, this.IIcon0);
				renderInvCuboid(renderer, block, 10.0F / 16.0F, 11.0F / 16.0F, 12.0F / 16.0F, 12.0F / 16.0F,
						12.0F / 16.0F, 13.0F / 16.0F, this.IIcon0);
				renderInvCuboid(renderer, block, 3.0F / 16.0F, 12.0F / 16.0F, 3.0F / 16.0F, 4.0F / 16.0F,
						14.0F / 16.0F, 4.0F / 16.0F, this.IIcon0);
				renderInvCuboid(renderer, block, 3.0F / 16.0F, 12.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F,
						14.0F / 16.0F, 13.0F / 16.0F, this.IIcon0);
				renderInvCuboid(renderer, block, 12.0F / 16.0F, 12.0F / 16.0F, 3.0F / 16.0F, 13.0F / 16.0F,
						14.0F / 16.0F, 4.0F / 16.0F, this.IIcon0);
				renderInvCuboid(renderer, block, 12.0F / 16.0F, 12.0F / 16.0F, 12.0F / 16.0F, 13.0F / 16.0F,
						14.0F / 16.0F, 13.0F / 16.0F, this.IIcon0);
				// base
				renderInvCuboid(renderer, block, 7.0F / 16.0F, 1.0F / 16.0F, 7.0F / 16.0F, 9.0F / 16.0F, 7.0F / 16.0F,
						9.0F / 16.0F, this.IIcon0);
				renderInvCuboid(renderer, block, 4.0F / 16.0F, 0.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 1.0F / 16.0F,
						12.0F / 16.0F, this.IIcon0);
			} else if (meta == 10) {
				// たて
				renderInvCuboidAlpha(renderer, block, 2.0F / 16.0F, 2.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F,
						14.0F / 16.0F, 4.0F / 16.0F, this.boxIIcon);
				renderInvCuboidAlpha(renderer, block, 2.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F,
						14.0F / 16.0F, 14.0F / 16.0F, this.boxIIcon);
				renderInvCuboidAlpha(renderer, block, 12.0F / 16.0F, 2.0F / 16.0F, 2.0F / 16.0F, 14.0F / 16.0F,
						14.0F / 16.0F, 4.0F / 16.0F, this.boxIIcon);
				renderInvCuboidAlpha(renderer, block, 12.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F, 14.0F / 16.0F,
						14.0F / 16.0F, 14.0F / 16.0F, this.boxIIcon);

				// よこ
				renderInvCuboidAlpha(renderer, block, 2.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F, 4.0F / 16.0F,
						4.0F / 16.0F, 12.0F / 16.0F, this.boxIIcon);
				renderInvCuboidAlpha(renderer, block, 12.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F, 14.0F / 16.0F,
						4.0F / 16.0F, 12.0F / 16.0F, this.boxIIcon);
				renderInvCuboidAlpha(renderer, block, 4.0F / 16.0F, 2.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F,
						4.0F / 16.0F, 4.0F / 16.0F, this.boxIIcon);
				renderInvCuboidAlpha(renderer, block, 4.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F, 12.0F / 16.0F,
						4.0F / 16.0F, 14.0F / 16.0F, this.boxIIcon);

				renderInvCuboidAlpha(renderer, block, 2.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F, 4.0F / 16.0F,
						14.0F / 16.0F, 12.0F / 16.0F, this.boxIIcon);
				renderInvCuboidAlpha(renderer, block, 12.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F, 14.0F / 16.0F,
						14.0F / 16.0F, 12.0F / 16.0F, this.boxIIcon);
				renderInvCuboidAlpha(renderer, block, 4.0F / 16.0F, 12.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F,
						14.0F / 16.0F, 4.0F / 16.0F, this.boxIIcon);
				renderInvCuboidAlpha(renderer, block, 4.0F / 16.0F, 12.0F / 16.0F, 12.0F / 16.0F, 12.0F / 16.0F,
						14.0F / 16.0F, 14.0F / 16.0F, this.boxIIcon);
			} else if (meta == 11) {
				// 中身
				renderInvCuboidAlpha(renderer, block, 4.0F / 16.0F, 3.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F,
						13.0F / 16.0F, 12.0F / 16.0F, this.IIcon0);

				// 頂点部分
				renderInvCuboid(renderer, block, 5.0F / 16.0F, 14.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F,
						16.0F / 16.0F, 11.0F / 16.0F, this.IIcon4);
				renderInvCuboid(renderer, block, 5.0F / 16.0F, 0.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 2.0F / 16.0F,
						11.0F / 16.0F, this.IIcon4);

				// 枠
				renderInvCuboid(renderer, block, 3.0F / 16.0F, 2.0F / 16.0F, 3.0F / 16.0F, 4.0F / 16.0F, 14.0F / 16.0F,
						4.0F / 16.0F, this.IIcon4);
				renderInvCuboid(renderer, block, 3.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F,
						14.0F / 16.0F, 13.0F / 16.0F, this.IIcon4);
				renderInvCuboid(renderer, block, 12.0F / 16.0F, 2.0F / 16.0F, 3.0F / 16.0F, 13.0F / 16.0F,
						14.0F / 16.0F, 4.0F / 16.0F, this.IIcon4);
				renderInvCuboid(renderer, block, 12.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F, 13.0F / 16.0F,
						14.0F / 16.0F, 13.0F / 16.0F, this.IIcon4);

				renderInvCuboid(renderer, block, 3.0F / 16.0F, 2.0F / 16.0F, 3.0F / 16.0F, 4.0F / 16.0F, 3.0F / 16.0F,
						13.0F / 16.0F, this.boxIIcon);
				renderInvCuboid(renderer, block, 12.0F / 16.0F, 2.0F / 16.0F, 3.0F / 16.0F, 13.0F / 16.0F,
						3.0F / 16.0F, 13.0F / 16.0F, this.IIcon4);
				renderInvCuboid(renderer, block, 3.0F / 16.0F, 2.0F / 16.0F, 3.0F / 16.0F, 13.0F / 16.0F, 3.0F / 16.0F,
						4.0F / 16.0F, this.IIcon4);
				renderInvCuboid(renderer, block, 3.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F, 13.0F / 16.0F,
						3.0F / 16.0F, 13.0F / 16.0F, this.IIcon4);

				renderInvCuboid(renderer, block, 3.0F / 16.0F, 13.0F / 16.0F, 3.0F / 16.0F, 4.0F / 16.0F,
						14.0F / 16.0F, 13.0F / 16.0F, this.IIcon4);
				renderInvCuboid(renderer, block, 12.0F / 16.0F, 13.0F / 16.0F, 3.0F / 16.0F, 13.0F / 16.0F,
						14.0F / 16.0F, 13.0F / 16.0F, this.IIcon4);
				renderInvCuboid(renderer, block, 3.0F / 16.0F, 13.0F / 16.0F, 3.0F / 16.0F, 13.0F / 16.0F,
						14.0F / 16.0F, 4.0F / 16.0F, this.IIcon4);
				renderInvCuboid(renderer, block, 3.0F / 16.0F, 13.0F / 16.0F, 12.0F / 16.0F, 13.0F / 16.0F,
						14.0F / 16.0F, 13.0F / 16.0F, this.IIcon4);
			}
		}

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		int meta = world.getBlockMetadata(x, y, z);
		if (meta > 11)
			meta = 11;
		this.boxIIcon = DCsAppleMilk.cLamp.getIcon(1, meta);
		this.glassIIcon = Blocks.glass.getBlockTextureFromSide(0);
		this.IIcon0 = DCsAppleMilk.cLamp.getIcon(0, meta);
		this.IIcon2 = DCsAppleMilk.cLamp.getIcon(2, meta);
		this.IIcon3 = DCsAppleMilk.cLamp.getIcon(3, meta);
		this.IIcon4 = DCsAppleMilk.teppanII.getBlockTextureFromSide(2);

		if (modelId == this.getRenderId()) {
			if (meta < 4) {
				renderer.setOverrideBlockTexture(this.boxIIcon);
				block.setBlockBounds(0.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F, 16.0F / 16.0F,
						16.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, 2.0F, 2.0F, 2.0F);

			} else if (meta > 3 && meta < 8) {
				renderer.setOverrideBlockTexture(this.glassIIcon);
				block.setBlockBounds(0.0F / 16.0F, 0.0F / 16.0F, 0.0F / 16.0F, 16.0F / 16.0F, 16.0F / 16.0F,
						16.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.boxIIcon);
				block.setBlockBounds(3.0F / 16.0F, 3.0F / 16.0F, 3.0F / 16.0F, 13.0F / 16.0F, 13.0F / 16.0F,
						13.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, 2.0F, 2.0F, 2.0F);
			} else if (meta == 8) {
				// is using x32Texture?
				if (DCsConfig.setAltTexturePass == 1) {
					// wing1
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(7.0F / 16.0F, 10.0F / 16.0F, 1.0F / 16.0F, 9.0F / 16.0F, 15.0F / 16.0F,
							2.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(7.0F / 16.0F, 10.0F / 16.0F, 14.0F / 16.0F, 9.0F / 16.0F, 15.0F / 16.0F,
							15.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(1.0F / 16.0F, 10.0F / 16.0F, 7.0F / 16.0F, 2.0F / 16.0F, 15.0F / 16.0F,
							9.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(14.0F / 16.0F, 10.0F / 16.0F, 7.0F / 16.0F, 15.0F / 16.0F, 15.0F / 16.0F,
							9.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					// wing2
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(6.0F / 16.0F, 6.0F / 16.0F, 1.0F / 16.0F, 10.0F / 16.0F, 10.0F / 16.0F,
							2.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(6.0F / 16.0F, 6.0F / 16.0F, 14.0F / 16.0F, 10.0F / 16.0F, 10.0F / 16.0F,
							15.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(1.0F / 16.0F, 6.0F / 16.0F, 6.0F / 16.0F, 2.0F / 16.0F, 10.0F / 16.0F,
							10.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(14.0F / 16.0F, 6.0F / 16.0F, 6.0F / 16.0F, 15.0F / 16.0F, 10.0F / 16.0F,
							10.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					// wing3
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(7.0F / 16.0F, 1.0F / 16.0F, 1.0F / 16.0F, 9.0F / 16.0F, 3.0F / 16.0F,
							2.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(7.0F / 16.0F, 1.0F / 16.0F, 14.0F / 16.0F, 9.0F / 16.0F, 3.0F / 16.0F,
							15.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(1.0F / 16.0F, 1.0F / 16.0F, 7.0F / 16.0F, 2.0F / 16.0F, 3.0F / 16.0F,
							9.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(14.0F / 16.0F, 1.0F / 16.0F, 7.0F / 16.0F, 15.0F / 16.0F, 3.0F / 16.0F,
							9.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					// blade
					renderer.setOverrideBlockTexture(this.IIcon3);
					block.setBlockBounds(7.0F / 16.0F, 5.0F / 16.0F, 1.0F / 16.0F, 9.0F / 16.0F, 9.0F / 16.0F,
							2.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon3);
					block.setBlockBounds(7.0F / 16.0F, 5.0F / 16.0F, 14.0F / 16.0F, 9.0F / 16.0F, 9.0F / 16.0F,
							15.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon3);
					block.setBlockBounds(1.0F / 16.0F, 5.0F / 16.0F, 7.0F / 16.0F, 2.0F / 16.0F, 9.0F / 16.0F,
							9.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon3);
					block.setBlockBounds(14.0F / 16.0F, 5.0F / 16.0F, 7.0F / 16.0F, 15.0F / 16.0F, 9.0F / 16.0F,
							9.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					// plate
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(5.0F / 16.0F, 3.0F / 16.0F, 1.0F / 16.0F, 11.0F / 16.0F, 5.0F / 16.0F,
							2.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(5.0F / 16.0F, 3.0F / 16.0F, 14.0F / 16.0F, 11.0F / 16.0F, 5.0F / 16.0F,
							15.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(1.0F / 16.0F, 3.0F / 16.0F, 5.0F / 16.0F, 2.0F / 16.0F, 5.0F / 16.0F,
							11.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(14.0F / 16.0F, 3.0F / 16.0F, 5.0F / 16.0F, 15.0F / 16.0F, 5.0F / 16.0F,
							11.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(7.0F / 16.0F, 3.0F / 16.0F, 0.0F / 16.0F, 9.0F / 16.0F, 5.0F / 16.0F,
							2.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(7.0F / 16.0F, 3.0F / 16.0F, 14.0F / 16.0F, 9.0F / 16.0F, 5.0F / 16.0F,
							16.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(0.0F / 16.0F, 3.0F / 16.0F, 7.0F / 16.0F, 2.0F / 16.0F, 5.0F / 16.0F,
							9.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(14.0F / 16.0F, 3.0F / 16.0F, 7.0F / 16.0F, 16.0F / 16.0F, 5.0F / 16.0F,
							9.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					// plate2
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(4.0F / 16.0F, 3.0F / 16.0F, 1.0F / 16.0F, 5.0F / 16.0F, 5.0F / 16.0F,
							2.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(4.0F / 16.0F, 3.0F / 16.0F, 14.0F / 16.0F, 5.0F / 16.0F, 5.0F / 16.0F,
							15.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(1.0F / 16.0F, 3.0F / 16.0F, 4.0F / 16.0F, 2.0F / 16.0F, 5.0F / 16.0F,
							5.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(14.0F / 16.0F, 3.0F / 16.0F, 4.0F / 16.0F, 15.0F / 16.0F, 5.0F / 16.0F,
							5.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					// plate3
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(11.0F / 16.0F, 3.0F / 16.0F, 1.0F / 16.0F, 12.0F / 16.0F, 5.0F / 16.0F,
							2.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(11.0F / 16.0F, 3.0F / 16.0F, 14.0F / 16.0F, 12.0F / 16.0F, 5.0F / 16.0F,
							15.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(1.0F / 16.0F, 3.0F / 16.0F, 11.0F / 16.0F, 2.0F / 16.0F, 5.0F / 16.0F,
							12.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(14.0F / 16.0F, 3.0F / 16.0F, 11.0F / 16.0F, 15.0F / 16.0F, 5.0F / 16.0F,
							12.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

				// base
				renderer.setOverrideBlockTexture(this.IIcon4);
				block.setBlockBounds(7.5F / 16.0F, 1.0F / 16.0F, 7.5F / 16.0F, 8.5F / 16.0F, 6.0F / 16.0F, 8.5F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.IIcon4);
				block.setBlockBounds(4.0F / 16.0F, 0.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 1.0F / 16.0F,
						12.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (meta == 9) {
				// is using x32Texture?
				if (DCsConfig.setAltTexturePass == 1) {
					// force
					renderer.setOverrideBlockTexture(this.boxIIcon);
					block.setBlockBounds(4.0F / 16.0F, 7.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 15.0F / 16.0F,
							12.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					// rod
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(2.0F / 16.0F, 2.0F / 16.0F, 2.0F / 16.0F, 3.0F / 16.0F, 12.0F / 16.0F,
							3.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(2.0F / 16.0F, 2.0F / 16.0F, 13.0F / 16.0F, 3.0F / 16.0F, 12.0F / 16.0F,
							14.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(13.0F / 16.0F, 2.0F / 16.0F, 2.0F / 16.0F, 14.0F / 16.0F, 12.0F / 16.0F,
							3.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(13.0F / 16.0F, 2.0F / 16.0F, 13.0F / 16.0F, 14.0F / 16.0F, 12.0F / 16.0F,
							14.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					// rod2
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(3.0F / 16.0F, 11.0F / 16.0F, 3.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F,
							6.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(3.0F / 16.0F, 11.0F / 16.0F, 10.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F,
							13.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(12.0F / 16.0F, 11.0F / 16.0F, 3.0F / 16.0F, 13.0F / 16.0F, 12.0F / 16.0F,
							6.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(12.0F / 16.0F, 11.0F / 16.0F, 10.0F / 16.0F, 13.0F / 16.0F, 12.0F / 16.0F,
							13.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					// rod3
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(4.0F / 16.0F, 11.0F / 16.0F, 3.0F / 16.0F, 6.0F / 16.0F, 12.0F / 16.0F,
							4.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(4.0F / 16.0F, 11.0F / 16.0F, 12.0F / 16.0F, 6.0F / 16.0F, 12.0F / 16.0F,
							13.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(10.0F / 16.0F, 11.0F / 16.0F, 3.0F / 16.0F, 12.0F / 16.0F, 12.0F / 16.0F,
							4.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(10.0F / 16.0F, 11.0F / 16.0F, 12.0F / 16.0F, 12.0F / 16.0F, 12.0F / 16.0F,
							13.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					// rod4
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(3.0F / 16.0F, 12.0F / 16.0F, 3.0F / 16.0F, 4.0F / 16.0F, 14.0F / 16.0F,
							4.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(3.0F / 16.0F, 12.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F, 14.0F / 16.0F,
							13.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(12.0F / 16.0F, 12.0F / 16.0F, 3.0F / 16.0F, 13.0F / 16.0F, 14.0F / 16.0F,
							4.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(12.0F / 16.0F, 12.0F / 16.0F, 12.0F / 16.0F, 13.0F / 16.0F, 14.0F / 16.0F,
							13.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

				// base
				renderer.setOverrideBlockTexture(this.IIcon4);
				block.setBlockBounds(7.5F / 16.0F, 1.0F / 16.0F, 7.5F / 16.0F, 8.5F / 16.0F, 7.0F / 16.0F, 8.5F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.IIcon4);
				block.setBlockBounds(4.0F / 16.0F, 0.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 1.0F / 16.0F,
						12.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (meta == 10) {
				if (DCsConfig.setAltTexturePass == 1) {
					renderer.setOverrideBlockTexture(this.boxIIcon);
					block.setBlockBounds(2.0F / 16.0F, 2.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F, 14.0F / 16.0F,
							4.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.boxIIcon);
					block.setBlockBounds(2.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F, 14.0F / 16.0F,
							14.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.boxIIcon);
					block.setBlockBounds(12.0F / 16.0F, 2.0F / 16.0F, 2.0F / 16.0F, 14.0F / 16.0F, 14.0F / 16.0F,
							4.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.boxIIcon);
					block.setBlockBounds(12.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F, 14.0F / 16.0F, 14.0F / 16.0F,
							14.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.boxIIcon);
					block.setBlockBounds(2.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F, 4.0F / 16.0F, 4.0F / 16.0F,
							12.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.boxIIcon);
					block.setBlockBounds(12.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F, 14.0F / 16.0F, 4.0F / 16.0F,
							12.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.boxIIcon);
					block.setBlockBounds(4.0F / 16.0F, 2.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F,
							4.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.boxIIcon);
					block.setBlockBounds(4.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F,
							14.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.boxIIcon);
					block.setBlockBounds(2.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F, 4.0F / 16.0F, 14.0F / 16.0F,
							12.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.boxIIcon);
					block.setBlockBounds(12.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F, 14.0F / 16.0F, 14.0F / 16.0F,
							12.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.boxIIcon);
					block.setBlockBounds(4.0F / 16.0F, 12.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F, 14.0F / 16.0F,
							4.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.boxIIcon);
					block.setBlockBounds(4.0F / 16.0F, 12.0F / 16.0F, 12.0F / 16.0F, 12.0F / 16.0F, 14.0F / 16.0F,
							14.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
				}

				// base
				renderer.setOverrideBlockTexture(this.IIcon4);
				block.setBlockBounds(7.5F / 16.0F, 1.0F / 16.0F, 7.5F / 16.0F, 8.5F / 16.0F, 3.0F / 16.0F, 8.5F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.IIcon4);
				block.setBlockBounds(4.0F / 16.0F, 0.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 1.0F / 16.0F,
						12.0F / 16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (meta == 11) {
				if (DCsConfig.setAltTexturePass == 1) {
					// frame
					renderer.setOverrideBlockTexture(this.IIcon4);
					block.setBlockBounds(3.0F / 16.0F, 2.0F / 16.0F, 3.0F / 16.0F, 4.0F / 16.0F, 13.0F / 16.0F,
							4.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon4);
					block.setBlockBounds(3.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F, 13.0F / 16.0F,
							13.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon4);
					block.setBlockBounds(12.0F / 16.0F, 2.0F / 16.0F, 3.0F / 16.0F, 13.0F / 16.0F, 13.0F / 16.0F,
							4.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon4);
					block.setBlockBounds(12.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F, 13.0F / 16.0F, 13.0F / 16.0F,
							13.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.IIcon4);
					block.setBlockBounds(3.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F, 4.0F / 16.0F, 3.0F / 16.0F,
							12.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon4);
					block.setBlockBounds(12.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F, 13.0F / 16.0F, 3.0F / 16.0F,
							12.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon4);
					block.setBlockBounds(4.0F / 16.0F, 2.0F / 16.0F, 3.0F / 16.0F, 12.0F / 16.0F, 3.0F / 16.0F,
							4.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon4);
					block.setBlockBounds(4.0F / 16.0F, 2.0F / 16.0F, 12.0F / 16.0F, 12.0F / 16.0F, 3.0F / 16.0F,
							13.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.IIcon4);
					block.setBlockBounds(3.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F, 4.0F / 16.0F, 13.0F / 16.0F,
							12.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon4);
					block.setBlockBounds(12.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F, 13.0F / 16.0F, 13.0F / 16.0F,
							12.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon4);
					block.setBlockBounds(4.0F / 16.0F, 12.0F / 16.0F, 3.0F / 16.0F, 12.0F / 16.0F, 13.0F / 16.0F,
							4.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon4);
					block.setBlockBounds(4.0F / 16.0F, 12.0F / 16.0F, 12.0F / 16.0F, 12.0F / 16.0F, 13.0F / 16.0F,
							13.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					// head
					renderer.setOverrideBlockTexture(this.IIcon3);
					block.setBlockBounds(4.0F / 16.0F, 12.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 13.0F / 16.0F,
							12.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon3);
					block.setBlockBounds(4.0F / 16.0F, 2.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 3.0F / 16.0F,
							12.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(5.0F / 16.0F, 13.0F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 14.5F / 16.0F,
							11.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon2);
					block.setBlockBounds(5.0F / 16.0F, 0.5F / 16.0F, 5.0F / 16.0F, 11.0F / 16.0F, 2.0F / 16.0F,
							11.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					renderer.setOverrideBlockTexture(this.IIcon4);
					block.setBlockBounds(6.0F / 16.0F, 14.5F / 16.0F, 6.0F / 16.0F, 10.0F / 16.0F, 15.0F / 16.0F,
							10.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon4);
					block.setBlockBounds(6.0F / 16.0F, 0.0F / 16.0F, 6.0F / 16.0F, 10.0F / 16.0F, 0.5F / 16.0F,
							10.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					// main
					renderer.setOverrideBlockTexture(this.IIcon0);
					block.setBlockBounds(4.0F / 16.0F, 3.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 12.0F / 16.0F,
							12.0F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);

					if (world.getBlock(x, y + 1, z) != Blocks.air) {
						// base
						renderer.setOverrideBlockTexture(this.IIcon4);
						block.setBlockBounds(7.5F / 16.0F, 13.0F / 16.0F, 7.5F / 16.0F, 8.5F / 16.0F, 15F / 16.0F,
								8.5F / 16.0F);
						renderer.setRenderBoundsFromBlock(block);
						renderer.renderStandardBlock(block, x, y, z);
						renderer.setOverrideBlockTexture(this.IIcon4);
						block.setBlockBounds(4.0F / 16.0F, 15.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 16.0F / 16.0F,
								12.0F / 16.0F);
						renderer.setRenderBoundsFromBlock(block);
						renderer.renderStandardBlock(block, x, y, z);
					}
				} else {
					renderer.setOverrideBlockTexture(this.IIcon4);
					block.setBlockBounds(7.5F / 16.0F, 10.0F / 16.0F, 7.5F / 16.0F, 8.5F / 16.0F, 15F / 16.0F,
							8.5F / 16.0F);
					renderer.setRenderBoundsFromBlock(block);
					renderer.renderStandardBlock(block, x, y, z);
					renderer.setOverrideBlockTexture(this.IIcon4);
					block.setBlockBounds(4.0F / 16.0F, 15.0F / 16.0F, 4.0F / 16.0F, 12.0F / 16.0F, 16.0F / 16.0F,
							12.0F / 16.0F);
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

		return DCsAppleMilk.modelCLamp;
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

	private void renderInvCuboidAlpha(RenderBlocks renderer, Block block, float minX, float minY, float minZ,
			float maxX, float maxY, float maxZ, IIcon icon) {
		Tessellator tessellator = Tessellator.instance;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		GL11.glColor4f(2.0F, 2.0F, 2.0F, 0.9F);
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
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
	}
}
