package mods.defeatedcrow.handler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.config.DCsConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

/**
 * AMT2用のカスタムエクスプロージョン。
 * 目的は2つあり、 <br>
 * ・シルクメロン用の着火者、ドロップアイテムを消さない爆発 <br>
 * ・アンカーミサイル用飛行特効 <br>
 * すべての場合でブロックを破壊しない。
 */
public class CustomExplosion extends Explosion {

	private final Random rand = new Random();
	private final World worldObj;
	private final EntityLivingBase igniter;
	private final Type type;
	private Map playerMap = new HashMap();

	public CustomExplosion(World world, Entity source, EntityLivingBase ign, double posX, double posY, double posZ,
			float size, Type t, boolean smoke) {
		super(world, source, posX, posY, posZ, size);
		this.worldObj = world;
		this.type = t;
		this.igniter = ign;
		this.isFlaming = false;
		this.isSmoking = smoke;
	}

	public void doExplosion() {
		float f = this.explosionSize;
		HashSet hashset = new HashSet();
		int i = 0;
		int j = 0;
		int k = 0;
		double d5 = this.explosionX;
		double d6 = this.explosionY;
		double d7 = this.explosionZ;

		// エンティティへのダメージ
		this.explosionSize *= 2.0F;
		i = MathHelper.floor_double(this.explosionX - (double) this.explosionSize - 1.0D);
		j = MathHelper.floor_double(this.explosionX + (double) this.explosionSize + 1.0D);
		k = MathHelper.floor_double(this.explosionY - (double) this.explosionSize - 1.0D);
		int i2 = MathHelper.floor_double(this.explosionY + (double) this.explosionSize + 1.0D);
		int l = MathHelper.floor_double(this.explosionZ - (double) this.explosionSize - 1.0D);
		int j2 = MathHelper.floor_double(this.explosionZ + (double) this.explosionSize + 1.0D);
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder,
				AxisAlignedBB.getBoundingBox((double) i, (double) k, (double) l, (double) j, (double) i2, (double) j2));
		Vec3 vec3 = Vec3.createVectorHelper(this.explosionX, this.explosionY, this.explosionZ);

		for (int i1 = 0; i1 < list.size(); ++i1) {
			Entity entity = (Entity) list.get(i1);
			double d4 = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ)
					/ (double) this.explosionSize;
			AMTLogger.debugInfo("d4 :" + d4);

			if (d4 <= 1.0D) {
				d5 = entity.posX - this.explosionX;
				d6 = entity.posY + (double) entity.getEyeHeight() - this.explosionY;
				d7 = entity.posZ - this.explosionZ;
				double d9 = (double) MathHelper.sqrt_double(d5 * d5 + d6 * d6 + d7 * d7);
				AMTLogger.debugInfo("d9 :" + d9);

				if (d9 != 0.0D) {
					d5 /= d9;
					d6 /= d9;
					d7 /= d9;
					double d10 = (double) this.worldObj.getBlockDensity(vec3, entity.boundingBox);
					double d11 = (1.0D - d4) * d10;
					AMTLogger.debugInfo("d11 :" + d11);

					boolean flag = true;
					float damage = (float) ((int) ((d11 * d11 + d11) / 2.0D * 8.0D * (double) this.explosionSize + 1.0D));
					AMTLogger.debugInfo("explosion prev damage :" + damage);
					damage = Math.max(damage, 3.0F);

					if (this.type == Type.Melon) {
						if (entity instanceof EntityItem || entity instanceof IProjectile || entity == this.exploder) {
							flag = false;
						} else if (entity instanceof EntityLivingBase && this.igniter != null) {
							EntityLivingBase living = (EntityLivingBase) entity;
							flag = !(living == this.igniter);
						} else if (entity instanceof EntityBoat || entity instanceof EntityMinecart) {
							flag = false;
						} else if (DCsConfig.PvPProhibitionMode && entity instanceof EntityPlayer) {
							flag = false;
						}
					} else if (this.type == Type.Anchor) {
						if (entity instanceof EntityLivingBase && this.igniter != null) {
							EntityLivingBase living = (EntityLivingBase) entity;
							flag = !(living == this.igniter);
						} else if (entity instanceof EntityBoat || entity instanceof EntityMinecart) {
							flag = false;
						} else if (DCsConfig.PvPProhibitionMode && entity instanceof EntityPlayer) {
							flag = false;
						} else if (!entity.onGround) {
							damage *= 10.0F;
						}
					} else {
						if (entity instanceof EntityLivingBase && this.igniter != null) {
							EntityLivingBase living = (EntityLivingBase) entity;
							flag = !(living == this.igniter);
						}
					}

					if (flag) {
						AMTLogger.debugInfo("explosion type :" + this.type);
						AMTLogger.debugInfo("explosion deal damage :" + damage);
						if (entity instanceof IProjectile) {
							entity.setDead();
						} else {
							entity.attackEntityFrom(DamageSource.setExplosionSource(this), damage);
						}
						double d8 = EnchantmentProtection.func_92092_a(entity, d11);
						entity.motionX += d5 * d8;
						entity.motionY += d6 * d8;
						entity.motionZ += d7 * d8;

					}
				}
			}
		}

		this.explosionSize = f;
		this.worldObj.playSoundEffect(this.explosionX, this.explosionY, this.explosionZ, "random.explode", 4.0F,
				(1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
	}

	public static enum Type {
		Melon, Anchor, Normal;
	}

}
