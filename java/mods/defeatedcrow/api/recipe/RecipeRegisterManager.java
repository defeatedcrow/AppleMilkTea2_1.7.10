package mods.defeatedcrow.api.recipe;

public final class RecipeRegisterManager {

	public static IIceRecipeRegister iceRecipe;
	public static ITeaRecipeRegister teaRecipe;
	public static IProcessorRecipeRegister processorRecipe;
	public static IEvaporatorRecipeRegister evaporatorRecipe;
	public static IPanRecipeRegister panRecipe;
	/**
	 * This is old instance. <br>
	 * It was changed to IFondueRegister, to deal with recipes other than chocolate fondue.
	 */
	public static IChocoFruitsRecipe chocoRecipe;
	public static IFondueRegister fondueRecipe;
	public static IPlateRecipeRegister plateRecipe;

	public static ISlagResultLoot slagLoot;

	private RecipeRegisterManager() {
	}

}
