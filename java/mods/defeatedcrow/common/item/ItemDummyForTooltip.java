package mods.defeatedcrow.common.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDummyForTooltip extends Item {

	public ItemDummyForTooltip() {
		super();
		this.setMaxStackSize(1);
	}

	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		NBTTagCompound nbt = par1ItemStack.getTagCompound();
		String name = "Empty";
		short s = 0;
		if (nbt != null && nbt.hasKey("fluid")) {
			name = nbt.getString("fluid");
		}
		if (nbt != null && nbt.hasKey("amount")) {
			s = nbt.getShort("amount");
		}
		par3List.add(new String(name + " " + s + "mB"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:dummy");
	}
}
