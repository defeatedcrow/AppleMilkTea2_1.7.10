package mods.defeatedcrow.common.block.container;

import java.util.Random;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.tile.TileWipeBox2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWipeBox2 extends BlockContainer {

	public BlockWipeBox2() {
		super(Material.cloth);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
		this.setStepSound(Block.soundTypeCloth);
		this.setTickRandomly(true);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
		TileWipeBox2 tile = (TileWipeBox2) par1World.getTileEntity(par2, par3, par4);

		if (itemstack == null) {
			if (tile != null) {
				short remain = tile.getRemainShort();

				if (remain == -1) {
					if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.paper, 1, 0))) {
						if (!par1World.isRemote)
							par5EntityPlayer.entityDropItem(new ItemStack(Items.paper, 1, 0), 1);
					}
					par1World.playSoundAtEntity(par5EntityPlayer, "dig.cloth", 1.0F, 1.3F);
				} else if (remain == 0 || remain < -1) {
					if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.paper, 1, 0))) {
						if (!par1World.isRemote)
							par5EntityPlayer.entityDropItem(new ItemStack(Items.paper, 1, 0), 1);
					}
					par1World.setBlockToAir(par2, par3, par4);
					par1World.playSoundAtEntity(par5EntityPlayer, "dig.cloth", 1.0F, 1.3F);
				} else if (remain > 0) {
					if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.paper, 1, 0))) {
						if (!par1World.isRemote)
							par5EntityPlayer.entityDropItem(new ItemStack(Items.paper, 1, 0), 1);
					}
					par1World.playSoundAtEntity(par5EntityPlayer, "dig.cloth", 1.0F, 1.3F);
					tile.setRemainShort((short) (remain - 1));
				}

			} else {

			}
			return true;
		} else if (itemstack.getItem() == Items.paper
				|| itemstack.getItem() == Item.getItemFromBlock(DCsAppleMilk.wipeBox)) {
			if (tile != null) {
				int r = tile.getRemainShort();
				int set = 0;
				int m = itemstack.getItemDamage();

				if (itemstack.getItem() == Items.paper) {
					set = 1;
				} else {
					set = (m == 0) ? 9 : 81;
				}

				set = r + set;

				if (r != -1) {
					if (set > 5000) {
						if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
							par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
									(ItemStack) null);
						}
						par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 1.0F, 1.8F);

						if (DCsConfig.allowInfinityWipes) {
							tile.setRemainShort((short) -1);
						} else {
							tile.setRemainShort((short) 5000);
						}
					} else {
						tile.setRemainShort((short) set);

						if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
							par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
									(ItemStack) null);
						}
						par1World.playSoundAtEntity(par5EntityPlayer, "dig.cloth", 1.0F, 1.3F);
					}
				}
			}
			return true;
		} else {
			return false;
		}
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
		this.setBlockBounds(0.125F, 0.0F, 0.03125F, 1.0F - 0.125F, 0.5625F, 0.96875F);
	}

	@Override
	public int damageDropped(int par1) {
		return 0;
	}

	// drop

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		short l = (short) par6ItemStack.getItemDamage();
		short l2 = 5000;
		if (l >= 5000) {
			l2 = 0;
		} else if (l < 1) {
			l2 = 5000;
		} else {
			l2 = (short) (5000 - l);
		}

		super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLivingBase, par6ItemStack);
		par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
		TileWipeBox2 tile = (TileWipeBox2) par1World.getTileEntity(par2, par3, par4);
		if (tile != null)
			tile.setRemainShort(l2);
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
		TileWipeBox2 tile = (TileWipeBox2) par1World.getTileEntity(par2, par3, par4);

		if (tile != null) {
			short l = tile.getRemainShort();
			int d = 0;
			boolean flag = false;
			if (l < 0) {
				d = 0;
				flag = true;
			} else if (l == 0) {
				flag = false;
			} else if (l > 4999) {
				d = 0;
				flag = true;
			} else {
				d = 5000 - l;
				flag = true;
			}

			if (flag) {
				float f = par1World.rand.nextFloat() * 0.8F + 0.1F;
				float f1 = par1World.rand.nextFloat() * 0.8F + 0.1F;
				float f2 = par1World.rand.nextFloat() * 0.8F + 0.1F;

				ItemStack itemstack = new ItemStack(this, 1, d);
				EntityItem entityitem = new EntityItem(par1World, (double) ((float) par2 + f),
						(double) ((float) par3 + f1), (double) ((float) par4 + f2), itemstack);

				float f3 = 0.05F;
				entityitem.motionX = (double) ((float) par1World.rand.nextGaussian() * f3);
				entityitem.motionY = (double) ((float) par1World.rand.nextGaussian() * f3 + 0.2F);
				entityitem.motionZ = (double) ((float) par1World.rand.nextGaussian() * f3);
				par1World.spawnEntityInWorld(entityitem);
			}

			par1World.func_147453_f(par2, par3, par4, par5);
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	// render

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
		return DCsAppleMilk.modelWipe;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("defeatedcrow:wipe2");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {

		return new TileWipeBox2();
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		TileWipeBox2 l = (TileWipeBox2) world.getTileEntity(x, y, z);
		boolean flag = (l != null && l.getRemainShort() == -1);
		return flag ? 15 : 0;
	}

	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		int l = par1World.getBlockMetadata(par2, par3, par4);
		short i = 0;
		TileWipeBox2 tile = (TileWipeBox2) par1World.getTileEntity(par2, par3, par4);
		if (tile != null)
			i = tile.getRemainShort();

		float r = par5Random.nextFloat();
		double d0 = (double) ((float) par2 + r);
		double d1 = (double) ((float) par3 + 0.5F + r);
		double d2 = (double) ((float) par4 + r);

		if (!DCsConfig.noRenderFoodsSteam && i == -1)
			par1World.spawnParticle("happyVillager", d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}

}
