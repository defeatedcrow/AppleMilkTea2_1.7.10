package mods.defeatedcrow.common.entity.edible;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PlaceableTart extends PlaceableFoods {

	public PlaceableTart(World world) {
		super(world);
	}

	public PlaceableTart(World world, ItemStack item) {
		super(world, true, item);
	}

	public PlaceableTart(World world, ItemStack item, double x, double y, double z) {
		super(world, true, item, x, y, z);
	}

	@Override
	protected ItemStack returnItem() {
		return new ItemStack(DCsAppleMilk.appleTart, 1, this.getItemMetadata());
	}

}
