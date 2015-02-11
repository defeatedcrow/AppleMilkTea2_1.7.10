package mods.defeatedcrow.recipe;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import mods.defeatedcrow.api.*;
import mods.defeatedcrow.api.charge.ChargeItemManager;
import mods.defeatedcrow.api.recipe.*;
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.plugin.LoadModHandler;
import net.minecraft.block.Block;
import net.minecraft.init.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RegisterMakerRecipe {
	
	public void registerTea()
	{
		//登録メソッド
		//teacup1
		RecipeRegisterManager.teaRecipe.registerCanMilk(new ItemStack(Items.milk_bucket, 1), new ItemStack(DCsAppleMilk.teacupBlock, 1, 1),
				new ItemStack(DCsAppleMilk.teacupBlock, 1, 1),
	    		new String("defeatedcrow:textures/blocks/contents_milk.png"));
	    
	    //牛乳を追加投入できるものは下記のメソッドで登録
		RecipeRegisterManager.teaRecipe.registerCanMilk(new ItemStack(Items.dye, 1, 3), new ItemStack(DCsAppleMilk.teacupBlock, 1, 6),
	    		new ItemStack(DCsAppleMilk.teacupBlock, 1, 7),
	    		new String("defeatedcrow:textures/blocks/contents_cocoa.png"),
	    		new String("defeatedcrow:textures/blocks/contents_cocoa_milk.png"));
	    
		RecipeRegisterManager.teaRecipe.registerCanMilk(new ItemStack(DCsAppleMilk.foodTea, 1, 0), new ItemStack(DCsAppleMilk.teacupBlock, 1, 4),
	    		new ItemStack(DCsAppleMilk.teacupBlock, 1, 5),
	    		new String("defeatedcrow:textures/blocks/contents_greentea.png"),
	    		new String("defeatedcrow:textures/blocks/contents_greentea_milk.png"));
	    
		RecipeRegisterManager.teaRecipe.registerCanMilk(new ItemStack(DCsAppleMilk.foodTea, 1, 1), new ItemStack(DCsAppleMilk.teacupBlock, 1, 2),
	    		new ItemStack(DCsAppleMilk.teacupBlock, 1, 3),
	    		new String("defeatedcrow:textures/blocks/contents_tea.png"),
	    		new String("defeatedcrow:textures/blocks/contents_tea_milk.png"));
	    
		RecipeRegisterManager.teaRecipe.registerCanMilk(new ItemStack(DCsAppleMilk.gratedApple, 1, 0), new ItemStack(DCsAppleMilk.teacupBlock, 1, 8),
	    		new ItemStack(DCsAppleMilk.teacupBlock, 1, 9),
	    		new String("defeatedcrow:textures/blocks/contents_juice.png"));
	    
		RecipeRegisterManager.teaRecipe.registerCanMilk(new ItemStack(DCsAppleMilk.gratedApple, 1, 1), new ItemStack(DCsAppleMilk.teacupBlock, 1, 8),
	    		new ItemStack(DCsAppleMilk.teacupBlock, 1, 9),
	    		new String("defeatedcrow:textures/blocks/contents_juice.png"));
	    
		RecipeRegisterManager.teaRecipe.registerCanMilk(new ItemStack(DCsAppleMilk.gratedApple, 1, 2), new ItemStack(DCsAppleMilk.teacupBlock, 1, 10),
	    		new ItemStack(DCsAppleMilk.teacupBlock, 1, 11),
	    		new String("defeatedcrow:textures/blocks/contents_lemon.png"));
	    
		RecipeRegisterManager.teaRecipe.registerCanMilk(new ItemStack(DCsAppleMilk.gratedApple, 1, 3), new ItemStack(DCsAppleMilk.teacupBlock, 1, 12),
	    		new ItemStack(DCsAppleMilk.teacupBlock, 1, 13),
	    		new String("defeatedcrow:textures/blocks/contents_cocoa.png"),
	    		new String("defeatedcrow:textures/blocks/contents_cocoa_milk.png"));
	    
	    //teacup2
		RecipeRegisterManager.teaRecipe.registerCanMilk(new ItemStack(DCsAppleMilk.foodTea, 1, 2), new ItemStack(DCsAppleMilk.teaCup2, 1, 0),
	    		new ItemStack(DCsAppleMilk.teaCup2, 1, 1),
	    		new String("defeatedcrow:textures/blocks/contents_earlgray.png"),
	    		new String("defeatedcrow:textures/blocks/contents_tea_milk.png"));
	    
		RecipeRegisterManager.teaRecipe.registerCanMilk(new ItemStack(DCsAppleMilk.foodTea, 1, 3), new ItemStack(DCsAppleMilk.teaCup2, 1, 2),
	    		new ItemStack(DCsAppleMilk.teaCup2, 1, 3),
	    		new String("defeatedcrow:textures/blocks/contents_appletea.png"),
	    		new String("defeatedcrow:textures/blocks/contents_tea_milk.png"));
	    
		RecipeRegisterManager.teaRecipe.register(new ItemStack(DCsAppleMilk.gratedApple, 1, 5), new ItemStack(DCsAppleMilk.teaCup2, 1, 4),
	    		new String("defeatedcrow:textures/blocks/contents_lime.png"));
	    
		RecipeRegisterManager.teaRecipe.register(new ItemStack(DCsAppleMilk.gratedApple, 1, 6), new ItemStack(DCsAppleMilk.teaCup2, 1, 5),
	    		new String("defeatedcrow:textures/blocks/contents_tomato.png"));
	    
		RecipeRegisterManager.teaRecipe.registerCanMilk(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), new ItemStack(DCsAppleMilk.teaCup2, 1, 6),
	    		new ItemStack(DCsAppleMilk.teaCup2, 1, 7),
	    		new String("defeatedcrow:textures/blocks/contents_berry.png"));
	    
		RecipeRegisterManager.teaRecipe.register(new ItemStack(DCsAppleMilk.gratedApple, 1, 8), new ItemStack(DCsAppleMilk.teaCup2, 1, 8),
	    		new String("defeatedcrow:textures/blocks/contents_grape.png"));
	    
		RecipeRegisterManager.teaRecipe.register(new ItemStack(DCsAppleMilk.leafTea, 1, 1), new ItemStack(DCsAppleMilk.teaCup2, 1, 9),
	    		new String("defeatedcrow:textures/blocks/contents_mint.png"));
		
		RecipeRegisterManager.teaRecipe.register(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 3), new ItemStack(DCsAppleMilk.teaCup2, 1, 10),
	    		new String("defeatedcrow:textures/blocks/contents_lemon.png"));
		
		RecipeRegisterManager.teaRecipe.register(new ItemStack(DCsAppleMilk.gratedApple, 1, 9), new ItemStack(DCsAppleMilk.teaCup2, 1, 11),
	    		new String("defeatedcrow:textures/blocks/contents_orange.png"));
		
		RecipeRegisterManager.teaRecipe.register(new ItemStack(DCsAppleMilk.yeast, 1, 1), new ItemStack(DCsAppleMilk.teaCup2, 1, 12),
	    		new String("defeatedcrow:textures/blocks/contents_soda.png"));
	}
	
	public void registerIce()
	{
		RecipeRegisterManager.iceRecipe.register(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 0), new ItemStack(DCsAppleMilk.blockIcecream, 1, 0));
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(DCsAppleMilk.teacupBlock, 1, 3), new ItemStack(DCsAppleMilk.blockIcecream, 1, 1),
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0));
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(DCsAppleMilk.teacupBlock, 1, 5), new ItemStack(DCsAppleMilk.blockIcecream, 1, 2),
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0));
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(DCsAppleMilk.teacupBlock, 1, 7), new ItemStack(DCsAppleMilk.blockIcecream, 1, 3),
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0));
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(DCsAppleMilk.teacupBlock, 1, 9), new ItemStack(DCsAppleMilk.blockIcecream, 1, 5),
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0));
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(DCsAppleMilk.teacupBlock, 1, 11), new ItemStack(DCsAppleMilk.blockIcecream, 1, 6),
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0));
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(DCsAppleMilk.teacupBlock, 1, 13), new ItemStack(DCsAppleMilk.blockIcecream, 1, 4),
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0));
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(Items.water_bucket, 1, 0), new ItemStack(DCsAppleMilk.EXItems, 1, 4),
				new ItemStack(Items.bucket, 1, 0));
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(DCsAppleMilk.teaCup2, 1, 4), new ItemStack(DCsAppleMilk.blockIcecream, 1, 7),
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0));
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(DCsAppleMilk.teaCup2, 1, 5), new ItemStack(DCsAppleMilk.blockIcecream, 1, 8),
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0));
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(DCsAppleMilk.teaCup2, 1, 7), new ItemStack(DCsAppleMilk.blockIcecream, 1, 9),
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0));
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(DCsAppleMilk.teaCup2, 1, 8), new ItemStack(DCsAppleMilk.blockIcecream, 1, 10),
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0));
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(DCsAppleMilk.teaCup2, 1, 9), new ItemStack(DCsAppleMilk.blockIcecream, 1, 11),
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0));
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(DCsAppleMilk.teaCup2, 1, 11), new ItemStack(DCsAppleMilk.blockIcecream, 1, 12),
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0));
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(DCsAppleMilk.teaCup2, 1, 12), new ItemStack(DCsAppleMilk.blockIcecream, 1, 13),
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0));
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 1), new ItemStack(DCsAppleMilk.cocktail, 1, 1),
				new ItemStack(DCsAppleMilk.emptyBottle, 1, 0));
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 17), new ItemStack(DCsAppleMilk.cocktail, 1, 1),
				new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 1));
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 33), new ItemStack(DCsAppleMilk.cocktail, 1, 1),
				new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 17));
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 49), new ItemStack(DCsAppleMilk.cocktail, 1, 1),
				new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 33));
		
		RecipeRegisterManager.iceRecipe.registerCharger(new ItemStack(DCsAppleMilk.icyCrystal, 1, 0), 64);
		RecipeRegisterManager.iceRecipe.registerCharger(new ItemStack(Blocks.ice, 1, 0), 8);
		RecipeRegisterManager.iceRecipe.registerCharger(new ItemStack(Blocks.packed_ice, 1, 0), 16);
		RecipeRegisterManager.iceRecipe.registerCharger(new ItemStack(Blocks.snow, 1, 0), 4);
		RecipeRegisterManager.iceRecipe.registerCharger(new ItemStack(Items.snowball, 1, 0), 1);
		
	}
	
	public void registerChargeItem()
	{
		ChargeItemManager.chargeItem.registerCharger(new ItemStack(DCsAppleMilk.batteryItem, 1, 0),  null,  6400);
		ChargeItemManager.chargeItem.registerCharger(new ItemStack(DCsAppleMilk.batteryItem, 1, 1),  null,  12800);
		ChargeItemManager.chargeItem.registerCharger(new ItemStack(DCsAppleMilk.batteryItem, 1, 2),  null,  800);
		ChargeItemManager.chargeItem.registerCharger(new ItemStack(DCsAppleMilk.yuzuBat, 1, 0),  null,  8000);
	}
	
	public void registerPan()
	{
		RecipeRegisterManager.panRecipe.register(new ItemStack(DCsAppleMilk.mincedFoods, 1, 3),
				new ItemStack(DCsAppleMilk.bowlBlock, 1, 0),
				new ItemStack(DCsAppleMilk.bowlJP, 1, 0),
				"rice", "Boiled Rice");
		
		RecipeRegisterManager.panRecipe.register(new ItemStack(DCsAppleMilk.mincedFoods, 1, 0),
				new ItemStack(DCsAppleMilk.bowlBlock, 1, 1),
				new ItemStack(DCsAppleMilk.bowlJP, 1, 1),
				"kinoko", "Mushroom Soup");
		
		RecipeRegisterManager.panRecipe.register(new ItemStack(DCsAppleMilk.mincedFoods, 1, 1),
				new ItemStack(DCsAppleMilk.bowlBlock, 1, 2),
				new ItemStack(DCsAppleMilk.bowlJP, 1, 2),
				"soup", "Salmon Stew");
		
		RecipeRegisterManager.panRecipe.register(new ItemStack(DCsAppleMilk.mincedFoods, 1, 2),
				new ItemStack(DCsAppleMilk.bowlBlock, 1, 3),
				new ItemStack(DCsAppleMilk.bowlJP, 1, 3),
				"zousui", "Zousui");
		
		RecipeRegisterManager.panRecipe.register(new ItemStack(DCsAppleMilk.mincedFoods, 1, 4),
				new ItemStack(DCsAppleMilk.bowlBlock, 1, 4),
				new ItemStack(DCsAppleMilk.bowlJP, 1, 4),
				"kayaku", "Kayakumeshi");
		
		RecipeRegisterManager.panRecipe.register(new ItemStack(DCsAppleMilk.mincedFoods, 1, 5),
				new ItemStack(DCsAppleMilk.bowlBlock, 1, 5),
				new ItemStack(DCsAppleMilk.bowlJP, 1, 5),
				"soi", "Tofu Nabe");
		
		RecipeRegisterManager.panRecipe.register(new ItemStack(DCsAppleMilk.mincedFoods, 1, 6),
				new ItemStack(DCsAppleMilk.bowlBlock, 1, 6),
				new ItemStack(DCsAppleMilk.bowlJP, 1, 6),
				"juice", "Pumpkin Soup");
		
		RecipeRegisterManager.panRecipe.register(new ItemStack(DCsAppleMilk.mincedFoods, 1, 7),
				new ItemStack(DCsAppleMilk.bowlBlock, 1, 7),
				new ItemStack(DCsAppleMilk.bowlJP, 1, 7),
				"BLTsoup", "BLT Soup");
		
		RecipeRegisterManager.panRecipe.register(new ItemStack(DCsAppleMilk.mincedFoods, 1, 9),
				new ItemStack(DCsAppleMilk.bowlBlock, 1, 8),
				new ItemStack(DCsAppleMilk.bowlJP, 1, 8),
				"misosoup", "Miso Soup");
		
		RecipeRegisterManager.panRecipe.register(new ItemStack(DCsAppleMilk.mincedFoods, 1, 10),
				new ItemStack(DCsAppleMilk.bowlBlock, 1, 9),
				new ItemStack(DCsAppleMilk.bowlJP, 1, 9),
				"clamsoup", "Clam Soup");
	}
	
	public void registerProcessor()
	{
		//プロセッサー用の新規レシピ
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.foodTea, 2, 0), true, null,
				new Object[]{DCsAppleMilk.leafTea});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 2, 6), true, null,
				new Object[]{DCsAppleMilk.clam});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.yeast, 1, 0), true, null,
				new Object[]{"cropApple", Items.sugar, DCsAppleMilk.bowlBlock});
		
		//おろし金レシピの移植
		//茶葉
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 0), true, null,
				new Object[]{"cropApple"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 1), true, null,
				new Object[]{"cropPeach"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 1), true, null,
				new Object[]{"cropBanana"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 9), true, null,
				new Object[]{"cropOrange"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 1), true, null,
				new Object[]{"cropPlum"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 2), true, null,
				new Object[]{"cropLemon","dropHoney"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 3), true, null,
				new Object[]{"cropCoffee"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 5), true, null,
				new Object[]{"cropLime"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 5), true, null,
				new Object[]{"cropCitron"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 6), true, null,
				new Object[]{"cropTomato"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 6), true, null,
				new Object[]{"tomato"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), true, null,
				new Object[]{"cropStrawberry"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), true, null,
				new Object[]{"cropBlueberry"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), true, null,
				new Object[]{"cropRaspberry"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), true, null,
				new Object[]{"cropBlackberry"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), true, null,
				new Object[]{"cropCassis"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 8), true, null,
				new Object[]{"cropGrape"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 8), true, null,
				new Object[]{"grape"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.foodTea, 1, 2), true, null,
				new Object[]{"cropOrange", new ItemStack(DCsAppleMilk.foodTea, 1, 1)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.foodTea, 1, 2), true, null,
				new Object[]{"cropCitron", new ItemStack(DCsAppleMilk.foodTea, 1, 1)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.foodTea, 1, 3), true, null,
				new Object[]{"cropApple", new ItemStack(DCsAppleMilk.foodTea, 1, 1)});
		
		//鍋材料
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 0), true, null,
				new Object[]{Blocks.brown_mushroom, Blocks.red_mushroom});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 1), true, null,
				new Object[]{new ItemStack(Items.fish, 1, 0), Items.carrot, Items.potato, "bucketMilk"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 1), true, null,
				new Object[]{new ItemStack(Items.fish, 1, 1), Items.carrot, Items.potato, "bucketMilk"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 2), true, null,
				new Object[]{Items.egg, Items.chicken, "cropRice"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 2), true, null,
				new Object[]{Items.egg, Items.chicken, "cropWheat"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 3), true, null,
				new Object[]{"cropRice"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 3), true, null,
				new Object[]{"cropWheat"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 4), true, null,
				new Object[]{new ItemStack(DCsAppleMilk.EXItems, 1, 2), "cropRice"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 4), true, null,
				new Object[]{new ItemStack(DCsAppleMilk.EXItems, 1, 2), "cropWheat"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 5), true, null,
				new Object[]{Items.chicken, "leek", "bucketSoymilk", "tofuKinu"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 5), true, null,
				new Object[]{Items.chicken, "leek", "bucketSoymilk", "tofuMomen"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 5), true, null,
				new Object[]{Items.chicken, "cropLeek", "foodSoymilk", "foodSilkentofu"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 6), true, null,
				new Object[]{Blocks.pumpkin, "bucketMilk"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 7), true, null,
				new Object[]{Items.porkchop, "cropLettus", "cropTomato"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 7), true, null,
				new Object[]{Items.porkchop, "cabbage", "tomato"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 8), true, null,
				new Object[]{new ItemStack(Items.dye,1,3), new ItemStack(Items.dye,1,3), "bucketMilk", "dustSugar"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 4), true, null,
				new Object[]{new ItemStack(DCsAppleMilk.mincedFoods, 1 ,8), "bucketMilk"});
		
		//砕石機
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(Items.flint), false, new ItemStack(Blocks.sand),
				new Object[]{new ItemStack(Blocks.gravel)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(Items.clay_ball), false, null,
				new Object[]{new ItemStack(Blocks.sand)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(Blocks.sand), false, new ItemStack(Items.rotten_flesh),
				new Object[]{new ItemStack(Blocks.soul_sand)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(Blocks.dirt), false, null,
				new Object[]{"dustAsh", "dustOilCake", new ItemStack(Items.dye, 1, 15), new ItemStack(Blocks.sand)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(Blocks.dirt), false, null,
				new Object[]{"dustAsh", "dustOilCake", new ItemStack(DCsAppleMilk.EXItems, 1, 6), new ItemStack(Blocks.sand)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.dustWood, 1, 0), false, null,
				new Object[]{"logWood"});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.dustWood, 1, 1), false, null,
				new Object[]{new ItemStack(Items.coal, 1, 1)});
		
		//ツール還元
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 24, 7), false, null,
				new Object[]{new ItemStack(Items.iron_axe, 1, 32767)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 16, 7), false, null,
				new Object[]{new ItemStack(Items.iron_hoe, 1, 32767)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 24, 7), false, null,
				new Object[]{new ItemStack(Items.iron_pickaxe, 1, 32767)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 8, 7), false, null,
				new Object[]{new ItemStack(Items.iron_shovel, 1, 32767)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 16, 7), false, null,
				new Object[]{new ItemStack(Items.iron_sword, 1, 32767)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 32, 7), false, null,
				new Object[]{new ItemStack(Items.iron_boots, 1, 32767)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 64, 7), false, null,
				new Object[]{new ItemStack(Items.iron_chestplate, 1, 32767)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 40, 7), false, null,
				new Object[]{new ItemStack(Items.iron_helmet, 1, 32767)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 56, 7), false, null,
				new Object[]{new ItemStack(Items.iron_leggings, 1, 32767)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(Items.gold_nugget, 16, 0), false, null,
				new Object[]{new ItemStack(Items.golden_hoe, 1, 32767)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(Items.gold_nugget, 24, 0), false, null,
				new Object[]{new ItemStack(Items.golden_pickaxe, 1, 32767)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(Items.gold_nugget, 8, 0), false, null,
				new Object[]{new ItemStack(Items.golden_shovel, 1, 32767)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(Items.gold_nugget, 24, 0), false, null,
				new Object[]{new ItemStack(Items.golden_axe, 1, 32767)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(Items.gold_nugget, 16, 0), false, null,
				new Object[]{new ItemStack(Items.golden_sword, 1, 32767)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(Items.gold_nugget, 32, 0), false, null,
				new Object[]{new ItemStack(Items.golden_boots, 1, 32767)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(Items.gold_nugget, 64, 0), false, null,
				new Object[]{new ItemStack(Items.golden_chestplate, 1, 32767)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(Items.gold_nugget, 40, 0), false, null,
				new Object[]{new ItemStack(Items.golden_helmet, 1, 32767)});
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(Items.gold_nugget, 56, 0), false, null,
				new Object[]{new ItemStack(Items.golden_leggings, 1, 32767)});
	}
	
	public void registerEvaporator()
	{
		//エッセンス
		RecipeRegisterManager.evaporatorRecipe.addRecipe(new ItemStack(DCsAppleMilk.essentialOil, 1, 0),
				null, new ItemStack(Items.apple, 8, 0));
		
		RecipeRegisterManager.evaporatorRecipe.addRecipe(new ItemStack(DCsAppleMilk.essentialOil, 1, 1),
				null, new ItemStack(Blocks.double_plant, 8, 4));
		
		RecipeRegisterManager.evaporatorRecipe.addRecipe(new ItemStack(DCsAppleMilk.essentialOil, 1, 2),
				null, new ItemStack(DCsAppleMilk.leafTea, 8, 1));
		
		RecipeRegisterManager.evaporatorRecipe.addRecipe(new ItemStack(DCsAppleMilk.essentialOil, 1, 3),
				null, new ItemStack(DCsAppleMilk.leafTea, 8, 3));
		
		RecipeRegisterManager.evaporatorRecipe.addRecipe(new ItemStack(DCsAppleMilk.essentialOil, 1, 4),
				null, new ItemStack(DCsAppleMilk.clam, 8, 0));
		
		RecipeRegisterManager.evaporatorRecipe.addRecipe(new ItemStack(DCsAppleMilk.essentialOil, 1, 5),
				null, new ItemStack(DCsAppleMilk.icyCrystal, 1, 0));
		
		//酒
		RecipeRegisterManager.evaporatorRecipe.addRecipe(null,
				new FluidStack(DCsAppleMilk.shothu_young, 100), new ItemStack(DCsAppleMilk.moromi, 1, 0), false);
		
		RecipeRegisterManager.evaporatorRecipe.addRecipe(null,
				new FluidStack(DCsAppleMilk.whiskey_young, 100), new ItemStack(DCsAppleMilk.moromi, 1, 1), false);
		
		RecipeRegisterManager.evaporatorRecipe.addRecipe(null,
				new FluidStack(DCsAppleMilk.rum_young, 100), new ItemStack(DCsAppleMilk.moromi, 1, 3), false);
		
		RecipeRegisterManager.evaporatorRecipe.addRecipe(null,
				new FluidStack(DCsAppleMilk.vodka_young, 100), new ItemStack(DCsAppleMilk.moromi, 1, 4), false);
		
		RecipeRegisterManager.evaporatorRecipe.addRecipe(null,
				new FluidStack(DCsAppleMilk.brandy_young, 100), new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 3));
		
		RecipeRegisterManager.evaporatorRecipe.addRecipe(null,
				new FluidStack(DCsAppleMilk.brandy_young, 100), new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 19));
		
		RecipeRegisterManager.evaporatorRecipe.addRecipe(null,
				new FluidStack(DCsAppleMilk.brandy_young, 100), new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 35));
		
		RecipeRegisterManager.evaporatorRecipe.addRecipe(null,
				new FluidStack(DCsAppleMilk.brandy_young, 100), new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 51));
		
		//製油
		RecipeRegisterManager.evaporatorRecipe.addRecipe(new ItemStack(DCsAppleMilk.dustWood, 1, 3),
				new FluidStack(DCsAppleMilk.camelliaOil, 100), new ItemStack(DCsAppleMilk.leafTea, 8, 4));
		
		RecipeRegisterManager.evaporatorRecipe.addRecipe(new ItemStack(DCsAppleMilk.dustWood, 1, 3),
				new FluidStack(DCsAppleMilk.vegitableOil, 25), new ItemStack(Items.wheat_seeds, 8));
		
		RecipeRegisterManager.evaporatorRecipe.addRecipe(new ItemStack(DCsAppleMilk.dustWood, 1, 3),
				new FluidStack(DCsAppleMilk.vegitableOil, 20), new ItemStack(Items.pumpkin_seeds, 8));
	}
	
	public void registerBrewing()
	{
		BrewingRecipe.registerRecipe(DCsAppleMilk.shothu_young, DCsAppleMilk.shothu);
		BrewingRecipe.registerRecipe(DCsAppleMilk.whiskey_young, DCsAppleMilk.whiskey);
		BrewingRecipe.registerRecipe(DCsAppleMilk.brandy_young, DCsAppleMilk.brandy);
		BrewingRecipe.registerRecipe(DCsAppleMilk.rum_young, DCsAppleMilk.rum);
		BrewingRecipe.registerRecipe(DCsAppleMilk.sake_young, DCsAppleMilk.sake);
		BrewingRecipe.registerRecipe(DCsAppleMilk.beer_young, DCsAppleMilk.beer);
		BrewingRecipe.registerRecipe(DCsAppleMilk.wine_young, DCsAppleMilk.wine);
	}
	
	public void addKelpRecipe()
	{
		 RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 9), true, null,
					new Object[]{"miso", "cropSeaWeed", "tofuKinu"});
		 
		 RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 9), true, null,
					new Object[]{"miso", "cropSeaWeed", "tofuMomen"});
		 
		 RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 10), true, null,
					new Object[]{"foodClam", "cropSeaWeed"});
		 
		 ArrayList<ItemStack> kelps = new ArrayList<ItemStack>();
		 
		 if (LoadModHandler.getArray("seaWeed") != null && !LoadModHandler.getArray("seaWeed").isEmpty()){
				kelps.addAll(LoadModHandler.getArray("seaWeed"));
			}
		 
		 for (ItemStack kelp : kelps)
			{
			 	RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 9), true, null,
						new Object[]{"miso", kelp, "tofuKinu"});
			 
			 	RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 9), true, null,
						new Object[]{"miso", kelp, "tofuMomen"});
				 
				RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 10), true, null,
							new Object[]{"foodClam", kelp});
			}
	}
	
	public static void registerPlate()
	{
		RecipeRegisterManager.plateRecipe.register(new ItemStack(Items.beef), 
				new ItemStack(DCsAppleMilk.foodPlate, 1, 0), 100, false);
		
		RecipeRegisterManager.plateRecipe.register(new ItemStack(Items.porkchop), 
				new ItemStack(DCsAppleMilk.foodPlate, 1, 1), 100, false);
		
		RecipeRegisterManager.plateRecipe.register(new ItemStack(Items.chicken), 
				new ItemStack(DCsAppleMilk.foodPlate, 1, 2), 100, true);
		
		RecipeRegisterManager.plateRecipe.register(new ItemStack(DCsAppleMilk.clam, 1, 0), 
				new ItemStack(DCsAppleMilk.foodPlate, 1, 3), 40, false);
		
		RecipeRegisterManager.plateRecipe.register(new ItemStack(Items.fish, 1, 0), 
				new ItemStack(Items.cooked_fished), 40, false);
		
		RecipeRegisterManager.plateRecipe.register(new ItemStack(Items.rotten_flesh, 1, 0), 
				new ItemStack(Items.leather), 40, false);
	}
	
	public static void testRecipe()
	{
		OreDictionary.registerOre("logYuzuWood", new ItemStack(DCsAppleMilk.logYuzu, 1, 0));
		
		RecipeRegisterManager.processorRecipe.addRecipe(new ItemStack(DCsAppleMilk.dustWood, 1, 0), false, null,
				new Object[]{"logYuzuWood"});
	}

}
