package mods.defeatedcrow.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import mods.defeatedcrow.api.recipe.IEvaporatorRecipe;
import mods.defeatedcrow.api.recipe.IEvaporatorRecipeRegister;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;

public class EvaporatorRecipeRegister implements IEvaporatorRecipeRegister {

	private static List<EvaporatorRecipe> recipes;

	public EvaporatorRecipeRegister() {
		this.recipes = new ArrayList<EvaporatorRecipe>();
	}

	public IEvaporatorRecipeRegister instance() {
		return RecipeRegisterManager.evaporatorRecipe;
	}

	@Override
	public void addRecipe(ItemStack output, FluidStack secondary, ItemStack input, boolean flag) {
		recipes.add(new EvaporatorRecipe(input, output, secondary, flag));
		String out = output == null ? "Empty" : output.getDisplayName();
		String sec = secondary == null ? "Empty" : secondary.getFluid().getLocalizedName(secondary);
		AMTLogger.debugInfo("Add Evaporator recipe: input: " + input.getDisplayName() + ", output: " + out
				+ ", secondary: " + sec);
	}

	public void addRecipe(ItemStack output, FluidStack secondary, ItemStack input) {
		this.addRecipe(output, secondary, input, true);
	}

	@Override
	public List<? extends IEvaporatorRecipe> getRecipeList() {
		return this.recipes;
	}

	@Override
	public IEvaporatorRecipe getRecipe(ItemStack input) {
		if (input == null)
			return null;
		for (EvaporatorRecipe recipe : this.recipes) {
			if (this.isItemEqual(input, recipe.getInput())) {
				return recipe;
			}
		}
		return null;
	}

	private boolean isItemEqual(ItemStack a, ItemStack b) {
		boolean flag = false;
		if (a.getItem() == b.getItem()) {
			if (a.getItemDamage() == b.getItemDamage()) {
				flag = true;
			} else if (b.getItemDamage() == Short.MAX_VALUE) {
				flag = true;
			}
		}
		return flag;
	}

	public class EvaporatorRecipe implements IEvaporatorRecipe {

		private final ItemStack input;
		private final ItemStack output;
		private final FluidStack second;
		private final boolean returnContainer;

		private final int damage = Short.MAX_VALUE;

		public EvaporatorRecipe(ItemStack inputItem, ItemStack outputItem, FluidStack secondary, boolean flag) {
			this.input = inputItem;
			this.output = outputItem;
			this.second = secondary;
			this.returnContainer = flag;
		}

		@Override
		public ItemStack getInput() {
			return this.input.copy();
		}

		@Override
		public ItemStack getOutput() {
			if (this.output == null)
				return null;
			return this.output.copy();
		}

		@Override
		public FluidStack getSecondary() {
			if (this.second == null)
				return null;
			return this.second.copy();
		}

		@Override
		public boolean returnContainer() {
			return this.returnContainer;
		}

	}

}
