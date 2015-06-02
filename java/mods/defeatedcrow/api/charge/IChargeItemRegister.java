package mods.defeatedcrow.api.charge;

import java.util.List;

import net.minecraft.item.ItemStack;

public abstract interface IChargeItemRegister {

	public abstract List<? extends IChargeItem> getChargeItemList();

	/**
	 * ItemStackにチャージ量が設定されている場合に値を返す。
	 * 
	 * @param item
	 *            (ItemStack) 確認する対象アイテム
	 */
	public abstract int getChargeAmount(ItemStack item);

	/**
	 * 新しいチャージアイテムを登録する。
	 * postInit以降のメソッドでの登録を推奨。
	 * 
	 * @param input
	 *            (ItemStack) 追加機械の燃料スロットに投入するアイテム
	 * @param val
	 *            (int) チャージ量
	 */
	public abstract void registerCharger(ItemStack input, ItemStack output, int val);

}
