package mods.defeatedcrow.common.block.plants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import mods.defeatedcrow.api.plants.IRightClickHarvestable;
import mods.defeatedcrow.api.plants.PlantsClickEvent;
import mods.defeatedcrow.common.AchievementRegister;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 柚子の葉ブロック。 <br>
 * メタデータ&3が、0:採取後の葉、1:自然生成時の葉、2:花つき、3:実つき。 <br>
 * 右クリックで実を採取したあと、メタデータが0になることで再採集までのインターバルを作る。
 */
public class BlockYuzuLeaves extends BlockLeavesBase implements IShearable, IRightClickHarvestable {
	int[] around;
	@SideOnly(Side.CLIENT)
	protected int graphicsLevel;
	protected IIcon[] leavesIcon;
	private static final String[] type = new String[] { "yuzu" };

	public BlockYuzuLeaves() {
		super(Material.leaves, false);
		this.setTickRandomly(true);
		this.setCreativeTab(DCsAppleMilk.applemilk);
		this.setHardness(0.1F);
		this.setLightOpacity(1);
		this.setStepSound(soundTypeGrass);

		this.field_150121_P = true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
		p_149666_3_.add(new ItemStack(p_149666_1_, 1, 3));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "leaves_yuzu_0");
		this.leavesIcon = new IIcon[3];

		for (int i = 0; i < 3; ++i) {
			if (i == 2 && DCsAppleMilk.CAL.get(Calendar.MONTH) == 3 && DCsAppleMilk.CAL.get(Calendar.DATE) == 1) {
				this.leavesIcon[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "leaves_yuzu_" + i
						+ "_4_1");
			} else {
				this.leavesIcon[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "leaves_yuzu_" + i);
			}
		}

	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
		Block block = par1World.getBlock(par2, par3, par4);
		int meta = par1World.getBlockMetadata(par2, par3, par4);

		// event
		PlantsClickEvent event = new PlantsClickEvent(par1World, par5EntityPlayer, itemstack, block, this, meta, par2,
				par3, par4);

		MinecraftForge.EVENT_BUS.post(event);

		if (event.hasResult() && event.getResult() == Result.ALLOW) {
			par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
			return true;
		}

		if (event.isCanceled()) {
			return false;
		}

		InventoryPlayer inventory = par5EntityPlayer.inventory;

		if (inventory != null) {
			ItemStack currentItem = inventory.getCurrentItem();
			if (this.onHarvest(par1World, par2, par3, par4, inventory, currentItem)) {
				par5EntityPlayer.triggerAchievement(AchievementRegister.getYuzu);
				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
				return true;
			}
		}

