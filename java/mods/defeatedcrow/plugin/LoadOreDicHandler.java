package mods.defeatedcrow.plugin;

import java.util.ArrayList;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class LoadOreDicHandler {

	/**
	 * OreDictionaryに登録されているアイテムを、使う分だけゲームのロード時にまとめて読み込み、このクラス内で管理する。
	 * OreIDでは取得できないアイテム（複数の辞書名が登録されたアイテム）への策。
	 * よって、このMODのpostInitより遅いタイミングで辞書登録されるMODには対応できない。
	 */
	private static ArrayList<ItemStack> listAlmond = new ArrayList<ItemStack>();
	private static ArrayList<ItemStack> listPeanut = new ArrayList<ItemStack>();
	private static ArrayList<ItemStack> listNuts = new ArrayList<ItemStack>();
	private static ArrayList<ItemStack> listCherry = new ArrayList<ItemStack>();
	private static ArrayList<ItemStack> listStraw = new ArrayList<ItemStack>();
	private static ArrayList<ItemStack> listBerry = new ArrayList<ItemStack>();
	private static ArrayList<ItemStack> listBanana = new ArrayList<ItemStack>();
	private static ArrayList<ItemStack> listRice = new ArrayList<ItemStack>();
	private static ArrayList<ItemStack> listHoney = new ArrayList<ItemStack>();
	private static ArrayList<ItemStack> listSoy = new ArrayList<ItemStack>();
	private static ArrayList<ItemStack> listSeaweed = new ArrayList<ItemStack>();

	public void load() {

		/**
		 * 必要分だけ取得してリストに加える。
		 */
		listAlmond.addAll(OreDictionary.getOres("cropAlmond"));
		listPeanut.addAll(OreDictionary.getOres("cropPeanut"));
		listNuts.addAll(OreDictionary.getOres("cropWalnut"));
		listNuts.addAll(OreDictionary.getOres("cropHazelnut"));
		listNuts.addAll(OreDictionary.getOres("cropCoconut"));
		listCherry.addAll(OreDictionary.getOres("cropCherry"));
		listStraw.addAll(OreDictionary.getOres("cropStrawberry"));
		listBerry.addAll(OreDictionary.getOres("cropRaspberry"));
		listBerry.addAll(OreDictionary.getOres("cropCranberry"));
		listBerry.addAll(OreDictionary.getOres("cropBlueberry"));
		listBerry.addAll(OreDictionary.getOres("cropBlackberry"));
		listBerry.addAll(OreDictionary.getOres("cropCassis"));
		listBanana.addAll(OreDictionary.getOres("cropBanana"));
		listRice.addAll(OreDictionary.getOres("cropRice"));
		listHoney.addAll(OreDictionary.getOres("dropHoney"));
		listSoy.addAll(OreDictionary.getOres("soybeans"));
		listSeaweed.addAll(OreDictionary.getOres("cropSeaweed"));

		/**
		 * 当MOD用の管理Mapへの登録。
		 * 鉱石辞書名とは異なる名前で登録している。
		 */
		if (listAlmond != null && listAlmond.isEmpty())
			LoadModHandler.registerArray("nuts", listAlmond);
		if (listPeanut != null && listPeanut.isEmpty())
			LoadModHandler.registerArray("nuts", listPeanut);
		if (listNuts != null && listNuts.isEmpty())
			LoadModHandler.registerArray("nuts", listNuts);
		if (listCherry != null && listCherry.isEmpty())
			LoadModHandler.registerArray("cherry", listCherry);
		if (listBerry != null && listBerry.isEmpty())
			LoadModHandler.registerArray("berry", listBerry);
		if (listStraw != null && listStraw.isEmpty())
			LoadModHandler.registerArray("strawberry", listStraw);
		if (listBanana != null && listBanana.isEmpty())
			LoadModHandler.registerArray("banana", listBanana);
		if (listRice != null && listRice.isEmpty())
			LoadModHandler.registerArray("rice", listRice);
		if (listHoney != null && listHoney.isEmpty())
			LoadModHandler.registerArray("honey", listHoney);
		if (listSoy != null && listSoy.isEmpty())
			LoadModHandler.registerArray("soy", listSoy);
		if (listSeaweed != null && !listSeaweed.isEmpty())
			LoadModHandler.registerArray("seaWeed", listSeaweed);

		/**
		 * 以下、登録したリストを使った追加レシピ登録。
		 */
		for (ItemStack soy : listSoy) {
			if (Util.notEmptyItem(soy)) {

				RecipeRegisterManager.evaporatorRecipe.addRecipe(new ItemStack(DCsAppleMilk.dustWood, 1, 3),
						new FluidStack(DCsAppleMilk.vegitableOil, 25), soy);
			}
		}

	}

	/**
	 * 別クラスから、ItemStackがこのクラスにあるリスト内に含まれているかを判定するためのメソッド。
	 * ここではアーモンドかどうかを判定するため、ListAlmondに含まれていればtrueを返す。
	 */
	public static boolean isAlmond(ItemStack itemstack) {
		boolean flag = false;
		flag = matchItems(listAlmond, itemstack);
		if (flag)
			AMTLogger.debugInfo("get:almond");
		return flag;
	}

	public static boolean isPeanut(ItemStack itemstack) {
		boolean flag = false;
		flag = matchItems(listPeanut, itemstack);
		if (flag)
			AMTLogger.debugInfo("get:peanut");
		return flag;
	}

	public static boolean isNuts(ItemStack itemstack) {
		boolean flag = false;
		flag = matchItems(listNuts, itemstack);
		if (flag)
			AMTLogger.debugInfo("get:nuts");
		return flag;
	}

	public static boolean isCherry(ItemStack itemstack) {
		boolean flag = false;
		flag = matchItems(listCherry, itemstack);
		if (flag)
			AMTLogger.debugInfo("get:cherry");
		return flag;
	}

	public static boolean isStraw(ItemStack itemstack) {
		boolean flag = false;
		flag = matchItems(listStraw, itemstack);
		if (flag)
			AMTLogger.debugInfo("get:strawberry");
		return flag;
	}

	public static boolean isBerry(ItemStack itemstack) {
		boolean flag = false;
		flag = matchItems(listBerry, itemstack);
		if (flag)
			AMTLogger.debugInfo("get:berrys");
		return flag;
	}

	public static boolean isBanana(ItemStack itemstack) {
		boolean flag = false;
		flag = matchItems(listBanana, itemstack);
		if (flag)
			AMTLogger.debugInfo("get:banana");
		return flag;
	}

	public static boolean isRice(ItemStack itemstack) {
		boolean flag = false;
		flag = matchItems(listRice, itemstack);
		if (flag)
			AMTLogger.debugInfo("get:rice");
		return flag;
	}

	/**
	 * for文を回して、リストを順にチェックしている。
	 */
	private static boolean matchItems(ArrayList<ItemStack> list, ItemStack items) {
		for (ItemStack checks : list) {
			if (checks != null && items != null
					&& (items.getItem() == checks.getItem() && items.getItemDamage() == checks.getItemDamage())) {
				return true;
			}
		}
		return false;
	}
}
