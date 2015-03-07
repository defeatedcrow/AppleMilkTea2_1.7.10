package mods.defeatedcrow.plugin.SSector;

import shift.sextiarysector.api.machine.energy.IEnergyHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class SS2DeviceHandler {
	
	private SS2DeviceHandler(){}
	
	public static boolean isGFDevice(TileEntity tile)
	{
		return tile instanceof IEnergyHandler;
	}
	
	public static int inputEnergy(TileEntity tile, ForgeDirection dir, int amount, boolean simulate)
	{
		int ret = 0;
		
		if (isGFDevice(tile))
		{
			IEnergyHandler handler = (IEnergyHandler) tile;
			
			if (handler.canInterface(dir))
			ret = handler.addEnergy(dir, 1, amount, simulate);
		}
		
		return ret;
	}

}
