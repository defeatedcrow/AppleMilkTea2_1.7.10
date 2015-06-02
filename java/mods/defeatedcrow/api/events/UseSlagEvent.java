package mods.defeatedcrow.api.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

/**
 * StrangeSlagの右クリック時に呼ばれる。 <br>
 * Cancelした場合、本来の処理は呼ばれない。
 */
@Cancelable
@Event.HasResult
public class UseSlagEvent extends Event {

	public final World world;
	public final EntityPlayer player;
	public ItemStack returnItem;

	public UseSlagEvent(World thisWorld, EntityPlayer thisPlayer, ItemStack item) {
		this.world = thisWorld;
		this.player = thisPlayer;
		this.returnItem = item;
	}
}
