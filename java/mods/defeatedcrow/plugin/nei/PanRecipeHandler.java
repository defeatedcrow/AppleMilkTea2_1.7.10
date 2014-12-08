package mods.defeatedcrow.plugin.nei;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.plugin.nei.TeaRecipeHandler.recipeCacher;
import mods.defeatedcrow.recipe.RegisteredRecipeGet;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class PanRecipeHandler extends TemplateRecipeHandler {
	
	private HashMap<ItemStack, ItemStack> panRecipe;
	
	private HashMap<ItemStack, ItemStack> recipeLoader() {
		if (RegisteredRecipeGet.panRecipeList != null && !RegisteredRecipeGet.panRecipeList.isEmpty()) {
			this.panRecipe = RegisteredRecipeGet.panRecipeList;
		}
		return this.panRecipe;
	}
	
	public class recipeCacher extends CachedRecipe {
		
		private PositionedStack input;
		private PositionedStack result;

		public recipeCacher(ItemStack in, ItemStack out) {
			in.stackSize = 1;
			this.input = new PositionedStack(in, 48, 21);
			this.result= new PositionedStack(out, 102, 21);
		}

		@Override
		public PositionedStack getResult() {
			return this.result;
		}
		
		@Override
		public PositionedStack getIngredient()
        {
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
	  return "DCsFilledPan";
	}
	
	@Override
	public void loadTransferRects() {
	    transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(65, 25, 20, 20), "DCsFilledPan"));
	}
	
	@Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if(outputId.equals("DCsFilledPan") && getClass() == PanRecipeHandler.class)
        {
            HashMap<ItemStack, ItemStack> recipes = (HashMap<ItemStack, ItemStack>) this.recipeLoader();

            if(recipes == null || recipes.isEmpty())return;
            for(Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
            {
                ItemStack item = recipe.getValue();
                ItemStack in = recipe.getKey();
                arecipes.add(new recipeCacher(in, item));
            }
        }
        else
        {
            super.loadCraftingRecipes(outputId, results);
        }
    }
	
	@Override
    public void loadCraftingRecipes(ItemStack result)
    {

		HashMap<ItemStack, ItemStack> recipes = (HashMap<ItemStack, ItemStack>) this.recipeLoader();

        if(recipes == null || recipes.isEmpty())return;
        for(Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
        {
            ItemStack item = recipe.getValue();
            ItemStack in = recipe.getKey();
            if(NEIServerUtils.areStacksSameType(item, result))
            {
                arecipes.add(new recipeCacher(in, item));
            }
        }
    }
	
	@Override
    public void loadUsageRecipes(ItemStack ingredient)
    {

		HashMap<ItemStack, ItemStack> recipes = (HashMap<ItemStack, ItemStack>) this.recipeLoader();

        if(recipes == null || recipes.isEmpty())return;
        for(Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
        {
            ItemStack item = recipe.getValue();
            ItemStack in = recipe.getKey();
            if(ingredient.getItem() == in.getItem() && ingredient.getItemDamage() == in.getItemDamage())
            {
                arecipes.add(new recipeCacher(ingredient, item));
            }
            else if (ingredient.getItem() == Item.getItemFromBlock(DCsAppleMilk.emptyPanGaiden)){
            	arecipes.add(new recipeCacher(in, item));
            }
        }
    }

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("dc.PanGuiNameNEI");
	}

	@Override
	public String getGuiTexture() {
		return "defeatedcrow:textures/gui/dummygui.png";
	}

}
