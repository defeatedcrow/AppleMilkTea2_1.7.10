package mods.defeatedcrow.plugin.nei;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.recipe.BrewingRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class BrewingRecipeHandler extends TemplateRecipeHandler {

	private HashMap<Fluid, Fluid> recipes = new HashMap<Fluid, Fluid>();

	private HashMap<Fluid, Fluid> recipeLoader() {
		if (!BrewingRecipe.recipe.isEmpty()) {
			for (Entry<Fluid, Fluid> entry : BrewingRecipe.recipe.entrySet()) {
				if (entry != null && entry.getKey() != null && entry.getValue() != null)
					this.recipes.put(entry.getKey(), entry.getValue());
			}
		}
		return this.recipes;
	}

	public class BrewRecipeCacher extends CachedRecipe {

		public PositionedStack input;
		public PositionedStack result;
		private FluidStack influid = null;
		private FluidStack resfluid = null;

		public BrewRecipeCacher(Fluid in, Fluid out) {
			if (in != null) {
				this.influid = new FluidStack(in, 1000);
				ItemStack dummyForFluid = new ItemStack(DCsAppleMilk.dummyItem, 1, 0);
				String name = in.getLocalizedName(influid);
				short amount = 1000;
				NBTTagCompound tag = new NBTTagCompound();
				tag.setString("fluid", name);
				tag.setShort("amount", amount);
				dummyForFluid.setTagCompound(tag);
				this.input = new PositionedStack(dummyForFluid, 48, 21);

			}
			if (out != null) {
				this.resfluid = new FluidStack(out, 1000);
				ItemStack dummyForFluid2 = new ItemStack(DCsAppleMilk.dummyItem, 1, 0);
				String name2 = out.getLocalizedName(resfluid);
				short amount2 = 1000;
				NBTTagCompound tag2 = new NBTTagCompound();
				tag2.setString("fluid", name2);
				tag2.setShort("amount", amount2);
				dummyForFluid2.setTagCompound(tag2);
				this.result = new PositionedStack(dummyForFluid2, 102, 21);
			}
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
		public PositionedStack getOtherStack() {
			return null;
		}

		public FluidStack getInFluid() {
			return this.influid;
		}

		public FluidStack getResFluid() {
			return this.resfluid;
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
		return "DCsBrewing";
	}

	@Override
	public void loadTransferRects() {
		transferRects.add(new BrewingRecipeHandler.RecipeTransferRect(new Rectangle(65, 25, 20, 20), "DCsBrewing"));
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("DCsBrewing") && getClass() == BrewingRecipeHandler.class) {
			Map<Fluid, Fluid> recipes = this.recipeLoader();

			if (recipes == null || recipes.isEmpty())
				return;

			for (Entry<Fluid, Fluid> entry : recipes.entrySet()) {
				Fluid in = entry.getKey();
				Fluid out = entry.getValue();

				if (in != null && out != null) {
					arecipes.add(new BrewRecipeCacher(in, out));
				}
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		Map<Fluid, Fluid> recipes = this.recipeLoader();

		if (recipes == null || recipes.isEmpty() || result == null)
			return;

		for (Entry<Fluid, Fluid> entry : recipes.entrySet()) {
			Fluid in = entry.getKey();
			Fluid out = entry.getValue();
			boolean flag = this.contain(out, result);

			if (!flag && result.getItem() == DCsAppleMilk.dummyItem) {
				NBTTagCompound tag = result.getTagCompound();
				if (tag != null && tag.hasKey("fluid")) {
					String id = tag.getString("fluid");
					String name = out.getLocalizedName(new FluidStack(out, 100));
					flag = id.equalsIgnoreCase(name);
				}
			}

			if (in != null && out != null && flag) {
				arecipes.add(new BrewRecipeCacher(in, out));
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		Map<Fluid, Fluid> recipes = this.recipeLoader();

		if (recipes == null || recipes.isEmpty() || ingredient == null)
			return;

		for (Entry<Fluid, Fluid> entry : recipes.entrySet()) {
			Fluid in = entry.getKey();
			Fluid out = entry.getValue();

			boolean flag = this.contain(in, ingredient);

			if (!flag && ingredient.getItem() == DCsAppleMilk.dummyItem) {
				NBTTagCompound tag = ingredient.getTagCompound();
				if (tag != null && tag.hasKey("fluid")) {
					String id = tag.getString("fluid");
					String name = in.getLocalizedName(new FluidStack(in, 100));
					flag = id.equalsIgnoreCase(name);
				}
			}

			if (flag) {
				BrewRecipeCacher cache = new BrewRecipeCacher(in, out);
				arecipes.add(cache);
			} else if (in != null && ingredient.getItem() == Item.getItemFromBlock(DCsAppleMilk.barrel)) {
				arecipes.add(new BrewRecipeCacher(in, out));
			}
		}
	}

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("dc.BrewingGuiNEI");
	}

	@Override
	public String getGuiTexture() {
		return "defeatedcrow:textures/gui/dummygui.png";
	}

	private boolean contain(Fluid in, ItemStack check) {
		boolean flag1 = false;
		if (check == null || in == null)
			return false;

		FluidStack target = new FluidStack(in, 100);
		flag1 = FluidContainerRegistry.containsFluid(check, target);

		return flag1;
	}

	@Override
	public void drawExtras(int recipe) {
		Map<Fluid, Fluid> recipes = this.recipeLoader();
		if (recipes == null || recipes.isEmpty())
			return;

		if (arecipes.get(recipe) instanceof BrewRecipeCacher) {
			BrewRecipeCacher current = (BrewRecipeCacher) arecipes.get(recipe);
			FluidStack in = current.influid;
			FluidStack out = current.resfluid;
			drawFluid(in, 16, 48, 21, 16, 16);
			drawFluid(out, 16, 102, 21, 16, 16);
		}
	}

	/**
	 * Original code was made by Shift02.
	 */
	private void drawFluid(FluidStack fluid, int level, int x, int y, int width, int height) {
		if (fluid == null || fluid.getFluid() == null) {
			return;
		}

		ResourceLocation res = null;
		if (fluid.getFluid().getSpriteNumber() == 0) {
			res = TextureMap.locationBlocksTexture;
		} else {
			res = TextureMap.locationItemsTexture;
		}
		Minecraft.getMinecraft().getTextureManager().bindTexture(res);

		IIcon icon = fluid.getFluid().getIcon(fluid);
		if (icon == null)
			return;
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		setGLColorFromInt(fluid.getFluid().getColor(fluid));

		int widR = width;
		int heiR = level;
		int yR = y + (height - heiR);

		int widL = 0;
		int heiL = 0;

		for (int i = 0; i < widR; i += 16) {
			for (int j = 0; j < heiR; j += 16) {
				widL = Math.min(widR - i, 16);
				heiL = Math.min(heiR - j, 16);
				this.drawTexturedModelRectFromIcon(x + i, yR + j, icon, widL, heiL);
			}
		}
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);

	}

	public static void setGLColorFromInt(int color) {
		float red = (color >> 16 & 255) / 255.0F;
		float green = (color >> 8 & 255) / 255.0F;
		float blue = (color & 255) / 255.0F;
		GL11.glColor4f(red, green, blue, 1.0F);
	}

	public void drawTexturedModelRectFromIcon(int x, int y, IIcon icon, int width, int height) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV((double) (x + 0), (double) (y + height), 0.0F, (double) icon.getMinU(),
				(double) icon.getMaxV());
		tessellator.addVertexWithUV((double) (x + width), (double) (y + height), 0.0F, (double) icon.getMaxU(),
				(double) icon.getMaxV());
		tessellator.addVertexWithUV((double) (x + width), (double) (y + 0), 0.0F, (double) icon.getMaxU(),
				(double) icon.getMinV());
		tessellator.addVertexWithUV((double) (x + 0), (double) (y + 0), 0.0F, (double) icon.getMinU(),
				(double) icon.getMinV());
		tessellator.draw();
	}

}
