package mods.defeatedcrow.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import mods.defeatedcrow.handler.NetworkUtilServer;
import mods.defeatedcrow.plugin.LoadModHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class DCsRecipeRegister {
	
	private Item rubberWood;
	private ArrayList<ItemStack> waterContainer = new ArrayList<ItemStack>();
	
	public void addRecipe()
	{
		OreDictionary.registerOre("dustSugar", new ItemStack(Items.sugar, 1, 0));
		OreDictionary.registerOre("cropWheat", new ItemStack(Items.wheat, 1, 0));
		OreDictionary.registerOre("cropApple", new ItemStack(Items.apple, 1, 0));
		OreDictionary.registerOre("bucketMilk", new ItemStack(Items.milk_bucket, 1, 0));
		
		this.addContainerRecipe();
		this.addTablewareRecipe();
		this.addGraterRecipe();
		this.addCocktailRecipe();
		this.addFoodRecipe();
		this.addMaterials();
		this.addChalcedony();
		this.addPrincess();
		this.addBottle();
		this.addCordial();
		this.addSmelting();
		
		
	    //extra recipe
	    if (DCsConfig.useEXRecipe)
	    {
	    	GameRegistry.addRecipe(
	 			new ItemStack(DCsAppleMilk.saplingTea,1),
	 			new Object[]{
	 					 "XXX",
	 					 "XYX",
	 					 "XXX",
	 					 Character.valueOf('X'), new ItemStack(Items.gold_nugget, 1),
	 					 Character.valueOf('Y'), new ItemStack(Blocks.sapling, 1, 0)
	 			});
	    }
	      
	    //add extra recipe if nether is disabled.
	    if (!NetworkUtilServer.INSTANCE.enableNether())
	    {
	    	GameRegistry.addRecipe(
	 			new ItemStack(DCsAppleMilk.teaMakerNext, 1, 0),
	 			new Object[]{
	 					 "XYX",
	 					 "ZYZ",
	 					 "XWX",
	 					 Character.valueOf('X'), new ItemStack(Blocks.stained_hardened_clay, 1, 0),
	 					 Character.valueOf('Y'), new ItemStack(Items.water_bucket, 1),
	 					 Character.valueOf('Z'), new ItemStack(Blocks.glass, 1),
	 					 Character.valueOf('W'), new ItemStack(Items.lava_bucket, 1)
	 			});
	 		 
	    	GameRegistry.addRecipe(
	 			new ShapedOreRecipe(
	 	    	new ItemStack(DCsAppleMilk.teaMakerNext, 1, 0),
	 	    	new Object[]{
	 						 "XYX",
	 						 "ZYZ",
	 						 "XWX",
	 						 Character.valueOf('X'), "ingotSilver",
	 						 Character.valueOf('Y'), new ItemStack(Items.water_bucket, 1),
	 						 Character.valueOf('Z'), new ItemStack(Blocks.glass, 1),
	 						 Character.valueOf('W'), new ItemStack(Items.lava_bucket, 1)}));
	     }
		 
	}
	
	static void addContainerRecipe() {
		
		for(int i = 0; i < 4; i++)
		 {
			 GameRegistry.addShapelessRecipe(
		    		  new ItemStack(Blocks.log,9,i),
		    			  new ItemStack(DCsAppleMilk.woodBox,1,i));
			 
			 GameRegistry.addRecipe(
					 new ItemStack(DCsAppleMilk.woodBox,1,i),
					 new Object[]{
						 "UUU",
						 "UUU",
						 "UUU",
						 Character.valueOf('U'), new ItemStack(Blocks.log,1,i)
					 });
		 }
		
		GameRegistry.addShapelessRecipe(
	    		  new ItemStack(Blocks.log2,9,1),
	    			  new ItemStack(DCsAppleMilk.woodBox,1,11));
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.woodBox,1,11),
				 new Object[]{
					 "UUU",
					 "UUU",
					 "UUU",
					 Character.valueOf('U'), new ItemStack(Blocks.log2,1,1)
				 });
		 
		 GameRegistry.addShapelessRecipe(
	    		  new ItemStack(Blocks.log2,9,0),
	    			  new ItemStack(DCsAppleMilk.woodBox,1,12));
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.woodBox,1,12),
				 new Object[]{
					 "UUU",
					 "UUU",
					 "UUU",
					 Character.valueOf('U'), new ItemStack(Blocks.log2,1,0)
				 });
		 
		 GameRegistry.addShapelessRecipe(
	    		  new ItemStack(Items.apple,9),
	    			  new ItemStack(DCsAppleMilk.appleBox,1));
		 
		 
		 GameRegistry.addShapelessRecipe(
	    		  new ItemStack(Items.coal,9,1),
	    			  new ItemStack(DCsAppleMilk.charcoalBox,1));
		 
		 for(int i = 0; i < 10; i++)
		 {
			 ItemStack itemStack = new ItemStack(DCsAppleMilk.leafTea,9, 0);
			 if (i == 1) itemStack = new ItemStack(Items.potato,9);
			 else if (i == 2) itemStack = new ItemStack(Items.carrot,9);
			 else if (i == 3) itemStack = new ItemStack(Blocks.pumpkin,9);
			 else if (i == 4) itemStack = new ItemStack(Items.wheat_seeds,9);
			 else if (i == 5) itemStack = new ItemStack(Items.reeds,9);
			 else if (i == 6) itemStack = new ItemStack(Blocks.cactus,9);
			 else if (i == 7) itemStack = new ItemStack(Items.dye,9,3);
			 else if (i == 8) itemStack = new ItemStack(Items.nether_wart,9);
			 else if (i == 9) itemStack = new ItemStack(Items.sugar,9);
			 else itemStack = new ItemStack(DCsAppleMilk.leafTea,9, 0);
			 
			 GameRegistry.addShapelessRecipe(
		    		  itemStack,
		    			  new ItemStack(DCsAppleMilk.vegiBag,1,i));
			 
			 if (i >= 0){
			 GameRegistry.addRecipe(
					 new ItemStack(DCsAppleMilk.vegiBag,1,i),
					 new Object[]{
						 "TTT",
						 "TTT",
						 "TTT",
						 Character.valueOf('T'), itemStack
					 });
			 }
		 }
		 
		 GameRegistry.addRecipe(
					new ShapedOreRecipe(
			    		new ItemStack(DCsAppleMilk.vegiBag, 1, 9),
			    		new Object[]{"XXX","XXX","XXX",
			    		Character.valueOf('X'), "dustSugar"}));
		 
		 
		 for(int i = 0; i < 4; i++)
		 {
			 ItemStack item = new ItemStack(Items.gunpowder, 9);
			 if (i == 1) item = new ItemStack(DCsAppleMilk.EXItems, 9, 2);
			 else if (i == 2) item = new ItemStack(Items.clay_ball, 9);
			 else if (i == 3) item = new ItemStack(DCsAppleMilk.clam, 9, 0);
			 else item = new ItemStack(Items.gunpowder,9);
			 
			 GameRegistry.addShapelessRecipe(
		    		  item,
		    			  new ItemStack(DCsAppleMilk.gunpowderContainer,1,i));
			 
			 if (i >= 0){
			 GameRegistry.addRecipe(
					 new ItemStack(DCsAppleMilk.gunpowderContainer,1,i),
					 new Object[]{
						 "TTT",
						 "TTT",
						 "TTT",
						 Character.valueOf('T'), item
					 });
			 }
		 }
		 
		 for(int i = 0; i < 4; i++)
		 {
			 ItemStack item = new ItemStack(DCsAppleMilk.leafTea, 9, 1);
			 if (i == 1) item = new ItemStack(DCsAppleMilk.leafTea, 9, 2);
			 else if (i == 2) item = new ItemStack(DCsAppleMilk.leafTea, 9, 3);
			 else if (i == 3) item = new ItemStack(DCsAppleMilk.leafTea, 9, 4);
			 else item = new ItemStack(DCsAppleMilk.leafTea, 9, 1);
			 
			 GameRegistry.addShapelessRecipe(
		    		  item,
		    			  new ItemStack(DCsAppleMilk.cardboard,1,i));
			 
			 if (i >= 0){
			 GameRegistry.addRecipe(
					 new ItemStack(DCsAppleMilk.cardboard,1,i),
					 new Object[]{
						 "TTT",
						 "TTT",
						 "TTT",
						 Character.valueOf('T'), item
					 });
			 }
		 }
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.appleBox,1),
				 new Object[]{
					 "UUU",
					 "UUU",
					 "UUU",
					 Character.valueOf('U'), Items.apple
				 });
		 
		 GameRegistry.addRecipe(
					new ShapedOreRecipe(
			    		new ItemStack(DCsAppleMilk.appleBox, 1, 0),
			    		new Object[]{"XXX","XXX","XXX",
			    		Character.valueOf('X'), "cropApple"}));
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.charcoalBox,1),
				 new Object[]{
					 "UUU",
					 "UUU",
					 "UUU",
					 Character.valueOf('U'), new ItemStack(Items.coal, 1, 1)
				 });
		 
		 GameRegistry.addShapelessRecipe(
				 new ItemStack(Items.egg, 8, 0),
				 new ItemStack(DCsAppleMilk.eggBasket, 1, 0));
		 
		 GameRegistry.addShapelessRecipe(
				 new ItemStack(DCsAppleMilk.clam, 8, 3),
				 new ItemStack(DCsAppleMilk.eggBasket, 1, 1));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.eggBasket, 1, 0),
	    		  new Object[]{"XXX","X X","XXX",
	    			  Character.valueOf('X'), new ItemStack(Items.egg, 1, 0)})); 
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.eggBasket, 1, 1),
	    		  new Object[]{"XXX","X X","XXX",
	    			  Character.valueOf('X'), new ItemStack(DCsAppleMilk.clam, 1, 3)})); 
		 
		 GameRegistry.addShapelessRecipe(
				 new ItemStack(Blocks.melon_block, 9, 0),
				 new ItemStack(DCsAppleMilk.melonBomb, 1, 0));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.melonBomb, 1, 0),
	    		  new Object[]{"XXX","XXX","XXX",
	    			  Character.valueOf('X'), new ItemStack(Blocks.melon_block, 1, 0)})); 
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.chopsticksBox, 1, 4),
	    		  new Object[]{"XXX","XYX","XXX",
	    			  Character.valueOf('Y'), new ItemStack(DCsAppleMilk.Basket, 1, 0),
	    			  Character.valueOf('X'), "stickWood"})); 
		 
		 GameRegistry.addShapelessRecipe(
				 new ItemStack(Blocks.red_mushroom, 9),
				 new ItemStack(DCsAppleMilk.mushroomBox, 1, 0));
		 
		 GameRegistry.addShapelessRecipe(
				 new ItemStack(Blocks.brown_mushroom, 9),
				 new ItemStack(DCsAppleMilk.mushroomBox, 1, 1));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mushroomBox, 1, 0),
	    		  new Object[]{"XXX","XXX","XXX",
	    			  Character.valueOf('X'), new ItemStack(Blocks.red_mushroom, 1)})); 
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mushroomBox, 1, 1),
	    		  new Object[]{"XXX","XXX","XXX",
	    			  Character.valueOf('X'), new ItemStack(Blocks.brown_mushroom, 1)})); 
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.chocoBlock, 1, 0),
	    		  new Object[]{"XXX","XYX","XXX",
	    			  Character.valueOf('Y'), new ItemStack(Items.gold_ingot, 1),
	    			  Character.valueOf('X'), "foodFruitsChocolate"}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.chocoBlock, 1, 1),
	    		  new Object[]{"XXX","XYX","XXX",
	    			  Character.valueOf('Y'), new ItemStack(Items.diamond, 1),
	    			  Character.valueOf('X'), "foodFruitsChocolate"}));
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.wipeBox,1,0),
				 new Object[]{
					 "UUU",
					 "UUU",
					 "UUU",
					 Character.valueOf('U'), Items.paper
				 });
		 
		 GameRegistry.addShapelessRecipe(
				 new ItemStack(Items.paper, 9),
				 new ItemStack(DCsAppleMilk.wipeBox, 1, 0));
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.wipeBox,1,1),
				 new Object[]{
					 "UUU",
					 "UUU",
					 "UUU",
					 Character.valueOf('U'), new ItemStack(DCsAppleMilk.wipeBox, 1, 0)
				 });
	}
	
	static void addTablewareRecipe() {
		
		GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.emptyCup, 1),
	    		  new Object[]{"XXX","XX ",
	    			  Character.valueOf('X'), "ingotSilver"}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.emptyCup, 1),
	    		  new Object[]{"XXX","XX ",
	    			  Character.valueOf('X'), new ItemStack(Blocks.stained_hardened_clay, 1, 0)}));
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.teaMakerNext, 1),
				 new Object[]{
					 "XYX",
					 "ZYZ",
					 "XWX",
					 Character.valueOf('X'), new ItemStack(Blocks.stained_hardened_clay, 1, 0),
					 Character.valueOf('Y'), new ItemStack(Items.water_bucket, 1),
					 Character.valueOf('Z'), new ItemStack(Blocks.glass, 1),
					 Character.valueOf('W'), new ItemStack(Items.blaze_rod, 1)
				 });
		 
