package mods.defeatedcrow.common.entity.edible;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PlaceableCup2 extends PlaceableFoods {

	public PlaceableCup2(World world) {
		super(world);
	}

	public PlaceableCup2(World world, ItemStack item) {
		super(world, true, item);
	}

	public PlaceableCup2(World world, ItemStack item, double x, double y, double z) {
		super(world, true, item, x, y, z);
	}

	@Override
	protected ItemStack returnItem() {
		return new ItemStack(DCsAppleMilk.teaCup2, 1, this.getItemMetadata());
	}

	@Override
	protected float getScale() {
		return Util.getCupScale();
	}

	@Override
	protected float getSize() {
		return Util.getCupSize();
	}

}
