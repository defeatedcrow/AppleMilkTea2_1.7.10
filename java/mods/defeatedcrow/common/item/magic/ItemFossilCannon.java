package mods.defeatedcrow.common.item.magic;

import java.util.List;

import mods.defeatedcrow.api.energy.BatteryItemBase;
import mods.defeatedcrow.api.energy.IBattery;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.entity.EntityAnchorMissile;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFossilCannon extends ItemBow implements IBattery {

	public ItemFossilCannon() {
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(16);
		this.setNoRepair();
	}

	// IBatteryのメソッド
	@Override
	public int getMaxAmount(ItemStack item) {
		return 12800;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:purple_scale");
	}

	// 文字色
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	/*
	 * 右クリック使用をやめた時に呼ばれるメソッド。右クリックを継続して押していた時間をもとに、エンティティを発射する処理を行う。
	 */
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {
		boolean ff = par3EntityPlayer.capabilities.isCreativeMode
				|| EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;
		boolean flag2 = false;
		if (par1ItemStack.getItem() == this) {
			int c2 = this.discharge(par1ItemStack, 400, false);
			if (ff || c2 == 400)
				flag2 = true;
		}

		if (flag2) {
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

			int power = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);
			int punch = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);
			int fire = EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack);

			boolean flag = false;

			if (target != null) {
				EntityAnchorMissile bullet = new EntityAnchorMissile(par2World, par3EntityPlayer, target, 1.0F, 1.0F,
						par3EntityPlayer.rotationYaw, 0.0F, 0.0F, 0.0F);

				if (power > 0) {
					bullet.setDamage(50.0D + power * 5.0D);
				}

				if (punch > 0) {
					bullet.setKnockbackStrength(1 + punch);
				}

				if (fire > 0) {
					bullet.setFire(100);
				}

				((IBattery) par1ItemStack.getItem()).discharge(par1ItemStack, 400, true);
				par2World.playSoundAtEntity(par3EntityPlayer, "random.pop", 1.0F, 1.0F
						/ (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
				if (!par2World.isRemote) {
					flag = par2World.spawnEntityInWorld(bullet);
				}
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

	// IBattery
	@Override
	public int getChargeAmount(ItemStack item) {
		NBTTagCompound nbt = item.getTagCompound();
		if (nbt != null && nbt.hasKey("charge")) {
			int charge = nbt.getInteger("charge");
			return charge;
		}
		return 0;
	}

	@Override
	public boolean isFullCharged(ItemStack item) {
		NBTTagCompound nbt = item.getTagCompound();
		if (nbt != null && nbt.hasKey("charge")) {
			int charge = nbt.getInteger("charge");
			return charge >= this.getMaxAmount(item);
		}
		return false;
	}

	@Override
	public int charge(ItemStack item, int amount, boolean flag) {

		if (item == null)
			return 0;

		NBTTagCompound nbt = item.getTagCompound();
		int charge = 0;
		int increase = 0;
		if (nbt != null && nbt.hasKey("charge")) {
			charge = nbt.getInteger("charge");
		}

		int i = this.getMaxAmount(item) - charge;
		Math.min(i, 0);

		increase = Math.min(amount, i);

		if (flag) {
			if (nbt != null) {
				nbt.setInteger("charge", (charge + increase));
				item.setTagCompound(nbt);
			} else {
				NBTTagCompound nbt2 = new NBTTagCompound();
				nbt2.setInteger("charge", (charge + increase));
				item.setTagCompound(nbt2);
			}
		}

		return increase;
	}

	@Override
	public int discharge(ItemStack item, int amount, boolean flag) {

		if (item == null)
			return 0;

		NBTTagCompound nbt = item.getTagCompound();
		int charge = 0;
		int reduce = 0;
		if (nbt != null && nbt.hasKey("charge")) {
			charge = nbt.getInteger("charge");
		}

		reduce = Math.min(amount, charge);

		if (flag) {
			if (nbt != null) {
				nbt.setInteger("charge", (charge - reduce));
				item.setTagCompound(nbt);
			} else {
				NBTTagCompound nbt2 = new NBTTagCompound();
				nbt2.setInteger("charge", (charge - reduce));
				item.setTagCompound(nbt2);
			}
		}

		return reduce;
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		NBTTagCompound nbt = par1ItemStack.getTagCompound();
		int charge = 0;
		int max = this.getMaxAmount(par1ItemStack);
		if (nbt != null && nbt.hasKey("charge")) {
			charge = nbt.getInteger("charge");
		}

		String s = new String("charge amount : " + charge + "/" + max);
		par3List.add(s);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		int charge = 0;
		int max = this.getMaxAmount(stack);
		if (nbt != null && nbt.hasKey("charge")) {
			charge = nbt.getInteger("charge");
			charge = MathHelper.clamp_int(charge, 0, max);
		}

		int i = max - charge;
		return (double) i / (double) max;
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	// @SideOnly(Side.CLIENT)
	// public boolean shouldRotateAroundWhenRendering()
	// {
	// return true;
	// }

}
