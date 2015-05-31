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

public class BlockFilledCup2 extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon itemIIcon;
	@SideOnly(Side.CLIENT)
	private IIcon boxIIcon;
	@SideOnly(Side.CLIENT)
	private IIcon[] contentsIIcon;

	public static final String[] contents = new String[] { "_earlgray", "_tea_milk", "_appletea", "_tea_milk", "_lime",
			"_tomato", "_berry", "_berry_milk", "_grape", "_mint", "_lemon", "_orange", "_soda" };

	public BlockFilledCup2() {
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
		return DCsConfig.useSummerRender ? DCsAppleMilk.modelCupSummer : DCsAppleMilk.modelFilledCup;
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
	}

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
		for (int i = 0; i < 13; ++i) {
			par3List.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par2;
		if (i > 12)
			i = 12;
		if (par1 == 2) {
			return this.boxIIcon;
		} else if (par1 == 1) {
			return this.itemIIcon;
		} else {
			return this.contentsIIcon[i];
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

		this.contentsIIcon = new IIcon[13];
		for (int i = 0; i < 13; ++i) {
			this.blockIcon = par1IIconRegister.registerIcon("defeatedcrow:contents" + contents[i]);
			this.itemIIcon = par1IIconRegister.registerIcon("defeatedcrow:contents" + contents[i]);
			this.contentsIIcon[i] = par1IIconRegister.registerIcon("defeatedcrow:contents" + contents[i]);
		}
	}

}
