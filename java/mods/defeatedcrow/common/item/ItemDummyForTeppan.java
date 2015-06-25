package mods.defeatedcrow.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDummyForTeppan extends Item {

	@SideOnly(Side.CLIENT)
	private IIcon iconItemType[];

	public ItemDummyForTeppan() {
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		int j = MathHelper.clamp_int(par1, 0, 1);
		return this.iconItemType[j];
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int j = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 1);
		return super.getUnlocalizedName() + "_" + j;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:teppan_dummy");
		this.iconItemType = new IIcon[2];
		this.iconItemType[0] = par1IconRegister.registerIcon("defeatedcrow:teppan_dummy");
		this.iconItemType[1] = par1IconRegister.registerIcon("defeatedcrow:teppan_dummy_oven");
	}
}
