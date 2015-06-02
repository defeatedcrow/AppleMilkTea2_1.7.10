package mods.defeatedcrow.recipe;

import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.api.recipe.IChargeIce;
import mods.defeatedcrow.api.recipe.IIceRecipe;
import mods.defeatedcrow.api.recipe.IIceRecipeRegister;
import mods.defeatedcrow.api.recipe.ITeaRecipeRegister;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import net.minecraft.item.ItemStack;

public class IceRecipeRegister implements IIceRecipeRegister {

	private static List<IceRecipe> recipes;
	private static List<ChargeIceItem> chargeItems;

	public IceRecipeRegister() {
		this.recipes = new ArrayList<IceRecipe>();
		this.chargeItems = new ArrayList<ChargeIceItem>();
	}

	public IIceRecipeRegister instance() {
		return RecipeRegisterManager.iceRecipe;
	}

	@Override
	public List<IceRecipe> getRecipeList() {
		return this.recipes;
	}

	@Override
	public List<ChargeIceItem> getChargeItemList() {
		return this.chargeItems;
	}

	@Override
	public IceRecipe getRecipe(ItemStack item) {
		if (item == null)
			return null;
		for (IceRecipe recipe : this.recipes) {
			if (this.isItemEqual(item, recipe.getInput())) {
				return recipe;
			}
		}
		return null;
	}

	@Override
	public int getChargeAmount(ItemStack item) {
		if (item == null)
			return 0;
		for (ChargeIceItem chargeable : this.chargeItems) {
			if (this.isItemEqual(item, chargeable.getItem())) {
				return chargeable.chargeAmount();
			}
		}
		return 0;
	}

	private boolean isItemEqual(ItemStack a, ItemStack b) {
		boolean flag = false;
		if (a.getItem() == b.getItem()) {
			if (a.getItemDamage() == b.getItemDamage()) {
				flag = true;
			} else if (b.getItemDamage() == Short.MAX_VALUE) {
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public void register(ItemStack input, ItemStack output) {
		if (input != null) {
			registerCanLeave(input, output, null);
		}
	}

	@Override
	public void registerCanLeave(ItemStack input, ItemStack output, ItemStack leaveStack) {
		if (input != null) {
			this.recipes.add(new IceRecipe(input, output, leaveStack));
		}

		String container = "null";
		if (leaveStack != null)
			container = leaveStack.getDisplayName();
		AMTLogger.debugInfo("Add IceMaker Recipe: input " + input.getDisplayName() + ", output "
				+ output.getDisplayName() + ", container " + container);
	}

	@Override
	public void registerCharger(ItemStack input, int val) {
		if (input != null && val > 0) {
			this.chargeItems.add(new ChargeIceItem(input, val));
		}
		AMTLogger.debugInfo("Add IceMaker chargeable item: input " + input.getDisplayName() + ", amount " + val);
	}

	public class IceRecipe implements IIceRecipe {

		private final ItemStack input;
		private final ItemStack output;
		private final ItemStack container;

		private final int damage = Short.MAX_VALUE;

		public IceRecipe(ItemStack inputItem, ItemStack outputItem, ItemStack containerItem) {
			this.input = inputItem;
			this.output = outputItem;
			this.container = containerItem;
		}

		@Override
		public ItemStack getInput() {
			return this.input.copy();
		}

		@Override
		public ItemStack getOutput() {
			if (this.output == null)
				return null;
			return this.output.copy();
		}

		@Override
		public ItemStack getContainer() {
			if (this.container == null)
				return null;
			return this.container.copy();
		}

	}

	public class ChargeIceItem implements IChargeIce {

		private final ItemStack input;
		private final int amount;

		public ChargeIceItem(ItemStack item, int val) {
			this.input = item;
			this.amount = val;
		}

		@Override
		public int chargeAmount() {
			return this.amount;
		}

		@Override
		public ItemStack getItem() {
			if (this.input == null)
				return null;
			return this.input;
		}

	}

}
