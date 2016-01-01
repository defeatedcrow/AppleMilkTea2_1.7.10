package mods.defeatedcrow.recipe;

import java.util.HashMap;

import mods.defeatedcrow.api.recipe.IIceRecipe;
import mods.defeatedcrow.api.recipe.ITeaRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.ItemStack;

public class RegisteredRecipeGet {

	public static HashMap<ItemStack, ItemStack> teaRecipeList = new HashMap<ItemStack, ItemStack>();
	public static HashMap<ItemStack, ItemStack[]> iceRecipeList = new HashMap<ItemStack, ItemStack[]>();
	public static HashMap<ItemStack, ItemStack> panRecipeList = new HashMap<ItemStack, ItemStack>();

	public void setRecipeList() {

		for (ITeaRecipe recipe : RecipeRegisterManager.teaRecipe.getRecipeList()) {
			ItemStack input = recipe.getInput();
			ItemStack output = recipe.getOutput();

			if (input != null && output != null) {
				teaRecipeList.put(input, output);
			}
		}

		for (IIceRecipe recipe : RecipeRegisterManager.iceRecipe.getRecipeList()) {
			ItemStack input = recipe.getInput();
			ItemStack[] output = new ItemStack[2];
			output[0] = recipe.getOutput();
			output[1] = recipe.getContainer();

			if (input != null && output != null) {
				iceRecipeList.put(input, output);
			}
		}

		panRecipeList.put(new ItemStack(DCsAppleMilk.mincedFoods, 1, 3), new ItemStack(DCsAppleMilk.bowlBlock, 1, 0));
		panRecipeList.put(new ItemStack(DCsAppleMilk.mincedFoods, 1, 0), new ItemStack(DCsAppleMilk.bowlBlock, 1, 1));
		panRecipeList.put(new ItemStack(DCsAppleMilk.mincedFoods, 1, 1), new ItemStack(DCsAppleMilk.bowlBlock, 1, 2));
		panRecipeList.put(new ItemStack(DCsAppleMilk.mincedFoods, 1, 2), new ItemStack(DCsAppleMilk.bowlBlock, 1, 3));
		panRecipeList.put(new ItemStack(DCsAppleMilk.mincedFoods, 1, 4), new ItemStack(DCsAppleMilk.bowlBlock, 1, 4));
		panRecipeList.put(new ItemStack(DCsAppleMilk.mincedFoods, 1, 5), new ItemStack(DCsAppleMilk.bowlBlock, 1, 5));
		panRecipeList.put(new ItemStack(DCsAppleMilk.mincedFoods, 1, 6), new ItemStack(DCsAppleMilk.bowlBlock, 1, 6));
		panRecipeList.put(new ItemStack(DCsAppleMilk.mincedFoods, 1, 7), new ItemStack(DCsAppleMilk.bowlBlock, 1, 7));
		panRecipeList.put(new ItemStack(DCsAppleMilk.mincedFoods, 1, 9), new ItemStack(DCsAppleMilk.bowlBlock, 1, 8));
		panRecipeList.put(new ItemStack(DCsAppleMilk.mincedFoods, 1, 10), new ItemStack(DCsAppleMilk.bowlBlock, 1, 9));
	}

}
