package mods.defeatedcrow.event;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.plugin.LoadModHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EatFoodEvent {

	@SubscribeEvent
	public void eatFinishEvent(PlayerUseItemEvent.Finish event) {
		EntityPlayer player = event.entityPlayer;
		ItemStack food = event.item;
		ItemStack target = LoadModHandler.getItem("DCsBakedApple");
		AMTLogger.debugInfo("now twsting");

		if (player != null && food != null && target != null) {
			AMTLogger.debugInfo("this item name : " + food.getDisplayName());
			if (food.getItem() == target.getItem()) {
				AMTLogger.debugInfo("result true");
			}
		}
	}

}
