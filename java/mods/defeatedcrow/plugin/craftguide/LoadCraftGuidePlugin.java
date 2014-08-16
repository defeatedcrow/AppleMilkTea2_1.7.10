package mods.defeatedcrow.plugin.craftguide;

import java.lang.reflect.Method;

import mods.defeatedcrow.common.AMTLogger;
import uristqwerty.CraftGuide.api.CraftGuideAPIObject;

public class LoadCraftGuidePlugin extends CraftGuideAPIObject{
	
	public static TeaRecipeHandlerCG teaRecipeCG;
	public static IceRecipeHandlerCG iceRecipeCG;
	public static PanRecipeHandlerCG panRecipeCG;
	public static ProsessorRecipeHandlerCG prosessorRecipeCG;
	public static EvaporatorRecipeHandlerCG evaporatorRecipeCG;
	
	public void load()
	{
		try
		{
			teaRecipeCG = new TeaRecipeHandlerCG();
			iceRecipeCG = new IceRecipeHandlerCG();
			panRecipeCG = new PanRecipeHandlerCG();
			prosessorRecipeCG = new ProsessorRecipeHandlerCG();
			evaporatorRecipeCG = new EvaporatorRecipeHandlerCG();
			
			Class api = Class.forName("uristqwerty.CraftGuide.ReflectionAPI");
			Method register = api.getMethod("registerAPIObject", new Class[] { Object.class });
			register.invoke(null, new Object[] {teaRecipeCG} );
			register.invoke(null, new Object[] {iceRecipeCG} );
			register.invoke(null, new Object[] {panRecipeCG} );
			register.invoke(null, new Object[] {prosessorRecipeCG} );
			register.invoke(null, new Object[] {evaporatorRecipeCG} );
			
			AMTLogger.loadedModInfo("CraftGuide");
		}
		catch (Exception e)
		{
			AMTLogger.logger.warn("Failed to registering CraftGuide Plugin.", new Object[]{e.getMessage()});
		}
		
	}

}
