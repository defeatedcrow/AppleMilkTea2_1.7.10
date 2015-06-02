package mods.defeatedcrow.api.recipe;

import net.minecraft.item.ItemStack;

public abstract interface IChargeIce {

	/**
	 * IceMakerの材料スロットに投入した時にチャージを増やせる量。
	 * 
	 * @param item
	 *            (ItemStack) 投入アイテム
	 */
	public abstract int chargeAmount();

	public abstract ItemStack getItem();

}
