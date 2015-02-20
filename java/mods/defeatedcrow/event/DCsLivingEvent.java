package mods.defeatedcrow.event;

import java.util.Iterator;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mods.defeatedcrow.potion.PotionImmunity;
import mods.defeatedcrow.api.potion.PotionImmunityBase;
import mods.defeatedcrow.api.potion.PotionLivingBase;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.living.LivingEvent;

public class DCsLivingEvent {
	
	private int remain = 0;//カウントダウン用
	private int type = 0;
	private static boolean remaining;
	
	@SubscribeEvent
	  public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
	  {
		Entity entity = event.entity;
		
		//やっつけ仕事なので、プレイヤーを常時監視して、抵抗力ポーションが働いているかを見張っている
		if ((entity instanceof EntityPlayer))
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			
			if(player != null)
			{
				//PotionEffectのリスト
				Iterator iterator = player.getActivePotionEffects().iterator();
				
				while (iterator.hasNext())
				{
					PotionEffect effect = (PotionEffect)iterator.next();
					
					int newID = effect.getPotionID();
					
					Potion potion = Potion.potionTypes[newID];
					int amp = effect.getAmplifier();
					int dur = effect.getDuration();
					
					if(potion != null && potion instanceof PotionImmunityBase)
					{
						PotionImmunityBase immunity = (PotionImmunityBase) potion;
						
						if (immunity.preventPotion(amp, immunity.id, player)) {
							AMTLogger.debugInfo("Succeeded to prevent bad status by PotionImmunity effect.");
						}
					}
				}
				
				if (this.remain > 0 && this.remaining)
				{
					this.remain--;
					
					if (this.remain == 0)
					{
						player.addChatMessage(new ChatComponentText("remain : 0"));
						this.remaining = false;
					}
				}
				
				if (!player.isEntityAlive())//生存確認
				{
					this.remain = 0;
					this.remaining = false;
				}
			}
		}
		
		//こちらはEntityLivingBaseの監視用
		if ((entity instanceof EntityLivingBase))
		{
			EntityLivingBase living = (EntityLivingBase)event.entity;
			
			if(living != null && !living.worldObj.isRemote)
			{
				//PotionEffectのリスト
				Iterator iterator = living.getActivePotionEffects().iterator();
				
				while (iterator.hasNext())
				{
					PotionEffect effect = (PotionEffect)iterator.next();
					
					int newID = effect.getPotionID();
					
					Potion potion = Potion.potionTypes[newID];
					int amp = effect.getAmplifier();
					int dur = effect.getDuration();
					
					if(potion != null && potion instanceof PotionLivingBase)
					{
						PotionLivingBase immunity = (PotionLivingBase) potion;
						
						if (immunity.formPotionEffect(amp, immunity.id, living)) {
							AMTLogger.debugInfo("Succeeded to form effect of PotionLivingBase.");
						}
					}
				}
			}
		}
	  }
}
