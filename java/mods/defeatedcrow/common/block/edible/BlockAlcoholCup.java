package mods.defeatedcrow.common.block.edible;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraft.src.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.common.tile.TileAlcoholCup;

public class BlockAlcoholCup extends BlockContainer{
	
	//shothu, sake, beer, wine, rum, gin, vodka, whiskey, apple, tea, cassis, plum, shothu, brandy, amaretto
	public static final String[] contents = new String[] {"_water", "_juice", "_tomato", "_juice", "_water", "_water", "_juice", "_lemon", "_tea", "_grape", "_juice", "_water", "_tea", "_tea"};
	
	@SideOnly(Side.CLIENT)
    private IIcon boxTex;
	@SideOnly(Side.CLIENT)
    private IIcon[] contentsTex;
	@SideOnly(Side.CLIENT)
    private IIcon bubbleTex;
	
	
	public BlockAlcoholCup ()
	{
		super(Material.glass);
		this.setStepSound(Block.soundTypeGlass);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
		this.setLightLevel(0.4F);
		this.setTickRandomly(true);
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
        int currentMeta = par1World.getBlockMetadata(par2, par3, par4);
        Block bottomBlockID = par1World.getBlock(par2, par3 - 1, par4);
        
        if (itemstack == null)
        {
        	if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(this,1,currentMeta)))
        	{
        		par5EntityPlayer.entityDropItem(new ItemStack(this,1,currentMeta), 1);
        	}
    		
    		par1World.setBlockToAir(par2, par3, par4);
    		par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
    		return true;
        }
        else if (itemstack.getItem() == Item.getItemFromBlock(this))
        {
        	if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(this,1,currentMeta)))
        	{
        		par5EntityPlayer.entityDropItem(new ItemStack(this,1,currentMeta), 1);
        	}
    		
    		par1World.setBlockToAir(par2, par3, par4);
    		par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
    		return true;
        }
        else
        {
        	return false;
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
		return DCsAppleMilk.modelAlcoholCup;
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
        this.thisBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
    }
	
	public void thisBoundingBox (int par1)
	{
		float f = 0.25F;
		this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, 0.7F, 1.0F - f);
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    { 
		int i = Math.min(par2, 13);
		if (par1 == 1)
		{
			if (par2 == 0 || par2 == 12 || par2 == 4 || par2 == 5)
			{
				return Blocks.water.getBlockTextureFromSide(1);
			}
			else
			{
				return this.contentsTex[par2];
			}
			
		}
		else if (par1 == 0)
		{
			return this.bubbleTex;
		}
		else
		{
			return this.boxTex;
		}
		
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		for(int i = 0; i < 14; ++i)
		{
			par3List.add(new ItemStack(this, 1, i));
		}
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
		this.boxTex = par1IconRegister.registerIcon("defeatedcrow:blueglass");
		this.bubbleTex = par1IconRegister.registerIcon("defeatedcrow:contents_sugar");
		this.contentsTex = new IIcon[14];
        for (int i = 0; i < 14; ++i)
        {
        	this.contentsTex[i] = par1IconRegister.registerIcon("defeatedcrow:contents" + contents[i]);
        }
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileAlcoholCup();
	}
	
}
