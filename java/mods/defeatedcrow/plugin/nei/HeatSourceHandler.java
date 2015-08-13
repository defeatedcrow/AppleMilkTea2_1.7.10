package mods.defeatedcrow.plugin.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.api.recipe.ICookingHeatSource;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class HeatSourceHandler extends TemplateRecipeHandler {

	private List<ICookingHeatSource> sources;

	private List<ICookingHeatSource> recipeLoader() {
		sources = new ArrayList<ICookingHeatSource>();
		List<ICookingHeatSource> list = new ArrayList<ICookingHeatSource>();
		if (RecipeRegisterManager.plateRecipe.getHeatSourcesList() != null
				&& !RecipeRegisterManager.plateRecipe.getHeatSourcesList().isEmpty()) {
			list.addAll((List<ICookingHeatSource>) RecipeRegisterManager.plateRecipe.getHeatSourcesList());
		}
		if (RecipeRegisterManager.panRecipe.getHeatSourcesList() != null
				&& !RecipeRegisterManager.panRecipe.getHeatSourcesList().isEmpty()) {

			list.addAll((List<ICookingHeatSource>) RecipeRegisterManager.panRecipe.getHeatSourcesList());
		}

		if (!list.isEmpty()) {
			for (ICookingHeatSource c : list) {
				boolean b = true;
				for (ICookingHeatSource t : sources) {
					if (t.getBlock() == null)
						continue;
					if (t.getBlock() == c.getBlock() && t.getMetadata() == c.getMetadata()) {
						b = false;
					}
				}
				if (b) {
					sources.add(c);
				}
			}
		}

		return this.sources;
	}

	public class HeatRecipeCacher extends CachedRecipe {

		private PositionedStack input;
		private PositionedStack result1;
		private PositionedStack result2;
		private final ItemStack panItem = new ItemStack(DCsAppleMilk.emptyPanGaiden);
		private final ItemStack plateItem = new ItemStack(DCsAppleMilk.teppanII);

		public HeatRecipeCacher(ItemStack in, boolean plate, boolean pan) {
			in.stackSize = 1;
			this.input = new PositionedStack(in, 75, 37);
			if (pan) {
				if (plate) {
					this.result1 = new PositionedStack(panItem, 65, 12);
					this.result2 = new PositionedStack(plateItem, 85, 12);
				} else {
					this.result1 = new PositionedStack(panItem, 75, 12);
					this.result2 = null;
				}
			} else {
				if (plate) {
					this.result1 = new PositionedStack(plateItem, 75, 12);
					this.result2 = null;
				} else {
					this.result1 = null;
					this.result2 = null;
				}
			}
		}

		@Override
		public PositionedStack getResult() {
			return this.result1;
		}

		@Override
		public PositionedStack getIngredient() {
			return this.input;
		}

		@Override
		public PositionedStack getOtherStack() {
			return this.result2;
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
		return "DCsHeatSource";
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(72, 27, 20, 10), "DCsHeatSource"));
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("DCsHeatSource") && getClass() == HeatSourceHandler.class) {
			List<ICookingHeatSource> recipes = this.recipeLoader();

			if (recipes == null || recipes.isEmpty())
				return;
			for (ICookingHeatSource recipe : recipes) {
				Block block = recipe.getBlock();
				int m = recipe.getMetadata();
				boolean a = RecipeRegisterManager.plateRecipe.isHeatSource(block, m);
				boolean b = RecipeRegisterManager.panRecipe.isHeatSource(block, m);
				arecipes.add(new HeatRecipeCacher(new ItemStack(block, 1, m), a, b));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		// List<ICookingHeatSource> recipes = this.recipeLoader();
		//
		// if (recipes == null || recipes.isEmpty())
		// return;
		// for (ICookingHeatSource recipe : recipes) {
		// Block block = recipe.getBlock();
		// int m = recipe.getMetadata();
		// ItemStack item = new ItemStack(block, 1, m);
		// boolean a = RecipeRegisterManager.plateRecipe.isHeatSource(block, m);
		// boolean b = RecipeRegisterManager.panRecipe.isHeatSource(block, m);
		// if (NEIServerUtils.areStacksSameType(item, result)) {
		// arecipes.add(new HeatRecipeCacher(item, a, b));
		// }
		// }
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		List<ICookingHeatSource> recipes = this.recipeLoader();

		if (recipes == null || recipes.isEmpty())
			return;
		for (ICookingHeatSource recipe : recipes) {
			Block block = recipe.getBlock();
			int m = recipe.getMetadata();
			ItemStack item = new ItemStack(block, 1, m);
			boolean a = RecipeRegisterManager.plateRecipe.isHeatSource(block, m);
			boolean b = RecipeRegisterManager.panRecipe.isHeatSource(block, m);
			if (ingredient.getItem() == item.getItem() && ingredient.getItemDamage() == item.getItemDamage()) {
				arecipes.add(new HeatRecipeCacher(ingredient, a, b));
			} else if (ingredient.getItem() == Item.getItemFromBlock(DCsAppleMilk.teppanII)) {
				arecipes.add(new HeatRecipeCacher(item, a, b));
			} else if (ingredient.getItem() == Item.getItemFromBlock(DCsAppleMilk.emptyPanGaiden)) {
				arecipes.add(new HeatRecipeCacher(item, a, b));
			}
		}
	}

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("dc.HeatSourceGuiNEI");
	}

	@Override
	public String getGuiTexture() {
		return "defeatedcrow:textures/gui/dummygui_2.png";
	}
}
