package mods.defeatedcrow.common.item.edible;

import java.util.ArrayList;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.plugin.LoadAppleCorePlugin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import squeek.applecore.api.food.FoodValues;
import squeek.applecore.api.food.IEdible;

/*クラッシュ回避用の中継クラス*/
@Optional.Interface(iface = "squeek.applecore.api.food.IEdible", modid = "AppleCore")
public class EdibleEntityItem2 extends EdibleEntityItem implements IEdible{
	
	public EdibleEntityItem2(boolean chopsticks, boolean tip) {
		super(chopsticks, tip);
	}

	/**
	 * AppleCore連携用、IEdibleの実装メソッド。
	 * */
	@Override
	@Optional.Method(modid = "AppleCore")
    public FoodValues getFoodValues(ItemStack itemStack)
    {
		int[] h = this.hungerOnEaten(itemStack.getItemDamage());
        return new FoodValues(h[0], h[1]*0.1F);
    }
	
	/**
	 * 食べる動作
	 */
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		int meta = par1ItemStack.getItemDamage();
		
		if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            --par1ItemStack.stackSize;
        }
		this.returnItemStack(par3EntityPlayer, meta);
		
		if (!par2World.isRemote)
		{
			if (this.effectOnEaten(par3EntityPlayer, meta) != null)
			{
				ArrayList<PotionEffect> potion = this.effectOnEaten(par3EntityPlayer, meta);
				if (potion != null && !potion.isEmpty())
				{
					for (PotionEffect ret : potion)
					{
						par3EntityPlayer.addPotionEffect(ret);
					}
				}
			}
			
			if (this.hungerOnEaten(meta) != null)
			{
				int[] h = this.hungerOnEaten(meta);
				if (DCsAppleMilk.SuccessLoadACore)
		        {
		            LoadAppleCorePlugin.addFoodStatus(par3EntityPlayer, par1ItemStack);
		        }
				else
				{
					par3EntityPlayer.getFoodStats().addStats(h[0], h[1]*0.1F);
				}
			}
		}

        return par1ItemStack;
    }
}
