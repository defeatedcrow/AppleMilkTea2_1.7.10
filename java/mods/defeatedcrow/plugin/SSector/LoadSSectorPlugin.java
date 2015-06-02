package mods.defeatedcrow.plugin.SSector;

import java.util.ArrayList;

import shift.sextiarysector.SSRecipes;
import shift.sextiarysector.api.SextiarySectorAPI;
import shift.sextiarysector.api.recipe.RecipeAPI;
import shift.sextiarysector.player.EntityPlayerManager;
import shift.sextiarysector.player.StaminaStats;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.handler.Util;
import mods.defeatedcrow.plugin.LoadModHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class LoadSSectorPlugin {

	public static ItemStack sakeBottle;
	public static ItemStack rumBottle;
	public static ItemStack ginBottle;
	public static ItemStack beerBottle;
	public static ItemStack emptyBottle;

	public static ItemStack laver;

	public void load() {
		// ArrayList<ItemStack> sake = OreDictionary.getOres("drinkSake");
		// ArrayList<ItemStack> rum = OreDictionary.getOres("drinkRum");
		// ArrayList<ItemStack> gin = OreDictionary.getOres("drinkGin");
		// ArrayList<ItemStack> beer = OreDictionary.getOres("drinkBeer");
		// ArrayList<ItemStack> bottle = OreDictionary.getOres("craftingBottle");
		//
		// if (sake.size() > 0){
		// sakeBottle = sake.get(0);
		// if (sakeBottle != null) {
		// LoadModHandler.registerModItems("drinkSake", new ItemStack(sakeBottle.getItem(), 1, 0));
		// }
		// }
		// if (rum.size() > 0){
		// rumBottle = rum.get(0);
		// if (rumBottle != null) {
		//
		// LoadModHandler.registerModItems("drinkRum", new ItemStack(rumBottle.getItem(), 1, 0));
		// }
		// }
		// if (gin.size() > 0){
		// ginBottle = gin.get(0);
		// if (ginBottle != null) {
		// LoadModHandler.registerModItems("drinkGin", new ItemStack(ginBottle.getItem(), 1, 0));
		// }
		// }
		// if (beer.size() > 0){
		// beerBottle = beer.get(0);
		// if (beerBottle != null) {
		// LoadModHandler.registerModItems("drinkBeer", new ItemStack(beerBottle.getItem(), 1, 0));
		// }
		// }
		// if (bottle.size() > 0){
		// emptyBottle = bottle.get(0);
		// if (emptyBottle != null) {
		// }
		// }

		Item item = Util.getModItem("SextiarySector", "Laver");
		if (item != null) {
			ItemStack registerItem = new ItemStack(item, 1, 0);
			if (LoadModHandler.registerModItems("seaWeed", registerItem)) {
				AMTLogger.debugInfo("Succeeded to get Laver");
			}
		}

	}

	public static void loadSS2Recipes(boolean flag) {
		if (!flag)
			return;

		// 石臼
		RecipeAPI.millstone.add("cropTea", new ItemStack(DCsAppleMilk.foodTea, 2));
		RecipeAPI.millstone.add(new ItemStack(DCsAppleMilk.clam), new ItemStack(DCsAppleMilk.EXItems, 2, 6));
		RecipeAPI.millstone.add(new ItemStack(Blocks.ice), new ItemStack(DCsAppleMilk.EXItems, 2, 4));

		// 製油
		RecipeAPI.extractor.add(new ItemStack(DCsAppleMilk.leafTea, 1, 4), new ItemStack(DCsAppleMilk.dustWood, 1, 3),
				new FluidStack(DCsAppleMilk.camelliaOil, 20));
		RecipeAPI.extractor.add(new ItemStack(Items.wheat_seeds, 1, 0), new ItemStack(DCsAppleMilk.dustWood, 1, 3),
				new FluidStack(DCsAppleMilk.vegitableOil, 2));
		RecipeAPI.extractor.add(new ItemStack(Items.pumpkin_seeds, 1, 0), new ItemStack(DCsAppleMilk.dustWood, 1, 3),
				new FluidStack(DCsAppleMilk.vegitableOil, 2));
		RecipeAPI.extractor.add("soybeans", new ItemStack(DCsAppleMilk.dustWood, 1, 3), new FluidStack(
				DCsAppleMilk.vegitableOil, 100));
		RecipeAPI.extractor.add("rapeSeeds", new ItemStack(DCsAppleMilk.dustWood, 1, 3), new FluidStack(
				DCsAppleMilk.vegitableOil, 120));

		// 製氷
		SSRecipes.freezer.add(new ItemStack(Items.emerald), new ItemStack(DCsAppleMilk.icyCrystal, 1, 0));

	}

	public static void addStatus(int par1, float par2, int par3, float par4, EntityPlayer par5EntityPlayer) {
		int m = par1;
		float ms = par2;
		int s = par3;
		float ss = par4;

		if (m > 0) {
			SextiarySectorAPI.addMoistureStats(par5EntityPlayer, m, ms);
		} else {
			SextiarySectorAPI.addMoistureExhaustion(par5EntityPlayer, (float) -ms * 4.0F);
		}

		if (s > 0) {
			SextiarySectorAPI.addStaminaStats(par5EntityPlayer, s, ss);
		} else {
			SextiarySectorAPI.addStaminaExhaustion(par5EntityPlayer, (float) -ss * 4.0F);
		}

	}

	public static float getStaminaStats(EntityPlayer player) {
		int ret = EntityPlayerManager.getStaminaStats(player).getStaminaLevel();
		return ret;
	}

}
