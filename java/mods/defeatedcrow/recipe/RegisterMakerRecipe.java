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
		RecipeRegisterManager.teaRecipe.registerCanMilk(new ItemStack(DCsAppleMilk.foodTea, 1, 0), new ItemStack(DCsAppleMilk.teaCup2, 1, 0),
	    		new ItemStack(DCsAppleMilk.teaCup2, 1, 1),
	    		new String("defeatedcrow:textures/blocks/contents_earlgray.png"),
	    		new String("defeatedcrow:textures/blocks/contents_tea_milk.png"));
	    
		RecipeRegisterManager.teaRecipe.registerCanMilk(new ItemStack(DCsAppleMilk.foodTea, 1, 1), new ItemStack(DCsAppleMilk.teaCup2, 1, 2),
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
	
	public void registerProsessor()
	{
		//プロセッサー用の新規レシピ
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.foodTea, 2, 0), true, null,
				new Object[]{DCsAppleMilk.leafTea});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 2, 6), true, null,
				new Object[]{DCsAppleMilk.clam});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.yeast, 1, 0), true, new ItemStack(Items.bowl),
				new Object[]{"cropApple", Items.sugar, DCsAppleMilk.bowlBlock});
		
		//おろし金レシピの移植
		//茶葉
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 0), true, null,
				new Object[]{"cropApple"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 1), true, null,
				new Object[]{"cropPeach"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 1), true, null,
				new Object[]{"cropBanana"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 9), true, null,
				new Object[]{"cropOrange"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 1), true, null,
				new Object[]{"cropPlum"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 2), true, null,
				new Object[]{"cropLemon","dropHoney"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 3), true, null,
				new Object[]{"cropCoffee"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 5), true, null,
				new Object[]{"cropLime"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 5), true, null,
				new Object[]{"cropCitron"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 6), true, null,
				new Object[]{"cropTomato"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 6), true, null,
				new Object[]{"tomato"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), true, null,
				new Object[]{"cropStrawberry"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), true, null,
				new Object[]{"cropBlueberry"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), true, null,
				new Object[]{"cropRaspberry"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), true, null,
				new Object[]{"cropBlackberry"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), true, null,
				new Object[]{"cropCassis"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 8), true, null,
				new Object[]{"cropGrape"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 8), true, null,
				new Object[]{"grape"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.foodTea, 1, 2), true, null,
				new Object[]{"cropOrange", new ItemStack(DCsAppleMilk.foodTea, 1, 1)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.foodTea, 1, 2), true, null,
				new Object[]{"cropCitron", new ItemStack(DCsAppleMilk.foodTea, 1, 1)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.foodTea, 1, 3), true, null,
				new Object[]{"cropApple", new ItemStack(DCsAppleMilk.foodTea, 1, 1)});
		
		//鍋材料
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 0), true, null,
				new Object[]{Blocks.brown_mushroom, Blocks.red_mushroom});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 1), true, new ItemStack(Items.bucket),
				new Object[]{new ItemStack(Items.fish, 1, 0), Items.carrot, Items.potato, "bucketMilk"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 1), true, new ItemStack(Items.bucket),
				new Object[]{new ItemStack(Items.fish, 1, 1), Items.carrot, Items.potato, "bucketMilk"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 2), true, null,
				new Object[]{Items.egg, Items.chicken, "cropRice"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 2), true, null,
				new Object[]{Items.egg, Items.chicken, "cropWheat"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 3), true, null,
				new Object[]{"cropRice"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 3), true, null,
				new Object[]{"cropWheat"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 4), true, null,
				new Object[]{new ItemStack(DCsAppleMilk.EXItems, 1, 2), "cropRice"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 4), true, null,
				new Object[]{new ItemStack(DCsAppleMilk.EXItems, 1, 2), "cropWheat"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 5), true, new ItemStack(Items.bucket),
				new Object[]{Items.chicken, "leek", "bucketSoymilk", "tofuKinu"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 5), true, new ItemStack(Items.bucket),
				new Object[]{Items.chicken, "leek", "bucketSoymilk", "tofuMomen"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 5), true, null,
				new Object[]{Items.chicken, "cropLeek", "foodSoymilk", "foodSilkentofu"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 6), true, new ItemStack(Items.bucket),
				new Object[]{Blocks.pumpkin, "bucketMilk"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 7), true, null,
				new Object[]{Items.porkchop, "cropLettus", "cropTomato"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 7), true, null,
				new Object[]{Items.porkchop, "cabbage", "tomato"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 8), true, new ItemStack(Items.bucket),
				new Object[]{new ItemStack(Items.dye,1,3), new ItemStack(Items.dye,1,3), "bucketMilk", "dustSugar"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 4), true, new ItemStack(Items.bucket),
				new Object[]{new ItemStack(DCsAppleMilk.mincedFoods, 1 ,8), "bucketMilk"});
		
		//砕石機
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(Items.flint), false, new ItemStack(Blocks.sand),
				new Object[]{new ItemStack(Blocks.gravel)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(Items.clay_ball), false, null,
				new Object[]{new ItemStack(Blocks.sand)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(Blocks.sand), false, new ItemStack(Items.rotten_flesh),
				new Object[]{new ItemStack(Blocks.soul_sand)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(Blocks.dirt), false, null,
				new Object[]{"dustAsh", "dustOilCake", new ItemStack(Items.dye, 1, 15), new ItemStack(Blocks.sand)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(Blocks.dirt), false, null,
				new Object[]{"dustAsh", "dustOilCake", new ItemStack(DCsAppleMilk.EXItems, 1, 6), new ItemStack(Blocks.sand)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.dustWood, 1, 0), false, null,
				new Object[]{"logWood"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.dustWood, 1, 1), false, null,
				new Object[]{new ItemStack(Items.coal, 1, 1)});
		
		//ツール還元
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 24, 7), false, null,
				new Object[]{new ItemStack(Items.iron_axe, 1, 32767)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 16, 7), false, null,
				new Object[]{new ItemStack(Items.iron_hoe, 1, 32767)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 24, 7), false, null,
				new Object[]{new ItemStack(Items.iron_pickaxe, 1, 32767)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 8, 7), false, null,
				new Object[]{new ItemStack(Items.iron_shovel, 1, 32767)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 16, 7), false, null,
				new Object[]{new ItemStack(Items.iron_sword, 1, 32767)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 32, 7), false, null,
				new Object[]{new ItemStack(Items.iron_boots, 1, 32767)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 64, 7), false, null,
				new Object[]{new ItemStack(Items.iron_chestplate, 1, 32767)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 40, 7), false, null,
				new Object[]{new ItemStack(Items.iron_helmet, 1, 32767)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 56, 7), false, null,
				new Object[]{new ItemStack(Items.iron_leggings, 1, 32767)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(Items.gold_nugget, 16, 0), false, null,
				new Object[]{new ItemStack(Items.golden_hoe, 1, 32767)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(Items.gold_nugget, 24, 0), false, null,
				new Object[]{new ItemStack(Items.golden_pickaxe, 1, 32767)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(Items.gold_nugget, 8, 0), false, null,
				new Object[]{new ItemStack(Items.golden_shovel, 1, 32767)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(Items.gold_nugget, 16, 0), false, null,
				new Object[]{new ItemStack(Items.golden_sword, 1, 32767)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(Items.gold_nugget, 32, 0), false, null,
				new Object[]{new ItemStack(Items.golden_boots, 1, 32767)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(Items.gold_nugget, 64, 0), false, null,
				new Object[]{new ItemStack(Items.golden_chestplate, 1, 32767)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(Items.gold_nugget, 40, 0), false, null,
				new Object[]{new ItemStack(Items.golden_helmet, 1, 32767)});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(Items.gold_nugget, 56, 0), false, null,
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
		BrewingRecipe.registerRecipe(DCsAppleMilk.sake_young, DCsAppleMilk.sake);
		BrewingRecipe.registerRecipe(DCsAppleMilk.beer_young, DCsAppleMilk.beer);
		BrewingRecipe.registerRecipe(DCsAppleMilk.wine_young, DCsAppleMilk.wine);
	}
	
	public void addKelpRecipe()
	{
		 RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 9), true, null,
					new Object[]{"miso", "cropSeaWeed", "tofuKinu"});
		 
		 RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 9), true, null,
					new Object[]{"miso", "cropSeaWeed", "tofuMomen"});
		 
		 RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 10), true, null,
					new Object[]{"foodClam", "cropSeaWeed"});
		 
		 ArrayList<ItemStack> kelps = new ArrayList<ItemStack>();
		 
		 if (LoadModHandler.getArray("seaWeed") != null && !LoadModHandler.getArray("seaWeed").isEmpty()){
				kelps.addAll(LoadModHandler.getArray("seaWeed"));
			}
		 
		 for (ItemStack kelp : kelps)
			{
			 	RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 9), true, null,
						new Object[]{"miso", kelp, "tofuKinu"});
			 
			 	RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 9), true, null,
						new Object[]{"miso", kelp, "tofuMomen"});
				 
				RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 10), true, null,
							new Object[]{"foodClam", kelp});
			}
	}

}
