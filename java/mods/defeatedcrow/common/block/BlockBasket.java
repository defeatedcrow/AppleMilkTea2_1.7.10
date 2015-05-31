package mods.defeatedcrow.common.block;

import java.util.List;
import java.util.Random;

import mods.defeatedcrow.api.events.AMTBlockRightClickEvent;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.TileBread;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
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

public class BlockBasket extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon[] basketTex;
	@SideOnly(Side.CLIENT)
	private IIcon[] basketSideTex;
	@SideOnly(Side.CLIENT)
	private IIcon[] basketTopTex;
	@SideOnly(Side.CLIENT)
	private IIcon[] breadTex;

	public BlockBasket() {
		super(Material.ground);
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

		AMTBlockRightClickEvent event = new AMTBlockRightClickEvent(par1World, par5EntityPlayer, itemstack, x, y, z);
		MinecraftForge.EVENT_BUS.post(event);

		if (event.isCanceled()) {
			return true;
		}

		if (itemstack == null) {
			if (currentMeta <= 0) {
				return false;
			} else {
				if (currentMeta < 6) {
					this.getBowl(par1World, par5EntityPlayer, currentMeta);
					par1World.setBlockMetadataWithNotify(x, y, z, (currentMeta - 1), 3);
					par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
					return true;
				} else {
					this.getBottle(par1World, par5EntityPlayer, currentMeta);
					if (currentMeta == 6) {
						par1World.setBlockToAir(x, y, z);
					} else {
						par1World.setBlockMetadataWithNotify(x, y, z, (currentMeta - 1), 3);
					}
					par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
					return true;
				}
			}
		} else if (itemstack.getItem() == Items.bread) {
			if (currentMeta >= 5) {
				return false;
			} else {
				if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
					par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
							(ItemStack) null);
				}
				par1World.setBlockMetadataWithNotify(x, y, z, (currentMeta + 1), 3);
				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
				return true;
			}
		} else if (itemstack.getItem() == Item.getItemFromBlock(DCsAppleMilk.emptyBottle)
				&& itemstack.getItemDamage() == 0) {
			if (currentMeta < 6 || currentMeta > 13) {
				return false;
			} else {
				if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
					par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
							(ItemStack) null);
				}
				par1World.setBlockMetadataWithNotify(x, y, z, (currentMeta + 1), 3);
				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
				return true;
			}
		} else {
			return false;
		}
	}

	private void getBowl(World world, EntityPlayer player, int meta) {
		if (!player.inventory.addItemStackToInventory(new ItemStack(Items.bread, 1))) {
			if (!world.isRemote)
				player.entityDropItem(new ItemStack(Items.bread, 1), 1.0F);
		}
	}

	private void getBottle(World world, EntityPlayer player, int meta) {
		if (!player.inventory.addItemStackToInventory(new ItemStack(DCsAppleMilk.emptyBottle, 1, 0))) {
			if (!world.isRemote)
				player.entityDropItem(new ItemStack(DCsAppleMilk.emptyBottle, 1, 0), 1.0F);
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
		return DCsAppleMilk.modelBasket;
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
		if (par1 < 6) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.6F, 1.0F);
		} else {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
		}

	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		int playerFacing = MathHelper.floor_double((double) ((par5EntityLivingBase.rotationYaw * 4F) / 360F) + 0.5D) & 3;

		boolean tall = false;

		if (par5EntityLivingBase != null && par5EntityLivingBase instanceof EntityPlayer) {
			tall = par5EntityLivingBase.isSneaking();
		}

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
		if (tileEntity != null && tileEntity instanceof TileBread) {
			((TileBread) tileEntity).setDirectionByte(facing);
			((TileBread) tileEntity).setTall(tall);
			if (!par1World.isRemote) {
				byte b = (byte) par1World.rand.nextInt(3);
				((TileBread) tileEntity).setType(b);
			}
			par1World.markBlockForUpdate(par2, par3, par4);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileBread();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		if (par2 < 6) {
			if (par1 == 0)
				return this.basketTex[0];
			if (par1 == 1)
				return this.basketTopTex[0];
			if (par1 == 2)
				return this.basketSideTex[0];
			if (par1 == 3)
				return this.breadTex[0];
			if (par1 == 4)
				return this.breadTex[1];
			else
				return Blocks.planks.getBlockTextureFromSide(0);
		} else {
			if (par1 == 0)
				return this.basketTex[1];
			if (par1 == 1)
				return this.basketTopTex[1];
			else
				return this.basketSideTex[1];
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(this, 1, 0));
		par3List.add(new ItemStack(this, 1, 5));
		par3List.add(new ItemStack(this, 1, 14));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.basketTex = new IIcon[2];
		this.basketSideTex = new IIcon[2];
		this.basketTopTex = new IIcon[2];
		this.breadTex = new IIcon[2];
		this.breadTex[0] = par1IconRegister.registerIcon("defeatedcrow:bread_S");
		this.breadTex[1] = par1IconRegister.registerIcon("defeatedcrow:bread_T");

		for (int i = 0; i < 2; ++i) {
			this.basketTex[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "basket_B" + i);
			this.basketSideTex[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "basket_S" + i);
			this.basketTopTex[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "basket_T" + i);
		}
	}

}
