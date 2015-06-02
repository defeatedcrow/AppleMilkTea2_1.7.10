package mods.defeatedcrow.common.block.plants;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemCassisTree extends ItemBlock {

	public ItemCassisTree(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);

	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		return super.getUnlocalizedName() + "_" + m;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

}
