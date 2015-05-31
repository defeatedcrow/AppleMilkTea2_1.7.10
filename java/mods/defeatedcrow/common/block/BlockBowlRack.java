package mods.defeatedcrow.common.block;

import java.util.Random;

import mods.defeatedcrow.api.events.AMTBlockRightClickEvent;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.TileBowlRack;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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

public class BlockBowlRack extends BlockContainer {

	public BlockBowlRack() {
		super(Material.wood);
		this.setStepSound(Block.soundTypeWood);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6,
			float par7, float par8, float par9) {
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
		int currentMeta = par1World.getBlockMetadata(x, y, z);
		Block bottomBlockID = par1World.getBlock(x, y - 1, z);
		TileEntity tile2 = par1World.getTileEntity(x, y, z);
		TileBowlRack tile = null;
		if (tile2 instanceof TileBowlRack) {
			tile = (TileBowlRack) tile2;
		} else {
			return false;
		}

		int remain = tile.getRemainByte();

		AMTBlockRightClickEvent event = new AMTBlockRightClickEvent(par1World, par5EntityPlayer, itemstack, x, y, z);
		MinecraftForge.EVENT_BUS.post(event);

		if (event.isCanceled()) {
			return true;
		}

		if (itemstack == null) {
			if (remain == 0) {
				return false;
			} else {
				this.getBowl(par5EntityPlayer, currentMeta);
				tile.setRemainByte((byte) (remain - 1));
				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
				par1World.markBlockForUpdate(x, y, z);
				return true;
			}
		} else if (itemstack.getItem() == Items.bowl) {
			if (remain > 3) {
				return false;
			} else {
				if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
					par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
							(ItemStack) null);
				}
				tile.setRemainByte((byte) (remain + 1));
				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
				par1World.markBlockForUpdate(x, y, z);
				return true;
			}
		} else {
			return false;
		}
	}

	private void getBowl(EntityPlayer player, int meta) {
		if (!player.inventory.addItemStackToInventory(new ItemStack(Items.bowl, 1))) {
			if (!player.worldObj.isRemote)
				player.entityDropItem(new ItemStack(Items.bowl, 1), 1);
		}
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
		return DCsAppleMilk.modelRack;
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
		this.TeaMakerBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	public void TeaMakerBoundingBox(int par1) {
		float f = 0.0F;
		this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, 0.8F, 1.0F - f);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return Blocks.planks.getIcon(1, 0);
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = Blocks.planks.getIcon(1, 0);
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		int l = MathHelper.floor_double((double) (par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		// if (par5EntityLivingBase.isSneaking()) {
		// l = (l | 4);
		// }

		TileEntity tile2 = par1World.getTileEntity(par2, par3, par4);
		if (tile2 instanceof TileBowlRack) {
			TileBowlRack tile = (TileBowlRack) tile2;
			tile.setRemainByte((byte) 0);
		}

		par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 3);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileBowlRack();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {

		TileEntity tile = world.getTileEntity(x, y, z);
		if (!world.isRemote && tile != null && tile instanceof TileBowlRack) {
			TileBowlRack rack = (TileBowlRack) tile;
			int amo = rack.getRemainByte();

			if (amo > 0) {
				ItemStack drop = new ItemStack(Items.bowl, amo, 0);

				float f = world.rand.nextFloat() * 0.8F + 0.1F;
				float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
				float f2 = world.rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1),
						(double) ((float) z + f2), drop);

				float f3 = 0.05F;
				entityitem.motionX = (double) ((float) world.rand.nextGaussian() * f3);
				entityitem.motionY = (double) ((float) world.rand.nextGaussian() * f3 + 0.2F);
				entityitem.motionZ = (double) ((float) world.rand.nextGaussian() * f3);
				world.spawnEntityInWorld(entityitem);
			}
		}
	}
}
