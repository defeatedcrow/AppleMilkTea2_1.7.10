package mods.defeatedcrow.api.edibles;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public interface IEdibleItem {

	/**
	 * 飲食後に返ってくる空容器
	 */
	public ItemStack getReturnContainer(int meta);

	/**
	 * 飲食時のポーション効果
	 */
	public ArrayList<PotionEffect> effectOnEaten(EntityPlayer player, int meta);

	/**
	 * 飲食時の空腹度回復
	 */
	public int[] hungerOnEaten(int meta);

}
