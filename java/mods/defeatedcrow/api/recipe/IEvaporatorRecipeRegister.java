package mods.defeatedcrow.api.recipe;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public interface IEvaporatorRecipeRegister {
	
	void addRecipe(ItemStack output, FluidStack secondary, ItemStack input);
	
	public abstract List<? extends IEvaporatorRecipe> getRecipeList();
	
	IEvaporatorRecipe getRecipe(ItemStack input);

}
