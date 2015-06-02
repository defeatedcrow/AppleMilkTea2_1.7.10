package mods.defeatedcrow.common.block;

import static net.minecraftforge.common.util.ForgeDirection.UP;
import net.minecraft.block.Block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.src.*;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockFlint extends BlockBreakable {

	public BlockFlint(Material material, boolean flag) {
		super("stoneSlab", material, flag);
		this.setHardness(1.5F);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setLightLevel(0.0F);
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 0;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean canPlaceTorchOnTop(World par1World, int par2, int par3, int par4) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		return 0x808080;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderColor(int par1) {
		return 0x808080;
	}

	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		return 0x808080;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = Blocks.stone_slab.getBlockTextureFromSide(1);
	}

	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return side != UP;
	}

}
