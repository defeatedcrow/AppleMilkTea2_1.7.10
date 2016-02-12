package mods.defeatedcrow.common.item.edible;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.plugin.LoadAppleCorePlugin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import squeek.applecore.api.food.FoodValues;
import squeek.applecore.api.food.IEdible;
import cpw.mods.fml.common.Optional;

/* クラッシュ回避用の中継クラス */
@Optional.Interface(iface = "squeek.applecore.api.food.IEdible", modid = "AppleCore")
public class EdibleEntityItem2 extends EdibleEntityItem implements IEdible {

	public EdibleEntityItem2(boolean chopsticks, boolean tip) {
		super(chopsticks, tip);
	}

	/**
	 * AppleCore連携用、IEdibleの実装メソッド。
	 */
	@Override
	@Optional.Method(modid = "AppleCore")
	public FoodValues getFoodValues(ItemStack itemStack) {
		int[] h = this.hungerOnEaten(itemStack.getItemDamage());
		return new FoodValues(h[0], h[1] * 0.1F);
	}

	@Override
	protected void addStatus(EntityPlayer player, int[] heal, ItemStack food) {
		if (DCsAppleMilk.SuccessLoadACore) {
			LoadAppleCorePlugin.addFoodStatus(player, food);
		} else {
			player.getFoodStats().addStats(heal[0], heal[1] * 0.1F);
		}
	}
}
