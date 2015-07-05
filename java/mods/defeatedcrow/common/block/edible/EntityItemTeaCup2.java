package mods.defeatedcrow.common.block.edible;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mods.defeatedcrow.api.potion.AMTPotionManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.entity.edible.PlaceableCup2;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityItemTeaCup2 extends EdibleEntityItemBlock2 {

	public static final String[] teaType = new String[] { "_earlgray", "_earlgray_milk", "_appletea", "_appletea_milk",
			"_lime", "_tomato", "_berry", "_berry_milk", "_grape", "_mint", "_yuzu", "_orange", "_soda" };

	private int healAmount = 0;

	public EntityItemTeaCup2(Block block) {
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
		int dur = 600;

		if ((meta & 1) == 1) {
			dur = 1200;
		}

		int id[] = { Potion.regeneration.id, dur, 0 };

		if (meta == 0 || meta == 1) {
			id[0] = AMTPotionManager.manager.AMTgetPotion("immunization").getId();
			id[2] = 1;
		} else if (meta == 2 || meta == 3) {
			id[0] = AMTPotionManager.manager.AMTgetPotion("immunization").getId();
		} else if (meta == 5) {
			id[0] = Potion.damageBoost.id;
		} else if (meta == 6 || meta == 7) {
			id[0] = Potion.resistance.id;
		} else if (meta == 8 || meta == 12) {
			id[0] = Potion.moveSpeed.id;
		} else if (meta == 11) {
			id[0] = Potion.jump.id;
			id[2] = 1;
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

	protected static void clearNegativePotion(EntityPlayer player) {
		if (player.isPotionActive(Potion.blindness)) {
			player.removePotionEffect(Potion.blindness.id);
		}
		if (player.isPotionActive(Potion.confusion)) {
			player.removePotionEffect(Potion.confusion.id);
		}
		if (player.isPotionActive(Potion.hunger)) {
			player.removePotionEffect(Potion.hunger.id);
		}
		if (player.isPotionActive(Potion.poison)) {
			player.removePotionEffect(Potion.poison.id);
		}
		if (player.isPotionActive(Potion.weakness)) {
			player.removePotionEffect(Potion.weakness.id);
		}
	}

	protected static void increaseDuration(EntityLivingBase living) {
		Iterator current = living.getActivePotionEffects().iterator();
		int increase = 6000;

		while (current.hasNext()) {
			PotionEffect potioneffect = (PotionEffect) current.next();
			boolean flag = potioneffect.getPotionID() != Potion.heal.id && potioneffect.getPotionID() != Potion.harm.id
					&& potioneffect.getPotionID() != Potion.regeneration.id;

			if (flag) {
				int d = Math.min(potioneffect.getDuration() + increase, 30000);
				living.addPotionEffect(new PotionEffect(potioneffect.getPotionID(), d, potioneffect.getAmplifier()));
			}
		}
	}

	protected void setPotionWithTea(EntityPlayer par1EntityPlayer, int meta) {
		if (meta < 4) {
			par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.heal.id, 1, 0));
		}

		if (meta == 4 || meta == 10) {
			clearNegativePotion(par1EntityPlayer);
		}

		if (meta == 9) {
			increaseDuration(par1EntityPlayer);
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
		if (m < 13)
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
		PlaceableCup2 entity = new PlaceableCup2(world, item, x, y, z);
		entity.rotationYaw = player.rotationYaw - 180.0F;

		if (!world.isRemote && item != null) {
			return world.spawnEntityInWorld(entity);
		}

		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		int l = par1ItemStack.getItemDamage();
		if (l == 4 || l == 10) {
			String s = "Remove the negative potion effects.";
			par3List.add(s);
		} else if (l == 9) {
			String s = "Increase the duration of the current potion effects.";
			par3List.add(s);
		}
	}

}
