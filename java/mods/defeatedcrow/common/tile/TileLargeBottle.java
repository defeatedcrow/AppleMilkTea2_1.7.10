package mods.defeatedcrow.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileLargeBottle extends TileHasRemain2 {

	private boolean side = false;

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.side = par1NBTTagCompound.getBoolean("Side");
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setBoolean("Side", this.side);
	}

	public boolean getSide() {
		return this.side;
	}

	public void setSide(boolean flag) {
		this.side = flag;
	}

	@SideOnly(Side.CLIENT)
	public short getRemainClient() {
		int r = this.getRemainShort();
		r = (r & 7);
		return (short) r;
	}

}
