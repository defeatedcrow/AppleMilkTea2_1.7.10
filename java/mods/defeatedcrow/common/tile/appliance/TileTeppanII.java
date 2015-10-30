package mods.defeatedcrow.common.tile.appliance;

import mods.defeatedcrow.api.recipe.IPlateRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import buildcraft.api.transport.IPipeConnection;
import buildcraft.api.transport.IPipeTile.PipeType;
import cpw.mods.fml.common.Optional;

@Optional.InterfaceList({ @Optional.Interface(
		iface = "buildcraft.api.transport.IPipeConnection",
		modid = "BuildCraft|Core"), })
public class TileTeppanII extends TileEntity implements ISidedInventory, IPipeConnection {

	private int cookTime = 0;
	private int cookFinishTime = 0;
	private int cookFailTime = 0;

	private boolean fisnished = false;
	private boolean failed = false;

	// private boolean isOvenMode = false;

	private int lastAmount = 0;

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);

		// アイテムの読み込み
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
		this.plateItems = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.plateItems.length) {
				this.plateItems[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.cookTime = par1NBTTagCompound.getShort("CookTime");
		this.cookFinishTime = par1NBTTagCompound.getShort("FinTime");
		this.cookFailTime = par1NBTTagCompound.getShort("FailTime");

		this.fisnished = par1NBTTagCompound.getBoolean("finish");
		this.failed = par1NBTTagCompound.getBoolean("fail");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);

		// 燃焼時間や調理時間などの書き込み
		par1NBTTagCompound.setShort("CookTime", (short) this.cookTime);
		par1NBTTagCompound.setShort("FinTime", (short) this.cookFinishTime);
		par1NBTTagCompound.setShort("FailTime", (short) this.cookFailTime);

		par1NBTTagCompound.setBoolean("finish", this.fisnished);
		par1NBTTagCompound.setBoolean("fail", this.failed);

		// アイテムの書き込み
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.plateItems.length; ++i) {
			if (this.plateItems[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.plateItems[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);

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

	/* ========== get, set ========== */

	public boolean isFinishCooking() {
		return this.fisnished && !this.failed && (this.plateItems[1] != null);
	}

	public boolean isFailed() {
		return this.failed;
	}

	public boolean isReadyToCook() {
		return !this.fisnished && !this.failed && this.plateNoHoldingItem();
	}

	// すべてリセットするメソッド
	public void refreshPlate() {
		this.fisnished = false;
		this.failed = false;
		this.setInventorySlotContents(0, (ItemStack) null);
		this.setInventorySlotContents(1, (ItemStack) null);
		this.setInventorySlotContents(2, (ItemStack) null);
		this.cookTime = 0;
		this.setCookFinishTime(0);
		this.markDirty();
	}

	// 描画系のアップデート
	public void updatePlate() {
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		this.worldObj.notifyBlockChange(xCoord, yCoord, zCoord, DCsAppleMilk.teppanII);
		this.worldObj.func_147453_f(xCoord, yCoord, zCoord, DCsAppleMilk.teppanII);
		this.markDirty();
	}

	public int getCookTime() {
		return this.cookTime;
	}

	public void setCookTime(int par1) {
		this.cookTime = par1;
	}

	public int getCookFinishTime() {
		return this.cookFinishTime;
	}

	// 失敗時間は必ず調理時間の2倍
	public void setCookFinishTime(int par1) {
		int i = DCsConfig.teppannRandomCookTime ? this.worldObj.rand.nextInt(par1 + 1) : par1;
		int k = DCsConfig.teppannReadyTime > 0 ? par1 + DCsConfig.teppannReadyTime : par1 * 2;
		this.cookFinishTime = i;
		this.cookFailTime = k;
	}

	public int getCookFailTime() {
		return this.cookFailTime;
	}

	// 何かしらのアイテムを保持している
	public boolean plateNoHoldingItem() {
		return this.plateItems[0] == null && this.plateItems[1] == null && this.plateItems[2] == null;
	}

	/* ========== Plateのレシピ制御部分 ========== */

	// Blockクラスから使用。レシピがあるアイテムかどうか
	public boolean canSetRecipe(ItemStack item) {
		if (!this.plateNoHoldingItem()) {
			return false;
		}

		if (item == null)
			return false;

		IPlateRecipe recipe = RecipeRegisterManager.plateRecipe.getRecipe(item);
		if (recipe == null)
			return false;

		// if (recipe.useOvenRecipe())
		// {
		// return this.isOvenMode();
		// }
		else {
			return true;
		}
	}

	// 投入メソッド
	public boolean setRecipe(ItemStack item) {
		if (!this.plateNoHoldingItem())
			return false;
		if (item == null)
			return false;

		IPlateRecipe recipe = RecipeRegisterManager.plateRecipe.getRecipe(item);
		if (recipe != null) {
			this.setInventorySlotContents(0, item);
			this.setCookFinishTime(recipe.cookingTime());
			return true;
		}
		return false;

	}

	// 熱源の上にいるか
	public boolean isOnHeatSource() {
		if (this.worldObj.isAirBlock(xCoord, yCoord - 1, zCoord))
			return false;

		Block block = this.worldObj.getBlock(xCoord, yCoord - 1, zCoord);
		int meta = this.worldObj.getBlockMetadata(xCoord, yCoord - 1, zCoord);
		if (block != null) {
			AMTLogger.debugInfo("Current block : " + block.getUnlocalizedName() + ":" + meta);
			return RecipeRegisterManager.plateRecipe.isHeatSource(block, meta);
		} else {
			AMTLogger.debugInfo("Current block is null");
		}
		return false;
	}

	public boolean isOvenMode() {
		int count = 0;
		boolean b = false;

		if (this.worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord)) {
			for (int i = 0; i < 3; i++) {
				if (!worldObj.isAirBlock(xCoord, yCoord + 1 + i, zCoord)
						&& worldObj.getBlock(xCoord, yCoord + 1 + i, zCoord).getMaterial() != Material.water) {
					b = true;
				}
			}
			return false;
		} else {
			b = true;
		}

		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			if (dir == ForgeDirection.DOWN || dir == ForgeDirection.UP)
				continue;
			else {
				int x = xCoord + dir.offsetX;
				int y = yCoord;
				int z = zCoord + dir.offsetZ;
				Block block = worldObj.getBlock(x, y, z);
				if (block == null || worldObj.isAirBlock(x, y, z))
					continue;

				if (block.getMaterial() != Material.water && block.getMaterial() != Material.air)
					;

				{
					count++;
				}
			}
		}
		return b && count >= 3;
	}

	// 実処理
	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			this.onServerUpdate();
		}

		if (!this.plateNoHoldingItem()) {
			// 焦げる
			if (DCsConfig.teppannHardMode && this.cookFailTime > 0 && this.cookTime > this.cookFailTime
					&& this.plateItems[1] != null) {
				this.setInventorySlotContents(1, (ItemStack) null);
				this.setInventorySlotContents(2, new ItemStack(DCsAppleMilk.clam, 1, 2));
				this.failed = true;
				this.worldObj.playSoundEffect(xCoord, yCoord, zCoord, "random.fizz", 1.0F, 1.0F);
				this.updatePlate();
			}

			// 製品の完成はここ
			if (this.cookFinishTime > 0 && this.cookTime > this.cookFinishTime) {
				if (!this.fisnished) {
					if (this.plateItems[0] != null && this.plateItems[1] == null) {
						IPlateRecipe recipe = RecipeRegisterManager.plateRecipe.getRecipe(this.plateItems[0]);
						if (recipe != null && recipe.getOutput() != null) {
							ItemStack ret = recipe.getOutput();
							ret.stackSize = 1;

							this.setInventorySlotContents(0, (ItemStack) null);
							this.setInventorySlotContents(1, ret);
							this.fisnished = true;
							this.worldObj.playSoundEffect(xCoord, yCoord, zCoord, "random.fizz", 1.0F, 1.0F);
							this.updatePlate();
						}
					}
				} else {

				}
			}

			// レシピ処理、ちなみにカウントが失敗判定以内であれば、カウントが止まり調理を待ってくれる
			if (this.plateItems[0] != null && this.isOnHeatSource() && this.plateItems[1] == null) {
				boolean cooking = false;

				IPlateRecipe recipe = RecipeRegisterManager.plateRecipe.getRecipe(this.plateItems[0]);
				if (recipe != null) {
					cooking = true;
					if (this.cookFinishTime == 0) {
						this.setCookFinishTime(recipe.cookingTime());
					}
				}

				if (recipe.useOvenRecipe() && cooking) {
					cooking = this.isOvenMode();
				}

				if (cooking)
					this.cookTime++;
			}
		} else {
			if (this.fisnished || this.failed) {
				this.refreshPlate();
			}
		}
	}

	private void onServerUpdate() {
		int itemCount = 0;
		for (int i = 0; i < this.getSizeInventory(); i++) {
			if (this.getStackInSlot(i) != null) {
				itemCount += this.getStackInSlot(i).getDisplayName().length();
			}
		}

		if (lastAmount != itemCount) {
			lastAmount = itemCount;
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	private static final int[] slots_top = new int[] { 0 };
	private static final int[] slots_bottom = new int[] {
			1,
			2 };
	private static final int[] slots_sides = new int[] {
			0,
			1,
			2 };

	public ItemStack[] plateItems = new ItemStack[3];

	// スロット数
	@Override
	public int getSizeInventory() {
		return this.plateItems.length;
	}

	// インベントリ内の任意のスロットにあるアイテムを取得
	@Override
	public ItemStack getStackInSlot(int par1) {
		par1 = MathHelper.clamp_int(par1, 0, this.getSizeInventory());
		return this.plateItems[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		par1 = MathHelper.clamp_int(par1, 0, this.getSizeInventory());
		if (this.plateItems[par1] != null) {
			ItemStack itemstack = null;

			if (this.plateItems[par1].stackSize <= par2) {
				itemstack = this.plateItems[par1];
				this.plateItems[par1] = null;
				return itemstack;
			} else {
				itemstack = this.plateItems[par1].splitStack(par2);

				if (this.plateItems[par1].stackSize == 0) {
					this.plateItems[par1] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		par1 = MathHelper.clamp_int(par1, 0, this.getSizeInventory());
		if (this.plateItems[par1] != null) {
			ItemStack itemstack = this.plateItems[par1];
			this.plateItems[par1] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	// インベントリ内のスロットにアイテムを入れる
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {

		if (par1 > 2)
			par1 = 0;// 存在しないスロットに入れようとすると強制的に材料スロットに変更される。

		this.plateItems[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	// インベントリの名前
	@Override
	public String getInventoryName() {
		return "Teppan";
	}

	// 多言語対応かどうか
	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	// インベントリ内のスタック限界値
	@Override
	public int getInventoryStackLimit() {
		return 1;// 1個ずつ
	}

	@Override
	public void markDirty() {
		super.markDirty();
	}

	// par1EntityPlayerがTileEntityを使えるかどうか
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer
				.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
		return par1 == 0 ? (!this.fisnished && this.canSetRecipe(par2ItemStack) ? true : false) : false;
	}

	// ホッパーにアイテムの受け渡しをする際の優先度
	@Override
	public int[] getAccessibleSlotsFromSide(int par1) {
		return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
	}

	// ホッパーからアイテムを入れられるかどうか
	@Override
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
		return this.isItemValidForSlot(par1, par2ItemStack);
	}

	// 隣接するホッパーにアイテムを送れるかどうか
	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
		return par1 != 0;
	}

	// BuildCraft対応
	@Optional.Method(modid = "BuildCraft|Core")
	@Override
	public ConnectOverride overridePipeConnection(PipeType type, ForgeDirection with) {
		return ConnectOverride.DISCONNECT;
	}

}
