package mods.defeatedcrow.api.energy;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;

/**
 * 充電可能アイテムのItemBlock版のベースクラス
 * 注意!このクラスを継承したItemBlockは、設置するとチャージ情報がリセットされてしまいます。
 * TileEntityなどを利用して、別途で情報保存用の処理を行う必要あり。
 */
public abstract class BatteyItemBlockBase extends ItemBlock implements IBattery {

	public BatteyItemBlockBase(Block block) {
		super(block);
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
	public abstract int getMaxAmount(ItemStack item);

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

}
