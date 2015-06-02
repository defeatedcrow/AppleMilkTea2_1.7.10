package mods.defeatedcrow.common.fluid;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockCamOilFluid extends BlockFluidClassic {

	@SideOnly(Side.CLIENT)
	protected IIcon baseIcon;
	@SideOnly(Side.CLIENT)
	protected IIcon sideIcon;

	public BlockCamOilFluid(Fluid fluid, Material material) {
		super(fluid, material);
		this.setQuantaPerBlock(8);
		this.slipperiness = 0.98F;
		this.displacements.put(Blocks.water, false);
		this.displacements.put(Blocks.lava, false);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return side != 0 && side != 1 ? this.sideIcon : this.baseIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.baseIcon = par1IconRegister.registerIcon("defeatedcrow:fluid/camelliaoil_still");
		this.sideIcon = par1IconRegister.registerIcon("defeatedcrow:fluid/camelliaoil_still");

	}

	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		Material mat = world.getBlock(x, y, z).getMaterial();
		if (mat.isLiquid()) {
			return false;
		}
		return super.canDisplace(world, x, y, z);
	}

	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z) {
		Material mat = world.getBlock(x, y, z).getMaterial();
		// if (mat.isLiquid()) {
		// return false;
		// }
		return super.displaceIfPossible(world, x, y, z);
	}

	// 大変良く滑る
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity != null) {
			if (entity.motionX * entity.motionZ < 20.0D) {
				entity.motionX *= 1.1D;
				entity.motionZ *= 1.1D;
			}
		}
	}

}
