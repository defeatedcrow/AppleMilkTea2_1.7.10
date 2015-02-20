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
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import mods.defeatedcrow.api.events.AMTBlockRightCrickEvent;
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.common.config.DCsConfigCocktail;
import mods.defeatedcrow.common.tile.TileCocktail;
import mods.defeatedcrow.common.tile.TileCocktailSP;

public class BlockCocktailSP extends BlockContainer{
	
	@SideOnly(Side.CLIENT)
    private IIcon boxTex;
	@SideOnly(Side.CLIENT)
    private IIcon contentsTex;
	
	public BlockCocktailSP ()
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
		return DCsAppleMilk.modelDial;
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
		if (par1 == 1)
		{
			return this.contentsTex;
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
		for(int i = 0; i < 3; ++i)
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
		this.contentsTex = par1IIconRegister.registerIcon("defeatedcrow:contents_cocktailbase");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileCocktailSP();
	}
	
	/*以下はレンダー用の項目*/
	
	public static float[] getColorPropertySP (int meta)
	{
		int i = MathHelper.clamp_int(meta, 0, 2);
		if (i == 0)
		{
			if (DCsConfigCocktail.colorf1.length >= 4) return DCsConfigCocktail.colorf1;
		}
		else if (i == 1)
		{
			if (DCsConfigCocktail.colorf2.length >= 4) return DCsConfigCocktail.colorf2;
		}
		else if (i == 2)
		{
			if (DCsConfigCocktail.colorf3.length >= 4) return DCsConfigCocktail.colorf3;
		}
		
		return new float[]{0.0F, 0.0F, 0.0F, 0.5F};
	}
	
	public static ModelType getGlassType(int meta)
	{
		int i = MathHelper.clamp_int(meta, 0, 2);
		int t = DCsConfigCocktail.type[i];
		if (t == 0) return ModelType.SHORT;
		else if (t == 1) return ModelType.LONG;
		else return ModelType.WINE;
	}
	
	public static DecorationType getDecoType(int meta)
	{
		int i = MathHelper.clamp_int(meta, 0, 2);
		int d = DCsConfigCocktail.deco[i];
		if (d == 0) return DecorationType.NONE;
		else if (d == 1) return DecorationType.LEMON;
		else if (d == 2) return DecorationType.LIME;
		else if (d == 3) return DecorationType.PINE;
		else return DecorationType.APPLE;
	}
	
	public enum ModelType
	{
		SHORT,
		LONG,
		WINE,
		NONE;
	}
	
	public enum DecorationType
	{
		LEMON,
		LIME,
		PINE,
		APPLE,
		NONE;
	}
	
}
