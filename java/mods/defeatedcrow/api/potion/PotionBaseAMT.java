package mods.defeatedcrow.api.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PotionBaseAMT extends Potion {

	protected static final ResourceLocation texture = new ResourceLocation("defeatedcrow:textures/gui/icons_potion.png");

	private final int indexX;
	private final int indexY;

	public PotionBaseAMT(int id, boolean flag, int color, int x, int y) {
		super(id, flag, color);
		indexX = x;
		indexY = y;
		this.setIconIndex(x, y);
	}

	// @Override
	// @SideOnly(Side.CLIENT)
	// public void renderInventoryEffect(int x, int y, PotionEffect effect,
	// net.minecraft.client.Minecraft mc) {
	// mc.getTextureManager().bindTexture(texture);
	// if (mc.currentScreen != null)
	// mc.currentScreen.drawTexturedModalRect(x + 6, y + 7, indexX * 18, indexY * 18, 18, 18);
	// }

	@Override
	@SideOnly(Side.CLIENT)
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		return super.getStatusIconIndex();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasStatusIcon() {
		return true;
	}

}
