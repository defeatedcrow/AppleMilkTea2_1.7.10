package mods.defeatedcrow.handler;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyConfigHelper {
	private KeyConfigHelper() {
	}

	public static int getJumpKey() {
		return Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode();
	}

	public static int getFowardKey() {
		return Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode();
	}

	public static int getBackKey() {
		return Minecraft.getMinecraft().gameSettings.keyBindBack.getKeyCode();
	}

	public static int getLeftKey() {
		return Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode();
	}

	public static int getRightKey() {
		return Minecraft.getMinecraft().gameSettings.keyBindRight.getKeyCode();
	}

	public static int getSneakKey() {
		return Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode();
	}

}
