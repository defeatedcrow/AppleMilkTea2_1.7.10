package mods.defeatedcrow.recipe;

import mods.defeatedcrow.api.*;
import mods.defeatedcrow.api.charge.ChargeItemManager;
import mods.defeatedcrow.api.recipe.*;
import mods.defeatedcrow.common.*;
import net.minecraft.block.Block;
import net.minecraft.init.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

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
	    
		RecipeRegisterManager.teaRecipe.registerCanMilk(new ItemStack(DCsAppleMilk.EXItems, 1, 2), new ItemStack(DCsAppleMilk.teacupBlock, 1, 4),
	    		new ItemStack(DCsAppleMilk.teacupBlock, 1, 5),
	    		new String("defeatedcrow:textures/blocks/contents_greentea.png"),
	    		new String("defeatedcrow:textures/blocks/contents_greentea_milk.png"));
	    
		RecipeRegisterManager.teaRecipe.registerCanMilk(new ItemStack(DCsAppleMilk.EXItems, 1, 3), new ItemStack(DCsAppleMilk.teacupBlock, 1, 2),
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
		RecipeRegisterManager.teaRecipe.registerCanMilk(new ItemStack(DCsAppleMilk.EXItems, 1, 8), new ItemStack(DCsAppleMilk.teaCup2, 1, 0),
	    		new ItemStack(DCsAppleMilk.teaCup2, 1, 1),
	    		new String("defeatedcrow:textures/blocks/contents_earlgray.png"),
	    		new String("defeatedcrow:textures/blocks/contents_tea_milk.png"));
	    
		RecipeRegisterManager.teaRecipe.registerCanMilk(new ItemStack(DCsAppleMilk.EXItems, 1, 9), new ItemStack(DCsAppleMilk.teaCup2, 1, 2),
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
		
		RecipeRegisterManager.iceRecipe.registerCanLeave(new ItemStack(Items.water_bucket, 1, 0), new ItemStack(DCsAppleMilk.EXItems, 1, 7),
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
		ChargeItemManager.chargeItem.registerCharger(new ItemStack(DCsAppleMilk.batteryItem, 1, 0),  null,  2000);
		ChargeItemManager.chargeItem.registerCharger(new ItemStack(DCsAppleMilk.batteryItem, 1, 1),  null,  5000);
		ChargeItemManager.chargeItem.registerCharger(new ItemStack(DCsAppleMilk.batteryItem, 1, 2),  null,  100);
	}
	
	public void registerProsessor()
	{
		RecipeRegisterManager.prosessorRecipe.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 1, 2),
				new Object[]{DCsAppleMilk.leafTea});
		
	}

}
