package mods.defeatedcrow.plugin.IC2;

import ic2.api.item.IC2Items;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import ic2.core.block.TileEntityBarrel;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.plugin.LoadModHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Loader;

public class LoadIC2Plugin {

	public static ItemStack IC2Cell;
	public static ItemStack IC2WaterCell;
	public static ItemStack IC2Coffeepowder;
	public static ItemStack IC2Mug;
	public static ItemStack IC2CoffeeBeans;
	public static ItemStack IC2MugCoffee;
	public static ItemStack IC2MugCoffeeMilk;
	public static ItemStack IC2dropRubber;
	public static Item IC2Rum;

	public static ItemStack IC2Furnace;

	public void load() {

		// IC2apiの機能によりアイテムを取得している
		this.IC2Cell = new ItemStack(IC2Items.getItem("cell").getItem(), 1, IC2Items.getItem("cell").getItemDamage());
		this.IC2WaterCell = new ItemStack(IC2Items.getItem("waterCell").getItem(), 1, IC2Items.getItem("waterCell")
				.getItemDamage());
		this.IC2CoffeeBeans = new ItemStack(IC2Items.getItem("coffeeBeans").getItem(), 1, IC2Items.getItem(
				"coffeeBeans").getItemDamage());
		this.IC2Coffeepowder = new ItemStack(IC2Items.getItem("coffeePowder").getItem(), 1, IC2Items.getItem(
				"coffeePowder").getItemDamage());
		this.IC2Mug = new ItemStack(IC2Items.getItem("mugEmpty").getItem(), 1, 0);
		this.IC2MugCoffee = new ItemStack(IC2Items.getItem("mugCoffee").getItem(), 1, 1);
		this.IC2MugCoffeeMilk = new ItemStack(IC2Items.getItem("mugCoffee").getItem(), 1, 2);
		this.IC2dropRubber = new ItemStack(IC2Items.getItem("rubber").getItem(), 1, IC2Items.getItem("rubber")
				.getItemDamage());
		this.IC2Furnace = new ItemStack(IC2Items.getItem("ironFurnace").getItem(), 1, IC2Items.getItem("ironFurnace")
				.getItemDamage());

		if (IC2Coffeepowder != null) {
			RecipeRegisterManager.teaRecipe.registerCanMilk(IC2Coffeepowder, new ItemStack(DCsAppleMilk.teacupBlock, 1,
					12), new ItemStack(DCsAppleMilk.teacupBlock, 1, 13), new String(
					"defeatedcrow:textures/blocks/contents_cocoa.png"), new String(
					"defeatedcrow:textures/blocks/contents_cocoa_milk.png"));
		}

		if (IC2CoffeeBeans != null) {
			OreDictionary.registerOre("cropCoffee", IC2CoffeeBeans);
		}

		// インスタントティー用の水入り容器登録
		if (this.IC2WaterCell != null) {
			if (LoadModHandler.registerModItems("containerWater", IC2WaterCell)) {
				AMTLogger.debugInfo("Succeeded to get IC2 water cell");
			}
		}

		if (this.IC2Furnace != null) {
			if (LoadModHandler.registerModItems("furnaceBlock", this.IC2Furnace)) {
				RecipeRegisterManager.panRecipe.registerHeatSource(Block.getBlockFromItem(IC2Furnace.getItem()), 1);
				AMTLogger.debugInfo("Succeeded to get IC2 Iron Furnace");
			}
		}

		// 以下はexp版専用のメソッド
		try {
			// 燃料登録
			if (FluidRegistry.isFluidRegistered("vegitable_oil")) {
				Recipes.semiFluidGenerator.addFluid("vegitable_oil", 1, 1.0D);
				AMTLogger.debugInfo("Succeeded to register fuel for IC2 Semifluid Generator : vegitable_oil");
			}
			if (FluidRegistry.isFluidRegistered("camellia_oil")) {
				Recipes.semiFluidGenerator.addFluid("camellia_oil", 1, 1.2D);
				AMTLogger.debugInfo("Succeeded to register fuel for IC2 Semifluid Generator : camellia_oil");
			}

		} catch (Exception e) {
			AMTLogger.debugInfo("Failed to register IC2machines recipe");
			e.printStackTrace(System.err);
		}

	}

