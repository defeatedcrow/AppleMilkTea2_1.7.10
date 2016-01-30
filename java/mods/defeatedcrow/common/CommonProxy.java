package mods.defeatedcrow.common;

import mods.defeatedcrow.client.gui.GuiAdvProcessor;
import mods.defeatedcrow.client.gui.GuiBatBox;
import mods.defeatedcrow.client.gui.GuiEvaporator;
import mods.defeatedcrow.client.gui.GuiIceMaker;
import mods.defeatedcrow.client.gui.GuiProcessor;
import mods.defeatedcrow.common.tile.TileAlcoholCup;
import mods.defeatedcrow.common.tile.TileBowlRack;
import mods.defeatedcrow.common.tile.TileBread;
import mods.defeatedcrow.common.tile.TileBrewingBarrel;
import mods.defeatedcrow.common.tile.TileCLamp;
import mods.defeatedcrow.common.tile.TileCPanel;
import mods.defeatedcrow.common.tile.TileCardBoard;
import mods.defeatedcrow.common.tile.TileChopsticksBox;
import mods.defeatedcrow.common.tile.TileCocktail;
import mods.defeatedcrow.common.tile.TileCocktail2;
import mods.defeatedcrow.common.tile.TileCocktailSP;
import mods.defeatedcrow.common.tile.TileContainerBase;
import mods.defeatedcrow.common.tile.TileCordial;
import mods.defeatedcrow.common.tile.TileCrowDoll;
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
import mods.defeatedcrow.common.tile.appliance.ContainerAdvProcessor;
import mods.defeatedcrow.common.tile.appliance.ContainerEvaporator;
import mods.defeatedcrow.common.tile.appliance.ContainerIceMaker;
import mods.defeatedcrow.common.tile.appliance.ContainerProcessor;
import mods.defeatedcrow.common.tile.appliance.TileAdvProcessor;
import mods.defeatedcrow.common.tile.appliance.TileEvaporator;
import mods.defeatedcrow.common.tile.appliance.TileFilledSoupPan;
import mods.defeatedcrow.common.tile.appliance.TileIceMaker;
import mods.defeatedcrow.common.tile.appliance.TileMakerNext;
import mods.defeatedcrow.common.tile.appliance.TilePanG;
import mods.defeatedcrow.common.tile.appliance.TileProcessor;
import mods.defeatedcrow.common.tile.appliance.TileTeppanII;
import mods.defeatedcrow.common.tile.energy.ContainerBatBox;
import mods.defeatedcrow.common.tile.energy.TileChargerBase;
import mods.defeatedcrow.common.tile.energy.TileChargerDevice;
import mods.defeatedcrow.common.tile.energy.TileGelBat;
import mods.defeatedcrow.common.tile.energy.TileHandleEngine;
import mods.defeatedcrow.handler.NetworkUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IGuiHandler {

	// TileEntityの登録
	// こちらはサーバー用クラスなので、レンダー関係は一切登録しない
	public void registerTileEntity() {
		GameRegistry.registerTileEntity(TileHasDirection.class, "TileHasDirection");
		GameRegistry.registerTileEntity(TileHasRemaining.class, "TileHasRemaining");
		GameRegistry.registerTileEntity(TileHasRemain2.class, "TileHasRemaining2");

		GameRegistry.registerTileEntity(TileCupHandle.class, "TileCupHandle");
		GameRegistry.registerTileEntity(TileBread.class, "TileBread");
		GameRegistry.registerTileEntity(TileDummy.class, "TileDummy");
		GameRegistry.registerTileEntity(TileJPBowl.class, "TileJPBowl");
		GameRegistry.registerTileEntity(TileChopsticksBox.class, "TileChopsticksBox");
		GameRegistry.registerTileEntity(TileEggs.class, "TileEggs");
		GameRegistry.registerTileEntity(TileSteak.class, "TileSteak");
		GameRegistry.registerTileEntity(TileMakerHandle.class, "TileMakerHandle");
		GameRegistry.registerTileEntity(TilePanHandle.class, "TilePanHandle");
		GameRegistry.registerTileEntity(TileFilledSoupPan.class, "TileFilledSoupPan");
		GameRegistry.registerTileEntity(TileMakerNext.class, "TileMakerNext");
		GameRegistry.registerTileEntity(TileWipeBox.class, "TileWipeBox");
		GameRegistry.registerTileEntity(TileIceMaker.class, "TileIceMaker");
		GameRegistry.registerTileEntity(TileIceCream.class, "TileIcecream");
		GameRegistry.registerTileEntity(TileWipeBox2.class, "TileWipeBox2");
		GameRegistry.registerTileEntity(TileRotaryDial.class, "TileRotaryDial");
		GameRegistry.registerTileEntity(TileCocktail.class, "TileCocktail");
		GameRegistry.registerTileEntity(TileCocktail2.class, "TileCocktail2");
		GameRegistry.registerTileEntity(TileLargeBottle.class, "TileLargeBottle");
		GameRegistry.registerTileEntity(TileEmptyBottle.class, "TileEmptyBottle");
		GameRegistry.registerTileEntity(TileCLamp.class, "TileChalcedonyLamp");
		GameRegistry.registerTileEntity(TileCordial.class, "TileCordial");
		GameRegistry.registerTileEntity(TileAlcoholCup.class, "TileAlcoholCup");
		GameRegistry.registerTileEntity(TileEvaporator.class, "TileEvaporator");
		GameRegistry.registerTileEntity(TileProcessor.class, "TileProcessor");
		GameRegistry.registerTileEntity(TileAdvProcessor.class, "TileAdvProcessor");
		GameRegistry.registerTileEntity(TileVegiBag.class, "TileVegiBag");
		GameRegistry.registerTileEntity(TileCardBoard.class, "TileCardboard");
		GameRegistry.registerTileEntity(TileCPanel.class, "TileChalcedonyPanel");
		GameRegistry.registerTileEntity(TileIncenseBase.class, "TileIncenseBase");
		GameRegistry.registerTileEntity(TilePanG.class, "TilePanG");
		GameRegistry.registerTileEntity(TileBrewingBarrel.class, "TileBarrel");
		GameRegistry.registerTileEntity(TileChargerBase.class, "TileChargerBase");
		GameRegistry.registerTileEntity(TileChargerDevice.class, "TileChargerDevice");
		GameRegistry.registerTileEntity(TileFlowerPot.class, "TileFlowerPot");
		GameRegistry.registerTileEntity(TileGelBat.class, "TileGelBattery");
		GameRegistry.registerTileEntity(TileTeppanII.class, "TileTeppanII");
		GameRegistry.registerTileEntity(TileCocktailSP.class, "TileCocktailSP");
		GameRegistry.registerTileEntity(TileHandleEngine.class, "TileEHandle");
		GameRegistry.registerTileEntity(TileBowlRack.class, "TileBowlRack");
		GameRegistry.registerTileEntity(TileContainerBase.class, "TileDCContainerBase");
		GameRegistry.registerTileEntity(TileCrowDoll.class, "TileDCCrowDoll");
	}

	// レンダーIDには-1を返す
	public int getRenderID() {
		return -1;
	}

	// レンダーの登録も何もしない
	public void registerRenderers() {

	}

	public int addArmor(String armor) {
		return 0;
	}

	// クライアント側のワールドではないのでnullを返す。
	public World getClientWorld() {

		return null;
	}

	// GUIの登録
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (!world.blockExists(x, y, z))
			return null;

		TileEntity tileentity = world.getTileEntity(x, y, z);
		if (tileentity instanceof TileIceMaker) {
			return new ContainerIceMaker(player, (TileIceMaker) tileentity);
		} else if (tileentity instanceof TileAdvProcessor) {
			return new ContainerAdvProcessor(player, (TileAdvProcessor) tileentity);
		} else if (tileentity instanceof TileProcessor) {
			return new ContainerProcessor(player, (TileProcessor) tileentity);
		} else if (tileentity instanceof TileEvaporator) {
			return new ContainerEvaporator(player, (TileEvaporator) tileentity);
		} else if (tileentity instanceof TileChargerBase) {
			return new ContainerBatBox(player, (TileChargerBase) tileentity);
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (!world.blockExists(x, y, z))
			return null;

		TileEntity tileentity = world.getTileEntity(x, y, z);
		if (tileentity instanceof TileIceMaker) {
			return new GuiIceMaker(player, (TileIceMaker) tileentity);
		} else if (tileentity instanceof TileAdvProcessor) {
			return new GuiAdvProcessor(player, (TileAdvProcessor) tileentity);
		} else if (tileentity instanceof TileProcessor) {
			return new GuiProcessor(player, (TileProcessor) tileentity);
		} else if (tileentity instanceof TileEvaporator) {
			return new GuiEvaporator(player, (TileEvaporator) tileentity);
		} else if (tileentity instanceof TileChargerBase) {
			return new GuiBatBox(player, (TileChargerBase) tileentity);
		}

		return null;
	}

	/**
	 * サーバサイドの場合、これが呼ばれるのは鯖の起動時、MODの読み込みなどとともに行われる。
	 * FMLServerHandlerを用いてサーバの環境を判定するため、クライアントでは行うことが出来ない。
	 */
	public void serverStart() {
		NetworkUtil.initServer();
	}

	public void playerLogin(EntityPlayer player) {
	}

	public void init() {
	}

	public void registerTex() {
	}

	public void registerFluidTex() {
	}

	public boolean isShiftKeyDown() {
		return false;
	}

	public boolean isJumpKeyDown() {
		return false;
	}

	public boolean isSneakKeyDown() {
		return false;
	}

	public boolean isFowardKeyDown() {
		return false;
	}

	public boolean isBackKeyDown() {
		return false;
	}

	public boolean isLeftKeyDown() {
		return false;
	}

	public boolean isRightKeyDown() {
		return false;
	}

	public boolean isWarpKeyDown() {
		return false;
	}

}
