package mods.defeatedcrow.plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import ruby.bamboo.api.crafting.CookingRegistory;
import ruby.bamboo.api.crafting.grind.GrindRegistory;
import cpw.mods.fml.common.registry.GameRegistry;

public class LoadBambooPlugin {

	private static ArrayList<ItemStack> baskets = new ArrayList<ItemStack>();

	public static List<ItemStack> getBasket() {
		return Collections.unmodifiableList(baskets);
	}

	public static int isBasketItem(ItemStack item) {
		if (item == null || baskets.isEmpty())
			return -1;
		int num = -1;
		for (int i = 0; i < baskets.size(); i++) {
			if (baskets.get(i).getItem() == item.getItem()) {
				if (baskets.get(i).getItemDamage() == item.getItemDamage()) {
					num = i;
					break;
				} else if (baskets.get(i).getItemDamage() == OreDictionary.WILDCARD_VALUE) {
					num = i;
					break;
				}
			}
		}

		return num;

	}

	public static void addJapaneseBowlContainer(ItemStack item) {
		if (item != null && item.getItem() != null) {
			baskets.add(item);
		}
	}

	// 専用クラスにお引っ越し
	// BambooMod様のアイテムのうち、鉱石辞書登録されていないものはココを使う
	public void loadBambooItems() {
		try {
			// Item item = Util.getModItem("BambooMod", "Bamboo:rawrice");
			Object obj = Class.forName("ruby.bamboo.BambooInit").getField("rawrice").get(null);
			AMTLogger.debugInfo("Current get Number : " + obj.toString());
			if (obj != null && obj instanceof Item) {
				ItemStack registerItem = new ItemStack((Item) obj, 1, 0);
				if (LoadModHandler.registerModItems("rice", registerItem)) {
					OreDictionary.registerOre("cropRice", registerItem);
					AMTLogger.debugInfo("Succeeded to get rawrice");
				}
			}
			Item item2 = Util.getModItem("BambooMod", "bamboobasket");
			if (item2 != null) {
				ItemStack registerItem2 = new ItemStack(item2, 1, 0);
				if (LoadModHandler.registerModItems("bambooBasket", registerItem2)) {
					AMTLogger.debugInfo("Succeeded to get bamboobasket");
				}

				if (registerItem2 != null) {
					baskets.add(registerItem2);
				}
			}
			Item item3 = Util.getModItem("BambooMod", "decoCarpet");
			if (item3 != null) {
				ItemStack registerItem3 = new ItemStack(item3, 1, 0);
				if (LoadModHandler.registerModItems("strawCarpet", registerItem3)) {
					AMTLogger.debugInfo("Succeeded to get decoCarpet");
				}

				if (LoadModHandler.getItem("straw") != null) {
					GameRegistry.addRecipe(registerItem3,
							new Object[] { "XXX", Character.valueOf('X'), LoadModHandler.getItem("straw") });
				}
			}
			Item item4 = Util.getModItem("BambooMod", "sakuraLog");
			if (item4 != null) {
				ItemStack registerItem4 = new ItemStack(item4, 1, 0);
				if (LoadModHandler.registerModItems("sakuraWood", registerItem4)) {
					AMTLogger.debugInfo("Succeeded to get sakuraLog");
				}

				if (registerItem4 != null) {
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.woodBox, 1, 8), new Object[] {
							"XXX", "XXX", "XXX", Character.valueOf('X'), registerItem4 }));

					GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(item4, 9, 0),
							new Object[] { new ItemStack(DCsAppleMilk.woodBox, 1, 8) }));
				}
			}
			Block item5 = Util.getModBlock("BambooMod", "campfire");
			if (item5 != null) {
				ItemStack registerItem5 = new ItemStack(item5, 1, 0);
				if (LoadModHandler.registerModItems("furneceBlock", registerItem5)) {
					RecipeRegisterManager.panRecipe.registerHeatSource(item5, -1);
					RecipeRegisterManager.plateRecipe.registerHeatSource(item5, -1);
					AMTLogger.debugInfo("Succeeded to get campfire");
				}
			}
			Item item6 = Util.getModItem("BambooMod", "itemseaweed");
			if (item6 != null) {
				ItemStack registerItem6 = new ItemStack(item6, 1, 0);
				if (LoadModHandler.registerModItems("seaWeed", registerItem6)) {
					AMTLogger.debugInfo("Succeeded to get seaweed");
				}
			}
			Item item7 = Util.getModItem("BambooMod", "itemtomato");
			if (item7 != null) {
				ItemStack registerItem7 = new ItemStack(item7, 1, 0);
				if (LoadModHandler.registerModItems("tomato", registerItem7)) {
					OreDictionary.registerOre("cropTomato", registerItem7);
					AMTLogger.debugInfo("Succeeded to get tomato");
				}
			}
		} catch (Exception e) {
			AMTLogger.debugInfo("Failed to register ModItems");
			e.printStackTrace(System.err);
		}
	}

	public static void loadBambooRecipes(boolean flag) // BambooMod様の石臼、囲炉裏にレシピを追加
	{
		if (!flag)
			return;

		try {
			GrindRegistory.addRecipe(new ItemStack(DCsAppleMilk.foodTea, 2, 0), new ItemStack(DCsAppleMilk.leafTea));

			GrindRegistory.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 2, 6), new ItemStack(DCsAppleMilk.clam));

			GrindRegistory.addRecipe(new ItemStack(DCsAppleMilk.dustWood, 1, 1), new ItemStack(Items.coal, 1, 1));

			GrindRegistory.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 2, 4), new ItemStack(Blocks.ice, 1, 0));

			CookingRegistory.addShapelessRecipe(new ItemStack(DCsAppleMilk.appleSandwich, 3, 0), new ItemStack(
					Items.apple), "foodDough");

			CookingRegistory.addShapelessRecipe(new ItemStack(DCsAppleMilk.appleSandwich, 3, 1), new ItemStack(
					Items.egg), "foodDough");

			CookingRegistory.addShapelessRecipe(new ItemStack(DCsAppleMilk.appleTart, 2, 0),
					new ItemStack(Items.apple), "foodDough", "foodSugar");

			CookingRegistory.addShapelessRecipe(new ItemStack(DCsAppleMilk.appleTart, 2, 2), "cropYuzu", "bucketMilk",
					"foodSugar", new ItemStack(Items.egg));

			CookingRegistory.addShapelessRecipe(new ItemStack(DCsAppleMilk.appleTart, 2, 3), "cropApricot",
					"bucketMilk", "foodSugar", new ItemStack(Items.egg));

			CookingRegistory.addShapelessRecipe(new ItemStack(DCsAppleMilk.appleSandwich, 3, 2), "foodSugar",
					"foodDough", new ItemStack(DCsAppleMilk.leafTea, 1, 2));

			CookingRegistory.addShapelessRecipe(new ItemStack(DCsAppleMilk.appleSandwich, 3, 3), "foodSugar",
					"foodDough", new ItemStack(DCsAppleMilk.leafTea, 1, 3));
		} catch (Exception e) {
			AMTLogger.debugInfo("Failed to register Bamboo recipes");
			e.printStackTrace(System.err);
		}
	}

}
