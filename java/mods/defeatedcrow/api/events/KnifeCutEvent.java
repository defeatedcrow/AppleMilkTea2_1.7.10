package mods.defeatedcrow.api.events;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;

/**
 * ChalcedonyKnifeがブロックを破壊する直前に呼ばれるイベント。 <br>
 * キャンセル可能。
 */
@Cancelable
@Event.HasResult
public class KnifeCutEvent extends Event {

	public final World world;
	public final EntityLivingBase entity;
	public final Block target;
	public final int targetMeta;
	public final int posX;
	public final int posY;
	public final int posZ;

	public KnifeCutEvent(World thisWorld, EntityLivingBase thisEntity, Block block, int meta, int x, int y, int z) {
		this.world = thisWorld;
		this.entity = thisEntity;
		this.target = block;
		this.targetMeta = meta;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
	}

}
