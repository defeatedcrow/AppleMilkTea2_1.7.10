package mods.defeatedcrow.api.charm;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

/**
 * Incenseの効果を定義するインターフェイス。 <br>
 * Item側に実装し、IncenseBaseがTick毎の処理で、入っているお香アイテムの実装を確認して効果をつくる。
 */
public interface IIncenseEffect {

	/**
	 * 効果範囲。EffectTypeによって同じ値でも動作が大きく異なる。 <br>
	 * Player：プレイヤーの各種監視イベントから判定される場合、イベント側で5固定で使うので、インターフェースの範囲指定が呼ばれない。 <br>
	 * Entity：指定した半径、同じ高さの平面の範囲に含まれるすべてのEntityが対象。 <br>
	 * Block：指定した半径（平面・高さ）の直方体の範囲内から、ランダムに1つのブロックを選んで対象とする。 <br>
	 * Alt：その他。Ice等の限られたIncense専用の設定で、設置チャンク内からランダムに範囲内のYを1つ選び、同じ高さのすべてのブロックを対象とする。
	 */
	public int effectAreaRange();

	/**
	 * 効果対象。 <br>
	 * Player：プレイヤーが対象 <br>
	 * Entity：プレイヤーを含むLivingEntity。ポーション効果が主なため、LivingEntityBaseの継承が必要。 <br>
	 * Block：範囲内のブロックひとつ。 <br>
	 * Alt：その他。Ice等の限られたIncense専用の設定。現在は未使用のため非推奨（処理が呼ばれない）。
	 */
	public EffectType getEffectType();

	/**
	 * 処理の実行部分。
	 * IncenseBaseのアップデート処理でこれを呼ぶ。
	 */
	public boolean formEffect(World world, int x, int y, int z, EntityLivingBase entity, IIncenseEffect incense);

	/**
	 * パーティクル関係。
	 */

	/**
	 * AMTに登録済みのものしか使えない。
	 * 現在は、flower、orb、blinkの３種
	 */
	public String particleIcon();

	public float particleColorR();

	public float particleColorG();

	public float particleColorB();

}
