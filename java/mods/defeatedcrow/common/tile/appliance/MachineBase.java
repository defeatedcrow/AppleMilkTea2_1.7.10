package mods.defeatedcrow.common.tile.appliance;

import mods.defeatedcrow.api.charge.ChargeItemManager;
import mods.defeatedcrow.api.charge.IChargeableMachine;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * ChargeItemを燃料とするTileEntityのベースクラス。
 * <br>チャージ管理部分のみを共通処理としてここで作っている。
 * <br>レシピとアイテム生産については個々のクラスで作る。
 * */
public abstract class MachineBase extends TileEntity implements ISidedInventory, IChargeableMachine
{
 
	//現在のチャージ量
	private int chargeAmount = 0;
	//チャージアイテムを溶かす際の判定発生間隔
	private int coolTime = 4;
	//作業中カウント
	public int cookTime = 0;
 
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
		this.itemstacks = new ItemStack[this.getSizeInventory()];
 
		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");
 
			if (b0 >= 0 && b0 < this.itemstacks.length)
			{
				this.itemstacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
		
		this.chargeAmount = par1NBTTagCompound.getShort("ChargeAmount");
		this.cookTime = par1NBTTagCompound.getShort("CookTime");
		this.coolTime = par1NBTTagCompound.getByte("CoolTime");
	}
 
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		
		NBTTagList nbttaglist = new NBTTagList();
		 
