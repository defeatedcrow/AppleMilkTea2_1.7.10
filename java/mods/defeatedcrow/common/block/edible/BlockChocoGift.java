package mods.defeatedcrow.common.block.edible;

import java.util.List;
import java.util.Random;

import mods.defeatedcrow.api.events.AMTBlockRightClickEvent;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChocoGift extends Block {

	private static final String[] boxType = new String[] { "", "_heartfelt" };

	@SideOnly(Side.CLIENT)
	private IIcon[] boxTex;
	@SideOnly(Side.CLIENT)
	private IIcon[] boxSideTex;

	public BlockChocoGift() {
		super(Material.circuits);
		this.setHardness(0.2F);
		this.setResistance(0.0F);
		this.setStepSound(Block.soundTypeCloth);
		this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.3F, 0.8F);
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
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par2;
		if (i > 2)
			i = 2;
		if (par1 == 1) {
			return this.boxTex[i];
		} else {
			return this.boxSideTex[i];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(DCsAppleMilk.chocoBlock, 1, 0));
		par3List.add(new ItemStack(DCsAppleMilk.chocoBlock, 1, 1));
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister) {
		this.boxTex = new IIcon[2];
		this.boxSideTex = new IIcon[2];

		for (int i = 0; i < 2; ++i) {
			this.boxTex[i] = par1IIconRegister.registerIcon(Util.getTexturePassNoAlt() + "chocogift" + boxType[i]);
			this.boxSideTex[i] = par1IIconRegister.registerIcon(Util.getTexturePassNoAlt() + "chocogiftside"
					+ boxType[i]);
		}
	}

}
