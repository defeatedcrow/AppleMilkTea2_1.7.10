package mods.defeatedcrow.common.block.edible;

import java.util.List;
import java.util.Random;

import mods.defeatedcrow.api.events.AMTBlockRightClickEvent;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.tile.TileCupHandle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

public class BlockFilledCup extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon itemIcon;
	@SideOnly(Side.CLIENT)
	private IIcon boxIcon;
	@SideOnly(Side.CLIENT)
	private IIcon[] contentsIcon;

	public static final String[] contents = new String[] { "_water", "_milk", "_tea", "_tea_milk", "_greentea",
			"_greentea_milk", "_cocoa", "_cocoa_milk", "_juice", "_juice_milk", "_lemon", "_lemon", "_cocoa",
			"_cocoa_milk" };

	public BlockFilledCup() {
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
		return (DCsConfig.useSummerRender) ? DCsAppleMilk.modelCupSummer : DCsAppleMilk.modelFilledCup;
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
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

	// set direction as tileentity
	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileCupHandle();
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		// this.setDefaultDirection(world, x, y, z);
	}

	// private void setDefaultDirection(World world, int x, int y, int z)
	// {
	// if (!DCsConfig.noUseCupDirection)
	// {
	// TileCupHandle tileCupHandle = (TileCupHandle)world.getTileEntity(x, y, z);
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
	// var9 = 4;
	// }
	//
	// tileCupHandle.setDirectionByte(var9);
	// }
	// }
	// }

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		int playerFacing = MathHelper.floor_double((double) ((par5EntityLivingBase.rotationYaw * 4F) / 360F) + 0.5D) & 3;

		byte facing = 0;
		if (playerFacing == 0) {
			facing = 0;
		}
		if (playerFacing == 1) {
			facing = 1;
		}
		if (playerFacing == 2) {
			facing = 2;
		}
		if (playerFacing == 3) {
			facing = 4;
		}

		TileEntity tileEntity = par1World.getTileEntity(par2, par3, par4);
		if (tileEntity != null && tileEntity instanceof TileCupHandle) {
			((TileCupHandle) tileEntity).setDirectionByte(facing);
			// System.out.println("[AppleMilk]Now fasing " + facing);
			par1World.markBlockForUpdate(par2, par3, par4);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < 14; ++i) {
			par3List.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par2;
		if (i > 13)
			i = 13;
		if (par1 == 2) {
			return this.boxIcon;
		} else if (par1 == 1) {
			return this.itemIcon;
		} else {
			return i == 0 ? Blocks.water.getIcon(0, 0) : this.contentsIcon[i];
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister) {
		this.boxIcon = par1IIconRegister.registerIcon("defeatedcrow:porcelain");

		this.contentsIcon = new IIcon[14];
		for (int i = 0; i < 14; ++i) {
			this.blockIcon = par1IIconRegister.registerIcon("defeatedcrow:contents" + contents[i]);
			this.itemIcon = par1IIconRegister.registerIcon("defeatedcrow:contents" + contents[i]);
			this.contentsIcon[i] = par1IIconRegister.registerIcon("defeatedcrow:contents" + contents[i]);
		}
	}

}
