package mods.defeatedcrow.client.gui;

import mods.defeatedcrow.common.tile.appliance.TileAdvProsessor;
import mods.defeatedcrow.common.tile.appliance.TileProsessor;
import net.minecraft.entity.player.EntityPlayer;

public class GuiAdvProsessor extends GuiProsessor {

	public GuiAdvProsessor(EntityPlayer player, TileAdvProsessor par2TileEntity) {
		super(player, par2TileEntity);
	}

	@Override
	public String GuiTexPass() {
		return "textures/gui/jawcrushergui.png";
	}

}
