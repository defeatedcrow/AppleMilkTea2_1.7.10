package mods.defeatedcrow.common.entity;

import java.util.Random;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

// 嫁。倉庫番。圧縮コンテナと電池、玉髄製品の販売。作物系圧縮コンテナのと、火打ち石の買い取り。
public class VillagerYome implements IVillageTradeHandler {

	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {

		if (villager.getProfession() == DCsConfig.villagerRecipe2ID) {
			// 原木箱。アカシアとダークオーク。
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 1), new ItemStack(DCsAppleMilk.woodBox, 1,
					11)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 1), new ItemStack(DCsAppleMilk.woodBox, 1,
					12)));
			// 木炭箱
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 3), new ItemStack(DCsAppleMilk.charcoalBox,
					1, 0)));
			// カシス、椿、柚子のダンボール
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 2), new ItemStack(DCsAppleMilk.cardboard, 1,
					1)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 2), new ItemStack(DCsAppleMilk.cardboard, 1,
					2)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 2), new ItemStack(DCsAppleMilk.cardboard, 1,
					3)));

			// カルセドニー製品
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 1), new ItemStack(DCsAppleMilk.chalcedony,
					1, 0)));
			recipeList
					.add(new MerchantRecipe(new ItemStack(Items.emerald, 5), new ItemStack(DCsAppleMilk.cLamp, 1, 9)));
			recipeList
					.add(new MerchantRecipe(new ItemStack(Items.emerald, 5), new ItemStack(DCsAppleMilk.cLamp, 1, 10)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 2), new ItemStack(
					DCsAppleMilk.chalcedonyKnife, 1, 0)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 3), new ItemStack(
					DCsAppleMilk.chalcedonyHammer, 1, 0)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 2),
					new ItemStack(DCsAppleMilk.monocle, 1, 0)));

			// 電池
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 2), new ItemStack(DCsAppleMilk.batteryItem,
					1, 0)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 6), new ItemStack(DCsAppleMilk.batteryItem,
					5, 0)));

			// 精油
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 5), new ItemStack(DCsAppleMilk.essentialOil,
					1, 8)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 5), new ItemStack(DCsAppleMilk.essentialOil,
					1, 9)));

			// 買い取りはふつうのコンテナ類、火打ち石
			recipeList.add(new MerchantRecipe(new ItemStack(DCsAppleMilk.woodBox, 3, 0),
					new ItemStack(Items.emerald, 2)));
			recipeList.add(new MerchantRecipe(new ItemStack(DCsAppleMilk.vegiBag, 3, 4),
					new ItemStack(Items.emerald, 2)));
			recipeList.add(new MerchantRecipe(new ItemStack(DCsAppleMilk.vegiBag, 3, 5),
					new ItemStack(Items.emerald, 2)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.flint, 4, 0), new ItemStack(Items.emerald, 1)));

			recipeList.add(new MerchantRecipe(new ItemStack(DCsAppleMilk.bottleCamOil, 1, 0), new ItemStack(
					Items.emerald, 1)));

		}

	}

}
