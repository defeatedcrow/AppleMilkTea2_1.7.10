package mods.defeatedcrow.common.block.appliance;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemTeppann extends ItemBlock {

	private static final String[] type = new String[] { "_empty", "_rawbeef", "_cookedbeef", "_rawpork", "_cookedpork",
			"_rawchicken", "_cookedchicken", "_clam", "_cookedclam", "_burnt" };

	public ItemTeppann(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		if (m < 10)
			return super.getUnlocalizedName() + type[m];
		else
			return super.getUnlocalizedName() + m;

	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

}
