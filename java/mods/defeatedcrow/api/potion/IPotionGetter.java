package mods.defeatedcrow.api.potion;

import net.minecraft.potion.Potion;

public interface IPotionGetter {

	/**
	 * 未登録のStringを入れた場合はnullが返る。 <br>
	 * また、前提MOD不足などで登録失敗していた場合は、代替としてPotion.regenerationが返る。 <br>
	 * AMTで追加されているPotionのStringリストは、IPotionGetter.javaのコメントを参照のこと。
	 */
	Potion AMTgetPotion(String name);

	/*
	 * AMT2 Potion String Table list
	 * 
	 * immunization
	 * projectile_resist
	 * explosion_resist
	 * reflex
	 * absorb_heal
	 * absorb_exp
	 * suffocation
	 * suffocation_resist
	 * hallucination
	 */

}
