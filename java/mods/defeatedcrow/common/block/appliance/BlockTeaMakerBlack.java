package mods.defeatedcrow.common.block.appliance;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class BlockTeaMakerBlack extends BlockTeaMakerNext {

	public BlockTeaMakerBlack() {
		super();
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(1.0F);
		this.setResistance(6000000.0F);
		this.setHarvestLevel("pickaxe", 1);
	}

	@Override
	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
		if ((entity instanceof EntityWither) || (entity instanceof EntityDragon)) {
			return this == DCsAppleMilk.teaMakerBlack ? false : super.canEntityDestroy(world, x, y, z, entity);
		}

		return super.canEntityDestroy(world, x, y, z, entity);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("obsidian");
	}

}
