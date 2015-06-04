package mods.defeatedcrow.common.block.container;

import java.util.List;

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

public class BlockMobDrop extends Block {

	private static final String[] type = new String[] { "_rotten", "_bone", "_spider", "_ender", "_slime" };

	@SideOnly(Side.CLIENT)
	private IIcon[] texType;

	public BlockMobDrop() {
		super(Material.wood);
		this.setStepSound(Block.soundTypeWood);
		this.setHardness(0.1F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par2 & 7;
		if (i > 4)
			i = 4;
		return this.texType[i];

	}

	@Override
	public int damageDropped(int par1) {
		return par1 & 7;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
		par3List.add(new ItemStack(par1, 1, 3));
		par3List.add(new ItemStack(par1, 1, 4));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "mobbox_rotten");
		this.texType = new IIcon[5];

		for (int i = 0; i < 5; ++i) {
			this.texType[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "mobbox" + type[i]);
		}

	}

}
