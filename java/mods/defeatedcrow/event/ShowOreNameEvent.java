package mods.defeatedcrow.event;

import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ShowOreNameEvent {
	
	private ArrayList<String> ore = new ArrayList<String>();
	
	@SubscribeEvent
	public void advancedTooltip(ItemTooltipEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		ItemStack target = event.itemStack;
		boolean flag = false;
		
		if (player != null && player instanceof EntityPlayerSP && target != null)
		{
			ItemStack met = player.inventory.armorInventory[3];//頭装備
			if (met != null && met.getItem() == DCsAppleMilk.monocle)
			{
				this.ore = this.getOre(target);
				event.toolTip.addAll(ore);
				flag = true;
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	private ArrayList<String> getOre(ItemStack item)
	{
		ArrayList<String> ore = new ArrayList<String>();
		
		int[] id = OreDictionary.getOreIDs(item);
		if (id != null && id.length > 0)
		{
			for (int i = 0 ; i < id.length ; i++)
			{
				String s = OreDictionary.getOreName(id[i]);
				ore.add(s);
			}
		}
		else
		{
			ore.add("unknown");
		}
		
		return ore;
	}

}
