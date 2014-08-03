package mods.defeatedcrow.api.recipe;

import net.minecraft.item.ItemStack;

public interface IProsessorRecipe {
	
	Object[] getInput();
	
	ItemStack getOutput();

}
