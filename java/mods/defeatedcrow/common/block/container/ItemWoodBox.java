package mods.defeatedcrow.common.block.container;

import mods.defeatedcrow.api.ICompressedItem;
import mods.defeatedcrow.handler.Util;
import mods.defeatedcrow.plugin.LoadModHandler;
import mods.defeatedcrow.plugin.LoadThaumcraftPlugin;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemWoodBox extends ItemBlock implements ICompressedItem {

	private static final String[] boxItemType = new String[] {
			"_oak",
			"_spruse",
			"_birch",
			"_jangle",
			"_rubber",
			"_greatwood",
			"_silverwood",
			"_force",
			"_sakura",
			"_momizi",
			"_JPcedar",
			"_darkoak",
			"_acacia" };

	public ItemWoodBox(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		if (m < 13)
			return super.getUnlocalizedName() + boxItemType[m];
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
			return new ItemStack(Blocks.log, 9, 0);
		case 1:
			return new ItemStack(Blocks.log, 9, 1);
		case 2:
			return new ItemStack(Blocks.log, 9, 2);
		case 3:
			return new ItemStack(Blocks.log, 9, 3);
		case 4:
			return Util.getOreStack("woodRubber");
		case 5:
			return LoadThaumcraftPlugin.thaumicGreatwood == null ? null : new ItemStack(
					LoadThaumcraftPlugin.thaumicGreatwood.getItem(), 9, 0);
		case 6:
			return LoadThaumcraftPlugin.thaumicSilverwood == null ? null : new ItemStack(
					LoadThaumcraftPlugin.thaumicSilverwood.getItem(), 9, 1);
		case 8:
			return LoadModHandler.getItem("sakuraWood");
		case 9:
			return LoadModHandler.getItem("mapleWood");
		case 10:
			return LoadModHandler.getItem("sugiWood");
		case 11:
			return new ItemStack(Blocks.log2, 9, 1);
		case 12:
			return new ItemStack(Blocks.log2, 9, 0);
		default:
			return null;
		}

	}

}
