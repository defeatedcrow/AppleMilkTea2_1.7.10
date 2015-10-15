package mods.defeatedcrow.common.item.magic;

import mods.defeatedcrow.api.charm.EffectType;
import mods.defeatedcrow.api.charm.IIncenseEffect;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// 浄化のインセンス
public class ItemIncenseFrankincense extends Item implements IIncenseEffect {

	public ItemIncenseFrankincense() {
		super();
		this.setMaxStackSize(64);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:incense_frankincense");
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
		return EffectType.EntityLiving;
	}

	@Override
	public boolean formEffect(World world, int x, int y, int z, EntityLivingBase entity, IIncenseEffect incense) {

		if (incense.getEffectType() == this.getEffectType() && entity != null) {
			if (entity instanceof EntityCreature)// 対象はEntityCreature
			{
				EntityCreature living = (EntityCreature) entity;

				if (living instanceof EntityMob)// 対象はバニラ敵モブであるEntityMob、及びスライムだけ。
				{
					living.setDead();
					return true;
				} else {
					return false;
				}

				/*
				 * 条件に一致するEntityは問答無用で消してしまうので注意。
				 */
			}

		}
		return false;
	}

	@Override
	public String particleIcon() {
		return "feather";
	}

	@Override
	public float particleColorR() {
		return 2.0F;
	}

	@Override
	public float particleColorG() {
		return 2.0F;
	}

	@Override
	public float particleColorB() {
		return 2.0F;
	}

}
