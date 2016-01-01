package mods.defeatedcrow.api.appliance;

import net.minecraft.item.ItemStack;

public interface IJawPlate {

	ItemStack returnItem(ItemStack item);

	int getTier(ItemStack item);

}
