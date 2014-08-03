package mods.defeatedcrow.common.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.handler.Util;

public class BlockVegiBag extends Block{
	
	private static final String[] bagVegi = new String[] {"_Leaves", "_Potato", "_Carrot", "_Pumpkin", "_Seed", "_Reed", "_Cactus", "_Cocoa", "_Wart", "Sugar"};
	private static final String[] bagTexType = new String[] {"LeavesBag_T", "PotatoBag_T", "CarrotBag_T", "PumpkinBag_T", "SeedBag_T", "ReedBag_T",
		"CactusBag_T", "CocoaBag_T", "WartBag_T", "SugarBag_T"};
	
	@SideOnly(Side.CLIENT)
    private IIcon[] wheatBagTop;
	@SideOnly(Side.CLIENT)
	private IIcon wheatBagSide;
	
	public BlockVegiBag ()
	{
		super(Material.wood);
		this.setStepSound(Block.soundTypeWood);
	}
	
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        int i = par2;
        if (i > 9) i = 9;
        if (par1 == 1)
        {
        	return this.wheatBagTop[i];
        }
        else if (par1 == 0)
        {
        	return this.blockIcon;
        }
        else
        {
        	return this.wheatBagSide;
        }
		
    }
	
	public int damageDropped(int par1)
    {
        return par1;
    }
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 6));
        par3List.add(new ItemStack(par1, 1, 7));
        par3List.add(new ItemStack(par1, 1, 8));
        par3List.add(new ItemStack(par1, 1, 9));
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "WheatBag_B");
        this.wheatBagSide = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "WheatBag_S");
        this.wheatBagTop = new IIcon[10];
        
        for (int i = 0; i < 10; ++i)
        {
            this.wheatBagTop[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + bagTexType[i]);
        }
        
	}

}
