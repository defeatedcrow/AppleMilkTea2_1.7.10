package mods.defeatedcrow.common.block.edible;

import java.util.ArrayList;

import mods.defeatedcrow.common.*;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.entity.edible.*;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityItemCocktail extends EdibleEntityItemBlock2 {

	private static final String[] type = new String[] { "_frozen_daiquiri", "_frozen_sake", "_saketini", "_gimlet",
			"_blackrose", "_redeye", "_pinacolada", "_americanlemonade", "_moscowmule", "_mintjulep", "_kir",
			"_cassismilk", "_bloodymary", "_cassistea", "_doubleapple", "_plumsoymilk" };

	public EntityItemCocktail(Block block) {
		super(block, false, true);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		if (m < 17)
			return super.getUnlocalizedName() + type[m];
		else
			return super.getUnlocalizedName() + m;
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (!par2World.isRemote) {
			this.addSSMoisture(4, 3F, par3EntityPlayer);
		}
		par3EntityPlayer.triggerAchievement(AchievementRegister.drinkCocktail);
		return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
	}

	@Override
	public int[] hungerOnEaten(int meta) {
		return new int[] { 0, 0 };
	}

	@Override
	public ArrayList<PotionEffect> effectOnEaten(EntityPlayer par1EntityPlayer, int meta) {
		PotionEffect potion = new PotionEffect(Potion.digSpeed.id, 2400, 2);
		int tick = 2400;
		boolean flag = false;

		ArrayList<PotionEffect> ret = new ArrayList<PotionEffect>();
		ret.add(new PotionEffect(Potion.hunger.id, 300, 1));

		if (meta == 0)// frozen daiquiri
		{
			if (par1EntityPlayer.isPotionActive(Potion.invisibility.id)) {
				tick = par1EntityPlayer.getActivePotionEffect(Potion.invisibility).getDuration() + 2400;
				potion = new PotionEffect(Potion.invisibility.id, tick, 0);
				flag = true;
			} else {
				potion = new PotionEffect(Potion.invisibility.id, 2400, 0);
			}
		} else if (meta == 1 && DCsAppleMilk.reflex != null)// frozen sake
		{
			if (par1EntityPlayer.isPotionActive(DCsConfig.potionIDReflex)) {
				tick = par1EntityPlayer.getActivePotionEffect(DCsAppleMilk.reflex).getDuration() + 600;
				potion = new PotionEffect(DCsConfig.potionIDReflex, tick, 2);
				flag = false;
			} else {
				potion = new PotionEffect(DCsConfig.potionIDReflex, 600, 2);
			}
		} else if (meta == 2)// sake-tini
		{
			if (par1EntityPlayer.isPotionActive(Potion.field_76434_w.id)) {
				tick = par1EntityPlayer.getActivePotionEffect(Potion.field_76434_w).getDuration() + 2400;
				potion = new PotionEffect(Potion.field_76434_w.id, tick, 1);
				flag = true;
			} else {
				potion = new PotionEffect(Potion.field_76434_w.id, 2400, 1);
			}
		} else if (meta == 3)// gimlet
		{
			if (par1EntityPlayer.isPotionActive(Potion.resistance.id)) {
				tick = par1EntityPlayer.getActivePotionEffect(Potion.resistance).getDuration() + 600;
				potion = new PotionEffect(Potion.resistance.id, tick, 4);
				flag = true;
			} else {
				potion = new PotionEffect(Potion.resistance.id, 600, 4);
			}
		} else if (meta == 4 && DCsAppleMilk.prvProjectile != null)// black rose
		{
			if (par1EntityPlayer.isPotionActive(DCsConfig.potionIDPrvProjectile)) {
				tick = par1EntityPlayer.getActivePotionEffect(DCsAppleMilk.prvProjectile).getDuration() + 2400;
				potion = new PotionEffect(DCsConfig.potionIDPrvProjectile, tick, 0);
				flag = false;
			} else {
				potion = new PotionEffect(DCsConfig.potionIDPrvProjectile, 2400, 0);
			}
		} else if (meta == 5)// red eye
		{
			if (par1EntityPlayer.isPotionActive(Potion.digSpeed.id)) {
				tick = par1EntityPlayer.getActivePotionEffect(Potion.digSpeed).getDuration() + 2400;
				potion = new PotionEffect(Potion.digSpeed.id, tick, 1);
				flag = true;
			} else {
				potion = new PotionEffect(Potion.digSpeed.id, 2400, 1);
			}
		} else if (meta == 6 && DCsAppleMilk.prvExplode != null)// pina colada
		{
			if (par1EntityPlayer.isPotionActive(DCsConfig.potionIDPrvExplode)) {
				tick = par1EntityPlayer.getActivePotionEffect(DCsAppleMilk.prvExplode).getDuration() + 2400;
				potion = new PotionEffect(DCsConfig.potionIDPrvExplode, tick, 0);
				flag = false;
			} else {
				potion = new PotionEffect(DCsConfig.potionIDPrvExplode, 2400, 0);
			}
		} else if (meta == 7 && DCsAppleMilk.absHeal != null)// american lemonade
		{
			if (par1EntityPlayer.isPotionActive(DCsConfig.potionIDAbsHeal)) {
				tick = par1EntityPlayer.getActivePotionEffect(DCsAppleMilk.absHeal).getDuration() + 1200;
				potion = new PotionEffect(DCsConfig.potionIDAbsHeal, tick, 2);
				flag = true;
			} else {
				potion = new PotionEffect(DCsConfig.potionIDAbsHeal, 1200, 1);
			}
		} else if (meta == 8)// moscow mule
		{
			if (par1EntityPlayer.isPotionActive(Potion.damageBoost.id)) {
				tick = par1EntityPlayer.getActivePotionEffect(Potion.damageBoost).getDuration() + 2400;
				potion = new PotionEffect(Potion.damageBoost.id, tick, 3);
				flag = true;
			} else {
				potion = new PotionEffect(Potion.damageBoost.id, 2400, 3);
			}
		} else if (meta == 9)// mint julep
		{
			if (par1EntityPlayer.isPotionActive(Potion.digSpeed.id)) {
				tick = par1EntityPlayer.getActivePotionEffect(Potion.digSpeed).getDuration() + 2400;
				potion = new PotionEffect(Potion.digSpeed.id, tick, 3);
				flag = true;
			} else {
				potion = new PotionEffect(Potion.digSpeed.id, 2400, 3);
			}
		} else if (meta == 10 && DCsAppleMilk.absEXP != null)// kir
		{
			if (par1EntityPlayer.isPotionActive(DCsConfig.potionIDAbsEXP)) {
				tick = par1EntityPlayer.getActivePotionEffect(DCsAppleMilk.absEXP).getDuration() + 1200;
				potion = new PotionEffect(DCsConfig.potionIDAbsEXP, tick, 2);
				flag = false;
			} else {
				potion = new PotionEffect(DCsConfig.potionIDAbsEXP, 1200, 1);
			}
		} else if (meta == 11)// cassis milk
		{
			if (par1EntityPlayer.isPotionActive(Potion.waterBreathing.id)) {
				tick = par1EntityPlayer.getActivePotionEffect(Potion.waterBreathing).getDuration() + 2400;
				potion = new PotionEffect(Potion.waterBreathing.id, tick, 0);
				flag = true;
			} else {
				potion = new PotionEffect(Potion.waterBreathing.id, 2400, 0);
			}
		} else if (meta == 12)// bloody mary
		{
			if (par1EntityPlayer.isPotionActive(Potion.damageBoost.id)) {
				tick = par1EntityPlayer.getActivePotionEffect(Potion.damageBoost).getDuration() + 600;
				potion = new PotionEffect(Potion.damageBoost.id, tick, 6);
				flag = true;
			} else {
				potion = new PotionEffect(Potion.damageBoost.id, 600, 6);
			}
		} else if (meta == 13)// cassis tea cocktail
		{
			potion = new PotionEffect(Potion.heal.id, 3, 2);
		} else if (meta == 14)// double apple
		{
			if (par1EntityPlayer.isPotionActive(Potion.jump.id)) {
				tick = par1EntityPlayer.getActivePotionEffect(Potion.jump).getDuration() + 2400;
				potion = new PotionEffect(Potion.jump.id, tick, 4);
				flag = true;
			} else {
				potion = new PotionEffect(Potion.jump.id, 2400, 4);
			}
		} else if (meta == 15 && DCsAppleMilk.Immunization != null)// plum soymilk
		{
			if (par1EntityPlayer.isPotionActive(DCsAppleMilk.Immunization.id)) {
				tick = par1EntityPlayer.getActivePotionEffect(DCsAppleMilk.Immunization).getDuration() + 2400;
				potion = new PotionEffect(DCsAppleMilk.Immunization.id, tick, 2);
				flag = false;
			} else {
				potion = new PotionEffect(DCsAppleMilk.Immunization.id, 2400, 2);
			}
		}

		ret.add(potion);
		if (flag) {
			ret.add(new PotionEffect(Potion.confusion.id, 300, 1));
		}

		return ret;
	}

	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.drink;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	protected boolean spownEntityFoods(World world, EntityPlayer player, ItemStack item, double x, double y, double z) {
		PlaceableCocktail entity = new PlaceableCocktail(world, item, x, y, z);
		entity.rotationYaw = player.rotationYaw - 180.0F;

		if (!world.isRemote && item != null) {
			return world.spawnEntityInWorld(entity);
		}

		return false;
	}

}
