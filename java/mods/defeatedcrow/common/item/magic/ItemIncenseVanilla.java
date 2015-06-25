package mods.defeatedcrow.common.item.magic;

import mods.defeatedcrow.api.charm.EffectType;
import mods.defeatedcrow.api.charm.IIncenseEffect;
import mods.defeatedcrow.common.tile.TileBrewingBarrel;
import mods.defeatedcrow.common.tile.TileCordial;
import mods.defeatedcrow.plugin.AddonIntegration;
import mods.defeatedcrow.plugin.HandleDryingRack;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// 熟成のインセンス
public class ItemIncenseVanilla extends Item implements IIncenseEffect {

	public ItemIncenseVanilla() {
		super();
		this.setMaxStackSize(64);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:incense_vanilla");
	}

	/*
	 * 以下はIncenseの効果を定義する部分。
	 * Item側に実装したほうが追加が容易だと思う。
	 */

	@Override
	public int effectAreaRange() {
		return 3;
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
			TileEntity tile = world.getTileEntity(x, y, z);
			boolean flag = false;
			if (tile != null) {
				if (tile instanceof TileBrewingBarrel) {
					TileBrewingBarrel barrel = (TileBrewingBarrel) tile;
					int age = barrel.getAgingStage();
					if (age < 4) {
						barrel.setAgingStage(age + 1);
						flag = true;
					}
				}
				if (tile instanceof TileCordial) {
					TileCordial cor = (TileCordial) tile;
					int age = cor.getAgingStage();
					if (age < 4) {
						cor.setAgingStage(age + 1);
						flag = true;
					}
				}
				if (AddonIntegration.loadedJP() && HandleDryingRack.isDryingRack(tile)) {
					HandleDryingRack.addDays(tile, 1, false);
					flag = true;
				}
			}

			if (flag) {
				world.playAuxSFX(2005, x, y, z, 0);
				world.markBlockForUpdate(x, y, z);
				return true;
			}

		}
		return false;
	}

	@Override
	public String particleIcon() {
		return "flower";
	}

	@Override
	public float particleColorR() {
		return 1.0F;
	}

	@Override
	public float particleColorG() {
		return 0.8F;
	}

	@Override
	public float particleColorB() {
		return 0.3F;
	}

}
