package mods.defeatedcrow.recipe;

import mods.defeatedcrow.api.charge.ChargeItemManager;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;

public class RegisterManager {
	
	public static void load()
	{
		RecipeRegisterManager.teaRecipe = new TeaRecipeRegister();
		RecipeRegisterManager.iceRecipe = new IceRecipeRegister();
		RecipeRegisterManager.prosessorRecipe = new ProsessorRecipeRegister();
		RecipeRegisterManager.evaporatorRecipe = new EvaporatorRecipeRegister();
		RecipeRegisterManager.panRecipe = new PanRecipeRegister();
		
		ChargeItemManager.chargeItem = new ChargeItemRegister();
		RecipeRegisterManager.slagLoot = new SlagResultLoot();
	}
	
	private RegisterManager(){}

}
