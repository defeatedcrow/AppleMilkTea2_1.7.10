package mods.defeatedcrow.plugin.ffm;

import mods.defeatedcrow.api.plants.IRightClickHarvestable;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import forestry.api.farming.ICrop;
import forestry.api.farming.IFarmable;
import forestry.core.vect.Vect;

public class FarmableAMT implements IFarmable {

	private final Block block;
	private final int matureMeta;

	public FarmableAMT(Block block, int matureMeta) {
		this.block = block;
		this.matureMeta = matureMeta;
	}

	@Override
	public boolean isSaplingAt(World world, int x, int y, int z) {
		if (block instanceof IRightClickHarvestable) {
			Block sap = ((IRightClickHarvestable) block).getSaplingBlock(matureMeta);
			int sapMeta = ((IRightClickHarvestable) block).getSaplingMeta(matureMeta);
			return world.getBlock(x, y, z) == sap && world.getBlockMetadata(x, y, z) == sapMeta;
		} else {
			return false;
		}
	}

	@Override
	public ICrop getCropAt(World world, int x, int y, int z) {
		if (world.getBlock(x, y, z) != block) {
			return null;
		}
		if (world.getBlockMetadata(x, y, z) != matureMeta) {
			return null;
		}
		return new CropAMTPlants(world, block, matureMeta, new Vect(x, y, z));
	}

	@Override
	public boolean isGermling(ItemStack stack) {
		if (block instanceof IRightClickHarvestable && stack != null && stack.getItem() instanceof ItemBlock) {
			Block sap = ((IRightClickHarvestable) block).getSaplingBlock(matureMeta);
			int sapMeta = ((IRightClickHarvestable) block).getSaplingMeta(matureMeta);
			return stack.getItem() == Item.getItemFromBlock(sap) && stack.getItemDamage() == sapMeta;
		} else {
			return false;
		}
	}

	@Override
	public boolean isWindfall(ItemStack itemstack) {
		return false;
	}

	@Override
	public boolean plantSaplingAt(EntityPlayer player, ItemStack germling, World world, int x, int y, int z) {
		if (block instanceof IRightClickHarvestable) {
			Block sap = ((IRightClickHarvestable) block).getSaplingBlock(matureMeta);
			int sapMeta = ((IRightClickHarvestable) block).getSaplingMeta(matureMeta);
			return world.setBlock(x, y, z, sap, sapMeta, 2);
		}
		return false;
	}

}
