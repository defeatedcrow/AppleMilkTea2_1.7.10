package mods.defeatedcrow.plugin.ffm;

import java.util.Collection;
import java.util.Stack;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import forestry.api.farming.ICrop;
import forestry.api.farming.IFarmHousing;
import forestry.api.farming.IFarmable;
import forestry.core.vect.Vect;
import forestry.farming.logic.FarmLogic;

public class FarmLogicAMT extends FarmLogic {

	private final IFarmable[] plants;

	public FarmLogicAMT(IFarmHousing housing) {
		super(housing);
		plants = LoadForestryPlugin.farmables;
	}

	@Override
	public IIcon getIcon() {
		return DCsAppleMilk.leafTea.getIconFromDamage(0);
	}

	@Override
	public String getName() {
		if (isManual) {
			return "AMT Manual Plantation";
		} else {
			return "AMT Managed Plantation";
		}
	}

	@Override
	public int getFertilizerConsumption() {
		return 80;
	}

	@Override
	public int getWaterConsumption(float hydrationModifier) {
		return (int) (10 * hydrationModifier);
	}

	@Override
	public boolean isAcceptedResource(ItemStack itemstack) {
		if (isManual) {
			return false;
		}
		return itemstack.getItem() == Item.getItemFromBlock(Blocks.dirt);
	}

	@Override
	public boolean isAcceptedGermling(ItemStack itemstack) {
		if (isManual) {
			return false;
		}
		for (IFarmable farm : plants) {
			if (farm.isGermling(itemstack)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Collection<ItemStack> collect() {
		return null;
	}

	@Override
	public boolean cultivate(int x, int y, int z, ForgeDirection direction, int extent) {
		World world = getWorld();
		for (int i = 0; i < extent; i++) {
			if (world.isAirBlock(x, y + 1, z) && world.getBlock(x, y, z) == Blocks.dirt) {
				for (IFarmable plant : plants) {
					if (housing.plantGermling(plant, world, x, y + 1, z)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public Collection<ICrop> harvest(int x, int y, int z, ForgeDirection direction, int extent) {
		World world = getWorld();
		Stack<ICrop> crops = new Stack<ICrop>();
		for (int i = 0; i < extent; i++) {
			for (int j = 0; j < 2; j++) {
				Vect position = translateWithOffset(x, y + 1 + j, z, direction, i);
				for (IFarmable seed : plants) {
					ICrop crop = seed.getCropAt(world, position.x, position.y, position.z);
					if (crop != null) {
						crops.push(crop);
					}
				}
			}

		}
		return crops;
	}

}
