package mods.defeatedcrow.client.particle;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * パーティクル用の画像登録クラス
 */
public class ParticleTex {

	private static ParticleTex instance;

	private ParticleTex() {
	}

	private static String[] iconNames = {
			"blink",
			"orb",
			"cloud",
			"flower",
			"feather" };
	static IIcon icons[];

	public static ParticleTex getInstance() {
		if (instance == null) {
			instance = new ParticleTex();
		}

		return instance;
	}

	/**
	 * パーティクル用の画像をまとめて登録
	 * ブロックやアイテムと異なりEntityFXはregisterIconメソッドがないのでTextureStitchEvent.Preイベントをフックして登録します
	 */
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void handleTextureRemap(TextureStitchEvent.Pre event) {
		if (event.map.getTextureType() == 1) {
			this.getInstance().registerIcons(event.map);
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		icons = new IIcon[iconNames.length];
		for (int i = 0; i < icons.length; ++i) {
			icons[i] = par1IconRegister.registerIcon("defeatedcrow:particle_" + iconNames[i]);
		}
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(String iconName) {
		for (int i = 0; i < iconNames.length; ++i) {
			if (iconName.equalsIgnoreCase(iconNames[i])) {
				return DCsAppleMilk.icyCrystal.getIconFromDamage(i + 1);
			}
		}
		return null;
	}

}
