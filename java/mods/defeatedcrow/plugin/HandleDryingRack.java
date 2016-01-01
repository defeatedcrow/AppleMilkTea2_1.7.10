package mods.defeatedcrow.plugin;

import net.minecraft.tileentity.TileEntity;

public class HandleDryingRack {

	private HandleDryingRack() {
	}

	public static boolean isDryingRack(TileEntity tile) {
		// if (tile != null)
		// return tile instanceof TileDryingRack;
		return false;
	}

	public static boolean addDays(TileEntity tile, int day, boolean isDryOrFerm) {
		// if (tile != null){
		// boolean flag = false;
		// if (tile instanceof TileDryingRack) {
		// TileDryingRack rack = (TileDryingRack) tile;
		// if (isDryOrFerm == rack.DryOrFerm()) {
		// for (int i = 0; i < 4; i++) {
		// rack.addDays(day);
		// flag = true;
		// }
		// }
		// }
		// return flag;}
		return false;
	}

}
