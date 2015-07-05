package mods.defeatedcrow.common.item.magic;

import mods.defeatedcrow.api.charm.EffectType;
import mods.defeatedcrow.api.charm.IIncenseEffect;
import mods.defeatedcrow.handler.Util;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// 惑乱のインセンス
public class ItemIncenseSandalwood extends Item implements IIncenseEffect {

	public ItemIncenseSandalwood() {
		super();
		this.setMaxStackSize(64);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:incense_sandalwood");
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
		return EffectType.EntityLiving;
	}

	@Override
	public boolean formEffect(World world, int x, int y, int z, EntityLivingBase entity, IIncenseEffect incense) {

		if (incense.getEffectType() == this.getEffectType() && entity != null) {
			if (entity instanceof EntityLiving)// 装備情報はEntityLivingにある
			{
				EntityLiving living = (EntityLiving) entity;

				if (living instanceof EntityTameable || living instanceof EntityAgeable) {
					return false;// 味方の可能性があるので何もしない
				} else {// 装備剥がし
					int slot = world.rand.nextInt(5);// 0~4、手持ちと防具スロット
					ItemStack equip = living.getEquipmentInSlot(slot);
					if (Util.notEmptyItem(equip)) {
						ItemStack drop = equip.copy();
						float f = world.rand.nextFloat() * 0.8F + 0.1F;
						float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
						float f2 = world.rand.nextFloat() * 0.8F + 0.1F;

						EntityItem entityitem = new EntityItem(world, (double) ((float) x + f),
								(double) ((float) y + f1), (double) ((float) z + f2), drop);

						float f3 = 0.05F;
						entityitem.motionX = (double) ((float) world.rand.nextGaussian() * f3);
						entityitem.motionY = (double) ((float) world.rand.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double) ((float) world.rand.nextGaussian() * f3);

						if (world.spawnEntityInWorld(entityitem)) {
							living.setCurrentItemOrArmor(slot, null);
							return true;
						}
					}
				}
			}

		}
		return false;
	}

	@Override
	public String particleIcon() {
		return "cloud";
	}

	@Override
	public float particleColorR() {
		return 0.7F;
	}

	@Override
	public float particleColorG() {
		return 0.6F;
	}

	@Override
	public float particleColorB() {
		return 0.5F;
	}

}
