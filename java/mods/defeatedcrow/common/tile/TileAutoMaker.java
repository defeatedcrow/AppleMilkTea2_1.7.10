package mods.defeatedcrow.common.tile;

import mods.defeatedcrow.api.recipe.ITeaRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.block.BlockAutoMaker;
import mods.defeatedcrow.common.tile.appliance.TileMakerNext;
import mods.defeatedcrow.handler.Util;
import mods.defeatedcrow.recipe.*;
import mods.defeatedcrow.recipe.TeaRecipeRegister.TeaRecipe;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileAutoMaker extends TileEntity implements IInventory
{
    private int coolTime = 0;
    private boolean autoMode = false;
    
    //モード切り替え
    private final String[] modeName = new String[] {"None", "AutoMode", "RSMode"};
    private byte mode = 0;
    
    //下にあるTileMakerNext
    private TileMakerNext tileNext;
    
    public ItemStack[] holdItemStacks = new ItemStack[1];

    //NBT
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);

        if (par1NBTTagCompound.hasKey("HoldItem"))
        {
            this.setItemstack(ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("HoldItem")));
        }
        
        this.coolTime = par1NBTTagCompound.getShort("CoolTime");
        
        this.mode = par1NBTTagCompound.getByte("Mode");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("CoolTime", (short)this.coolTime);
        par1NBTTagCompound.setByte("Mode", (byte)this.mode);

        if (this.getItemstack() != null)
        {
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

    public ItemStack getItemstack()
    {
        return this.getStackInSlot(0);
    }
    
    public int getCoolTime()
    {
    	return this.coolTime;
    }
    
    public byte getMode()
    {
    	return this.mode;
    }
    
    public boolean isCoolTime()
    {
    	return this.coolTime > 0;
    }
    
    public boolean isAutoMode()
    {
    	return this.mode == 1 ? true : false;
    }
    

    public void setItemstack(ItemStack par1ItemStack)
    {
        this.setInventorySlotContents(0, par1ItemStack);
        this.markDirty();
    }
    
    public void setCoolTime(int par1)
    {
    	this.coolTime = par1;
    }
    
    public void setMode(byte par1)
    {
    	this.mode = par1;
    }
    
    //update tileentity
    @Override
    public void updateEntity()
    {
    	if (this.worldObj != null)
        {
            --this.coolTime;

            if (!this.isCoolTime())
            {
                this.setCoolTime(0);
                this.updateAutoMaker();
            }
        }
    	
    	super.updateEntity();
    }
    
    private boolean updateAutoMaker()
    {
    	if (this.worldObj != null && !this.worldObj.isRemote)
        {
    		if (this.holdItemStacks[0] != null && !this.isCoolTime() && this.mode > 0 && this.isMakerEmpty())
    		{
    			if (this.isAutoMode())
    			{
    				boolean flag = false;
//    				int id = this.isTeaMaterial(this.getItemstack());
//    				TileMakerNext tile = (TileMakerNext)this.worldObj.getTileEntity(xCoord, (yCoord - 1), zCoord);
//        			
//        			if (tile != null && id > 1 && this.updateBlock() && this.reduceItemStack())
//        			{
//        				flag = true;
//        				this.markDirty();
//        				tile.markDirty();
//        				this.setCoolTime(8);
//        			}
//    				
    				return flag;
    			}
    			else
    			{
    				this.setCoolTime(8);
    				return false;
    			}
    		}
    		else
    		{
    			return false;
    		}
        }
    	else
    	{
    		return false;
    	}
	}
    
    private boolean updateBlock() {
    	
		boolean flag = false;
		Block under = this.worldObj.getBlock(xCoord, yCoord -1, zCoord);
    	if (this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3))
    	{
//    		this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
//        	this.worldObj.notifyBlockOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, under);
        	flag = true;
    	}
    	
    	return flag;
    	
	}

	public boolean reduceItemStack()
    {
    	boolean flag = false;
    	int stackSize = 0;
    	if (this.getItemstack() == null)
    	{
    		flag = false;
    	}
    	else
    	{
    		stackSize = this.getItemstack().stackSize;
        	if (stackSize > 1)
        	{
        		ItemStack copy = this.getItemstack().copy();
        		stackSize--;
        		copy.stackSize -= 1;
        		this.setInventorySlotContents(0, copy);
        		flag = true;
        	}
        	else
        	{
        		this.setInventorySlotContents(0,(ItemStack)null);
        		flag =  true;
        	}
    	}
    	
    	return flag;
    }
    
    private boolean isMakerEmpty()
    {
    	boolean flag = false;
    	if (this.onMakerNext())
    	{
    		if (this.tileNext != null && this.tileNext.getRecipe() == null)
    		{
    			flag = true;
    		}
    	}
    	return flag;
    }
    
    private boolean onMakerNext()
    {
    	TileEntity tile = this.worldObj.getTileEntity(xCoord, (yCoord - 1), zCoord);
    	if (this.worldObj != null && tile != null && tile instanceof TileMakerNext)
    	{
    		this.tileNext = (TileMakerNext)tile;
    		return true;
    	}
    	return false;
    }
    
    private boolean isTeaMaterial(ItemStack input)
    {
    	ITeaRecipe recipe = RecipeRegisterManager.teaRecipe.getRecipe(input);
    	return recipe != null;
    }

	private int getMetadata()
    {
    	return this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
    }

	// スロット数
	@Override
	public int getSizeInventory() {
		return this.holdItemStacks.length;
	}
	 
	// インベントリ内の任意のスロットにあるアイテムを取得
	@Override
	public ItemStack getStackInSlot(int par1) {
		return this.holdItemStacks[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (this.holdItemStacks[par1] != null)
		{
			ItemStack itemstack;
 
			if (this.holdItemStacks[par1].stackSize <= par2)
			{
				itemstack = this.holdItemStacks[par1];
				this.holdItemStacks[par1] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.holdItemStacks[par1].splitStack(par2);
 
				if (this.holdItemStacks[par1].stackSize == 0)
				{
					this.holdItemStacks[par1] = null;
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
		if (this.holdItemStacks[par1] != null)
		{
			ItemStack itemstack = this.holdItemStacks[par1];
			this.holdItemStacks[par1] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		this.holdItemStacks[par1] = par2ItemStack;
 
		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
	}
 
	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
		
		boolean flag = false;
		if (par2ItemStack != null && this.isTeaMaterial(par2ItemStack))
		{
			flag = true;
		}
		return flag;
	}

	@Override
	public String getInventoryName() {
		return "Automated TeaMaker";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}
    
}
