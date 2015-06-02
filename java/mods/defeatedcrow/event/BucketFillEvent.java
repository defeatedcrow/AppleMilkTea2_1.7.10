package mods.defeatedcrow.event;

import java.util.HashMap;
import java.util.Map;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;

/**
 * This Class was created based on the BucketHander.class (BuildCraft).
 * Original code was created by SpaceToad and BuildCraft Team.
 */
public class BucketFillEvent {

	public static Map<Block, Item> buckets = new HashMap<Block, Item>();

	@SubscribeEvent
	public void onBucketFill(FillBucketEvent event) {
		ItemStack result = fillCustomBucket(event.world, event.target);

		if (result == null) {
			return;
		}

		event.result = result;
		event.setResult(Result.ALLOW);
	}

	private ItemStack fillCustomBucket(World world, MovingObjectPosition pos) {
		Block block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);

		Item bucket = buckets.get(block);
		if (bucket != null) {
			AMTLogger.debugInfo("bucket event : " + bucket.getUnlocalizedName());
		}

		if (bucket != null && world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) == 0) {
			world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
			return new ItemStack(bucket);
		} else {
			return null;
		}
	}

	public void register() {
		buckets.put(DCsAppleMilk.blockVegitableOil, DCsAppleMilk.bucketVegiOil);
		buckets.put(DCsAppleMilk.blockCamelliaOil, DCsAppleMilk.bucketCamOil);
	}

}
