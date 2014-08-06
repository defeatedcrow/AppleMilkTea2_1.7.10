package mods.defeatedcrow.plugin;

import java.util.ArrayList;


import shift.sextiarysector.api.SextiarySectorAPI;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.DCsConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class LoadSSectorPlugin {
	
	public static ItemStack sakeBottle;
	public static ItemStack rumBottle;
	public static ItemStack ginBottle;
	public static ItemStack beerBottle;
	public static ItemStack emptyBottle;
	
	public void load()
	{
		ArrayList<ItemStack> sake = OreDictionary.getOres("drinkSake");
		ArrayList<ItemStack> rum = OreDictionary.getOres("drinkRum");
		ArrayList<ItemStack> gin = OreDictionary.getOres("drinkGin");
		ArrayList<ItemStack> beer = OreDictionary.getOres("drinkBeer");
		ArrayList<ItemStack> bottle = OreDictionary.getOres("craftingBottle");
		
		if (sake.size() > 0){
			sakeBottle = sake.get(0);
			if (sakeBottle != null) {
				LoadModHandler.registerModItems("drinkSake", new ItemStack(sakeBottle.getItem(), 1, 0));
			}
		}
		if (rum.size() > 0){
			rumBottle = rum.get(0);
			if (rumBottle != null) {

				LoadModHandler.registerModItems("drinkRum", new ItemStack(rumBottle.getItem(), 1, 0));
			}
		}
		if (gin.size() > 0){
			ginBottle = gin.get(0);
			if (ginBottle != null) {
				LoadModHandler.registerModItems("drinkGin", new ItemStack(ginBottle.getItem(), 1, 0));
			}
		}
		if (beer.size() > 0){
			beerBottle = beer.get(0);
			if (beerBottle != null) {
				LoadModHandler.registerModItems("drinkBeer", new ItemStack(beerBottle.getItem(), 1, 0));
			}
		}
		if (bottle.size() > 0){
			emptyBottle = bottle.get(0);
			if (emptyBottle != null) {
			}
		}

	}
	
	public static void addStatus(int par1, float par2, int par3, float par4, EntityPlayer par5EntityPlayer)
	{
		int m = par1;
		float ms = par2;
		int s = par3;
		float ss = par4;
		
		if (m > 0)
		{
			SextiarySectorAPI.addMoistureStats(par5EntityPlayer, m, ms);
		}
		else
		{
			SextiarySectorAPI.addMoistureExhaustion(par5EntityPlayer, (float)-m*4.0F);
		}
		
		if (s > 0)
		{
			SextiarySectorAPI.addStaminaStats(par5EntityPlayer, s, ss);
		}
		else
		{
			SextiarySectorAPI.addStaminaExhaustion(par5EntityPlayer, (float)-s*4.0F);
		}
		
	}

}
