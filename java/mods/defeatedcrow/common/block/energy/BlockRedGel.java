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
 * 貼るタイプの赤石。
 * 赤石と違って、どんな状態でもテクスチャは変わらず、また出力方向も常に対角以外の5面である。
 * 右クリックでON・OFFを切り替える。
 * 
 * 向きはメタデータで格納。0-6:6面分
 */
public class BlockRedGel extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon texSide;

	public BlockRedGel() {
		super(Material.circuits);
		this.setStepSound(Block.soundTypePiston);
		this.setTickRandomly(true);
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

	// アイコンの特殊動作。固形ブロックに張り付いている時、そのブロックの外見に偽装する。
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);
		int m = meta & 7;
		ForgeDirection dir = ForgeDirection.getOrientation(m);
		Block block = world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
		int meta2 = world.getBlockMetadata(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);

		if (block != null && block.isNormalCube()) {
			return block.getIcon(side, meta2);
		} else {
			return this.getIcon(side, meta);
		}
	}

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		ForgeDirection dir = ForgeDirection.getOrientation(meta & 7);

		if (dir != ForgeDirection.UP) {
			Block under = world.getBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);

			if (under != null && !under.isAir(world, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ)
					&& under == Blocks.grass) {
				int color = under.colorMultiplier(world, x, y, z);
				return color;
			}
		}

		return super.colorMultiplier(world, x, y, z);
	}

	// @Override
	// @SideOnly(Side.CLIENT)
	// public IIcon getIcon(int par1, int par2)
	// {
	// return par1 == 1 ? this.blockIcon : this.texSide;
	// }

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
		ForgeDirection dir = ForgeDirection.getOrientation(m);

		switch (dir) {
		case UP:// 上付き
			this.setBlockBounds(0.0F, 1.0F - f, 0.0F, 1.0F, 1.0F, 1.0F);
			break;
		case DOWN:// 下付き
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
			break;
		case NORTH:// 北
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
			break;
		case SOUTH:// 南
			this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
			break;
		case WEST:// 西
			this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
			break;
		case EAST:// 東
			this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			break;
		default:
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
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
		this.texSide = par1IIconRegister.registerIcon("defeatedcrow:redgel");
		this.blockIcon = par1IIconRegister.registerIcon("defeatedcrow:redgel");

	}

	/* 以下はブロックの動作 */

	// 右クリック時
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7,
			float par8, float par9) {
		ItemStack item = player.inventory.getCurrentItem();
		int currentMeta = world.getBlockMetadata(x, y, z);
		ForgeDirection dir = ForgeDirection.getOrientation(currentMeta & 7);

		boolean change = false;

		// 誤爆防止用に、赤石ジェルを持っていると動作しないようになっている
		if (!Util.notEmptyItem(item) || item.getItem() != Item.getItemFromBlock(this)) {
			if (currentMeta < 8) {
				world.setBlockMetadataWithNotify(x, y, z, (currentMeta | 8), 2);
				world.playSoundEffect((double) x + 0.5D, (double) y + 0.1D, (double) z + 0.5D, "random.click", 0.3F,
						0.6F);
				change = true;
			} else {
				world.setBlockMetadataWithNotify(x, y, z, (currentMeta & 7), 2);
				world.playSoundEffect((double) x + 0.5D, (double) y + 0.1D, (double) z + 0.5D, "random.click", 0.3F,
						0.5F);
				change = true;
			}
		}

		if (change) {
			this.updatePlate(world, x, y, z, (currentMeta & 8));
			world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			world.notifyBlocksOfNeighborChange(x, y, z, this);
			world.notifyBlocksOfNeighborChange(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, this);
			world.func_147453_f(x, y, z, this);
			world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
			return true;
		}

		return false;
	}

	// ピストンでは押せない
	@Override
	public int getMobilityFlag() {
		return 1;
	}

	// Tick間隔
	@Override
	public int tickRate(World p_149738_1_) {
		return 20;
	}

	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote) {
			int i = world.getBlockMetadata(x, y, z);
			ForgeDirection dir = ForgeDirection.getOrientation(i);

			if (i > 0) {
				this.updatePlate(world, x, y, z, i & 8);
			}
		}
	}

	protected void updatePlate(World world, int x, int y, int z, int str) {
		int i1 = str;
		boolean flag = str > 0;
		boolean flag1 = i1 > 0;
		int meta = world.getBlockMetadata(x, y, z);
		ForgeDirection dir = ForgeDirection.getOrientation(meta);

		if (!flag1 && flag) {
			world.setBlockMetadataWithNotify(x, y, z, (meta & 7), 2);
			world.notifyBlocksOfNeighborChange(x, y, z, this);
			world.notifyBlocksOfNeighborChange(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, this);
			world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			world.playSoundEffect((double) x + 0.5D, (double) y + 0.1D, (double) z + 0.5D, "random.click", 0.3F, 0.5F);
		} else if (flag1 && !flag) {
			world.setBlockMetadataWithNotify(x, y, z, (meta | 8), 2);
			world.notifyBlocksOfNeighborChange(x, y, z, this);
			world.notifyBlocksOfNeighborChange(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, this);
			world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			world.playSoundEffect((double) x + 0.5D, (double) y + 0.1D, (double) z + 0.5D, "random.click", 0.3F, 0.6F);
		}

		if (flag1) {
			world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
		}
	}

	/* 以下、動力関係 */

	@Override
	public boolean canProvidePower() {
		return true;
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side) {
		return this.isProvidingWeakPower(world, x, y, z, side);
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
		int i1 = world.getBlockMetadata(x, y, z);
		ForgeDirection dir = ForgeDirection.getOrientation(i1);
		ForgeDirection check = ForgeDirection.getOrientation(side);

		return (dir.equals(check) || i1 < 8) ? 0 : 15;
	}

}
