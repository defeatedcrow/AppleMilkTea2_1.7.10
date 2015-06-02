package mods.defeatedcrow.potion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.potion.Potion;
import mods.defeatedcrow.api.potion.IPotionGetter;
import mods.defeatedcrow.common.DCsAppleMilk;

public class PotionGetter implements IPotionGetter {

	private static HashMap<String, Potion> potionMap = new HashMap<String, Potion>();

	@Override
	public Potion AMTgetPotion(String name) {

		Potion ret = null;
		if (potionMap.containsKey(name)) {
			ret = potionMap.get(name);
			return checkIsAdded(ret);
		}

		return null;
	}

	public static void initialize() {
		potionMap.put("immunization", checkIsAdded(DCsAppleMilk.Immunization));
		potionMap.put("projectile_resist", checkIsAdded(DCsAppleMilk.prvProjectile));
		potionMap.put("explosion_resist", checkIsAdded(DCsAppleMilk.prvExplode));
		potionMap.put("reflex", checkIsAdded(DCsAppleMilk.reflex));
		potionMap.put("absorb_heal", checkIsAdded(DCsAppleMilk.absHeal));
		potionMap.put("absorb_exp", checkIsAdded(DCsAppleMilk.absEXP));
		potionMap.put("suffocation", checkIsAdded(DCsAppleMilk.suffocation));
		potionMap.put("suffocation_resist", checkIsAdded(DCsAppleMilk.prvSuffocation));
		potionMap.put("hallucination", checkIsAdded(DCsAppleMilk.hallucinations));
	}

	private static Potion checkIsAdded(Potion potion) {
		return potion == null ? Potion.regeneration : potion;
	}

}
