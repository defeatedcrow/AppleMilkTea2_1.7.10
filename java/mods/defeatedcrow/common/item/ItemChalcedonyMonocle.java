package mods.defeatedcrow.common.item;

import java.util.List;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemChalcedonyMonocle extends ItemArmor {

	public ItemChalcedonyMonocle(ArmorMaterial material, int index, int slot) {
		super(material, index, slot);
	}

	@Override
	public String getArmorTexture(ItemStack item, Entity entity, int slot, String type) {
		if (item.getItem() == DCsAppleMilk.monocle) {
			return "defeatedcrow:textures/armor/monocle.png";
		}
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:tools/monocle");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		if (par1ItemStack != null && DCsAppleMilk.proxy.isShiftKeyDown()) { // shiftキー押下時
			par3List.add("While you are wearing this item, ore dictionary names is displayed in it's tooltip.");
		} else {
			par3List.add(EnumChatFormatting.ITALIC + "LShift: Expand tooltip.");
		}
	}
}
