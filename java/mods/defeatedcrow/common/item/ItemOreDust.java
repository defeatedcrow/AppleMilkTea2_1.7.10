package mods.defeatedcrow.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemOreDust extends Item {

	@SideOnly(Side.CLIENT)
	private IIcon iconType[];

	private static final String[] itemType = new String[] { "iron", "tin", "copper", "silver", "lead", "gold",
			"nickel", "platinum" };

	public ItemOreDust() {
		super();
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setMaxStackSize(64);

	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		int j = MathHelper.clamp_int(par1, 0, 7);
		return this.iconType[j];
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int meta = par1ItemStack.getItemDamage();
		return meta < 8 ? super.getUnlocalizedName() + "_" + this.itemType[meta] : super.getUnlocalizedName() + "_"
				+ meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < 8; i++) {
			par3List.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.iconType = new IIcon[8];

		for (int i = 0; i < 8; ++i) {
			this.iconType[i] = par1IconRegister.registerIcon("defeatedcrow:oredust_" + itemType[i]);
		}
	}
}
