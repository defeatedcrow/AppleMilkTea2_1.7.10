package mods.defeatedcrow.common.block;

import java.util.Random;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.TileCrowDoll;
import mods.defeatedcrow.handler.CoordListRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCrowDoll extends BlockContainer {

	public BlockCrowDoll() {
		super(Material.clay);
		this.setHardness(0.5F);
		this.setResistance(30.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setLightLevel(0.2F);
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean canPlaceTorchOnTop(World par1World, int par2, int par3, int par4) {
		return true;
	}

	@Override
	public int getRenderType() {
		return DCsAppleMilk.modelDial;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		this.thisBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	public void thisBoundingBox(int par1) {
		float f = 0.0625F;
		this.setBlockBounds(4 * f, 0.0F, 4 * f, 12 * f, 12 * f, 12 * f);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("defeatedcrow:crowdoll");
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return side != ForgeDirection.DOWN;
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (par5EntityLivingBase.isSneaking()) {
			l = (l | 4);
		}
		par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 3);
		super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLivingBase, par6ItemStack);
	}

	// Cood登録と解除
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		if (!world.isRemote) {
			int cX = x >> 4;
			int cZ = z >> 4;
			if (CoordListRegister.setCood(world, x, y, z, cX, cZ)) {
				AMTLogger.debugInfo("add coord");
			}
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int i) {
		if (!world.isRemote) {
			int cX = x >> 4;
			int cZ = z >> 4;
			if (CoordListRegister.deleteCood(world, x, y, z, cX, cZ)) {
				AMTLogger.debugInfo("remove coord");
			}
		}
		world.func_147453_f(x, y, z, block);
		super.breakBlock(world, x, y, z, block, i);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileCrowDoll();
	}

	// クリックでブルブルする
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		if (world.isRemote)
			return;
		if (player != null) {
			TileEntity tile = world.getTileEntity(x, y, z);
			TileCrowDoll doll = null;
			if (tile != null && tile instanceof TileCrowDoll) {
				doll = (TileCrowDoll) tile;
			}
			if (doll != null) {
				doll.range = 3.0D;
				world.markBlockForUpdate(x, y, z);
			}
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
	}

	// おまけ
	@Override
	public float getEnchantPowerBonus(World world, int x, int y, int z) {
		return 5;
	}

}
