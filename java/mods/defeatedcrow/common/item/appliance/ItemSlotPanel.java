package mods.defeatedcrow.common.item.appliance;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSlotPanel extends Item {

	public ItemSlotPanel() {
		super();
		this.setMaxStackSize(1);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:slotpanel");
	}

}
