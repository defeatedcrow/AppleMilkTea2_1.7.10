package mods.defeatedcrow.api.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

/**
 * IEdibleを実装しているAMTの飲食アイテムの効果発揮時に差し込めるイベント。 <br>
 * キャンセル可能。 <br>
 * 必ずResultを返す必要がある。Result.ALLOWの場合、アイテムがひとつ消費される。
 */
@Cancelable
@Event.HasResult
public class EatEdiblesEvent extends Event {

	public final World world;
	public final EntityPlayer player;
	public final ItemStack edibles;

	public EatEdiblesEvent(World thisWorld, EntityPlayer thisPlayer, ItemStack item) {
		this.world = thisWorld;
		this.player = thisPlayer;
		this.edibles = item;
	}

}
