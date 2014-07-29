package mods.defeatedcrow.common.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class TileLargeBottle extends TileHasRemain2{
	

	@SideOnly(Side.CLIENT)
	public short getRemainClient()
	{
		int r = this.getRemainShort();
		r = (r & 7);
		return (short) r;
	}

}
