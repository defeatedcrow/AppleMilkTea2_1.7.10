package mods.defeatedcrow.common.block.container;

import mods.defeatedcrow.api.ICompressedItem;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemWipeBox extends ItemBlock implements ICompressedItem {

	private static final String[] type = new String[] {
			"_basket",
			"_wipe",
			"_basket_open",
			"_wipe_open" };

	public ItemWipeBox(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);

	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		if (m < 4)
			return super.getUnlocalizedName() + type[m];
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
		int m = cont.getItemDamage() & 1;
		switch (m) {
		case 0:
			return new ItemStack(Items.paper, 9, 0);
		case 1:
			return new ItemStack(DCsAppleMilk.wipeBox, 9, 0);
		default:
			return null;
		}
	}

}
