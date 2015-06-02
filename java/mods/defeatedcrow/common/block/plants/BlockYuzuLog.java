package mods.defeatedcrow.common.block.plants;

import java.util.Random;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockYuzuLog extends BlockRotatedPillar {

	@SideOnly(Side.CLIENT)
	protected IIcon sideIcon;
	@SideOnly(Side.CLIENT)
	protected IIcon topIcon;

	public BlockYuzuLog() {
		super(Material.wood);
		this.setCreativeTab(DCsAppleMilk.applemilk);
		this.setHardness(2.0F);
		this.setStepSound(soundTypeWood);
	}

	public static int func_150165_c(int p_150165_0_) {
		return 0;
	}

	@Override
	public int quantityDropped(Random p_149745_1_) {
		return 1;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5Block, int par6Meta) {
		byte b0 = 4;
		int i1 = b0 + 1;

		if (world.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1)) {
			for (int j1 = -b0; j1 <= b0; ++j1) {
				for (int k1 = -b0; k1 <= b0; ++k1) {
					for (int l1 = -b0; l1 <= b0; ++l1) {
						Block block = world.getBlock(x + j1, y + k1, z + l1);
						if (block.isLeaves(world, x + j1, y + k1, z + l1)) {
							block.beginLeavesDecay(world, x + j1, y + k1, z + l1);
						}
					}
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int p_150163_1_) {
		return this.sideIcon;
	}

	@SideOnly(Side.CLIENT)
	protected IIcon getTopIcon(int p_150161_1_) {
		return this.topIcon;
	}

	@Override
	public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	public boolean isWood(IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "log_yuzu_top");
		this.sideIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "log_yuzu_side");
		this.topIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "log_yuzu_top");

	}

}
