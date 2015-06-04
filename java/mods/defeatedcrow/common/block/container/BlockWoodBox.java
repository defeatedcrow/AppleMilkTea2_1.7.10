package mods.defeatedcrow.common.block.container;

import java.util.List;
import java.util.Random;

import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWoodBox extends Block {

	private static final String[] boxType = new String[] { "_oak", "_spruse", "_birch", "_jungle", "_rubber", "_great",
			"_silver", "_force", "_sakura", "_momizi", "_JPcedar", "_darkoak", "_acacia" };

	@SideOnly(Side.CLIENT)
	private IIcon[] boxTex;
	@SideOnly(Side.CLIENT)
	private IIcon[] boxSideTex;

	public BlockWoodBox() {
		super(Material.wood);
		this.setStepSound(Block.soundTypeWood);
		this.setHardness(0.1F);
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par2;
		if (i > 12)
			i = 12;
		if (par1 == 4 || par1 == 5) {
			return this.boxSideTex[i];
		} else {
			return this.boxTex[i];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
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
		par3List.add(new ItemStack(par1, 1, 10));
		par3List.add(new ItemStack(par1, 1, 11));
		par3List.add(new ItemStack(par1, 1, 12));
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.boxTex = new IIcon[13];
		this.boxSideTex = new IIcon[13];

		for (int i = 0; i < 13; ++i) {
			this.boxTex[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "WoodBox" + boxType[i]);
			this.boxSideTex[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "WoodBoxside" + boxType[i]);
		}
	}

}
