package mods.defeatedcrow.plugin.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class ChocoRecipeHandler extends TemplateRecipeHandler {

	private HashMap<Object, ItemStack> recipes = new HashMap<Object, ItemStack>();

	private HashMap<Object, ItemStack> recipeLoader() {
		if (!RecipeRegisterManager.chocoRecipe.getRecipeList().isEmpty()) {
			for (Entry<Object, ItemStack> entry : RecipeRegisterManager.chocoRecipe.getRecipeList().entrySet()) {
				if (entry != null && entry.getKey() != null && entry.getValue() != null)
					this.recipes.put(entry.getKey(), entry.getValue());
			}
		}
		return this.recipes;
	}

	public class RecipeCacher extends CachedRecipe {

		public ArrayList<PositionedStack> input;
		public PositionedStack result;

		public RecipeCacher() {
			input = new ArrayList<PositionedStack>();
		}

		public RecipeCacher(ItemStack out) {
			this();
			if (out != null)
				this.result = new PositionedStack(out, 102, 21);
		}

		public RecipeCacher(List<ItemStack> in, ItemStack out) {
			this(out);
			setInput(in);
		}

		public void setInput(List<ItemStack> items) {
			input.clear();
			PositionedStack stack = new PositionedStack(items, 48, 21);
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
		return "DCsChocolate";
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(65, 25, 20, 20), "DCsChocolate"));
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("DCsChocolate") && getClass() == ChocoRecipeHandler.class) {
			Map<Object, ItemStack> recipes = this.recipeLoader();

			if (recipes == null || recipes.isEmpty())
				return;

			for (Entry<Object, ItemStack> entry : recipes.entrySet()) {
				Object in = entry.getKey();
				ItemStack out = entry.getValue();

				List<ItemStack> inputs = new ArrayList<ItemStack>();
				if (in instanceof String) {
					String s = (String) in;
					List<ItemStack> items = OreDictionary.getOres(s);
					if (items != null && !items.isEmpty())
						inputs.addAll(items);
				} else if (in instanceof ItemStack) {
					inputs.add((ItemStack) in);
				}

				if (!inputs.isEmpty())
					arecipes.add(new RecipeCacher(inputs, out));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		Map<Object, ItemStack> recipes = this.recipeLoader();

		if (recipes == null || recipes.isEmpty())
			return;

		for (Entry<Object, ItemStack> entry : recipes.entrySet()) {
			Object in = entry.getKey();
			ItemStack out = entry.getValue();

			List<ItemStack> inputs = new ArrayList<ItemStack>();
			if (in instanceof String) {
				String s = (String) in;
				List<ItemStack> items = OreDictionary.getOres(s);
				if (items != null && !items.isEmpty())
					inputs.addAll(items);
			} else if (in instanceof ItemStack) {
				inputs.add((ItemStack) in);
			}

			if (!inputs.isEmpty() && out != null && NEIServerUtils.areStacksSameType(out, result)) {
				arecipes.add(new RecipeCacher(inputs, out));
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		Map<Object, ItemStack> recipes = this.recipeLoader();

		if (recipes == null || recipes.isEmpty())
			return;

		for (Entry<Object, ItemStack> entry : recipes.entrySet()) {
			Object in = entry.getKey();
			ItemStack out = entry.getValue();

			List<ItemStack> inputs = new ArrayList<ItemStack>();
			if (in instanceof String) {

				String s = (String) in;
				List<ItemStack> items = OreDictionary.getOres(s);
				if (items != null && !items.isEmpty())
					inputs.addAll(items);
			} else if (in instanceof ItemStack) {
				inputs.add((ItemStack) in);
			}

			boolean flag = this.contain(inputs, ingredient);

			if (flag && !inputs.isEmpty()) {
				RecipeCacher cache = new RecipeCacher(inputs, out);
				cache.setIngredientPermutation(cache.input, ingredient);
				arecipes.add(cache);
			} else if (!inputs.isEmpty() && ingredient.getItem() == Item.getItemFromBlock(DCsAppleMilk.filledSoupPan)) {
				arecipes.add(new RecipeCacher(inputs, out));
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
		return StatCollector.translateToLocal("dc.ChocoGuiNEI");
	}

	@Override
	public String getGuiTexture() {
		return "defeatedcrow:textures/gui/dummygui.png";
	}

}
