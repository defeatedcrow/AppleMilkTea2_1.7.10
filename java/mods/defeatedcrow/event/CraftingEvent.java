package mods.defeatedcrow.event;

import java.util.ArrayList;

import mods.defeatedcrow.common.AchievementRegister;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.FakePlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class CraftingEvent {

	@SubscribeEvent
	public void onCraftingEvent(PlayerEvent.ItemCraftedEvent event) {

		EntityPlayer player = event.player;
		IInventory craftMatrix = event.craftMatrix;
		ItemStack crafting = event.crafting;

		// 実績
		if (crafting != null) {
			if (crafting.getItem() == Item.getItemFromBlock(DCsAppleMilk.teaMakerNext)) {
				player.triggerAchievement(AchievementRegister.craftTeaMaker);
			} else if (crafting.getItem() == Item.getItemFromBlock(DCsAppleMilk.emptyPanGaiden)) {
				player.triggerAchievement(AchievementRegister.craftPan);
			} else if (crafting.getItem() == Item.getItemFromBlock(DCsAppleMilk.teppanII)) {
				player.triggerAchievement(AchievementRegister.craftTeppan);
			} else if (crafting.getItem() == Item.getItemFromBlock(DCsAppleMilk.iceMaker)) {
				player.triggerAchievement(AchievementRegister.craftIceMaker);
			} else if (crafting.getItem() == Item.getItemFromBlock(DCsAppleMilk.woodBox)) {
				player.triggerAchievement(AchievementRegister.craftLogBox);
			} else if (crafting.getItem() == DCsAppleMilk.EXItems && crafting.getItemDamage() == 3) {
				player.triggerAchievement(AchievementRegister.craftChalGear);
			} else if (crafting.getItem() == Item.getItemFromBlock(DCsAppleMilk.processor)) {
				player.triggerAchievement(AchievementRegister.craftAutoMaker);
			} else if (crafting.getItem() == Item.getItemFromBlock(DCsAppleMilk.vegiBag)) {
				player.triggerAchievement(AchievementRegister.craftVegiBag);
			} else if (crafting.getItem() == Item.getItemFromBlock(DCsAppleMilk.cLamp) && crafting.getItemDamage() == 5) {
				player.triggerAchievement(AchievementRegister.craftGlassLamp);
			} else if (crafting.getItem() == DCsAppleMilk.DCgrater) {
				player.triggerAchievement(AchievementRegister.craftGrater);
			} else if (crafting.getItem() == DCsAppleMilk.batteryItem && crafting.getItemDamage() == 2) {
				player.triggerAchievement(AchievementRegister.craftYuzuBattery);
			} else if (crafting.getItem() == Item.getItemFromBlock(DCsAppleMilk.gelBat)) {
				player.triggerAchievement(AchievementRegister.craftChargeableBat);
			} else if (crafting.getItem() == Item.getItemFromBlock(DCsAppleMilk.barrel)) {
				player.triggerAchievement(AchievementRegister.craftBarrel);
			} else if (crafting.getItem() == Item.getItemFromBlock(DCsAppleMilk.evaporator)) {
				player.triggerAchievement(AchievementRegister.craftEvp);
			} else if (crafting.getItem() == Item.getItemFromBlock(DCsAppleMilk.advProcessor)) {
				player.triggerAchievement(AchievementRegister.craftJaw);
			} else if (crafting.getItem() == DCsAppleMilk.princessClam && crafting.getItemDamage() > 0) {
				player.triggerAchievement(AchievementRegister.craftCharm);
			} else if (crafting.getItem() == DCsAppleMilk.appleTart) {
				player.triggerAchievement(AchievementRegister.craftTart);
			}
		}

		ArrayList<ItemStack> rets = new ArrayList<ItemStack>();

		for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
			ItemStack m = craftMatrix.getStackInSlot(i);

			if (m != null
					&& m.getItem() == Items.potionitem
					&& (crafting.getItem() == Item.getItemFromBlock(DCsAppleMilk.teacupBlock) || crafting.getItem() == Item
							.getItemFromBlock(DCsAppleMilk.teaCup2))) {
				rets.add(new ItemStack(Items.glass_bottle, 1, 0));
			}

			if (m != null && m.getItem() == DCsAppleMilk.itemLargeBottle) {
				int type = m.getItemDamage() & 15;
				int rem = (m.getItemDamage() >> 4) & 7;
				rem--;
				if (rem < 0) {
					rets.add(new ItemStack(DCsAppleMilk.emptyBottle, 1, 0));
				} else {
					int next = (rem << 4) + type;
					rets.add(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, next));
				}
			} else if (m != null && m.getItem() == DCsAppleMilk.itemCordial) {
				int rem = m.getItemDamage() & 3;
				if (rem > 0) {
					rem--;
					if (rem == 0) {
						rets.add(new ItemStack(DCsAppleMilk.emptyBottle, 1, 0));
					} else {
						int next = m.getItemDamage() - 1;
						rets.add(new ItemStack(DCsAppleMilk.itemCordial, 1, next));
					}
				} else {
					rets.add(new ItemStack(DCsAppleMilk.emptyBottle, 1, 0));
				}
			}
		}
		if (!rets.isEmpty()) {
			for (ItemStack ret : rets) {
				if (player != null && !(player instanceof FakePlayer) && !player.inventory.addItemStackToInventory(ret)) {
					player.entityDropItem(ret, 1);
				}
			}
		}
	}

	@SubscribeEvent
	public void onSmelting(PlayerEvent.ItemSmeltedEvent event) {

		EntityPlayer player = event.player;
		ItemStack item = event.smelting;

		if (item != null) {
			if (item.getItem() == Item.getItemFromBlock(DCsAppleMilk.charcoalBox)) {
				player.triggerAchievement(AchievementRegister.craftCharcoalContainer);
			} else if (item.getItem() == Item.getItemFromBlock(DCsAppleMilk.chalcedony)) {
				player.triggerAchievement(AchievementRegister.craftChalcedony);
			} else if (item.getItem() == DCsAppleMilk.foodTea && item.getItemDamage() == 0) {
				player.triggerAchievement(AchievementRegister.makeTeaLeaves);
			}
		}
	}

}
