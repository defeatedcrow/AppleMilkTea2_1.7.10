package mods.defeatedcrow.recipe;

import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.api.recipe.*;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.recipe.PlateRecipeRegister.PlateRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class PlateRecipeRegister implements IPlateRecipeRegister {

	private static List<PlateRecipe> recipes;
	private static List<ItemStack> heatSource;

	public PlateRecipeRegister() {
		this.recipes = new ArrayList<PlateRecipe>();
		this.heatSource = new ArrayList<ItemStack>();
	}

	@Override
	public List<PlateRecipe> getRecipeList() {
		return this.recipes;
	}

	@Override
	public List<ItemStack> getHeatSourceList() {
		return this.heatSource;
	}

	public IPlateRecipeRegister instance() {
		return RecipeRegisterManager.plateRecipe;
	}

	@Override
	public PlateRecipe getRecipe(ItemStack item) {
		if (item == null)
			return null;
		for (PlateRecipe recipe : this.recipes) {
			if (this.isItemEqual(item, recipe.getInput().getItem(), recipe.getInput().getItemDamage())) {
				return recipe;
			}
		}
		return null;
	}

	@Override
	public boolean isHeatSource(Block block, int meta) {
		if (block == null)
			return false;
		int m = MathHelper.clamp_int(meta, 0, 15);
		for (ItemStack source : this.heatSource) {
			if (this.isItemEqual(source, Item.getItemFromBlock(block), m)) {
				return true;
			}
		}
		return false;
	}

	private boolean isItemEqual(ItemStack a, Item b, int meta) {
		if (a == null)
			return false;
		boolean flag = false;
		if (a.getItem() == b) {
			if (a.getItemDamage() == meta) {
				flag = true;
			} else if (a.getItemDamage() == 32767) {
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public void register(ItemStack input, ItemStack output, int time, boolean isOven) {
		if (input != null && output != null) {
			this.recipes.add(new PlateRecipe(input, output, time, isOven));
			AMTLogger.debugInfo("Add Plate Recipe: input " + input.getDisplayName() + ", output "
					+ output.getDisplayName());
		}
	}

	@Override
	public void registerHeatSource(Block block, int meta) {
		if (block != null) {
			int m = MathHelper.clamp_int(meta, -1, 15);
			if (m == -1) {
				this.heatSource.add(new ItemStack(block, 1, 32767));
			} else {
				this.heatSource.add(new ItemStack(block, 1, m));
			}

			AMTLogger.debugInfo("Add heat source : " + block.getLocalizedName());
		}
	}

	public class PlateRecipe implements IPlateRecipe {

		private final ItemStack input;
		private final ItemStack output;
		private final int cookTime;
		private final boolean isOven;

		public PlateRecipe(ItemStack inputItem, ItemStack outputItem, int time, boolean flag) {
			this.input = inputItem;
			this.output = outputItem;
			this.cookTime = time;
			this.isOven = flag;
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
		public int cookingTime() {
			return this.cookTime;
		}

		@Override
		public boolean useOvenRecipe() {
			return this.isOven;
		}

	}

}
