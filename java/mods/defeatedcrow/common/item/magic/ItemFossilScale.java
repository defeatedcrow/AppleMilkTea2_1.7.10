package mods.defeatedcrow.common.item.magic;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.entity.EntityAnchorMissile;
import mods.defeatedcrow.common.entity.dummy.EntityIllusionMobs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemFossilScale extends Item {

	public ItemFossilScale() {
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(16);
		this.setNoRepair();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:purple_scale");
	}

	// 文字色
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.uncommon;
	}

	/*
	 * 右クリック使用をやめた時に呼ばれるメソッド。右クリックを継続して押していた時間をもとに、エンティティを発射する処理を行う。
	 */
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {
		float yaw = par3EntityPlayer.rotationYaw;
		float pitch = par3EntityPlayer.rotationPitch;
		double dx = -(double) (MathHelper.sin(yaw / 180.0F * (float) Math.PI)) * 30.0D;
		double dz = (double) (MathHelper.cos(yaw / 180.0F * (float) Math.PI)) * 30.0D;
		double dy = -(double) (MathHelper.sin(pitch / 180.0F * (float) Math.PI)) * 30.0D;

		double minX = par3EntityPlayer.posX + Math.min(-1, dx);
		double minY = par3EntityPlayer.posY + Math.min(-1, dy);
		double minZ = par3EntityPlayer.posZ + Math.min(-1, dz);
		double maxX = par3EntityPlayer.posX + Math.max(1, dx);
		double maxY = par3EntityPlayer.posY + Math.max(1, dy);
		double maxZ = par3EntityPlayer.posZ + Math.max(1, dz);

		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
		List list = par2World.getEntitiesWithinAABB(EntityLivingBase.class, aabb);
		EntityLivingBase target = null;

		AMTLogger.debugInfo("yaw & pitch : " + yaw + "," + pitch);
		AMTLogger.debugInfo("aabb : " + minX + "," + minY + "," + minZ + "," + maxX + "," + maxY + "," + maxZ);

		if (list != null && !list.isEmpty()) {
			for (int k = 0; k < list.size(); k++) {
				EntityLivingBase entity = (EntityLivingBase) list.get(k);
				if (entity.canEntityBeSeen(par3EntityPlayer) && !(entity instanceof EntityPlayer)
						&& !(entity instanceof EntityTameable) && !(entity instanceof EntityHorse)
						&& !(entity instanceof EntityVillager)) {
					target = entity;
					AMTLogger.debugInfo("target : " + target.toString());
					break;
				}
			}
		}

		// par4は右クリックの押下時間。
		int j = this.getMaxItemUseDuration(par1ItemStack) - par4;

		// 右クリック押下時間をもとに計算。20で割り（単位を秒に変換）、なにやら二次関数的な計算式に入れている。
		// ここではバニラ弓のまま使っているが、独自の計算式でも良いと思います。
		float f = (float) j / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		// タメ時間が一定以下の場合、何も起こさず処理から抜ける。
		if ((double) f < 0.1D) {
			return;
		}
		// fの上限値。
		if (f > 1.0F) {
			f = 1.0F;
		}

		boolean flag = false;

		if (target != null) {
			EntityAnchorMissile bullet = new EntityAnchorMissile(par2World, par3EntityPlayer, target, 1.0F, 1.0F,
					par3EntityPlayer.rotationYaw, 0.0F, 0.0F, 0.0F);

			// EntityIllusionMobs bullet = new EntityIllusionMobs(par2World,
			// par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, yaw);

			par1ItemStack.damageItem(1, par3EntityPlayer);
			par2World.playSoundAtEntity(par3EntityPlayer, "random.pop", 1.0F, 1.0F
					/ (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
			if (!par2World.isRemote) {
				flag = par2World.spawnEntityInWorld(bullet);
			}

		}
	}

	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		return par1ItemStack;
	}

	/*
	 * 右クリックでの使用（タメ）時間の上限。
	 */
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}

	/*
	 * 右クリック時の動作のタイプ。
	 * ここではbow（引き絞るタメ動作）にしているが、ガードや飲食などに変えることも出来、呼ばれるメソッドが異なる。
	 */
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	/*
	 * 右クリックでの使用時に呼ばれるメソッド。
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	@SideOnly(Side.CLIENT)
	/*
	 * 右クリックでタメている時のアイコンを変えられる。今回は特に変えていない。
	 */
	public IIcon getItemIconForUseDuration(int par1) {
		return this.itemIcon;
	}

}
