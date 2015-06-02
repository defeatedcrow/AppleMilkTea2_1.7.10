package mods.defeatedcrow.common.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileCPanel extends TileEntity {

	private ItemStack holdItem = null;

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);

		if (par1NBTTagCompound.hasKey("HoldItem")) {
			this.setItemstack(ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("HoldItem")));
		}
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);

		if (this.getItemstack() != null) {
			par1NBTTagCompound.setTag("HoldItem", this.getItemstack().writeToNBT(new NBTTagCompound()));
		}
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

	public ItemStack getItemstack() {
		return this.holdItem;
	}

	public void setItemstack(ItemStack par1ItemStack) {
		this.holdItem = par1ItemStack;
	}

}
