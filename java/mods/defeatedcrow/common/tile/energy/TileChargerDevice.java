package mods.defeatedcrow.common.tile.energy;

import mods.defeatedcrow.common.config.PropertyHandler;
import mods.defeatedcrow.plugin.IC2.EUItemHandler;
import mods.defeatedcrow.plugin.IC2.EUSinkManager;
import mods.defeatedcrow.plugin.IC2.IEUSinkChannel;
import mods.defeatedcrow.plugin.SSector.SS2ItemHandler;
import mods.defeatedcrow.plugin.cofh.RFItemHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceHandler;
import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;
import cofh.api.tileentity.IEnergyInfo;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModAPIManager;
import cpw.mods.fml.common.Optional;

/*
 * TileChargerBaseの発展型。
 * 他MODのエネルギー受け入れのために用意したもの。
 */
@Optional.InterfaceList({
		@Optional.Interface(iface = "cofh.api.energy.IEnergyHandler", modid = "CoFHAPI|energy"),
		@Optional.Interface(iface = "cofh.api.tileentity.IEnergyInfo", modid = "CoFHAPI|tileentity"),
		@Optional.Interface(iface = "shift.sextiarysector.api.gearforce.tileentity.IGearForceHandler",
				modid = "SextiarySector") })
public class TileChargerDevice extends TileChargerBase implements IEnergyHandler, IEnergyInfo, IGearForceHandler {

	protected IEUSinkChannel EUChannel;

	// このTileにはコンストラクタが要る
	public TileChargerDevice() {
		super();
		if (Loader.isModLoaded("IC2")) {
			EUChannel = EUSinkManager.getChannel(this, MAX_CHARGE, 3);
		}
	}

	// このへんはオーバーライドしとかないとイマイチ動きが悪い
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		if (EUChannel != null) {
			EUChannel.readFromNBT2(par1NBTTagCompound);
		}
		super.readFromNBT(par1NBTTagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		if (EUChannel != null) {
			EUChannel.writeToNBT2(par1NBTTagCompound);
		}
		super.writeToNBT(par1NBTTagCompound);
	}

	@Override
	public Packet getDescriptionPacket() {
		return super.getDescriptionPacket();
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
	}

	/* Modごとの変換レート。コンフィグ変更可能にしました。バランスは投げ捨てました。諸事情により問い合わせは拒否します。 */

	private static int exchangeRateRF() {
		// RF -> Charge
		return PropertyHandler.rateRF();
	}

	private static int exchangeRateEU() {
		// EU -> Charge
		return PropertyHandler.rateEU();
	}

	private static int exchangeRateGF() {
		// GF -> Charge
		return PropertyHandler.rateGF();
	}

	/* 充電操作用のメソッド */

	/**
	 * 他MODの電池アイテムを対応させるためのメソッド。
	 * フル充電でもtrueを返す。
	 */
	@Override
	public boolean isChargeableBattery(ItemStack item) {
		boolean flag = false;

		if (Loader.isModLoaded("SextiarySector")) {
			flag = SS2ItemHandler.isGFItem(item);
		}
		if (ModAPIManager.INSTANCE.hasAPI("CoFHAPI|energy") && !flag) {
			flag = RFItemHandler.isChargeable(item);
		}
		if (!flag && Loader.isModLoaded("IC2") && !flag) {
			flag = EUItemHandler.isChargeable(item);
		}

		return flag;
	}

	/**
	 * 他MODの電池アイテムを対応させるためのメソッド。
	 * ここで充電を増やす。 <br>
	 * 減らす方はTileChargerBaseで行っているので不要。 <br>
	 * シミュレート可能。
	 */
	@Override
	public int chargeAnotherBattery(ItemStack item, int inc, boolean flag) {
		int ret = 0;
		if (Loader.isModLoaded("SextiarySector")) {
			int i = SS2ItemHandler.chargeAmount(item, inc * this.exchangeRateGF(), flag);
			ret = Math.round(i / this.exchangeRateGF());
		}
		if (ModAPIManager.INSTANCE.hasAPI("CoFHAPI|energy") && ret == 0) {
			int i = RFItemHandler.chargeAmount(item, inc * this.exchangeRateRF(), flag);
			ret = Math.round(i / this.exchangeRateRF());
		}
		if (Loader.isModLoaded("IC2") && ret == 0) {
			int i = EUItemHandler.chargeAmount(item, inc * this.exchangeRateEU(), flag);
			ret = Math.round(i / this.exchangeRateEU());
		}
		return ret;
	}

