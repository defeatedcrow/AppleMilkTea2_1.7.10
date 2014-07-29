package mods.defeatedcrow.common.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemTeaMaker extends ItemBlock{
	
	private static final String[] type = new String[] {"_empty", "_milk", "_tea", "_tea_milk", "_greentea", "_greentea_milk", "_cocoa", "_cocoa_milk", "_coffee", "_coffee_milk", "_juice", "_fruitshakes", "_lemon", "_lemon_milk"};
	
	public ItemTeaMaker(Block block)
	{
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int m = (par1ItemStack.getItemDamage());
		if (m < 14) return super.getUnlocalizedName() + type[m];
		else return super.getUnlocalizedName() + m;
		
	}
	
	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}

}
