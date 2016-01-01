package mods.defeatedcrow.handler;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class Coord {

	public final int x;
	public final int z;
	public final int dim;

	public Coord(int i, int j, int d) {
		x = i;
		z = j;
		dim = d;
	}

	public Chunk getChunk(World world) {
		return world.getChunkFromBlockCoords(x, z);
	}

	public boolean sameCood(int i, int j, int d) {
		return i == x && j == z && dim == d;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Coord) {
			Coord p = (Coord) obj;
			return p.x == x && p.z == z && p.dim == dim;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int i = x + z * 953 + dim * 13;
		return i;
	}

}
