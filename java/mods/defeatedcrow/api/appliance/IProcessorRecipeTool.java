package mods.defeatedcrow.api.appliance;

import net.minecraft.item.ItemStack;

/**
 * Food ProcessorやJaw Crusherの材料欄に入れて使えるアイテム。
 * クラフトツールのように耐久値の減るアイテムと一緒にクラフトするレシピに使える。
 */
public interface IProcessorRecipeTool {

	ItemStack returnItem(ItemStack item);

}
