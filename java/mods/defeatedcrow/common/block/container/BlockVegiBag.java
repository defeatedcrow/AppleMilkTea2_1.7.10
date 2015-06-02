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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.common.tile.TileVegiBag;
import mods.defeatedcrow.handler.Util;

public class BlockVegiBag extends BlockContainer {

	private static final String[] bagVegi = new String[] { "_leaves", "_potato", "_carrot", "_pumpkin", "_seed",
			"_reed", "_cactus", "_cocoa", "_wart", "_sugar" };
	public static final String[] bagTexType = new String[] { "LeavesBag_T", "PotatoBag_T", "CarrotBag_T",
			"PumpkinBag_T", "SeedBag_T", "ReedBag_T", "CactusBag_T", "CocoaBag_T", "WartBag_T", "SugarBag_T" };

	@SideOnly(Side.CLIENT)
	private IIcon[] wheatBagTop;
	@SideOnly(Side.CLIENT)
	private IIcon wheatBagSide;

	public BlockVegiBag() {
		super(Material.wood);
		this.setStepSound(Block.soundTypeWood);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par2;
		if (i > 9)
			i = 9;
		if (par1 == 1) {
			return this.wheatBagTop[i];
		} else if (par1 == 0) {
			return this.blockIcon;
		} else {
			return this.wheatBagSide;
		}

	}

	public int damageDropped(int par1) {
		return par1;
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
		par3List.add(new ItemStack(par1, 1, 3));
		par3List.add(new ItemStack(par1, 1, 4));
		par3List.add(new ItemStack(par1, 1, 5));
		par3List.add(new ItemStack(par1, 1, 6));
		par3List.add(new ItemStack(par1, 1, 7));
		par3List.add(new ItemStack(par1, 1, 8));
		par3List.add(new ItemStack(par1, 1, 9));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "bag_wheat_B");
		this.wheatBagSide = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "bag_wheat_S");
		this.wheatBagTop = new IIcon[10];

		for (int i = 0; i < 10; ++i) {
			this.wheatBagTop[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "bag" + bagVegi[i] + "_T");
		}

	}

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
		if (tileEntity != null && tileEntity instanceof TileVegiBag) {
			((TileVegiBag) tileEntity).setDirectionByte(facing);
			par1World.markBlockForUpdate(par2, par3, par4);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileVegiBag();
	}

}
