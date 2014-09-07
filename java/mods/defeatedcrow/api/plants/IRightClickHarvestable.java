package mods.defeatedcrow.api.plants;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IRightClickHarvestable {
	
	boolean onHarvest(World world, int x, int y, int z, IInventory inventory, ItemStack currentItem);
	
	boolean isHarvestable(World world, int x, int y, int z);
	
	ItemStack getCropItem(int blockMeta);

}
