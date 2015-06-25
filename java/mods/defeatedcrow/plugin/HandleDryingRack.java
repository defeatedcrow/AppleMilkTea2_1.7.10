package mods.defeatedcrow.plugin;

import net.minecraft.tileentity.TileEntity;
import defeatedcrow.addonforamt.jpaddon.common.block.TileDryingRack;

public class HandleDryingRack {

	private HandleDryingRack() {
	}

	public static boolean isDryingRack(TileEntity tile) {
		if (tile == null)
			return false;
		return tile instanceof TileDryingRack;
	}

	public static boolean addDays(TileEntity tile, int day, boolean isDryOrFerm) {
		if (tile == null)
			return false;
		boolean flag = false;
		if (tile instanceof TileDryingRack) {
			TileDryingRack rack = (TileDryingRack) tile;
			if (isDryOrFerm == rack.DryOrFerm()) {
				for (int i = 0; i < 4; i++) {
					rack.addDays(day);
					flag = true;
				}
			}
		}
		return flag;
	}

}
