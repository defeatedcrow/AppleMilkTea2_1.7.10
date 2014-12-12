package mods.defeatedcrow.api.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;

/**
 * ただ単に継承して使えるようにするだけのもの。
 * <br>Immunityとは名ばかりで、実際はかかっている間はTick毎に一回ずつ処理が呼び出される。
 * <br>したがって実情はプレイヤーの常時監視用システムである。*/
public abstract class PotionImmunityBase extends Potion{

	protected PotionImmunityBase(int id, boolean flag, int color) {
		super(id, flag, color);
	}
	
	
	/**
	 * @param id : このポーションのID
	 * @param amp : このポーション効果のAmplifier
	 * @param player : 監視対象のプレイヤー
	 * <br>このメソッド内でTick毎の処理を行い、成否判定をboolean型で返して下さい。
	 * <br>返されるbooleanは成功時のログ出力にのみ影響し、どちらでもプレイは続行されます。
	 * */
	public abstract boolean preventPotion(int amp, int id, EntityPlayer player);

}
