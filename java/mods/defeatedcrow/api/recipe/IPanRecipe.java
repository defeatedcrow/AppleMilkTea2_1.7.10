package mods.defeatedcrow.api.recipe;

import net.minecraft.item.ItemStack;

public interface IPanRecipe {

	ItemStack getInput();

	ItemStack getOutput();

	ItemStack getOutputJP();

	String getTex();

	String getDisplayName();

}
