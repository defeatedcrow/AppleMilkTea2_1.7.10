package mods.defeatedcrow.api.recipe;

import java.util.List;

import net.minecraft.item.ItemStack;

/**
 * StrangeSlagの使用時に出てくるアイテムを追加するためのインターフェイス。
 */
public interface ISlagResultLoot {

	/*
	 * <Tierについて>
	 * 
	 * Tierごとに確率が異なり、まず各Tierを抽選後、同じTierの登録アイテムの中から更に抽選されます。
	 * 同じTierであれば同率です。
	 * 
	 * Tier1(55%):土砂などハズレ枠のアイテム
	 * Tier2(30%):比較的生成数の多い鉱石ナゲット、少しレア度の高い石系のアイテム
	 * Tier3(10%):貴金属や生成数の低い鉱石の粉末系アイテム
	 * Tier4(4%):宝石系のアイテム
	 * Tier5(1%):隠し枠のレアアイテム
	 */

	/**
	 * Lootを追加する。
	 * 
	 * @param item
	 *            : 追加するItemStack
	 * @param tier
	 *            : 追加するアイテムのtier
	 */
	void addLoot(ItemStack item, int tier);

	List<ItemStack> getLootList(int tier);

}
