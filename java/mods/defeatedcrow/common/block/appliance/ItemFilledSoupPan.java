package mods.defeatedcrow.common.block.appliance;

import mods.defeatedcrow.api.appliance.SoupType;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemFilledSoupPan extends ItemBlock {

	public ItemFilledSoupPan(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int i = Math.min(par1ItemStack.getItemDamage(), SoupType.types.length);
		return super.getUnlocalizedName() + "_" + SoupType.getType(i);
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

}
