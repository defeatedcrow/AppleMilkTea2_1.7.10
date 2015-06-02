package mods.defeatedcrow.plugin.cofh;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.item.ItemStack;

/**
 * RFを充電可能なアイテムの操作のための中継クラス。
 */
public class RFItemHandler {

	private RFItemHandler() {
	}

	public static boolean isChargeable(ItemStack item) {
		if (item == null)
			return false;

		if (item.getItem() instanceof IEnergyContainerItem) {
			IEnergyContainerItem cont = (IEnergyContainerItem) item.getItem();

			int cur = cont.getEnergyStored(item);
			int max = cont.getMaxEnergyStored(item);

			return cur < max;
		}
		return false;
	}

	public static int getAmount(ItemStack item) {
		if (item == null)
			return 0;

		if (item.getItem() instanceof IEnergyContainerItem) {
			IEnergyContainerItem cont = (IEnergyContainerItem) item.getItem();

			int cur = cont.getEnergyStored(item);

			return cur;
		}
		return 0;
	}

	public static int chargeAmount(ItemStack item, int get, boolean isSimulate) {
		if (item == null)
			return 0;

		if (item.getItem() instanceof IEnergyContainerItem) {
			IEnergyContainerItem cont = (IEnergyContainerItem) item.getItem();

			int i = cont.getMaxEnergyStored(item) - cont.getEnergyStored(item);
			int j = Math.min(i, get);

			return cont.receiveEnergy(item, j, isSimulate);
		}

		return 0;
	}

	public static int dischargeAmount(ItemStack item, int ret, boolean isSimulate) {
		if (item == null)
			return 0;

		if (item.getItem() instanceof IEnergyContainerItem) {
			IEnergyContainerItem cont = (IEnergyContainerItem) item.getItem();

			int i = cont.getEnergyStored(item);
			int j = Math.min(i, ret);

			return cont.extractEnergy(item, j, isSimulate);
		}

		return 0;
	}

}
