package mods.defeatedcrow.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class DCsNetworkHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("DCsAppleMilk");

	public static void init() {
		INSTANCE.registerMessage(MessageHandlerCharmWarp.class, MessageCharmWarp.class, 0, Side.SERVER);
	}
}
