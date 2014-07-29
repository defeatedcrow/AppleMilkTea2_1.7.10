package mods.defeatedcrow.recipe;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;

public class RegisterManager {
	
	public static void load()
	{
		RecipeRegisterManager.teaRecipe = new TeaRecipeRegister();
		RecipeRegisterManager.iceRecipe = new IceRecipeRegister();
	}
	
	private RegisterManager(){}

}
