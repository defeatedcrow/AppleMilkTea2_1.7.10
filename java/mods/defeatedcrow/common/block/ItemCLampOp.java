package mods.defeatedcrow.common.block;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCLampOp extends ItemBlock {

	private static final String[] type = new String[] { "", "_orange", "_white", "_black" };

	public ItemCLampOp(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);

	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		if (m < 4)
			return super.getUnlocalizedName() + type[m];
		else
			return super.getUnlocalizedName() + m;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

}
