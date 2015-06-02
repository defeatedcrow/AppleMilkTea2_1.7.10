package mods.defeatedcrow.common.entity.edible;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PlaceableBowl extends PlaceableFoods {

	public PlaceableBowl(World world) {
		super(world);
	}

	public PlaceableBowl(World world, ItemStack item) {
		super(world, true, item);
	}

	public PlaceableBowl(World world, ItemStack item, double x, double y, double z) {
		super(world, true, item, x, y, z);
	}

	@Override
	protected ItemStack returnItem() {
		return new ItemStack(DCsAppleMilk.bowlBlock, 1, this.getItemMetadata());
	}

	@Override
	protected byte particleNumber() {
		return 2;
	}

}
