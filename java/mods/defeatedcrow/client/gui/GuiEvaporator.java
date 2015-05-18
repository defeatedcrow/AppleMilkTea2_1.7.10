package mods.defeatedcrow.client.gui;

import java.util.ArrayList;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.PropertyHandler;
import mods.defeatedcrow.common.tile.appliance.ContainerEvaporator;
import mods.defeatedcrow.common.tile.appliance.TileEvaporator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

public class GuiEvaporator extends GuiContainer {

	private TileEvaporator tileentity;
	private TileEvaporator inventory;

	public GuiEvaporator(EntityPlayer player, TileEvaporator par2TileEntity) {
		super(new ContainerEvaporator(player, par2TileEntity));
		this.tileentity = par2TileEntity;
		this.inventory = par2TileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		// インベントリ名の描画
		String s = this.inventory.hasCustomInventoryName() ? this.inventory.getInventoryName() : I18n.format(
				this.inventory.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2,
				4210752);
	}

	@Override
	public void drawScreen(int x, int y, float par3) {
		super.drawScreen(x, y, par3);

		// チャージゲージのマウスオーバー
		boolean b1 = this.func_146978_c(11, 26, 12, 27, x, y);
		if (b1) {
			int charge = this.tileentity.getChargeAmount();
			ArrayList<String> list1 = new ArrayList<String>();
			list1.add("Charge Amount : " + charge + "/" + tileentity.getMaxChargeAmount());

			if (DCsAppleMilk.proxy.isShiftKeyDown()) { // shiftキー押下時
				int vsRF = charge * PropertyHandler.rateRF();
				int vsEU = charge * PropertyHandler.rateEU();
				int vsGF = charge * PropertyHandler.rateGF();
				list1.add(" - " + vsRF + "/" + tileentity.getMaxChargeAmount() * PropertyHandler.rateRF() + " RF");
				list1.add(" - " + vsEU + "/" + tileentity.getMaxChargeAmount() * PropertyHandler.rateEU() + " EU");
				list1.add(" - " + vsGF + "/" + tileentity.getMaxChargeAmount() * PropertyHandler.rateGF() + " GF");
			} else {
				list1.add(EnumChatFormatting.ITALIC + "LShift: Expand tooltip.");
			}
			this.drawHoveringText(list1, x, y, fontRendererObj);
		}

		// 液体情報
		boolean b2 = this.func_146978_c(141, 13, 16, 41, x, y);
		if (b2) {
			int charge = this.tileentity.getChargeAmount();
			ArrayList<String> list2 = new ArrayList<String>();
			list2.add("Fluid : " + this.tileentity.productTank.getFluidName());
			list2.add("Amount : " + this.tileentity.productTank.getFluidAmount());
			this.drawHoveringText(list2, x, y, fontRendererObj);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		// テクスチャの指定
		// ResourceLocationの第一引数を付け足してドメインを指定することもできる
		// 例:new ResourceLocation("sample", "textures/gui/container/furnace.png")
		this.mc.getTextureManager().bindTexture(new ResourceLocation("defeatedcrow", "textures/gui/evaporatorgui.png"));

		// かまど描画処理
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		int i1;
		int i2;
		int i3;
		int i4;

		i1 = this.tileentity.getBurnTimeRemainingScaled(27);
		this.drawTexturedModalRect(k + 11, l + 53 - i1, 176, 43 - i1, 12, i1);

		i2 = this.tileentity.getCookProgressScaled(24);
		this.drawTexturedModalRect(k + 77, l + 18, 176, 0, i2 + 1, 16);

		i3 = this.tileentity.getCookProgressScaled(16);
		this.drawTexturedModalRect(k + 57, l + 35, 201, 0, 15, i3);

		drawFluid(this.tileentity.productTank.getFluid(), this.tileentity.getFluidAmountScaled(41), k + 141, l + 13,
				16, 41);
	}

	/**
	 * Original code was made by Shift02.
	 */
	private void drawFluid(FluidStack fluid, int level, int x, int y, int width, int height) {
		if (fluid == null || fluid.getFluid() == null) {
			return;
		}

		ResourceLocation res = null;
		if (fluid.getFluid().getSpriteNumber() == 0) {
			res = TextureMap.locationBlocksTexture;
		} else {
			res = TextureMap.locationItemsTexture;
		}
		mc.getTextureManager().bindTexture(res);

		IIcon icon = fluid.getFluid().getIcon(fluid);
		if (icon == null)
			return;
		mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		setGLColorFromInt(fluid.getFluid().getColor(fluid));

		int widR = width;
		int heiR = level;
		int yR = y + (height - heiR);

		int widL = 0;
		int heiL = 0;

		for (int i = 0; i < widR; i += 16) {
			for (int j = 0; j < heiR; j += 16) {
				widL = Math.min(widR - i, 16);
				heiL = Math.min(heiR - j, 16);
				this.drawTexturedModelRectFromIcon(x + i, yR + j, icon, widL, heiL);
			}
		}
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);

	}

	public static void setGLColorFromInt(int color) {
		float red = (color >> 16 & 255) / 255.0F;
		float green = (color >> 8 & 255) / 255.0F;
		float blue = (color & 255) / 255.0F;
		GL11.glColor4f(red, green, blue, 1.0F);
	}

}
