package mods.defeatedcrow.common.block.energy;

import java.util.List;

import mods.defeatedcrow.api.charge.ChargeItemManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.PropertyHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBatBox extends ItemBlock {

	public ItemBatBox(Block block) {
		super(block);
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		NBTTagCompound nbt = par1ItemStack.getTagCompound();
		int s = 0;
		if (nbt != null && nbt.hasKey("charge")) {
			s = nbt.getInteger("charge");
		} else {
			s = ChargeItemManager.chargeItem.getChargeAmount(new ItemStack(this));
		}
		par3List.add("Charge Amount : " + s);

		if (DCsAppleMilk.proxy.isShiftKeyDown()) { // shiftキー押下時
			int vsRF = s * PropertyHandler.rateRF();
			int vsEU = s * PropertyHandler.rateEU();
			int vsGF = s * PropertyHandler.rateGF();
			par3List.add(vsRF + " RF");
			par3List.add(vsEU + " EU");
			par3List.add(vsGF + " GF");
		} else {
			par3List.add(EnumChatFormatting.ITALIC + "LShift: Expand tooltip.");
		}
	}
}
