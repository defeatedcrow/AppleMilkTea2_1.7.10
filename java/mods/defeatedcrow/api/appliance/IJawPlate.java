package mods.defeatedcrow.api.appliance;

import net.minecraft.item.ItemStack;

/**
 * Jawplateを作成する場合に実装するインターフェイス
 */
public interface IJawPlate {

	// 一回使用される度に呼ばれるメソッド
	ItemStack returnItem(ItemStack item);

	// Tier設定
	int getTier(ItemStack item);

}
