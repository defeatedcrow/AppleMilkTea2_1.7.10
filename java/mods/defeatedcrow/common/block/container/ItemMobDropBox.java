package mods.defeatedcrow.common.block.container;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemMobDropBox extends ItemBlock {

	private static final String[] boxItemType = new String[] { "_rotten", "_bone", "_spider", "_ender", "_slime" };

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

}