		return false;
	}

	/* === 以下、Leaves用メソッド === */

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5Block, int meta) {
		byte b0 = 1;
		int i1 = b0 + 1;

		if (world.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1)) {
			for (int j1 = -b0; j1 <= b0; ++j1) {
				for (int k1 = -b0; k1 <= b0; ++k1) {
					for (int l1 = -b0; l1 <= b0; ++l1) {
						Block block = world.getBlock(x + j1, y + k1, z + l1);
						if (block.isLeaves(world, x + j1, y + k1, z + l1)) {
							block.beginLeavesDecay(world, x + j1, y + k1, z + l1);
						}
					}
				}
			}
		}
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote) {
			int l = world.getBlockMetadata(x, y, z);

			int newMeta = l;

			// バニラ葉の動作そのまま
			if ((l & 8) != 0 && (l & 4) == 0)// decay判定
			{
				byte b0 = 4;
				int i1 = b0 + 1;
				byte b1 = 32;
				int j1 = b1 * b1;
				int k1 = b1 / 2;

				if (this.around == null) {
					this.around = new int[b1 * b1 * b1];
				}

				int l1;

				if (world.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1)) {
					int i2;
					int j2;

					for (l1 = -b0; l1 <= b0; ++l1) {
						for (i2 = -b0; i2 <= b0; ++i2) {
							for (j2 = -b0; j2 <= b0; ++j2) {
								Block block = world.getBlock(x + l1, y + i2, z + j2);

								if (!block.canSustainLeaves(world, x + l1, y + i2, z + j2)) {
									if (block.isLeaves(world, x + l1, y + i2, z + j2)) {
										this.around[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
									} else {
										this.around[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
									}
								} else {
									this.around[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
								}
							}
						}
					}

					for (l1 = 1; l1 <= 4; ++l1) {
						for (i2 = -b0; i2 <= b0; ++i2) {
							for (j2 = -b0; j2 <= b0; ++j2) {
								for (int k2 = -b0; k2 <= b0; ++k2) {
									if (this.around[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] == l1 - 1) {
										if (this.around[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2) {
											this.around[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
										}

										if (this.around[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2) {
											this.around[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
										}

										if (this.around[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] == -2) {
											this.around[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] = l1;
										}

										if (this.around[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2) {
											this.around[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;
										}

										if (this.around[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] == -2) {
											this.around[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] = l1;
										}

										if (this.around[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] == -2) {
											this.around[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
										}
									}
								}
							}
						}
					}
				}

				l1 = this.around[k1 * j1 + k1 * b1 + k1];

				newMeta = l & -9;

				if (l1 >= 0) {
					world.setBlockMetadataWithNotify(x, y, z, newMeta, 4);
				} else {
					this.removeLeaves(world, x, y, z);
				}
			}

			// 柚子の葉の成長
			int type = newMeta & 3;
			if (type < 3) {
				if (type == 0) {
					if (rand.nextInt(40) == 0) {
						world.setBlockMetadataWithNotify(x, y, z, newMeta + 1, 3);
					}
				} else {
					if (rand.nextInt(15) == 0) {
						world.setBlockMetadataWithNotify(x, y, z, newMeta + 1, 3);
					}
				}
			}
		}
	}

	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_,
			Random p_149734_5_) {
		if (p_149734_1_.canLightningStrikeAt(p_149734_2_, p_149734_3_ + 1, p_149734_4_)
				&& !World.doesBlockHaveSolidTopSurface(p_149734_1_, p_149734_2_, p_149734_3_ - 1, p_149734_4_)
				&& p_149734_5_.nextInt(15) == 1) {
			double d0 = p_149734_2_ + p_149734_5_.nextFloat();
			double d1 = p_149734_3_ - 0.05D;
			double d2 = p_149734_4_ + p_149734_5_.nextFloat();
			p_149734_1_.spawnParticle("dripWater", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	private void removeLeaves(World world, int x, int y, int z) {
		this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
		world.setBlockToAir(x, y, z);
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random rand) {
		return rand.nextInt(1) == 0 ? 1 : 0;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Item.getItemFromBlock(DCsAppleMilk.saplingYuzu);
	}

	/**
	 * Drops the block items with a specified chance of dropping the specified items
	 */
	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int p_149690_5_, float p_149690_6_,
			int p_149690_7_) {
		super.dropBlockAsItemWithChance(world, x, y, z, p_149690_5_, 1.0f, p_149690_7_);
	}

	protected int func_150123_b(int meta) {
		return meta == 3 ? 2 : 10;
	}

	/**
	 * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the
	 * coordinates of the
	 * block and l is the block's subtype/damage.
	 */
	@Override
	public void harvestBlock(World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_,
			int p_149636_5_, int p_149636_6_) {
		{
			super.harvestBlock(p_149636_1_, p_149636_2_, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_);
		}
	}

	/**
	 * Determines the damage on the item the block drops. Used in cloth and wood.
	 */
	@Override
	public int damageDropped(int p_149692_1_) {
		return super.damageDropped(p_149692_1_) & 3;
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether or not to render the
	 * shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this
	 * block.
	 */
	@Override
	public boolean isOpaqueCube() {
		return !this.field_150121_P;
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int meta = par2 & 3;
		return meta == 0 ? this.leavesIcon[0] : this.leavesIcon[meta - 1];
	}

	/**
	 * Pass true to draw this block using fancy graphics, or false for fast graphics.
	 */
	@SideOnly(Side.CLIENT)
	public void setGraphicsLevel(boolean p_150122_1_) {
		this.field_150121_P = p_150122_1_;
		this.graphicsLevel = p_150122_1_ ? 0 : 1;
	}

	/**
	 * Returns an item stack containing a single instance of the current block type. 'i' is the
	 * block's subtype/damage
	 * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested
	 * should return null.
	 */
	@Override
	protected ItemStack createStackedBlock(int p_149644_1_) {
		return new ItemStack(Item.getItemFromBlock(this), 1, p_149644_1_ & 3);
	}

	public String[] func_150125_e() {
		return this.type;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 3));
		return ret;
	}

	@Override
	public void beginLeavesDecay(World world, int x, int y, int z) {

		int i2 = world.getBlockMetadata(x, y, z);

		if ((i2 & 8) == 0) {
			world.setBlockMetadataWithNotify(x, y, z, i2 | 8, 4);
		}
		world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 4);
	}

	@Override
	public boolean isLeaves(IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		int chance = this.func_150123_b(metadata);

		if (fortune > 0) {
			chance -= 2 << fortune;
			if (chance < 2)
				chance = 2;
		}

		if (world.rand.nextInt(chance) == 0)
			ret.add(new ItemStack(this.getItemDropped(metadata, world.rand, fortune), 1, this.damageDropped(metadata)));

		chance = (metadata & 3) == 3 ? 10 : 50;
		if (fortune > 0) {
			chance -= 10 << fortune;
			if (chance < 2)
				chance = 2;
		}

		if (world.rand.nextInt(chance) == 0)
			ret.add(new ItemStack(DCsAppleMilk.leafTea, 1, 3));

		return ret;
	}

	@Override
	public boolean onHarvest(World world, int x, int y, int z, IInventory inventory, ItemStack currentItem) {

		int meta = world.getBlockMetadata(x, y, z);
		if ((meta & 3) != 3)
			return false;

		ItemStack ret = this.getCropItem(meta);
		boolean flag = false;

		if (Util.notEmptyItem(ret) && (currentItem == null || currentItem.getItem() == ret.getItem())) {
			if (inventory instanceof InventoryPlayer) {
				InventoryPlayer playerInv = (InventoryPlayer) inventory;

				if (playerInv.addItemStackToInventory(ret)) {
					world.setBlockMetadataWithNotify(x, y, z, meta - 3, 3);
					playerInv.markDirty();
					return true;
				} else
					flag = true;
			} else if (inventory != null) {
				int slot = this.getAddSlot(inventory, ret);
				if (slot > -1) {
					if (inventory.getStackInSlot(slot) == null) {
						inventory.setInventorySlotContents(slot, ret);
					} else {
						++inventory.getStackInSlot(slot).stackSize;
					}
					world.setBlockMetadataWithNotify(x, y, z, meta - 3, 3);
					return true;
				} else {
					flag = true;
				}
			} else {
				flag = true;
			}
		}

		if (flag) {
			float a = world.rand.nextFloat() * 0.8F + 0.1F;
			float a1 = world.rand.nextFloat() * 0.8F + 0.1F;
			float a2 = world.rand.nextFloat() * 0.8F + 0.1F;
			EntityItem drop = new EntityItem(world, x + a, y + a1, z + a2, ret);
			drop.motionY = 0.25F;

			if (!world.isRemote && world.spawnEntityInWorld(drop)) {
				world.setBlockMetadataWithNotify(x, y, z, 0, 3);
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isHarvestable(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z) & 3;
		return meta == 3;
	}

	@Override
	public ItemStack getCropItem(int blockMeta) {
		ItemStack ret = null;
		if ((blockMeta & 3) == 3)
			ret = new ItemStack(DCsAppleMilk.leafTea, 1, 3);
		return ret;
	}

	private int getAddSlot(IInventory inventory, ItemStack get) {
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			if (inventory.getStackInSlot(i) == null) {
				return i;
			} else if (Util.notEmptyItem(get)) {
				ItemStack cur = inventory.getStackInSlot(i);
				if (get.getItem() == cur.getItem() && get.getItemDamage() == cur.getItemDamage()
						&& cur.stackSize < cur.getMaxStackSize()) {
					return i;
				}
			}
		}
		return -1;
	}

	@Override
	public int getGrownMetadata(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if (meta < 4)
			return 3;
		else if (meta < 8)
			return 7;
		else if (meta < 12)
			return 11;
		else
			return 15;
	}

	@Override
	public int getInitialMetadata(World world, int x, int y, int z) {
		return 0;
	}

	@Override
	public int getGrownMetadata(int meta) {
		if (meta < 4)
			return 3;
		else if (meta < 8)
			return 7;
		else if (meta < 12)
			return 11;
		else
			return 15;
	}

	@Override
	public int getInitialMetadata(int meta) {
		return 0;
	}

	@Override
	public Block getSaplingBlock(int meta) {
		return DCsAppleMilk.saplingYuzu;
	}

	@Override
	public int getSaplingMeta(int meta) {
		return 0;
	}
}
