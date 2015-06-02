package mods.defeatedcrow.handler;

import java.lang.reflect.Method;

import mods.defeatedcrow.common.AMTLogger;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * グーで殴る
 */
public class GenkotuHandler {

	public static ItemStack getMobsDrop(EntityLivingBase entity) {
		Method method;
		ItemStack ret = null;
		if (entity instanceof EntityLiving) {
			EntityLiving living = (EntityLiving) entity;
			String packageName = entity.getClass().getName();

			try {
				method = entity.getClass().getDeclaredMethod("getDropItem");
				method.setAccessible(true);
				Object obj = method.invoke(entity);
				if (obj != null && obj instanceof Item) {
					Item ID = (Item) obj;
					if (ID != null) {
						ret = new ItemStack(ID, 1, 0);
						AMTLogger.debugInfo("Item name : " + ret.getDisplayName());
					}
				}

			} catch (Exception e) {
				AMTLogger.info("Failed to get drop : " + entity.getCommandSenderName());
				return null;
			}
		}
		return ret;
	}

}
