package mods.defeatedcrow.plugin;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.init.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class LoadExBucketPlugin {

	public static ItemStack woodenBucketMilk;
	public static ItemStack goldenBucketMilk;

	public void load() {
		try {
			Item item = Util.getModItem("AndanteMod_ExBucket", "ExBucket:WoodenBucketMilk");
			if (item != null) {
				woodenBucketMilk = new ItemStack(item, 1, 0);
				if (LoadModHandler.registerModItems("containerMilk", woodenBucketMilk)) {
					AMTLogger.debugInfo("Succeeded to get WoodenBucketMIlk");
				}

				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 1),
						new Object[] { new ItemStack(DCsAppleMilk.DCgrater, 1, 32767), new ItemStack(Items.fish, 1),
								new ItemStack(Items.carrot, 1), new ItemStack(Items.potato, 1), woodenBucketMilk }));

				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 6),
						new Object[] { "toolGrater", new ItemStack(Blocks.pumpkin, 1, 0), woodenBucketMilk }));

				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 8),
						new Object[] { "toolGrater", woodenBucketMilk, "dustSugar", new ItemStack(Items.dye, 9, 3),
								new ItemStack(Items.dye, 9, 3) }));

				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 4),
						new Object[] { new ItemStack(DCsAppleMilk.mincedFoods, 1, 8), woodenBucketMilk }));

				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 0),
						new Object[] { woodenBucketMilk, "dustSugar" }));

				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (112 + 8)),
						new Object[] { "XXX", "XZX", "XXX", Character.valueOf('Z'),
								new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 0), Character.valueOf('X'),
								woodenBucketMilk }));

				// ピニャコラーダのアナザーレシピも追加
				ItemStack gummiPine = LoadModHandler.getItem("pineapple");

				if (gummiPine != null) {
					GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 6),
							new Object[] { "bottleRum", gummiPine, woodenBucketMilk, "foodCrushedIce" }));
				}

			}
			Item item2 = Util.getModItem("AndanteMod_ExBucket", "ExBucket:GoldenBucketMilk");
			if (item2 != null) {
				goldenBucketMilk = new ItemStack(item2, 1, 32767);
				if (LoadModHandler.registerModItems("containerMilk", goldenBucketMilk)) {
					AMTLogger.debugInfo("Succeeded to get GoldenBucketMilk");
				}

				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 1),
						new Object[] { "toolGrater", new ItemStack(Items.fish, 1), new ItemStack(Items.carrot, 1),
								new ItemStack(Items.potato, 1), goldenBucketMilk }));

				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 6),
						new Object[] { "toolGrater", new ItemStack(Blocks.pumpkin, 1, 0), goldenBucketMilk }));

				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.mincedFoods, 1, 8),
						new Object[] { "toolGrater", goldenBucketMilk, "dustSugar", new ItemStack(Items.dye, 9, 3),
								new ItemStack(Items.dye, 9, 3) }));

				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.gratedApple, 1, 4),
						new Object[] { new ItemStack(DCsAppleMilk.mincedFoods, 1, 8), goldenBucketMilk }));

				GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 0),
						new Object[] { goldenBucketMilk, "dustSugar" }));

				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (112 + 8)),
						new Object[] { "XXX", "XZX", "XXX", Character.valueOf('Z'),
								new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 0), Character.valueOf('X'),
								goldenBucketMilk }));

				// ピニャコラーダのアナザーレシピも追加
				ItemStack gummiPine = LoadModHandler.getItem("pineapple");

				if (gummiPine != null) {
					GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(DCsAppleMilk.cocktail, 1, 6),
							new Object[] { "bottleRum", gummiPine, goldenBucketMilk, "foodCrushedIce" }));
				}
			}
			// 以下は別のレシピ用の水バケツ
			Item item3 = Util.getModItem("AndanteMod_ExBucket", "ExBucket:WoodenBucketWater");
			if (item3 != null) {
				if (LoadModHandler.registerModItems("containerWater", new ItemStack(item3, 1, 0))) {
					AMTLogger.debugInfo("Succeeded to get WoodenBucketWater");
				}
			}
			Item item4 = Util.getModItem("AndanteMod_ExBucket", "ExBucket:GoldenBucketWater");
			if (item4 != null) {
				if (LoadModHandler.registerModItems("containerWater", new ItemStack(item4, 1, 32767))) {
					AMTLogger.debugInfo("Succeeded to get GoldenBucketWater");
				}
			}
		} catch (Exception e) {
			AMTLogger.debugInfo("Failed to register ModItems");
			e.printStackTrace(System.err);
		}
	}

}
