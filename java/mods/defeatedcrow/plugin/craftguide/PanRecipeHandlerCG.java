package mods.defeatedcrow.plugin.craftguide;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import mods.defeatedcrow.recipe.RegisteredRecipeGet;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.ItemStack;
import uristqwerty.CraftGuide.api.ItemSlot;
import uristqwerty.CraftGuide.api.RecipeGenerator;
import uristqwerty.CraftGuide.api.RecipeProvider;
import uristqwerty.CraftGuide.api.RecipeTemplate;
import uristqwerty.CraftGuide.api.Slot;
import uristqwerty.CraftGuide.api.SlotType;

public class PanRecipeHandlerCG implements RecipeProvider {

	private final Slot[] slots = new Slot[3];

	public PanRecipeHandlerCG() {
		this.slots[0] = new ItemSlot(6, 21, 16, 16, true);
		this.slots[1] = new ItemSlot(58, 21, 16, 16, true).setSlotType(SlotType.OUTPUT_SLOT);
		this.slots[2] = new ItemSlot(32, 38, 20, 20).setSlotType(SlotType.MACHINE_SLOT);
	}

	@Override
	public void generateRecipes(RecipeGenerator generator) {

		RecipeTemplate template;
		ItemStack pan = new ItemStack(DCsAppleMilk.emptyPanGaiden, 1, 0);
		template = generator.createRecipeTemplate(this.slots, null, "defeatedcrow:textures/gui/craftguidegui.png", 1,
				1, 82, 1);
		HashMap<ItemStack, ItemStack> recipeGet = RegisteredRecipeGet.panRecipeList;
		Iterator<Entry<ItemStack, ItemStack>> inputs = recipeGet.entrySet().iterator();
		while (inputs.hasNext()) {
			Entry<ItemStack, ItemStack> recipe = inputs.next();
			Object[] items = new Object[3];
			items[0] = recipe.getKey();
			items[1] = recipe.getValue();
			items[2] = pan;
			generator.addRecipe(template, items);
		}

	}

}
