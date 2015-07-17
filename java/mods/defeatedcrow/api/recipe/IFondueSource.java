package mods.defeatedcrow.api.recipe;

import java.util.ArrayList;

import mods.defeatedcrow.api.appliance.SoupType;
import net.minecraft.item.ItemStack;

public interface IFondueSource {

	SoupType beforeType();

	ArrayList<ItemStack> getProcessedInput();

	Object getInput();

	SoupType afterType();

	public boolean matches(ItemStack items);

}
