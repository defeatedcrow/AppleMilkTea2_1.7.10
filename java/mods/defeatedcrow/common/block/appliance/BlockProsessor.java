package mods.defeatedcrow.common.block.appliance;

import java.util.Random;

import mods.defeatedcrow.client.particle.EntityBlinkFX;
import mods.defeatedcrow.client.particle.ParticleTex;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.DCsConfig;
import mods.defeatedcrow.common.tile.appliance.TileProsessor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockProsessor extends BlockContainer {
	
	protected Random rand = new Random();
	
	public BlockProsessor()
	{
		super(Material.ground);
		this.setHardness(2.0F);
		this.setResistance(2.0F);
		this.setTickRandomly(true);
	}
	
	//外見の設定
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }
	
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }
	
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        this.thisBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
    }
	
	public void thisBoundingBox (int par1)
	{
		float f = 0.125F;
		this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, 1.0F, 1.0F - f);
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
 
	public boolean renderAsNormalBlock() 
	{
		return false;
	}
	
	@Override
	public int getRenderType()
	{
		return DCsAppleMilk.modelProsessor;
	}
	
	//中身の設定
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
		
		ItemStack item = par5EntityPlayer.inventory.getCurrentItem();
		TileProsessor tile = (TileProsessor)par1World.getTileEntity(par2, par3, par4);
		if (tile != null)
		{
//			if (item == null)
//			{
//				int charge = tile.getChargeAmount();
//				boolean active = tile.isActive();
//				par5EntityPlayer.addChatComponentMessage(new ChatComponentText("current charge amount : " + charge));
//				par5EntityPlayer.addChatComponentMessage(new ChatComponentText("Active : " + active));
//			}
//			else
//			{
				if (par1World.isRemote)
				{
					return true;
				}
				else
				{
					par5EntityPlayer.openGui(DCsAppleMilk.instance, DCsAppleMilk.instance.guiProsessor, par1World, par2, par3, par4);
					return true;
				}
//			}
		}
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		short charge = 0;
		
		if (par6ItemStack.getItem() == Item.getItemFromBlock(this))
		{
			if (par6ItemStack.hasTagCompound() && par6ItemStack.getTagCompound().hasKey("charge"))
			{
				NBTTagCompound tag = par6ItemStack.getTagCompound();
				charge = tag.getShort("charge");
			}
		}
		
		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		if (tile != null && tile instanceof TileProsessor)
		{
			((TileProsessor)tile).setChargeAmount(charge);
			par1World.markBlockForUpdate(par2, par3, par4);
		}
	}
	

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
	{
		TileProsessor tileentity = (TileProsessor) par1World.getTileEntity(par2, par3, par4);
	 
		if (tileentity != null)
		{
			int charge = tileentity.getChargeAmount();
			
			ItemStack block = new ItemStack(this, 1, 0);
			
			float a = this.rand.nextFloat() * 0.8F + 0.1F;
			float a1 = this.rand.nextFloat() * 0.8F + 0.1F;
			float a2 = this.rand.nextFloat() * 0.8F + 0.1F;
			EntityItem drop = new EntityItem(par1World, (double)((float)par2 + a), (double)((float)par3 + a1), (double)((float)par4 + a2), block);
			
			if (charge > 0)
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setShort("charge", (short) charge);
				drop.getEntityItem().setTagCompound(tag);
			}
			
			float a3 = 0.05F;
			drop.motionX = (double)((float)this.rand.nextGaussian() * a3);
			drop.motionY = (double)((float)this.rand.nextGaussian() * a3 + 0.2F);
			drop.motionZ = (double)((float)this.rand.nextGaussian() * a3);
			par1World.spawnEntityInWorld(drop);
			
			for (int j1 = 0; j1 < tileentity.getSizeInventory(); ++j1)
			{
				ItemStack itemstack = tileentity.getStackInSlot(j1);
				
				if (itemstack != null)
				{
					float f = this.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
	 
					while (itemstack.stackSize > 0)
					{
						int k1 = this.rand.nextInt(21) + 10;
	 
						if (k1 > itemstack.stackSize)
						{
							k1 = itemstack.stackSize;
						}
	 
						itemstack.stackSize -= k1;
						EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));
	 
						if (itemstack.hasTagCompound())
						{
							entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
						}
	 
						float f3 = 0.05F;
						entityitem.motionX = (double)((float)this.rand.nextGaussian() * f3);
						entityitem.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double)((float)this.rand.nextGaussian() * f3);
						par1World.spawnEntityInWorld(entityitem);
					}
				}
			}
	 
			par1World.func_147453_f(par2, par3, par4, par5);
		}
	 
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}
	 
	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileProsessor();
	}
		
	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune)
	{
		return null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("defeatedcrow:porcelain");
	}

}
