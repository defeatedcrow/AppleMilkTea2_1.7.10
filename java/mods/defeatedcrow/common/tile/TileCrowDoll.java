package mods.defeatedcrow.common.tile;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.handler.CoordListRegister;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileCrowDoll extends TileEntity {

	private boolean active = false;

	public double range = 0.0D;

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.range = par1NBTTagCompound.getDouble("Shake");
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setDouble("Shake", this.range);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		this.writeToNBT(nbtTagCompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTagCompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (!active) {
				int cX = xCoord >> 4;
				int cZ = zCoord >> 4;
				if (CoordListRegister.setCood(worldObj, xCoord, yCoord, zCoord, cX, cZ)) {
					AMTLogger.debugInfo("add coord");
				}
			}
		}
		if (range * range > 0.01D) {
			range *= -0.9D;
		} else {
			range = 0.0D;
		}
	}

}
