package mods.defeatedcrow.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileBread extends TileHasDirection {
	private boolean isTallModel;
	private byte type = 0;

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.isTallModel = par1NBTTagCompound.getBoolean("Tall");
		this.type = par1NBTTagCompound.getByte("Type");
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setBoolean("Tall", this.isTallModel);
		par1NBTTagCompound.setByte("Type", this.type);
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

	public boolean getTall() {
		return this.isTallModel;
	}

	public void setTall(boolean par1) {
		this.isTallModel = par1;
	}

	public byte getType() {
		return this.type;
	}

	public void setType(byte b) {
		this.type = b;
	}
}