	public static void loadRecipes(boolean flag) {
		if (!flag)
			return;

		try {
			RecipeInputItemStack input = new RecipeInputItemStack(new ItemStack(DCsAppleMilk.woodBox, 1, 4), 1);
			NBTTagCompound metadata = new NBTTagCompound();
			metadata.setInteger("extractor", 2000);
			ItemStack outputs = new ItemStack(IC2dropRubber.getItem(), 9, 0);

			Recipes.extractor.addRecipe(input, metadata, outputs);

			RecipeInputItemStack input2 = new RecipeInputItemStack(new ItemStack(DCsAppleMilk.leafTea, 1, 0), 1);
			NBTTagCompound metadata2 = new NBTTagCompound();
			metadata2.setInteger("macerater", 2000);
			ItemStack outputs2 = new ItemStack(DCsAppleMilk.foodTea, 2, 0);

			RecipeInputItemStack input3 = new RecipeInputItemStack(new ItemStack(DCsAppleMilk.emptyBottle, 1, 0), 1);
			ItemStack outputs3 = new ItemStack(DCsAppleMilk.EXItems, 2, 5);

			RecipeInputItemStack input4 = new RecipeInputItemStack(new ItemStack(DCsAppleMilk.clam, 1, 0), 1);
			ItemStack outputs4 = new ItemStack(DCsAppleMilk.EXItems, 2, 6);

			RecipeInputItemStack input5 = new RecipeInputItemStack(new ItemStack(Items.coal, 1, 1), 1);
			ItemStack outputs5 = new ItemStack(DCsAppleMilk.dustWood, 1, 1);

			Recipes.macerator.addRecipe(input2, metadata2, outputs2);
			Recipes.macerator.addRecipe(input3, metadata2, outputs3);
			Recipes.macerator.addRecipe(input4, metadata2, outputs4);
			if (!Loader.isModLoaded("Railcraft")) {
				Recipes.macerator.addRecipe(input5, metadata2, outputs5);
			}
			AMTLogger.debugInfo("Succeeded to register IC2machines recipe");

			RecipeInputItemStack input6 = new RecipeInputItemStack(new ItemStack(DCsAppleMilk.leafTea, 1, 3), 2);
			NBTTagCompound metadata3 = new NBTTagCompound();
			metadata2.setInteger("macerater", 2000);
			ItemStack outputs6 = new ItemStack(DCsAppleMilk.yuzuGel, 1, 0);

			RecipeInputItemStack input7 = new RecipeInputItemStack(new ItemStack(Blocks.melon_block, 1, 0), 9);
			ItemStack outputs7 = new ItemStack(DCsAppleMilk.silkyMelon, 1, 0);

			RecipeInputItemStack input8 = new RecipeInputItemStack(new ItemStack(DCsAppleMilk.flintBlock, 1, 0), 4);
			ItemStack outputs8 = new ItemStack(DCsAppleMilk.chalcedony, 1, 0);

			Recipes.compressor.addRecipe(input6, metadata3, outputs6);
			Recipes.compressor.addRecipe(input7, metadata3, outputs7);
			Recipes.compressor.addRecipe(input8, metadata3, outputs8);
		} catch (Exception e) {
			AMTLogger.debugInfo("Failed to register IC2machines recipe");
			e.printStackTrace(System.err);
		}
	}

	/**
	 * simulate=trueの場合、樽を空にする
	 */
	public static boolean isRumBarrel(TileEntity tile, boolean simulate) {
		if (tile instanceof TileEntityBarrel) {
			TileEntityBarrel barrel = (TileEntityBarrel) tile;
			if (barrel.isEmpty())
				return false;
			boolean flag = false;

			int type = barrel.type;
			int boose = barrel.boozeAmount;
			int age = barrel.age;

			int progress = age * 100 / barrel.timeNedForRum(boose);

			if (progress > 80) {
				flag = true;
				if (!simulate) {
					barrel.drainLiquid(boose);
				}
			}

			AMTLogger.debugInfo("IC2 barrel :" + flag + " age:" + age + " progress:" + progress);
			return flag;

		}

		return false;
	}

}
