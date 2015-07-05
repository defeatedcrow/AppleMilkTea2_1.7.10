package mods.defeatedcrow.common.block.container;

import java.util.List;
import java.util.Random;

import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGunpowderContainer extends Block {

	private static final String[] boxType = new String[] { "gunpowder", "kayaku", "clay", "clam" };

	@SideOnly(Side.CLIENT)
	private IIcon[] boxTex;
	@SideOnly(Side.CLIENT)
	private IIcon boxSideTex;

	public BlockGunpowderContainer() {
		super(Material.ground);
		this.setHardness(1.0F);
		this.setResistance(2.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setTickRandomly(true);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par2 & 3;
		if (i > 3)
			i = 3;
		if (par1 == 1) {
			return this.boxTex[i];
		} else {
			return this.boxSideTex;
		}
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if (!par1World.isRemote) {
			super.updateTick(par1World, par2, par3, par4, par5Random);

			int meta = par1World.getBlockMetadata(par2, par3, par4);
			int m = meta & 3;
			boolean isHalf = (meta & 4) != 0;

			if (!isHalf && !DCsConfig.noWetGContainer) {
				if (m < 2 && this.isRaining(par1World, par2, par3, par4)
						&& par1World.canBlockSeeTheSky(par2, par3 + 1, par4)) {
					if (m == 0)
						par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
					else if (m == 1)
						par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
				} else if (this.isDryBiome(par1World, par2, par3, par4)) {
					if (m == 1)
						par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
					else if (m == 2)
						par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
				}
			}

			if (m == 3 && DCsConfig.bonemealClam) {
				boolean flag = false;
				int y = 0;
				// 2,3,4マス上についてチェックする
				for (int i = 0; i < 3; i++) {
					if (this.likeBonemeal(par1World, par2, par3 + 2 + i, par4)) {
						flag = true;
						y = par3 + 2 + i;
						break;
					}
				}

				if (flag) {
					par1World.playAuxSFX(2005, par2, y + 1, par4, 0);

					if (par1World.rand.nextInt(10) == 0) {
						par1World.setBlock(par2, par3, par4, Blocks.dirt);
					}
				}
			}
		}
	}

	private boolean isRaining(World world, int x, int y, int z) {
		boolean a = world.isRaining();
		BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		boolean b = !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.DRY)
				&& !BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.NETHER);
		return a && b;
	}

	private boolean isDryBiome(World world, int x, int y, int z) {
		BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		boolean b = BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.DRY)
				|| BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.NETHER);
		return b;
	}

	public static boolean likeBonemeal(World par1World, int par2, int par3, int par4) {
		Block l = par1World.getBlock(par2, par3, par4);
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		BonemealEvent event = new BonemealEvent(null, par1World, l, par2, par3, par4);
		if (MinecraftForge.EVENT_BUS.post(event)) {
			return false;
		}

		if (event.getResult() == Result.ALLOW) {
			return true;
		}

		if (l instanceof IGrowable) {
			IGrowable igrowable = (IGrowable) l;

			if (igrowable.func_149851_a(par1World, par2, par3, par4, par1World.isRemote)) {
				if (!par1World.isRemote) {
					if (igrowable.func_149852_a(par1World, par1World.rand, par2, par3, par4)) {
						igrowable.func_149853_b(par1World, par1World.rand, par2, par3, par4);
					}
				}

				return true;
			}
		}
		return false;
	}

	@Override
	public int tickRate(World par1World) {
		return 20;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
		par3List.add(new ItemStack(par1, 1, 3));
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.boxSideTex = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "container_S");
		this.boxTex = new IIcon[4];

		for (int i = 0; i < 4; ++i) {
			this.boxTex[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "container_" + boxType[i]
					+ "_T");
		}
	}

	// ハーフブロック化

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int damageDropped(int par1) {
		return par1 & 3;
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		int meta = par6ItemStack.getItemDamage();
		int next = meta;

		if (par5EntityLivingBase.isSneaking()) {
			next = next | 4;
		}

		par1World.setBlockMetadataWithNotify(par2, par3, par4, next, 3);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		this.updateThisBounds(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	public void updateThisBounds(int meta) {
		if ((meta & 4) == 4) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		} else {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

}
