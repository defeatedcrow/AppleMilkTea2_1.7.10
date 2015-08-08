package mods.defeatedcrow.common.block.container;

import java.util.List;

import mods.defeatedcrow.api.events.AMTBlockRightClickEvent;
import mods.defeatedcrow.common.tile.TileContainerBase;
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
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockContainerBase extends BlockContainer {

	@SideOnly(Side.CLIENT)
	protected IIcon bottomIcon;
	@SideOnly(Side.CLIENT)
	protected IIcon sideIcon;
	@SideOnly(Side.CLIENT)
	protected IIcon topIcon;

	public BlockContainerBase() {
		super(Material.ground);
		this.setStepSound(Block.soundTypeWood);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6,
			float par7, float par8, float par9) {
		ItemStack item = par5EntityPlayer.inventory.getCurrentItem();
		int meta = par1World.getBlockMetadata(x, y, z);
		boolean side = false;
		int rem = meta & 7;

		AMTBlockRightClickEvent event = new AMTBlockRightClickEvent(par1World, par5EntityPlayer, item, x, y, z);
		MinecraftForge.EVENT_BUS.post(event);

		if (event.isCanceled()) {
			return true;
		}

		if (item == null) {
			this.getItem(par1World, par5EntityPlayer, meta);
			if (rem == 0) {
				par1World.setBlockToAir(x, y, z);
			} else {
				par1World.setBlockMetadataWithNotify(x, y, z, (meta - 1), 3);
			}
			par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
			return true;
		} else if (this.isSameItem(item)) {
			if (rem == 7) {
				return false;
			} else {
				if (!par5EntityPlayer.capabilities.isCreativeMode && --item.stackSize <= 0) {
					par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
							(ItemStack) null);
				}
				par1World.setBlockMetadataWithNotify(x, y, z, (meta + 1), 3);
				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
				return true;
			}
		} else {
			return false;
		}
	}

	protected void getItem(World world, EntityPlayer player, int meta) {
		if (!world.isRemote) {
			double x = player.posX;
			double y = player.posY + 0.2D;
			double z = player.posZ;
			if (returnItem() != null && returnItem().getItem() != null) {
				EntityItem drop = new EntityItem(world, x, y, z, returnItem().copy());
				world.spawnEntityInWorld(drop);
			}
		}
	}

	protected boolean isSameItem(ItemStack item) {
		if (this.returnItem() == null || item == null)
			return false;
		else {
			return this.returnItem().getItem() == item.getItem()
					&& this.returnItem().getItemDamage() == item.getItemDamage();
		}
	}

	public abstract ItemStack returnItem();

	@Override
	public int damageDropped(int par1) {
		return par1 & 7;
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
	public abstract int getRenderType();

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		int meta = par1World.getBlockMetadata(par2, par3, par4) & 7;
		int playerFacing = MathHelper.floor_double((double) ((par5EntityLivingBase.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		int side = 0;
		if (playerFacing == 1 || playerFacing == 3) {
			side = 8;
		}
		par1World.setBlockMetadataWithNotify(par2, par3, par4, meta | side, 3);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		if (par2 > 0) {
			return par1 == 1 ? this.topIcon : (par1 == 0 ? this.bottomIcon : this.sideIcon);
		} else {
			return this.blockIcon;
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.bottomIcon = par1IconRegister.registerIcon("defeatedcrow:x32/basket_B1");
		this.sideIcon = par1IconRegister.registerIcon("defeatedcrow:x32/basket_S1");
		this.topIcon = par1IconRegister.registerIcon("defeatedcrow:x32/basket_T1");
		this.blockIcon = par1IconRegister.registerIcon("defeatedcrow:x32/basket_B1");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(this, 1, 7));
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileContainerBase();
	}

}
