package mods.defeatedcrow.common;

import mods.defeatedcrow.common.block.BlockBasket;
import mods.defeatedcrow.common.block.BlockBowlRack;
import mods.defeatedcrow.common.block.BlockCPanel;
import mods.defeatedcrow.common.block.BlockChalcedony;
import mods.defeatedcrow.common.block.BlockChalcedonyLamp;
import mods.defeatedcrow.common.block.BlockChalcedonyLampOp;
import mods.defeatedcrow.common.block.BlockChopsticksBox;
import mods.defeatedcrow.common.block.BlockCrowDoll;
import mods.defeatedcrow.common.block.BlockFlint;
import mods.defeatedcrow.common.block.BlockIncenseBase;
import mods.defeatedcrow.common.block.BlockRotaryDial;
import mods.defeatedcrow.common.block.BlockWoodPanel;
import mods.defeatedcrow.common.block.BlockYuzuFence;
import mods.defeatedcrow.common.block.ItemBowlRack;
import mods.defeatedcrow.common.block.ItemBreadBasket;
import mods.defeatedcrow.common.block.ItemCLampOp;
import mods.defeatedcrow.common.block.ItemChalcedony;
import mods.defeatedcrow.common.block.ItemChalcedonyLamp;
import mods.defeatedcrow.common.block.ItemChopsticksBox;
import mods.defeatedcrow.common.block.ItemCrowDoll;
import mods.defeatedcrow.common.block.ItemFlintBlock;
import mods.defeatedcrow.common.block.ItemWoodPanel;
import mods.defeatedcrow.common.block.appliance.BlockAdvProcessor;
import mods.defeatedcrow.common.block.appliance.BlockEmptyCup;
import mods.defeatedcrow.common.block.appliance.BlockEmptyPanG;
import mods.defeatedcrow.common.block.appliance.BlockEvaporator;
import mods.defeatedcrow.common.block.appliance.BlockFilledSoupPan;
import mods.defeatedcrow.common.block.appliance.BlockIceMaker;
import mods.defeatedcrow.common.block.appliance.BlockProcessor;
import mods.defeatedcrow.common.block.appliance.BlockTeaMakerBlack;
import mods.defeatedcrow.common.block.appliance.BlockTeaMakerNext;
import mods.defeatedcrow.common.block.appliance.BlockTeppanII;
import mods.defeatedcrow.common.block.appliance.ItemAppliance;
import mods.defeatedcrow.common.block.appliance.ItemFilledSoupPan;
import mods.defeatedcrow.common.block.appliance.ItemMachineBlock;
import mods.defeatedcrow.common.block.appliance.ItemPanG;
import mods.defeatedcrow.common.block.brewing.BlockBarrel;
import mods.defeatedcrow.common.block.brewing.BlockCordial;
import mods.defeatedcrow.common.block.brewing.BlockEmptyBottle;
import mods.defeatedcrow.common.block.brewing.BlockLargeBottle;
import mods.defeatedcrow.common.block.brewing.ItemBlockBottle;
import mods.defeatedcrow.common.block.brewing.ItemBlockCordial;
import mods.defeatedcrow.common.block.brewing.ItemCordial;
import mods.defeatedcrow.common.block.brewing.ItemEmptyBottle;
import mods.defeatedcrow.common.block.brewing.ItemLargeBottle;
import mods.defeatedcrow.common.block.container.BlockAppleBox;
import mods.defeatedcrow.common.block.container.BlockCardboard;
import mods.defeatedcrow.common.block.container.BlockCharcoalBox;
import mods.defeatedcrow.common.block.container.BlockContainerSaddle;
import mods.defeatedcrow.common.block.container.BlockContainerWaterBottle;
import mods.defeatedcrow.common.block.container.BlockEggBasket;
import mods.defeatedcrow.common.block.container.BlockFlowerPot;
import mods.defeatedcrow.common.block.container.BlockFlowerVase;
import mods.defeatedcrow.common.block.container.BlockGunpowderContainer;
import mods.defeatedcrow.common.block.container.BlockHedge;
import mods.defeatedcrow.common.block.container.BlockMelonBomb;
import mods.defeatedcrow.common.block.container.BlockMobDrop;
import mods.defeatedcrow.common.block.container.BlockMushBox;
import mods.defeatedcrow.common.block.container.BlockSilkyMelon;
import mods.defeatedcrow.common.block.container.BlockVegiBag;
import mods.defeatedcrow.common.block.container.BlockWipeBox;
import mods.defeatedcrow.common.block.container.BlockWipeBox2;
import mods.defeatedcrow.common.block.container.BlockWoodBox;
import mods.defeatedcrow.common.block.container.ItemAppleBox;
import mods.defeatedcrow.common.block.container.ItemCardboard;
import mods.defeatedcrow.common.block.container.ItemCharcoalBox;
import mods.defeatedcrow.common.block.container.ItemContainerBase;
import mods.defeatedcrow.common.block.container.ItemEggBasket;
import mods.defeatedcrow.common.block.container.ItemFlowerPot;
import mods.defeatedcrow.common.block.container.ItemFlowerVase;
import mods.defeatedcrow.common.block.container.ItemGunpowderContainer;
import mods.defeatedcrow.common.block.container.ItemHedge;
import mods.defeatedcrow.common.block.container.ItemMelonBomb;
import mods.defeatedcrow.common.block.container.ItemMobDropBox;
import mods.defeatedcrow.common.block.container.ItemMushBox;
import mods.defeatedcrow.common.block.container.ItemSilkyMelon;
import mods.defeatedcrow.common.block.container.ItemVegiBag;
import mods.defeatedcrow.common.block.container.ItemWipeBox;
import mods.defeatedcrow.common.block.container.ItemWipeBox2;
import mods.defeatedcrow.common.block.container.ItemWoodBox;
import mods.defeatedcrow.common.block.edible.BlockAlcoholCup;
import mods.defeatedcrow.common.block.edible.BlockBowl;
import mods.defeatedcrow.common.block.edible.BlockBowlJP;
import mods.defeatedcrow.common.block.edible.BlockChocoGift;
import mods.defeatedcrow.common.block.edible.BlockCocktail;
import mods.defeatedcrow.common.block.edible.BlockCocktail2;
import mods.defeatedcrow.common.block.edible.BlockCocktailSP;
import mods.defeatedcrow.common.block.edible.BlockFilledCup;
import mods.defeatedcrow.common.block.edible.BlockFilledCup2;
import mods.defeatedcrow.common.block.edible.BlockFoodPlate;
import mods.defeatedcrow.common.block.edible.BlockIceCream;
import mods.defeatedcrow.common.block.edible.EntityItemAlcoholCup;
import mods.defeatedcrow.common.block.edible.EntityItemBowl;
import mods.defeatedcrow.common.block.edible.EntityItemBowlJP;
import mods.defeatedcrow.common.block.edible.EntityItemCocktail;
import mods.defeatedcrow.common.block.edible.EntityItemCocktail2;
import mods.defeatedcrow.common.block.edible.EntityItemCocktailSP;
import mods.defeatedcrow.common.block.edible.EntityItemIceCream;
import mods.defeatedcrow.common.block.edible.EntityItemSteak;
import mods.defeatedcrow.common.block.edible.EntityItemTeaCup;
import mods.defeatedcrow.common.block.edible.EntityItemTeaCup2;
import mods.defeatedcrow.common.block.edible.ItemChocoGift;
import mods.defeatedcrow.common.block.energy.BlockBatBox;
import mods.defeatedcrow.common.block.energy.BlockGelBat;
import mods.defeatedcrow.common.block.energy.BlockHandleEngine;
import mods.defeatedcrow.common.block.energy.BlockRedGel;
import mods.defeatedcrow.common.block.energy.BlockYuzuBat;
import mods.defeatedcrow.common.block.energy.BlockYuzuLight;
import mods.defeatedcrow.common.block.energy.ItemBatBox;
import mods.defeatedcrow.common.block.energy.ItemGelBat;
import mods.defeatedcrow.common.block.plants.BlockCassisTree;
import mods.defeatedcrow.common.block.plants.BlockClamSand;
import mods.defeatedcrow.common.block.plants.BlockMintCrop;
import mods.defeatedcrow.common.block.plants.BlockSaplingTea;
import mods.defeatedcrow.common.block.plants.BlockTeaTree;
import mods.defeatedcrow.common.block.plants.BlockYuzuLeaves;
import mods.defeatedcrow.common.block.plants.BlockYuzuLog;
import mods.defeatedcrow.common.block.plants.BlockYuzuSapling;
import mods.defeatedcrow.common.block.plants.ItemCassisTree;
import mods.defeatedcrow.common.block.plants.ItemClamSand;
import mods.defeatedcrow.common.block.plants.ItemTeaSapling;
import mods.defeatedcrow.common.block.plants.ItemTeaTree;
import mods.defeatedcrow.common.block.plants.ItemYuzuLeaves;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.fluid.BlockCamOilFluid;
import mods.defeatedcrow.common.fluid.BlockDummyFluid;
import mods.defeatedcrow.common.fluid.BlockDummyFluid2;
import mods.defeatedcrow.common.fluid.BlockOilFluid;
import mods.defeatedcrow.common.fluid.ItemBottleCamOil;
import mods.defeatedcrow.common.fluid.ItemBottleVegiOil;
import mods.defeatedcrow.common.fluid.ItemBucketCamOil;
import mods.defeatedcrow.common.fluid.ItemBucketVegiOil;
import mods.defeatedcrow.common.fluid.ItemBucketYoungAlcohol;
import mods.defeatedcrow.common.fluid.ItemDummyFluid;
import mods.defeatedcrow.common.fluid.ItemDummyFluid2;
import mods.defeatedcrow.common.item.ItemCarbonStick;
import mods.defeatedcrow.common.item.ItemChalcedonyHammer;
import mods.defeatedcrow.common.item.ItemChalcedonyKnife;
import mods.defeatedcrow.common.item.ItemChalcedonyMonocle;
import mods.defeatedcrow.common.item.ItemChalcedonyShears;
import mods.defeatedcrow.common.item.ItemChopsticks;
import mods.defeatedcrow.common.item.ItemContainerDoor;
import mods.defeatedcrow.common.item.ItemEXItem;
import mods.defeatedcrow.common.item.ItemFireStarter;
import mods.defeatedcrow.common.item.ItemFoodTea;
import mods.defeatedcrow.common.item.ItemInkStick;
import mods.defeatedcrow.common.item.ItemLeafTea;
import mods.defeatedcrow.common.item.ItemMintSeed;
import mods.defeatedcrow.common.item.ItemOnixSword;
import mods.defeatedcrow.common.item.ItemOreDust;
import mods.defeatedcrow.common.item.appliance.ItemBattery;
import mods.defeatedcrow.common.item.appliance.ItemGrater;
import mods.defeatedcrow.common.item.appliance.ItemIcyCrystal;
import mods.defeatedcrow.common.item.appliance.ItemJawplate;
import mods.defeatedcrow.common.item.appliance.ItemSlotPanel;
import mods.defeatedcrow.common.item.appliance.ItemYuzuGatling;
import mods.defeatedcrow.common.item.edible.ItemAppleSandwich;
import mods.defeatedcrow.common.item.edible.ItemAppleTart;
import mods.defeatedcrow.common.item.edible.ItemBakedApple;
import mods.defeatedcrow.common.item.edible.ItemBaseSoupBowl;
import mods.defeatedcrow.common.item.edible.ItemChocoFruits;
import mods.defeatedcrow.common.item.edible.ItemClam;
import mods.defeatedcrow.common.item.edible.ItemCondensedMilk;
import mods.defeatedcrow.common.item.edible.ItemGratedApple;
import mods.defeatedcrow.common.item.edible.ItemIcyToffyApple;
import mods.defeatedcrow.common.item.edible.ItemMincedFoods;
import mods.defeatedcrow.common.item.edible.ItemMoromi;
import mods.defeatedcrow.common.item.edible.ItemToffyApple;
import mods.defeatedcrow.common.item.edible.ItemYeast;
import mods.defeatedcrow.common.item.magic.ItemDebugArm;
import mods.defeatedcrow.common.item.magic.ItemEssentialOil;
import mods.defeatedcrow.common.item.magic.ItemFossilCannon;
import mods.defeatedcrow.common.item.magic.ItemFossilScale;
import mods.defeatedcrow.common.item.magic.ItemIncenseAgar;
import mods.defeatedcrow.common.item.magic.ItemIncenseApple;
import mods.defeatedcrow.common.item.magic.ItemIncenseClam;
import mods.defeatedcrow.common.item.magic.ItemIncenseFrankincense;
import mods.defeatedcrow.common.item.magic.ItemIncenseIce;
import mods.defeatedcrow.common.item.magic.ItemIncenseLavender;
import mods.defeatedcrow.common.item.magic.ItemIncenseMint;
import mods.defeatedcrow.common.item.magic.ItemIncenseRose;
import mods.defeatedcrow.common.item.magic.ItemIncenseSandalwood;
import mods.defeatedcrow.common.item.magic.ItemIncenseVanilla;
import mods.defeatedcrow.common.item.magic.ItemIncenseYuzu;
import mods.defeatedcrow.common.item.magic.ItemPrincessClam;
import mods.defeatedcrow.common.item.magic.ItemStrangeSlag;
import mods.defeatedcrow.common.item.magic.ItemWoodDust;
import mods.defeatedcrow.event.BucketFillEvent;
import mods.defeatedcrow.event.DispenserEvent;
import mods.defeatedcrow.event.FluidDispenser;
import mods.defeatedcrow.potion.PotionConfinement;
import mods.defeatedcrow.potion.PotionHallucination;
import mods.defeatedcrow.potion.PotionImmunity;
import mods.defeatedcrow.potion.PotionProtectionEX;
import mods.defeatedcrow.potion.PotionReflex;
import mods.defeatedcrow.potion.PotionSuffocation;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class MaterialRegister {

	public static MaterialRegister instance = new MaterialRegister();

	private static Fluid registerVegiOil;
	private static Fluid registerCamOil;
	private static Fluid registerShothuYoung;
	private static Fluid registerShothu;
	private static Fluid registerWhiskeyYoung;
	private static Fluid registerWhiskey;
	private static Fluid registerBrandyYoung;
	private static Fluid registerBrandy;
	private static Fluid registerSakeYoung;
	private static Fluid registerSake;
	private static Fluid registerBeerYoung;
	private static Fluid registerBeer;
	private static Fluid registerWineYoung;
	private static Fluid registerWine;
	private static Fluid registerRumYoung;
	private static Fluid registerRum;
	private static Fluid registerVodkaYoung;
	private static Fluid registerVodka;

	private MaterialRegister() {
	}

	public void load() {
		this.addTools();
		this.addPlants();
		this.addContainers();
		this.addFoods();
		this.addDecorations();
		this.addMaterials();
		this.addMachines();

		// 登録順をちょっと調整
		// ツール
		GameRegistry.registerItem(DCsAppleMilk.DCgrater, "defeatedcrow.grater");
		GameRegistry.registerItem(DCsAppleMilk.firestarter, "defeatedcrow.firestarter");
		GameRegistry.registerItem(DCsAppleMilk.chalcedonyHammer, "defeatedcrow.chalcedonyStoneCutter");
		GameRegistry.registerItem(DCsAppleMilk.chalcedonyKnife, "defeatedcrow.chalcedonyKnife");
		GameRegistry.registerItem(DCsAppleMilk.pruningShears, "defeatedcrow.chalcedonyShears");
		GameRegistry.registerItem(DCsAppleMilk.monocle, "defeatedcrow.monocle");
		GameRegistry.registerItem(DCsAppleMilk.onixSword, "defeatedcrow.onixSword");
		GameRegistry.registerItem(DCsAppleMilk.chopsticks, "defeatedcrow.chopsticks");
		GameRegistry.registerItem(DCsAppleMilk.yuzuGatling, "defeatedcrow.yuzuGatling");
		GameRegistry.registerItem(DCsAppleMilk.fossilCannon, "defeatedcrow.fossilCannon");
		GameRegistry.registerItem(DCsAppleMilk.eightEyesArm, "defeatedcrow.debugArm");

		// たべもの
		GameRegistry.registerItem(DCsAppleMilk.bakedApple, "defeatedcrow.bakedApple");
		GameRegistry.registerItem(DCsAppleMilk.appleTart, "defeatedcrow.appleTart");
		GameRegistry.registerItem(DCsAppleMilk.appleSandwich, "defeatedcrow.appleSandwich");
		GameRegistry.registerItem(DCsAppleMilk.toffyApple, "defeatedcrow.toffyApple");
		GameRegistry.registerItem(DCsAppleMilk.icyToffyApple, "defeatedcrow.icyToffyApple");
		GameRegistry.registerItem(DCsAppleMilk.EXItems, "defeatedcrow.condensedMilk");
		GameRegistry.registerItem(DCsAppleMilk.condensedMIlk, "defeatedcrow.milkCandy");
		GameRegistry.registerItem(DCsAppleMilk.chocolateFruits, "defeatedcrow.chocolateFruits");

		// 植物
		GameRegistry.registerItem(DCsAppleMilk.leafTea, "defeatedcrow.leafTea");
		GameRegistry.registerItem(DCsAppleMilk.itemMintSeed, "defeatedcrow.seedMint");
		GameRegistry.registerItem(DCsAppleMilk.clam, "defeatedcrow.clam");

		// 飲み物素材
		GameRegistry.registerItem(DCsAppleMilk.foodTea, "defeatedcrow.foodTea");
		GameRegistry.registerItem(DCsAppleMilk.gratedApple, "defeatedcrow.gratedApple");
		GameRegistry.registerItem(DCsAppleMilk.mincedFoods, "defeatedcrow.mincedFoods");
		GameRegistry.registerItem(DCsAppleMilk.yeast, "defeatedcrow.yeast");
		GameRegistry.registerItem(DCsAppleMilk.moromi, "defeatedcrow.moromi");
		GameRegistry.registerItem(DCsAppleMilk.itemLargeBottle, "defeatedcrow.itemBottle");
		GameRegistry.registerItem(DCsAppleMilk.itemCordial, "defeatedcrow.itemCordial");
		GameRegistry.registerItem(DCsAppleMilk.baseSoupBowl, "defeatedcrow.basesoupitem");

		// 装置関係
		GameRegistry.registerItem(DCsAppleMilk.batteryItem, "defeatedcrow.battery");
		GameRegistry.registerItem(DCsAppleMilk.slotPanel, "defeatedcrow.slotPanel");
		GameRegistry.registerItem(DCsAppleMilk.jawPlate, "defeatedcrow.jawPlate");
		GameRegistry.registerItem(DCsAppleMilk.dustWood, "defeatedcrow.dustWood");
		GameRegistry.registerItem(DCsAppleMilk.essentialOil, "defeatedcrow.essentialOil");
		GameRegistry.registerItem(DCsAppleMilk.incenseRose, "defeatedcrow.incense_rose");
		GameRegistry.registerItem(DCsAppleMilk.incenseApple, "defeatedcrow.incense_apple");
		GameRegistry.registerItem(DCsAppleMilk.incenseMint, "defeatedcrow.incense_mint");
		GameRegistry.registerItem(DCsAppleMilk.incenseClam, "defeatedcrow.incense_clam");
		GameRegistry.registerItem(DCsAppleMilk.incenseIce, "defeatedcrow.incense_ice");
		GameRegistry.registerItem(DCsAppleMilk.incenseLavender, "defeatedcrow.incense_lavender");
		GameRegistry.registerItem(DCsAppleMilk.incenseSandalwood, "defeatedcrow.incense_sandalwood");
		GameRegistry.registerItem(DCsAppleMilk.incenseAgar, "defeatedcrow.incense_aloeswood");
		GameRegistry.registerItem(DCsAppleMilk.incenseFrank, "defeatedcrow.incense_frankincense");
		GameRegistry.registerItem(DCsAppleMilk.incenseYuzu, "defeatedcrow.incense_yuzu");
		GameRegistry.registerItem(DCsAppleMilk.incenseVanilla, "defeatedcrow.incense_vanilla");

		// そのほか
		GameRegistry.registerItem(DCsAppleMilk.inkStick, "defeatedcrow.inkStick");
		GameRegistry.registerItem(DCsAppleMilk.icyCrystal, "defeatedcrow.icyCrystal");
		GameRegistry.registerItem(DCsAppleMilk.princessClam, "defeatedcrow.princessClam");
		GameRegistry.registerItem(DCsAppleMilk.stickCarbon, "defeatedcrow.stickCarbon");
		GameRegistry.registerItem(DCsAppleMilk.oreDust, "defeatedcrow.oreDust");
		GameRegistry.registerItem(DCsAppleMilk.strangeSlag, "defeatedcrow.strangeSlag");
		GameRegistry.registerItem(DCsAppleMilk.fossilScale, "defeatedcrow.fossilScale");

		GameRegistry.registerItem(DCsAppleMilk.containerItemDoorW, "defeatedcrow.containeritemDoorW");
		GameRegistry.registerItem(DCsAppleMilk.containerItemDoorI, "defeatedcrow.containeritemDoorI");

		// 基本ツール
		GameRegistry.registerBlock(DCsAppleMilk.teaMakerNext, ItemAppliance.class, "defeatedcrow.teaMakerNext");
		GameRegistry.registerBlock(DCsAppleMilk.teaMakerBlack, ItemAppliance.class, "defeatedcrow.teaMakerBlack");
		GameRegistry.registerBlock(DCsAppleMilk.emptyCup, "defeatedcrow.emptyCup");
		GameRegistry.registerBlock(DCsAppleMilk.emptyPanGaiden, ItemPanG.class, "defeatedcrow.emptyPanG");
		GameRegistry.registerBlock(DCsAppleMilk.filledSoupPan, ItemFilledSoupPan.class, "defeatedcrow.filledSoupPan");
		GameRegistry.registerBlock(DCsAppleMilk.iceMaker, "defeatedcrow.iceMaker");
		GameRegistry.registerBlock(DCsAppleMilk.teppanII, ItemAppliance.class, "defeatedcrow.teppanII");
		GameRegistry.registerBlock(DCsAppleMilk.processor, ItemMachineBlock.class, "defeatedcrow.processor");
		GameRegistry.registerBlock(DCsAppleMilk.advProcessor, ItemMachineBlock.class, "defeatedcrow.advProcessor");
		GameRegistry.registerBlock(DCsAppleMilk.evaporator, ItemMachineBlock.class, "defeatedcrow.evaporator");

		// エネルギー
		GameRegistry.registerBlock(DCsAppleMilk.redGel, "defeatedcrow.redGel");
		GameRegistry.registerBlock(DCsAppleMilk.yuzuGel, "defeatedcrow.lightGel");
		GameRegistry.registerBlock(DCsAppleMilk.yuzuBat, ItemBatBox.class, "defeatedcrow.yuzuBatContainer");
		GameRegistry.registerBlock(DCsAppleMilk.gelBat, ItemGelBat.class, "defeatedcrow.gelBatContainer");
		GameRegistry.registerBlock(DCsAppleMilk.batBox, ItemBatBox.class, "defeatedcrow.batBox");
		GameRegistry.registerBlock(DCsAppleMilk.handleEngine, "defeatedcrow.EHandle");

		// 圧縮系
		GameRegistry.registerBlock(DCsAppleMilk.woodBox, ItemWoodBox.class, "defeatedcrow.WoodBox");
		GameRegistry.registerBlock(DCsAppleMilk.appleBox, ItemAppleBox.class, "defeatedcrow.AppleBox");
		GameRegistry.registerBlock(DCsAppleMilk.vegiBag, ItemVegiBag.class, "defeatedcrow.VegiBag");
		GameRegistry.registerBlock(DCsAppleMilk.cardboard, ItemCardboard.class, "defeatedcrow.cardboardBox");
		GameRegistry.registerBlock(DCsAppleMilk.charcoalBox, ItemCharcoalBox.class, "defeatedcrow.Charcoalcontainer");
		GameRegistry.registerBlock(DCsAppleMilk.gunpowderContainer, ItemGunpowderContainer.class,
				"defeatedcrow.GunpowderContainer");
		GameRegistry.registerBlock(DCsAppleMilk.mobBlock, ItemMobDropBox.class, "defeatedcrow.mobDropBox");
		GameRegistry.registerFuelHandler(DCsAppleMilk.charcoalBox);
		GameRegistry.registerBlock(DCsAppleMilk.flowerPot, ItemFlowerPot.class, "defeatedcrow.flowerPot");
		GameRegistry.registerBlock(DCsAppleMilk.flowerBase, ItemFlowerVase.class, "defeatedcrow.flowerVase");
		GameRegistry.registerBlock(DCsAppleMilk.hedge, ItemHedge.class, "defeatedcrow.hedge");
		GameRegistry.registerBlock(DCsAppleMilk.containerWBottle, ItemContainerBase.class,
				"defeatedcrow.containerBottleW");
		GameRegistry.registerBlock(DCsAppleMilk.containerSaddle, ItemContainerBase.class,
				"defeatedcrow.containerSaddle");

		// 自然
		GameRegistry.registerBlock(DCsAppleMilk.saplingTea, ItemTeaSapling.class, "defeatedcrow.saplingTea");
		GameRegistry.registerBlock(DCsAppleMilk.teaTree, ItemTeaTree.class, "defeatedcrow.teaTree");
		GameRegistry.registerBlock(DCsAppleMilk.cassisTree, ItemCassisTree.class, "defeatedcrow.cassisTree");
		GameRegistry.registerBlock(DCsAppleMilk.saplingYuzu, "defeatedcrow.saplingYuzu");
		GameRegistry.registerBlock(DCsAppleMilk.logYuzu, "defeatedcrow.logYuzu");
		GameRegistry.registerBlock(DCsAppleMilk.leavesYuzu, ItemYuzuLeaves.class, "defeatedcrow.leavesYuzu");
		GameRegistry.registerBlock(DCsAppleMilk.clamSand, ItemClamSand.class, "defeatedcrow.clamSand");
		GameRegistry.registerBlock(DCsAppleMilk.cropMint, "defeatedcrow.cropMint");

		// ボトル関係
		GameRegistry.registerBlock(DCsAppleMilk.emptyBottle, ItemEmptyBottle.class, "defeatedcrow.emptyBottle");
		GameRegistry.registerBlock(DCsAppleMilk.largeBottle, ItemBlockBottle.class, "defeatedcrow.largeBottle");
		GameRegistry.registerBlock(DCsAppleMilk.cordial, ItemBlockCordial.class, "defeatedcrow.blockCordial");
		GameRegistry.registerBlock(DCsAppleMilk.barrel, ItemAppliance.class, "defeatedcrow.blockBarrel");
		GameRegistry.registerBlock(DCsAppleMilk.blockDummyAlcohol, ItemDummyFluid.class,
				"defeatedcrow.blockDummyAlcohol");
		GameRegistry.registerBlock(DCsAppleMilk.blockDummyAlcohol2, ItemDummyFluid2.class,
				"defeatedcrow.blockDummyAlcohol2");

		// カルセドニー
		GameRegistry.registerBlock(DCsAppleMilk.flintBlock, ItemFlintBlock.class, "defeatedcrow.flintBlock");
		GameRegistry.registerBlock(DCsAppleMilk.chalcedony, ItemChalcedony.class, "defeatedcrow.chalcedony");
		GameRegistry.registerBlock(DCsAppleMilk.cLamp, ItemChalcedonyLamp.class, "defeatedcrow.chalcedonyLamp");
		GameRegistry.registerBlock(DCsAppleMilk.cLampOpaque, ItemCLampOp.class, "defeatedcrow.chalcedonyLampOp");
		GameRegistry.registerBlock(DCsAppleMilk.chalcenonyPanel, "defeatedcrow.chalcedonyPanel");
		GameRegistry.registerBlock(DCsAppleMilk.incenseBase, "defeatedcrow.incenseBase");

		// インテリア
		GameRegistry.registerBlock(DCsAppleMilk.Basket, ItemBreadBasket.class, "defeatedcrow.basket");
		GameRegistry.registerBlock(DCsAppleMilk.bowlRack, ItemBowlRack.class, "defeatedcrow.bowlRack");
		GameRegistry.registerBlock(DCsAppleMilk.chopsticksBox, ItemChopsticksBox.class, "defeatedcrow.chopsticksBox");
		GameRegistry.registerBlock(DCsAppleMilk.eggBasket, ItemEggBasket.class, "defeatedcrow.eggBasket");
		GameRegistry.registerBlock(DCsAppleMilk.mushroomBox, ItemMushBox.class, "defeatedcrow.mushroomBox");
		GameRegistry.registerBlock(DCsAppleMilk.melonBomb, ItemMelonBomb.class, "defeatedcrow.melonBomb");
		GameRegistry.registerBlock(DCsAppleMilk.silkyMelon, ItemSilkyMelon.class, "defeatedcrow.melonSilky");
		GameRegistry.registerBlock(DCsAppleMilk.wipeBox, ItemWipeBox.class, "defeatedcrow.wipeBox");
		GameRegistry.registerBlock(DCsAppleMilk.wipeBox2, ItemWipeBox2.class, "defeatedcrow.wipeBox2");
		GameRegistry.registerBlock(DCsAppleMilk.rotaryDial, "defeatedcrow.rotaryDial");
		GameRegistry.registerBlock(DCsAppleMilk.yuzuFence, "defeatedcrow.yuzuFence");
		GameRegistry.registerBlock(DCsAppleMilk.woodPanel, ItemWoodPanel.class, "defeatedcrow.woodPanel");
		GameRegistry.registerBlock(DCsAppleMilk.crowDoll, ItemCrowDoll.class, "defeatedcrow.crowFigure");

		// 食べ物
		GameRegistry.registerBlock(DCsAppleMilk.bowlBlock, EntityItemBowl.class, "defeatedcrow.bowlBlock");
		GameRegistry.registerBlock(DCsAppleMilk.bowlJP, EntityItemBowlJP.class, "defeatedcrow.bowlJP");
		GameRegistry.registerBlock(DCsAppleMilk.foodPlate, EntityItemSteak.class, "defeatedcrow.foodPlate");
		GameRegistry.registerBlock(DCsAppleMilk.chocoBlock, ItemChocoGift.class, "defeatedcrow.chocolateGift");
		GameRegistry.registerBlock(DCsAppleMilk.teacupBlock, EntityItemTeaCup.class, "defeatedcrow.filledCup");
		GameRegistry.registerBlock(DCsAppleMilk.teaCup2, EntityItemTeaCup2.class, "defeatedcrow.filledCup2");
		GameRegistry.registerBlock(DCsAppleMilk.blockIcecream, EntityItemIceCream.class, "defeatedcrow.iceCreamBlock");
		GameRegistry.registerBlock(DCsAppleMilk.alcoholCup, EntityItemAlcoholCup.class, "defeatedcrow.alcoholCup");
		GameRegistry.registerBlock(DCsAppleMilk.cocktail, EntityItemCocktail.class, "defeatedcrow.cocktail");
		GameRegistry.registerBlock(DCsAppleMilk.cocktail2, EntityItemCocktail2.class, "defeatedcrow.cocktail2");
		GameRegistry.registerBlock(DCsAppleMilk.cocktailSP, EntityItemCocktailSP.class, "defeatedcrow.cocktailSP");
	}

	public boolean addPotion() {
		int lim = 128;
		if (Potion.potionTypes.length < lim)
			return false;
		// ポーション効果の追加
		// コンフィグでIDが~127の範囲内の時のみ追加する。
		if (Potion.potionTypes[DCsConfig.potionIDImmunity] == null && DCsConfig.potionIDImmunity < lim) {
			DCsAppleMilk.Immunization = (new PotionImmunity(DCsConfig.potionIDImmunity, false, 0xFEC0C0, 0, 0))
					.setPotionName("DCs.potion.immunization");
		}
		if (Potion.potionTypes[DCsConfig.potionIDPrvExplode] == null && DCsConfig.potionIDPrvExplode < lim) {
			DCsAppleMilk.prvExplode = (new PotionProtectionEX(DCsConfig.potionIDPrvExplode, false, 0x646464, false,
					true, false, DamageSource.anvil, 2, 0)).setPotionName("DCs.potion.protectionExplode");
		}
		if (Potion.potionTypes[DCsConfig.potionIDPrvProjectile] == null && DCsConfig.potionIDPrvProjectile < lim) {
			DCsAppleMilk.prvProjectile = (new PotionProtectionEX(DCsConfig.potionIDPrvProjectile, false, 0x996420,
					false, false, true, DamageSource.magic, 1, 0)).setPotionName("DCs.potion.protectionProjectile");
		}
		if (Potion.potionTypes[DCsConfig.potionIDReflex] == null && DCsConfig.potionIDReflex < lim) {
			DCsAppleMilk.reflex = (new PotionReflex(DCsConfig.potionIDReflex, false, 0x0064FF, false, 3, 0))
					.setPotionName("DCs.potion.reflex");
		}
		if (Potion.potionTypes[DCsConfig.potionIDAbsEXP] == null && DCsConfig.potionIDAbsEXP < lim) {
			DCsAppleMilk.absEXP = (new PotionReflex(DCsConfig.potionIDAbsEXP, false, 0X00FF64, false, 4, 0))
					.setPotionName("DCs.potion.absorptionEXP");
		}
		if (Potion.potionTypes[DCsConfig.potionIDAbsHeal] == null && DCsConfig.potionIDAbsHeal < lim) {
			DCsAppleMilk.absHeal = (new PotionReflex(DCsConfig.potionIDAbsHeal, false, 0xFF32C0, false, 5, 0))
					.setPotionName("DCs.potion.absorptionHeal");
		}
		if (Potion.potionTypes[DCsConfig.potionIDSuffocation] == null && DCsConfig.potionIDSuffocation < lim) {
			DCsAppleMilk.suffocation = (new PotionSuffocation(DCsConfig.potionIDSuffocation, true, 0x161616, 6, 0))
					.setPotionName("DCs.potion.suffocation");
		}
		if (Potion.potionTypes[DCsConfig.potionIDPrvSuffocation] == null && DCsConfig.potionIDPrvSuffocation < lim) {
			DCsAppleMilk.prvSuffocation = (PotionProtectionEX) (new PotionProtectionEX(
					DCsConfig.potionIDPrvSuffocation, true, 0xC064FF, false, false, false, DamageSource.inWall, 7, 0))
					.setProtectSuffocation().setPotionName("DCs.potion.protectionSuffocation");
		}
		if (Potion.potionTypes[DCsConfig.potionIDHallucinations] == null && DCsConfig.potionIDHallucinations < lim) {
			DCsAppleMilk.hallucinations = new PotionHallucination(DCsConfig.potionIDHallucinations, true, 0xFF32FF, 0,
					1).setPotionName("DCs.potion.hallucination");
		}
		if (Potion.potionTypes[DCsConfig.potionIDConfinement] == null && DCsConfig.potionIDConfinement < lim) {
			DCsAppleMilk.confinement = new PotionConfinement(DCsConfig.potionIDConfinement, true, 0X646464, 0, 2)
					.setPotionName("DCs.potion.confinement");
		}
		return true;
	}

	public void addFluid() {
		// vegi
		registerVegiOil = new Fluid("vegitable_oil").setDensity(800).setViscosity(1500);
		FluidRegistry.registerFluid(registerVegiOil);
		DCsAppleMilk.vegitableOil = FluidRegistry.getFluid("vegitable_oil");

		DCsAppleMilk.blockVegitableOil = new BlockOilFluid(DCsAppleMilk.vegitableOil, Material.water)
				.setBlockName("defeatedcrow.blockVegiOil");
		GameRegistry.registerBlock(DCsAppleMilk.blockVegitableOil, "defeatedcrow.blockVegiOil");
		DCsAppleMilk.vegitableOil.setBlock(DCsAppleMilk.blockVegitableOil);

		if (DCsAppleMilk.blockVegitableOil != null) {
			DCsAppleMilk.bucketVegiOil = new ItemBucketVegiOil(DCsAppleMilk.blockVegitableOil)
					.setUnlocalizedName("defeatedcrow.bucketVegiOil").setContainerItem(Items.bucket)
					.setCreativeTab(DCsAppleMilk.applemilk);
			GameRegistry.registerItem(DCsAppleMilk.bucketVegiOil, "defeatedcrow.bucketVegiOil");
			FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("vegitable_oil",
					FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(DCsAppleMilk.bucketVegiOil), new ItemStack(
					Items.bucket));

			DCsAppleMilk.bottleVegiOil = new ItemBottleVegiOil(DCsAppleMilk.blockVegitableOil)
					.setUnlocalizedName("defeatedcrow.bottleVegiOil")
					.setContainerItem(Item.getItemFromBlock(DCsAppleMilk.emptyBottle))
					.setCreativeTab(DCsAppleMilk.applemilk);
			GameRegistry.registerItem(DCsAppleMilk.bottleVegiOil, "defeatedcrow.bottleVegiOil");
			FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("vegitable_oil", 200),
					new ItemStack(DCsAppleMilk.bottleVegiOil),
					new ItemStack(Item.getItemFromBlock(DCsAppleMilk.emptyBottle)));
		}

		// camellia
		registerCamOil = new Fluid("camellia_oil").setDensity(800).setViscosity(1500);
		FluidRegistry.registerFluid(registerCamOil);
		DCsAppleMilk.camelliaOil = FluidRegistry.getFluid("camellia_oil");

		DCsAppleMilk.blockCamelliaOil = new BlockCamOilFluid(DCsAppleMilk.camelliaOil, Material.water)
				.setBlockName("defeatedcrow.blockCamOil");
		GameRegistry.registerBlock(DCsAppleMilk.blockCamelliaOil, "defeatedcrow.blockCamOil");
		DCsAppleMilk.camelliaOil.setBlock(DCsAppleMilk.blockCamelliaOil);

		if (DCsAppleMilk.blockCamelliaOil != null) {
			DCsAppleMilk.bucketCamOil = new ItemBucketCamOil(DCsAppleMilk.blockCamelliaOil)
					.setUnlocalizedName("defeatedcrow.bucketCamOil").setContainerItem(Items.bucket)
					.setCreativeTab(DCsAppleMilk.applemilk);
			GameRegistry.registerItem(DCsAppleMilk.bucketCamOil, "defeatedcrow.bucketCamOil");
			FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("camellia_oil",
					FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(DCsAppleMilk.bucketCamOil), new ItemStack(
					Items.bucket));

			DCsAppleMilk.bottleCamOil = new ItemBottleCamOil(DCsAppleMilk.blockCamelliaOil)
					.setUnlocalizedName("defeatedcrow.bottleCamOil")
					.setContainerItem(Item.getItemFromBlock(DCsAppleMilk.emptyBottle))
					.setCreativeTab(DCsAppleMilk.applemilk);
			GameRegistry.registerItem(DCsAppleMilk.bottleCamOil, "defeatedcrow.bottleCamOil");
			FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("camellia_oil", 200),
					new ItemStack(DCsAppleMilk.bottleCamOil),
					new ItemStack(Item.getItemFromBlock(DCsAppleMilk.emptyBottle)));
		}

		// alcohol
		registerShothuYoung = new Fluid("shothu_young").setDensity(1000).setViscosity(1000);
		FluidRegistry.registerFluid(registerShothuYoung);
		DCsAppleMilk.shothu_young = FluidRegistry.getFluid("shothu_young");

		registerWhiskeyYoung = new Fluid("whiskey_young").setDensity(1000).setViscosity(1000);
		FluidRegistry.registerFluid(registerWhiskeyYoung);
		DCsAppleMilk.whiskey_young = FluidRegistry.getFluid("whiskey_young");

		registerBrandyYoung = new Fluid("brandy_young").setDensity(1000).setViscosity(1000);
		FluidRegistry.registerFluid(registerBrandyYoung);
		DCsAppleMilk.brandy_young = FluidRegistry.getFluid("brandy_young");

		registerRumYoung = new Fluid("rum_young").setDensity(1000).setViscosity(1000);
		FluidRegistry.registerFluid(registerRumYoung);
		DCsAppleMilk.rum_young = FluidRegistry.getFluid("rum_young");

		registerVodkaYoung = new Fluid("vodka_young").setDensity(1000).setViscosity(1000);
		FluidRegistry.registerFluid(registerVodkaYoung);
		DCsAppleMilk.vodka_young = FluidRegistry.getFluid("vodka_young");

		registerShothu = new Fluid("shothu_dc").setDensity(1000).setViscosity(1000);
		FluidRegistry.registerFluid(registerShothu);
		DCsAppleMilk.shothu = FluidRegistry.getFluid("shothu_dc");

		registerWhiskey = new Fluid("whiskey_dc").setDensity(1000).setViscosity(1000);
		FluidRegistry.registerFluid(registerWhiskey);
		DCsAppleMilk.whiskey = FluidRegistry.getFluid("whiskey_dc");

		registerBrandy = new Fluid("brandy_dc").setDensity(1000).setViscosity(1000);
		FluidRegistry.registerFluid(registerBrandy);
		DCsAppleMilk.brandy = FluidRegistry.getFluid("brandy_dc");

		registerRum = new Fluid("rum_dc").setDensity(1000).setViscosity(1000);
		FluidRegistry.registerFluid(registerRum);
		DCsAppleMilk.rum = FluidRegistry.getFluid("rum_dc");

		registerVodka = new Fluid("vodka_dc").setDensity(1000).setViscosity(1000);
		FluidRegistry.registerFluid(registerVodka);
		DCsAppleMilk.vodka = FluidRegistry.getFluid("vodka_dc");

		registerSakeYoung = new Fluid("sake_young").setDensity(1000).setViscosity(1000);
		FluidRegistry.registerFluid(registerSakeYoung);
		DCsAppleMilk.sake_young = FluidRegistry.getFluid("sake_young");

		registerBeerYoung = new Fluid("beer_young").setDensity(1000).setViscosity(1000);
		FluidRegistry.registerFluid(registerBeerYoung);
		DCsAppleMilk.beer_young = FluidRegistry.getFluid("beer_young");

		registerWineYoung = new Fluid("wine_young").setDensity(1000).setViscosity(1000);
		FluidRegistry.registerFluid(registerWineYoung);
		DCsAppleMilk.wine_young = FluidRegistry.getFluid("wine_young");

		registerSake = new Fluid("sake_dc").setDensity(1000).setViscosity(1000);
		FluidRegistry.registerFluid(registerSake);
		DCsAppleMilk.sake = FluidRegistry.getFluid("sake_dc");

		registerBeer = new Fluid("beer_dc").setDensity(1000).setViscosity(1000);
		FluidRegistry.registerFluid(registerBeer);
		DCsAppleMilk.beer = FluidRegistry.getFluid("beer_dc");

		registerWine = new Fluid("wine_dc").setDensity(1000).setViscosity(1000);
		FluidRegistry.registerFluid(registerWine);
		DCsAppleMilk.wine = FluidRegistry.getFluid("wine_dc");

		DCsAppleMilk.bucketYoungAlcohol = new ItemBucketYoungAlcohol()
				.setUnlocalizedName("defeatedcrow.bucketYoungAlcohol").setContainerItem(Items.bucket)
				.setCreativeTab(DCsAppleMilk.applemilk);
		GameRegistry.registerItem(DCsAppleMilk.bucketYoungAlcohol, "defeatedcrow.bucketYoungAlcohol");

		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("shothu_young",
				FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(DCsAppleMilk.bucketYoungAlcohol, 1, 0),
				new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("whiskey_young",
				FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(DCsAppleMilk.bucketYoungAlcohol, 1, 1),
				new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("brandy_young",
				FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(DCsAppleMilk.bucketYoungAlcohol, 1, 2),
				new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("rum_young",
				FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(DCsAppleMilk.bucketYoungAlcohol, 1, 3),
				new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("vodka_young",
				FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(DCsAppleMilk.bucketYoungAlcohol, 1, 4),
				new ItemStack(Items.bucket));

		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("sake_young",
				FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(DCsAppleMilk.moromi, 1, 0), new ItemStack(
				Items.bucket));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("beer_young",
				FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(DCsAppleMilk.moromi, 1, 1), new ItemStack(
				Items.bucket));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("wine_young",
				FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(DCsAppleMilk.moromi, 1, 2), new ItemStack(
				Items.bucket));

		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("shothu_dc", 200), new ItemStack(
				DCsAppleMilk.itemLargeBottle, 1, 48), new ItemStack(Item.getItemFromBlock(DCsAppleMilk.emptyBottle)));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("whiskey_dc", 200), new ItemStack(
				DCsAppleMilk.itemLargeBottle, 1, 55), new ItemStack(Item.getItemFromBlock(DCsAppleMilk.emptyBottle)));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("brandy_dc", 200), new ItemStack(
				DCsAppleMilk.itemLargeBottle, 1, 56), new ItemStack(Item.getItemFromBlock(DCsAppleMilk.emptyBottle)));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("rum_dc", 200), new ItemStack(
				DCsAppleMilk.itemLargeBottle, 1, 53), new ItemStack(Item.getItemFromBlock(DCsAppleMilk.emptyBottle)));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("vodka_dc", 200), new ItemStack(
				DCsAppleMilk.itemLargeBottle, 1, 54), new ItemStack(Item.getItemFromBlock(DCsAppleMilk.emptyBottle)));

		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("sake_dc", 200), new ItemStack(
				DCsAppleMilk.itemLargeBottle, 1, 49), new ItemStack(Item.getItemFromBlock(DCsAppleMilk.emptyBottle)));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("beer_dc", 200), new ItemStack(
				DCsAppleMilk.itemLargeBottle, 1, 50), new ItemStack(Item.getItemFromBlock(DCsAppleMilk.emptyBottle)));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("wine_dc", 200), new ItemStack(
				DCsAppleMilk.itemLargeBottle, 1, 51), new ItemStack(Item.getItemFromBlock(DCsAppleMilk.emptyBottle)));

		DCsAppleMilk.proxy.registerFluidTex();

		(new BucketFillEvent()).register();
		DispenserEvent.instance.registerFluidDispence();
		FluidDispenser.load();

	}

	static void addTools() {
		DCsAppleMilk.DCgrater = (new ItemGrater()).setUnlocalizedName("defeatedcrow.grater").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.chopsticks = (new ItemChopsticks()).setUnlocalizedName("defeatedcrow.chopsticks").setCreativeTab(
				DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.chalcedonyKnife = (new ItemChalcedonyKnife(DCsAppleMilk.enumToolMaterialChalcedony))
				.setUnlocalizedName("defeatedcrow.chalcedonyKnife").setCreativeTab(DCsAppleMilk.applemilk);

		DCsAppleMilk.pruningShears = (new ItemChalcedonyShears(DCsAppleMilk.enumToolMaterialChalcedony))
				.setUnlocalizedName("defeatedcrow.chalcedonyShears").setCreativeTab(DCsAppleMilk.applemilk);

		DCsAppleMilk.firestarter = (new ItemFireStarter()).setUnlocalizedName("defeatedcrow.firestarter")
				.setCreativeTab(DCsAppleMilk.applemilk);

		DCsAppleMilk.chalcedonyHammer = (new ItemChalcedonyHammer(DCsAppleMilk.enumToolMaterialChalcedony))
				.setUnlocalizedName("defeatedcrow.chalcedonyStoneCutter").setCreativeTab(DCsAppleMilk.applemilk);

		DCsAppleMilk.monocle = (new ItemChalcedonyMonocle(ItemArmor.ArmorMaterial.IRON,
				DCsAppleMilk.proxy.addArmor("monocle"), 0)).setUnlocalizedName("defeatedcrow.monocle").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.onixSword = (new ItemOnixSword()).setUnlocalizedName("defeatedcrow.onixSword").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.yuzuGatling = (new ItemYuzuGatling()).setUnlocalizedName("defeatedcrow.yuzuGatling")
				.setCreativeTab(DCsAppleMilk.applemilkMagic);

		DCsAppleMilk.fossilCannon = (new ItemFossilCannon()).setUnlocalizedName("defeatedcrow.fossilCannon")
				.setCreativeTab(DCsAppleMilk.applemilkMagic);

		DCsAppleMilk.eightEyesArm = (new ItemDebugArm()).setUnlocalizedName("defeatedcrow.debugArm");

	}

	static void addFoods() {
		DCsAppleMilk.bakedApple = (new ItemBakedApple(7, 7, false)).setUnlocalizedName("defeatedcrow.bakedApple")
				.setCreativeTab(DCsAppleMilk.applemilkFood);

		DCsAppleMilk.appleTart = (new ItemAppleTart()).setUnlocalizedName("defeatedcrow.appleTart").setCreativeTab(
				DCsAppleMilk.applemilkFood);

		DCsAppleMilk.appleSandwich = (new ItemAppleSandwich()).setUnlocalizedName("defeatedcrow.appleSandwich")
				.setCreativeTab(DCsAppleMilk.applemilkFood);

		DCsAppleMilk.toffyApple = (new ItemToffyApple(4, 4, false)).setUnlocalizedName("defeatedcrow.toffyApple")
				.setCreativeTab(DCsAppleMilk.applemilkFood);

		DCsAppleMilk.icyToffyApple = (new ItemIcyToffyApple(4, 4, false)).setUnlocalizedName(
				"defeatedcrow.icyToffyApple").setCreativeTab(DCsAppleMilk.applemilkFood);

		DCsAppleMilk.chocolateFruits = (new ItemChocoFruits(6, 6, false)).setUnlocalizedName(
				"defeatedcrow.fruitsChocolate").setCreativeTab(DCsAppleMilk.applemilkFood);

		DCsAppleMilk.foodTea = (new ItemFoodTea()).setUnlocalizedName("defeatedcrow.foodTea").setCreativeTab(
				DCsAppleMilk.applemilkMaterial);

		DCsAppleMilk.gratedApple = (new ItemGratedApple(2, 2, false)).setUnlocalizedName("defeatedcrow.gratedApple")
				.setCreativeTab(DCsAppleMilk.applemilkMaterial);

		DCsAppleMilk.mincedFoods = (new ItemMincedFoods()).setUnlocalizedName("defeatedcrow.mincedItem")
				.setCreativeTab(DCsAppleMilk.applemilkMaterial);

		DCsAppleMilk.yeast = (new ItemYeast()).setUnlocalizedName("defeatedcrow.yeast").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.moromi = (new ItemMoromi()).setUnlocalizedName("defeatedcrow.moromi").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.EXItems = (new ItemEXItem()).setUnlocalizedName("defeatedcrow.extraItems").setCreativeTab(
				DCsAppleMilk.applemilkMaterial);

		DCsAppleMilk.condensedMIlk = (new ItemCondensedMilk(1, 1, false)).setUnlocalizedName(
				"defeatedcrow.condensedMilk").setCreativeTab(DCsAppleMilk.applemilkMaterial);

		DCsAppleMilk.bowlBlock = (new BlockBowl()).setBlockName("defeatedcrow.bowlBlock").setCreativeTab(
				DCsAppleMilk.applemilkFood);

		DCsAppleMilk.bowlJP = (new BlockBowlJP()).setBlockName("defeatedcrow.bowlBlockJP").setCreativeTab(
				DCsAppleMilk.applemilkFood);

		DCsAppleMilk.foodPlate = (new BlockFoodPlate()).setBlockName("defeatedcrow.foodPlate").setCreativeTab(
				DCsAppleMilk.applemilkFood);

		DCsAppleMilk.chocoBlock = (new BlockChocoGift()).setBlockName("defeatedcrow.chocolateGift").setCreativeTab(
				DCsAppleMilk.applemilkFood);

		DCsAppleMilk.teacupBlock = (new BlockFilledCup()).setBlockName("defeatedcrow.filledCup").setCreativeTab(
				DCsAppleMilk.applemilkFood);

		DCsAppleMilk.teaCup2 = (new BlockFilledCup2()).setBlockName("defeatedcrow.filledCup2").setCreativeTab(
				DCsAppleMilk.applemilkFood);

		// DCsAppleMilk.emptyWallMug = (new ItemEmptyWallMug()).
		// setUnlocalizedName("defeatedcrow.emptyWallMug").
		// setCreativeTab(DCsAppleMilk.applemilk);
		//
		// DCsAppleMilk.wallMug = (new ItemWallMug()).
		// setUnlocalizedName("defeatedcrow.wallMug").
		// setCreativeTab(DCsAppleMilk.applemilkFood);

		DCsAppleMilk.blockIcecream = (new BlockIceCream()).setBlockName("defeatedcrow.iceCreamBlock").setCreativeTab(
				DCsAppleMilk.applemilkFood);

		DCsAppleMilk.alcoholCup = (new BlockAlcoholCup()).setBlockName("defeatedcrow.alcoholCup").setCreativeTab(
				DCsAppleMilk.applemilkFood);

		DCsAppleMilk.cocktail = (new BlockCocktail()).setBlockName("defeatedcrow.cocktail").setCreativeTab(
				DCsAppleMilk.applemilkFood);

		DCsAppleMilk.cocktail2 = (new BlockCocktail2()).setBlockName("defeatedcrow.cocktail2").setCreativeTab(
				DCsAppleMilk.applemilkFood);

		DCsAppleMilk.cocktailSP = (new BlockCocktailSP()).setBlockName("defeatedcrow.cocktailSP").setCreativeTab(
				DCsAppleMilk.applemilkFood);

		DCsAppleMilk.emptyBottle = (new BlockEmptyBottle()).setBlockName("defeatedcrow.emptyBottle").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.largeBottle = (new BlockLargeBottle()).setBlockName("defeatedcrow.largeBottle");

		DCsAppleMilk.itemLargeBottle = (new ItemLargeBottle()).setUnlocalizedName("defeatedcrow.itemBottle")
				.setCreativeTab(DCsAppleMilk.applemilk);

		DCsAppleMilk.cordial = (new BlockCordial()).setBlockName("defeatedcrow.blockCordial");

		DCsAppleMilk.barrel = (new BlockBarrel()).setBlockName("defeatedcrow.blockBarrel").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.itemCordial = (new ItemCordial()).setUnlocalizedName("defeatedcrow.itemCordial").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.baseSoupBowl = (new ItemBaseSoupBowl()).setUnlocalizedName("defeatedcrow.basesoupitem")
				.setCreativeTab(DCsAppleMilk.applemilkFood);

		DCsAppleMilk.blockDummyAlcohol = (new BlockDummyFluid()).setBlockName("defeatedcrow.blockDummyAlcohol");

		DCsAppleMilk.blockDummyAlcohol2 = (new BlockDummyFluid2()).setBlockName("defeatedcrow.blockDummyAlcohol2");
	}

	static void addContainers() {
		DCsAppleMilk.woodBox = (new BlockWoodBox()).setBlockName("defeatedcrow.WoodBox").setCreativeTab(
				DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.appleBox = (new BlockAppleBox()).setBlockName("defeatedcrow.appleBox").setCreativeTab(
				DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.vegiBag = (new BlockVegiBag()).setBlockName("defeatedcrow.vegiBag").setCreativeTab(
				DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.cardboard = (new BlockCardboard()).setBlockName("defeatedcrow.cardboardBox").setCreativeTab(
				DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.charcoalBox = (BlockCharcoalBox) (new BlockCharcoalBox()).setBlockName(
				"defeatedcrow.charcoalContainer").setCreativeTab(DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.gunpowderContainer = (new BlockGunpowderContainer()).setBlockName(
				"defeatedcrow.gunpowderContainer").setCreativeTab(DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.mobBlock = (new BlockMobDrop()).setBlockName("defeatedcrow.mobDropBox").setCreativeTab(
				DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.mushroomBox = (new BlockMushBox()).setBlockName("defeatedcrow.mushroomBox").setCreativeTab(
				DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.eggBasket = (new BlockEggBasket()).setBlockName("defeatedcrow.eggBasket").setCreativeTab(
				DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.melonBomb = (new BlockMelonBomb()).setBlockName("defeatedcrow.compressedMelon").setCreativeTab(
				DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.silkyMelon = (new BlockSilkyMelon()).setBlockName("defeatedcrow.compressedSilkyMelon")
				.setCreativeTab(DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.wipeBox = (new BlockWipeBox()).setBlockName("defeatedcrow.wipeBox").setCreativeTab(
				DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.wipeBox2 = (new BlockWipeBox2()).setBlockName("defeatedcrow.wipeBox2");

		DCsAppleMilk.flowerPot = (new BlockFlowerPot()).setBlockName("defeatedcrow.flowerPot").setCreativeTab(
				DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.containerWBottle = (new BlockContainerWaterBottle()).setBlockName("defeatedcrow.containerBottleW")
				.setCreativeTab(DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.containerSaddle = (new BlockContainerSaddle()).setBlockName("defeatedcrow.containerSaddle")
				.setCreativeTab(DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.containerItemDoorW = (new ItemContainerDoor(Blocks.wooden_door))
				.setUnlocalizedName("defeatedcrow.containeritemDoorW")
				.setTextureName("defeatedcrow:containeritem_doorW").setCreativeTab(DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.containerItemDoorI = (new ItemContainerDoor(Blocks.iron_door))
				.setUnlocalizedName("defeatedcrow.containeritemDoorI")
				.setTextureName("defeatedcrow:containeritem_doorI").setCreativeTab(DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.flowerBase = (new BlockFlowerVase()).setBlockName("defeatedcrow.flowerVase").setCreativeTab(
				DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.hedge = (new BlockHedge()).setBlockName("defeatedcrow.hedge").setCreativeTab(
				DCsAppleMilk.applemilkContainer);

	}

	static void addDecorations() {
		DCsAppleMilk.bowlRack = (new BlockBowlRack()).setBlockName("defeatedcrow.bowlRack").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.Basket = (new BlockBasket()).setBlockName("defeatedcrow.basket").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.chopsticksBox = (new BlockChopsticksBox()).setBlockName("defeatedcrow.chopsticksBox")
				.setCreativeTab(DCsAppleMilk.applemilk);

		DCsAppleMilk.chopsticks = (new ItemChopsticks()).setUnlocalizedName("defeatedcrow.chopsticks").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.flintBlock = (new BlockFlint(Material.rock, false)).setBlockName("defeatedcrow.flintBlock")
				.setCreativeTab(DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.chalcedony = (new BlockChalcedony(Material.rock, false)).setBlockName("defeatedcrow.chalcedony")
				.setCreativeTab(DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.cLamp = (new BlockChalcedonyLamp(Material.glass, false)).setBlockName(
				"defeatedcrow.chalcedonyLamp").setCreativeTab(DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.cLampOpaque = (new BlockChalcedonyLampOp(Material.glass, false)).setBlockName(
				"defeatedcrow.chalcedonyLampOp").setCreativeTab(DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.rotaryDial = (new BlockRotaryDial()).setBlockName("defeatedcrow.rotaryDial").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.chalcenonyPanel = (new BlockCPanel()).setBlockName("defeatedcrow.chalcedonyPanel").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.yuzuFence = (new BlockYuzuFence()).setBlockName("defeatedcrow.yuzuFence").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.woodPanel = (new BlockWoodPanel()).setBlockName("defeatedcrow.woodPanel").setCreativeTab(
				DCsAppleMilk.applemilkContainer);

		DCsAppleMilk.crowDoll = (new BlockCrowDoll()).setBlockName("defeatedcrow.crowFigure").setCreativeTab(
				DCsAppleMilk.applemilk);
	}

	static void addPlants() {
		DCsAppleMilk.leafTea = (new ItemLeafTea()).setUnlocalizedName("defeatedcrow.leafTea").setCreativeTab(
				DCsAppleMilk.applemilkMaterial);

		DCsAppleMilk.itemMintSeed = (new ItemMintSeed()).setUnlocalizedName("defeatedcrow.seedMint")
				.setCreativeTab(DCsAppleMilk.applemilkMaterial).setTextureName("defeatedcrow:seed_mint");

		DCsAppleMilk.saplingTea = (new BlockSaplingTea()).setBlockName("defeatedcrow.saplingTea").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.teaTree = (new BlockTeaTree()).setBlockName("defeatedcrow.teaTree").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.saplingYuzu = (new BlockYuzuSapling()).setBlockName("defeatedcrow.saplingYuzu").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.logYuzu = (new BlockYuzuLog()).setBlockName("defeatedcrow.logYuzu").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.leavesYuzu = (new BlockYuzuLeaves()).setBlockName("defeatedcrow.leavesYuzu").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.cassisTree = (new BlockCassisTree()).setBlockName("defeatedcrow.cassisTree").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.cropMint = (new BlockMintCrop()).setBlockName("defeatedcrow.cropMint");

		DCsAppleMilk.clam = (new ItemClam()).setUnlocalizedName("defeatedcrow.clam").setCreativeTab(
				DCsAppleMilk.applemilkFood);

		DCsAppleMilk.princessClam = (new ItemPrincessClam()).setUnlocalizedName("defeatedcrow.princessClam")
				.setCreativeTab(DCsAppleMilk.applemilkMagic);

		DCsAppleMilk.clamSand = (new BlockClamSand()).setBlockName("defeatedcrow.clamSand").setCreativeTab(
				DCsAppleMilk.applemilk);
	}

	static void addMaterials() {
		DCsAppleMilk.icyCrystal = (new ItemIcyCrystal()).setUnlocalizedName("defeatedcrow.icyCrystal").setCreativeTab(
				DCsAppleMilk.applemilkMaterial);

		DCsAppleMilk.inkStick = (new ItemInkStick()).setUnlocalizedName("defeatedcrow.inkStick").setCreativeTab(
				DCsAppleMilk.applemilkMaterial);

		DCsAppleMilk.stickCarbon = (new ItemCarbonStick()).setUnlocalizedName("defeatedcrow.stickCarbon")
				.setCreativeTab(DCsAppleMilk.applemilkMaterial);

		DCsAppleMilk.oreDust = (new ItemOreDust()).setUnlocalizedName("defeatedcrow.oreDust").setCreativeTab(
				DCsAppleMilk.applemilkMaterial);

		DCsAppleMilk.strangeSlag = (new ItemStrangeSlag()).setUnlocalizedName("defeatedcrow.strangeSlag")
				.setCreativeTab(DCsAppleMilk.applemilkMagic);

		DCsAppleMilk.fossilScale = (new ItemFossilScale()).setUnlocalizedName("defeatedcrow.fossilScale")
				.setCreativeTab(DCsAppleMilk.applemilkMagic);
	}

	static void addMachines() {
		DCsAppleMilk.teaMakerNext = (new BlockTeaMakerNext()).setBlockName("defeatedcrow.teaMakerNext").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.teaMakerBlack = (new BlockTeaMakerBlack()).setBlockName("defeatedcrow.teaMakerBlack")
				.setCreativeTab(DCsAppleMilk.applemilk);

		DCsAppleMilk.emptyCup = (new BlockEmptyCup()).setBlockName("defeatedcrow.emptyCup").setCreativeTab(
				DCsAppleMilk.applemilk);

		// DCsAppleMilk.emptyPan = (new BlockEmptyPan()).
		// setBlockName("defeatedcrow.soupPanEmpty").
		// setCreativeTab(DCsAppleMilk.applemilk);

		DCsAppleMilk.emptyPanGaiden = (new BlockEmptyPanG()).setBlockName("defeatedcrow.soupPanG").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.filledSoupPan = (new BlockFilledSoupPan()).setBlockName("defeatedcrow.filledSoupPan");

		DCsAppleMilk.teppanII = (new BlockTeppanII()).setBlockName("defeatedcrow.cookingIronPlateII").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.iceMaker = (new BlockIceMaker()).setBlockName("defeatedcrow.iceMaker").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.processor = (new BlockProcessor()).setBlockName("defeatedcrow.processor").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.advProcessor = (new BlockAdvProcessor()).setBlockName("defeatedcrow.advProcessor").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.evaporator = (new BlockEvaporator()).setBlockName("defeatedcrow.evaporator").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.slotPanel = (new ItemSlotPanel()).setUnlocalizedName("defeatedcrow.slotPanel").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.jawPlate = (new ItemJawplate()).setUnlocalizedName("defeatedcrow.jawPlate").setCreativeTab(
				DCsAppleMilk.applemilk);

		// エネルギー
		DCsAppleMilk.batteryItem = (new ItemBattery()).setUnlocalizedName("defeatedcrow.battery").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.yuzuBat = (new BlockYuzuBat()).setBlockName("defeatedcrow.yuzuBatContainer").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.gelBat = (new BlockGelBat()).setBlockName("defeatedcrow.gelBatContainer").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.redGel = (new BlockRedGel()).setBlockName("defeatedcrow.redGel").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.yuzuGel = (new BlockYuzuLight()).setBlockName("defeatedcrow.lightGel").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.batBox = (new BlockBatBox()).setBlockName("defeatedcrow.batBox").setCreativeTab(
				DCsAppleMilk.applemilk);

		DCsAppleMilk.handleEngine = (new BlockHandleEngine()).setBlockName("defeatedcrow.EHandle").setCreativeTab(
				DCsAppleMilk.applemilk);

		// インセンス
		DCsAppleMilk.essentialOil = (new ItemEssentialOil()).setUnlocalizedName("defeatedcrow.essentialOil")
				.setCreativeTab(DCsAppleMilk.applemilkMagic);

		DCsAppleMilk.dustWood = (new ItemWoodDust()).setUnlocalizedName("defeatedcrow.dustWood").setCreativeTab(
				DCsAppleMilk.applemilkMaterial);

		DCsAppleMilk.incenseBase = (new BlockIncenseBase()).setBlockName("defeatedcrow.incenseBase").setCreativeTab(
				DCsAppleMilk.applemilkMagic);

		DCsAppleMilk.incenseRose = (ItemIncenseRose) (new ItemIncenseRose()).setUnlocalizedName(
				"defeatedcrow.incense_rose").setCreativeTab(DCsAppleMilk.applemilkMagic);

		DCsAppleMilk.incenseApple = (ItemIncenseApple) (new ItemIncenseApple()).setUnlocalizedName(
				"defeatedcrow.incense_apple").setCreativeTab(DCsAppleMilk.applemilkMagic);

		DCsAppleMilk.incenseIce = (ItemIncenseIce) (new ItemIncenseIce())
				.setUnlocalizedName("defeatedcrow.incense_ice").setCreativeTab(DCsAppleMilk.applemilkMagic);

		DCsAppleMilk.incenseClam = (ItemIncenseClam) (new ItemIncenseClam()).setUnlocalizedName(
				"defeatedcrow.incense_clam").setCreativeTab(DCsAppleMilk.applemilkMagic);

		DCsAppleMilk.incenseMint = (ItemIncenseMint) (new ItemIncenseMint()).setUnlocalizedName(
				"defeatedcrow.incense_mint").setCreativeTab(DCsAppleMilk.applemilkMagic);

		DCsAppleMilk.incenseLavender = (ItemIncenseLavender) (new ItemIncenseLavender()).setUnlocalizedName(
				"defeatedcrow.incense_lavender").setCreativeTab(DCsAppleMilk.applemilkMagic);

		DCsAppleMilk.incenseSandalwood = (ItemIncenseSandalwood) (new ItemIncenseSandalwood()).setUnlocalizedName(
				"defeatedcrow.incense_sandalwood").setCreativeTab(DCsAppleMilk.applemilkMagic);

		DCsAppleMilk.incenseAgar = (ItemIncenseAgar) (new ItemIncenseAgar()).setUnlocalizedName(
				"defeatedcrow.incense_aloeswood").setCreativeTab(DCsAppleMilk.applemilkMagic);

		DCsAppleMilk.incenseFrank = (ItemIncenseFrankincense) (new ItemIncenseFrankincense()).setUnlocalizedName(
				"defeatedcrow.incense_frankincense").setCreativeTab(DCsAppleMilk.applemilkMagic);

		DCsAppleMilk.incenseYuzu = (ItemIncenseYuzu) (new ItemIncenseYuzu()).setUnlocalizedName(
				"defeatedcrow.incense_yuzu").setCreativeTab(DCsAppleMilk.applemilkMagic);

		DCsAppleMilk.incenseVanilla = (ItemIncenseVanilla) (new ItemIncenseVanilla()).setUnlocalizedName(
				"defeatedcrow.incense_vanilla").setCreativeTab(DCsAppleMilk.applemilkMagic);

	}

}
