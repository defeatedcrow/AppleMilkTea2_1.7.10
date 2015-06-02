package mods.defeatedcrow.event;

import java.util.ArrayList;
import java.util.Iterator;

import mods.defeatedcrow.api.potion.PotionImmunityBase;
import mods.defeatedcrow.api.potion.PotionLivingBase;
import mods.defeatedcrow.common.AMTLogger;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DCsLivingEvent {

	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
		Entity entity = event.entity;

		// やっつけ仕事なので、プレイヤーを常時監視して、抵抗力ポーションが働いているかを見張っている
		if ((entity instanceof EntityPlayer)) {
			EntityPlayer player = (EntityPlayer) event.entity;

			ArrayList<PotionEffect> immunities = new ArrayList<PotionEffect>();

			if (player != null) {
				// PotionEffectのリスト
				Iterator iterator = player.getActivePotionEffects().iterator();

				while (iterator.hasNext()) {
					PotionEffect effect = (PotionEffect) iterator.next();

					int id = effect.getPotionID();
					Potion potion = Potion.potionTypes[id];

					if (potion != null && potion instanceof PotionImmunityBase) {
						immunities.add(effect);
					}
				}

				for (PotionEffect eff : immunities) {
					Potion potion = Potion.potionTypes[eff.getPotionID()];
					int amp = eff.getAmplifier();
					int dur = eff.getDuration();

					if (potion != null && potion instanceof PotionImmunityBase) {
						PotionImmunityBase immunity = (PotionImmunityBase) potion;

						if (immunity.preventPotion(amp, immunity.id, player)) {
							AMTLogger.debugInfo("Succeeded to prevent bad status by PotionImmunity effect.");
						}
					}
				}
			}
		}

		// こちらはEntityLivingBaseの監視用
		if ((entity instanceof EntityLivingBase)) {
			EntityLivingBase living = (EntityLivingBase) event.entity;

			ArrayList<PotionEffect> potions = new ArrayList<PotionEffect>();

			if (living != null && !living.worldObj.isRemote) {
				// PotionEffectのリスト
				Iterator iterator = living.getActivePotionEffects().iterator();

				while (iterator.hasNext()) {
					PotionEffect effect = (PotionEffect) iterator.next();

					int id = effect.getPotionID();
					Potion potion = Potion.potionTypes[id];

					if (potion != null && potion instanceof PotionLivingBase) {
						potions.add(effect);
					}

					if (potion != null && potion.id == potion.jump.id) {
						living.fallDistance = 0.0F;
					}

					if (living.ridingEntity != null && living.ridingEntity instanceof EntityLivingBase) {
						EntityLivingBase riding = (EntityLivingBase) event.entity.ridingEntity;
						if (potion != null) {
							riding.addPotionEffect(effect);
						}
					}

				}
			}

			for (PotionEffect eff : potions) {
				Potion potion = Potion.potionTypes[eff.getPotionID()];
				int amp = eff.getAmplifier();
				int dur = eff.getDuration();

				if (potion != null && potion instanceof PotionLivingBase) {
					PotionLivingBase immunity = (PotionLivingBase) potion;

					if (immunity.formPotionEffect(amp, immunity.id, living)) {
						AMTLogger.debugInfo("Succeeded to form effect of PotionLivingBase.");
					}
				}
			}
		}
	}
}
