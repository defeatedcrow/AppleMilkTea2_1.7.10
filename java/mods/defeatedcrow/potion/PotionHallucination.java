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
	 * */
	@Override
	public boolean preventPotion(int amp, int id, EntityPlayer player)
	{
		boolean flag = false;
		Random rand = player.worldObj.rand;
		
		if (id == DCsConfig.potionIDHallucinations && !player.worldObj.isRemote)
		{
			double x = player.posX + rand.nextInt(7) - 3.0D;
			double y = player.posY;
			double z = player.posZ + rand.nextInt(7) - 3.0D;
			int chance = rand.nextInt(200);
			float f = rand.nextFloat();
			
			if (amp >= 0)
			{
				switch(chance)
				{
				case 0:
					player.worldObj.playSoundEffect(x, y, z, "mob.zombie.say", 1.0F, 0.5F + f);
					flag = true;
					break;
				case 1:
					player.worldObj.playSoundEffect(x, y, z, "mob.creeper.say", 1.0F, 0.5F + f);
					flag = true;
					break;
				case 2:
					player.worldObj.playSoundEffect(x, y, z, "mob.endermen.stare", 1.0F, 0.5F + f);
					flag = true;
					break;
				case 3:
					player.worldObj.playSoundEffect(x, y, z, "creeper.primed", 1.0F, 0.5F + f);
					flag = true;
					break;
				case 4:
					player.worldObj.playSoundEffect(x, y, z, "mob.skeleton.say", 1.0F, 0.5F + f);
					flag = true;
					break;
				case 5:
					player.worldObj.playSoundEffect(x, y, z, "mob.silverfish.say", 1.0F, 0.9F + f);
					flag = true;
					break;
				case 6:
					player.worldObj.playSoundEffect(x, y, z, "mob.spider.say", 1.0F, 0.5F + f);
					flag = true;
					break;
				case 7:
					player.worldObj.playSoundEffect(x, y, z, "mob.endermen.idle", 1.0F, 0.5F + f);
					flag = true;
					break;
				case 8:
					player.worldObj.playSoundEffect(x, y, z, "mob.blaze.breathe", 1.0F, 0.5F + f);
					flag = true;
					break;
				case 9:
					player.worldObj.playSoundEffect(x, y, z, "mob.ghast.moan", 1.0F, 0.5F + f);
					flag = true;
					break;
				default:
					break;
				}
			}
		}
		
		return flag;
	}

}
