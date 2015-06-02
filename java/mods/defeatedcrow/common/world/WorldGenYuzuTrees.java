package mods.defeatedcrow.common.world;

import java.util.Random;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.block.plants.BlockYuzuSapling;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenYuzuTrees extends WorldGenAbstractTree {

	/** The minimum height of a generated tree. */
	private final int minTreeHeight;
	/** The metadata value of the wood to use in tree generation. */
	private final int metaWood;

	public WorldGenYuzuTrees(boolean flag) {
		this(flag, 4, 0, false);
	}

	public WorldGenYuzuTrees(boolean flag, int minHeight, int logMeta, boolean vine) {
		super(flag);
		this.minTreeHeight = minHeight;
		this.metaWood = logMeta;
	}

	public boolean generate(World world, Random rand, int x, int y, int z) {
		int height = rand.nextInt(4) + this.minTreeHeight;
		boolean flag = true;

		if (y >= 1 && y + height + 1 <= 256) {
			byte leavesRange;
			int k1;
			Block block;

			for (int searchY = y; searchY <= y + 1 + height; ++searchY) {
				leavesRange = 1;

				if (searchY == y) {
					leavesRange = 0;
				}

				if (searchY >= y + 1 + height - 3)// 頂点より3ブロック低い
				{
					leavesRange = 2;
				}

				for (int j1 = x - leavesRange; j1 <= x + leavesRange && flag; ++j1) {
					for (k1 = z - leavesRange; k1 <= z + leavesRange && flag; ++k1) {
						if (searchY >= 0 && searchY < 256) {
							block = world.getBlock(j1, searchY, k1);

							if (!this.isReplaceable(world, j1, searchY, k1)) {
								flag = false;
							}
						} else {
							flag = false;
						}
					}
				}
			}

			if (!flag) {
				return false;
			} else {
				Block block2 = world.getBlock(x, y - 1, z);

				boolean isSoil = block2.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP,
						(BlockYuzuSapling) DCsAppleMilk.saplingYuzu);
				if (isSoil && y < 256 - height - 1) {
					block2.onPlantGrow(world, x, y - 1, z, x, y, z);
					leavesRange = (byte) (height > 4 ? 4 : 3);
					byte b1 = 0;
					int l1;
					int i2;
					int j2;
					int i3;

					for (k1 = y - leavesRange + height; k1 <= y + height; ++k1) {
						i3 = k1 - (y + height);
						l1 = height > 5 ? (i3 < -3 ? b1 - i3 / 2 : b1 + 2 - i3 / 2) : (i3 < -3 ? b1 - 1 - i3 / 2 : b1
								+ 1 - i3 / 2);

						for (i2 = x - l1; i2 <= x + l1; ++i2) {
							j2 = i2 - x;

							for (int k2 = z - l1; k2 <= z + l1; ++k2) {
								int l2 = k2 - z;

								if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || rand.nextInt(2) != 0 && i3 != 0) {
									Block block1 = world.getBlock(i2, k1, k2);

									int meta = rand.nextInt(3) + 1;

									if (block1.isAir(world, i2, k1, k2) || block1.isLeaves(world, i2, k1, k2)) {
										this.setBlockAndNotifyAdequately(world, i2, k1, k2, DCsAppleMilk.leavesYuzu,
												meta);
									}
								}
							}
						}
					}

					for (k1 = 0; k1 < height; ++k1) {
						block = world.getBlock(x, y + k1, z);

						if (block.isAir(world, x, y + k1, z) || block.isLeaves(world, x, y + k1, z)
								|| block.getMaterial() == Material.plants) {
							this.setBlockAndNotifyAdequately(world, x, y + k1, z, DCsAppleMilk.logYuzu, 0);
						}
					}

					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	@Override
	protected boolean isReplaceable(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		return block.isAir(world, x, y, z) || block.isLeaves(world, x, y, z) || block.isWood(world, x, y, z)
				|| block == DCsAppleMilk.saplingYuzu || func_150523_a(block);
	}

}
