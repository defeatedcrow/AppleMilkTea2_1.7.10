package mods.defeatedcrow.common.entity.edible;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PlaceableIcecream extends PlaceableFoods{
	
	public PlaceableIcecream(World world){
    	super(world);
    }

	public PlaceableIcecream(World world, boolean chops, ItemStack item) {
		super(world, chops, item);
	}

	public PlaceableIcecream(World world, boolean chops, ItemStack item,
			double x, double y, double z) {
		super(world, chops, item, x, y, z);
	}

	@Override
	protected ItemStack returnItem() {
		return new ItemStack(DCsAppleMilk.blockIcecream, 1, this.getItemMetadata());
	}

}
