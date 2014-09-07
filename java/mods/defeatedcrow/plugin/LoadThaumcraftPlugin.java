package mods.defeatedcrow.plugin;

import cpw.mods.fml.common.registry.GameRegistry;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.DCsConfig;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class LoadThaumcraftPlugin {
	
	public static ItemStack thaumicSilverwood;
	public static ItemStack thaumicGreatwood;
	public static ItemStack thaumicNitor;
	
	public void load()
	{
		//TC4apiの機能でアイテムを取得
		this.thaumicGreatwood = new ItemStack(ItemApi.getBlock("blockMagicalLog", 0).getItem(), 1, 0);
		this.thaumicSilverwood = new ItemStack(ItemApi.getBlock("blockMagicalLog", 1).getItem(), 1, 1);
		
		this.thaumicNitor = new ItemStack(ItemApi.getBlock("blockAiry", 1).getItem(), 1, 1);
		
		//blocks
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.woodBox), new int[]{5}, new AspectList().add(Aspect.TREE, 9).add(Aspect.MAGIC, 3));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.woodBox), new int[]{6}, new AspectList().add(Aspect.TREE, 9).add(Aspect.MAGIC, 3).add(Aspect.ORDER, 3));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.woodBox), new int[]{0,1,2,3,10}, new AspectList().add(Aspect.TREE, 8));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.woodBox), new int[]{4}, new AspectList().add(Aspect.TREE, 8).add(Aspect.SLIME, 3));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.woodBox), new int[]{7}, new AspectList().add(Aspect.TREE, 8).add(Aspect.ENERGY, 3));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.woodBox), new int[]{8}, new AspectList().add(Aspect.TREE, 8).add(Aspect.WEATHER, 3));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.woodBox), new int[]{9}, new AspectList().add(Aspect.TREE, 8).add(Aspect.WEATHER, 3));
	    
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.appleBox), new int[]{0}, new AspectList().add(Aspect.PLANT, 8).add(Aspect.CROP, 3));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.charcoalBox), new int[]{0}, new AspectList().add(Aspect.FIRE, 8));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.gunpowderContainer), new int[]{0}, new AspectList().add(Aspect.FIRE, 8).add(Aspect.ENTROPY, 3));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.gunpowderContainer), new int[]{1}, new AspectList().add(Aspect.HUNGER, 8).add(Aspect.ENTROPY, 3));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.gunpowderContainer), new int[]{2}, new AspectList().add(Aspect.EARTH, 8).add(Aspect.WATER, 3));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.gunpowderContainer), new int[]{3}, new AspectList().add(Aspect.WATER, 8).add(Aspect.HEAL, 3));
	    
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.vegiBag), new int[]{0}, new AspectList().add(Aspect.CROP, 8).add(Aspect.PLANT, 4));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.vegiBag), new int[]{1,2,3,7}, new AspectList().add(Aspect.CROP, 8));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.vegiBag), new int[]{5}, new AspectList().add(Aspect.CROP, 8).add(Aspect.AIR, 3));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.vegiBag), new int[]{6}, new AspectList().add(Aspect.CROP, 8).add(Aspect.WATER, 3));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.vegiBag), new int[]{8}, new AspectList().add(Aspect.CROP, 8).add(Aspect.MAGIC, 3));
	    
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.flintBlock), new int[]{0}, new AspectList().add(Aspect.MINE, 3).add(Aspect.FIRE, 3));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.chalcedony), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.MINE, 3).add(Aspect.CRYSTAL, 3));
	    
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.cLamp), new int[]{0,1,6}, new AspectList().add(Aspect.LIGHT, 2).add(Aspect.CRYSTAL, 3));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.cLamp), new int[]{2,3,8}, new AspectList().add(Aspect.LIGHT, 2).add(Aspect.CRYSTAL, 3).add(Aspect.VOID, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.cLamp), new int[]{4}, new AspectList().add(Aspect.LIGHT, 2).add(Aspect.CRYSTAL, 3).add(Aspect.MIND, 2).add(Aspect.ENERGY, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.cLamp), new int[]{5}, new AspectList().add(Aspect.LIGHT, 2).add(Aspect.CRYSTAL, 3).add(Aspect.BEAST, 2).add(Aspect.UNDEAD, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.cLamp), new int[]{10}, new AspectList().add(Aspect.LIGHT, 2).add(Aspect.CRYSTAL, 3).add(Aspect.MECHANISM, 2).add(Aspect.TRAP, 2));
	    
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.Basket), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.CRAFT, 2).add(Aspect.HUNGER, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.eggBasket), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.CRAFT, 2).add(Aspect.HEAL, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.bowlRack), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.CRAFT, 2).add(Aspect.TREE, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.chopsticksBox), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.CRAFT, 2).add(Aspect.TREE, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.clamSand), new int[]{0}, new AspectList().add(Aspect.WATER, 2).add(Aspect.HEAL, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.clamSand), new int[]{1}, new AspectList().add(Aspect.WATER, 2).add(Aspect.VOID, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.clamSand), new int[]{2}, new AspectList().add(Aspect.SENSES, 4).add(Aspect.AURA, 5));
	    
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.emptyPanGaiden), new int[]{0}, new AspectList().add(Aspect.CRAFT, 2).add(Aspect.VOID, 1).add(Aspect.HUNGER, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.filledPan), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.CRAFT, 2).add(Aspect.HUNGER, 1).add(Aspect.FIRE, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.filledPan2), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.CRAFT, 2).add(Aspect.HUNGER, 1).add(Aspect.FIRE, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.teppann), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.CRAFT, 2).add(Aspect.HUNGER, 1).add(Aspect.FIRE, 3));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.iceMaker), new int[]{0}, new AspectList().add(Aspect.CRAFT, 2).add(Aspect.HUNGER, 1).add(Aspect.COLD, 3));
//	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.autoMaker), new int[]{0}, new AspectList().add(Aspect.CRAFT, 2).add(Aspect.HUNGER, 1).add(Aspect.EXCHANGE, 3));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.teaMakerNext), new int[]{0}, new AspectList().add(Aspect.WATER, 2).add(Aspect.VOID, 1).add(Aspect.FIRE, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.rotaryDial), new int[]{0}, new AspectList().add(Aspect.MECHANISM, 2).add(Aspect.EXCHANGE, 2).add(Aspect.TRAVEL, 1));
	    
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.emptyCup), new int[]{0}, new AspectList().add(Aspect.WATER, 2).add(Aspect.VOID, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.teacupBlock), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.WATER, 2).add(Aspect.HEAL, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.teaCup2), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.WATER, 2).add(Aspect.HEAL, 1));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.bowlBlock), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.HUNGER, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.bowlJP), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.HUNGER, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.foodPlate), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.HUNGER, 2).add(Aspect.FLESH, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.blockIcecream), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.HUNGER, 2).add(Aspect.COLD, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.cocktail), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.WATER, 2).add(Aspect.MAGIC, 2).add(Aspect.SENSES, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.largeBottle), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.WATER, 8).add(Aspect.VOID, 8).add(Aspect.CRYSTAL, 4));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.cordial), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.WATER, 3).add(Aspect.HEAL, 3).add(Aspect.CRYSTAL, 4));
	    
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.saplingTea), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.PLANT, 2).add(Aspect.HEAL, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.teaTree), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.PLANT, 2).add(Aspect.AIR, 2));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.cassisTree), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.PLANT, 2).add(Aspect.SENSES, 2));
	    
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.melonBomb), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.CROP, 8).add(Aspect.ENTROPY, 3).add(Aspect.WATER, 3));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.mushroomBox), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.PLANT, 8).add(Aspect.DARKNESS, 3).add(Aspect.MAGIC, 1));
	    
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.chocoBlock), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.HEAL, 3).add(Aspect.MIND, 3).add(Aspect.SENSES, 3));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.filledChocoPan), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.HEAL, 3).add(Aspect.MIND, 3).add(Aspect.MAGIC, 3));
	    
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.wipeBox2), new int[]{0,1}, new AspectList().add(Aspect.MIND, 3).add(Aspect.MAGIC, 3));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.wipeBox), new int[]{2,3}, new AspectList().add(Aspect.MIND, 6).add(Aspect.MAGIC, 6));
	    ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.wipeBox2), new int[]{OreDictionary.WILDCARD_VALUE}, new AspectList().add(Aspect.MIND, 10).add(Aspect.MAGIC, 10).add(Aspect.VOID, 10));
	    
	    AMTLogger.debugInfo("Succeeded to register aspects for Thaumcraft");
		
		if (this.thaumicGreatwood != null){
			
			//取得したアイテムを使ったレシピの登録。こちらは恐らく旧版でも動作する
			GameRegistry.addShapelessRecipe(
		    		  new ItemStack(LoadThaumcraftPlugin.thaumicGreatwood.getItem(), 9, 0),
		    			  new ItemStack(DCsAppleMilk.woodBox,1,5));
			 
			  GameRegistry.addRecipe(
					 new ItemStack(DCsAppleMilk.woodBox,1,5),
					 new Object[]{
						 "UUU",
						 "UUU",
						 "UUU",
						 Character.valueOf('U'), new ItemStack(LoadThaumcraftPlugin.thaumicGreatwood.getItem(), 1, 0)
					 });
		}
		
		if (this.thaumicSilverwood != null){
			
			GameRegistry.addShapelessRecipe(
		    		  new ItemStack(LoadThaumcraftPlugin.thaumicSilverwood.getItem(), 9, 1),
		    			  new ItemStack(DCsAppleMilk.woodBox,1,6));
			 
			  GameRegistry.addRecipe(
					 new ItemStack(DCsAppleMilk.woodBox,1,6),
					 new Object[]{
						 "UUU",
						 "UUU",
						 "UUU",
						 Character.valueOf('U'), new ItemStack(LoadThaumcraftPlugin.thaumicSilverwood.getItem(), 1, 1)
					 });
		}
		
		if (this.thaumicNitor != null)
		{
			if (LoadModHandler.registerModItems("furnaceBlock", this.thaumicNitor)){
				AMTLogger.debugInfo("Succeeded to get Nitor.");
			}
		}
	}
}
