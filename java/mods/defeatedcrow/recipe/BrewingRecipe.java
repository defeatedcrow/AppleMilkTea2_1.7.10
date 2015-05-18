package mods.defeatedcrow.recipe;

import java.util.HashMap;
import java.util.Map;

import mods.defeatedcrow.api.recipe.IBrewingRecipe;
import net.minecraftforge.fluids.Fluid;

public class BrewingRecipe implements IBrewingRecipe {

	public final static BrewingRecipe instance = new BrewingRecipe();

	BrewingRecipe() {
	}

	public static Map<Fluid, Fluid> recipe = new HashMap<Fluid, Fluid>();

	@Override
	public void registerRecipe(Fluid input, Fluid output) {
		if (input != null) {
			if (output != null) {
				recipe.put(input, output);
			}
		}
	}

	@Override
	public Map<Fluid, Fluid> recipeMap() {
		return this.recipe;
	}

}
