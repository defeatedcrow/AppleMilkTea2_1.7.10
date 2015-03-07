package mods.defeatedcrow.common;

import java.io.IOException;
import java.util.List;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.relauncher.CoreModManager;

public class RequiredCoreModChecker {
	
	private RequiredCoreModChecker(){}
	
	private static boolean completed = false;
	private final String BR = System.getProperty("line.separator");
	
	public static boolean getCompleted()
	{
		return completed;
	}
	
	public static void coreModCheck()
	{
		//まずはコアモッドのチェック
		List<String> loaded = CoreModManager.getLoadedCoremods();
		for (String name : loaded)
		{
			AMTLogger.debugInfo("this CoreMod Name : " + name);
			if (name.equalsIgnoreCase("PotionExtension-1.0.4-forMC1.7.jar"))
			{
				completed = true;
				AMTLogger.info("Required CoreMod was loaded successfully : PotionExtension-1.0.4.jar");
			}
		}
		
		//未導入の場合
		if (!completed)
		{
			AMTLogger.warn("Failed to checking required CoreMod : PotionExtension-1.0.4forMC1.7.jar");
			AMTLogger.warn("Please restart the client, or get to download PotionExtensionCore from the link : ");
			AMTLogger.warn("http://forum.minecraftuser.jp/viewtopic.php?f=13&t=6672");
		}
		
	}
	

}
