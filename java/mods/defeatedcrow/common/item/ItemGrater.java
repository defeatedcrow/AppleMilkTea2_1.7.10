package mods.defeatedcrow.common.item;

import java.util.ArrayList;
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
	
	private boolean repair = false;

	public ItemGrater (){
		super ();
		this.setMaxStackSize(1);
		this.setMaxDamage(64);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister){
	this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:grater");
	}

}
