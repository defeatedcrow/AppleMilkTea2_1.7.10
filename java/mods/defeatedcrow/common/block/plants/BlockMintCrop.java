package mods.defeatedcrow.common.block.plants;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Random;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockMintCrop extends BlockBush implements IGrowable {
	@SideOnly(Side.CLIENT)
	private IIcon[] iconArray;

	public BlockMintCrop() {
		super();
		this.setTickRandomly(true);
		float f = 0.5F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
		this.setCreativeTab((CreativeTabs) null);
		this.setHardness(0.0F);
		this.setStepSound(soundTypeGrass);
		this.disableStats();
	}

	/*
	 * 真下のブロックに植えることが出来るかをブロックIDで判定するメソッド。
	 * バニラではpar1 == Block.tilledField.blockID;で判定しているが、これでは拡張性がなさすぎるので、
	 * 他の条件を追加してみる。
	 */
	protected boolean canThisPlantGrowOnThisBlockID(Block par1) {
		if (par1 == Blocks.farmland)
			return true;

		/*
		 * 今回は、バニラの耕地ブロックを継承しているかで判定している。
		 * 他のModで追加された耕地ブロックに対応できる可能性がある。
		 */
		Block block = par1;
		return (block != null && block instanceof BlockFarmland);
	}

	/*
	 * ブロックのアップデート時に呼ばれるメソッド。
	 * superでBlockFlowerのメソッド（この場所にブロックとして存在できるかの判定）を呼び、
	 * 次にこのブロックの成長判定を行っている。
	 */
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		super.updateTick(par1World, par2, par3, par4, par5Random);

		if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9) {
			int l = par1World.getBlockMetadata(par2, par3, par4);

			if (l < 3) {
				float f = this.getGrowthRate(par1World, par2, par3, par4);

				if (par5Random.nextInt((int) (25.0F / f) + 1) == 0) {
					++l;
					par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
				}
			}
		}
	}

	// IGrowableで使用されているメソッド。
	public void grow(World world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange(world.rand, 2, 5);

		if (l > 3) {
			l = 3;
		}

		world.setBlockMetadataWithNotify(x, y, z, l, 2);
	}

	/*
	 * 骨粉による成長メソッド。
	 */
	public boolean fertilize(World par1World, int par2, int par3, int par4) {
		return (par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2));
	}

	/*
	 * 周囲のブロックから、成長しやすさを判定しているところ。
	 */
	private float getGrowthRate(World par1World, int par2, int par3, int par4) {
		float f = 1.0F;
		Block l = par1World.getBlock(par2, par3, par4 - 1);
		Block i1 = par1World.getBlock(par2, par3, par4 + 1);
		Block j1 = par1World.getBlock(par2 - 1, par3, par4);
		Block k1 = par1World.getBlock(par2 + 1, par3, par4);
		Block l1 = par1World.getBlock(par2 - 1, par3, par4 - 1);
		Block i2 = par1World.getBlock(par2 + 1, par3, par4 - 1);
		Block j2 = par1World.getBlock(par2 + 1, par3, par4 + 1);
		Block k2 = par1World.getBlock(par2 - 1, par3, par4 + 1);

		boolean flag = j1 == this || k1 == this;
		boolean flag1 = l == this || i1 == this;
		boolean flag2 = l1 == this || i2 == this || j2 == this || k2 == this;

		for (int l2 = par2 - 1; l2 <= par2 + 1; ++l2) {
			for (int i3 = par4 - 1; i3 <= par4 + 1; ++i3) {
				Block j3 = par1World.getBlock(l2, par3 - 1, i3);
				float f1 = 0.0F;

				if (j3 != null && j3.canSustainPlant(par1World, l2, par3 - 1, i3, ForgeDirection.UP, this)) {
					f1 = 1.0F;
				}

				if (l2 != par2 || i3 != par4) {
					f1 /= 4.0F;
				}

				f += f1;
			}
		}

		/*
		 * バニラだと下記条件（隣接して同じ作物を植えている）では成長速度が下がっている。
		 * ミントの場合は逆になる。
		 */
		if (flag2 || flag && flag1) {
			f *= 2.0F;
		}

		return f;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int j = MathHelper.clamp_int(par2, 0, 3);
		return this.iconArray[j];
	}

	/*
	 * ブロックのレンダータイプ。6はバニラの小麦のように、井型に表示される。
	 */
	public int getRenderType() {
		return 6;
	}

	/*
	 * 対応するタネアイテムのID。
	 */
	protected Item getSeedItem() {
		return DCsAppleMilk.itemMintSeed;
	}

	/*
	 * 得られる作物アイテムのID。
	 */
	protected Item getCropItem() {
		return DCsAppleMilk.leafTea;
	}

	/*
	 * 複数の種類のアイテムをドロップさせる場合のメソッド。
	 * ドロップするアイテムの種類は、getBlockDroppedで設定する。
	 */
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);

		if (metadata >= 3) {
			ret.add(new ItemStack(this.getCropItem(), 1, 1));
			for (int n = 0; n < 1 + fortune; n++) {
				if (world.rand.nextInt(15) <= metadata) {
					ret.add(new ItemStack(this.getCropItem(), 1, 1));
					ret.add(new ItemStack(this.getCropItem(), 1, 1));
				}
			}
		}

		return ret;
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return DCsAppleMilk.itemMintSeed;
	}

	public int quantityDropped(Random par1Random) {
		return 1;
	}

	public int damageDropped(int par1) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
		return this.getSeedItem();
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.iconArray = new IIcon[4];

		for (int i = 0; i < this.iconArray.length; ++i) {
			this.iconArray[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "crop_mint" + "_stage_" + i);
		}
	}

	@Override
	public boolean func_149851_a(World world, int x, int y, int z, boolean var5) {
		return world.getBlockMetadata(x, y, z) < 3;
	}

	@Override
	public boolean func_149852_a(World world, Random rand, int x, int y, int z) {
		return true;
	}

	@Override
	public void func_149853_b(World world, Random rand, int x, int y, int z) {
		this.grow(world, x, y, z);
	}
}
