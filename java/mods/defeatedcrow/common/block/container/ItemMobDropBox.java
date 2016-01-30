package mods.defeatedcrow.common.block.container;

import mods.defeatedcrow.api.ICompressedItem;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemMobDropBox extends ItemBlock implements ICompressedItem {

	private static final String[] boxItemType = new String[] {
			"_rotten",
			"_bone",
			"_spider",
			"_ender",
			"_slime" };

	public ItemMobDropBox(Block block) {
		super(block);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		if (m < 5)
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
			return new ItemStack(Items.rotten_flesh, 9, 0);
		case 1:
			return new ItemStack(Items.bone, 9, 0);
		case 2:
			return new ItemStack(Items.spider_eye, 9, 0);
		case 3:
			return new ItemStack(Items.ender_pearl, 9, 0);
		case 4:
			return new ItemStack(Items.slime_ball, 9, 0);
		default:
			return null;
		}
	}

}
