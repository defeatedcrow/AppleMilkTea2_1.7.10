package mods.defeatedcrow.client.gui;

import mods.defeatedcrow.common.tile.ContainerAutoMaker;
import mods.defeatedcrow.common.tile.TileAutoMaker;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Deprecated
public class GuiAutoMaker extends GuiContainer {
	private static final ResourceLocation guiTextures = new ResourceLocation("defeatedcrow",
			"textures/gui/automakergui.png");
	private IInventory playerInv;
	private TileAutoMaker entityInv;
	private static final String[] modeString = new String[] { "Disabled Automated TeaMaker.", "Enabled Auto mode.",
			"Enabled Manual mode.", "Enabled RS mode." };

	public GuiAutoMaker(InventoryPlayer par1InventoryPlayer, TileAutoMaker tile) {
		super(new ContainerAutoMaker(par1InventoryPlayer, tile));
		this.playerInv = par1InventoryPlayer;
		this.entityInv = tile;
		this.ySize = 168;
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		String s = this.entityInv.hasCustomInventoryName() ? this.entityInv.getInventoryName() : I18n.format(
				this.entityInv.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2,
				4210752);
	}

	private String modeMessage(int mode) {

		String s = "[AppleMilk] " + this.modeString[mode];
		return s;
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(guiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		if (this.entityInv.getMode() == 0) {
			this.drawTexturedModalRect(k + 80, l + 24, 176, 0, 16, 16);
		} else {
			int i = this.entityInv.getMode();
			this.drawTexturedModalRect(k + 80, l + 24, 176, 16 * i, 16, 16);
		}
	}

	public TileAutoMaker getAutoMaker() {
		return this.entityInv;
	}

	static ResourceLocation textureForButton() {
		return guiTextures;
	}
}
