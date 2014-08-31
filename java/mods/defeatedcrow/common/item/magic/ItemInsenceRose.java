package mods.defeatedcrow.common.item.magic;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.api.charm.EffectType;
import mods.defeatedcrow.api.charm.IInsenceEffect;
import mods.defeatedcrow.client.particle.EntityDCCloudFX;
import mods.defeatedcrow.client.particle.ParticleTex;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsConfig;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemInsenceRose extends Item implements IInsenceEffect{
	
	public ItemInsenceRose (){
		super ();
		this.setMaxStackSize(64);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister){

		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:insence_rose");
	}
	
	/*
	 * 以下はInsenceの効果を定義する部分。
	 * Item側に実装したほうが追加が容易だと思う。
	 * */
	
	@Override
	public int effectAreaRange() {
		return 3;
	}

	@Override
	public EffectType getEffectType() {
		return EffectType.EntityLiving;
	}

	@Override
	public boolean formEffect(World world, int x, int y, int z,
			EntityLivingBase entity, IInsenceEffect insence) {
		
		if (insence.getEffectType() == this.getEffectType() && entity != null)
		{
			entity.addPotionEffect(new PotionEffect(Potion.heal.id, 1, 0));
			return true;
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
		return 0.5F;
	}

	@Override
	public float particleColorB() {
		return 1.0F;
	}

}
