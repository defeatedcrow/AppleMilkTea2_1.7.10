package mods.defeatedcrow.common.block.container;

import mods.defeatedcrow.api.ICompressedItem;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemVegiBag extends ItemBlock implements ICompressedItem {

	private static final String[] bagVegi = new String[] {
			"_Leaves",
			"_Potato",
			"_Carrot",
			"_Pumpkin",
			"_Seed",
			"_Reed",
			"_Cactus",
			"_Cocoa",
			"_Wart",
			"_Sugar" };

	public ItemVegiBag(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		if (m < 10)
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
			return new ItemStack(DCsAppleMilk.leafTea, 9, 0);
		case 1:
			return new ItemStack(Items.potato, 9, 0);
		case 2:
			return new ItemStack(Items.carrot, 9, 0);
		case 3:
			return new ItemStack(Blocks.pumpkin, 9, 0);
		case 4:
			return new ItemStack(Items.wheat_seeds, 9, 0);
		case 5:
			return new ItemStack(Items.reeds, 9, 0);
		case 6:
			return new ItemStack(Blocks.cactus, 9, 0);
		case 7:
			return new ItemStack(Items.dye, 9, 3);
		case 8:
			return new ItemStack(Blocks.nether_wart, 9, 0);
		case 9:
			return new ItemStack(Items.sugar, 9, 0);
		default:
			return null;
		}
	}

}
