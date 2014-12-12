/**
 * Copyright (c) defeatedcrow, 2013
 * URL:http://forum.minecraftuser.jp/viewtopic.php?f=13&t=17657
 *
 * Apple&Milk&Tea! is distributed under the terms of the Minecraft Mod Public License 1.0, or MMPL.
 * Please check the License(MMPL_1.0).txt included in the package file of this Mod.
 */

package mods.defeatedcrow.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

import mods.defeatedcrow.api.*;
import mods.defeatedcrow.common.block.*;
import mods.defeatedcrow.common.block.container.BlockCharcoalBox;
import mods.defeatedcrow.common.entity.*;
import mods.defeatedcrow.common.entity.edible.*;
import mods.defeatedcrow.common.item.*;
import mods.defeatedcrow.common.item.magic.*;
import mods.defeatedcrow.common.world.*;
import mods.defeatedcrow.common.world.village.*;
import mods.defeatedcrow.event.*;
import mods.defeatedcrow.handler.*;
import mods.defeatedcrow.plugin.*;
import mods.defeatedcrow.plugin.craftguide.LoadCraftGuidePlugin;
import mods.defeatedcrow.plugin.mce.MCEconomyPlugin;
import mods.defeatedcrow.potion.*;
import mods.defeatedcrow.recipe.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.src.*;
import net.minecraft.stats.Achievement;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.*;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(
		modid = "DCsAppleMilk",
		name = "Apple&Milk&Tea!",
		version = "1.7.10_2.0.beta8",
		dependencies = "required-after:Forge@[10.13.0.1207,);after:IC2;after:Thaumcraft;after:BambooMod;after:pamharvestcraft;after:Forestry;after:mod_ecru_MapleTree"
		)
public class DCsAppleMilk{
	
	//プロキシの登録
	@SidedProxy(clientSide = "mods.defeatedcrow.client.ClientProxy", serverSide = "mods.defeatedcrow.common.CommonProxy")
	public static CommonProxy proxy;
	
	//インスタンスの生成
	@Instance("DCsAppleMilk")
    public static DCsAppleMilk instance;
	
	//クリエイティブタブの追加
	public static final CreativeTabs applemilk = new CreativeTabAMT("applemilk");
	public static final CreativeTabs applemilkFood = new CreativeTabAMTFood("applemilkfood");
	public static final CreativeTabs applemilkContainer = new CreativeTabAMTContainer("applemilkcontainer");
	public static final CreativeTabs applemilkMagic = new CreativeTabAMTMagic("applemilkmagic");
	
	//ブロックのインスタンス
	//ツール
	public static Block  teaMakerNext;
	public static Block  teaMakerBlack;
	public static Block  emptyCup;
	public static Block  iceMaker;
	public static Block  emptyPanGaiden;
	public static Block  filledChocoPan;
	public static Block  teppann;
	public static Block  prosessor;
	public static Block  evaporator;
	public static Block  advProsessor;
	public static Block  incenseBase;
	//エネルギー
	public static Block  batBox;
	public static Block  redGel;
	public static Block  yuzuBat;
	public static Block  gelBat;
	//たべもの
	public static Block  teacupBlock;
	public static Block  teaCup2;
	public static Block  blockIcecream;
	public static Block  cocktail;
	public static Block  cocktail2;
	public static Block  alcoholCup;
	public static Block  bowlBlock;
	public static Block  bowlJP;
	public static Block  foodPlate;
	public static Block  chocoBlock;
	//ビン
	public static Block  emptyBottle;
	public static Block  largeBottle;
	public static Block  cordial;
	public static Block  barrel;
	//コンテナ
	public static Block  woodBox;
	public static Block  appleBox;
	public static Block  vegiBag;
	public static Block  cardboard;
	public static BlockCharcoalBox  charcoalBox;
	public static Block  gunpowderContainer;
	public static Block  eggBasket;
	public static Block  mushroomBox;
	public static Block  melonBomb;
	public static Block  wipeBox;
	public static Block  wipeBox2;
	public static Block  mobBlock;
	public static Block  silkyMelon;
	public static Block  flowerPot;
	public static Block  yuzuFence;
	//自然
	public static Block  saplingTea;
	public static Block  teaTree;
	public static Block  cassisTree;
	public static Block  clamSand;
	public static Block  cropMint;
	public static Block  saplingYuzu;
	public static Block  logYuzu;
	public static Block  leavesYuzu;
	//インテリア
	public static Block  bowlRack;
	public static Block  Basket;
	public static Block  chopsticksBox;
	//カルセドニー
	public static Block  flintBlock;
	public static Block  chalcedony;
	public static Block  cLamp;
	public static Block  rotaryDial;
	public static Block  chalcenonyPanel;
	
