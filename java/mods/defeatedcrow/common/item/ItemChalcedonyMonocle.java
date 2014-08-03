package mods.defeatedcrow.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemChalcedonyMonocle extends ItemArmor{

	public ItemChalcedonyMonocle(ArmorMaterial material, int index,
			int slot) {
		super(material, index, slot);
	}
	
	@Override
	public String getArmorTexture(ItemStack item, Entity entity, int slot, String type)
	  {
	    if (item.getItem() == DCsAppleMilk.monocle) {
	      return "defeatedcrow:textures/armor/monocle.png";
	    }
	    return null;
	  }

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister){
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:monocle");
	}
}
