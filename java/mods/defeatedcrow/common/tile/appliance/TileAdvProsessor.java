package mods.defeatedcrow.common.tile.appliance;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

/**
 * 基本構造はフードプロセッサーと同じ。
 * 異なる点は、 <br>
 * ・鉱石系レシピを受け入れ可能で、食べ物レシピは一切受け付けない。 <br>
 * ・空きスロットを「スロットパネル」で埋めておくことが出来る。 <br>
 * ・背面、右、左それぞれホッパーで搬入可能スロットが異なる。 <br>
 * という点。岩石の処理や工業連携レシピ、ツールの還元レシピに特化している。原木の粉砕にも要求される。
 */
public class TileAdvProsessor extends TileProsessor {

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
	}

	@Override
	public Packet getDescriptionPacket() {
		return super.getDescriptionPacket();
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
	}

	/* チャージ消費量はフードプロセッサーの4倍。 */
	@Override
	public int getDecrementChargePerTick() {
		return 4;
	}

	@Override
	public int getMaxChargeAmount() {
		return 25600;
	}

	/*
	 * 食べ物レシピ以外が対応レシピ。
	 * Recipeクラス側でスロットパネル利用可能等の判定を行うので、このフラグで設定。
	 */
	@Override
	public boolean acceptFoodRecipe() {
		return false;
	}

	/*
	 * Sideによって受け入れ可能なスロットが異なる。
	 * 2,3,4 : 背面/前面
	 * 5,6,7 : 右
	 * 8,9,10 : 左
	 */
	@Override
	public int[] getAccessibleSlotsFromSide(int par1) {
		if (par1 == 2 || par1 == 3) {
			return new int[] {
					0,
					2,
					3,
					4 };
		} else if (par1 == 4) {
			return new int[] {
					0,
					5,
					6,
					7 };
		} else if (par1 == 5) {
			return new int[] {
					0,
					8,
					9,
					10 };
		} else {
			return super.getAccessibleSlotsFromSide(par1);
		}
	}

	// slotPanelの排出を禁止
	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
		return par2ItemStack.getItem() != DCsAppleMilk.slotPanel;
	}

	// 材料欄に電池が入らないように
	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
		return (par1 == 1 || par1 > 10) ? false : (par1 == 0 ? this.isItemFuel(par2ItemStack) : !this
				.isItemFuel(par2ItemStack));
	}

	@Override
	public String getInventoryName() {
		return "Hyper Jaw Crusher";
	}

}