	//アイテムのインスタンス
	//食べ物アイテム
	public static Item  bakedApple;
	public static Item  appleTart;
	public static Item  toffyApple;
	public static Item  icyToffyApple;
	public static Item  appleSandwich;
	public static Item  chocolateFruits;
	
	//食材
	public static Item  leafTea;
	public static Item  gratedApple;
	public static Item  mincedFoods;
	public static Item  condensedMIlk;
	public static Item  clam;
	
	//素材系
	public static Item  EXItems;
	public static Item  inkStick;
	public static Item  foodTea;
	public static Item  DCgrater;
	public static Item  icyCrystal;
	public static Item  itemMintSeed;
	public static Item  stickCarbon;
	
	//玉髄ツール
	public static Item  chalcedonyKnife;
	public static Item  firestarter;
	public static Item  chalcedonyHammer;
	public static Item  monocle;
	public static Item  onixSword;
	public static Item  pruningShears;
	
	//その他ツール
	public static Item  chopsticks;
	public static Item  milkBottle;
	
	//装置関係
	public static Item  batteryItem;
	public static Item  slotPanel;
	
	//酒造等
	public static Item  itemLargeBottle;
	public static Item  itemCordial;
	public static Item  yeast;
	public static Item  moromi;
	public static Item  youngAlcohol;
	
	//魔法関係
	public static Item  princessClam;
	public static Item  dustWood;
	public static Item  essentialOil;
	
	public static ItemIncenseApple  incenseApple;
	public static ItemIncenseRose  incenseRose;
	public static ItemIncenseMint  incenseMint;
	public static ItemIncenseIce  incenseIce;
	public static ItemIncenseClam  incenseClam;
	public static ItemIncenseLavender  incenseLavender;
	public static ItemIncenseSandalwood  incenseSandalwood;
	public static ItemIncenseAgar  incenseAgar;
	public static ItemIncenseFrankincense  incenseFrank;
	
	//液体
	public static Fluid  vegitableOil;
	public static Fluid  camelliaOil;
	public static Fluid  shothu_young;
	public static Fluid  whiskey_young;
	public static Fluid  brandy_young;
	public static Fluid  shothu;
	public static Fluid  whiskey;
	public static Fluid  brandy;
	public static Fluid  sake_young;
	public static Fluid  beer_young;
	public static Fluid  wine_young;
	public static Fluid  sake;
	public static Fluid  beer;
	public static Fluid  wine;
	
	public static Block  blockVegitableOil;
	public static Block  blockCamelliaOil;
	public static Block  blockDummyAlcohol;
	public static Block  blockDummyAlcohol2;
	
	public static Item  bucketVegiOil;
	public static Item  bottleVegiOil;
	public static Item  bucketCamOil;
	public static Item  bottleCamOil;
	public static Item  bucketYoungAlcohol;
	public static Item  dummyItem;
	
	//以下は没アイテム。クラスだけ残してある関係でインスタンスもとってあるが、中身はnullである
	public static Block  emptyPan;
	public static Block  filledPan;
	public static Block  filledPan2;
	public static Block  canister;
	public static Item  emptyWallMug;
	public static Item  wallMug;
	
	//ポーションのインスタンス
	public static Potion Immunization;
	public static Potion prvExplode;
	public static Potion prvProjectile;
	public static Potion reflex;
	public static Potion absEXP;
	public static Potion absHeal;
	public static Potion suffocation;
	public static PotionProtectionEX prvSuffocation;
	public static Potion hallucinations;
	public static Potion confinement;
	
	//gui
	public int guiIdAutoMaker = 1;
	public int guiIceMaker = 2;
	public int guiProsessor = 3;
	public int guiEvaporator = 4;
	public int guiAdvProsessor = 5;
	public int guiBatBox = 6;
	
	//villager関連
	public static VillagerCafe villager;
	public static VillagerYome villagerYome;
	
