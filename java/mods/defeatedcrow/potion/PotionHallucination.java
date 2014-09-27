package mods.defeatedcrow.potion;

import java.util.Random;

import mods.defeatedcrow.common.*;
import mods.defeatedcrow.api.potion.PotionImmunityBase;
import mods.defeatedcrow.common.DCsConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;

/**
 * 幻覚ポーション。
 * Tick毎に呼び出される。
 * */
public class PotionHallucination extends PotionImmunityBase
{
	
	public PotionHallucination(int par1, boolean par2, int par3)
    {
        super(par1, par2, par3);
    }
	
	/**
	 * PlayerのonUpdateEventで呼ばれるメソッド。
	 * Amplifierごとに効果が悪化する。
	 * リモートワールドにしか発生しないので、罹患者にしか感じられない。
	 * */
	@Override
	public boolean preventPotion(int amp, int id, EntityPlayer player)
	{
		boolean flag = false;
		Random rand = player.worldObj.rand;
		
		if (id == DCsConfig.potionIDHallucinations && player.worldObj.isRemote)
		{
			double x = player.prevPosX + rand.nextInt(10) - 5.0D;
			double y = player.prevPosY;
			double z = player.prevPosZ + rand.nextInt(10) - 5.0D;
			int chance = rand.nextInt(20);
			float f = rand.nextFloat();
			
			if (amp == 0)
			{
				if (chance == 0)
				{
					player.worldObj.playSoundEffect(x, y, z, "mob.zombie.say", 1.0F, 0.9F + f);
				}
				else if (chance == 1)
				{
					player.worldObj.playSoundEffect(x, y, z, "mob.creeper.say", 1.0F, 0.9F + f);
				}
				else if (chance == 2)
				{
					player.worldObj.playSoundEffect(x, y, z, "mob.endermen.stare", 1.0F, 0.9F + f);
				}
			}
		}
		
		return flag;
	}

}
