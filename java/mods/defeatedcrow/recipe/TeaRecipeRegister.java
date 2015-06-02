package mods.defeatedcrow.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import mods.defeatedcrow.api.recipe.ITeaRecipe;
import mods.defeatedcrow.api.recipe.ITeaRecipeRegister;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.event.DispenserEvent;

public class TeaRecipeRegister implements ITeaRecipeRegister {

	private static List<TeaRecipe> recipes;

	public TeaRecipeRegister() {
		this.recipes = new ArrayList<TeaRecipe>();
	}

	@Override
	public List<TeaRecipe> getRecipeList() {
		return this.recipes;
	}

	public ITeaRecipeRegister instance() {
		return RecipeRegisterManager.teaRecipe;
	}

	@Override
	public TeaRecipe getRecipe(ItemStack item) {
		if (item == null)
			return null;
		for (TeaRecipe recipe : this.recipes) {
			if (this.isItemEqual(item, recipe.getInput())) {
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
			} else if (a.getItemDamage() == Short.MAX_VALUE) {
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public void register(ItemStack input, ItemStack output, String tex) {
		if (input != null) {
			registerCanMilk(input, output, null, tex, tex);
		}
	}

	@Override
	public void registerCanMilk(ItemStack input, ItemStack output, ItemStack output2, String tex) {
		if (input != null) {
			registerCanMilk(input, output, output2, tex, tex);
		}
	}

	@Override
	public void registerCanMilk(ItemStack input, ItemStack output, ItemStack output2, String tex, String milktex) {
		if (input != null) {
			this.recipes.add(new TeaRecipe(input, output, output2, tex, milktex));
			DispenserEvent.instance.registerTeaMakerEvent(input);
		}
		String milkName = "null";
		if (output2 != null)
			milkName = output2.getDisplayName();
		AMTLogger.debugInfo("Add TeaMaker Recipe: input " + input.getDisplayName() + ", output "
				+ output.getDisplayName() + ", output_milk " + milkName);
	}

	public class TeaRecipe implements ITeaRecipe {

		private final ItemStack input;
		private final ItemStack output;
		private final ItemStack outputMilk;
		private final String Tex;
		private final String Milk_Tex;

		public TeaRecipe(ItemStack inputItem, ItemStack outputItem, ItemStack milkItem, String tex, String milkTex) {
			this.input = inputItem;
			this.output = outputItem;
			this.outputMilk = milkItem;
			this.Tex = tex;
			this.Milk_Tex = milkTex;
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
		public ItemStack getOutputMilk() {
			if (this.outputMilk == null)
				return null;
			return this.outputMilk.copy();
		}

		@Override
		public String getTex() {
			if (this.Tex == null)
				return "defeatedcrow:textures/blocks/contents_water.png";
			return this.Tex;
		}

		@Override
		public String getMilkTex() {
			if (this.Milk_Tex == null) {
				return this.Tex != null ? this.Tex : "defeatedcrow:textures/blocks/contents_water.png";
			}
			return this.Milk_Tex;
		}

	}

}
