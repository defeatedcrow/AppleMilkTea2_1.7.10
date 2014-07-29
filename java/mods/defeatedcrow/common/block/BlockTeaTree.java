package mods.defeatedcrow.common.block;

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

public class BlockTeaTree extends Block implements IPlantable{
	
	@SideOnly(Side.CLIENT)
    private IIcon leafIcon;
	@SideOnly(Side.CLIENT)
    private IIcon newleafIcon;
	@SideOnly(Side.CLIENT)
    private IIcon logIcon;
	
	public BlockTeaTree ()
	{
		super(Material.wood);
		this.setStepSound(Block.soundTypeGrass);
		this.setTickRandomly(true);
	}
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (!par1World.isRemote && par1World.rand.nextInt(6) == 0)
		{
			int meta = par1World.getBlockMetadata(par2, par3, par4);
			if (meta != 1 && (par1World.getBlockLightValue(par2, par3, par4) > 11))
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
			}
		}
	}
	
	public boolean fertilize(World par1World, int par2, int par3, int par4)
    {
        return par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
    }
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
        int meta = par1World.getBlockMetadata(par2, par3, par4);
        
        if (itemstack == null)
        {
        	if (meta == 1)
        	{
        		if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(DCsAppleMilk.leafTea, 1, 0)))
        		{
        			par5EntityPlayer.entityDropItem(new ItemStack(DCsAppleMilk.leafTea, 1, 0), 1);
        		}
        		
        		par5EntityPlayer.triggerAchievement(AchievementRegister.getTeaLeaves);
        		
        		par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
        		
        		par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
        		return true;
        	}
        	else
        	{
        		return false;
        	}
        }
        else if (meta == 1 && itemstack.getItem()== DCsAppleMilk.leafTea)
        {
        	if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(DCsAppleMilk.leafTea, 1, 0)))
        	{
        		par5EntityPlayer.entityDropItem(new ItemStack(DCsAppleMilk.leafTea, 1, 0), 1);
        	}
        	par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
        	
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
		return DCsAppleMilk.modelTeaTree;
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    { 
		int i = par2;
		if (par1 == 0)
        {
        	return this.logIcon;
        }
        else if(par1 == 1)
        {
        	return this.leafIcon;
        }
        else
        {
        	return this.newleafIcon;
        }
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
    }
	
	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune)
	{
		return Item.getItemFromBlock(DCsAppleMilk.saplingTea);
	}
	
	public int quantityDropped(Random random)
	{
		return random.nextInt(2) == 0 ? 1 : 2;
	}
	
	protected boolean canSilkHarvest()
    {
        return true;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.leafIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "tealeaf");
		this.newleafIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "tealeaf_2");
		this.logIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "tealog");
				
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
