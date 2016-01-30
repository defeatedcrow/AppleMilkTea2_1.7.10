package mods.defeatedcrow.common.block.container;

import mods.defeatedcrow.api.ICompressedItem;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemGunpowderContainer extends ItemBlock implements ICompressedItem {

	private static final String[] type = new String[] {
			"_gunpowder",
			"_kayaku",
			"_clay" };

	public ItemGunpowderContainer(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);

	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		if (m < 3)
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
		int m = cont.getItemDamage();
		switch (m) {
		case 0:
			return new ItemStack(Items.gunpowder, 9, 0);
		case 1:
			return new ItemStack(DCsAppleMilk.clam, 9, 0);
		case 2:
			return new ItemStack(DCsAppleMilk.EXItems, 9, 2);
		case 3:
			return new ItemStack(Items.clay_ball, 9, 0);
		default:
			return null;
		}
	}

}
