package mods.defeatedcrow.api.appliance;

import mods.defeatedcrow.api.recipe.ITeaRecipe;
import net.minecraft.item.ItemStack;

/**
 * TeaMakerを外部から操作するためのインターフェイス。
 * ティーメーカーの右クリックイベントなどと組み合わせて使用できる。
 */
public interface ITeaMaker {

	/**
	 * 現在のティーメーカーの中身のレシピ。
	 */
	public ITeaRecipe getRecipe();

	/**
	 * 現在のティーメーカーの中身の残量。
	 */
	public byte getRemain();

	/**
	 * 現在のティーメーカーの中身にミルクが入っているかどうか。
	 */
	public boolean getMilked();

	/**
	 * 変数のアイテムがティーメーカーのレシピを持つ飲み物の材料かどうか、
	 * 及びティーメーカーがレシピを受け入れられる状態かどうか(残量が0で、レシピがnullであるか)を判定する。 <br>
	 * 材料の受け入れが可能であればtrueを返す。
	 */
	public boolean canSetRecipe(ItemStack item);

	/**
	 * ティーメーカーに材料アイテムを投入し、飲み物を作る。
	 */
	public void setRecipe(ItemStack item);

	/**
	 * 現在のティーメーカーの残量だけを変更する。
	 */
	public void setRemain(byte rem);

	/**
	 * 現在のティーメーカーの保有レシピがミルク受け入れ可能であれば、ミルク入りレシピに変える。
	 */
	public void setMilk(boolean flag);

	/**
	 * 現在のティーメーカーのレシピから得られる飲み物アイテムを返す。
	 */
	public ItemStack getOutput();

}
