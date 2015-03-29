package mods.defeatedcrow.plugin.SSector;

import shift.sextiarysector.api.machine.energy.IGFEnergyHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class SS2DeviceHandler {
	
	private SS2DeviceHandler(){}
	
	public static boolean isGFDevice(TileEntity tile)
	{
		return tile instanceof IGFEnergyHandler;
	}
	
	public static int inputEnergy(TileEntity tile, ForgeDirection dir, int amount, boolean simulate)
	{
		int ret = 0;
		
		if (isGFDevice(tile))
		{
			IGFEnergyHandler handler = (IGFEnergyHandler) tile;
			
			if (handler.canInterface(dir))
			ret = handler.addEnergy(dir, 1, amount, simulate);
		}
		
		return ret;
	}

}
