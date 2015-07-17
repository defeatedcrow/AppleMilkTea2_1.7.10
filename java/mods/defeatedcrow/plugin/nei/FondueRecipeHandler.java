package mods.defeatedcrow.plugin.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.api.appliance.SoupType;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.recipe.FondueRecipeRegister.FondueRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class FondueRecipeHandler extends TemplateRecipeHandler {

	private List<FondueRecipe> fondueRecipes;

	private List<FondueRecipe> recipeLoader() {
		fondueRecipes = new ArrayList<FondueRecipe>();
		if (RecipeRegisterManager.fondueRecipe.getRecipeList() != null
				&& !RecipeRegisterManager.fondueRecipe.getRecipeList().isEmpty()) {
			this.fondueRecipes = (List<FondueRecipe>) RecipeRegisterManager.fondueRecipe.getRecipeList();
		}
		return fondueRecipes;
	}

	public class RecipeCacher extends CachedRecipe {

		public ArrayList<PositionedStack> input;
		private PositionedStack result;
		private PositionedStack type;

		public RecipeCacher() {
			input = new ArrayList<PositionedStack>();
		}

		public RecipeCacher(List<ItemStack> input, SoupType bef, ItemStack res) {
			this();
			setInput(input);
			ItemStack item = null;
			if (bef != SoupType.EMPTY) {
				item = new ItemStack(DCsAppleMilk.filledSoupPan, 1, bef.id);
			} else {
				item = new ItemStack(DCsAppleMilk.emptyPanGaiden, 1, 0);
			}
			if (res != null)
				this.result = new PositionedStack(res, 118, 33);
			if (item != null)
				this.type = new PositionedStack(item, 77, 33);
		}

		public void setInput(List<ItemStack> items) {
			input.clear();
			PositionedStack stack = new PositionedStack(items, 40, 8);
			stack.setMaxSize(1);
			input.add(stack);
		}

		@Override
		public PositionedStack getResult() {
			return this.result;
		}

		@Override
		public List<PositionedStack> getIngredients() {
			return getCycledIngredients(cycleticks / 20, input);
		}

		@Override
		public PositionedStack getOtherStack() {
			return this.type;
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
		return "DCsFondueRecipe";
	}

	@Override
	public void loadTransferRects() {
		transferRects
				.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(62, 8, 20, 20), "DCsFondueRecipe"));
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("DCsFondueRecipe") && getClass() == FondueRecipeHandler.class) {
			List<FondueRecipe> recipes = this.recipeLoader();

			if (recipes == null || recipes.isEmpty())
				return;

			for (FondueRecipe recipe : recipes) {
				SoupType bef = recipe.getType();
				ItemStack out = recipe.getOutput();

				List<ItemStack> inputs = new ArrayList<ItemStack>();
				inputs.addAll(recipe.getProcessedInput());

				if (!inputs.isEmpty() && out != null)
					arecipes.add(new RecipeCacher(inputs, bef, out));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		List<FondueRecipe> recipes = this.recipeLoader();

		if (recipes == null || recipes.isEmpty())
			return;

		for (FondueRecipe recipe : recipes) {
			SoupType bef = recipe.getType();
			ItemStack out = recipe.getOutput();

			List<ItemStack> inputs = new ArrayList<ItemStack>();
			inputs.addAll(recipe.getProcessedInput());

			if (!inputs.isEmpty() && out != null && NEIServerUtils.areStacksSameType(out, result)) {
				arecipes.add(new RecipeCacher(inputs, bef, out));
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		List<FondueRecipe> recipes = this.recipeLoader();

		if (recipes == null || recipes.isEmpty())
			return;

		for (FondueRecipe recipe : recipes) {
			SoupType bef = recipe.getType();
			ItemStack out = recipe.getOutput();

			List<ItemStack> inputs = new ArrayList<ItemStack>();
			inputs.addAll(recipe.getProcessedInput());

			boolean flag = this.contain(inputs, ingredient);

			if (flag && !inputs.isEmpty()) {
				RecipeCacher cache = new RecipeCacher(inputs, bef, out);
				cache.setIngredientPermutation(cache.input, ingredient);
				arecipes.add(cache);
			} else if (!inputs.isEmpty() && ingredient.getItem() == Item.getItemFromBlock(DCsAppleMilk.filledSoupPan)
					&& ingredient.getItemDamage() == bef.id) {
				arecipes.add(new RecipeCacher(inputs, bef, out));
			} else if (!inputs.isEmpty() && ingredient.getItem() == Item.getItemFromBlock(DCsAppleMilk.emptyPanGaiden)
					&& bef.id == 0) {
				arecipes.add(new RecipeCacher(inputs, bef, out));
			}
		}
	}

	private boolean contain(List<ItemStack> inputs, ItemStack check) {
		boolean flag1 = false;
		if (check == null)
			return false;

		for (ItemStack ret : inputs) {
			if (ret == null)
				continue;
			if (NEIServerUtils.areStacksSameType(ret, check))
				flag1 = true;
		}

		return flag1;
	}

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("dc.FondueRecipeGuiNEI");
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
		mc.fontRenderer.drawString(I18n.format(d, new Object[0]), 82, 13, 0x000000);
	}
}
