package mods.defeatedcrow.common.item.magic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.entity.dummy.EntityStunEffect;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemFossilScale extends Item{
	
	public ItemFossilScale (){
		super ();
		this.setMaxStackSize(64);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister){

		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:purple_scale");
	}
	
//	@Override
//    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity)
//    {
//        if (entity.worldObj.isRemote) return true;
//        
//        if (entity instanceof EntityLiving)
//        {
//        	EntityLiving living = (EntityLiving) entity;
//        	EntityStunEffect stun = new EntityStunEffect(living.worldObj, living, player, 200);
//        	
//        	if(living.worldObj.spawnEntityInWorld(stun))
//        		living.worldObj.playSoundAtEntity(living, "random.pop", 0.4F, 1.8F);
//        	
//        	return true;
//        }
//        return true;
//    }

}
