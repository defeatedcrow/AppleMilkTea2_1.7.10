package mods.defeatedcrow.common.block.brewing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.TileCordial;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/*
 * メタデータにて、熟成完了かどうか、及び残量を管理する。
 * 0~3に残量&熟成完了かどうか、以降のバイトに種類が入っている。
 * 0: 未完了。NBTに日数が入っているので、多少は進行具合を保持できる。
 * 1~3: 残量。また、0でないことが熟成完了のフラグになっている。
 */
public class ItemCordial extends Item {

	private static String[] type = new String[] { "apple", "tea", "cassis", "plum", "apricot" };

	private static final Block thisBlockID = DCsAppleMilk.cordial;

	@SideOnly(Side.CLIENT)
	private IIcon[] thisTex;

	public ItemCordial() {
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setNoRepair();
	}

	// ItemBlockのように振る舞う。ブロック版のCordialを設置し、NBTがある場合はTileのStageに渡す。
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
			int par5, int par6, int par7, float par8, float par9, float par10) {
		Block i1 = par3World.getBlock(par4, par5, par6);

		if (i1 == Blocks.snow && (par3World.getBlockMetadata(par4, par5, par6) & 7) < 1) {
			par7 = 1;
		} else if (i1 != Blocks.vine && i1 != Blocks.tallgrass && i1 != Blocks.deadbush
				&& (i1 == null || !i1.isReplaceable(par3World, par4, par5, par6))) {
			if (par7 == 0) {
				--par5;
			}

			if (par7 == 1) {
				++par5;
			}

			if (par7 == 2) {
				--par6;
			}

			if (par7 == 3) {
				++par6;
			}

			if (par7 == 4) {
				--par4;
			}

			if (par7 == 5) {
				++par4;
			}
		}

		if (par1ItemStack.stackSize == 0) {
			return false;
		} else if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) {
			return false;
		} else if (par5 == 255) {
			return false;
		} else if (par3World.canPlaceEntityOnSide(thisBlockID, par4, par5, par6, false, par7, par2EntityPlayer,
				par1ItemStack)) {
			Block block = thisBlockID;
			int j1 = this.getMetadata(par1ItemStack.getItemDamage());// ダメージは16以上なので、ブロックのメタデータに入れてはいけない
			int type = this.checkType(j1);// 種類
			boolean aged = (j1 & 3) > 0;// 熟成完了か
			int k1 = thisBlockID.onBlockPlaced(par3World, par4, par5, par6, par7, par8, par9, par10, type);
			// onBlockPlacedで改めて返ってくるメタデータ値

			if (placeBlockAt(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10,
					type)) {
				par3World.playSoundEffect((double) ((float) par4 + 0.5F), (double) ((float) par5 + 0.5F),
						(double) ((float) par6 + 0.5F), block.stepSound.getStepResourcePath(),
						(block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
				--par1ItemStack.stackSize;
			}

			// TileEntityへのデータ受け渡し
			TileCordial tile = (TileCordial) par3World.getTileEntity(par4, par5, par6);
			if (tile != null) {
				NBTTagCompound nbt = par1ItemStack.getTagCompound();
				int stage = 0;
				if (nbt != null && !aged) {
					stage = nbt.getByte("stage");
					tile.setAgingStage(stage);
				}
				tile.setAged(aged);
			}// Block側のonBlockPlacedByでも同じことをやっているため、処理が重複している

			return true;
		} else {
			return false;
		}
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Returns true if the given ItemBlock can be placed on the given side of the given block position.
	 */
	public boolean canPlaceItemBlockOnSide(World par1World, int par2, int par3, int par4, int par5,
			EntityPlayer par6EntityPlayer, ItemStack par7ItemStack) {
		Block i1 = par1World.getBlock(par2, par3, par4);

		if (i1 == Blocks.snow) {
			par5 = 1;
		} else if (i1 != Blocks.vine && i1 != Blocks.tallgrass && i1 != Blocks.deadbush
				&& (i1 == null || !i1.isReplaceable(par1World, par2, par3, par4))) {
			if (par5 == 0) {
				--par3;
			}

			if (par5 == 1) {
				++par3;
			}

			if (par5 == 2) {
				--par4;
			}

			if (par5 == 3) {
				++par4;
			}

			if (par5 == 4) {
				--par2;
			}

			if (par5 == 5) {
				++par2;
			}
		}

		return par1World.canPlaceEntityOnSide(thisBlockID, par2, par3, par4, false, par5, (Entity) null, par7ItemStack);
	}

	// metadataには種類の値が入っている
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
			float hitX, float hitY, float hitZ, int metadata) {
		if (!world.setBlock(x, y, z, thisBlockID, metadata, 3)) {
			return false;
		}

		if (world.getBlock(x, y, z) == thisBlockID) {
			thisBlockID.onBlockPlacedBy(world, x, y, z, player, stack);
			thisBlockID.onPostBlockPlaced(world, x, y, z, metadata);
		}

		return true;
	}

	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		int l = par1ItemStack.getItemDamage();
		int type = checkType(l);
		int rem = checkRemain(l);
		if (rem > 0) {
			par3List.add(new String("count: " + rem));
		} else {
			par3List.add(new String("Young"));
		}
	}

	private int checkType(int par1) {
		int m = par1 >> 2;// 右に2シフト
		return m;
	}

	private int checkRemain(int par1) {
		int m = (par1 & 3);// 1、2の桁をチェック
		return m;
	}

	// 以下はサブタイプやアイコン登録など
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		int j = par1 >> 2;
		return this.thisTex[j];
	}

	@Override
	public int getMetadata(int par1) {
		return par1;// ここではダメージ値通りのものを返す
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName() + "_" + par1ItemStack.getItemDamage();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(this, 1, 0));
		par3List.add(new ItemStack(this, 1, 3));
		par3List.add(new ItemStack(this, 1, 4));
		par3List.add(new ItemStack(this, 1, 7));
		par3List.add(new ItemStack(this, 1, 8));
		par3List.add(new ItemStack(this, 1, 11));
		par3List.add(new ItemStack(this, 1, 12));
		par3List.add(new ItemStack(this, 1, 15));
		par3List.add(new ItemStack(this, 1, 16));
		par3List.add(new ItemStack(this, 1, 19));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		this.thisTex = new IIcon[5];

		for (int i = 0; i < 5; ++i) {
			this.thisTex[i] = par1IconRegister.registerIcon("defeatedcrow:cordial_" + type[i]);
		}
	}

}
