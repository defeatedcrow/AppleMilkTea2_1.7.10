package mods.defeatedcrow.event;

import mods.defeatedcrow.api.recipe.ITeaRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.block.appliance.BlockTeaMakerNext;
import mods.defeatedcrow.common.tile.TileIncenseBase;
import mods.defeatedcrow.common.tile.appliance.TileMakerNext;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class DispenserEvent {

	public static DispenserEvent instance = new DispenserEvent();

	private DispenserEvent() {
	}

	public void init() {
		// 着火具
		BlockDispenser.dispenseBehaviorRegistry.putObject(DCsAppleMilk.firestarter, new BehaviorDefaultDispenseItem() {
			private boolean flag = true;

			/**
			 * Dispense the specified stack, play the dispense sound and spawn particles.
			 */
			@Override
			protected ItemStack dispenseStack(IBlockSource block, ItemStack item) {
				EnumFacing enumfacing = BlockDispenser.func_149937_b(block.getBlockMetadata());
				World world = block.getWorld();
				int i = block.getXInt() + enumfacing.getFrontOffsetX();
				int j = block.getYInt() + enumfacing.getFrontOffsetY();
				int k = block.getZInt() + enumfacing.getFrontOffsetZ();

				if (world.isAirBlock(i, j, k)) {
					world.setBlock(i, j, k, Blocks.fire);

					if (item.attemptDamageItem(1, world.rand)) {
						item.stackSize = 0;
					}
				} else if (world.getBlock(i, j, k) == Blocks.tnt) {
					Blocks.tnt.onBlockDestroyedByPlayer(world, i, j, k, 1);
					world.setBlockToAir(i, j, k);
				} else if (world.getBlock(i, j, k) == DCsAppleMilk.incenseBase) {
					TileIncenseBase tile = (TileIncenseBase) world.getTileEntity(i, j, k);
					if (tile != null && item != null && tile.hasItem()) {
						if (!tile.getActive()) {
							if (item.attemptDamageItem(1, world.rand)) {
								item.stackSize = 0;
							}
							tile.setActive();
							tile.markDirty();
							world.setBlockMetadataWithNotify(i, j, k, 1, 3);
							world.scheduleBlockUpdate(i, j, k, DCsAppleMilk.incenseBase, 20);
						}
					}
				} else {
					this.flag = false;
				}

				return item;
			}

			/**
			 * Play the dispense sound from the specified block.
			 */
			@Override
			protected void playDispenseSound(IBlockSource block) {
				if (this.flag) {
					block.getWorld().playAuxSFX(1000, block.getXInt(), block.getYInt(), block.getZInt(), 0);
				} else {
					block.getWorld().playAuxSFX(1001, block.getXInt(), block.getYInt(), block.getZInt(), 0);
				}
			}
		});

	}

	public void registerTeaMakerEvent(ItemStack item) {
		// cocoaは登録禁止!
		if (item.getItem() == Items.dye)
			return;
		// ティーメーカー動作
		BlockDispenser.dispenseBehaviorRegistry.putObject(item.getItem(), new BehaviorDefaultDispenseItem() {
			private boolean flag = true;

			/**
			 * Dispense the specified stack, play the dispense sound and spawn particles.
			 */
			@Override
			protected ItemStack dispenseStack(IBlockSource block, ItemStack item) {
				EnumFacing enumfacing = BlockDispenser.func_149937_b(block.getBlockMetadata());
				World world = block.getWorld();
				int i = block.getXInt() + enumfacing.getFrontOffsetX();
				int j = block.getYInt() + enumfacing.getFrontOffsetY();
				int k = block.getZInt() + enumfacing.getFrontOffsetZ();

				if (!world.isRemote && world.getBlock(i, j, k) instanceof BlockTeaMakerNext) {
					TileMakerNext tile = (TileMakerNext) world.getTileEntity(i, j, k);
					if (tile != null && item != null && tile.getItemStack() == null) {
						ITeaRecipe recipe = RecipeRegisterManager.teaRecipe.getRecipe(item);
						if (recipe != null) {
							tile.setRecipe(new ItemStack(item.getItem(), 1, item.getItemDamage()));
							tile.setRemain((byte) 3);
							tile.markDirty();

							int meta = world.getBlockMetadata(i, j, k);
							world.markBlockForUpdate(i, j, k);
							world.notifyBlockChange(i, j, k, world.getBlock(i, j, k));

							--item.stackSize;

							flag = true;
						}
					}
				} else {
					this.flag = false;
				}

				return flag ? item : super.dispenseStack(block, item);
			}

			/**
			 * Play the dispense sound from the specified block.
			 */
			@Override
			protected void playDispenseSound(IBlockSource block) {
				if (this.flag) {
					block.getWorld().playAuxSFX(1009, block.getXInt(), block.getYInt(), block.getZInt(), 0);
				} else {
					block.getWorld().playAuxSFX(1001, block.getXInt(), block.getYInt(), block.getZInt(), 0);
				}
			}
		});

	}

	public void registerFluidDispence() {
		// 容器から出す
		BlockDispenser.dispenseBehaviorRegistry.putObject(DCsAppleMilk.bucketCamOil, new BehaviorDefaultDispenseItem() {
			private boolean flag = true;

			@Override
			protected ItemStack dispenseStack(IBlockSource block, ItemStack itemstack) {
				EnumFacing enumfacing = BlockDispenser.func_149937_b(block.getBlockMetadata());
				World world = block.getWorld();
				int i = block.getXInt() + enumfacing.getFrontOffsetX();
				int j = block.getYInt() + enumfacing.getFrontOffsetY();
				int k = block.getZInt() + enumfacing.getFrontOffsetZ();

				if (!world.isRemote && world.isAirBlock(i, j, k)) {
					if (world.setBlock(i, j, k, DCsAppleMilk.blockCamelliaOil)) {
						flag = true;
					}
				}

				if (flag) {
					return new ItemStack(Items.bucket);
				} else {
					return itemstack;
				}
			}

			@Override
			protected void playDispenseSound(IBlockSource block) {
				if (this.flag) {
					block.getWorld().playAuxSFX(1009, block.getXInt(), block.getYInt(), block.getZInt(), 0);
				} else {
					block.getWorld().playAuxSFX(1001, block.getXInt(), block.getYInt(), block.getZInt(), 0);
				}
			}

		});

		BlockDispenser.dispenseBehaviorRegistry.putObject(DCsAppleMilk.bucketVegiOil,
				new BehaviorDefaultDispenseItem() {
					private boolean flag = true;

					@Override
					protected ItemStack dispenseStack(IBlockSource block, ItemStack itemstack) {
						EnumFacing enumfacing = BlockDispenser.func_149937_b(block.getBlockMetadata());
						World world = block.getWorld();
						int i = block.getXInt() + enumfacing.getFrontOffsetX();
						int j = block.getYInt() + enumfacing.getFrontOffsetY();
						int k = block.getZInt() + enumfacing.getFrontOffsetZ();

						if (!world.isRemote && world.isAirBlock(i, j, k)) {
							if (world.setBlock(i, j, k, DCsAppleMilk.blockVegitableOil)) {
								flag = true;
							}
						}

						if (flag) {
							return new ItemStack(Items.bucket);
						} else {
							return itemstack;
						}
					}

					@Override
					protected void playDispenseSound(IBlockSource block) {
						if (this.flag) {
							block.getWorld().playAuxSFX(1009, block.getXInt(), block.getYInt(), block.getZInt(), 0);
						} else {
							block.getWorld().playAuxSFX(1001, block.getXInt(), block.getYInt(), block.getZInt(), 0);
						}
					}

				});

		// 容器に汲む
		// BlockDispenser.dispenseBehaviorRegistry.putObject(Items.bucket, new
		// EmptyBucketDispenseItem());
	}

	private class EmptyBucketDispenseItem extends BehaviorDefaultDispenseItem {
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

		@Override
		protected void playDispenseSound(IBlockSource block) {
			if (this.flag) {
				block.getWorld().playAuxSFX(1009, block.getXInt(), block.getYInt(), block.getZInt(), 0);
			} else {
				block.getWorld().playAuxSFX(1001, block.getXInt(), block.getYInt(), block.getZInt(), 0);
			}
		}
	}

}
