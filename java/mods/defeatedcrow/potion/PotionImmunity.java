package mods.defeatedcrow.potion;


import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.common.*;
import mods.defeatedcrow.api.potion.PotionImmunityBase;
import mods.defeatedcrow.common.config.DCsConfig;
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
		List<Integer> check = new ArrayList<Integer>();
		boolean flag = false;
		
		if (id == DCsConfig.potionIDImmunity && !player.worldObj.isRemote)
		{
			if (amp == 0)
			{
				check.add(Potion.hunger.id);
			}
			
			if (amp > 0)
			{
				check.add(Potion.poison.id);
				check.add(Potion.wither.id);
			}
			
			if (amp > 1)
			{
				check.add(Potion.confusion.id);
				check.add(Potion.blindness.id);
				if (DCsAppleMilk.suffocation != null)
				{
					check.add(DCsAppleMilk.suffocation.id);
				}
			}
			
			if (amp > 2)
			{
				check.add(Potion.digSlowdown.id);
				check.add(Potion.moveSlowdown.id);
				check.add(Potion.weakness.id);
			}
			
			for (int i = 0 ; i < check.size() ; i++)
			{
				if (player.isPotionActive(check.get(i)))
				{
					player.removePotionEffect(check.get(i));
					flag = true;
				}
			}
		}
		
		return flag;
	}

}
