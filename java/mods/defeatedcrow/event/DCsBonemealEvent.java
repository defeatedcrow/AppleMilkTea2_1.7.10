package mods.defeatedcrow.event;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.block.plants.BlockCassisTree;
import mods.defeatedcrow.common.block.plants.BlockMintCrop;
import mods.defeatedcrow.common.block.plants.BlockSaplingTea;
import mods.defeatedcrow.common.block.plants.BlockTeaTree;
import mods.defeatedcrow.common.block.plants.BlockYuzuSapling;
import net.minecraft.block.Block;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DCsBonemealEvent {
	@SubscribeEvent
	public void useBoneMeal(BonemealEvent event) {
		Block id = event.world.getBlock(event.x, event.y, event.z);
		if (id == DCsAppleMilk.cropMint) {
			if (((BlockMintCrop) DCsAppleMilk.cropMint).fertilize(event.world, event.x, event.y, event.z)) {
				event.setResult(Result.ALLOW);
			}
		} else if (id == DCsAppleMilk.cassisTree) {
			if (((BlockCassisTree) DCsAppleMilk.cassisTree).fertilize(event.world, event.x, event.y, event.z)) {
				event.setResult(Result.ALLOW);
			}
		} else if (id == DCsAppleMilk.teaTree) {
			if (((BlockTeaTree) DCsAppleMilk.teaTree).fertilize(event.world, event.x, event.y, event.z)) {
				event.setResult(Result.ALLOW);
			}
		} else if (id == DCsAppleMilk.saplingTea) {
			if (((BlockSaplingTea) DCsAppleMilk.saplingTea).fertilize(event.world, event.x, event.y, event.z)) {
				event.setResult(Result.ALLOW);
			}
		} else if (id == DCsAppleMilk.saplingYuzu) {
			if (((BlockYuzuSapling) DCsAppleMilk.saplingYuzu).fertilize(event.world, event.x, event.y, event.z)) {
				event.setResult(Result.ALLOW);
			}
		}
	}

}
