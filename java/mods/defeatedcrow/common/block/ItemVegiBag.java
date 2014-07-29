package mods.defeatedcrow.common.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemVegiBag extends ItemBlock{
	
	private static final String[] bagVegi = new String[] {"_Leaves", "_Potato", "_Carrot", "_Pumpkin", "_Seed", "_Reed", "_Cactus", "Cocoa", "_Wart", "_Sugar"};
	
	public ItemVegiBag(Block block)
	{
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int m = (par1ItemStack.getItemDamage());
		if (m < 10) return super.getUnlocalizedName() + bagVegi[m];
		else return super.getUnlocalizedName() + m;
	}
	
	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}

}
