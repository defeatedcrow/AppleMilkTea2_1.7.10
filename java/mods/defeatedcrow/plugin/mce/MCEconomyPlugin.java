package mods.defeatedcrow.plugin.mce;

import java.util.ArrayList;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProduct;
import shift.mceconomy2.api.shop.IShop;

public class MCEconomyPlugin implements IShop {

	private static ArrayList<IProduct> DCsProduct = new ArrayList<IProduct>();
	public static int DCshopId = -1;

	public void registerShop() {
		registerProduct();
		DCshopId = MCEconomyAPI.registerShop(this);
		AMTLogger.trace("Succeeded to register the shop ID. ID: " + DCshopId);
	}

	void registerProduct() {
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.saplingTea, 1, 0), 25));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.saplingTea, 1, 1), 50));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.saplingTea, 1, 2), 50));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.saplingYuzu, 1, 0), 50));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.itemMintSeed, 1, 0), 50));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.foodTea, 1, 0), 15));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.foodTea, 1, 1), 15));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.foodTea, 1, 2), 50));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.foodTea, 1, 3), 30));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.gratedApple, 1, 2), 40));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.gratedApple, 1, 3), 40));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.gratedApple, 1, 5), 40));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.gratedApple, 1, 6), 40));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), 40));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.gratedApple, 1, 8), 40));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.gratedApple, 1, 9), 40));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.mincedFoods, 1, 5), 50));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.mincedFoods, 1, 6), 50));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.mincedFoods, 1, 7), 50));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.mincedFoods, 1, 9), 50));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.mincedFoods, 1, 10), 50));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 51), 300));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 54), 500));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 52), 500));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.essentialOil, 1, 6), 300));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.essentialOil, 1, 7), 300));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.emptyCup, 1, 0), 25));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.teaMakerNext, 1, 0), 1000));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.DCgrater, 1, 0), 80));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.emptyPanGaiden, 1, 0), 200));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.teppanII, 1, 0), 1000));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.iceMaker, 1, 0), 1800));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.processor, 1, 0), 2000));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.jawPlate, 1, 5), 3000));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.batteryItem, 1, 1), 500));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.batteryItem, 64, 1), 30000));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.chalcedonyHammer, 1, 0), 300));
		DCsProduct.add(new AMTProduct(new ItemStack(DCsAppleMilk.pruningShears, 1, 0), 450));
	}

	@Override
	public String getShopName(World world, EntityPlayer player) {
		return "AppleMilkTea's Delivery Service";
	}

	@Override
	public void addProduct(IProduct product) {
		DCsProduct.add(product);
	}

	@Override
	public ArrayList<IProduct> getProductList(World world, EntityPlayer player) {
		return DCsProduct;
	}

	public class AMTProduct implements IProduct {

		private final ItemStack item;
		private final int cost;

		public AMTProduct(ItemStack i, int c) {
			item = i;
			cost = c;
		}

		// ひとまず無条件で購入できる
		@Override
		public ItemStack getItem(IShop shop, World world, EntityPlayer player) {
			return item;
		}

		@Override
		public int getCost(IShop shop, World world, EntityPlayer player) {
			return cost;
		}

		@Override
		public boolean canBuy(IShop shop, World world, EntityPlayer player) {
			return true;
		}

	}

}
