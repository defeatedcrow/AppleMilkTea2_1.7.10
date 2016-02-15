package mods.defeatedcrow.common.block;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.item.magic.ItemPrincessClam;
import mods.defeatedcrow.common.tile.TileCPanel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCPanel extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon windIcon;
	@SideOnly(Side.CLIENT)
	private IIcon moonIcon;

	public BlockCPanel() {
		super(Material.glass);
		this.setTickRandomly(true);
		this.setHardness(0.3F);
		this.setResistance(3.0F);
	}

	@Override
	public int getRenderType() {
		return DCsAppleMilk.modelCPanel;
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
	public boolean canProvidePower() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		int m = meta & 7;
		if (side == 1) {
			return m == 1 ? this.windIcon : (m == 2 ? this.moonIcon : this.blockIcon);
		} else {
			return this.blockIcon;
		}
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		ItemStack item = par5EntityPlayer.inventory.getCurrentItem();
		int currentMeta = par1World.getBlockMetadata(par2, par3, par4);
		TileCPanel tile = (TileCPanel) par1World.getTileEntity(par2, par3, par4);

		if (tile != null) {
			if (item != null && tile.getItemstack() == null) {
				if (item.getItem() == DCsAppleMilk.princessClam && item.getItemDamage() > 2) {
					if (item.getItemDamage() == 3)// wind
					{
						ItemStack put = item.copy();
						put.stackSize = 1;
						tile.setItemstack(put);
						--item.stackSize;
						par5EntityPlayer.inventory.markDirty();
						tile.markDirty();
						par1World.setBlockMetadataWithNotify(par2, par3, par4, currentMeta + 1, 3);
					} else if (item.getItemDamage() == 4)// moon
					{
						ItemStack put = item.copy();
						put.stackSize = 1;
						tile.setItemstack(put);
						--item.stackSize;
						par5EntityPlayer.inventory.markDirty();
						tile.markDirty();
						par1World.setBlockMetadataWithNotify(par2, par3, par4, currentMeta + 2, 3);
					}
				}
			} else {
				ItemStack get = tile.getItemstack();
				if (!par1World.isRemote && get != null) {
					String type = "None";
					String effect = "None";
					NBTTagCompound nbt = get.getTagCompound();

					if (get.getItemDamage() == 3) {
						type = "Wind";
						if (nbt != null) {
							byte m = nbt.getByte("mode");
							if (m == 1) {
								effect = nbt.getString("targetName");
							} else if (m == 2) {
								int X = nbt.getInteger("posX");
								int Y = nbt.getInteger("posY");
								int Z = nbt.getInteger("posZ");
								effect = X + ", " + Y + ", " + Z;
							}
						}
					} else if (get.getItemDamage() == 4) {
						type = "Moon";
						effect = "Warp on the ground";
					}

					par5EntityPlayer.addChatMessage(new ChatComponentText("Type : " + type));
					par5EntityPlayer.addChatMessage(new ChatComponentText("Effect : " + effect));
				}
			}
		}
		return true;
	}

	@Override
	public void setBlockBoundsForItemRender() {
		float f = 0.5F;
		float f1 = 0.125F;
		float f2 = 0.5F;
		this.setBlockBounds(0.5F - f, 0.5F - f1, 0.5F - f2, 0.5F + f, 0.5F + f1, 0.5F + f2);
	}

	@Override
	public int getMobilityFlag() {
		return 1;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
		this.setPlateBound(p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_));
	}

	protected void setPlateBound(int meta) {
		boolean flag = this.strengthAsMeta(meta) > 0;
		float f = 0.0625F;

		if (flag) {
			this.setBlockBounds(f, 0.0F, f, 1.0F - f, 0.03125F, 1.0F - f);
		} else {
			this.setBlockBounds(f, 0.0F, f, 1.0F - f, 0.0625F, 1.0F - f);
		}
	}

	@Override
	public int tickRate(World p_149738_1_) {
		return 20;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_,
			int p_149668_4_) {
		return null;
	}

	@Override
	public boolean getBlocksMovement(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_) {
		return true;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return world.doesBlockHaveSolidTopSurface(world, x, y - 1, z)
				|| BlockFence.func_149825_a(world.getBlock(x, y - 1, z));
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		boolean flag = false;

		if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z)
				&& !BlockFence.func_149825_a(world.getBlock(x, y - 1, z))) {
			flag = true;
		}

		if (flag) {
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote) {
			int i = this.strengthAsMeta(world.getBlockMetadata(x, y, z));

			if (i > 0) {
				this.updatePlate(world, x, y, z, i);
			}
		}
	}

	// このブロックのメインイベント。
	// 感圧板としてのアップデート+ワープ処理。
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (!world.isRemote) {
			int i = this.strengthAsMeta(world.getBlockMetadata(x, y, z));

			if (i == 0) {
				this.updatePlate(world, x, y, z, 0);
			}

			TileCPanel tile = (TileCPanel) world.getTileEntity(x, y, z);
			int meta = (world.getBlockMetadata(x, y, z) & 7);

			if (entity instanceof EntityPlayer && tile != null && meta > 0 && i > 0) {
				EntityPlayer player = (EntityPlayer) entity;
				if (meta == 1)// wind
				{
					ItemStack item = tile.getItemstack();
					if (item != null) {
						NBTTagCompound nbt = item.getTagCompound();
						if (nbt != null && nbt.hasKey("DCsCharm")) {
							byte m = nbt.getByte("DCsCharm");
							if (m == 1)// player
							{
								String name = nbt.getString("DCtargetName");
								this.formWindPlayerEffect(world, player, name);
							} else if (m == 2) {
								int X = nbt.getInteger("DCposX");
								int Y = nbt.getInteger("DCposY");
								int Z = nbt.getInteger("DCposZ");
								int dim = nbt.getInteger("DCdim");
								this.formWindPosEffect(world, player, X, Y, Z, dim);
							}
						}
					}

				} else if (meta == 2) {
					ItemStack item = tile.getItemstack();
					if (item != null) {
						this.formMoonEffect(world, x, y, z, player);
					}
				}

			}
		}
	}

	// ワープの実行処理
	private boolean formWindPlayerEffect(World world, EntityPlayer player, String targetName) {
		EntityPlayer target = world.getPlayerEntityByName(targetName);

		if (target != null) {
			int X = (int) target.posX;
			int Y = (int) target.posY;
			int Z = (int) target.posZ;
			String DimName = target.worldObj.provider.getDimensionName();
			AMTLogger.debugInfo(DimName + " : " + X + "," + Y + "," + Z);

			int x1 = 0;
			int y1 = 0;
			int z1 = 0;
			boolean flag = false;

			for (int i = 0; i < 3; i++) {
				for (int k = 0; k < 3; k++) {
					if (world.isAirBlock(X + i - 1, Y, Z + k - 1) && world.isAirBlock(X + i - 1, Y + 1, Z + k - 1)
							&& world.isSideSolid(X + i - 1, Y - 1, Z + k - 1, ForgeDirection.UP)) {
						x1 = X + i - 1;
						z1 = Z + k - 1;
						y1 = Y;
						flag = true;
					}
				}
			}

			if (flag) {
				AMTLogger.debugInfo("Checking... " + DimName + " : " + x1 + "," + y1 + "," + z1);

				boolean thisWorld = player.worldObj == world;
				boolean sameDim = target.worldObj.provider.getDimensionName().equalsIgnoreCase(
						world.provider.getDimensionName());

				if (thisWorld && sameDim) {
					AMTLogger.debugInfo("warp");

					player.setPositionAndUpdate(x1 + 0.5D, y1, z1 + 0.5D);
					player.fallDistance = 0.0F;
					world.playSoundAtEntity(player, "defeatedcrow:suzu", 1.0F, 1.2F);
					player.addChatMessage(new ChatComponentText("Succeeded to warp near the registered player : "
							+ target.getDisplayName()));

					return true;
				} else if (!sameDim) {
					player.addChatMessage(new ChatComponentText("Fail to warp near the registered player : "
							+ target.getDisplayName() + " is not alive in this dimention."));
				} else {
					player.addChatMessage(new ChatComponentText("Fail to get position of the registered player."));
				}
			} else {
				player.addChatMessage(new ChatComponentText("Fail to warp near the registered player : "
						+ "Fail to get position of around " + target.getDisplayName() + "."));
			}
		}
		return false;
	}

	private boolean formWindPosEffect(World world, EntityPlayer player, int x, int y, int z, int dim) {
		if (world.isSideSolid(x, y, z, ForgeDirection.UP) && world.provider.dimensionId == dim) {
			AMTLogger.debugInfo("warp");

			player.setPositionAndUpdate(x + 0.5D, y + 1, z + 0.5D);
			player.fallDistance = 0.0F;
			world.playSoundAtEntity(player, "defeatedcrow:suzu", 1.0F, 1.2F);

			return true;
		}
		return false;
	}

	private boolean formMoonEffect(World world, int x, int y, int z, EntityPlayer player) {
		int X = x;
		int Y = y;
		int Z = z;

		int y1 = Y;

		boolean flag = false;

		for (int i = 1; i < 128; i++) {
			if (Y + i < 1 || Y + i > 255) {
				break;
			}

			if (ItemPrincessClam.moonCanWarp(world, X, Y + i, Z)
					&& (world.isAirBlock(X, Y + i + 1, Z)
							|| world.getBlock(X, Y + i + 1, Z).getMaterial() == Material.plants || world.getBlock(X,
							Y + i + 1, Z).getMaterial() == Material.snow) && world.isAirBlock(X, Y + i + 2, Z)) {
				y1 = Y + i;
				flag = true;
				break;
			}
		}

		AMTLogger.debugInfo(X + "," + y1 + "," + Z);

		if (flag && y1 > Y) {
			if (player.worldObj == world) {
				AMTLogger.debugInfo("warp");

				player.setPositionAndUpdate(X + 0.5D, y1 + 1, Z + 0.5D);
				player.fallDistance = 0.0F;
				world.playSoundAtEntity(player, "defeatedcrow:suzu", 1.0F, 1.2F);
				return true;
			}
		}
		return false;
	}

	protected void updatePlate(World world, int x, int y, int z, int str) {
		int i1 = this.calcStrength(world, x, y, z);
		boolean flag = str > 0;
		boolean flag1 = i1 > 0;
		int meta = world.getBlockMetadata(x, y, z);

		if (!flag1 && flag) {
			world.setBlockMetadataWithNotify(x, y, z, (meta & 7), 2);
			this.notifyPlate(world, x, y, z);
			world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			world.playSoundEffect(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.3F, 0.5F);
		} else if (flag1 && !flag) {
			world.setBlockMetadataWithNotify(x, y, z, (meta | 8), 2);
			this.notifyPlate(world, x, y, z);
			world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			world.playSoundEffect(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.3F, 0.6F);
		}

		if (flag1) {
			world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
		}
	}

	protected AxisAlignedBB func_150061_a(int x, int y, int z) {
		float f = 0.125F;
		return AxisAlignedBB.getBoundingBox(x + f, y, z + f, x + 1 - f, y + 0.25D, z + 1 - f);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		if (this.strengthAsMeta(meta) > 0) {
			this.notifyPlate(world, x, y, z);
		}

		TileCPanel tile = (TileCPanel) world.getTileEntity(x, y, z);
		if (tile != null) {
			ItemStack drop = tile.getItemstack();
			if (drop != null) {
				float f = world.rand.nextFloat() * 0.8F + 0.1F;
				float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
				float f2 = world.rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityitem = new EntityItem(world, x + f, y + f1, z + f2, drop.copy());

				if (drop.hasTagCompound()) {
					entityitem.getEntityItem().setTagCompound((NBTTagCompound) drop.getTagCompound().copy());
				}

				float f3 = 0.05F;
				entityitem.motionX = (float) world.rand.nextGaussian() * f3;
				entityitem.motionY = (float) world.rand.nextGaussian() * f3 + 0.2F;
				entityitem.motionZ = (float) world.rand.nextGaussian() * f3;
				world.spawnEntityInWorld(entityitem);
			}
		}

		super.breakBlock(world, x, y, z, block, meta);
	}

	protected void notifyPlate(World world, int x, int y, int z) {
		world.notifyBlocksOfNeighborChange(x, y, z, this);
		world.notifyBlocksOfNeighborChange(x, y - 1, z, this);
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int p_149709_5_) {
		return this.strengthAsMeta(world.getBlockMetadata(x, y, z));
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int meta) {
		return this.strengthAsMeta(world.getBlockMetadata(x, y, z));
	}

	protected int calcStrength(World world, int x, int y, int z) {
		List list = null;
		list = world.getEntitiesWithinAABB(EntityPlayer.class, this.func_150061_a(x, y, z));

		if (list != null && !list.isEmpty()) {
			Iterator iterator = list.iterator();

			while (iterator.hasNext()) {
				Entity entity = (Entity) iterator.next();

				if (!entity.doesEntityNotTriggerPressurePlate()) {
					return 15;
				}
			}
		}

		return 0;
	}

	private int strengthAsMeta(int meta) {
		return meta > 7 ? 15 : 0;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileCPanel();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("defeatedcrow:chalcedony");
		this.windIcon = par1IconRegister.registerIcon("defeatedcrow:raden_wing");
		this.moonIcon = par1IconRegister.registerIcon("defeatedcrow:raden_moon");
	}

}
