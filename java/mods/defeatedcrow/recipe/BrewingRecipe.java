package mods.defeatedcrow.recipe;

import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class BrewingRecipe {
	
	private BrewingRecipe(){}
	
	public static Map<Fluid, Fluid> recipe = new HashMap<Fluid, Fluid>();
	
	public static void registerRecipe(Fluid input, Fluid output)
	{
		if (input != null)
		{
			if (output != null)
			{
				recipe.put(input, output);
			}
		}
	}

}
