package mods.defeatedcrow.common.fluid;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBottleVegiOil extends ItemBucket {

	public ItemBottleVegiOil(Block block) {
		super(block);
		this.setContainerItem(Item.getItemFromBlock(DCsAppleMilk.emptyBottle));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:bottle_oil");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
		return p_77659_1_;
	}

}
