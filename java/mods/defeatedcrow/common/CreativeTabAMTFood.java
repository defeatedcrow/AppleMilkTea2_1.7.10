package mods.defeatedcrow.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabAMTFood extends CreativeTabs {

	public CreativeTabAMTFood(String type) {
		super(type);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
		return "Apple&Milk&Tea:Foods";
	}

	@Override
	public Item getTabIconItem() {
		return DCsAppleMilk.appleTart;
	}

}
