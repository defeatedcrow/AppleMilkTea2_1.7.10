package mods.defeatedcrow.plugin;

import cpw.mods.fml.common.FMLModContainer;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import buildcraft.api.fuels.BuildcraftFuelRegistry;

public class LoadBCPlugin {

	public void loadEnergy() {
		ModContainer mod = Loader.instance().getIndexedModList().get("BuildCraft|Energy");
		String s = mod.getVersion();
		AMTLogger.debugInfo("BuildCraft version : " + s);
		if (!s.contains("6.0")) {
			if (!s.contains("6.1.1")) {
				try {
					BuildcraftFuelRegistry.fuel.addFuel(DCsAppleMilk.vegitableOil, 20, 5000);
					BuildcraftFuelRegistry.fuel.addFuel(DCsAppleMilk.camelliaOil, 30, 10000);
				} catch (Exception e) {
					// 何もしない
				}
			}
		}
	}

}
