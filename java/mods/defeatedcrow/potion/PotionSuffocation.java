package mods.defeatedcrow.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;

public class PotionSuffocation extends Potion{
	
	public PotionSuffocation(int par1, boolean par2, int par3)
    {
        super(par1, par2, par3);
    }
	
	@Override
	public void performEffect(EntityLivingBase par1EntityLivingBase, int par2)
    {
		if (par2 > 0 && par1EntityLivingBase.getHealth() > 1.0F)
        {
			float damage = par2 / 2;
            par1EntityLivingBase.attackEntityFrom(DamageSource.inWall, damage);
        }
    }
	
	@Override
	public boolean isReady(int par1, int par2)
    {
		int k = 25 >> par2;
        return k > 0 ? par1 % k == 0 : true;
    }

}
