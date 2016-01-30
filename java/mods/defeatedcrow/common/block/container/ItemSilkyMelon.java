package mods.defeatedcrow.common.block.container;

import java.util.List;

import mods.defeatedcrow.api.ICompressedItem;
import mods.defeatedcrow.common.entity.EntitySilkyMelon;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemSilkyMelon extends ItemBlock implements ICompressedItem {

	public ItemSilkyMelon(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);

	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack,
	 * world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		float f = 1.0F;
		float f1 = par3EntityPlayer.prevRotationPitch
				+ (par3EntityPlayer.rotationPitch - par3EntityPlayer.prevRotationPitch) * f;
		float f2 = par3EntityPlayer.prevRotationYaw + (par3EntityPlayer.rotationYaw - par3EntityPlayer.prevRotationYaw)
				* f;
		double d0 = par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * f;
		double d1 = par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * f + 1.62D
				- par3EntityPlayer.yOffset;
		double d2 = par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = 5.0D;
		Vec3 vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
		MovingObjectPosition movingobjectposition = par2World.rayTraceBlocks(vec3, vec31, true);

		if (movingobjectposition == null) {
			return par1ItemStack;
		} else {
			Vec3 vec32 = par3EntityPlayer.getLook(f);
			boolean flag = false;
			float f9 = 1.0F;
			List list = par2World.getEntitiesWithinAABBExcludingEntity(par3EntityPlayer, par3EntityPlayer.boundingBox
					.addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3).expand(f9, f9, f9));
			int i;

			for (i = 0; i < list.size(); ++i) {
				Entity entity = (Entity) list.get(i);

				if (entity.canBeCollidedWith()) {
					float f10 = entity.getCollisionBorderSize();
					AxisAlignedBB axisalignedbb = entity.boundingBox.expand(f10, f10, f10);

					if (axisalignedbb.isVecInside(vec3)) {
						flag = true;
					}
				}
			}

			if (flag) {
				return par1ItemStack;
			} else {
				if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
					i = movingobjectposition.blockX;
					int j = movingobjectposition.blockY;
					int k = movingobjectposition.blockZ;

					if (par2World.getBlock(i, j, k) == Blocks.snow) {
						--j;
					}

					EntitySilkyMelon entityMelon = new EntitySilkyMelon(par2World, i + 0.5F, j + 0.5F, k + 0.5F);
					entityMelon.rotationYaw = ((MathHelper
							.floor_double(par3EntityPlayer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3) - 1) * 90;

					if (!par2World.getCollidingBoundingBoxes(entityMelon,
							entityMelon.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty()) {
						return par1ItemStack;
					}

					if (!par2World.isRemote) {
						par2World.spawnEntityInWorld(entityMelon);
					}

					if (!par3EntityPlayer.capabilities.isCreativeMode) {
						--par1ItemStack.stackSize;
					}
				}

				return par1ItemStack;
			}
		}
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public ItemStack getDisassembledItem(ItemStack cont) {
		return new ItemStack(Blocks.melon_block, 9, 0);
	}

}
