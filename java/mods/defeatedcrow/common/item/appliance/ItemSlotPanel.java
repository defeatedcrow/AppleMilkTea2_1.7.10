package mods.defeatedcrow.common.item.appliance;

import java.util.List;

import mods.defeatedcrow.api.appliance.IProcessorPanel;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSlotPanel extends Item implements IProcessorPanel {

	public ItemSlotPanel() {
		super();
		this.setMaxStackSize(1);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:tools/slotpanel");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		if (par1ItemStack != null && DCsAppleMilk.proxy.isShiftKeyDown()) { // shiftキー押下時
			par3List.add("This item is put in the slot of Jaw Crusher.");
			par3List.add("If you do it, this item behaves as the empty slot.");
		} else {
			par3List.add(EnumChatFormatting.ITALIC + "LShift: Expand tooltip.");
		}
	}

}
