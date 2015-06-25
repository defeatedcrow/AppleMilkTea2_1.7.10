package mods.defeatedcrow.potion;

import java.util.Random;

import mods.defeatedcrow.api.potion.PotionImmunityBase;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.entity.dummy.EntityIllusionMobs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * 幻覚ポーション。
 * Tick毎に呼び出される。
 */
public class PotionHallucination extends PotionImmunityBase {

	private final String[] overworldMobs = new String[] { "mob.zombie.say", "mob.creeper.say", "mob.endermen.stare",
			"creeper.primed", "mob.skeleton.say", "mob.spider.say", "mob.endermen.idle" };
	private final String[] netherMobs = new String[] { "mob.blaze.breathe", "mob.ghast.moan" };

	public PotionHallucination(int par1, boolean par2, int par3, int x, int y) {
		super(par1, par2, par3, x, y);
	}

	/**
	 * PlayerのonUpdateEventで呼ばれるメソッド。
	 * Amplifierごとに効果が悪化する予定。今はレベル1の効果しか作っていない。
	 */
	@Override
	public boolean preventPotion(int amp, int id, EntityPlayer player) {
		boolean flag = false;
		Random rand = player.worldObj.rand;

		if (id == DCsConfig.potionIDHallucinations && !player.worldObj.isRemote) {
			double x = player.posX + rand.nextInt(7) - 3.0D;
			double y = player.posY;
			double z = player.posZ + rand.nextInt(7) - 3.0D;
			int chance = rand.nextInt(200);
			float f = rand.nextFloat();
			float yaw = player.rotationYaw + 180.0F;
			if (yaw > 180.0F)
				yaw -= 360.0F;

			int dim = player.worldObj.provider.dimensionId;
			String voice = "mob.zombie.say";
			if (dim == -1) {
				int i = player.worldObj.rand.nextInt(netherMobs.length);
				voice = netherMobs[i];
			} else {
				int i = player.worldObj.rand.nextInt(overworldMobs.length);
				voice = overworldMobs[i];
			}

			if (amp >= 0) {
				if (chance <= amp) {
					player.worldObj.playSoundEffect(x, y, z, voice, 1.0F, 0.8F + f);
					flag = true;
				}
			}

			if (amp > 0) {
				if (chance < amp) {
					int ix = MathHelper.floor_double(x);
					int iy = MathHelper.floor_double(y);
					int iz = MathHelper.floor_double(z);
					boolean a = player.worldObj.isSideSolid(ix, iy - 1, iz, ForgeDirection.UP);
					boolean b = player.worldObj.isAirBlock(ix, iy, iz);
					boolean c = player.worldObj.isAirBlock(ix, iy + 1, iz);

					if (a && b && c) {
						EntityIllusionMobs illusion = new EntityIllusionMobs(player.worldObj, x, y, z, yaw);
						player.worldObj.spawnEntityInWorld(illusion);
						player.worldObj.playSoundEffect(x, y, z, "mob.creeper.say", 1.0F, 0.8F + f);
						flag = true;
					}

				}
			}
		}

		return flag;
	}

}
