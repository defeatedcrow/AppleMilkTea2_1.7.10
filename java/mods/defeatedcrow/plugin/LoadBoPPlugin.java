package mods.defeatedcrow.plugin;

import biomesoplenty.api.content.BOPCItems;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class LoadBoPPlugin {
	
	public static ItemStack bopHoney;
	public static ItemStack bopBerry;
	
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
	}

}
