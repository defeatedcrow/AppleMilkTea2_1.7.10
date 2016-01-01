package mods.defeatedcrow.plugin.craftguide;

import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.recipe.ProcessorRecipeRegister.ProcessorRecipe;
import net.minecraft.item.ItemStack;
import uristqwerty.CraftGuide.api.ItemSlot;
import uristqwerty.CraftGuide.api.RecipeGenerator;
import uristqwerty.CraftGuide.api.RecipeProvider;
import uristqwerty.CraftGuide.api.RecipeTemplate;
import uristqwerty.CraftGuide.api.Slot;
import uristqwerty.CraftGuide.api.SlotType;

public class ProsessorRecipeHandlerCG implements RecipeProvider {

	private final Slot[] slots = new Slot[12];

	public ProsessorRecipeHandlerCG() {
		this.slots[0] = new ItemSlot(58, 18, 16, 16, true).setSlotType(SlotType.OUTPUT_SLOT);
		this.slots[1] = new ItemSlot(58, 35, 16, 16, true).setSlotType(SlotType.OUTPUT_SLOT);
		this.slots[2] = new ItemSlot(58, 2, 20, 20).setSlotType(SlotType.MACHINE_SLOT);

		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 3; k++) {
				this.slots[3 + i + k * 3] = new ItemSlot(i * 18 + 2, k * 18 + 2, 16, 16, true);
			}
		}

	}

	@Override
	public void generateRecipes(RecipeGenerator generator) {

		RecipeTemplate template;
		ItemStack machine = new ItemStack(DCsAppleMilk.processor, 1, 0);
		template = generator.createRecipeTemplate(this.slots, machine, "defeatedcrow:textures/gui/craftguidegui.png",
				1, 120, 82, 120);
		List<ProcessorRecipe> recipeGet = (List<ProcessorRecipe>) RecipeRegisterManager.processorRecipe.getRecipes();
		for (ProcessorRecipe recipe : recipeGet) {
			boolean food = recipe.isFoodRecipe();
			if (!food)
				continue;
			ItemStack output = recipe.getOutput();
			ItemStack second = recipe.getSecondary();
			Object[] items = new Object[12];
			items[0] = output;
			items[1] = second;
			items[2] = machine;
			int i = 3;
			for (Object obj : recipe.getProcessedInput()) {
				if (i > 11)
					break;
				ItemStack item;
				if (obj instanceof ItemStack) {
					item = (ItemStack) obj;
					items[i] = item;
				} else if (obj instanceof ArrayList) {
					ArrayList<ItemStack> ret = (ArrayList<ItemStack>) obj;
					if (ret == null || ret.isEmpty())
						continue;
					items[i] = ret;
				}
				i++;
			}
			generator.addRecipe(template, items);
		}

	}
}
