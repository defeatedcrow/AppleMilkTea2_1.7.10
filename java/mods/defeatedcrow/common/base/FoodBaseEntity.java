package mods.defeatedcrow.common.base;

import mods.defeatedcrow.common.entity.edible.PlaceableFoods;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * AMT2のソースを利用して食べ物の見た目を持つEntityを追加する場合、このクラスをextendsして下さい。<br>
 * Entityの仕様はAMT2のEntity類(PlaceableFoods)に準拠します。<br>
 * 異なる仕様にしたい場合は、PlaceableFoodsのメソッドをオーバーライドして上書きして下さい。
 */
public abstract class FoodBaseEntity extends PlaceableFoods implements IFoodType {

	public FoodBaseEntity(World world) {
		super(world);
	}

	public FoodBaseEntity(World world, ItemStack item) {
		super(world, true, item);
	}

	public FoodBaseEntity(World world, ItemStack item, double x, double y, double z) {
		super(world, true, item, x, y, z);
	}

	public abstract IIcon getSoupIcon(int meta);

}
