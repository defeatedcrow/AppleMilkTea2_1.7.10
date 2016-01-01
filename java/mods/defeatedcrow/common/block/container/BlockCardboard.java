package mods.defeatedcrow.common.block.container;

import java.util.List;

import mods.defeatedcrow.common.tile.TileCardBoard;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCardboard extends BlockContainer {

	private static final String[] bagVegi = new String[] {
			"_mint",
			"_cassis",
			"_yuzu",
			"_camellia",
			"_coffee",
			"_bamboo",
			"_tomato",
			"_grape" };

	@SideOnly(Side.CLIENT)
	private IIcon texTop;
	@SideOnly(Side.CLIENT)
	private IIcon texBottom;
	@SideOnly(Side.CLIENT)
	private IIcon texFront;
	@SideOnly(Side.CLIENT)
	private IIcon[] texSide;

	public BlockCardboard() {
		super(Material.wood);
		this.setStepSound(Block.soundTypeWood);
		this.setHardness(0.1F);
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
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par2 & 7;
		boolean flag = par2 > 7;
		if (par1 == 1) {
			return this.texTop;
		} else if (par1 == 0) {
			return this.texBottom;
		} else if (par1 == 2 || par1 == 3) {
			return flag ? this.texFront : this.texSide[i];
		} else {
			return flag ? this.texSide[i] : this.texFront;
		}

	}

	@Override
	public int damageDropped(int par1) {
		return par1 & 7;
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		int meta = par6ItemStack.getItemDamage();
		byte facing = 0;

		if (l == 0) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, meta | 8, 3);
			facing = 0;
		}

		if (l == 1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, meta, 3);
			facing = 1;
		}

		if (l == 2) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, meta | 8, 3);
			facing = 2;
		}

		if (l == 3) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, meta, 3);
			facing = 4;
		}

		TileEntity tileEntity = par1World.getTileEntity(par2, par3, par4);
		if (tileEntity != null && tileEntity instanceof TileCardBoard) {
			((TileCardBoard) tileEntity).setDirectionByte(facing);
			par1World.markBlockForUpdate(par2, par3, par4);
		}
	}

	@Override
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
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "cardboard_S_yuzu");
		this.texFront = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "cardboard_F");
		this.texTop = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "cardboard_T");
		this.texBottom = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "cardboard_B");
		this.texSide = new IIcon[8];

		for (int i = 0; i < 8; ++i) {
			this.texSide[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "cardboard_S" + bagVegi[i]);
		}

	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileCardBoard();
	}

}
