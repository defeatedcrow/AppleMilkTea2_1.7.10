package mods.defeatedcrow.common.tile.energy;

import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;
import cofh.api.tileentity.IEnergyInfo;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/*TileChargerBaseの発展型。
 * 他MODのエネルギー受け入れのために用意したもの。*/
@Optional.InterfaceList(
	{
		@Optional.Interface(iface = "cofh.api.energy.IEnergyHandler", modid = "CoFHCore"),
		@Optional.Interface(iface = "cofh.api.tileentity.IEnergyInfo", modid = "CoFHCore"),
	}
)
public class TileChargerDevice extends TileChargerBase implements IEnergyHandler, IEnergyInfo{
	
	//このへんはオーバーライドしとかないとイマイチ動きが悪い
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
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
	
	/* Modごとの変換レート。コンフィグ変更可能にする予定はあるが、バランス調整前なので暫定的に変更できないようにする */
	
	private int exchangeRateRF()
	{
		//RF -> Charge
		return 10;
	}
	
	private int exchangeRateEU()
	{
		//EU -> Charge
		return 2;
	}
	
	private int exchangeRateGF()
	{
		//GF -> Charge
		return 3;
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
		if (this.isFullCharged() || get < 10) return 0;
		
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
	
	
	
	

}
