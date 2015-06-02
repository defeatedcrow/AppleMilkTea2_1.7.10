package mods.defeatedcrow.common.entity;

import java.util.Random;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

// カフェマスター。食べ物と酒の販売、材料や食器の買い取り。
public class VillagerCafe implements IVillageTradeHandler {

	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {

		if (villager.getProfession() == DCsConfig.villagerRecipeID) {
			// タルト
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 1), new ItemStack(DCsAppleMilk.appleTart, 1,
					0)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 1), new ItemStack(Items.pumpkin_pie, 2, 0)));
			// スープ
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 1), new ItemStack(DCsAppleMilk.bowlBlock, 1,
					6)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 1), new ItemStack(DCsAppleMilk.bowlBlock, 1,
					7)));
			// お肉
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 2), new ItemStack(DCsAppleMilk.foodPlate, 1,
					2)));

			// カップも
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 1), new ItemStack(DCsAppleMilk.teacupBlock,
					1, 6)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 1), new ItemStack(DCsAppleMilk.teacupBlock,
					1, 10)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 1), new ItemStack(DCsAppleMilk.teacupBlock,
					1, 12)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 2),
					new ItemStack(DCsAppleMilk.teaCup2, 1, 0)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 2),
					new ItemStack(DCsAppleMilk.teaCup2, 1, 4)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 2),
					new ItemStack(DCsAppleMilk.teaCup2, 1, 5)));

			// 茶葉
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 2),
					new ItemStack(DCsAppleMilk.foodTea, 1, 2)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 2), new ItemStack(DCsAppleMilk.gratedApple,
					1, 3)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 2),
					new ItemStack(DCsAppleMilk.leafTea, 1, 1)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 1),
					new ItemStack(DCsAppleMilk.leafTea, 1, 2)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 1),
					new ItemStack(DCsAppleMilk.leafTea, 1, 3)));

			// お酒
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 3), new ItemStack(
					DCsAppleMilk.itemLargeBottle, 1, 51)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 5), new ItemStack(
					DCsAppleMilk.itemLargeBottle, 1, 53)));

			// 買い取りは材料の葉や、余った空容器など
			recipeList.add(new MerchantRecipe(new ItemStack(DCsAppleMilk.leafTea, 3, 0),
					new ItemStack(Items.emerald, 1)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.egg, 8, 0), new ItemStack(Items.emerald, 1)));
			recipeList.add(new MerchantRecipe(new ItemStack(Blocks.pumpkin, 3, 0), new ItemStack(Items.emerald, 1)));

			recipeList.add(new MerchantRecipe(new ItemStack(Items.bowl, 3, 0), new ItemStack(Items.emerald, 1)));
			recipeList.add(new MerchantRecipe(new ItemStack(DCsAppleMilk.emptyCup, 1, 0), new ItemStack(Items.emerald,
					1)));
			recipeList.add(new MerchantRecipe(new ItemStack(DCsAppleMilk.emptyBottle, 1, 0), new ItemStack(
					Items.emerald, 1)));

		}

	}

}
