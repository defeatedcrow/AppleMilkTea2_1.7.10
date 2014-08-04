package mods.defeatedcrow.common.tile.appliance;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mods.defeatedcrow.api.recipe.IProsessorRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.recipe.ProsessorRecipeRegister.ProsessorRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.oredict.OreDictionary;

public class TileProsessor extends MachineBase{

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
		
		List<ItemStack> items = new ArrayList<ItemStack>(this.getCurrentContains());
		List<IProsessorRecipe> recipes = new ArrayList<IProsessorRecipe>((List<IProsessorRecipe>) RecipeRegisterManager.prosessorRecipe.getRecipes());
		if (recipes == null || recipes.isEmpty()) return false;
		
		ItemStack output = null;
		
		for(IProsessorRecipe recipe : recipes)
		{
			if (recipe.matches(items))
			{
				output = recipe.getOutput().copy();
				flag1 = true;
				break;
			}
		}
		
		if (output == null) return false;
		
		if (this.itemstacks[11] == null)
		{
			flag2 = true;
		}
		else
		{
			if (this.itemstacks[11].isItemEqual(output))
			{
				int result = this.itemstacks[11].stackSize + output.stackSize;
				flag2 = (result <= this.getInventoryStackLimit() && result <= output.getMaxStackSize());
			}
		}
		
		return flag1 && flag2;
	}
	
	private List<ItemStack> getCurrentContains()
	{
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		for (int i = 2; i < 11; i++)
		{
			if (this.itemstacks[i] != null)
			{
				items.add(this.itemstacks[i].copy());
			}
		}
		return items;
	}

	@Override
	public void onProgress() {
		//結局canSmelt()と同じことをしていて無駄な感じはする
		List<ItemStack> items = new ArrayList<ItemStack>(this.getCurrentContains());
		List<IProsessorRecipe> recipes = new ArrayList<IProsessorRecipe>((List<IProsessorRecipe>) RecipeRegisterManager.prosessorRecipe.getRecipes());
		if (recipes == null || recipes.isEmpty()) return;
		
		IProsessorRecipe activeRecipe = null;
		boolean flag = false;
		
		for(IProsessorRecipe recipe : recipes)
		{
			if (recipe.matches(items))
			{
				activeRecipe = recipe;
				flag = true;
			}
		}
		
		if (flag && activeRecipe != null)
		{
			//まずは材料を減らす
			List<Object> required = activeRecipe.getProcessedInput();
			ItemStack output = activeRecipe.getOutput().copy();
			
			for (int i = 2; i < 11; i++)
			{
				ItemStack slot = this.itemstacks[i];

	            if (slot != null)
	            {
	                boolean inRecipe = false;
	                Iterator<Object> req = required.iterator();

	                //9スロットについて、要求材料の数だけ回す
	                while (req.hasNext())
	                {
	                    boolean match = false;
	                    Object next = req.next();
	                    int count = ((ItemStack)next).stackSize;

	                    if (next instanceof ItemStack)
	                    {
	                        match = OreDictionary.itemMatches((ItemStack)next, slot, false)
	                        		&& slot.stackSize >= count;
	                    }
	                    else if (next instanceof ArrayList)
	                    {
	                        Iterator<ItemStack> itr = ((ArrayList<ItemStack>)next).iterator();
	                        while (itr.hasNext() && !match)
	                        {
	                            match = OreDictionary.itemMatches(itr.next(), slot, false)
	                            		&& slot.stackSize > 0;
	                        }
	                    }

	                    if (match)
	                    {
	                        inRecipe = true;
	                        required.remove(next);
	                        this.itemstacks[i].stackSize -= count;;
	                        if (this.itemstacks[i].stackSize == 0) this.itemstacks[i] = null;
	                        break;
	                    }
	                }

	                if (!inRecipe)
	                {
	                    return;//中断
	                }
	            }
			}
			
			//次に完成品を完成品スロットへ
			if (this.itemstacks[11] == null)
			{
				this.itemstacks[11] = output.copy();
			}
			else if (this.itemstacks[11].isItemEqual(output))
			{
				this.itemstacks[11].stackSize += output.stackSize;
			}
		}
	}
	
	/*====== 以下、インベントリ関係 ======*/
	
	/*
	 * フードプロセッサーの場合
	 * 燃料スロット：0
	 * 燃料空容器の排出スロット：1
	 * 材料スロット：2～10
	 * 完成品スロット：11
	 * */
	
	@Override
	public int getSizeInventory() {
		return 12;
	}

	@Override
	protected int[] slotsTop() {
		return new int[]{0,2,3,4,5,6,7,8,9,10};
	}

	@Override
	protected int[] slotsBottom() {
		return new int[]{1,11};
	}

	@Override
	protected int[] slotsSides() {
		return new int[]{0,1,11};
	}
	
	@Override
	public String getInventoryName() {
		return "Food Prosessor";
	}

}
