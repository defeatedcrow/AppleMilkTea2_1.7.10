package mods.defeatedcrow.api.recipe;

import java.util.List;
import net.minecraft.item.ItemStack;

public interface ITeaRecipeRegister {

	/**
	 * レシピのリストを取得する。
	 */
	List<? extends ITeaRecipe> getRecipeList();

	/**
	 * ItemStackに登録されたレシピを返すメソッド。
	 * 
	 * @param item
	 *            (ItemStack) 確認する対象アイテム
	 */
	ITeaRecipe getRecipe(ItemStack item);

	/**
	 * 新しいレシピを登録する際に呼び出すメソッド。
	 * postInit以降のメソッドでの登録を推奨。 <br>
	 * inputのメタデータに32767（shortの上限値）を入れた場合、異なるメタデータでも同じアウトプットを登録するレシピになります。
	 * 
	 * @param input
	 *            (ItemStack) 投入するアイテム
	 * @param output
	 *            (ItemStack) ティーメーカーから得られるアイテム
	 * @param tex
	 *            (String) テクスチャへのパス 例："applemilk:textures/blocks/contents_milk.png"
	 */
	void register(ItemStack input, ItemStack output, String tex);

	/**
	 * ミルクを追加投入できる場合のレシピ登録メソッドその1。
	 * postInit以降のメソッドでの登録を推奨。
	 * 
	 * @param input
	 *            (ItemStack) 投入するアイテム
	 * @param output
	 *            (ItemStack) ティーメーカーから得られるアイテム
	 * @param output2
	 *            (ItemStack) ミルク追加時に得られるアイテム
	 * @param tex
	 *            (String) テクスチャへのパス 例："applemilk:textures/blocks/contents_milk.png"
	 */
	void registerCanMilk(ItemStack input, ItemStack output, ItemStack output2, String tex);

	/**
	 * ミルクを追加投入できる場合のレシピ登録メソッドその2。
	 * ミルク入り飲料に専用のテクスチャを登録したい場合に使用。 <br>
	 * （テクスチャの用意が手間だという方は、その1の方を使用して下さい。） <br>
	 * postInit以降のメソッドでの登録を推奨。
	 * 
	 * @param input
	 *            (ItemStack) 投入するアイテム
	 * @param output
	 *            (ItemStack) ティーメーカーから得られるアイテム
	 * @param output2
	 *            (ItemStack) ミルク追加時に得られるアイテム
	 * @param tex
	 *            (String) テクスチャへのパス 例："applemilk:textures/blocks/contents_tea.png"
	 * @param milktex
	 *            (String) テクスチャへのパス 例："applemilk:textures/blocks/contents_tea_milk.png"
	 */
	void registerCanMilk(ItemStack input, ItemStack output, ItemStack output2, String tex, String milktex);

}
