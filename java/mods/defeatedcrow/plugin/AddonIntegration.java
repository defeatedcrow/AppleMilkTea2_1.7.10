package mods.defeatedcrow.plugin;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

//defeatedcrow製add-onとの調整が必要な部分をここに置く
public class AddonIntegration {
	
	private static boolean isJPLoaded;
	private static boolean isMagicLoaded;
	private static boolean isSweetLoaded;
	
	private AddonIntegration(){
	}
	
	public static void load()
	{
		isJPLoaded = Loader.isModLoaded("AddonAMTJP");
		isMagicLoaded = Loader.isModLoaded("AddonAMTMagic");
		isSweetLoaded = Loader.isModLoaded("AddonAMTSweet");
	}
	
	public static void addRecipe()
	{
		if (!isJPLoaded)
		{
			GameRegistry.addShapelessRecipe(
		    		  new ItemStack(DCsAppleMilk.foodTea,1,1),
		    			  new ItemStack(DCsAppleMilk.foodTea,1,0));
			
			GameRegistry.addShapelessRecipe(
		    		  new ItemStack(DCsAppleMilk.foodTea,1,4),
		    			  new ItemStack(DCsAppleMilk.foodTea,1,1));
		}
	}
}
