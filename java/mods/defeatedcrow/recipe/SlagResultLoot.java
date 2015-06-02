package mods.defeatedcrow.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import mods.defeatedcrow.api.recipe.ISlagResultLoot;
import mods.defeatedcrow.handler.Util;

public class SlagResultLoot implements ISlagResultLoot {

	@Override
	public void addLoot(ItemStack item, int tier) {

		if (Util.notEmptyItem(item)) {
			switch (tier) {
			case 1:
				OreCrushRecipe.tier1.add(item);
				break;
			case 2:
				OreCrushRecipe.tier2.add(item);
				break;
			case 3:
				OreCrushRecipe.tier3.add(item);
				break;
			case 4:
				OreCrushRecipe.tier4.add(item);
				break;
			case 5:
				OreCrushRecipe.tier5.add(item);
				break;
			default:
				OreCrushRecipe.tier1.add(item);
				break;
			}
		}
	}

	@Override
	public List<ItemStack> getLootList(int tier) {
		int i = MathHelper.clamp_int(tier, 1, 5);
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		switch (tier) {
		case 1:
			return OreCrushRecipe.tier1;
		case 2:
			return OreCrushRecipe.tier2;
		case 3:
			return OreCrushRecipe.tier3;
		case 4:
			return OreCrushRecipe.tier4;
		case 5:
			return OreCrushRecipe.tier5;
		default:
			break;
		}

		return ret;
	}

}
