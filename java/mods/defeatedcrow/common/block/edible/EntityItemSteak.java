package mods.defeatedcrow.common.block.edible;

import java.util.ArrayList;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.entity.edible.*;
import mods.defeatedcrow.plugin.LoadSSectorPlugin;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityItemSteak extends EdibleEntityItemBlock{
	
	private static final String[] type = new String[] {"_beef", "_pork", "_chicken", "_clam"};
	
	public EntityItemSteak(Block block)
	{
		super(block, true, false);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int m = (par1ItemStack.getItemDamage());
		if (m < 4) return super.getUnlocalizedName() + type[m];
		else return super.getUnlocalizedName() + m;
	}
	
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        int meta = par1ItemStack.getItemDamage();
        int heal = (meta == 3) ? 1 : 2;
		
		if (!par2World.isRemote)
		{
			if (meta < 3 && DCsAppleMilk.suffocation != null)
			{
				boolean flag = par3EntityPlayer.isPotionActive(DCsAppleMilk.suffocation)
						&& (par3EntityPlayer.getActivePotionEffect(DCsAppleMilk.suffocation).getDuration() < 100);
				
				if (flag){
					int dur = par3EntityPlayer.getActivePotionEffect(DCsAppleMilk.suffocation).getDuration();
					par3EntityPlayer.addPotionEffect(new PotionEffect(DCsAppleMilk.suffocation.id, dur + 100, 1));
				}
				else {
					par3EntityPlayer.addPotionEffect(new PotionEffect(DCsAppleMilk.suffocation.id, 200, 0));
				}
			}
		}

        return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
    }
	
	@Override
	public ArrayList<PotionEffect> effectOnEaten(EntityPlayer player, int meta) {
		
		ArrayList<PotionEffect> ret = new ArrayList<PotionEffect>();
		if (meta == 3)
		{
			ret.add(new PotionEffect(Potion.field_76443_y.id, 3, 2));
		}
		else
		{
			ret.add(new PotionEffect(Potion.field_76443_y.id, 3, 3));
		}
		
		return ret;
	}
	
	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}
	
	@Override
	protected boolean spownEntityFoods(World world, EntityPlayer player, ItemStack item, double x, double y, double z)
	{
		PlaceableSteak entity = new PlaceableSteak(world, item, x, y, z);
		entity.rotationYaw = player.rotationYaw - 180.0F;
		
		if (!world.isRemote && item != null)
		{
			return world.spawnEntityInWorld(entity);
		}
		
		return false;
	}

}
