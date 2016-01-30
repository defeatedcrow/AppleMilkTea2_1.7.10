package mods.defeatedcrow.common.block.container;

import mods.defeatedcrow.api.ICompressedItem;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import wa.block.Blocks;

public class ItemHedge extends ItemBlock implements ICompressedItem {

	private static final String[] types = new String[] {
			"_boxwood_n",
			"_podocarp",
			"_photinia",
			"_snakegourd",
			"_osmanthus",
			"_boxwood_g" };

	public ItemHedge(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage() & 7);
		if (m < 6)
			return super.getUnlocalizedName() + types[m];
		else
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
		if (m < 7) {
			return new ItemStack(Blocks.sapling, 9, m);
		} else {
			return null;
		}
	}

}
