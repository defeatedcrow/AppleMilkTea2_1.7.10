package mods.defeatedcrow.common.block.container;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemWipeBox2 extends ItemBlock {

	public ItemWipeBox2(Block block) {
		super(block);
		setMaxDamage(5000);
		setHasSubtypes(false);
		this.setMaxStackSize(1);
	}

	@SideOnly(Side.CLIENT)
	/**
	 * allows items to add custom lines of information to the mouseover description
	 */
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		short l = (short) (5000 - par1ItemStack.getItemDamage());
		if (l < 0)
			l = 0;
		par3List.add(new String("count: " + l));
	}

}
