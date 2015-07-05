package mods.defeatedcrow.common.item.magic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mods.defeatedcrow.api.events.UseSlagEvent;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.recipe.OreCrushRecipe;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemStrangeSlag extends Item {

	private Random rand = new Random();

	public ItemStrangeSlag() {
		super();
		this.setMaxStackSize(64);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:strange_slag");
	}

	/* 使用効果 */
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
			int par5, int par6, int par7, float par8, float par9, float par10) {
		if (par2EntityPlayer == null)
			return true;
		this.onItemRightClick(par1ItemStack, par3World, par2EntityPlayer);
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (entityplayer == null)
			return itemstack;

		ItemStack ret = this.returnItem(itemstack, world, entityplayer);
		boolean flag = false;

		UseSlagEvent event = new UseSlagEvent(world, entityplayer, ret);
		MinecraftForge.EVENT_BUS.post(event);
		boolean res = false;

		if (event.hasResult() && event.getResult() == Result.ALLOW) {
			ret = event.returnItem;
		}

		if (event.isCanceled()) {
			return itemstack;
		}

		if (ret != null && ret.getItem() != null) {
			float b = entityplayer.rotationYawHead;
			float b2 = entityplayer.rotationPitch;
			double bx = (double) (-MathHelper.sin(b / 180.0F * (float) Math.PI) * MathHelper.cos(b2 / 180.0F
					* (float) Math.PI));
			double bz = (double) (MathHelper.cos(b / 180.0F * (float) Math.PI) * MathHelper.cos(b2 / 180.0F
					* (float) Math.PI));
			double by = 0.15D;

			float a = (float) bx * 1.5F;
			float a1 = rand.nextFloat() * 0.5F + 0.25F;
			float a2 = (float) bz * 1.5F;

			EntityItem drop = new EntityItem(world, (double) ((float) entityplayer.posX + a),
					(double) ((float) entityplayer.posY + a1), (double) ((float) entityplayer.posZ + a2),
					new ItemStack(ret.getItem(), 1, ret.getItemDamage()));
			drop.motionX = bx;
			drop.motionY = by;
			drop.motionZ = bz;

			if (!world.isRemote) {
				world.spawnEntityInWorld(drop);
			}
			flag = true;
		}

		if (flag) {
			if (!entityplayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
				entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, (ItemStack) null);
				entityplayer.inventory.markDirty();
			}
		}

		return itemstack;
	}

	// ガチャの結果
	public ItemStack returnItem(ItemStack item, World world, EntityPlayer player) {
		int rand = this.rand.nextInt(100);
		ItemStack result = null;

		if (rand < 1) {
			ArrayList<ItemStack> gets = OreCrushRecipe.tier5;
			if (!gets.isEmpty()) {
				int r = this.rand.nextInt(gets.size());
				if (gets.get(r) != null)
					result = gets.get(r);
			}
		} else if (rand < 4) {
			ArrayList<ItemStack> gets = OreCrushRecipe.tier4;
			if (!gets.isEmpty()) {
				int r = this.rand.nextInt(gets.size());
				if (gets.get(r) != null)
					result = gets.get(r);
			}
		} else if (rand < 15) {
			ArrayList<ItemStack> gets = OreCrushRecipe.tier3;
			if (!gets.isEmpty()) {
				int r = this.rand.nextInt(gets.size());
				if (gets.get(r) != null)
					result = gets.get(r);
			}
		} else if (rand < 45) {
			ArrayList<ItemStack> gets = OreCrushRecipe.tier2;
			if (!gets.isEmpty()) {
				int r = this.rand.nextInt(gets.size());
				if (gets.get(r) != null)
					result = gets.get(r);
			}
		} else {
			ArrayList<ItemStack> gets = OreCrushRecipe.tier1;
			if (!gets.isEmpty()) {
				int r = this.rand.nextInt(gets.size());
				if (gets.get(r) != null)
					result = gets.get(r);
			}
		}

		if (result == null || result.getItem() == null) {
			result = new ItemStack(Items.bone);
		}

		return result;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		if (par1ItemStack != null && DCsAppleMilk.proxy.isShiftKeyDown()) { // shiftキー押下時
			par3List.add("Please use this with right-click.");
		} else {
			par3List.add(EnumChatFormatting.ITALIC + "LShift: Expand tooltip.");
		}
	}
}
