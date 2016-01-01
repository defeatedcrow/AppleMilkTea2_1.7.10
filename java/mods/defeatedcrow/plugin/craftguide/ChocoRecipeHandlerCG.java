package mods.defeatedcrow.plugin.craftguide;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import uristqwerty.CraftGuide.api.ItemSlot;
import uristqwerty.CraftGuide.api.RecipeGenerator;
import uristqwerty.CraftGuide.api.RecipeProvider;
import uristqwerty.CraftGuide.api.RecipeTemplate;
import uristqwerty.CraftGuide.api.Slot;
import uristqwerty.CraftGuide.api.SlotType;

public class ChocoRecipeHandlerCG implements RecipeProvider {

	private final Slot[] slots = new Slot[3];

	public ChocoRecipeHandlerCG() {
		this.slots[0] = new ItemSlot(6, 20, 16, 16, true);
		this.slots[1] = new ItemSlot(57, 20, 16, 16, true).setSlotType(SlotType.OUTPUT_SLOT);
		this.slots[2] = new ItemSlot(31, 38, 20, 20).setSlotType(SlotType.MACHINE_SLOT);
	}

	@Override
	public void generateRecipes(RecipeGenerator generator) {
		RecipeTemplate template;
		ItemStack machine = new ItemStack(DCsAppleMilk.filledSoupPan, 1, 0);
		template = generator.createRecipeTemplate(this.slots, machine, "defeatedcrow:textures/gui/craftguidegui.png",
				1, 1, 82, 1);
		Map<Object, ItemStack> recipeGet = RecipeRegisterManager.chocoRecipe.getRecipeList();
		for (Entry<Object, ItemStack> entry : recipeGet.entrySet()) {
			Object obj = entry.getKey();
			ItemStack out = entry.getValue();
			Object[] items = new Object[3];
			items[1] = out;
			items[2] = machine;

			if (obj instanceof ItemStack) {
				items[0] = obj;
			} else if (obj instanceof String) {
				String s = (String) obj;
				ArrayList<ItemStack> ret = OreDictionary.getOres(s);
				if (ret == null || ret.isEmpty())
					continue;
				items[0] = ret;
			}

			generator.addRecipe(template, items);
		}

	}
}