	// こちら側からネットワークへのチェックが要るIC2ケーブルからのEU受け入れはこのメソッドで行う。
	@Override
	public int acceptChargeFromDir(ForgeDirection dir) {
		int ret = 0;
		// EU受入量は指定する必要があるので、とりあえず512とする。
		if (EUChannel != null) {
			int i = this.getChargeAmount();
			double eu = Math.min(EUChannel.getEnergyStored2(), 512);
			double get = eu / this.exchangeRateEU();
			if ((MAX_CHARGE - i) < get)
				return 0;

			if (EUChannel.useEnergy2(eu)) {
				ret = (int) get;
			}
		}

		if (ret == 0)
			return super.acceptChargeFromDir(dir);

		return ret;

	}

	/* 他MODの電池を燃料スロットで溶かすための操作 */

	// 燃料判定
	@Override
	public int getItemBurnTime(ItemStack item) {
		if (item == null)
			return 0;
		else {
			int ret = 0;
			int inc = 16; // 速度はチャージバッテリーと同じ

			if (Loader.isModLoaded("SextiarySector") && ret == 0) {
				int i = SS2ItemHandler.dischargeAmount(item, inc * exchangeRateGF(), true);
				ret = Math.round(i / exchangeRateGF());
			}
			if (ModAPIManager.INSTANCE.hasAPI("CoFHAPI|energy") && ret == 0) {
				int i = RFItemHandler.dischargeAmount(item, inc * exchangeRateRF(), true);
				ret = Math.round(i / exchangeRateRF());
			}
			if (Loader.isModLoaded("IC2") && ret == 0) {
				int i = EUItemHandler.dischargeAmount(item, inc * exchangeRateEU(), true);
				ret = Math.round(i / exchangeRateEU());
			}
			if (ret == 0)
				return super.getItemBurnTime(item);
			return ret;
		}
	}

	@Override
	public int discharge(ItemStack item, int amount, int slot) {
		if (item == null)
			return 0;
		else {
			int ret = 0;
			int inc = amount;

			if (Loader.isModLoaded("SextiarySector") && ret == 0) {
				int i = SS2ItemHandler.dischargeAmount(item, inc * exchangeRateGF(), false);
				ret = Math.round(i / exchangeRateGF());

				if (ret > 0 && SS2ItemHandler.getAmount(item) == 0 && this.itemstacks[1] == null) {
					if (item == null || item.stackSize == 0) {
						this.setInventorySlotContents(0, null);
					} else {
						this.setInventorySlotContents(1, item.copy());
						this.decrStackSize(slot, 1);
					}
				}
			}
			if (ModAPIManager.INSTANCE.hasAPI("CoFHAPI|energy") && ret == 0) {
				int i = RFItemHandler.dischargeAmount(item, inc * exchangeRateRF(), false);
				ret = Math.round(i / exchangeRateRF());

				if (ret > 0 && RFItemHandler.getAmount(item) == 0 && this.itemstacks[1] == null) {
					if (item == null || item.stackSize == 0) {
						this.setInventorySlotContents(0, null);
					} else {
						this.setInventorySlotContents(1, item.copy());
						this.decrStackSize(slot, 1);
					}

				}
			}
			if (Loader.isModLoaded("IC2") && ret == 0) {
				int i = EUItemHandler.dischargeAmount(item, inc * exchangeRateEU(), false);
				ret = Math.round(i / exchangeRateEU());

				if (ret > 0 && EUItemHandler.getAmount(item) == 0 && this.itemstacks[1] == null) {
					if (item == null || item.stackSize == 0) {
						this.setInventorySlotContents(0, null);
					} else {
						this.setInventorySlotContents(1, item.copy());
						this.decrStackSize(slot, 1);
					}
				}
			}

			if (ret == 0) {
				this.decrStackSize(slot, 1);
				ret = super.getItemBurnTime(item);
			}

			return ret;
		}
	}

	/* for EU */

	@Override
	public void invalidate() {
		if (EUChannel != null) {
			EUChannel.invalidate2();
		}
		super.invalidate();
	}

	// 以下はSinkChannel用のメソッド
	@Override
	public void onChunkUnload() {
		if (EUChannel != null) {
			EUChannel.onChunkUnload2();
		}
		super.onChunkUnload();
	}

