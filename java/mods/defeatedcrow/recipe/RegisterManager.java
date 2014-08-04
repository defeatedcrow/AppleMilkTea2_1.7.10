package mods.defeatedcrow.recipe;

import mods.defeatedcrow.api.charge.ChargeItemManager;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;

public class RegisterManager {
	
	public static void load()
	{
		RecipeRegisterManager.teaRecipe = new TeaRecipeRegister();
		RecipeRegisterManager.iceRecipe = new IceRecipeRegister();
		RecipeRegisterManager.prosessorRecipe = new ProsessorRecipeRegister();
		
		ChargeItemManager.chargeItem = new ChargeItemRegister();
	}
	
	private RegisterManager(){}

}
