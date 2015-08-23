package mods.defeatedcrow.common.block.plants;

import static net.minecraftforge.common.EnumPlantType.Plains;

import java.util.List;
import java.util.Random;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSaplingTea extends Block implements IPlantable {
	@SideOnly(Side.CLIENT)
	private IIcon[] saplingIcon;

	private static final String[] index = new String[] {
			"tea",
			"cassis",
			"camellia" };

	public BlockSaplingTea() {
		super(Material.plants);
		this.setTickRandomly(true);
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
		this.setStepSound(soundTypeGrass);
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
	public int getRenderType() {
		return 1;
	}

	// 当たり判定はなし
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if (!par1World.isRemote) {
			super.updateTick(par1World, par2, par3, par4, par5Random);

			if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0) {
				this.growTree(par1World, par2, par3, par4, par5Random);
			}
		}

		if (!this.canBlockStay(par1World, par2, par3, par4)) {
			this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
			par1World.setBlockToAir(par2, par3, par4);
		}
	}

	public boolean fertilize(World par1World, int par2, int par3, int par4) {
		this.growTree(par1World, par2, par3, par4, par1World.rand);
		return true;
	}

	/**
	 * Attempts to grow a sapling into a tree
	 */
	public void growTree(World par1World, int par2, int par3, int par4, Random par5Random) {
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (meta == 0) {
			par1World.setBlock(par2, par3, par4, DCsAppleMilk.teaTree, 0, 3);
		} else if (meta == 1) {
			par1World.setBlock(par2, par3, par4, DCsAppleMilk.cassisTree, 0, 3);
			if (par1World.isAirBlock(par2, par3 + 1, par4)) {
				par1World.setBlock(par2, par3 + 1, par4, DCsAppleMilk.cassisTree, 0, 3);
			}
		} else {
			par1World.setBlock(par2, par3, par4, DCsAppleMilk.cassisTree, 4, 3);
			if (par1World.isAirBlock(par2, par3 + 1, par4)) {
				par1World.setBlock(par2, par3 + 1, par4, DCsAppleMilk.cassisTree, 4, 3);
			}
		}
	}

	protected boolean canThisPlantGrowOnThisBlockID(Block par1) {
		Block block = par1;
		return par1 == Blocks.grass || par1 == Blocks.dirt || par1 == Blocks.farmland
				|| (block != null && block.getMaterial() == Material.grass);
	}

	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
		Block soil = par1World.getBlock(par2, par3 - 1, par4);
		return (par1World.getFullBlockLightValue(par2, par3, par4) >= 11 || par1World.canBlockSeeTheSky(par2, par3,
				par4)) && this.canThisPlantGrowOnThisBlockID(par1World.getBlock(par2, par3 - 1, par4));
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		return super.canPlaceBlockAt(par1World, par2, par3, par4) && canBlockStay(par1World, par2, par3, par4);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (itemstack == null || par1World.isRemote) {
			return false;
		} else {
			// if ((itemstack.itemID == Item.dyePowder.itemID) && (itemstack.getItemDamage() == 15) &&
			// (par1World.getBlockLightValue(par2, par3, par4) > 11))
			// {
			// if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
			// {
			// par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
			// (ItemStack)null);
			// }
			// this.growTree(par1World, par2, par3, par4, par1World.rand);
			// return true;
			// }
			// else
			// {
			// return false;
			// }
			return false;
		}
	}

	/**
	 * Determines the damage on the item the block drops. Used in cloth and wood.
	 */
	@Override
	public int damageDropped(int par1) {
		return Math.min(par1, 2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int meta = MathHelper.clamp_int(par2, 0, 2);
		return this.saplingIcon[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.saplingIcon = new IIcon[3];
		for (int i = 0; i < 3; ++i) {
			this.saplingIcon[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "sapling_" + index[i]);
		}
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {

		return Plains;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return this;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}
}
