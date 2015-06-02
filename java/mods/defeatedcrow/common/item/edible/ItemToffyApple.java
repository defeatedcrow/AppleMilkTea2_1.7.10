package mods.defeatedcrow.common.item.edible;

import net.minecraft.src.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import mods.defeatedcrow.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemToffyApple extends ItemFood {

	public ItemToffyApple(int reco, int sat, boolean flag) {
		super(reco, sat, flag);
		maxStackSize = 64;

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:toffyapple");
	}

}
