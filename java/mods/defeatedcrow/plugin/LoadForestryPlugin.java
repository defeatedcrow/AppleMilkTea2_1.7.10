package mods.defeatedcrow.plugin;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.handler.Util;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class LoadForestryPlugin {
	
	public void load()
	{
		ItemStack item = new ItemStack(Util.getModItem("Forestey", "waxCapsule"), 1);
		if (item != null) {
			ItemStack register = item;
			if (register != null) {
				LoadModHandler.registerModItems("emptyCapsule", register);
			}
		}
		ItemStack item2 = new ItemStack(Util.getModItem("Forestey", "refractoryEmpty"), 1);
		if (item2 != null) {
			ItemStack register = item2;
			if (register != null) {
				LoadModHandler.registerModItems("emptyRefractory", register);
			}
		}
		ItemStack item3 = new ItemStack(Util.getModItem("Forestey", "canEmpty"), 1);
		if (item3 != null) {
			ItemStack register = item3;
			if (register != null) {
				LoadModHandler.registerModItems("emptyCan", register);
			}
		}
		ItemStack item4 = new ItemStack(Util.getModItem("Forestey", "waxCapsuleWater"), 1);
		if (item4 != null) {
			ItemStack register = item4;
			if (register != null) {
				LoadModHandler.registerModItems("waterCapsule", register);
				if (LoadModHandler.registerModItems("containerWater", register)) {
					AMTLogger.debugInfo("Succeeded to get Forestry water capsule");
				}
			}
		}
		ItemStack item5 = new ItemStack(Util.getModItem("Forestey", "refractoryWater"), 1);
		if (item5 != null) {
			ItemStack register = item5;
			if (register != null) {
				LoadModHandler.registerModItems("waterRefractory", register);
				if (LoadModHandler.registerModItems("containerWater", register)) {
					AMTLogger.debugInfo("Succeeded to get Forestry water refractory");
				}
			}
		}
		ItemStack item6 = new ItemStack(Util.getModItem("Forestey", "camWater"), 1);
		if (item6 != null) {
			ItemStack register = item6;
			if (register != null) {
				LoadModHandler.registerModItems("waterCan", register);
				if (LoadModHandler.registerModItems("containerWater", register)) {
					AMTLogger.debugInfo("Succeeded to get Forestry water can");
				}
			}
		}
	}

}
