package mods.defeatedcrow.api.recipe;

import java.util.List;

import net.minecraft.item.ItemStack;

public abstract interface IIceRecipeRegister {

	/**
	 * レシピのリストを取得する。
	 */
	public abstract List<? extends IIceRecipe> getRecipeList();

	public abstract List<? extends IChargeIce> getChargeItemList();

	/**
	 * ItemStackに登録されたレシピを返すメソッド。
	 * 
	 * @param item
	 *            (ItemStack) 確認する対象アイテム
	 */
	public abstract IIceRecipe getRecipe(ItemStack item);

	/**
	 * ItemStackにチャージ量が設定されている場合に値を返す。
	 * 
	 * @param item
	 *            (ItemStack) 確認する対象アイテム
	 */
	public abstract int getChargeAmount(ItemStack item);

	/**
	 * 新しいレシピを登録する際に呼び出すメソッド。
	 * postInit以降のメソッドでの登録を推奨。
	 * 
	 * @param input
	 *            (ItemStack) 投入するアイテム
	 * @param output
	 *            (ItemStack) アイスメーカーから得られるアイテム
	 */
	public abstract void register(ItemStack input, ItemStack output);

	/**
	 * 新しいレシピを登録する際に呼び出すメソッド。
	 * postInit以降のメソッドでの登録を推奨。 <br>
	 * アウトプットは完成スロット左、空容器は完成スロット右に排出される。 <br>
	 * 空容器と名づけているが、副生成物のように扱っても良いと思う。
	 * 
	 * @param input
	 *            (ItemStack) 材料スロットに入れるアイテム
	 * @param output
	 *            (ItemStack) アイスメーカーから得られるアイテム
	 * @param container
	 *            (ItemStack) 空容器スロットに排出される副生成物アイテム
	 */
	public abstract void registerCanLeave(ItemStack input, ItemStack output, ItemStack container);

	/**
	 * 新しいチャージアイテムを登録する。
	 * postInit以降のメソッドでの登録を推奨。
	 * 
	 * @param input
	 *            (ItemStack) アイスメーカーの燃料スロットに投入するアイテム
	 * @param val
	 *            (int) チャージ量（上限127）
	 */
	public abstract void registerCharger(ItemStack input, int val);

}
