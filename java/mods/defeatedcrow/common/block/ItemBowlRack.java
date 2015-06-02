package mods.defeatedcrow.common.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBowlRack extends ItemBlock {

	public ItemBowlRack(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int i = par1ItemStack.getItemDamage();
		return super.getUnlocalizedName() + i;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

}
