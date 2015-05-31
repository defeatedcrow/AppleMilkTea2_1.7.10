package mods.defeatedcrow.common.tile.energy;

import mods.defeatedcrow.api.charge.IChargeGenerator;
import mods.defeatedcrow.api.charge.IChargeableMachine;
import mods.defeatedcrow.common.config.PropertyHandler;
import mods.defeatedcrow.plugin.SSector.SS2DeviceHandler;
import mods.defeatedcrow.plugin.cofh.RFDeviceHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyProvider;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModAPIManager;
import cpw.mods.fml.common.Optional;

@Optional.InterfaceList({ @Optional.Interface(iface = "cofh.api.energy.IEnergyProvider", modid = "CoFHAPI|energy"), })
public class TileHandleEngine extends TileEntity implements IChargeGenerator, IEnergyProvider {

	private int interval = 0;
	private int round = 0;
	private int click = 0;

	private int chargeAmount = 0;
	private final int MAX_CHARGE = 32;

	private int lastRound = 0;

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);

		this.interval = par1NBTTagCompound.getShort("Interval");
		this.round = par1NBTTagCompound.getShort("Round");
		this.click = par1NBTTagCompound.getShort("Click");
		this.chargeAmount = par1NBTTagCompound.getShort("ChargeAmount");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);

		NBTTagList nbttaglist = new NBTTagList();

		par1NBTTagCompound.setShort("Interval", (short) this.interval);
		par1NBTTagCompound.setShort("Round", (short) this.round);
		par1NBTTagCompound.setShort("Click", (short) this.click);
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

	public void setClick(int par1) {
		this.click = par1;
	}

	public int getClick() {
		return this.click;
	}

	public void setInterval(int par1) {
		if (par1 > 8)
			par1 = 8;
		this.interval = par1;
	}

	public float getRound() {
		return (float) this.round;
	}

	private int rateRF() {
		return PropertyHandler.rateRF();
	}

	@Override
	public void updateEntity() {
		if (interval > 0) {
			int next = this.chargeAmount + 2;
			next = Math.min(next, MAX_CHARGE);
			this.setChargeAmount(next);

			int r = this.round + 4;
			if (r > 360)
				r -= 360;
			this.round = r;

			this.interval--;
		}

		// 真下のTileのチェック
		TileEntity tile = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
		if (tile != null) {
			int ext = Math.min(this.chargeAmount, 2);
			boolean b = false;

			if (tile instanceof IChargeableMachine) {
				b = true;
			}
			if (!b && Loader.isModLoaded("SextiarySector")) {
				int ext2 = ext * PropertyHandler.rateGF();
				ext2 = SS2DeviceHandler.inputEnergy(tile, ForgeDirection.UP, ext2, true);
				if (SS2DeviceHandler.isGFDevice(tile) && ext2 > 0) {
					SS2DeviceHandler.inputEnergy(tile, ForgeDirection.UP, ext2, false);
					this.chargeAmount -= ext;
					b = true;
				}
			}
			if (!b && ModAPIManager.INSTANCE.hasAPI("CoFHAPI|energy")) {
				int ext2 = ext * PropertyHandler.rateRF();
				ext2 = RFDeviceHandler.inputEnergy(ForgeDirection.UP, tile, ext2, true);
				if (RFDeviceHandler.isRFDevice(tile) && ext2 > 0) {
					RFDeviceHandler.inputEnergy(ForgeDirection.UP, tile, ext2, false);
					this.chargeAmount -= ext;
					b = true;
				}
			}

		}

		if (!worldObj.isRemote) {
			this.updateServer();
		}
	}

	public void updateServer() {
		int current = this.round;
		if (current != this.lastRound) {
			this.lastRound = current;
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}

	/* IChargeGenerator */

	@Override
	public boolean canGenerate() {
		return this.chargeAmount > 0;
	}

	@Override
	public int generateCharge(ForgeDirection dir, boolean flag) {
		if (dir != ForgeDirection.DOWN)
			return 0;

		int ret = Math.min(chargeAmount, 2);
		if (ret > 0) {
			if (!flag)
				this.chargeAmount -= ret;
			return ret;
		}
		return 0;
	}

	/* IEnergyProvider */

	@Optional.Method(modid = "CoFHAPI|energy")
	@Override
	public boolean canConnectEnergy(ForgeDirection paramForgeDirection) {
		return paramForgeDirection == ForgeDirection.DOWN && this.chargeAmount > 0;
	}

	@Optional.Method(modid = "CoFHAPI|energy")
	@Override
	public int extractEnergy(ForgeDirection paramForgeDirection, int paramInt, boolean paramBoolean) {
		if (paramForgeDirection != ForgeDirection.DOWN)
			return 0;

		int ret = Math.min(this.chargeAmount, 2);
		int extract = ret * PropertyHandler.rateRF();

		if (ret > 0) {
			this.chargeAmount -= ret;
			return extract;
		}
		return 0;
	}

	@Optional.Method(modid = "CoFHAPI|energy")
	@Override
	public int getEnergyStored(ForgeDirection paramForgeDirection) {
		return this.chargeAmount * PropertyHandler.rateRF();
	}

	@Optional.Method(modid = "CoFHAPI|energy")
	@Override
	public int getMaxEnergyStored(ForgeDirection paramForgeDirection) {
		return this.MAX_CHARGE * PropertyHandler.rateRF();
	}

}
