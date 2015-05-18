package mods.defeatedcrow.api.edibles;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * 食べられるアイテムの作成を補助するためのクラス。 <br>
 * 同じ処理（容器返却処理）を複数のアイテムに重複して追加するのは面倒だし見た目も良くないので作成。 <br>
 * デフォルト動作では、返却容器はなし、飲食時の効果は空腹度回復のポーション効果である。
 */
public class EdibleItem extends Item implements IEdibleItem {

	public EdibleItem() {
		super();
	}

	/**
	 * 食べる動作
	 */
	@Override
	public ItemStack onEaten(ItemStack item, World world, EntityPlayer player) {
		int meta = item.getItemDamage();

		if (!player.capabilities.isCreativeMode) {
			--item.stackSize;
		}
		this.returnItemStack(player, meta);

		if (!world.isRemote) {
			if (this.effectOnEaten(player, meta) != null) {
				ArrayList<PotionEffect> potion = this.effectOnEaten(player, meta);
				if (potion != null && !potion.isEmpty()) {
					for (PotionEffect ret : potion) {
						player.addPotionEffect(ret);
					}
				}
			}

			if (this.hungerOnEaten(meta) != null) {
				int[] h = this.hungerOnEaten(meta);
				player.getFoodStats().addStats(h[0], h[1]);
			}
		}

		return item;
	}

	/**
	 * ガリガリ咀嚼する時間
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack item) {
		return 32;
	}

	/**
	 * 飲食時のエフェクト。
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack item) {
		return EnumAction.eat;
	}

	/**
	 * 右クリック動作時に飲食効果を呼び出すメソッド。 <br>
	 * カーソルが特定のブロックをターゲットしている時は呼び出されないので、
	 * 何もない方向を向いておく必要がある。
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
		player.setItemInUse(item, this.getMaxItemUseDuration(item));
		return item;
	}

	/**
	 * 空容器の返却を行うメソッド。
	 */
	protected boolean returnItemStack(EntityPlayer player, int meta) {
		ItemStack ret = this.getReturnContainer(meta);
		if (ret != null) {
			if (!player.inventory.addItemStackToInventory(ret)) {
				player.entityDropItem(ret, 1);
				return true;
			}
		}
		return false;
	}

	/**
	 * 返却される空容器をメタデータ毎に定義する。
	 */
	@Override
	public ItemStack getReturnContainer(int meta) {

		return null;
	}

	/**
	 * 飲食時のポーション効果をメタデータ毎に定義する。 <br>
	 * 注意点として、ItemFoodのような空腹度回復効果ではなく、
	 * ポーション効果のSaturationを利用して空腹度回復を行っている。
	 */
	@Override
	public ArrayList<PotionEffect> effectOnEaten(EntityPlayer player, int meta) {

		ArrayList<PotionEffect> ret = new ArrayList<PotionEffect>();
		ret.add(new PotionEffect(Potion.field_76443_y.id, 2, 2));
		return ret;
	}

	@Override
	public int[] hungerOnEaten(int meta) {
		return new int[] { 4, 2 };
	}

}
