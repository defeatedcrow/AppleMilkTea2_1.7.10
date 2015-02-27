package mods.defeatedcrow.api.charge;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * chargeを発生させるTileEntityに実装するメソッド。
 * <br>なお、このMODのチャージエネルギーは基本的に様々なMODのエネルギーを仲介するためのAMT2専用のエネルギーなので、
 * 他MODがチャージを受け取ることは想定していない。
 * <br>（連携目的であれば、RFやEUなどの他の工業エネルギーを利用することをお勧めする。
 *  それで自動的にAMTにも対応できる。）
 * */
public interface IChargeGenerator {
	
	boolean canGenerate();
	
	int generateCharge(ForgeDirection dir, boolean simulate);

}
