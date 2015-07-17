package mods.defeatedcrow.plugin.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.recipe.TeaRecipeRegister.TeaRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
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
		private PositionedStack maker;
		private PositionedStack cup;

		public recipeCacher(ItemStack in, ItemStack out) {
			in.stackSize = 1;
			this.input = new PositionedStack(in, 40, 8);
			this.result = new PositionedStack(out, 118, 33);
			this.maker = new PositionedStack(new ItemStack(DCsAppleMilk.teaMakerNext, 1, 0), 77, 33);
			this.cup = new PositionedStack(new ItemStack(DCsAppleMilk.emptyCup, 1, 0), 98, 18);
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
			stacks.add(this.maker);
			stacks.add(this.cup);
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
		return "DCsTeaMaker";
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(62, 8, 20, 20), "DCsTeaMaker"));
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
