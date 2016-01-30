package mods.defeatedcrow.network;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageHandlerCharmWarp implements IMessageHandler<MessageCharmWarp, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageCharmWarp message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().playerEntity;
		if (player != null) {
			player.setPositionAndUpdate(message.x + 0.5D, message.y + 1, message.z + 0.5D);
			player.fallDistance = 0.0F;
			player.worldObj.playSoundAtEntity(player, "defeatedcrow:suzu", 1.0F, 1.2F);
		}
		return null;
	}
}
