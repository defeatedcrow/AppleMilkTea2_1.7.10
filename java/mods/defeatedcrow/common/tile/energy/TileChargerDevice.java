package mods.defeatedcrow.common.tile.energy;

import shift.sextiarysector.api.machine.energy.IGFEnergyHandler;
import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;
import cofh.api.tileentity.IEnergyInfo;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.PropertyHandler;
import mods.defeatedcrow.plugin.*;
import mods.defeatedcrow.plugin.IC2.*;
import mods.defeatedcrow.plugin.cofh.RFItemHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

/*TileChargerBaseの発展型。
 * 他MODのエネルギー受け入れのために用意したもの。*/
@Optional.InterfaceList(
	{
		@Optional.Interface(iface = "cofh.api.energy.IEnergyHandler", modid = "CoFHCore"),
		@Optional.Interface(iface = "cofh.api.tileentity.IEnergyInfo", modid = "CoFHCore"),
		@Optional.Interface(iface = "shift.sextiarysector.api.machine.energy.IGFEnergyHandler", modid = "SextiarySector")
	}
)
public class TileChargerDevice extends TileChargerBase implements IEnergyHandler, IEnergyInfo, IGFEnergyHandler{
	
	protected IEUSinkChannel EUChannel;
	
	//このTileにはコンストラクタが要る
	public TileChargerDevice() {
		super();
		if (DCsAppleMilk.SuccessLoadIC2) EUChannel = EUSinkManager.getChannel(this, MAX_CHARGE, 3);
	}
	
	//このへんはオーバーライドしとかないとイマイチ動きが悪い
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		if (EUChannel != null) {
			EUChannel.readFromNBT2(par1NBTTagCompound);
		}
		super.readFromNBT(par1NBTTagCompound);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
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
	
	private int exchangeRateRF()
	{
		//RF -> Charge
		return PropertyHandler.rateRF();
	}
	
	private int exchangeRateEU()
	{
		//EU -> Charge
		return PropertyHandler.rateEU();
	}
	
	private int exchangeRateGF()
	{
		//GF -> Charge
		return PropertyHandler.rateGF();
	}
	
	/* 充電操作用のメソッド */
	
	/**
	 * 他MODの電池アイテムを対応させるためのメソッド。
	 * フル充電でもtrueを返す。
	 * */
	@Override
	public boolean isChargeableBattery(ItemStack item)
	{
		boolean flag = false;
		
		if (Loader.isModLoaded("CoFHCore"))
		{
			flag = RFItemHandler.isChargeable(item);
		}
		if (!flag && Loader.isModLoaded("IC2"))
		{
			flag = EUItemHandler.isChargeable(item);
		}
		
		return flag;
	}
	
	/**
	 * 他MODの電池アイテムを対応させるためのメソッド。
	 * ここで充電を増やす。
	 * <br>減らす方はTileChargerBaseで行っているので不要。
	 * <br>シミュレート可能。
	 * */
	@Override
	public int chargeAnotherBattery(ItemStack item, int inc, boolean flag)
	{
		int ret = 0;
		if (Loader.isModLoaded("CoFHCore"))
		{
			int i  = RFItemHandler.chargeAmount(item, inc * this.exchangeRateRF(), flag);
			ret = Math.round(i / this.exchangeRateRF());
		}
		if (Loader.isModLoaded("IC2") && ret == 0)
		{
			int i  = EUItemHandler.chargeAmount(item, inc * this.exchangeRateEU(), flag);
			ret = Math.round(i / this.exchangeRateEU());
		}
		return ret;
	}
	
	//こちら側からネットワークへのチェックが要るIC2ケーブルからのEU受け入れはこのメソッドで行う。
	@Override
	public int acceptChargeFromDir(ForgeDirection dir)
	{
		int ret = 0;
		//EU受入量は指定する必要があるので、とりあえず512とする。
		if (EUChannel != null)
		{
			int i = this.getChargeAmount();
			double eu = Math.min(EUChannel.getEnergyStored2(), 512);
			double get = eu / this.exchangeRateEU();
			if ((MAX_CHARGE - i) < get) return 0;
			
			if (EUChannel.useEnergy2(eu)){
				ret = (int) get;
			}
		}
		
		if (ret == 0)
		{
			return super.acceptChargeFromDir(dir);
		}
		
		return ret;
		
	}
	
