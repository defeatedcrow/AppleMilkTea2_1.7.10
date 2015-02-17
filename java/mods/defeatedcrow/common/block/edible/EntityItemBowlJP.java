package mods.defeatedcrow.common.block.edible;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.entity.edible.*;
import mods.defeatedcrow.plugin.LoadBambooPlugin;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityItemBowlJP extends EdibleEntityItemBlock2{
	
	public static final String[] type = new String[] {"_rice", "_mushroom", "_stew", "_zousui", "_kayaku", "_soi", "_pumpkin", "_BLTsoup", "_misosoup", "_clamsoup"};
	
	public EntityItemBowlJP(Block block)
	{
		super(block, true, false);
		setMaxDamage(0);
		setHasSubtypes(true);
		
		if (LoadBambooPlugin.bambooBasket != null) {
			setContainerItem(LoadBambooPlugin.bambooBasket.getItem());
		}
		else {
			setContainerItem(Items.bowl);
		}
	}
	
	@Override
	public int[] hungerOnEaten(int meta) {
		return new int[] {4,2};
	}
	
	@Override
	public ItemStack getReturnContainer(int meta) {
		
		return (LoadBambooPlugin.bambooBasket != null) ? LoadBambooPlugin.bambooBasket.copy(): new ItemStack(Items.bowl, 1, 0);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int m = (par1ItemStack.getItemDamage());
		if (m < 10) return super.getUnlocalizedName() + type[m];
		else return super.getUnlocalizedName() + m;
	}
	
	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}
	
	@Override
	protected boolean spownEntityFoods(World world, EntityPlayer player, ItemStack item, double x, double y, double z)
	{
		PlaceableBowlJP entity = new PlaceableBowlJP(world, item, x, y, z);
		entity.rotationYaw = player.rotationYaw - 180.0F;
		
		if (!world.isRemote && item != null)
		{
			return world.spawnEntityInWorld(entity);
		}
		
		return false;
	}

}
