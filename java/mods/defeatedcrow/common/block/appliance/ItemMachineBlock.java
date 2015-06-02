package mods.defeatedcrow.common.block.appliance;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemMachineBlock extends ItemBlock {

	public ItemMachineBlock(Block block) {
		super(block);
	}

	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		NBTTagCompound nbt = par1ItemStack.getTagCompound();
		short s = 0;
		if (nbt != null && nbt.hasKey("charge")) {
			s = nbt.getShort("charge");
		}
		par3List.add(new String("Charge Amount : " + s));
	}

}
