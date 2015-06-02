package mods.defeatedcrow.api.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

/**
 * 右クリックで何らかの効果を発揮するブロック
 * （右クリック回収動作を含む）の右クリック効果前に呼ばれるイベント。 <br>
 * 現在の対象ブロック： <br>
 * Basket、BowlRack、IncanseBase、RotaryDial、EmptyCup、EmptyBottle <br>
 * BarrelなどTileEntityにNBT情報を持つブロックは基本的にこのイベントで扱わない <br>
 * また、食べ物ブロックはこのイベントを持つが、デフォルトではEntity化しているのでコンフィグでEntity化をOFFにしている場合以外は意味が無い。 <br>
 * Entity化していないChocoGiftへの干渉はこのイベントでのみ行える。
 */
@Cancelable
@Event.HasResult
public class AMTBlockRightClickEvent extends Event {

	public final World world;
	public final EntityPlayer player;
	public final ItemStack heldItem;

	public final int posX;
	public final int posY;
	public final int posZ;

	public AMTBlockRightClickEvent(World thisWorld, EntityPlayer thisPlayer, ItemStack item, int X, int Y, int Z) {
		this.world = thisWorld;
		this.player = thisPlayer;
		this.heldItem = item;
		this.posX = X;
		this.posY = Y;
		this.posZ = Z;
	}

}
