package mods.defeatedcrow.plugin.craftguide;

import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.api.appliance.SoupType;
import mods.defeatedcrow.api.recipe.IFondueRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.ItemStack;
import uristqwerty.CraftGuide.api.ItemSlot;
import uristqwerty.CraftGuide.api.RecipeGenerator;
import uristqwerty.CraftGuide.api.RecipeProvider;
import uristqwerty.CraftGuide.api.RecipeTemplate;
import uristqwerty.CraftGuide.api.Slot;
import uristqwerty.CraftGuide.api.SlotType;

public class FondueRecipeHandlerCG implements RecipeProvider {

	private final Slot[] slots = new Slot[3];

	public FondueRecipeHandlerCG() {
		this.slots[0] = new ItemSlot(6, 20, 16, 16, true);
		this.slots[1] = new ItemSlot(57, 20, 16, 16, true).setSlotType(SlotType.OUTPUT_SLOT);
		this.slots[2] = new ItemSlot(31, 38, 20, 20).setSlotType(SlotType.MACHINE_SLOT);
	}

	@Override
	public void generateRecipes(RecipeGenerator generator) {
		RecipeTemplate template;
		ItemStack machine = new ItemStack(DCsAppleMilk.emptyPanGaiden, 1, 0);
		template = generator.createRecipeTemplate(this.slots, machine, "defeatedcrow:textures/gui/craftguidegui.png",
				1, 1, 82, 1);
		List<IFondueRecipe> recipeGet = (List<IFondueRecipe>) RecipeRegisterManager.fondueRecipe.getRecipeList();
		for (IFondueRecipe recipe : recipeGet) {
			ArrayList<ItemStack> in = recipe.getProcessedInput();
			ItemStack out = recipe.getOutput();
			SoupType bef = recipe.getType();
			if (in.isEmpty() || out == null)
				continue;

			Object[] items = new Object[3];
			items[0] = in;
			items[1] = out;

			if (bef.id != 0) {
				machine = new ItemStack(DCsAppleMilk.filledSoupPan, 1, bef.id);
			}
			items[2] = machine;

			generator.addRecipe(template, items);
		}
	}
}
