package mods.defeatedcrow.common.block.edible;

import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.entity.edible.*;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityItemAlcoholCup extends EdibleEntityItemBlock{
	
	private static final String[] type = new String[] {"_sake", "_beer", "_wine", "_rum", "_gin", "_vodka", "_whiskey",
		"_apple", "_tea", "_cassis", "_plum", "_shothu", "_brandy", "_amaretto"};
	
	public EntityItemAlcoholCup(Block block)
	{
		super(block, false, true);
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
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par2World.isRemote)
		{
    		this.addSSMoisture(4, 3F, par3EntityPlayer);
    		if (DCsAppleMilk.suffocation != null && par3EntityPlayer.isPotionActive(DCsAppleMilk.suffocation))
    		{
    			par3EntityPlayer.removePotionEffect(DCsAppleMilk.suffocation.id);
    		}
		}
		return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
	}
	
	@Override
	public ArrayList<PotionEffect> effectOnEaten(EntityPlayer player, int meta) {
		
		ArrayList<PotionEffect> ret = new ArrayList<PotionEffect>();
		ret.add(new PotionEffect(Potion.hunger.id, 200, 1));
		ret.addAll(this.getPotionWithIce(player, meta));
		return ret;
	}
	
	@Override
	public int[] hungerOnEaten(int meta) {
		return new int[] {0,0};
	}
	
	@Override
	public ItemStack getReturnContainer(int meta) {
		
		return new ItemStack(DCsAppleMilk.emptyCup, 1, 0);
	}
	
	protected static ArrayList<PotionEffect> getPotionWithIce (EntityPlayer par1EntityPlayer, int meta)
	{
		PotionEffect potion = new PotionEffect(Potion.heal.id, 1, 1);
		int tick = 2400;
		boolean flag = false;
		
		ArrayList<PotionEffect> ret = new ArrayList<PotionEffect>();
		
		if((meta == 0 || meta == 11) && DCsAppleMilk.reflex != null)//sake
		{
			potion = new PotionEffect(DCsAppleMilk.reflex.id, 600, 0);
		}
		else if(meta == 1)//beer
		{
			potion = new PotionEffect(Potion.digSpeed.id, 2400, 0);
		}
		else if (meta == 2 && DCsAppleMilk.absHeal != null)//wine
		{
			potion = new PotionEffect(DCsAppleMilk.absHeal.id, 600, 0);
		}
		else if (meta == 3)//rum
		{
			potion = new PotionEffect(Potion.resistance.id, 2400, 1);
		}
		else if (meta == 4 || meta == 13)//gin
		{
			potion = new PotionEffect(Potion.damageBoost.id, 2400, 0);
		}
		else if (meta == 5)//vodka
		{
			potion = new PotionEffect(Potion.damageBoost.id, 2400, 1);
		}
		else if (meta == 6)//wiskey
		{
			potion = new PotionEffect(Potion.digSpeed.id, 2400, 1);
		}
		else if (meta == 7)//apple
		{
			potion = new PotionEffect(Potion.jump.id, 2400, 1);
		}
		else if (meta == 8)//tea
		{
			potion = new PotionEffect(Potion.heal.id, 1, 1);
		}
		else if (meta == 9)//cassis
		{
			potion = new PotionEffect(Potion.fireResistance.id, 2400, 0);
		}
		else if (meta == 12)//brandy
		{
			potion = new PotionEffect(Potion.regeneration.id, 1200, 1);
		}
		else if (meta == 10 && DCsAppleMilk.prvSuffocation != null)//plum
		{
			potion = new PotionEffect(DCsAppleMilk.prvSuffocation.id, 2400, 0);
		}
		else
		{
			potion = new PotionEffect(Potion.heal.id, 1, 1);
		}
		
		ret.add(potion);
		return ret;
	}
	
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
    	return EnumAction.drink;
    }
	
	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}
	
	@Override
	protected boolean spownEntityFoods(World world, EntityPlayer player, ItemStack item, double x, double y, double z)
	{
		PlaceableAlcoholCup entity = new PlaceableAlcoholCup(world, item, x, y, z);
		entity.rotationYaw = player.rotationYaw - 180.0F;
		
		if (!world.isRemote && item != null)
		{
			return world.spawnEntityInWorld(entity);
		}
		
		return false;
	}
}
