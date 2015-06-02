package mods.defeatedcrow.common.item.appliance;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.IInventory;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGrater extends Item {

	public ItemGrater() {
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(64);
		this.setNoRepair();
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
		return false;
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack item) {
		if (item.getItem() == this) {
			Random rand = Item.itemRand;
			boolean flag = item.attemptDamageItem(1, rand);
			return flag ? null : item;
		}
		return super.getContainerItem(item);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:grater");
	}

}
