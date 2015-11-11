package mods.defeatedcrow.client.gui;

import mods.defeatedcrow.common.tile.appliance.TileAdvProcessor;
import net.minecraft.entity.player.EntityPlayer;

public class GuiAdvProcessor extends GuiProcessor {

	public GuiAdvProcessor(EntityPlayer player, TileAdvProcessor par2TileEntity) {
		super(player, par2TileEntity);
	}

	@Override
	public String GuiTexPass() {
		return "textures/gui/jawcrushergui.png";
	}

}
