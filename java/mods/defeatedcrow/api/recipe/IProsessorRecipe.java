package mods.defeatedcrow.api.recipe;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface IProsessorRecipe {
	
	Object[] getInput();
	
	ItemStack getOutput();
	
	ItemStack getSecondary();
	
	public boolean isFoodRecipe();
	
	public List<Object> getProsessedInput();
	
	public int getRecipeSize();
	
	public boolean matches(List<ItemStack> items);

}
