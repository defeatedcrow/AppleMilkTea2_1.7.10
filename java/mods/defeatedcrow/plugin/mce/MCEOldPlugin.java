package mods.defeatedcrow.plugin.mce;

import java.util.ArrayList;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.ItemStack;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProductItem;
import shift.mceconomy2.api.shop.ProductItem;
import shift.mceconomy2.api.shop.ProductList;

public class MCEOldPlugin extends ProductList {

	private static ArrayList<IProductItem> DCsProduct = new ArrayList<IProductItem>();
	public static int OldshopId = -1;

	public void load() {
		registerProducts();
		OldshopId = MCEconomyAPI.registerProductList(this);
		AMTLogger.trace("Succeeded to register the old shop ID. ID: " + OldshopId);
	}

	static void registerProducts() {
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.saplingTea, 1, 0), 25));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.saplingTea, 1, 1), 50));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.saplingTea, 1, 2), 50));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.saplingYuzu, 1, 0), 50));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.itemMintSeed, 1, 0), 50));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.foodTea, 1, 0), 15));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.foodTea, 1, 1), 15));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.foodTea, 1, 2), 50));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.foodTea, 1, 3), 30));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.gratedApple, 1, 2), 40));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.gratedApple, 1, 3), 40));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.gratedApple, 1, 5), 40));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.gratedApple, 1, 6), 40));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), 40));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.gratedApple, 1, 8), 40));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.gratedApple, 1, 9), 40));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.mincedFoods, 1, 5), 50));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.mincedFoods, 1, 6), 50));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.mincedFoods, 1, 7), 50));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.mincedFoods, 1, 9), 50));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.mincedFoods, 1, 10), 50));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 51), 300));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 54), 500));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 52), 500));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.essentialOil, 1, 6), 300));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.essentialOil, 1, 7), 300));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.emptyCup, 1, 0), 25));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.teaMakerNext, 1, 0), 1000));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.DCgrater, 1, 0), 80));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.emptyPanGaiden, 1, 0), 200));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.teppanII, 1, 0), 1000));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.iceMaker, 1, 0), 1800));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.processor, 1, 0), 2000));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.batteryItem, 1, 1), 500));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.batteryItem, 64, 1), 30000));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.chalcedonyHammer, 1, 0), 300));
		DCsProduct.add(new ProductItem(new ItemStack(DCsAppleMilk.pruningShears, 1, 0), 450));
	}

	@Override
	public String getProductListName() {

		return "AppleMilkTea's Delivery Service";
	}

	@Override
	public void addItemProduct(IProductItem item) {
		DCsProduct.add(item);
	}

	@Override
	public int getItemProductSize() {
		return DCsProduct.size();
	}

	@Override
	public ArrayList<IProductItem> getProductList() {
		return DCsProduct;
	}

}
