package mods.defeatedcrow.api.recipe;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public interface IPlateRecipeRegister {

	/**
	 * レシピのリストを取得する。
	 */
	List<? extends IPlateRecipe> getRecipeList();

	@Deprecated
	List<ItemStack> getHeatSourceList();

	List<? extends ICookingHeatSource> getHeatSourcesList();

	/**
	 * ItemStackに登録されたレシピを返すメソッド。
	 * 
	 * @param item
	 *            (ItemStack) 確認する対象アイテム
	 */
	IPlateRecipe getRecipe(ItemStack item);

	/**
	 * 鉄板の熱源かの判定。
	 */
	boolean isHeatSource(Block block, int meta);

	/**
	 * 新しいレシピを登録する際に呼び出すメソッド。
	 * postInit以降のメソッドでの登録を推奨。 <br>
	 * inputのメタデータに32767（shortの上限値）を入れた場合、異なるメタデータでも同じアウトプットを登録するレシピになります。
	 * 
	 * @param input
	 *            (ItemStack) 投入するアイテム
	 * @param output
	 *            (ItemStack) 鉄板から得られるアイテム
	 * @param cookingTime
	 *            (int) 焼き上がりに掛かる時間。レシピごとに設定可能。
	 * @param isOvenRecipe
	 *            (boolean) Oven化しないと扱えないレシピかどうか。
	 */
	void register(ItemStack input, ItemStack output, int cookingTime, boolean isOvenRecipe);

	/**
	 * 熱源の登録。
	 * 
	 * @param block
	 *            (Block) ブロック
	 * @param meta
	 *            (int) メタデータ
	 */
	void registerHeatSource(Block block, int meta);

}
