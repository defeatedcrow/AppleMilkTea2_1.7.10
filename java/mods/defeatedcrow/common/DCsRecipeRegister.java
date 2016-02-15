package mods.defeatedcrow.common;

import java.util.ArrayList;

import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.config.DCsConfigCocktail;
import mods.defeatedcrow.handler.Util;
import mods.defeatedcrow.plugin.LoadModHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class DCsRecipeRegister {

	private Item rubberWood;
	private ArrayList<ItemStack> waterContainer = new ArrayList<ItemStack>();
	private ArrayList<ItemStack> seaWeed = new ArrayList<ItemStack>();

	public void addRecipe() {
		OreDictionary.registerOre("dustSugar", new ItemStack(Items.sugar, 1, 0));
		OreDictionary.registerOre("foodSugar", new ItemStack(Items.sugar, 1, 0));
		OreDictionary.registerOre("cropWheat", new ItemStack(Items.wheat, 1, 0));
		OreDictionary.registerOre("cropApple", new ItemStack(Items.apple, 1, 0));
		OreDictionary.registerOre("foodEgg", new ItemStack(Items.egg, 1, 0));
		OreDictionary.registerOre("bucketWater", new ItemStack(Items.water_bucket, 1, 0));
		OreDictionary.registerOre("bucketMilk", new ItemStack(Items.milk_bucket, 1, 0));
		OreDictionary.registerOre("bucketLava", new ItemStack(Items.lava_bucket, 1, 0));
		OreDictionary.registerOre("gemCoal", new ItemStack(Items.coal, 1, 0));

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
		this.addCharms();
		this.addMachines();
		this.addSmelting();

		// extra recipe
		if (DCsConfig.useEXRecipe) {
			GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.saplingTea, 1), new Object[] {
					"XXX",
					"XYX",
					"XXX",
					Character.valueOf('X'),
					new ItemStack(Items.gold_nugget, 1),
					Character.valueOf('Y'),
					new ItemStack(Blocks.sapling, 1, 0) });
		}

		if (DCsConfig.hardLeatherRecipe) {
			GameRegistry.addShapelessRecipe(new ItemStack(Items.leather, 1, 0), new ItemStack(DCsAppleMilk.foodTea, 1,
					0), new ItemStack(Items.rotten_flesh, 1, 0));
		}

		// add extra recipe if nether is disabled.
		// if (!NetworkUtilServer.INSTANCE.enableNether()) {
		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.teaMakerNext, 1, 0), new Object[] {
				"XYX",
				"ZYZ",
				"XWX",
				Character.valueOf('X'),
				new ItemStack(Blocks.stained_hardened_clay, 1, 0),
				Character.valueOf('Y'),
				new ItemStack(Items.water_bucket, 1),
				Character.valueOf('Z'),
				new ItemStack(Blocks.glass, 1),
				Character.valueOf('W'),
				new ItemStack(Items.lava_bucket, 1) });

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.teaMakerNext, 1, 0), new Object[] {
				"XYX",
				"ZYZ",
				"XWX",
				Character.valueOf('X'),
				"ingotSilver",
				Character.valueOf('Y'),
				new ItemStack(Items.water_bucket, 1),
				Character.valueOf('Z'),
				new ItemStack(Blocks.glass, 1),
				Character.valueOf('W'),
				new ItemStack(Items.lava_bucket, 1) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.evaporator, 1), new Object[] {
				" X ",
				"ZYZ",
				"XWX",
				Character.valueOf('W'),
				"gearIron",
				Character.valueOf('Z'),
				new ItemStack(Items.glass_bottle, 1),
				Character.valueOf('Y'),
				"bucketLava",
				Character.valueOf('X'),
				new ItemStack(Blocks.stained_hardened_clay, 1, 0) }));
		// }

	}

	static void addContainerRecipe() {

		for (int i = 0; i < 4; i++) {
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.log, 9, i), new ItemStack(DCsAppleMilk.woodBox, 1, i));

			GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.woodBox, 1, i), new Object[] {
					"UUU",
					"UUU",
					"UUU",
					Character.valueOf('U'),
					new ItemStack(Blocks.log, 1, i) });
		}

		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.log2, 9, 1), new ItemStack(DCsAppleMilk.woodBox, 1, 11));

		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.woodBox, 1, 11), new Object[] {
				"UUU",
				"UUU",
				"UUU",
				Character.valueOf('U'),
				new ItemStack(Blocks.log2, 1, 1) });

		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.log2, 9, 0), new ItemStack(DCsAppleMilk.woodBox, 1, 12));

		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.woodBox, 1, 12), new Object[] {
				"UUU",
				"UUU",
				"UUU",
				Character.valueOf('U'),
				new ItemStack(Blocks.log2, 1, 0) });

		GameRegistry.addShapelessRecipe(new ItemStack(Items.apple, 9), new ItemStack(DCsAppleMilk.appleBox, 1));

		GameRegistry.addShapelessRecipe(new ItemStack(Items.coal, 9, 1), new ItemStack(DCsAppleMilk.charcoalBox, 1));

		for (int i = 0; i < 10; i++) {
			ItemStack itemStack = new ItemStack(DCsAppleMilk.leafTea, 9, 0);
			if (i == 1)
				itemStack = new ItemStack(Items.potato, 9);
			else if (i == 2)
				itemStack = new ItemStack(Items.carrot, 9);
			else if (i == 3)
				itemStack = new ItemStack(Blocks.pumpkin, 9);
			else if (i == 4)
				itemStack = new ItemStack(Items.wheat_seeds, 9);
			else if (i == 5)
				itemStack = new ItemStack(Items.reeds, 9);
			else if (i == 6)
				itemStack = new ItemStack(Blocks.cactus, 9);
			else if (i == 7)
				itemStack = new ItemStack(Items.dye, 9, 3);
			else if (i == 8)
				itemStack = new ItemStack(Items.nether_wart, 9);
			else if (i == 9)
				itemStack = new ItemStack(Items.sugar, 9);
			else
				itemStack = new ItemStack(DCsAppleMilk.leafTea, 9, 0);

			GameRegistry.addShapelessRecipe(itemStack, new ItemStack(DCsAppleMilk.vegiBag, 1, i));

			if (i >= 0) {
				GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.vegiBag, 1, i), new Object[] {
						"TTT",
						"TTT",
						"TTT",
						Character.valueOf('T'),
						itemStack });
			}
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.vegiBag, 1, 9), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				Character.valueOf('X'),
				"dustSugar" }));

		for (int i = 0; i < 4; i++) {
			ItemStack item = new ItemStack(Items.gunpowder, 9);
			if (i == 1)
				item = new ItemStack(DCsAppleMilk.EXItems, 9, 2);
			else if (i == 2)
				item = new ItemStack(Items.clay_ball, 9);
			else if (i == 3)
				item = new ItemStack(DCsAppleMilk.clam, 9, 0);
			else
				item = new ItemStack(Items.gunpowder, 9);

			GameRegistry.addShapelessRecipe(item, new ItemStack(DCsAppleMilk.gunpowderContainer, 1, i));

			if (i >= 0) {
				GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.gunpowderContainer, 1, i), new Object[] {
						"TTT",
						"TTT",
						"TTT",
						Character.valueOf('T'),
						item });
			}
		}

		for (int i = 0; i < 4; i++) {
			ItemStack item = new ItemStack(DCsAppleMilk.leafTea, 9, 1);
			if (i == 1)
				item = new ItemStack(DCsAppleMilk.leafTea, 9, 2);
			else if (i == 2)
				item = new ItemStack(DCsAppleMilk.leafTea, 9, 3);
			else if (i == 3)
				item = new ItemStack(DCsAppleMilk.leafTea, 9, 4);
			else
				item = new ItemStack(DCsAppleMilk.leafTea, 9, 1);

			GameRegistry.addShapelessRecipe(item, new ItemStack(DCsAppleMilk.cardboard, 1, i));

			if (i >= 0) {
				GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.cardboard, 1, i), new Object[] {
						"TTT",
						"TTT",
						"TTT",
						Character.valueOf('T'),
						item });
			}
		}

		if (DCsConfig.enableMobBlock.length >= 5) {
			for (int i = 0; i < 5; i++) {
				ItemStack item = new ItemStack(Items.rotten_flesh, 8);
				if (i == 1)
					item = new ItemStack(Items.bone, 8, 0);
				else if (i == 2)
					item = new ItemStack(Items.spider_eye, 8);
				else if (i == 3)
					item = new ItemStack(Items.ender_pearl, 8, 0);
				else if (i == 4)
					item = new ItemStack(Items.slime_ball, 8, 0);
				else
					item = new ItemStack(Items.rotten_flesh, 8);

				if (DCsConfig.enableMobBlock[i]) {
					GameRegistry.addShapelessRecipe(item, new ItemStack(DCsAppleMilk.mobBlock, 1, i));

					if (i >= 0) {
						GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.mobBlock, 1, i), new Object[] {
								"TTT",
								"T T",
								"TTT",
								Character.valueOf('T'),
								item });
					}
				}
			}
		}

		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.appleBox, 1), new Object[] {
				"UUU",
				"UUU",
				"UUU",
				Character.valueOf('U'),
				Items.apple });

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.appleBox, 1, 0), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				Character.valueOf('X'),
				"cropApple" }));

		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.charcoalBox, 1), new Object[] {
				"UUU",
				"UUU",
				"UUU",
				Character.valueOf('U'),
				new ItemStack(Items.coal, 1, 1) });

		GameRegistry.addShapelessRecipe(new ItemStack(Items.egg, 8, 0), new ItemStack(DCsAppleMilk.eggBasket, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.clam, 8, 3), new ItemStack(DCsAppleMilk.eggBasket,
				1, 1));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.eggBasket, 1, 0), new Object[] {
				"XXX",
				"X X",
				"XXX",
				Character.valueOf('X'),
				new ItemStack(Items.egg, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.eggBasket, 1, 1), new Object[] {
				"XXX",
				"X X",
				"XXX",
				Character.valueOf('X'),
				new ItemStack(DCsAppleMilk.clam, 1, 3) }));

		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.melon_block, 9, 0), new ItemStack(DCsAppleMilk.melonBomb,
				1, 0));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.melonBomb, 1, 0), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				Character.valueOf('X'),
				new ItemStack(Blocks.melon_block, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.silkyMelon, 1, 0), new Object[] {
				" X ",
				"XYX",
				" X ",
				Character.valueOf('Y'),
				new ItemStack(DCsAppleMilk.melonBomb, 1, 0),
				Character.valueOf('X'),
				new ItemStack(Items.string, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.chopsticksBox, 1, 4), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				Character.valueOf('Y'),
				new ItemStack(DCsAppleMilk.Basket, 1, 0),
				Character.valueOf('X'),
				"stickWood" }));

		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.red_mushroom, 9), new ItemStack(DCsAppleMilk.mushroomBox,
				1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.brown_mushroom, 9), new ItemStack(
				DCsAppleMilk.mushroomBox, 1, 1));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.mushroomBox, 1, 0), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				Character.valueOf('X'),
				new ItemStack(Blocks.red_mushroom, 1) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.mushroomBox, 1, 1), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				Character.valueOf('X'),
				new ItemStack(Blocks.brown_mushroom, 1) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.chocoBlock, 1, 0), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				Character.valueOf('Y'),
				new ItemStack(Items.gold_ingot, 1),
				Character.valueOf('X'),
				"foodFruitsChocolate" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.chocoBlock, 1, 1), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				Character.valueOf('Y'),
				new ItemStack(Items.diamond, 1),
				Character.valueOf('X'),
				"foodFruitsChocolate" }));

		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.wipeBox, 1, 0), new Object[] {
				"UUU",
				"UUU",
				"UUU",
				Character.valueOf('U'),
				Items.paper });

		GameRegistry.addShapelessRecipe(new ItemStack(Items.paper, 9), new ItemStack(DCsAppleMilk.wipeBox, 1, 0));

		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.wipeBox, 1, 1), new Object[] {
				"UUU",
				"UUU",
				"UUU",
				Character.valueOf('U'),
				new ItemStack(DCsAppleMilk.wipeBox, 1, 0) });

		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.flowerPot, 1, 0), new Object[] {
				"UUU",
				"UUU",
				"UUU",
				Character.valueOf('U'),
				new ItemStack(Blocks.red_flower) });

		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.flowerPot, 1, 4), new Object[] {
				"UUU",
				"UUU",
				"UUU",
				Character.valueOf('U'),
				new ItemStack(Blocks.yellow_flower) });

		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.red_flower, 9),
				new ItemStack(DCsAppleMilk.flowerPot, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.yellow_flower, 9), new ItemStack(DCsAppleMilk.flowerPot,
				1, 4));

		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.yuzuFence, 4, 0), new Object[] {
				"UUU",
				'U',
				new ItemStack(DCsAppleMilk.logYuzu, 1, 0) });

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.woodPanel, 1, 0), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				"dustWood",
				'Y',
				"plankWood" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.woodPanel, 1, 0), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				"dustAnyWood",
				'Y',
				"plankWood" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.woodPanel, 1, 0), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				'X',
				"pulpWood",
				'Y',
				"plankWood" }));

		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.dustWood, 8), new ItemStack(DCsAppleMilk.woodPanel,
				1, 0));

		// 特殊コンテナ
		Item[] rets = {
				Item.getItemFromBlock(DCsAppleMilk.containerWBottle),
				Item.getItemFromBlock(DCsAppleMilk.containerSaddle),
				DCsAppleMilk.containerItemDoorW,
				DCsAppleMilk.containerItemDoorI };

		Item[] items = {
				Items.potionitem,
				Items.saddle,
				Items.wooden_door,
				Items.iron_door };

		for (int i = 0; i < 4; i++) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(rets[i], 1, 3), new Object[] {
					"XX",
					"XX",
					'X',
					new ItemStack(items[i], 1, 0) }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(rets[i], 1, 7), new Object[] {
					"XXX",
					"X X",
					"XXX",
					'X',
					new ItemStack(items[i], 1, 0) }));

			GameRegistry.addShapelessRecipe(new ItemStack(items[i], 8, 0), new ItemStack(rets[i], 1, 7));

			for (int j = 0; j < 7; j++) {
				GameRegistry.addShapelessRecipe(new ItemStack(rets[i], 1, j + 1), new ItemStack(rets[i], 1, j),
						new ItemStack(items[i], 1, 0));
			}
		}

		for (int i = 0; i < 6; i++) {
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.sapling, 9, i),
					new ItemStack(DCsAppleMilk.hedge, 1, i));

			GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.hedge, 1, i), new Object[] {
					"UUU",
					"UUU",
					"UUU",
					Character.valueOf('U'),
					new ItemStack(Blocks.sapling, 1, i) });
		}

		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.hedge, 1, 6), new Object[] {
				"UUU",
				"UUU",
				"UUU",
				Character.valueOf('U'),
				new ItemStack(DCsAppleMilk.saplingYuzu, 1, 0) });

		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.saplingYuzu, 9, 0), new ItemStack(
				DCsAppleMilk.hedge, 1, 6));

		for (int i = 0; i < 4; i++) {
			int[] k = {
					4,
					5,
					1,
					0 };
			GameRegistry.addShapelessRecipe(new ItemStack(Blocks.double_plant, 9, k[i]), new ItemStack(
					DCsAppleMilk.flowerBase, 1, i));

			GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.flowerBase, 1, i), new Object[] {
					"UUU",
					"UUU",
					"UUU",
					Character.valueOf('U'),
					new ItemStack(Blocks.double_plant, 1, k[i]) });
		}
	}

	static void addTablewareRecipe() {

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.emptyCup, 1), new Object[] {
				"XXX",
				"XX ",
				Character.valueOf('X'),
				"ingotSilver" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.emptyCup, 1), new Object[] {
				"XXX",
				"XX ",
				Character.valueOf('X'),
				new ItemStack(Blocks.stained_hardened_clay, 1, 0) }));

		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.teaMakerNext, 1), new Object[] {
				"XYX",
				"ZYZ",
				"XWX",
				Character.valueOf('X'),
				new ItemStack(Blocks.stained_hardened_clay, 1, 0),
				Character.valueOf('Y'),
				new ItemStack(Items.water_bucket, 1),
				Character.valueOf('Z'),
				new ItemStack(Blocks.glass, 1),
				Character.valueOf('W'),
				new ItemStack(Items.blaze_rod, 1) });

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.teaMakerNext, 1), new Object[] {
				"XYX",
				"ZYZ",
				"XWX",
				Character.valueOf('X'),
				"ingotSilver",
				Character.valueOf('Y'),
				new ItemStack(Items.water_bucket, 1),
				Character.valueOf('Z'),
				new ItemStack(Blocks.glass, 1),
				Character.valueOf('W'),
				new ItemStack(Items.blaze_rod, 1) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.teaMakerBlack, 1), new Object[] {
				" X ",
				"XYX",
				" X ",
				Character.valueOf('X'),
				new ItemStack(Blocks.obsidian, 1),
				Character.valueOf('Y'),
				new ItemStack(DCsAppleMilk.teaMakerNext, 1) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.iceMaker, 1), new Object[] {
				" X ",
				"ZYZ",
				"Z Z",
				Character.valueOf('X'),
				"gearIron",
				Character.valueOf('Z'),
				new ItemStack(Items.iron_ingot, 1),
				Character.valueOf('Y'),
				new ItemStack(DCsAppleMilk.icyCrystal, 1) }));

		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.emptyPanGaiden, 1), new Object[] {
				"X X",
				"X X",
				"XXX",
				Character.valueOf('X'),
				new ItemStack(Blocks.hardened_clay, 1) });

		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.DCgrater, 1, 0), new Object[] {
				" X ",
				"XYX",
				"XYX",
				Character.valueOf('X'),
				new ItemStack(Items.stick, 1),
				Character.valueOf('Y'),
				new ItemStack(Blocks.iron_bars, 1) });

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.bowlRack, 1), new Object[] {
				"X X",
				"X X",
				"YYY",
				Character.valueOf('X'),
				"stickWood",
				Character.valueOf('Y'),
				"plankWood" }));

		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.Basket, 1), new Object[] {
				"X X",
				"X X",
				"XXX",
				Character.valueOf('X'),
				new ItemStack(Items.reeds, 1) });

		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.teppanII, 1, 0), new Object[] {
				"X X",
				"XXX",
				" Y ",
				Character.valueOf('X'),
				new ItemStack(Items.iron_ingot, 1),
				Character.valueOf('Y'),
				new ItemStack(Blocks.furnace, 1) });

		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.emptyBottle, 1, 0), new Object[] {
				" Y ",
				"X X",
				" X ",
				Character.valueOf('X'),
				new ItemStack(Blocks.glass, 1),
				Character.valueOf('Y'),
				new ItemStack(Items.iron_ingot, 1) });

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.emptyBottle, 1, 0), new Object[] {
				" Y ",
				"X X",
				" X ",
				Character.valueOf('X'),
				new ItemStack(Blocks.glass, 1),
				Character.valueOf('Y'),
				"ingotTin" }));

		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.Basket, 1, 14), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				Character.valueOf('X'),
				new ItemStack(DCsAppleMilk.emptyBottle, 1, 0) });
	}

	static void addGraterRecipe() {

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 0), new Object[] {
				"toolGrater",
				new ItemStack(Items.apple, 1, 0) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 0), new Object[] {
				"toolGrater",
				"cropApple" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 1), new Object[] {
				"toolGrater",
				"cropPeach" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 1), new Object[] {
				"toolGrater",
				"cropBanana" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 9), new Object[] {
				"toolGrater",
				"cropOrange" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 1), new Object[] {
				"toolGrater",
				"cropPlum" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 2), new Object[] {
				"toolGrater",
				"cropLemon",
				"dropHoney" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 3), new Object[] {
				"toolGrater",
				"cropCoffee", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 5), new Object[] {
				"toolGrater",
				"cropLime", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 5), new Object[] {
				"toolGrater",
				"cropCitron", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 6), new Object[] {
				"toolGrater",
				"cropTomato", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 6), new Object[] {
				"toolGrater",
				"tomato", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), new Object[] {
				"toolGrater",
				"cropStrawberry", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), new Object[] {
				"toolGrater",
				"cropBlueberry", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), new Object[] {
				"toolGrater",
				"cropRaspberry", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), new Object[] {
				"toolGrater",
				"cropBlackberry", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), new Object[] {
				"toolGrater",
				"cropCassis", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), new Object[] {
				"toolGrater",
				"listAllberry", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 8), new Object[] {
				"toolGrater",
				"cropGrape", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 8), new Object[] {
				"toolGrater",
				"grape", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.foodTea, 1, 2), new Object[] {
				"toolGrater",
				new ItemStack(DCsAppleMilk.foodTea, 1, 1),
				"cropOrange", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.foodTea, 1, 2), new Object[] {
				"toolGrater",
				new ItemStack(DCsAppleMilk.foodTea, 1, 1),
				"cropCitron", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.foodTea, 1, 3), new Object[] {
				"toolGrater",
				new ItemStack(DCsAppleMilk.foodTea, 1, 1),
				new ItemStack(Items.apple, 1, 0) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.foodTea, 1, 3), new Object[] {
				"toolGrater",
				new ItemStack(DCsAppleMilk.foodTea, 1, 1),
				"cropApple" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 0), new Object[] {
				"toolGrater",
				new ItemStack(Blocks.brown_mushroom, 1, 0),
				new ItemStack(Blocks.red_mushroom, 1, 0) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 1), new Object[] {
				"toolGrater",
				new ItemStack(Items.fish, 1, 0),
				new ItemStack(Items.carrot, 1),
				new ItemStack(Items.potato, 1),
				"bucketMilk" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 1), new Object[] {
				"toolGrater",
				new ItemStack(Items.fish, 1, 1),
				new ItemStack(Items.carrot, 1),
				new ItemStack(Items.potato, 1),
				"bucketMilk" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 1), new Object[] {
				"toolGrater",
				new ItemStack(Items.fish, 1, 0),
				new ItemStack(Items.carrot, 1, 0),
				new ItemStack(Items.potato, 1, 0),
				"listAllmilk" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 2), new Object[] {
				"toolGrater",
				new ItemStack(Items.egg, 1, 0),
				new ItemStack(Items.chicken, 1, 0),
				"cropWheat" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 2), new Object[] {
				"toolGrater",
				new ItemStack(Items.egg, 1, 0),
				new ItemStack(Items.chicken, 1, 0),
				"cropRice" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 3), new Object[] {
				"toolGrater",
				"cropRice",
				"cropRice",
				"cropRice" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 3), new Object[] {
				"toolGrater",
				"cropWheat",
				"cropWheat",
				"cropWheat" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 4), new Object[] {
				"toolGrater",
				new ItemStack(DCsAppleMilk.EXItems, 1, 2),
				"cropRice" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 4), new Object[] {
				"toolGrater",
				new ItemStack(DCsAppleMilk.EXItems, 1, 2),
				"cropWheat" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 5), new Object[] {
				"toolGrater",
				new ItemStack(Items.chicken, 1, 0),
				"leek",
				"bucketSoymilk",
				"tofuKinu" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 5), new Object[] {
				"toolGrater",
				new ItemStack(Items.chicken, 1, 0),
				"leek",
				"bucketSoymilk",
				"tofuMomen" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 5), new Object[] {
				"toolGrater",
				new ItemStack(Items.chicken, 1, 0),
				"cropLeek",
				"foodSoymilk",
				"foodSilkentofu" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 6), new Object[] {
				"toolGrater",
				new ItemStack(Blocks.pumpkin, 1, 0),
				"bucketMilk" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 6), new Object[] {
				"toolGrater",
				new ItemStack(Blocks.pumpkin, 1, 0),
				"listAllmilk" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 7), new Object[] {
				"toolGrater",
				new ItemStack(Items.porkchop, 1, 0),
				"cropLettuce",
				"cropTomato", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 7), new Object[] {
				"toolGrater",
				new ItemStack(Items.porkchop, 1, 0),
				"cabbage",
				"tomato", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 8), new Object[] {
				"toolGrater",
				"bucketMilk",
				"dustSugar",
				new ItemStack(Items.dye, 9, 3),
				new ItemStack(Items.dye, 9, 3) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 8), new Object[] {
				"toolGrater",
				"listAllmilk",
				"dustSugar",
				new ItemStack(Items.dye, 9, 3),
				new ItemStack(Items.dye, 9, 3) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 9), new Object[] {
				"toolGrater",
				"miso",
				"cropSeaWeed",
				"tofuKinu" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 9), new Object[] {
				"toolGrater",
				"miso",
				"cropSeaWeed",
				"tofuMomen" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 10), new Object[] {
				"toolGrater",
				new ItemStack(DCsAppleMilk.clam, 1, 0),
				"cropSeaWeed" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 4), new Object[] {
				new ItemStack(DCsAppleMilk.mincedFoods, 1, 8),
				"bucketMilk" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 4), new Object[] {
				new ItemStack(DCsAppleMilk.mincedFoods, 1, 8),
				"listAllmilk" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.yeast, 1, 1), new Object[] {
				new ItemStack(DCsAppleMilk.yeast, 1, 0),
				"dustSugar",
				"dustSugar",
				"dustSugar" }));

	}

	static void addCocktailRecipe() {

		// フローズン・ダイキリ
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 0), new Object[] {
				new ItemStack(DCsAppleMilk.teaCup2, 1, 4),
				"foodCrushedIce",
				"dustSugar",
				"bottleRum" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 0), new Object[] {
				"foodLimejuice",
				"foodCrushedIce",
				"dustSugar",
				"bottleRum" }));

		// サケティーニ
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 2), new Object[] {
				"bottleGin",
				"bottleSake" }));

		// ギムレット
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 3), new Object[] {
				new ItemStack(DCsAppleMilk.teaCup2, 1, 4),
				"dustSugar",
				"bottleGin" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 3), new Object[] {
				"foodLimejuice",
				"dustSugar",
				"bottleGin" }));

		// ブラック・ローズ
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 4), new Object[] {
				new ItemStack(DCsAppleMilk.teacupBlock, 1, 12),
				"bottleRum" }));

		// レッドアイ
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 5), new Object[] {
				new ItemStack(DCsAppleMilk.teaCup2, 1, 5),
				"bottleBeer" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 5), new Object[] {
				"foodTomatojuice",
				"bottleBeer" }));

		// ピニャ・コラーダ
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 6), new Object[] {
				"bottleRum",
				"cropPineapple",
				"cropCoconut",
				"foodCrushedIce" }));

		// アメリカン・レモネード
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 7), new Object[] {
				new ItemStack(DCsAppleMilk.teacupBlock, 1, 10),
				"bottleWine" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 7), new Object[] {
				"foodLemonaid",
				"bottleWine" }));

		// モスコミュール
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 8), new Object[] {
				new ItemStack(DCsAppleMilk.teaCup2, 1, 4),
				"bottleVodka",
				"drinkCider" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 8), new Object[] {
				new ItemStack(DCsAppleMilk.teaCup2, 1, 4),
				"bottleVodka",
				"foodGingersoda" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 8), new Object[] {
				"foodLimejuice",
				"bottleVodka",
				"foodGingersoda" }));

		// ミント・ジュレップ
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 9), new Object[] {
				"dustSugar",
				"bottleWhiskey",
				"cropSpiceleaf",
				"foodCrushedIce" }));

		// キール
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 10), new Object[] {
				"bottleCassisliqueur",
				"bottleWine" }));

		// カシスミルク
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 11), new Object[] {
				"bottleCassisliqueur",
				"bucketSoymilk" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 11), new Object[] {
				"bottleCassisliqueur",
				"bucketMilk" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 11), new Object[] {
				"bottleCassisliqueur",
				"listAllmilk" }));

		// ブラッディメアリー
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 12), new Object[] {
				"bottleVodka",
				"blackPepper",
				new ItemStack(DCsAppleMilk.teaCup2, 1, 5) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 12), new Object[] {
				"bottleVodka",
				"whitePepper",
				new ItemStack(DCsAppleMilk.teaCup2, 1, 5) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 12), new Object[] {
				"bottleVodka",
				"cropChilipepper",
				new ItemStack(DCsAppleMilk.teaCup2, 1, 5) }));

		// カシスティーカクテル
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 13), new Object[] {
				"bottleTealiqueur",
				"bottleCassisliqueur",
				"foodCrushedIce" }));

		// ダブルアップル
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 14), new Object[] {
				"bottleAppleliqueur",
				"cropApple",
				new ItemStack(DCsAppleMilk.teacupBlock, 1, 8), }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 14), new Object[] {
				"bottleAppleliqueur",
				"cropApple",
				"foodApplejuice" }));

		// 豆乳梅酒
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 15), new Object[] {
				"bottlePlumliqueur",
				"bucketSoymilk" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 15), new Object[] {
				"bottlePlumliqueur",
				"foodSoymilk" }));

		// パナシェ
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail2, 1, 1), new Object[] {
				"bottleBeer",
				new ItemStack(DCsAppleMilk.teacupBlock, 1, 10) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail2, 1, 1), new Object[] {
				"bottleBeer",
				"foodLemonaid" }));

		// スプリッツァー
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail2, 1, 2), new Object[] {
				"bottleWine",
				new ItemStack(DCsAppleMilk.teaCup2, 1, 12) }));

		// スクリュードライバー
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail2, 1, 3), new Object[] {
				"bottleVodka",
				new ItemStack(DCsAppleMilk.teaCup2, 1, 11) }));

		// ゴッドファーザー
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail2, 1, 4), new Object[] {
				"bottleWhiskey",
				"bottleAmarettoliqueur" }));

		// トムアンドジェリー
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail2, 1, 5), new Object[] {
				"bottleBrandy",
				"bucketMilk",
				"bottleRum",
				"dustSugar",
				new ItemStack(Items.egg) }));

		// アレクサンドラ
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail2, 1, 6), new Object[] {
				"bottleBrandy",
				"dustSugar",
				new ItemStack(Items.dye, 1, 3) }));

		// ズーム
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail2, 1, 7), new Object[] {
				"bottleBrandy",
				"dropHoney",
				"dustSugar" }));

		// アマレットミルクティー
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail2, 1, 8), new Object[] {
				new ItemStack(DCsAppleMilk.teaCup2, 1, 1),
				"bottleAmarettoliqueur" }));

		// スノウ・サローノ
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail2, 1, 9), new Object[] {
				"bucketMilk",
				"bottleAmarettoliqueur" }));

		// 以下、ストレートで飲むレシピ
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.alcoholCup, 1, 0), new Object[] {
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
				"bottleSake", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.alcoholCup, 1, 1), new Object[] {
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
				"bottleBeer", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.alcoholCup, 1, 2), new Object[] {
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
				"bottleWine", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.alcoholCup, 1, 4), new Object[] {
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
				"bottleRum", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.alcoholCup, 1, 3), new Object[] {
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
				"bottleGin", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.alcoholCup, 1, 5), new Object[] {
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
				"bottleVodka", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.alcoholCup, 1, 6), new Object[] {
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
				"bottleWhiskey", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.alcoholCup, 1, 12), new Object[] {
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
				"bottleBrandy", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.alcoholCup, 1, 7), new Object[] {
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
				"bottleAppleliqueur", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.alcoholCup, 1, 8), new Object[] {
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
				"bottleTealiqueur", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.alcoholCup, 1, 9), new Object[] {
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
				"bottleCassisliqueur", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.alcoholCup, 1, 10), new Object[] {
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
				"bottlePlumliqueur", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.alcoholCup, 1, 11), new Object[] {
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
				"bottleShothu", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.alcoholCup, 1, 13), new Object[] {
				new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
				"bottleAmarettoliqueur", }));
	}

	static void addFoodRecipe() {

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.appleTart, 1, 0), new Object[] {
				"cropApple",
				"dustSugar",
				"cropWheat" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.appleTart, 1, 1), new Object[] {
				"cropCassis",
				"dustSugar",
				"cropWheat" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.appleTart, 1, 2), new Object[] {
				"cropYuzu",
				"dustSugar",
				"bucketMilk",
				Items.egg }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.appleTart, 1, 3), new Object[] {
				"cropApricot",
				"dustSugar",
				"bucketMilk",
				Items.egg }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.appleTart, 1, 3), new Object[] {
				"apricot",
				"dustSugar",
				"bucketMilk",
				Items.egg }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.toffyApple, 1, 0), new Object[] {
				"cropApple",
				"dustSugar",
				"stickWood" }));

		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.icyToffyApple, 1, 0), new ItemStack(
				DCsAppleMilk.toffyApple, 1), new ItemStack(Items.snowball, 1));

		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.icyToffyApple, 1, 1), new ItemStack(
				DCsAppleMilk.toffyApple, 1), new ItemStack(Items.feather, 1));

		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.icyToffyApple, 1, 2), new ItemStack(
				DCsAppleMilk.toffyApple, 1), new ItemStack(Items.gold_nugget, 1));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.icyToffyApple, 1, 3), new ItemStack(
				DCsAppleMilk.toffyApple, 1), new ItemStack(Blocks.leaves, 1, 32767)));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.icyToffyApple, 1, 3), new Object[] {
				new ItemStack(DCsAppleMilk.toffyApple, 1),
				"leavesWood" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 0), new Object[] {
				"bucketMilk",
				"dustSugar" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 0), new Object[] {
				"listAllmilk",
				"dustSugar" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 1), new Object[] {
				"cropCassis",
				"dustSugar" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 2), new Object[] {
				"cropSpiceleaf",
				"dustSugar" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 3), new Object[] {
				"cropYuzu",
				"dustSugar" }));

		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.inkStick, 1), new ItemStack(DCsAppleMilk.EXItems, 1,
				1), new ItemStack(Items.coal, 1, 1));

		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.inkStick, 1), new ItemStack(DCsAppleMilk.EXItems, 1,
				1), new ItemStack(Items.coal, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.appleSandwich, 2, 0), new ItemStack(Items.apple, 1),
				new ItemStack(Items.bread, 1));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.appleSandwich, 2, 0), new Object[] {
				"cropApple",
				new ItemStack(Items.bread, 1) }));

		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.appleSandwich, 2, 1), new ItemStack(Items.egg, 1),
				new ItemStack(Items.bread, 1));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.appleSandwich, 2, 2), new Object[] {
				"foodCassisPreserve",
				new ItemStack(Items.bread, 1) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.appleSandwich, 2, 3), new Object[] {
				"foodYuzuMarmalade",
				new ItemStack(Items.bread, 1) }));

		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.foodTea, 1, 1), new ItemStack(DCsAppleMilk.foodTea,
				1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.foodTea, 1, 4), new ItemStack(DCsAppleMilk.foodTea,
				1, 1));
	}

	static void addChalcedony() {

		GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.flintBlock, 1), new Object[] {
				"UU",
				"UU",
				Character.valueOf('U'),
				Items.flint });

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.flint, 4), new Object[] { new ItemStack(
				DCsAppleMilk.flintBlock, 1) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.chalcedonyKnife, 1), new Object[] {
				"X",
				"X",
				"Y",
				Character.valueOf('Y'),
				"stickWood",
				Character.valueOf('X'),
				new ItemStack(DCsAppleMilk.chalcedony, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.chalcedonyHammer, 1), new Object[] {
				"XXX",
				" Y ",
				" Y ",
				Character.valueOf('Y'),
				"stickWood",
				Character.valueOf('X'),
				new ItemStack(DCsAppleMilk.chalcedony, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.monocle, 1), new Object[] {
				" X ",
				"XZX",
				" XY",
				Character.valueOf('X'),
				new ItemStack(Items.iron_ingot, 1, 0),
				Character.valueOf('Y'),
				new ItemStack(DCsAppleMilk.chalcedony, 1, 0),
				Character.valueOf('Z'),
				new ItemStack(Blocks.glass_pane, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.pruningShears, 1), new Object[] {
				" XX",
				" Y ",
				"YZ ",
				Character.valueOf('X'),
				new ItemStack(DCsAppleMilk.chalcedonyKnife, 1, 0),
				Character.valueOf('Y'),
				"stickCarbon",
				Character.valueOf('Z'),
				new ItemStack(Items.leather, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.onixSword, 1), new Object[] {
				"X",
				"X",
				"Y",
				Character.valueOf('Y'),
				new ItemStack(Items.iron_ingot),
				Character.valueOf('X'),
				new ItemStack(DCsAppleMilk.chalcedony, 1, 3) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.EXItems, 1, 3), new Object[] {
				" X ",
				"X X",
				" X ",
				Character.valueOf('X'),
				new ItemStack(DCsAppleMilk.chalcedony, 1, 0) }));

		if (!DCsConfig.disableFireSteater) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.firestarter, 1), new Object[] {
					new ItemStack(DCsAppleMilk.chalcedony, 1, 0),
					new ItemStack(Items.iron_ingot, 1), }));
		}

		String[] dyes = {
				"dyeBlue",
				"dyeOrange",
				"dyeWhite",
				"dyeBlack" };
		for (int i = 0; i < 4; i++) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.chalcedony, 1, i), new Object[] {
					new ItemStack(DCsAppleMilk.chalcedony, 1, 32767),
					dyes[i] }));

			GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.cLamp, 1, i), new Object[] {
					" U ",
					"UXU",
					" U ",
					Character.valueOf('U'),
					Items.redstone,
					Character.valueOf('X'),
					new ItemStack(DCsAppleMilk.chalcedony, 1, i) });

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cLampOpaque, 1, i), new Object[] {
					new ItemStack(DCsAppleMilk.cLamp, 1, i),
					Blocks.gravel }));

			GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.cLamp, 1, i + 4), new Object[] {
					" U ",
					"UXU",
					" U ",
					Character.valueOf('U'),
					Blocks.glass,
					Character.valueOf('X'),
					new ItemStack(DCsAppleMilk.cLamp, 1, i) });
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.cLamp, 1, 8), new Object[] {
				" X ",
				"XYX",
				" X ",
				Character.valueOf('Y'),
				new ItemStack(DCsAppleMilk.cLamp, 1, 0),
				Character.valueOf('X'),
				"ingotSilver" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.cLamp, 1, 9), new Object[] {
				" X ",
				"XYX",
				" X ",
				Character.valueOf('Y'),
				new ItemStack(DCsAppleMilk.cLamp, 1, 1),
				Character.valueOf('X'),
				"ingotLead" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.cLamp, 1, 10), new Object[] {
				" X ",
				"XYX",
				" X ",
				Character.valueOf('Y'),
				new ItemStack(DCsAppleMilk.cLamp, 1, 2),
				Character.valueOf('X'),
				new ItemStack(Items.gold_ingot, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.cLamp, 1, 11), new Object[] {
				" X ",
				"XYX",
				" X ",
				Character.valueOf('Y'),
				new ItemStack(DCsAppleMilk.cLamp, 1, 3),
				Character.valueOf('X'),
				"ingotSteel" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.cLamp, 1, 11), new Object[] {
				" X ",
				"XYX",
				" X ",
				Character.valueOf('Y'),
				new ItemStack(DCsAppleMilk.cLamp, 1, 3),
				Character.valueOf('X'),
				"ingotBlackSteel" }));

	}

	static void addPrincess() {

		if (DCsConfig.useEXRecipe) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.princessClam, 1, 0), new Object[] {
					"XYX",
					" Z ",
					Character.valueOf('Z'),
					new ItemStack(DCsAppleMilk.clam, 1, 0),
					Character.valueOf('Y'),
					new ItemStack(Items.diamond, 1, 0),
					Character.valueOf('X'),
					"ingotGold" }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.princessClam, 1, 0), new Object[] {
					"XYX",
					" Z ",
					Character.valueOf('Z'),
					new ItemStack(DCsAppleMilk.clam, 1, 0),
					Character.valueOf('Y'),
					new ItemStack(Items.diamond, 1, 0),
					Character.valueOf('X'),
					new ItemStack(Items.gold_ingot, 1, 0) }));
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.princessClam, 1, 1), new Object[] {
				" Y ",
				"XZX",
				" Y ",
				Character.valueOf('Z'),
				new ItemStack(DCsAppleMilk.princessClam, 1, 0),
				Character.valueOf('Y'),
				new ItemStack(Items.diamond, 1, 0),
				Character.valueOf('X'),
				new ItemStack(Blocks.obsidian, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.princessClam, 1, 2), new Object[] {
				" Y ",
				"XZX",
				" Y ",
				Character.valueOf('Z'),
				new ItemStack(DCsAppleMilk.princessClam, 1, 0),
				Character.valueOf('Y'),
				new ItemStack(Items.emerald, 1, 0),
				Character.valueOf('X'),
				new ItemStack(Blocks.obsidian, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.princessClam, 1, 3), new Object[] {
				" Y ",
				"XZX",
				" Y ",
				Character.valueOf('Z'),
				new ItemStack(DCsAppleMilk.princessClam, 1, 0),
				Character.valueOf('Y'),
				new ItemStack(Items.quartz, 1, 0),
				Character.valueOf('X'),
				new ItemStack(Blocks.obsidian, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.princessClam, 1, 4), new Object[] {
				" Y ",
				"XZX",
				" Y ",
				Character.valueOf('Z'),
				new ItemStack(DCsAppleMilk.princessClam, 1, 0),
				Character.valueOf('Y'),
				new ItemStack(Items.dye, 1, 4),
				Character.valueOf('X'),
				new ItemStack(Blocks.obsidian, 1, 0) }));

		if (DCsConfig.charmRemain == 0) {
			GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.princessClam, 1, 3), new ItemStack(
					DCsAppleMilk.princessClam, 1, 3));
		}
	}

	static void addBottle() {

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (48 + 1)),
				new Object[] {
						new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						"drinkSake", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (48 + 2)),
				new Object[] {
						new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						"drinkBeer", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (48 + 3)),
				new Object[] {
						new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						"drinkWine", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (48 + 5)),
				new Object[] {
						new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						"drinkRum", }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (48 + 4)),
				new Object[] {
						new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						"drinkGin", }));
	}

	static void addCordial() {// 酒別にレシピがあり、量が多いので、for文で回しつつ個別メソッドを用意した

		String[] alcohol = new String[] {
				"bottleShothu",
				"bottleGin",
				"bottleRum",
				"bottleVodka",
				"bottleWhiskey",
				"bottleBrandy" };

		for (int i = 0; i < 6; i++) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.itemCordial, 1, 0), new Object[] {
					"dustSugar",
					alcohol[i],
					"cropApple" }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.itemCordial, 1, 4), new Object[] {
					"dustSugar",
					alcohol[i],
					"foodTea" }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.itemCordial, 1, 8), new Object[] {
					"dustSugar",
					alcohol[i],
					"cropCassis" }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.itemCordial, 1, 12), new Object[] {
					"dustSugar",
					alcohol[i],
					"cropPlum" }));
		}

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.itemCordial, 1, 16), new Object[] {
				"dustSugar",
				"bottleBrandy",
				"apricotSeed" }));

		// 酒造関係の素材もここ
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.moromi, 1, 0), new Object[] {
				"cropRice",
				"cropRice",
				"cropRice",
				new ItemStack(Items.bucket, 1, 0),
				"foodYeast" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.moromi, 1, 1), new Object[] {
				new ItemStack(Items.wheat, 1, 0),
				new ItemStack(Items.wheat, 1, 0),
				new ItemStack(Items.wheat, 1, 0),
				new ItemStack(Items.bucket, 1, 0),
				"foodYeast" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.moromi, 1, 2), new Object[] {
				"grape",
				"grape",
				"grape",
				new ItemStack(Items.bucket, 1, 0),
				"foodYeast" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.moromi, 1, 2), new Object[] {
				"cropGrape",
				"cropGrape",
				"cropGrape",
				new ItemStack(Items.bucket, 1, 0),
				"foodYeast" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.moromi, 1, 3), new Object[] {
				"cropReed",
				"cropReed",
				"cropReed",
				new ItemStack(Items.bucket, 1, 0),
				"foodYeast" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.moromi, 1, 3), new Object[] {
				new ItemStack(Items.reeds),
				new ItemStack(Items.reeds),
				new ItemStack(Items.reeds),
				new ItemStack(Items.bucket, 1, 0),
				"foodYeast" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.moromi, 1, 4), new Object[] {
				"cropPotato",
				"cropPotato",
				"cropPotato",
				new ItemStack(Items.bucket, 1, 0),
				"foodYeast" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.moromi, 1, 4), new Object[] {
				new ItemStack(Items.potato),
				new ItemStack(Items.potato),
				new ItemStack(Items.potato),
				new ItemStack(Items.bucket, 1, 0),
				"foodYeast" }));

		// vodka
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (48 + 6)),
				new Object[] {
						new ItemStack(DCsAppleMilk.emptyBottle, 1, 0),
						new ItemStack(DCsAppleMilk.bucketYoungAlcohol, 1, 4),
						new ItemStack(Items.coal, 1, 1), }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.barrel, 1, 0), new Object[] {
				" X ",
				"XZX",
				" X ",
				Character.valueOf('Z'),
				"slimeball",
				Character.valueOf('X'),
				"logWood" }));

	}

	static void addMachines() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.processor, 1), new Object[] {
				"XXX",
				"ZYZ",
				"ZWZ",
				Character.valueOf('W'),
				"gearIron",
				Character.valueOf('Z'),
				new ItemStack(Items.iron_ingot, 1),
				Character.valueOf('Y'),
				new ItemStack(Items.iron_sword, 1),
				Character.valueOf('X'),
				new ItemStack(Blocks.glass, 1) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.advProcessor, 1), new Object[] {
				"ZXZ",
				"ZYZ",
				"ZWZ",
				Character.valueOf('W'),
				new ItemStack(DCsAppleMilk.batteryItem, 1, 0),
				Character.valueOf('Z'),
				new ItemStack(Items.iron_ingot, 1),
				Character.valueOf('Y'),
				new ItemStack(DCsAppleMilk.processor, 1),
				Character.valueOf('X'),
				new ItemStack(Items.diamond_sword, 1) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.advProcessor, 1), new Object[] {
				"ZXZ",
				"ZYZ",
				"ZWZ",
				Character.valueOf('W'),
				new ItemStack(DCsAppleMilk.gelBat, 1, 0),
				Character.valueOf('Z'),
				new ItemStack(Items.iron_ingot, 1),
				Character.valueOf('Y'),
				new ItemStack(DCsAppleMilk.processor, 1),
				Character.valueOf('X'),
				new ItemStack(Items.diamond_sword, 1) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.evaporator, 1), new Object[] {
				" X ",
				"ZYZ",
				"XWX",
				Character.valueOf('W'),
				"gearIron",
				Character.valueOf('Z'),
				new ItemStack(Items.glass_bottle, 1),
				Character.valueOf('Y'),
				new ItemStack(Items.blaze_rod, 1),
				Character.valueOf('X'),
				new ItemStack(Blocks.stained_hardened_clay, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.rotaryDial, 1, 0), new Object[] {
				"XXX",
				"ZYZ",
				"XXX",
				Character.valueOf('X'),
				new ItemStack(Blocks.stained_hardened_clay, 1, 15),
				Character.valueOf('Y'),
				"ingotGold",
				Character.valueOf('Z'),
				new ItemStack(DCsAppleMilk.EXItems, 1, 3) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.rotaryDial, 1, 0), new Object[] {
				"XXX",
				"ZYZ",
				"XXX",
				Character.valueOf('X'),
				new ItemStack(Blocks.stained_hardened_clay, 1, 15),
				Character.valueOf('Y'),
				new ItemStack(Items.gold_ingot, 1, 0),
				Character.valueOf('Z'),
				new ItemStack(DCsAppleMilk.EXItems, 1, 3) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.chalcenonyPanel, 1, 0), new Object[] {
				"XX",
				Character.valueOf('X'),
				new ItemStack(DCsAppleMilk.chalcedony, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.slotPanel, 4, 0), new Object[] {
				"X X",
				" Y ",
				"X X",
				Character.valueOf('X'),
				new ItemStack(DCsAppleMilk.chalcedony, 1, 0),
				Character.valueOf('Y'),
				new ItemStack(Items.iron_ingot, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.stickCarbon, 1), new Object[] {
				"  X",
				" X ",
				"X  ",
				Character.valueOf('X'),
				"dustCoal" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.redGel, 1, 0), new Object[] {
				"cropYuzu",
				new ItemStack(Items.redstone, 1, 0),
				"slimeball" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.yuzuGel, 1, 0), new Object[] {
				"cropYuzu",
				"slimeball" }));

		// 柚子電池
		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.batteryItem, 1, 2), new ItemStack(
				DCsAppleMilk.leafTea, 1, 3), new ItemStack(Items.gold_nugget, 1, 0), new ItemStack(
				DCsAppleMilk.EXItems, 1, 7));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.batteryItem, 1, 2), new Object[] {
				"nuggetTin",
				new ItemStack(DCsAppleMilk.leafTea, 1, 3),
				"nuggetCopper" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.batteryItem, 1, 2), new Object[] {
				"nuggetIron",
				new ItemStack(DCsAppleMilk.leafTea, 1, 3),
				"stickCarbon" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.batteryItem, 1, 2), new Object[] {
				"nuggetCopper",
				new ItemStack(DCsAppleMilk.leafTea, 1, 3),
				"stickCarbon" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.batteryItem, 1, 2), new Object[] {
				"nuggetGold",
				new ItemStack(DCsAppleMilk.leafTea, 1, 3),
				"stickCarbon" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.batteryItem, 1, 2), new Object[] {
				"nuggetSilver",
				new ItemStack(DCsAppleMilk.leafTea, 1, 3),
				"stickCarbon" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.batteryItem, 1, 2), new Object[] {
				"nuggetLead",
				new ItemStack(DCsAppleMilk.leafTea, 1, 3),
				"stickCarbon" }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.batteryItem, 1, 2), new Object[] {
				"nuggetFlint",
				new ItemStack(DCsAppleMilk.leafTea, 1, 3),
				"stickCarbon" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.yuzuBat, 1, 0), new Object[] {
				"XXX",
				"XYX",
				"XXX",
				Character.valueOf('X'),
				new ItemStack(DCsAppleMilk.batteryItem, 1, 2),
				Character.valueOf('Y'),
				"stickCarbon" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.gelBat, 1, 0), new Object[] {
				"XYX",
				"YZY",
				"XYX",
				Character.valueOf('X'),
				"stickCarbon",
				Character.valueOf('Y'),
				new ItemStack(Blocks.glass_pane, 1, 0),
				Character.valueOf('Z'),
				new ItemStack(DCsAppleMilk.redGel, 1, 0) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.batBox, 1, 0), new Object[] {
				"XYX",
				"WZW",
				"XVX",
				Character.valueOf('X'),
				new ItemStack(Items.iron_ingot, 1, 0),
				Character.valueOf('Y'),
				new ItemStack(Items.redstone, 1, 0),
				Character.valueOf('Z'),
				new ItemStack(DCsAppleMilk.gelBat, 1, 0),
				Character.valueOf('V'),
				new ItemStack(Blocks.chest, 1, 0),
				Character.valueOf('W'),
				"gearIron" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.handleEngine, 1, 0), new Object[] {
				" X ",
				" Y ",
				"XZX",
				Character.valueOf('X'),
				"ingotIron",
				Character.valueOf('Y'),
				"gearChalcedony",
				Character.valueOf('Z'),
				new ItemStack(DCsAppleMilk.redGel, 1, 0) }));

		// 歯板
		ItemStack plate = new ItemStack(DCsAppleMilk.jawPlate, 1, 0);
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("dcsJawCount", 64);
		plate.setTagCompound(tag);
		GameRegistry.addRecipe(new ShapedOreRecipe(plate, new Object[] {
				"XXX",
				" Y ",
				Character.valueOf('X'),
				new ItemStack(Blocks.stained_hardened_clay, 1, 32767),
				Character.valueOf('Y'),
				"toolGrater" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.jawPlate, 1, 1), new Object[] {
				"XXX",
				" Y ",
				Character.valueOf('X'),
				new ItemStack(Blocks.cobblestone, 1, 0),
				Character.valueOf('Y'),
				"toolGrater" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.jawPlate, 1, 2), new Object[] {
				"XXX",
				" Y ",
				Character.valueOf('X'),
				"blockChalcedony",
				Character.valueOf('Y'),
				"toolGrater" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.jawPlate, 1, 3), new Object[] {
				"XXX",
				" Y ",
				Character.valueOf('X'),
				"ingotIron",
				Character.valueOf('Y'),
				"toolGrater" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.jawPlate, 1, 4), new Object[] {
				"XXX",
				" Y ",
				Character.valueOf('X'),
				"ingotSteel",
				Character.valueOf('Y'),
				"toolGrater" }));

		// カノン
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.yuzuGatling, 1, 0), new Object[] {
				"  X",
				"YXZ",
				"WV ",
				Character.valueOf('X'),
				"blockChalcedony",
				Character.valueOf('Y'),
				"gearIron",
				Character.valueOf('V'),
				new ItemStack(DCsAppleMilk.gelBat, 1, 0),
				Character.valueOf('Z'),
				new ItemStack(DCsAppleMilk.firestarter, 1, 32767),
				Character.valueOf('W'),
				new ItemStack(DCsAppleMilk.cardboard, 1, 2) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.fossilCannon, 1, 0), new Object[] {
				"  X",
				"YX ",
				"W  ",
				Character.valueOf('X'),
				new ItemStack(DCsAppleMilk.fossilScale),
				Character.valueOf('Y'),
				new ItemStack(Items.nether_star),
				Character.valueOf('W'),
				new ItemStack(DCsAppleMilk.yuzuGatling, 1) }));
	}

	static void addMaterials() {

		// sapling & seed
		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.saplingTea, 1, 1), new ItemStack(
				DCsAppleMilk.leafTea, 1, 2), new ItemStack(DCsAppleMilk.saplingTea, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.saplingTea, 1, 2), new ItemStack(Blocks.red_flower,
				1, 0), new ItemStack(DCsAppleMilk.saplingTea, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.saplingYuzu, 1, 0), new ItemStack(
				DCsAppleMilk.leafTea, 1, 3), new ItemStack(Blocks.sapling, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.itemMintSeed, 1, 0), new ItemStack(
				Items.wheat_seeds, 1, 0), new ItemStack(DCsAppleMilk.leafTea, 1, 1));

		// misc
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.icyCrystal, 1), new Object[] {
				"XYX",
				"YZY",
				"XYX",
				Character.valueOf('X'),
				new ItemStack(Items.snowball, 1),
				Character.valueOf('Y'),
				new ItemStack(Blocks.ice, 1),
				Character.valueOf('Z'),
				new ItemStack(Items.emerald, 1) }));

		// oilの詰め替えレシピ
		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.bottleCamOil, 5, 0), new ItemStack(
				DCsAppleMilk.emptyBottle, 1, 0), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0), new ItemStack(
				DCsAppleMilk.emptyBottle, 1, 0), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0), new ItemStack(
				DCsAppleMilk.emptyBottle, 1, 0), new ItemStack(DCsAppleMilk.bucketCamOil, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.bottleVegiOil, 5, 0), new ItemStack(
				DCsAppleMilk.emptyBottle, 1, 0), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0), new ItemStack(
				DCsAppleMilk.emptyBottle, 1, 0), new ItemStack(DCsAppleMilk.emptyBottle, 1, 0), new ItemStack(
				DCsAppleMilk.emptyBottle, 1, 0), new ItemStack(DCsAppleMilk.bucketVegiOil, 1, 0));

		// extended vanilla recipe
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks, 4, 2), new ItemStack(DCsAppleMilk.logYuzu, 1, 0));

		GameRegistry.addShapelessRecipe(new ItemStack(DCsAppleMilk.EXItems, 1, 1), new ItemStack(Items.leather, 1));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.EXItems, 9, 7), new ItemStack(
				Items.iron_ingot, 1), "toolGrater"));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.EXItems, 9, 14), new ItemStack(
				Items.flint, 1), "toolGrater"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.iron_ingot, 1), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				Character.valueOf('X'),
				new ItemStack(DCsAppleMilk.EXItems, 1, 7) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.flint, 1), new Object[] {
				"XXX",
				"XXX",
				"XXX",
				Character.valueOf('X'),
				new ItemStack(DCsAppleMilk.EXItems, 1, 14) }));

		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.sticky_piston, 1), new ItemStack(DCsAppleMilk.EXItems, 1,
				1), new ItemStack(Blocks.piston, 1));

		GameRegistry.addShapelessRecipe(new ItemStack(Items.magma_cream, 1), new ItemStack(DCsAppleMilk.EXItems, 1, 1),
				new ItemStack(Items.blaze_powder, 1));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.dye, 1, 2), "toolGrater", new ItemStack(
				DCsAppleMilk.leafTea, 1, 0)));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.EXItems, 1, 6), "toolGrater",
				new ItemStack(DCsAppleMilk.clam, 1, 0)));

		GameRegistry.addRecipe(new ItemStack(Items.lead, 1), new Object[] {
				"TT ",
				"TS ",
				"  T",
				Character.valueOf('T'),
				Items.string,
				Character.valueOf('S'),
				new ItemStack(DCsAppleMilk.EXItems, 1, 1), });

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.string, 3, 0), new ItemStack(Blocks.wool, 1,
				32767), "toolGrater"));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.dustWood, 1, 1), new ItemStack(
				Items.coal, 1, 32767), "toolGrater"));
	}

	static void addCharms() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.incenseBase, 1), new Object[] {
				" X ",
				"XYX",
				"ZZZ",
				Character.valueOf('X'),
				new ItemStack(DCsAppleMilk.chalcedony, 1, 0),
				Character.valueOf('Y'),
				new ItemStack(DCsAppleMilk.princessClam, 1, 0),
				Character.valueOf('Z'),
				new ItemStack(Items.iron_ingot, 1, 0) }));

		final Item[] incenses = new Item[] {
				DCsAppleMilk.incenseApple,
				DCsAppleMilk.incenseRose,
				DCsAppleMilk.incenseMint,
				DCsAppleMilk.incenseYuzu,
				DCsAppleMilk.incenseClam,
				DCsAppleMilk.incenseIce,
				DCsAppleMilk.incenseLavender,
				DCsAppleMilk.incenseVanilla,
				DCsAppleMilk.incenseSandalwood,
				DCsAppleMilk.incenseAgar,
				DCsAppleMilk.incenseFrank, };

		for (int i = 0; i < 11; i++) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(incenses[i], 1, 0), new Object[] {
					"dustWood",
					new ItemStack(DCsAppleMilk.bottleCamOil, 1, 0),
					new ItemStack(DCsAppleMilk.essentialOil, 1, i) }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(incenses[i], 1, 0), new Object[] {
					"dustWood",
					new ItemStack(DCsAppleMilk.bottleVegiOil, 1, 0),
					new ItemStack(DCsAppleMilk.essentialOil, 1, i) }));
		}

	}

	static void addSmelting() {

		GameRegistry.addSmelting(Items.apple, new ItemStack(DCsAppleMilk.bakedApple, 1), 0.3F);

		GameRegistry.addSmelting(DCsAppleMilk.woodBox, new ItemStack(DCsAppleMilk.charcoalBox, 1, 0), 0.1F);

		// コンフィグでoffできるように
		if (!DCsConfig.hardLeatherRecipe) {
			GameRegistry.addSmelting(Items.rotten_flesh, new ItemStack(Items.leather, 1), 0F);
		}

		GameRegistry.addSmelting(DCsAppleMilk.flintBlock, new ItemStack(DCsAppleMilk.chalcedony, 1, 0), 0.5F);

		GameRegistry.addSmelting(DCsAppleMilk.icyCrystal, new ItemStack(Items.quartz, 1), 0.5F);

		GameRegistry.addSmelting(new ItemStack(DCsAppleMilk.EXItems, 1, 1), new ItemStack(Items.slime_ball, 1), 0.1F);

		GameRegistry.addSmelting(new ItemStack(DCsAppleMilk.leafTea, 1, 0), new ItemStack(DCsAppleMilk.foodTea, 1, 0),
				0.2F);

		GameRegistry.addSmelting(new ItemStack(DCsAppleMilk.clam, 1, 0), new ItemStack(DCsAppleMilk.clam, 1, 1), 0.2F);

		GameRegistry.addSmelting(new ItemStack(DCsAppleMilk.emptyBottle, 1, 0), new ItemStack(DCsAppleMilk.EXItems, 1,
				5), 0.2F);

		GameRegistry.addSmelting(new ItemStack(DCsAppleMilk.EXItems, 1, 5), new ItemStack(Blocks.glass, 1, 0), 0.2F);

		GameRegistry.addSmelting(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 0), new ItemStack(DCsAppleMilk.EXItems,
				8, 0), 0.2F);

		GameRegistry.addSmelting(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 1), new ItemStack(
				DCsAppleMilk.icyToffyApple, 4, 4), 0.2F);

		GameRegistry.addSmelting(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 2), new ItemStack(
				DCsAppleMilk.icyToffyApple, 4, 5), 0.2F);

		GameRegistry.addSmelting(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 3), new ItemStack(
				DCsAppleMilk.icyToffyApple, 4, 6), 0.2F);

		GameRegistry.addSmelting(new ItemStack(DCsAppleMilk.dustWood, 1, 0),
				new ItemStack(DCsAppleMilk.dustWood, 1, 2), 0.2F);

		GameRegistry.addSmelting(new ItemStack(DCsAppleMilk.woodPanel, 1, 0),
				new ItemStack(DCsAppleMilk.dustWood, 8, 2), 0.2F);

		GameRegistry.addSmelting(new ItemStack(DCsAppleMilk.logYuzu, 1, 0), new ItemStack(Items.coal, 1, 1), 0.2F);

		for (int i = 0; i < 6; i++) {
			GameRegistry.addSmelting(new ItemStack(DCsAppleMilk.jawPlate, 1, i), new ItemStack(DCsAppleMilk.jawPlate,
					1, i), 0.0F);
		}
	}

	/*
	 * この部分は、複数のModの水入り容器を取得した後に呼び出すため、postInitに呼ぶ
	 * 水入り容器の取得はLoadModHandlerを利用
	 */
	public void addInstantTea() {

		this.waterContainer.add(new ItemStack(Items.water_bucket, 1, 0));
		this.waterContainer.add(new ItemStack(Items.potionitem, 1, 0));

		if (LoadModHandler.getArray("containerWater") != null && !LoadModHandler.getArray("containerWater").isEmpty()) {
			this.waterContainer.addAll(LoadModHandler.getArray("containerWater"));
		}

		for (ItemStack water : waterContainer) {

			if (water == null || water.getItem() == null)
				continue;

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.teacupBlock, 1, 4),// greentea
					new Object[] {
							new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
							water,
							new ItemStack(DCsAppleMilk.foodTea, 1, 0) }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.teacupBlock, 1, 2),// tea
					new Object[] {
							new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
							water,
							new ItemStack(DCsAppleMilk.foodTea, 1, 1) }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.teacupBlock, 1, 6),// cocoa
					new Object[] {
							new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
							water,
							new ItemStack(Items.dye, 1, 3) }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.teacupBlock, 1, 8),// juice
					new Object[] {
							new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
							water,
							new ItemStack(DCsAppleMilk.gratedApple, 1, 0) }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.teacupBlock, 1, 8),// juice
					new Object[] {
							new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
							water,
							new ItemStack(DCsAppleMilk.gratedApple, 1, 1) }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.teacupBlock, 1, 10),// lemon
					new Object[] {
							new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
							water,
							new ItemStack(DCsAppleMilk.gratedApple, 1, 2) }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.teacupBlock, 1, 12),// coffee
					new Object[] {
							new ItemStack(DCsAppleMilk.emptyCup, 1, 0),
							water,
							new ItemStack(DCsAppleMilk.gratedApple, 1, 3) }));

		}

	}

	// カスタムカクテル
	public void addCocktailSPRecipe() {
		String[] string1 = DCsConfigCocktail.recipe1;
		String[] string2 = DCsConfigCocktail.recipe2;
		String[] string3 = DCsConfigCocktail.recipe3;

		Object[] rec1 = new Object[string1.length];
		Object[] rec2 = new Object[string2.length];
		Object[] rec3 = new Object[string3.length];

		for (int i = 0; i < string1.length; i++) {
			String str = string1[i];

			if (str.contains(":")) {
				String[] split = str.split(":");
				ItemStack input = new ItemStack(DCsAppleMilk.clam, 1, 0);

				try {
					if (split.length == 1) {
						Item item1 = Util.getModItem("minecraft", split[0]);
						if (item1 != null) {
							input = new ItemStack(item1, 1, 0);
						}
					} else if (split.length == 2) {
						Item item2 = Util.getModItem(split[0], split[1]);
						input = new ItemStack(item2, 1, 0);
					} else if (split.length == 3) {
						Item item2 = Util.getModItem(split[0], split[1]);
						int m = Integer.parseInt(split[2]);
						input = new ItemStack(item2, 1, m);
					}
				} catch (Exception e) {

				}

				rec1[i] = input;
			} else {
				// OreDitionaryと予想して、文字列のまま加える
				rec1[i] = str;
			}
		}

		for (int k = 0; k < string2.length; k++) {
			String str = string2[k];

			if (str.contains(":")) {
				String[] split = str.split(":");
				ItemStack input = new ItemStack(DCsAppleMilk.clam, 1, 0);

				try {
					if (split.length == 1) {
						Item item1 = Util.getModItem("minecraft", split[0]);
						if (item1 != null) {
							input = new ItemStack(item1, 1, 0);
						}
					} else if (split.length == 2) {
						Item item2 = Util.getModItem(split[0], split[1]);
						input = new ItemStack(item2, 1, 0);
					} else if (split.length == 3) {
						Item item2 = Util.getModItem(split[0], split[1]);
						int m = Integer.parseInt(split[2]);
						input = new ItemStack(item2, 1, m);
					}
				} catch (Exception e) {

				}

				rec2[k] = input;
			} else {
				// OreDitionaryと予想して、文字列のまま加える
				rec2[k] = str;
			}
		}

		for (int j = 0; j < string3.length; j++) {
			String str = string3[j];

			if (str.contains(":")) {
				String[] split = str.split(":");
				ItemStack input = new ItemStack(DCsAppleMilk.clam, 1, 0);

				try {
					if (split.length == 1) {
						Item item1 = Util.getModItem("minecraft", split[0]);
						if (item1 != null) {
							input = new ItemStack(item1, 1, 0);
						}
					} else if (split.length == 2) {
						Item item2 = Util.getModItem(split[0], split[1]);
						input = new ItemStack(item2, 1, 0);
					} else if (split.length == 3) {
						Item item2 = Util.getModItem(split[0], split[1]);
						int m = Integer.parseInt(split[2]);
						input = new ItemStack(item2, 1, m);
					}
				} catch (Exception e) {

				}

				rec3[j] = input;
			} else {
				// OreDitionaryと予想して、文字列のまま加える
				rec3[j] = str;
			}
		}

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktailSP, 1, 0), rec1));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktailSP, 1, 1), rec2));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktailSP, 1, 2), rec3));
	}

	public void addKelpRecipe() {
		if (LoadModHandler.getArray("seaWeed") != null && !LoadModHandler.getArray("seaWeed").isEmpty()) {
			this.seaWeed.addAll(LoadModHandler.getArray("seaWeed"));
		}

		for (ItemStack kelp : seaWeed) {
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 9), new Object[] {
					"toolGrater",
					"miso",
					kelp,
					"tofuKinu" }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 9), new Object[] {
					"toolGrater",
					"miso",
					kelp,
					"tofuMomen" }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 10), new Object[] {
					"toolGrater",
					new ItemStack(DCsAppleMilk.clam, 1, 0),
					kelp }));
		}
	}

	// 金属ナゲットのレシピ登録も、他MODのアイテム追加が終わるpostInitで呼ぶ
	public void addMetalRecipe() {
		ArrayList<ItemStack> woodRubber = OreDictionary.getOres("woodRubber");
		if (woodRubber != null && woodRubber.size() > 0 && woodRubber.get(0) != null) {
			ItemStack ret = woodRubber.get(0);
			int d = ret.getItemDamage();
			if (d == OreDictionary.WILDCARD_VALUE)
				d = 0;

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ret.getItem(), 9, d), new Object[] {
					"X",
					'X',
					new ItemStack(DCsAppleMilk.woodBox, 1, 4) }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.woodBox, 1, 4), new Object[] {
					"XXX",
					"XXX",
					"XXX",
					Character.valueOf('X'),
					"woodRubber" }));
		}

		ArrayList<ItemStack> ingotTin = OreDictionary.getOres("ingotTin");
		if (ingotTin.size() > 0) {
			ItemStack ret = ingotTin.get(0);

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.EXItems, 9, 8), new Object[] {
					"ingotTin",
					"toolGrater" }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ret.getItem(), 1, ret.getItemDamage()),
					new Object[] {
							"XXX",
							"XXX",
							"XXX",
							Character.valueOf('X'),
							new ItemStack(DCsAppleMilk.EXItems, 1, 8) }));
		}

		ArrayList<ItemStack> ingotCopper = OreDictionary.getOres("ingotCopper");
		if (ingotCopper.size() > 0) {
			ItemStack ret = ingotCopper.get(0);

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.EXItems, 9, 9), new Object[] {
					"ingotCopper",
					"toolGrater" }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ret.getItem(), 1, ret.getItemDamage()),
					new Object[] {
							"XXX",
							"XXX",
							"XXX",
							Character.valueOf('X'),
							new ItemStack(DCsAppleMilk.EXItems, 1, 9) }));
		}

		ArrayList<ItemStack> ingotSilver = OreDictionary.getOres("ingotSilver");
		if (ingotSilver.size() > 0) {
			ItemStack ret = ingotSilver.get(0);

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.EXItems, 9, 10), new Object[] {
					"ingotSilver",
					"toolGrater" }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ret.getItem(), 1, ret.getItemDamage()),
					new Object[] {
							"XXX",
							"XXX",
							"XXX",
							Character.valueOf('X'),
							new ItemStack(DCsAppleMilk.EXItems, 1, 10) }));
		}

		ArrayList<ItemStack> ingotSteel = OreDictionary.getOres("ingotSteel");
		if (ingotSteel.size() > 0) {
			ItemStack ret = ingotSteel.get(0);

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.EXItems, 9, 11), new Object[] {
					"ingotSteel",
					"toolGrater" }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ret.getItem(), 1, ret.getItemDamage()),
					new Object[] {
							"XXX",
							"XXX",
							"XXX",
							Character.valueOf('X'),
							new ItemStack(DCsAppleMilk.EXItems, 1, 11) }));
		}

		ArrayList<ItemStack> ingotLead = OreDictionary.getOres("ingotLead");
		if (ingotLead.size() > 0) {
			ItemStack ret = ingotLead.get(0);

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.EXItems, 9, 12), new Object[] {
					"ingotLead",
					"toolGrater" }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ret.getItem(), 1, ret.getItemDamage()),
					new Object[] {
							"XXX",
							"XXX",
							"XXX",
							Character.valueOf('X'),
							new ItemStack(DCsAppleMilk.EXItems, 1, 12) }));
		}

		ArrayList<ItemStack> ingotBronze = OreDictionary.getOres("ingotBronze");
		if (ingotBronze.size() > 0) {
			ItemStack ret = ingotBronze.get(0);

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.EXItems, 9, 13), new Object[] {
					"ingotBronze",
					"toolGrater" }));

			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ret.getItem(), 1, ret.getItemDamage()),
					new Object[] {
							"XXX",
							"XXX",
							"XXX",
							Character.valueOf('X'),
							new ItemStack(DCsAppleMilk.EXItems, 1, 13) }));
		}
	}

	public static void addCardboardRecipe() {
		ArrayList<ItemStack> coffee = new ArrayList<ItemStack>(OreDictionary.getOres("cropCoffee"));
		if (!coffee.isEmpty()) {
			for (ItemStack i : coffee) {
				if (i == null || i.getItem() == null)
					continue;

				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(i.getItem(), 9, i.getItemDamage()),
						new Object[] { new ItemStack(DCsAppleMilk.cardboard, 1, 4) }));

				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.cardboard, 1, 4), new Object[] {
						"XXX",
						"XXX",
						"XXX",
						'X',
						new ItemStack(i.getItem(), 1, i.getItemDamage()) }));
			}
		}

		ArrayList<ItemStack> bamboo = new ArrayList<ItemStack>(OreDictionary.getOres("bamboo"));
		if (!bamboo.isEmpty()) {
			for (ItemStack i : bamboo) {
				if (i == null || i.getItem() == null)
					continue;

				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(i.getItem(), 9, i.getItemDamage()),
						new Object[] { new ItemStack(DCsAppleMilk.cardboard, 1, 5) }));

				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.cardboard, 1, 5), new Object[] {
						"XXX",
						"XXX",
						"XXX",
						'X',
						new ItemStack(i.getItem(), 1, i.getItemDamage()) }));
			}
		}

		ArrayList<ItemStack> tomato = new ArrayList<ItemStack>(OreDictionary.getOres("cropTomato"));
		tomato.addAll(OreDictionary.getOres("tomato"));
		if (!tomato.isEmpty()) {
			for (ItemStack i : tomato) {
				if (i == null || i.getItem() == null)
					continue;

				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(i.getItem(), 9, i.getItemDamage()),
						new Object[] { new ItemStack(DCsAppleMilk.cardboard, 1, 6) }));

				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.cardboard, 1, 6), new Object[] {
						"XXX",
						"XXX",
						"XXX",
						'X',
						new ItemStack(i.getItem(), 1, i.getItemDamage()) }));
			}
		}

		ArrayList<ItemStack> grape = new ArrayList<ItemStack>(OreDictionary.getOres("cropGrape"));
		grape.addAll(OreDictionary.getOres("grape"));
		if (!grape.isEmpty()) {
			for (ItemStack i : grape) {
				if (i == null || i.getItem() == null)
					continue;

				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(i.getItem(), 9, i.getItemDamage()),
						new Object[] { new ItemStack(DCsAppleMilk.cardboard, 1, 7) }));

				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.cardboard, 1, 7), new Object[] {
						"XXX",
						"XXX",
						"XXX",
						'X',
						new ItemStack(i.getItem(), 1, i.getItemDamage()) }));
			}
		}
	}
}
