package mods.defeatedcrow.common.block.brewing;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.plugin.IC2.LoadIC2Plugin;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;

import cpw.mods.fml.common.Loader;

public class ItemEmptyBottle extends ItemBlock {

	public ItemEmptyBottle(Block block) {
		super(block);
	}

	// 特定のBlockに使用した場合、設置の代わりに効果を発揮する
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
			int par5, int par6, int par7, float par8, float par9, float par10) {
		Block block = par3World.getBlock(par4, par5, par6);
		TileEntity tile = par3World.getTileEntity(par4, par5, par6);

		if (tile != null) {
			if (Loader.isModLoaded("IC2") && LoadIC2Plugin.isRumBarrel(tile, false)) {

				ItemStack rum = new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 53);
				// プレイヤーにラムを与える
				if (!par2EntityPlayer.capabilities.isCreativeMode && --par1ItemStack.stackSize <= 0) {
					par2EntityPlayer.inventory.setInventorySlotContents(par2EntityPlayer.inventory.currentItem,
							(ItemStack) null);
				}

				if (!par2EntityPlayer.inventory.addItemStackToInventory(rum)) {
					if (!par3World.isRemote) {
						par2EntityPlayer.entityDropItem(rum, 1);
					}
				}
				par3World.playSoundAtEntity(par2EntityPlayer, "random.pop", 0.4F, 1.8F);
				par2EntityPlayer.inventory.markDirty();
				LoadIC2Plugin.isRumBarrel(tile, true);

				return true;
			}
		}

		return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
	}

	// mobを殴ったときの挙動
	@Override
	public Multimap getItemAttributeModifiers() {
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(
				field_111210_e, "Tool modifier", 2.0D, 0));
		return multimap;
	}

}
