package mods.defeatedcrow.common.item.magic;

import mods.defeatedcrow.api.charm.EffectType;
import mods.defeatedcrow.api.charm.IIncenseEffect;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// potion効果解除
public class ItemIncenseYuzu extends Item implements IIncenseEffect {

	public ItemIncenseYuzu() {
		super();
		this.setMaxStackSize(64);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:incense_yuzu");
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
			entity.clearActivePotions();
			return true;
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
		return 0.5F;
	}

}
