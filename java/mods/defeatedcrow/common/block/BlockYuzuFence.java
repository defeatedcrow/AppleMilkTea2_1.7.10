package mods.defeatedcrow.common.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.AchievementRegister;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import static net.minecraftforge.common.util.ForgeDirection.*;

public class BlockYuzuFence extends Block {

	public BlockYuzuFence() {
		super(Material.wood);
		this.setStepSound(Block.soundTypeWood);
		this.setHardness(0.2F);
		this.setResistance(3.0F);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int damageDropped(int par1) {
		return 0;
	}

	@Override
	public int getRenderType() {
		return DCsAppleMilk.modelYuzuFence;
	}

	// 当たり判定の設定

	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity) {
		// 隣接チェック
		boolean north = this.canConnectBlock(world, x, y, z - 1, NORTH);
		boolean south = this.canConnectBlock(world, x, y, z + 1, SOUTH);
		boolean west = this.canConnectBlock(world, x - 1, y, z, WEST);
		boolean east = this.canConnectBlock(world, x + 1, y, z, EAST);
		boolean up = world.isAirBlock(x, y + 1, z);
		float f1 = 0.375F;
		float f2 = 0.625F;

		// 中央の柱と柱上部は常に当たり判定がある
		AxisAlignedBB aabb1 = AxisAlignedBB.getBoundingBox((double) (x + f1), (double) y, (double) (z + f1),
				(double) (x + f2), (double) (y + 1.2), (double) (z + f2));
		if (aabb1 != null && aabb1.intersectsWith(aabb)) {
			list.add(aabb1);
		}

		if (up) {
			AxisAlignedBB aabbU = AxisAlignedBB.getBoundingBox((double) (x + 0.25F), (double) (y + 1.4),
					(double) (z + 0.25F), (double) (x + 0.75F), (double) (y + 1.5), (double) (z + 0.75F));
			if (aabbU != null && aabbU.intersectsWith(aabb)) {
				list.add(aabbU);
			}
		}

		if (north) {
			AxisAlignedBB aabbN = AxisAlignedBB.getBoundingBox((double) (x + f1), (double) (y), (double) (z + 0.0F),
					(double) (x + f2), (double) (y + 1.2), (double) (z + f1));
			if (aabbN != null && aabbN.intersectsWith(aabb)) {
				list.add(aabbN);
			}
		}

		if (south) {
			AxisAlignedBB aabbS = AxisAlignedBB.getBoundingBox((double) (x + f1), (double) (y), (double) (z + f2),
					(double) (x + f2), (double) (y + 1.2), (double) (z + 1));
			if (aabbS != null && aabbS.intersectsWith(aabb)) {
				list.add(aabbS);
			}
		}

		if (west) {
			AxisAlignedBB aabbW = AxisAlignedBB.getBoundingBox((double) (x), (double) (y), (double) (z + f1),
					(double) (x + f2), (double) (y + 1.2), (double) (z + f2));
			if (aabbW != null && aabbW.intersectsWith(aabb)) {
				list.add(aabbW);
			}
		}

		if (east) {
			AxisAlignedBB aabbE = AxisAlignedBB.getBoundingBox((double) (x + f2), (double) (y), (double) (z + f1),
					(double) (x + 1), (double) (y + 1.2), (double) (z + f2));
			if (aabbE != null && aabbE.intersectsWith(aabb)) {
				list.add(aabbE);
			}
		}
	}

	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		float f1 = 0.375F;
		float f2 = 0.625F;
		float f3 = 0.375F;
		float f4 = 0.625F;
		boolean north = this.canConnectBlock(world, x, y, z - 1, NORTH);
		boolean south = this.canConnectBlock(world, x, y, z + 1, SOUTH);
		boolean west = this.canConnectBlock(world, x - 1, y, z, WEST);
		boolean east = this.canConnectBlock(world, x + 1, y, z, EAST);

		if (north)
			f3 = 0.0F;
		if (south)
			f4 = 1.0F;
		if (west)
			f1 = 0.0F;
		if (east)
			f2 = 1.0F;

		this.setBlockBounds(f1, 0.0F, f3, f2, 1.0F, f4);
	}

	public boolean canConnectBlock(IBlockAccess world, int x, int y, int z, ForgeDirection dir) {
		Block block = world.getBlock(x, y, z);
		if (block != null && !block.isAir(world, x, y, z)) {
			return block == this || world.isSideSolid(x, y, z, dir.getOpposite(), false);
		}
		return false;
	}

	// 接触判定
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		if (par5Entity instanceof EntityLiving && !(par5Entity instanceof EntityPlayer)
				&& !(par5Entity instanceof EntityTameable) && !(par5Entity instanceof EntityVillager)
				&& !(par5Entity instanceof EntityHorse)) {
			EntityLiving living = (EntityLiving) par5Entity;
			living.attackEntityFrom(DamageSource.cactus, 2.0F);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "yuzufence");
	}

}
