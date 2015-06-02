package mods.defeatedcrow.api.charge;

import net.minecraft.item.ItemStack;

/**
 * chargeを消費するTileEntityに実装されているメソッド。 <br>
 * あくまでチャージ消費Tileの情報閲覧用であり、このインターフェイスを継承しても、チャージを受け取れるようにはならないので注意。 <br>
 * チャージ消費装置を他MODから追加する手段は今のところ用意していない。 <br>
 * (利用は可能ですが推奨しません。あくまでdefeatedcrow製の装置のためのインターフェイスです。)
 */
public interface IChargeableMachine {

	/**
	 * 装置がクラフト処理中であるか
	 */
	boolean isActive();

	int getChargeAmount();

	int getMaxChargeAmount();

	/**
	 * 装置の燃料スロットに投入できる電池アイテムであり、 <br>
	 * さらに電池のチャージ量にたいして装置の残量が十分に減っていればtrue
	 */
	boolean canReceiveChargeItem(ItemStack item);

	/**
	 * 装置のチャージを増加させる。
	 * 実際に増加できる値が返る。 <br>
	 * isSimulateがtrueの場合、実際の増加処理は行わない。
	 */
	int addCharge(int amount, boolean isSimulate);

	/**
	 * 装置のチャージを減少させる。
	 * 実際に減少できる値が返る。 <br>
	 * isSimulateがtrueの場合、実際の処理は行わない。
	 */
	int extractCharge(int amount, boolean isSimulate);

}
