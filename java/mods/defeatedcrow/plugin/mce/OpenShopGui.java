package mods.defeatedcrow.plugin.mce;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import shift.mceconomy2.api.MCEconomyAPI;

/**
 * MOD本体で他MODのクラスをインポートしないためのクッション用クラス
 * MCEconomy導入済みかどうかの判定も行う
 */
public class OpenShopGui {

	/**
	 * 当MODのショップGuiを開く。未導入時には何もしない。
	 * 
	 * @param player
	 *            Guiを開いたプレイヤー
	 * @param world
	 *            現在のワールド
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void openShopGui(EntityPlayer player, World world, int x, int y, int z) {
		if (DCsAppleMilk.SuccessLoadEconomy && MCEconomyPlugin.DCshopId > 0) {
			MCEconomyAPI.openShopGui(MCEconomyPlugin.DCshopId, player, world, x, y, z);
		}
	}

	public static void openOldShopGui(EntityPlayer player, World world, int x, int y, int z) {
		if (DCsAppleMilk.SuccessLoadEconomy && MCEOldPlugin.OldshopId > 0) {
			MCEconomyAPI.openShopGui(MCEOldPlugin.OldshopId, player, world, x, y, z);
		}
	}

	/**
	 * MPの加算用メソッド
	 * 
	 * @param player
	 *            対象プレイヤー
	 * @param amount
	 *            量
	 */
	public static void addMP(EntityPlayer player, int amount) {
		if (DCsAppleMilk.SuccessLoadEconomy && amount > 0) {
			MCEconomyAPI.addPlayerMP(player, amount, true);
		}
	}

	/**
	 * MPの減算用メソッド
	 * 
	 * @param player
	 *            対象プレイヤー
	 * @param amount
	 *            量
	 */
	public static void reduceMP(EntityPlayer player, int amount) {
		if (DCsAppleMilk.SuccessLoadEconomy && amount > 0) {
			MCEconomyAPI.reducePlayerMP(player, amount, true);
		}
	}

}
