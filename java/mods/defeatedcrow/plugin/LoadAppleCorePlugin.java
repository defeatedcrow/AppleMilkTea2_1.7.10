package mods.defeatedcrow.plugin;

import squeek.applecore.api.food.IEdible;
import squeek.applecore.api.food.ItemFoodProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class LoadAppleCorePlugin {

	public static void addFoodStatus(EntityPlayer player, ItemStack food) {
		if (food.getItem() instanceof IEdible) {
			IEdible edible = (IEdible) food.getItem();
			player.getFoodStats().func_151686_a(new ItemFoodProxy(edible), food);
		}
	}

}
