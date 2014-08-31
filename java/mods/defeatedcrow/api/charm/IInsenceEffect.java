package mods.defeatedcrow.api.charm;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Insenceの効果を定義するインターフェイス。
 * <br>Item側に実装し、InsenceBaseがTick毎の処理で、入っているお香アイテムの実装を確認して効果をつくる。
 * */
public interface IInsenceEffect {
	
	
	/**
	 * 効果範囲。EffectTypeによって同じ値でも動作が大きく異なる。
	 * <br>Player：プレイヤーの各種監視イベントから判定される場合、イベント側で5固定で使うので、インターフェースの範囲指定が呼ばれない。
	 * <br>Entity：指定した半径、同じ高さの平面の範囲に含まれるすべてのEntityが対象。
	 * <br>Block：指定した半径（平面・高さ）の直方体の範囲内から、ランダムに1つのブロックを選んで対象とする。
	 * <br>Alt：その他。Ice等の限られたInsence専用の設定で、設置チャンク内からランダムに範囲内のYを1つ選び、同じ高さのすべてのブロックを対象とする。
	 * */
	public int effectAreaRange();
	
	/**
	 * 効果対象。
	 * <br>Player：プレイヤーが対象
	 * <br>Entity：プレイヤーを含むLivingEntity。ポーション効果が主なため、LivingEntityBaseの継承が必要。
	 * <br>Block：範囲内のブロックひとつ。
	 * <br>Alt：その他。Ice等の限られたInsence専用の設定。
	 * */
	public EffectType getEffectType();
	
	/**
	 * 処理の実行部分。
	 * InsenceBaseのアップデート処理でこれを呼ぶ。
	 * */
	public boolean formEffect(World world, int x, int y, int z, EntityLivingBase entity, IInsenceEffect insence);
	
	/**
	 * パーティクル関係。
	 * */
	public String particleIcon();
	
	public float particleColorR();
	
	public float particleColorG();
	
	public float particleColorB();

}
