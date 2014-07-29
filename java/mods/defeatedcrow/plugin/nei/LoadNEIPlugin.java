package mods.defeatedcrow.plugin.nei;

import mods.defeatedcrow.common.AMTLogger;
import codechicken.nei.api.API;

public class LoadNEIPlugin {
	
	public static TeaRecipeHandler teaRecipe;
	public static IceRecipeHandler iceRecipe;
	public static PanRecipeHandler panRecipe;
	
	public static void load() {
		
		teaRecipe = new TeaRecipeHandler();
		iceRecipe = new IceRecipeHandler();
		panRecipe = new PanRecipeHandler();
		
		API.registerRecipeHandler(teaRecipe);
		API.registerUsageHandler(teaRecipe);
		API.registerGuiOverlay(GuiRecipe.class, teaRecipe.getOverlayIdentifier(), 0, 0);
		
		API.registerRecipeHandler(iceRecipe);
		API.registerUsageHandler(iceRecipe);
		API.registerGuiOverlay(GuiRecipe.class, iceRecipe.getOverlayIdentifier(), 0, 0);
		
		API.registerRecipeHandler(panRecipe);
		API.registerUsageHandler(panRecipe);
		API.registerGuiOverlay(GuiRecipe.class, panRecipe.getOverlayIdentifier(), 0, 0);
		
		AMTLogger.loadedModInfo("NEI");
		
	}

}
