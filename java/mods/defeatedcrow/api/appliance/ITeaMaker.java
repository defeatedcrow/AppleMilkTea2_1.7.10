package mods.defeatedcrow.api.appliance;

import net.minecraft.item.ItemStack;
import mods.defeatedcrow.api.recipe.ITeaRecipe;

public interface ITeaMaker {
	
	public ITeaRecipe getRecipe();
	
	public byte getRemain();
	
	public boolean getMilked();
	
	public boolean canSetRecipe(ItemStack item);
	
	public void setRecipe(ItemStack item);
	
	public void setRemain(byte rem);
	
	public void setMilk(boolean flag);
	
	public ItemStack getOutput();

}
