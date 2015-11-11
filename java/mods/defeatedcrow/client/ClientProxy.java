package mods.defeatedcrow.client;

import mods.defeatedcrow.client.entity.RenderAlcoholCupEntity;
import mods.defeatedcrow.client.entity.RenderAnchorMissile;
import mods.defeatedcrow.client.entity.RenderBowlEntity;
import mods.defeatedcrow.client.entity.RenderBowlJPEntity;
import mods.defeatedcrow.client.entity.RenderCocktail2Entity;
import mods.defeatedcrow.client.entity.RenderCocktailEntity;
import mods.defeatedcrow.client.entity.RenderCocktailSPEntity;
import mods.defeatedcrow.client.entity.RenderCup2Entity;
import mods.defeatedcrow.client.entity.RenderCupEntity;
import mods.defeatedcrow.client.entity.RenderIceCreamEntity;
import mods.defeatedcrow.client.entity.RenderIllusionCreeper;
import mods.defeatedcrow.client.entity.RenderKinokoEntity;
import mods.defeatedcrow.client.entity.RenderMelonBomb;
import mods.defeatedcrow.client.entity.RenderSandwichEntity;
import mods.defeatedcrow.client.entity.RenderSilkyMelon;
import mods.defeatedcrow.client.entity.RenderSteakEntity;
import mods.defeatedcrow.client.entity.RenderStunEntity;
import mods.defeatedcrow.client.entity.RenderTartEntity;
import mods.defeatedcrow.client.entity.RenderYuzuBullet;
import mods.defeatedcrow.client.entity.base.RenderFoodEntityBase;
import mods.defeatedcrow.client.item.RenderEHandleItem;
import mods.defeatedcrow.client.item.RenderEightEyesArm;
import mods.defeatedcrow.client.item.RenderItemCocktailSP;
import mods.defeatedcrow.client.item.RenderItemFossilCannon;
import mods.defeatedcrow.client.item.RenderItemYuzuGatling;
import mods.defeatedcrow.client.model.model.ModelAnchorMissile;
import mods.defeatedcrow.client.model.model.ModelYuzuBullet;
import mods.defeatedcrow.client.model.tileentity.TileEntityAlcoholCupRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityAutoMakerRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityBarrelRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityBottleRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityBowlJPRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityBowlRackRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityBreadRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityCLampRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityCanisterRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityCardBoardRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityChargerRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityChocoPanRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityChopsticksRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityCocktail2Renderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityCocktailRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityCocktailSPRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityContainerBaseRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityCordialRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityCupHandleRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityDialRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityEHandleRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityEggsRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityEmptyBottleRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityEvaporatorRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityFlowerPotRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityIceCreamRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityIceMakerRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityIncenseBaseRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityJawCrusherRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityMakerNextRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityMakerRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityPanGRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityPanHandleRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityProcessorRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntitySoupPanRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntitySteakRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityTeppanIIRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityVegiBagRenderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityWipe2Renderer;
import mods.defeatedcrow.client.model.tileentity.TileEntityWipeBoxRenderer;
import mods.defeatedcrow.client.particle.ParticleTex;
import mods.defeatedcrow.client.renderblocks.RenderAlcoholCup;
import mods.defeatedcrow.client.renderblocks.RenderAutoMaker;
import mods.defeatedcrow.client.renderblocks.RenderBowlRack;
import mods.defeatedcrow.client.renderblocks.RenderBreadBasket;
import mods.defeatedcrow.client.renderblocks.RenderCLampOp;
import mods.defeatedcrow.client.renderblocks.RenderCPanel;
import mods.defeatedcrow.client.renderblocks.RenderCassisTree;
import mods.defeatedcrow.client.renderblocks.RenderChalcedonyLamp;
import mods.defeatedcrow.client.renderblocks.RenderChargerDevice;
import mods.defeatedcrow.client.renderblocks.RenderChocoPan;
import mods.defeatedcrow.client.renderblocks.RenderChopsticksBox;
import mods.defeatedcrow.client.renderblocks.RenderCocktail;
import mods.defeatedcrow.client.renderblocks.RenderContainerWBottle;
import mods.defeatedcrow.client.renderblocks.RenderCordial;
import mods.defeatedcrow.client.renderblocks.RenderCupSummer;
import mods.defeatedcrow.client.renderblocks.RenderDial;
import mods.defeatedcrow.client.renderblocks.RenderEHandle;
import mods.defeatedcrow.client.renderblocks.RenderEggsBasket;
import mods.defeatedcrow.client.renderblocks.RenderEmptyCup;
import mods.defeatedcrow.client.renderblocks.RenderEvaporator;
import mods.defeatedcrow.client.renderblocks.RenderFilledBowl;
import mods.defeatedcrow.client.renderblocks.RenderFilledBowlJP;
import mods.defeatedcrow.client.renderblocks.RenderFilledCup;
import mods.defeatedcrow.client.renderblocks.RenderFlowerPot;
import mods.defeatedcrow.client.renderblocks.RenderFoodPlate;
import mods.defeatedcrow.client.renderblocks.RenderGelBat;
import mods.defeatedcrow.client.renderblocks.RenderIceCream;
import mods.defeatedcrow.client.renderblocks.RenderIceMaker;
import mods.defeatedcrow.client.renderblocks.RenderIncenseBase;
import mods.defeatedcrow.client.renderblocks.RenderJawCrusher;
import mods.defeatedcrow.client.renderblocks.RenderKinoko;
import mods.defeatedcrow.client.renderblocks.RenderLargeBottle;
import mods.defeatedcrow.client.renderblocks.RenderProcessor;
import mods.defeatedcrow.client.renderblocks.RenderSoupPan;
import mods.defeatedcrow.client.renderblocks.RenderSoupPanFilled;
import mods.defeatedcrow.client.renderblocks.RenderTeaMakerNext;
import mods.defeatedcrow.client.renderblocks.RenderTeaTree;
import mods.defeatedcrow.client.renderblocks.RenderTeppann;
import mods.defeatedcrow.client.renderblocks.RenderWipeBox;
import mods.defeatedcrow.client.renderblocks.RenderWoodPanel;
import mods.defeatedcrow.client.renderblocks.RenderYuzuBat;
import mods.defeatedcrow.client.renderblocks.RenderYuzuFence;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.CommonProxy;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.entity.EntityAnchorMissile;
import mods.defeatedcrow.common.entity.EntityKinoko;
import mods.defeatedcrow.common.entity.EntityMelonBomb;
import mods.defeatedcrow.common.entity.EntitySilkyMelon;
import mods.defeatedcrow.common.entity.EntityYuzuBullet;
import mods.defeatedcrow.common.entity.dummy.EntityIllusionMobs;
import mods.defeatedcrow.common.entity.dummy.EntityStunEffect;
import mods.defeatedcrow.common.entity.edible.PlaceableAlcoholCup;
import mods.defeatedcrow.common.entity.edible.PlaceableBaseSoup;
import mods.defeatedcrow.common.entity.edible.PlaceableBowl;
import mods.defeatedcrow.common.entity.edible.PlaceableBowlJP;
import mods.defeatedcrow.common.entity.edible.PlaceableCocktail;
import mods.defeatedcrow.common.entity.edible.PlaceableCocktail2;
import mods.defeatedcrow.common.entity.edible.PlaceableCocktailSP;
import mods.defeatedcrow.common.entity.edible.PlaceableCup1;
import mods.defeatedcrow.common.entity.edible.PlaceableCup2;
import mods.defeatedcrow.common.entity.edible.PlaceableIcecream;
import mods.defeatedcrow.common.entity.edible.PlaceableSandwich;
import mods.defeatedcrow.common.entity.edible.PlaceableSteak;
import mods.defeatedcrow.common.entity.edible.PlaceableTart;
import mods.defeatedcrow.common.tile.TileAlcoholCup;
import mods.defeatedcrow.common.tile.TileAutoMaker;
import mods.defeatedcrow.common.tile.TileBowlRack;
import mods.defeatedcrow.common.tile.TileBread;
import mods.defeatedcrow.common.tile.TileBrewingBarrel;
import mods.defeatedcrow.common.tile.TileCLamp;
import mods.defeatedcrow.common.tile.TileCPanel;
import mods.defeatedcrow.common.tile.TileCanister;
import mods.defeatedcrow.common.tile.TileCardBoard;
import mods.defeatedcrow.common.tile.TileChocoPan;
import mods.defeatedcrow.common.tile.TileChopsticksBox;
import mods.defeatedcrow.common.tile.TileCocktail;
import mods.defeatedcrow.common.tile.TileCocktail2;
import mods.defeatedcrow.common.tile.TileCocktailSP;
import mods.defeatedcrow.common.tile.TileContainerBase;
import mods.defeatedcrow.common.tile.TileCordial;
import mods.defeatedcrow.common.tile.TileCupHandle;
import mods.defeatedcrow.common.tile.TileDummy;
import mods.defeatedcrow.common.tile.TileEggs;
import mods.defeatedcrow.common.tile.TileEmptyBottle;
import mods.defeatedcrow.common.tile.TileFlowerPot;
import mods.defeatedcrow.common.tile.TileHasDirection;
import mods.defeatedcrow.common.tile.TileHasRemain2;
import mods.defeatedcrow.common.tile.TileHasRemaining;
import mods.defeatedcrow.common.tile.TileIceCream;
import mods.defeatedcrow.common.tile.TileIncenseBase;
import mods.defeatedcrow.common.tile.TileJPBowl;
import mods.defeatedcrow.common.tile.TileLargeBottle;
import mods.defeatedcrow.common.tile.TileMakerHandle;
import mods.defeatedcrow.common.tile.TilePanHandle;
import mods.defeatedcrow.common.tile.TileRotaryDial;
import mods.defeatedcrow.common.tile.TileSteak;
import mods.defeatedcrow.common.tile.TileVegiBag;
import mods.defeatedcrow.common.tile.TileWipeBox;
import mods.defeatedcrow.common.tile.TileWipeBox2;
import mods.defeatedcrow.common.tile.appliance.TileAdvProcessor;
import mods.defeatedcrow.common.tile.appliance.TileAdvProsessor;
import mods.defeatedcrow.common.tile.appliance.TileEvaporator;
import mods.defeatedcrow.common.tile.appliance.TileFilledSoupPan;
import mods.defeatedcrow.common.tile.appliance.TileIceMaker;
import mods.defeatedcrow.common.tile.appliance.TileMakerNext;
import mods.defeatedcrow.common.tile.appliance.TilePanG;
import mods.defeatedcrow.common.tile.appliance.TileProcessor;
import mods.defeatedcrow.common.tile.appliance.TileProsessor;
import mods.defeatedcrow.common.tile.appliance.TileTeppanII;
import mods.defeatedcrow.common.tile.appliance.TileTeppann;
import mods.defeatedcrow.common.tile.energy.TileChargerBase;
import mods.defeatedcrow.common.tile.energy.TileChargerDevice;
import mods.defeatedcrow.common.tile.energy.TileGelBat;
import mods.defeatedcrow.common.tile.energy.TileHandleEngine;
import mods.defeatedcrow.handler.KeyConfigHelper;
import mods.defeatedcrow.handler.NetworkUtil;
import mods.defeatedcrow.handler.Util;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public int addArmor(String armor) {
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}

	@Override
	public void registerTileEntity() {

		GameRegistry.registerTileEntity(TileHasDirection.class, "TileHasDirection");
		GameRegistry.registerTileEntity(TileHasRemaining.class, "TileHasRemaining");
		GameRegistry.registerTileEntity(TileHasRemain2.class, "TileHasRemaining2");
		GameRegistry.registerTileEntity(TileDummy.class, "TileDummy");
		GameRegistry.registerTileEntity(TileCPanel.class, "TileChalcedonyPanel");

		ClientRegistry.registerTileEntity(TileCupHandle.class, "TileCupHandle", new TileEntityCupHandleRenderer());
		ClientRegistry.registerTileEntity(TileBread.class, "TileBread", new TileEntityBreadRenderer());
		ClientRegistry.registerTileEntity(TileJPBowl.class, "TileJPBowl", new TileEntityBowlJPRenderer());
		ClientRegistry
				.registerTileEntity(TileChopsticksBox.class, "TileChopsticks", new TileEntityChopsticksRenderer());
		ClientRegistry.registerTileEntity(TileEggs.class, "TileEggs", new TileEntityEggsRenderer());
		ClientRegistry.registerTileEntity(TileSteak.class, "TileSteak", new TileEntitySteakRenderer());
		ClientRegistry.registerTileEntity(TileMakerHandle.class, "TileMakerHandle", new TileEntityMakerRenderer());
		ClientRegistry.registerTileEntity(TilePanHandle.class, "TilePanHandle", new TileEntityPanHandleRenderer());
		ClientRegistry.registerTileEntity(TileMakerNext.class, "TilemakerNext", new TileEntityMakerNextRenderer());
		ClientRegistry.registerTileEntity(TileWipeBox.class, "TileWipeBox", new TileEntityWipeBoxRenderer());
		ClientRegistry.registerTileEntity(TileIceMaker.class, "TileIceMaker", new TileEntityIceMakerRenderer());
		ClientRegistry.registerTileEntity(TileIceCream.class, "TileIceCream", new TileEntityIceCreamRenderer());
		ClientRegistry.registerTileEntity(TileWipeBox2.class, "TileWipeBox2", new TileEntityWipe2Renderer());
		ClientRegistry.registerTileEntity(TileRotaryDial.class, "TileRotaryDial", new TileEntityDialRenderer());
		ClientRegistry.registerTileEntity(TileCocktail.class, "TileCocktail", new TileEntityCocktailRenderer());
		ClientRegistry.registerTileEntity(TileCocktail2.class, "TileCocktail2", new TileEntityCocktail2Renderer());
		ClientRegistry.registerTileEntity(TileLargeBottle.class, "TileLargeBottle", new TileEntityBottleRenderer());
		ClientRegistry
				.registerTileEntity(TileEmptyBottle.class, "TileEmptyBottle", new TileEntityEmptyBottleRenderer());
		ClientRegistry.registerTileEntity(TileCLamp.class, "TileChalcedonyLamp", new TileEntityCLampRenderer());
		ClientRegistry.registerTileEntity(TileCordial.class, "TileCordial", new TileEntityCordialRenderer());
		ClientRegistry.registerTileEntity(TileAlcoholCup.class, "TileAlcoholCup", new TileEntityAlcoholCupRenderer());
		ClientRegistry.registerTileEntity(TileProcessor.class, "TileProcessor", new TileEntityProcessorRenderer());
		ClientRegistry.registerTileEntity(TileAdvProcessor.class, "TileAdvProcessor",
				new TileEntityJawCrusherRenderer());
		ClientRegistry.registerTileEntity(TileEvaporator.class, "TileEvaporator", new TileEntityEvaporatorRenderer());
		ClientRegistry.registerTileEntity(TileVegiBag.class, "TileVegiBag", new TileEntityVegiBagRenderer());
		ClientRegistry.registerTileEntity(TileCardBoard.class, "TileCardBoard", new TileEntityCardBoardRenderer());
		ClientRegistry
				.registerTileEntity(TileIncenseBase.class, "TileIncenseBase", new TileEntityIncenseBaseRenderer());
		ClientRegistry.registerTileEntity(TilePanG.class, "TilePanG", new TileEntityPanGRenderer());
		ClientRegistry.registerTileEntity(TileCanister.class, "TileCanister", new TileEntityCanisterRenderer());
		ClientRegistry.registerTileEntity(TileBrewingBarrel.class, "TileBarrel", new TileEntityBarrelRenderer());
		ClientRegistry
				.registerTileEntity(TileChargerDevice.class, "TileChargerDevice", new TileEntityChargerRenderer());
		ClientRegistry.registerTileEntity(TileFlowerPot.class, "TileFlowerPot", new TileEntityFlowerPotRenderer());
		ClientRegistry.registerTileEntity(TileTeppanII.class, "TileTeppanII", new TileEntityTeppanIIRenderer());
		ClientRegistry.registerTileEntity(TileCocktailSP.class, "TileCocktailSP", new TileEntityCocktailSPRenderer());
		ClientRegistry.registerTileEntity(TileHandleEngine.class, "TileEHandle", new TileEntityEHandleRenderer());
		ClientRegistry.registerTileEntity(TileBowlRack.class, "TileBowlRack", new TileEntityBowlRackRenderer());

		GameRegistry.registerTileEntity(TileChargerBase.class, "TileChargerBase");
		GameRegistry.registerTileEntity(TileGelBat.class, "TileGelBattery");
		ClientRegistry.registerTileEntity(TileContainerBase.class, "TileDCContainerBase",
				new TileEntityContainerBaseRenderer());

		// deprecated
		GameRegistry.registerTileEntity(TileTeppann.class, "TileTeppann");
		ClientRegistry.registerTileEntity(TileChocoPan.class, "TileChocoPan", new TileEntityChocoPanRenderer());
		ClientRegistry
				.registerTileEntity(TileFilledSoupPan.class, "TileFilledSoupPan", new TileEntitySoupPanRenderer());
		ClientRegistry.registerTileEntity(TileAutoMaker.class, "TileAutoMaker", new TileEntityAutoMakerRenderer());
		ClientRegistry.registerTileEntity(TileProsessor.class, "TileProsessor", new TileEntityProcessorRenderer());
		ClientRegistry.registerTileEntity(TileAdvProsessor.class, "TileAdvProsessor",
				new TileEntityJawCrusherRenderer());
	}

	@Override
	public int getRenderID() {
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public void registerRenderers() {
		RenderingRegistry.registerBlockHandler(new RenderEmptyCup());
		RenderingRegistry.registerBlockHandler(new RenderSoupPan());
		RenderingRegistry.registerBlockHandler(new RenderTeaTree());
		RenderingRegistry.registerBlockHandler(new RenderFilledCup());
		RenderingRegistry.registerBlockHandler(new RenderFilledBowl());
		RenderingRegistry.registerBlockHandler(new RenderBowlRack());
		RenderingRegistry.registerBlockHandler(new RenderChalcedonyLamp());
		RenderingRegistry.registerBlockHandler(new RenderBreadBasket());
		RenderingRegistry.registerBlockHandler(new RenderFoodPlate());
		RenderingRegistry.registerBlockHandler(new RenderTeppann());
		RenderingRegistry.registerBlockHandler(new RenderFilledBowlJP());
		RenderingRegistry.registerBlockHandler(new RenderCupSummer());
		RenderingRegistry.registerBlockHandler(new RenderChopsticksBox());
		RenderingRegistry.registerBlockHandler(new RenderEggsBasket());
		RenderingRegistry.registerBlockHandler(new RenderKinoko());
		RenderingRegistry.registerBlockHandler(new RenderChocoPan());
		RenderingRegistry.registerBlockHandler(new RenderTeaMakerNext());
		RenderingRegistry.registerBlockHandler(new RenderAutoMaker());
		RenderingRegistry.registerBlockHandler(new RenderWipeBox());
		RenderingRegistry.registerBlockHandler(new RenderIceMaker());
		RenderingRegistry.registerBlockHandler(new RenderIceCream());
		RenderingRegistry.registerBlockHandler(new RenderDial());
		RenderingRegistry.registerBlockHandler(new RenderCocktail());
		RenderingRegistry.registerBlockHandler(new RenderLargeBottle());
		RenderingRegistry.registerBlockHandler(new RenderCassisTree());
		RenderingRegistry.registerBlockHandler(new RenderCordial());
		RenderingRegistry.registerBlockHandler(new RenderAlcoholCup());
		RenderingRegistry.registerBlockHandler(new RenderProcessor());
		RenderingRegistry.registerBlockHandler(new RenderEvaporator());
		RenderingRegistry.registerBlockHandler(new RenderJawCrusher());
		RenderingRegistry.registerBlockHandler(new RenderCPanel());
		RenderingRegistry.registerBlockHandler(new RenderIncenseBase());
		RenderingRegistry.registerBlockHandler(new RenderYuzuBat());
		RenderingRegistry.registerBlockHandler(new RenderGelBat());
		RenderingRegistry.registerBlockHandler(new RenderChargerDevice());
		RenderingRegistry.registerBlockHandler(new RenderFlowerPot());
		RenderingRegistry.registerBlockHandler(new RenderYuzuFence());
		RenderingRegistry.registerBlockHandler(new RenderEHandle());
		RenderingRegistry.registerBlockHandler(new RenderWoodPanel());
		RenderingRegistry.registerBlockHandler(new RenderCLampOp());
		RenderingRegistry.registerBlockHandler(new RenderSoupPanFilled());
		RenderingRegistry.registerBlockHandler(new RenderContainerWBottle());

		RenderingRegistry.registerEntityRenderingHandler(EntityMelonBomb.class, new RenderMelonBomb());
		RenderingRegistry.registerEntityRenderingHandler(EntitySilkyMelon.class, new RenderSilkyMelon());
		RenderingRegistry.registerEntityRenderingHandler(PlaceableIcecream.class, new RenderIceCreamEntity());
		RenderingRegistry.registerEntityRenderingHandler(PlaceableSteak.class, new RenderSteakEntity());
		RenderingRegistry.registerEntityRenderingHandler(PlaceableAlcoholCup.class, new RenderAlcoholCupEntity());
		RenderingRegistry.registerEntityRenderingHandler(PlaceableCocktail.class, new RenderCocktailEntity());
		RenderingRegistry.registerEntityRenderingHandler(PlaceableCocktail2.class, new RenderCocktail2Entity());
		RenderingRegistry.registerEntityRenderingHandler(PlaceableBowl.class, new RenderBowlEntity());
		RenderingRegistry.registerEntityRenderingHandler(PlaceableBowlJP.class, new RenderBowlJPEntity());
		RenderingRegistry.registerEntityRenderingHandler(PlaceableCup1.class, new RenderCupEntity());
		RenderingRegistry.registerEntityRenderingHandler(PlaceableCup2.class, new RenderCup2Entity());
		RenderingRegistry.registerEntityRenderingHandler(PlaceableTart.class, new RenderTartEntity());
		RenderingRegistry.registerEntityRenderingHandler(PlaceableSandwich.class, new RenderSandwichEntity());
		RenderingRegistry.registerEntityRenderingHandler(EntityKinoko.class, new RenderKinokoEntity());
		RenderingRegistry.registerEntityRenderingHandler(EntityStunEffect.class, new RenderStunEntity());
		RenderingRegistry.registerEntityRenderingHandler(EntityIllusionMobs.class, new RenderIllusionCreeper());
		RenderingRegistry.registerEntityRenderingHandler(EntityAnchorMissile.class, new RenderAnchorMissile(
				new ModelAnchorMissile()));
		RenderingRegistry.registerEntityRenderingHandler(EntityYuzuBullet.class, new RenderYuzuBullet(
				new ModelYuzuBullet()));
		RenderingRegistry.registerEntityRenderingHandler(PlaceableCocktailSP.class, new RenderCocktailSPEntity());
		RenderingRegistry.registerEntityRenderingHandler(PlaceableBaseSoup.class, new RenderFoodEntityBase());

		MinecraftForgeClient.registerItemRenderer(DCsAppleMilk.yuzuGatling, new RenderItemYuzuGatling());
		MinecraftForgeClient.registerItemRenderer(DCsAppleMilk.fossilCannon, new RenderItemFossilCannon());
		MinecraftForgeClient.registerItemRenderer(DCsAppleMilk.eightEyesArm, new RenderEightEyesArm());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DCsAppleMilk.cocktailSP),
				new RenderItemCocktailSP());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DCsAppleMilk.handleEngine),
				new RenderEHandleItem());

		VillagerRegistry.instance().registerVillagerSkin(DCsConfig.villagerRecipeID,
				new ResourceLocation(Util.getEntityTexturePassNoAlt() + "villager_cafe.png"));
		VillagerRegistry.instance().registerVillagerSkin(DCsConfig.villagerRecipe2ID,
				new ResourceLocation(Util.getEntityTexturePassNoAlt() + "villager_yome.png"));
	}

	/**
	 * クライアントサイドの場合、呼ばれるのはログイン時になる。
	 * FMLClientHandlerを用いてサーバの環境を判定するため、クライアント限定処理。
	 */
	@Override
	public void serverStart() {

	}

	@Override
	public void playerLogin(EntityPlayer player) {
		if (player instanceof EntityClientPlayerMP) {
			String playerName = player.getDisplayName();
			AMTLogger.info("Player Logged in : " + playerName);
			NetworkUtil.initClientMP();
		}
	}

	@Override
	public void init() {
	}

	@Override
	public void registerTex() {
		MinecraftForge.EVENT_BUS.register(ParticleTex.getInstance());
	}

	@Override
	public void registerFluidTex() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent.Post event) {
		DCsAppleMilk.vegitableOil.setIcons(DCsAppleMilk.blockVegitableOil.getIcon(0, 0));
		DCsAppleMilk.camelliaOil.setIcons(DCsAppleMilk.blockCamelliaOil.getIcon(0, 0));
		DCsAppleMilk.shothu_young.setIcons(DCsAppleMilk.blockDummyAlcohol.getIcon(0, 0));
		DCsAppleMilk.whiskey_young.setIcons(DCsAppleMilk.blockDummyAlcohol.getIcon(0, 1));
		DCsAppleMilk.brandy_young.setIcons(DCsAppleMilk.blockDummyAlcohol.getIcon(0, 2));
		DCsAppleMilk.rum_young.setIcons(DCsAppleMilk.blockDummyAlcohol.getIcon(0, 0));
		DCsAppleMilk.vodka_young.setIcons(DCsAppleMilk.blockDummyAlcohol.getIcon(0, 0));
		DCsAppleMilk.shothu.setIcons(DCsAppleMilk.blockDummyAlcohol.getIcon(0, 3));
		DCsAppleMilk.whiskey.setIcons(DCsAppleMilk.blockDummyAlcohol.getIcon(0, 4));
		DCsAppleMilk.brandy.setIcons(DCsAppleMilk.blockDummyAlcohol.getIcon(0, 5));
		DCsAppleMilk.rum.setIcons(DCsAppleMilk.blockDummyAlcohol.getIcon(0, 9));
		DCsAppleMilk.vodka.setIcons(DCsAppleMilk.blockDummyAlcohol.getIcon(0, 10));
		DCsAppleMilk.sake_young.setIcons(DCsAppleMilk.blockDummyAlcohol2.getIcon(0, 0));
		DCsAppleMilk.beer_young.setIcons(DCsAppleMilk.blockDummyAlcohol2.getIcon(0, 1));
		DCsAppleMilk.wine_young.setIcons(DCsAppleMilk.blockDummyAlcohol2.getIcon(0, 2));
		DCsAppleMilk.sake.setIcons(DCsAppleMilk.blockDummyAlcohol2.getIcon(0, 3));
		DCsAppleMilk.beer.setIcons(DCsAppleMilk.blockDummyAlcohol2.getIcon(0, 4));
		DCsAppleMilk.wine.setIcons(DCsAppleMilk.blockDummyAlcohol2.getIcon(0, 5));
	}

	@Override
	public boolean isShiftKeyDown() {
		return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
	}

	@Override
	public boolean isJumpKeyDown() {
		return Keyboard.isKeyDown(KeyConfigHelper.getJumpKey());
	}

	@Override
	public boolean isSneakKeyDown() {
		return Keyboard.isKeyDown(KeyConfigHelper.getSneakKey());
	}

	@Override
	public boolean isFowardKeyDown() {
		return Keyboard.isKeyDown(KeyConfigHelper.getFowardKey());
	}

	@Override
	public boolean isBackKeyDown() {
		return Keyboard.isKeyDown(KeyConfigHelper.getBackKey());
	}

	@Override
	public boolean isLeftKeyDown() {
		return Keyboard.isKeyDown(KeyConfigHelper.getLeftKey());
	}

	@Override
	public boolean isRightKeyDown() {
		return Keyboard.isKeyDown(KeyConfigHelper.getRightKey());
	}
}
