package mods.defeatedcrow.api.recipe;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public interface IPanRecipeRegister {

	/**
	 * レシピのリストを取得する。
	 */
	List<? extends IPanRecipe> getRecipeList();

	@Deprecated
	List<ItemStack> getHeatSourceList();

	List<? extends ICookingHeatSource> getHeatSourcesList();

	/**
	 * ItemStackに登録されたレシピを返すメソッド。
	 * 
	 * @param item
	 *            (ItemStack) 確認する対象アイテム
	 */
	IPanRecipe getRecipe(ItemStack item);

	/**
	 * 鍋の熱源かの判定。
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
	 *            (ItemStack) 鍋から得られるアイテム
	 * @param tex
	 *            (String) テクスチャへのパス 例："applemilk:textures/blocks/contents_milk.png"
	 */
	void register(ItemStack input, ItemStack output, String tex, String disp);

	/**
	 * 新しいレシピを登録する際に呼び出すメソッド。
	 * こちらは竹MODとの連携を考慮したもの。AMT2本体は必ずこちらを使う。 <br>
	 * inputのメタデータに32767（shortの上限値）を入れた場合、異なるメタデータでも同じアウトプットを登録するレシピになります。
	 * 
	 * @param input
	 *            (ItemStack) 投入するアイテム
	 * @param output
	 *            (ItemStack) 鍋から得られるアイテム
	 * @param outputJP
	 *            (ItemStack)　竹カゴを使って得られるアイテム
	 * @param tex
	 *            (String) テクスチャへのパス 例："applemilk:textures/blocks/contents_milk.png"
	 */
	void register(ItemStack input, ItemStack output, ItemStack outputJP, String tex, String disp);

	/**
	 * 鍋の下に置ける熱源の登録。
	 * 
	 * @param block
	 *            (Block) ブロック
	 * @param meta
	 *            (int) メタデータ
	 */
	void registerHeatSource(Block block, int meta);

}
