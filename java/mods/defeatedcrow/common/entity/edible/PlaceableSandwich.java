package mods.defeatedcrow.common.entity.edible;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PlaceableSandwich extends PlaceableFoods {

	public PlaceableSandwich(World world) {
		super(world);
	}

	public PlaceableSandwich(World world, ItemStack item) {
		super(world, true, item);
	}

	public PlaceableSandwich(World world, ItemStack item, double x, double y, double z) {
		super(world, true, item, x, y, z);
	}

	@Override
	protected ItemStack returnItem() {
		return new ItemStack(DCsAppleMilk.appleSandwich, 1, this.getItemMetadata());
	}

}
