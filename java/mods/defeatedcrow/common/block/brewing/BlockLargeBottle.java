package mods.defeatedcrow.common.block.brewing;

import java.util.Random;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.TileLargeBottle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 酒瓶。空カップを持って右クリックすると、ロック・ストレートで頂ける <br>
 * キャニスターを別ブロックに分離。
 */
public class BlockLargeBottle extends BlockContainer {

	private static final String[] contents = new String[] { "_shothu", "_sake", "_beer", "_wine", "_gin", "_rum",
			"_vodka", "_whiskey", "_brandy" };

	@SideOnly(Side.CLIENT)
	private IIcon[] boxTex;
	@SideOnly(Side.CLIENT)
	private IIcon[] sideTex;
	@SideOnly(Side.CLIENT)
	private IIcon[] itemTex;

	public BlockLargeBottle() {
		super(Material.circuits);
		this.setStepSound(Block.soundTypeGlass);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
	}

	// 回収動作
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
		int currentMeta = par1World.getBlockMetadata(par2, par3, par4);
		TileLargeBottle tile = (TileLargeBottle) par1World.getTileEntity(par2, par3, par4);

		if (itemstack == null)// 素手では何もしない
		{
			return false;
		} else if (itemstack.getItem() == Item.getItemFromBlock(DCsAppleMilk.emptyCup))// カップでストレートのお酒を汲む
		{
			short i = tile.getRemainShort();
			int type = currentMeta;
			int rem = checkRemain(i);

			boolean flag = false;

			if (i > 0) {
				tile.setRemainShort((short) (i - 1));
				flag = true;
			} else {

			}

			if (flag) {
				int meta = 0;
				if (type == 0)
					meta = 11;
				else if (type == 8)
					meta = 12;
				else {
					meta = type - 1;
				}

				if (!par5EntityPlayer.capabilities.isCreativeMode) {
					--itemstack.stackSize;
				}

				if (!par1World.isRemote) {
					EntityItem drop = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY,
							par5EntityPlayer.posZ, new ItemStack(DCsAppleMilk.alcoholCup, 1, meta));
					par1World.spawnEntityInWorld(drop);
				}

				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
			}

			return true;
		} else {
			return false;
		}
	}

	// 設置動作
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		short l = (short) par6ItemStack.getItemDamage();
		int playerFacing = MathHelper.floor_double((double) ((par5EntityLivingBase.rotationYaw * 4F) / 360F) + 0.5D) & 3;

		boolean facing = false;
		if (playerFacing == 1 || playerFacing == 3) {
			facing = false;
		} else {
			facing = true;
		}

		super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLivingBase, par6ItemStack);
		par1World.setBlockMetadataWithNotify(par2, par3, par4, (l & 15), 3);
		// メタデータ自体はタイプに対応している。RenderBlockクラスでメタデータごとのテクスチャを振り分けるためだが、他にも方法はある気がする。

		// damageから残量だけを取り出す
		int i = l >> 4;
		i = i & 7;
		TileLargeBottle tile = (TileLargeBottle) par1World.getTileEntity(par2, par3, par4);
		if (tile != null) {
			tile.setRemainShort((short) i);
			tile.setSide(facing);
		}
	}

	// 破壊
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
		TileLargeBottle tile = (TileLargeBottle) par1World.getTileEntity(par2, par3, par4);
		int type = par1World.getBlockMetadata(par2, par3, par4);

		if (tile != null) {
			// ブロックのメタデータとTileのShort値から新しいダメージ値を生成
			short l = (short) (tile.getRemainShort());

			int damage = (l << 4) + par6;

			if (l >= 0 && l <= 5000)// 上限については余り考えていない
			{
				float f = par1World.rand.nextFloat() * 0.8F + 0.1F;
				float f1 = par1World.rand.nextFloat() * 0.8F + 0.1F;
				float f2 = par1World.rand.nextFloat() * 0.8F + 0.1F;

				// アイテム版の酒瓶をドロップ
				ItemStack itemstack = new ItemStack(DCsAppleMilk.itemLargeBottle, 1, damage);
				EntityItem entityitem = new EntityItem(par1World, (double) ((float) par2 + f),
						(double) ((float) par3 + f1), (double) ((float) par4 + f2), itemstack);

				float f3 = 0.05F;
				entityitem.motionX = (double) ((float) par1World.rand.nextGaussian() * f3);
				entityitem.motionY = (double) ((float) par1World.rand.nextGaussian() * f3 + 0.2F);
				entityitem.motionZ = (double) ((float) par1World.rand.nextGaussian() * f3);
				par1World.spawnEntityInWorld(entityitem);
			}

			par1World.func_147453_f(par2, par3, par4, par5);
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return null;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	// 外見
	@Override
	public int getRenderType() {
		return DCsAppleMilk.modelLargeBottle;
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
		this.thisBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	public void thisBoundingBox(int par1) {
		float f = 0.25F;
		this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, 1.0F, 1.0F - f);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par2;
		if (i > 8)
			i = 8;
		if (par1 == 2) {
			return this.boxTex[i];
		} else if (par1 == 3) {
			return this.sideTex[i];
		} else {
			return this.itemTex[i];
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.sideTex = new IIcon[9];
		this.boxTex = new IIcon[9];
		this.itemTex = new IIcon[9];
		this.blockIcon = par1IconRegister.registerIcon("defeatedcrow:bottle" + "_shothu");

		for (int i = 0; i < 9; ++i) {
			this.sideTex[i] = par1IconRegister.registerIcon("defeatedcrow:bottleside" + contents[i]);
			this.boxTex[i] = par1IconRegister.registerIcon("defeatedcrow:bottle" + contents[i]);
			this.itemTex[i] = par1IconRegister.registerIcon("defeatedcrow:itembottle" + contents[i]);
		}
	}

	// Tile
	@Override
	public TileEntity createNewTileEntity(World world, int a) {

		return new TileLargeBottle();
	}

	public static int checkRemain(short par1)// Remain値の16、32、64の位が残量の管理用バイト
	{
		int m = (par1 & 7);// シフトを不要にした
		return m;
	}

}
