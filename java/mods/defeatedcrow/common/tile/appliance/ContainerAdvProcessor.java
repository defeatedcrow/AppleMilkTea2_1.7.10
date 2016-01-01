package mods.defeatedcrow.common.tile.appliance;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerAdvProcessor extends Container {

	protected TileAdvProcessor tileentity;
	protected TileAdvProcessor inventory;

	private int lastCookTime;
	private int lastBurnTime;

	public ContainerAdvProcessor(EntityPlayer player, TileAdvProcessor par2TileEntity) {
		this.tileentity = par2TileEntity;
		this.inventory = par2TileEntity;

		// 燃料
		this.addSlotToContainer(new Slot(this.inventory, 0, 9, 9));
		// 材料
		int j;
		for (j = 0; j < 3; ++j) {
			for (int k = 0; k < 3; ++k) {
				this.addSlotToContainer(new Slot(this.inventory, 2 + k + j * 3, 33 + k * 18, 16 + j * 18));
			}
		}

		// 完成品
		this.addSlotToContainer(new SlotFurnace(player, this.inventory, 1, 9, 55));
		this.addSlotToContainer(new SlotFurnace(player, this.inventory, 11, 118, 35 + adj()));
		this.addSlotToContainer(new SlotFurnace(player, this.inventory, 12, 145, 35 + adj()));

		// 歯板
		this.addSlotToContainer(new Slot(this.inventory, 13, 91, 16));

		int i;

		// 1 ～ 3段目のインベントリ
		for (i = 0; i < 3; ++i) {
			for (int h = 0; h < 9; ++h) {
				this.addSlotToContainer(new Slot(player.inventory, h + i * 9 + 9, 8 + h * 18, 84 + i * 18));
			}
		}

		// 4段目のインベントリ
		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
		}
	}

	protected int adj() {
		return 8;
	}

	@Override
	public void addCraftingToCrafters(ICrafting par1ICrafting) {
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, this.tileentity.cookTime);
		par1ICrafting.sendProgressBarUpdate(this, 1, this.tileentity.getChargeAmount());
	}

	// 更新を送る
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);

			if (this.lastCookTime != this.tileentity.cookTime) {
				icrafting.sendProgressBarUpdate(this, 0, this.tileentity.cookTime);
			}

			if (this.lastBurnTime != this.tileentity.getChargeAmount()) {
				icrafting.sendProgressBarUpdate(this, 1, this.tileentity.getChargeAmount());
			}
		}

		this.lastCookTime = this.tileentity.cookTime;
		this.lastBurnTime = this.tileentity.getChargeAmount();
	}

	// 更新する
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			this.tileentity.cookTime = par2;
		}

		if (par1 == 1) {
			this.tileentity.setChargeAmount(par2);
		}
	}

	// InventorySample内のisUseableByPlayerメソッドを参照
	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return this.inventory.isUseableByPlayer(par1EntityPlayer);
	}

	// Shiftクリック
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);
		int lim = tileentity.getSizeInventory();

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			// カーソルを排出スロットにあわせているとき
			if (par2 == 1 || par2 == 11 || par2 == 12 || par2 == lim - 1) {
				// アイテムの移動
				if (!this.mergeItemStack(itemstack1, lim, 35 + lim, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			// カーソルをプレイヤーのインベントリにあわせている
			else if (par2 >= lim) {
				// 燃料である
				if (TileProcessor.isItemFuel(itemstack)) {
					// アイテムの移動(スロット0～1へ)
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
				} else// それ以外のアイテムはすべて材料欄に飛ばす
				{
					// アイテムの移動(スロット2～10へ)
					if (!this.mergeItemStack(itemstack1, 1, 10, false)) {
						return null;
					}
				}
			}
			// アイテムの移動(スロット3～39へ)
			else if (!this.mergeItemStack(itemstack1, lim, 35 + lim, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}

}
