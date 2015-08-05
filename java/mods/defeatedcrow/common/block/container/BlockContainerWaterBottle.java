package mods.defeatedcrow.common.block.container;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockContainerWaterBottle extends BlockContainerBase {

	public BlockContainerWaterBottle() {
		super();
	}

	@Override
	public ItemStack returnItem() {
		return new ItemStack(Items.potionitem, 1, 0);
	}

	@Override
	public int getRenderType() {
		return DCsAppleMilk.modelCWBottle;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.bottomIcon = par1IconRegister.registerIcon("defeatedcrow:x32/basket_B1");
		this.sideIcon = par1IconRegister.registerIcon("defeatedcrow:x32/basket_S1");
		this.topIcon = par1IconRegister.registerIcon("defeatedcrow:x32/basket_T1");
		this.blockIcon = par1IconRegister.registerIcon("defeatedcrow:containeritem_bottleW");
	}

}
