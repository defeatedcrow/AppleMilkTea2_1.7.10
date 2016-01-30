package mods.defeatedcrow.common.block.container;

import mods.defeatedcrow.api.ICompressedItem;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import wa.block.Blocks;

public class ItemFlowerPot extends ItemBlock implements ICompressedItem {

	public ItemFlowerPot(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		return super.getUnlocalizedName() + m;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public ItemStack getDisassembledItem(ItemStack cont) {
		if (cont == null || cont.getItem() == null)
			return null;
		int m = cont.getItemDamage();
		switch (m) {
		case 0:
			return new ItemStack(Blocks.red_flower, 9, 0);
		case 1:
			return new ItemStack(Blocks.yellow_flower, 9, 0);
		default:
			return null;
		}
	}

}
