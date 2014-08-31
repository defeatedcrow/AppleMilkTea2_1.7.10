package mods.defeatedcrow.common.block.container;

import net.minecraft.block.Block;
import net.minecraft.item.*;

public class ItemCardboard extends ItemBlock{
	
	private static final String[] bagVegi = new String[] {"_mint", "_cassis", "_yuzu", "_camellia"};
	
	public ItemCardboard(Block block)
	{
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int m = (par1ItemStack.getItemDamage() & 7);
		if (m < 4) return super.getUnlocalizedName() + bagVegi[m];
		else return super.getUnlocalizedName() + m;
	}
	
	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}

}
