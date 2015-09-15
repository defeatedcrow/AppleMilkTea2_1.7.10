package mods.defeatedcrow.common.item.appliance;

import java.util.List;

import mods.defeatedcrow.api.energy.IBattery;
import mods.defeatedcrow.api.events.ShootingGunEvent;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.entity.EntityYuzuBullet;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemYuzuGatling extends ItemBow implements IBattery {

	public ItemYuzuGatling() {
		super();
		this.setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:yuzu");
	}

	// 文字色
	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	// IBatteryのメソッド
	@Override
	public int getMaxAmount(ItemStack item) {
		return 6400;
	}

	// 右クリ使用時
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 16;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		boolean creative = par3EntityPlayer.capabilities.isCreativeMode;

		boolean hasYuzu = false;
		boolean inf = EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;
		int power = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);
		int fire = EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack);

		boolean looseCheck = creative || inf;
		boolean chargeCheck = creative;

		EntityYuzuBullet bullet = new EntityYuzuBullet(par2World, par3EntityPlayer, 3.0F, 0.0F, 0.0F, 0.0F, 0.0F);

		float dam = (fire > 0) ? 5.0F : 3.0F;
		dam = dam + power * 0.5F;

		int yuzu = 1;
		int charge = 2;

		ShootingGunEvent event = new ShootingGunEvent(par2World, par3EntityPlayer, par1ItemStack, dam, 1, 2);
		MinecraftForge.EVENT_BUS.post(event);

		if (event.isCanceled()) {
			return par1ItemStack;
		}

		if (event.hasResult() && event.getResult() == Result.ALLOW) {
			dam = event.damage;
			yuzu = event.looseBullet;
			charge = event.looseCharge;
			looseCheck = yuzu < 1;
			chargeCheck = charge < 0;
		}

		bullet.setDamage(dam);

		if (fire > 0) {
			bullet.setFire(100);
		}

		if (!looseCheck && this.looseYuzu(par3EntityPlayer, DCsAppleMilk.leafTea, 3, yuzu)) {
			looseCheck = true;
		}
		if (!chargeCheck && this.discharge(par1ItemStack, charge, true) > 0) {
			chargeCheck = true;
		}

		if (dam > 0.0F && looseCheck && chargeCheck && !par2World.isRemote) {
			par2World.spawnEntityInWorld(bullet);
		}
		par2World.playSoundAtEntity(par3EntityPlayer, "random.door_close", 1.0F,
				1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 1.3F);
		return par1ItemStack;
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		return par1ItemStack;
	}

	private boolean looseYuzu(EntityPlayer player, Item item, int meta, int count) {
		if (player == null)
			return false;

		for (int i = 0; i < player.inventory.mainInventory.length; ++i) {
			if (player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].getItem() == item
					&& player.inventory.mainInventory[i].getItemDamage() == meta) {
				if (player.inventory.mainInventory[i].stackSize >= count) {
					player.inventory.mainInventory[i].stackSize -= count;
					if (player.inventory.mainInventory[i].stackSize <= 0) {
						player.inventory.mainInventory[i] = null;
					}
					return true;
				}
			}
		}

		return false;
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

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

}
