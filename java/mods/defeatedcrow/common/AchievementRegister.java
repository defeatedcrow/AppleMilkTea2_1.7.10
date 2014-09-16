package mods.defeatedcrow.common;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;

public class AchievementRegister {
	
	//実績追加
	public static Achievement craftTeaMaker;
	public static Achievement craftPan;
	public static Achievement craftIceMaker;
	public static Achievement craftTeppan;
	public static Achievement craftAutoMaker;
	public static Achievement craftLogBox;
	public static Achievement craftGrater;
	public static Achievement craftChalcedony;
	public static Achievement craftChalKnife;
	public static Achievement craftGlassLamp;
	public static Achievement getTeaLeaves;
	public static Achievement makeTeaLeaves;
	public static Achievement getTea;
	public static Achievement getAppleMilkTea;
	public static Achievement getSoup;
	public static Achievement getHamaguri;
	public static Achievement eatChocoGift;
	public static Achievement eatIcecream;
	public static Achievement crashMelon;
	public static Achievement craftCharcoalContainer;
	public static Achievement craftVegiBag;
	public static Achievement drinkCocktail;
	public static Achievement burnOnTeppan;
	public static Achievement makeRice;
	
	static Achievement[] DCachievementsList;
	public static AchievementPage DCachievementPage;
	public static final String newAchievement = "Apple&Milk&Tea!";
	
	
	
