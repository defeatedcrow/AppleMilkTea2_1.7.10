package mods.defeatedcrow.recipe;

import java.util.ArrayList;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.PropertyHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

/*
 * ここでは他MODの鉱石辞書登録内容を走査し、
 * クラッシャへの粉砕レシピ登録や、奇妙なボタからのドロップ登録を行う。
 */
public class OreCrushRecipe {

	private OreCrushRecipe() {
	}

	// 土砂 55%
	public static ArrayList<ItemStack> tier1 = new ArrayList<ItemStack>();
	// 鉱石ナゲット 30%
	public static ArrayList<ItemStack> tier2 = new ArrayList<ItemStack>();
	// 鉱石砂 10%
	public static ArrayList<ItemStack> tier3 = new ArrayList<ItemStack>();
	// ジェム 4%
	public static ArrayList<ItemStack> tier4 = new ArrayList<ItemStack>();
	// レアアイテム 1%
	public static ArrayList<ItemStack> tier5 = new ArrayList<ItemStack>();

	public static void searchOreName() {
		String[] ores1 = new String[] {
				"Iron",
				"Tin",
				"Copper",
				"Zinc" };
		String[] ores2 = new String[] {
				"Silver",
				"Lead",
				"Gold",
				"Redstone" };
		String[] ores3 = new String[] {
				"Nickel",
				"Platinum",
				"Magnetite" };
		String[] gems = new String[] {
				"Coal",
				"Quartz" };
		String[] gems2 = new String[] {
				"Diamond",
				"Ruby",
				"Sapphire",
				"Peridot",
				"Emerald" };

		int[] d = PropertyHandler.getDustGen();

		// その1
		for (int i = 0; i < ores1.length; i++) {
			String ore = "ore" + ores1[i];
			ItemStack nugget = null;
			ItemStack dust = null;

			if (OreDictionary.getOres("nugget" + ores1[i]) != null
					&& !OreDictionary.getOres("nugget" + ores1[i]).isEmpty())
				nugget = OreDictionary.getOres("nugget" + ores1[i]).get(0);
			if (OreDictionary.getOres("dust" + ores1[i]) != null && !OreDictionary.getOres("dust" + ores1[i]).isEmpty())
				dust = OreDictionary.getOres("dust" + ores1[i]).get(0);

			if (OreDictionary.getOres(ore) != null && !OreDictionary.getOres(ore).isEmpty() && dust != null) {
				RecipeRegisterManager.processorRecipe.addRecipe(
						new ItemStack(dust.getItem(), d[0], dust.getItemDamage()), false, 1,
						new ItemStack(dust.getItem(), d[1], dust.getItemDamage()), 0.5F, new Object[] { ore });
			}

			if (nugget != null) {
				tier2.add(nugget);
			}
		}

		// その2
		for (int i = 0; i < ores2.length; i++) {
			String ore = "ore" + ores2[i];
			ItemStack dust = null;
			ItemStack ingot = null;
			if (OreDictionary.getOres("dust" + ores2[i]) != null && !OreDictionary.getOres("dust" + ores2[i]).isEmpty())
				dust = OreDictionary.getOres("dust" + ores2[i]).get(0);

			if (OreDictionary.getOres(ore) != null && !OreDictionary.getOres(ore).isEmpty() && dust != null) {
				RecipeRegisterManager.processorRecipe.addRecipe(
						new ItemStack(dust.getItem(), d[0], dust.getItemDamage()), false, 2, new ItemStack(
								DCsAppleMilk.strangeSlag, 1, 0), 0.5F, new Object[] { ore });
			}

			if (OreDictionary.getOres("ingot" + ores2[i]) != null
					&& !OreDictionary.getOres("ingot" + ores2[i]).isEmpty()) {
				ingot = OreDictionary.getOres("ingot" + ores2[i]).get(0);
			}

			if (dust != null) {
				tier3.add(dust);
			}
		}

		// その3
		for (int i = 0; i < ores3.length; i++) {
			String ore = "ore" + ores3[i];
			ItemStack dust = null;
			ItemStack ingot = null;
			if (OreDictionary.getOres("dust" + ores3[i]) != null && !OreDictionary.getOres("dust" + ores3[i]).isEmpty())
				dust = OreDictionary.getOres("dust" + ores3[i]).get(0);

			if (OreDictionary.getOres(ore) != null && !OreDictionary.getOres(ore).isEmpty() && dust != null) {
				RecipeRegisterManager.processorRecipe.addRecipe(
						new ItemStack(dust.getItem(), d[0], dust.getItemDamage()), false, 3, new ItemStack(
								DCsAppleMilk.strangeSlag, 1, 0), 0.5F, new Object[] { ore });
			}

			if (OreDictionary.getOres("ingot" + ores3[i]) != null
					&& !OreDictionary.getOres("ingot" + ores3[i]).isEmpty()) {
				ingot = OreDictionary.getOres("ingot" + ores3[i]).get(0);
			}

			if (dust != null) {
				tier3.add(dust);
			}
		}

		// その4
		for (int i = 0; i < gems.length; i++) {
			String ore = "ore" + gems[i];
			ItemStack gem = null;
			if (OreDictionary.getOres("gem" + gems[i]) != null && !OreDictionary.getOres("gem" + gems[i]).isEmpty())
				gem = OreDictionary.getOres("gem" + gems[i]).get(0);

			if (OreDictionary.getOres(ore) != null && !OreDictionary.getOres(ore).isEmpty() && gem != null) {
				RecipeRegisterManager.processorRecipe.addRecipe(
						new ItemStack(gem.getItem(), d[0], gem.getItemDamage()), false, 1, new ItemStack(gem.getItem(),
								1, gem.getItemDamage()), 0.5F, new Object[] { ore });
			}

			if (gem != null) {
				tier2.add(gem);
			}
		}

		// その5
		for (int i = 0; i < gems2.length; i++) {
			String ore = "ore" + gems2[i];
			ItemStack gem = null;
			if (OreDictionary.getOres("gem" + gems2[i]) != null && !OreDictionary.getOres("gem" + gems2[i]).isEmpty())
				gem = OreDictionary.getOres("gem" + gems2[i]).get(0);

			if (OreDictionary.getOres(ore) != null && !OreDictionary.getOres(ore).isEmpty() && gem != null) {
				RecipeRegisterManager.processorRecipe.addRecipe(
						new ItemStack(gem.getItem(), d[0], gem.getItemDamage()), false, 3, new ItemStack(
								DCsAppleMilk.strangeSlag, 1, 0), 0.5F, new Object[] { ore });
			}

			if (gem != null) {
				tier4.add(gem);
			}
		}

		// 残りのボタ枠を埋める
		tier1.add(new ItemStack(Items.bone));
		tier1.add(new ItemStack(Items.clay_ball));
		tier1.add(new ItemStack(Blocks.dirt));
		tier1.add(new ItemStack(Blocks.cobblestone));
		tier1.add(new ItemStack(Blocks.gravel));

		tier2.add(new ItemStack(Blocks.end_stone));
		tier2.add(new ItemStack(Blocks.ice));
		tier2.add(new ItemStack(Items.flint));

		tier3.add(new ItemStack(Items.glowstone_dust));

		tier4.add(new ItemStack(DCsAppleMilk.chalcedony));
		tier4.add(new ItemStack(Items.ender_pearl));

		tier5.add(new ItemStack(Items.spawn_egg, 1, 120));
		tier5.add(new ItemStack(DCsAppleMilk.fossilScale));

		// ボタ山設定以外のdust取得
		// ingotが存在しないと焼くレシピも存在しない
		String[] ores4 = new String[] {
				"Iron",
				"Tin",
				"Copper",
				"Silver",
				"Lead",
				"Gold",
				"Nickel",
				"Platinum" };
		for (int i = 0; i < ores4.length; i++) {
			if (OreDictionary.getOres("ingot" + ores4[i]) != null
					&& !OreDictionary.getOres("ingot" + ores4[i]).isEmpty()) {
				ItemStack ingot = OreDictionary.getOres("ingot" + ores4[i]).get(0);
				GameRegistry.addSmelting(new ItemStack(DCsAppleMilk.oreDust, 1, i), new ItemStack(ingot.getItem(), 1,
						ingot.getItemDamage()), 0.3F);
			} else// なかったら鉄になる
			{
				GameRegistry.addSmelting(new ItemStack(DCsAppleMilk.oreDust, 1, i), new ItemStack(Items.iron_ingot),
						0.3F);
			}
		}

	}

}