	//前提CoreModsの導入チェック
	public static boolean RequiredCoreEnabled = false;

	public static boolean SuccessLoadIC2 = false;
	public static boolean SuccessLoadBamboo = false;
	public static boolean SuccessLoadBoP = false;
	public static boolean SuccessLoadTofu = false;
	public static boolean SuccessLoadThaumcraft = false;
	public static boolean SuccessLoadEconomy = false;
	public static boolean SuccessLoadSSector = false;
	public static boolean SuccessLoadGummi = false;
	public static boolean[] SuccessLoadGrowth = new boolean[] {false, false, false};
	public static boolean SuccessLoadMapleTree = false;
	public static boolean SuccessLoadExtraTrees = false;
	public static boolean SuccessLoadExBucket = false;
	public static boolean SuccessLoadRC = false;
	public static boolean SuccessLoadSugi = false;
	public static boolean SuccessLoadDart = false;
	public static boolean SuccessLoadTE4 = false;
	public static boolean SuccessLoadWa = false;
	public static boolean SuccessLoadCGuide = false;
	public static boolean SuccessLoadFFM = false;
	public static boolean SuccessLoadBC = false;
	public static boolean SuccessLoadACore = false;
	
	//内部処理用
	public static boolean debugMode = false;
	public static boolean succeedAddPotion = false;
	
	//新ツール属性の追加
	public static Item.ToolMaterial enumToolMaterialChalcedony;
	
	//レンダー登録用ID
	public static int modelTeaMaker;
	public static int modelCup;
	public static int modelPan;
	public static int modelTeaTree;
	public static int modelFilledCup;
	public static int modelBowl;
	public static int modelRack;
	public static int modelCLamp;
	public static int modelBasket;
	public static int modelFoodPlate;
	public static int modelTeppann;
	public static int modelBowlJP;
	public static int modelCupSummer;
	public static int modelChopsticks;
	public static int modelKinoko;
	public static int modelEggBasket;
	public static int modelMelonBomb;
	public static int modelChocoPan;
	public static int modelMakerNext;
	public static int modelAutoMaker;
	public static int modelWipe;
	public static int modelIceMaker;
	public static int modelIceCream;
	public static int modelDial;
	public static int modelCocktail;
	public static int modelLargeBottle;
	public static int modelCanister;
	public static int modelCordial;
	public static int modelCassisTree;
	public static int modelAlcoholCup;
	public static int modelProsessor;
	public static int modelEvaporator;
	public static int modelJawCrusher;
	public static int modelCPanel;
	public static int modelIncenseBase;
	public static int modelYuzuBat;
	public static int modelGelBat;
	public static int modelBatBox;
	public static int modelFlowerPot;
	public static int modelYuzuFence;
	
	public static final String[] TEX_PASS = new String[] {
		"defeatedcrow:",
		"defeatedcrow:x32/",
		"defeatedcrow:x32alt/"
	};
	
	public static final String[] TEX_PASS_ENTITY = new String[] {
		"defeatedcrow:textures/entity/",
		"defeatedcrow:textures/entity/x32/",
		"defeatedcrow:textures/entity/x32alt/"
	};
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		//前提MODの導入確認ログ
		(new RequiredCoreModChecker()).coreModCheck();
		
		//APIのインスタンス生成
		RegisterManager.load();
		
		//Configuration setting
		//コンフィグを生成する
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		(new DCsConfig()).config(cfg);
		
		//デバッグモードのチェック
		String str = DCsConfig.debugPass;
		if (str != null && !str.isEmpty()){
			boolean b = Util.checkDebugModePass(str);
			if (b) AMTLogger.info("Starting in Debug Mode.");
			this.debugMode = b;
		}
		
		//Registering
		//Material
		//ツール属性の内容を登録する
		enumToolMaterialChalcedony = EnumHelper.addToolMaterial("CHALCEDONY", 2, 128, 5.0F, 4.0F, 18);
		enumToolMaterialChalcedony.customCraftingMaterial = Items.flint;
		
		//ブロックやアイテムの読み込みと登録
		MaterialRegister.instance.load();
		MaterialRegister.instance.addFluid();
		
