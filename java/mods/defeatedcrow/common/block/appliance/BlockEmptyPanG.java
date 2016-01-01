package mods.defeatedcrow.common.block.appliance;

import java.util.Random;

import mods.defeatedcrow.api.appliance.SoupType;
import mods.defeatedcrow.api.recipe.IFondueSource;
import mods.defeatedcrow.api.recipe.IPanRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.client.particle.EntityDCCloudFX;
import mods.defeatedcrow.client.particle.ParticleTex;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.AchievementRegister;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.tile.appliance.TileFilledSoupPan;
import mods.defeatedcrow.common.tile.appliance.TilePanG;
import mods.defeatedcrow.handler.Util;
import mods.defeatedcrow.plugin.LoadBambooPlugin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEmptyPanG extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon boxTex;
	@SideOnly(Side.CLIENT)
	private IIcon chainTex;

	public BlockEmptyPanG() {
		super(Material.ground);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
		TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
		TilePanG tile = null;
		if (tileentity instanceof TilePanG) {
			tile = (TilePanG) tileentity;
		} else {
			return false;
		}

		if (itemstack == null || itemstack.getItem() == Item.getItemFromBlock(this)) {
			ItemStack drop = new ItemStack(DCsAppleMilk.emptyPanGaiden, 1, 0);

			ItemStack input = tile.getItemStack();
			byte rem = tile.getRemainByte();
			String disp = tile.getDisplayName();

			if (input != null) {
				NBTTagCompound tag = new NBTTagCompound();

				tag.setByte("remain", rem);
				tag.setString("display", disp);
				tag.setTag("input", input.writeToNBT(new NBTTagCompound()));

				drop.setTagCompound(tag);
			}

			if (!par1World.isRemote) {
				EntityItem entity = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY,
						par5EntityPlayer.posZ, drop);
				par1World.spawnEntityInWorld(entity);
			}

			par1World.removeTileEntity(par2, par3, par4);
			par1World.setBlockToAir(par2, par3, par4);
			par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
			return true;
		} else if (itemstack.getItem() == Items.bowl)// 木のボウル：ノーマルのoutputを返し、remainを1減らす。
		{
			if (tile.getOutput() != null && tile.getRemainByte() > 0) {
				ItemStack ret = tile.getOutput().copy();
				int rem = tile.getRemainByte() - 1;

				if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
					par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
							(ItemStack) null);
				}

				if (!par1World.isRemote) {
					EntityItem entity = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY,
							par5EntityPlayer.posZ, ret);
					par1World.spawnEntityInWorld(entity);
				}
				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
				if (ret.getItem() == Item.getItemFromBlock(DCsAppleMilk.bowlBlock)) {
					par5EntityPlayer.triggerAchievement(AchievementRegister.getSoup);
				}

				tile.setRemainByte((byte) rem);
				if (rem < 1) {
					tile.setItemStack(null);
					tile.setRemainByte((byte) 0);
					tile.markDirty();
					par1World.markBlockForUpdate(par2, par3, par4);
				}
			}
			return true;
		} else if (!LoadBambooPlugin.getBasket().isEmpty() && LoadBambooPlugin.isBasketItem(itemstack) >= 0)// 竹のボウル：和風のoutputを返し、remainを1減らす。
		{
			if (tile.getOutput() != null && tile.getRemainByte() > 0) {
				ItemStack ret = tile.getOutputJP().copy();
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setByte("BowlNum", (byte) LoadBambooPlugin.isBasketItem(itemstack));
				ret.setTagCompound(nbt);

				int rem = tile.getRemainByte() - 1;

				if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
					par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
							(ItemStack) null);
				}

				if (!par1World.isRemote) {
					EntityItem entity = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY,
							par5EntityPlayer.posZ, ret);
					par1World.spawnEntityInWorld(entity);
				}
				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);

				tile.setRemainByte((byte) rem);
				if (rem < 1) {
					tile.setItemStack(null);
					tile.setRemainByte((byte) 0);
					tile.markDirty();
					par1World.markBlockForUpdate(par2, par3, par4);
				}
			}
			return true;
		} else { // その他のアイテムはすべて材料判定に回す
			Item ID = itemstack.getItem();
			int IDm = itemstack.getItemDamage();

			if (this.onFurnace(par1World, par2, par3 - 1, par4) && tile.getItemStack() == null) {
				ItemStack input = new ItemStack(ID, 1, IDm);

				IPanRecipe recipe = RecipeRegisterManager.panRecipe.getRecipe(input);
				if (recipe != null) {
					tile.setItemStack(input);
					tile.setRemainByte((byte) 3);
					tile.markDirty();
					par1World.markBlockForUpdate(par2, par3, par4);

					if (!par5EntityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
						par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
								(ItemStack) null);
					}

					par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
					par5EntityPlayer.triggerAchievement(AchievementRegister.makeRice);
					return true;
				} else {
					if (par1World.isRemote)
						par5EntityPlayer.addChatMessage(new ChatComponentText(StatCollector
								.translateToLocal("dc.panMessage.noRecipe")));
					return true;
				}
			} else {
				if (!this.onFurnace(par1World, par2, par3 - 1, par4)) {
					if (par1World.isRemote)
						par5EntityPlayer.addChatMessage(new ChatComponentText(StatCollector
								.translateToLocal("dc.panMessage.noHeatSource")));
					return true;
				} else if (tile.getItemStack() != null) {
					if (par1World.isRemote)
						par5EntityPlayer.addChatMessage(new ChatComponentText(StatCollector
								.translateToLocal("dc.panMessage.alreadyHasRecipe")));
					return true;
				}
				return false;
			}
		}
	}

	// change soup type
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (world.isRemote || !this.onFurnace(world, x, y - 1, z) || entity == null)
			return;
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile == null || !(tile instanceof TilePanG))
			return;

		TilePanG pan = (TilePanG) tile;
		boolean flag = false;
		if (entity instanceof EntityItem) {
			ItemStack input = ((EntityItem) entity).getEntityItem();
			IFondueSource recipe = RecipeRegisterManager.fondueRecipe.getType(input);
			if (recipe != null && recipe.beforeType() == SoupType.EMPTY && recipe.matches(input)) {
				this.setSoupType(world, x, y, z, recipe.afterType());
				world.playSoundAtEntity(entity, "random.pop", 0.4F, 1.8F);

				ItemStack container = null;
				if (FluidContainerRegistry.isFilledContainer(input)) {
					container = FluidContainerRegistry.drainFluidContainer(input);
				} else {
					container = input.getItem().getContainerItem(input);
				}

				if (container != null) {
					EntityItem cont = new EntityItem(world, x, y + 1.0D, z, container);
					world.spawnEntityInWorld(cont);
				}

				if (input.stackSize > 1) {
					((EntityItem) entity).getEntityItem().stackSize--;
				} else {
					entity.setDead();
				}
			}
		}
	}

	public void setSoupType(World world, int x, int y, int z, SoupType type) {
		world.removeTileEntity(x, y, z);
		world.setBlock(x, y, z, DCsAppleMilk.filledSoupPan);
		TileFilledSoupPan tile2 = (TileFilledSoupPan) world.getTileEntity(x, y, z);
		tile2.setType(type);
		tile2.setRemainByte((byte) 8);
		world.playAuxSFX(2005, x, y, z, 0);
		world.markBlockForUpdate(x, y, z);
		AMTLogger.debugInfo("current type : " + type.display);
	}

	public boolean onFurnace(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);

		if (RecipeRegisterManager.panRecipe.isHeatSource(block, meta)) {
			return true;
		}
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
		return DCsAppleMilk.modelPan;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {

		return new TilePanG();
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		int playerFacing = MathHelper.floor_double((par5EntityLivingBase.rotationYaw * 4F) / 360F + 0.5D) & 3;

		boolean facing = false;
		if (playerFacing == 0) {
			facing = false;
		}
		if (playerFacing == 1) {
			facing = true;
		}
		if (playerFacing == 2) {
			facing = false;
		}
		if (playerFacing == 3) {
			facing = true;
		}

		// ItemStackのNBT
		NBTTagCompound nbt = par6ItemStack.getTagCompound();
		ItemStack input = null;
		byte rem = 0;

		if (nbt != null && nbt.hasKey("input")) {
			input = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("input"));
			rem = nbt.getByte("remain");
		}

		TileEntity tileEntity = par1World.getTileEntity(par2, par3, par4);
		if (tileEntity != null && tileEntity instanceof TilePanG) {
			TilePanG pan = (TilePanG) tileEntity;
			pan.setDirection(facing);
			pan.setItemStack(input);
			pan.setRemainByte(rem);
			pan.markDirty();
			par1World.markBlockForUpdate(par2, par3, par4);
		}
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
		TileEntity tile = par1World.getTileEntity(par2, par3, par4);

		if (tile instanceof TilePanG) {
			TilePanG tileentity = (TilePanG) tile;
			byte rem = tileentity.getRemainByte();
			ItemStack input = tileentity.getItemStack();
			String disp = tileentity.getDisplayName();

			ItemStack block = new ItemStack(this, 1, 0);

			float a = par1World.rand.nextFloat() * 0.8F + 0.1F;
			float a1 = par1World.rand.nextFloat() * 0.8F + 0.1F;
			float a2 = par1World.rand.nextFloat() * 0.8F + 0.1F;
			EntityItem drop = new EntityItem(par1World, par2 + a, par3 + a1, par4 + a2, block);

			if (input != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("remain", rem);
				tag.setString("display", disp);
				tag.setTag("input", input.writeToNBT(new NBTTagCompound()));
				drop.getEntityItem().setTagCompound(tag);
			}

			float a3 = 0.05F;
			drop.motionX = (float) par1World.rand.nextGaussian() * a3;
			drop.motionY = (float) par1World.rand.nextGaussian() * a3 + 0.2F;
			drop.motionZ = (float) par1World.rand.nextGaussian() * a3;
			par1World.spawnEntityInWorld(drop);
		}
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
		float f = 0.125F;
		this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, 0.5F, 1.0F - f);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par2;
		if (par1 == 1) {
			return this.boxTex;
		} else if (par1 == 0) {
			return this.chainTex;
		} else {
			return Blocks.water.getBlockTextureFromSide(0);
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister) {
		this.boxTex = par1IIconRegister.registerIcon("defeatedcrow:porcelain");
		this.chainTex = par1IIconRegister.registerIcon(Util.getTexturePassNoAlt() + "chain");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		int l = par1World.getBlockMetadata(par2, par3, par4);
		Block i = par1World.getBlock(par2, par3 - 1, par2);
		double d0 = par2 + 0.25F + par5Random.nextFloat() / 2;
		double d1 = par3 + par5Random.nextFloat();
		double d2 = par4 + 0.25F + par5Random.nextFloat() / 2;
		double d3 = 0.0199999988079071D;
		double d4 = 0.27000001072883606D;

		TilePanG tile = (TilePanG) par1World.getTileEntity(par2, par3, par4);
		boolean flag = false;

		if (tile != null) {
			flag = (tile.getRemainByte() > 0);
		}

		if (!DCsConfig.noRenderFoodsSteam && flag) {
			EntityDCCloudFX cloud = new EntityDCCloudFX(par1World, d0, d1, d2, 0.0D, d3, 0.0D);
			cloud.setParticleIcon(ParticleTex.getInstance().getIcon("cloud"));
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
		}
	}
}
