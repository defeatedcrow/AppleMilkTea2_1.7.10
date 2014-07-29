package mods.defeatedcrow.common.tile;

import java.io.DataOutputStream;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class ContainerAutoMaker extends Container
{
    private final TileAutoMaker inventory;
	private World world;
	private int xCoord;
	private int yCoord;
	private int zCoord;
	private byte Mode = 0;
	private EntityPlayer player;

    public ContainerAutoMaker(InventoryPlayer par1InventoryPlayer, TileAutoMaker tile)
    {
        this.inventory = tile;
        this.inventory.openInventory();;
        this.player = par1InventoryPlayer.player;
        this.world = tile.getWorldObj();
        this.xCoord = tile.xCoord;
        this.yCoord = tile.yCoord;
        this.zCoord = tile.zCoord;
        this.Mode = tile.getMode();
        
        byte b0 = 84;
        int i;

        for (i = 0; i < this.inventory.getSizeInventory(); ++i)
        {
            addSlotToContainer(new Slot(this.inventory, i, 80 + i * 18, 48));
        }

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, i * 18 + b0));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 58 + b0));
        }
    }
    
    public void addCraftingToCrafters(ICrafting par1ICrafting)
	{
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, this.inventory.getMode());
	}
 
	// 更新を送る
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
 
		for (int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting icrafting = (ICrafting)this.crafters.get(i);
 
			if (this.Mode != this.inventory.getMode())
			{
				icrafting.sendProgressBarUpdate(this, 0, this.inventory.getMode());
			}
		}
 
		this.Mode = this.inventory.getMode();
	}
 
	// 更新する
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2)
	{
		if (par1 == 0)
		{
			this.inventory.setMode((byte)par2);
		}
	}

	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.inventory.isUseableByPlayer(par1EntityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 < this.inventory.getSizeInventory())
            {
                if (!this.mergeItemStack(itemstack1, this.inventory.getSizeInventory(), this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.inventory.getSizeInventory(), false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer par1EntityPlayer)
    {
        super.onContainerClosed(par1EntityPlayer);
        this.inventory.closeInventory();;
    }
    
}
