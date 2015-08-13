package mods.defeatedcrow.common.item.magic;

import java.util.List;

import mods.defeatedcrow.api.energy.IBattery;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.TileBrewingBarrel;
import mods.defeatedcrow.common.tile.appliance.MachineBase;
import mods.defeatedcrow.common.tile.energy.TileChargerDevice;
import mods.defeatedcrow.plugin.cofh.RFDeviceHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.ModAPIManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDebugArm extends Item implements IBattery {

	public ItemDebugArm() {
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setNoRepair();

		if (DCsAppleMilk.debugMode) {
			this.setCreativeTab(DCsAppleMilk.applemilkMagic);
		}
	}

	// 右クリック効果
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
			int par5, int par6, int par7, float par8, float par9, float par10) {
		Block block = par3World.getBlock(par4, par5, par6);
		TileEntity tile = par3World.getTileEntity(par4, par5, par6);
		int meta = par3World.getBlockMetadata(par4, par5, par6);

		if (par2EntityPlayer == null)
			return false;
		boolean se = false;

		// charge
		if (tile instanceof MachineBase) {
			MachineBase machine = (MachineBase) tile;
			if (!machine.isFullCharged()) {
				int max = machine.getMaxChargeAmount();
				int ret = machine.getChargeAmount() + 800;
				ret = MathHelper.clamp_int(ret, 0, max);
				machine.setChargeAmount(ret);
				se = true;
			}
		} else if (tile instanceof TileChargerDevice) {
			TileChargerDevice device = (TileChargerDevice) tile;
			if (!device.isFullCharged()) {
				int max = device.getMaxChargeAmount();
				int ret = device.getChargeAmount() + 800;
				ret = MathHelper.clamp_int(ret, 0, max);
				device.setChargeAmount(ret);
				se = true;
			}
		}
		// RF
		else if (ModAPIManager.INSTANCE.hasAPI("CoFHAPI|energy") && RFDeviceHandler.isRFDevice(tile)) {
			se = RFDeviceHandler.inputEnergy(ForgeDirection.UP, tile, 1000, false) > 0;
		}

		// Barrel
		if (tile instanceof TileBrewingBarrel) {
			TileBrewingBarrel barrel = (TileBrewingBarrel) tile;
			if (!barrel.getAged() && !barrel.productTank.isEmpty()) {
				barrel.setAgingStage(4);
				se = true;
			}
		}

		// 骨粉
		if (ItemDye.applyBonemeal(par1ItemStack, par3World, par4, par5, par6, par2EntityPlayer)) {
			if (!par3World.isRemote) {
				par3World.playAuxSFX(2005, par4, par5, par6, 0);
				se = true;
			}
		}

		// どれでもなかった場合
		if (!se) {
			if (block.canPlaceTorchOnTop(par3World, par4, par5, par6) && par3World.isAirBlock(par4, par5 + 1, par6)) {
				if (!par3World.isRemote)
					par3World.setBlock(par4, par5 + 1, par6, Blocks.torch);
				se = true;
			}
		}

		if (se) {
			par3World.playSoundAtEntity(par2EntityPlayer, "random.pop", 0.4F, 1.8F);
		}
		return true;
	}

	// 対Entity
	@Override
	public boolean itemInteractionForEntity(ItemStack item, EntityPlayer player, EntityLivingBase target) {
		return false;
	}

	// 対Entity
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		return false;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.none;
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.rare;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:purple_scale");
	}

	// IBattery
	@Override
	public int getMaxAmount(ItemStack item) {
		return 51200;
	}

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
