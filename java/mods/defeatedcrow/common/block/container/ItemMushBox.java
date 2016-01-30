package mods.defeatedcrow.common.block.container;

import mods.defeatedcrow.api.ICompressedItem;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.entity.EntityKinoko;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMushBox extends ItemBlock implements ICompressedItem {

	private static final String[] type = new String[] {
			"_red",
			"_brown" };

	public ItemMushBox(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);

	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		if (m < 2)
			return super.getUnlocalizedName() + type[m];
		else
			return super.getUnlocalizedName() + m;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side,
			float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		Block block = world.getBlock(x, y, z);

		if (block == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1) {
			side = 1;
		} else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush
				&& !block.isReplaceable(world, x, y, z)) {
			if (side == 0) {
				--y;
			}

			if (side == 1) {
				++y;
			}

			if (side == 2) {
				--z;
			}

			if (side == 3) {
				++z;
			}

			if (side == 4) {
				--x;
			}

			if (side == 5) {
				++x;
			}
		}

		if (item.stackSize == 0) {
			return false;
		} else if (!player.canPlayerEdit(x, y, z, side, item)) {
			return false;
		} else if (y == 255 && this.field_150939_a.getMaterial().isSolid()) {
			return false;
		} else if (world.canPlaceEntityOnSide(this.field_150939_a, x, y, z, false, side, player, item)) {
			if (DCsConfig.allowEdibleEntities) {
				int m = this.getMetadata(item.getItemDamage());

				if (!world.isRemote) {
					if (this.spownEntityFoods(world, player, new ItemStack(this, 1, m), x + 0.5F, y + 0.0F, z + 0.5F)) {
						world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F,
								this.field_150939_a.stepSound.func_150496_b(),
								(this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F,
								this.field_150939_a.stepSound.getPitch() * 0.8F);
						--item.stackSize;
					}
				}
				return true;
			} else {
				int i1 = this.getMetadata(item.getItemDamage());
				int j1 = this.field_150939_a.onBlockPlaced(world, x, y, z, side, p_77648_8_, p_77648_9_, p_77648_10_,
						i1);

				if (placeBlockAt(item, player, world, x, y, z, side, p_77648_8_, p_77648_9_, p_77648_10_, j1)) {
					world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, this.field_150939_a.stepSound.func_150496_b(),
							(this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F,
							this.field_150939_a.stepSound.getPitch() * 0.8F);
					--item.stackSize;
				}

				return true;
			}
		} else {
			return false;
		}
	}

	// 各Entityクラスで中身をオーバーライドすること。
	protected boolean spownEntityFoods(World world, EntityPlayer player, ItemStack item, double x, double y, double z) {
		EntityKinoko entity = new EntityKinoko(world, false, item, x, y, z);
		entity.rotationYaw = player.rotationYaw - 180.0F;

		if (!world.isRemote && item != null) {
			return world.spawnEntityInWorld(entity);
		}

		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean func_150936_a(World world, int x, int y, int z, int side, EntityPlayer player, ItemStack item) {
		Block block = world.getBlock(x, y, z);

		if (block == Blocks.snow_layer) {
			side = 1;
		} else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush
				&& !block.isReplaceable(world, x, y, z)) {
			if (side == 0) {
				--y;
			}

			if (side == 1) {
				++y;
			}

			if (side == 2) {
				--z;
			}

			if (side == 3) {
				++z;
			}

			if (side == 4) {
				--x;
			}

			if (side == 5) {
				++x;
			}
		}

		return world.canPlaceEntityOnSide(this.field_150939_a, x, y, z, false, side, (Entity) null, item);
	}

	@Override
	public ItemStack getDisassembledItem(ItemStack cont) {
		if (cont == null || cont.getItem() == null)
			return null;
		int m = cont.getItemDamage();
		switch (m) {
		case 0:
			return new ItemStack(Blocks.red_mushroom, 9, 0);
		case 1:
			return new ItemStack(Blocks.brown_mushroom, 9, 0);
		default:
			return null;
		}
	}

}
