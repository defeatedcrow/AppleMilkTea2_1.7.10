package mods.defeatedcrow.common.block;


import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.src.*;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import mods.defeatedcrow.api.events.AMTBlockRightCrickEvent;
import mods.defeatedcrow.common.*;

public class BlockBowlRack extends Block{	
		
	public BlockBowlRack ()
	{
		super(Material.wood);
		this.setStepSound(Block.soundTypeWood);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
        int currentMeta = par1World.getBlockMetadata(par2, par3, par4);
        Block bottomBlockID = par1World.getBlock(par2, par3 - 1, par4);
        
        AMTBlockRightCrickEvent event = new AMTBlockRightCrickEvent(par1World, par5EntityPlayer, itemstack, par2, par3, par4);
        MinecraftForge.EVENT_BUS.post(event);
        
        if (event.isCanceled())
        {
            return true;
        }
        
        if (itemstack == null)
        {
    		if (currentMeta == 0)
    		{
    			return false;
    		}
    		else
    		{
    			this.getBowl(par5EntityPlayer, currentMeta);
    			par1World.setBlockMetadataWithNotify(par2, par3, par4, (currentMeta - 1), 3);
        		par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
        		return true;
    		}
        }
        else if (itemstack.getItem() == Items.bowl)
        {
        	if (currentMeta >= 3)
        	{
        		return false;
        	}
        	else
        	{
        		if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
                {
            		par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, (ItemStack)null);
                }
            	par1World.setBlockMetadataWithNotify(par2, par3, par4, (currentMeta + 1), 3);
        		par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
        		return true;
        	}
        }
        else
        {
        	return false;
        }
    }
	
	private void getBowl (EntityPlayer player, int meta)
	{
		if (!player.inventory.addItemStackToInventory(new ItemStack(Items.bowl,1)))
    	{
			if (!player.worldObj.isRemote) player.entityDropItem(new ItemStack(Items.bowl,1),1);
    	}
	}
	
	public int damageDropped(int par1)
    {
        return par1;
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
		return DCsAppleMilk.modelRack;
	}
	
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
        this.TeaMakerBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
    }
	
	public void TeaMakerBoundingBox (int par1)
	{
		float f = 0.0F;
		this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, 0.8F, 1.0F - f);
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    { 
		return Blocks.planks.getIcon(1, 0);
    }
	
	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = Blocks.planks.getIcon(1, 0);
	}
	
}
