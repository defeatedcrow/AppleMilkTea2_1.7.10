package mods.defeatedcrow.api.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;

/**
 * ただ単に継承して使えるようにするだけのもの。 <br>
 * ImmunityBaseと違い、EntityLivingBaseに影響がある。
 */
public abstract class PotionLivingBase extends Potion {

	protected PotionLivingBase(int id, boolean flag, int color) {
		super(id, flag, color);
	}

	/**
	 * @param id
	 *            : このポーションのID
	 * @param amp
	 *            : このポーション効果のAmplifier
	 * @param living
	 *            : 監視対象のEntity <br>
	 *            このメソッド内でTick毎の処理を行い、成否判定をboolean型で返して下さい。 <br>
	 *            返されるbooleanは成功時のログ出力にのみ影響し、どちらでもプレイは続行されます。
	 */
	public abstract boolean formPotionEffect(int amp, int id, EntityLivingBase living);

}
