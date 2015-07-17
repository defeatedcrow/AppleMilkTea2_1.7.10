package mods.defeatedcrow.plugin.nei;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiAppliance extends GuiContainer {

	public GuiAppliance(Container par1Container) {
		super(par1Container);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(
				new ResourceLocation("defeatedcrow", "textures/gui/appliancegui_nei.png"));
	}

}
