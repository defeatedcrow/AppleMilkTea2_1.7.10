package mods.defeatedcrow.common.item.magic;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.api.charm.EffectType;
import mods.defeatedcrow.api.charm.IIncenseEffect;
import mods.defeatedcrow.client.particle.EntityDCCloudFX;
import mods.defeatedcrow.client.particle.ParticleTex;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.config.DCsConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

// 氷結のインセンス
public class ItemIncenseIce extends Item implements IIncenseEffect {

	public ItemIncenseIce() {
		super();
		this.setMaxStackSize(64);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:incense_ice");
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
			int meta = world.getBlockMetadata(x, y, z);
			if (block != null && meta == 0)// 水源のみ
			{
				if (block == Blocks.water) {
					world.setBlock(x, y, z, Blocks.ice);
					return true;
				} else if (block == Blocks.lava) {
					world.setBlock(x, y, z, Blocks.obsidian);
					return true;
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
