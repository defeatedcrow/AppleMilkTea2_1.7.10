package mods.defeatedcrow.common.block.container;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemFlowerVase extends ItemBlock {

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

}
