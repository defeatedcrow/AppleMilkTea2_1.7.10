package mods.defeatedcrow.common.block.appliance;

import java.util.Random;

import mods.defeatedcrow.api.events.AMTBlockRightClickEvent;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.tile.TileCupHandle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEmptyCup extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon itemIIcon;
	@SideOnly(Side.CLIENT)
	private IIcon boxIIcon;
	@SideOnly(Side.CLIENT)
	private IIcon summerboxIIcon;

	public BlockEmptyCup() {
		super(Material.circuits);
		this.setStepSound(Block.soundTypeStone);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		AMTBlockRightClickEvent event = new AMTBlockRightClickEvent(par1World, par5EntityPlayer, itemstack, par2, par3,
				par4);
		MinecraftForge.EVENT_BUS.post(event);

		if (event.isCanceled()) {
			return true;
		}

		if (itemstack == null) {
			if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(DCsAppleMilk.emptyCup, 1))) {
				if (!par1World.isRemote)
					par5EntityPlayer.entityDropItem(new ItemStack(DCsAppleMilk.emptyCup, 1), 1);
			}

			par1World.setBlockToAir(par2, par3, par4);
			par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
			return true;
		} else if (itemstack.getItem() == Item.getItemFromBlock(this)) {
			if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(DCsAppleMilk.emptyCup, 1))) {
				if (!par1World.isRemote)
					par5EntityPlayer.entityDropItem(new ItemStack(DCsAppleMilk.emptyCup, 1), 1);
			}

			par1World.setBlockToAir(par2, par3, par4);
			par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
			return true;
		} else {
			return false;
		}
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
		return (DCsConfig.useSummerRender) ? DCsAppleMilk.modelCupSummer : DCsAppleMilk.modelCup;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return (DCsConfig.useSummerRender) ? new TileCupHandle() : null;
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
		this.CupBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	public void CupBoundingBox(int par1) {
		float f = 0.25F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.5F, 0.5F + f);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		// this.setDefaultDirection(world, x, y, z);
	}

	// private void setDefaultDirection(World world, int x, int y, int z)
	// {
	//
	// if (!world.isRemote)
	// {
	// Block var5 = world.getBlock(x, y, z - 1);
	// Block var6 = world.getBlock(x, y, z + 1);
	// Block var7 = world.getBlock(x - 1, y, z);
	// Block var8 = world.getBlock(x + 1, y, z);
	// byte var9 = 0;
	//
	// if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])
	// {
	// var9 = 0;
	// }
	//
	// if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
	// {
	// var9 = 1;
	// }
	//
	// if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])
	// {
	// var9 = 2;
	// }
	//
	// if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])
	// {
	// var9 = 3;
	// }
	//
	// world.setBlockMetadataWithNotify(x, y, z, var9, 3);
	// }
	// }

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		int l = MathHelper.floor_double((double) (par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
		}

		if (l == 1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
		}

		if (l == 2) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 3);
		}

		if (l == 3) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 3);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par2;
		if (par1 == 0) {
			return this.boxIIcon;
		} else if (par1 == 1) {
			return this.summerboxIIcon;
		} else {
			return this.itemIIcon;
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister) {
		this.boxIIcon = par1IIconRegister.registerIcon("defeatedcrow:porcelain");
		this.itemIIcon = par1IIconRegister.registerIcon("defeatedcrow:cup_empty");
		this.summerboxIIcon = par1IIconRegister.registerIcon("defeatedcrow:summercup_side");
	}

}
