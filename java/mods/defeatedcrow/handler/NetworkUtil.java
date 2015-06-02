package mods.defeatedcrow.handler;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.server.FMLServerHandler;
import net.minecraft.server.MinecraftServer;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;

public class NetworkUtil {

	private NetworkUtil() {
	}

	// プレイヤーがクライアント側で呼ぶ処理
	public static void initClientMP() {
		if (MinecraftServer.getServer() != null) {
			if (MinecraftServer.getServer().isSinglePlayer()) {
				AMTLogger.debugInfo("Recognized to Single Mode.");
				NetworkUtilServer.INSTANCE.setIngratedServerMode();
			} else {
				AMTLogger.debugInfo("Recognized to Server Mode.");
				String name = MinecraftServer.getServer().getServerOwner();
				if (name == null) {
					name = "unknown";
				}
				boolean online = MinecraftServer.getServer().isServerInOnlineMode();
				boolean nether = MinecraftServer.getServer().getAllowNether();
				boolean pvp = MinecraftServer.getServer().isPVPEnabled();
				NetworkUtilServer.INSTANCE.setServerMode(name, online, nether, pvp);
			}
		} else// 恐らくマルチではこれになる。getServer()でnullしかかえってこない
		{
			AMTLogger.warn("Failed to recognize Minecraft Server. It will not work correctly.");
			NetworkUtilServer.INSTANCE.setServerMode("unknown", false, true, true);
		}
	}

	@SideOnly(Side.SERVER)
	public static void initServer() {
		if (FMLServerHandler.instance().getServer() != null) {
			AMTLogger.debugInfo("Recognized to Server Mode.");
			String name = FMLServerHandler.instance().getServer().getServerOwner();
			if (name == null) {
				name = "unknown";
			}
			boolean online = FMLServerHandler.instance().getServer().isServerInOnlineMode();
			boolean nether = FMLServerHandler.instance().getServer().getAllowNether();
			boolean pvp = FMLServerHandler.instance().getServer().isPVPEnabled();
			NetworkUtilServer.INSTANCE.setServerMode(name, online, nether, pvp);
		} else {
			AMTLogger.warn("Failed to recognize Minecraft Server. It will not work correctly.");
			NetworkUtilServer.INSTANCE.setServerMode("unknown", false, false, false);
		}
	}
}
