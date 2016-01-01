package mods.defeatedcrow.api.recipe;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface IProcessorRecipe {

	Object[] getInput();

	ItemStack getOutput();

	ItemStack getSecondary();

	float getChance();

	public boolean isFoodRecipe();

	public boolean forceReturnContainer();

	public List<Object> getProcessedInput();

	public int getRecipeSize();

	public boolean matches(List<ItemStack> items);

	ItemStack getContainerItem(List<ItemStack> items);

	int getRecipeTier();

	boolean matchTier(ItemStack item);

}
