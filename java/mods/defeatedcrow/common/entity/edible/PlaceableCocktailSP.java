package mods.defeatedcrow.common.entity.edible;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PlaceableCocktailSP extends PlaceableFoods{
	
	public PlaceableCocktailSP(World world){
    	super(world);
    }

	public PlaceableCocktailSP(World world, ItemStack item) {
		super(world, true, item);
	}

	public PlaceableCocktailSP(World world, ItemStack item,
			double x, double y, double z) {
		super(world, true, item, x, y, z);
	}

	@Override
	protected ItemStack returnItem() {
		return new ItemStack(DCsAppleMilk.cocktailSP, 1, this.getItemMetadata());
	}

}
