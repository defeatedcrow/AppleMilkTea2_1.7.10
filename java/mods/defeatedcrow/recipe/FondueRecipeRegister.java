package mods.defeatedcrow.recipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mods.defeatedcrow.api.appliance.SoupType;
import mods.defeatedcrow.api.recipe.IFondueRecipe;
import mods.defeatedcrow.api.recipe.IFondueRegister;
import mods.defeatedcrow.api.recipe.IFondueSource;
import mods.defeatedcrow.common.AMTLogger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class FondueRecipeRegister implements IFondueRegister {

	private List<FondueRecipe> recipes;
	private List<FondueSource> sources;

	public FondueRecipeRegister() {
		this.recipes = new ArrayList<FondueRecipe>();
		this.sources = new ArrayList<FondueSource>();
	}

	@Override
	public void register(ItemStack input, ItemStack output, SoupType type) {
		if (output != null && input != null && type != null) {
			recipes.add(new FondueRecipe(input, output, type));
			AMTLogger.debugInfo("Add Fondue recipe: output " + output.getDisplayName());
		}
	}

	@Override
	public void registerByOre(String input, ItemStack output, SoupType type) {
		if (output != null && input != null && type != null) {
			recipes.add(new FondueRecipe(input, output, type));
			AMTLogger.debugInfo("Add Fondue recipe: output " + output.getDisplayName());
		}
	}

	@Override
	public void registerSource(Object input, SoupType before, SoupType result) {
		if (result != null && input != null && before != null) {
			sources.add(new FondueSource(input, before, result));
			AMTLogger.debugInfo("Add Fondue source: result " + result.display);
		}
	}

	@Override
	public List<? extends IFondueRecipe> getRecipeList() {
		return this.recipes;
	}

	@Override
	public List<? extends IFondueSource> getSourceList() {
		return this.sources;
	}

	@Override
	public IFondueRecipe getRecipe(ItemStack input, SoupType type) {
		if (recipes.isEmpty()) {
			return null;
		} else {
			for (IFondueRecipe recipe : recipes) {
				if (recipe.matches(input) && recipe.getType() == type) {
					return recipe;
				}
			}
			return null;
		}
	}

	@Override
	public IFondueSource getType(ItemStack input) {
		if (sources.isEmpty()) {
			return null;
		} else {
			for (IFondueSource ret : sources) {
				if (ret.matches(input)) {
					return ret;
				}
			}
			return null;
		}
	}

	public class FondueRecipe implements IFondueRecipe {

		private final Object input;
		private final ItemStack output;
		private SoupType type;
		private ArrayList<ItemStack> processedInput;

		public FondueRecipe(Object in, ItemStack out, SoupType t) {
			this.input = in;
			this.output = out;
			this.type = t;
			processedInput = new ArrayList<ItemStack>();
			if (input instanceof String) {
				processedInput.addAll(OreDictionary.getOres((String) input));
			} else if (input instanceof ItemStack) {
				processedInput.add(((ItemStack) input).copy());
			} else if (input instanceof Item) {
				processedInput.add(new ItemStack((Item) input, 1, 0));
			} else if (input instanceof Block) {
				processedInput.add(new ItemStack((Block) input, 1, 0));
			} else {
				throw new IllegalArgumentException("Unknown Object passed to recipe!");
			}
		}

		@Override
		public Object getInput() {
			return this.input;
		}

		@Override
		public ArrayList<ItemStack> getProcessedInput() {
			return this.processedInput;
		}

		@Override
		public ItemStack getOutput() {
			return this.output == null ? null : this.output.copy();
		}

		@Override
		public SoupType getType() {
			return this.type;
		}

		@Override
		public boolean matches(ItemStack item) {
			ArrayList<ItemStack> required = new ArrayList<ItemStack>(this.processedInput);
			if (item != null && item.getItem() != null && !required.isEmpty()) {
				Iterator<ItemStack> itr = required.iterator();
				boolean match = false;
				while (itr.hasNext() && !match) {
					match = OreDictionary.itemMatches(itr.next(), item, false);
				}
				return match;
			}
			return false;
		}

	}

	public class FondueSource implements IFondueSource {

		private final Object input;
		private final SoupType result;
		private final SoupType before;
		private ArrayList<ItemStack> processedInput;

		public FondueSource(Object in, SoupType bef, SoupType ret) {
			this.input = in;
			this.result = ret;
			this.before = bef;
			processedInput = new ArrayList<ItemStack>();
			if (input instanceof String) {
				processedInput.addAll(OreDictionary.getOres((String) input));
			} else if (input instanceof ItemStack) {
				processedInput.add(((ItemStack) input).copy());
			} else if (input instanceof Item) {
				processedInput.add(new ItemStack((Item) input, 1, 0));
			} else if (input instanceof Block) {
				processedInput.add(new ItemStack((Block) input, 1, 0));
			} else {
				throw new IllegalArgumentException("Unknown Object passed to recipe!");
			}
		}

		@Override
		public Object getInput() {
			return this.input;
		}

		@Override
		public ArrayList<ItemStack> getProcessedInput() {
			return this.processedInput;
		}

		@Override
		public SoupType beforeType() {
			return this.before;
		}

		@Override
		public SoupType afterType() {
			return this.result;
		}

		@Override
		public boolean matches(ItemStack item) {
			ArrayList<ItemStack> required = new ArrayList<ItemStack>(this.processedInput);
			if (item != null && item.getItem() != null && !required.isEmpty()) {
				Iterator<ItemStack> itr = required.iterator();
				boolean match = false;
				while (itr.hasNext() && !match) {
					match = OreDictionary.itemMatches(itr.next(), item, false);
				}
				return match;
			}
			return false;
		}

	}

}
