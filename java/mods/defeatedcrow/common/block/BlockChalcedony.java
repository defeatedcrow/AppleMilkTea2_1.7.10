package mods.defeatedcrow.common.block;

import net.minecraft.block.Block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockChalcedony extends BlockBreakable {

	@SideOnly(Side.CLIENT)
	private IIcon[] color;

	public BlockChalcedony(Material material, boolean flag) {
		super("defeatedcrow:chalcedony", material, flag);
		this.setHardness(1.5F);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setLightLevel(0.0F);
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	public int damageDropped(int par1) {
		return par1;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
	}

	public int getMobilityFlag() {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = MathHelper.clamp_int(par2, 0, 3);
		return color[i];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < 4; ++i) {
			par3List.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.color = new IIcon[4];

		for (int i = 0; i < 4; ++i) {
			if (i == 0)
				this.color[i] = par1IconRegister.registerIcon("defeatedcrow:chalcedony");
			else if (i == 1)
				this.color[i] = par1IconRegister.registerIcon("defeatedcrow:chalcedony_orange");
			else if (i == 2)
				this.color[i] = par1IconRegister.registerIcon("defeatedcrow:chalcedony_white");
			else
				this.color[i] = par1IconRegister.registerIcon("defeatedcrow:chalcedony_black");

		}

	}

}
