package mods.defeatedcrow.plugin;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.railcraft.api.crafting.RailcraftCraftingManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class LoadRailCraftPlugin {
	
	private static FluidStack creosote;
	
	public void load()
	{
		if (FluidRegistry.isFluidRegistered("creosote"))
		{
			int id = FluidRegistry.getFluidID("creosote");
			this.creosote = new FluidStack(id, 500);
			if (this.creosote != null)
			{
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
	}

}
