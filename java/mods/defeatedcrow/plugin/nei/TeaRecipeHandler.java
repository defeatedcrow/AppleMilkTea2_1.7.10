package mods.defeatedcrow.plugin.nei;

import java.awt.Rectangle;
import java.util.List;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.recipe.TeaRecipeRegister.TeaRecipe;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class TeaRecipeHandler extends TemplateRecipeHandler {

	private List<TeaRecipe> teaRecipes;

	private List<TeaRecipe> recipeLoader() {
		if (RecipeRegisterManager.teaRecipe.getRecipeList() != null
				&& !RecipeRegisterManager.teaRecipe.getRecipeList().isEmpty()) {
			this.teaRecipes = (List<TeaRecipe>) RecipeRegisterManager.teaRecipe.getRecipeList();
		}
		return teaRecipes;
	}

	public class recipeCacher extends CachedRecipe {

		private PositionedStack input;
		private PositionedStack result;

		public recipeCacher(ItemStack in, ItemStack out) {
			in.stackSize = 1;
			this.input = new PositionedStack(in, 48, 21);
			this.result = new PositionedStack(out, 102, 21);
		}

		@Override
		public PositionedStack getResult() {
			return this.result;
		}

		@Override
		public PositionedStack getIngredient() {
			return this.input;
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
		return "DCsTeaMaker";
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(65, 25, 20, 20), "DCsTeaMaker"));
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("DCsTeaMaker") && getClass() == TeaRecipeHandler.class) {
			List<TeaRecipe> recipes = (List<TeaRecipe>) this.recipeLoader();

			if (recipes == null || recipes.isEmpty())
				return;
			for (TeaRecipe recipe : recipes) {
				ItemStack item = recipe.getOutput();
				ItemStack in = recipe.getInput();
				arecipes.add(new recipeCacher(in, item));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {

		List<TeaRecipe> recipes = (List<TeaRecipe>) this.recipeLoader();

		if (recipes == null || recipes.isEmpty())
			return;
		for (TeaRecipe recipe : recipes) {
			ItemStack item = recipe.getOutput();
			ItemStack in = recipe.getInput();
			if (NEIServerUtils.areStacksSameType(item, result)) {
				arecipes.add(new recipeCacher(in, item));
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		List<TeaRecipe> recipes = (List<TeaRecipe>) this.recipeLoader();

		if (recipes == null || recipes.isEmpty())
			return;
		for (TeaRecipe recipe : recipes) {
			ItemStack item = recipe.getOutput();
			ItemStack in = recipe.getInput();
			if (ingredient.getItem() == in.getItem() && ingredient.getItemDamage() == in.getItemDamage()) {
				arecipes.add(new recipeCacher(ingredient, item));
			} else if (ingredient.getItem() == Item.getItemFromBlock(DCsAppleMilk.teaMakerNext)
					|| ingredient.getItem() == Item.getItemFromBlock(DCsAppleMilk.teaMakerBlack)) {
				arecipes.add(new recipeCacher(in, item));
			}
		}
	}

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("dc.MakerGuiNEI");
	}

	@Override
	public String getGuiTexture() {
		return "defeatedcrow:textures/gui/dummygui.png";
	}

}
