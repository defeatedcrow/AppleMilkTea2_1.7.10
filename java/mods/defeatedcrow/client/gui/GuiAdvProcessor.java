package mods.defeatedcrow.client.gui;

import java.util.ArrayList;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.PropertyHandler;
import mods.defeatedcrow.common.tile.appliance.ContainerAdvProcessor;
import mods.defeatedcrow.common.tile.appliance.TileAdvProcessor;
import mods.defeatedcrow.common.tile.appliance.TileProcessor;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiAdvProcessor extends GuiContainer {

	protected TileProcessor tileentity;
	protected TileProcessor inventory;

	public GuiAdvProcessor(EntityPlayer player, TileAdvProcessor par2TileEntity) {
		super(new ContainerAdvProcessor(player, par2TileEntity));
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
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		// テクスチャの指定
		// ResourceLocationの第一引数を付け足してドメインを指定することもできる
		// 例:new ResourceLocation("sample", "textures/gui/container/furnace.png")
		this.mc.getTextureManager().bindTexture(new ResourceLocation("defeatedcrow", this.GuiTexPass()));

		// かまど描画処理
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		int i1;
		int i2;

		i1 = this.tileentity.getBurnTimeRemainingScaled(27);
		this.drawTexturedModalRect(k + 11, l + 53 - i1, 176, 43 - i1, 12, i1);

		i2 = this.tileentity.getCookProgressScaled(24);
		this.drawTexturedModalRect(k + 88, l + 43, 176, 0, i2 + 1, 16);
	}

	public String GuiTexPass() {
		return "textures/gui/jawcrushergui.png";
	}

}
