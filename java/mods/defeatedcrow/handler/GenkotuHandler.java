package mods.defeatedcrow.handler;

import java.lang.reflect.Method;

import mods.defeatedcrow.common.AMTLogger;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.ReflectionHelper;

/**
 * グーで殴る
 */
public class GenkotuHandler {

	public static ItemStack getMobsDrop(EntityLivingBase entity) {
		ItemStack ret = null;
		if (entity instanceof EntityLiving) {
			EntityLiving living = (EntityLiving) entity;
			String packageName = living.getClass().getName();

			try {
				Class clazz = living.getClass();
				String[] s = { "getDropItem" };
				Method method = ReflectionHelper.findMethod(clazz, living, s, Item.class);
				/* living.getClass().getDeclaredMethod("u"); */
				method.setAccessible(true);
				Object obj = method.invoke(living);
				if (obj != null && obj instanceof Item) {
					Item ID = (Item) obj;
					if (ID != null) {
						ret = new ItemStack(ID, 1, 0);
						AMTLogger.debugInfo("Item name : " + ret.getDisplayName());
					}
				}

			} catch (Exception e) {
				AMTLogger.info("Failed to get drop : " + living.getCommandSenderName());
				return null;
			}
		}
		return ret;
	}

}
