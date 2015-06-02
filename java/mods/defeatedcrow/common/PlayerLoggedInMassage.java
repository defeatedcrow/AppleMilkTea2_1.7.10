package mods.defeatedcrow.common;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class PlayerLoggedInMassage {

	private boolean chatCompleted = false;

	@SubscribeEvent
	/* メッセージ用 */
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (!chatCompleted && event.entity instanceof EntityPlayer && event.entity.worldObj.isRemote) {
			if (!RequiredCoreModChecker.getCompleted()) {
				chatCompleted = true;
				EntityPlayer player = (EntityPlayer) event.entity;
				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA + "["
						+ DCsAppleMilk.instance.getModName() + "] " + "Required Core Mod ("
						+ RequiredCoreModChecker.FILE_NAME + ") was not loaded."));
				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.AQUA
						+ "If this is a first start, please restart the client."));
			}
		}
	}

}
