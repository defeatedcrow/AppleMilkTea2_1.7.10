package mods.defeatedcrow.plugin.nei;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiRecipe extends GuiContainer {

	public GuiRecipe(Container par1Container) {
		super(par1Container);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation("defeatedcrow", "textures/gui/dummygui.png"));
	}

}
