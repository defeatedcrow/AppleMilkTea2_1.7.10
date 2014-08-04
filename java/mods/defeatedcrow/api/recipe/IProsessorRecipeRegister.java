package mods.defeatedcrow.api.recipe;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface IProsessorRecipeRegister {
	
	void addRecipe(ItemStack output, Object... input);
	
	List<? extends IProsessorRecipe> getRecipes();

}
