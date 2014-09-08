package mods.defeatedcrow.plugin;

import mods.defeatedcrow.common.DCsAppleMilk;
import buildcraft.api.fuels.IronEngineFuel;

public class LoadBCPlugin {
	
	public void loadEnergy()
	{
		IronEngineFuel.addFuel(DCsAppleMilk.vegitableOil, 2, 5000);
		IronEngineFuel.addFuel(DCsAppleMilk.camelliaOil, 3, 10000);
	}

}
