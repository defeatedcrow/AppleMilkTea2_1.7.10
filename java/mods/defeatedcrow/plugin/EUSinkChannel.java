package mods.defeatedcrow.plugin;

import net.minecraft.tileentity.TileEntity;
import ic2.api.energy.prefab.BasicSink;

//該当のTileEntityクラスに直接BasicSinkを置かないための中継点。
public class EUSinkChannel extends BasicSink{

	public EUSinkChannel(TileEntity parent1, int capacity1, int tier1) {
		super(parent1, capacity1, tier1);
	}

}
