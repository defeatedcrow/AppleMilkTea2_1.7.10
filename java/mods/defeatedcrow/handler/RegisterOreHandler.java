package mods.defeatedcrow.handler;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RegisterOreHandler {

	public void register() {
		OreDictionary.registerOre("dyeBlack", new ItemStack(DCsAppleMilk.inkStick));
		OreDictionary.registerOre("dyeOrange", new ItemStack(DCsAppleMilk.foodTea, 1, 4));
		OreDictionary.registerOre("dyeWhite", new ItemStack(DCsAppleMilk.EXItems, 1, 6));
		OreDictionary.registerOre("treeSapling", new ItemStack(DCsAppleMilk.saplingTea, 1, 0));
		OreDictionary.registerOre("treeSapling", new ItemStack(DCsAppleMilk.saplingTea, 1, 1));
		OreDictionary.registerOre("treeSapling", new ItemStack(DCsAppleMilk.saplingTea, 1, 2));
		OreDictionary.registerOre("treeSapling", new ItemStack(DCsAppleMilk.saplingYuzu, 1, 0));
		OreDictionary.registerOre("logWood", new ItemStack(DCsAppleMilk.logYuzu, 1, 0));
		OreDictionary.registerOre("treeLeaves", new ItemStack(DCsAppleMilk.leavesYuzu, 1, 32767));
		OreDictionary.registerOre("saplingTea", new ItemStack(DCsAppleMilk.saplingTea, 1, 0));
		OreDictionary.registerOre("saplingCassis", new ItemStack(DCsAppleMilk.saplingTea, 1, 1));
		OreDictionary.registerOre("saplingCamellia", new ItemStack(DCsAppleMilk.saplingTea, 1, 2));
		OreDictionary.registerOre("saplingYuzu", new ItemStack(DCsAppleMilk.saplingYuzu, 1, 0));
		OreDictionary.registerOre("foodClam", new ItemStack(DCsAppleMilk.clam, 1, 0));
		OreDictionary.registerOre("cookingClam", new ItemStack(DCsAppleMilk.clam, 1, 0));
		OreDictionary.registerOre("cropTea", new ItemStack(DCsAppleMilk.leafTea, 1, 0));
		OreDictionary.registerOre("cropSpiceleaf", new ItemStack(DCsAppleMilk.leafTea, 1, 1));
		OreDictionary.registerOre("cropCassis", new ItemStack(DCsAppleMilk.leafTea, 1, 2));
		OreDictionary.registerOre("cropYuzu", new ItemStack(DCsAppleMilk.leafTea, 1, 3));
		OreDictionary.registerOre("cropCitron", new ItemStack(DCsAppleMilk.leafTea, 1, 3));
		OreDictionary.registerOre("cropCamellia", new ItemStack(DCsAppleMilk.leafTea, 1, 4));
		OreDictionary.registerOre("foodCondencedMilk", new ItemStack(DCsAppleMilk.condensedMIlk, 1, 0));
		OreDictionary.registerOre("foodCassisPreserve", new ItemStack(DCsAppleMilk.condensedMIlk, 1, 1));
		OreDictionary.registerOre("foodMintSauce", new ItemStack(DCsAppleMilk.condensedMIlk, 1, 2));
		OreDictionary.registerOre("foodYuzuMarmalade", new ItemStack(DCsAppleMilk.condensedMIlk, 1, 3));
		OreDictionary.registerOre("foodGreenTea", new ItemStack(DCsAppleMilk.foodTea, 1, 0));
		OreDictionary.registerOre("foodTea", new ItemStack(DCsAppleMilk.foodTea, 1, 1));
		OreDictionary.registerOre("foodEarlGray", new ItemStack(DCsAppleMilk.foodTea, 1, 2));
		OreDictionary.registerOre("foodAppletea", new ItemStack(DCsAppleMilk.foodTea, 1, 3));
		OreDictionary.registerOre("foodCoffee", new ItemStack(DCsAppleMilk.gratedApple, 1, 3));
		OreDictionary.registerOre("cropRice", new ItemStack(DCsAppleMilk.mincedFoods, 1, 3));
		OreDictionary.registerOre("cookingRice", new ItemStack(DCsAppleMilk.bowlBlock, 1, 0));
		OreDictionary.registerOre("cookingRice", new ItemStack(DCsAppleMilk.bowlJP, 1, 0));
		OreDictionary.registerOre("foodYeast", new ItemStack(DCsAppleMilk.yeast, 1, 0));
		OreDictionary.registerOre("bucketCamelliaOil", new ItemStack(DCsAppleMilk.bucketCamOil, 1, 0));
		OreDictionary.registerOre("bucketVegitableOil", new ItemStack(DCsAppleMilk.bucketVegiOil, 1, 0));
		OreDictionary.registerOre("bottleCamelliaOil", new ItemStack(DCsAppleMilk.bottleCamOil, 1, 0));
		OreDictionary.registerOre("bottleVegitableOil", new ItemStack(DCsAppleMilk.bottleVegiOil, 1, 0));

		OreDictionary.registerOre("foodGratedApple", new ItemStack(DCsAppleMilk.gratedApple, 1, 0));
		OreDictionary.registerOre("foodGratedFruit", new ItemStack(DCsAppleMilk.gratedApple, 1, 1));
		OreDictionary.registerOre("foodHoneyLemon", new ItemStack(DCsAppleMilk.gratedApple, 1, 2));
		OreDictionary.registerOre("foodGratedLime", new ItemStack(DCsAppleMilk.gratedApple, 1, 5));
		OreDictionary.registerOre("foodGratedTomato", new ItemStack(DCsAppleMilk.gratedApple, 1, 6));
		OreDictionary.registerOre("foodGratedBerry", new ItemStack(DCsAppleMilk.gratedApple, 1, 7));
		OreDictionary.registerOre("foodGratedGrape", new ItemStack(DCsAppleMilk.gratedApple, 1, 8));
		OreDictionary.registerOre("foodGratedOrange", new ItemStack(DCsAppleMilk.gratedApple, 1, 9));
		OreDictionary.registerOre("foodGratedChocolate", new ItemStack(DCsAppleMilk.mincedFoods, 1, 8));

		OreDictionary.registerOre("foodBlockMilk", new ItemStack(DCsAppleMilk.teacupBlock, 1, 1));
		OreDictionary.registerOre("foodBlockGreentea", new ItemStack(DCsAppleMilk.teacupBlock, 1, 2));
		OreDictionary.registerOre("foodBlockMilkGrenntea", new ItemStack(DCsAppleMilk.teacupBlock, 1, 3));
		OreDictionary.registerOre("foodBlockTea", new ItemStack(DCsAppleMilk.teacupBlock, 1, 4));
		OreDictionary.registerOre("foodBlockMilkTea", new ItemStack(DCsAppleMilk.teacupBlock, 1, 5));
		OreDictionary.registerOre("foodBlockCocoa", new ItemStack(DCsAppleMilk.teacupBlock, 1, 6));
		OreDictionary.registerOre("foodBlockMilkCocoa", new ItemStack(DCsAppleMilk.teacupBlock, 1, 7));
		OreDictionary.registerOre("foodBlockJuice", new ItemStack(DCsAppleMilk.teacupBlock, 1, 8));
		OreDictionary.registerOre("foodBlockMilkJuice", new ItemStack(DCsAppleMilk.teacupBlock, 1, 9));
		OreDictionary.registerOre("foodBlockLemonade", new ItemStack(DCsAppleMilk.teacupBlock, 1, 10));
		OreDictionary.registerOre("foodBlockMilkLamonade", new ItemStack(DCsAppleMilk.teacupBlock, 1, 11));
		OreDictionary.registerOre("foodBlockCoffee", new ItemStack(DCsAppleMilk.teacupBlock, 1, 12));
		OreDictionary.registerOre("foodBlockMilkCoffee", new ItemStack(DCsAppleMilk.teacupBlock, 1, 13));
		OreDictionary.registerOre("foodBlockEarlGray", new ItemStack(DCsAppleMilk.teaCup2, 1, 0));
		OreDictionary.registerOre("foodBlockMilkEarlGray", new ItemStack(DCsAppleMilk.teaCup2, 1, 1));
		OreDictionary.registerOre("foodBlockAppletea", new ItemStack(DCsAppleMilk.teaCup2, 1, 2));
		OreDictionary.registerOre("foodBlockMilkAppletea", new ItemStack(DCsAppleMilk.teaCup2, 1, 3));
		OreDictionary.registerOre("foodBlockLimejuice", new ItemStack(DCsAppleMilk.teaCup2, 1, 4));
		OreDictionary.registerOre("foodBlockTomatojuice", new ItemStack(DCsAppleMilk.teaCup2, 1, 5));
		OreDictionary.registerOre("foodBlockMilkBerry", new ItemStack(DCsAppleMilk.teaCup2, 1, 7));
		OreDictionary.registerOre("foodBlockGrapejuice", new ItemStack(DCsAppleMilk.teaCup2, 1, 8));
		OreDictionary.registerOre("foodBlockMintTea", new ItemStack(DCsAppleMilk.teaCup2, 1, 9));
		OreDictionary.registerOre("foodBlockYuzuDrink", new ItemStack(DCsAppleMilk.teaCup2, 1, 10));
		OreDictionary.registerOre("foodBlockOrangejuice", new ItemStack(DCsAppleMilk.teaCup2, 1, 11));
		OreDictionary.registerOre("foodBlockSoda", new ItemStack(DCsAppleMilk.teaCup2, 1, 12));

		OreDictionary.registerOre("foodBlockRice", new ItemStack(DCsAppleMilk.bowlBlock, 1, 0));
		OreDictionary.registerOre("foodBlockMushroomstew", new ItemStack(DCsAppleMilk.bowlBlock, 1, 1));
		OreDictionary.registerOre("foodBlockSalmonstew", new ItemStack(DCsAppleMilk.bowlBlock, 1, 2));
		OreDictionary.registerOre("foodBlockZousui", new ItemStack(DCsAppleMilk.bowlBlock, 1, 3));
		OreDictionary.registerOre("foodBlockKayakumeshi", new ItemStack(DCsAppleMilk.bowlBlock, 1, 4));
		OreDictionary.registerOre("foodBlockTofunabe", new ItemStack(DCsAppleMilk.bowlBlock, 1, 5));
		OreDictionary.registerOre("foodBlockPupmkinsoup", new ItemStack(DCsAppleMilk.bowlBlock, 1, 6));
		OreDictionary.registerOre("foodBlockBLTsoup", new ItemStack(DCsAppleMilk.bowlBlock, 1, 7));

		OreDictionary.registerOre("foodBlockSteak", new ItemStack(DCsAppleMilk.foodPlate, 1, 0));
		OreDictionary.registerOre("foodBlockTonteki", new ItemStack(DCsAppleMilk.foodPlate, 1, 1));
		OreDictionary.registerOre("foodBlockChicken", new ItemStack(DCsAppleMilk.foodPlate, 1, 2));
		OreDictionary.registerOre("foodBlockClam", new ItemStack(DCsAppleMilk.foodPlate, 1, 3));

		OreDictionary.registerOre("foodToffyApple", new ItemStack(DCsAppleMilk.toffyApple));
		OreDictionary.registerOre("foodAppleTart", new ItemStack(DCsAppleMilk.appleTart, 1, 0));
		OreDictionary.registerOre("foodCassisTart", new ItemStack(DCsAppleMilk.appleTart, 1, 1));
		OreDictionary.registerOre("foodYuzuCake", new ItemStack(DCsAppleMilk.appleTart, 1, 2));
		OreDictionary.registerOre("foodApricotCake", new ItemStack(DCsAppleMilk.appleTart, 1, 3));
		OreDictionary.registerOre("foodBakedApple", new ItemStack(DCsAppleMilk.bakedApple));
		OreDictionary.registerOre("foodAppleSandwich", new ItemStack(DCsAppleMilk.appleSandwich, 1, 0));
		OreDictionary.registerOre("foodEggSandwich", new ItemStack(DCsAppleMilk.appleSandwich, 1, 1));
		OreDictionary.registerOre("foodCassisSandwich", new ItemStack(DCsAppleMilk.appleSandwich, 1, 2));
		OreDictionary.registerOre("foodYuzuSandwich", new ItemStack(DCsAppleMilk.appleSandwich, 1, 3));
		OreDictionary.registerOre("foodCookedClam", new ItemStack(DCsAppleMilk.clam, 1, 1));
		OreDictionary.registerOre("cookingRice", new ItemStack(DCsAppleMilk.bowlJP, 1, 0));

		OreDictionary.registerOre("blockChalcedony", new ItemStack(DCsAppleMilk.chalcedony, 1, 0));
		OreDictionary.registerOre("blockChalcedony", new ItemStack(DCsAppleMilk.chalcedony, 1, 1));
		OreDictionary.registerOre("blockChalcedony", new ItemStack(DCsAppleMilk.chalcedony, 1, 2));
		OreDictionary.registerOre("blockChalcedony", new ItemStack(DCsAppleMilk.chalcedony, 1, 3));

		OreDictionary.registerOre("toolGrater", new ItemStack(DCsAppleMilk.DCgrater, 1, 32767));
		OreDictionary.registerOre("toolChaicedonyHammer", new ItemStack(DCsAppleMilk.chalcedonyHammer, 1, 32767));
		OreDictionary.registerOre("toolChaicedonyKnife", new ItemStack(DCsAppleMilk.chalcedonyKnife, 1, 32767));
		OreDictionary.registerOre("toolChaicedonyShears", new ItemStack(DCsAppleMilk.pruningShears, 1, 32767));
		OreDictionary.registerOre("toolFirestarter", new ItemStack(DCsAppleMilk.firestarter, 1, 32767));
		OreDictionary.registerOre("toolGrater", new ItemStack(DCsAppleMilk.DCgrater, 1, 32767));
		OreDictionary.registerOre("blockTeaMaker", new ItemStack(DCsAppleMilk.teaMakerNext));
		OreDictionary.registerOre("blockIceMaker", new ItemStack(DCsAppleMilk.iceMaker));
		OreDictionary.registerOre("blockEmptyCup", new ItemStack(DCsAppleMilk.emptyCup));
		OreDictionary.registerOre("blockEmptyPan", new ItemStack(DCsAppleMilk.emptyPanGaiden));
		OreDictionary.registerOre("blockEvaporator", new ItemStack(DCsAppleMilk.evaporator));
		OreDictionary.registerOre("blockProsessor", new ItemStack(DCsAppleMilk.processor));

		OreDictionary.registerOre("gearIron", new ItemStack(DCsAppleMilk.EXItems, 1, 3));
		OreDictionary.registerOre("gearChalcedony", new ItemStack(DCsAppleMilk.EXItems, 1, 3));
		OreDictionary.registerOre("foodBlackEgg", new ItemStack(DCsAppleMilk.clam, 1, 3));
		OreDictionary.registerOre("gemIce", new ItemStack(DCsAppleMilk.icyCrystal, 1, 0));
		OreDictionary.registerOre("foodCrushedIce", new ItemStack(DCsAppleMilk.EXItems, 1, 4));
		OreDictionary.registerOre("cookingIce", new ItemStack(DCsAppleMilk.EXItems, 1, 4));
		OreDictionary.registerOre("dustGlass", new ItemStack(DCsAppleMilk.EXItems, 1, 5));
		OreDictionary.registerOre("nuggetIron", new ItemStack(DCsAppleMilk.EXItems, 1, 7));
		OreDictionary.registerOre("nuggetTin", new ItemStack(DCsAppleMilk.EXItems, 1, 8));
		OreDictionary.registerOre("nuggetCopper", new ItemStack(DCsAppleMilk.EXItems, 1, 9));
		OreDictionary.registerOre("nuggetSilver", new ItemStack(DCsAppleMilk.EXItems, 1, 10));
		OreDictionary.registerOre("nuggetSteel", new ItemStack(DCsAppleMilk.EXItems, 1, 11));
		OreDictionary.registerOre("nuggetLead", new ItemStack(DCsAppleMilk.EXItems, 1, 12));
		OreDictionary.registerOre("nuggetBronze", new ItemStack(DCsAppleMilk.EXItems, 1, 13));
		OreDictionary.registerOre("nuggetFlint", new ItemStack(DCsAppleMilk.EXItems, 1, 14));
		OreDictionary.registerOre("dustWood", new ItemStack(DCsAppleMilk.dustWood, 1, 0));
		OreDictionary.registerOre("dustCharcoal", new ItemStack(DCsAppleMilk.dustWood, 1, 1));
		OreDictionary.registerOre("dustCoal", new ItemStack(DCsAppleMilk.dustWood, 1, 1));
		OreDictionary.registerOre("dustAsh", new ItemStack(DCsAppleMilk.dustWood, 1, 2));
		OreDictionary.registerOre("dustOilCake", new ItemStack(DCsAppleMilk.dustWood, 1, 3));
		OreDictionary.registerOre("stickCarbon", new ItemStack(DCsAppleMilk.stickCarbon, 1, 0));
		OreDictionary.registerOre("plateChocolate", new ItemStack(DCsAppleMilk.chocolateFruits, 1, 13));

		OreDictionary.registerOre("dustIron", new ItemStack(DCsAppleMilk.oreDust, 1, 0));
		OreDictionary.registerOre("dustTin", new ItemStack(DCsAppleMilk.oreDust, 1, 1));
		OreDictionary.registerOre("dustCopper", new ItemStack(DCsAppleMilk.oreDust, 1, 2));
		OreDictionary.registerOre("dustSilver", new ItemStack(DCsAppleMilk.oreDust, 1, 3));
		OreDictionary.registerOre("dustLead", new ItemStack(DCsAppleMilk.oreDust, 1, 4));
		OreDictionary.registerOre("dustGold", new ItemStack(DCsAppleMilk.oreDust, 1, 5));
		OreDictionary.registerOre("dustNickel", new ItemStack(DCsAppleMilk.oreDust, 1, 6));
		OreDictionary.registerOre("dustPlatinum", new ItemStack(DCsAppleMilk.oreDust, 1, 7));

		OreDictionary.registerOre("blockClam", new ItemStack(DCsAppleMilk.gunpowderContainer, 1, 3));
		OreDictionary.registerOre("gemClam", new ItemStack(DCsAppleMilk.princessClam, 1, 0));
		OreDictionary.registerOre("dustClam", new ItemStack(DCsAppleMilk.EXItems, 1, 6));
		OreDictionary.registerOre("blockClamSand", new ItemStack(DCsAppleMilk.clamSand, 1, 0));

		OreDictionary.registerOre("gemClam", new ItemStack(DCsAppleMilk.princessClam, 1, 0));

		if (DCsConfig.allowSlimeBallDic) {
			OreDictionary.registerOre("slimeball", new ItemStack(DCsAppleMilk.EXItems, 1, 1));
		}

		for (int i = 0; i < 13; i++) {
			OreDictionary.registerOre("foodFruitsChocolate", new ItemStack(DCsAppleMilk.chocolateFruits, 1, i));
		}

		for (int i = 0; i < 14; i++) {
			OreDictionary.registerOre("foodBlockIcecream", new ItemStack(DCsAppleMilk.blockIcecream, 1, i));
		}

		for (int i = 1; i < 4; i++) {
			OreDictionary.registerOre("bottleAppleliqueur", new ItemStack(DCsAppleMilk.itemCordial, 1, i));
			OreDictionary.registerOre("bottleTealiqueur", new ItemStack(DCsAppleMilk.itemCordial, 1, i + 4));
			OreDictionary.registerOre("bottleCassisliqueur", new ItemStack(DCsAppleMilk.itemCordial, 1, i + 8));
			OreDictionary.registerOre("bottlePlumliqueur", new ItemStack(DCsAppleMilk.itemCordial, 1, i + 12));
			OreDictionary.registerOre("bottleAmarettoliqueur", new ItemStack(DCsAppleMilk.itemCordial, 1, i + 16));
		}

		OreDictionary.registerOre("bottleShothu", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 0));
		OreDictionary.registerOre("bottleSake", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 1));
		OreDictionary.registerOre("bottleBeer", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 2));
		OreDictionary.registerOre("bottleWine", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 3));
		OreDictionary.registerOre("bottleGin", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 4));
		OreDictionary.registerOre("bottleRum", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 5));
		OreDictionary.registerOre("bottleVodka", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 6));
		OreDictionary.registerOre("bottleWhiskey", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 7));
		OreDictionary.registerOre("bottleBrandy", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 8));

		OreDictionary.registerOre("bottleShothu", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 16));
		OreDictionary.registerOre("bottleSake", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 17));
		OreDictionary.registerOre("bottleBeer", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 18));
		OreDictionary.registerOre("bottleWine", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 19));
		OreDictionary.registerOre("bottleGin", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 20));
		OreDictionary.registerOre("bottleRum", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 21));
		OreDictionary.registerOre("bottleVodka", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 22));
		OreDictionary.registerOre("bottleWhiskey", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 23));
		OreDictionary.registerOre("bottleBrandy", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 24));

		OreDictionary.registerOre("bottleShothu", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 32));
		OreDictionary.registerOre("bottleSake", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 33));
		OreDictionary.registerOre("bottleBeer", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 34));
		OreDictionary.registerOre("bottleWine", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 35));
		OreDictionary.registerOre("bottleGin", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 36));
		OreDictionary.registerOre("bottleRum", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 37));
		OreDictionary.registerOre("bottleVodka", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 38));
		OreDictionary.registerOre("bottleWhiskey", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 39));
		OreDictionary.registerOre("bottleBrandy", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 40));

		OreDictionary.registerOre("bottleShothu", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 48));
		OreDictionary.registerOre("bottleSake", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 49));
		OreDictionary.registerOre("bottleBeer", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 50));
		OreDictionary.registerOre("bottleWine", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 51));
		OreDictionary.registerOre("bottleGin", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 52));
		OreDictionary.registerOre("bottleRum", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 53));
		OreDictionary.registerOre("bottleVodka", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 54));
		OreDictionary.registerOre("bottleWhiskey", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 55));
		OreDictionary.registerOre("bottleBrandy", new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 56));

		// insence
		OreDictionary.registerOre("itemIncense", new ItemStack(DCsAppleMilk.incenseAgar, 1, 0));
		OreDictionary.registerOre("itemIncense", new ItemStack(DCsAppleMilk.incenseApple, 1, 0));
		OreDictionary.registerOre("itemIncense", new ItemStack(DCsAppleMilk.incenseClam, 1, 0));
		OreDictionary.registerOre("itemIncense", new ItemStack(DCsAppleMilk.incenseFrank, 1, 0));
		OreDictionary.registerOre("itemIncense", new ItemStack(DCsAppleMilk.incenseIce, 1, 0));
		OreDictionary.registerOre("itemIncense", new ItemStack(DCsAppleMilk.incenseLavender, 1, 0));
		OreDictionary.registerOre("itemIncense", new ItemStack(DCsAppleMilk.incenseMint, 1, 0));
		OreDictionary.registerOre("itemIncense", new ItemStack(DCsAppleMilk.incenseRose, 1, 0));
		OreDictionary.registerOre("itemIncense", new ItemStack(DCsAppleMilk.incenseSandalwood, 1, 0));
		OreDictionary.registerOre("itemIncense", new ItemStack(DCsAppleMilk.incenseVanilla, 1, 0));
		OreDictionary.registerOre("itemIncense", new ItemStack(DCsAppleMilk.incenseYuzu, 1, 0));
		OreDictionary.registerOre("itemEssentialOil", new ItemStack(DCsAppleMilk.essentialOil, 1, 0));

	}

}
