package mods.defeatedcrow.api.recipe;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public interface IEvaporatorRecipeRegister {

	/**
	 * Evaporator(減圧蒸留器)にレシピを追加する。 <br>
	 * output、secondaryのどちらかがnullでも登録できる。 <br>
	 * inputがFluidContainerRegistryに登録された液体コンテナの場合、自動で空容器を返却するが、 <br>
	 * flagがfalseの場合は空容器返却をキャンセルできる。
	 * 
	 * @param output
	 *            (ItemStack) 完成品
	 * @param secondary
	 *            (FluidStack) 完成品(液体)
	 * @param input
	 *            (ItemStack) 材料アイテム
	 * @param flag
	 *            (boolean) 空容器返却の可否
	 */
	void addRecipe(ItemStack output, FluidStack secondary, ItemStack input, boolean flag);

	/**
	 * Evaporator(減圧蒸留器)にレシピを追加する。 <br>
	 * output、secondaryのどちらかがnullでも登録できる。 <br>
	 * inputがFluidContainerRegistryに登録された液体コンテナの場合、強制的に空容器を返却する
	 * 
	 * @param output
	 *            (ItemStack) 完成品
	 * @param secondary
	 *            (FluidStack) 完成品(液体)
	 * @param input
	 *            (ItemStack) 材料アイテム
	 */
	void addRecipe(ItemStack output, FluidStack secondary, ItemStack input);

	public abstract List<? extends IEvaporatorRecipe> getRecipeList();

	IEvaporatorRecipe getRecipe(ItemStack input);

}
