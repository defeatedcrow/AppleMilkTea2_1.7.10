package mods.defeatedcrow.plugin.nei;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import mods.defeatedcrow.recipe.RegisteredRecipeGet;
import mods.defeatedcrow.client.gui.GuiIceMaker;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class IceRecipeHandler extends TemplateRecipeHandler {
	
	private HashMap<ItemStack,ItemStack[]> iceRecipe;
	
	private HashMap<ItemStack, ItemStack[]> recipeLoader() {
		if (RegisteredRecipeGet.iceRecipeList != null && !RegisteredRecipeGet.iceRecipeList.isEmpty()) {
			this.iceRecipe = RegisteredRecipeGet.iceRecipeList;
		}
		return this.iceRecipe;
	}
	
	public class recipeCacher extends CachedRecipe {
		
		private PositionedStack input;
		private PositionedStack result;
		private PositionedStack leave;

		public recipeCacher(ItemStack in, ItemStack[] out) {
			in.stackSize = 1;
			this.input = new PositionedStack(in, 51, 6);
			this.result= new PositionedStack(out[0], 107, 24);
			if (out[1] != null)
			{
				this.leave = new PositionedStack(out[1], 135, 24);
			}
			
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
		
		public PositionedStack getOtherStack()
        {
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
	    transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(65, 25, 20, 20), "DCsIceMaker"));
	}
	
	@Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if(outputId.equals("DCsIceMaker"))
        {
            HashMap<ItemStack, ItemStack[]> recipes = (HashMap<ItemStack, ItemStack[]>) this.recipeLoader();

            if(recipes == null || recipes.isEmpty())return;
            for(Entry<ItemStack, ItemStack[]> recipe : recipes.entrySet())
            {
                ItemStack[] items = recipe.getValue();
                ItemStack in = recipe.getKey();
                arecipes.add(new recipeCacher(in,items));
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

		HashMap<ItemStack, ItemStack[]> recipes = (HashMap<ItemStack, ItemStack[]>) this.recipeLoader();

        if(recipes == null || recipes.isEmpty())return;
        for(Entry<ItemStack, ItemStack[]> recipe : recipes.entrySet())
        {
        	ItemStack[] items = recipe.getValue();
            ItemStack in = recipe.getKey();
            if(NEIServerUtils.areStacksSameType(items[0], result))
            {
                arecipes.add(new recipeCacher(in, items));
            }
        }
    }
	
	@Override
    public void loadUsageRecipes(ItemStack ingredient)
    {

		HashMap<ItemStack, ItemStack[]> recipes = (HashMap<ItemStack, ItemStack[]>) this.recipeLoader();

        if(recipes == null || recipes.isEmpty())return;
        for(Entry<ItemStack, ItemStack[]> recipe : recipes.entrySet())
        {
        	ItemStack[] items = recipe.getValue();
            ItemStack in = recipe.getKey();
            if(ingredient.getItem() == in.getItem() && ingredient.getItemDamage() == in.getItemDamage())
            {
                arecipes.add(new recipeCacher(ingredient, items));
            }
        }
    }

	@Override
	public String getRecipeName() {
		return "Ice Maker";
	}

	@Override
	public String getGuiTexture() {
		return "defeatedcrow:textures/gui/icemakergui.png";
	}
	
	@Override
    public void drawExtras(int recipe)
    {
        drawProgressBar(52, 24, 176, 0, 14, 14, 32, 3);
        drawProgressBar(74, 24, 176, 14, 24, 16, 48, 0);
    }

}
