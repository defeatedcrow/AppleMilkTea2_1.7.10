package mods.defeatedcrow.common.block.container;

import mods.defeatedcrow.api.ICompressedItem;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemCharcoalBox extends ItemBlock implements ICompressedItem {

	public ItemCharcoalBox(Block block) {
		super(block);
	}

	@Override
	public ItemStack getDisassembledItem(ItemStack container) {
		return new ItemStack(Items.coal, 9, 1);
	}

}
