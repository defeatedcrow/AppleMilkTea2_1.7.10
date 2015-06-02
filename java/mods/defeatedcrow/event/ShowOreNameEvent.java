package mods.defeatedcrow.event;

import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ShowOreNameEvent {

	private ArrayList<String> ores = new ArrayList<String>();
	private ArrayList<String> fluids = new ArrayList<String>();

	@SubscribeEvent
	public void advancedTooltip(ItemTooltipEvent event) {
		EntityPlayer player = event.entityPlayer;
		ItemStack target = event.itemStack;
		boolean flag = false;

		if (player != null && player instanceof EntityPlayerSP && target != null) {
			ItemStack met = player.inventory.armorInventory[3];// 頭装備
			if (met != null && met.getItem() == DCsAppleMilk.monocle) {
				this.ores = this.getOre(target);
				this.fluids = this.getFluidName(target);

				event.toolTip.addAll(ores);
				event.toolTip.addAll(fluids);
				flag = true;
			}
		}
	}

	@SideOnly(Side.CLIENT)
	private ArrayList<String> getOre(ItemStack item) {
		ArrayList<String> ore = new ArrayList<String>();

		int[] id = OreDictionary.getOreIDs(item);
		if (id != null && id.length > 0) {
			for (int i = 0; i < id.length; i++) {
				String s = OreDictionary.getOreName(id[i]);
				ore.add(s);
			}
		} else {
			ore.add("No ore dictionary name");
		}

		return ore;
	}

	@SideOnly(Side.CLIENT)
	private ArrayList<String> getFluidName(ItemStack item) {
		ArrayList<String> fluid = new ArrayList<String>();

		FluidStack f = FluidContainerRegistry.getFluidForFilledItem(item);
		if (f != null && f.getFluid() != null && f.amount > 0) {
			fluid.add("Fluid container : " + f.getLocalizedName() + " " + f.amount);
			fluid.add("Fluid unlocalized name : " + f.getUnlocalizedName());
		} else {
			fluid.add("Not fluid container");
		}

		return fluid != null ? fluid : null;
	}

}
