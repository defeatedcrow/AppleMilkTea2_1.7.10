package mods.defeatedcrow.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

/*
 * 熟成時間の処理と、完了したかどうかの判定を持つ。
 * 直射日光は厳禁。日光に当てると熟成時間がリセットされてしまう。
 */
public class TileCordial extends TileEntity {

	private int aging = 0;
	private boolean isAged = false;

	// NBT
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.aging = par1NBTTagCompound.getInteger("Remaining");
		this.isAged = par1NBTTagCompound.getBoolean("IsAged");
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("Remaining", this.aging);
		par1NBTTagCompound.setBoolean("IsAged", this.isAged);
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

	public int getAgingTime() {
		return this.aging;
	}

	public void setAgingTime(int par1) {
		this.aging = par1;
	}

	public boolean getAged() {
		return this.isAged;
	}

	public void setAged(boolean par1) {
		this.isAged = par1;
	}

	// レンダー用の熟成段階取得メソッド。一日ごとに色が濃くなっていく。
	public int getAgingStage() {
		int i = this.aging / 6000;
		return i;
	}

	public void setAgingStage(int par1) {
		int i = par1 * 6000;
		this.aging = i;
	}

	@Override
	public void updateEntity() {
		if (this.worldObj != null) {
			if (!this.isAged)// まだ熟成未完了
			{
				// 直射日光が当たっていない・常温でのみ熟成する。
				if (!this.worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord) && !this.isDryBiome()) {
					this.aging++;

					if (this.aging > 24000)// 4日間で熟成完了する
					{
						this.aging = 24000;
						this.setAged(true);
					}
				}
			}
		}

		super.updateEntity();
	}

	public int getMetadata() {
		return this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
	}

	public boolean isColdBiome() {
		boolean flag = false;
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(xCoord, zCoord);

		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.COLD)) {
			flag = true;
		}

		return flag;
	}

	public boolean isDryBiome() {
		boolean flag = false;
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(xCoord, zCoord);

		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.DRY)
				|| BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.NETHER)) {
			flag = true;
		}

		return flag;
	}

}
