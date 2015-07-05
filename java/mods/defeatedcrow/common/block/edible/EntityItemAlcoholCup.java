package mods.defeatedcrow.common.block.edible;

import java.util.ArrayList;

import mods.defeatedcrow.api.potion.AMTPotionManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.entity.edible.PlaceableAlcoholCup;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityItemAlcoholCup extends EdibleEntityItemBlock2 {

	private static final String[] type = new String[] { "_sake", "_beer", "_wine", "_gin", "_rum", "_vodka",
			"_whiskey", "_apple", "_tea", "_cassis", "_plum", "_shothu", "_brandy", "_amaretto" };

	public EntityItemAlcoholCup(Block block) {
		super(block, false, true);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		if (m < 14)
			return super.getUnlocalizedName() + type[m];
		else
			return super.getUnlocalizedName() + m;
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (!par2World.isRemote) {
			this.addSSMoisture(4, 3F, par3EntityPlayer);
			if (DCsAppleMilk.suffocation != null && par3EntityPlayer.isPotionActive(DCsAppleMilk.suffocation)) {
				par3EntityPlayer.removePotionEffect(DCsAppleMilk.suffocation.id);
			}
		}
		return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
	}

	@Override
	public ArrayList<PotionEffect> effectOnEaten(EntityPlayer player, int meta) {

		ArrayList<PotionEffect> ret = new ArrayList<PotionEffect>();
		ret.add(new PotionEffect(Potion.hunger.id, 200, 1));
		ret.addAll(this.getPotionWithIce(player, meta));
		return ret;
	}

	@Override
	public int[] hungerOnEaten(int meta) {
		return new int[] { 0, 0 };
	}

	@Override
	public ItemStack getReturnContainer(int meta) {

		return new ItemStack(DCsAppleMilk.emptyCup, 1, 0);
	}

	protected static ArrayList<PotionEffect> getPotionWithIce(EntityPlayer par1EntityPlayer, int meta) {
		ArrayList<PotionEffect> ret = new ArrayList<PotionEffect>();
		int id[] = { Potion.regeneration.id, 2400, 0 };

		if (meta == 0 || meta == 11) {
			id[0] = AMTPotionManager.manager.AMTgetPotion("reflex").getId();
			id[1] = 600;
		} else if (meta == 1) {
			id[0] = Potion.digSpeed.id;
		} else if (meta == 2) {
			id[0] = AMTPotionManager.manager.AMTgetPotion("absorb_heal").getId();
			id[1] = 600;
		} else if (meta == 3 || meta == 13) {
			id[0] = Potion.damageBoost.id;
		} else if (meta == 4) {
			id[0] = Potion.resistance.id;
		} else if (meta == 5) {
			id[0] = Potion.damageBoost.id;
			id[2] = 1;
		} else if (meta == 6) {
			id[0] = Potion.digSpeed.id;
		} else if (meta == 7) {
			id[0] = Potion.jump.id;
		} else if (meta == 8) {
			id[0] = Potion.heal.id;
			id[1] = 1;
			id[2] = 1;
		} else if (meta == 9) {
			id[0] = Potion.fireResistance.id;
		} else if (meta == 10) {
			id[0] = AMTPotionManager.manager.AMTgetPotion("suffocation_resist").getId();
		} else if (meta == 12) {
			id[0] = Potion.regeneration.id;
			id[1] = 1200;
			id[2] = 1;
		} else {// 例外用
			id[0] = Potion.heal.id;
			id[1] = 1;
		}

		if (id[0] != 0) {
			Potion p = Potion.potionTypes[id[0]];
			if (p != null) {

			}
			if (par1EntityPlayer.isPotionActive(id[0])) {
				id[1] += par1EntityPlayer.getActivePotionEffect(p).getDuration();
				ret.add(new PotionEffect(id[0], id[1], id[2]));
			} else {
				ret.add(new PotionEffect(id[0], id[1], id[2]));
			}
		}

		// if (ret.isEmpty()) {
		// ret.add(new PotionEffect(Potion.heal.id, 1, 1));
		// }
		return ret;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.drink;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	protected boolean spownEntityFoods(World world, EntityPlayer player, ItemStack item, double x, double y, double z) {
		PlaceableAlcoholCup entity = new PlaceableAlcoholCup(world, item, x, y, z);
		entity.rotationYaw = player.rotationYaw - 180.0F;

		if (!world.isRemote && item != null) {
			return world.spawnEntityInWorld(entity);
		}

		return false;
	}
}
