package mods.defeatedcrow.common.block.edible;

import java.util.List;
import java.util.Random;

import mods.defeatedcrow.api.events.AMTBlockRightClickEvent;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfigCocktail;
import mods.defeatedcrow.common.tile.TileCocktailSP;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
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

public class BlockCocktailSP extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon boxTex;
	@SideOnly(Side.CLIENT)
	private IIcon contentsTex;

	public BlockCocktailSP() {
		super(Material.glass);
		this.setStepSound(Block.soundTypeGlass);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
		this.setLightLevel(0.4F);
		this.setTickRandomly(true);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
		int currentMeta = par1World.getBlockMetadata(par2, par3, par4);
		Block bottomBlockID = par1World.getBlock(par2, par3 - 1, par4);

		AMTBlockRightClickEvent event = new AMTBlockRightClickEvent(par1World, par5EntityPlayer, itemstack, par2, par3,
				par4);
		MinecraftForge.EVENT_BUS.post(event);

		if (event.isCanceled()) {
			return true;
		}

		if (itemstack == null) {
			ItemStack ret = new ItemStack(this, 1, currentMeta);
			if (!par1World.isRemote) {
				EntityItem entity = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY,
						par5EntityPlayer.posZ, ret);
				par1World.spawnEntityInWorld(entity);
			}

			par1World.setBlockToAir(par2, par3, par4);
			par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
			return true;
		} else if (itemstack.getItem() == Item.getItemFromBlock(this)) {
			ItemStack ret = new ItemStack(this, 1, currentMeta);
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
		return DCsAppleMilk.modelDial;
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
		float f = 0.25F;
		this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, 0.7F, 1.0F - f);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		if (par1 == 1) {
			return this.contentsTex;
		} else {
			return this.boxTex;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < 3; ++i) {
			par3List.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister) {
		this.boxTex = par1IIconRegister.registerIcon("defeatedcrow:blueglass");
		this.contentsTex = par1IIconRegister.registerIcon("defeatedcrow:contents_cocktailbase");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileCocktailSP();
	}

	/* 以下はレンダー用の項目 */

	public static float[] getColorPropertySP(int meta) {
		int i = MathHelper.clamp_int(meta, 0, 2);
		if (i == 0) {
			if (DCsConfigCocktail.colorf1.length >= 4)
				return DCsConfigCocktail.colorf1;
		} else if (i == 1) {
			if (DCsConfigCocktail.colorf2.length >= 4)
				return DCsConfigCocktail.colorf2;
		} else if (i == 2) {
			if (DCsConfigCocktail.colorf3.length >= 4)
				return DCsConfigCocktail.colorf3;
		}

		return new float[] { 0.0F, 0.0F, 0.0F, 0.5F };
	}

	public static ModelType getGlassType(int meta) {
		int i = MathHelper.clamp_int(meta, 0, 2);
		int t = DCsConfigCocktail.type[i];
		if (t == 0)
			return ModelType.SHORT;
		else if (t == 1)
			return ModelType.LONG;
		else
			return ModelType.WINE;
	}

	public static DecorationType getDecoType(int meta) {
		int i = MathHelper.clamp_int(meta, 0, 2);
		int d = DCsConfigCocktail.deco[i];
		if (d == 0)
			return DecorationType.NONE;
		else if (d == 1)
			return DecorationType.LEMON;
		else if (d == 2)
			return DecorationType.LIME;
		else if (d == 3)
			return DecorationType.PINE;
		else
			return DecorationType.APPLE;
	}

	public enum ModelType {
		SHORT, LONG, WINE, NONE;
	}

	public enum DecorationType {
		LEMON, LIME, PINE, APPLE, NONE;
	}

}
