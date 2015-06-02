package mods.defeatedcrow.common.item.edible;

import java.util.List;

import net.minecraft.src.*;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.common.entity.edible.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAppleSandwich extends EdibleEntityItem2 {

	@SideOnly(Side.CLIENT)
	private IIcon iconSandwichType[];

	private static final String[] sandwichType = new String[] { "_apple", "_egg", "_cassis", "_yuzu" };

	public ItemAppleSandwich() {
		super(true, false);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setMaxStackSize(64);
	}

	@Override
	public int[] hungerOnEaten(int meta) {
		return new int[] { 4, 2 };
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		int j = MathHelper.clamp_int(par1, 0, 3);
		return this.iconSandwichType[j];
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
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.iconSandwichType = new IIcon[4];

		for (int i = 0; i < 4; ++i) {
			this.iconSandwichType[i] = par1IconRegister.registerIcon("defeatedcrow:sandwich" + sandwichType[i]);
		}
	}

	@Override
	protected boolean spownEntityFoods(World world, EntityPlayer player, ItemStack item, double x, double y, double z) {
		PlaceableSandwich entity = new PlaceableSandwich(world, item, x, y, z);
		entity.rotationYaw = player.rotationYaw - 180.0F;

		if (!world.isRemote && item != null) {
			return world.spawnEntityInWorld(entity);
		}
		return false;
	}

}
