package mods.defeatedcrow.common.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemContainerDoor extends Item {

	public final Block output;

	public ItemContainerDoor(Block b) {
		super();
		setMaxDamage(0);
		setHasSubtypes(true);
		output = b;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName();
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(this, 1, 7));
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		int l = par1ItemStack.getItemDamage();
		int rem = (l & 7) + 1;
		par3List.add(new String("Number: " + rem));
	}

	// 以下は設置時の動作

	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float fx,
			float fy, float fz) {
		Block block = world.getBlock(x, y, z);

		if (side != 1) {
			return false;
		} else if (item.stackSize == 0) {
			return false;
		} else if (!player.canPlayerEdit(x, y + 1, z, side, item) || !player.canPlayerEdit(x, y + 2, z, side, item)) {
			return false;
		} else if (y > 253) {
			return false;
		} else if (output.canPlaceBlockAt(world, x, y + 1, z)) {

			if (output.canPlaceBlockAt(world, x, y + 1, z)) {
				int i1 = MathHelper.floor_double((double) ((player.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
				ItemDoor.placeDoorBlock(world, x, y + 1, z, i1, output);
				ItemStack next = null;
				if (item.getItemDamage() > 0) {
					next = new ItemStack(item.getItem(), 1, item.getItemDamage() - 1);
				}
				--item.stackSize;
				this.dropItem(world, player, next);
			}
			return true;
		} else {
			return false;
		}
	}

	private void dropItem(World world, EntityPlayer player, ItemStack item) {
		if (item == null || world.isRemote)
			return;
		else {
			EntityItem drop = new EntityItem(world, player.posX, player.posY + 0.25D, player.posZ, item.copy());
			world.spawnEntityInWorld(drop);
		}
	}

}
