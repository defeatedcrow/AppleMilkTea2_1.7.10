package mods.defeatedcrow.common.block.container;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemWoodBox extends ItemBlock {

	private static final String[] boxItemType = new String[] { "_oak", "_spruse", "_birch", "_jangle", "_rubber",
			"_greatwood", "_silverwood", "_force", "_sakura", "_momizi", "_JPcedar", "_darkoak", "_acacia" };

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

}
