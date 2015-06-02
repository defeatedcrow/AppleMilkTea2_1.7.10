package mods.defeatedcrow.plugin;

import ic2.api.recipe.Recipes;
import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import forestry.api.recipes.RecipeManagers;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class LoadForestryPlugin {

	public void load() {
		ItemStack item = new ItemStack(Util.getModItem("Forestry", "waxCapsule"), 1);
		if (item != null) {
			ItemStack register = new ItemStack(item.getItem());
			if (register != null) {
				LoadModHandler.registerModItems("emptyCapsule", register);
			}
		}
		ItemStack item2 = new ItemStack(Util.getModItem("Forestry", "refractoryEmpty"), 1);
		if (item2 != null) {
			ItemStack register = new ItemStack(item2.getItem());
			if (register != null) {
				LoadModHandler.registerModItems("emptyRefractory", register);
			}
		}
		ItemStack item3 = new ItemStack(Util.getModItem("Forestry", "canEmpty"), 1);
		if (item3 != null) {
			ItemStack register = new ItemStack(item3.getItem());
			if (register != null) {
				LoadModHandler.registerModItems("emptyCan", register);
			}
		}
		ItemStack item4 = new ItemStack(Util.getModItem("Forestry", "waxCapsuleWater"), 1);
		if (item4 != null) {
			ItemStack register = new ItemStack(item4.getItem());
			if (register != null) {
				LoadModHandler.registerModItems("waterCapsule", register);
				if (LoadModHandler.registerModItems("containerWater", register)) {
					AMTLogger.debugInfo("Succeeded to get Forestry water capsule");
				}
			}
		}
		ItemStack item5 = new ItemStack(Util.getModItem("Forestry", "refractoryWater"), 1);
		if (item5 != null) {
			ItemStack register = new ItemStack(item5.getItem());
			if (register != null) {
				LoadModHandler.registerModItems("waterRefractory", register);
				if (LoadModHandler.registerModItems("containerWater", register)) {
					AMTLogger.debugInfo("Succeeded to get Forestry water refractory");
				}
			}
		}
		ItemStack item6 = new ItemStack(Util.getModItem("Forestry", "waterCan"), 1);
		if (item6 != null) {
			ItemStack register = new ItemStack(item6.getItem());
			if (register != null) {
				LoadModHandler.registerModItems("waterCan", register);
				if (LoadModHandler.registerModItems("containerWater", register)) {
					AMTLogger.debugInfo("Succeeded to get Forestry water can");
				}
			}
		}
		ItemStack item7 = new ItemStack(Util.getModItem("Forestry", "honeyedSlice"), 1);
		if (item7 != null) {
			ItemStack register = new ItemStack(item7.getItem());
			if (register != null) {
				LoadModHandler.registerModItems("honeyedSlice", register);
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(register.getItem(), 4, register
						.getItemDamage()), new Object[] { "XXX", "XYX", "XXX", Character.valueOf('Y'),
						new ItemStack(Items.bread, 1, 0), Character.valueOf('X'), "dropHoney" }));
				AMTLogger.debugInfo("Succeeded to get Forestry Honeyed Slice");
			}
		}
		ItemStack item8 = new ItemStack(Util.getModItem("Forestry", "fertilizerCompound"), 1);
		if (item8 != null) {
			ItemStack register = new ItemStack(item8.getItem());
			if (register != null) {
				LoadModHandler.registerModItems("fertilizer", register);

				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(register.getItem(), 2, register
						.getItemDamage()), new Object[] { "dustOilCake", new ItemStack(Items.dye, 1, 15), "dustAsh" }));

				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(register.getItem(), 2, register
						.getItemDamage()), new Object[] { "dustOilCake", "dustClam", "dustAsh" }));

				ItemStack mulch = new ItemStack(Util.getModItem("Forestry", "mulch"), 1);
				if (mulch != null) {
					LoadModHandler.registerModItems("mulch", mulch);

					GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(register.getItem(), 2, register
							.getItemDamage()), new Object[] { mulch, new ItemStack(Items.dye, 1, 15), "dustAsh" }));

					GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(register.getItem(), 2, register
							.getItemDamage()), new Object[] { mulch, "dustClam", "dustAsh" }));
				}

				AMTLogger.debugInfo("Succeeded to get Forestry fertilizer");
			}
		}

		if (FluidRegistry.isFluidRegistered("vegitable_oil")) {
			FuelManager.bronzeEngineFuel.put(DCsAppleMilk.vegitableOil, new EngineBronzeFuel(DCsAppleMilk.vegitableOil,
					2, 2500, 1));
			AMTLogger.debugInfo("Succeeded to register fuel for Forestry Bronze Engine : vegitable_oil");
		}
		if (FluidRegistry.isFluidRegistered("camellia_oil")) {
			FuelManager.bronzeEngineFuel.put(DCsAppleMilk.camelliaOil, new EngineBronzeFuel(DCsAppleMilk.camelliaOil,
					4, 2500, 1));
			AMTLogger.debugInfo("Succeeded to register fuel for Forestry Bronze Engine : camellia_oil");
		}
	}

	public static void loadRecipes(boolean flag) {
		if (!flag)
			return;

		// 製油
		RecipeManagers.squeezerManager.addRecipe(4, new ItemStack[] { new ItemStack(DCsAppleMilk.leafTea, 8, 4) },
				new FluidStack(DCsAppleMilk.camelliaOil, 100), new ItemStack(DCsAppleMilk.dustWood, 1, 3), 70);

		// エッセンス
		Fluid juice = FluidRegistry.getFluid("juice");
		Fluid biomass = FluidRegistry.getFluid("biomass");
		Fluid ice = FluidRegistry.getFluid("ice");
		if (juice != null && biomass != null && ice != null) {
			RecipeManagers.squeezerManager.addRecipe(4, new ItemStack[] { new ItemStack(DCsAppleMilk.leafTea, 8, 1) },
					new FluidStack(juice, 100), new ItemStack(DCsAppleMilk.essentialOil, 1, 2), 10);

			RecipeManagers.squeezerManager.addRecipe(4, new ItemStack[] { new ItemStack(DCsAppleMilk.leafTea, 8, 3) },
					new FluidStack(juice, 200), new ItemStack(DCsAppleMilk.essentialOil, 1, 3), 10);

			RecipeManagers.squeezerManager.addRecipe(4, new ItemStack[] { new ItemStack(DCsAppleMilk.clam, 8, 0) },
					new FluidStack(biomass, 100), new ItemStack(DCsAppleMilk.essentialOil, 1, 4), 10);

			RecipeManagers.squeezerManager.addRecipe(4,
					new ItemStack[] { new ItemStack(DCsAppleMilk.icyCrystal, 1, 0) }, new FluidStack(ice, 100),
					new ItemStack(DCsAppleMilk.essentialOil, 1, 5), 10);
		}
	}

}
