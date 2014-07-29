package mods.defeatedcrow.common;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;
import mods.defeatedcrow.*;
import mods.defeatedcrow.common.tile.TileAutoMaker;

public class DCsLangRegister {
	
	public void registerLang()
	{
		LanguageRegistry.addName(DCsAppleMilk.bakedApple, "Baked Apple");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.bakedApple, "ja_JP", "焼きリンゴ");
 
		LanguageRegistry.addName(DCsAppleMilk.appleTart, "Apple Tart");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.appleTart, "ja_JP", "リンゴのタルト");
		
		LanguageRegistry.addName(DCsAppleMilk.toffyApple, "Toffy Apple");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.toffyApple, "ja_JP", "りんご飴");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.appleSandwich, 1, 0), "Apple Sandwich");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.appleSandwich, 1, 0), "ja_JP", "りんごサンド");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.appleSandwich, 1, 1), "Egg Sandwich");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.appleSandwich, 1, 1), "ja_JP", "たまごサンド");
 
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.icyToffyApple, 1, 0), "Icy Toffy Apple");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.icyToffyApple, 1, 0), "ja_JP", "氷結りんご飴");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.icyToffyApple, 1, 1), "Airy Toffy Apple");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.icyToffyApple, 1, 1), "ja_JP", "風のりんご飴");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.icyToffyApple, 1, 2), "Golden Toffy Apple");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.icyToffyApple, 1, 2), "ja_JP", "黄金りんご飴");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.icyToffyApple, 1, 3), "Green Toffy Apple");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.icyToffyApple, 1, 3), "ja_JP", "葉緑素りんご飴");
		
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.woodBox, 1, 0), "Oak Box");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.woodBox, 1, 0), "ja_JP", "樫の丸太箱");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.woodBox, 1, 1), "Spruse Box");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.woodBox, 1, 1), "ja_JP", "松の丸太箱");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.woodBox, 1, 2), "Birch Box");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.woodBox, 1, 2), "ja_JP", "白樺の丸太箱");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.woodBox, 1, 3), "Jangle Box");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.woodBox, 1, 3), "ja_JP", "ジャングルの丸太箱");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.woodBox, 1, 4), "Rubberwood Box");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.woodBox, 1, 4), "ja_JP", "ゴムの木の丸太箱");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.woodBox, 1, 5), "Greatwood Box");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.woodBox, 1, 5), "ja_JP", "グレートウッドの丸太箱");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.woodBox, 1, 6), "Silverwood Box");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.woodBox, 1, 6), "ja_JP", "シルバーウッドの丸太箱");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.woodBox, 1, 7), "Forcewood Box");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.woodBox, 1, 7), "ja_JP", "フォースウッドの丸太箱");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.woodBox, 1, 8), "Cherry Box");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.woodBox, 1, 8), "ja_JP", "桜の丸太箱");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.woodBox, 1, 9), "Momizi Box");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.woodBox, 1, 9), "ja_JP", "モミジの丸太箱");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.woodBox, 1, 10), "JP Ceder Box");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.woodBox, 1, 10), "ja_JP", "杉の丸太箱");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.mushroomBox, 1, 0), "Red Mushroom Box");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.mushroomBox, 1, 0), "ja_JP", "赤キノコブロック");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.mushroomBox, 1, 1), "Brown Mushroom Box");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.mushroomBox, 1, 1), "ja_JP", "茶キノコブロック");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.melonBomb, 1, 0), "Compressed Melon Box");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.melonBomb, 1, 0), "ja_JP", "圧縮されたメロン");
		
		LanguageRegistry.addName(DCsAppleMilk.appleBox, "Apple Box");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.appleBox, "ja_JP", "りんご箱");
		
		LanguageRegistry.addName(DCsAppleMilk.charcoalBox, "Charcoal Container");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.charcoalBox, "ja_JP", "木炭コンテナ");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlRack,1,0), "Bowl Rack");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlRack,1,0), "ja_JP", "ボウルラック");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlRack,1,1), "Bowl Rack");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlRack,1,1), "ja_JP", "ボウルラック");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlRack,1,2), "Bowl Rack");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlRack,1,2), "ja_JP", "ボウルラック");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlRack,1,3), "Bowl Rack");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlRack,1,3), "ja_JP", "ボウルラック");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.Basket,1,0), "Bread Basket");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.Basket,1,0), "ja_JP", "ブレッドバスケット");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.Basket,1,1), "Bread Basket");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.Basket,1,1), "ja_JP", "ブレッドバスケット");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.Basket,1,2), "Bread Basket");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.Basket,1,2), "ja_JP", "ブレッドバスケット");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.Basket,1,3), "Bread Basket");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.Basket,1,3), "ja_JP", "ブレッドバスケット");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.Basket,1,4), "Bread Basket");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.Basket,1,4), "ja_JP", "ブレッドバスケット");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.Basket,1,5), "Bread Basket");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.Basket,1,5), "ja_JP", "ブレッドバスケット");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.Basket,1,6), "Bottle Case");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.Basket,1,6), "ja_JP", "ボトルケース");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.Basket,1,7), "Bottle Case");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.Basket,1,7), "ja_JP", "ボトルケース");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.Basket,1,8), "Bottle Case");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.Basket,1,8), "ja_JP", "ボトルケース");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.Basket,1,9), "Bottle Case");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.Basket,1,9), "ja_JP", "ボトルケース");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.Basket,1,10), "Bottle Case");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.Basket,1,10), "ja_JP", "ボトルケース");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.Basket,1,11), "Bottle Case");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.Basket,1,11), "ja_JP", "ボトルケース");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.Basket,1,12), "Bottle Case");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.Basket,1,12), "ja_JP", "ボトルケース");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.Basket,1,13), "Bottle Case");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.Basket,1,13), "ja_JP", "ボトルケース");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.Basket,1,14), "Bottle Case");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.Basket,1,14), "ja_JP", "ボトルケース");
		
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.eggBasket,1,0), "Egg Basket");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.eggBasket,1,0), "ja_JP", "タマゴバスケット");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.eggBasket,1,1), "Black Egg Basket");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.eggBasket,1,1), "ja_JP", "黒タマゴバスケット");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.eggBasket,1,2), "Egg Cage");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.eggBasket,1,2), "ja_JP", "タマゴケージ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.eggBasket,1,3), "Black Egg Cage");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.eggBasket,1,3), "ja_JP", "黒タマゴケージ");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chopsticksBox,1,0), "Chopsticks Holder");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chopsticksBox,1,0), "ja_JP", "箸立て");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chopsticksBox,1,1), "Chopsticks Holder");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chopsticksBox,1,1), "ja_JP", "箸立て");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chopsticksBox,1,2), "Chopsticks Holder");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chopsticksBox,1,2), "ja_JP", "箸立て");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chopsticksBox,1,3), "Chopsticks Holder");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chopsticksBox,1,3), "ja_JP", "箸立て");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chopsticksBox,1,4), "Chopsticks Holder");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chopsticksBox,1,4), "ja_JP", "箸立て");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wipeBox,1,0), "Wipe Box");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wipeBox,1,0), "ja_JP", "ティッシュ箱");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wipeBox,1,1), "KixWipe");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wipeBox,1,1), "ja_JP", "キムワイプ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wipeBox,1,2), "Opened Wipe Box");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wipeBox,1,2), "ja_JP", "開封済みティッシュ箱");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wipeBox,1,3), "Opened KixWipe");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wipeBox,1,3), "ja_JP", "開封済みキムワイプ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wipeBox,1,4), "Infinity KixWipe");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wipeBox,1,4), "ja_JP", "無限キムワイプ");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wipeBox2,1,0), "Large paper Box");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wipeBox2,1,0), "ja_JP", "A4用紙箱");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chopsticks,1,0), "Chopsticks");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chopsticks,1,0), "ja_JP", "箸");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chopsticks,1,1), "Wood Spoon");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chopsticks,1,1), "ja_JP", "木のスプーン");
		
		LanguageRegistry.addName(DCsAppleMilk.emptyPan, "Empty Pan");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.emptyPan, "ja_JP", "空の鍋");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.filledPan, 1, 0), "Boiled Rice");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.filledPan, 1, 0), "ja_JP", "御飯");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.filledPan, 1, 1), "Mashroom Stew");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.filledPan, 1, 1), "ja_JP", "キノコシチュー鍋");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.filledPan, 1, 2), "Saimon Stew");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.filledPan, 1, 2), "ja_JP", "鮭のシチュー鍋");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.filledPan, 1, 3), "Zousui");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.filledPan, 1, 3), "ja_JP", "とりたま雑炊鍋");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.filledPan2, 1, 0), "Kayakumeshi");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.filledPan2, 1, 0), "ja_JP", "かやく御飯");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.filledPan2, 1, 1), "Tofu-Nabe");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.filledPan2, 1, 1), "ja_JP", "豆乳鍋");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.filledPan2, 1, 2), "Pumpkin Soup");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.filledPan2, 1, 2), "ja_JP", "かぼちゃポタージュ鍋");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.filledPan2, 1, 3), "BLT Soup");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.filledPan2, 1, 3), "ja_JP", "BLTスープ鍋");
		
		LanguageRegistry.addName(DCsAppleMilk.filledChocoPan, "Chocolate Fondue");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.filledChocoPan, "ja_JP", "チョコフォンデュ鍋");
		
		LanguageRegistry.addName(DCsAppleMilk.emptyCup, "Empty Tea Cup");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.emptyCup, "ja_JP", "空のティーカップ");
		
		LanguageRegistry.addName(DCsAppleMilk.saplingTea, "Tea Sapling");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.saplingTea, "ja_JP", "茶の苗");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teaTree, 1 ,0), "Tea Tree");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teaTree, 1, 0), "ja_JP", "茶の木");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teaTree, 1 ,1), "Grown Tea Tree");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teaTree, 1, 1), "ja_JP", "成長した茶の木");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.vegiBag, 1, 0), "Tea Leaves Bag");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.vegiBag, 1, 0), "ja_JP", "茶葉袋");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.vegiBag, 1, 1), "Potato Bag");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.vegiBag, 1, 1), "ja_JP", "じゃがいも袋");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.vegiBag, 1, 2), "Carrot Bag");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.vegiBag, 1, 2), "ja_JP", "にんじん袋");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.vegiBag, 1, 3), "Pumpkin Bag");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.vegiBag, 1, 3), "ja_JP", "かぼちゃ袋");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.vegiBag, 1, 4), "Seed Bag");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.vegiBag, 1, 4), "ja_JP", "種袋");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.vegiBag, 1, 5), "Reed Bag");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.vegiBag, 1, 5), "ja_JP", "サトウキビ袋");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.vegiBag, 1, 6), "Cactus Bag");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.vegiBag, 1, 6), "ja_JP", "サボテン袋");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.vegiBag, 1, 7), "Cocoa Bag");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.vegiBag, 1, 7), "ja_JP", "カカオ袋");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.vegiBag, 1, 8), "NetherWart Bag");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.vegiBag, 1, 8), "ja_JP", "ネザーウォート袋");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.vegiBag, 1, 9), "Sugar Bag");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.vegiBag, 1, 9), "ja_JP", "砂糖袋");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.EXItems, 1, 0), "Milk Candy");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.EXItems, 1, 0), "ja_JP", "ミルク飴");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.EXItems, 1, 1), "Animal Glue");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.EXItems, 1, 1), "ja_JP", "膠");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.EXItems, 1, 2), "Green Tea Leaves");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.EXItems, 1, 2), "ja_JP", "緑茶の茶葉");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.EXItems, 1, 3), "Tea Leaves");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.EXItems, 1, 3), "ja_JP", "紅茶の茶葉");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.EXItems, 1, 4), "Oxidized Tea Leaves");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.EXItems, 1, 4), "ja_JP", "酸化した茶葉");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.EXItems, 1, 5), "Kayaku");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.EXItems, 1, 5), "ja_JP", "かやく");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.EXItems, 1, 6), "Chalcedony Gear");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.EXItems, 1, 6), "ja_JP", "玉髄の歯車");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.EXItems, 1, 7), "Crushed Ice");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.EXItems, 1, 7), "ja_JP", "ロックアイス");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.EXItems, 1, 8), "Earl Gray Leaves");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.EXItems, 1, 8), "ja_JP", "アールグレイの茶葉");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.EXItems, 1, 9), "Appletea Leaves");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.EXItems, 1, 9), "ja_JP", "アップルティーの茶葉");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.EXItems, 1, 10), "Empty WallMug");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.EXItems, 1, 10), "ja_JP", "空のウォールマグ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.EXItems, 1, 11), "Glass Dust");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.EXItems, 1, 11), "ja_JP", "ガラスペレット");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.EXItems, 1, 12), "Clam Dust");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.EXItems, 1, 12), "ja_JP", "粉末ハマグリ");
		
		LanguageRegistry.addName(DCsAppleMilk.condensedMIlk, "Condensed MIlk");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.condensedMIlk, "ja_JP", "加糖練乳");
		LanguageRegistry.addName(DCsAppleMilk.inkStick, "Inkstick");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.inkStick, "ja_JP", "墨");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.leafTea, 1, 0), "Raw Tea Leaves");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.leafTea, 1, 0), "ja_JP", "生の茶葉");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.leafTea, 1, 1), "Mint Leaves");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.leafTea, 1, 1), "ja_JP", "ミントの葉");
		
		LanguageRegistry.addName(DCsAppleMilk.DCgrater, "Grater");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.DCgrater, "ja_JP", "おろし金");
		
		LanguageRegistry.addName(DCsAppleMilk.teaMakerNext, "Tea Maker");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.teaMakerNext, "ja_JP", "ティーメーカー");
		
		LanguageRegistry.addName(DCsAppleMilk.autoMaker, "Auto Tea Attachmant");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.autoMaker, "ja_JP", "ティーメーカー・アタッチメント");
		
		LanguageRegistry.addName(DCsAppleMilk.iceMaker, "Ice Maker");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.iceMaker, "ja_JP", "かき氷機");
		
		LanguageRegistry.addName(DCsAppleMilk.icyCrystal, "Icy Crystal");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.icyCrystal, "ja_JP", "氷結クリスタル");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.blockIcecream, 1, 0), "Milk Icecream");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.blockIcecream, 1, 0), "ja_JP", "アイスクリン");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.blockIcecream, 1, 1), "Tea Icecream");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.blockIcecream, 1, 1), "ja_JP", "紅茶アイス");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.blockIcecream, 1, 2), "Greentea Icecream");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.blockIcecream, 1, 2), "ja_JP", "抹茶アイス");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.blockIcecream, 1, 3), "Cocoa Icecream");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.blockIcecream, 1, 3), "ja_JP", "チョコチップアイス");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.blockIcecream, 1, 4), "Coffee Icecream");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.blockIcecream, 1, 4), "ja_JP", "コーヒーアイス");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.blockIcecream, 1, 5), "Fruit Icecream");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.blockIcecream, 1, 5), "ja_JP", "フルーツアイス");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.blockIcecream, 1, 6), "Lemon Icecream");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.blockIcecream, 1, 6), "ja_JP", "レモンアイス");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.blockIcecream, 1, 7), "Lime Icecream");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.blockIcecream, 1, 7), "ja_JP", "ライムシャーベット");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.blockIcecream, 1, 8), "Tomato Icecream");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.blockIcecream, 1, 8), "ja_JP", "トマトシャーベット");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.blockIcecream, 1, 9), "Berry Icecream");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.blockIcecream, 1, 9), "ja_JP", "ベリーアイス");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cocktail, 1, 0), "Frozen Diquiri");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cocktail, 1, 0), "ja_JP", "フローズン・ダイキリ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cocktail, 1, 1), "Frozen Sake");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cocktail, 1, 1), "ja_JP", "みぞれ酒");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cocktail, 1, 2), "Sake-tini");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cocktail, 1, 2), "ja_JP", "サケティーニ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cocktail, 1, 3), "Gimlet");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cocktail, 1, 3), "ja_JP", "ギムレット");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cocktail, 1, 4), "Black Rose");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cocktail, 1, 4), "ja_JP", "ブラックローズ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cocktail, 1, 5), "Red Eye");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cocktail, 1, 5), "ja_JP", "レッドアイ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cocktail, 1, 6), "Pina Colada");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cocktail, 1, 6), "ja_JP", "ピニャ・コラーダ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cocktail, 1, 7), "American Lemonade");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cocktail, 1, 7), "ja_JP", "アメリカンレモネード");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cocktail, 1, 8), "Moscow Mule");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cocktail, 1, 8), "ja_JP", "モスコミュール");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cocktail, 1, 9), "Mint Julep");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cocktail, 1, 9), "ja_JP", "ミントジュレップ");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.mincedFoods, 1, 0), "Minced Mushrooms");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.mincedFoods, 1, 0), "ja_JP", "キノコシチューの材料");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.mincedFoods, 1, 1), "Minced Fish and Vegitables");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.mincedFoods, 1, 1), "ja_JP", "鮭シチューの材料");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.mincedFoods, 1, 2), "Minced Chikin and rice");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.mincedFoods, 1, 2), "ja_JP", "雑炊の材料");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.mincedFoods, 1, 3), "Washed Rice");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.mincedFoods, 1, 3), "ja_JP", "搗いた米");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.mincedFoods, 1, 4), "Materials of Kayakumeshi");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.mincedFoods, 1, 4), "ja_JP", "かやく御飯の材料");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.mincedFoods, 1, 5), "Materials of Tofu-Nabe");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.mincedFoods, 1, 5), "ja_JP", "豆乳鍋の材料");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.mincedFoods, 1, 6), "Minced pumpkin");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.mincedFoods, 1, 6), "ja_JP", "かぼちゃポタージュの材料");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.mincedFoods, 1, 7), "Minced BLT");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.mincedFoods, 1, 7), "ja_JP", "BLTスープの材料");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.mincedFoods, 1, 8), "Minced Chocolate");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.mincedFoods, 1, 8), "ja_JP", "チョコフォンデュの材料");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.gratedApple, 1, 0), "Grated Apple");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.gratedApple, 1, 0), "ja_JP", "すりリンゴ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.gratedApple, 1, 1), "Grated Fruit");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.gratedApple, 1, 1), "ja_JP", "フルーツペースト");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.gratedApple, 1, 2), "Honey Lemon Slices");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.gratedApple, 1, 2), "ja_JP", "蜂蜜漬けレモン");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.gratedApple, 1, 3), "Coffee Powder");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.gratedApple, 1, 3), "ja_JP", "コーヒー粉");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.gratedApple, 1, 4), "Ganache");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.gratedApple, 1, 4), "ja_JP", "ガナッシュ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.gratedApple, 1, 5), "Grated Lime");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.gratedApple, 1, 5), "ja_JP", "ライムペースト");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.gratedApple, 1, 6), "Grated Tomato");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.gratedApple, 1, 6), "ja_JP", "トマトペースト");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), "Grated Berry");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.gratedApple, 1, 7), "ja_JP", "ベリーペースト");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.gratedApple, 1, 8), "Grated Grape");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.gratedApple, 1, 8), "ja_JP", "ぶどうペースト");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlBlock, 1, 0), "Boiled Rice");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlBlock, 1, 0), "ja_JP", "御飯");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlBlock, 1, 1), "Mashroom Stew");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlBlock, 1, 1), "ja_JP", "キノコシチュー");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlBlock, 1, 2), "Salmon Stew");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlBlock, 1, 2), "ja_JP", "鮭のクリームシチュー");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlBlock, 1, 3), "Torizousui");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlBlock, 1, 3), "ja_JP", "とりたま雑炊");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlBlock, 1, 4), "Kayakumeshi");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlBlock, 1, 4), "ja_JP", "かやくご飯");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlBlock, 1, 5), "Tofu-Nabe");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlBlock, 1, 5), "ja_JP", "豆乳鍋");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlBlock, 1, 6), "Pumpkin Soup");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlBlock, 1, 6), "ja_JP", "かぼちゃポタージュ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlBlock, 1, 7), "BLT Soup");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlBlock, 1, 7), "ja_JP", "BLTスープ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlBlock, 1, 15), "Empty Bowl");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlBlock, 1, 15), "ja_JP", "空のボウル");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlJP, 1, 0), "JP Boiled Rice");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlJP, 1, 0), "ja_JP", "御飯(和風)");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlJP, 1, 1), "JP Mashroom Stew");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlJP, 1, 1), "ja_JP", "キノコシチュー(和風)");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlJP, 1, 2), "JP Salmon Stew");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlJP, 1, 2), "ja_JP", "鮭のクリームシチュー(和風)");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlJP, 1, 3), "JP Torizousui");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlJP, 1, 3), "ja_JP", "とりたま雑炊(和風)");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlJP, 1, 4), "JP Kayakumeshi");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlJP, 1, 4), "ja_JP", "かやくご飯(和風)");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlJP, 1, 5), "JP Tofu-Nabe");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlJP, 1, 5), "ja_JP", "豆乳鍋(和風)");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlJP, 1, 6), "JP Pumpkin Soup");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlJP, 1, 6), "ja_JP", "かぼちゃポタージュ(和風)");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlJP, 1, 7), "JP BLT Soup");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlJP, 1, 7), "ja_JP", "BLTスープ(和風)");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.bowlJP, 1, 15), "Empty JP Bowl");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.bowlJP, 1, 15), "ja_JP", "空のお椀");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teacupBlock, 1, 0), "Empty Tea Cup");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teacupBlock, 1, 0), "ja_JP", "空のティーカップ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teacupBlock, 1, 0), "Hot Milk");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teacupBlock, 1, 1), "ja_JP", "ホットミルク");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teacupBlock, 1, 2), "Tea");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teacupBlock, 1, 2), "ja_JP", "紅茶");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teacupBlock, 1, 3), "Milk Tea");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teacupBlock, 1, 3), "ja_JP", "ミルクティー");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teacupBlock, 1, 4), "Green Tea");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teacupBlock, 1, 4), "ja_JP", "緑茶");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teacupBlock, 1, 5), "Milk Green Tea");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teacupBlock, 1, 5), "ja_JP", "抹茶ラテ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teacupBlock, 1, 6), "Black Cocoa");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teacupBlock, 1, 6), "ja_JP", "ブラックココア");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teacupBlock, 1, 7), "Milk Cocoa");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teacupBlock, 1, 7), "ja_JP", "ミルクココア");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teacupBlock, 1, 8), "Fruits Juice");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teacupBlock, 1, 8), "ja_JP", "フルーツジュース");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teacupBlock, 1, 9), "Fruit Shakes");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teacupBlock, 1, 9), "ja_JP", "フルーツセーキ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teacupBlock, 1, 10), "Hot Lemonade");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teacupBlock, 1, 10), "ja_JP", "はちみつレモン");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teacupBlock, 1, 11), "Milk Lemonade");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teacupBlock, 1, 11), "ja_JP", "レモン牛乳");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teacupBlock, 1, 12), "Coffee");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teacupBlock, 1, 12), "ja_JP", "コーヒー");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teacupBlock, 1, 13), "Milk Coffee");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teacupBlock, 1, 13), "ja_JP", "ミルクコーヒー");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teaCup2, 1, 0), "Earl Gray Tea");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teaCup2, 1, 0), "ja_JP", "アールグレイ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teaCup2, 1, 1), "Earl Gray Milk Tea");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teaCup2, 1, 1), "ja_JP", "アールグレイミルクティー");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teaCup2, 1, 2), "Apple Tea");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teaCup2, 1, 2), "ja_JP", "アップルティー");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teaCup2, 1, 3), "Apple Milk Tea");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teaCup2, 1, 3), "ja_JP", "アップルミルクティー");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teaCup2, 1, 4), "Lime Juice");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teaCup2, 1, 4), "ja_JP", "ライムジュース");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teaCup2, 1, 5), "Tomato Juice");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teaCup2, 1, 5), "ja_JP", "トマトジュース");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teaCup2, 1, 6), "Berry Juice");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teaCup2, 1, 6), "ja_JP", "ベリージュース");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teaCup2, 1, 7), "Berry Shakes");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teaCup2, 1, 7), "ja_JP", "ベリーミルクセーキ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teaCup2, 1, 8), "Grape Juice");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teaCup2, 1, 8), "ja_JP", "グレープジュース");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teaCup2, 1, 9), "Mint Tea");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teaCup2, 1, 9), "ja_JP", "ミントティー");
		
		LanguageRegistry.addName(DCsAppleMilk.flintBlock, "Flint Block");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.flintBlock, "ja_JP", "フリントブロック");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chalcedony, 1, 0), "Chalcedony Block");
		LanguageRegistry.instance().addNameForObject((new ItemStack(DCsAppleMilk.chalcedony, 1, 0)), "ja_JP", "玉髄ブロック");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chalcedony, 1, 1), "Orange Chalcedony Block");
		LanguageRegistry.instance().addNameForObject((new ItemStack(DCsAppleMilk.chalcedony, 1, 1)), "ja_JP", "黄玉髄ブロック");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chalcedony, 1, 2), "White Chalcedony Block");
		LanguageRegistry.instance().addNameForObject((new ItemStack(DCsAppleMilk.chalcedony, 1, 2)), "ja_JP", "白玉髄ブロック");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chalcedony, 1, 3), "Red Chalcedony Block");
		LanguageRegistry.instance().addNameForObject((new ItemStack(DCsAppleMilk.chalcedony, 1, 3)), "ja_JP", "赤玉髄ブロック");
		
		LanguageRegistry.addName(DCsAppleMilk.chalcedonyKnife, "Chalcedony Knife");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.chalcedonyKnife, "ja_JP", "玉髄のナイフ");
		LanguageRegistry.addName(DCsAppleMilk.firestarter, "Fire Starter");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.firestarter, "ja_JP", "上質な着火具");
		LanguageRegistry.addName(DCsAppleMilk.chalcedonyHammer, "Chalcedony Stone Cutter");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.chalcedonyHammer, "ja_JP", "玉髄の石切り槌");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cLamp, 1, 0), "Bluechalcedony GlowBlock");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cLamp, 1, 0), "ja_JP", "青玉髄のランプ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cLamp, 1, 1), "Orangechalcedony GlowBlock");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cLamp, 1, 1), "ja_JP", "黄玉髄のランプ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cLamp, 1, 2), "Bluechalcedony Glass Lamp");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cLamp, 1, 2), "ja_JP", "青玉髄のガラスランプ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cLamp, 1, 3), "Orangechalcedony Glass Lamp");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cLamp, 1, 3), "ja_JP", "黄玉髄のガラスランプ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cLamp, 1, 4), "Chalcedony Candlestick");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cLamp, 1, 4), "ja_JP", "玉髄の燭台");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cLamp, 1, 5), "Chalcedony Desk Lamp");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cLamp, 1, 5), "ja_JP", "玉髄の卓上ランプ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cLamp, 1, 6), "Whitechalcedony GlowBlock");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cLamp, 1, 6), "ja_JP", "白玉髄のランプ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cLamp, 1, 7), "Redchalcedony GlowBlock");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cLamp, 1, 7), "ja_JP", "赤玉髄のランプ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cLamp, 1, 8), "Whitechalcedony Glass Lamp");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cLamp, 1, 8), "ja_JP", "白玉髄のガラスランプ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cLamp, 1, 9), "Redchalcedony Glass Lamp");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cLamp, 1, 9), "ja_JP", "赤玉髄のガラスランプ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cLamp, 1, 10), "Chalcedony Floor Lamp");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cLamp, 1, 10), "ja_JP", "玉髄のフロアランプ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.cLamp, 1, 11), "Chalcedony Oriental Lamp");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.cLamp, 1, 11), "ja_JP", "玉髄のオリエンタルランプ");
		
		LanguageRegistry.addName(DCsAppleMilk.rotaryDial, "Rotary Dial");
		LanguageRegistry.instance().addNameForObject(DCsAppleMilk.rotaryDial, "ja_JP", "黒電話");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.gunpowderContainer, 1, 0), "Gunpowder Container");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.gunpowderContainer, 1, 0), "ja_JP", "火薬コンテナ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.gunpowderContainer, 1, 1), "Kayaku Container");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.gunpowderContainer, 1, 1), "ja_JP", "かやくコンテナ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.gunpowderContainer, 1, 2), "Clay Container");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.gunpowderContainer, 1, 2), "ja_JP", "粘土コンテナ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.gunpowderContainer, 1, 3), "Clam Container");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.gunpowderContainer, 1, 3), "ja_JP", "ハマグリコンテナ");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.teppann, 1, 0), "Cooking Iron Plate");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.teppann, 1, 0), "ja_JP", "調理用鉄板");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.foodPlate, 1, 0), "Beef Steak Plate");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.foodPlate, 1, 0), "ja_JP", "ビフテキプレート");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.foodPlate, 1, 1), "Pork Steak Plate");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.foodPlate, 1, 1), "ja_JP", "トンテキプレート");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.foodPlate, 1, 2), "Roasted Chicken Plate");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.foodPlate, 1, 2), "ja_JP", "鶏の丸焼きプレート");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.foodPlate, 1, 3), "Roasted Clam Plate");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.foodPlate, 1, 3), "ja_JP", "焼きハマグリプレート");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.clamSand, 1, 0), "Clam in Sand");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.clamSand, 1, 0), "ja_JP", "ハマグリ砂");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.clamSand, 1, 1), "Declined ClamSand");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.clamSand, 1, 1), "ja_JP", "衰退したハマグリ砂");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.clamSand, 1, 2), "Princess in Sand");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.clamSand, 1, 2), "ja_JP", "ハマグリ姫の砂");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.clam, 1, 0), "Clam");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.clam, 1, 0), "ja_JP", "ハマグリ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.clam, 1, 1), "Cooked Clam");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.clam, 1, 1), "ja_JP", "焼きハマグリ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.clam, 1, 2), "Burnt Meat");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.clam, 1, 2), "ja_JP", "焦げた肉片");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.clam, 1, 3), "Black Egg");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.clam, 1, 3), "ja_JP", "黒たまご");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.princessClam, 1, 0), "Princess Clam");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.princessClam, 1, 0), "ja_JP", "プリンセスハマグリ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.princessClam, 1, 1), "Raden Charm (Flower)");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.princessClam, 1, 1), "ja_JP", "螺鈿細工(花)");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.princessClam, 1, 2), "Raden Charm (Butterfly)");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.princessClam, 1, 2), "ja_JP", "螺鈿細工(蝶)");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.princessClam, 1, 3), "Raden Charm (Wind)");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.princessClam, 1, 3), "ja_JP", "螺鈿細工(風)");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.princessClam, 1, 4), "Raden Charm (Moon)");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.princessClam, 1, 4), "ja_JP", "螺鈿細工(月)");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 0), "Almond Chocolate");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 0), "ja_JP", "アーモンドチョコ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 1), "Peanut Chocolate");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 1), "ja_JP", "ピーナッツチョコ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 2), "Crushed Nuts Chocolate");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 2), "ja_JP", "ナッツクランチチョコ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 3), "Strawberry Chocolate");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 3), "ja_JP", "チョコいちご");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 4), "Cherry Chocolate");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 4), "ja_JP", "チョコさくらんぼ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 5), "Berry Chocolate");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 5), "ja_JP", "ベリーチョコ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 6), "Banana Chocolate");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 6), "ja_JP", "チョコバナナ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 7), "Cereal Chocolate Bar");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 7), "ja_JP", "シリアルチョコバー");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 8), "Chocolate Bread");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 8), "ja_JP", "チョコがけパン");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 9), "Cookie Chocolate");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 9), "ja_JP", "チョコがけクッキー");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 10), "Chocolate Truffles");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 10), "ja_JP", "トリュフチョコ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 11), "Chocolate Candy");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 11), "ja_JP", "チョコキャンディ");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 12), "Chocolate Apple");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chocolateFruits, 1, 12), "ja_JP", "チョコがけリンゴ");
		
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chocoBlock, 1, 0), "Chocolate Gift");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chocoBlock, 1, 0), "ja_JP", "チョコの贈り物");
		LanguageRegistry.addName(new ItemStack(DCsAppleMilk.chocoBlock, 1, 1), "Heartfelt Chocolate Gift");
		LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.chocoBlock, 1, 1), "ja_JP", "心をこめたチョコの贈り物");
		
		LanguageRegistry.instance().addStringLocalization("DCs.potion.immunization", "Immunization");
		LanguageRegistry.instance().addStringLocalization("DCs.potion.immunization", "ja_JP", "免疫力");
		
		//items that have large amount damage
		int[] amount = {0, 16, 32, 48, 64, 80, 96, 112};
		for (int i = 0 ; i < amount.length ; i++)
		{
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (0 + amount[i])), "Empty Large Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (0 + amount[i])), "ja_JP", "空の一升瓶");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (1 + amount[i])), "Sake Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (1 + amount[i])), "ja_JP", "日本酒ボトル");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (2 + amount[i])), "Beer Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (2 + amount[i])), "ja_JP", "ビールボトル");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (3 + amount[i])), "Wine Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (3 + amount[i])), "ja_JP", "ワインボトル");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (4 + amount[i])), "Gin Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (4 + amount[i])), "ja_JP", "ジンボトル");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (5 + amount[i])), "Rum Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (5 + amount[i])), "ja_JP", "ラムボトル");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (6 + amount[i])), "Wodka Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (6 + amount[i])), "ja_JP", "ウォッカボトル");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (7 + amount[i])), "Whiskey Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (7 + amount[i])), "ja_JP", "ウィスキーボトル");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (8 + amount[i])), "Milk Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (8 + amount[i])), "ja_JP", "牛乳入り大瓶");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (9 + amount[i])), "Soymilk Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (9 + amount[i])), "ja_JP", "豆乳入り大瓶");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (10 + amount[i])), "Sugar Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (10 + amount[i])), "ja_JP", "砂糖入り大瓶");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (11 + amount[i])), "Caramel Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (11 + amount[i])), "ja_JP", "カラメル大瓶");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (12 + amount[i])), "Honey Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (12 + amount[i])), "ja_JP", "ハチミツ大瓶");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (13 + amount[i])), "Nuts Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (13 + amount[i])), "ja_JP", "瓶詰めナッツ");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (14 + amount[i])), "Berry Jam Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, (14 + amount[i])), "ja_JP", "瓶詰めベリージャム");
			
		}
		
		int[] amount2 = {0, 16, 32, 48, 64, 80, 96, 112};
		for (int i = 0 ; i < amount.length ; i++)
		{
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.largeBottle, 1, (0 + amount[i])), "Empty Large Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.largeBottle, 1, (0 + amount[i])), "ja_JP", "空の一升瓶");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.largeBottle, 1, (1 + amount[i])), "Sake Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.largeBottle, 1, (1 + amount[i])), "ja_JP", "日本酒ボトル");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.largeBottle, 1, (2 + amount[i])), "Beer Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.largeBottle, 1, (2 + amount[i])), "ja_JP", "ビールボトル");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.largeBottle, 1, (3 + amount[i])), "Wine Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.largeBottle, 1, (3 + amount[i])), "ja_JP", "ワインボトル");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.largeBottle, 1, (4 + amount[i])), "Gin Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.largeBottle, 1, (4 + amount[i])), "ja_JP", "ジンボトル");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.largeBottle, 1, (5 + amount[i])), "Rum Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.largeBottle, 1, (5 + amount[i])), "ja_JP", "ラムボトル");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.largeBottle, 1, (6 + amount[i])), "Wodka Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.largeBottle, 1, (6 + amount[i])), "ja_JP", "ウォッカボトル");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.largeBottle, 1, (7 + amount[i])), "Whiskey Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.largeBottle, 1, (7 + amount[i])), "ja_JP", "ウィスキーボトル");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.largeBottle, 1, (8 + amount[i])), "Milk Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.largeBottle, 1, (8 + amount[i])), "ja_JP", "牛乳入り大瓶");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.largeBottle, 1, (9 + amount[i])), "Soymilk Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.largeBottle, 1, (9 + amount[i])), "ja_JP", "豆乳入り大瓶");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.largeBottle, 1, (10 + amount[i])), "Sugar Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.largeBottle, 1, (10 + amount[i])), "ja_JP", "砂糖入り大瓶");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.largeBottle, 1, (11 + amount[i])), "Caramel Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.largeBottle, 1, (11 + amount[i])), "ja_JP", "カラメル大瓶");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.largeBottle, 1, (12 + amount[i])), "Honey Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.largeBottle, 1, (12 + amount[i])), "ja_JP", "ハチミツ大瓶");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.largeBottle, 1, (13 + amount[i])), "Nuts Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.largeBottle, 1, (13 + amount[i])), "ja_JP", "瓶詰めナッツ");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.largeBottle, 1, (14 + amount[i])), "Berry Jam Bottle");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.largeBottle, 1, (14 + amount[i])), "ja_JP", "瓶詰めベリージャム");
			
		}
		
		int[] type = {0, 16, 32, 48, 64, 80, 96, 112, 128, 144, 160, 176, 192, 208, 224, 240};
		for (int i = 0 ; i < type.length ; i++)
		{
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wallMug, 1, (0 + type[i])), "Tea Wallmug");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wallMug, 1, (0 + type[i])), "ja_JP", "紅茶のウォールマグ");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wallMug, 1, (1 + type[i])), "Green Tea Wallmug");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wallMug, 1, (1 + type[i])), "ja_JP", "緑茶のウォールマグ");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wallMug, 1, (2 + type[i])), "Cocoa Wallmug");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wallMug, 1, (2 + type[i])), "ja_JP", "ココアのウォールマグ");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wallMug, 1, (3 + type[i])), "Coffee Wallmug");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wallMug, 1, (3 + type[i])), "ja_JP", "コーヒーのウォールマグ");
			
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wallMug, 1, (4 + type[i])), "Milk Tea Wallmug");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wallMug, 1, (4 + type[i])), "ja_JP", "ミルクティーマグ");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wallMug, 1, (5 + type[i])), "Milk Green Tea Wallmug");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wallMug, 1, (5 + type[i])), "ja_JP", "ミルク抹茶マグ");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wallMug, 1, (6 + type[i])), "Milk Cocoa Wallmug");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wallMug, 1, (6 + type[i])), "ja_JP", "ミルクココアマグ");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wallMug, 1, (7 + type[i])), "Milk Coffee Wallmug");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wallMug, 1, (7 + type[i])), "ja_JP", "ミルクコーヒーマグ");
			
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wallMug, 1, (8 + type[i])), "Condensed Milk Tea Mug");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wallMug, 1, (8 + type[i])), "ja_JP", "練乳ティーマグ");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wallMug, 1, (9 + type[i])), "Condenced Milk Green Tea Mug");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wallMug, 1, (9 + type[i])), "ja_JP", "練乳入り抹茶マグ");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wallMug, 1, (10 + type[i])), "Condensed Milk Cocoa Mug");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wallMug, 1, (10 + type[i])), "ja_JP", "練乳入りココアマグ");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wallMug, 1, (11 + type[i])), "Condensed Milk Coffee Mug");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wallMug, 1, (11 + type[i])), "ja_JP", "ベトナムコーヒーマグ");
			
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wallMug, 1, (12 + type[i])), "Soy Tea Mug");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wallMug, 1, (12 + type[i])), "ja_JP", "豆乳紅茶ラテマグ");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wallMug, 1, (13 + type[i])), "Soy Green Tea Mug");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wallMug, 1, (13 + type[i])), "ja_JP", "豆乳抹茶ラテマグ");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wallMug, 1, (14 + type[i])), "Soy Cocoa Mug");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wallMug, 1, (14 + type[i])), "ja_JP", "豆乳ココアマグ");
			LanguageRegistry.addName(new ItemStack(DCsAppleMilk.wallMug, 1, (15 + type[i])), "Soy Coffee Mug");
			LanguageRegistry.instance().addNameForObject(new ItemStack(DCsAppleMilk.wallMug, 1, (15 + type[i])), "ja_JP", "豆乳ラテマグ");
		}
	}

}
