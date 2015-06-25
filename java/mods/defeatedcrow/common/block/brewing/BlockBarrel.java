package mods.defeatedcrow.common.block.brewing;

import java.util.Random;

import mods.defeatedcrow.common.AchievementRegister;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.TileBrewingBarrel;
import mods.defeatedcrow.handler.Util;
import mods.defeatedcrow.recipe.BrewingRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/*
 * 基本仕様はCordialの流用で、但し外見から熟成段階が見えない。
 * Cordialとの違いは、樽のままではレシピに使用できず、いったん瓶に移さないとならない点である。
 */
public class BlockBarrel extends BlockContainer {

	public BlockBarrel() {
		super(Material.wood);
		this.setStepSound(Block.soundTypeWood);
		this.setHardness(0.3F);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		ItemStack item = par5EntityPlayer.inventory.getCurrentItem();
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		TileBrewingBarrel tile = (TileBrewingBarrel) par1World.getTileEntity(par2, par3, par4);

		if (tile == null) {
			return true;
		}

		if (Util.notEmptyItem(item) && tile.getAged()) {
			if (tile.productTank.isEmpty()) {
				tile.setAged(false);
				tile.setAgingStage(0);
				return true;
			}

			FluidStack fluid = tile.productTank.getFluid().copy();
			int drainAmount = 0;
			ItemStack ret = null;

			if (item.getItem() == Items.bucket && fluid.amount > 1000)// バケツ
			{
				ret = FluidContainerRegistry.fillFluidContainer(new FluidStack(fluid.getFluid(), 1000), new ItemStack(
						Items.bucket));
				if (Util.notEmptyItem(ret))
					drainAmount = 1000;
			} else if (item.getItem() == Item.getItemFromBlock(DCsAppleMilk.emptyBottle) && fluid.amount > 200)// ビン
			{
				ret = FluidContainerRegistry.fillFluidContainer(new FluidStack(fluid.getFluid(), 200), new ItemStack(
						DCsAppleMilk.emptyBottle));
				if (Util.notEmptyItem(ret))
					drainAmount = 200;
			} else {
				ret = FluidContainerRegistry.fillFluidContainer(new FluidStack(fluid.getFluid(), 1000), new ItemStack(
						item.getItem(), 1, item.getItemDamage()));
				if (Util.notEmptyItem(ret))
					drainAmount = 1000;
			}

			if (Util.notEmptyItem(ret) && drainAmount > 0) {
				ItemStack result = new ItemStack(ret.getItem(), 1, ret.getItemDamage());
				if (tile.productTank.drain(drainAmount, true) != null) {
					if (!par5EntityPlayer.capabilities.isCreativeMode && --item.stackSize <= 0) {
						par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
								(ItemStack) null);
					}

					if (!par1World.isRemote) {
						EntityItem drop = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY,
								par5EntityPlayer.posZ, result);
						par1World.spawnEntityInWorld(drop);
					}
					par5EntityPlayer.inventory.markDirty();
					par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);

					// 実績
					if (ret.getItem() == DCsAppleMilk.itemLargeBottle) {
						par5EntityPlayer.triggerAchievement(AchievementRegister.getAlcohol);
					}
					return true;
				}
			}

			return true;
		} else if (Util.notEmptyItem(item) && tile.productTank.isEmpty()) {
			if (FluidContainerRegistry.isFilledContainer(item)) {
				FluidStack input = FluidContainerRegistry.getFluidForFilledItem(item);
				if (input != null) {
					if (BrewingRecipe.recipe.containsKey(input.getFluid())) {
						ItemStack ret = FluidContainerRegistry.drainFluidContainer(item);
						Fluid fluid = input.getFluid();
						int amount = input.amount;

						tile.setAged(false);
						tile.setAgingStage(0);

						if (Util.notEmptyItem(ret) && tile.productTank.fill(new FluidStack(fluid, amount), true) > 0) {
							if (!par5EntityPlayer.capabilities.isCreativeMode && --item.stackSize <= 0) {
								par5EntityPlayer.inventory.setInventorySlotContents(
										par5EntityPlayer.inventory.currentItem, (ItemStack) null);
							}

							if (!par1World.isRemote) {
								EntityItem drop = new EntityItem(par1World, par5EntityPlayer.posX,
										par5EntityPlayer.posY, par5EntityPlayer.posZ, ret.copy());
								par1World.spawnEntityInWorld(drop);
							}

							par5EntityPlayer.inventory.markDirty();
							par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
							return true;
						}
					} else {
						par5EntityPlayer
								.addChatMessage(new ChatComponentText("This fluid can not brew in this barrel."));
					}
				}
			}
			return true;
		} else if (Util.notEmptyItem(item) && DCsAppleMilk.debugMode && item.getItem() == DCsAppleMilk.chalcedonyKnife) {
			tile.setAgingStage(4);
			return true;
		} else {
			if (!par1World.isRemote) {
				int age = tile.getAgingStage();
				boolean flag = tile.getAged();
				boolean flag2 = tile.isDryBiome() || par1World.canBlockSeeTheSky(par2, par3, par4);

				if (!tile.productTank.isEmpty()) {
					String type = "empty";
					type = tile.productTank.getFluidName();
					String s = "Brewing Barrel : ";
					if (flag) {
						s = s + "fermented";
					} else {
						s = s + "passed " + age + " days";
					}
					par5EntityPlayer.addChatMessage(new ChatComponentText(s));
					par5EntityPlayer.addChatMessage(new ChatComponentText("Type : " + type));
				}

				if (flag2) {
					par5EntityPlayer.addChatMessage(new ChatComponentText("This place is not suitable for brewing."));
				}
			}
			return true;
		}
	}

	// 設置
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		short l = (short) par6ItemStack.getItemDamage();
		int playerFacing = MathHelper.floor_double((double) ((par5EntityLivingBase.rotationYaw * 4F) / 360F) + 0.5D) & 3;

		// 向き変更用のフラグ
		boolean facing = false;
		if (playerFacing == 1 || playerFacing == 3) {
			facing = false;
		} else {
			facing = true;
		}

		TileBrewingBarrel tile = (TileBrewingBarrel) par1World.getTileEntity(par2, par3, par4);
		if (tile != null) {
			tile.setSide(facing);
		}
	}

	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		return false;
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
		return DCsAppleMilk.modelDial;
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
		float f = 0.125F;
		this.setBlockBounds(0.0F, 0.0F + f, 0.0F, 1.0F, 1.0F - f, 1.0F);
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister) {
		this.blockIcon = par1IIconRegister.registerIcon("defeatedcrow:barrel");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileBrewingBarrel();
	}
}
