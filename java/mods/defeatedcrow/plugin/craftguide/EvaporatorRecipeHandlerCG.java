package mods.defeatedcrow.plugin.craftguide;

import java.util.List;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.recipe.EvaporatorRecipeRegister.EvaporatorRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import uristqwerty.CraftGuide.api.ItemSlot;
import uristqwerty.CraftGuide.api.LiquidSlot;
import uristqwerty.CraftGuide.api.RecipeGenerator;
import uristqwerty.CraftGuide.api.RecipeProvider;
import uristqwerty.CraftGuide.api.RecipeTemplate;
import uristqwerty.CraftGuide.api.Slot;
import uristqwerty.CraftGuide.api.SlotType;

public class EvaporatorRecipeHandlerCG implements RecipeProvider {

	private final Slot[] slots = new Slot[4];

	public EvaporatorRecipeHandlerCG() {
		this.slots[0] = new ItemSlot(6, 21, 16, 16, true);
		this.slots[1] = new ItemSlot(54, 32, 16, 16, true).setSlotType(SlotType.OUTPUT_SLOT);
		this.slots[2] = new LiquidSlot(54, 8).setSlotType(SlotType.OUTPUT_SLOT);
		this.slots[3] = new ItemSlot(32, 38, 20, 20).setSlotType(SlotType.MACHINE_SLOT);
	}

	@Override
	public void generateRecipes(RecipeGenerator generator) {

		RecipeTemplate template;
		ItemStack machine = new ItemStack(DCsAppleMilk.evaporator, 1, 0);
		template = generator.createRecipeTemplate(this.slots, machine, "defeatedcrow:textures/gui/craftguidegui.png",
				1, 179, 82, 179);
		List<EvaporatorRecipe> recipeGet = (List<EvaporatorRecipe>) RecipeRegisterManager.evaporatorRecipe
				.getRecipeList();
		for (EvaporatorRecipe recipe : recipeGet) {
			ItemStack input = recipe.getInput();
			ItemStack output = recipe.getOutput();
			FluidStack second = recipe.getSecondary();
			Object[] items = new Object[4];
			items[0] = input;
			items[1] = output;
			items[2] = second;
			items[3] = machine;
			generator.addRecipe(template, items);
		}

	}
}
