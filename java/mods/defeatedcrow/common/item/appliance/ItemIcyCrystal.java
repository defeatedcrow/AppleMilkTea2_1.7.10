package mods.defeatedcrow.common.item.appliance;

import java.util.List;

import net.minecraft.src.*;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import mods.defeatedcrow.*;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemIcyCrystal extends Item {
	
	@SideOnly(Side.CLIENT)
    private IIcon iconType[];
	private static final String[] itemType = new String[] {"_blink", "_orb", "_cloud"};
	
	public ItemIcyCrystal (){
		super ();
		this.setMaxStackSize(64);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
    {
        int j = MathHelper.clamp_int(par1, 0, 3);
        return j > 0 ? this.iconType[j - 1] : this.itemIcon;
    }
	
	@Override
	public int getMetadata(int par1) {
		return par1;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName() + "_" + par1ItemStack.getItemDamage();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(this, 1, 0));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister){
		
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:icycrystal");
		this.iconType = new IIcon[3];
		for (int i = 0; i < 3; ++i)
        {
            this.iconType[i] = par1IconRegister.registerIcon("defeatedcrow:particle" + itemType[i]);
        }
		
	}

}
