package mods.defeatedcrow.common.block;

import java.util.List;
import java.util.Random;

import mods.defeatedcrow.client.particle.EntityOrbFX;
import mods.defeatedcrow.client.particle.ParticleTex;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.tile.TileCLamp;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockChalcedonyLamp extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon[] color;
	@SideOnly(Side.CLIENT)
	private IIcon[] burst;
	@SideOnly(Side.CLIENT)
	private IIcon[] force;
	@SideOnly(Side.CLIENT)
	private IIcon[] sword;
	@SideOnly(Side.CLIENT)
	private IIcon inner;

	public BlockChalcedonyLamp(Material material, boolean flag) {
		super(Material.glass);
		this.setHardness(0.5F);
		this.setResistance(1.0F);
		this.setStepSound(Block.soundTypeGlass);
		this.setLightLevel(1.0F);
		this.setTickRandomly(true);
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
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
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		this.LampBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	public void LampBoundingBox(int par1) {
		float f = 0.125F;
		if ((par1 < 8)) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		} else {
			this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, 1.0F, 1.0F - f);
		}

	}

	@Override
	public int getMobilityFlag() {
		return 0;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return DCsConfig.setAltTexturePass == 1 ? null : new TileCLamp();
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		// this.setDefaultDirection(world, x, y, z);
	}

	// private void setDefaultDirection(World world, int x, int y, int z)
	// {
	// if (!DCsConfig.noUseCupDirection && DCsConfig.setAltTexturePass > 1)
	// {
	// TileCLamp tile = (TileCLamp)world.getTileEntity(x, y, z);
	//
	// if (!world.isRemote && tile != null)
	// {
	// Block var5 = world.getBlock(x, y, z - 1);
	// Block var6 = world.getBlock(x, y, z + 1);
	// Block var7 = world.getBlock(x - 1, y, z);
	// Block var8 = world.getBlock(x + 1, y, z);
	// byte var9 = 0;
	//
	// if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])
	// {
	// var9 = 0;
	// }
	//
	// if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
	// {
	// var9 = 1;
	// }
	//
	// if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])
	// {
	// var9 = 2;
	// }
	//
	// if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])
	// {
	// var9 = 4;
	// }
	//
	// tile.setDirectionByte(var9);
	// }
	// }
	// }

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		int playerFacing = MathHelper.floor_double((double) ((par5EntityLivingBase.rotationYaw * 4F) / 360F) + 0.5D) & 3;

		if (DCsConfig.setAltTexturePass > 1) {
			byte facing = 0;
			if (playerFacing == 0) {
				facing = 0;
			}
			if (playerFacing == 1) {
				facing = 1;
			}
			if (playerFacing == 2) {
				facing = 2;
			}
			if (playerFacing == 3) {
				facing = 4;
			}

			TileEntity tileEntity = par1World.getTileEntity(par2, par3, par4);
			if (tileEntity != null && tileEntity instanceof TileCLamp) {
				((TileCLamp) tileEntity).setDirectionByte(facing);
				par1World.markBlockForUpdate(par2, par3, par4);
			}
		}
	}

	// rendering
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		/*
		 * 0~3 : ノーマルランプ
		 * 4~7 : ガラスランプ
		 * 8 ~ 11 : 特殊型ランプ
		 */
		int type = par2 & 3;
		if (par2 < 4) {
			return par1 == 1 ? this.color[type] : this.inner;
		} else if (par2 > 3 && par2 < 8) {
			return par1 == 1 ? this.color[type] : this.inner;
		} else if (par2 == 8) {
			return par1 == 1 ? this.color[0]
					: (par1 == 0 ? this.burst[0] : (par1 == 2 ? this.burst[1] : this.burst[2]));
		} else if (par2 == 9) {
			return par1 == 1 ? this.color[1] : (par1 == 0 ? this.force[0] : this.force[1]);
		} else if (par2 == 11) {
			return par1 == 1 ? this.color[3]
					: (par1 == 0 ? this.sword[0] : (par1 == 2 ? this.sword[1] : this.sword[2]));
		} else {
			return this.color[2];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < 8; ++i) {
			par3List.add(new ItemStack(this, 1, i));
		}
		par3List.add(new ItemStack(this, 1, 8));
		par3List.add(new ItemStack(this, 1, 9));
		par3List.add(new ItemStack(this, 1, 10));
		par3List.add(new ItemStack(this, 1, 11));
	}

	@Override
	public int getRenderType() {
		return DCsAppleMilk.modelCLamp;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		int l = par1World.getBlockMetadata(par2, par3, par4);
		Block i = par1World.getBlock(par2, par3 - 1, par2);
		double d0 = (double) ((float) par2 + 0.45F + par5Random.nextFloat() / 10);
		double d1 = (double) ((float) par3 + 0.25 + par5Random.nextFloat() / 10);
		double d2 = (double) ((float) par4 + 0.45F + par5Random.nextFloat() / 10);
		double d3 = 0.0159999988079071D;
		double d4 = 0.27000001072883606D;

		if (!DCsConfig.noRenderFoodsSteam && DCsConfig.setAltTexturePass == 1) {

			if (l == 8 || l == 10) {
				EntityOrbFX cloud = new EntityOrbFX(par1World, d0, d1, d2, 0.0D, d3, 0.0D);
				cloud.setParticleIcon(ParticleTex.getInstance().getIcon("orb"));
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
			}

		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.color = new IIcon[4];
		for (int i = 0; i < 4; ++i) {
			if (i == 0)
				this.color[i] = par1IconRegister.registerIcon("defeatedcrow:chalcedony");
			else if (i == 1)
				this.color[i] = par1IconRegister.registerIcon("defeatedcrow:chalcedony_orange");
			else if (i == 2)
				this.color[i] = par1IconRegister.registerIcon("defeatedcrow:chalcedony_white");
			else
				this.color[i] = par1IconRegister.registerIcon("defeatedcrow:chalcedony_black");

		}
		this.force = new IIcon[2];
		for (int i = 0; i < 2; ++i) {
			this.force[i] = par1IconRegister.registerIcon("defeatedcrow:lampside_force_" + i);

		}
		this.burst = new IIcon[3];
		for (int i = 0; i < 3; ++i) {
			this.burst[i] = par1IconRegister.registerIcon("defeatedcrow:lampside_burst_" + i);

		}
		this.sword = new IIcon[3];
		for (int i = 0; i < 3; ++i) {
			this.sword[i] = par1IconRegister.registerIcon("defeatedcrow:lampside_stone_" + i);

		}
		this.inner = par1IconRegister.registerIcon("defeatedcrow:whitepanel");
	}

}
