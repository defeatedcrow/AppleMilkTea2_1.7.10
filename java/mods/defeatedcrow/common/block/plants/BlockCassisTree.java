package mods.defeatedcrow.common.block.plants;

import static net.minecraftforge.common.EnumPlantType.Plains;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.handler.Util;

public class BlockCassisTree extends Block implements IPlantable{
	
	@SideOnly(Side.CLIENT)
    private IIcon leafIIcon;//内側
	@SideOnly(Side.CLIENT)
    private IIcon[] newleafIIcon;//外側
	@SideOnly(Side.CLIENT)
    private IIcon logIIcon;
	
	public BlockCassisTree ()
	{
		super(Material.wood);
		this.setStepSound(Block.soundTypeGrass);
		this.setTickRandomly(true);
	}
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (!par1World.isRemote && par1World.rand.nextInt(8) == 0)
		{
			int meta = par1World.getBlockMetadata(par2, par3, par4);
			int growth = meta & 3;
			
			/*4段階成長する。成長し切ると実を採取できる。*/
			if (growth < 3 && (par1World.getBlockLightValue(par2, par3, par4) > 11))
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, (meta + 1), 3);
			}
		}
	}
	
	public boolean fertilize(World par1World, int par2, int par3, int par4)
    {
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		if (meta < 3)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
			return true;
		}
		else if (meta > 3 && meta < 7)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 7, 2);
			return true;
		}
		else
		{
			return false;
		}
    }
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
        int meta = par1World.getBlockMetadata(par2, par3, par4);
        
        if (itemstack == null)
        {
        	/*AMT2より、椿にも実の採集機能を付与。*/
        	if (meta == 3)
        	{
        		ItemStack get = new ItemStack(DCsAppleMilk.leafTea, 1, 2);
        		
        		if (!par5EntityPlayer.inventory.addItemStackToInventory(get))
        		{
        			par5EntityPlayer.entityDropItem(get, 1);
        		}
        		
        		//2段階戻る
        		par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
        		
        		par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
        		return true;
        	}
        	else if (meta == 7)
        	{
        		ItemStack get = new ItemStack(DCsAppleMilk.leafTea, 1, 4);
        		
        		if (!par5EntityPlayer.inventory.addItemStackToInventory(get))
        		{
        			par5EntityPlayer.entityDropItem(get, 1);
        		}
        		
        		//2段階戻る
        		par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 3);
        		
        		par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
        		return true;
        	}
        	else
        	{
        		return false;
        	}
        }
        else if (!par1World.isRemote && meta == 3 && itemstack.getItem() == DCsAppleMilk.leafTea)
        {
        	ItemStack get = new ItemStack(DCsAppleMilk.leafTea, 1, 2);
    		
    		if (!par5EntityPlayer.inventory.addItemStackToInventory(get))
    		{
    			par5EntityPlayer.entityDropItem(get, 1);
    		}
    		
    		//2段階戻る
    		par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
    		par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
    		return true;
        }
        else if (!par1World.isRemote && meta == 7 && itemstack.getItem() == DCsAppleMilk.leafTea)
        {
        	ItemStack get = new ItemStack(DCsAppleMilk.leafTea, 1, 4);
    		
    		if (!par5EntityPlayer.inventory.addItemStackToInventory(get))
    		{
    			par5EntityPlayer.entityDropItem(get, 1);
    		}
    		
    		//2段階戻る
    		par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 3);
    		par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
    		return true;
        }
        else
        {
        	return false;
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
	
	@Override
	public int getRenderType()
	{
		return DCsAppleMilk.modelCassisTree;
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    { 
		int i = Math.min(par2, 7);
		if (par1 == 0)
        {
        	return this.logIIcon;
        }
        else if(par1 == 1)
        {
        	return this.leafIIcon;
        }
        else
        {
        	return this.newleafIIcon[i];
        }
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(DCsAppleMilk.cassisTree, 1, 3));
        par3List.add(new ItemStack(DCsAppleMilk.cassisTree, 1, 7));
    }
	
	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune)
	{
		return Item.getItemFromBlock(DCsAppleMilk.saplingTea);
	}
	
	public int damageDropped(int par1)
    {
        return par1 < 4 ? 1 : 2;
    }
	
	public int quantityDropped(Random random)
	{
		return random.nextInt(3) == 0 ? 2 : 1;
	}
	
	protected boolean canSilkHarvest()
    {
        return true;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.leafIIcon = par1IIconRegister.registerIcon(Util.getTexturePassNoAlt() + "tealeaf");
		this.logIIcon = par1IIconRegister.registerIcon(Util.getTexturePassNoAlt() + "tealog");
		this.blockIcon = par1IIconRegister.registerIcon(Util.getTexturePassNoAlt() + "tealeaf");
		
		this.newleafIIcon = new IIcon[8];
		for (int i = 0; i < 8; ++i)
        {
			if (i < 4)
			{
				int j = Math.max(i, 1);
				this.newleafIIcon[i] = par1IIconRegister.registerIcon(Util.getTexturePassNoAlt() + "cassisleaf_" + j);
			}
			else if (i < 8)
			{
				int j = Math.max(i, 1) - 4;
				this.newleafIIcon[i] = par1IIconRegister.registerIcon(Util.getTexturePassNoAlt() + "camellialeaf_" + j);
			}
            
        }
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return Plains;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return this;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}
	

}
