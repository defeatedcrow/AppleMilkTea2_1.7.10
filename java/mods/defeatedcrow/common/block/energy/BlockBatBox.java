package mods.defeatedcrow.common.block.energy;

import java.util.Random;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.energy.TileChargerBase;
import mods.defeatedcrow.common.tile.energy.TileChargerDevice;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBatBox extends BlockContainer {

	protected Random rand = new Random();

	public BlockBatBox() {
		super(Material.ground);
		this.setHardness(2.0F);
		this.setResistance(2.0F);
		this.setTickRandomly(true);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {

		ItemStack item = par5EntityPlayer.inventory.getCurrentItem();
		TileChargerBase tile = (TileChargerBase) par1World.getTileEntity(par2, par3, par4);
		if (tile != null) {
			if (par1World.isRemote) {
				return true;
			} else {
				par5EntityPlayer.openGui(DCsAppleMilk.instance, DCsAppleMilk.instance.guiBatBox, par1World, par2, par3,
						par4);
				return true;
			}
		}
		return true;
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
		return DCsAppleMilk.modelBatBox;
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		int playerFacing = MathHelper.floor_double((par5EntityLivingBase.rotationYaw * 4F) / 360F + 0.5D) & 3;

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
			facing = 3;
		}

		par1World.setBlockMetadataWithNotify(par2, par3, par4, facing, 3);

		int charge = 0;

		if (par6ItemStack.getItem() == Item.getItemFromBlock(this)) {
			if (par6ItemStack.hasTagCompound() && par6ItemStack.getTagCompound().hasKey("charge")) {
				NBTTagCompound tag = par6ItemStack.getTagCompound();
				charge = tag.getInteger("charge");
			}
		}

		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		if (tile != null && tile instanceof TileChargerBase) {
			((TileChargerBase) tile).setChargeAmount(charge);
			par1World.markBlockForUpdate(par2, par3, par4);
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return null;
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		TileChargerBase tileentity = null;

		if (tile != null && tile instanceof TileChargerBase) {
			tileentity = (TileChargerBase) tile;
			int charge = tileentity.getChargeAmount();

			ItemStack block = new ItemStack(this, 1, 0);

			float a = this.rand.nextFloat() * 0.8F + 0.1F;
			float a1 = this.rand.nextFloat() * 0.8F + 0.1F;
			float a2 = this.rand.nextFloat() * 0.8F + 0.1F;
			EntityItem drop = new EntityItem(par1World, par2 + a, par3 + a1, par4 + a2, block);

			if (charge > 0) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("charge", charge);
				drop.getEntityItem().setTagCompound(tag);
			}

			float a3 = 0.05F;
			drop.motionX = (float) this.rand.nextGaussian() * a3;
			drop.motionY = (float) this.rand.nextGaussian() * a3 + 0.2F;
			drop.motionZ = (float) this.rand.nextGaussian() * a3;
			par1World.spawnEntityInWorld(drop);

			for (int j1 = 0; j1 < tileentity.getSizeInventory(); ++j1) {
				ItemStack itemstack = tileentity.getStackInSlot(j1);

				if (itemstack != null) {
					float f = this.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0) {
						int k1 = this.rand.nextInt(21) + 10;

						if (k1 > itemstack.stackSize) {
							k1 = itemstack.stackSize;
						}

						itemstack.stackSize -= k1;
						EntityItem entityitem = new EntityItem(par1World, par2 + f, par3 + f1, par4 + f2,
								new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound(
									(NBTTagCompound) itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (float) this.rand.nextGaussian() * f3;
						entityitem.motionY = (float) this.rand.nextGaussian() * f3 + 0.2F;
						entityitem.motionZ = (float) this.rand.nextGaussian() * f3;
						par1World.spawnEntityInWorld(entityitem);
					}
				}
			}

			par1World.func_147453_f(par2, par3, par4, par5);
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileChargerDevice();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("defeatedcrow:charger_F");
	}

}
