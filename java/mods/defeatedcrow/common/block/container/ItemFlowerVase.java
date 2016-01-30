package mods.defeatedcrow.common.block.container;

import mods.defeatedcrow.api.ICompressedItem;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import wa.block.Blocks;

public class ItemFlowerVase extends ItemBlock implements ICompressedItem {

	private static final String[] types = new String[] {
			"_rose",
			"_peony",
			"_lilac",
			"_sun" };

	public ItemFlowerVase(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage() & 7);
		if (m < 4)
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
		switch (m) {
		case 0:
			return new ItemStack(Blocks.double_plant, 9, 4);
		case 1:
			return new ItemStack(Blocks.double_plant, 9, 5);
		case 2:
			return new ItemStack(Blocks.double_plant, 9, 1);
		case 3:
			return new ItemStack(Blocks.double_plant, 9, 0);
		default:
			return null;
		}
	}

}
