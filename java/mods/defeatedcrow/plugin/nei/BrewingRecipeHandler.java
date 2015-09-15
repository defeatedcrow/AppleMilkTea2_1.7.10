package mods.defeatedcrow.plugin.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.FluidContMap;
import mods.defeatedcrow.handler.FluidContMap.BottlePack;
import mods.defeatedcrow.recipe.BrewingRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
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

		public ArrayList<PositionedStack> inputList;
		public PositionedStack input;
		public PositionedStack result;
		public PositionedStack barrel;
		private FluidStack influid = null;
		private FluidStack resfluid = null;

		public BrewRecipeCacher() {
			inputList = new ArrayList<PositionedStack>();
		}

		public BrewRecipeCacher(List<ItemStack> input, Fluid in, Fluid out) {
			this();
			setInput(input);
			if (in != null) {
				this.influid = new FluidStack(in, 1000);
				ItemStack dummyForFluid = new ItemStack(DCsAppleMilk.dummyItem, 1, 0);
				String name = in.getLocalizedName(influid);
				short amount = 1000;
				NBTTagCompound tag = new NBTTagCompound();
				tag.setString("fluid", name);
				tag.setShort("amount", amount);
				dummyForFluid.setTagCompound(tag);
				this.input = new PositionedStack(dummyForFluid, 20, 8);

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
				this.result = new PositionedStack(dummyForFluid2, 118, 33);
			}
			ItemStack item = new ItemStack(DCsAppleMilk.barrel, 1, 0);
			this.barrel = new PositionedStack(item, 77, 33);
		}

		public void setInput(List<ItemStack> items) {
			inputList.clear();
			PositionedStack stack = new PositionedStack(items, 40, 8);
			stack.setMaxSize(1);
			inputList.add(stack);
		}

		@Override
		public PositionedStack getResult() {
			return this.result;
		}

		@Override
		public List<PositionedStack> getIngredients() {
			return getCycledIngredients(cycleticks / 20, inputList);
		}

		@Override
		public List<PositionedStack> getOtherStacks() {
			ArrayList<PositionedStack> stacks = new ArrayList<PositionedStack>();
			stacks.add(input);
			stacks.add(barrel);
			return stacks;
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
		transferRects.add(new BrewingRecipeHandler.RecipeTransferRect(new Rectangle(77, 33, 20, 20), "DCsBrewing"));
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
					ArrayList<ItemStack> containers = new ArrayList<ItemStack>();
					BottlePack pack = FluidContMap.getPack(in);
					if (pack != null && !pack.getAllContainer().isEmpty()) {
						containers.addAll(pack.getAllContainer());
					}
					arecipes.add(new BrewRecipeCacher(containers, in, out));
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

			if (flag && in != null && out != null) {
				ArrayList<ItemStack> containers = new ArrayList<ItemStack>();
				BottlePack pack = FluidContMap.getPack(in);
				if (pack != null && !pack.getAllContainer().isEmpty()) {
					containers.addAll(pack.getAllContainer());
					arecipes.add(new BrewRecipeCacher(containers, in, out));
				}

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

			if (flag && in != null && out != null) {
				ArrayList<ItemStack> containers = new ArrayList<ItemStack>();
				BottlePack pack = FluidContMap.getPack(in);
				if (pack != null && !pack.getAllContainer().isEmpty()) {
					containers.addAll(pack.getAllContainer());
					arecipes.add(new BrewRecipeCacher(containers, in, out));
				}
			} else if (in != null && ingredient.getItem() == Item.getItemFromBlock(DCsAppleMilk.barrel)) {
				ArrayList<ItemStack> containers = new ArrayList<ItemStack>();
				BottlePack pack = FluidContMap.getPack(in);
				if (pack != null && !pack.getAllContainer().isEmpty()) {
					containers.addAll(pack.getAllContainer());
					arecipes.add(new BrewRecipeCacher(containers, in, out));
				}
				arecipes.add(new BrewRecipeCacher(containers, in, out));
			}
		}
	}

	@Override
	public String getRecipeName() {
		return StatCollector.translateToLocal("dc.BrewingGuiNEI");
	}

	@Override
	public String getGuiTexture() {
		return "defeatedcrow:textures/gui/appliancegui_nei.png";
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
			Minecraft mc = Minecraft.getMinecraft();
			drawAdditionalSlot(19, 7);

			String d = 4 + " days";
			mc.fontRenderer.drawString(I18n.format(d, new Object[0]), 95, 18, 0x000000);

			String r = "Use by Right-Click";
			mc.fontRenderer.drawString(I18n.format(r, new Object[0]), 4, 32, 0x000000);

			BrewRecipeCacher current = (BrewRecipeCacher) arecipes.get(recipe);
			FluidStack in = current.influid;
			FluidStack out = current.resfluid;
			drawFluid(in, 16, 20, 8, 16, 16);
			drawFluid(out, 16, 118, 33, 16, 16);
		}
	}

	private void drawAdditionalSlot(int x, int y) {
		ResourceLocation res = new ResourceLocation(this.getGuiTexture());
		Minecraft.getMinecraft().getTextureManager().bindTexture(res);
		Minecraft.getMinecraft().currentScreen.drawTexturedModalRect(x, y, 44, 18, 18, 18);
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
		tessellator.addVertexWithUV(x + 0, y + height, 0.0F, icon.getMinU(), icon.getMaxV());
		tessellator.addVertexWithUV(x + width, y + height, 0.0F, icon.getMaxU(), icon.getMaxV());
		tessellator.addVertexWithUV(x + width, y + 0, 0.0F, icon.getMaxU(), icon.getMinV());
		tessellator.addVertexWithUV(x + 0, y + 0, 0.0F, icon.getMinU(), icon.getMinV());
		tessellator.draw();
	}

}
