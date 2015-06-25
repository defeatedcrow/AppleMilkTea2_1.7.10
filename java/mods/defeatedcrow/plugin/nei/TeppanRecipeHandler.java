package mods.defeatedcrow.plugin.nei;

import java.awt.Rectangle;
import java.util.List;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.recipe.PlateRecipeRegister.PlateRecipe;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class TeppanRecipeHandler extends TemplateRecipeHandler {

	private List<PlateRecipe> recipes;

	private List<PlateRecipe> recipeLoader() {
		if (RecipeRegisterManager.plateRecipe.getRecipeList() != null
				&& !RecipeRegisterManager.plateRecipe.getRecipeList().isEmpty()) {
			this.recipes = (List<PlateRecipe>) RecipeRegisterManager.plateRecipe.getRecipeList();
		}
		return this.recipes;
	}

	public class TeppanRecipeCacher extends CachedRecipe {

		private PositionedStack input;
		private PositionedStack result;
		private PositionedStack dummy;

		public TeppanRecipeCacher(ItemStack in, ItemStack out, boolean b) {
			in.stackSize = 1;
			this.input = new PositionedStack(in, 48, 21);
			this.result = new PositionedStack(out, 102, 21);
			if (b) {
				this.dummy = new PositionedStack(new ItemStack(DCsAppleMilk.dummyTeppan, 1, 1), 74, 37);
			} else {
				this.dummy = new PositionedStack(new ItemStack(DCsAppleMilk.dummyTeppan, 1, 0), 74, 37);
			}
		}

		@Override
		public PositionedStack getResult() {
			return this.result;
		}

		@Override
		public PositionedStack getIngredient() {
			return this.input;
		}

		@Override
		public PositionedStack getOtherStack() {
			return this.dummy;
		}

	}

	public PositionedStack getResult() {
		return null;
	}

	@Override
	public Class<? extends GuiContainer> getGuiClass() {
		return GuiRecipe.class;
	}

	@Override
	public String getOverlayIdentifier() {
		return "DCsTeppan";
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(72, 15, 20, 20), "DCsTeppan"));
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("DCsTeppan") && getClass() == TeppanRecipeHandler.class) {
			List<PlateRecipe> recipes = this.recipeLoader();

			if (recipes == null || recipes.isEmpty())
				return;
			for (PlateRecipe recipe : this.recipes) {
				ItemStack items = recipe.getOutput();
				ItemStack in = recipe.getInput();
				boolean b = recipe.useOvenRecipe();
				arecipes.add(new TeppanRecipeCacher(in, items, b));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		List<PlateRecipe> recipes = this.recipeLoader();

		if (recipes == null || recipes.isEmpty())
			return;
		for (PlateRecipe recipe : this.recipes) {
			ItemStack items = recipe.getOutput();
			ItemStack in = recipe.getInput();
			boolean b = recipe.useOvenRecipe();
			if (NEIServerUtils.areStacksSameType(items, result)) {
				arecipes.add(new TeppanRecipeCacher(in, items, b));
			}

		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {

		List<PlateRecipe> recipes = this.recipeLoader();

		if (recipes == null || recipes.isEmpty())
			return;
		for (PlateRecipe recipe : this.recipes) {
			ItemStack items = recipe.getOutput();
			ItemStack in = recipe.getInput();
			boolean b = recipe.useOvenRecipe();
			if (ingredient.getItem() == in.getItem() && ingredient.getItemDamage() == in.getItemDamage()) {
				arecipes.add(new TeppanRecipeCacher(ingredient, items, b));
			} else if (ingredient.getItem() == Item.getItemFromBlock(DCsAppleMilk.teppanII)) {
				arecipes.add(new TeppanRecipeCacher(in, items, b));
			} else if (ingredient.getItem() == DCsAppleMilk.dummyTeppan) {
				arecipes.add(new TeppanRecipeCacher(in, items, b));
			}
		}
	}

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("dc.PlateGuiNameNEI");
	}

	@Override
	public String getGuiTexture() {
		return "defeatedcrow:textures/gui/dummygui.png";
	}
}
