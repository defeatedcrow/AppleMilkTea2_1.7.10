package mods.defeatedcrow.recipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mods.defeatedcrow.api.appliance.SoupType;
import mods.defeatedcrow.api.recipe.IChocoFruitsRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ChocolateRecipe implements IChocoFruitsRecipe {

	private final HashMap<Object, ItemStack> recipes;

	public ChocolateRecipe() {
		this.recipes = new HashMap<Object, ItemStack>();
	}

	/**
	 * Now it's empty. Please use IFondueRecipe.
	 */
	public IChocoFruitsRecipe instance() {
		return RecipeRegisterManager.chocoRecipe;
	}

	@Override
	public Map<Object, ItemStack> getRecipeList() {
		return this.recipes;
	}

	@Override
	public ItemStack getOutput(ItemStack input) {
		if (input == null || input.getItem() == null)
			return null;

		ItemStack ret = null;

		for (Object key : recipes.keySet()) {
			if (key instanceof String) {
				String s = (String) key;
				List<ItemStack> items = OreDictionary.getOres(s);
				for (int i = 0; i < items.size(); i++) {
					if (matchItem(input, items.get(i))) {
						ret = recipes.get(key);
						break;
					}
				}
			} else if (key instanceof ItemStack) {
				ItemStack item = (ItemStack) key;

				if (matchItem(input, (ItemStack) key)) {
					ret = recipes.get(key);
					break;
				}
			}

		}

		return ret == null ? null : ret.copy();
	}

	protected static boolean matchItem(ItemStack input, ItemStack key) {
		if (input == null || key == null)
			return false;
		if (input.getItem() == null || key.getItem() == null)
			return false;
		else {
			return (input.getItem() == key.getItem() && (input.getItemDamage() == key.getItemDamage() || key
					.getItemDamage() == OreDictionary.WILDCARD_VALUE));
		}
	}

	@Override
	public void register(ItemStack input, ItemStack output) {
		if (input == null || output == null)
			return;
		if (input.getItem() == null || output.getItem() == null)
			return;
		// for (Object key : recipes.keySet()) {
		// if (key instanceof ItemStack) {
		// if (matchItem(input, (ItemStack) key)) {
		// return;
		// }
		// }
		// }
		// recipes.put(input, output);
		RecipeRegisterManager.fondueRecipe.register(input, output, SoupType.CHOCO);
	}

	@Override
	public void register(String input, ItemStack output) {
		if (input == null || output == null)
			return;
		if (output.getItem() == null)
			return;
		// recipes.put(input, output);
		RecipeRegisterManager.fondueRecipe.registerByOre(input, output, SoupType.CHOCO);
	}

}
