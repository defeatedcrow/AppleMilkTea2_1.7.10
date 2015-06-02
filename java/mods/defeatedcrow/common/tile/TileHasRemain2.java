package mods.defeatedcrow.common.tile;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileHasRemain2 extends TileEntity {
	private short remain = 1;

	// NBT
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.remain = par1NBTTagCompound.getShort("Remaining");
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("Remaining", this.remain);
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

	public short getRemainShort() {
		return this.remain;
	}

	public void setRemainShort(short par1) {
		this.remain = par1;
	}

	public int getMetadata() {
		return this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
	}
}
