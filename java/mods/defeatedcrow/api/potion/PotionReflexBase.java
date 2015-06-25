package mods.defeatedcrow.api.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

/**
 * ただ単に継承して使えるようにするだけのもの。 <br>
 * 継承しているポーション効果は、
 * プレイヤーを含むEntityLivingBaseのダメージ処理時に呼び出される。(amplifier現象機能は廃止済み) <br>
 * 呼び出されたあとの処理はこのクラス内で定義できます。
 */
public abstract class PotionReflexBase extends PotionBaseAMT {

	public boolean endlessly = false;

	protected PotionReflexBase(int id, boolean flag, int color, boolean isInfinity, int x, int y) {
		super(id, flag, color, x, y);
		this.endlessly = isInfinity;
	}

	/**
	 * @param target
	 *            : 攻撃を受けたEntityLivingBase
	 * @param source
	 *            : 受けた攻撃のダメージソース
	 * @param effect
	 *            : このポーション効果
	 * @param amount
	 *            : 受けたダメージの値 <br>
	 *            このメソッド内でダメージを受けた瞬間の処理を行い、成否判定をboolean型で返して下さい。 <br>
	 *            trueを返した場合のみ、ダメージの無効化が行われます。
	 */
	public abstract boolean effectFormer(EntityLivingBase target, DamageSource source, PotionEffect effect, float amount);

}
