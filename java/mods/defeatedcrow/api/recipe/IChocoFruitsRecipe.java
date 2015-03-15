package mods.defeatedcrow.api.recipe;

import java.util.Map;

import net.minecraft.item.ItemStack;

/**
 * Chocolate fondue recipes for pan.
 * */
public interface IChocoFruitsRecipe {
	
	//The input objects include ItemStack or String (ore dictionary name).
	Map<Object, ItemStack> getRecipeList();
	
	ItemStack getOutput(ItemStack input);
	
	void register(ItemStack input, ItemStack output);
	
	void register(String input, ItemStack output);

}
