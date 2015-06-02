package mods.defeatedcrow.common.tile.appliance;

import java.io.DataOutputStream;
import java.io.IOException;

import mods.defeatedcrow.recipe.*;
import mods.defeatedcrow.recipe.IceRecipeRegister.IceRecipe;
import mods.defeatedcrow.api.recipe.*;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileIceMaker extends TileEntity implements ISidedInventory {

	// 現在のチャージ量
	public int chargeAmount;
	// 燃料アイテムのチャージ量
	public int currentItemCharge;
	// 調理時間はかまどと同じ
	public int cookTime;
	// チャージアイテムを溶かす際の判定発生間隔
	// 温暖バイオームでのチャージ減少判定にも使用
	private int coolTime = 8;

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);

		// アイテムの読み込み
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
		this.iceItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.iceItemStacks.length) {
				this.iceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.chargeAmount = par1NBTTagCompound.getShort("ChargeAmount");
		this.cookTime = par1NBTTagCompound.getShort("CookTime");
		this.coolTime = par1NBTTagCompound.getByte("CoolTime");

	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);

		// 燃焼時間や調理時間などの書き込み
		par1NBTTagCompound.setShort("ChargeAmount", (byte) this.chargeAmount);
		par1NBTTagCompound.setShort("CookTime", (short) this.cookTime);
		par1NBTTagCompound.setByte("CoolTime", (byte) this.coolTime);

		// アイテムの書き込み
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.iceItemStacks.length; ++i) {
			if (this.iceItemStacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.iceItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);

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

	// 調理中の矢印の描画
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int par1) {
		return this.cookTime * par1 / 150;
	}

	// チャージゲージの描画
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int par1) {
		return this.chargeAmount * par1 / 127;
	}

	// 調理中
	public boolean isBurning() {
		return this.cookTime > 0;
	}

	// 調理中
	public boolean isCharged() {
		return this.chargeAmount > 0;
	}

	// 以下はパケット送受信用メソッド
	public void setChargeAmount(int par1) {
		this.chargeAmount = par1;
	}

	public int getChargeAmount() {
		return this.chargeAmount;
	}

	/**
	 * Tick毎のアイスメーカーの処理。ほぼバニラかまどのパクリ。
	 */
	public void updateEntity() {
		boolean flag = this.isCharged();
		boolean flag1 = false;

		// まず燃料を溶かす処理

		// 硬直時間：燃料の消費及び温暖バイオームでのチャージ減少に利用
		if (this.coolTime > 0) {
			--this.coolTime;
		}

		if (!this.worldObj.isRemote) {
			if (this.coolTime == 0) {
				// チャージが満タンではないか？
				if (this.chargeAmount < 127 && this.isItemFuel(this.iceItemStacks[1])) {
					// チャージ残量＋アイテムのチャージ量
					int i = this.chargeAmount + getItemBurnTime(this.iceItemStacks[1]);

					if (i < 128)// 128未満ならOK
					{
						this.chargeAmount = i;
						flag1 = true;

						// スロット1のアイテムを減らす
						if (this.iceItemStacks[1] != null) {
							--this.iceItemStacks[1].stackSize;

							if (this.iceItemStacks[1].stackSize == 0) {
								this.iceItemStacks[1] = this.iceItemStacks[1].getItem().getContainerItem(
										this.iceItemStacks[1]);
							}
						}
					}
				}

				// 暑いバイオームではチャージを1減らす
				if (this.isHotBiome() == 2 && this.isCharged()) {
					--this.chargeAmount;
					if (!this.isCharged())
						flag1 = false;
				}

				// 最後に硬直時間を8tickに設定
				this.coolTime = 8;
			}

			// 調理の待ち時間を設定（バニラかまどよりは早い）
			if (this.isCharged() && this.canSmelt()) {
				++this.cookTime;

				if (this.cookTime == 150) {
					this.cookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			} else {
				this.cookTime = 0;
			}

			if (flag != this.isCharged()) {
				flag1 = true;
			}

			if (flag1) {
				this.markDirty();
			}
		}
	}

	/**
	 * アイテムがIceMakerにレシピ登録された材料かどうか
	 * レシピ登録はTeaMakerと類似の登録制を使用
	 */
	private boolean canSmelt() {
		if (this.iceItemStacks[0] == null) {
			return false;
		} else {
			IIceRecipe recipe = RecipeRegisterManager.iceRecipe.getRecipe(this.iceItemStacks[0]);

			if (recipe != null) {

				// スタックサイズのチェック
				if (this.iceItemStacks[0].stackSize < recipe.getInput().stackSize)
					return false;

				if (recipe.getContainer() != null) {
					ItemStack container = recipe.getContainer();
					ItemStack output = recipe.getOutput();

					if (output == null || container == null)
						return false;
					boolean flag1 = false;
					boolean flag2 = false;

					if (this.iceItemStacks[2] == null) {
						flag1 = true;
					} else {
						if (this.iceItemStacks[2].isItemEqual(output)) {
							int result = this.iceItemStacks[2].stackSize + output.stackSize;
							flag1 = (result <= this.getInventoryStackLimit() && result <= output.getMaxStackSize());
						}
					}

					if (this.iceItemStacks[3] == null) {
						flag2 = true;
					} else {
						if (this.iceItemStacks[3].isItemEqual(container)) {
							int leave = this.iceItemStacks[3].stackSize + container.stackSize;
							flag2 = (leave <= this.getInventoryStackLimit() && leave <= container.getMaxStackSize());
						}
					}

					return (flag1 && flag2);
				} else {
					ItemStack output = recipe.getOutput();

					if (output == null)
						return false;

					if (this.iceItemStacks[2] == null)
						return true;
					if (!this.iceItemStacks[2].isItemEqual(output))
						return false;

					int result = this.iceItemStacks[2].stackSize + output.stackSize;
					return (result <= this.getInventoryStackLimit() && result <= output.getMaxStackSize());
				}
			}

			return false;
		}
	}

	/**
	 * 実際に材料を消費して、完成スロットにアウトプットを返すためのメソッド
	 */
	public void smeltItem() {
		if (this.canSmelt()) {
			IIceRecipe recipe = RecipeRegisterManager.iceRecipe.getRecipe(this.iceItemStacks[0]);
			ItemStack itemstack = recipe.getOutput();
			ItemStack container = recipe.getContainer();

			if (this.iceItemStacks[0].stackSize < recipe.getInput().stackSize)
				return;

			if (this.iceItemStacks[2] == null) {
				this.iceItemStacks[2] = itemstack.copy();
			} else if (this.iceItemStacks[2].isItemEqual(itemstack)) {
				this.iceItemStacks[2].stackSize += itemstack.stackSize;
			}

			if (container != null)// 材料スロットに残すアイテム
			{
				if (this.iceItemStacks[3] == null) {
					this.iceItemStacks[3] = container.copy();
				} else if (this.iceItemStacks[3].isItemEqual(container)) {
					this.iceItemStacks[3].stackSize += container.stackSize;
				}
			}

			this.iceItemStacks[0].stackSize -= recipe.getInput().stackSize;

			if (this.iceItemStacks[0].stackSize <= 0) {
				this.iceItemStacks[0] = null;
			}

			// チャージを消費
			if (this.isHotBiome() == 0) {
				--this.chargeAmount;
			} else {
				this.chargeAmount -= this.isHotBiome() * 2;
			}
			if (this.chargeAmount < 0)
				this.chargeAmount = 0;
		}
	}

	/**
	 * 現在のチャージ残量が、稼働に必要な分だけあるか。バイオームごとに異なるため専用メソッドで判定。
	 */
	public boolean enoughCharge() {
		int biome = this.isHotBiome();
		if (biome == 0) {
			return this.isCharged();
		} else if (biome == 2) {
			return (this.chargeAmount > 3);
		} else {
			return (this.chargeAmount > 1);
		}
	}

	/**
	 * このアイテムのチャージ量
	 * 
	 * @param par0ItemStack
	 *            チェック対象アイテム
	 */
	public static int getItemBurnTime(ItemStack par0ItemStack) {
		if (par0ItemStack == null) {
			return 0;
		} else {
			// Item item = par0ItemStack.getItem();
			//
			// if (par0ItemStack.getItem() instanceof ItemBlock && Block.getBlockFromItem(item) != null)
			// {
			// Block block = Block.getBlockFromItem(item);
			//
			// if (block.getMaterial() == Material.craftedSnow)
			// {
			// return 4;
			// }
			//
			// if (block.getMaterial() == Material.ice)
			// {
			// return 8;
			// }
			// }
			// if (item == Items.snowball) return 1;

			if (RecipeRegisterManager.iceRecipe.getChargeAmount(par0ItemStack) > 0) {
				return RecipeRegisterManager.iceRecipe.getChargeAmount(par0ItemStack);
			}
			return 0;
		}
	}

	/**
	 * このアイテムがIceMakerにチャージできる冷媒であるかどうか
	 * 
	 * @param par0ItemStack
	 *            チェック対象アイテム
	 */
	public static boolean isItemFuel(ItemStack par0ItemStack) {
		return getItemBurnTime(par0ItemStack) > 0;
	}

	/**
	 * 暑いバイオームかどうか
	 * 砂漠系・ジャングル系バイオームでは2、普通・温暖なバイオームでは1、寒冷バイオームでは0を返す。
	 * （BiomeDictionaryを利用しているため他MODの追加バイオームでも正常に機能するはず。）
	 * どれにも属さない（BiomeDictionaryに登録していない）場合は一律で1。
	 */
	public int isHotBiome() {
		int l = 1;
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(xCoord, zCoord);

		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.HOT)
				|| BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.JUNGLE)
				|| BiomeDictionary.isBiomeOfType(biome, Type.NETHER)) {
			l = 2;
		} else if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.COLD)) {
			l = 0;
		} else {
			l = 1;
		}

		return l;
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	private static final int[] slots_top = new int[] { 0 };
	private static final int[] slots_bottom = new int[] { 2, 3, 1 };
	private static final int[] slots_sides = new int[] { 1 };

	public ItemStack[] iceItemStacks = new ItemStack[4];

	// スロット数
	@Override
	public int getSizeInventory() {
		return this.iceItemStacks.length;
	}

	// インベントリ内の任意のスロットにあるアイテムを取得
	@Override
	public ItemStack getStackInSlot(int par1) {
		return this.iceItemStacks[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (this.iceItemStacks[par1] != null) {
			ItemStack itemstack;

			if (this.iceItemStacks[par1].stackSize <= par2) {
				itemstack = this.iceItemStacks[par1];
				this.iceItemStacks[par1] = null;
				return itemstack;
			} else {
				itemstack = this.iceItemStacks[par1].splitStack(par2);

				if (this.iceItemStacks[par1].stackSize == 0) {
					this.iceItemStacks[par1] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		if (this.iceItemStacks[par1] != null) {
			ItemStack itemstack = this.iceItemStacks[par1];
			this.iceItemStacks[par1] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	// インベントリ内のスロットにアイテムを入れる
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {

		if (par1 > 3)
			par1 = 0;// 存在しないスロットに入れようとすると強制的に材料スロットに変更される。

		this.iceItemStacks[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	// インベントリの名前
	@Override
	public String getInventoryName() {
		return "Ice Maker";
	}

	// 多言語対応かどうか
	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	// インベントリ内のスタック限界値
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
		super.markDirty();
	}

	// par1EntityPlayerがTileEntityを使えるかどうか
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
				: par1EntityPlayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
						(double) this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
		return par1 > 1 ? false : (par1 == 1 ? this.isItemFuel(par2ItemStack) : true);
	}

	// ホッパーにアイテムの受け渡しをする際の優先度
	@Override
	public int[] getAccessibleSlotsFromSide(int par1) {
		return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
	}

	// ホッパーからアイテムを入れられるかどうか
	@Override
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
		return this.isItemValidForSlot(par1, par2ItemStack);
	}

	// 隣接するホッパーにアイテムを送れるかどうか
	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
		return par3 != 0 || par1 != 1;
	}

}
