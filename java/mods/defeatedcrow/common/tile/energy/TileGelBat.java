package mods.defeatedcrow.common.tile.energy;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

// chargeの一時保管用
public class TileGelBat extends TileEntity {

	private int chargeAmount = 0;

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);

		this.chargeAmount = par1NBTTagCompound.getShort("ChargeAmount");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);

		NBTTagList nbttaglist = new NBTTagList();

		par1NBTTagCompound.setShort("ChargeAmount", (short) this.chargeAmount);
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

	public void setChargeAmount(int par1) {
		this.chargeAmount = par1;
	}

	public int getChargeAmount() {
		return this.chargeAmount;
	}

}
