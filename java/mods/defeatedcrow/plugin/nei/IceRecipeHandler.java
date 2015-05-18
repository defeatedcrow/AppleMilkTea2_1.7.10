package mods.defeatedcrow.plugin.nei;

import java.awt.Rectangle;
import java.util.List;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.client.gui.GuiIceMaker;
import mods.defeatedcrow.recipe.IceRecipeRegister.IceRecipe;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class IceRecipeHandler extends TemplateRecipeHandler {

	private List<IceRecipe> iceRecipes;

	private List<IceRecipe> recipeLoader() {
		if (RecipeRegisterManager.iceRecipe.getRecipeList() != null
				&& !RecipeRegisterManager.iceRecipe.getRecipeList().isEmpty()) {
			this.iceRecipes = (List<IceRecipe>) RecipeRegisterManager.iceRecipe.getRecipeList();
		}
		return iceRecipes;
	}

	public class recipeCacher extends CachedRecipe {

		private PositionedStack input;
		private PositionedStack result;
		private PositionedStack leave;

		public recipeCacher(ItemStack in, ItemStack[] out) {
			// in.stackSize = 1;
			this.input = new PositionedStack(in, 51, 6);
			this.result = new PositionedStack(out[0], 107, 24);
			if (out[1] != null) {
				this.leave = new PositionedStack(out[1], 135, 24);
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
			return this.leave;
		}

	}

	public PositionedStack getResult() {
		return null;
	}

	@Override
	public Class<? extends GuiContainer> getGuiClass() {
		return GuiIceMaker.class;
	}

	@Override
	public String getOverlayIdentifier() {
		return "DCsIceMaker";
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(75, 30, 20, 20), "DCsIceMaker"));
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("DCsIceMaker") && getClass() == IceRecipeHandler.class) {
			List<IceRecipe> recipes = (List<IceRecipe>) this.recipeLoader();

			if (recipes == null || recipes.isEmpty())
				return;
			for (IceRecipe recipe : recipes) {
				ItemStack[] items = { recipe.getOutput(), recipe.getContainer() };
				ItemStack in = recipe.getInput();
				arecipes.add(new recipeCacher(in, items));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		List<IceRecipe> recipes = (List<IceRecipe>) this.recipeLoader();

		if (recipes == null || recipes.isEmpty())
			return;
		for (IceRecipe recipe : recipes) {
			ItemStack[] items = { recipe.getOutput(), recipe.getContainer() };
			ItemStack in = recipe.getInput();
			if (NEIServerUtils.areStacksSameType(items[0], result)) {
				arecipes.add(new recipeCacher(in, items));
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		List<IceRecipe> recipes = (List<IceRecipe>) this.recipeLoader();

		if (recipes == null || recipes.isEmpty())
			return;
		for (IceRecipe recipe : recipes) {
			ItemStack[] items = { recipe.getOutput(), recipe.getContainer() };
			ItemStack in = recipe.getInput();
			if (ingredient.getItem() == in.getItem() && ingredient.getItemDamage() == in.getItemDamage()) {
				ItemStack ing2 = new ItemStack(ingredient.getItem(), in.stackSize, ingredient.getItemDamage());
				arecipes.add(new recipeCacher(ing2, items));
			}
		}
	}

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("dc.IceMakerNEI");
	}

	@Override
	public String getGuiTexture() {
		return "defeatedcrow:textures/gui/icemakergui.png";
	}

	@Override
	public void drawExtras(int recipe) {
		drawProgressBar(52, 24, 176, 0, 14, 14, 32, 3);
		drawProgressBar(74, 24, 176, 14, 24, 16, 48, 0);
	}

}
