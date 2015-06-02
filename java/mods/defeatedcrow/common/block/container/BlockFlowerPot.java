package mods.defeatedcrow.common.block.container;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.TileFlowerPot;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockFlowerPot extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon[] flowerIcon;

	public BlockFlowerPot() {
		super(Material.ground);
		this.setStepSound(Block.soundTypeGrass);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return DCsAppleMilk.modelFlowerPot;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}

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
		int meta = par1 & 3;

		if (meta == 0) {
			this.setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
		} else if (meta == 1) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
		} else if (meta == 2) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
		} else if (meta == 3) {
			this.setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		} else {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		int l = MathHelper.floor_double((double) (par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int meta = par6ItemStack.getItemDamage();

		if (l == 0) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, meta, 3);
		}

		if (l == 1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, meta + 1, 3);
		}

		if (l == 2) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, meta + 2, 3);
		}

		if (l == 3) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, meta + 3, 3);
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	public int damageDropped(int par1) {
		return par1 & 4;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileFlowerPot();
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 4));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		if (par1 == 0) {
			return this.blockIcon;
		} else {
			return par2 > 3 ? this.flowerIcon[1] : this.flowerIcon[0];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("defeatedcrow:basket_B0");

		this.flowerIcon = new IIcon[2];

		this.flowerIcon[0] = par1IconRegister.registerIcon("defeatedcrow:flowerpot_red");
		this.flowerIcon[1] = par1IconRegister.registerIcon("defeatedcrow:flowerpot_yellow");
	}

}
