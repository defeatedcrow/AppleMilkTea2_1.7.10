package mods.defeatedcrow.plugin.nei;

import net.minecraft.item.Item;
import mods.defeatedcrow.common.AMTLogger;
import codechicken.nei.api.API;

public class LoadNEIPlugin {
	
	
	
	public static TeaRecipeHandler teaRecipe;
	public static IceRecipeHandler iceRecipe;
	public static PanRecipeHandler panRecipe;
	public static ProsessorRecipeHandler prosessorRecipe;
	public static AdvProsessorRecipeHandler advProsessorRecipe;
	public static EvaporatorRecipeHandler evaporatorRecipe;
	
	public static void load() {
		
		teaRecipe = new TeaRecipeHandler();
		iceRecipe = new IceRecipeHandler();
		panRecipe = new PanRecipeHandler();
		prosessorRecipe = new ProsessorRecipeHandler();
		advProsessorRecipe = new AdvProsessorRecipeHandler();
		evaporatorRecipe = new EvaporatorRecipeHandler();
		
		
		API.registerRecipeHandler(teaRecipe);
		API.registerUsageHandler(teaRecipe);
		API.registerGuiOverlay(GuiRecipe.class, teaRecipe.getOverlayIdentifier(), 0, 0);
		
		API.registerRecipeHandler(iceRecipe);
		API.registerUsageHandler(iceRecipe);
		API.registerGuiOverlay(GuiRecipe.class, iceRecipe.getOverlayIdentifier(), 0, 0);
		
		API.registerRecipeHandler(panRecipe);
		API.registerUsageHandler(panRecipe);
		API.registerGuiOverlay(GuiRecipe.class, panRecipe.getOverlayIdentifier(), 0, 0);
		
		API.registerRecipeHandler(prosessorRecipe);
		API.registerUsageHandler(prosessorRecipe);
		API.registerGuiOverlay(GuiRecipe.class, prosessorRecipe.getOverlayIdentifier(), 0, 0);
		
		API.registerRecipeHandler(advProsessorRecipe);
		API.registerUsageHandler(advProsessorRecipe);
		API.registerGuiOverlay(GuiRecipe.class, advProsessorRecipe.getOverlayIdentifier(), 0, 0);
		
		API.registerRecipeHandler(evaporatorRecipe);
		API.registerUsageHandler(evaporatorRecipe);
		API.registerGuiOverlay(GuiRecipe.class, evaporatorRecipe.getOverlayIdentifier(), 0, 0);
		
		AMTLogger.loadedModInfo("NEI");
		
	}

}
