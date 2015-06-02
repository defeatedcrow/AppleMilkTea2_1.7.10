package mods.defeatedcrow.common.tile.appliance;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import mods.defeatedcrow.api.appliance.ITeaMaker;
import mods.defeatedcrow.api.recipe.ITeaRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import mods.defeatedcrow.recipe.IceRecipeRegister;
import mods.defeatedcrow.recipe.TeaRecipeRegister;
import mods.defeatedcrow.recipe.TeaRecipeRegister.TeaRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileMakerNext extends TileEntity implements ITeaMaker {
	private byte remain = 1;
	private byte contentsID = 0;
	private boolean isMilk = false;

	private ItemStack input = null;
	private String tex = "defeatedcrow:textures/blocks/contents_water.png";
	private String tex_milk = "defeatedcrow:textures/blocks/contents_water.png";

	private byte coolTime = 0;

	// NBT
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);

		if (par1NBTTagCompound.hasKey("Input")) {
			this.setItemStack(ItemStack.loadItemStackFromNBT(par1NBTTagCompound.getCompoundTag("Input")));
		}

		this.remain = par1NBTTagCompound.getByte("Remaining");
		this.isMilk = par1NBTTagCompound.getBoolean("Milk");
		this.tex = par1NBTTagCompound.getString("Tex");
		this.tex_milk = par1NBTTagCompound.getString("Tex_Milk");
		this.coolTime = par1NBTTagCompound.getByte("CoolTime");
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setByte("Remaining", this.remain);
		par1NBTTagCompound.setBoolean("Milk", this.isMilk);
		par1NBTTagCompound.setString("Tex", tex);
		par1NBTTagCompound.setString("Tex_Milk", tex_milk);
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

	/* 以下はITeaRecipeのメソッド */

	@Override
	public ITeaRecipe getRecipe() {
		if (Util.notEmptyItem(input)) {
			return RecipeRegisterManager.teaRecipe.getRecipe(input);
		} else {
			return null;
		}

	}

	@Override
	public void setRecipe(ItemStack item) {
		this.input = item;
		this.setTexture(item);
		this.setRemain((byte) (3 + this.worldObj.rand.nextInt(3)));
		this.updateTeaMaker();
	}

	@Override
	public byte getRemain() {
		return this.remain;
	}

	@Override
	public void setRemain(byte par1) {
		this.remain = par1;
	}

	@Override
	public boolean getMilked() {
		return this.isMilk;
	}

	@Override
	public void setMilk(boolean flag) {
		this.isMilk = flag;
		this.remain = 3;
		this.updateTeaMaker();
	}

	@Override
	public ItemStack getOutput() {
		if (this.input != null) {
			ITeaRecipe recipe = RecipeRegisterManager.teaRecipe.getRecipe(input);
			if (recipe != null) {
				if (this.isMilk && recipe.getOutputMilk() != null) {
					return recipe.getOutputMilk();
				} else {
					return recipe.getOutput();
				}
			}
		}
		return null;
	}

	@Override
	public boolean canSetRecipe(ItemStack item) {
		if (this.input != null)
			return false;

		ITeaRecipe recipe = RecipeRegisterManager.teaRecipe.getRecipe(item);

		return recipe != null;

	}

	public ItemStack getItemStack() {
		return this.input;
	}

	private void setItemStack(ItemStack item) {
		this.input = item;
	}

	public String getCurrentTexture() {
		return this.isMilk ? this.tex_milk : this.tex;
	}

	private void setTexture(ItemStack input) {
		if (input == null) {
			this.tex = "defeatedcrow:textures/blocks/contents_water.png";
			this.tex_milk = "defeatedcrow:textures/blocks/contents_water.png";
			return;
		} else {
			ITeaRecipe recipe = RecipeRegisterManager.teaRecipe.getRecipe(input);
			if (recipe != null) {
				this.tex = recipe.getTex();
				if (recipe.getMilkTex() != null) {
					this.tex_milk = recipe.getMilkTex();
				} else {
					this.tex_milk = recipe.getTex();
				}
			} else {
				this.tex = "defeatedcrow:textures/blocks/contents_water.png";
				this.tex_milk = "defeatedcrow:textures/blocks/contents_water.png";
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

	public void clearTile() {
		this.input = null;
		this.setTexture(null);
		this.isMilk = false;
		this.remain = 0;
		this.markDirty();
	}

	private void updateTeaMaker() {
		this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		this.worldObj.notifyBlockChange(xCoord, yCoord, zCoord, DCsAppleMilk.teaMakerNext);
		this.worldObj.func_147453_f(xCoord, yCoord, zCoord, DCsAppleMilk.teaMakerNext);
		this.markDirty();
	}
}
