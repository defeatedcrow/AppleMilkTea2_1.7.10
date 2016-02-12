package mods.defeatedcrow.common.block.edible;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.plugin.LoadAppleCorePlugin;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import squeek.applecore.api.food.FoodValues;
import squeek.applecore.api.food.IEdible;
import cpw.mods.fml.common.Optional;

/* クラッシュ回避用の中継クラス */
@Optional.Interface(iface = "squeek.applecore.api.food.IEdible", modid = "AppleCore")
public class EdibleEntityItemBlock2 extends EdibleEntityItemBlock implements IEdible {

	public EdibleEntityItemBlock2(Block block, boolean chopsticks, boolean tip) {
		super(block, chopsticks, tip);
	}

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
