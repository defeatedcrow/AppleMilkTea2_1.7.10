package mods.defeatedcrow.common.block.energy;

import java.util.Random;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGelBat extends Block{
	
	@SideOnly(Side.CLIENT)
    private IIcon texTop;
	@SideOnly(Side.CLIENT)
	private IIcon texSide;
	
	public BlockGelBat ()
	{
		super(Material.ground);
		this.setStepSound(Block.soundTypePiston);
		this.setHardness(1.0F);
		this.setResistance(2.0F);
	}
	
	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.texTop : this.texSide;
    }
	
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	@Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	@Override
	public int getRenderType()
	{
		return DCsAppleMilk.modelGelBat;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.blockIcon = par1IIconRegister.registerIcon("defeatedcrow:porcelain");
		this.texTop = par1IIconRegister.registerIcon("defeatedcrow:porcelain");
		this.texSide = par1IIconRegister.registerIcon("defeatedcrow:redgel");
		
	}

}
