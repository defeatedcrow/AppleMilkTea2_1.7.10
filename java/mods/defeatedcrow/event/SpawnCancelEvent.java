package mods.defeatedcrow.event;

import mods.defeatedcrow.handler.Coord;
import mods.defeatedcrow.handler.CoordListRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SpawnCancelEvent {

	@SubscribeEvent
	public void onSpawnEvent(LivingSpawnEvent.CheckSpawn event) {
		Entity entity = event.entity;
		World world = event.world;

		if (!world.isRemote && entity != null && entity instanceof EntityLivingBase && entity instanceof IMob) {
			int x = MathHelper.floor_double(entity.posX);
			int y = MathHelper.floor_double(entity.posY);
			int z = MathHelper.floor_double(entity.posZ);
			if (entity instanceof EntityLiving && ((EntityLiving) entity).hasCustomNameTag()) {
				return;
			}

			int cX = x >> 4;
			int cZ = z >> 4;
			Coord cood = new Coord(cX, cZ, world.provider.dimensionId);
			if (CoordListRegister.isCoodIncluded(cood)) {
				if (entity.ridingEntity != null) {
					Entity ride = entity.ridingEntity;
					ride.riddenByEntity = null;
					entity.ridingEntity = null;
					ride.setDead();

				}
				if (entity.riddenByEntity != null) {
					Entity rider = entity.riddenByEntity;
					rider.ridingEntity = null;
					entity.riddenByEntity = null;
					rider.setDead();
				}
				event.setResult(Result.DENY);
			}
		}
	}

}
