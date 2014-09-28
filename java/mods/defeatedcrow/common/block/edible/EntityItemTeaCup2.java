package mods.defeatedcrow.common.block.edible;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.entity.edible.*;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityItemTeaCup2 extends EdibleEntityItemBlock{
	
	public static final String[] teaType = new String[] {"_earlgray", "_earlgray_milk", "_appletea", "_appletea_milk", "_lime", "_tomato", "_berry", "_berry_milk", "_grape", "_mint", "_yuzu", "_orange", "_soda"};
	
	private int healAmount = 0;
	
	public EntityItemTeaCup2(Block block)
	{
		super(block, true, true);
		setMaxDamage(0);
		setHasSubtypes(true);
		this.setMaxStackSize(Util.getCupStacksize());
	}
	
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        int meta = par1ItemStack.getItemDamage();
		
		if (!par2World.isRemote)
		{
			this.setPotionWithTea(par3EntityPlayer, meta);
			this.addSSMoisture(-8, 1.5F, par3EntityPlayer);
		}

        return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
    }
	
	@Override
	public ItemStack getReturnContainer(int meta) {
		
		return new ItemStack(DCsAppleMilk.emptyCup, 1, 0);
	}
	
	@Override
	public int[] hungerOnEaten(int meta) {
		return new int[] {0,0};
	}

	@Override
	public ArrayList<PotionEffect> effectOnEaten(EntityPlayer player, int meta) {
		
		ArrayList<PotionEffect> ret = new ArrayList<PotionEffect>();
		
		if((meta == 0 || meta == 1) && DCsAppleMilk.Immunization != null)
		{
			ret.add(new PotionEffect(DCsAppleMilk.Immunization.id, 600, 1));
		}
		else if((meta == 2 || meta == 3) && DCsAppleMilk.Immunization != null)
		{
			ret.add(new PotionEffect(DCsAppleMilk.Immunization.id, 600, 0));
		}
		else if (meta == 5)
		{
			ret.add(new PotionEffect(Potion.damageBoost.id, 600, 0));
		}
		else if (meta == 6 || meta == 7)
		{
			ret.add(new PotionEffect(Potion.resistance.id, 600, 0));
		}
		else if (meta == 8 || meta == 12)
		{
			ret.add(new PotionEffect(Potion.moveSpeed.id, 600, 0));
		}
		else if (meta == 11)
		{
			ret.add(new PotionEffect(Potion.jump.id, 600, 1));
		}
		else
		{
			ret.add(new PotionEffect(Potion.heal.id, 1, 1));
		}
		return ret;
	}
	
	protected static void clearNegativePotion(EntityPlayer player)
	{
		if (player.isPotionActive(Potion.blindness)){
			player.removePotionEffect(Potion.blindness.id);
		}
		if (player.isPotionActive(Potion.confusion)){
			player.removePotionEffect(Potion.confusion.id);
		}
		if (player.isPotionActive(Potion.hunger)){
			player.removePotionEffect(Potion.hunger.id);
		}
		if (player.isPotionActive(Potion.poison)){
			player.removePotionEffect(Potion.poison.id);
		}
		if (player.isPotionActive(Potion.weakness)){
			player.removePotionEffect(Potion.weakness.id);
		}
	}
	
	protected static void increaseDuration(EntityLivingBase living)
	{
		Iterator current = living.getActivePotionEffects().iterator();
		int increase = 6000;
		
		while (current.hasNext())
        {
            PotionEffect potioneffect = (PotionEffect)current.next();
            boolean flag = potioneffect.getPotionID() != Potion.heal.id && potioneffect.getPotionID() !=  Potion.harm.id
            		&& potioneffect.getPotionID() != Potion.regeneration.id;
            if (flag) living.addPotionEffect(new PotionEffect(potioneffect.getPotionID(), potioneffect.getDuration() + increase, potioneffect.getAmplifier()));
        }
	}
	
	protected void setPotionWithTea (EntityPlayer par1EntityPlayer, int meta)
	{
		if (meta < 4)
		{
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.heal.id, 1, 0));
		}
		
		if (meta == 4 || meta == 10)
		{
			clearNegativePotion(par1EntityPlayer);
		}
		
		if (meta == 9)
		{
			increaseDuration(par1EntityPlayer);
		}
		
		if ((meta & 1) == 1) {
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 0));
		}
		
		if (DCsAppleMilk.suffocation != null && par1EntityPlayer.isPotionActive(DCsAppleMilk.suffocation))
		{
			par1EntityPlayer.removePotionEffect(DCsAppleMilk.suffocation.id);
		}
	}
	
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 16;
    }

    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
    	return EnumAction.drink;
    }
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int m = (par1ItemStack.getItemDamage());
		if (m < 13) return super.getUnlocalizedName() + teaType[m];
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
		PlaceableCup2 entity = new PlaceableCup2(world, item, x, y, z);
		entity.rotationYaw = player.rotationYaw - 180.0F;
		
		if (!world.isRemote && item != null)
		{
			return world.spawnEntityInWorld(entity);
		}
		
		return false;
	}
	
}
