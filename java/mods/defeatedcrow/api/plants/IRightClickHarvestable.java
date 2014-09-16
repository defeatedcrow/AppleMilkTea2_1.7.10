package mods.defeatedcrow.api.plants;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * 植物系ブロック用のインターフェイス。
 * 収穫時の動作、収穫可能判定、収穫物の3つを返すためのもの。
 * */
public interface IRightClickHarvestable {
	
	/**
	 * 収穫時に呼ばれるメソッド。
	 * このメソッドの中で引数のインベントリへ収穫物を突っ込む（失敗時は引数の座標にEntityItemをドロップ）、植物ブロックのメタデータ更新を行う。
	 * <br>成功時trueを返す。
	 * <br>引数のworld、x、y、zに収穫可能な植物ブロックが無ければ失敗する。
	 */
	boolean onHarvest(World world, int x, int y, int z, IInventory inventory, ItemStack currentItem);
	
	/**
	 * この座標に収穫可能状態の植物ブロックがあるかどうか。
	 * */
	boolean isHarvestable(World world, int x, int y, int z);
	
	/**
	 * メタデータごとに収穫物を返す。
	 * 未成熟のメタデータの場合はnullを返す。
	 * */
	ItemStack getCropItem(int blockMeta);

}
