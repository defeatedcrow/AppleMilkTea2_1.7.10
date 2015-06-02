package mods.defeatedcrow.common.block.container;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.common.tile.TileWipeBox;
import mods.defeatedcrow.common.tile.TileWipeBox2;
import mods.defeatedcrow.handler.Util;

public class BlockWipeBox extends BlockContainer {

	private static final String[] boxType = new String[] { "_B", "_T", "_S1", "_S2", "_C", "_C" };

	@SideOnly(Side.CLIENT)
	private IIcon[] KimTex;
	@SideOnly(Side.CLIENT)
	private IIcon boxTex;

	public BlockWipeBox() {
		super(Material.cloth);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
		this.setStepSound(Block.soundTypeCloth);
		this.setTickRandomly(true);
	}

	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
		int currentMeta = par1World.getBlockMetadata(par2, par3, par4);
		TileWipeBox tile = (TileWipeBox) par1World.getTileEntity(par2, par3, par4);

		if (itemstack == null) {
			if (tile != null) {
				if (currentMeta == 0) {
					par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 3);
					tile.setRemainByte((byte) 8);
					par1World.playSoundAtEntity(par5EntityPlayer, "dig.cloth", 1.0F, 1.3F);
				} else if (currentMeta == 1) {
					par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 3);
					tile.setRemainByte((byte) 80);
					par1World.playSoundAtEntity(par5EntityPlayer, "dig.cloth", 1.0F, 1.3F);
				} else {
					byte remain = tile.getRemainByte();

					if (remain < 1) {
						if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.paper, 1, 0))) {
							if (!par1World.isRemote)
								par5EntityPlayer.entityDropItem(new ItemStack(Items.paper, 1, 0), 1);
						}
						par1World.setBlockToAir(par2, par3, par4);
						par1World.playSoundAtEntity(par5EntityPlayer, "dig.cloth", 1.0F, 1.3F);
					} else if (remain > 0) {
						if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.paper, 1, 0))) {
							if (!par1World.isRemote)
								par5EntityPlayer.entityDropItem(new ItemStack(Items.paper, 1), 1);
						}
						par1World.playSoundAtEntity(par5EntityPlayer, "dig.cloth", 1.0F, 1.3F);
						tile.setRemainByte((byte) (remain - 1));
					}
				}

			} else {

			}
			return true;
		} else if (itemstack.getItem() == Items.paper
				|| (itemstack.getItem() == Item.getItemFromBlock(DCsAppleMilk.wipeBox))) {
			if (tile != null && (currentMeta == 1 || currentMeta == 3)) {
				int r = tile.getRemainByte();

				if (itemstack.getItem() == Items.paper) {
					r++;
				} else if (itemstack.getItem() == Item.getItemFromBlock(DCsAppleMilk.wipeBox)) {
					r = (itemstack.getItemDamage() == 0) ? r + 9 : r + 81;
				}

				if (r > 127) {
					if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
						par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
								(ItemStack) null);
					}
					par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 1.0F, 1.8F);
					tile.setRemainByte((byte) 0);
					par1World.setBlock(par2, par3, par4, DCsAppleMilk.wipeBox2, 0, 3);
					TileWipeBox2 tile2 = (TileWipeBox2) par1World.getTileEntity(par2, par3, par4);
					tile2.setRemainShort((short) r);
				} else {
					tile.setRemainByte((byte) r);

					if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
						par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
								(ItemStack) null);
					}
					par1World.playSoundAtEntity(par5EntityPlayer, "dig.cloth", 1.0F, 1.3F);
				}
			}
			return true;
		} else {
			return false;
		}
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

	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		this.thisBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	public void thisBoundingBox(int par1) {
		float f = 0.1875F;
		this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, 0.5F, 1.0F - f);
	}

	public int damageDropped(int par1) {
		return (par1 & 1);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par1;
		if ((par2 & 1) == 1) {
			return this.KimTex[i];
		} else {
			return this.boxTex;
		}
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return DCsAppleMilk.modelWipe;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return metadata > 1 ? Items.paper : Item.getItemFromBlock(this);
	}

	public int quantityDropped(Random random) {
		return 1;
	}

	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
		TileWipeBox tile = (TileWipeBox) par1World.getTileEntity(par2, par3, par4);

		if (tile != null && par6 > 1) {
			short l = tile.getRemainByte();
			if (l >= 0) {
				float f = par1World.rand.nextFloat() * 0.8F + 0.1F;
				float f1 = par1World.rand.nextFloat() * 0.8F + 0.1F;
				float f2 = par1World.rand.nextFloat() * 0.8F + 0.1F;

				int boxCount = l / 9;
				int paperCount = l % 9;

				ItemStack box = new ItemStack(this, boxCount, 0);
				ItemStack paper = new ItemStack(Items.paper, paperCount, 0);
				EntityItem entitybox = new EntityItem(par1World, (double) ((float) par2 + f),
						(double) ((float) par3 + f1), (double) ((float) par4 + f2), box);
				EntityItem entitypaper = new EntityItem(par1World, (double) ((float) par2 + f),
						(double) ((float) par3 + f1), (double) ((float) par4 + f2), paper);

				float f3 = 0.05F;
				entitybox.motionX = (double) ((float) par1World.rand.nextGaussian() * f3);
				entitybox.motionY = (double) ((float) par1World.rand.nextGaussian() * f3 + 0.2F);
				entitybox.motionZ = (double) ((float) par1World.rand.nextGaussian() * f3);
				par1World.spawnEntityInWorld(entitybox);

				entitypaper.motionX = (double) ((float) par1World.rand.nextGaussian() * f3);
				entitypaper.motionY = (double) ((float) par1World.rand.nextGaussian() * f3 + 0.2F);
				entitypaper.motionZ = (double) ((float) par1World.rand.nextGaussian() * f3);
				par1World.spawnEntityInWorld(entitypaper);
			}

			par1World.func_147453_f(par2, par3, par4, par5);
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.boxTex = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "basket_B0");
		this.KimTex = new IIcon[6];

		for (int i = 0; i < 6; ++i) {
			this.KimTex[i] = par1IconRegister.registerIcon("defeatedcrow:wipes" + boxType[i]);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {

		return new TileWipeBox();
	}

}
