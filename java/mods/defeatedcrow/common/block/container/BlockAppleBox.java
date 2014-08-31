package mods.defeatedcrow.common.block.container;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.src.*;
import net.minecraft.util.IIcon;
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.handler.Util;

public class BlockAppleBox extends Block{
	
	@SideOnly(Side.CLIENT)
    private IIcon appleBoxTop;
	@SideOnly(Side.CLIENT)
	private IIcon appleBoxSide;
	
	public BlockAppleBox ()
	{
		super(Material.wood);
		this.setStepSound(Block.soundTypeWood);
	}
	
	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.appleBoxTop : (par1 == 0 ? this.appleBoxSide : (par1 != 2 && par1 != 4 ? this.blockIcon : this.appleBoxSide));
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "WoodBox");
		this.appleBoxTop = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "applebox");
		this.appleBoxSide = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "WoodBox");
		
	}
}
