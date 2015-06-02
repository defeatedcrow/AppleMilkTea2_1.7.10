package mods.defeatedcrow.common.block.plants;

import java.util.List;
import java.util.Random;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.world.*;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockYuzuSapling extends BlockBush implements IGrowable {
	public BlockYuzuSapling() {
		super(Material.plants);
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
		this.setCreativeTab(DCsAppleMilk.applemilk);
	}

	@Override
	protected boolean canPlaceBlockOn(Block block) {
		return block.getMaterial() == Material.ground;
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void updateTick(World world, int par2, int par3, int par4, Random rand) {
		if (!world.isRemote) {
			super.updateTick(world, par2, par3, par4, rand);

			if (world.getBlockLightValue(par2, par3 + 1, par4) >= 9 && rand.nextInt(7) == 0) {
				this.grow(world, par2, par3, par4, rand);
			}
		}
	}

	public void grow(World world, int x, int y, int z, Random rand) {
		int l = world.getBlockMetadata(x, y, z);

		if ((l & 8) == 0) {
			world.setBlockMetadataWithNotify(x, y, z, l | 8, 3);
		} else {
			this.growTree(world, x, y, z, rand);
		}

	}

	public boolean fertilize(World world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);

		if (!world.isRemote) {
			if ((l & 8) == 0) {
				return world.setBlockMetadataWithNotify(x, y, z, l | 8, 3);
			} else {
				this.growTree(world, x, y, z, world.rand);
				return true;
			}
		}
		return true;
	}

	public void growTree(World world, int x, int y, int z, Random rand) {
		if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(world, rand, x, y, z))
			return;

		int l = world.getBlockMetadata(x, y, z);
		WorldGenYuzuTrees object = new WorldGenYuzuTrees(true);

		if (!object.generate(world, rand, x, y, z)) {
			AMTLogger.debugInfo("Failed to growing this tree.");
			world.setBlock(x, y, z, this, l, 4);
		}
	}

	public boolean growable(World world, int x, int y, int z, int meta) {
		return world.getBlock(x, y, z) == this && (world.getBlockMetadata(x, y, z) & 7) == meta;
	}

	/**
	 * Determines the damage on the item the block drops. Used in cloth and wood.
	 */
	public int damageDropped(int meta) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1) {
		this.blockIcon = par1.registerIcon(Util.getTexturePassNoAlt() + "sapling_yuzu");
	}

	public boolean func_149851_a(World world, int x, int y, int z, boolean flag) {
		return true;
	}

	public boolean func_149852_a(World world, Random rand, int x, int y, int z) {
		return true;
	}

	public void func_149853_b(World world, Random rand, int x, int y, int z) {
		this.grow(world, x, y, z, rand);
	}
}