//		 GameRegistry.addRecipe(
//				 new ShapedOreRecipe(
//	    		  new ItemStack(DCsAppleMilk.autoMaker, 1),
//	    		  new Object[]{
//						 "ZYZ",
//						 "ZXZ",
//						 Character.valueOf('Z'), new ItemStack(Blocks.stained_hardened_clay, 1, 0),
//						 Character.valueOf('Y'), "gearIron",
//						 Character.valueOf('X'), new ItemStack(Blocks.dispenser, 1)}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.teaMakerNext, 1),
	    		  new Object[]{
						 "XYX",
						 "ZYZ",
						 "XWX",
						 Character.valueOf('X'), "ingotSilver",
						 Character.valueOf('Y'), new ItemStack(Items.water_bucket, 1),
						 Character.valueOf('Z'), new ItemStack(Blocks.glass, 1),
						 Character.valueOf('W'), new ItemStack(Items.blaze_rod, 1)}));
		 
//		 GameRegistry.addRecipe(
//				 new ShapedOreRecipe(
//	    		  new ItemStack(DCsAppleMilk.autoMaker, 1),
//	    		  new Object[]{
//						 "ZYZ",
//						 "ZXZ",
//						 Character.valueOf('Z'), "ingotSilver",
//						 Character.valueOf('Y'), "gearIron",
//						 Character.valueOf('X'), new ItemStack(Blocks.dispenser, 1)}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.iceMaker, 1),
	    		  new Object[]{
						 " X ",
						 "ZYZ",
						 "Z Z",
						 Character.valueOf('X'), "gearIron",
						 Character.valueOf('Z'), new ItemStack(Items.iron_ingot, 1),
						 Character.valueOf('Y'), new ItemStack(DCsAppleMilk.icyCrystal, 1)}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.prosessor, 1),
	    		  new Object[]{
						 "XXX",
						 "ZYZ",
						 "ZWZ",
						 Character.valueOf('W'), "gearIron",
						 Character.valueOf('Z'), new ItemStack(Items.iron_ingot, 1),
						 Character.valueOf('Y'), new ItemStack(Items.iron_sword, 1),
						 Character.valueOf('X'), new ItemStack(Blocks.glass, 1)}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.evaporator, 1),
	    		  new Object[]{
						 " X ",
						 "ZYZ",
						 "XWX",
						 Character.valueOf('W'), "gearIron",
						 Character.valueOf('Z'), new ItemStack(Items.glass_bottle, 1),
						 Character.valueOf('Y'), new ItemStack(Items.blaze_rod, 1),
						 Character.valueOf('X'), new ItemStack(Blocks.stained_hardened_clay, 1, 0)}));
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.emptyPan, 1),
				 new Object[]{
					 "X X",
					 "X X",
					 "XXX",
					 Character.valueOf('X'), new ItemStack(Blocks.hardened_clay, 1)
				 });
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.DCgrater, 1, 0),
				 new Object[]{
					 " X ",
					 "XYX",
					 "XYX",
					 Character.valueOf('X'), new ItemStack(Items.stick, 1),
					 Character.valueOf('Y'), new ItemStack(Blocks.iron_bars, 1)
				 });
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.bowlRack, 1),
	    		  new Object[]{"X X","X X","YYY",
	    			  Character.valueOf('X'), "stickWood",
	    			  Character.valueOf('Y'), "plankWood"}));
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.Basket, 1),
				 new Object[]{
					 "X X",
					 "X X",
					 "XXX",
					 Character.valueOf('X'), new ItemStack(Items.reeds, 1)
				 });
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.teppann, 1, 0),
				 new Object[]{
					 "X X",
					 "XXX",
					 " Y ",
					 Character.valueOf('X'), new ItemStack(Items.iron_ingot, 1),
					 Character.valueOf('Y'), new ItemStack(Blocks.furnace, 1)
				 });
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
				 new Object[]{
					 " Y ",
					 "X X",
					 " X ",
					 Character.valueOf('X'), new ItemStack(Blocks.glass, 1),
					 Character.valueOf('Y'), new ItemStack(Items.iron_ingot, 1)
				 });
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
	    		  new Object[]{
	    			 " Y ",
					 "X X",
					 " X ",
					 Character.valueOf('X'), new ItemStack(Blocks.glass, 1),
					 Character.valueOf('Y'), "ingotTin"}));
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.Basket, 1, 14),
				 new Object[]{
					 "XXX",
					 "XXX",
					 "XXX",
					 Character.valueOf('X'), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0)
				 });
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.emptyWallMug),
				 new Object[]{
					 " X ",
					 "XYX",
					 " X ",
					 Character.valueOf('X'), new ItemStack(Blocks.glass_pane, 1, 0),
					 Character.valueOf('Y'), new ItemStack(Items.slime_ball, 1, 0)
				 });
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.emptyWallMug),
	    		  new Object[]{
	    			" X ",
					"XYX",
					" X ",
					Character.valueOf('X'), new ItemStack(Blocks.glass_pane, 1, 0),
					Character.valueOf('Y'), "dropSlime"}));
	}
	
	static void addGraterRecipe() {
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 0),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  new ItemStack(Items.apple, 1, 0)
					 }));
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 0),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "cropApple"
					 }));
	    		  
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 1),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "cropPeach"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 1),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "cropBanana"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 1),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "cropOrange"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 1),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "cropPlum"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 2),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "cropLemon",
		    		  "dropHoney"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 3),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "cropCoffee",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 5),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "cropLime",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 5),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "cropCitron",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 6),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "cropTomato",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 6),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "tomato",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 7),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "cropStrawberry",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 7),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "cropBlueberry",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 7),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "cropRaspberry",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 7),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "cropBlackberry",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 7),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "cropCassis",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 7),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "listAllberry",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 8),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "cropGrape",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 8),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "grape",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.foodTea, 1, 2),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
	    			  new ItemStack(DCsAppleMilk.foodTea, 1, 1),
		    		  "cropOrange",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.foodTea, 1, 2),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
	    			  new ItemStack(DCsAppleMilk.foodTea, 1, 1),
		    		  "cropCitron",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.foodTea, 1, 3),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
	    			  new ItemStack(DCsAppleMilk.foodTea, 1, 1),
	    			  new ItemStack(Items.apple, 1, 0)
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.foodTea, 1, 3),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
	    			  new ItemStack(DCsAppleMilk.foodTea, 1, 1),
	    			  "cropApple"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 0),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  new ItemStack(Blocks.brown_mushroom, 1, 0),
		    		  new ItemStack(Blocks.red_mushroom, 1, 0)
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 1),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  new ItemStack(Items.fish, 1, 0),
		    		  new ItemStack(Items.carrot, 1),
	    			  new ItemStack(Items.potato, 1),
	    			  "bucketMilk"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 1),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  new ItemStack(Items.fish, 1, 1),
		    		  new ItemStack(Items.carrot, 1),
	    			  new ItemStack(Items.potato, 1),
	    			  "bucketMilk"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 1),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  new ItemStack(Items.fish, 1, 0),
		    		  new ItemStack(Items.carrot, 1, 0),
	    			  new ItemStack(Items.potato, 1, 0),
	    			  "listAllmilk"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 2),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  new ItemStack(Items.egg, 1, 0),
		    		  new ItemStack(Items.chicken, 1, 0),
	    			  "cropWheat"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 2),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  new ItemStack(Items.egg, 1, 0),
		    		  new ItemStack(Items.chicken, 1, 0),
	    			  "cropRice"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 3),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
	    			  "cropRice","cropRice","cropRice"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 3),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
	    			  "cropWheat","cropWheat","cropWheat"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 4),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
	    			  new ItemStack(DCsAppleMilk.EXItems, 1, 2),
		    		  "cropRice"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 4),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
	    			  new ItemStack(DCsAppleMilk.EXItems, 1, 2),
	    			  "cropWheat"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 5),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
	    			  new ItemStack(Items.chicken, 1, 0),
	    			  "leek",
		    		  "bucketSoymilk",
		    		  "tofuKinu"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 5),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
	    			  new ItemStack(Items.chicken, 1, 0),
	    			  "leek",
		    		  "bucketSoymilk",
		    		  "tofuMomen"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 5),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
	    			  new ItemStack(Items.chicken, 1, 0),
	    			  "cropLeek",
		    		  "foodSoymilk",
		    		  "foodSilkentofu"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 6),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
	    			  new ItemStack(Blocks.pumpkin, 1, 0),
	    			  "bucketMilk"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 6),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
	    			  new ItemStack(Blocks.pumpkin, 1, 0),
	    			  "listAllmilk"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 7),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
	    			  new ItemStack(Items.porkchop, 1, 0),
	    			  "cropLettuce",
		    		  "cropTomato",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 7),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
	    			  new ItemStack(Items.porkchop, 1, 0),
	    			  "cabbage",
		    		  "tomato",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 8),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "bucketMilk",
		    		  "dustSugar",
		    		  new ItemStack(Items.dye,9,3),
		    		  new ItemStack(Items.dye,9,3)
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.mincedFoods, 1, 8),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
		    		  "listAllmilk",
		    		  "dustSugar",
		    		  new ItemStack(Items.dye,9,3),
		    		  new ItemStack(Items.dye,9,3)
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 4),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.mincedFoods, 1, 8),
		    		  "bucketMilk"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 4),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.mincedFoods, 1, 8),
		    		  "listAllmilk"
					 }));
		
	}
	
	static void addCocktailRecipe() {
		
		//フローズン・ダイキリ
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 0),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.teaCup2, 1, 4),
	    			  "foodCrushedIce",
	    			  "dustSugar",
	    			  "bottleRum"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 0),
	    		  new Object[]{
	    			  "foodLimejuice",
	    			  "foodCrushedIce",
	    			  "dustSugar",
	    			  "bottleRum"
					 }));
		 
		 //サケティーニ
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 2),
	    		  new Object[]{
	    			  "bottleGin",
	    			  "bottleSake"
					 }));
		 
		 //ギムレット
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 3),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.teaCup2, 1, 4),
	    			  "dustSugar",
	    			  "bottleGin"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 3),
	    		  new Object[]{
	    			  "foodLimejuice",
	    			  "dustSugar",
	    			  "bottleGin"
					 }));
		 
		 //ブラック・ローズ
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 4),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.teacupBlock, 1, 12),
	    			  "bottleRum"
					 }));
		 
		 //レッドアイ
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 5),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.teaCup2, 1, 5),
	    			  "bottleBeer"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 5),
	    		  new Object[]{
	    			  "foodTomatojuice",
	    			  "bottleBeer"
					 }));
		 
		 //ピニャ・コラーダ
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 6),
	    		  new Object[]{
	    			  "bottleRum",
	    			  "cropPineapple",
	    			  "cropCoconut",
	    			  "foodCrushedIce"
					 }));
		 
		 //アメリカン・レモネード
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 7),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.teacupBlock, 1, 10),
	    			  "bottleWine"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 7),
	    		  new Object[]{
	    			  "foodLemonaid",
	    			  "bottleWine"
					 }));
		 
		 //モスコミュール
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 8),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.teaCup2, 1, 4),
	    			  "bottleVodka",
	    			  "drinkCider"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 8),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.teaCup2, 1, 4),
	    			  "bottleVodka",
	    			  "foodGingersoda"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 8),
	    		  new Object[]{
	    			  "foodLimejuice",
	    			  "bottleVodka",
	    			  "foodGingersoda"
					 }));
		 
		 //ミント・ジュレップ
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 9),
	    		  new Object[]{
	    			  "dustSugar",
	    			  "bottleWhiskey",
	    			  "cropSpiceleaf",
	    			  "foodCrushedIce"
					 }));
		 
		 //キール
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 10),
	    		  new Object[]{
	    			  "bottleCassisLiquor",
	    			  "bottleWine"
					 }));
		 
		//カシスミルク
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 11),
	    		  new Object[]{
	    			  "bottleCassisLiquor",
	    			  "bucketSoymilk"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 11),
	    		  new Object[]{
	    			  "bottleCassisLiquor",
	    			  "bucketMilk"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 11),
	    		  new Object[]{
	    			  "bottleCassisLiquor",
	    			  "listAllmilk"
					 }));
		 
		 //ブラッディメアリー
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 12),
	    		  new Object[]{
	    			  "bottleVodka",
	    			  "blackPepper",
	    			  new ItemStack(DCsAppleMilk.teaCup2, 1, 5)
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 12),
	    		  new Object[]{
	    			  "bottleVodka",
	    			  "whitePepper",
	    			  new ItemStack(DCsAppleMilk.teaCup2, 1, 5)
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 12),
	    		  new Object[]{
	    			  "bottleVodka",
	    			  "cropChilipepper",
	    			  new ItemStack(DCsAppleMilk.teaCup2, 1, 5)
					 }));
		 
		 //カシスティーカクテル
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 13),
	    		  new Object[]{
	    			  "bottleTeaLiquor",
	    			  "bottleCassisLiquor",
	    			  "foodCrushedIce"
					 }));
		 
		 //ダブルアップル
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 14),
	    		  new Object[]{
	    			  "bottleAppleLiquor",
	    			  "cropApple",
	    			  new ItemStack(DCsAppleMilk.teacupBlock, 1, 8),
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 14),
	    		  new Object[]{
	    			  "bottleAppleLiquor",
	    			  "cropApple",
	    			  "foodApplejuice"
					 }));
		 
		 //豆乳梅酒
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 15),
	    		  new Object[]{
	    			  "bottlePlumLiquor",
	    			  "bucketSoymilk"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cocktail, 1, 15),
	    		  new Object[]{
	    			  "bottlePlumLiquor",
	    			  "foodSoymilk"
					 }));
		 
		 //以下、ストレートで飲むレシピ
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.alcoholCup, 1, 0),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
	    			  "bottleSake",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.alcoholCup, 1, 1),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
	    			  "bottleBeer",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.alcoholCup, 1, 2),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
	    			  "bottleWine",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.alcoholCup, 1, 3),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
	    			  "bottleRum",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.alcoholCup, 1, 4),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
	    			  "bottleGin",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.alcoholCup, 1, 5),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
	    			  "bottleVodka",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.alcoholCup, 1, 6),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
	    			  "bottleWhiskey",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.alcoholCup, 1, 7),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
	    			  "bottleAppleLiquor",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.alcoholCup, 1, 8),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
	    			  "bottleTeaLiquor",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.alcoholCup, 1, 9),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
	    			  "bottleCassisLiquor",
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.alcoholCup, 1, 10),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
	    			  "bottlePlumLiquor",
					 }));
	}
	
	static void addFoodRecipe() {
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.appleTart, 1, 0),
	    		  new Object[]{
	    			  "cropApple",
	    			  "dustSugar",
	    			  "cropWheat"
					 }));
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.appleTart, 1, 1),
	    		  new Object[]{
	    			  "cropCassis",
	    			  "dustSugar",
	    			  "cropWheat"
					 }));
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.appleTart, 1, 2),
	    		  new Object[]{
	    			  "cropYuzu",
	    			  "dustSugar",
	    			  "bucketMilk",
	    			  Items.egg
					 }));
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.toffyApple, 1, 0),
	    		  new Object[]{
	    			  "cropApple",
	    			  "dustSugar",
	    			  "stickWood"
					 }));
		 
		 GameRegistry.addShapelessRecipe(
	    		  new ItemStack(DCsAppleMilk.icyToffyApple,1,0),
	    			  new ItemStack(DCsAppleMilk.toffyApple,1),
	    			  new ItemStack(Items.snowball,1));
		 
		 GameRegistry.addShapelessRecipe(
	    		  new ItemStack(DCsAppleMilk.icyToffyApple,1,1),
	    			  new ItemStack(DCsAppleMilk.toffyApple,1),
	    			  new ItemStack(Items.feather,1));
		 
		 GameRegistry.addShapelessRecipe(
	    		  new ItemStack(DCsAppleMilk.icyToffyApple,1,2),
	    			  new ItemStack(DCsAppleMilk.toffyApple,1),
	    			  new ItemStack(Items.gold_nugget,1));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.icyToffyApple,1,3),
	    			  new ItemStack(DCsAppleMilk.toffyApple,1),
	    			  new ItemStack(Blocks.leaves, 1, 32767)));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.icyToffyApple,1,3),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.toffyApple,1),
		    		  "leavesWood"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.condensedMIlk, 1, 0),
	    		  new Object[]{
	    			  "bucketMilk",
		    		  "dustSugar"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.condensedMIlk, 1, 0),
	    		  new Object[]{
	    			  "listAllmilk",
		    		  "dustSugar"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.condensedMIlk, 1, 1),
	    		  new Object[]{
	    			  "cropCassis",
		    		  "dustSugar"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.condensedMIlk, 1, 2),
	    		  new Object[]{
	    			  "cropSpiceleaf",
		    		  "dustSugar"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.condensedMIlk, 1, 3),
	    		  new Object[]{
	    			  "cropYuzu",
		    		  "dustSugar"
					 }));
		 
		 GameRegistry.addShapelessRecipe(
	    		  new ItemStack(DCsAppleMilk.EXItems,1,3),
	    			  new ItemStack(DCsAppleMilk.EXItems,1,2));
		 
		 GameRegistry.addShapelessRecipe(
	    		  new ItemStack(DCsAppleMilk.EXItems,1,4),
	    			  new ItemStack(DCsAppleMilk.EXItems,1,3));
		 
		 GameRegistry.addShapelessRecipe(
	    		  new ItemStack(DCsAppleMilk.inkStick,1),
	    			  new ItemStack(DCsAppleMilk.EXItems,1,1),
	    			  new ItemStack(Items.coal,1,1));
		 
		 GameRegistry.addShapelessRecipe(
	    		  new ItemStack(DCsAppleMilk.appleSandwich, 2, 0),
	    			  new ItemStack(Items.apple,1),
	    			  new ItemStack(Items.bread,1));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.appleSandwich, 2, 0),
	    		  new Object[]{
	    			  "cropApple",
		    		  new ItemStack(Items.bread, 1)
					 }));
		 
		 GameRegistry.addShapelessRecipe(
	    		  new ItemStack(DCsAppleMilk.appleSandwich,2,1),
	    			  new ItemStack(Items.egg,1),
	    			  new ItemStack(Items.bread,1));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.appleSandwich, 2, 2),
	    		  new Object[]{
	    			  "foodCassisPreserve",
		    		  new ItemStack(Items.bread, 1)
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.appleSandwich, 2, 3),
	    		  new Object[]{
	    			  "foodYuzuMarmalade",
		    		  new ItemStack(Items.bread, 1)
					 }));
	}
	
	static void addChalcedony() {
		
		GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.flintBlock,1),
				 new Object[]{
					 "UU",
					 "UU",
					 Character.valueOf('U'), Items.flint
				 });
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(Items.flint, 4),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.flintBlock, 1)
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.chalcedonyKnife, 1),
	    		  new Object[]{"X","X","Y",
	    			  Character.valueOf('Y'), "stickWood",
	    			  Character.valueOf('X'), new ItemStack(DCsAppleMilk.chalcedony, 1, 0)}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.chalcedonyHammer, 1),
	    		  new Object[]{"XXX"," Y "," Y ",
	    			  Character.valueOf('Y'), "stickWood",
	    			  Character.valueOf('X'), new ItemStack(DCsAppleMilk.chalcedony, 1, 0)}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.monocle, 1),
	    		  new Object[]{" X ","XZX"," XY",
	    			  Character.valueOf('X'), new ItemStack(Items.iron_ingot, 1, 0),
	    			  Character.valueOf('Y'), new ItemStack(DCsAppleMilk.chalcedony, 1, 0),
	    			  Character.valueOf('Z'), new ItemStack(Blocks.glass_pane, 1, 0)}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.onixSword, 1),
	    		  new Object[]{"X","X","Y",
	    			  Character.valueOf('Y'), new ItemStack(Items.iron_ingot),
	    			  Character.valueOf('X'), new ItemStack(DCsAppleMilk.chalcedony, 1, 3)}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.EXItems, 1, 3),
	    		  new Object[]{" X ","X X"," X ",
	    			  Character.valueOf('X'), new ItemStack(DCsAppleMilk.chalcedony, 1, 0)}));
		 
		 if (!DCsConfig.disableFireSteater)
		 {
			 GameRegistry.addRecipe(
					 new ShapelessOreRecipe(
		    		  new ItemStack(DCsAppleMilk.firestarter, 1),
		    		  new Object[]{
		    			  new ItemStack(DCsAppleMilk.chalcedony, 1, 0),
		    			  new ItemStack(Items.iron_ingot, 1),
						 })); 
		 }
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.chalcedony, 1, 1),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.chalcedony, 1, 0),
	    			  "dyeOrange"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.chalcedony, 1, 2),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.chalcedony, 1, 0),
	    			  "dyeWhite"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.chalcedony, 1, 2),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.chalcedony, 1, 0),
	    			  new ItemStack(Items.dye, 1, 15)
					 }));
		 
		 GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.chalcedony, 1, 3),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.chalcedony, 1, 0),
	    			  "dyeBlack"
					 }));
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.cLamp, 1, 0),
				 new Object[]{
					 " U ",
					 "UXU",
					 " U ",
					 Character.valueOf('U'), Items.redstone,
					 Character.valueOf('X'), new ItemStack(DCsAppleMilk.chalcedony, 1, 0)
				 });
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.cLamp, 1, 1),
				 new Object[]{
					 " U ",
					 "UXU",
					 " U ",
					 Character.valueOf('U'), Items.redstone,
					 Character.valueOf('X'), new ItemStack(DCsAppleMilk.chalcedony, 1, 1)
				 });
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.cLamp, 1, 2),
				 new Object[]{
					 " U ",
					 "UXU",
					 " U ",
					 Character.valueOf('U'), Items.redstone,
					 Character.valueOf('X'), new ItemStack(DCsAppleMilk.chalcedony, 1, 2)
				 });
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.cLamp, 1, 3),
				 new Object[]{
					 " U ",
					 "UXU",
					 " U ",
					 Character.valueOf('U'), Items.redstone,
					 Character.valueOf('X'), new ItemStack(DCsAppleMilk.chalcedony, 1, 3)
				 });
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.cLamp, 1, 4),
				 new Object[]{
					 " U ",
					 "UXU",
					 " U ",
					 Character.valueOf('U'), Blocks.glass,
					 Character.valueOf('X'), new ItemStack(DCsAppleMilk.cLamp, 1, 0)
				 });
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.cLamp, 1, 5),
				 new Object[]{
					 " U ",
					 "UXU",
					 " U ",
					 Character.valueOf('U'), Blocks.glass,
					 Character.valueOf('X'), new ItemStack(DCsAppleMilk.cLamp, 1, 1)
				 });
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.cLamp, 1, 6),
				 new Object[]{
					 " U ",
					 "UXU",
					 " U ",
					 Character.valueOf('U'), Blocks.glass,
					 Character.valueOf('X'), new ItemStack(DCsAppleMilk.cLamp, 1, 2)
				 });
		 
		 GameRegistry.addRecipe(
				 new ItemStack(DCsAppleMilk.cLamp, 1, 7),
				 new Object[]{
					 " U ",
					 "UXU",
					 " U ",
					 Character.valueOf('U'), Blocks.glass,
					 Character.valueOf('X'), new ItemStack(DCsAppleMilk.cLamp, 1, 3)
				 });
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cLamp, 1, 8),
	    		  new Object[]{" X ","XYX"," X ",
	    			  Character.valueOf('Y'), new ItemStack(DCsAppleMilk.cLamp, 1, 0),
	    			  Character.valueOf('X'), "ingotSilver"}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cLamp, 1, 9),
	    		  new Object[]{" X ","XYX"," X ",
	    			  Character.valueOf('Y'), new ItemStack(DCsAppleMilk.cLamp, 1, 1),
	    			  Character.valueOf('X'), "ingotLead"}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cLamp, 1, 10),
	    		  new Object[]{" X ","XYX"," X ",
	    			  Character.valueOf('Y'), new ItemStack(DCsAppleMilk.cLamp, 1, 2),
	    			  Character.valueOf('X'), new ItemStack(Items.gold_ingot, 1, 0)}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.cLamp, 1, 11),
	    		  new Object[]{" X ","XYX"," X ",
	    			  Character.valueOf('Y'), new ItemStack(DCsAppleMilk.cLamp, 1, 3),
	    			  Character.valueOf('X'), "ingotSteel"}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.rotaryDial, 1, 0),
	    		  new Object[]{"XXX","ZYZ","XXX",
	    			  Character.valueOf('X'), new ItemStack(Blocks.stained_hardened_clay, 1, 15),
	    			  Character.valueOf('Y'), "ingotGold",
	    			  Character.valueOf('Z'), new ItemStack(DCsAppleMilk.EXItems, 1, 3)}));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.rotaryDial, 1, 0),
	    		  new Object[]{"XXX","ZYZ","XXX",
	    			  Character.valueOf('X'), new ItemStack(Blocks.stained_hardened_clay, 1, 15),
	    			  Character.valueOf('Y'), new ItemStack(Items.gold_ingot, 1, 0),
	    			  Character.valueOf('Z'), new ItemStack(DCsAppleMilk.EXItems, 1, 3)}));
	}
	
	static void addPrincess() {
		
		if (DCsConfig.useEXRecipe)
		{
			GameRegistry.addRecipe(
					 new ShapedOreRecipe(
		    		  new ItemStack(DCsAppleMilk.princessClam, 1, 0),
		    		  new Object[]{
							 "XYX",
							 " Z ",
							 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.clam, 1, 0),
							 Character.valueOf('Y'), new ItemStack(Items.diamond, 1, 0),
							 Character.valueOf('X'), "ingotGold"}));
			
			GameRegistry.addRecipe(
					 new ShapedOreRecipe(
		    		  new ItemStack(DCsAppleMilk.princessClam, 1, 0),
		    		  new Object[]{
							 "XYX",
							 " Z ",
							 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.clam, 1, 0),
							 Character.valueOf('Y'), new ItemStack(Items.diamond, 1, 0),
							 Character.valueOf('X'), new ItemStack(Items.gold_ingot, 1, 0)}));
		}
		
		GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.princessClam, 1, 1),
	    		  new Object[]{
	    			  	 " Y ",
						 "XZX",
						 " Y ",
						 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.princessClam, 1, 0),
						 Character.valueOf('Y'), new ItemStack(Items.diamond, 1, 0),
						 Character.valueOf('X'), new ItemStack(Blocks.obsidian, 1, 0)}));
		
		GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.princessClam, 1, 2),
	    		  new Object[]{
	    			  	 " Y ",
						 "XZX",
						 " Y ",
						 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.princessClam, 1, 0),
						 Character.valueOf('Y'), new ItemStack(Items.emerald, 1, 0),
						 Character.valueOf('X'), new ItemStack(Blocks.obsidian, 1, 0)}));
		
		GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.princessClam, 1, 3),
	    		  new Object[]{
	    			  	 " Y ",
						 "XZX",
						 " Y ",
						 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.princessClam, 1, 0),
						 Character.valueOf('Y'), new ItemStack(Items.quartz, 1, 0),
						 Character.valueOf('X'), new ItemStack(Blocks.obsidian, 1, 0)}));
		
		GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.princessClam, 1, 4),
	    		  new Object[]{
	    			  	 " Y ",
						 "XZX",
						 " Y ",
						 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.princessClam, 1, 0),
						 Character.valueOf('Y'), new ItemStack(Items.dye, 1, 4),
						 Character.valueOf('X'), new ItemStack(Blocks.obsidian, 1, 0)}));
		
		GameRegistry.addShapelessRecipe(
				new ItemStack(DCsAppleMilk.princessClam, 1, 3),
				new ItemStack(DCsAppleMilk.princessClam, 1, 3));
	}
	
	static void addBottle() {
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (48 + 1)),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
	    			  "drinkSake",
					 }));
     
     GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (48 + 2)),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
	    			  "drinkBeer",
					 }));
     
     GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (48 + 3)),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
	    			  "drinkWine",
					 }));
     
     GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (48 + 5)),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
	    			  "drinkRum",
					 }));
     
     GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (48 + 4)),
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
	    			  "drinkGin",
					 }));
     
     GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (112 + 8)),
	    		  new Object[]{
						 "XXX",
						 "XZX",
						 "XXX",
						 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						 Character.valueOf('X'), "bucketMilk"}));
     
     GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (112 + 9)),
	    		  new Object[]{
						 "XXX",
						 "XZX",
						 "XXX",
						 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						 Character.valueOf('X'), "bucketSoymilk"}));
     
     GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (112 + 9)),
	    		  new Object[]{
						 "XXX",
						 "XZX",
						 "XXX",
						 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						 Character.valueOf('X'), "foodSoymilk"}));
     
     GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (112 + 10)),
	    		  new Object[]{
						 "XXX",
						 "XZX",
						 "XXX",
						 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						 Character.valueOf('X'), "dustSugar"}));
     
     GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (112 + 12)),
	    		  new Object[]{
						 "XXX",
						 "XZX",
						 "XXX",
						 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						 Character.valueOf('X'), "dropHoney"}));
     
     GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (112 + 13)),
	    		  new Object[]{
						 "XXX",
						 "XZX",
						 "XXX",
						 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						 Character.valueOf('X'), "cropAlmond"}));
     
     GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (112 + 13)),
	    		  new Object[]{
						 "XXX",
						 "XZX",
						 "XXX",
						 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						 Character.valueOf('X'), "cropPeanut"}));
     
     GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (112 + 13)),
	    		  new Object[]{
						 "XXX",
						 "XZX",
						 "XXX",
						 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						 Character.valueOf('X'), "cropWalnut"}));
     
     GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (112 + 13)),
	    		  new Object[]{
						 "XXX",
						 "XZX",
						 "XXX",
						 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						 Character.valueOf('X'), "cropHazelnut"}));
     
     GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (112 + 13)),
	    		  new Object[]{
						 "XXX",
						 "XZX",
						 "XXX",
						 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						 Character.valueOf('X'), "cropChestnut"}));
     
     GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (112 + 13)),
	    		  new Object[]{
						 "XXX",
						 "XZX",
						 "XXX",
						 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						 Character.valueOf('X'), "marron"}));
     
     GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (112 + 14)),
	    		  new Object[]{
						 "XXX",
						 "XZX",
						 "XXX",
						 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						 Character.valueOf('X'), "listAllberry"}));
     
     GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (112 + 14)),
	    		  new Object[]{
						 "XXX",
						 "XZX",
						 "XXX",
						 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						 Character.valueOf('X'), "cropStrawberry"}));
     
     GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (112 + 14)),
	    		  new Object[]{
						 "XXX",
						 "XZX",
						 "XXX",
						 Character.valueOf('Z'), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						 Character.valueOf('X'), "cropRaspberry"}));
	}
	
	static void addCordial() {//酒別にレシピがあり、量が多いので、for文で回しつつ個別メソッドを用意した
		
		String[] alcohol = new String[] {"bottleSake", "bottleGin", "bottleRum", "bottleVodka", "bottleWhiskey"};
		
		for (int i = 0 ; i < 5 ; i++)
		{
			GameRegistry.addRecipe(
					 new ShapelessOreRecipe(
		    		  new ItemStack(DCsAppleMilk.itemCordial, 1, 0),
		    		  new Object[]{
		    			  "dustSugar",
		    			  alcohol[i],
		    			  "cropApple"
						 }));
			
			GameRegistry.addRecipe(
					 new ShapelessOreRecipe(
		    		  new ItemStack(DCsAppleMilk.itemCordial, 1, 4),
		    		  new Object[]{
		    			  "dustSugar",
		    			  alcohol[i],
		    			  "foodTea"
						 }));
			
			GameRegistry.addRecipe(
					 new ShapelessOreRecipe(
		    		  new ItemStack(DCsAppleMilk.itemCordial, 1, 8),
		    		  new Object[]{
		    			  "dustSugar",
		    			  alcohol[i],
		    			  "cropCassis"
						 }));
			
			GameRegistry.addRecipe(
					 new ShapelessOreRecipe(
		    		  new ItemStack(DCsAppleMilk.itemCordial, 1, 12),
		    		  new Object[]{
		    			  "dustSugar",
		    			  alcohol[i],
		    			  "cropPlum"
						 }));
		}
		
		
	}
	
	static void addMaterials() {
		
		//sapling & seed
		GameRegistry.addShapelessRecipe(
	    		  new ItemStack(DCsAppleMilk.saplingTea, 1, 1),
	    			  new ItemStack(DCsAppleMilk.leafTea, 1, 2),
	    			  new ItemStack(DCsAppleMilk.saplingTea, 1, 0));
		
		GameRegistry.addShapelessRecipe(
	    		  new ItemStack(DCsAppleMilk.saplingTea, 1, 2),
	    			  new ItemStack(Blocks.red_flower, 1, 0),
	    			  new ItemStack(DCsAppleMilk.saplingTea, 1, 0));
		
		GameRegistry.addShapelessRecipe(
	    		  new ItemStack(DCsAppleMilk.saplingYuzu, 1, 0),
	    			  new ItemStack(DCsAppleMilk.leafTea, 1, 3),
	    			  new ItemStack(Blocks.sapling, 1, 0));
		
		GameRegistry.addShapelessRecipe(
	    		  new ItemStack(DCsAppleMilk.itemMintSeed, 1, 0),
	    			  new ItemStack(Items.wheat_seeds, 1, 0),
	    			  new ItemStack(DCsAppleMilk.leafTea, 1, 1));
		
		//misc
		GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(DCsAppleMilk.icyCrystal, 1),
	    		  new Object[]{
						 "XYX",
						 "YZY",
						 "XYX",
						 Character.valueOf('X'), new ItemStack(Items.snowball, 1),
						 Character.valueOf('Y'), new ItemStack(Blocks.ice, 1),
						 Character.valueOf('Z'), new ItemStack(Items.emerald, 1)}));
		
		GameRegistry.addShapelessRecipe(
	    		  new ItemStack(DCsAppleMilk.batteryItem, 1, 2),
	    		      new ItemStack(DCsAppleMilk.leafTea, 1, 3),
	    			  new ItemStack(Items.gold_nugget, 1, 0),
	    			  new ItemStack(DCsAppleMilk.EXItems, 1, 7));
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.batteryItem, 1, 2),
	    		  new Object[]{
	    			  "nuggetTin",
	    			  new ItemStack(DCsAppleMilk.leafTea, 1, 3),
	    			  "nuggetCopper"
					 }));
		 
		 //extended vanilla recipe
		 GameRegistry.addShapelessRecipe(
	    		  new ItemStack(DCsAppleMilk.EXItems, 1, 1),
	    			  new ItemStack(Items.leather, 1));
		 
		 GameRegistry.addShapelessRecipe(
	    		  new ItemStack(DCsAppleMilk.EXItems, 9, 7),
	    			  new ItemStack(Items.iron_ingot, 1),
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767));
		 
		 GameRegistry.addRecipe(
				 new ShapedOreRecipe(
	    		  new ItemStack(Items.iron_ingot, 1),
	    		  new Object[]{
						 "XXX",
						 "XXX",
						 "XXX",
						 Character.valueOf('X'), new ItemStack(DCsAppleMilk.EXItems, 1, 7)}));
		 
		 GameRegistry.addShapelessRecipe(
	    		  new ItemStack(Blocks.sticky_piston, 1),
	    			  new ItemStack(DCsAppleMilk.EXItems, 1, 1),
	    			  new ItemStack(Blocks.piston, 1));
		 
		 GameRegistry.addShapelessRecipe(
	    		  new ItemStack(Items.magma_cream, 1),
	    			  new ItemStack(DCsAppleMilk.EXItems, 1, 1),
	    			  new ItemStack(Items.blaze_powder, 1));
		 
		 GameRegistry.addShapelessRecipe(
	    		  new ItemStack(Items.dye, 1, 2),
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
	    			  new ItemStack(DCsAppleMilk.leafTea, 1, 0));
		 
		 GameRegistry.addShapelessRecipe(
	    		  new ItemStack(DCsAppleMilk.EXItems, 1, 6),
	    			  new ItemStack(DCsAppleMilk.DCgrater, 1, 32767),
	    			  new ItemStack(DCsAppleMilk.clam, 1, 0));
		 
		 GameRegistry.addRecipe(
				 new ItemStack(Items.lead,1),
				 new Object[]{
					 "TT ",
					 "TS ",
					 "  T",
					 Character.valueOf('T'), Items.string,
					 Character.valueOf('S'), new ItemStack(DCsAppleMilk.EXItems,1,1),
				 });
	}
	
	static void addSmelting() {
		
		GameRegistry.addSmelting(
	    		  Items.apple,
	    		  new ItemStack(DCsAppleMilk.bakedApple, 1),
	    		  0.3F);   
	      
	      GameRegistry.addSmelting(
	    		  DCsAppleMilk.woodBox,
	    		  new ItemStack(DCsAppleMilk.charcoalBox, 1, 0),
	    		  0.1F);
	      
	      GameRegistry.addSmelting(
	    		  Items.rotten_flesh,
	    		  new ItemStack(Items.leather, 1),
	    		  0F);
	      
	      GameRegistry.addSmelting(
	    		  DCsAppleMilk.flintBlock,
	    		  new ItemStack(DCsAppleMilk.chalcedony, 1, 0),
	    		  0.5F);
	      
	      GameRegistry.addSmelting(
	    		  DCsAppleMilk.icyCrystal,
	    		  new ItemStack(Items.quartz, 1),
	    		  0.5F);
	      
	      FurnaceRecipes.smelting().func_151394_a(new ItemStack(DCsAppleMilk.leafTea, 1, 0),
	    		  new ItemStack(DCsAppleMilk.foodTea, 1, 0), 0.2F);
	      
	      FurnaceRecipes.smelting().func_151394_a(new ItemStack(DCsAppleMilk.clam, 1, 0),
	    		  new ItemStack(DCsAppleMilk.clam, 1, 1), 0.2F);
	      
	      FurnaceRecipes.smelting().func_151394_a(new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
	    		  new ItemStack(DCsAppleMilk.EXItems, 1, 5), 0.2F);
	      
	      FurnaceRecipes.smelting().func_151394_a(new ItemStack(DCsAppleMilk.EXItems, 1, 5),
	    		  new ItemStack(Blocks.glass,1, 0), 0.2F);
	      
	      FurnaceRecipes.smelting().func_151394_a(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 0),
	    		  new ItemStack(DCsAppleMilk.EXItems, 8, 0), 0.2F);
	      
	      FurnaceRecipes.smelting().func_151394_a(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 1),
	    		  new ItemStack(DCsAppleMilk.icyToffyApple, 4, 4), 0.2F);
	      
	      FurnaceRecipes.smelting().func_151394_a(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 2),
	    		  new ItemStack(DCsAppleMilk.icyToffyApple, 4, 5), 0.2F);
	}
	
	/*この部分は、複数のModの水入り容器を取得した後に呼び出すため、postInitに呼ぶ
	 * 水入り容器の取得はLoadModHandlerを利用*/
	public void addInstantTea() {
		
		this.waterContainer.add(new ItemStack(Items.water_bucket, 1, 0));
		
		if (LoadModHandler.getArray("containerWater") != null && !LoadModHandler.getArray("containerWater").isEmpty()){
			this.waterContainer.addAll(LoadModHandler.getArray("containerWater"));
		}
		
		for (ItemStack water : waterContainer)
		{
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.teacupBlock, 1, 4),//greentea
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
		    		  water,
		    		  new ItemStack(DCsAppleMilk.foodTea, 1, 0)
					 }));
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.teacupBlock, 1, 2),//tea
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
	    			  water,
		    		  new ItemStack(DCsAppleMilk.foodTea, 1, 1)
					 }));
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.teacupBlock, 1, 6),//cocoa
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
	    			  water,
		    		  new ItemStack(Items.dye, 1, 3)
					 }));
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.teacupBlock, 1, 8),//juice
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
	    			  water,
		    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 0)
					 }));
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.teacupBlock, 1, 8),//juice
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
	    			  water,
		    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 1)
					 }));
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.teacupBlock, 1, 10),//lemon
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
	    			  water,
		    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 2)
					 }));
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.teacupBlock, 1, 12),//coffee
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
	    			  water,
		    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 3)
					 }));
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.wallMug, 1, 1),//greentea
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyWallMug),
	    			  water,
		    		  new ItemStack(DCsAppleMilk.foodTea, 1, 0)
					 }));
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.wallMug, 1, 0),//tea
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyWallMug),
	    			  water,
		    		  new ItemStack(DCsAppleMilk.foodTea, 1, 1)
					 }));
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.wallMug, 1, 2),//cocoa
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyWallMug),
	    			  water,
		    		  new ItemStack(Items.dye, 1, 3)
					 }));
		
		GameRegistry.addRecipe(
				 new ShapelessOreRecipe(
	    		  new ItemStack(DCsAppleMilk.wallMug, 1, 3),//coffee
	    		  new Object[]{
	    			  new ItemStack(DCsAppleMilk.emptyWallMug),
	    			  water,
		    		  new ItemStack(DCsAppleMilk.gratedApple, 1, 3)
					 }));
		}
		
	}
	
	//金属ナゲットのレシピ登録も、他MODのアイテム追加が終わるpostInitで呼ぶ
	public void addMetalRecipe()
	{
		ArrayList<ItemStack> woodRubber = OreDictionary.getOres("woodRubber");
	    if (woodRubber.size() > 0)
	    {
	    	ItemStack ret = woodRubber.get(0);
	    	
	    	GameRegistry.addRecipe(
					new ShapelessOreRecipe(
			    		new ItemStack(ret.getItem(), 9, ret.getItemDamage()),
			    		new Object[]{
			    		new ItemStack(DCsAppleMilk.woodBox, 1, 4),
			    		}));
		     
		    GameRegistry.addRecipe(
					new ShapedOreRecipe(
			    		new ItemStack(DCsAppleMilk.woodBox, 1, 4),
			    		new Object[]{"XXX","XXX","XXX",
			    		Character.valueOf('X'), "woodRubber"}));
	    }
	    
	    ArrayList<ItemStack> ingotTin = OreDictionary.getOres("ingotTin");
	    while(ingotTin.size() > 0)
	    {
	    	ItemStack ret = ingotTin.get(0);
	    	
	    	GameRegistry.addRecipe(
					new ShapelessOreRecipe(
			    		new ItemStack(DCsAppleMilk.EXItems, 9, 8),
			    		new Object[]{
			    		"ingtoTin",
			    		new ItemStack(DCsAppleMilk.DCgrater, 1, 32767)
			    		}));
		     
		    GameRegistry.addRecipe(
					new ShapedOreRecipe(
			    		new ItemStack(ret.getItem(), 1, ret.getItemDamage()),
			    		new Object[]{"XXX","XXX","XXX",
			    		Character.valueOf('X'), new ItemStack(DCsAppleMilk.EXItems, 1, 8)}));
	    }
	    
	    ArrayList<ItemStack> ingotCopper = OreDictionary.getOres("ingotCopper");
	    while(ingotTin.size() > 0)
	    {
	    	ItemStack ret = ingotTin.get(0);
	    	
	    	GameRegistry.addRecipe(
					new ShapelessOreRecipe(
			    		new ItemStack(DCsAppleMilk.EXItems, 9, 9),
			    		new Object[]{
			    		"ingtoCopper",
			    		new ItemStack(DCsAppleMilk.DCgrater, 1, 32767)
			    		}));
		     
		    GameRegistry.addRecipe(
					new ShapedOreRecipe(
			    		new ItemStack(ret.getItem(), 1, ret.getItemDamage()),
			    		new Object[]{"XXX","XXX","XXX",
			    		Character.valueOf('X'), new ItemStack(DCsAppleMilk.EXItems, 1, 9)}));
	    }
	}
	
}
