package mods.defeatedcrow.api.plants;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

/**
 * 右クリック収穫系の植物ブロックの右クリック処理中に呼ばれるイベント。 <br>
 * キャンセル可能。 <br>
 * キャンセルした場合、onHarvestなどの本来の処理はキャンセルされる。 <br>
 * Result.ALLOWの場合、メタデータが初期値に戻る。
 */
@Cancelable
@Event.HasResult
public class PlantsClickEvent extends Event {

	public final World world;
	public final EntityPlayer player;
	public final ItemStack holdItem;
	public final Block target;
	public final IRightClickHarvestable targetPlant;
	public final int targetMeta;
	public final int posX;
	public final int posY;
	public final int posZ;

	public PlantsClickEvent(World thisWorld, EntityPlayer thisPlayer, ItemStack hold, Block block,
			IRightClickHarvestable thisPlant, int meta, int x, int y, int z) {
		this.world = thisWorld;
		this.player = thisPlayer;
		this.holdItem = hold;
		this.target = block;
		this.targetPlant = thisPlant;
		this.targetMeta = meta;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
	}

}
