package mods.defeatedcrow.common.block.energy;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBatBox extends ItemBlock{

	public ItemBatBox(Block block) {
		super(block);
	}
	
	@SideOnly(Side.CLIENT)
    //マウスオーバー時の表示情報
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		NBTTagCompound nbt = par1ItemStack.getTagCompound();
		int s = 0;
		if (nbt != null && nbt.hasKey("charge"))
		{
			s = nbt.getInteger("charge");
		}
		par3List.add(new String("Charge Amount : " + s));
	}
}
