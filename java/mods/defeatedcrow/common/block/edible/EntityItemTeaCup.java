package mods.defeatedcrow.common.block.edible;

import java.util.ArrayList;

import mods.defeatedcrow.api.potion.AMTPotionManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.entity.edible.PlaceableCup1;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityItemTeaCup extends EdibleEntityItemBlock2 {

	public static final String[] teaType = new String[] { "_empty", "_milk", "_tea", "_tea_milk", "_greentea",
			"_greentea_milk", "_cocoa", "_cocoa_milk", "_juice", "_fruitshakes", "_lemon", "_lemon_milk", "_coffee",
			"_coffee_milk" };

	private int healAmount = 0;

	public EntityItemTeaCup(Block block) {
		super(block, true, true);
		setMaxDamage(0);
		setHasSubtypes(true);
		this.setMaxStackSize(Util.getCupStacksize());
		setContainerItem(Item.getItemFromBlock(DCsAppleMilk.emptyCup));
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		int meta = par1ItemStack.getItemDamage();

		if (!par2World.isRemote) {
			this.setPotionWithTea(par3EntityPlayer, meta);
			this.addSSMoisture(6, 1.5F, par3EntityPlayer);
		}

		return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
	}

	@Override
	public ItemStack getReturnContainer(int meta) {

		return new ItemStack(DCsAppleMilk.emptyCup, 1, 0);
	}

	@Override
	public int[] hungerOnEaten(int meta) {
		return new int[] { 0, 0 };
	}

	@Override
	public ArrayList<PotionEffect> effectOnEaten(EntityPlayer player, int meta) {

		ArrayList<PotionEffect> ret = new ArrayList<PotionEffect>();
		int id[] = { Potion.regeneration.id, 1200, 0 };

		if (meta == 0) {// milk
			id[0] = AMTPotionManager.manager.AMTgetPotion("immunization").getId();
			id[1] = 5;
		} else if (meta == 4 || meta == 5) {
			id[0] = Potion.digSpeed.id;
		} else if (meta == 6 || meta == 7) {
			id[0] = Potion.nightVision.id;
		} else if (meta == 8 || meta == 9) {
			id[0] = AMTPotionManager.manager.AMTgetPotion("immunization").getId();
		} else if (meta == 10 || meta == 11) {
			id[0] = AMTPotionManager.manager.AMTgetPotion("immunization").getId();
			id[2] = 1;
		} else if (meta == 12 || meta == 13) {
			id[0] = Potion.nightVision.id;
		} else {// 例外用
			id[0] = Potion.heal.id;
			id[1] = 1;
		}

		if (id[0] != 0) {
			Potion p = Potion.potionTypes[id[0]];
			if (p != null) {

			}
			if (player.isPotionActive(id[0])) {
				id[1] += player.getActivePotionEffect(p).getDuration();
				ret.add(new PotionEffect(id[0], id[1], id[2]));
			} else {
				ret.add(new PotionEffect(id[0], id[1], id[2]));
			}
		}

		if ((meta & 1) == 1 || ret.isEmpty()) {
			ret.add(new PotionEffect(Potion.regeneration.id, 200, 0));
		}
		return ret;
	}

	protected void setPotionWithTea(EntityPlayer par1EntityPlayer, int meta) {
		if (meta == 1) {
			par1EntityPlayer.clearActivePotions();
		}

		if (DCsAppleMilk.suffocation != null && par1EntityPlayer.isPotionActive(DCsAppleMilk.suffocation)) {
			par1EntityPlayer.removePotionEffect(DCsAppleMilk.suffocation.id);
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 16;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.drink;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		if (m < 14)
			return super.getUnlocalizedName() + teaType[m];
		else
			return super.getUnlocalizedName() + m;

	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	protected boolean spownEntityFoods(World world, EntityPlayer player, ItemStack item, double x, double y, double z) {
		PlaceableCup1 entity = new PlaceableCup1(world, item, x, y, z);
		entity.rotationYaw = player.rotationYaw - 180.0F;

		if (!world.isRemote && item != null) {
			return world.spawnEntityInWorld(entity);
		}

		return false;
	}

}
