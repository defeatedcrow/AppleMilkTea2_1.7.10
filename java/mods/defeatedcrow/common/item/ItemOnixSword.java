package mods.defeatedcrow.common.item;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemOnixSword extends ItemSword{
	
	private float damage;
	
	public ItemOnixSword (){
		super (DCsAppleMilk.enumToolMaterialChalcedony);

		this.setMaxStackSize(1);
		this.setMaxDamage(192);
		this.damage = 7.0F;
	}
	
	@Override
	public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.damage, 0));
        return multimap;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:onixsword");
	}
	
	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	return par1ItemStack;
    }

	@Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }

	@Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }

	@Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {

    	par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }

	@Override
    public int getItemEnchantability()
    {
        return 1;
    }
    
	@Override
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
    	int j = this.getMaxItemUseDuration(par1ItemStack) - par4;

        boolean flag = par3EntityPlayer.capabilities.isCreativeMode || 
        		EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        if (!flag)
        {
        	par1ItemStack.damageItem(1, par3EntityPlayer);
        }

        float f = (float)j / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;

        if ((double)f < 0.5D)
        {
            return;
        }

        if (f > 1.0F)
        {
            f = 1.0F;
        }

        PotionEffect add;
        if (DCsAppleMilk.reflex != null)
        {
        	add = new PotionEffect(DCsAppleMilk.reflex.id, 30, 3);
        }
        else
        {
        	add = new PotionEffect(Potion.resistance.id, 30, 4);
        }
        
        par3EntityPlayer.addPotionEffect(add);
        par2World.playSoundAtEntity(par3EntityPlayer, "defeatedcrow:suzu", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
    }

}
