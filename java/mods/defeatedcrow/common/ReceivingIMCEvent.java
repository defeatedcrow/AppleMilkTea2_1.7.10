package mods.defeatedcrow.common;

import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.recipe.IceRecipeRegister;
import mods.defeatedcrow.recipe.TeaRecipeRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;

public class ReceivingIMCEvent {

	private ReceivingIMCEvent() {
	}

	public static void receiveIMC(IMCEvent event) {
		for (IMCMessage message : event.getMessages()) {
			if (message.key.equals("TeaMakerRecipe")) {
				receiveAddTeaRecipe(event, message);
			} else if (message.key.equals("IceMakerRecipe")) {
				receiveAddIceRecipe(event, message);
			} else if (message.key.equals("IceChargeItem")) {
				receiveAddIceCharge(event, message);
			} else {
				AMTLogger.warn("Received IMC message with unknown key : " + message.key);
			}
		}
	}

	private static void receiveAddTeaRecipe(IMCEvent event, IMCMessage message) {
		boolean flag = false;
		if (message.isNBTMessage()) {
			NBTTagCompound tag = message.getNBTValue();
			if (tag.hasKey("input", 10) && tag.hasKey("output", 10) && tag.hasKey("texture")) {
				ItemStack input = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("input"));
				ItemStack output = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("output"));
				ItemStack outputMilk = null;
				if (tag.hasKey("outputMilk", 10)) {
					outputMilk = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("outputMilk"));
				}
				String texture = tag.getString("texture");
				String texture_milk = "defeatedcrow:textures/blocks/contents_water.png";
				if (tag.hasKey("textureMilk")) {
					texture_milk = tag.getString("textureMilk");
				}

				if (input != null && output != null && texture != null) {
					RecipeRegisterManager.teaRecipe.registerCanMilk(input, output, outputMilk, texture, texture_milk);
					flag = true;
				}
			}
		}

		if (!flag) {
			AMTLogger.warn("Failed to register new TeaMaker recipe with IMCMassage from " + message.getSender());
		}
	}

	private static void receiveAddIceRecipe(IMCEvent event, IMCMessage message) {
		boolean flag = false;
		if (message.isNBTMessage()) {
			NBTTagCompound tag = message.getNBTValue();
			if (tag.hasKey("input", 10) && tag.hasKey("output", 10)) {
				ItemStack input = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("input"));
				ItemStack output = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("output"));
				ItemStack container = null;
				if (tag.hasKey("container", 10)) {
					container = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("container"));
				}

				if (input != null && output != null) {
					RecipeRegisterManager.iceRecipe.registerCanLeave(input, output, container);
					flag = true;
				}
			}
		}

		if (!flag) {
			AMTLogger.warn("Failed to register new IceMaker recipe with IMCMassage from " + message.getSender());
		}
	}

	private static void receiveAddIceCharge(IMCEvent event, IMCMessage message) {
		boolean flag = false;
		if (message.isNBTMessage()) {
			NBTTagCompound tag = message.getNBTValue();
			if (tag.hasKey("input", 10) && tag.hasKey("amount")) {
				ItemStack input = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("input"));
				int amount = 0;
				amount = tag.getInteger("amount");

				if (input != null && amount > 0) {
					RecipeRegisterManager.iceRecipe.registerCharger(input, amount);
					flag = true;
				}
			}
		}

		if (!flag) {
			AMTLogger
					.warn("Failed to register new IceMaker chageable item with IMCMassage from " + message.getSender());
		}
	}

}