		for (int i = 0; i < this.itemstacks.length; ++i)
		{
			if (this.itemstacks[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				this.itemstacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		
		//燃焼時間や調理時間などの書き込み
		par1NBTTagCompound.setShort("ChargeAmount", (short)this.chargeAmount);
		par1NBTTagCompound.setShort("CookTime", (short)this.cookTime);
		par1NBTTagCompound.setByte("CoolTime", (byte)this.coolTime);
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
 
	//調理中の矢印の描画
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int par1)
	{
		return this.cookTime * par1 / 100;
	}
 
	//チャージゲージの描画
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int par1)
	{
		return this.chargeAmount * par1 / 25600;
	}
 
	//調理中
	@Override
	public boolean isActive()
	{
		return this.cookTime > 0;
	}
	
	//チャージが少しでもあれば
	public boolean isFullCharged()
	{
		return this.chargeAmount == 25600;
	}
	
	//チャージに空きがあり、燃料スロットのアイテムを受け入れられる状態
	@Override
	public boolean canReceiveChargeItem(ItemStack item)
	{
		boolean flag = false;
		boolean flag2 = false;
		if (item != null)
		{
			int i = ChargeItemManager.chargeItem.getChargeAmount(item);
			flag = this.getChargeAmount() + 1 <= 25600;
		}
		
		if (this.getStackInSlot(0) == null)
		{
			flag2 = true;
		}
		else
		{
			ItemStack current = this.getStackInSlot(0);
			flag2 = item.isItemEqual(current) && (current.stackSize + item.stackSize < current.getMaxStackSize());
		}
		
		return flag && flag2;
	}
	
	//以下はパケット送受信用メソッド
	public void setChargeAmount(int par1)
	{
		this.chargeAmount = par1;
	}
	
	public int getChargeAmount()
	{
		return this.chargeAmount;
	}
 
	/**
	 * Tick毎のアイスメーカーの処理。ほぼバニラかまどのパクリ。
	 */
	public void updateEntity()
	{
		boolean flag = this.isFullCharged();
		boolean flag1 = false;
 
		//まず燃料を溶かす処理
		
		//硬直時間：燃料の消費及び温暖バイオームでのチャージ減少に利用
		if (this.coolTime > 0)
		{
			--this.coolTime;
		}
 
		if (!this.worldObj.isRemote)
		{
			if (this.coolTime == 0)
			{
				//チャージが満タンではないか？
				if (!flag && this.isItemFuel(this.itemstacks[0]))
				{
					//チャージ残量＋アイテムのチャージ量
					int i = this.chargeAmount += getItemBurnTime(this.itemstacks[0]);
	 
					if (i <= 25600)//25600未満ならOK
					{
						this.chargeAmount = i;
						flag1 = true;
	 
						//スロット1のアイテムを減らす
						if (this.itemstacks[0] != null)
						{
							--this.itemstacks[0].stackSize;
	 
							if (this.itemstacks[0].stackSize == 0)
							{
								this.itemstacks[0] = null;
							}
						}
					}
				}
				
				//最後に硬直時間を8tickに設定
				this.coolTime = 4;
			}
			
			/*
			 * 調理の待ち時間&チャージ減少処理。
			 * 100tickの調理時間が終わるまでは、tick毎にチャージが減っていく。途中で0になったら調理が振り出しに戻る。
			 * */
			if (this.getChargeAmount() > 0 && this.canSmelt())
			{
				++this.cookTime;
				--this.chargeAmount;

				if (this.cookTime == 100)
				{
					this.cookTime = 0;
					this.onProgress();
					flag1 = true;
				}
			}
			else
			{
				this.cookTime = 0;
			}

			if (this.getChargeAmount() < 1)
			{
				flag1 = true;
			}
			
			
			this.markDirty();
		}
	}
 
	/**
	 * アイテムがIceMakerにレシピ登録された材料かどうか
	 * レシピ登録はTeaMakerと類似の登録制を使用
	 */
	public abstract boolean canSmelt();
 
	/**
	 * 実際に材料を消費して、完成スロットにアウトプットを返すためのメソッド
	 */
	public abstract void onProgress();
 
	/**
	 * このアイテムのチャージ量
	 * @param par0ItemStack チェック対象アイテム
	 */
	public static int getItemBurnTime(ItemStack par0ItemStack)
	{
		if (par0ItemStack == null)
		{
			return 0;
		}
		else
		{
			if (ChargeItemManager.chargeItem.getChargeAmount(par0ItemStack) > 0)
			{
				return ChargeItemManager.chargeItem.getChargeAmount(par0ItemStack);
			}
			
			return 0;
		}
	}
 
	/**
	 * このアイテムがチャージできる燃料であるかどうか
	 * @param par0ItemStack チェック対象アイテム
	 */
	public static boolean isItemFuel(ItemStack par0ItemStack)
	{
		return getItemBurnTime(par0ItemStack) > 0;
	}
	
	/* ========== 以下、ISidedInventoryのメソッド ==========*/
	
	/*
	 * 0 : 燃料搬入
	 * 1 : 燃料の空容器搬出
	 * 2~ : 各Tileで実装される。
	 * */
	protected abstract int[] slotsTop();
	protected abstract int[] slotsBottom();
	protected abstract int[] slotsSides();
 
	public ItemStack[] itemstacks = new ItemStack[getSizeInventory()];
 
 
	//スロット数は各Tileでオーバーライドして増やすこと。2は最低限の値。
	@Override
	public int getSizeInventory() {
		return 2;
	}
 
	//インベントリ内の任意のスロットにあるアイテムを取得
	@Override
	public ItemStack getStackInSlot(int par1) {
		return par1 < this.getSizeInventory() ? this.itemstacks[par1] : null;
	}
 
	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (this.itemstacks[par1] != null)
		{
			ItemStack itemstack;
 
			if (this.itemstacks[par1].stackSize <= par2)
			{
				itemstack = this.itemstacks[par1];
				this.itemstacks[par1] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.itemstacks[par1].splitStack(par2);
 
				if (this.itemstacks[par1].stackSize == 0)
				{
					this.itemstacks[par1] = null;
				}
 
				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}
 
	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		if (this.itemstacks[par1] != null)
		{
			ItemStack itemstack = this.itemstacks[par1];
			this.itemstacks[par1] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}
 
	// インベントリ内のスロットにアイテムを入れる
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		
		if (par1 > this.getSizeInventory()) par1 = 0;//存在しないスロットに入れようとすると強制的に材料スロットに変更される。
		
		this.itemstacks[par1] = par2ItemStack;
 
		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}
 
	// インベントリの名前
	@Override
	public abstract String getInventoryName();
 
	// 多言語対応かどうか
	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}
 
	// インベントリ内のスタック限界値
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
 
	@Override
	public void markDirty() {
		super.markDirty();
	}
 
	// par1EntityPlayerがTileEntityを使えるかどうか
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
	}
 
	@Override
	public void openInventory() {}
 
	@Override
	public void closeInventory() {}
 
	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
		return (par1 == 1 || par1 == 11) ? false : (par1 == 0 ? this.isItemFuel(par2ItemStack) : true);
	}
 
	//ホッパーにアイテムの受け渡しをする際の優先度
	@Override
	public int[] getAccessibleSlotsFromSide(int par1) {
		return par1 == 0 ? slotsBottom() : (par1 == 1 ? slotsTop() : slotsSides());
	}
 
	//ホッパーからアイテムを入れられるかどうか
	@Override
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
		return this.isItemValidForSlot(par1, par2ItemStack);
	}
 
	//隣接するホッパーにアイテムを送れるかどうか
	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
		return true;
	}
}
