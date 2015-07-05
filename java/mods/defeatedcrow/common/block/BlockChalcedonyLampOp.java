package mods.defeatedcrow.common.block;

import static net.minecraftforge.common.util.ForgeDirection.UP;

import java.util.List;
import java.util.Random;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChalcedonyLampOp extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon[] color;

	private String[] name = { "", "_orange", "_white", "_black" };

	public BlockChalcedonyLampOp(Material material, boolean flag) {
		super(Material.glass);
		this.setHardness(0.5F);
		this.setResistance(1.0F);
		this.setStepSound(Block.soundTypeGlass);
		this.setLightLevel(1.0F);
	}

	@Override
	public int getRenderType() {
		return DCsAppleMilk.modelCLampOp;
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
	}

	@Override
	public boolean isOpaqueCube() {
		return true;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
	}

	@Override
	public int getMobilityFlag() {
		return 0;
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return side != UP;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int type = par2 & 3;
		return this.color[type];
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
			this.color[i] = par1IconRegister.registerIcon("defeatedcrow:chalcedony_opaq" + name[i]);
		}
	}

}
