package mods.defeatedcrow.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import mods.defeatedcrow.api.recipe.ISlagResultLoot;
import mods.defeatedcrow.handler.Util;

public class SlagResultLoot implements ISlagResultLoot{

	@Override
	public void addLoot(ItemStack item, int tier) {
		
		if (Util.notEmptyItem(item))
		{
			switch(tier)
			{
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
		switch(tier)
		{
		case 1:
			ret.addAll(OreCrushRecipe.tier1);
			break;
		case 2:
			ret.addAll(OreCrushRecipe.tier2);
			break;
		case 3:
			ret.addAll(OreCrushRecipe.tier3);
			break;
		case 4:
			ret.addAll(OreCrushRecipe.tier4);
			break;
		case 5:
			ret.addAll(OreCrushRecipe.tier5);
			break;
		default:
			break;
		}
		
		return ret;
	}

}
