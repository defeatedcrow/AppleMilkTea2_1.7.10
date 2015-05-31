package mods.defeatedcrow.common.block.container;

import java.util.List;
import java.util.Random;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMushBox extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon itemIcon;
	@SideOnly(Side.CLIENT)
	private IIcon boxIcon;

	public BlockMushBox() {
		super(Material.wood);
		this.setStepSound(Block.soundTypeStone);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		if (itemstack == null) {
			ItemStack ret = new ItemStack(this, 1, meta);
			if (!par1World.isRemote) {
				EntityItem entity = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY,
						par5EntityPlayer.posZ, ret);
				par1World.spawnEntityInWorld(entity);
			}

			par1World.setBlockToAir(par2, par3, par4);
			par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
			return true;
		} else if (itemstack.getItem() == Item.getItemFromBlock(DCsAppleMilk.mushroomBox)) {
			ItemStack ret = new ItemStack(this, 1, meta);
			if (!par1World.isRemote) {
				EntityItem entity = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY,
						par5EntityPlayer.posZ, ret);
				par1World.spawnEntityInWorld(entity);
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
		return DCsAppleMilk.modelKinoko;
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
		float f = 0.3F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.575F, 0.5F + f);
	}

	// @Override
	// public TileEntity createNewTileEntity(World world, int a) {
	// return DCsAppleMilk.noUseCupDirection ? null : new TileKinoko();
	// }

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < 2; ++i) {
			par3List.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = Blocks.red_mushroom.getIcon(0, 0);
	}

}
