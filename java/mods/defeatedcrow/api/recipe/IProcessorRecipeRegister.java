package mods.defeatedcrow.api.recipe;

import java.util.List;

import net.minecraft.item.ItemStack;

/**
 * このクラスではProcessor、Jaw Crusherの両方のレシピを扱う。
 */
public interface IProcessorRecipeRegister {

	/**
	 * Processor(フードプロセッサー)またはJaw Crusher(ジョークラッシャー)のどちらかにレシピを追加する。
	 * isFoodRecipeがtrueではProcessor、falseではJawCrusher専用レシピとなる。 <br>
	 * secondaryがnullでも登録できるが、output及びinputがnullでは登録できない。 <br>
	 * また、inputには鉱石辞書名を使用できる。"ingotIron"のように、文字列として""で囲めば良い。 <br>
	 * こちらのメソッドでは、secondaryの発生確率をsecondaryChance(0.0F～1.0F)によって登録できる。
	 * 
	 * @param output
	 *            : 完成品
	 * @param secondary
	 *            : 完成品(液体)
	 * @param isFoodRecipe
	 *            : Processor用レシピかどうか
	 * @param forceReturnContainer
	 *            : 材料アイテムがContaineeItemと同じclassの場合でも、容器返却を強制する
	 * @param secondaryChance
	 *            : secondary生成確率
	 * @param input
	 *            : 材料アイテム(ItemStack, String)
	 */
	void addRecipe(ItemStack output, boolean isFoodRecipe, boolean forceReturnContainer, ItemStack secondary,
			float secondaryChance, Object... input);

	/**
	 * Processor(フードプロセッサー)またはJaw Crusher(ジョークラッシャー)のどちらかにレシピを追加する。 <br>
	 * forceReturnContainerがfalseになっている登録メソッド。
	 * 
	 * @param output
	 *            : 完成品
	 * @param secondary
	 *            : 完成品(液体)
	 * @param isFoodRecipe
	 *            : Processor用レシピかどうか
	 * @param secondaryChance
	 *            : secondary生成確率
	 * @param input
	 *            : 材料アイテム(ItemStack, String)
	 */
	void addRecipe(ItemStack output, boolean isFoodRecipe, ItemStack secondary, float secondaryChance, Object... input);

	/**
	 * Processor(フードプロセッサー)またはJaw Crusher(ジョークラッシャー)のどちらかにレシピを追加する。 <br>
	 * こちらのメソッドでは、secondaryの発生確率は100%で固定される。
	 * 
	 * @param output
	 *            : 完成品
	 * @param secondary
	 *            : 完成品(液体)
	 * @param isFoodRecipe
	 *            : Processor用レシピかどうか
	 * @param input
	 *            : 材料アイテム(ItemStack, String)
	 */
	void addRecipe(ItemStack output, boolean isFoodRecipe, ItemStack secondary, Object... input);

	List<? extends IProcessorRecipe> getRecipes();

}
