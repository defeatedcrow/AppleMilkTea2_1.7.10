package mods.defeatedcrow.plugin.nei;

import mods.defeatedcrow.client.gui.GuiAdvProcessor;
import mods.defeatedcrow.client.gui.GuiEvaporator;
import mods.defeatedcrow.client.gui.GuiIceMaker;
import mods.defeatedcrow.client.gui.GuiProcessor;
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
	public static BrewingRecipeHandler brewRecipe;
	public static FondueSourceHandler fondueSource;
	public static FondueRecipeHandler fondueRecipe;
	public static HeatSourceHandler heatSource;

	public static void load() {

		teaRecipe = new TeaRecipeHandler();
		iceRecipe = new IceRecipeHandler();
		panRecipe = new PanRecipeHandler();
		processorRecipe = new ProcessorRecipeHandler();
		advProcessorRecipe = new AdvProcessorRecipeHandler();
		evaporatorRecipe = new EvaporatorRecipeHandler();
		plateRecipe = new TeppanRecipeHandler();
		// chocoRecipe = new ChocoRecipeHandler();
		brewRecipe = new BrewingRecipeHandler();
		fondueSource = new FondueSourceHandler();
		fondueRecipe = new FondueRecipeHandler();
		heatSource = new HeatSourceHandler();

		API.registerRecipeHandler(teaRecipe);
		API.registerUsageHandler(teaRecipe);
		API.registerGuiOverlay(GuiAppliance.class, teaRecipe.getOverlayIdentifier(), 0, 0);

		API.registerRecipeHandler(iceRecipe);
		API.registerUsageHandler(iceRecipe);
		API.registerGuiOverlay(GuiIceMaker.class, iceRecipe.getOverlayIdentifier(), 0, 0);

		API.registerRecipeHandler(panRecipe);
		API.registerUsageHandler(panRecipe);
		API.registerGuiOverlay(GuiAppliance.class, panRecipe.getOverlayIdentifier(), 0, 0);

		API.registerRecipeHandler(processorRecipe);
		API.registerUsageHandler(processorRecipe);
		API.registerGuiOverlay(GuiProcessor.class, processorRecipe.getOverlayIdentifier(), 0, 0);

		API.registerRecipeHandler(advProcessorRecipe);
		API.registerUsageHandler(advProcessorRecipe);
		API.registerGuiOverlay(GuiAdvProcessor.class, advProcessorRecipe.getOverlayIdentifier(), 0, 0);

		API.registerRecipeHandler(evaporatorRecipe);
		API.registerUsageHandler(evaporatorRecipe);
		API.registerGuiOverlay(GuiEvaporator.class, evaporatorRecipe.getOverlayIdentifier(), 0, 0);

		API.registerRecipeHandler(plateRecipe);
		API.registerUsageHandler(plateRecipe);
		API.registerGuiOverlay(GuiRecipe.class, plateRecipe.getOverlayIdentifier(), 0, 0);

		API.registerRecipeHandler(brewRecipe);
		API.registerUsageHandler(brewRecipe);
		API.registerGuiOverlay(GuiRecipe.class, brewRecipe.getOverlayIdentifier(), 0, 0);

		API.registerRecipeHandler(fondueSource);
		API.registerUsageHandler(fondueSource);
		API.registerGuiOverlay(GuiAppliance.class, fondueSource.getOverlayIdentifier(), 0, 0);

		API.registerRecipeHandler(fondueRecipe);
		API.registerUsageHandler(fondueRecipe);
		API.registerGuiOverlay(GuiRecipe.class, fondueRecipe.getOverlayIdentifier(), 0, 0);

		API.registerRecipeHandler(heatSource);
		API.registerUsageHandler(heatSource);
		API.registerGuiOverlay(GuiRecipe.class, heatSource.getOverlayIdentifier(), 0, 0);

		AMTLogger.loadedModInfo("NEI");

	}

}
