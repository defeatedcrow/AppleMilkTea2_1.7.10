package mods.defeatedcrow.common.block.appliance;

import java.util.Random;

import mods.defeatedcrow.api.events.TeamakerRightClickEvent;
import mods.defeatedcrow.api.recipe.ITeaRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.AchievementRegister;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.appliance.TileMakerNext;
import mods.defeatedcrow.plugin.IC2.LoadIC2Plugin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTeaMakerNext extends BlockContainer {

	public BlockTeaMakerNext() {
		super(Material.ground);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		Random rand = new Random();
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
		TileMakerNext tile = (TileMakerNext) par1World.getTileEntity(par2, par3, par4);
		if (tile == null)
			return false;

		ItemStack tileItem = tile.getItemStack();// tileが保持しているアイテム
		int remain = tile.getRemain();// 残量
		ITeaRecipe tileRecipe = null;
		if (tileItem != null)
			RecipeRegisterManager.teaRecipe.getRecipe(tileItem);

		TeamakerRightClickEvent event = new TeamakerRightClickEvent(par5EntityPlayer, par2, par3, par4, tile, remain,
				tileRecipe);
		MinecraftForge.EVENT_BUS.post(event);

		if (event.isCanceled()) {
			return true;
		}

		ITeaRecipe recipe = null;// itemのほうのレシピ
		if (itemstack != null)
			recipe = RecipeRegisterManager.teaRecipe.getRecipe(itemstack);

		if (itemstack == null) {
			AMTLogger.debugInfo("Checking tile... ");
			if (tileItem != null)
				AMTLogger.debugInfo("tile hold item: " + tileItem.getDisplayName());
			if (tile.getOutput() != null)
				AMTLogger.debugInfo("tile hold recipe: " + tile.getOutput().getDisplayName());
			if (tile.getMilked())
				AMTLogger.debugInfo("milk recipe");
			AMTLogger.debugInfo("tile remaining: " + tile.getRemain());
			AMTLogger.debugInfo("tile texture: " + tile.getCurrentTexture());

			return DCsAppleMilk.debugMode;
		} else if (tileItem != null && remain > 0) // 空ではない
		{
			Item item = itemstack.getItem();
			int meta = itemstack.getItemDamage();

			if (item == Item.getItemFromBlock(DCsAppleMilk.emptyCup)) // 空マグで右クリックする処理を最優先に
			{
				if (tile.getOutput() != null) {
					ItemStack output = tile.getOutput();// アウトプットの取得。レシピ無しの場合nullを返すのでそのチェックも兼ねている
					if (output != null) {
						if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
							par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
									(ItemStack) null);
						}

						if (!par1World.isRemote) {
							EntityItem drop = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY,
									par5EntityPlayer.posZ, new ItemStack(output.getItem(), 1, output.getItemDamage()));
							par1World.spawnEntityInWorld(drop);
						}

						// 実績用処理
						if (output.getItem() == Item.getItemFromBlock(DCsAppleMilk.teacupBlock)
								&& output.getItemDamage() == 4) {
							par5EntityPlayer.triggerAchievement(AchievementRegister.getTea);
						} else if (output.getItem() == Item.getItemFromBlock(DCsAppleMilk.teaCup2)
								&& output.getItemDamage() == 3) {
							par5EntityPlayer.triggerAchievement(AchievementRegister.getAppleMilkTea);
						}

						tile.setRemain((byte) (remain - 1));
						if ((remain - 1) == 0) {
							tile.clearTile();
						}
						par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
						return true;
					}
				}
				return false;
			} else if (tileItem.getItem() == DCsAppleMilk.gratedApple && tileItem.getItemDamage() == 3
					&& DCsAppleMilk.SuccessLoadIC2 && LoadIC2Plugin.IC2Mug != null
					&& LoadIC2Plugin.IC2MugCoffee != null && itemstack.getItem() == LoadIC2Plugin.IC2Mug.getItem()) // IC2コーヒー
			{
				if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
					par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
							(ItemStack) null);
				}

				if (tile.getMilked()) {
					if (!par5EntityPlayer.inventory.addItemStackToInventory(LoadIC2Plugin.IC2MugCoffeeMilk.copy())) {
						if (!par1World.isRemote)
							par5EntityPlayer.entityDropItem(LoadIC2Plugin.IC2MugCoffeeMilk.copy(), 1);
					}
				} else {
					if (!par5EntityPlayer.inventory.addItemStackToInventory(LoadIC2Plugin.IC2MugCoffee.copy())) {
						if (!par1World.isRemote)
							par5EntityPlayer.entityDropItem(LoadIC2Plugin.IC2MugCoffee.copy(), 1);
					}
				}

				tile.setRemain((byte) (remain - 1));
				if ((remain - 1) == 0) {
					tile.clearTile();
				}

				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
				return true;
			} else if (recipe != null) // 手持ちアイテムが登録済みの茶の材料のとき
			{
				AMTLogger.debugInfo("Checking currentItem... ");
				boolean flag = false;
				String s = "null";
				if (recipe != null) {
					flag = true;
					s = recipe.getOutput().getDisplayName();
				}
				AMTLogger.debugInfo("This Item has recipe: " + flag + ", output: " + s);

				if (tile.getItemStack().getItem() == Items.milk_bucket) // ティーメーカーが牛乳のみのとき
				{
					AMTLogger.debugInfo("milk -> milk_drink");
					tile.setMilk(true);
					tile.setRecipe(new ItemStack(itemstack.getItem(), 1, itemstack.getItemDamage()));

					if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
						par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
								(ItemStack) null);
					}

					par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
					return true;
				} else // 牛乳以外
				{
					if (!tile.getMilked() && itemstack.getItem() == Items.milk_bucket) // 牛乳バケツを持っている
					{
						AMTLogger.debugInfo("drink -> milk_drink");

						if (recipe.getOutputMilk() != null) // ミルク追加可能として事前に登録済み
						{
							tile.setMilk(true);// ミルクフラグだけONに

							if (!par1World.isRemote) {
								EntityItem drop = new EntityItem(par1World, par5EntityPlayer.posX,
										par5EntityPlayer.posY, par5EntityPlayer.posZ, new ItemStack(Items.bucket, 1, 0));
								par1World.spawnEntityInWorld(drop);
							}
							if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
								par5EntityPlayer.inventory.setInventorySlotContents(
										par5EntityPlayer.inventory.currentItem, (ItemStack) null);
							}
							par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
							return true;
						} else {
							return false;
						}
					}
				}
			} else {
				return false;
			}
		} else // ティーメーカーが空の時
		{
			if (this.isEmptyCell(itemstack)) // 水を汲むための処理
			{
				this.getWater(itemstack, par5EntityPlayer);
				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
				return true;
			} else if (recipe != null) // 投入できる材料を持っている
			{
				AMTLogger.debugInfo("Checking currentItem... ");
				boolean flag = false;
				String s = "null";
				if (recipe != null) {
					flag = true;
					s = recipe.getOutput().getDisplayName();
				}
				AMTLogger.debugInfo("This Item has recipe: " + flag + ", output: " + s);

				if (itemstack.getItem() == Items.milk_bucket) {
					if (!par1World.isRemote) {
						EntityItem drop = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY,
								par5EntityPlayer.posZ, new ItemStack(Items.bucket, 1, 0));
						par1World.spawnEntityInWorld(drop);
					}
				}

				tile.setRecipe(new ItemStack(itemstack.getItem(), 1, itemstack.getItemDamage()));

				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
				if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
					par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
							(ItemStack) null);
				}
				return true;
			} else {
				return false;
			}

		}

		return false;
	}

	private void getWater(ItemStack itemstack, EntityPlayer par2EntityPlayer) {
		Item ID = itemstack.getItem();
		int IDm = itemstack.getItemDamage();
		ItemStack water = null;

		if (ID == Items.bucket) {
			water = new ItemStack(Items.water_bucket, 1, 0);
		} else if (ID == Items.glass_bottle) {
			water = new ItemStack(Items.potionitem, 1, 0);
		} else if (ID == Item.getItemFromBlock(DCsAppleMilk.emptyCup)) {
			water = new ItemStack(DCsAppleMilk.teacupBlock, 1, 0);
		} else if (DCsAppleMilk.SuccessLoadIC2 && LoadIC2Plugin.IC2Cell != null
				&& itemstack.getItem() == LoadIC2Plugin.IC2Cell.getItem()) {
			water = LoadIC2Plugin.IC2WaterCell.copy();
		}

		if (water != null) {

			if (!par2EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
				par2EntityPlayer.inventory.setInventorySlotContents(par2EntityPlayer.inventory.currentItem,
						(ItemStack) null);
			}

			if (!par2EntityPlayer.worldObj.isRemote) {
				EntityItem drop = new EntityItem(par2EntityPlayer.worldObj, par2EntityPlayer.posX,
						par2EntityPlayer.posY, par2EntityPlayer.posZ, water);
				par2EntityPlayer.worldObj.spawnEntityInWorld(drop);
			}
		}
	}

	private boolean isEmptyCell(ItemStack itemstack) {
		boolean flag;
		if (itemstack.getItem() == Items.bucket)
			flag = true;
		else if (itemstack.getItem() == Items.glass_bottle)
			flag = true;
		else if (itemstack.getItem() == Item.getItemFromBlock(DCsAppleMilk.emptyCup))
			flag = true;
		else if (DCsAppleMilk.SuccessLoadIC2 && LoadIC2Plugin.IC2Cell != null
				&& itemstack.getItem() == LoadIC2Plugin.IC2Cell.getItem())
			flag = true;
		else
			flag = false;

		return flag;
	}

	@Override
	public int damageDropped(int par1) {
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

	@Override
	public int getRenderType() {
		return DCsAppleMilk.modelMakerNext;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {

		return new TileMakerNext();
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}

	private void setDefaultDirection(World world, int x, int y, int z) {

		if (!world.isRemote) {
			Block var5 = world.getBlock(x, y, z - 1);
			Block var6 = world.getBlock(x, y, z + 1);
			Block var7 = world.getBlock(x - 1, y, z);
			Block var8 = world.getBlock(x + 1, y, z);
			byte var9 = 0;

			if (var5.func_149730_j() && !var6.func_149730_j()) {
				var9 = 0;
			}

			if (var6.func_149730_j() && !var5.func_149730_j()) {
				var9 = 1;
			}

			if (var7.func_149730_j() && !var8.func_149730_j()) {
				var9 = 2;
			}

			if (var8.func_149730_j() && !var7.func_149730_j()) {
				var9 = 3;
			}

			world.setBlockMetadataWithNotify(x, y, z, var9, 3);
		}
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		int playerFacing = MathHelper.floor_double((double) ((par5EntityLivingBase.rotationYaw * 4F) / 360F) + 0.5D) & 3;

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
			facing = 3;
		}

		par1World.setBlockMetadataWithNotify(par2, par3, par4, facing, 3);
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
		this.TeaMakerBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	public void TeaMakerBoundingBox(int par1) {
		float f = 0.3125F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		if (par1 == 1) {
			return this.blockIcon;
		} else {
			return Blocks.water.getBlockTextureFromSide(0);
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("defeatedcrow:porcelain");
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int i, int j, int k, int l) {
		TileMakerNext tile = (TileMakerNext) world.getTileEntity(i, j, k);
		if (tile != null) {
			return tile.getRemain();
		} else {
			return 0;
		}
	}
}
