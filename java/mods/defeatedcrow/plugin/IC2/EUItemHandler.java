package mods.defeatedcrow.plugin.IC2;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

// EU使用ツール、電池のための中継クラス
public class EUItemHandler {

	private EUItemHandler() {
	}

	public static boolean isChargeable(ItemStack item) {
		if (item == null)
			return false;

		if (item.getItem() instanceof IElectricItem) {
			IElectricItem bat = (IElectricItem) item.getItem();
			if (bat.getTier(item) < 4 && ElectricItem.manager.getCharge(item) < bat.getMaxCharge(item)) {
				return true;
			}
		}

		return false;
	}

	public static int getAmount(ItemStack item) {
		if (item == null)
			return 0;

		if (item.getItem() instanceof IElectricItem) {
			IElectricItem bat = (IElectricItem) item.getItem();
			return MathHelper.floor_double(ElectricItem.manager.getCharge(item));
		}

		return 0;
	}

	public static int chargeAmount(ItemStack item, int get, boolean isSimulate) {
		if (item == null)
			return 0;

		if (item.getItem() instanceof IElectricItem) {
			IElectricItem bat = (IElectricItem) item.getItem();
			if (bat.getTier(item) < 4 || ElectricItem.manager.getCharge(item) < bat.getMaxCharge(item)) {
				double ret = ElectricItem.manager.charge(item, (double) get, 3, false, isSimulate);
				int i = MathHelper.floor_double(ret);

				return i;
			}
		}

		return 0;
	}

	public static int dischargeAmount(ItemStack item, int ret, boolean isSimulate) {
		if (item == null)
			return 0;

		if (item.getItem() instanceof IElectricItem) {
			IElectricItem bat = (IElectricItem) item.getItem();
			if (bat.getTier(item) < 4 || ElectricItem.manager.getCharge(item) >= ret) {
				double get = ElectricItem.manager.discharge(item, (double) ret, 3, false, isSimulate, isSimulate);
				int i = MathHelper.floor_double(get);

				return i;
			}
		}

		return 0;
	}

}
