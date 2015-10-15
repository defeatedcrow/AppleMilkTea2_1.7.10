package mods.defeatedcrow.plugin;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.railcraft.api.crafting.RailcraftCraftingManager;
import mods.railcraft.api.fuel.FuelManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class LoadRailCraftPlugin {

	private static FluidStack creosote;

	public void load() {
		if (FluidRegistry.isFluidRegistered("creosote")) {
			Fluid f = FluidRegistry.getFluid("creosote");
			this.creosote = new FluidStack(f, 500);
			if (DCsConfig.altModRecipe && this.creosote != null) {
				RailcraftCraftingManager.cokeOven.addRecipe(new ItemStack(DCsAppleMilk.woodBox, 1, 0), false, false,
						new ItemStack(DCsAppleMilk.charcoalBox, 1, 0), this.creosote, 1800);
				RailcraftCraftingManager.cokeOven.addRecipe(new ItemStack(DCsAppleMilk.woodBox, 1, 1), false, false,
						new ItemStack(DCsAppleMilk.charcoalBox, 1, 0), this.creosote, 1800);
				RailcraftCraftingManager.cokeOven.addRecipe(new ItemStack(DCsAppleMilk.woodBox, 1, 2), false, false,
						new ItemStack(DCsAppleMilk.charcoalBox, 1, 0), this.creosote, 1800);
				RailcraftCraftingManager.cokeOven.addRecipe(new ItemStack(DCsAppleMilk.woodBox, 1, 3), false, false,
						new ItemStack(DCsAppleMilk.charcoalBox, 1, 0), this.creosote, 1800);
				RailcraftCraftingManager.cokeOven.addRecipe(new ItemStack(DCsAppleMilk.woodBox, 1, 11), false, false,
						new ItemStack(DCsAppleMilk.charcoalBox, 1, 0), this.creosote, 1800);
				RailcraftCraftingManager.cokeOven.addRecipe(new ItemStack(DCsAppleMilk.woodBox, 1, 12), false, false,
						new ItemStack(DCsAppleMilk.charcoalBox, 1, 0), this.creosote, 1800);
			}

		}

		FuelManager.addBoilerFuel(DCsAppleMilk.vegitableOil, 3200);
		AMTLogger.debugInfo("Add fuel for RC Boiler : vegitable_oil, 3200 fuel");
		FuelManager.addBoilerFuel(DCsAppleMilk.camelliaOil, 6400);
		AMTLogger.debugInfo("Add fuel for RC Boiler : camellia_oil, 6400 fuel");
	}

}
