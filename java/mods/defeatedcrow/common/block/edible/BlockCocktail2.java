package mods.defeatedcrow.common.block.edible;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import mods.defeatedcrow.api.events.AMTBlockRightCrickEvent;
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.common.tile.TileCocktail;

public class BlockCocktail2 extends BlockContainer{
	
	//魔晄割り、パナシェ、スプリッツァー、スクリュードライバー、ゴッドファーザー、ホットエッグノック、アレキサンダー、ズーム、アマレットミルクティー、スノー・サローノ
	//ロング、ワイン、ワイン、ロング、ロング、ロング、ショート、ショート、ロング、ワイン
	public static final String[] contents = new String[] {"_soda", "_juice", "_lemon", "_orange", "_tea", "_maple", "_maple", "_juice_milk", "_tea_milk", "_tea"};
	
	@SideOnly(Side.CLIENT)
    private IIcon boxTex;
	@SideOnly(Side.CLIENT)
    private IIcon[] contentsTex;
	
	
	public BlockCocktail2 ()
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
        
        AMTBlockRightCrickEvent event = new AMTBlockRightCrickEvent(par1World, par5EntityPlayer, itemstack, par2, par3, par4);
        MinecraftForge.EVENT_BUS.post(event);
        
        if (event.isCanceled())
        {
            return true;
        }
        
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
		return DCsAppleMilk.modelCocktail;
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
		int i = Math.min(par2, 9);
		if (par1 == 1)
		{
			return this.contentsTex[i];
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
		for(int i = 0; i < 10; ++i)
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
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.boxTex = par1IIconRegister.registerIcon("defeatedcrow:blueglass");
		this.contentsTex = new IIcon[10];
        for (int i = 0; i < 10; ++i)
        {
        	this.contentsTex[i] = par1IIconRegister.registerIcon("defeatedcrow:contents" + contents[i]);
        }
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileCocktail();
	}
	
}