		//ポーションIDが拡張出来ているかのチェックを行い、成功時のみポーションを追加する。
		int potion = Potion.potionTypes.length;
		try
		{
			MaterialRegister.instance.addPotion();
			this.succeedAddPotion = true;
			AMTLogger.debugInfo("Succeed to add new potion effect.");
		}
		catch  (Exception e)
		{
			AMTLogger.debugInfo("Failed to add new potion effect.");
		}
		
		
		//実績の追加
		(new AchievementRegister()).register();
		
		//particle用テクスチャ登録
		proxy.registerTex();
		
	    //Registering OreDictionary  
		//ForgeのOreDicyionaryの登録部分をpreInitに移した
		(new RegisterOreHandler()).register();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) throws IOException
	{
		//Registering TileEntity
		//TileEntityの登録はプロキシクラスに任せる
		proxy.registerTileEntity();
		
		//Registering Entity
		//Entityの登録
		if (DCsConfig.entityIdMelon == 0) DCsConfig.entityIdMelon = EntityRegistry.findGlobalUniqueEntityId();
		if (DCsConfig.entityIdIce == 0) DCsConfig.entityIdIce = EntityRegistry.findGlobalUniqueEntityId();
		if (DCsConfig.entityIdCup == 0) DCsConfig.entityIdCup = EntityRegistry.findGlobalUniqueEntityId();
		if (DCsConfig.entityIdCup2 == 0) DCsConfig.entityIdCup2 = EntityRegistry.findGlobalUniqueEntityId();
		if (DCsConfig.entityIdBowl == 0) DCsConfig.entityIdBowl = EntityRegistry.findGlobalUniqueEntityId();
		if (DCsConfig.entityIdBowlJP == 0) DCsConfig.entityIdBowlJP = EntityRegistry.findGlobalUniqueEntityId();
		if (DCsConfig.entityIdSteak == 0) DCsConfig.entityIdSteak = EntityRegistry.findGlobalUniqueEntityId();
		if (DCsConfig.entityIdCocktail == 0) DCsConfig.entityIdCocktail = EntityRegistry.findGlobalUniqueEntityId();
		if (DCsConfig.entityIdAlcohol == 0) DCsConfig.entityIdAlcohol = EntityRegistry.findGlobalUniqueEntityId();
		if (DCsConfig.entityIdSandwich == 0) DCsConfig.entityIdSandwich = EntityRegistry.findGlobalUniqueEntityId();
		if (DCsConfig.entityIdTart == 0) DCsConfig.entityIdTart = EntityRegistry.findGlobalUniqueEntityId();
		if (DCsConfig.entityIdSilkMelon == 0) DCsConfig.entityIdSilkMelon = EntityRegistry.findGlobalUniqueEntityId();
		if (DCsConfig.entityIdKinoko == 0) DCsConfig.entityIdKinoko = EntityRegistry.findGlobalUniqueEntityId();
		if (DCsConfig.entityIdCocktail2 == 0) DCsConfig.entityIdCocktail2 = EntityRegistry.findGlobalUniqueEntityId();
		
		EntityRegistry.registerModEntity(EntityMelonBomb.class, "compressedMelon", DCsConfig.entityIdMelon, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntitySilkyMelon.class, "compressedSilkyMelon", DCsConfig.entityIdSilkMelon, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityKinoko.class, "mushroomBox", DCsConfig.entityIdKinoko, this, 250, 5, true);
		
		EntityRegistry.registerModEntity(PlaceableIcecream.class, "PlaceableIceCream", DCsConfig.entityIdIce, this, 250, 5, true);
		EntityRegistry.registerModEntity(PlaceableSteak.class, "PlaceableSteak", DCsConfig.entityIdSteak, this, 250, 5, true);
		EntityRegistry.registerModEntity(PlaceableAlcoholCup.class, "PlaceableAlcoholCup", DCsConfig.entityIdAlcohol, this, 250, 5, true);
		EntityRegistry.registerModEntity(PlaceableCocktail.class, "PlaceableCocktail", DCsConfig.entityIdCocktail, this, 250, 5, true);
		EntityRegistry.registerModEntity(PlaceableCocktail2.class, "PlaceableCocktail2", DCsConfig.entityIdCocktail2, this, 250, 5, true);
		EntityRegistry.registerModEntity(PlaceableBowl.class, "PlaceableBowl", DCsConfig.entityIdBowl, this, 250, 5, true);
		EntityRegistry.registerModEntity(PlaceableBowlJP.class, "PlaceableBowlJP", DCsConfig.entityIdBowlJP, this, 250, 5, true);
		EntityRegistry.registerModEntity(PlaceableCup1.class, "PlaceableCup", DCsConfig.entityIdCup, this, 250, 5, true);
		EntityRegistry.registerModEntity(PlaceableCup2.class, "PlaceableCup2", DCsConfig.entityIdCup2, this, 250, 5, true);
		EntityRegistry.registerModEntity(PlaceableTart.class, "PlaceableTart", DCsConfig.entityIdTart, this, 250, 5, true);
		EntityRegistry.registerModEntity(PlaceableSandwich.class, "PlaceableSandwich", DCsConfig.entityIdSandwich, this, 250, 5, true);
		
		//Villagerの登録
		villager = new VillagerCafe();
		villagerYome = new VillagerYome();
		VillagerRegistry vill = VillagerRegistry.instance();
		
		vill.registerVillagerId(DCsConfig.villagerRecipeID);
		vill.registerVillageTradeHandler(DCsConfig.villagerRecipeID, villager);
		
		vill.registerVillagerId(DCsConfig.villagerRecipe2ID);
		vill.registerVillageTradeHandler(DCsConfig.villagerRecipe2ID, villagerYome);
		
		vill.registerVillageCreationHandler(new VillageCreateHandleCafe());
        net.minecraft.world.gen.structure.MapGenStructureIO.func_143031_a(ComponentVillageCafe.class, "ViCafe");
        
        vill.registerVillageCreationHandler(new VillageCreateHandleWarehouse());
        net.minecraft.world.gen.structure.MapGenStructureIO.func_143031_a(ComponentVillageWarehouse.class, "ViWarehouse");
		
		//Registering new Recipe
		//レシピや言語の登録は長くなるので専用クラスに任せる
		(new DCsRecipeRegister()).addRecipe();
		
		(new AddChestGen()).addChestItems();
		
		//Langファイル同梱のため、Lang登録クラスはコメントアウトしました。
		//(new DCsLangRegister()).registerLang();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		
		//地形生成イベントの登録
		//コンフィグでONのときだけ読み込まれる
		if (!DCsConfig.notGenTeaTree)
		{
			if (DCsConfig.teaTreeGenValue < 0) DCsConfig.teaTreeGenValue = 1;
			if (DCsConfig.teaTreeGenValue > 20) DCsConfig.teaTreeGenValue = 20;
			
			GameRegistry.registerWorldGenerator(new WorldgenTeaTree(), 1);
		}
		
		if (!DCsConfig.disableClam)
		{
			GameRegistry.registerWorldGenerator(new WorldgenClam(), 2);
		}
		
		//Registering new event
		//ログイン時イベント
		MinecraftForge.EVENT_BUS.register(this);
		//ポーション効果の内容をLivingEventで作ったのでそれの読み込み
		MinecraftForge.EVENT_BUS.register(new DCsLivingEvent());
		MinecraftForge.EVENT_BUS.register(new DCsHurtEvent());
		//螺鈿チャームの効果
		MinecraftForge.EVENT_BUS.register(new EntityMoreDropEvent());
		//骨粉効果
		MinecraftForge.EVENT_BUS.register(new DCsBonemealEvent());
		//モノクルの鉱石辞書名確認機能
		MinecraftForge.EVENT_BUS.register(new ShowOreNameEvent());
		//バケツイベント
		MinecraftForge.EVENT_BUS.register(new BucketFillEvent());
		//クラフトで耐久が減るアイテムの登録
		FMLCommonHandler.instance().bus().register(new CraftingEvent());
		
		//ディスペンサー動作への登録
		DispenserEvent.instance.init();
		
		//Registering new Render
		//新しいレンダーIDの登録もプロキシクラス内でやる
		//サーバ側で誤ってクライアント専用のクラス（レンダー関係）を読み込ませないため
		this.modelTeaMaker = proxy.getRenderID();
		this.modelCup = proxy.getRenderID();
		this.modelPan = proxy.getRenderID();
		this.modelTeaTree = proxy.getRenderID();
		this.modelFilledCup = proxy.getRenderID();
		this.modelBowl = proxy.getRenderID();
		this.modelRack = proxy.getRenderID();
		this.modelCLamp = proxy.getRenderID();
		this.modelBasket = proxy.getRenderID();
		this.modelFoodPlate = proxy.getRenderID();
		this.modelTeppann = proxy.getRenderID();
		this.modelBowlJP = proxy.getRenderID();
		this.modelCupSummer = proxy.getRenderID();
		this.modelChopsticks = proxy.getRenderID();
		this.modelEggBasket = proxy.getRenderID();
		this.modelKinoko = proxy.getRenderID();
		this.modelMelonBomb = proxy.getRenderID();
		this.modelChocoPan = proxy.getRenderID();
		this.modelMakerNext = proxy.getRenderID();
		this.modelAutoMaker = proxy.getRenderID();
		this.modelWipe = proxy.getRenderID();
		this.modelIceMaker = proxy.getRenderID();
		this.modelIceCream = proxy.getRenderID();
		this.modelCocktail = proxy.getRenderID();
		this.modelDial = proxy.getRenderID();
		this.modelLargeBottle = proxy.getRenderID();
		this.modelCordial = proxy.getRenderID();
		this.modelCassisTree = proxy.getRenderID();
		this.modelAlcoholCup = proxy.getRenderID();
		this.modelProsessor = proxy.getRenderID();
		this.modelEvaporator = proxy.getRenderID();
		this.modelJawCrusher = proxy.getRenderID();
		this.modelCPanel = proxy.getRenderID();
		this.modelIncenseBase = proxy.getRenderID();
		this.modelCanister = proxy.getRenderID();
		this.modelYuzuBat = proxy.getRenderID();
		this.modelGelBat = proxy.getRenderID();
		this.modelBatBox = proxy.getRenderID();
		this.modelFlowerPot = proxy.getRenderID();
		this.modelYuzuFence = proxy.getRenderID();
		proxy.registerRenderers();
	    
	    //ティーメーカーのレシピ数の無限化のため、専用のレシピ登録クラスを用意した
	    (new RegisterMakerRecipe()).registerTea();
	    AMTLogger.trace("Registered new tea maker recipe");
	    
	    //アイスメーカーのレシピ登録
	    (new RegisterMakerRecipe()).registerIce();
	    AMTLogger.trace("Registered new ice maker recipe");
	    
	    //鍋のレシピ登録
	    (new RegisterMakerRecipe()).registerPan();
	    AMTLogger.trace("Registered new pan recipe");
	    
	    //チャージアイテム
	    (new RegisterMakerRecipe()).registerChargeItem();
	    AMTLogger.trace("Registered new ice maker recipe");
	    
	    //プロセッサーのレシピ登録
	    (new RegisterMakerRecipe()).registerProsessor();
	    AMTLogger.trace("Registered new prosessor recipe");

	    //エバポレーターのレシピ登録
	    (new RegisterMakerRecipe()).registerEvaporator();
	    AMTLogger.trace("Registered new evaporator recipe");
	    
	    //樽での醸造レシピ
	    (new RegisterMakerRecipe()).registerBrewing();
	    AMTLogger.trace("Registered new brewing recipe");
	}
	
	@EventHandler
	public void receiveIMC(IMCEvent event)
	{
		ReceivingIMCEvent.receiveIMC(event);
	}
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event) {
		
		if (debugMode)
		{
			(new LoadModHandler()).loadAppleMilk();
		}
		
	    if (Loader.isModLoaded("IC2"))
	    {
	    	AMTLogger.loadingModInfo("IC2");
	    	try
	        {
	          this.SuccessLoadIC2 = true;
	          (new LoadIC2Plugin()).load();
	          AMTLogger.loadedModInfo("IC2");
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("IC2");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("Forestry"))
	    {
	    	AMTLogger.loadingModInfo("Forestry");
	    	try
	        {
	          this.SuccessLoadFFM = true;
	          (new LoadForestryPlugin()).load();
	          AMTLogger.loadedModInfo("Forestry");
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("Forestry");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("BuildCraft|Energy"))
	    {
	    	AMTLogger.loadingModInfo("BuildCraft|Energy");
	    	try
	        {
	          this.SuccessLoadBC = true;
	          (new LoadBCPlugin()).loadEnergy();
	          AMTLogger.loadedModInfo("BuildCraft|Energy");
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("BuildCraft|Energy");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("BambooMod"))
	    {
	    	AMTLogger.loadingModInfo("BambooMod");
	    	try
	        {
	          this.SuccessLoadBamboo = true;
	          (new LoadBambooPlugin()).load();
	          (new LoadBambooPlugin()).loadBambooItems();
	          AMTLogger.loadedModInfo("BambooMod");
	          
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("BambooMod");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("TofuCraft"))
	    {
	    	AMTLogger.loadingModInfo("TofuCraft");
	    	try
	        {
	          this.SuccessLoadTofu = true;
	          (new LoadTofuPlugin()).load();
	          AMTLogger.loadedModInfo("TofuCraft");
	          
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("TofuCraft");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("Thaumcraft"))
	    {
	    	AMTLogger.loadingModInfo("Thaumcraft");
	    	try
	        {
	          this.SuccessLoadThaumcraft = true;
	          (new LoadThaumcraftPlugin()).load();
	          AMTLogger.loadedModInfo("Thaumcraft");
	          
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("Thaumcraft");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("BiomesOPlenty"))
	    {
	    	AMTLogger.loadingModInfo("BiomesOPlenty");
	    	try
	        {
	          this.SuccessLoadBoP = true;
	          (new LoadBoPPlugin()).load();
	          AMTLogger.loadedModInfo("BipmesOPlenty");
	          
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("BiomesOPlenty");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("AndanteMod_Gummi"))
	    {
	    	AMTLogger.loadingModInfo("AndanteMod_Gummi");
	    	try
	        {
	          this.SuccessLoadGummi = true;
	          (new LoadModHandler()).loadGummi();
	          AMTLogger.loadedModInfo("AndanteMod_Gummi");
	          
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("AndanteMod_Gummi");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("AndanteMod_ExBucket"))
	    {
	    	AMTLogger.loadingModInfo("AndanteMod_ExBucket");
	    	try
	        {
	          this.SuccessLoadExBucket = true;
	          (new LoadExBucketPlugin()).load();
	          AMTLogger.loadedModInfo("AndanteMod_ExBucket");
	          
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("AndanteMod_ExBucket");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("mceconomy2"))
	    {
	    	AMTLogger.loadingModInfo("MCEconomy2");
	    	try
	        {
	          this.SuccessLoadEconomy = true;
	          (new MCEconomyPlugin()).registerSellable();
	          AMTLogger.loadedModInfo("MCEconomy2");
	          
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("MCEconomy");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("SextiarySector"))
	    {
	    	AMTLogger.loadingModInfo("SextiarySector");
	    	try
	        {
	          this.SuccessLoadSSector = true;
	          (new LoadSSectorPlugin()).load();
	          AMTLogger.loadedModInfo("SextiarySector");
	          
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("SextiarySector");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("Growthcraft|Rice"))
	    {
	    	AMTLogger.loadingModInfo("Growthcraft|Rice");
	    	try
	        {
	          this.SuccessLoadGrowth[0] = true;
	          (new LoadModHandler()).loadGrowthRice();
	          AMTLogger.loadedModInfo("Growthcraft|Rice");
	          
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("Growthcraft|Rice");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("Growthcraft|Hops"))
	    {
	    	AMTLogger.loadingModInfo("Growthcraft|Hops");
	    	try
	        {
	          this.SuccessLoadGrowth[1] = true;
	          (new LoadModHandler()).loadGrowthHops();
	          AMTLogger.loadedModInfo("Growthcraft|Hops");
	          
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("Growthcraft|Hops");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("Growthcraft|Grapes"))
	    {
	    	AMTLogger.loadingModInfo("Growthcraft|Grapes");
	    	try
	        {
	          this.SuccessLoadGrowth[2] = true;
	          (new LoadModHandler()).loadGrowthGrape();
	          AMTLogger.loadedModInfo("Growthcraft|Grapes");
	          
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("Growthcraft|Grapes");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("mod_ecru_MapleTree"))
	    {
	    	AMTLogger.loadingModInfo("mod_ecru_MapleTree");
	    	try
	        {
	          this.SuccessLoadMapleTree = true;
	          (new LoadModHandler()).loadMaple();
	          AMTLogger.loadedModInfo("mod_ecru_MapleTree");
	          
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("mod_ecru_MapleTree");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("ExtraTrees"))
	    {
	    	AMTLogger.loadingModInfo("ExtraTrees");
	    	try
	        {
	          this.SuccessLoadExtraTrees = true;
	          (new LoadModHandler()).loadExtraTrees();
	          AMTLogger.loadedModInfo("ExtraTrees");
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("ExtraTrees");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("Railcraft"))
	    {
	    	AMTLogger.loadingModInfo("Railcraft");
	    	try
	        {
	          this.SuccessLoadRC = true;
	          (new LoadRailCraftPlugin()).load();
	          AMTLogger.loadedModInfo("Railcraft");
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("Railcraft");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("kegare.sugiforest"))
	    {
	    	AMTLogger.loadingModInfo("kegare.sugiforest");
	    	try
	        {
	          this.SuccessLoadSugi = true;
	          (new LoadModHandler()).loadSugi();
	          AMTLogger.loadedModInfo("kegare.sugiforest");
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("kegare.sugiforest");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("DartCraft"))
	    {
	    	AMTLogger.loadingModInfo("DartCraft");
	    	try
	        {
	          this.SuccessLoadDart = true;
	          (new LoadModHandler()).loadForce();
	          AMTLogger.loadedModInfo("DartCraft");
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("DartCraft");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("Wa"))
	    {
	    	AMTLogger.loadingModInfo("Wa");
	    	try
	        {
	          this.SuccessLoadWa = true;
	          (new LoadModHandler()).loadWa();
	          AMTLogger.loadedModInfo("Wa");
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("Wa");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("EnchantChanger"))
	    {
	    	AMTLogger.loadingModInfo("EnchantChanger");
	    	try
	        {
	          (new LoadModHandler()).loadEnchantChanger();
	          AMTLogger.loadedModInfo("EnchantChanger");
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("EnchantChanger");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    if (Loader.isModLoaded("AppleCore"))
	    {
	    	AMTLogger.loadingModInfo("AppleCore");
	    	try
	        {
	    	  //ここでは、専用のフラグを切り替えるだけ。
	          this.SuccessLoadACore = true;
	          AMTLogger.loadedModInfo("AppleCore");
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("AppleCore");
	          e.printStackTrace(System.err);
	        }
	    }
	    
	    //Checking another mods
	    //他のMODのブロック・アイテム登録クラスに先行しないよう、postInitメソッドでロードする
	    //当MODで勝手に追加する鉱石辞書も含めるように、読み込む位置を他MODのロード処理より後にした
	    (new LoadOreDicHandler()).load();
	    
	    //インスタントティーレシピ
	    //他MODの水入り容器をひと通り取得した後に行うので、最後のほうで呼ぶ
	    (new DCsRecipeRegister()).addInstantTea();
	    
	    //その他の他MODアイテム使用レシピ登録
	    (new DCsRecipeRegister()).addKelpRecipe();
	    (new DCsRecipeRegister()).addMetalRecipe();
	    (new RegisterMakerRecipe()).addKelpRecipe();
	    
	    //レシピ閲覧系MODの連携要素
	    (new RegisteredRecipeGet()).setRecipeList();
	    
	    //NEIのみクライアントサイドで読み込む
	    proxy.loadNEI();
	    
	    //CraftGuideへのレシピ登録
	    if (Loader.isModLoaded("craftguide"))
	    {
	    	AMTLogger.loadingModInfo("craftguide");
	    	try
	        {
	          this.SuccessLoadCGuide = true;
	          (new LoadCraftGuidePlugin()).load();;
	          AMTLogger.loadedModInfo("craftguide");
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("craftguide");
	          e.printStackTrace(System.err);
	        }
	    }
    }
	
//	@EventHandler
//	public void startServer(FMLServerStartedEvent event)
//	{
//		this.proxy.serverStart();
//	}
//	
//	@SubscribeEvent
//    /*マルチでは各プレイヤーがログイン時にサーバの情報を読まなければならない*/
//    public void onEntityJoinWorld(EntityJoinWorldEvent event)  {
//        if (event.entity instanceof EntityPlayer) {
//        	EntityPlayer player = (EntityPlayer) event.entity;
//        	this.proxy.playerLogin(player);
//        }
//	}
	
}
