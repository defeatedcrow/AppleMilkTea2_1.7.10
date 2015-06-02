package mods.defeatedcrow.common.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.common.item.edible.ItemWallMug;
import mods.defeatedcrow.common.tile.TileCanister;
import mods.defeatedcrow.plugin.LoadModHandler;

/**
 * メタデータ0~7: 酒瓶。空カップを持って右クリックすると、ロック・ストレートで頂ける <br>
 * メタデータ8~15: キャニスター。中身の取り出し及びウォールマグのカスタマイズ。
 */
public class BlockCanister extends BlockContainer {

	private static final String[] contents = new String[] { "_milk", "_milk", "_sugar", "_maple", "_juice", "_nuts",
			"_berryjam" };

	@SideOnly(Side.CLIENT)
	private IIcon[] boxTex;
	@SideOnly(Side.CLIENT)
	private IIcon[] sideTex;

	public BlockCanister() {
		super(Material.circuits);
		this.setStepSound(Block.soundTypeGlass);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
	}

	// 回収動作
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
		int currentMeta = par1World.getBlockMetadata(par2, par3, par4);
		TileCanister tile = (TileCanister) par1World.getTileEntity(par2, par3, par4);

		if (itemstack == null)// 中身を一つづつ取り出すことが出来る。
		{
			if (tile == null) {
				return false;
			} else {
				short i = tile.getRemainShort();
				int rem = checkRemain(i);
				int type = currentMeta & 7;

				// 中身の取得
				String[] get = new String[] { "bucketMilk", "bucketSoy", "sugar", "maple", "honey", "nuts", "berry" };
				ItemStack ret = null;
				if (type == 2) {
					ret = new ItemStack(Items.sugar, 1, 0);
				} else if (type > 2) {
					ret = LoadModHandler.getRandomItem(get[type]);
				}

				// 取得成功時のみ
				if (type > 1 && ret != null) {
					if (!par5EntityPlayer.inventory.addItemStackToInventory(ret)) {
						par5EntityPlayer.entityDropItem(ret, 1);
					}

					if (rem == 0) {
						tile.setRemainShort((short) 0);
						par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
					} else {
						rem--;
						tile.setRemainShort((short) rem);
					}
					par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
					return true;
				}
			}
			return false;
		} else if (itemstack.getItem() == Items.bucket)// キャニスターの場合に限り、中身を一つづつ取り出すことが出来る。
		{
			if (tile == null) {
				return false;
			} else {
				short i = tile.getRemainShort();
				int rem = checkRemain(i);
				int type = currentMeta & 7;

				// 中身の取得
				ItemStack ret = null;
				if (type == 0) {
					ret = new ItemStack(Items.milk_bucket, 1, 0);
				} else if (type == 1) {
					ret = LoadModHandler.getItem("bucketSoy");
				}

				if (type < 2 && ret != null) {
					if (!par5EntityPlayer.inventory.addItemStackToInventory(ret)) {
						par5EntityPlayer.entityDropItem(ret, 1);
					}
					if (!par5EntityPlayer.capabilities.isCreativeMode) {
						--itemstack.stackSize;
					}

					if (rem == 0) {
						tile.setRemainShort((short) 0);
						par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
					} else {
						rem--;
						tile.setRemainShort((short) rem);
					}
					par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
					return true;
				}
			}
			return false;
		} else if (itemstack.getItem() == DCsAppleMilk.wallMug)// wallmugのカスタマイズ
		{
			short i = tile.getRemainShort();
			int type = currentMeta;
			int rem = checkRemain(i);

			int damage = itemstack.getItemDamage();
			int targetMilk = (damage >> 2) & 3;
			int targetSugar = (damage >> 4) & 3;
			int targetFruit = (damage >> 6) & 3;

			boolean flag = false;
			int[] customize = { 4, 12, 16, 32, 48, 128, 192 };

			if (rem >= 0) {
				if (targetMilk == 0 && (type == 0 || type == 1)) {
					flag = true;
				} else if (targetSugar == 0 && (type == 2 || type == 3 || type == 4)) {
					flag = true;
				} else if (targetFruit == 0 && (type == 5 || type == 6)) {
					flag = true;
				}
			}

			if (flag) {
				if (!par5EntityPlayer.capabilities.isCreativeMode) {
					--itemstack.stackSize;
				}

				if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(DCsAppleMilk.wallMug, 1,
						(damage + customize[type & 7])))) {
					par5EntityPlayer.entityDropItem(new ItemStack(DCsAppleMilk.wallMug, 1,
							(damage + customize[type & 7])), 1);
				}

				int newRem = 0;
				if (rem - 1 < 0) {
					par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
				} else {
					newRem = rem - 1;
				}
				tile.setRemainShort((short) newRem);

				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
			}

			return true;
		} else {
			return false;
		}
	}

	// 設置動作
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		short l = (short) par6ItemStack.getItemDamage();

		super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLivingBase, par6ItemStack);
		par1World.setBlockMetadataWithNotify(par2, par3, par4, (l & 15), 3);
		// メタデータ自体はタイプに対応している。RenderBlockクラスでメタデータごとのテクスチャを振り分けるためだが、他にも方法はある気がする。

		// damageから残量だけを取り出す
		int i = l >> 4;
		i = i & 7;
		TileCanister tile = (TileCanister) par1World.getTileEntity(par2, par3, par4);
		if (tile != null) {
			tile.setRemainShort((short) i);
		}
	}

	// 破壊
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
		TileCanister tile = (TileCanister) par1World.getTileEntity(par2, par3, par4);
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

	public int damageDropped(int par1) {
		return par1;
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	public int quantityDropped(Random random) {
		return 0;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	// 外見
	@Override
	public int getRenderType() {
		return DCsAppleMilk.modelCanister;
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

	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		this.thisBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	public void thisBoundingBox(int par1) {
		float f = 0.25F;
		this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, 1.0F, 1.0F - f);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par2;
		if (i > 6)
			i = 6;
		return this.boxTex[i];

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.boxTex = new IIcon[7];

		for (int j = 0; j < 7; ++j) {
			this.boxTex[j] = par1IconRegister.registerIcon("defeatedcrow:contents" + contents[j]);
		}
	}

	// Tile
	@Override
	public TileEntity createNewTileEntity(World world, int a) {

		return new TileCanister();
	}

	public static int checkRemain(short par1)// Remain値の16、32、64の位が残量の管理用バイト
	{
		int m = (par1 & 7);// シフトを不要にした
		return m;
	}

}
