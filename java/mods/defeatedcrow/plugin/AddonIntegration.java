package mods.defeatedcrow.plugin;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.common.Loader;

// defeatedcrow製add-onとの調整が必要な部分をここに置く
public class AddonIntegration {

	private static boolean isJPLoaded;
	private static boolean isMagicLoaded;
	private static boolean isSweetLoaded;

	private AddonIntegration() {
	}

	public static void load() {
		isJPLoaded = Loader.isModLoaded("AMTAddonJP");
		isMagicLoaded = Loader.isModLoaded("AMTAddonMagic");
		isSweetLoaded = Loader.isModLoaded("AMTAddonSweet");
	}

	public static boolean loadedJP() {
		return isJPLoaded;
	}

	public static boolean loadedMagic() {
		return isMagicLoaded;
	}

	public static void addRecipe() {
		if (!isJPLoaded) {
			RecipeRegisterManager.evaporatorRecipe.addRecipe(null, new FluidStack(DCsAppleMilk.shothu_young, 100),
					new ItemStack(DCsAppleMilk.moromi, 1, 0), true);
		}
	}
}
