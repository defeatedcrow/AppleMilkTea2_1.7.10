package mods.defeatedcrow.plugin.ffm;

import java.util.List;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemChalcedonyCircuit extends Item {

	public ItemChalcedonyCircuit() {
		super();
		maxStackSize = 64;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:plugins/circuit_chalcedony");
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		if (DCsAppleMilk.proxy.isShiftKeyDown()) { // shiftキー押下時
			par3List.add(EnumChatFormatting.WHITE.toString() + EnumChatFormatting.UNDERLINE + "Manual Farms");
			par3List.add("AMT shrubs plantation");
		} else {
			par3List.add(EnumChatFormatting.ITALIC + "LShift: Expand tooltip.");
		}
	}

}
