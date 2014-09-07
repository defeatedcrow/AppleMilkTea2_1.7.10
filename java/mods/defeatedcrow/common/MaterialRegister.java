package mods.defeatedcrow.common;

import mods.defeatedcrow.common.block.*;
import mods.defeatedcrow.common.block.appliance.*;
import mods.defeatedcrow.common.block.container.*;
import mods.defeatedcrow.common.block.edible.*;
import mods.defeatedcrow.common.fluid.*;
import mods.defeatedcrow.common.block.plants.*;
import mods.defeatedcrow.common.item.*;
import mods.defeatedcrow.common.item.appliance.*;
import mods.defeatedcrow.common.item.edible.*;
import mods.defeatedcrow.common.item.magic.*;
import mods.defeatedcrow.event.BucketFillEvent;
import mods.defeatedcrow.potion.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class MaterialRegister {
	
	public static MaterialRegister instance = new MaterialRegister();
	
	private static Fluid registerVegiOil;
	private static Fluid registerCamOil;
	
	private MaterialRegister(){}
	
	public void load()
	{
		this.addTools();
		this.addPlants();
		this.addContainers();
		this.addFoods();
		this.addDecorations();
		this.addMaterials();
		this.addMachines();
		
		//登録順をちょっと調整
		//ツール
		GameRegistry.registerItem(DCsAppleMilk.DCgrater,"defeatedcrow.grater");
		GameRegistry.registerItem(DCsAppleMilk.firestarter,"defeatedcrow.firestarter");
		GameRegistry.registerItem(DCsAppleMilk.chalcedonyHammer,"defeatedcrow.chalcedonyStoneCutter");
		GameRegistry.registerItem(DCsAppleMilk.chalcedonyKnife,"defeatedcrow.chalcedonyKnife");
		GameRegistry.registerItem(DCsAppleMilk.monocle, "defeatedcrow.monocle");
		GameRegistry.registerItem(DCsAppleMilk.onixSword, "defeatedcrow.onixSword");
		GameRegistry.registerItem(DCsAppleMilk.chopsticks,"defeatedcrow.chopsticks");
		
		//たべもの
		GameRegistry.registerItem(DCsAppleMilk.bakedApple, "defeatedcrow.bakedApple");
		GameRegistry.registerItem(DCsAppleMilk.appleTart, "defeatedcrow.appleTart");
		GameRegistry.registerItem(DCsAppleMilk.appleSandwich, "defeatedcrow.appleSandwich");
		GameRegistry.registerItem(DCsAppleMilk.toffyApple, "defeatedcrow.toffyApple");
		GameRegistry.registerItem(DCsAppleMilk.icyToffyApple, "defeatedcrow.icyToffyApple");
		GameRegistry.registerItem(DCsAppleMilk.EXItems, "defeatedcrow.condensedMilk");
		GameRegistry.registerItem(DCsAppleMilk.condensedMIlk, "defeatedcrow.milkCandy");
		GameRegistry.registerItem(DCsAppleMilk.chocolateFruits,"defeatedcrow.chocolateFruits");
		//植物
		GameRegistry.registerItem(DCsAppleMilk.leafTea, "defeatedcrow.leafTea");
		GameRegistry.registerItem(DCsAppleMilk.itemMintSeed,"defeatedcrow.seedMint");
		GameRegistry.registerItem(DCsAppleMilk.clam,"defeatedcrow.clam");
		//飲み物素材
		GameRegistry.registerItem(DCsAppleMilk.foodTea,"defeatedcrow.foodTea");
		GameRegistry.registerItem(DCsAppleMilk.gratedApple,"defeatedcrow.gratedApple");
		GameRegistry.registerItem(DCsAppleMilk.mincedFoods,"defeatedcrow.mincedFoods");
		GameRegistry.registerItem(DCsAppleMilk.yeast,"defeatedcrow.yeast");
		GameRegistry.registerItem(DCsAppleMilk.emptyWallMug,"defeatedcrow.emptyWallMug");
		GameRegistry.registerItem(DCsAppleMilk.wallMug,"defeatedcrow.wallMug");
		GameRegistry.registerItem(DCsAppleMilk.itemLargeBottle,"defeatedcrow.itemBottle");
		GameRegistry.registerItem(DCsAppleMilk.itemCordial,"defeatedcrow.itemCordial");
		//装置関係
		GameRegistry.registerItem(DCsAppleMilk.batteryItem, "defeatedcrow.battery");
		GameRegistry.registerItem(DCsAppleMilk.slotPanel,"defeatedcrow.slotPanel");
		GameRegistry.registerItem(DCsAppleMilk.dustWood,"defeatedcrow.dustWood");
		GameRegistry.registerItem(DCsAppleMilk.essentialOil,"defeatedcrow.essentialOil");
		GameRegistry.registerItem(DCsAppleMilk.insenceRose,"defeatedcrow.insence_rose");
		//そのほか
		GameRegistry.registerItem(DCsAppleMilk.inkStick, "defeatedcrow.inkStick");
		GameRegistry.registerItem(DCsAppleMilk.icyCrystal,"defeatedcrow.icyCrystal");
		GameRegistry.registerItem(DCsAppleMilk.princessClam,"defeatedcrow.princessClam");
		
		//基本ツール
		GameRegistry.registerBlock(DCsAppleMilk.teaMakerNext, "defeatedcrow.teaMakerNext");
		GameRegistry.registerBlock(DCsAppleMilk.teaMakerBlack, "defeatedcrow.teaMakerBlack");
		GameRegistry.registerBlock(DCsAppleMilk.emptyCup, "defeatedcrow.emptyCup");
		GameRegistry.registerBlock(DCsAppleMilk.emptyPan, "defeatedcrow.emptyPan");
		GameRegistry.registerBlock(DCsAppleMilk.emptyPanGaiden, ItemPanG.class, "defeatedcrow.emptyPanG");
		GameRegistry.registerBlock(DCsAppleMilk.iceMaker, "defeatedcrow.iceMaker");
		GameRegistry.registerBlock(DCsAppleMilk.teppann, ItemTeppann.class, "defeatedcrow.teppann");
		GameRegistry.registerBlock(DCsAppleMilk.prosessor, ItemMachineBlock.class, "defeatedcrow.prosessor");
		GameRegistry.registerBlock(DCsAppleMilk.advProsessor, ItemMachineBlock.class, "defeatedcrow.advProsessor");
		GameRegistry.registerBlock(DCsAppleMilk.evaporator, ItemMachineBlock.class, "defeatedcrow.evaporator");
		//圧縮系
		GameRegistry.registerBlock(DCsAppleMilk.woodBox, ItemWoodBox.class, "defeatedcrow.WoodBox");
		GameRegistry.registerBlock(DCsAppleMilk.appleBox, "defeatedcrow.AppleBox");
		GameRegistry.registerBlock(DCsAppleMilk.vegiBag, ItemVegiBag.class, "defeatedcrow.VegiBag");
		GameRegistry.registerBlock(DCsAppleMilk.cardboard, ItemCardboard.class, "defeatedcrow.cardboardBox");
		GameRegistry.registerBlock(DCsAppleMilk.charcoalBox, "defeatedcrow.Charcoalcontainer");
		GameRegistry.registerBlock(DCsAppleMilk.gunpowderContainer, ItemGunpowderContainer.class, "defeatedcrow.GunpowderContainer");
		GameRegistry.registerBlock(DCsAppleMilk.mobBlock, ItemMobDropBox.class, "defeatedcrow.mobDropBox");
		//自然
		GameRegistry.registerBlock(DCsAppleMilk.saplingTea, ItemTeaSapling.class, "defeatedcrow.saplingTea");
		GameRegistry.registerBlock(DCsAppleMilk.teaTree, ItemTeaTree.class, "defeatedcrow.teaTree");
		GameRegistry.registerBlock(DCsAppleMilk.cassisTree, ItemCassisTree.class, "defeatedcrow.cassisTree");
		GameRegistry.registerBlock(DCsAppleMilk.saplingYuzu, "defeatedcrow.saplingYuzu");
		GameRegistry.registerBlock(DCsAppleMilk.logYuzu, "defeatedcrow.logYuzu");
		GameRegistry.registerBlock(DCsAppleMilk.leavesYuzu, ItemYuzuLeaves.class, "defeatedcrow.leavesYuzu");
		GameRegistry.registerBlock(DCsAppleMilk.clamSand, ItemClamSand.class, "defeatedcrow.clamSand");
		GameRegistry.registerBlock(DCsAppleMilk.cropMint, "defeatedcrow.cropMint");
		GameRegistry.registerBlock(DCsAppleMilk.emptyBottle, "defeatedcrow.emptyBottle");
		GameRegistry.registerBlock(DCsAppleMilk.largeBottle, "defeatedcrow.largeBottle");
		GameRegistry.registerBlock(DCsAppleMilk.cordial, ItemBlockCordial.class, "defeatedcrow.blockCordial");
		GameRegistry.registerFuelHandler(DCsAppleMilk.charcoalBox);
		//カルセドニー
		GameRegistry.registerBlock(DCsAppleMilk.flintBlock, "defeatedcrow.flintBlock");
		GameRegistry.registerBlock(DCsAppleMilk.chalcedony, ItemChalcedony.class, "defeatedcrow.chalcedony");
		GameRegistry.registerBlock(DCsAppleMilk.cLamp, ItemChalcedonyLamp.class, "defeatedcrow.chalcedonyLamp");
		GameRegistry.registerBlock(DCsAppleMilk.chalcenonyPanel, "defeatedcrow.chalcedonyPanel");
		GameRegistry.registerBlock(DCsAppleMilk.insenceBase, "defeatedcrow.insenceBase");
		//インテリア
		GameRegistry.registerBlock(DCsAppleMilk.Basket, ItemBreadBasket.class, "defeatedcrow.basket");
		GameRegistry.registerBlock(DCsAppleMilk.bowlRack, ItemBowlRack.class, "defeatedcrow.bowlRack");
		GameRegistry.registerBlock(DCsAppleMilk.chopsticksBox, ItemChopsticksBox.class,  "defeatedcrow.chopsticksBox");
		GameRegistry.registerBlock(DCsAppleMilk.eggBasket, ItemEggBasket.class,  "defeatedcrow.eggBasket");
		GameRegistry.registerBlock(DCsAppleMilk.mushroomBox, ItemMushBox.class,  "defeatedcrow.mushroomBox");
		GameRegistry.registerBlock(DCsAppleMilk.melonBomb, ItemMelonBomb.class,  "defeatedcrow.melonBomb");
		GameRegistry.registerBlock(DCsAppleMilk.silkyMelon, ItemSilkyMelon.class,  "defeatedcrow.melonSilky");
		GameRegistry.registerBlock(DCsAppleMilk.wipeBox, ItemWipeBox.class, "defeatedcrow.wipeBox");
		GameRegistry.registerBlock(DCsAppleMilk.wipeBox2, ItemWipeBox2.class, "defeatedcrow.wipeBox2");
		GameRegistry.registerBlock(DCsAppleMilk.rotaryDial, "defeatedcrow.rotaryDial");
		//食べ物
		GameRegistry.registerBlock(DCsAppleMilk.filledPan, ItemFilledPan.class, "defeatedcrow.SoupPan");
		GameRegistry.registerBlock(DCsAppleMilk.filledPan2, ItemFilledPan2.class, "defeatedcrow.soupPan2");
		GameRegistry.registerBlock(DCsAppleMilk.filledChocoPan, "defeatedcrow.soupPan3");
		GameRegistry.registerBlock(DCsAppleMilk.bowlBlock, EntityItemBowl.class, "defeatedcrow.bowlBlock");
		GameRegistry.registerBlock(DCsAppleMilk.bowlJP, EntityItemBowlJP.class, "defeatedcrow.bowlJP");
		GameRegistry.registerBlock(DCsAppleMilk.foodPlate, EntityItemSteak.class, "defeatedcrow.foodPlate");
		GameRegistry.registerBlock(DCsAppleMilk.chocoBlock, ItemChocoGift.class,  "defeatedcrow.chocolateGift");
		GameRegistry.registerBlock(DCsAppleMilk.teacupBlock, EntityItemTeaCup.class, "defeatedcrow.filledCup");
		GameRegistry.registerBlock(DCsAppleMilk.teaCup2, EntityItemTeaCup2.class, "defeatedcrow.filledCup2");
		GameRegistry.registerBlock(DCsAppleMilk.blockIcecream, EntityItemIceCream.class, "defeatedcrow.iceCreamBlock");
		GameRegistry.registerBlock(DCsAppleMilk.cocktail, EntityItemCocktail.class, "defeatedcrow.cocktail");
		GameRegistry.registerBlock(DCsAppleMilk.alcoholCup, EntityItemAlcoholCup.class, "defeatedcrow.alcoholCup");
	}
	
	public void addPotion()
	{
		//ポーション効果の追加
		//コンフィグでIDが~127の範囲内の時のみ追加する。
		if (Potion.potionTypes[DCsConfig.potionIDImmunity] == null && DCsConfig.potionIDImmunity < 128){
			DCsAppleMilk.Immunization = (new PotionImmunity(DCsConfig.potionIDImmunity, false, 7889559)).
					setPotionName("DCs.potion.immunization");
		}
		else {
            throw new IllegalArgumentException("Failed to register new Potion : Immunization of DCsAppleMilk");
        }
		if (Potion.potionTypes[DCsConfig.potionIDPrvExplode] == null && DCsConfig.potionIDPrvExplode < 128){
			DCsAppleMilk.prvExplode = (new PotionProtectionEX(DCsConfig.potionIDPrvExplode, false, 3237665,
					false, true, false, DamageSource.anvil)).
					setPotionName("DCs.potion.protectionExplode");
		}
		else {
            throw new IllegalArgumentException("Failed to register new Potion : Explode Protection of DCsAppleMilk");
        }
		if (Potion.potionTypes[DCsConfig.potionIDPrvProjectile] == null && DCsConfig.potionIDPrvProjectile < 128){
			DCsAppleMilk.prvProjectile = (new PotionProtectionEX(DCsConfig.potionIDPrvProjectile, false, 1151526,
					false, false, true, DamageSource.magic)).
					setPotionName("DCs.potion.protectionProjectile");
		}
		else {
            throw new IllegalArgumentException("Failed to register new Potion : Projectile Protection of DCsAppleMIlk");
        }
		if (Potion.potionTypes[DCsConfig.potionIDReflex] == null && DCsConfig.potionIDReflex < 128){
			DCsAppleMilk.reflex = (new PotionReflex(DCsConfig.potionIDReflex, false, 999999, false)).
					setPotionName("DCs.potion.reflex");
		}
		else {
            throw new IllegalArgumentException("Failed to register new Potion : Reflex of DCsAppleMilk");
        }
		if (Potion.potionTypes[DCsConfig.potionIDAbsEXP] == null && DCsConfig.potionIDAbsEXP < 128){
			DCsAppleMilk.absEXP = (new PotionReflex(DCsConfig.potionIDAbsEXP, false, 5599557, false)).
					setPotionName("DCs.potion.absorptionEXP");
		}
		else {
            throw new IllegalArgumentException("Failed to register new Potion : EXP Absorption of DCsAppleMilk");
        }
		if (Potion.potionTypes[DCsConfig.potionIDAbsHeal] == null && DCsConfig.potionIDAbsHeal < 128){
			DCsAppleMilk.absHeal = (new PotionReflex(DCsConfig.potionIDAbsHeal, false, 9933221, false)).
					setPotionName("DCs.potion.absorptionHeal");
		}
		else {
            throw new IllegalArgumentException("Failed to register new Potion : Damage Absorption of DCsAppleMilk");
        }
		if (Potion.potionTypes[DCsConfig.potionIDSuffocation] == null && DCsConfig.potionIDSuffocation < 128){
			DCsAppleMilk.suffocation = (new PotionSuffocation(DCsConfig.potionIDSuffocation, true, 9933221)).
					setPotionName("DCs.potion.suffocation");
		}
		else {
            throw new IllegalArgumentException("Failed to register new Potion : Damage Absorption of DCsAppleMilk");
        }
		if (Potion.potionTypes[DCsConfig.potionIDPrvSuffocation] == null && DCsConfig.potionIDPrvSuffocation < 128){
			DCsAppleMilk.prvSuffocation = (PotionProtectionEX) (new PotionProtectionEX(DCsConfig.potionIDPrvSuffocation, true, 9933221, 
					false, false, false, DamageSource.inWall)).setProtectSuffocation()
					.setPotionName("DCs.potion.protectionSuffocation");
		}
		else {
            throw new IllegalArgumentException("Failed to register new Potion : Damage Absorption of DCsAppleMilk");
        }
	}
	
	public void addFluid()
	{
		//vegi
		registerVegiOil = new Fluid("vegitable_oil").setDensity(800).setViscosity(1500);
		FluidRegistry.registerFluid(registerVegiOil);
		DCsAppleMilk.vegitableOil = FluidRegistry.getFluid("vegitable_oil");
		
		DCsAppleMilk.blockVegitableOil = new BlockOilFluid(DCsAppleMilk.vegitableOil, Material.water)
		.setBlockName("defeatedcrow.blockVegiOil");
		GameRegistry.registerBlock(DCsAppleMilk.blockVegitableOil, "defeatedcrow.blockVegiOil");
		DCsAppleMilk.vegitableOil.setBlock(DCsAppleMilk.blockVegitableOil);
		
		if (DCsAppleMilk.blockVegitableOil != null)
		{
			DCsAppleMilk.bucketVegiOil = new ItemBucketVegiOil(DCsAppleMilk.blockVegitableOil)
			.setUnlocalizedName("defeatedcrow.bucketVegiOil")
			.setContainerItem(Items.bucket)
			.setCreativeTab(DCsAppleMilk.applemilk);
			GameRegistry.registerItem(DCsAppleMilk.bucketVegiOil, "defeatedcrow.bucketVegiOil");
			FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("vegitable_oil", FluidContainerRegistry.BUCKET_VOLUME)
					,new ItemStack(DCsAppleMilk.bucketVegiOil), new ItemStack(Items.bucket));
			
			DCsAppleMilk.bottleVegiOil = new ItemBottleVegiOil(DCsAppleMilk.blockVegitableOil)
			.setUnlocalizedName("defeatedcrow.bottleVegiOil")
			.setContainerItem(Item.getItemFromBlock(DCsAppleMilk.emptyBottle))
			.setCreativeTab(DCsAppleMilk.applemilk);
			GameRegistry.registerItem(DCsAppleMilk.bottleVegiOil, "defeatedcrow.bottleVegiOil");
			FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("vegitable_oil", 200)
					,new ItemStack(DCsAppleMilk.bottleVegiOil), new ItemStack(Item.getItemFromBlock(DCsAppleMilk.emptyBottle)));
		}
		
		//camellia
		registerCamOil = new Fluid("camellia_oil").setDensity(800).setViscosity(1500);
		FluidRegistry.registerFluid(registerCamOil);
		DCsAppleMilk.camelliaOil = FluidRegistry.getFluid("camellia_oil");
		
		DCsAppleMilk.blockCamelliaOil = new BlockCamOilFluid(DCsAppleMilk.camelliaOil, Material.water)
		.setBlockName("defeatedcrow.blockCamOil");
		GameRegistry.registerBlock(DCsAppleMilk.blockCamelliaOil, "defeatedcrow.blockCamOil");
		DCsAppleMilk.camelliaOil.setBlock(DCsAppleMilk.blockCamelliaOil);
		
		if (DCsAppleMilk.blockCamelliaOil != null)
		{
			DCsAppleMilk.bucketCamOil = new ItemBucketCamOil(DCsAppleMilk.blockCamelliaOil)
			.setUnlocalizedName("defeatedcrow.bucketCamOil")
			.setContainerItem(Items.bucket)
			.setCreativeTab(DCsAppleMilk.applemilk);
			GameRegistry.registerItem(DCsAppleMilk.bucketCamOil, "defeatedcrow.bucketCamOil");
			FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("camellia_oil", FluidContainerRegistry.BUCKET_VOLUME)
					,new ItemStack(DCsAppleMilk.bucketCamOil), new ItemStack(Items.bucket));
			
			DCsAppleMilk.bottleCamOil = new ItemBottleCamOil(DCsAppleMilk.blockCamelliaOil)
			.setUnlocalizedName("defeatedcrow.bottleCamOil")
			.setContainerItem(Item.getItemFromBlock(DCsAppleMilk.emptyBottle))
			.setCreativeTab(DCsAppleMilk.applemilk);
			GameRegistry.registerItem(DCsAppleMilk.bottleCamOil, "defeatedcrow.bottleCamOil");
			FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("camellia_oil", 200)
					,new ItemStack(DCsAppleMilk.bottleCamOil), new ItemStack(Item.getItemFromBlock(DCsAppleMilk.emptyBottle)));
		}
		
		DCsAppleMilk.proxy.registerFluidTex();
		
		(new BucketFillEvent()).register();
	}

	static void addTools()
	{
		DCsAppleMilk.DCgrater  = (ItemGrater)(new ItemGrater()).
				setUnlocalizedName("defeatedcrow.grater").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.chopsticks = (new ItemChopsticks()).
				setUnlocalizedName("defeatedcrow.chopsticks").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.chalcedonyKnife = (new ItemChalcedonyKnife( DCsAppleMilk.enumToolMaterialChalcedony)).
				setUnlocalizedName("defeatedcrow.chalcedonyKnife").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.firestarter = (new ItemFireStarter()).
				setUnlocalizedName("defeatedcrow.firestarter").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.chalcedonyHammer = (new ItemChalcedonyHammer(DCsAppleMilk.enumToolMaterialChalcedony)).
				setUnlocalizedName("defeatedcrow.chalcedonyStoneCutter").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.monocle = (new ItemChalcedonyMonocle(ItemArmor.ArmorMaterial.IRON, DCsAppleMilk.proxy.addArmor("monocle"), 0))
				.setUnlocalizedName("defeatedcrow.monocle")
				.setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.onixSword = (new ItemOnixSword())
				.setUnlocalizedName("defeatedcrow.onixSword")
				.setCreativeTab(DCsAppleMilk.applemilk);
	}
	
	static void addFoods()
	{
		DCsAppleMilk.bakedApple = (new ItemBakedApple(7, 7, false)).
				setUnlocalizedName("defeatedcrow.bakedApple").
				setCreativeTab(DCsAppleMilk.applemilkFood);
		
		DCsAppleMilk.appleTart = (new ItemAppleTart()).
				setUnlocalizedName("defeatedcrow.appleTart").
				setCreativeTab(DCsAppleMilk.applemilkFood);
		
		DCsAppleMilk.appleSandwich  = (new ItemAppleSandwich()).
				setUnlocalizedName("defeatedcrow.appleSandwich").
				setCreativeTab(DCsAppleMilk.applemilkFood);
		
		DCsAppleMilk.toffyApple = (new ItemToffyApple(4, 4, false)).
				setUnlocalizedName("defeatedcrow.toffyApple").
				setCreativeTab(DCsAppleMilk.applemilkFood);
		
		DCsAppleMilk.icyToffyApple  = (new ItemIcyToffyApple(4, 4, false)).
				setUnlocalizedName("defeatedcrow.icyToffyApple").
				setCreativeTab(DCsAppleMilk.applemilkFood);
		
		DCsAppleMilk.chocolateFruits = (new ItemChocoFruits(6, 6, false)).
				setUnlocalizedName("defeatedcrow.fruitsChocolate").
				setCreativeTab(DCsAppleMilk.applemilkFood);
		
		DCsAppleMilk.foodTea  = (new ItemFoodTea()).
				setUnlocalizedName("defeatedcrow.foodTea").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.gratedApple  = (new ItemGratedApple(2, 2, false)).
				setUnlocalizedName("defeatedcrow.gratedApple").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.mincedFoods  = (new ItemMincedFoods()).
				setUnlocalizedName("defeatedcrow.mincedItem").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.yeast  = (new ItemYeast()).
				setUnlocalizedName("defeatedcrow.yeast").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.EXItems  = (new ItemEXItem()).
				setUnlocalizedName("defeatedcrow.extraItems").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.condensedMIlk  = (new ItemCondensedMilk(1, 1, false)).
				setUnlocalizedName("defeatedcrow.condensedMilk").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.filledPan = (new BlockFilledPan()).
				setBlockName("defeatedcrow.soupPan");
		
		DCsAppleMilk.filledPan2 = (new BlockFilledPan2()).
				setBlockName("defeatedcrow.soupPan_2");
		
		DCsAppleMilk.filledChocoPan = (new BlockFilledChocoPan()).
				setBlockName("defeatedcrow.soupPan_3");
		
		DCsAppleMilk.bowlBlock = (new BlockBowl()).
				setBlockName("defeatedcrow.bowlBlock").
				setCreativeTab(DCsAppleMilk.applemilkFood);
		
		DCsAppleMilk.bowlJP = (new BlockBowlJP()).
				setBlockName("defeatedcrow.bowlBlockJP").
				setCreativeTab(DCsAppleMilk.applemilkFood);
		
		DCsAppleMilk.foodPlate = (new BlockFoodPlate()).
				setBlockName("defeatedcrow.foodPlate").
				setCreativeTab(DCsAppleMilk.applemilkFood);
		
		DCsAppleMilk.chocoBlock = (new BlockChocoGift()).
				setBlockName("defeatedcrow.chocolateGift").
				setCreativeTab(DCsAppleMilk.applemilkFood);
		
		DCsAppleMilk.teacupBlock = (new BlockFilledCup()).
				setBlockName("defeatedcrow.filledCup").
				setCreativeTab(DCsAppleMilk.applemilkFood);
		
		DCsAppleMilk.teaCup2 = (new BlockFilledCup2()).
				setBlockName("defeatedcrow.filledCup2").
				setCreativeTab(DCsAppleMilk.applemilkFood);
		
		DCsAppleMilk.emptyWallMug = (new ItemEmptyWallMug()).
				setUnlocalizedName("defeatedcrow.emptyWallMug").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.wallMug = (new ItemWallMug()).
				setUnlocalizedName("defeatedcrow.wallMug").
				setCreativeTab(DCsAppleMilk.applemilkFood);
		
		DCsAppleMilk.blockIcecream = (new BlockIceCream()).
				setBlockName("defeatedcrow.iceCreamBlock").
				setCreativeTab(DCsAppleMilk.applemilkFood);
		
		DCsAppleMilk.alcoholCup = (new BlockAlcoholCup()).
				setBlockName("defeatedcrow.alcoholCup").
				setCreativeTab(DCsAppleMilk.applemilkFood);
		
		DCsAppleMilk.cocktail = (new BlockCocktail()).
				setBlockName("defeatedcrow.cocktail").
				setCreativeTab(DCsAppleMilk.applemilkFood);
		
		DCsAppleMilk.emptyBottle = (new BlockEmptyBottle()).
				setBlockName("defeatedcrow.emptyBottle")
				.setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.largeBottle = (new BlockLargeBottle()).
				setBlockName("defeatedcrow.largeBottle");
		
		
		DCsAppleMilk.itemLargeBottle = (new ItemLargeBottle()).
				setUnlocalizedName("defeatedcrow.itemBottle").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.cordial = (new BlockCordial()).
				setBlockName("defeatedcrow.blockCordial");
		
		DCsAppleMilk.itemCordial = (new ItemCordial()).
				setUnlocalizedName("defeatedcrow.itemCordial").
				setCreativeTab(DCsAppleMilk.applemilk);
	}
	
	static void addContainers()
	{
		DCsAppleMilk.woodBox = (new BlockWoodBox()).
				setBlockName("defeatedcrow.WoodBox").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.appleBox = (new BlockAppleBox()).
				setBlockName("defeatedcrow.appleBox").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.vegiBag = (new BlockVegiBag()).
				setBlockName("defeatedcrow.vegiBag").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.cardboard = (new BlockCardboard()).
				setBlockName("defeatedcrow.cardboardBox").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.charcoalBox = (BlockCharcoalBox) (new BlockCharcoalBox()).
				setBlockName("defeatedcrow.charcoalContainer").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.gunpowderContainer = (new BlockGunpowderContainer()).
				setBlockName("defeatedcrow.gunpowderContainer").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.mobBlock = (new BlockMobDrop()).
				setBlockName("defeatedcrow.mobDropBox").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.mushroomBox = (new BlockMushBox()).
				setBlockName("defeatedcrow.mushroomBox").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.eggBasket = (new BlockEggBasket()).
				setBlockName("defeatedcrow.eggBasket").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.melonBomb = (new BlockMelonBomb()).
				setBlockName("defeatedcrow.compressedMelon").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.silkyMelon = (new BlockSilkyMelon()).
				setBlockName("defeatedcrow.compressedSilkyMelon").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.wipeBox = (new BlockWipeBox()).
				setBlockName("defeatedcrow.wipeBox").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.wipeBox2 = (new BlockWipeBox2()).
				setBlockName("defeatedcrow.wipeBox2");
		
	}
	
	static void addDecorations()
	{
		DCsAppleMilk.bowlRack = (new BlockBowlRack()).
				setBlockName("defeatedcrow.bowlRack").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.Basket = (new BlockBasket()).
				setBlockName("defeatedcrow.basket").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.chopsticksBox = (new BlockChopsticksBox()).
				setBlockName("defeatedcrow.chopsticksBox").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.chopsticks = (new ItemChopsticks()).
				setUnlocalizedName("defeatedcrow.chopsticks").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.flintBlock = (new BlockFlint(Material.rock, false)).
				setBlockName("defeatedcrow.flintBlock").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.chalcedony = (new BlockChalcedony(Material.rock, false)).
				setBlockName("defeatedcrow.chalcedony").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.cLamp = (new BlockChalcedonyLamp(Material.glass, false)).
				setBlockName("defeatedcrow.chalcedonyLamp").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.rotaryDial = (new BlockRotaryDial()).
				setBlockName("defeatedcrow.rotaryDial").
				setCreativeTab(DCsAppleMilk.applemilkContainer);
		
		DCsAppleMilk.chalcenonyPanel = (new BlockCPanel()).
				setBlockName("defeatedcrow.chalcedonyPanel").
				setCreativeTab(DCsAppleMilk.applemilk);
	}
	
	static void addPlants()
	{
		DCsAppleMilk.leafTea  = (new ItemLeafTea()).
				setUnlocalizedName("defeatedcrow.leafTea").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.itemMintSeed = (new ItemMintSeed()).
				setUnlocalizedName("defeatedcrow.seedMint").
				setCreativeTab(DCsAppleMilk.applemilk).
				setTextureName("defeatedcrow:seed_mint");
		
		DCsAppleMilk.saplingTea = (new BlockSaplingTea()).
				setBlockName("defeatedcrow.saplingTea").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.teaTree = (new BlockTeaTree()).
				setBlockName("defeatedcrow.teaTree").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.saplingYuzu = (new BlockYuzuSapling()).
				setBlockName("defeatedcrow.saplingYuzu").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.logYuzu = (new BlockYuzuLog()).
				setBlockName("defeatedcrow.logYuzu").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.leavesYuzu = (new BlockYuzuLeaves()).
				setBlockName("defeatedcrow.leavesYuzu").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.cassisTree = (new BlockCassisTree()).
				setBlockName("defeatedcrow.cassisTree").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.cropMint = (new BlockMintCrop()).
				setBlockName("defeatedcrow.cropMint");
		
		DCsAppleMilk.clam = (new ItemClam()).
				setUnlocalizedName("defeatedcrow.clam").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.princessClam = (new ItemPrincessClam()).
				setUnlocalizedName("defeatedcrow.princessClam").
				setCreativeTab(DCsAppleMilk.applemilkMagic);
		
		DCsAppleMilk.clamSand = (new BlockClamSand()).
				setBlockName("defeatedcrow.clamSand").
				setCreativeTab(DCsAppleMilk.applemilk);
	}
	
	static void addMaterials()
	{
		DCsAppleMilk.icyCrystal = (new ItemIcyCrystal()).
				setUnlocalizedName("defeatedcrow.icyCrystal").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.inkStick  = (new ItemInkStick()).
				setUnlocalizedName("defeatedcrow.inkStick").
				setCreativeTab(DCsAppleMilk.applemilk);
	}
	
	static void addMachines()
	{
		DCsAppleMilk.teaMakerNext = (new BlockTeaMakerNext()).
				setBlockName("defeatedcrow.teaMakerNext").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.teaMakerBlack = (new BlockTeaMakerBlack()).
				setBlockName("defeatedcrow.teaMakerBlack").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.emptyCup = (new BlockEmptyCup()).
				setBlockName("defeatedcrow.emptyCup").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.emptyPan = (new BlockEmptyPan()).
				setBlockName("defeatedcrow.soupPanEmpty");
		
		DCsAppleMilk.emptyPanGaiden = (new BlockEmptyPanG()).
				setBlockName("defeatedcrow.soupPanG").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.teppann = (new BlockTeppann()).
				setBlockName("defeatedcrow.cookingIronPlate").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.iceMaker = (new BlockIceMaker()).
				setBlockName("defeatedcrow.iceMaker").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.prosessor = (new BlockProsessor()).
				setBlockName("defeatedcrow.prosessor").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.advProsessor = (new BlockAdvProsessor()).
				setBlockName("defeatedcrow.advProsessor").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.evaporator = (new BlockEvaporator()).
				setBlockName("defeatedcrow.evaporator").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.batteryItem = (new ItemBattery()).
				setUnlocalizedName("defeatedcrow.battery").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.slotPanel = (new ItemSlotPanel()).
				setUnlocalizedName("defeatedcrow.slotPanel").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.essentialOil = (new ItemEssentialOil()).
				setUnlocalizedName("defeatedcrow.essentialOil").
				setCreativeTab(DCsAppleMilk.applemilkMagic);
		
		DCsAppleMilk.dustWood = (new ItemWoodDust()).
				setUnlocalizedName("defeatedcrow.dustWood").
				setCreativeTab(DCsAppleMilk.applemilk);
		
		DCsAppleMilk.insenceBase = (new BlockInsenceBase()).
				setBlockName("defeatedcrow.insenceBase").
				setCreativeTab(DCsAppleMilk.applemilkMagic);
		
		DCsAppleMilk.insenceRose = (ItemInsenceRose) (new ItemInsenceRose()).
				setUnlocalizedName("defeatedcrow.insence_rose").
				setCreativeTab(DCsAppleMilk.applemilkMagic);
		
	}
	
}
