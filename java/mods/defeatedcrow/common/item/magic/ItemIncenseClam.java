package mods.defeatedcrow.common.item.magic;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.api.charm.EffectType;
import mods.defeatedcrow.api.charm.IIncenseEffect;
import mods.defeatedcrow.client.particle.EntityDCCloudFX;
import mods.defeatedcrow.client.particle.ParticleTex;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.block.container.BlockGunpowderContainer;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.tile.TileBrewingBarrel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

// 成長のインセンス
public class ItemIncenseClam extends Item implements IIncenseEffect {

	public ItemIncenseClam() {
		super();
		this.setMaxStackSize(64);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:incense_clam");
	}

	/*
	 * 以下はIncenseの効果を定義する部分。
	 * Item側に実装したほうが追加が容易だと思う。
	 */

	@Override
	public int effectAreaRange() {
		return 5;
	}

	@Override
	public EffectType getEffectType() {
		return EffectType.Block;
	}

	@Override
	public boolean formEffect(World world, int x, int y, int z, EntityLivingBase entity, IIncenseEffect incense) {

		if (incense.getEffectType() == this.getEffectType()) {
			Block block = world.getBlock(x, y, z);

			/*
			 * 3パターンの効果を持つ。
			 * ・ハマグリ発生効果
			 * ・醸造樽の熟成促進効果
			 * ・骨粉効果
			 * 上から順に試すため、優先度は上ほど高い。
			 */
			if (block != null) {
				boolean flag = false;

				if (block.getMaterial() == Material.water && world.getBlock(x, y - 1, z) == Blocks.sand) {
					world.setBlock(x, y - 1, z, DCsAppleMilk.clamSand, 0, 3);
					world.playAuxSFX(2005, x, y + 1, z, 0);
					flag = true;
				} else if (block == DCsAppleMilk.barrel) {
					TileBrewingBarrel barrel = (TileBrewingBarrel) world.getTileEntity(x, y, z);
					if (barrel != null) {
						int currentStage = barrel.getAgingStage();
						if (!barrel.productTank.isEmpty() && !barrel.getAged() && currentStage < 4) {
							barrel.setAgingStage(currentStage + 1);
							barrel.markDirty();
							world.markBlockForUpdate(x, y, z);
							flag = true;
						}
					}
				} else if (BlockGunpowderContainer.likeBonemeal(world, x, y, z)) {
					flag = true;
				}

				if (flag) {
					world.playAuxSFX(2005, x, y + 1, z, 0);
				}
			}

		}
		return false;
	}

	@Override
	public String particleIcon() {
		return "blink";
	}

	@Override
	public float particleColorR() {
		return 1.0F;
	}

	@Override
	public float particleColorG() {
		return 1.0F;
	}

	@Override
	public float particleColorB() {
		return 1.0F;
	}

}
