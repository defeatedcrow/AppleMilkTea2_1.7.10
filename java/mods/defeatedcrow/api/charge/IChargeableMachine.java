package mods.defeatedcrow.api.charge;

import net.minecraft.item.ItemStack;

public interface IChargeableMachine {
	
	public abstract boolean isActive();
	
	public abstract int getChargeAmount();
	
	public abstract boolean canReceiveChargeItem(ItemStack item);

}
