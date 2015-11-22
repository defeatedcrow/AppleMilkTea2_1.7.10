package mods.defeatedcrow.common.tile;

import mods.defeatedcrow.common.fluid.DCsTank;
import mods.defeatedcrow.recipe.BrewingRecipe;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

/*
 * 熟成時間の処理と、完了したかどうかの判定を持つ。
 * 直射日光は厳禁。日光に当てると熟成時間がリセットされてしまう。
 * 酒は液体タンクに保管されている。熟成完了するまでアクセス出来ない。
 */
public class TileBrewingBarrel extends TileEntity implements IFluidHandler {

	private int aging = 0;
	private boolean isAged = false;
	public DCsTank productTank = new DCsTank(1000);
	// 向き
	private boolean side = false;

	private int lastState = 0;

	// NBT
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.aging = par1NBTTagCompound.getInteger("Remaining");
		this.isAged = par1NBTTagCompound.getBoolean("IsAged");
		this.side = par1NBTTagCompound.getBoolean("Side");

		this.productTank = new DCsTank(1000);
		if (par1NBTTagCompound.hasKey("productTank")) {
			this.productTank.readFromNBT(par1NBTTagCompound.getCompoundTag("productTank"));
		}
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("Remaining", this.aging);
		par1NBTTagCompound.setBoolean("IsAged", this.isAged);
		par1NBTTagCompound.setBoolean("Side", this.side);

		NBTTagCompound tank = new NBTTagCompound();
		this.productTank.writeToNBT(tank);
		par1NBTTagCompound.setTag("productTank", tank);
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

	public boolean getSide() {
		return this.side;
	}

	public void setSide(boolean flag) {
		this.side = flag;
	}

	// レンダー用の熟成段階取得メソッド。一日（20分）ごとに色が濃くなっていく。
	public int getAgingStage() {
		int i = this.aging / 6000;
		return i;
	}

	public void setAgingStage(int par1) {
		int i = par1 * 6000;
		this.aging = i;
	}

	// レンダー用。真下の状態を確認
	public boolean isOnNormalCube() {
		Block block = worldObj.getBlock(xCoord, yCoord - 1, zCoord);
		return !block.isAir(worldObj, xCoord, yCoord - 1, zCoord) && block.isNormalCube();
	}

	@Override
	public void updateEntity() {
		if (!this.worldObj.isRemote) {
			if (!this.isAged && this.canBrew())// まだ熟成未完了
			{
				// 直射日光が当たっていない・常温でのみ熟成する。
				if (!this.worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord) && !this.isDryBiome()) {
					this.aging++;

					if (this.aging > 24000)// 4日間で熟成完了する
					{
						this.aging = 24000;
						this.onBrewing();
						this.setAged(true);
					}
				}
			}

			if (this.productTank.isEmpty()) {
				this.setAged(false);
				this.setAgingTime(0);
			}

			int state = this.productTank.getFluidAmount() + this.aging;
			if (lastState != state) {
				lastState = state;
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}
		}

		super.updateEntity();
	}

	private boolean canBrew() {
		boolean flag = false;
		Fluid input = this.productTank.getFluidType();

		if (input != null && BrewingRecipe.recipe.containsKey(input)) {
			flag = true;
		}

		return flag;
	}

	// 熟成完了処理
	private void onBrewing() {
		Fluid input = this.productTank.getFluidType();
		int amount = this.productTank.getFluidAmount();

		if (input != null && amount > 0) {
			if (BrewingRecipe.recipe.containsKey(input)) {
				Fluid output = BrewingRecipe.recipe.get(input);
				FluidStack ret = new FluidStack(output, amount);
				this.productTank.setFluid(ret);
			}
		}
		this.markDirty();
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

	/* ====== 以下、IFluidHandlerの実装メソッド ====== */

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource == null || !this.isAged) {
			return null;
		}
		if (productTank.getFluidType() == resource.getFluid()) {
			FluidStack ret = productTank.drain(resource.amount, doDrain);
			if (productTank.isEmpty()) {
				this.setAged(false);
				this.setAgingStage(0);
			}
			return ret;
		}
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if (maxDrain <= 0 || !this.isAged) {
			return null;
		}
		FluidStack ret = productTank.drain(maxDrain, doDrain);
		if (productTank.isEmpty()) {
			this.setAged(false);
			this.setAgingStage(0);
		}
		return ret;
	}

	// 外部からの液体の受け入れ
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if (resource == null || resource.getFluid() == null || this.isAged) {
			return 0;
		}

		FluidStack current = this.productTank.getFluid();
		FluidStack resourceCopy = resource.copy();
		if (current != null && current.amount > 0 && current.isFluidEqual(resourceCopy)) {
			return 0;
		}

		int i = 0;
		int used = this.productTank.fill(resourceCopy, doFill);
		resourceCopy.amount -= used;
		i += used;

		return i;
	}

	// 空でないと&醸造未完了でないと受け入れない
	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return !this.isAged && fluid != null && BrewingRecipe.recipe.containsKey(fluid) && this.productTank.isEmpty();
	}

	// 醸造が完了するまで引き抜けないようにする
	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return this.isAged;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] { productTank.getInfo() };
	}

}
