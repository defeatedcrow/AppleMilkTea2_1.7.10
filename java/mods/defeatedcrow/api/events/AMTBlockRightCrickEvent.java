package mods.defeatedcrow.api.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

/**
 * This class will be deleted for fix the typo.
 */
@Deprecated
@Cancelable
@Event.HasResult
public class AMTBlockRightCrickEvent extends AMTBlockRightClickEvent {

	public AMTBlockRightCrickEvent(World thisWorld, EntityPlayer thisPlayer, ItemStack item, int X, int Y, int Z) {
		super(thisWorld, thisPlayer, item, X, Y, Z);
	}

}
