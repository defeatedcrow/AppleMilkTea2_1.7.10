package mods.defeatedcrow.common.fluid;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockOilFluid extends BlockFluidClassic {
	
	@SideOnly(Side.CLIENT)
	protected IIcon baseIcon;
	@SideOnly(Side.CLIENT)
	protected IIcon sideIcon;

	public BlockOilFluid(Fluid fluid, Material material) {
		super(fluid, material);
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return side != 0 && side != 1 ? this.sideIcon : this.baseIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.baseIcon = par1IconRegister.registerIcon("defeatedcrow:fluid/oil_still");
		this.sideIcon = par1IconRegister.registerIcon("defeatedcrow:fluid/oil_still");
		
	}
	
	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		if (world.getBlock(x, y, z).getMaterial().isLiquid()) {
			return false;
		}
		return super.canDisplace(world, x, y, z);
	}

	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z) {
		if (world.getBlock(x, y, z).getMaterial().isLiquid()) {
			return false;
		}
		return super.displaceIfPossible(world, x, y, z);
	}

}
