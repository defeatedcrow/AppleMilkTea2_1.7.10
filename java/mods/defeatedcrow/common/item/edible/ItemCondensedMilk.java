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
import mods.defeatedcrow.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCondensedMilk extends ItemFood {

	@SideOnly(Side.CLIENT)
	private IIcon iconType[];

	private static final String[] icon = new String[] { "condensedmilk", "preserve_cassis", "sauce_mint",
			"marmalade_yuzu" };

	public ItemCondensedMilk(int reco, int sat, boolean flag) {
		super(reco, sat, flag);
		this.setMaxStackSize(64);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		int j = MathHelper.clamp_int(par1, 0, 3);
		return this.iconType[j];
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
		this.iconType = new IIcon[4];

		for (int i = 0; i < 4; ++i) {
			this.iconType[i] = par1IconRegister.registerIcon("defeatedcrow:" + icon[i]);
		}
	}

}
