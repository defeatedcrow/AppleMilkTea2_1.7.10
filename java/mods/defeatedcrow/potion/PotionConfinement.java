package mods.defeatedcrow.potion;

import mods.defeatedcrow.api.potion.PotionLivingBase;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.entity.EntityLivingBase;

/**
 * ここではEntityの移動速度を強制でゼロにして足止めしてしまう効果を作っている。
 */
@Deprecated
public class PotionConfinement extends PotionLivingBase {

	public PotionConfinement(int par1, boolean par2, int par3, int x, int y) {
		super(par1, par2, par3, x, y);
	}

	/**
	 * PlayerのonUpdateEventで呼ばれるメソッド。
	 * 無理やり速度に0を入れているが、これでも結構動かれてしまう。改良が必要。
	 */
	@Override
	public boolean formPotionEffect(int amp, int id, EntityLivingBase living) {
		int[] check;
		boolean flag = false;

		if (DCsAppleMilk.confinement != null && id == DCsAppleMilk.confinement.id) {
			living.motionX = 0.0F;
			living.motionZ = 0.0F;
			living.moveForward = 0.0F;
			flag = true;
		}

		return flag;
	}

}
