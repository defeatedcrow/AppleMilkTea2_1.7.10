package mods.defeatedcrow.common.block;

import java.util.List;
import java.util.Random;

import mods.defeatedcrow.api.events.AMTBlockRightClickEvent;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.TileChopsticksBox;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChopsticksBox extends BlockContainer {

	public BlockChopsticksBox() {
		super(Material.wood);
		this.setStepSound(Block.soundTypeWood);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
		int currentMeta = par1World.getBlockMetadata(par2, par3, par4);
		Block bottomBlockID = par1World.getBlock(par2, par3 - 1, par4);

		AMTBlockRightClickEvent event = new AMTBlockRightClickEvent(par1World, par5EntityPlayer, itemstack, par2, par3,
				par4);
		MinecraftForge.EVENT_BUS.post(event);

		if (event.isCanceled()) {
			return true;
		}

		if (itemstack == null) {
			if (currentMeta == 0) {
				return false;
			} else {
				this.getBowl(par5EntityPlayer, currentMeta);
				par1World.setBlockMetadataWithNotify(par2, par3, par4, (currentMeta - 1), 3);
				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
				return true;
			}
		} else if (itemstack.getItem() == DCsAppleMilk.chopsticks) {
			if (currentMeta >= 4) {
				return false;
			} else {
				if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
					par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
							(ItemStack) null);
				}
				par1World.setBlockMetadataWithNotify(par2, par3, par4, (currentMeta + 1), 3);
				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
				return true;
			}
		} else {
			return false;
		}
	}

	private void getBowl(EntityPlayer player, int meta) {
		ItemStack ret = null;
		if (player.isSneaking()) {
			ret = new ItemStack(DCsAppleMilk.chopsticks, 1, 1);
		} else {
			ret = new ItemStack(DCsAppleMilk.chopsticks, 1, 0);
		}

		if (ret != null && !player.worldObj.isRemote) {
			EntityItem entity = new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, ret);
			player.worldObj.spawnEntityInWorld(entity);
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
		return DCsAppleMilk.modelChopsticks;
	}

	// tile
	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileChopsticksBox();
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
		float f = 0.3F;
		this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, 0.8F, 1.0F - f);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return Blocks.planks.getIcon(1, 4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(this, 1, 0));
		par3List.add(new ItemStack(this, 1, 4));
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
}
