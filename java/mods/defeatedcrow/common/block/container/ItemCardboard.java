package mods.defeatedcrow.common.block.container;

import mods.defeatedcrow.api.ICompressedItem;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemCardboard extends ItemBlock implements ICompressedItem {

	private static final String[] bagVegi = new String[] {
			"_mint",
			"_cassis",
			"_yuzu",
			"_camellia",
			"_coffee",
			"_bamboo",
			"_tomato",
			"_grape" };

	public ItemCardboard(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage() & 7);
		if (m < 8)
			return super.getUnlocalizedName() + bagVegi[m];
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
			return new ItemStack(DCsAppleMilk.leafTea, 9, 1);
		case 1:
			return new ItemStack(DCsAppleMilk.leafTea, 9, 2);
		case 2:
			return new ItemStack(DCsAppleMilk.leafTea, 9, 3);
		case 3:
			return new ItemStack(DCsAppleMilk.leafTea, 9, 4);
		case 4:
			return Util.getOreStack("cropCoffee");
		case 5:
			return Util.getOreStack("bamboo");
		case 6:
			return Util.getOreStack("cropTomato");
		case 7:
			return Util.getOreStack("cropGrape");
		default:
			return null;
		}
	}

}
