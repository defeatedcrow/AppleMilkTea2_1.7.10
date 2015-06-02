package mods.defeatedcrow.api.charge;

import net.minecraft.item.ItemStack;

/**
 * 充電不可の使い捨て電池アイテムのインターフェイス
 * かき氷器用のIChargeIceと混同しやすい名前になってしまった
 */
public interface IChargeItem {

	/**
	 * 燃料スロットに投入した時にチャージを増やせる量。
	 * 
	 * @param item
	 *            (ItemStack) 投入アイテム
	 */
	public abstract int chargeAmount();

	/**
	 * 燃料となるアイテム
	 * 
	 * @param item
	 *            (ItemStack) 登録アイテム
	 */
	public abstract ItemStack getItem();

	/**
	 * 空になった場合に返却されるアイテム
	 * 
	 * @param item
	 *            (ItemStack) 登録アイテム
	 */
	public abstract ItemStack returnItem();

}
