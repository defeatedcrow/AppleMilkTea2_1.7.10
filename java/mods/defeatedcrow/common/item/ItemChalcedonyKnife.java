package mods.defeatedcrow.common.item;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import mods.defeatedcrow.api.events.KnifeCutEvent;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.MinecraftForge;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemChalcedonyKnife extends ItemTool {

	public static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[] {
			Blocks.pumpkin,
			Blocks.lit_pumpkin,
			Blocks.melon_block,
			Blocks.wool });

	public ItemChalcedonyKnife(ToolMaterial par2) {
		super(3.0F, par2, blocksEffectiveAgainst);
		this.setMaxStackSize(1);
	}

	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6,
			EntityLivingBase par7EntityLivingBase) {
		Block ID = par2World.getBlock(par3, par4, par5);
		int meta = par2World.getBlockMetadata(par3, par4, par5);

		// event
		KnifeCutEvent event = new KnifeCutEvent(par2World, par7EntityLivingBase, ID, meta, par3, par4, par5);

		MinecraftForge.EVENT_BUS.post(event);

		if (event.hasResult() && event.getResult() == Result.ALLOW) {
			return true;
		}

		if (event.isCanceled()) {
			return false;
		}

		if (ID != Blocks.leaves && ID != Blocks.web && ID != Blocks.tallgrass && ID != Blocks.vine
				&& ID != Blocks.tripwire && ID != Blocks.pumpkin && ID != Blocks.melon_block
				&& !(ID instanceof IShearable)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean func_150897_b(Block par1Block) {
		return par1Block == Blocks.web || par1Block == Blocks.redstone_wire || par1Block == Blocks.tripwire;
	}

	@Override
	public float func_150893_a(ItemStack par1ItemStack, Block par2Block) {
		return par2Block != Blocks.web && par2Block != Blocks.leaves ? (par2Block == Blocks.wool ? 5.0F : super
				.func_150893_a(par1ItemStack, par2Block)) : 15.0F;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity) {
		if (entity.worldObj.isRemote) {
			return false;
		}
		if (entity instanceof IShearable) {
			IShearable target = (IShearable) entity;
			if (target.isShearable(itemstack, entity.worldObj, (int) entity.posX, (int) entity.posY, (int) entity.posZ)) {
				ArrayList<ItemStack> drops = target.onSheared(itemstack, entity.worldObj, (int) entity.posX,
						(int) entity.posY, (int) entity.posZ,
						EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, itemstack));

				Random rand = new Random();
				for (ItemStack stack : drops) {
					EntityItem ent = entity.entityDropItem(stack, 1.0F);
					ent.motionY += rand.nextFloat() * 0.05F;
					ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
					ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
				}
				itemstack.damageItem(1, entity);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player) {
		if (player.worldObj.isRemote) {
			return false;
		}
		Block id = player.worldObj.getBlock(x, y, z);
		if (id instanceof IShearable) {
			IShearable target = (IShearable) id;
			if (target.isShearable(itemstack, player.worldObj, x, y, z)) {
				ArrayList<ItemStack> drops = target.onSheared(itemstack, player.worldObj, x, y, z,
						EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, itemstack));
				Random rand = new Random();

				for (ItemStack stack : drops) {
					float f = 0.7F;
					double d = rand.nextFloat() * f + (1.0F - f) * 0.5D;
					double d1 = rand.nextFloat() * f + (1.0F - f) * 0.5D;
					double d2 = rand.nextFloat() * f + (1.0F - f) * 0.5D;
					EntityItem entityitem = new EntityItem(player.worldObj, x + d, y + d1, z + d2, stack);
					entityitem.delayBeforeCanPickup = 10;
					player.worldObj.spawnEntityInWorld(entityitem);
				}

				itemstack.damageItem(1, player);
				player.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(id)], 1);
			}
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:tools/chalcedonyknife");
	}

}
