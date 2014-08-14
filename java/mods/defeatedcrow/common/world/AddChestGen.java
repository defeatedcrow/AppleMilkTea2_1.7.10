package mods.defeatedcrow.common.world;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class AddChestGen {
	
	public void addChestItems() {
		
		//追加したいチェストの種類を文字列で設定。ここでは村とジャングル寺院に追加する。
		String[] categoryA = new String[]{"villageBlacksmith", "pyramidJungleChest" };
		String[] categoryB = new String[]{"strongholdCorridor", "strongholdCrossing" };
		
		for (String category : categoryA)
		{
			//上記カテゴリーのお宝リストを取得
			ChestGenHooks dungeon = ChestGenHooks.getInfo(category);
			//addItemメソッドで新しいアイテムを追加
			dungeon.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.saplingTea),1,3,20));
			dungeon.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.rotaryDial),1,1,10));
			dungeon.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 19),1,1,10));
			dungeon.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 18),1,1,10));
			dungeon.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 0),1,1,20));
			dungeon.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.leafTea, 1, 1),1,3,20));
		}
		
		for (String category : categoryB)
		{
			//上記カテゴリーのお宝リストを取得
			ChestGenHooks dungeon2 = ChestGenHooks.getInfo(category);
			//addItemメソッドで新しいアイテムを追加
			dungeon2.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 22),1,1,10));
			dungeon2.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 23),1,1,10));
			dungeon2.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.princessClam, 1, 0),1,1,5));
		}
		
		ChestGenHooks dungeon3 = ChestGenHooks.getInfo("dungeonChest");
		dungeon3.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 17),1,1,10));
		dungeon3.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.batteryItem, 1, 0),1,4,10));
		
		ChestGenHooks dungeon4 = ChestGenHooks.getInfo("mineshaftCorridor");
		dungeon4.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 20),1,1,10));
		dungeon4.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 21),1,1,10));
		dungeon4.addItem(new WeightedRandomChestContent(new ItemStack(DCsAppleMilk.itemMintSeed, 1, 0),1,3,10));
	}
	
}
