package mods.defeatedcrow.potion;

import mods.defeatedcrow.common.*;
import mods.defeatedcrow.api.potion.PotionImmunityBase;
import mods.defeatedcrow.api.potion.PotionLivingBase;
import mods.defeatedcrow.common.DCsConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;

/**
 * ここではEntityの移動速度を強制でゼロにして足止めしてしまう効果を作っている。
 * */
public class PotionConfinement extends PotionLivingBase
{
	
	public PotionConfinement(int par1, boolean par2, int par3)
    {
        super(par1, par2, par3);
    }
	
	/**
	 * PlayerのonUpdateEventで呼ばれるメソッド。
	 * Amplifierごとに除去可能なポーション効果をチェックしている。
	 * 改良が必要。
	 * */
	@Override
	public boolean formPotionEffect(int amp, int id, EntityLivingBase living)
	{
		int[] check;
		boolean flag = false;
		
		if (DCsAppleMilk.confinement != null && id == DCsAppleMilk.confinement.id)
		{
			living.motionX = 0.0F;
			living.motionZ = 0.0F;
			flag = true;
		}
		
		return flag;
	}

}
