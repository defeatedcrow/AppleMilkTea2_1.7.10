package mods.defeatedcrow.asm;

import java.io.File;
import java.util.Map;

import mods.defeatedcrow.asm.config.DCsConfiguration;
import mods.defeatedcrow.asm.config.PropertyDC;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

/**
 * Original code was created by A.K.
 */
// @IFMLLoadingPlugin.MCVersion("1.7.10")
public class AppleMilkCorePlugin implements IFMLLoadingPlugin {

	public static boolean allowLoad = true;
	// 前提MODの設定に伴い、当コアモッドの機能を禁止。
	public static boolean forcedDisable = true;

	public static boolean dependenciesChecker = false;
	public static int range = Byte.MAX_VALUE;
	private final String BR = System.getProperty("line.separator");

	public static Logger logger = LogManager.getLogger("AppleMilkCore");

	@Override
	public String[] getASMTransformerClass() {
		return null;
		// new String[] { "mods.defeatedcrow.asm.PotionArrayEXTransformer2",
		// "mods.defeatedcrow.asm.PotionEffectTransformer" };
	}

	@Override
	public String getModContainerClass() {
		return "mods.defeatedcrow.asm.AppleMilkCoreModContainer";
	}

	@Override
	public String getSetupClass() {
		return "mods.defeatedcrow.asm.DepLoader";
	}

	@Override
	public void injectData(Map<String, Object> data) {
		// if (data.containsKey("mcLocation")) {
		// File mcLocation = (File) data.get("mcLocation");
		// File configLocation = new File(mcLocation, "config");
		// File configFile = new File(configLocation, "AppleMilkCore.cfg");
		//
		// loadConfig(configFile);
		// }
	}

	private void loadConfig(File configFile) {
		DCsConfiguration config = new DCsConfiguration(configFile);
		config.load();
		PropertyDC a = config.get("general", "EnableLoadCore", true,
				"Enable to load AppleMilkCore. If you want to disable AppleMilkCore, please set false." + BR
						+ "(For example, for avoiding crash cause of conflict with MCPC+.)");

		allowLoad = a.getBoolean(true);

		config.save();
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}
