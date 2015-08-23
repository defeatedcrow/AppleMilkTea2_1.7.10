package mods.defeatedcrow.plugin.ffm;

import java.util.ArrayList;
import java.util.Collection;

import mods.defeatedcrow.api.plants.IRightClickHarvestable;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import forestry.core.proxy.Proxies;
import forestry.core.vect.Vect;
import forestry.farming.logic.Crop;

public class CropAMTPlants extends Crop {

	protected final Block block;
	protected final int meta;

	public CropAMTPlants(World world, Block block, int meta, Vect position) {
		super(world, position);
		this.block = block;
		this.meta = meta;
	}

	@Override
	protected boolean isCrop(Vect pos) {
		return getBlock(pos) == block && getBlockMeta(pos) == meta;
	}

	@Override
	protected Collection<ItemStack> harvestBlock(Vect pos) {
		Collection<ItemStack> harvested = new ArrayList<ItemStack>();
		if (block instanceof IRightClickHarvestable) {
			IRightClickHarvestable plant = (IRightClickHarvestable) block;
			harvested.add(plant.getCropItem(meta));
			Proxies.common.addBlockDestroyEffects(world, pos.x, pos.y, pos.z, DCsAppleMilk.teaTree, 0);
			world.setBlockMetadataWithNotify(pos.x, pos.y, pos.z, plant.getInitialMetadata(world, pos.x, pos.y, pos.z),
					2);
		}
		return harvested;
	}

	@Override
	public String toString() {
		return String.format("CropAMTPlants [ position: [ %s ]; block: %s; meta: %s ]", position.toString(),
				block.getUnlocalizedName(), meta);
	}
}
