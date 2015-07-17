package mods.defeatedcrow.plugin.craftguide;

import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.api.appliance.SoupType;
import mods.defeatedcrow.api.recipe.IFondueSource;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.ItemStack;
import uristqwerty.CraftGuide.api.ItemSlot;
import uristqwerty.CraftGuide.api.RecipeGenerator;
import uristqwerty.CraftGuide.api.RecipeProvider;
import uristqwerty.CraftGuide.api.RecipeTemplate;
import uristqwerty.CraftGuide.api.Slot;
import uristqwerty.CraftGuide.api.SlotType;

public class FondueSourceHandlerCG implements RecipeProvider {

	private final Slot[] slots = new Slot[3];

	public FondueSourceHandlerCG() {
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
		List<IFondueSource> recipeGet = (List<IFondueSource>) RecipeRegisterManager.fondueRecipe.getSourceList();
		for (IFondueSource recipe : recipeGet) {
			ArrayList<ItemStack> in = recipe.getProcessedInput();
			SoupType out = recipe.afterType();
			SoupType bef = recipe.beforeType();
			if (in.isEmpty())
				continue;

			Object[] items = new Object[3];
			items[0] = in;

			ItemStack result = new ItemStack(DCsAppleMilk.emptyPanGaiden, 1, 0);
			if (out.id != 0) {
				result = new ItemStack(DCsAppleMilk.filledSoupPan, 1, out.id);
			}

			if (bef.id != 0) {
				machine = new ItemStack(DCsAppleMilk.filledSoupPan, 1, bef.id);
			}

			items[1] = result;
			items[2] = machine;

			generator.addRecipe(template, items);
		}
	}
}
