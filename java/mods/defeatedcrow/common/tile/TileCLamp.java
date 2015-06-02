package mods.defeatedcrow.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileCLamp extends TileHasDirection {

	private short rad = 0;

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.rad = par1NBTTagCompound.getShort("Rad");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("Rad", this.rad);
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

	public short getAngle() {
		return this.rad;
	}

	public void setAngle(short par1) {
		this.rad = par1;
	}

	@Override
	public void updateEntity() {
		if (this.rad >= 360) {
			this.rad = 0;
		} else {
			++this.rad;
			this.markDirty();
		}

		super.updateEntity();
	}

}
