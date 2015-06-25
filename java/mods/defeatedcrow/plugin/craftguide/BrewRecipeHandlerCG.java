package mods.defeatedcrow.plugin.craftguide;

import java.util.Map;
import java.util.Map.Entry;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.recipe.BrewingRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import uristqwerty.CraftGuide.api.ItemSlot;
import uristqwerty.CraftGuide.api.LiquidSlot;
import uristqwerty.CraftGuide.api.RecipeGenerator;
import uristqwerty.CraftGuide.api.RecipeProvider;
import uristqwerty.CraftGuide.api.RecipeTemplate;
import uristqwerty.CraftGuide.api.Slot;
import uristqwerty.CraftGuide.api.SlotType;

public class BrewRecipeHandlerCG implements RecipeProvider {

	private final Slot[] slots = new Slot[3];

	public BrewRecipeHandlerCG() {
		this.slots[0] = new LiquidSlot(6, 20);
		this.slots[1] = new LiquidSlot(57, 20).setSlotType(SlotType.OUTPUT_SLOT);
		this.slots[2] = new ItemSlot(31, 38, 20, 20).setSlotType(SlotType.MACHINE_SLOT);
	}

	@Override
	public void generateRecipes(RecipeGenerator generator) {
		RecipeTemplate template;
		ItemStack machine = new ItemStack(DCsAppleMilk.barrel, 1, 0);
		template = generator.createRecipeTemplate(this.slots, machine, "defeatedcrow:textures/gui/craftguidegui.png",
				1, 1, 82, 1);
		Map<Fluid, Fluid> recipeGet = BrewingRecipe.recipe;
		for (Entry<Fluid, Fluid> entry : recipeGet.entrySet()) {
			Fluid in = entry.getKey();
			Fluid out = entry.getValue();
			if (in == null || out == null)
				continue;

			Object[] items = new Object[3];
			items[0] = new FluidStack(in, 1000);
			items[1] = new FluidStack(out, 1000);
			items[2] = machine;

			generator.addRecipe(template, items);
		}

	}

}
