package mods.defeatedcrow.common.tile.appliance;

import mods.defeatedcrow.api.appliance.SoupType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileFilledSoupPan extends TileEntity {

	private byte type = 0;
	private byte remain = 0;
	private String tex = "defeatedcrow:textures/blocks/contents_rice.png";
	private byte coolTime = 0;
	private boolean direction = false;

	private int last = 0;

	// NBT
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);

		this.type = par1NBTTagCompound.getByte("Type");
		this.remain = par1NBTTagCompound.getByte("Remaining");
		this.direction = par1NBTTagCompound.getBoolean("Direction");
		this.tex = par1NBTTagCompound.getString("Tex");
		this.coolTime = par1NBTTagCompound.getByte("CoolTime");
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setByte("Type", this.type);
		par1NBTTagCompound.setByte("Remaining", this.remain);
		par1NBTTagCompound.setBoolean("Direction", this.direction);
		par1NBTTagCompound.setString("Tex", tex);
		par1NBTTagCompound.setByte("CoolTime", this.coolTime);
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

	/* --- update --- */

	@Override
	public void updateEntity() {
		if (!this.worldObj.isRemote) {
			int i = this.type * this.remain;
			if (i != this.last) {
				this.last = i;
				this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}
		}
		super.updateEntity();
	}

	/* --- getter, setter --- */

	public byte getTypeByte() {
		return this.type;
	}

	public void setTypeByte(byte par1) {
		this.type = par1;
	}

	public SoupType getType() {
		return SoupType.getType(type);
	}

	public void setType(SoupType i) {
		byte b = (byte) i.id;
		this.type = b;
	}

	public byte getRemainByte() {
		return this.remain;
	}

	public void setRemainByte(byte i) {
		this.remain = i;
	}

	public boolean getDirection() {
		return this.direction;
	}

	public void setDirection(boolean par1) {
		this.direction = par1;
	}

	public String getCurrentTexture() {
		return this.getType().texture;
	}

	public String getDisplayName() {
		return this.getType().display;
	}

	private byte getCoolTime() {
		return this.coolTime;
	}

	private void setCoolTime(byte t) {
		this.coolTime = t;
	}

	public int getMetadata() {
		return this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
	}

}
