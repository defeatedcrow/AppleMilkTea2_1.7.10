package mods.defeatedcrow.common;

import java.util.List;

import cpw.mods.fml.relauncher.CoreModManager;

public class RequiredCoreModChecker {

	private RequiredCoreModChecker() {
	}

	private static boolean completed = false;
	private final String BR = System.getProperty("line.separator");

	public static final String VERSION = "1.7.10-1.1.0";
	public static final String FILE_NAME = "PotionExtension-1.7.10-1.1.0.jar";

	public static boolean getCompleted() {
		return completed;
	}

	public static void coreModCheck() {
		// まずはコアモッドのチェック
		List<String> loaded = CoreModManager.getLoadedCoremods();
		for (String name : loaded) {
			AMTLogger.debugInfo("this CoreMod Name : " + name);
			if (name.equalsIgnoreCase(FILE_NAME)) {
				completed = true;
				AMTLogger.info("Required CoreMod was loaded successfully : " + FILE_NAME);
			} else if (name.contains("ExtendedPotions")) {
				completed = true;
				AMTLogger.info("Found another potion extension mod. : " + "ExtendedPotions");
			}
		}

		// 未導入の場合
		if (!completed) {
			AMTLogger.warn("Failed to checking required CoreMod : " + FILE_NAME);
			AMTLogger.warn("Please restart the client, or get to download PotionExtensionCore from the link : ");
			AMTLogger.warn("http://forum.minecraftuser.jp/viewtopic.php?f=13&t=6672");
		}

	}

}
