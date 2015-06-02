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

public class IceRecipeHandlerCG implements RecipeProvider {

	private final Slot[] slots = new Slot[4];

	public IceRecipeHandlerCG() {
		this.slots[0] = new ItemSlot(6, 21, 16, 16, true);
		this.slots[1] = new ItemSlot(58, 10, 16, 16, true).setSlotType(SlotType.OUTPUT_SLOT);
		this.slots[2] = new ItemSlot(58, 32, 16, 16, true).setSlotType(SlotType.OUTPUT_SLOT);
		this.slots[3] = new ItemSlot(32, 38, 20, 20).setSlotType(SlotType.MACHINE_SLOT);
	}

	@Override
	public void generateRecipes(RecipeGenerator generator) {

		RecipeTemplate template;
		ItemStack iceMaker = new ItemStack(DCsAppleMilk.iceMaker, 1, 0);
		template = generator.createRecipeTemplate(this.slots, iceMaker, "defeatedcrow:textures/gui/craftguidegui.png",
				1, 60, 82, 60);
		HashMap<ItemStack, ItemStack[]> recipeGet = RegisteredRecipeGet.iceRecipeList;
		Iterator<Entry<ItemStack, ItemStack[]>> inputs = recipeGet.entrySet().iterator();
		while (inputs.hasNext()) {
			Entry<ItemStack, ItemStack[]> recipe = inputs.next();
			ItemStack[] outputs = recipe.getValue();
			Object[] items = new Object[4];
			items[0] = recipe.getKey();
			items[1] = outputs[0];
			items[2] = outputs[1];
			items[3] = iceMaker;
			generator.addRecipe(template, items);
		}

	}

}
