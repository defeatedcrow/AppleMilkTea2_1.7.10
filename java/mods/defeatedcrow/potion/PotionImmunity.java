package mods.defeatedcrow.potion;


import mods.defeatedcrow.common.*;
import mods.defeatedcrow.api.potion.PotionImmunityBase;
import mods.defeatedcrow.common.DCsConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;

/**
 * Immunityポーションのクラス。
 * Tick毎に呼び出される。
 * */
public class PotionImmunity extends PotionImmunityBase
{
	
	public PotionImmunity(int par1, boolean par2, int par3)
    {
        super(par1, par2, par3);
    }
	
	/**
	 * PlayerのonUpdateEventで呼ばれるメソッド。
	 * Amplifierごとに除去可能なポーション効果をチェックしている。
	 * 改良が必要。
	 * */
	@Override
	public boolean preventPotion(int amp, int id, EntityPlayer player)
	{
		int[] check;
		boolean flag = false;
		
		if (id == DCsConfig.potionIDImmunity && !player.worldObj.isRemote)
		{
			if (amp == 0)
			{
				check = new int[] {Potion.hunger.id};
			}
			else if (amp == 1)
			{
				check = new int[] {Potion.poison.id, Potion.wither.id};
			}
			else
			{
				check = new int[] {Potion.poison.id, Potion.wither.id, Potion.confusion.id, Potion.blindness.id};
			}
			
			for (int i = 0 ; i < check.length ; i++)
			{
				if (player.isPotionActive(check[i]))
				{
					player.removePotionEffect(check[i]);
					flag = true;
				}
			}
		}
		
		return flag;
	}

}
