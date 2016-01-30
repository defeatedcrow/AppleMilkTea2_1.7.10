package mods.defeatedcrow.common.tile.appliance;

import mods.defeatedcrow.api.appliance.IJawPlate;
import mods.defeatedcrow.api.recipe.IProcessorRecipe;
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
public class TileAdvProcessor extends TileProcessor {

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
	 * 対応レシピ判定。
	 * Recipeクラス側でスロットパネル利用可能等の判定を行うので、このフラグで設定。
	 */
	@Override
	public boolean acceptFoodRecipe() {
		ItemStack stack = this.getStackInSlot(13);
		if (stack != null && stack.getItem() instanceof IJawPlate) {
			int tier = ((IJawPlate) stack.getItem()).getTier(stack);
			return tier == -1;
		}
		return false;
	}

	@Override
	public boolean ismatchTier(IProcessorRecipe recipe) {
		ItemStack stack = this.getStackInSlot(13);
		return recipe.matchTier(stack);
	}

	@Override
	public void onRecipeOutput() {
		ItemStack stack = this.getStackInSlot(13);
		if (stack != null && stack.getItem() instanceof IJawPlate) {
			ItemStack ret = ((IJawPlate) stack.getItem()).returnItem(stack);
			this.setInventorySlotContents(13, ret);
		}
	}

	/* === inventory === */

	// slot追加
	@Override
	public int getSizeInventory() {
		return 14;
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

	@Override
	protected int[] slotsTop() {
		return new int[] {
				0,
				2,
				3,
				4,
				5,
				6,
				7,
				8,
				9,
				10,
				13 };
	}

	@Override
	protected int[] slotsBottom() {
		return new int[] {
				1,
				11,
				12,
				13 };
	}

	// slotPanelの排出を禁止
	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		if (stack == null)
			return false;
		if (slot == 13) {
			if (stack.getItem() instanceof IJawPlate) {
				int tier = ((IJawPlate) stack.getItem()).getTier(stack);
				return tier == 0;
			}
		}
		return stack.getItem() != DCsAppleMilk.slotPanel;
	}

	@Override
	public String getInventoryName() {
		return "Hyper Jaw Crusher";
	}

}
