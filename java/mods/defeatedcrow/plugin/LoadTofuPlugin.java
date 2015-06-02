package mods.defeatedcrow.plugin;

import java.util.ArrayList;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class LoadTofuPlugin {

	public static ItemStack tofuKinu;
	public static ItemStack bucketTounyu;
	public static ItemStack negi;

	public void load() {

		// ここでもOreDicの機能でアイテムを取得しているので、専用クラスにする意味は薄い。
		// 強いて言えば、豆腐クラフト様の未導入時に間違って読み込んでしまうミスを減らす程度か。
		ArrayList<ItemStack> kinu = OreDictionary.getOres("tofuKinu");
		ArrayList<ItemStack> tounyu = OreDictionary.getOres("bucketSoymilk");
		ArrayList<ItemStack> naganegi = OreDictionary.getOres("leek");

		if (kinu.size() > 0) {
			tofuKinu = new ItemStack(kinu.get(0).getItem(), 1, kinu.get(0).getItemDamage());
		}
		if (tounyu.size() > 0) {
			bucketTounyu = new ItemStack(tounyu.get(0).getItem(), 1, tounyu.get(0).getItemDamage());
			if (bucketTounyu != null) {
				LoadModHandler.registerModItems("bucketSoy", bucketTounyu);
			}
		}
		if (naganegi.size() > 0) {
			negi = new ItemStack(naganegi.get(0).getItem(), 1, naganegi.get(0).getItemDamage());
		}
	}

}
