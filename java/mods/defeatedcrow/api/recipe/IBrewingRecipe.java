package mods.defeatedcrow.api.recipe;

import java.util.Map;

import net.minecraftforge.fluids.Fluid;

/**
 * Brewing Barrel にレシピを追加するAPI。
 * input amount : output amount = 1 : 1
 */
public interface IBrewingRecipe {

	void registerRecipe(Fluid input, Fluid output);

	Map<Fluid, Fluid> recipeMap();

}
