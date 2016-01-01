package mods.defeatedcrow.common.block.container;

import java.util.List;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHedge extends Block {

	private static final String[] leaves = new String[] {
			"_boxwood_n",
			"_podocarp",
			"_photinia",
			"_snakegourd",
			"_osmanthus",
			"_boxwood_g" };

	@SideOnly(Side.CLIENT)
	private IIcon[] baseTex;
	@SideOnly(Side.CLIENT)
	private IIcon[] leafTex;
	@SideOnly(Side.CLIENT)
	private IIcon tamazusaN;
	@SideOnly(Side.CLIENT)
	private IIcon tamazusaC;

	public BlockHedge() {
		super(Material.wood);
		this.setStepSound(Block.soundTypeGrass);
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
		if (i > 5)
			i = 5;
		if (par1 == 0) {
			return this.leafTex[i];
		} else if (par1 == 1) {
			return this.baseTex[0];
		} else if (par1 == 2) {
			return this.baseTex[1];
		} else if (par1 == 3) {
			return this.baseTex[2];
		} else if (par1 == 4) {
			return this.tamazusaC;
		} else if (par1 == 5) {
			return this.tamazusaN;
		} else {
			return this.baseTex[0];
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
			par1World.setBlockMetadataWithNotify(par2, par3, par4, meta, 3);
		}

		if (l == 1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, meta | 8, 3);
		}

		if (l == 2) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, meta, 3);
		}

		if (l == 3) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, meta | 8, 3);
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
	}

	@Override
	public int getRenderType() {
		return DCsAppleMilk.modelHedge;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "hedge/hedge_base1");
		this.tamazusaN = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "hedge/hedge_snakegourd_f");
		this.tamazusaC = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "hedge/hedge_snakegourd_c");
		this.baseTex = new IIcon[3];
		this.leafTex = new IIcon[6];

		for (int i = 0; i < 3; ++i) {
			this.baseTex[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "hedge/hedge_base" + (i + 1));
		}

		for (int i = 0; i < 6; ++i) {
			this.leafTex[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "hedge/hedge" + leaves[i]);
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
	public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4) {
		boolean flag = world.getBlock(par2, par3 + 1, par4) == this;
		this.thisBoundingBox(world.getBlockMetadata(par2, par3, par4), flag);
	}

	public void thisBoundingBox(int par1, boolean flag) {
		float f = 0.0625F;
		int meta = par1 & 7;
		HedgeType type = getType(meta);
		float height = 1.0F;
		if (type == HedgeType.LOW && !flag)
			height = 0.5F;
		boolean side = par1 > 7;

		if (side) {
			this.setBlockBounds(0.0F, 0.0F, f * 3, 1.0F, height, f * 13);
		} else {
			this.setBlockBounds(f * 3, 0.0F, 0.0F, f * 13, height, 1.0F);
		}
	}

	public HedgeType getType(int meta) {
		switch (meta) {
		case 2:
		case 4:
			return HedgeType.FENCE;
		case 3:
			return HedgeType.LATTICE;
		case 0:
		case 5:
			return HedgeType.LOW;
		default:
			return HedgeType.NORMAL;
		}
	}

	public static enum HedgeType {

		NORMAL,

		LATTICE,

		FENCE,

		LOW

	}

}
