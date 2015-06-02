package mods.defeatedcrow.common.tile;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

/*
 * 情報の保存と、作動時間のカウントだけ行う。
 */
public class TileIncenseBase extends TileEntity {

	private ItemStack[] holdItem = new ItemStack[2];
	private boolean isActive = false;
	private int remainTick = 0;

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);

		if (par1NBTTagCompound.hasKey("HoldItem")) {
			this.setItemstack(ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("HoldItem")));
		}

		if (par1NBTTagCompound.hasKey("Ash")) {
			this.holdItem[1] = ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("Ash"));
		}

		this.remainTick = par1NBTTagCompound.getShort("RemainTick");
		this.isActive = par1NBTTagCompound.getBoolean("Active");
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setShort("RemainTick", (short) this.remainTick);
		par1NBTTagCompound.setBoolean("Active", this.isActive);

		if (this.getItemstack() != null) {
			par1NBTTagCompound.setTag("HoldItem", this.getItemstack().writeToNBT(new NBTTagCompound()));
		}

		if (this.getAsh() != null) {
			par1NBTTagCompound.setTag("Ash", this.getAsh().writeToNBT(new NBTTagCompound()));
		}
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		this.writeToNBT(nbtTagCompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTagCompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}

	public boolean hasItem() {
		boolean flag = false;
		if (holdItem[0] != null) {
			flag = true;
		}
		return flag;
	}

	public ItemStack getItemstack() {
		return this.holdItem[0];
	}

	public ItemStack getAsh() {
		return this.holdItem[1];
	}

	public void setItemstack(ItemStack par1ItemStack) {
		this.holdItem[0] = par1ItemStack;
	}

	public int getRemain() {
		return this.remainTick;
	}

	public void setRemain(int i) {
		this.remainTick = i;
	}

	public boolean getActive() {
		return this.isActive;
	}

	public void setActive() {
		this.isActive = true;
		this.remainTick = 2400;// 2分間
	}

	public void updateEntity() {
		if (this.getActive()) {
			if (this.hasItem()) {
				if (this.getRemain() > 0)// 効果継続処理
				{
					--this.remainTick;

					if (this.getRemain() == 0) {
						this.addAsh();

						if (this.holdItem[0].stackSize > 1) {// 2個以上ある場合は、効果を延長できる
							--this.holdItem[0].stackSize;
							this.setRemain(2400);
						} else {
							this.setItemstack(null);// なくなった場合の処理
						}
						this.markDirty();
					}

				}
			} else {
				this.setRemain(0);
				this.isActive = false;
				this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
			}
		}
	}

	private void addAsh() {
		ItemStack ash = this.getAsh();
		if (ash == null) {
			ash = new ItemStack(DCsAppleMilk.dustWood, 1, 2);
		} else if (ash.stackSize < 64) {
			++ash.stackSize;
		}
		this.holdItem[1] = ash;
	}

}
