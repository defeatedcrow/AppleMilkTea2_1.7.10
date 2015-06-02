package mods.defeatedcrow.common.entity.edible;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PlaceableSteak extends PlaceableFoods {

	public PlaceableSteak(World world) {
		super(world);
	}

	public PlaceableSteak(World world, ItemStack item) {
		super(world, true, item);
	}

	public PlaceableSteak(World world, ItemStack item, double x, double y, double z) {
		super(world, true, item, x, y, z);
	}

	@Override
	protected ItemStack returnItem() {
		return new ItemStack(DCsAppleMilk.foodPlate, 1, this.getItemMetadata());
	}

	@Override
	protected byte particleNumber() {
		return 2;
	}

}
