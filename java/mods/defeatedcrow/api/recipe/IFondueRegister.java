package mods.defeatedcrow.api.recipe;

import java.util.List;

import mods.defeatedcrow.api.appliance.SoupType;
import net.minecraft.item.ItemStack;

public abstract interface IFondueRegister {

	/**
	 * レシピのリストを取得する。
	 */
	public abstract List<? extends IFondueRecipe> getRecipeList();

	public abstract List<? extends IFondueSource> getSourceList();

	/**
	 * ItemStackに登録されたレシピを返すメソッド。
	 * 
	 * @param item
	 *            (ItemStack) 確認する対象アイテム
	 * @param type
	 *            (SoupType) 鍋の中身タイプ
	 */
	public abstract IFondueRecipe getRecipe(ItemStack item, SoupType type);

	/**
	 * 投入するとSoupTypeが変わるかどうかを返す。
	 * 
	 * @param item
	 *            (ItemStack) 確認する対象アイテム
	 */
	public abstract IFondueSource getType(ItemStack item);

	/**
	 * 新しいレシピを登録する際に呼び出すメソッド。
	 * postInit以降のメソッドでの登録を推奨。
	 * 
	 * @param input
	 *            投入するアイテム
	 * @param output
	 *            鍋から得られるアイテム
	 * @param type
	 *            必要なSoupType
	 */
	public abstract void register(ItemStack input, ItemStack output, SoupType type);

	/**
	 * 新しいレシピを鉱石辞書名で登録する。
	 * 
	 * @param input
	 *            投入するアイテム
	 * @param output
	 *            鍋から得られるアイテム
	 * @param type
	 *            必要なSoupType
	 */
	public abstract void registerByOre(String input, ItemStack output, SoupType type);

	/**
	 * 鍋に投げ込むとSoupTypeが変化するアイテムの登録。
	 * 
	 * @param input
	 *            投入するアイテム
	 * @param before
	 *            変化前のType。EMPTYの場合、Typeを問わない
	 * @param result
	 *            変化後のType
	 */
	public abstract void registerSource(Object input, SoupType before, SoupType result);

}
