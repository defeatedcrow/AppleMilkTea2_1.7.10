package mods.defeatedcrow.common.tile;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.handler.CoordListRegister;
import net.minecraft.tileentity.TileEntity;

public class TileCrowDoll extends TileEntity {

	private boolean active = false;

	@Override
	public void updateEntity() {
		if (!active && !worldObj.isRemote) {
			int cX = xCoord >> 4;
			int cZ = zCoord >> 4;
			if (CoordListRegister.setCood(worldObj, xCoord, yCoord, zCoord, cX, cZ)) {
				AMTLogger.debugInfo("add coord");
			}
		}
	}

}
