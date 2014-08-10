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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.handler.Util;

public class BlockCardboard extends Block{
	
	private static final String[] bagVegi = new String[] {"_mint", "_cassis", "_yuzu", "_camellia"};
	
	@SideOnly(Side.CLIENT)
    private IIcon texTop;
	@SideOnly(Side.CLIENT)
    private IIcon texBottom;
	@SideOnly(Side.CLIENT)
    private IIcon texFront;
	@SideOnly(Side.CLIENT)
	private IIcon[] texSide;
	
	public BlockCardboard ()
	{
		super(Material.wood);
		this.setStepSound(Block.soundTypeWood);
	}
	
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        int i = par2 & 7;
        boolean flag = par2 > 7;
        if (i > 3) i = 3;
        if (par1 == 1)
        {
        	return this.texTop;
        }
        else if (par1 == 0)
        {
        	return this.texBottom;
        }
        else if (par1 == 2 || par1 == 3)
        {
        	return flag ? this.texFront : this.texSide[i];
        }
        else
        {
        	return flag ? this.texSide[i] : this.texFront;
        }
		
    }
	
	public int damageDropped(int par1)
    {
        return par1 & 7;
    }
	
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int meta = par6ItemStack.getItemDamage();

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, meta | 8, 3);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, meta, 3);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, meta | 8, 3);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, meta, 3);
        }
    }
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "cardboard_S_yuzu");
        this.texFront = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "cardboard_F");
        this.texTop = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "cardboard_T");
        this.texBottom = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "cardboard_B");
        this.texSide = new IIcon[4];
        
        for (int i = 0; i < 4; ++i)
        {
            this.texSide[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "cardboard_S" + bagVegi[i]);
        }
        
	}

}
