package mods.defeatedcrow.plugin.cofh;

import cofh.api.energy.IEnergyHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * RFを保持できる装置を操作するための中継クラス。
 * */
public class RFDeviceHandler {
	
	private RFDeviceHandler(){}
	
	public static boolean isRFDevice(TileEntity tile)
	{
		return tile instanceof IEnergyHandler;
	}
	
	public static int inputEnergy(TileEntity tile, int amount)
	{
		int ret = 0;
		
		if (isRFDevice(tile))
		{
			IEnergyHandler handler = (IEnergyHandler) tile;
			
			if (handler.canConnectEnergy(ForgeDirection.UNKNOWN))
			ret = handler.receiveEnergy(ForgeDirection.UNKNOWN, amount, false);
		}
		
		return ret;
	}

}
