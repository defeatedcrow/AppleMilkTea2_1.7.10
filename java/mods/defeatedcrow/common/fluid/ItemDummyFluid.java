package mods.defeatedcrow.common.fluid;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDummyFluid extends ItemBlock {

	public ItemDummyFluid(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		return super.getUnlocalizedName() + "_" + m;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	// 設置禁止
	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
		return p_77659_1_;
	}

}
