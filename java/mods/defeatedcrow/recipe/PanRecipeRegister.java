package mods.defeatedcrow.recipe;

import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.api.recipe.ICookingHeatSource;
import mods.defeatedcrow.api.recipe.IPanRecipe;
import mods.defeatedcrow.api.recipe.IPanRecipeRegister;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PanRecipeRegister implements IPanRecipeRegister {

	private static List<PanRecipe> recipes;
	@Deprecated
	private static List<ItemStack> heatSource;
	private static List<PanHeatSource> sources;

	public PanRecipeRegister() {
		this.recipes = new ArrayList<PanRecipe>();
		this.heatSource = new ArrayList<ItemStack>();
		this.sources = new ArrayList<PanHeatSource>();
	}

	@Override
	public List<PanRecipe> getRecipeList() {
		return this.recipes;
	}

	@Override
	public List<ItemStack> getHeatSourceList() {
		return this.heatSource;
	}

	@Override
	public List<PanHeatSource> getHeatSourcesList() {
		return this.sources;
	}

	public IPanRecipeRegister instance() {
		return RecipeRegisterManager.panRecipe;
	}

	@Override
	public PanRecipe getRecipe(ItemStack item) {
		if (item == null)
			return null;
		for (PanRecipe recipe : this.recipes) {
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
		for (PanHeatSource source : this.sources) {
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
	public void register(ItemStack input, ItemStack output, String tex, String disp) {
		if (input != null) {
			register(input, output, output, tex, disp);
		}
	}

	@Override
	public void register(ItemStack input, ItemStack output, ItemStack output2, String tex, String disp) {
		if (input != null) {
			this.recipes.add(new PanRecipe(input, output, output2, tex, disp));
		}
		AMTLogger.debugInfo("Add Pan Recipe: input " + input.getDisplayName() + ", output " + output.getDisplayName());
	}

	@Override
	public void registerHeatSource(Block block, int meta) {
		if (block != null) {
			this.sources.add(new PanHeatSource(block, meta));
			AMTLogger.debugInfo("Add pan heat source : " + block.getLocalizedName() + ":" + meta);
		}
	}

	public class PanRecipe implements IPanRecipe {

		private final ItemStack input;
		private final ItemStack output;
		private final ItemStack outputJP;
		private final String Tex;
		private final String display;

		public PanRecipe(ItemStack inputItem, ItemStack outputItem, ItemStack jpItem, String tex, String disp) {
			this.input = inputItem;
			this.output = outputItem;
			this.outputJP = jpItem;
			this.Tex = tex;
			this.display = disp;
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
		public ItemStack getOutputJP() {
			if (this.outputJP == null)
				return null;
			return this.outputJP.copy();
		}

		@Override
		public String getTex() {
			if (this.Tex == null)
				return "rice";
			return this.Tex;
		}

		@Override
		public String getDisplayName() {
			if (this.display == null)
				return "Rice";
			return this.display;
		}
	}

	public class PanHeatSource implements ICookingHeatSource {

		private final Block block;
		private final int metadata;

		public PanHeatSource(Block b, int m) {
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
