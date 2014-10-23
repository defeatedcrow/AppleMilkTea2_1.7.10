package mods.defeatedcrow.common.block.energy;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import mods.defeatedcrow.api.energy.BatteyItemBlockBase;

public class ItemGelBat extends BatteyItemBlockBase{

	public ItemGelBat(Block block) {
		super(block);
		this.setMaxStackSize(1);
	}

	@Override
	public int getMaxAmount(ItemStack item) {
		return 12800;
	}

}
