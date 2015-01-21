package mods.defeatedcrow.common;

import java.io.IOException;
import java.util.List;

import cpw.mods.fml.relauncher.CoreModManager;

public class RequiredCoreModChecker {
	
	private static boolean completed = false;
	private final String BR = System.getProperty("line.separator");
	
	public boolean isCompleted()
	{
		return this.completed;
	}
	
	public void coreModCheck()
	{
		//まずはコアモッドのチェック
		List<String> loaded = CoreModManager.getLoadedCoremods();
		for (String name : loaded)
		{
			AMTLogger.debugInfo("this CoreMod Name : " + name);
			if (name.equalsIgnoreCase("PotionExtension-1.0.3-forMC1.7.jar"))
			{
				this.completed = true;
				AMTLogger.info("Required CoreMod was loaded successfully : PotionExtension-1.0.3.jar");
			}
		}
		
		//未導入の場合
		if (!this.completed)
		{
			AMTLogger.warn("Failed to checking required CoreMod : PotionExtension-1.0.3.jar");
			AMTLogger.warn("Please restart the client, or get to download PotionExtensionCore from the link : ");
			AMTLogger.warn("http://forum.minecraftuser.jp/viewtopic.php?f=13&t=6672");
		}
		
	}

}
