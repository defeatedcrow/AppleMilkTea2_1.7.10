package mods.defeatedcrow.common.world;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class AddChestGen {

	public void addChestItems() {

		// 追加したいチェストの種類を文字列で設定。ここでは村とジャングル寺院に追加する。
		String[] categoryA = new String[] {
				"villageBlacksmith",
				"pyramidJungleChest" };
		String[] categoryB = new String[] {
				"strongholdCorridor",
				"strongholdCrossing" };

		for (String category : categoryA) {
			// 上記カテゴリーのお宝リストを取得
			ChestGenHooks dungeon = ChestGenHooks.getInfo(category);
			// addItemメソッドで新しいアイテムを追加
			dungeon.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.saplingTea), 1, 3, 20));
			dungeon.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.rotaryDial), 1, 1, 10));
			dungeon.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 19), 1, 1, 10));
			dungeon.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 18), 1, 1, 10));
			dungeon.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 0), 1, 1, 20));
			dungeon.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.leafTea, 1, 1), 1, 3, 20));
		}

		for (String category : categoryB) {
			// 上記カテゴリーのお宝リストを取得
			ChestGenHooks dungeon2 = ChestGenHooks.getInfo(category);
			// addItemメソッドで新しいアイテムを追加
			dungeon2.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 22), 1, 1,
					10));
			dungeon2.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 23), 1, 1,
					10));
			dungeon2.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.princessClam, 1, 0), 1, 1, 5));
		}

		ChestGenHooks dungeon3 = ChestGenHooks.getInfo("dungeonChest");
		dungeon3.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 17), 1, 1, 10));
		dungeon3.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.batteryItem, 1, 0), 1, 4, 10));
		dungeon3.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.essentialOil, 1, 6), 1, 3, 5));

		ChestGenHooks dungeon4 = ChestGenHooks.getInfo("mineshaftCorridor");
		dungeon4.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 20), 1, 1, 10));
		dungeon4.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 21), 1, 1, 10));
		dungeon4.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.itemMintSeed, 1, 0), 1, 3, 10));
		dungeon3.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.essentialOil, 1, 10), 1, 3, 3));
		dungeon3.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.batteryItem, 1, 0), 1, 4, 10));

		ChestGenHooks cafe = ChestGenHooks.getInfo("villageCafeDC");
		cafe.setMin(3);
		cafe.setMax(7);
		ChestGenHooks.addItem("villageCafeDC",
				new WeightedRandomChestContent(Item.getItemFromBlock(DCsAppleMilk.emptyCup), 0, 1, 4, 5));
		ChestGenHooks.addItem("villageCafeDC",
				new WeightedRandomChestContent(Item.getItemFromBlock(DCsAppleMilk.silkyMelon), 0, 1, 4, 5));
		ChestGenHooks.addItem("villageCafeDC",
				new WeightedRandomChestContent(Item.getItemFromBlock(DCsAppleMilk.appleBox), 0, 1, 4, 5));
		ChestGenHooks.addItem("villageCafeDC", new WeightedRandomChestContent(DCsAppleMilk.appleSandwich, 0, 1, 4, 10));
		ChestGenHooks.addItem("villageCafeDC", new WeightedRandomChestContent(DCsAppleMilk.bakedApple, 0, 1, 4, 10));
		ChestGenHooks.addItem("villageCafeDC", new WeightedRandomChestContent(DCsAppleMilk.bottleCamOil, 0, 1, 4, 5));
		ChestGenHooks.addItem("villageCafeDC", new WeightedRandomChestContent(DCsAppleMilk.clam, 0, 1, 4, 5));
		ChestGenHooks.addItem("villageCafeDC", new WeightedRandomChestContent(DCsAppleMilk.condensedMIlk, 0, 1, 4, 5));
		ChestGenHooks.addItem("villageCafeDC", new WeightedRandomChestContent(DCsAppleMilk.toffyApple, 0, 1, 4, 5));
		ChestGenHooks.addItem("villageCafeDC", new WeightedRandomChestContent(DCsAppleMilk.leafTea, 0, 1, 4, 5));
		ChestGenHooks.addItem("villageCafeDC", new WeightedRandomChestContent(DCsAppleMilk.EXItems, 0, 1, 4, 5));
		ChestGenHooks.addItem("villageCafeDC", new WeightedRandomChestContent(DCsAppleMilk.yeast, 0, 1, 4, 5));
		ChestGenHooks.addItem("villageCafeDC", new WeightedRandomChestContent(DCsAppleMilk.DCgrater, 0, 1, 1, 5));
		ChestGenHooks.addItem("villageCafeDC", new WeightedRandomChestContent(DCsAppleMilk.princessClam, 0, 1, 1, 2));
		ChestGenHooks.addItem("villageCafeDC", new WeightedRandomChestContent(DCsAppleMilk.essentialOil, 0, 1, 1, 2));
	}

}
