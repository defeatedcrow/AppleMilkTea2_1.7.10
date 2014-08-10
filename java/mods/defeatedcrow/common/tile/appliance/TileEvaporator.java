package mods.defeatedcrow.common.tile.appliance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mods.defeatedcrow.api.edibles.EdibleItem;
import mods.defeatedcrow.api.edibles.IEdibleItem;
import mods.defeatedcrow.api.recipe.*;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.block.edible.EdibleEntityItemBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class TileEvaporator extends MachineBase{
	
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

	@Override
	public boolean canSmelt() {
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		boolean flag4 = false;
		
		ItemStack items = this.itemstacks[2];
		if (items == null) return false;
		
		IEvaporatorRecipe recipe = RecipeRegisterManager.evaporatorRecipe.getRecipe(items);
		if (recipe == null) return false;
		
		ItemStack output = recipe.getOutput();
		if (output == null) return false;
		else flag1 = true;
		
		ItemStack container = null;
		if (items.getItem() instanceof IEdibleItem)
		{
			IEdibleItem edible = (IEdibleItem)items.getItem();
			container = edible.getReturnContainer(items.getItemDamage());
		}
		else if (items.getItem().hasContainerItem(items))
		{
			container = items.getItem().getContainerItem(items);
		}
		
		if (this.itemstacks[3] == null)
		{
			flag2 = true;
		}
		else
		{
			if (this.itemstacks[3].isItemEqual(output))
			{
				int result = this.itemstacks[3].stackSize + output.stackSize;
				flag2 = (result <= this.getInventoryStackLimit() && result <= output.getMaxStackSize());
			}
		}
		
		if (container != null)
		{
			if (this.itemstacks[5] == null)
			{
				flag4 = true;
			}
			else
			{
				if (this.itemstacks[5].isItemEqual(container))
				{
					int result = this.itemstacks[5].stackSize + container.stackSize;
					flag4 = (result <= this.getInventoryStackLimit() && result <= container.getMaxStackSize());
				}
			}
		}
		else
		{
			flag4 = true;
		}
		
		FluidStack second = recipe.getSecondary();
		
		//液体ありのレシピの場合は、とりあえず禁止しておく
		if (second == null) flag3 = true;
		else
		{
			flag3 = false;
		}
		
//		AMTLogger.debugInfo("Evaporator update : " + flag1 + ", " + flag2 + ", " + flag3 + ", " + flag4);
		
		return flag1 && flag2 && flag3 && flag4;
	}

	@Override
	public void onProgress() {
		//結局canSmelt()と同じことをしていて無駄な感じはする
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		
		ItemStack items = this.itemstacks[2];
		if (items == null) return;
		
		IEvaporatorRecipe recipe = RecipeRegisterManager.evaporatorRecipe.getRecipe(items);
		if (recipe == null) return;
		
		ItemStack output = recipe.getOutput();
		if (output == null) return;
		else flag1 = true;
		
		ItemStack container = null;
		if (items.getItem() instanceof IEdibleItem)
		{
			IEdibleItem edible = (IEdibleItem)items.getItem();
			container = edible.getReturnContainer(items.getItemDamage());
		}
		else if (items.getItem().hasContainerItem(items))
		{
			container = items.getItem().getContainerItem(items);
		}
		
		//材料を減らし、返却アイテムが有る場合は返却スロットへ。
		if (this.itemstacks[2] != null)
		{
			--this.itemstacks[2].stackSize;

			if (this.itemstacks[2].stackSize == 0)
			{
				this.itemstacks[2] = this.itemstacks[2].getItem().getContainerItem(this.itemstacks[2]);
			}
			
			if (container != null)
			{
				if (this.itemstacks[5] == null)
				{
					this.itemstacks[5] = container.copy();
				}
				else if (this.itemstacks[5].isItemEqual(container))
				{
					this.itemstacks[5].stackSize += container.stackSize;
				}
			}
			
			flag2 = true;
		}
		
		if (flag1 && flag2)
		{
			
			AMTLogger.debugInfo("current recipe : " + output.toString());
			
			//次に完成品を完成品スロットへ
			if (this.itemstacks[3] == null)
			{
				this.itemstacks[3] = output.copy();
			}
			else if (this.itemstacks[3].isItemEqual(output))
			{
				this.itemstacks[3].stackSize += output.stackSize;
			}
			
			this.markDirty();
		}
	}
	
	/*====== 以下、インベントリ関係 ======*/
	
	/*
	 * フードプロセッサーの場合
	 * 燃料スロット：0
	 * 燃料空容器の排出スロット：1
	 * 材料スロット：2
	 * 完成品スロット：3,4
	 * 材料のから容器排出：5
	 * 上記にプラスして排出用液体スロットひとつ
	 * */
	
	@Override
	public int getSizeInventory() {
		return 6;
	}

	@Override
	protected int[] slotsTop() {
		return new int[]{0,2};
	}

	@Override
	protected int[] slotsBottom() {
		return new int[]{1,3,4,5};
	}

	@Override
	protected int[] slotsSides() {
		return new int[]{0,1,2,3,4,5};
	}
	
	@Override
	public String getInventoryName() {
		return "Evaporator";
	}

}
