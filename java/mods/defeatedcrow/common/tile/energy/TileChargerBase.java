package mods.defeatedcrow.common.tile.energy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.api.charge.ChargeItemManager;
import mods.defeatedcrow.api.charge.IChargeableMachine;
import mods.defeatedcrow.api.energy.IBattery;
import mods.defeatedcrow.common.DCsConfig;
import mods.defeatedcrow.handler.Util;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/*AMT単体で動作させる場合は、このクラスだけで事足りる*/
public class TileChargerBase extends TileEntity implements ISidedInventory, IChargeableMachine{
	
	//現在のチャージ量
	private int chargeAmount = 0;
	//チャージアイテムを溶かす際の判定発生間隔
	private int coolTime = 4;
	
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
		
		this.chargeAmount = par1NBTTagCompound.getInteger("ChargeAmount");
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
		
		par1NBTTagCompound.setTag("Items", nbttaglist);
		
		//燃焼時間や調理時間などの書き込み
		par1NBTTagCompound.setInteger("ChargeAmount", this.chargeAmount);
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
	
	//チャージゲージの描画
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int par1)
	{
		return this.chargeAmount * par1 / this.getMaxChargeAmount();
	}
	
	//チャージが満タンである
	public boolean isFullCharged()
	{
		return this.chargeAmount == this.getMaxChargeAmount();
	}
	
	//チャージゲージ上限も変更可能に。
	public int getMaxChargeAmount()
	{
		return 128000;
	}
	
	/* ゲッターとセッター */
	
	public void setChargeAmount(int par1)
	{
		this.chargeAmount = par1;
	}
	
	public int getChargeAmount()
	{
		return this.chargeAmount;
	}
	
	//アプデ処理
	@Override
	public void updateEntity()
	{
		boolean flag = this.isFullCharged();
		boolean flag1 = false;
 
		//まず燃料を溶かす処理
		
		//硬直時間：燃料の消費に利用
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
					int i = this.chargeAmount + getItemBurnTime(this.itemstacks[0]);
	 
					if (i <= this.getMaxChargeAmount())//指定したチャージ上限より小さいかどうか
					{
						this.chargeAmount = i;
						flag1 = true;
	 
						if (this.itemstacks[0].getItem() instanceof IBattery)
						{
							//IBatteryの場合、ここでは空容器のスロット移動は行わない。
							IBattery bat = (IBattery) this.itemstacks[0].getItem();
							bat.discharge(this.itemstacks[0], 16, true);
						}
						else//スロット1のアイテムを減らす
						{
							this.decrStackSize(0, 1);
						}
					}
					
				}
				
				//からになったIBatteryのスタック移動はここ
				if (this.itemstacks[0] != null && this.itemstacks[0].getItem() instanceof IBattery)
				{
					IBattery bat = (IBattery) this.itemstacks[0].getItem();
					if (bat.getChargeAmount(this.itemstacks[0]) == 0
							&& this.itemstacks[1] == null)
					{
						this.setInventorySlotContents(1, this.itemstacks[0].copy());
						this.decrStackSize(0, 1);
					}
				}
				
				//次はスロット2~9の充電池を順に充電していく
				for (int i = 2 ; i < this.getSizeInventory() ; i++)
				{
					ItemStack current = this.itemstacks[i];
					int charge = this.getChargeAmount();
					int inc = Math.min(charge, 16);
					
					if (Util.notEmptyItem(current) && current.getItem() instanceof IBattery && inc > 0)
					{
						IBattery bat = (IBattery) current.getItem();
						int ret = bat.charge(current, inc, true);
						this.setChargeAmount(charge - ret);
					}
				}
				
				//最後に硬直時間を4tickに設定
				this.coolTime = DCsConfig.batteryUpdate;
			}
			
			//sideごとにケーブルなどからのエネルギーを取得。ただしこのMODにはケーブル類はない。
			ForgeDirection[] dirs = ForgeDirection.VALID_DIRECTIONS;
			for (ForgeDirection dir : dirs)
			{
				if (!this.isFullCharged())
				{
					int accept = this.acceptChargeFromDir(dir);
					int cap = this.getMaxChargeAmount() - this.getChargeAmount();
					accept = Math.min(accept, cap);
					this.chargeAmount += accept;
				}
				
			}
			
			if (this.chargeAmount > this.getMaxChargeAmount())
			{
				this.chargeAmount = this.getMaxChargeAmount();
			}
			
			if (this.getChargeAmount() < 1)
			{
				flag1 = true;
			}
			
			
			this.markDirty();
		}
	}
	
	/* IChargeableMachineのメソッド */
	
	@Override
	public boolean isActive()
	{
		return this.coolTime > 0;
	}
	
	@Override
	public boolean canReceiveChargeItem(ItemStack item)
	{
		boolean flag = false;
		boolean flag2 = false;
		if (item != null)
		{
			int i = this.getItemBurnTime(item);
			flag = i > 0 && (this.getChargeAmount() + i <= this.getMaxChargeAmount());
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
			else if (par0ItemStack.getItem() instanceof IBattery)
			{
				//充電池の場合、16/4tickずつ減少する。
				IBattery bat = (IBattery) par0ItemStack.getItem();
				int ret = bat.discharge(par0ItemStack, 16, false);
				return ret;
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
	
	/**
	 * 隣接ブロックからエネルギーを受け入れるメソッド。継承先で中身を入れる。
	 * AMT単体では特に用のないメソッド
	 * */
	public int acceptChargeFromDir(ForgeDirection dir)
	{
		return 0;
	}
	
/* ========== 以下、ISidedInventoryのメソッド ==========*/
	
	/*
	 * 0 : 燃料搬入
	 * 1 : 燃料の空容器搬出
	 * 2~ : 各Tileで実装される。
	 * */
	protected int[] slotsTop(){
		return new int[]{0,1,2,3,4,5,6,7,8,9};
	}
	
	protected int[] slotsBottom(){
		return new int[]{0,1,2,3,4,5,6,7,8,9};
	}
	
	protected int[] slotsSides(){
		return new int[]{0,1,2,3,4,5,6,7,8,9};
	}
 
	public ItemStack[] itemstacks = new ItemStack[getSizeInventory()];
 
 
	//スロット数は各Tileでオーバーライドして増やすこと。2は最低限の値。
	@Override
	public int getSizeInventory() {
		return 10;
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
	public String getInventoryName(){
		return "Battery Charger";
	}
 
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
		if (par1 == 1)
		{
			return false;
		}
		else if (par1 == 0)
		{
			return this.isItemFuel(par2ItemStack);
		}
		else
		{
			return par2ItemStack != null && par2ItemStack.getItem() instanceof IBattery;
		}
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
		if (par1 == 1)
		{
			return true;
		}
		else if (par1 > 1)
		{
			if (par2ItemStack != null && par2ItemStack.getItem() instanceof IBattery)
			{
				IBattery bat = (IBattery) par2ItemStack.getItem();
				return bat.isFullCharged(par2ItemStack);
			}
		}
		return false;
	}

}
