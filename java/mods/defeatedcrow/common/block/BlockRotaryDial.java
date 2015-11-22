package mods.defeatedcrow.common.block;

import java.util.Random;

import mods.defeatedcrow.api.events.AMTBlockRightClickEvent;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.TileRotaryDial;
import mods.defeatedcrow.plugin.mce.OpenShopGui;
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
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRotaryDial extends BlockContainer {

	public BlockRotaryDial() {
		super(Material.glass);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
		this.setTickRandomly(true);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();

		AMTBlockRightClickEvent event = new AMTBlockRightClickEvent(par1World, par5EntityPlayer, itemstack, par2, par3,
				par4);
		MinecraftForge.EVENT_BUS.post(event);

		if (event.isCanceled()) {
			return true;
		}

		if (par1World.isRemote) {
			return true;
		} else {
			if (DCsAppleMilk.SuccessLoadEconomy) {
				if (DCsAppleMilk.OldEconomy)
					OpenShopGui.openOldShopGui(par5EntityPlayer, par1World, par2, par3, par4);
				else
					OpenShopGui.openShopGui(par5EntityPlayer, par1World, par2, par3, par4);
			}
		}

		return true;
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
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
		float f = 0.125F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.8F, 1.0F);
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
	// int var5 = world.getBlockId(x, y, z - 1);
	// int var6 = world.getBlockId(x, y, z + 1);
	// int var7 = world.getBlockId(x - 1, y, z);
	// int var8 = world.getBlockId(x + 1, y, z);
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
		int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (par5EntityLivingBase.isSneaking()) {
			l = (l | 4);
		}
		par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 3);
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("defeatedcrow:rotarydial_block");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileRotaryDial();
	}

}
