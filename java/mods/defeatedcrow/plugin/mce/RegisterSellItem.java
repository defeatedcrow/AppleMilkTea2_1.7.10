package mods.defeatedcrow.plugin.mce;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.ItemStack;
import shift.mceconomy2.api.MCEconomyAPI;

public class RegisterSellItem {

	public static void registerSellable() {
		// 無限化手段があるものは無価値又は安値。
		// container
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.appleBox, 1, 0), 3);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.woodBox, 1, 0), 3);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.woodBox, 1, 1), 3);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.woodBox, 1, 2), 3);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.woodBox, 1, 3), 3);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.woodBox, 1, 4), 24);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.woodBox, 1, 5), 32);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.woodBox, 1, 6), 32);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.woodBox, 1, 8), 24);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.woodBox, 1, 9), 24);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.woodBox, 1, 11), 5);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.woodBox, 1, 12), 5);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.charcoalBox, 1, 0), 3);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.gunpowderContainer, 1, 0), 3);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.gunpowderContainer, 1, 1), 0);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.gunpowderContainer, 1, 2), 0);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.gunpowderContainer, 1, 3), 3);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.vegiBag, 1, 0), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.vegiBag, 1, 1), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.vegiBag, 1, 2), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.vegiBag, 1, 3), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.vegiBag, 1, 4), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.vegiBag, 1, 5), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.vegiBag, 1, 6), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.vegiBag, 1, 7), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.vegiBag, 1, 8), 18);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.vegiBag, 1, 8), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cardboard, 1, 0), 16);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cardboard, 1, 1), 16);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cardboard, 1, 2), 16);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cardboard, 1, 3), 16);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.eggBasket, 1, 0), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.eggBasket, 1, 1), 12);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.mushroomBox, 1, 0), 8);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.mushroomBox, 1, 1), 8);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.melonBomb, 1, 0), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.silkyMelon, 1, 0), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.wipeBox, 1, 0), 6);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.wipeBox, 1, 1), 18);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.wipeBox2, 1, 0), 40);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.mobBlock, 1, 0), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.mobBlock, 1, 1), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.mobBlock, 1, 2), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.mobBlock, 1, 3), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.mobBlock, 1, 4), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.flowerPot, 1, 0), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.flowerPot, 1, 4), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.yuzuFence, 1, 0), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.woodPanel, 1, 0), 10);

		// baskets
		for (int i = 0; i < 15; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.Basket, 1, i), (5 + i));
		}
		for (int i = 0; i < 8; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.containerWBottle, 1, i), i * 10 + 15);
		}
		for (int i = 0; i < 8; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.containerSaddle, 1, i), i * 20 + 25);
		}
		for (int i = 0; i < 8; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.containerItemDoorW, 1, i), i * 8 + 8);
		}
		for (int i = 0; i < 8; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.containerItemDoorI, 1, i), i * 250 + 250);
		}
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.chopsticksBox, 1, 0), 5);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.chopsticksBox, 1, 4), 5);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.chocoBlock, 1, 0), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.chocoBlock, 1, 1), 500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.bowlRack), 5);

		// chalcedony
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.chalcedony, 1, 0), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.chalcedony, 1, 1), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.chalcedony, 1, 2), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.chalcedony, 1, 3), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.flintBlock), 25);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cLamp, 1, 0), 60);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cLamp, 1, 1), 60);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cLamp, 1, 2), 60);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cLamp, 1, 3), 60);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cLamp, 1, 4), 75);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cLamp, 1, 5), 75);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cLamp, 1, 6), 75);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cLamp, 1, 7), 75);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cLamp, 1, 8), -1);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cLamp, 1, 9), -1);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cLamp, 1, 10), -1);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cLamp, 1, 11), -1);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.chalcenonyPanel, 1, 0), 90);

		// Maker
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.teaMakerNext), 200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.emptyCup), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.emptyPanGaiden), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.iceMaker), 200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.teppanII), 150);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.processor), 200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.evaporator), 200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.advProcessor), 400);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.barrel), 20);

		// gadget
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.rotaryDial), 300);

		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.gelBat), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.batBox), 250);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.handleEngine), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.yuzuBat), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.yuzuGel), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.redGel), 15);

		// foodblock
		for (int i = 0; i < 14; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.teacupBlock, 1, i), 20);
		}
		for (int i = 0; i < 13; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.teaCup2, 1, i), 24);
		}
		for (int i = 0; i < 8; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.bowlBlock, 1, i), 8);
		}
		for (int i = 0; i < 8; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.bowlJP, 1, i), 10);
		}
		for (int i = 0; i < 14; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.blockIcecream, 1, i), 25);
		}
		for (int i = 0; i < 4; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.foodPlate, 1, i), 8);
		}
		for (int i = 0; i < 14; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.alcoholCup, 1, i), 15);
		}
		for (int i = 0; i < 15; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cocktail, 1, i), 30);
		}
		for (int i = 0; i < 10; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cocktail2, 1, i), 35);
		}

		// bottle
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.emptyBottle, 1, 0), 10);

		int[] amount = {
				0,
				16,
				32,
				48 };
		for (int i = 0; i < amount.length; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (amount[i])), 50);
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (1 + amount[i])), 50);
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (2 + amount[i])), 50);
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (3 + amount[i])), 80);
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (4 + amount[i])), 80);
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (5 + amount[i])), 80);
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (6 + amount[i])), 120);
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (7 + amount[i])), 120);
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (8 + amount[i])), 120);
		}

		for (int i = 0; i < 5; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cordial, 1, i * 3 + 1), 1);
		}

		// tree
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.saplingTea), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.saplingTea, 1, 1), 15);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.saplingTea, 1, 2), 15);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.saplingYuzu), 15);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.logYuzu), 5);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.teaTree, 1, 0), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.teaTree, 1, 1), 40);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cassisTree, 1, 3), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.cassisTree, 1, 7), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.leavesYuzu, 1, 3), 50);

		// fooditem
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.bakedApple), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.appleTart), 3);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.appleTart, 1, 1), 3);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.appleTart, 1, 2), 5);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.appleTart, 1, 3), 5);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.appleSandwich), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.appleSandwich, 1, 1), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.appleSandwich, 1, 2), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.appleSandwich, 1, 3), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.toffyApple), 2);

		for (int i = 0; i < 7; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.icyToffyApple, 1, i), 5);
		}

		for (int i = 0; i < 4; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.condensedMIlk, 1, i), i);
		}

		for (int i = 0; i < 13; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.chocolateFruits, 1, i), 8);
		}

		for (int i = 0; i < 11; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.mincedFoods, 1, i), 5);
		}

		for (int i = 0; i < 10; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.gratedApple, 1, i), 5);
		}

		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.foodTea, 1, 0), 2);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.foodTea, 1, 1), 3);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.foodTea, 1, 2), 5);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.foodTea, 1, 3), 4);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.EXItems, 1, 0), 4);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.EXItems, 1, 1), 3);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.EXItems, 1, 2), 6);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.EXItems, 1, 3), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.EXItems, 1, 5), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.EXItems, 1, 6), 5);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.EXItems, 1, 7), 7);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.EXItems, 1, 8), 5);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.EXItems, 1, 9), 5);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.inkStick), 8);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.yeast, 1, 0), 10);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.yeast, 1, 1), 30);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.stickCarbon, 1, 0), 10);

		for (int i = 0; i < 5; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.leafTea, 1, i), 1);
		}

		for (int i = 0; i < 3; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.dustWood, 1, i), 2);
		}

		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.chopsticks), -1);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.chopsticks, 1, 1), -1);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.icyCrystal), 150);

		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.chalcedonyKnife), 80);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.chalcedonyHammer), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.firestarter), 50);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.DCgrater), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.monocle), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.onixSword), 150);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.pruningShears), 160);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.slotPanel), 60);

		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.batteryItem, 1, 0), 100);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.batteryItem, 1, 1), 200);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.batteryItem, 1, 2), 10);

		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.princessClam, 1, 0), 400);
		for (int i = 0; i < 4; i++) {
			MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.princessClam, 1, i + 1), -1);
		}

		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.strangeSlag, 1, 0), 20);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.fossilScale, 1, 0), 300);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.yuzuGatling, 1, 0), 120);
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.fossilCannon, 1, 0), 500);

		// aroma
		MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.incenseBase, 1, 0), 700);
		for (int i = 0; i < 11; i++) {
			if (i < 8)
				MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.princessClam, 1, i), 50);
			else
				MCEconomyAPI.addPurchaseItem(new ItemStack(DCsAppleMilk.princessClam, 1, i), 150);
		}

		AMTLogger.trace("Succeeded to register the price for MCEconomy2");
	}

}
