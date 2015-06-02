package mods.defeatedcrow.common.item.magic;

import mods.defeatedcrow.api.charm.EffectType;
import mods.defeatedcrow.api.charm.IIncenseEffect;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.entity.dummy.EntityStunEffect;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// 鎮静のインセンス
public class ItemIncenseAgar extends Item implements IIncenseEffect {

	public ItemIncenseAgar() {
		super();
		this.setMaxStackSize(64);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:incense_aloeswood");
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
			if (entity instanceof EntityLiving)// 対象はEntityLiving
			{
				EntityLiving living = (EntityLiving) entity;

				// if (DCsAppleMilk.confinement != null)//ポーション効果が追加されているか
				// {
				// living.addPotionEffect(new PotionEffect(DCsAppleMilk.confinement.id, 200, 0));
				// }

				/* 動きを止めてしまう効果に変更。 */
				EntityStunEffect stun = new EntityStunEffect(world, living, null, 20);
				if (!world.isRemote) {
					world.spawnEntityInWorld(stun);
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
		return 0.3F;
	}

	@Override
	public float particleColorG() {
		return 1.0F;
	}

	@Override
	public float particleColorB() {
		return 0.3F;
	}

}
