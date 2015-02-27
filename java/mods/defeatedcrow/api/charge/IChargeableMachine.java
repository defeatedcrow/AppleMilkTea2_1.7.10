package mods.defeatedcrow.api.charge;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * chargeを消費するTileEntityに実装されているメソッド。
 * <br>あくまでチャージ消費Tileの情報閲覧用であり、このインターフェイスを継承しても、チャージを受け取れるようにはならないので注意。
 * <br>チャージ消費装置を他MODから追加する手段は今のところ用意していない。
 * */
public interface IChargeableMachine {
	
	boolean isActive();
	
	int getChargeAmount();
	
	int getMaxChargeAmount();
	
	boolean canReceiveChargeItem(ItemStack item);

}
