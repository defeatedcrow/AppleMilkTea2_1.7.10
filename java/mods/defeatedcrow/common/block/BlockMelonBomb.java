package mods.defeatedcrow.common.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.common.entity.EntityMelonBomb;

public class BlockMelonBomb extends Block{
	
	@SideOnly(Side.CLIENT)
    private IIcon itemIcon;
	@SideOnly(Side.CLIENT)
    private IIcon boxIcon;
	
	private final int[] sideX = new int[] {1, -1, 0, 0, 0, 0};
	private final int[] sideY = new int[] {0, 0, 1, -1, 0, 0};
	private final int[] sideZ = new int[] {0, 0, 0, 0, 1, -1};
	
	public BlockMelonBomb ()
	{
		super(Material.wood);
		this.setStepSound(Block.soundTypeStone);
		float f = 0.375F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.75F, 0.5F + f);
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
        int meta = par1World.getBlockMetadata(par2, par3, par4);
        
        if (itemstack == null)
        {
        	if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(this,1)))
        	{
        		par5EntityPlayer.entityDropItem(new ItemStack(this,1), 1.0F);
        	}
    		
    		par1World.setBlockToAir(par2, par3, par4);
    		par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
    		return true;
        }
        else if (itemstack.getItem() == Item.getItemFromBlock(this))
        {
        	if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(this,1)))
        	{
        		par5EntityPlayer.entityDropItem(new ItemStack(this,1), 1.0F);
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
	
	//set entityMelinBomb
	private boolean canMelonStay(World world, int x, int y, int z)
	{
		boolean flag = false;
		for (int i = 0; i < 6 ; i++)
		{
			Material check = world.getBlock(x + this.sideX[i], y + this.sideY[i], z + this.sideZ[i]).getMaterial();
			if (check == Material.water) flag = true;
		}
		
		if (flag)
		{
			return false;
		}
		else
		{
			return world.doesBlockHaveSolidTopSurface(world, x, y - 1, z);
		}
	}
	
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
		if (!par1World.isRemote && !this.canMelonStay(par1World, par2, par3, par4))
        {
            this.setEntityMelon(par1World, par2, par3, par4);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }
	
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
    {
		if (!par1World.isRemote && !this.canMelonStay(par1World, par2, par3, par4))
        {
            this.setEntityMelon(par1World, par2, par3, par4);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }
	
	private void setEntityMelon(World par1World, int par2, int par3, int par4)
	{
		EntityMelonBomb entityMelon = new EntityMelonBomb(par1World, (double)((float)par2 + 0.5F), (double)((float)par3 + 1.0F), (double)((float)par4 + 0.5F));
        entityMelon.rotationYaw = 0.0F;
        
        if (!par1World.isRemote)
        {
            par1World.spawnEntityInWorld(entityMelon);
        }
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
 
	public boolean renderAsNormalBlock() 
	{
		return false;
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
        this.CupBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
    }
	
	public void CupBoundingBox (int par1)
	{
		float f = 0.375F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.75F, 0.5F + f);
	}
	
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    { 
		int i = par2;
		if (par1 == 0 || par1 == 1)
        {
        	return this.boxIcon;
        }
        else
        {
        	return this.itemIcon;
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
		this.blockIcon = par1IconRegister.registerIcon("defeatedcrow:melonbox");
		this.boxIcon = par1IconRegister.registerIcon("defeatedcrow:melonbox_top");
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:melonbox");
	}

}
