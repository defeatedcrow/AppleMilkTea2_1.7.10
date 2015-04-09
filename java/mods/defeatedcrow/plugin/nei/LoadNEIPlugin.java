package mods.defeatedcrow.plugin.nei;

import net.minecraft.item.Item;
import mods.defeatedcrow.client.gui.*;
import mods.defeatedcrow.common.AMTLogger;
import codechicken.nei.api.API;

public class LoadNEIPlugin {
	
	
	
	public static TeaRecipeHandler teaRecipe;
	public static IceRecipeHandler iceRecipe;
	public static PanRecipeHandler panRecipe;
	public static ProcessorRecipeHandler processorRecipe;
	public static AdvProcessorRecipeHandler advProcessorRecipe;
	public static EvaporatorRecipeHandler evaporatorRecipe;
	public static TeppanRecipeHandler plateRecipe;
	public static ChocoRecipeHandler chocoRecipe;
	
	public static void load() {
		
		teaRecipe = new TeaRecipeHandler();
		iceRecipe = new IceRecipeHandler();
		panRecipe = new PanRecipeHandler();
		processorRecipe = new ProcessorRecipeHandler();
		advProcessorRecipe = new AdvProcessorRecipeHandler();
		evaporatorRecipe = new EvaporatorRecipeHandler();
		plateRecipe = new TeppanRecipeHandler();
		chocoRecipe = new ChocoRecipeHandler();
		
		
		API.registerRecipeHandler(teaRecipe);
		API.registerUsageHandler(teaRecipe);
		API.registerGuiOverlay(GuiRecipe.class, teaRecipe.getOverlayIdentifier(), 0, 0);
		
		API.registerRecipeHandler(iceRecipe);
		API.registerUsageHandler(iceRecipe);
		API.registerGuiOverlay(GuiIceMaker.class, iceRecipe.getOverlayIdentifier(), 0, 0);
		
		API.registerRecipeHandler(panRecipe);
		API.registerUsageHandler(panRecipe);
		API.registerGuiOverlay(GuiRecipe.class, panRecipe.getOverlayIdentifier(), 0, 0);
		
		API.registerRecipeHandler(processorRecipe);
		API.registerUsageHandler(processorRecipe);
		API.registerGuiOverlay(GuiProsessor.class, processorRecipe.getOverlayIdentifier(), 0, 0);
		
		API.registerRecipeHandler(advProcessorRecipe);
		API.registerUsageHandler(advProcessorRecipe);
		API.registerGuiOverlay(GuiAdvProsessor.class, advProcessorRecipe.getOverlayIdentifier(), 0, 0);
		
		API.registerRecipeHandler(evaporatorRecipe);
		API.registerUsageHandler(evaporatorRecipe);
		API.registerGuiOverlay(GuiEvaporator.class, evaporatorRecipe.getOverlayIdentifier(), 0, 0);
		
		API.registerRecipeHandler(plateRecipe);
		API.registerUsageHandler(plateRecipe);
		API.registerGuiOverlay(GuiRecipe.class, plateRecipe.getOverlayIdentifier(), 0, 0);
		
		API.registerRecipeHandler(chocoRecipe);
		API.registerUsageHandler(chocoRecipe);
		API.registerGuiOverlay(GuiRecipe.class, chocoRecipe.getOverlayIdentifier(), 0, 0);
		
		AMTLogger.loadedModInfo("NEI");
		
	}

}
