package mods.defeatedcrow.common.world.village;

import static net.minecraftforge.common.ChestGenHooks.VILLAGE_BLACKSMITH;

import java.util.Random;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.common.ChestGenHooks;

public class ComponentVillageWarehouse extends StructureVillagePieces.Village {

	// 今のところ、中身は鍛冶屋と同じ。
	public static final WeightedRandomChestContent[] villageBlacksmithChestContents = new WeightedRandomChestContent[] {
			new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3),
			new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10),
			new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5),
			new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15),
			new WeightedRandomChestContent(Items.apple, 0, 1, 3, 15),
			new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 5),
			new WeightedRandomChestContent(Items.iron_sword, 0, 1, 1, 5),
			new WeightedRandomChestContent(Items.iron_chestplate, 0, 1, 1, 5),
			new WeightedRandomChestContent(Items.iron_helmet, 0, 1, 1, 5),
			new WeightedRandomChestContent(Items.iron_leggings, 0, 1, 1, 5),
			new WeightedRandomChestContent(Items.iron_boots, 0, 1, 1, 5),
			new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.obsidian), 0, 3, 7, 5),
			new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.sapling), 0, 3, 7, 5),
			new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3),
			new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1),
			new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1),
			new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1) };
	private boolean hasMadeChest;

	public ComponentVillageWarehouse() {
	}

	public ComponentVillageWarehouse(StructureVillagePieces.Start par1StartPiece, int par2, Random par3Random,
			StructureBoundingBox par4Box, int par5) {
		super(par1StartPiece, par2);
		this.coordBaseMode = par5;
		this.boundingBox = par4Box;
	}

	@Override
	protected void func_143012_a(NBTTagCompound tag) {
		super.func_143012_a(tag);
		tag.setBoolean("Chest", this.hasMadeChest);
	}

	@Override
	protected void func_143011_b(NBTTagCompound tag) {
		super.func_143011_b(tag);
		this.hasMadeChest = tag.getBoolean("Chest");
	}

	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox) {

		if (this.field_143015_k < 0)// 地面の平均高さのチェックかな？
		{
			this.field_143015_k = this.getAverageGroundLevel(world, structureboundingbox);

			if (this.field_143015_k < 0) {
				return true;
			}

			this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 8 - 1, 0);
		}

		int widthX = 5;
		int widthZ = 6;
		int height = 7;

		int type = random.nextInt(3);

		// air
		this.fillWithBlocks(world, structureboundingbox, 0, 1, 0, 4, 8, 7, Blocks.air, Blocks.air, false);
		// 床
		this.fillWithBlocks(world, structureboundingbox, 0, 0, 1, 4, 2, 6, Blocks.cobblestone, Blocks.cobblestone,
				false);
		this.fillWithBlocks(world, structureboundingbox, 1, 1, 2, 3, 2, 5, Blocks.air, Blocks.air, false);
		this.fillWithBlocks(world, structureboundingbox, 1, 1, 2, 3, 1, 5, DCsAppleMilk.flintBlock,
				DCsAppleMilk.flintBlock, false);

		// 柱
		this.fillWithBlocks(world, structureboundingbox, 0, 3, 1, 0, 4, 1, Blocks.cobblestone, Blocks.cobblestone,
				false);
		this.fillWithBlocks(world, structureboundingbox, 0, 3, 6, 0, 4, 6, Blocks.cobblestone, Blocks.cobblestone,
				false);
		this.fillWithBlocks(world, structureboundingbox, 4, 3, 1, 4, 4, 1, Blocks.cobblestone, Blocks.cobblestone,
				false);
		this.fillWithBlocks(world, structureboundingbox, 4, 3, 6, 4, 4, 6, Blocks.cobblestone, Blocks.cobblestone,
				false);

		// 壁
		this.fillWithBlocks(world, structureboundingbox, 1, 3, 1, 3, 5, 1, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(world, structureboundingbox, 1, 3, 6, 3, 5, 6, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(world, structureboundingbox, 0, 3, 2, 0, 4, 5, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(world, structureboundingbox, 4, 3, 2, 4, 4, 5, Blocks.planks, Blocks.planks, false);

		// 上階の壁
		this.fillWithBlocks(world, structureboundingbox, 2, 5, 1, 2, 5, 1, Blocks.glass, Blocks.glass, false);
		this.fillWithBlocks(world, structureboundingbox, 2, 5, 6, 2, 5, 6, Blocks.glass, Blocks.glass, false);
		this.fillWithBlocks(world, structureboundingbox, 2, 6, 1, 2, 6, 1, Blocks.planks, Blocks.planks, false);
		this.fillWithBlocks(world, structureboundingbox, 2, 6, 6, 2, 6, 6, Blocks.planks, Blocks.planks, false);

		// 屋根
		int r = this.getMetadataWithOffset(Blocks.stone_stairs, 0);
		int l = this.getMetadataWithOffset(Blocks.stone_stairs, 1);
		int f = this.getMetadataWithOffset(Blocks.stone_stairs, 2);
		int b = this.getMetadataWithOffset(Blocks.stone_stairs, 3);
		for (int z = 1; z < 7; z++) {
			this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, r, 0, 5, z, structureboundingbox);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, r, 1, 6, z, structureboundingbox);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, l, 4, 5, z, structureboundingbox);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_stairs, l, 3, 6, z, structureboundingbox);
			this.placeBlockAtCurrentPosition(world, Blocks.stone_slab, 0, 2, 7, z, structureboundingbox);
		}

		// ドア
		this.placeDoorAtCurrentPosition(world, structureboundingbox, random, 2, 2, 1,
				this.getMetadataWithOffset(Blocks.wooden_door, 3));
		this.placeBlockAtCurrentPosition(world, Blocks.oak_stairs, this.getMetadataWithOffset(Blocks.oak_stairs, 3), 2,
				1, 0, structureboundingbox);

		// 内装 x:1~3, z: 2~5の範囲

		// 木箱
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.woodBox, 0, 3, 2, 2, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.woodBox, 0, 3, 2, 3, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.woodBox, type, 3, 3, 2, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.woodBox, 0, 1, 2, 4, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.woodBox, type, 1, 2, 3, structureboundingbox);
		// 木炭
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.charcoalBox, 0, 3, 2, 5, structureboundingbox);
		// 野菜袋
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.vegiBag, type, 2, 2, 5, structureboundingbox);

		// がらくた
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.Basket, 14, 1, 2, 2, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.eggBasket, 0, 1, 3, 3, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.wipeBox, 0, 1, 3, 4, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.barrel, 0, 1, 2, 5, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.emptyPanGaiden, 0, 3, 5, 4, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, Blocks.ladder, this.getMetadataWithOffset(Blocks.ladder, 0), 1, 5, 4,
				structureboundingbox);

		// 照明
		this.placeBlockAtCurrentPosition(world, Blocks.fence, 0, 2, 6, 3, structureboundingbox);
		this.placeBlockAtCurrentPosition(world, DCsAppleMilk.cLamp, 5, 2, 5, 3, structureboundingbox);

		// チェスト
		if (!this.hasMadeChest) {
			int i = this.getYWithOffset(2);
			int j = this.getXWithOffset(3, 3);
			int k = this.getZWithOffset(4, 4);

			if (structureboundingbox.isVecInside(j, i, k)) {
				this.hasMadeChest = true;
				this.generateStructureChestContents(world, structureboundingbox, random, 3, 2, 4,
						ChestGenHooks.getItems(VILLAGE_BLACKSMITH, random),
						ChestGenHooks.getCount(VILLAGE_BLACKSMITH, random));
			}
		}

		for (int i = 1; i <= widthZ; ++i) {
			for (int j = 0; j <= widthX; ++j) {
				this.clearCurrentPositionBlocksUpwards(world, j, height + 2, i, structureboundingbox);
				this.func_151554_b(world, Blocks.cobblestone, 0, j, -1, i, structureboundingbox); // fillCurrentPositionBlocksDownwards
			}
		}

		this.spawnVillagers(world, structureboundingbox, 2, 2, 0, 1);
		return true;
	}

	@Override
	protected int getVillagerType(int par1) {
		return DCsConfig.villagerRecipe2ID;
	}
}
