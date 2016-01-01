package mods.defeatedcrow.plugin.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.client.gui.GuiAdvProcessor;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.recipe.ProcessorRecipeRegister.ProcessorRecipe;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class AdvProcessorRecipeHandler extends TemplateRecipeHandler {

	public int[][] stackorder = new int[][] {
			{
					0,
					0 },
			{
					1,
					0 },
			{
					0,
					1 },
			{
					1,
					1 },
			{
					0,
					2 },
			{
					1,
					2 },
			{
					2,
					0 },
			{
					2,
					1 },
			{
					2,
					2 } };

	private List<ProcessorRecipe> recipes;

	private List<ProcessorRecipe> recipeLoader() {
		if (RecipeRegisterManager.processorRecipe.getRecipes() != null
				&& !RecipeRegisterManager.processorRecipe.getRecipes().isEmpty()) {
			this.recipes = (List<ProcessorRecipe>) RecipeRegisterManager.processorRecipe.getRecipes();
		}
		return this.recipes;
	}

	public class RecipeCacher extends CachedRecipe {

		public ArrayList<PositionedStack> input;
		public PositionedStack result;
		public PositionedStack leave;
		public PositionedStack plate;

		public RecipeCacher() {
			input = new ArrayList<PositionedStack>();
		}

		public RecipeCacher(ItemStack[] out) {
			this();
			if (out[0] != null) {
				this.result = new PositionedStack(out[0], 113, 32);
			}

			if (out[1] != null) {
				this.leave = new PositionedStack(out[1], 140, 32);
			}
		}

		public RecipeCacher(List<?> in, ItemStack[] out, int tier) {
			this(out);
			setInput(in);
			ItemStack jawplate = null;
			switch (tier) {
			case -1:
				jawplate = new ItemStack(DCsAppleMilk.jawPlate, 1, 0);
				break;
			case 1:
				jawplate = new ItemStack(DCsAppleMilk.jawPlate, 1, 1);
				break;
			case 2:
				jawplate = new ItemStack(DCsAppleMilk.jawPlate, 1, 3);
				break;
			case 3:
				jawplate = new ItemStack(DCsAppleMilk.jawPlate, 1, 5);
				break;
			default:
				break;
			}
			if (jawplate != null)
				this.plate = new PositionedStack(jawplate, 86, 5);
		}

		public void setInput(List<?> items) {
			input.clear();
			for (int ingred = 0; ingred < items.size(); ingred++) {
				PositionedStack stack = new PositionedStack(items.get(ingred), 28 + stackorder[ingred][0] * 18,
						5 + stackorder[ingred][1] * 18);
				stack.setMaxSize(1);
				input.add(stack);
			}
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
		public List<PositionedStack> getOtherStacks() {
			ArrayList<PositionedStack> stacks = new ArrayList<PositionedStack>();
			if (leave != null)
				stacks.add(leave);
			if (plate != null)
				stacks.add(plate);
			return stacks;
		}

	}

	public PositionedStack getResult() {
		return null;
	}

	@Override
	public Class<? extends GuiContainer> getGuiClass() {
		return GuiAdvProcessor.class;
	}

	@Override
	public String getOverlayIdentifier() {
		return "DCsAdvProcessor";
	}

	@Override
	public void loadTransferRects() {
		transferRects
				.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(90, 38, 20, 20), "DCsAdvProcessor"));
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("DCsAdvProcessor") && getClass() == AdvProcessorRecipeHandler.class) {
			List<ProcessorRecipe> recipes = this.recipeLoader();

			if (recipes == null || recipes.isEmpty())
				return;
			for (ProcessorRecipe recipe : recipes) {
				List<Object> in = recipe.getProcessedInput();
				boolean flag = true;
				for (Object ret : in) {
					if (ret instanceof List) {
						List<ItemStack> items = (List<ItemStack>) ret;
						if (items.isEmpty())
							flag = false;
					}
				}

				ItemStack[] out = new ItemStack[] {
						recipe.getOutput(),
						recipe.getSecondary() };
				if (flag)
					arecipes.add(new RecipeCacher(in, out, recipe.getRecipeTier()));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {

		List<ProcessorRecipe> recipes = this.recipeLoader();

		if (recipes == null || recipes.isEmpty())
			return;
		for (ProcessorRecipe recipe : recipes) {
			List<Object> in = recipe.getProcessedInput();
			// input中にnullがないかチェックする。鉱石辞書レシピ用。
			boolean flag = true;
			for (Object ret : in) {
				if (ret instanceof List) {
					List<ItemStack> items = (List<ItemStack>) ret;
					if (items.isEmpty())
						flag = false;
				}
			}
			ItemStack[] out = new ItemStack[] {
					recipe.getOutput(),
					recipe.getSecondary() };
			if (flag && out[0] != null && NEIServerUtils.areStacksSameType(out[0], result)) {
				arecipes.add(new RecipeCacher(in, out, recipe.getRecipeTier()));
			}
			if (flag && out[1] != null && NEIServerUtils.areStacksSameType(out[1], result)) {
				arecipes.add(new RecipeCacher(in, out, recipe.getRecipeTier()));
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {

		List<ProcessorRecipe> recipes = this.recipeLoader();

		if (recipes == null || recipes.isEmpty() || ingredient == null)
			return;
		for (ProcessorRecipe recipe : recipes) {
			if (recipe == null)
				continue;

			List<Object> in = recipe.getProcessedInput();
			ItemStack[] out = new ItemStack[] {
					recipe.getOutput(),
					recipe.getSecondary() };
			int tier = recipe.getRecipeTier();

			boolean flag = this.contain(in, ingredient);

			if (flag) {
				RecipeCacher cache = new RecipeCacher(in, out, tier);
				cache.setIngredientPermutation(cache.input, ingredient);
				arecipes.add(cache);
			}
		}
	}

	private boolean contain(List<Object> in, ItemStack check) {
		boolean flag1 = false;
		if (check == null)
			return false;

		for (Object ret : in) {
			if (ret == null)
				continue;

			if (ret instanceof ItemStack) {
				ItemStack input = (ItemStack) ret;
				if (NEIServerUtils.areStacksSameType(input, check))
					flag1 = true;
			} else if (ret instanceof List) {
				List<ItemStack> items = (List<ItemStack>) ret;
				if (items.isEmpty())
					continue;
				for (ItemStack item : items) {
					if (NEIServerUtils.areStacksSameType(item, check))
						flag1 = true;
				}
			}
		}

		return flag1;
	}

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("dc.JawCrusherNEI");
	}

	@Override
	public String getGuiTexture() {
		return "defeatedcrow:textures/gui/jawcrushergui_nei.png";
	}

	@Override
	public void drawExtras(int recipe) {
		drawProgressBar(83, 32, 176, 0, 24, 16, 32, 0);
	}

}
