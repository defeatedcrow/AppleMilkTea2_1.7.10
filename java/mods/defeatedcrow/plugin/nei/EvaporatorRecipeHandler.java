package mods.defeatedcrow.plugin.nei;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;

import mods.defeatedcrow.recipe.EvaporatorRecipeRegister.EvaporatorRecipe;
import mods.defeatedcrow.recipe.RegisteredRecipeGet;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.client.gui.GuiIceMaker;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class EvaporatorRecipeHandler extends TemplateRecipeHandler {
	
	private List<EvaporatorRecipe> recipes;
	
	private List<EvaporatorRecipe> recipeLoader() {
		if (RecipeRegisterManager.evaporatorRecipe.getRecipeList() != null && !RecipeRegisterManager.evaporatorRecipe.getRecipeList().isEmpty()) {
			this.recipes = (List<EvaporatorRecipe>) RecipeRegisterManager.evaporatorRecipe.getRecipeList();
		}
		return this.recipes;
	}
	
	public class EvaporatorRecipeCacher extends CachedRecipe {
		
		private PositionedStack input;
		private PositionedStack result;
		private PositionedStack leave;
		private PositionedStack dummy;
		private FluidStack fluid = null;

		public EvaporatorRecipeCacher(ItemStack in, ItemStack out, ItemStack sec, FluidStack f) {
			this.input = new PositionedStack(in, 51, 6);
			if (out != null)
			{
				this.result= new PositionedStack(out, 106, 10);
			}
			if (sec != null)
			{
				this.leave = new PositionedStack(in, 51, 24);
			}
			if (f != null)
			{
				this.fluid = f;
				ItemStack dummyForFluid = new ItemStack(DCsAppleMilk.dummyItem, 1, 0);
				String name = f.getFluid().getLocalizedName(f);
				short amount = (short) f.amount;
				
				NBTTagCompound tag = new NBTTagCompound();
				tag.setString("fluid", name);
				tag.setShort("amount", amount);
				dummyForFluid.setTagCompound(tag);
				
				this.dummy = new PositionedStack(dummyForFluid, 106, 37);
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
		
		@Override
		public PositionedStack getOtherStack()
        {
            return this.dummy;
        }
		
		public FluidStack getFluid()
		{
			return this.fluid;
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
	  return "DCsEvaporator";
	}
	
	@Override
	public void loadTransferRects() {
	    transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(70, 10, 20, 20), "DCsEvaporator"));
	}
	
	@Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if(outputId.equals("DCsEvaporator") && getClass() == EvaporatorRecipeHandler.class)
        {
            List<EvaporatorRecipe> recipes = this.recipeLoader();

            if(recipes == null || recipes.isEmpty())return;
            for(EvaporatorRecipe recipe : this.recipes)
            {
                ItemStack items = recipe.getOutput();
                ItemStack in = recipe.getInput();
                FluidStack f = recipe.getSecondary();
                arecipes.add(new EvaporatorRecipeCacher(in, items, null, f));
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
		List<EvaporatorRecipe> recipes = this.recipeLoader();

        if(recipes == null || recipes.isEmpty())return;
        for(EvaporatorRecipe recipe : this.recipes)
        {
            ItemStack items = recipe.getOutput();
            ItemStack in = recipe.getInput();
            FluidStack f = recipe.getSecondary();
            if(NEIServerUtils.areStacksSameType(items, result))
            {
                arecipes.add(new EvaporatorRecipeCacher(in, items, null, f));
            }
        }
    }
	
	@Override
    public void loadUsageRecipes(ItemStack ingredient)
    {

		List<EvaporatorRecipe> recipes = this.recipeLoader();

        if(recipes == null || recipes.isEmpty())return;
        for(EvaporatorRecipe recipe : this.recipes)
        {
            ItemStack items = recipe.getOutput();
            ItemStack in = recipe.getInput();
            FluidStack f = recipe.getSecondary();
            if(ingredient.getItem() == in.getItem() && ingredient.getItemDamage() == in.getItemDamage())
            {
                arecipes.add(new EvaporatorRecipeCacher(in, items, null, f));
            }
        }
    }

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("dc.EvaporatorNEI");
	}

	@Override
	public String getGuiTexture() {
		return "defeatedcrow:textures/gui/evaporatorgui_nei.png";
	}
	
	@Override
    public void drawExtras(int recipe)
    {
        drawProgressBar(72, 7, 176, 0, 24, 16, 50, 0);
        drawProgressBar(52, 26, 201, 0, 15, 16, 50, 1);
        
        List<EvaporatorRecipe> recipes = this.recipeLoader();
        if(recipes == null || recipes.isEmpty())return;
        
        if(arecipes.get(recipe) instanceof EvaporatorRecipeCacher)
        {
        	EvaporatorRecipeCacher current = (EvaporatorRecipeCacher) arecipes.get(recipe);
        	FluidStack get = current.fluid;
        	drawFluid(get, 16, 106, 37, 16, 16);
        }
    }
	
	private void drawFluid(FluidStack fluid, int level, int x, int y, int width, int height) {
		if (fluid == null || fluid.getFluid() == null) {
			return;
		}
		IIcon icon = fluid.getFluid().getIcon(fluid);
		if (icon == null)return;
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		setGLColorFromInt(fluid.getFluid().getColor(fluid));
		int fullX = width / 16;
		int fullY = height / 16;
		int lastX = width - fullX * 16;
		int lastY = height - fullY * 16;
		int fullLvl = (height - level) / 16;
		int lastLvl = (height - level) - fullLvl * 16;
		for (int i = 0; i < fullX; i++) {
			for (int j = 0; j < fullY; j++) {
				if (j >= fullLvl) {
					drawCutIcon(icon, x + i * 16, y + j * 16, 16, 16, j == fullLvl ? lastLvl : 0);
				}
			}
		}
		for (int i = 0; i < fullX; i++) {
			drawCutIcon(icon, x + i * 16, y + fullY * 16, 16, lastY, fullLvl == fullY ? lastLvl : 0);
		}
		for (int i = 0; i < fullY; i++) {
			if (i >= fullLvl) {
				drawCutIcon(icon, x + fullX * 16, y + i * 16, lastX, 16, i == fullLvl ? lastLvl : 0);
			}
		}
		drawCutIcon(icon, x + fullX * 16, y + fullY * 16, lastX, lastY, fullLvl == fullY ? lastLvl : 0);
	}
	
	public static void setGLColorFromInt(int color) {
		float red = (color >> 16 & 255) / 255.0F;
		float green = (color >> 8 & 255) / 255.0F;
		float blue = (color & 255) / 255.0F;
		GL11.glColor4f(red, green, blue, 1.0F);
	}

	/**
	 * Original code was made by SpaceToad and the BuildCraft Team. 
	 * */
	private void drawCutIcon(IIcon icon, int x, int y, int width, int height, int cut) {
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		tess.addVertexWithUV(x, y + height, 0.0F, icon.getMinU(), icon.getInterpolatedV(height));
		tess.addVertexWithUV(x + width, y + height, 0.0F, icon.getInterpolatedU(width), icon.getInterpolatedV(height));
		tess.addVertexWithUV(x + width, y + cut, 0.0F, icon.getInterpolatedU(width), icon.getInterpolatedV(cut));
		tess.addVertexWithUV(x, y + cut, 0.0F, icon.getMinU(), icon.getInterpolatedV(cut));
		tess.draw();
	}

}
