package mods.defeatedcrow.common.block.container;

import java.util.Random;

import cpw.mods.fml.common.IFuelHandler;
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
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.handler.Util;

public class BlockCharcoalBox extends Block implements IFuelHandler {

	@SideOnly(Side.CLIENT)
	private IIcon charcoalBoxTop;
	@SideOnly(Side.CLIENT)
	private IIcon charcoalBoxSide;

	public BlockCharcoalBox() {
		super(Material.ground);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(1.0F);
		this.setResistance(2.0F);
	}

	@Override
	public int getBurnTime(ItemStack fuel) {

		Item i = fuel.getItem();
		return i == Item.getItemFromBlock(this) ? 16000 : 0;
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return par1 == 1 ? this.charcoalBoxTop : (par1 == 0 ? this.charcoalBoxSide
				: (par1 != 2 && par1 != 4 ? this.blockIcon : this.charcoalBoxSide));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister) {
		this.blockIcon = par1IIconRegister.registerIcon(Util.getTexturePassNoAlt() + "container_S");
		this.charcoalBoxTop = par1IIconRegister.registerIcon(Util.getTexturePassNoAlt() + "container_charcoal_T");
		this.charcoalBoxSide = par1IIconRegister.registerIcon(Util.getTexturePassNoAlt() + "container_S");

	}

}
