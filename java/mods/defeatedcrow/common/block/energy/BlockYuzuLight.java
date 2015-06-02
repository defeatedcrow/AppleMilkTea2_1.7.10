package mods.defeatedcrow.common.block.energy;

import java.util.Random;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/*
 * 貼るタイプのライト。
 * 赤石ジェルのライト版で、後発だけど機能が少ない。
 * 
 * 向きはメタデータで格納。0-6:6面分
 */
public class BlockYuzuLight extends Block {

	public BlockYuzuLight() {
		super(Material.clay);
		this.setStepSound(Block.soundTypePiston);
		this.setLightLevel(1.0F);
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public int damageDropped(int p_149692_1_) {
		return 0;
	}

	/* 以下はレンダー用のメソッド群 */

	// 当たり判定はなし
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	// 光透過関係
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	// アイテムアイコン用ボックス
	@Override
	public void setBlockBoundsForItemRender() {
		float f = 0.35F;
		float f1 = 0.35F;
		float f2 = 0.35F;
		this.setBlockBounds(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);
	}

	// 設置時のボックス
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
		this.setThisBound(p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_));
	}

	// メタデータと向き情報を交換、向きごとのブロックを取得。
	// 今回は試験的にForgeDirectionを使用。いずれ他のクラスもForgeDirection使用に切り替えていく。
	protected void setThisBound(int meta) {
		int m = meta & 7;
		float f = 0.0625F;
		float f1 = 0.375F;
		float f2 = 0.625F;
		ForgeDirection dir = ForgeDirection.getOrientation(m);

		switch (dir) {
		case UP:// 上付き
			this.setBlockBounds(f1, 1.0F - f, f1, f2, 1.0F, f2);
			break;
		case DOWN:// 下付き
			this.setBlockBounds(f1, 0.0F, f1, f2, f, f2);
			break;
		case NORTH:// 北
			this.setBlockBounds(f1, f1, 0.0F, f2, f2, f);
			break;
		case SOUTH:// 南
			this.setBlockBounds(f1, f1, 1.0F - f, f2, f2, 1.0F);
			break;
		case WEST:// 西
			this.setBlockBounds(0.0F, f1, f1, f, f2, f2);
			break;
		case EAST:// 東
			this.setBlockBounds(1.0F - f, f1, f1, 1.0F, f2, f2);
			break;
		default:
			this.setBlockBounds(f1, 0.0F, f1, f2, f, f2);
			break;
		}
	}

	/* 設置動作 */

	// 設置可能判定
	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {
		ForgeDirection dir = ForgeDirection.getOrientation(side).getOpposite();
		AMTLogger.debugInfo("current direction : " + dir.name());

		// 固形ブロック以外だと設置不可
		return world.isSideSolid(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, dir);
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		return (par1World.isSideSolid(par2, par3 + 1, par4, ForgeDirection.DOWN))
				|| (par1World.isSideSolid(par2, par3 - 1, par4, ForgeDirection.UP))
				|| (par1World.isSideSolid(par2 + 1, par3, par4, ForgeDirection.EAST))
				|| (par1World.isSideSolid(par2 - 1, par3, par4, ForgeDirection.WEST))
				|| (par1World.isSideSolid(par2, par3, par4 + 1, ForgeDirection.SOUTH))
				|| (par1World.isSideSolid(par2, par3, par4 - 1, ForgeDirection.NORTH));
	}

	/**
	 * Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
	 */
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		ForgeDirection dir = ForgeDirection.getOrientation(side).getOpposite();
		int newMeta = 0;

		switch (dir) {
		case UP:// 上付き
			newMeta = 1;
			break;
		case DOWN:// 下付き
			newMeta = 0;
			break;
		case NORTH:// 北
			newMeta = 2;
			break;
		case SOUTH:// 南
			newMeta = 3;
			break;
		case WEST:// 西
			newMeta = 4;
			break;
		case EAST:// 東
			newMeta = 5;
			break;
		default:
			newMeta = 0;
			break;
		}

		AMTLogger.debugInfo("current direction : " + dir.name());
		return newMeta;

	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		if (!this.canPlaceBlockAt(world, x, y, z)) {
			this.dropBlockAsItem(world, x, y, z, 0, 0);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister) {
		this.blockIcon = par1IIconRegister.registerIcon("defeatedcrow:lightgel");

	}

	/* 以下はブロックの動作 */

	// ピストンでは押せない
	@Override
	public int getMobilityFlag() {
		return 1;
	}

}
