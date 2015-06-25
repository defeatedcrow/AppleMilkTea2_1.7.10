package mods.defeatedcrow.potion;

import mods.defeatedcrow.api.potion.PotionReflexBase;
import mods.defeatedcrow.common.config.DCsConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;

/**
 * 当て身ポーション。
 * このポーションは効果時間中にダメージを受けると発動し、ダメージを反射したり、吸収したりする。
 */
public class PotionReflex extends PotionReflexBase {

	public PotionReflex(int par1, boolean par2, int par3, boolean par4, int x, int y) {
		super(par1, par2, par3, par4, x, y);
	}

	@Override
	public boolean effectFormer(EntityLivingBase target, DamageSource source, PotionEffect effect, float amount) {
		boolean succeed = false;

		if (amount < 1.0F) {
			amount = 1.0F;
		}

		int amp = effect.getAmplifier();

		if (effect.getPotionID() == DCsConfig.potionIDReflex)// 反射
		{
			if (amount < 5.0F) {
				amount = 5.0F;
			}

			/* 攻撃元のエンティティがいない場合は何もしない */
			if (source instanceof EntityDamageSource) {
				EntityDamageSource source2 = (EntityDamageSource) source;
				Entity attacker = source2.getEntity();

				if (attacker != null) {
					if (attacker instanceof EntityLivingBase)// 生き物の時はダメージやノックバック処理を行う
					{
						EntityLivingBase livingAttacker = (EntityLivingBase) attacker;

						if (attacker != target) {
							// ノックバック
							float range = 360 - livingAttacker.rotationYaw;
							float yawX = MathHelper.sin(range / 180.0F * (float) Math.PI);
							float yawZ = MathHelper.cos(range / 180.0F * (float) Math.PI);
							livingAttacker.motionX += 1.0 * yawX;
							livingAttacker.motionY += 0.3;
							livingAttacker.motionZ += 1.0 * yawZ;
							// magic属性のダメージ
							livingAttacker.attackEntityFrom(DamageSource.magic, amount * amp);
							// プレイヤーには金属音が聞こえる
							Float r = target.worldObj.rand.nextFloat();
							target.worldObj.playSoundAtEntity(target, "defeatedcrow:metal", 1.0F, 0.5F + r);
							succeed = true;
						}
					} else if (amp > 0) {
						// 生き物でない場合は何もしないが、無効化効果は働く
						Float r = target.worldObj.rand.nextFloat();
						target.worldObj.playSoundAtEntity(target, "defeatedcrow:metal", 1.0F, 0.5F + r);
						succeed = true;
					}
				}
			}
		}

		if (effect.getPotionID() == DCsConfig.potionIDAbsEXP) {
			if (target instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) target;
				int get = Math.round(Math.abs(amount));
				boolean flag = false;

				if (amp > 1) {
					flag = true;
				} else if (amp > 0 && (source.isExplosion() || source.isFireDamage())) {
					flag = true;
				} else {
					flag = (source instanceof EntityDamageSource);
				}

				if (flag) {
					player.addExperience(get);
					Float r = player.worldObj.rand.nextFloat();
					player.worldObj.playSoundAtEntity(target, "defeatedcrow:suzu", 1.0F, 0.5F + r);
					succeed = true;
				}
			}
		}

		if (effect.getPotionID() == DCsConfig.potionIDAbsHeal) {
			if (target instanceof EntityLivingBase) {
				EntityLivingBase player = (EntityLivingBase) target;
				boolean flag = false;

				if (amp > 1) {
					flag = true;
				} else if (amp > 0 && (source.isExplosion() || source.isFireDamage())) {
					flag = true;
				} else {
					flag = (source instanceof EntityDamageSource);
				}

				if (flag) {
					player.heal(amount * amp);
					Float r = player.worldObj.rand.nextFloat();
					player.worldObj.playSoundAtEntity(target, "defeatedcrow:suzu", 1.0F, 0.5F + r);
					succeed = true;
				}
			}
		}

		return succeed;
	}

}
