package mods.defeatedcrow.recipe;

import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.api.recipe.ICookingHeatSource;
import mods.defeatedcrow.api.recipe.IPlateRecipe;
import mods.defeatedcrow.api.recipe.IPlateRecipeRegister;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PlateRecipeRegister implements IPlateRecipeRegister {

	private static List<PlateRecipe> recipes;
	@Deprecated
	private static List<ItemStack> heatSource;
	private static List<HeatSource> sources;

	public PlateRecipeRegister() {
		this.recipes = new ArrayList<PlateRecipe>();
		this.heatSource = new ArrayList<ItemStack>();
		this.sources = new ArrayList<HeatSource>();
	}

	@Override
	public List<PlateRecipe> getRecipeList() {
		return this.recipes;
	}

	@Override
	public List<ItemStack> getHeatSourceList() {
		return this.heatSource;
	}

	@Override
	public List<HeatSource> getHeatSourcesList() {
		return this.sources;
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
		for (HeatSource source : this.sources) {
			if (block == source.block) {
				if (source.metadata == -1 || source.metadata == meta) {
					return true;
				}
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
			this.sources.add(new HeatSource(block, meta));
			AMTLogger.debugInfo("Add plate heat source : " + block.getLocalizedName() + ":" + meta);
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

	public class HeatSource implements ICookingHeatSource {

		private final Block block;
		private final int metadata;

		public HeatSource(Block b, int m) {
			block = b;
			metadata = m;
		}

		@Override
		public Block getBlock() {
			return block;
		}

		@Override
		public int getMetadata() {
			return metadata;
		}

	}

}