	@Override
	public void invalidate() {
		if (EUChannel != null) EUChannel.invalidate2();
		super.invalidate();
	}
	
	//以下はSinkChannel用のメソッド
	@Override
	public void onChunkUnload() {
		if (EUChannel != null) EUChannel.onChunkUnload2();
		super.onChunkUnload();
	}
	
	@Override
	public void updateEntity() {
		if (!this.worldObj.isRemote && EUChannel != null)
		{
			EUChannel.updateEntity2();
		}
		super.updateEntity();
	}
	
	/* RF用 */

	@Optional.Method(modid = "CoFHCore")
	@Override
	public boolean canConnectEnergy(ForgeDirection dir) {
		//向きごとにコネクト可能か見ているっぽい
		TileEntity tile = this.worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
		boolean flag = (tile instanceof IEnergyConnection);
		return flag;
	}

	@Optional.Method(modid = "CoFHCore")
	@Override
	public int receiveEnergy(ForgeDirection dir, int in, boolean flag) {
		//エネルギーの受け入れ
		int eng = this.getChargeAmount();
		int get = in;
		if (this.isFullCharged() || get < this.exchangeRateRF()) return 0;
		
		int ret = Math.min((this.getMaxChargeAmount() - eng) * this.exchangeRateRF(), get);
		
		if (!flag){
			int i = Math.round(ret / this.exchangeRateRF());//1/10に
			this.setChargeAmount(eng + i);
		}
		
		return ret;
	}

	@Optional.Method(modid = "CoFHCore")
	@Override
	public int extractEnergy(ForgeDirection paramForgeDirection, int paramInt,
			boolean paramBoolean) {
		//出力はしない
		return 0;
	}

	@Optional.Method(modid = "CoFHCore")
	@Override
	public int getEnergyStored(ForgeDirection paramForgeDirection) {
		//10倍になる
		return this.getChargeAmount() * this.exchangeRateRF();
	}

	@Optional.Method(modid = "CoFHCore")
	@Override
	public int getMaxEnergyStored(ForgeDirection paramForgeDirection) {
		//10倍
		return this.getMaxChargeAmount() * this.exchangeRateRF();
	}

	@Optional.Method(modid = "CoFHCore")
	@Override
	public int getInfoEnergyPerTick() {
		return 0;
	}

	@Optional.Method(modid = "CoFHCore")
	@Override
	public int getInfoMaxEnergyPerTick() {
		return 0;
	}

	@Optional.Method(modid = "CoFHCore")
	@Override
	public int getInfoEnergyStored() {
		int eng = this.getChargeAmount();
		int get = eng * this.exchangeRateRF();
		return get;
	}

	@Optional.Method(modid = "CoFHCore")
	@Override
	public int getInfoMaxEnergyStored() {
		int eng = this.getMaxChargeAmount();
		int get = eng * this.exchangeRateRF();
		return get;
	}
	
	/* GF用 */

	@Optional.Method(modid = "SextiarySector")
	@Override
	public int addEnergy(ForgeDirection from, int power, int speed,
			boolean simulate) {
		//エネルギーの受け入れ
		int eng = this.getChargeAmount();
		int get = speed;
		if (this.isFullCharged() || speed < this.exchangeRateEU()) return 0;
				
		int ret = Math.min((this.getMaxChargeAmount() - eng) * this.exchangeRateGF(), get);
				
		if (!simulate){
			int i = Math.round(ret / this.exchangeRateGF());//1/3に
			this.setChargeAmount(eng + i);
		}
				
		return ret;
	}

	@Optional.Method(modid = "SextiarySector")
	@Override
	public int drawEnergy(ForgeDirection from, int power, int speed,
			boolean simulate) {
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
		return 3;
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
