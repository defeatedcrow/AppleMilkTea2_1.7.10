package mods.defeatedcrow.client;

import mods.defeatedcrow.client.entity.*;
import mods.defeatedcrow.client.item.RenderEHandleItem;
import mods.defeatedcrow.client.item.RenderEightEyesArm;
import mods.defeatedcrow.client.item.RenderItemCocktailSP;
import mods.defeatedcrow.client.item.RenderItemFossilCannon;
import mods.defeatedcrow.client.item.RenderItemYuzuGatling;
import mods.defeatedcrow.client.model.*;
import mods.defeatedcrow.client.model.model.*;
import mods.defeatedcrow.client.model.tileentity.*;
import mods.defeatedcrow.client.particle.*;
import mods.defeatedcrow.client.renderblocks.*;
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.entity.*;
import mods.defeatedcrow.common.entity.dummy.*;
import mods.defeatedcrow.common.entity.edible.*;
import mods.defeatedcrow.common.tile.*;
import mods.defeatedcrow.common.tile.appliance.*;
import mods.defeatedcrow.common.tile.energy.*;
import mods.defeatedcrow.handler.*;
import mods.defeatedcrow.plugin.nei.LoadNEIPlugin;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}
	
	@Override
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
	
	@Override
	public void registerTileEntity()
    {
		GameRegistry.registerTileEntity(TileTeppann.class, "TileTeppann");
		GameRegistry.registerTileEntity(TileHasDirection.class, "TileHasDirection");
		GameRegistry.registerTileEntity(TileHasRemaining.class, "TileHasRemaining");
		GameRegistry.registerTileEntity(TileHasRemain2.class, "TileHasRemaining2");
		GameRegistry.registerTileEntity(TileDummy.class, "TileDummy");
		GameRegistry.registerTileEntity(TileCPanel.class, "TileChalcedonyPanel");
		
		ClientRegistry.registerTileEntity(TileCupHandle.class, "TileCupHandle", new TileEntityCupHandleRenderer());
		ClientRegistry.registerTileEntity(TileBread.class, "TileBread", new TileEntityBreadRenderer());
		ClientRegistry.registerTileEntity(TileJPBowl.class, "TileJPBowl", new TileEntityBowlJPRenderer());
		ClientRegistry.registerTileEntity(TileChopsticksBox.class, "TileChopsticks", new TileEntityChopsticksRenderer());
		ClientRegistry.registerTileEntity(TileEggs.class, "TileEggs", new TileEntityEggsRenderer());
		ClientRegistry.registerTileEntity(TileSteak.class, "TileSteak", new TileEntitySteakRenderer());
		ClientRegistry.registerTileEntity(TileMakerHandle.class, "TileMakerHandle", new TileEntityMakerRenderer());
		ClientRegistry.registerTileEntity(TilePanHandle.class, "TilePanHandle", new TileEntityPanHandleRenderer());
		ClientRegistry.registerTileEntity(TileChocoPan.class, "TileChocoPan", new TileEntityChocoPanRenderer());
		ClientRegistry.registerTileEntity(TileMakerNext.class, "TilemakerNext", new TileEntityMakerNextRenderer());
		ClientRegistry.registerTileEntity(TileAutoMaker.class, "TileAutoMaker", new TileEntityAutoMakerRenderer());
		ClientRegistry.registerTileEntity(TileWipeBox.class, "TileWipeBox", new TileEntityWipeBoxRenderer());
		ClientRegistry.registerTileEntity(TileIceMaker.class, "TileIceMaker", new TileEntityIceMakerRenderer());
		ClientRegistry.registerTileEntity(TileIceCream.class, "TileIceCream", new TileEntityIceCreamRenderer());
		ClientRegistry.registerTileEntity(TileWipeBox2.class, "TileWipeBox2", new TileEntityWipe2Renderer());
		ClientRegistry.registerTileEntity(TileRotaryDial.class, "TileRotaryDial", new TileEntityDialRenderer());
		ClientRegistry.registerTileEntity(TileCocktail.class, "TileCocktail", new TileEntityCocktailRenderer());
		ClientRegistry.registerTileEntity(TileCocktail2.class, "TileCocktail2", new TileEntityCocktail2Renderer());
		ClientRegistry.registerTileEntity(TileLargeBottle.class, "TileLargeBottle", new TileEntityBottleRenderer());
		ClientRegistry.registerTileEntity(TileEmptyBottle.class, "TileEmptyBottle", new TileEntityEmptyBottleRenderer());
		ClientRegistry.registerTileEntity(TileCLamp.class, "TileChalcedonyLamp", new TileEntityCLampRenderer());
		ClientRegistry.registerTileEntity(TileCordial.class, "TileCordial", new TileEntityCordialRenderer());
		ClientRegistry.registerTileEntity(TileAlcoholCup.class, "TileAlcoholCup", new TileEntityAlcoholCupRenderer());
		ClientRegistry.registerTileEntity(TileProsessor.class, "TileProsessor", new TileEntityProcessorRenderer());
		ClientRegistry.registerTileEntity(TileAdvProsessor.class, "TileAdvProsessor", new TileEntityJawCrusherRenderer());
		ClientRegistry.registerTileEntity(TileEvaporator.class, "TileEvaporator", new TileEntityEvaporatorRenderer());
		ClientRegistry.registerTileEntity(TileVegiBag.class, "TileVegiBag", new TileEntityVegiBagRenderer());
		ClientRegistry.registerTileEntity(TileCardBoard.class, "TileCardBoard", new TileEntityCardBoardRenderer());
		ClientRegistry.registerTileEntity(TileIncenseBase.class, "TileIncenseBase", new TileEntityIncenseBaseRenderer());
		ClientRegistry.registerTileEntity(TilePanG.class, "TilePanG", new TileEntityPanGRenderer());
		ClientRegistry.registerTileEntity(TileCanister.class, "TileCanister", new TileEntityCanisterRenderer());
		ClientRegistry.registerTileEntity(TileBrewingBarrel.class, "TileBarrel", new TileEntityBarrelRenderer());
		ClientRegistry.registerTileEntity(TileChargerDevice.class, "TileChargerDevice", new TileEntityChargerRenderer());
		ClientRegistry.registerTileEntity(TileFlowerPot.class, "TileFlowerPot", new TileEntityFlowerPotRenderer());
		ClientRegistry.registerTileEntity(TileTeppanII.class, "TileTeppanII", new TileEntityTeppanIIRenderer());
		ClientRegistry.registerTileEntity(TileCocktailSP.class, "TileCocktailSP", new TileEntityCocktailSPRenderer());
		ClientRegistry.registerTileEntity(TileHandleEngine.class, "TileEHandle", new TileEntityEHandleRenderer());
		
		GameRegistry.registerTileEntity(TileChargerBase.class, "TileChargerBase");
		GameRegistry.registerTileEntity(TileGelBat.class, "TileGelBattery");
	}

	@Override
	public int getRenderID()
	{
		return RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
	public void registerRenderers()
	{
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
		RenderingRegistry.registerBlockHandler(new RenderProsessor());
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
		RenderingRegistry.registerEntityRenderingHandler(EntityAnchorMissile.class, new RenderAnchorMissile(new ModelAnchorMissile()));
		RenderingRegistry.registerEntityRenderingHandler(EntityYuzuBullet.class, new RenderYuzuBullet(new ModelYuzuBullet()));
		RenderingRegistry.registerEntityRenderingHandler(PlaceableCocktailSP.class, new RenderCocktailSPEntity());
		
		MinecraftForgeClient.registerItemRenderer(DCsAppleMilk.yuzuGatling, new RenderItemYuzuGatling());
		MinecraftForgeClient.registerItemRenderer(DCsAppleMilk.fossilCannon, new RenderItemFossilCannon());
		MinecraftForgeClient.registerItemRenderer(DCsAppleMilk.eightEyesArm, new RenderEightEyesArm());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DCsAppleMilk.cocktailSP), new RenderItemCocktailSP());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(DCsAppleMilk.handleEngine), new RenderEHandleItem());
		
		VillagerRegistry.instance().registerVillagerSkin(DCsConfig.villagerRecipeID, new ResourceLocation(Util.getEntityTexturePassNoAlt() + "villager_cafe.png"));
		VillagerRegistry.instance().registerVillagerSkin(DCsConfig.villagerRecipe2ID, new ResourceLocation(Util.getEntityTexturePassNoAlt() + "villager_yome.png"));
	}
	
	/**
	 * クライアントサイドの場合、呼ばれるのはログイン時になる。
	 * FMLClientHandlerを用いてサーバの環境を判定するため、クライアント限定処理。
	 * */
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
	public void loadNEI() {
		if (Loader.isModLoaded("NotEnoughItems")) {
			AMTLogger.loadingModInfo("NotEnoughItems");
	    	try
	        {
	    		LoadNEIPlugin.load();
	        }
	        catch (Exception e) {
	        	AMTLogger.failLoadingModInfo("NotEnoughItems");
	          e.printStackTrace(System.err);
	        }
		}
	}
	
	@Override
	public void registerFluidTex()
	{
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent.Post event)
	{
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

}
