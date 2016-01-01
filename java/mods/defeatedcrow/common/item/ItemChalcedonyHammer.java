package mods.defeatedcrow.common.item;

import java.util.Set;

import mods.defeatedcrow.common.AMTLogger;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import com.google.common.collect.Sets;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemChalcedonyHammer extends ItemPickaxe {
	/** an array of the blocks this pickaxe is effective against */
	public static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[] {
			Blocks.cobblestone,
			Blocks.double_stone_slab,
			Blocks.stone_slab,
			Blocks.stone,
			Blocks.sandstone,
			Blocks.mossy_cobblestone,
			Blocks.iron_ore,
			Blocks.iron_block,
			Blocks.coal_ore,
			Blocks.gold_block,
			Blocks.gold_ore,
			Blocks.diamond_ore,
			Blocks.diamond_block,
			Blocks.ice,
			Blocks.netherrack,
			Blocks.lapis_ore,
			Blocks.lapis_block,
			Blocks.redstone_ore,
			Blocks.lit_redstone_ore,
			Blocks.rail,
			Blocks.detector_rail,
			Blocks.golden_rail,
			Blocks.activator_rail });

	public ItemChalcedonyHammer(ToolMaterial par2EnumToolMaterial) {
		super(par2EnumToolMaterial);
		this.setHarvestLevel("pickaxe", 2);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, Block par3, int par4, int par5, int par6,
			EntityLivingBase par7EntityLivingBase) {
		if (par3 != Blocks.ice) {
			return super.onBlockDestroyed(par1ItemStack, par2World, par3, par4, par5, par6, par7EntityLivingBase);
		} else {
			return true;
		}
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
			int par5, int par6, int par7, float par8, float par9, float par10) {
		Block i1 = par3World.getBlock(par4, par5, par6);
		int meta = par3World.getBlockMetadata(par4, par5, par6);
		if (par1ItemStack == null || i1 == null)
			return false;

		int damage = 0;
		boolean deco = false;
		boolean brk = false;
		int eff = 0;
		int unb = 0;

		// エンチャントのチェック
		if (par1ItemStack.hasTagCompound()) {
			NBTTagList nbttaglist = par1ItemStack.getEnchantmentTagList();
			if (nbttaglist != null) {
				for (int i = 0; i < nbttaglist.tagCount(); ++i) {
					short short1 = nbttaglist.getCompoundTagAt(i).getShort("id");
					short short2 = nbttaglist.getCompoundTagAt(i).getShort("lvl");

					if (short1 == Enchantment.efficiency.effectId) {
						eff = short2;
					}
					if (short1 == Enchantment.unbreaking.effectId) {
						unb = short2;
					}
				}
			}
		}

		// ターゲットの判定
		if (i1 == Blocks.stone || i1 == Blocks.stonebrick) {
			deco = true;
		}

		if (eff > 1 && i1.getMaterial() == Material.glass) {
			brk = true;
		} else if (eff > 0 && (i1.getMaterial() == Material.ice || i1.getMaterial() == Material.packedIce)) {
			brk = true;
		} else {
			brk = (i1 == Blocks.stone || i1 == Blocks.ice);
		}

		// ダメージ
		if (unb > 0 && Item.itemRand.nextInt(unb) != 0) {
			damage = 0;
		} else {
			damage = 2 + eff;
		}

		AMTLogger.debugInfo("Current data...");
		AMTLogger.debugInfo("Block : " + i1.getLocalizedName());
		AMTLogger.debugInfo("Enchantment : efficiency " + eff + ", unbreaking " + unb);
		AMTLogger.debugInfo("Result : deco " + deco + ", breakable " + brk);

		if (par2EntityPlayer.isSneaking()) {
			if (deco) {
				if (i1 == Blocks.stone) {
					par3World.setBlock(par4, par5, par6, Blocks.stonebrick, 0, 3);
				} else if (i1 == Blocks.stonebrick) {
					par3World.setBlock(par4, par5, par6, Blocks.stonebrick, 3, 3);
				}
				par3World.playSoundEffect(par4 + 0.5F, par5 + 0.5F, par6 + 0.5F,
						Blocks.stone.stepSound.getBreakSound(), (Blocks.stone.stepSound.getVolume() + 1.0F) / 2.0F,
						Blocks.stone.stepSound.getPitch() * 0.8F);
				par1ItemStack.damageItem(damage, par2EntityPlayer);
				return true;
			}
		} else {
			if (brk) {
				ItemStack get = new ItemStack(i1, 1, meta);

				if (i1 == Blocks.stone
						|| (i1.getItemDropped(meta, itemRand, 0) == null || i1.quantityDropped(meta, 0, itemRand) == 0)) {
					par3World.setBlockToAir(par4, par5, par6);
					par3World.playSoundEffect(par4 + 0.5F, par5 + 0.5F, par6 + 0.5F, i1.stepSound.getBreakSound(),
							(i1.stepSound.getVolume() + 1.0F) / 2.0F, i1.stepSound.getPitch() * 0.8F);

					if (!par2EntityPlayer.inventory.addItemStackToInventory(get)) {
						par2EntityPlayer.func_146097_a(get, true, false);// プレイヤーがアイテムを投げるのたぶんコレ
					}

					par1ItemStack.damageItem(damage, par2EntityPlayer);
					return true;
				}

			}
		}

		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:tools/chalcedonyhammer");
	}
}
