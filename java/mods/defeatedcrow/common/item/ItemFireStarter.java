package mods.defeatedcrow.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFireStarter extends Item {
	public ItemFireStarter() {
		super();
		this.maxStackSize = 1;
		this.setMaxDamage(128);
	}

	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have
	 * one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
			int par5, int par6, int par7, float par8, float par9, float par10) {
		if (par7 == 0) {
			--par5;
		}

		if (par7 == 1) {
			++par5;
		}

		if (par7 == 2) {
			--par6;
		}

		if (par7 == 3) {
			++par6;
		}

		if (par7 == 4) {
			--par4;
		}

		if (par7 == 5) {
			++par4;
		}

		if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) {
			return false;
		} else {
			if (par3World.isAirBlock(par4, par5, par6)) {
				par3World.playSoundEffect(par4 + 0.5D, par5 + 0.5D, par6 + 0.5D, "fire.ignite", 1.0F,
						itemRand.nextFloat() * 0.4F + 0.8F);
				par3World.setBlock(par4, par5, par6, Blocks.fire);
			}

			par1ItemStack.damageItem(1, par2EntityPlayer);
			return true;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:tools/firestarter");
	}
}
