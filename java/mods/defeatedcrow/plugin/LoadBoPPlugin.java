package mods.defeatedcrow.plugin;

import biomesoplenty.api.content.BOPCItems;
import mods.defeatedcrow.api.recipe.IEvaporatorRecipeRegister;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import mods.defeatedcrow.recipe.EvaporatorRecipeRegister;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class LoadBoPPlugin {

	public static ItemStack bopHoney;
	public static ItemStack bopBerry;
	@Deprecated
	public static ItemStack bopLavender;

	public void load() {

		bopHoney = new ItemStack(BOPCItems.food, 1, 9);
		bopBerry = new ItemStack(BOPCItems.food, 1, 0);

		if (bopHoney != null) {
			OreDictionary.registerOre("dropHoney", bopHoney);
			LoadModHandler.registerModItems("honey", bopHoney);
		}

		if (bopBerry != null) {
			OreDictionary.registerOre("cropRaspberry", bopBerry);
			LoadModHandler.registerModItems("berry", bopBerry);
		}

		Item item = Util.getModItem("BiomesOPlenty", "coral1");
		if (item != null) {
			ItemStack registerItem = new ItemStack(item, 1, 11);
			if (LoadModHandler.registerModItems("seaWeed", registerItem)) {
				AMTLogger.debugInfo("Succeeded to get Kelp");
			}
		}

		Item item2 = Util.getModItem("BiomesOPlenty", "flowers2");
		if (item2 != null) {
			ItemStack registerItem = new ItemStack(item2, 1, 3);
			if (LoadModHandler.registerModItems("lavender", registerItem)) {
				AMTLogger.debugInfo("Succeeded to get Lavender");

				ItemStack reg = new ItemStack(item2, 8, 3);
				IEvaporatorRecipeRegister eva = RecipeRegisterManager.evaporatorRecipe;
				if (eva.getRecipe(reg) == null) {
					eva.addRecipe(new ItemStack(DCsAppleMilk.essentialOil, 1, 6), null, reg);
				}
			}

			ItemStack registerItem2 = new ItemStack(item2, 1, 2);
			if (LoadModHandler.registerModItems("fireFlower", registerItem2)) {
				RecipeRegisterManager.panRecipe.registerHeatSource(Block.getBlockFromItem(item2), 2);
				RecipeRegisterManager.plateRecipe.registerHeatSource(Block.getBlockFromItem(item2), 2);
				AMTLogger.debugInfo("Succeeded to get FireFlower");
			}
		}
	}

}
