package mods.defeatedcrow.event;

import java.util.Map;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.*;
import net.minecraft.init.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.RegistrySimple;
import net.minecraft.world.World;

/**
 * Original code was made by Zot201.
 */
public class FluidDispenser {

	private FluidDispenser() {
	}

	public static void load() {
		final IBehaviorDispenseItem lastHandler = (IBehaviorDispenseItem) BlockDispenser.dispenseBehaviorRegistry
				.getObject(Items.bucket);

		final IBehaviorDispenseItem myHandler = new BehaviorDefaultDispenseItem() {

			private final BehaviorDefaultDispenseItem defaultReturnItem = new BehaviorDefaultDispenseItem();
			private boolean flag = true;

			@Override
			protected ItemStack dispenseStack(IBlockSource block, ItemStack itemstack) {
				EnumFacing enumfacing = BlockDispenser.func_149937_b(block.getBlockMetadata());
				World world = block.getWorld();
				int i = block.getXInt() + enumfacing.getFrontOffsetX();
				int j = block.getYInt() + enumfacing.getFrontOffsetY();
				int k = block.getZInt() + enumfacing.getFrontOffsetZ();

				ItemStack fill = null;

				if (!world.isRemote && world.getBlock(i, j, k) == DCsAppleMilk.blockCamelliaOil) {
					if (world.setBlockToAir(i, j, k)) {
						world.markBlockForUpdate(i, j, k);
						world.notifyBlockChange(i, j, k, world.getBlock(i, j, k));
						fill = new ItemStack(DCsAppleMilk.bucketCamOil);
						flag = true;
					}
				}

				if (!world.isRemote && world.getBlock(i, j, k) == DCsAppleMilk.blockVegitableOil) {
					if (world.setBlockToAir(i, j, k)) {
						world.markBlockForUpdate(i, j, k);
						world.notifyBlockChange(i, j, k, world.getBlock(i, j, k));
						fill = new ItemStack(DCsAppleMilk.bucketVegiOil);
						flag = true;
					}
				}

				if (fill == null) {
					return this.defaultReturnItem.dispense(block, itemstack);
				}

				if (--itemstack.stackSize == 0) {
					itemstack = fill.copy();
				} else if (((TileEntityDispenser) block.getBlockTileEntity()).func_146019_a(fill) < 0) {
					return this.defaultReturnItem.dispense(block, itemstack);
				}

				return itemstack;
			}

			protected void playDispenseSound(IBlockSource block) {
				if (this.flag) {
					block.getWorld().playAuxSFX(1009, block.getXInt(), block.getYInt(), block.getZInt(), 0);
				} else {
					block.getWorld().playAuxSFX(1001, block.getXInt(), block.getYInt(), block.getZInt(), 0);
				}
			}
		};

		final IBehaviorDispenseItem combinedHandler = new IBehaviorDispenseItem() {
			@Override
			public ItemStack dispense(IBlockSource block, ItemStack stack) {

				boolean isMyItem = false;
				EnumFacing enumfacing = BlockDispenser.func_149937_b(block.getBlockMetadata());
				World world = block.getWorld();
				int i = block.getXInt() + enumfacing.getFrontOffsetX();
				int j = block.getYInt() + enumfacing.getFrontOffsetY();
				int k = block.getZInt() + enumfacing.getFrontOffsetZ();

				if (world.getBlock(i, j, k) == DCsAppleMilk.blockVegitableOil
						|| world.getBlock(i, j, k) == DCsAppleMilk.blockCamelliaOil) {
					isMyItem = true;
				}

				return (isMyItem ? myHandler : lastHandler).dispense(block, stack);
			}
		};

		Map<Item, IBehaviorDispenseItem> internalMap = ObfuscationReflectionHelper.getPrivateValue(
				RegistrySimple.class, (RegistrySimple) BlockDispenser.dispenseBehaviorRegistry, "registryObjects",
				"field_82596_a");
		internalMap.remove(Items.bucket);

		BlockDispenser.dispenseBehaviorRegistry.putObject(Items.bucket, combinedHandler);
	}

}
