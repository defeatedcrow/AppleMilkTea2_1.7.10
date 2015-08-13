package mods.defeatedcrow.common.entity.edible;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.base.FoodBaseEntity;
import mods.defeatedcrow.common.base.FoodModelType.Deco;
import mods.defeatedcrow.common.base.FoodModelType.Dish;
import mods.defeatedcrow.common.base.FoodModelType.Soup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class PlaceableBaseSoup extends FoodBaseEntity {

	public PlaceableBaseSoup(World world) {
		super(world);
	}

	public PlaceableBaseSoup(World world, ItemStack item) {
		super(world, item);
	}

	public PlaceableBaseSoup(World world, ItemStack item, double x, double y, double z) {
		super(world, item, x, y, z);
	}

	@Override
	public Soup getSoupType() {
		return Soup.WoodSoup;
	}

	@Override
	public Deco getDecoType() {
		return Deco.None;
	}

	@Override
	public Dish getDishType() {
		return Dish.WoodBowl;
	}

	@Override
	public IIcon getSoupIcon(int meta) {
		return DCsAppleMilk.baseSoupBowl.getIconFromDamage(meta + 16);
	}

	@Override
	protected ItemStack returnItem() {
		return new ItemStack(DCsAppleMilk.baseSoupBowl, 1, this.getItemMetadata());
	}

}
