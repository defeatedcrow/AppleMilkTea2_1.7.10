package mods.defeatedcrow.common;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;

public class AchievementRegister {

	// 実績追加
	public static Achievement craftTeaMaker;
	public static Achievement craftPan;
	public static Achievement craftIceMaker;
	public static Achievement craftTeppan;
	public static Achievement craftAutoMaker;
	public static Achievement craftLogBox;
	public static Achievement craftGrater;
	public static Achievement craftChalcedony;
	public static Achievement craftChalGear;
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

	// AMT2追加分
	public static Achievement getYuzu;
	public static Achievement craftYuzuBattery;
	public static Achievement craftChargeableBat;
	public static Achievement craftEvp;
	public static Achievement craftBarrel;
	public static Achievement craftJaw;
	public static Achievement useIncense;
	public static Achievement craftTart;
	public static Achievement useSilkMelon;
	public static Achievement getPrincess;
	public static Achievement craftCharm;
	// public static Achievement getCordial;
	public static Achievement getAlcohol;

	static Achievement[] DCachievementsList;
	public static AchievementPage DCachievementPage;
	public static final String newAchievement = "Apple&Milk&Tea!";

	public static void register() {

		getTeaLeaves = (new Achievement("defeatedcrow.getTeaLeaves", "dc.getTeaLeaves", 0, 1, new ItemStack(
				DCsAppleMilk.leafTea, 1, 0), AchievementList.openInventory)).initIndependentStat().registerStat();

		makeTeaLeaves = (new Achievement("defeatedcrow.makeTealeaves", "dc.makeTeaLeaves", -2, 0, new ItemStack(
				DCsAppleMilk.foodTea, 1, 0), getTeaLeaves)).initIndependentStat().registerStat();

		craftTeaMaker = (new Achievement("defeatedcrow.teamaker", "dc.craftTeaMaker", -2, -2, new ItemStack(
				DCsAppleMilk.teaMakerNext, 1, 0), makeTeaLeaves)).initIndependentStat().registerStat();

		craftPan = (new Achievement("defeatedcrow.pan", "dc.craftPan", -2, 2, new ItemStack(
				DCsAppleMilk.emptyPanGaiden, 1, 0), AchievementList.buildFurnace)).initIndependentStat().registerStat();

		craftTeppan = (new Achievement("defeatedcrow.teppan", "dc.craftTeppan", -5, 4, new ItemStack(
				DCsAppleMilk.teppanII, 1, 0), AchievementList.acquireIron)).initIndependentStat().registerStat();

		craftIceMaker = (new Achievement("defeatedcrow.icemaker", "dc.craftIceMaker", 2, -2, new ItemStack(
				DCsAppleMilk.iceMaker, 1, 0), AchievementList.acquireIron)).initIndependentStat().registerStat();

		craftChalcedony = (new Achievement("defeatedcrow.chalcedony", "dc.craftChalcedony", 2, 2, new ItemStack(
				DCsAppleMilk.chalcedony, 1, 0), AchievementList.buildFurnace)).initIndependentStat().registerStat();

		craftLogBox = (new Achievement("defeatedcrow.logbox", "dc.craftLogbox", 0, 3, new ItemStack(
				DCsAppleMilk.woodBox, 1, 0), AchievementList.buildWorkBench)).initIndependentStat().registerStat();

		craftGrater = (new Achievement("defeatedcrow.grater", "dc.craftGrater", -4, 2, new ItemStack(
				DCsAppleMilk.DCgrater, 1, 0), craftPan)).registerStat();

		makeRice = (new Achievement("defeatedcrow.rice", "dc.makeRice", -6, 1, new ItemStack(
				DCsAppleMilk.emptyPanGaiden, 1, 0), craftGrater)).registerStat();

		getSoup = (new Achievement("defeatedcrow.getSoup", "dc.getSoup", -8, 1, new ItemStack(DCsAppleMilk.bowlBlock,
				1, 2), makeRice)).registerStat();

		eatChocoGift = (new Achievement("defeatedcrow.chocolateGift", "dc.eatChocoGift", -9, -1, new ItemStack(
				DCsAppleMilk.chocoBlock, 1, 1), makeRice)).setSpecial().registerStat();

		getTea = (new Achievement("defeatedcrow.getTea", "dc.getTea", -3, -4, new ItemStack(DCsAppleMilk.teacupBlock,
				1, 4), craftTeaMaker)).registerStat();

		getAppleMilkTea = (new Achievement("defeatedcrow.applemilktea", "dc.getAppleMilkTea", -4, -6, new ItemStack(
				DCsAppleMilk.teaCup2, 1, 3), getTea)).setSpecial().registerStat();

		getHamaguri = (new Achievement("defeatedcrow.hamaguri", "dc.getHamaguri", -7, 4, new ItemStack(
				DCsAppleMilk.foodPlate, 1, 3), craftTeppan)).registerStat();

		craftCharcoalContainer = (new Achievement("defeatedcrow.charcoalcontainer", "dc.craftCharcoalContainer", -2, 4,
				new ItemStack(DCsAppleMilk.charcoalBox, 1, 0), craftLogBox)).registerStat();

		craftVegiBag = (new Achievement("defeatedcrow.vegibag", "dc.craftVegiBag", 1, 5, new ItemStack(
				DCsAppleMilk.vegiBag, 1, 1), craftLogBox)).registerStat();

		crashMelon = (new Achievement("defeatedcrow.crashMelon", "dc.crashMelon", 1, 7, new ItemStack(
				DCsAppleMilk.melonBomb, 1, 0), craftVegiBag)).setSpecial().registerStat();

		eatIcecream = (new Achievement("defeatedcrow.icecream", "dc.eatIcecream", 3, -4, new ItemStack(
				DCsAppleMilk.blockIcecream, 1, 0), craftIceMaker)).setSpecial().registerStat();

		craftChalGear = (new Achievement("defeatedcrow.chalGear", "dc.craftChalGear", 4, 2, new ItemStack(
				DCsAppleMilk.EXItems, 1, 3), craftChalcedony)).registerStat();

		craftGlassLamp = (new Achievement("defeatedcrow.glasslamp", "dc.craftGlassLamp", 3, 4, new ItemStack(
				DCsAppleMilk.cLamp, 1, 5), craftChalcedony)).setSpecial().registerStat();

		craftAutoMaker = (new Achievement("defeatedcrow.craftProsessor", "dc.craftProcesser", -4, 0, new ItemStack(
				DCsAppleMilk.processor, 1, 0), craftGrater)).registerStat();

		burnOnTeppan = (new Achievement("defeatedcrow.teppanFire", "dc.burnOnTeppan", -6, 6, new ItemStack(Blocks.fire,
				1, 0), craftTeppan)).registerStat();

		getPrincess = (new Achievement("defeatedcrow.getPrincess", "dc.getPrincess", 5, 4, new ItemStack(
				DCsAppleMilk.princessClam, 1, 0), AchievementList.openInventory)).initIndependentStat().registerStat();

		getYuzu = (new Achievement("defeatedcrow.getYuzu", "dc.getYuzuCrop", 0, -1, new ItemStack(DCsAppleMilk.leafTea,
				1, 3), AchievementList.openInventory)).initIndependentStat().registerStat();

		craftYuzuBattery = (new Achievement("defeatedcrow.yuzuBattery", "dc.craftYuzuBattery", 0, -3, new ItemStack(
				DCsAppleMilk.batteryItem, 1, 2), getYuzu)).registerStat();

		craftChargeableBat = (new Achievement("defeatedcrow.chargeableBattery", "dc.chargeableBattery", 0, -5,
				new ItemStack(DCsAppleMilk.gelBat, 1, 0), craftYuzuBattery)).registerStat();

		craftBarrel = (new Achievement("defeatedcrow.craftBarrel", "dc.craftBarrel", 2, 0, new ItemStack(
				DCsAppleMilk.barrel, 1, 0), AchievementList.buildWorkBench)).initIndependentStat().registerStat();

		getAlcohol = (new Achievement("defeatedcrow.getAlcohol", "dc.getAlcohol", 4, 0, new ItemStack(
				DCsAppleMilk.itemLargeBottle, 1, 5), craftBarrel)).registerStat();

		craftEvp = (new Achievement("defeatedcrow.evaporator", "dc.craftEvaporator", 6, -1, new ItemStack(
				DCsAppleMilk.evaporator, 1, 0), getAlcohol)).registerStat();

		drinkCocktail = (new Achievement("defeatedcrow.drinkCocktail", "dc.drinkCocktail", 7, -3, new ItemStack(
				DCsAppleMilk.cocktail, 1, 0), getAlcohol)).setSpecial().registerStat();

		// getCordial = (new Achievement("defeatedcrow.getCordial", "getCordial", 5, -3, new
		// ItemStack(DCsAppleMilk.itemCordial, 1, 3), craftEvp))
		// .registerStat();

		craftJaw = (new Achievement("defeatedcrow.craftJaw", "dc.craftHyperJaw", 6, 1, new ItemStack(
				DCsAppleMilk.advProcessor, 1, 0), craftChalGear)).registerStat();

		craftTart = (new Achievement("defeatedcrow.craftTart", "dc.craftTart", -5, -3, new ItemStack(
				DCsAppleMilk.appleTart, 1, 0), AchievementList.openInventory)).initIndependentStat().registerStat();

		useSilkMelon = (new Achievement("defeatedcrow.useSilkMelon", "dc.explodeSilkMelon", -1, 7, new ItemStack(
				DCsAppleMilk.silkyMelon, 1, 0), crashMelon)).setSpecial().registerStat();

		craftCharm = (new Achievement("defeatedcrow.craftCharm", "dc.craftCharm", 7, 6, new ItemStack(
				DCsAppleMilk.princessClam, 1, 1), getPrincess)).setSpecial().registerStat();

		useIncense = (new Achievement("defeatedcrow.useIncense", "dc.useIncense", 9, 4, new ItemStack(
				DCsAppleMilk.incenseBase, 1, 0), craftCharm)).setSpecial().registerStat();

		DCachievementsList = new Achievement[] {
				getTeaLeaves,
				craftTeaMaker,
				craftPan,
				craftTeppan,
				craftIceMaker,
				craftChalcedony,
				craftLogBox,
				craftGrater,
				makeTeaLeaves,
				getSoup,
				eatChocoGift,
				getTea,
				getAppleMilkTea,
				getHamaguri,
				craftCharcoalContainer,
				craftVegiBag,
				crashMelon,
				eatIcecream,
				craftChalGear,
				craftGlassLamp,
				craftAutoMaker,
				drinkCocktail,
				burnOnTeppan,
				makeRice,
				getAlcohol,
				getPrincess,
				getYuzu,
				craftYuzuBattery,
				craftChargeableBat,
				craftBarrel,
				craftEvp,
				craftJaw,
				useIncense,
				craftTart,
				useSilkMelon,
				craftCharm };
		DCachievementPage = new AchievementPage(newAchievement, DCachievementsList);
		AchievementPage.registerAchievementPage(DCachievementPage);

		DCsAppleMilk.proxy.init();
	}

}
