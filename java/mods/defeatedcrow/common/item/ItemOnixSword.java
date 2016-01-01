package mods.defeatedcrow.common.item;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemOnixSword extends ItemSword {

	private float damage;

	public ItemOnixSword() {
		super(DCsAppleMilk.enumToolMaterialChalcedony);

		this.setMaxStackSize(1);
		this.setMaxDamage(192);
		this.damage = 7.0F;
	}

	@Override
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(
				field_111210_e, "Weapon modifier", this.damage, 0));
		return multimap;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:tools/onixsword");
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		return par1ItemStack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {

		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {
		int j = this.getMaxItemUseDuration(par1ItemStack) - par4;

		boolean flag = par3EntityPlayer.capabilities.isCreativeMode
				|| EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

		boolean flag2 = false;
		int unb = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, par1ItemStack);
		if (unb > 0) {
			flag2 = itemRand.nextInt(unb + 1) > 0;
		}

		if (!flag && !flag2) {
			par1ItemStack.damageItem(1, par3EntityPlayer);
		}

		int shp = EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, par1ItemStack);

		float f = j / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;

		if (f < 0.5D) {
			return;
		}

		if (f > 1.0F) {
			f = 1.0F;
		}

		PotionEffect add;
		if (DCsAppleMilk.reflex != null) {
			add = new PotionEffect(DCsAppleMilk.reflex.id, 30 + shp * 10, 2 + shp);
		} else {
			add = new PotionEffect(Potion.resistance.id, 30 + shp * 10, 4);
		}

		par3EntityPlayer.addPotionEffect(add);
		par2World.playSoundAtEntity(par3EntityPlayer, "defeatedcrow:suzu", 1.0F, 1.0F
				/ (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
	}

}
