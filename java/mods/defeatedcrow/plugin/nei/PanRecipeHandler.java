package mods.defeatedcrow.plugin.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.recipe.PanRecipeRegister.PanRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class PanRecipeHandler extends TemplateRecipeHandler {

	private List<PanRecipe> panRecipes;

	private List<PanRecipe> recipeLoader() {
		if (RecipeRegisterManager.panRecipe.getRecipeList() != null
				&& !RecipeRegisterManager.panRecipe.getRecipeList().isEmpty()) {
			this.panRecipes = (List<PanRecipe>) RecipeRegisterManager.panRecipe.getRecipeList();
		}
		return panRecipes;
	}

	public class RecipeCatcher extends CachedRecipe {

		private PositionedStack input;
		private PositionedStack result;
		private PositionedStack pan;
		private PositionedStack bowl;

		public RecipeCatcher(ItemStack in, ItemStack out) {
			in.stackSize = 1;
			this.input = new PositionedStack(in, 40, 8);
			this.result = new PositionedStack(out, 118, 33);
			this.pan = new PositionedStack(new ItemStack(DCsAppleMilk.emptyPanGaiden, 1, 0), 77, 33);
			this.bowl = new PositionedStack(new ItemStack(Items.bowl, 1, 0), 98, 18);
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
		public List<PositionedStack> getOtherStacks() {
			ArrayList<PositionedStack> stacks = new ArrayList<PositionedStack>();
			stacks.add(this.pan);
			stacks.add(this.bowl);
			return stacks;
		}

	}

	public PositionedStack getResult() {
		return null;
	}

	@Override
	public Class<? extends GuiContainer> getGuiClass() {
		return GuiAppliance.class;
	}

	@Override
	public String getOverlayIdentifier() {
		return "DCsFilledPan";
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(62, 8, 20, 20), "DCsFilledPan"));
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("DCsFilledPan") && getClass() == PanRecipeHandler.class) {
			List<PanRecipe> recipes = (List<PanRecipe>) this.recipeLoader();

			if (recipes == null || recipes.isEmpty())
				return;
			for (PanRecipe recipe : recipes) {
				ItemStack item = recipe.getOutput();
				ItemStack in = recipe.getInput();
				arecipes.add(new RecipeCatcher(in, item));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {

		List<PanRecipe> recipes = (List<PanRecipe>) this.recipeLoader();

		if (recipes == null || recipes.isEmpty())
			return;
		for (PanRecipe recipe : recipes) {
			ItemStack item = recipe.getOutput();
			ItemStack in = recipe.getInput();
			if (NEIServerUtils.areStacksSameType(item, result)) {
				arecipes.add(new RecipeCatcher(in, item));
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {

		List<PanRecipe> recipes = (List<PanRecipe>) this.recipeLoader();

		if (recipes == null || recipes.isEmpty())
			return;
		for (PanRecipe recipe : recipes) {
			ItemStack item = recipe.getOutput();
			ItemStack in = recipe.getInput();
			if (ingredient.getItem() == in.getItem() && ingredient.getItemDamage() == in.getItemDamage()) {
				arecipes.add(new RecipeCatcher(ingredient, item));
			} else if (ingredient.getItem() == Item.getItemFromBlock(DCsAppleMilk.emptyPanGaiden)) {
				arecipes.add(new RecipeCatcher(in, item));
			}
		}
	}

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("dc.PanGuiNameNEI");
	}

	@Override
	public String getGuiTexture() {
		return "defeatedcrow:textures/gui/appliancegui_nei.png";
	}

	@Override
	public void drawExtras(int recipe) {
		// message
		Minecraft mc = Minecraft.getMinecraft();
		String d = "Use by Right-Click";
		mc.fontRenderer.drawString(I18n.format(d, new Object[0]), 4, 32, 0x000000);
	}

}
