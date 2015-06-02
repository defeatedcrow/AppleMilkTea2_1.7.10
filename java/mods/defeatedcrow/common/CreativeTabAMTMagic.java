package mods.defeatedcrow.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabAMTMagic extends CreativeTabs {

	public CreativeTabAMTMagic(String type) {
		super(type);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
		return "Apple&Milk&Tea:Charms";
	}

	@Override
	public Item getTabIconItem() {
		return DCsAppleMilk.princessClam;
	}

}
