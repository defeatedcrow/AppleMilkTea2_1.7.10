package mods.defeatedcrow.common.block.energy;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.energy.*;
import mods.defeatedcrow.plugin.SSector.LoadSSectorPlugin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

public class BlockHandleEngine extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon[] thisIcon;

	public BlockHandleEngine() {
		super(Material.clay);
		this.setHardness(0.5F);
		this.setResistance(1.0F);
		this.setStepSound(Block.soundTypeWood);
		this.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.75F, 0.625F);
	}

	// 右クリック処理
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		if (tile != null && tile instanceof TileHandleEngine) {
			if (par5EntityPlayer == null || par5EntityPlayer instanceof FakePlayer)
				return true;

			TileHandleEngine engine = (TileHandleEngine) tile;
			engine.setInterval(8);

			if (!par1World.isRemote) {
				int def = par1World.difficultySetting.getDifficultyId();
				boolean reduce = false;
				if (def > 0) {
					int c = 40 / def;
					reduce = engine.getClick() > c;
				}

				if (reduce) {
					engine.setClick(0);
					int f = par5EntityPlayer.getFoodStats().getFoodLevel();
					float s = par5EntityPlayer.getFoodStats().getSaturationLevel();
					if (f >= 1) {
						par5EntityPlayer.getFoodStats().addStats(-1, 0.0F);
					}
					if (Loader.isModLoaded("SextiarySector")) {
						if (LoadSSectorPlugin.getStaminaStats(par5EntityPlayer) > 1) {
							LoadSSectorPlugin.addStatus(0, 0.0F, -1, 0.0F, par5EntityPlayer);
						}
					}
				} else {
					engine.setClick(engine.getClick() + 1);
				}
			}
			return true;
		}

		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileHandleEngine();
	}

	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.75F, 0.875F);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return par1 == 0 ? this.thisIcon[0] : this.thisIcon[1];
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	public int getMobilityFlag() {
		return 2;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.thisIcon = new IIcon[2];
		for (int i = 0; i < 2; ++i) {
			if (i == 0)
				this.thisIcon[i] = par1IconRegister.registerIcon("defeatedcrow:teppann");
			else
				this.thisIcon[i] = par1IconRegister.registerIcon("defeatedcrow:lampside_burst_0");

		}
		this.blockIcon = par1IconRegister.registerIcon("defeatedcrow:teppann");
	}

}