	@Override
	public void updateEntity() {
		if (!this.worldObj.isRemote && EUChannel != null) {
			EUChannel.updateEntity2();
		}
		super.updateEntity();
	}

	/* for RF */

	@Optional.Method(modid = "CoFHAPI|energy")
	@Override
	public boolean canConnectEnergy(ForgeDirection dir) {
		// 向きごとにコネクト可能か見ているっぽい
		TileEntity tile = this.worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
		boolean flag = (tile instanceof IEnergyConnection);
		return flag;
	}

	@Optional.Method(modid = "CoFHAPI|energy")
	@Override
	public int receiveEnergy(ForgeDirection dir, int in, boolean flag) {
		// エネルギーの受け入れ
		int eng = this.getChargeAmount();
		int get = in;
		if (this.isFullCharged() || get < this.exchangeRateRF())
			return 0;

		int ret = Math.min((this.getMaxChargeAmount() - eng) * this.exchangeRateRF(), get);

		if (!flag) {
			int i = Math.round(ret / this.exchangeRateRF());// 1/10に
			this.setChargeAmount(eng + i);
		}

		return ret;
	}

	@Optional.Method(modid = "CoFHAPI|energy")
	@Override
	public int extractEnergy(ForgeDirection paramForgeDirection, int paramInt, boolean paramBoolean) {
		// 出力はしない
		return 0;
	}

	@Optional.Method(modid = "CoFHAPI|energy")
	@Override
	public int getEnergyStored(ForgeDirection paramForgeDirection) {
		// 10倍になる
		return this.getChargeAmount() * this.exchangeRateRF();
	}

	@Optional.Method(modid = "CoFHAPI|energy")
	@Override
	public int getMaxEnergyStored(ForgeDirection paramForgeDirection) {
		// 10倍
		return this.getMaxChargeAmount() * this.exchangeRateRF();
	}

	@Optional.Method(modid = "CoFHAPI|tileentity")
	@Override
	public int getInfoEnergyPerTick() {
		return 0;
	}

	@Optional.Method(modid = "CoFHAPI|tileentity")
	@Override
	public int getInfoMaxEnergyPerTick() {
		return 0;
	}

	@Optional.Method(modid = "CoFHAPI|tileentity")
	@Override
	public int getInfoEnergyStored() {
		int eng = this.getChargeAmount();
		int get = eng * this.exchangeRateRF();
		return get;
	}

	@Optional.Method(modid = "CoFHAPI|tileentity")
	@Override
	public int getInfoMaxEnergyStored() {
		int eng = this.getMaxChargeAmount();
		int get = eng * this.exchangeRateRF();
		return get;
	}

	/* for GF */

	@Optional.Method(modid = "SextiarySector")
	@Override
	public int addEnergy(ForgeDirection from, int power, int speed, boolean simulate) {
		// エネルギーの受け入れ
		int eng = this.getChargeAmount();
		int get = speed;
		if (this.isFullCharged() || power < 3)
			return 0;

		if (get < this.exchangeRateGF()) {
			get = 3;
		}
		int ret = Math.min((this.getMaxChargeAmount() - eng) * this.exchangeRateGF(), get);

		if (!simulate) {
			int i = Math.round(1.0F * ret / this.exchangeRateGF());// 1/3に
			this.setChargeAmount(eng + i);
		}

		return ret;
	}

	@Optional.Method(modid = "SextiarySector")
	@Override
	public int drawEnergy(ForgeDirection from, int power, int speed, boolean simulate) {
		return 0;
	}

	@Optional.Method(modid = "SextiarySector")
	@Override
	public boolean canInterface(ForgeDirection from) {
		return true;
	}

	@Optional.Method(modid = "SextiarySector")
	@Override
	public int getSpeedStored(ForgeDirection from) {
		int eng = this.getChargeAmount();
		int get = eng * this.exchangeRateGF();
		return get;
	}

	@Optional.Method(modid = "SextiarySector")
	@Override
	public int getPowerStored(ForgeDirection from) {
		return 0;
	}

	@Optional.Method(modid = "SextiarySector")
	@Override
	public int getMaxSpeedStored(ForgeDirection from) {
		int eng = this.getMaxChargeAmount();
		int get = eng * this.exchangeRateGF();
		return get;
	}

	@Optional.Method(modid = "SextiarySector")
	@Override
	public int getMaxPowerStored(ForgeDirection from) {
		return 3;
	}

}
