package mods.defeatedcrow.api.energy;

import net.minecraft.item.ItemStack;

/**
 * 充電可能なバッテリーアイテムのインターフェイス
 * 充電池は、使い捨て電池と違い、ゆっくりとチャージが減少する
 */
public interface IBattery {

	public int getChargeAmount(ItemStack item);

	public int getMaxAmount(ItemStack item);

	public boolean isFullCharged(ItemStack item);

	/**
	 * 充電を増やす処理。シミュレート可能。返り値は実際に充電を増加できる値。
	 * 
	 * @param item
	 *            : 電池アイテム
	 * @param amount
	 *            : 充電増加予定の値
	 * @param flag
	 *            : 実際に処理するかどうか。falseの場合は計算のみで、実際の増加処理は行わない。
	 */
	public int charge(ItemStack item, int amount, boolean flag);

	/**
	 * 充電を減らす処理。シミュレート可能。返り値は実際の充電の減少値。
	 * 
	 * @param item
	 *            : 電池アイテム
	 * @param amount
	 *            : 充電減少予定の値
	 * @param flag
	 *            : 実際に処理するかどうか。falseの場合は計算のみで、実際の減少処理は行わない。
	 */
	public int discharge(ItemStack item, int amount, boolean flag);

}
