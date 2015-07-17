package mods.defeatedcrow.plugin.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.api.appliance.SoupType;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.recipe.FondueRecipeRegister.FondueSource;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class FondueSourceHandler extends TemplateRecipeHandler {

	private List<FondueSource> fondueSources;

	private List<FondueSource> recipeLoader() {
		if (RecipeRegisterManager.fondueRecipe.getSourceList() != null
				&& !RecipeRegisterManager.fondueRecipe.getSourceList().isEmpty()) {
			this.fondueSources = (List<FondueSource>) RecipeRegisterManager.fondueRecipe.getSourceList();
		}
		return fondueSources;
	}

	public class RecipeCacher extends CachedRecipe {

		public ArrayList<PositionedStack> input;
		private PositionedStack result;
		private PositionedStack type;

		public RecipeCacher() {
			input = new ArrayList<PositionedStack>();
		}

		public RecipeCacher(List<ItemStack> input, SoupType bef, SoupType res) {
			this();
			setInput(input);
			ItemStack item = null;
			ItemStack item2 = null;
			if (bef != SoupType.EMPTY) {
				item = new ItemStack(DCsAppleMilk.filledSoupPan, 1, bef.id);
			} else {
				item = new ItemStack(DCsAppleMilk.emptyPanGaiden, 1, 0);
			}
			if (res != SoupType.EMPTY) {
				item2 = new ItemStack(DCsAppleMilk.filledSoupPan, 1, res.id);
			} else {
				item2 = new ItemStack(DCsAppleMilk.emptyPanGaiden, 1, 0);
			}
			if (item2 != null)
				this.result = new PositionedStack(item2, 118, 33);
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
		return "DCsFondueSource";
	}

	@Override
	public void loadTransferRects() {
		transferRects
				.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(62, 8, 20, 20), "DCsFondueSource"));
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("DCsFondueSource") && getClass() == FondueSourceHandler.class) {
			List<FondueSource> recipes = this.recipeLoader();

			if (recipes == null || recipes.isEmpty())
				return;

			for (FondueSource recipe : recipes) {
				SoupType bef = recipe.beforeType();
				SoupType out = recipe.afterType();

				List<ItemStack> inputs = new ArrayList<ItemStack>();
				inputs.addAll(recipe.getProcessedInput());

				if (!inputs.isEmpty())
					arecipes.add(new RecipeCacher(inputs, bef, out));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		List<FondueSource> recipes = this.recipeLoader();

		if (recipes == null || recipes.isEmpty())
			return;

		for (FondueSource recipe : recipes) {
			SoupType bef = recipe.beforeType();
			SoupType out = recipe.afterType();

			List<ItemStack> inputs = new ArrayList<ItemStack>();
			inputs.addAll(recipe.getProcessedInput());

			ItemStack check = new ItemStack(DCsAppleMilk.filledSoupPan, 1, out.id);
			if (!inputs.isEmpty() && check != null && NEIServerUtils.areStacksSameType(check, result)) {
				arecipes.add(new RecipeCacher(inputs, bef, out));
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		List<FondueSource> recipes = this.recipeLoader();

		if (recipes == null || recipes.isEmpty())
			return;

		for (FondueSource recipe : recipes) {
			SoupType bef = recipe.beforeType();
			SoupType out = recipe.afterType();

			List<ItemStack> inputs = new ArrayList<ItemStack>();
			inputs.addAll(recipe.getProcessedInput());

			boolean flag = this.contain(inputs, ingredient);

			if (flag && !inputs.isEmpty()) {
				RecipeCacher cache = new RecipeCacher(inputs, bef, out);
				cache.setIngredientPermutation(cache.input, ingredient);
				arecipes.add(cache);
			} else if (!inputs.isEmpty() && ingredient.getItem() == Item.getItemFromBlock(DCsAppleMilk.filledSoupPan)) {
				arecipes.add(new RecipeCacher(inputs, bef, out));
			} else if (!inputs.isEmpty() && ingredient.getItem() == Item.getItemFromBlock(DCsAppleMilk.emptyPanGaiden)) {
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
		return StatCollector.translateToLocal("dc.FondueSourceGuiNEI");
	}

	@Override
	public String getGuiTexture() {
		return "defeatedcrow:textures/gui/appliancegui_nei.png";
	}

	@Override
	public void drawExtras(int recipe) {
		// message
		Minecraft mc = Minecraft.getMinecraft();
		String d = "Throw into the pan";
		mc.fontRenderer.drawString(I18n.format(d, new Object[0]), 82, 13, 0x000000);
	}
}
