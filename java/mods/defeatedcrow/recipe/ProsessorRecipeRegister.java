package mods.defeatedcrow.recipe;

import java.util.ArrayList;
import java.util.List;

import buildcraft.api.core.IInvSlot;
import buildcraft.core.inventory.InventoryIterator;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
import mods.defeatedcrow.api.recipe.*;

/**
 * This code was in reference to the AssemblyRecipeManager.class that was created by BuildCraft Team.
 * */
public class ProsessorRecipeRegister implements IProsessorRecipeRegister{

	private static List<ProsessorRecipe> recipes;
	
	public ProsessorRecipeRegister()
	{
		this.recipes = new ArrayList<ProsessorRecipe>();
	}
	
	public IProsessorRecipeRegister instance()
	{
		return RecipeRegisterManager.prosessorRecipe;
	}
	
	@Override
	public void adRecipe(ItemStack output, Object... input) {
		recipes.add(new ProsessorRecipe(output, input));
	}

	@Override
	public List<? extends IProsessorRecipe> getRecipes() {
		return this.recipes;
	}
	
	public class ProsessorRecipe implements IProsessorRecipe{
		
		public final ItemStack output;
		private final Object[] input;
		private final Object[] processedInput;
		
		public ProsessorRecipe(ItemStack output, Object... inputs)
		{
			this.output = output;
			this.input = inputs;
			this.processedInput = new Object[inputs.length];
			for (int i = 0; i < inputs.length; i++) {
				if (inputs[i] instanceof String) {
					processedInput[i] = OreDictionary.getOres((String) inputs[i]);
				} else if (inputs[i] instanceof ItemStack) {
					processedInput[i] = inputs[i];
				} else if (inputs[i] instanceof Integer) {
					processedInput[i] = inputs[i];
				} else {
					throw new IllegalArgumentException("Unknown Object passed to recipe!");
				}
			}
			
		}
		
		@Override
		public Object[] getInput() {
			return this.processedInput;
		}

		@Override
		public ItemStack getOutput() {
			return this.output;
		}
		
		public boolean canBeDone(List<ItemStack> items) {
			for (int i = 0; i < processedInput.length; i++) {
				if (processedInput[i] == null) {
					continue;
				}

				if (processedInput[i] instanceof ItemStack) {
					ItemStack requirement = (ItemStack) processedInput[i];
					int found = 0; // Amount of ingredient found in inventory
					int expected = requirement.stackSize;
					for (ItemStack item : items) {
						if (item == null) {
							continue;
						}

						if (item.isItemEqual(requirement)) {
							found += item.stackSize; // Adds quantity of stack to amount found
						}

						if (found >= expected) {
							break;
						}
					}

					// Return false if the amount of ingredient found
					// is not enough
					if (found < expected) {
						return false;
					}
				} else if (processedInput[i] instanceof List) {
					List<ItemStack> oreList = (List<ItemStack>) processedInput[i];
					int found = 0; // Amount of ingredient found in inventory
					//BCの元ソースでは、[i++ +1]になっている部分。要検証
					int expected = 1;
					if(processedInput[i + 1] instanceof Integer){
						expected = (Integer)processedInput[i + 1];
					}
					for (ItemStack item : items) {
						if (item == null) {
							continue;
						}
						for (ItemStack oreItem : oreList) {
							if (OreDictionary.itemMatches(oreItem, item, true)) {
								found += item.stackSize;
								break;
							}
						}
						if (found >= expected) {
							break;
						}
					}

					// Return false if the amount of ingredient found
					// is not enough
					if (found < expected) {
						return false;
					}
				}
			}
			return true;
		}
	}
}
