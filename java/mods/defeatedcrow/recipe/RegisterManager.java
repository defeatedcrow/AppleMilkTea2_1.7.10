package mods.defeatedcrow.recipe;

import mods.defeatedcrow.api.charge.ChargeItemManager;
import mods.defeatedcrow.api.potion.AMTPotionManager;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.potion.PotionGetter;

public class RegisterManager {

	public static void load() {
		RecipeRegisterManager.teaRecipe = new TeaRecipeRegister();
		RecipeRegisterManager.iceRecipe = new IceRecipeRegister();
		RecipeRegisterManager.processorRecipe = new ProcessorRecipeRegister();
		RecipeRegisterManager.evaporatorRecipe = new EvaporatorRecipeRegister();
		RecipeRegisterManager.panRecipe = new PanRecipeRegister();
		RecipeRegisterManager.chocoRecipe = new ChocolateRecipe();
		RecipeRegisterManager.fondueRecipe = new FondueRecipeRegister();
		RecipeRegisterManager.plateRecipe = new PlateRecipeRegister();

		ChargeItemManager.chargeItem = new ChargeItemRegister();
		RecipeRegisterManager.slagLoot = new SlagResultLoot();

		AMTPotionManager.manager = new PotionGetter();
	}

	private RegisterManager() {
	}

}
