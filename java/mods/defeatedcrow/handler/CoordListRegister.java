package mods.defeatedcrow.handler;

import java.util.ArrayList;
import java.util.HashMap;

import mods.defeatedcrow.common.AMTLogger;
import net.minecraft.world.World;

public class CoordListRegister {

	private static CoordListRegister instance;
	protected static final ArrayList<Coord> coodList = new ArrayList<Coord>();
	protected static final HashMap<Coord, ArrayList<Pos>> coodCounter = new HashMap<Coord, ArrayList<Pos>>();

	private CoordListRegister() {
	}

	public static CoordListRegister getInstance() {
		if (instance == null) {
			instance = new CoordListRegister();
		}
		return instance;
	}

	/** 指定した座礁のブロックをChunkLoaderとして起動する */
	public static boolean setCood(World world, int x, int y, int z, int i, int j) {

		if (world.isRemote)
			return false;
		Coord cood = new Coord(i, j, world.provider.dimensionId);
		Pos pos = new Pos(x, y, z);

		if (!isCoodIncluded(cood)) {
			coodList.add(cood);
			ArrayList<Pos> posList = new ArrayList<Pos>();
			posList.add(pos);
			coodCounter.put(cood, posList);
			return true;
		} else {
			boolean f = false;
			for (Pos p : coodCounter.get(cood)) {
				if (p.equals(pos))
					f = true;
			}
			if (!f) {
				coodCounter.get(cood).add(pos);
				AMTLogger.debugInfo("already added coord");
				return false;
			}
			return false;
		}
	}

	/** 指定した座礁のChunkLoaderを停止する */
	public static boolean deleteCood(World world, int x, int y, int z, int i, int j) {

		if (world.isRemote)
			return false;
		Coord cood = new Coord(i, j, world.provider.dimensionId);
		Pos pos = new Pos(x, y, z);

		if (isCoodIncluded(cood)) {
			boolean f = false;
			for (Pos p : coodCounter.get(cood)) {
				if (p.equals(pos))
					f = true;
			}
			if (f) {
				coodCounter.get(cood).remove(pos);
				AMTLogger.debugInfo("remove pos");
				if (coodCounter.get(cood).isEmpty()) {
					coodList.remove(cood);
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isCoodIncluded(Coord cood) {
		if (coodList.isEmpty())
			return false;
		for (Coord c : coodList) {
			if (c.equals(cood)) {
				return true;
			}
		}
		return false;
	}

}
