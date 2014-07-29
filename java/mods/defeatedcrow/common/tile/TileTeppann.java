package mods.defeatedcrow.common.tile;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.DCsConfig;
import mods.defeatedcrow.handler.Util;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileTeppann extends TileEntity
{
    private ItemStack cookingItem;
    private int cookingTime = 0;
    private boolean finishedCooking = false;
    private int readyTime = 0;
    private boolean tooLate = false;

    //NBT
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);

        if (par1NBTTagCompound.hasKey("CookingItem"))
        {
            this.getItemstack(ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("CookingItem")));
        }
        
        this.cookingTime = par1NBTTagCompound.getShort("CookingTime");
        
        this.finishedCooking = par1NBTTagCompound.getBoolean("FinishedCooking");
        
        this.readyTime = par1NBTTagCompound.getShort("ReadyTime");
        
        this.tooLate = par1NBTTagCompound.getBoolean("TooLate");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("CookingTime", (short)this.cookingTime);
        par1NBTTagCompound.setBoolean("FinishedCooking", this.finishedCooking);
        par1NBTTagCompound.setShort("ReadyTime", (short)this.readyTime);
        par1NBTTagCompound.setBoolean("TooLate", this.tooLate);

        if (this.setItemstack() != null)
        {
            par1NBTTagCompound.setTag("CookingItem", this.setItemstack().writeToNBT(new NBTTagCompound()));
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

    public ItemStack setItemstack()
    {
        return this.cookingItem;
    }
    
    public int setCookingTime()
    {
    	return this.cookingTime;
    }
    
    public int setReadyTime()
    {
    	return this.readyTime;
    }
    
    public boolean isCooking()
    {
    	return this.cookingTime > 0;
    }
    
    public boolean isCookingFinished()
    {
    	return this.finishedCooking;
    }
    
    public boolean isTooLate()
    {
    	return this.tooLate;
    }

    public void getItemstack(ItemStack par1ItemStack)
    {
        this.cookingItem = par1ItemStack;
        this.markDirty();
    }
    
    public void getCookingTime(int par1)
    {
    	this.cookingTime = par1;
    }
    
    public void getReadyTime(int par1)
    {
    	this.readyTime = par1;
    }
    
    //update tileentity
    public void updateEntity()
    {
    	if (this.cookingTime > 0)
    	{
    		--this.cookingTime;
    		
    		if (this.cookingTime == 0)
    		{
    			this.finishedCooking = true;
    			this.worldObj.playSoundEffect(xCoord, yCoord, zCoord, "random.fizz", 1.0F, 1.0F);
    			this.markDirty();
    			this.updateTeppannMeta();
    			this.readyTime = Util.getTeppannReadyTime();
    		}
    		else if (this.cookingItem == null)
    		{
    			this.cookingTime = 0;
    			this.markDirty();
    			this.updateTeppannMeta();
    		}
    	}
    	else if (this.cookingItem != null && !this.finishedCooking)
    	{
    		this.cookingTime = this.getCookingTime();
    		this.readyTime = 0;
    		this.tooLate = false;
    		this.markDirty();
    		this.updateTeppannMeta();
    	}
    	else if (this.cookingItem == null && this.finishedCooking)
    	{
    		this.finishedCooking = false;
    		this.updateTeppannMeta();
    	}
    	
    	if (this.readyTime > 0)
    	{
    		--this.readyTime;
    		
    		if (this.readyTime == 0)
    		{
    			if (this.cookingItem != null || this.finishedCooking)
    			{
    				if (DCsConfig.teppannHardMode)
    				{
    					this.tooLate = true;
    					this.worldObj.playSoundEffect(xCoord, yCoord, zCoord, "random.fizz", 1.0F, 1.0F);
    					this.updateTeppannMeta();
    					this.markDirty();
    				}
    				else
    				{
    					this.tooLate = false;
    				}
    			}
    			else
    			{
    				this.tooLate = false;
    			}
    		}
    		else if (this.cookingItem == null || !this.finishedCooking)
    		{
    			this.tooLate = false;
    			this.readyTime = 0;
    			this.updateTeppannMeta();
    			this.markDirty();
    		}
    	}
    	
    }
    
    private int getCookingTime()
    {
    	int l = 1;
    	
    	if (DCsConfig.teppannRandomCookTime) l += this.worldObj.rand.nextInt(100);
    	else l = 100;
    	
    	return l;
    }
    
    private int getMetadata()
    {
    	return this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
    }
    
    private void updateTeppanFoods()
    {
    	int meta = this.getMetadata();
    	int ItemCount = this.cookingItem.stackSize;
    	
    	if (meta == 2) this.cookingItem = (new ItemStack(DCsAppleMilk.foodPlate, ItemCount, 0));
    	else if (meta == 4) this.cookingItem = (new ItemStack(DCsAppleMilk.foodPlate, ItemCount, 1));
    	else if (meta == 6) this.cookingItem = (new ItemStack(DCsAppleMilk.foodPlate, ItemCount, 2));
    	else if (meta == 8) this.cookingItem = (new ItemStack(DCsAppleMilk.foodPlate, ItemCount, 3));
    }
    
    public void updateTeppannMeta()
    {
    	int meta = 0;
    	
    	if (this.cookingItem != null)
    	{
    		if (!this.isTooLate())
    		{
    			if (this.cookingItem.getItem() == Items.beef)
        		{
        			meta = this.finishedCooking ?  2 : 1;
        		}
        		else if (this.cookingItem.getItem() == Items.porkchop)
        		{
        			meta = this.finishedCooking ?  4 : 3;
        		}
        		else if (this.cookingItem.getItem() == Items.chicken)
        		{
        			meta = this.finishedCooking ?  6 : 5;
        		}
        		else if (this.cookingItem.getItem() == DCsAppleMilk.clam)
        		{
        			meta = this.finishedCooking ?  8 : 7;
        		}
    		}
    		else
    		{
    			meta = 9;
    		}
    		
    	}
    	
    	
    	this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, meta, 3);
    }
}
