package mods.defeatedcrow.common.block.appliance;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPanG extends ItemAppliance {

	public ItemPanG(Block block) {
		super(block);
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		NBTTagCompound nbt = par1ItemStack.getTagCompound();
		String disp = "Empty";
		short s = 0;
		boolean flag = false;
		if (nbt != null && nbt.hasKey("input")) {
			disp = nbt.getString("display");
			s = nbt.getByte("remain");
			flag = true;
		}
		if (flag) {
			par3List.add(new String("Type : " + disp));
			par3List.add(new String("Amount : " + s));
		}

	}

}
