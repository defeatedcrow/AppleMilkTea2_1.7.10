package mods.defeatedcrow.recipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mods.defeatedcrow.api.appliance.IJawPlate;
import mods.defeatedcrow.api.appliance.IProcessorPanel;
import mods.defeatedcrow.api.recipe.IProcessorRecipe;
import mods.defeatedcrow.api.recipe.IProcessorRecipeRegister;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ProcessorRecipeRegister implements IProcessorRecipeRegister {

	private static List<ProcessorRecipe> recipes;

	public ProcessorRecipeRegister() {
		this.recipes = new ArrayList<ProcessorRecipe>();
	}

	public IProcessorRecipeRegister instance() {
		return RecipeRegisterManager.processorRecipe;
	}

	@Override
	public void addRecipe(ItemStack output, boolean isFood, int tier, boolean forceReturn, ItemStack secondary,
			float secondaryChance, Object... input) {
		float c = MathHelper.clamp_float(0.0F, secondaryChance, 1.0F);
		if (output == null || output.stackSize == 0)
			output = null;
		if (secondary == null || secondary.stackSize == 0)
			secondary = null;
		recipes.add(new ProcessorRecipe(output, secondary, isFood, forceReturn, tier, secondaryChance, input));
		AMTLogger.debugInfo("Add Prosessor recipe: output " + (output == null ? "null" : output.getDisplayName())
				+ ", tier" + tier);
	}

	@Override
	public void addRecipe(ItemStack output, boolean flag, int tier, ItemStack secondary, float secondaryChance,
			Object... input) {
		addRecipe(output, flag, tier, false, secondary, secondaryChance, input);
	}

	@Override
	public void addRecipe(ItemStack output, boolean flag, int tier, ItemStack secondary, Object... input) {
		addRecipe(output, flag, tier, false, secondary, 1.0F, input);
	}

	@Override
	public void addRecipe(ItemStack output, boolean isFood, boolean forceReturn, ItemStack secondary,
			float secondaryChance, Object... input) {
		addRecipe(output, isFood, 3, false, secondary, secondaryChance, input);
	}

	@Override
	public void addRecipe(ItemStack output, boolean flag, ItemStack secondary, float secondaryChance, Object... input) {
		addRecipe(output, flag, 3, false, secondary, secondaryChance, input);
	}

	@Override
	public void addRecipe(ItemStack output, boolean flag, ItemStack secondary, Object... input) {
		addRecipe(output, flag, 3, false, secondary, 1.0F, input);
	}

	@Override
	public List<ProcessorRecipe> getRecipes() {
		return this.recipes;
	}

	public class ProcessorRecipe implements IProcessorRecipe {

		public final ItemStack output;
		public final ItemStack secondary;
		private final Object[] input;
		private final ArrayList<Object> processedInput;
		private final float chance;
		private final boolean forceContainer;
		private final int tier;

		public final boolean foodRecipe;

		public ProcessorRecipe(ItemStack output, ItemStack sec, boolean flag, boolean flag2, int t,
				float secondaryChance, Object... inputs) {
			this.output = output;
			this.input = inputs;
			this.secondary = sec;
			this.foodRecipe = flag;
			this.forceContainer = flag2;
			this.chance = secondaryChance;
			this.processedInput = new ArrayList<Object>();
			if (foodRecipe) {
				tier = -1;
			} else {
				// if (PropertyHandler.procDifficulty() == 0)
				// tier = 0;
				// else if (PropertyHandler.procDifficulty() == 2)
				// tier = 3;
				// else
				tier = t;
			}
			for (int i = 0; i < inputs.length; i++) {
				if (inputs[i] instanceof String) {
					processedInput.add(OreDictionary.getOres((String) inputs[i]));
				} else if (inputs[i] instanceof ItemStack) {
					processedInput.add(((ItemStack) inputs[i]).copy());
				} else if (inputs[i] instanceof Item) {
					processedInput.add(new ItemStack((Item) inputs[i], 1, 0));
				} else if (inputs[i] instanceof Block) {
					processedInput.add(new ItemStack((Block) inputs[i], 1, 0));
				} else {
					throw new IllegalArgumentException("Unknown Object passed to recipe!");
				}
			}
		}

		@Override
		public Object[] getInput() {
			return this.input;
		}

		@Override
		public ItemStack getOutput() {
			return this.output == null ? null : this.output.copy();
		}

		@Override
		public float getChance() {
			return this.chance;
		}

		@Override
		public ItemStack getSecondary() {
			if (this.secondary != null) {
				return this.secondary.copy();
			} else {
				return null;
			}
		}

		@Override
		public ItemStack getContainerItem(List<ItemStack> items) {
			ItemStack cont = null;
			for (int i = 0; i < items.size(); i++) {
				ItemStack next = items.get(i);
				if (next != null) {
					cont = next.getItem().getContainerItem(next);
					if (cont != null && cont.getItem() != next.getItem()) {
						break;
					} else {
						cont = FluidContainerRegistry.drainFluidContainer(next);
						if (cont != null) {
							break;
						}
					}
				}
			}

			return cont == null ? null : cont;
		}

		@Override
		public boolean isFoodRecipe() {
			return this.foodRecipe;
		}

		@Override
		public boolean forceReturnContainer() {
			return this.forceContainer;
		}

		@Override
		public List<Object> getProcessedInput() {
			return new ArrayList<Object>(this.processedInput);
		}

		@Override
		public int getRecipeSize() {
			return this.processedInput.size();
		}

		@Override
		public boolean matches(List<ItemStack> items) {
			ArrayList<Object> required = new ArrayList<Object>(this.processedInput);
			boolean food = this.isFoodRecipe();

			for (int x = 0; x < items.size(); x++) {
				ItemStack slot = items.get(x);

				if (slot != null) {
					boolean inRecipe = false;
					Iterator<Object> req = required.iterator();

					if (slot.getItem() instanceof IProcessorPanel) {
						inRecipe = true;
						continue;
					}

					while (req.hasNext()) {
						boolean match = false;

						Object next = req.next();

						if (next instanceof ItemStack) {
							match = OreDictionary.itemMatches((ItemStack) next, slot, false);
						} else if (next instanceof ArrayList) {
							Iterator<ItemStack> itr = ((ArrayList<ItemStack>) next).iterator();
							while (itr.hasNext() && !match) {
								match = OreDictionary.itemMatches(itr.next(), slot, false);
							}
						}

						if (match) {
							inRecipe = true;
							required.remove(next);
							break;
						}
					}

					if (!inRecipe) {
						return false;
					}
				}
			}
			return required.isEmpty();
		}

		@Override
		public int getRecipeTier() {
			return tier;
		}

		@Override
		public boolean matchTier(ItemStack item) {
			int tier = this.getRecipeTier();
			if (item == null || item.getItem() == null) {
				return tier == 0;
			} else if (item.getItem() instanceof IJawPlate) {
				IJawPlate tool = (IJawPlate) item.getItem();
				int toolTier = tool.getTier(item);
				return toolTier >= tier;
			}
			return false;
		}
	}
}
