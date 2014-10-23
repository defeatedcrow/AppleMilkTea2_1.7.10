package mods.defeatedcrow.plugin;

import mods.defeatedcrow.common.DCsAppleMilk;
import buildcraft.api.fuels.BuildcraftFuelRegistry;

public class LoadBCPlugin {
	
	public void loadEnergy()
	{
		BuildcraftFuelRegistry.fuel.addFuel(DCsAppleMilk.vegitableOil, 20, 5000);
		BuildcraftFuelRegistry.fuel.addFuel(DCsAppleMilk.camelliaOil, 30, 10000);
	}

}
