package mods.defeatedcrow.api.events;

import net.minecraft.entity.Entity;
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
public class AMTFoodEntityRightCrickEvent extends AMTFoodEntityRightClickEvent {

	public AMTFoodEntityRightCrickEvent(World thisWorld, EntityPlayer thisPlayer, ItemStack item, Entity entity) {
		super(thisWorld, thisPlayer, item, entity);
	}
}
