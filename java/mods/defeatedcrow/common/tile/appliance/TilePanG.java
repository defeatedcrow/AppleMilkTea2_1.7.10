package mods.defeatedcrow.common.tile.appliance;

import mods.defeatedcrow.api.recipe.IPanRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TilePanG extends TileEntity {
	private byte remain = 1;
	private boolean direction = false;

	private ItemStack input = null;
	private String tex = "defeatedcrow:textures/blocks/contents_rice.png";

	private byte coolTime = 0;

	// NBT
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);

		if (par1NBTTagCompound.hasKey("Input")) {
			this.setItemStack(ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("Input")));
		}

		this.remain = par1NBTTagCompound.getByte("Remaining");
		this.direction = par1NBTTagCompound.getBoolean("Direction");
		this.tex = par1NBTTagCompound.getString("Tex");
		this.coolTime = par1NBTTagCompound.getByte("CoolTime");
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setByte("Remaining", this.remain);
		par1NBTTagCompound.setBoolean("Direction", this.direction);
		par1NBTTagCompound.setString("Tex", tex);
		par1NBTTagCompound.setByte("CoolTime", this.coolTime);

		if (this.getItemStack() != null) {
			par1NBTTagCompound.setTag("Input", this.getItemStack().writeToNBT(new NBTTagCompound()));
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

	public byte getRemainByte() {
		return this.remain;
	}

	public void setRemainByte(byte par1) {
		this.remain = par1;
	}

	public boolean getDirection() {
		return this.direction;
	}

	public void setDirection(boolean par1) {
		this.direction = par1;
	}

	public ItemStack getItemStack() {
		return this.input;
	}

	public void setItemStack(ItemStack item) {
		this.input = item;
		this.setTexture(item);
		this.markDirty();
	}

	public String getCurrentTexture() {
		return this.tex;
	}

	public String getDisplayName() {
		String s = "Empty";
		if (this.getRecipe() != null) {
			s = this.getRecipe().getDisplayName();
		}
		return s;
	}

	public void setTexture(ItemStack input) {
		if (input == null) {
			this.tex = "defeatedcrow:textures/blocks/contents_rice.png";
			return;
		} else {
			IPanRecipe recipe = RecipeRegisterManager.panRecipe.getRecipe(input);
			if (recipe != null) {
				String s = recipe.getTex();
				if (s.contains(":")) {
					this.tex = s;
				} else {
					this.tex = "defeatedcrow:textures/blocks/contents_" + s + ".png";
				}
			} else {
				this.tex = "defeatedcrow:textures/blocks/contents_rice.png";
			}
		}
	}

	private byte getCoolTime() {
		return this.coolTime;
	}

	private void setCoolTime(byte t) {
		this.coolTime = t;
	}

	public int getMetadata() {
		return this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
	}

	@Override
	public void updateEntity() {
		// インベントリのチェックをしている
		if (this.input == null) {
			this.clearTile();
		}

		if (this.coolTime == 0) {
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			this.setCoolTime((byte) 20);
		}

		super.updateEntity();
	}

	public IPanRecipe getRecipe() {
		if (this.input == null)
			return null;
		if (this.input != null) {
			IPanRecipe recipe = RecipeRegisterManager.panRecipe.getRecipe(this.input);
			return recipe;
		}
		return null;
	}

	public ItemStack getOutput() {
		if (this.input != null) {
			IPanRecipe recipe = RecipeRegisterManager.panRecipe.getRecipe(input);
			if (recipe != null) {
				return recipe.getOutput();
			}
		}
		return null;
	}

	public ItemStack getOutputJP() {
		if (this.input != null) {
			IPanRecipe recipe = RecipeRegisterManager.panRecipe.getRecipe(input);
			if (recipe != null) {
				return recipe.getOutputJP();
			}
		}
		return null;
	}

	public void clearTile() {
		this.input = null;
		this.remain = 0;
		this.tex = "defeatedcrow:textures/blocks/contents_rice.png";
	}
}
