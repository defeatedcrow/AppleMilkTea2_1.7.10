package mods.defeatedcrow.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import mods.defeatedcrow.api.charge.*;
import mods.defeatedcrow.common.AMTLogger;

public class ChargeItemRegister implements IChargeItemRegister {

	private static List<ChargeItem> chargeItems;

	public ChargeItemRegister() {
		this.chargeItems = new ArrayList<ChargeItem>();
	}

	public IChargeItemRegister instance() {
		return ChargeItemManager.chargeItem;
	}

	@Override
	public List<? extends IChargeItem> getChargeItemList() {
		return this.chargeItems;
	}

	@Override
	public int getChargeAmount(ItemStack item) {
		if (item == null)
			return 0;
		for (ChargeItem chargeable : this.chargeItems) {
			if (this.isItemEqual(item, chargeable.getItem())) {
				return chargeable.chargeAmount();
			}
		}
		return 0;
	}

	private boolean isItemEqual(ItemStack a, ItemStack b) {
		boolean flag = false;
		if (a.getItem() == b.getItem()) {
			if (a.getItemDamage() == b.getItemDamage()) {
				flag = true;
			} else if (a.getItemDamage() == Short.MAX_VALUE) {
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public void registerCharger(ItemStack input, ItemStack output, int val) {
		if (input != null) {
			if (input.isItemStackDamageable()) {
				this.chargeItems.add(new ChargeItem(input, output, 0));
				AMTLogger.debugInfo("Add damageable chargeable item: input " + input.getDisplayName());
			} else if (val > 0) {
				this.chargeItems.add(new ChargeItem(input, output, val));
				AMTLogger
						.debugInfo("Add damageable chargeable item: input " + input.getDisplayName() + " val : " + val);
			}
		}

	}

	public class ChargeItem implements IChargeItem {

		private final ItemStack input;
		private final ItemStack output;
		private final int amount;

		public ChargeItem(ItemStack item, ItemStack output, int val) {
			this.input = item;
			this.amount = val;
			this.output = output;
		}

		@Override
		public int chargeAmount() {
			return this.amount;
		}

		@Override
		public ItemStack getItem() {
			if (this.input == null)
				return null;
			return this.input;
		}

		@Override
		public ItemStack returnItem() {
			if (this.output == null)
				return null;
			return this.output;
		}

	}

}
