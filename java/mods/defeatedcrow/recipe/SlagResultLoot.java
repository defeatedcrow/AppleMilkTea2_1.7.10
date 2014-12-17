package mods.defeatedcrow.recipe;

import net.minecraft.item.ItemStack;
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

}
