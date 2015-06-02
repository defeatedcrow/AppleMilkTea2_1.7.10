package mods.defeatedcrow.common.block.energy;

import java.util.Random;

import mods.defeatedcrow.api.energy.IBattery;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.energy.TileGelBat;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGelBat extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon texTop;
	@SideOnly(Side.CLIENT)
	private IIcon texSide;

	protected Random rand = new Random();

	public BlockGelBat() {
		super(Material.ground);
		this.setStepSound(Block.soundTypePiston);
		this.setHardness(1.0F);
		this.setResistance(2.0F);
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return null;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return par1 == 1 ? this.texTop : this.texSide;
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
		return DCsAppleMilk.modelGelBat;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister) {
		this.blockIcon = par1IIconRegister.registerIcon("defeatedcrow:porcelain");
		this.texTop = par1IIconRegister.registerIcon("defeatedcrow:porcelain");
		this.texSide = par1IIconRegister.registerIcon("defeatedcrow:redgel");

	}

	// 以下は設置、破壊時処理
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		int charge = 0;

		if (par6ItemStack.getItem() == Item.getItemFromBlock(this)) {
			if (par6ItemStack.hasTagCompound() && par6ItemStack.getTagCompound().hasKey("charge")) {
				NBTTagCompound tag = par6ItemStack.getTagCompound();
				charge = tag.getInteger("charge");
			}
		}

		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		if (tile != null && tile instanceof TileGelBat) {
			((TileGelBat) tile).setChargeAmount(charge);
			par1World.markBlockForUpdate(par2, par3, par4);
		}
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
		TileGelBat tileentity = (TileGelBat) par1World.getTileEntity(par2, par3, par4);

		if (tileentity != null) {
			int charge = tileentity.getChargeAmount();

			ItemStack block = new ItemStack(this, 1, 0);

			float a = this.rand.nextFloat() * 0.8F + 0.1F;
			float a1 = this.rand.nextFloat() * 0.8F + 0.1F;
			float a2 = this.rand.nextFloat() * 0.8F + 0.1F;
			EntityItem drop = new EntityItem(par1World, (double) ((float) par2 + a), (double) ((float) par3 + a1),
					(double) ((float) par4 + a2), block);

			if (charge > 0) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("charge", (int) charge);
				drop.getEntityItem().setTagCompound(tag);
			}

			float a3 = 0.05F;
			drop.motionX = (double) ((float) this.rand.nextGaussian() * a3);
			drop.motionY = (double) ((float) this.rand.nextGaussian() * a3 + 0.2F);
			drop.motionZ = (double) ((float) this.rand.nextGaussian() * a3);
			par1World.spawnEntityInWorld(drop);

			par1World.func_147453_f(par2, par3, par4, par5);
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileGelBat();
	}

}
