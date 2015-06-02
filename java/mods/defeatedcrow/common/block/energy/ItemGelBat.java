package mods.defeatedcrow.common.block.energy;

import cpw.mods.fml.common.Optional;
import shift.sextiarysector.api.gearforce.item.IGearForceItemManager;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import mods.defeatedcrow.api.energy.BatteyItemBlockBase;
import mods.defeatedcrow.common.config.PropertyHandler;

public class ItemGelBat extends BatteyItemBlockBase {

	public ItemGelBat(Block block) {
		super(block);
		this.setMaxStackSize(1);
	}

	@Override
	public int getMaxAmount(ItemStack item) {
		return 12800;
	}
}
