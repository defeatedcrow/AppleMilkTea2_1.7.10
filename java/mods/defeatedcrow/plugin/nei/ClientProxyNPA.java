package mods.defeatedcrow.plugin.nei;

import mods.defeatedcrow.common.AMTLogger;
import cpw.mods.fml.common.Loader;

public class ClientProxyNPA extends CommonProxyNPA {

	@Override
	public void loadNEI() {
		if (Loader.isModLoaded("NotEnoughItems")) {
			AMTLogger.loadingModInfo("NotEnoughItems");
			try {
				LoadNEIPlugin.load();
			} catch (Exception e) {
				AMTLogger.failLoadingModInfo("NotEnoughItems");
				e.printStackTrace(System.err);
			}
		}
	}

}
