package mods.defeatedcrow.common.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemChalcedony extends ItemBlock {

	private static final String[] type = new String[] { "", "_orange", "_white", "_onix" };

	public ItemChalcedony(Block block) {
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

}
