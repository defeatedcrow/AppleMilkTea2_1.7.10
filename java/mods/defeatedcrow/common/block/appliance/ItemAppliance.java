package mods.defeatedcrow.common.block.appliance;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAppliance extends ItemBlock {

	public ItemAppliance(Block block) {
		super(block);
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b) {
		super.addInformation(item, player, list, b);
		boolean flag = Loader.isModLoaded("NotEnoughItems") && Loader.isModLoaded("DCsNEIPluginAMT");
		if (flag) {
			list.add(EnumChatFormatting.ITALIC + "Push NEI Usage key : display recipes");
		}

	}

}
