package mods.defeatedcrow.common.block;

import mods.defeatedcrow.common.AMTLogger;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import static net.minecraftforge.common.util.ForgeDirection.*;

public class ItemWoodPanel extends ItemBlock {

	public ItemWoodPanel(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);

	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
			float hitX, float hitY, float hitZ, int metadata) {
		ForgeDirection dir = ForgeDirection.getOrientation(side).getOpposite();
		int oX = dir.offsetX;
		int oY = dir.offsetY;
		int oZ = dir.offsetZ;
		int facing = MathHelper.floor_double((double) ((player.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		Block target = world.getBlock(x + oX, y + oY, z + oZ);
		int targetMeta = world.getBlockMetadata(x + oX, y + oY, z + oZ);

		int nextMeta = 0;

		if (target == field_150939_a && !player.isSneaking()) {
			nextMeta = targetMeta;
		} else if (dir != UP && dir != DOWN) {
			switch (side) {
			case 2:
				nextMeta = 0;
				break;
			case 3:
				nextMeta = 1;
				break;
			case 4:
				nextMeta = 3;
				break;
			case 5:
				nextMeta = 2;
				break;
			default:
				nextMeta = 0;
				break;
			}
		} else {
			switch (facing) {
			case 0:
				nextMeta = 0;
				break;
			case 1:
				nextMeta = 2;
				break;
			case 2:
				nextMeta = 1;
				break;
			case 3:
				nextMeta = 3;
				break;
			default:
				nextMeta = 0;
				break;
			}
		}

		if (!world.setBlock(x, y, z, field_150939_a, nextMeta, 3)) {
			return false;
		}

		if (world.getBlock(x, y, z) == field_150939_a) {
			field_150939_a.onBlockPlacedBy(world, x, y, z, player, stack);
			field_150939_a.onPostBlockPlaced(world, x, y, z, nextMeta);
		}

		AMTLogger.debugInfo("player facing :" + facing);
		AMTLogger.debugInfo("current meta :" + nextMeta);
		return true;
	}

}
