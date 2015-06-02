package mods.defeatedcrow.common.block.container;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEggBasket extends ItemBlock {

	public ItemEggBasket(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int i = par1ItemStack.getItemDamage();
		return super.getUnlocalizedName() + i;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

}
