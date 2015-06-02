package mods.defeatedcrow.common.item;

import java.util.List;

import net.minecraft.src.*;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import mods.defeatedcrow.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLeafTea extends Item {

	@SideOnly(Side.CLIENT)
	private IIcon iconItemType[];

	public ItemLeafTea() {
		super();
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		maxStackSize = 64;

	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		int j = MathHelper.clamp_int(par1, 0, 4);
		return this.iconItemType[j];
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName() + "_" + par1ItemStack.getItemDamage();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(this, 1, 0));
		par3List.add(new ItemStack(this, 1, 1));
		par3List.add(new ItemStack(this, 1, 2));
		par3List.add(new ItemStack(this, 1, 3));
		par3List.add(new ItemStack(this, 1, 4));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.iconItemType = new IIcon[5];
		this.iconItemType[0] = par1IconRegister.registerIcon("defeatedcrow:leaf_raw");
		this.iconItemType[1] = par1IconRegister.registerIcon("defeatedcrow:leaf_mint");
		this.iconItemType[2] = par1IconRegister.registerIcon("defeatedcrow:cassis");
		this.iconItemType[3] = par1IconRegister.registerIcon("defeatedcrow:yuzu");
		this.iconItemType[4] = par1IconRegister.registerIcon("defeatedcrow:camelliafruits");
	}

}
