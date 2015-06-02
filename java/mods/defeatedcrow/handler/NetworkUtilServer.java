package mods.defeatedcrow.handler;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * client、server共通の鯖データ保存クラス。
 * プレイヤー:ログイン時に取得
 * サーバ:起動時にサーバのプロパティを別クラスで取得
 */
public class NetworkUtilServer {

	public static final NetworkUtilServer INSTANCE = new NetworkUtilServer();

	private NetworkUtilServer() {
	}

	private static boolean isOnlineMode;
	private static boolean allowedNether;
	private static boolean allowedPvP;
	private static String ownerName;

	private static boolean isIntegratedServer = false;

	public void setServerMode(String owner, boolean online, boolean nether, boolean pvp) {
		this.ownerName = owner;
		this.isOnlineMode = online;
		this.allowedNether = nether;
		this.allowedPvP = pvp;

		AMTLogger.info("Current server property...");
		AMTLogger.info("Owner : " + this.ownerName);
		AMTLogger.info("Online Mode : " + this.isOnlineMode);
		AMTLogger.info("Enabled Nether : " + this.allowedNether);
		AMTLogger.info("Enabled PvP : " + this.allowedPvP);
	}

	public void setIngratedServerMode() {
		this.ownerName = FMLClientHandler.instance().getServer().getServerOwner();
		if (this.ownerName.equalsIgnoreCase("ForgeDevName")) {
			AMTLogger.info("Recognized the Forge Development Server.　Starting the debug mode.");
			this.isIntegratedServer = true;
			this.isOnlineMode = true;
			this.allowedNether = true;
			this.allowedPvP = true;
			DCsAppleMilk.debugMode = true;
		} else if (FMLClientHandler.instance().getServer() instanceof IntegratedServer) {
			AMTLogger.info("Recognized Integrated Server. It's only runnable in Client.");
			this.isIntegratedServer = true;
			this.isOnlineMode = true;
			this.allowedNether = true;
			this.allowedPvP = true;
		} else {
			AMTLogger.info("Failed to recognize Minecraft Server. What this?");
			this.isIntegratedServer = false;
			this.isOnlineMode = false;
			this.allowedNether = true;
			this.allowedPvP = true;
		}
		AMTLogger.info("Owner : " + this.ownerName);
		AMTLogger.info("Online Mode : " + this.isOnlineMode);
		AMTLogger.info("Enabled Nether : " + this.allowedNether);
		AMTLogger.info("Enabled PvP : " + this.allowedPvP);
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public boolean isOnlineMode() {
		return this.isOnlineMode;
	}

	public boolean enableNether() {
		return this.allowedNether;
	}

	public boolean enablePvP() {
		return this.allowedPvP;
	}

	public boolean isIntegraterServer() {
		return this.isIntegratedServer;
	}
}
