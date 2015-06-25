package mods.defeatedcrow.plugin.nei;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

@Mod(
		modid = "DCsNEIPluginAMT",
		name = "NEIPluginForAMT",
		version = "1.7.10_1.0a",
		dependencies = "required-after:Forge@[10.13.2.1291,);required-after:DCsAppleMilk")
public class NEIPluginAMTCore {

	// プロキシの登録
	@SidedProxy(
			clientSide = "mods.defeatedcrow.plugin.nei.ClientProxyNPA",
			serverSide = "mods.defeatedcrow.plugin.nei.CommonProxyNPA")
	public static CommonProxyNPA proxy;

	// インスタンスの生成
	@Instance("DCsNEIPluginAMT")
	public static NEIPluginAMTCore instance;

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.loadNEI();
	}

}
