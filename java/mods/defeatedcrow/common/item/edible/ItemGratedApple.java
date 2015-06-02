package mods.defeatedcrow.common.item.edible;

import java.util.List;

import net.minecraft.src.*;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import mods.defeatedcrow.common.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGratedApple extends ItemFood {

	@SideOnly(Side.CLIENT)
	private IIcon iconType[];

	private static final String[] itemType = new String[] { "gratedapple", "gratedpeach", "honeylemon",
			"roastedcoffeepowder", "ganache", "gratedlime", "gratedtomato", "gratedberry", "gratedgrape", "orangeslice" };

	public ItemGratedApple(int reco, int sat, boolean flag) {
		super(reco, sat, flag);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setMaxStackSize(64);

	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		int j = MathHelper.clamp_int(par1, 0, 9);
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
		par3List.add(new ItemStack(this, 1, 9));
		par3List.add(new ItemStack(this, 1, 3));
		par3List.add(new ItemStack(this, 1, 4));
		par3List.add(new ItemStack(this, 1, 5));
		par3List.add(new ItemStack(this, 1, 6));
		par3List.add(new ItemStack(this, 1, 7));
		par3List.add(new ItemStack(this, 1, 8));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.iconType = new IIcon[10];

		for (int i = 0; i < 10; ++i) {
			this.iconType[i] = par1IconRegister.registerIcon("defeatedcrow:" + itemType[i]);
		}
	}

}
