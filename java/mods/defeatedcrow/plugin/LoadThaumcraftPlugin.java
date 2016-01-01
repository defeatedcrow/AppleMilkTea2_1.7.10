package mods.defeatedcrow.plugin;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.registry.GameRegistry;

public class LoadThaumcraftPlugin {

	public static ItemStack thaumicSilverwood;
	public static ItemStack thaumicGreatwood;
	public static ItemStack thaumicNitor;

	public void load() {
		ModContainer mod = Loader.instance().getIndexedModList().get("Thaumcraft");
		String s = mod.getVersion();
		AMTLogger.debugInfo("Thaumcraft version : " + s);
		if (s.contains("4.2.")) {
			try {
				this.integration();
			} catch (Exception e) {
				// 何もしない
			}
		}
	}

	public void integration() {
		// TC4apiの機能でアイテムを取得
		this.thaumicGreatwood = new ItemStack(ItemApi.getBlock("blockMagicalLog", 0).getItem(), 1, 0);
		this.thaumicSilverwood = new ItemStack(ItemApi.getBlock("blockMagicalLog", 1).getItem(), 1, 1);
		this.thaumicNitor = new ItemStack(ItemApi.getBlock("blockAiry", 1).getItem(), 1, 1);

		// items
		// foodsにaspectsは無し。食い物の相は似通うので…

		// tools
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.DCgrater), new int[] { 32767 }, new AspectList()
				.add(Aspect.TOOL, 2).add(Aspect.ENTROPY, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.firestarter), new int[] { 32767 }, new AspectList()
				.add(Aspect.TOOL, 2).add(Aspect.FIRE, 1).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.chalcedonyHammer), new int[] { 32767 },
				new AspectList().add(Aspect.TOOL, 2).add(Aspect.CRYSTAL, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.chalcedonyKnife), new int[] { 32767 },
				new AspectList().add(Aspect.TOOL, 2).add(Aspect.CRYSTAL, 1).add(Aspect.WEAPON, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.pruningShears), new int[] { 32767 },
				new AspectList().add(Aspect.TOOL, 2).add(Aspect.CRYSTAL, 1).add(Aspect.CROP, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.monocle), new int[] { 32767 },
				new AspectList().add(Aspect.TOOL, 2).add(Aspect.CRYSTAL, 1).add(Aspect.SENSES, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.onixSword), new int[] { 32767 }, new AspectList()
				.add(Aspect.WEAPON, 2).add(Aspect.CRYSTAL, 1).add(Aspect.MAGIC, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.chopsticks), new int[] { 32767 },
				new AspectList().add(Aspect.TOOL, 1));

		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.yuzuGatling), new int[] { 32767 }, new AspectList()
				.add(Aspect.WEAPON, 3).add(Aspect.ENERGY, 1).add(Aspect.HARVEST, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.fossilCannon), new int[] { 32767 }, new AspectList()
				.add(Aspect.WEAPON, 3).add(Aspect.ENERGY, 1).add(Aspect.WATER, 1));

		// battery
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.batteryItem), new int[] {
				0,
				1,
				2 }, new AspectList().add(Aspect.ENERGY, 3));

		// magic
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.essentialOil), new int[] { 32767 },
				new AspectList().add(Aspect.SENSES, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.incenseAgar), new int[] { 0 },
				new AspectList().add(Aspect.SENSES, 1).add(Aspect.MAGIC, 1).add(Aspect.WATER, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.incenseApple), new int[] { 0 }, new AspectList()
				.add(Aspect.SENSES, 1).add(Aspect.MAGIC, 1).add(Aspect.FLIGHT, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.incenseClam), new int[] { 0 },
				new AspectList().add(Aspect.SENSES, 1).add(Aspect.MAGIC, 1).add(Aspect.LIFE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.incenseFrank), new int[] { 0 }, new AspectList()
				.add(Aspect.SENSES, 1).add(Aspect.MAGIC, 1).add(Aspect.AURA, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.incenseIce), new int[] { 0 },
				new AspectList().add(Aspect.SENSES, 1).add(Aspect.MAGIC, 1).add(Aspect.COLD, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.incenseLavender), new int[] { 0 }, new AspectList()
				.add(Aspect.SENSES, 1).add(Aspect.MAGIC, 1).add(Aspect.MIND, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.incenseMint), new int[] { 0 },
				new AspectList().add(Aspect.SENSES, 1).add(Aspect.MAGIC, 1).add(Aspect.AIR, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.incenseRose), new int[] { 0 },
				new AspectList().add(Aspect.SENSES, 1).add(Aspect.MAGIC, 1).add(Aspect.HEAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.incenseSandalwood), new int[] { 0 },
				new AspectList().add(Aspect.SENSES, 1).add(Aspect.MAGIC, 1).add(Aspect.SOUL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.princessClam), new int[] { 0 }, new AspectList()
				.add(Aspect.MAGIC, 2).add(Aspect.WATER, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.princessClam), new int[] { 1 }, new AspectList()
				.add(Aspect.MAGIC, 5).add(Aspect.HARVEST, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.princessClam), new int[] { 2 }, new AspectList()
				.add(Aspect.MAGIC, 5).add(Aspect.SOUL, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.princessClam), new int[] { 3 }, new AspectList()
				.add(Aspect.MAGIC, 5).add(Aspect.FLIGHT, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.princessClam), new int[] { 4 }, new AspectList()
				.add(Aspect.MAGIC, 5).add(Aspect.LIGHT, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.strangeSlag), new int[] { 0 },
				new AspectList().add(Aspect.MAGIC, 2).add(Aspect.METAL, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.fossilScale), new int[] { 0 },
				new AspectList().add(Aspect.MAGIC, 2).add(Aspect.WATER, 3));

		// materials
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.inkStick), new int[] { 0 },
				new AspectList().add(Aspect.SENSES, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.icyCrystal), new int[] { 0 },
				new AspectList().add(Aspect.COLD, 3));

		// fruids
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.bottleCamOil), new int[] { 0 }, new AspectList()
				.add(Aspect.WATER, 1).add(Aspect.PLANT, 1).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.bottleVegiOil), new int[] { 0 }, new AspectList()
				.add(Aspect.WATER, 1).add(Aspect.PLANT, 1).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.bucketCamOil), new int[] { 0 }, new AspectList()
				.add(Aspect.WATER, 2).add(Aspect.PLANT, 1).add(Aspect.FIRE, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.bucketVegiOil), new int[] { 0 }, new AspectList()
				.add(Aspect.WATER, 2).add(Aspect.PLANT, 1).add(Aspect.FIRE, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.bucketYoungAlcohol), new int[] { 32767 },
				new AspectList().add(Aspect.WATER, 2).add(Aspect.SENSES, 1));

		// blocks
		// containers
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.woodBox), new int[] { 5 },
				new AspectList().add(Aspect.TREE, 9).add(Aspect.MAGIC, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.woodBox), new int[] { 6 },
				new AspectList().add(Aspect.TREE, 9).add(Aspect.MAGIC, 3).add(Aspect.ORDER, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.woodBox), new int[] {
				0,
				1,
				2,
				3,
				10 }, new AspectList().add(Aspect.TREE, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.woodBox), new int[] { 4 },
				new AspectList().add(Aspect.TREE, 8).add(Aspect.SLIME, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.woodBox), new int[] { 7 },
				new AspectList().add(Aspect.TREE, 8).add(Aspect.ENERGY, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.woodBox), new int[] { 8 },
				new AspectList().add(Aspect.TREE, 8).add(Aspect.WEATHER, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.woodBox), new int[] { 9 },
				new AspectList().add(Aspect.TREE, 8).add(Aspect.WEATHER, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.appleBox), new int[] { 0 },
				new AspectList().add(Aspect.PLANT, 8).add(Aspect.CROP, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.charcoalBox), new int[] { 0 },
				new AspectList().add(Aspect.FIRE, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.gunpowderContainer), new int[] { 0 },
				new AspectList().add(Aspect.FIRE, 8).add(Aspect.ENTROPY, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.gunpowderContainer), new int[] { 1 },
				new AspectList().add(Aspect.HUNGER, 8).add(Aspect.ENTROPY, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.gunpowderContainer), new int[] { 2 },
				new AspectList().add(Aspect.EARTH, 8).add(Aspect.WATER, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.gunpowderContainer), new int[] { 3 },
				new AspectList().add(Aspect.WATER, 8).add(Aspect.HEAL, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.vegiBag), new int[] { 0 },
				new AspectList().add(Aspect.CROP, 8).add(Aspect.PLANT, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.vegiBag), new int[] {
				1,
				2,
				3,
				7 }, new AspectList().add(Aspect.CROP, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.vegiBag), new int[] { 5 },
				new AspectList().add(Aspect.CROP, 8).add(Aspect.AIR, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.vegiBag), new int[] { 6 },
				new AspectList().add(Aspect.CROP, 8).add(Aspect.WATER, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.vegiBag), new int[] { 8 },
				new AspectList().add(Aspect.CROP, 8).add(Aspect.MAGIC, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.cardboard), new int[] {
				0,
				1,
				2,
				3 }, new AspectList().add(Aspect.CROP, 8));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.mobBlock), new int[] { 0 },
				new AspectList().add(Aspect.FLESH, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.mobBlock), new int[] { 1 },
				new AspectList().add(Aspect.DEATH, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.mobBlock), new int[] { 2 },
				new AspectList().add(Aspect.POISON, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.mobBlock), new int[] { 3 },
				new AspectList().add(Aspect.ELDRITCH, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.mobBlock), new int[] { 4 },
				new AspectList().add(Aspect.SLIME, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.flowerPot), new int[] {
				0,
				1 }, new AspectList().add(Aspect.SENSES, 5).add(Aspect.PLANT, 3));

		// chalcedony
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.flintBlock), new int[] { 0 },
				new AspectList().add(Aspect.MINE, 3).add(Aspect.FIRE, 3));
		ThaumcraftApi
				.registerObjectTag(new ItemStack(DCsAppleMilk.chalcedony), new int[] { OreDictionary.WILDCARD_VALUE },
						new AspectList().add(Aspect.MINE, 3).add(Aspect.CRYSTAL, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.cLamp), new int[] {
				0,
				1,
				2,
				3 }, new AspectList().add(Aspect.LIGHT, 2).add(Aspect.CRYSTAL, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.cLamp), new int[] {
				4,
				5,
				6,
				7 }, new AspectList().add(Aspect.LIGHT, 2).add(Aspect.CRYSTAL, 3).add(Aspect.VOID, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.cLamp), new int[] { 8 },
				new AspectList().add(Aspect.LIGHT, 2).add(Aspect.CRYSTAL, 3).add(Aspect.MIND, 2).add(Aspect.ENERGY, 2));
		ThaumcraftApi
				.registerObjectTag(
						new ItemStack(DCsAppleMilk.cLamp),
						new int[] { 9 },
						new AspectList().add(Aspect.LIGHT, 2).add(Aspect.CRYSTAL, 3).add(Aspect.BEAST, 2)
								.add(Aspect.UNDEAD, 2));
		ThaumcraftApi.registerObjectTag(
				new ItemStack(DCsAppleMilk.cLamp),
				new int[] { 10 },
				new AspectList().add(Aspect.LIGHT, 2).add(Aspect.CRYSTAL, 3).add(Aspect.MECHANISM, 2)
						.add(Aspect.TRAP, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.cLamp), new int[] { 11 },
				new AspectList().add(Aspect.LIGHT, 2).add(Aspect.CRYSTAL, 3).add(Aspect.ENTROPY, 2)
						.add(Aspect.ORDER, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.chalcenonyPanel), new int[] { 0 }, new AspectList()
				.add(Aspect.MINE, 1).add(Aspect.CRYSTAL, 1).add(Aspect.SENSES, 1));

		// deco
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.Basket), new int[] { OreDictionary.WILDCARD_VALUE },
				new AspectList().add(Aspect.CRAFT, 2).add(Aspect.HUNGER, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.eggBasket),
				new int[] { OreDictionary.WILDCARD_VALUE }, new AspectList().add(Aspect.CRAFT, 2).add(Aspect.HEAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.bowlRack),
				new int[] { OreDictionary.WILDCARD_VALUE }, new AspectList().add(Aspect.CRAFT, 2).add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.chopsticksBox),
				new int[] { OreDictionary.WILDCARD_VALUE }, new AspectList().add(Aspect.CRAFT, 2).add(Aspect.TREE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.clamSand), new int[] { 0 },
				new AspectList().add(Aspect.WATER, 2).add(Aspect.HEAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.clamSand), new int[] { 1 },
				new AspectList().add(Aspect.WATER, 2).add(Aspect.VOID, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.clamSand), new int[] { 2 },
				new AspectList().add(Aspect.SENSES, 4).add(Aspect.AURA, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.yuzuGel), new int[] { 0 },
				new AspectList().add(Aspect.ENERGY, 1).add(Aspect.SLIME, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.redGel), new int[] { 0 },
				new AspectList().add(Aspect.ENERGY, 2).add(Aspect.SLIME, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.yuzuFence), new int[] { 0 },
				new AspectList().add(Aspect.TREE, 2).add(Aspect.TRAP, 3));

		// tools
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.emptyPanGaiden), new int[] { 0 }, new AspectList()
				.add(Aspect.CRAFT, 2).add(Aspect.VOID, 1).add(Aspect.HUNGER, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.teppanII),
				new int[] { OreDictionary.WILDCARD_VALUE }, new AspectList().add(Aspect.CRAFT, 2).add(Aspect.HUNGER, 1)
						.add(Aspect.FIRE, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.iceMaker), new int[] { 0 },
				new AspectList().add(Aspect.CRAFT, 2).add(Aspect.HUNGER, 1).add(Aspect.COLD, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.teaMakerNext), new int[] { 0 }, new AspectList()
				.add(Aspect.WATER, 2).add(Aspect.VOID, 1).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.teaMakerBlack), new int[] { 0 }, new AspectList()
				.add(Aspect.WATER, 2).add(Aspect.DARKNESS, 1).add(Aspect.FIRE, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.rotaryDial), new int[] { 0 },
				new AspectList().add(Aspect.MECHANISM, 2).add(Aspect.EXCHANGE, 2).add(Aspect.TRAVEL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.processor), new int[] { 0 },
				new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.ENTROPY, 3).add(Aspect.CRAFT, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.advProcessor), new int[] { 0 }, new AspectList()
				.add(Aspect.MECHANISM, 3).add(Aspect.ENTROPY, 3).add(Aspect.METAL, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.evaporator), new int[] { 0 },
				new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.WATER, 2).add(Aspect.VOID, 2));

		// energy
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.yuzuBat), new int[] { 0 },
				new AspectList().add(Aspect.ENERGY, 5).add(Aspect.CROP, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.gelBat), new int[] { 0 },
				new AspectList().add(Aspect.ENERGY, 5).add(Aspect.SLIME, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.batBox), new int[] { 0 },
				new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.ENERGY, 3).add(Aspect.EXCHANGE, 3));

		// foods
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.emptyCup), new int[] { 0 },
				new AspectList().add(Aspect.WATER, 2).add(Aspect.VOID, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.teacupBlock),
				new int[] { OreDictionary.WILDCARD_VALUE }, new AspectList().add(Aspect.WATER, 2).add(Aspect.HEAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.teaCup2),
				new int[] { OreDictionary.WILDCARD_VALUE }, new AspectList().add(Aspect.WATER, 2).add(Aspect.HEAL, 1));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.bowlBlock),
				new int[] { OreDictionary.WILDCARD_VALUE }, new AspectList().add(Aspect.HUNGER, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.bowlJP), new int[] { OreDictionary.WILDCARD_VALUE },
				new AspectList().add(Aspect.HUNGER, 2));
		ThaumcraftApi
				.registerObjectTag(new ItemStack(DCsAppleMilk.foodPlate), new int[] { OreDictionary.WILDCARD_VALUE },
						new AspectList().add(Aspect.HUNGER, 2).add(Aspect.FLESH, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.blockIcecream),
				new int[] { OreDictionary.WILDCARD_VALUE }, new AspectList().add(Aspect.HUNGER, 2).add(Aspect.COLD, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.cocktail),
				new int[] { OreDictionary.WILDCARD_VALUE }, new AspectList().add(Aspect.WATER, 2).add(Aspect.MAGIC, 2)
						.add(Aspect.SENSES, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.largeBottle),
				new int[] { OreDictionary.WILDCARD_VALUE }, new AspectList().add(Aspect.WATER, 8).add(Aspect.VOID, 8)
						.add(Aspect.CRYSTAL, 4));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.barrel), new int[] { OreDictionary.WILDCARD_VALUE },
				new AspectList().add(Aspect.WATER, 3).add(Aspect.AIR, 3).add(Aspect.MAGIC, 1));

		// plants
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.saplingTea),
				new int[] { OreDictionary.WILDCARD_VALUE }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.HEAL, 2));
		ThaumcraftApi
				.registerObjectTag(new ItemStack(DCsAppleMilk.saplingYuzu), new int[] { OreDictionary.WILDCARD_VALUE },
						new AspectList().add(Aspect.PLANT, 2).add(Aspect.ENERGY, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.teaTree),
				new int[] { OreDictionary.WILDCARD_VALUE }, new AspectList().add(Aspect.PLANT, 2).add(Aspect.AIR, 2));
		ThaumcraftApi
				.registerObjectTag(new ItemStack(DCsAppleMilk.cassisTree), new int[] { OreDictionary.WILDCARD_VALUE },
						new AspectList().add(Aspect.PLANT, 2).add(Aspect.SENSES, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.logYuzu),
				new int[] { OreDictionary.WILDCARD_VALUE }, new AspectList().add(Aspect.TREE, 2).add(Aspect.ENERGY, 2));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.leavesYuzu),
				new int[] { OreDictionary.WILDCARD_VALUE }, new AspectList().add(Aspect.PLANT, 1).add(Aspect.AIR, 1)
						.add(Aspect.ENERGY, 1));

		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.melonBomb),
				new int[] { OreDictionary.WILDCARD_VALUE }, new AspectList().add(Aspect.CROP, 8).add(Aspect.ENTROPY, 3)
						.add(Aspect.WATER, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.mushroomBox),
				new int[] { OreDictionary.WILDCARD_VALUE },
				new AspectList().add(Aspect.PLANT, 8).add(Aspect.DARKNESS, 3).add(Aspect.MAGIC, 1));

		// choco
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.chocoBlock),
				new int[] { OreDictionary.WILDCARD_VALUE }, new AspectList().add(Aspect.HEAL, 3).add(Aspect.MIND, 3)
						.add(Aspect.SENSES, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.filledSoupPan),
				new int[] { OreDictionary.WILDCARD_VALUE }, new AspectList().add(Aspect.HEAL, 3).add(Aspect.MIND, 3)
						.add(Aspect.MAGIC, 3));

		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.wipeBox2), new int[] {
				0,
				1 }, new AspectList().add(Aspect.MIND, 3).add(Aspect.MAGIC, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.wipeBox), new int[] {
				2,
				3 }, new AspectList().add(Aspect.MIND, 6).add(Aspect.MAGIC, 6));
		ThaumcraftApi.registerObjectTag(new ItemStack(DCsAppleMilk.wipeBox2),
				new int[] { OreDictionary.WILDCARD_VALUE }, new AspectList().add(Aspect.MIND, 10).add(Aspect.MAGIC, 10)
						.add(Aspect.VOID, 10));

		AMTLogger.debugInfo("Succeeded to register aspects for Thaumcraft");

		if (this.thaumicGreatwood != null) {

			// 取得したアイテムを使ったレシピの登録。こちらは恐らく旧版でも動作する
			GameRegistry.addShapelessRecipe(new ItemStack(LoadThaumcraftPlugin.thaumicGreatwood.getItem(), 9, 0),
					new ItemStack(DCsAppleMilk.woodBox, 1, 5));

			GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.woodBox, 1, 5), new Object[] {
					"UUU",
					"UUU",
					"UUU",
					Character.valueOf('U'),
					new ItemStack(LoadThaumcraftPlugin.thaumicGreatwood.getItem(), 1, 0) });
		}

		if (this.thaumicSilverwood != null) {

			GameRegistry.addShapelessRecipe(new ItemStack(LoadThaumcraftPlugin.thaumicSilverwood.getItem(), 9, 1),
					new ItemStack(DCsAppleMilk.woodBox, 1, 6));

			GameRegistry.addRecipe(new ItemStack(DCsAppleMilk.woodBox, 1, 6), new Object[] {
					"UUU",
					"UUU",
					"UUU",
					Character.valueOf('U'),
					new ItemStack(LoadThaumcraftPlugin.thaumicSilverwood.getItem(), 1, 1) });
		}

		if (this.thaumicNitor != null) {
			if (LoadModHandler.registerModItems("furnaceBlock", this.thaumicNitor)) {
				RecipeRegisterManager.panRecipe.registerHeatSource(Block.getBlockFromItem(thaumicNitor.getItem()), 1);
				RecipeRegisterManager.plateRecipe.registerHeatSource(Block.getBlockFromItem(thaumicNitor.getItem()), 1);
				AMTLogger.debugInfo("Succeeded to get Nitor.");
			}
		}
	}
}
