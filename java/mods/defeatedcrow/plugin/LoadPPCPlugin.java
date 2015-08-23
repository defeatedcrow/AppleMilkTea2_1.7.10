package mods.defeatedcrow.plugin;

import mods.defeatedcrow.handler.Util;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class LoadPPCPlugin {

	private LoadPPCPlugin() {
	}

	public static void load() {
		Item rad = Util.getModItem("peaceplantcraft", "DAIKON");
		if (rad != null) {
			ItemStack register = new ItemStack(rad, 1);
			if (register != null) {
				LoadModHandler.registerModItems("PPCradish", register);
				OreDictionary.registerOre("cropRadish", register);
			}
		}
		Item gp = Util.getModItem("peaceplantcraft", "PIMAN");
		if (gp != null) {
			ItemStack register = new ItemStack(gp, 1);
			if (register != null) {
				LoadModHandler.registerModItems("PPCgreenpepper", register);
				OreDictionary.registerOre("cropGreenPepper", register);
			}
		}
		Item tom = Util.getModItem("peaceplantcraft", "TOMATO");
		if (tom != null) {
			ItemStack register = new ItemStack(tom, 1);
			if (register != null) {
				LoadModHandler.registerModItems("PPCtomato", register);
				OreDictionary.registerOre("cropTomato", register);
			}
		}
		Item ric = Util.getModItem("peaceplantcraft", "KOME");
		if (ric != null) {
			ItemStack register = new ItemStack(ric, 1);
			if (register != null) {
				LoadModHandler.registerModItems("PPCrice", register);
				OreDictionary.registerOre("cropRice", register);
			}
		}
		Item let = Util.getModItem("peaceplantcraft", "RETASU");
		if (let != null) {
			ItemStack register = new ItemStack(let, 1);
			if (register != null) {
				LoadModHandler.registerModItems("PPClettuce", register);
				OreDictionary.registerOre("cropLettuce", register);
			}
		}
		Item tur = Util.getModItem("peaceplantcraft", "KABU");
		if (tur != null) {
			ItemStack register = new ItemStack(tur, 1);
			if (register != null) {
				LoadModHandler.registerModItems("PPCturnip", register);
				OreDictionary.registerOre("cropTurnip", register);
			}
		}
		Item nap = Util.getModItem("peaceplantcraft", "HAKUSAI");
		if (nap != null) {
			ItemStack register = new ItemStack(nap, 1);
			if (register != null) {
				LoadModHandler.registerModItems("PPCnapacabbage", register);
				OreDictionary.registerOre("cropNapaCabbage", register);
			}
		}
		Item oni = Util.getModItem("peaceplantcraft", "TAMANEGI");
		if (oni != null) {
			ItemStack register = new ItemStack(oni, 1);
			if (register != null) {
				LoadModHandler.registerModItems("PPConion", register);
				OreDictionary.registerOre("cropOnion", register);
			}
		}
		Item gar = Util.getModItem("peaceplantcraft", "NINNIKU");
		if (gar != null) {
			ItemStack register = new ItemStack(gar, 1);
			if (register != null) {
				LoadModHandler.registerModItems("PPCgarlic", register);
				OreDictionary.registerOre("cropGarlic", register);
			}
		}
		Item soy = Util.getModItem("peaceplantcraft", "EDAMAME");
		if (soy != null) {
			ItemStack register = new ItemStack(soy, 1);
			if (register != null) {
				LoadModHandler.registerModItems("PPsoy", register);
				OreDictionary.registerOre("cropSoybeans", register);
				OreDictionary.registerOre("cropEdamanme", register);
			}
		}
		Item lee = Util.getModItem("peaceplantcraft", "NEGI");
		if (lee != null) {
			ItemStack register = new ItemStack(lee, 1);
			if (register != null) {
				LoadModHandler.registerModItems("PPleek", register);
				OreDictionary.registerOre("cropLeek", register);
			}
		}
		Item cab = Util.getModItem("peaceplantcraft", "KYABETSU");
		if (cab != null) {
			ItemStack register = new ItemStack(cab, 1);
			if (register != null) {
				LoadModHandler.registerModItems("PPcabbage", register);
				OreDictionary.registerOre("cropCabbage", register);
			}
		}
		Item cuc = Util.getModItem("peaceplantcraft", "KYURI");
		if (cuc != null) {
			ItemStack register = new ItemStack(cuc, 1);
			if (register != null) {
				LoadModHandler.registerModItems("PPcucumber", register);
				OreDictionary.registerOre("cropCucumber", register);
			}
		}
		Item egp = Util.getModItem("peaceplantcraft", "NASU");
		if (egp != null) {
			ItemStack register = new ItemStack(egp, 1);
			if (register != null) {
				LoadModHandler.registerModItems("PPeggplant", register);
				OreDictionary.registerOre("cropEggplant", register);
			}
		}
	}

}
