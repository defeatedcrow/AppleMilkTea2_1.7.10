package mods.defeatedcrow.recipe;

import mods.defeatedcrow.api.*;
import mods.defeatedcrow.api.charge.ChargeItemManager;
import mods.defeatedcrow.api.recipe.*;
import mods.defeatedcrow.common.*;
import net.minecraft.block.Block;
import net.minecraft.init.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

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
	}
	
	public void registerIce()
	{
		RecipeRegisterManager.iceRecipe.register(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 0), new ItemStack(DCsAppleMilk.blockIcecream, 1, 0));
		
		RecipeRegisterManager.iceRecipe.register(new ItemStack(Blocks.gravel, 1, 0), new ItemStack(Items.flint, 1, 0));
		
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
	}
	
	public void registerProsessor()
	{
		//プロセッサー用の新規レシピ
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.foodTea, 2, 0), true, null,
				new Object[]{DCsAppleMilk.leafTea});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 2, 6), true, null,
				new Object[]{DCsAppleMilk.clam});
		
		//おろし金レシピの移植
		//茶葉
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 0), true, null,
				new Object[]{"cropApple"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 1), true, null,
				new Object[]{"cropPeach"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 1), true, null,
				new Object[]{"cropBanana"});
		
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 1), true, null,
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
	}
	
	public void registerEvaporator()
	{
		RecipeRegisterManager.evaporatorRecipe.addRecipe(null,
				new FluidStack(DCsAppleMilk.vegitableOil, 100), new ItemStack(DCsAppleMilk.leafTea, 1, 4));
	}

}