	public static void register()
	{
		
		getTeaLeaves = (new Achievement("defeatedcrow.getTeaLeaves", "getTeaLeaves", 0, 1, new ItemStack(DCsAppleMilk.leafTea, 1, 0), AchievementList.openInventory))
				.initIndependentStat().registerStat();
 
		
		craftTeaMaker = (new Achievement("defeatedcrow.teamaker", "craftTeaMaker", 0, -1, new ItemStack(DCsAppleMilk.teaMakerNext, 1, 0), AchievementList.blazeRod))
				.initIndependentStat().registerStat();
		
		craftPan = (new Achievement("defeatedcrow.pan", "craftPan", -2, 0, new ItemStack(DCsAppleMilk.emptyPanGaiden, 1, 0), AchievementList.buildFurnace))
				.initIndependentStat().registerStat();
		
		craftTeppan = (new Achievement("defeatedcrow.teppan", "craftTeppan", -2, 2, new ItemStack(DCsAppleMilk.teppann, 1, 0), AchievementList.acquireIron))
				.initIndependentStat().registerStat();
		
		craftIceMaker = (new Achievement("defeatedcrow.icemaker", "craftIceMaker", 2, 0, new ItemStack(DCsAppleMilk.iceMaker, 1, 0), AchievementList.acquireIron))
				.initIndependentStat().registerStat();
		
		craftChalcedony = (new Achievement("defeatedcrow.chalcedony", "craftChalcedony", 2, 2, new ItemStack(DCsAppleMilk.chalcedony, 1, 0), AchievementList.buildFurnace))
				.initIndependentStat().registerStat();
		
		craftLogBox = (new Achievement("defeatedcrow.logbox", "craftLogbox", 0, 3, new ItemStack(DCsAppleMilk.woodBox, 1, 0), AchievementList.buildWorkBench))
				.initIndependentStat().registerStat();
		
		craftGrater = (new Achievement("defeatedcrow.grater", "craftGrater", -3, -2, new ItemStack(DCsAppleMilk.DCgrater, 1, 0), craftPan))
				.registerStat();
		
		makeTeaLeaves = (new Achievement("defeatedcrow.makeTealeaves", "makeTeaLeaves", -1, -3, new ItemStack(DCsAppleMilk.foodTea, 1, 0), getTeaLeaves))
				.initIndependentStat().registerStat();
		
		makeRice = (new Achievement("defeatedcrow.rice", "makeRice", -5, 0, new ItemStack(DCsAppleMilk.emptyPanGaiden, 1, 0), craftGrater))
				.registerStat();
		
		getSoup = (new Achievement("defeatedcrow.getSoup", "getSoup", -7, -2, new ItemStack(DCsAppleMilk.bowlBlock, 1, 2), makeRice))
				.registerStat();
		
		eatChocoGift = (new Achievement("defeatedcrow.chocolateGift", "eatChocoGift", -8, 1, new ItemStack(DCsAppleMilk.chocoBlock, 1, 1), makeRice))
				.setSpecial().registerStat();
		
		getTea = (new Achievement("defeatedcrow.getTea", "getTea", 1, -3, new ItemStack(DCsAppleMilk.teacupBlock, 1, 2), craftTeaMaker))
				.registerStat();
		
		getAppleMilkTea = (new Achievement("defeatedcrow.applemilktea", "getAppleMilkTea", 0, -5, new ItemStack(DCsAppleMilk.teaCup2, 1, 3), getTea))
				.setSpecial().registerStat();
		
		getHamaguri = (new Achievement("defeatedcrow.hamaguri", "getHamaguri", -3, 4, new ItemStack(DCsAppleMilk.foodPlate, 1, 3), craftTeppan))
				.registerStat();
		
		craftCharcoalContainer = (new Achievement("defeatedcrow.charcoalcontainer", "craftCharcoalContainer", -1, 5, new ItemStack(DCsAppleMilk.charcoalBox, 1, 0), craftLogBox))
				.registerStat();
		
		craftVegiBag = (new Achievement("defeatedcrow.vegibag", "craftVegiBag", 1, 5, new ItemStack(DCsAppleMilk.vegiBag, 1, 1), craftLogBox))
				.registerStat();
		
		crashMelon = (new Achievement("defeatedcrow.crashMelon", "crashMelon", 1, 8, new ItemStack(DCsAppleMilk.melonBomb, 1, 0), craftVegiBag))
				.setSpecial().registerStat();
		
		eatIcecream = (new Achievement("defeatedcrow.icecream", "eatIcecream", 5, -1, new ItemStack(DCsAppleMilk.blockIcecream, 1, 0), craftIceMaker))
				.setSpecial().registerStat();
		
		craftChalKnife = (new Achievement("defeatedcrow.chalKnife", "craftChalKnife", 5, 2, new ItemStack(DCsAppleMilk.chalcedonyKnife, 1, 0), craftChalcedony))
				.registerStat();
		
		craftGlassLamp = (new Achievement("defeatedcrow.glasslamp", "craftGlassLamp", 4, 4, new ItemStack(DCsAppleMilk.cLamp, 1, 3), craftChalcedony))
				.setSpecial().registerStat();
		
		craftAutoMaker = (new Achievement("defeatedcrow.prosessor", "craftAutoMaker", 7, 3, new ItemStack(DCsAppleMilk.prosessor, 1, 0), craftChalKnife))
				.registerStat();
		
		drinkCocktail = (new Achievement("defeatedcrow.cocktail", "drinkCocktail", 3, -2, new ItemStack(DCsAppleMilk.cocktail, 1, 0), craftIceMaker))
				.registerStat();
		
		burnOnTeppan = (new Achievement("defeatedcrow.teppanFire", "burnOnTeppan", -4, 2, new ItemStack(Blocks.fire, 1, 0), craftTeppan))
				.registerStat();
		
		DCachievementsList = new Achievement[] { getTeaLeaves, craftTeaMaker, craftPan, craftTeppan, craftIceMaker, craftChalcedony, craftLogBox, craftGrater,
				makeTeaLeaves, getSoup, eatChocoGift, getTea, getAppleMilkTea, getHamaguri, craftCharcoalContainer, craftVegiBag, crashMelon, eatIcecream,
				craftChalKnife, craftGlassLamp, craftAutoMaker, drinkCocktail, burnOnTeppan, makeRice};
		DCachievementPage = new AchievementPage(newAchievement, DCachievementsList);
		AchievementPage.registerAchievementPage(DCachievementPage);
		
		DCsAppleMilk.proxy.init();
	}

}
