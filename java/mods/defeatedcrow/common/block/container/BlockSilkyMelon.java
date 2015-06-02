package mods.defeatedcrow.common.block.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.entity.EntityMelonBomb;
import mods.defeatedcrow.common.entity.EntitySilkyMelon;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockSilkyMelon extends BlockMelonBomb {

	public BlockSilkyMelon() {
		super();
	}

	@Override
	protected void setEntityMelon(World par1World, int par2, int par3, int par4) {
		EntitySilkyMelon entityMelon = new EntitySilkyMelon(par1World, (double) ((float) par2 + 0.5F),
				(double) ((float) par3 + 1.0F), (double) ((float) par4 + 0.5F));
		entityMelon.rotationYaw = 0.0F;

		if (!par1World.isRemote) {
			if (entityMelon != null)
				AMTLogger.debugInfo("melon " + entityMelon.getEntityId());
			par1World.spawnEntityInWorld(entityMelon);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("defeatedcrow:melonbox_silky");
		this.boxIcon = par1IconRegister.registerIcon("defeatedcrow:melonbox_silky_top");
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:melonbox_silky");
	}

}
