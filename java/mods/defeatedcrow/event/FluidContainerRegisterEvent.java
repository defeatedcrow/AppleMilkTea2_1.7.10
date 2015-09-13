package mods.defeatedcrow.event;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.handler.FluidContMap;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class FluidContainerRegisterEvent {

	@SubscribeEvent
	public void onRegister(FluidContainerRegistry.FluidContainerRegisterEvent event) {
		FluidContainerData data = event.data;
		FluidStack fluid = data.fluid;
		if (data != null && fluid != null && fluid.getFluid() != null) {
			FluidContMap.Register(fluid.getFluid(), data);
			AMTLogger.debugInfo("register fluid cont map : " + fluid.getFluid().getLocalizedName(fluid));
		}
	}

}
