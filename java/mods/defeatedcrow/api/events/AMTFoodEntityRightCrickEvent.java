package mods.defeatedcrow.api.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

/**
 * AMT2で追加されるEntity状態の食べ物の右クリック効果前に呼ばれるイベント。
 * <br>Cancelした場合、本来の処理は呼ばれない。
 * */
@Cancelable
@Event.HasResult
public class AMTFoodEntityRightCrickEvent extends Event{
	
	public final World world;
	public final EntityPlayer player;
	public final ItemStack heldItem;
	
	public final Entity target;
	
	public AMTFoodEntityRightCrickEvent (World thisWorld, EntityPlayer thisPlayer, ItemStack item, Entity entity)
	{
		this.world = thisWorld;
		this.player = thisPlayer;
		this.heldItem = item;
		this.target = entity;
	}

}
