package mods.defeatedcrow.plugin.craftguide;

import java.util.List;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.recipe.PlateRecipeRegister.PlateRecipe;
import net.minecraft.item.ItemStack;
import uristqwerty.CraftGuide.api.ItemSlot;
import uristqwerty.CraftGuide.api.RecipeGenerator;
import uristqwerty.CraftGuide.api.RecipeProvider;
import uristqwerty.CraftGuide.api.RecipeTemplate;
import uristqwerty.CraftGuide.api.Slot;
import uristqwerty.CraftGuide.api.SlotType;

public class TeppanRecipeHandlerCG implements RecipeProvider {

	private final Slot[] slots = new Slot[4];

	public TeppanRecipeHandlerCG() {
		this.slots[0] = new ItemSlot(6, 21, 16, 16, true);
		this.slots[1] = new ItemSlot(58, 21, 16, 16, true).setSlotType(SlotType.OUTPUT_SLOT);
		this.slots[2] = new ItemSlot(32, 38, 16, 16, true).setSlotType(SlotType.OUTPUT_SLOT);
		this.slots[3] = new ItemSlot(6, 38, 20, 20).setSlotType(SlotType.MACHINE_SLOT);
	}

	@Override
	public void generateRecipes(RecipeGenerator generator) {

		RecipeTemplate template;
		ItemStack machine = new ItemStack(DCsAppleMilk.teppanII, 1, 0);
		template = generator.createRecipeTemplate(this.slots, machine, "defeatedcrow:textures/gui/craftguidegui.png",
				1, 1, 82, 1);
		List<PlateRecipe> recipeGet = (List<PlateRecipe>) RecipeRegisterManager.plateRecipe.getRecipeList();
		for (PlateRecipe recipe : recipeGet) {
			ItemStack input = recipe.getInput();
			ItemStack output = recipe.getOutput();
			ItemStack mode = recipe.useOvenRecipe() ? new ItemStack(DCsAppleMilk.dummyTeppan, 1, 0) : new ItemStack(
					DCsAppleMilk.dummyTeppan, 1, 1);
			Object[] items = new Object[4];
			items[0] = input;
			items[1] = output;
			items[2] = mode;
			items[3] = machine;
			generator.addRecipe(template, items);
		}
	}

}
