package mods.defeatedcrow.common;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLLog;

public class AMTLogger {

	public static Logger logger = LogManager.getLogger("AppleMilkTea");

	public static void loadingModInfo(String modid) {
		AMTLogger.logger.trace("Now checking other mod :" + modid);
	}

	public static void loadedModInfo(String modid) {
		AMTLogger.logger.trace("Succeeded to check other mod :" + modid);
	}

	public static void failLoadingModInfo(String modid) {
		AMTLogger.logger.error("Failed to check other mod :" + modid);
	}

	public static void trace(String msg) {
		AMTLogger.logger.trace(msg);
	}

	public static void info(String msg) {
		AMTLogger.logger.info(msg);
	}

	public static void warn(String msg) {
		AMTLogger.logger.warn(msg);
	}

	public static void debugInfo(String msg) {
		if (DCsAppleMilk.debugMode) {
			AMTLogger.logger.info("Debug: " + msg);
		}
	}

}
