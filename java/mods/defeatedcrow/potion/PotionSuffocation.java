package mods.defeatedcrow.potion;

import mods.defeatedcrow.api.potion.PotionBaseAMT;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class PotionSuffocation extends PotionBaseAMT {

	public PotionSuffocation(int par1, boolean par2, int par3, int x, int y) {
		super(par1, par2, par3, x, y);
	}

	@Override
	public void performEffect(EntityLivingBase par1EntityLivingBase, int par2) {
		if (par2 > 0 && par1EntityLivingBase.getHealth() > 1.0F) {
			float damage = par2;
			par1EntityLivingBase.attackEntityFrom(DamageSource.inWall, damage);
		}
	}

	@Override
	public boolean isReady(int par1, int par2) {
		int k = 25 >> par2;
		return k > 0 ? par1 % k == 0 : true;
	}

}
