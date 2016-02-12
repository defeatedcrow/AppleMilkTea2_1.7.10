package mods.defeatedcrow.common.world.village;

import java.util.Random;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.common.ChestGenHooks;

public class ComponentVillageCafe extends StructureVillagePieces.Village {

	private boolean hasMadeChest;

	public ComponentVillageCafe() {
	}

	public ComponentVillageCafe(StructureVillagePieces.Start par1StartPiece, int par2, Random par3Random,
			StructureBoundingBox par4Box, int par5) {
		super(par1StartPiece, par2);
		this.coordBaseMode = par5;
		this.boundingBox = par4Box;
	}

	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox) {

		if (this.field_143015_k < 0)// 地面の平均高さのチェックかな？
		{
			this.field_143015_k = this.getAverageGroundLevel(world, structureboundingbox);

			if (this.field_143015_k < 0) {
				return true;
			}

			this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 7 - 1, 0);
		}

		int widthX = 7;
		int widthZ = 7;
		int height = 7;

		// air
		this.fillWithBlocks(world, structureboundingbox, 0, 1, 0, 9, 8, 8, Blocks.air, Blocks.air, false);
		// 床
		// 丸石
		this.fillWithBlocks(world, structureboundingbox, 0, 0, 1, 7, 1, 7, Blocks.cobblestone, Blocks.cobblestone,
				false);
		// 板張り
		this.fillWithBlocks(world, structureboundingbox, 1, 1, 2, 6, 1, 6, Blocks.planks, Blocks.planks, false);

		// 柱
		for (int y = 0; y < 3; y++) {
			this.placeBlockAtCurrentPosition(world, Blocks.log, 2, 0, 2 + y, 1, structureboundingbox);
			this.placeBlockAtCurrentPosition(world, Blocks.log, 2, 0, 2 + y, 7, structureboundingbox);
			this.placeBlockAtCurrentPosition(world, Blocks.log, 2, 7, 2 + y, 1, structureboundingbox);
			this.placeBlockAtCurrentPosition(world, Blocks.log, 2, 7, 2 + y, 7, structureboundingbox);
		}

		// 壁
		this.fillWithBlocks(world, structureboundingbox, 1, 2, 1, 6, 5, 1, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(world, structureboundingbox, 1, 2, 7, 6, 4, 7, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(world, structureboundingbox, 0, 2, 2, 0, 4, 6, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(world, structureboundingbox, 7, 2, 2, 7, 4, 6, Blocks.planks, Blocks.planks, false);

		// 上階の壁
		this.fillWithBlocks(world, structureboundingbox, 1, 5, 2, 6, 5, 2, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(world, structureboundingbox, 1, 5, 7, 6, 5, 7, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(world, structureboundingbox, 2, 6, 2, 5, 6, 2, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(world, structureboundingbox, 2, 6, 7, 5, 6, 7, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(world, structureboundingbox, 3, 7, 2, 4, 7, 2, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(world, structureboundingbox, 3, 7, 7, 4, 7, 7, Blocks.planks, Blocks.planks, false);

		// 屋根
		int r = this.getMetadataWithOffset(Blocks.oak_stairs, 0);
		int l = this.getMetadataWithOffset(Blocks.oak_stairs, 1);
		int f = this.getMetadataWithOffset(Blocks.oak_stairs, 2);
		int b = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
		for (int z = 1; z < 8; z++) {
			this.placeBlockAtCurrentPosition(world, Blocks.dark_oak_stairs, r, 0, 5, z, structureboundingbox);
			this.placeBlockAtCurrentPosition(world, Blocks.dark_oak_stairs, r, 1, 6, z, structureboundingbox);
			this.placeBlockAtCurrentPosition(world, Blocks.dark_oak_stairs, r, 2, 7, z, structureboundingbox);
			this.placeBlockAtCurrentPosition(world, Blocks.dark_oak_stairs, r, 3, 8, z, structureboundingbox);
			this.placeBlockAtCurrentPosition(world, Blocks.dark_oak_stairs, l, 7, 5, z, structureboundingbox);
			this.placeBlockAtCurrentPosition(world, Blocks.dark_oak_stairs, l, 6, 6, z, structureboundingbox);
			this.placeBlockAtCurrentPosition(world, Blocks.dark_oak_stairs, l, 5, 7, z, structureboundingbox);
			this.placeBlockAtCurrentPosition(world, Blocks.dark_oak_stairs, l, 4, 8, z, structureboundingbox);
		}

		// ひさし
		for (int x = 0; x < 4; x++) {
			this.placeBlockAtCurrentPosition(world, Blocks.wool, 0, x * 2, 5, 0, structureboundingbox);
			this.placeBlockAtCurrentPosition(world, Blocks.wool, 14, x * 2 + 1, 5, 0, structureboundingbox);
		}

		// ドア
		this.placeDoorAtCurrentPosition(world, structureboundingbox, random, 5, 2, 1,
				this.getMetadataWithOffset(Blocks.wooden_door, 3));
		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 3), 5,
				1, 0, structureboundingbox);

		// まど
		this.fillWithBlocks(world, structureboundingbox, 2, 3, 1, 3, 4, 1, Blocks.glass, Blocks.glass, false);
		this.fillWithBlocks(world, structureboundingbox, 0, 3, 3, 0, 4, 4, Blocks.glass, Blocks.glass, false);
		this.fillWithBlocks(world, structureboundingbox, 7, 3, 3, 7, 4, 4, Blocks.glass, Blocks.glass, false);
		this.fillWithBlocks(world, structureboundingbox, 3, 6, 2, 4, 6, 2, Blocks.glass, Blocks.glass, false);
		this.fillWithBlocks(world, structureboundingbox, 3, 6, 7, 4, 6, 7, Blocks.glass, Blocks.glass, false);

		// 花壇
		this.fillWithBlocks(world, structureboundingbox, 2, 1, 0, 3, 1, 0, Blocks.brick_block, Blocks.brick_block,
				false);
		this.fillWithBlocks(world, structureboundingbox, 8, 1, 3, 8, 1, 4, Blocks.brick_block, Blocks.brick_block,
				false);
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.teaTree, 1, 2, 2, 0, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.teaTree, 1, 3, 2, 0, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.teaTree, 1, 8, 2, 3, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.teaTree, 1, 8, 2, 4, structureboundingbox);

		// 照明
		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 2, 6, 5, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 5, 6, 5, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.cLamp, 5, 2, 5, 5, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.cLamp, 5, 5, 5, 5, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 2, 6, 1, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 5, 6, 1, structureboundingbox);

		// 内装
		// 客席
		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, l, 2, 2, 2, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, r, 4, 2, 2, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, f, 6, 2, 3, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, b, 6, 2, 5, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 3, 2, 2, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 6, 2, 4, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, Blocks.carpet, 14, 3, 3, 2, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, Blocks.carpet, 14, 6, 3, 4, structureboundingbox);

		// カウンター
		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, f, 2, 2, 4, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, b + 4, 1, 2, 5, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, b + 4, 2, 2, 5, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, b + 4, 3, 2, 5, structureboundingbox);

		// 装飾
		this.placeBlockAtCurrentPosition(world, Blocks.brick_block, 0, 6, 2, 6, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.teaTree, 1, 6, 3, 6, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, Blocks.bookshelf, 0, 5, 2, 6, structureboundingbox);

		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.rotaryDial, f, 5, 3, 6, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.teaMakerNext, f, 3, 3, 5, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.emptyCup, f, 2, 3, 5, structureboundingbox);

		// 20%
		if (random.nextInt(5) == 0) {
			this.placeBlockAtCurrentPosition(world, DCsAppleMilk.crowDoll, f, 1, 3, 5, structureboundingbox);
		}

		// チェスト
		this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 1, 1, 6, structureboundingbox);
		if (!this.hasMadeChest) {
			this.hasMadeChest = true;
			this.generateStructureChestContents(world, structureboundingbox, random, 1, 1, 6,
					ChestGenHooks.getInfo("villageCafeDC").getItems(random), ChestGenHooks.getInfo("villageCafeDC")
							.getCount(random));
		}
		this.placeBlockAtCurrentPosition(world, Blocks.trapdoor, r, 1, 2, 6, structureboundingbox);

		for (int i = 1; i <= widthZ; ++i) {
			for (int j = 0; j <= widthX; ++j) {
				this.clearCurrentPositionBlocksUpwards(world, j, height + 2, i, structureboundingbox);
				this.func_151554_b(world, Blocks.cobblestone, 0, j, -1, i, structureboundingbox); // fillCurrentPositionBlocksDownwards
			}
		}

		this.spawnVillagers(world, structureboundingbox, 3, 2, 3, 1);
		return true;
	}

	@Override
	protected int getVillagerType(int par1) {
		return DCsConfig.villagerRecipeID;
	}
}
