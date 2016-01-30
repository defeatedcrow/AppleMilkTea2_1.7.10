package mods.defeatedcrow.common.block.plants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mods.defeatedcrow.api.plants.IRightClickHarvestable;
import mods.defeatedcrow.api.plants.PlantsClickEvent;
import mods.defeatedcrow.client.particle.EntityOrbFX;
import mods.defeatedcrow.client.particle.ParticleTex;
import mods.defeatedcrow.common.AchievementRegister;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockClamSand extends Block implements IRightClickHarvestable {

	private final int[] sideX = new int[] {
			1,
			-1,
			0,
			0 };
	private final int[] sideZ = new int[] {
			0,
			0,
			1,
			-1 };

	public BlockClamSand() {
		super(Material.ground);
		this.setStepSound(Block.soundTypeSand);
		this.setHardness(0.5F);
		this.setResistance(1.0F);
		this.setTickRandomly(true);
	}

	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
		super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);

		if (metadata == 2) {
			ret.add(new ItemStack(DCsAppleMilk.princessClam, 1, 0));
		} else {
			ret.add(new ItemStack(DCsAppleMilk.clam, 1, 0));
		}

		return ret;
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3) {
		return Item.getItemFromBlock(Blocks.sand);
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
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
				if (meta == 2)
					par5EntityPlayer.triggerAchievement(AchievementRegister.getPrincess);
				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
				return true;
			}
		}

		return false;
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		// 海・浜バイオームでは増殖しやすい
		BiomeGenBase biome = par1World.getBiomeGenForCoords(par2, par4);
		int r = 15;
		if (BiomeDictionary.isBiomeOfType(biome, Type.OCEAN) || BiomeDictionary.isBiomeOfType(biome, Type.BEACH)) {
			r = 5;
		}
		if (par1World.getBlockMetadata(par2, par3, par4) == 2) {
			r = 3;
		}

		if (!par1World.isRemote && par1World.rand.nextInt(r) == 0) {
			super.updateTick(par1World, par2, par3, par4, par5Random);

			// 周囲のハマグリ数のカウント
			int count = 0;
			boolean aroundPrincess = false;

			// 各方角ごとに、隣接1~3マス先までのハマグリ砂を探す。最大12カウント。
			for (int i = 0; i < 3; i++) {
				if (par1World.getBlock(par2 + 1 + i, par3, par4) == DCsAppleMilk.clamSand) {
					count++;
					if (par1World.getBlockMetadata(par2 + 1 + i, par3, par4) == 2)
						aroundPrincess = true;
				}
				if (par1World.getBlock(par2 - 1 - i, par3, par4) == DCsAppleMilk.clamSand) {
					count++;
					if (par1World.getBlockMetadata(par2 - 1 - i, par3, par4) == 2)
						aroundPrincess = true;
				}
				if (par1World.getBlock(par2, par3, par4 + 1 + i) == DCsAppleMilk.clamSand) {
					count++;
					if (par1World.getBlockMetadata(par2, par3, par4 + 1 + i) == 2)
						aroundPrincess = true;
				}
				if (par1World.getBlock(par2, par3, par4 - 1 - i) == DCsAppleMilk.clamSand) {
					count++;
					if (par1World.getBlockMetadata(par2, par3, par4 - 1 - i) == 2)
						aroundPrincess = true;
				}
			}

			if (aroundPrincess)// 姫ハマグリがいた
			{
				count = count / 4;// カウントを減
			}

			// AMTLogger.debugInfo("count: " + count);

			// メタデータ
			int meta = par1World.getBlockMetadata(par2, par3, par4);
			int chance = DCsConfig.clamChanceValue;
			int pr = Util.getPrincessChanceValue();

			// 増殖予定の座標選定
			int i = par1World.rand.nextInt(4);
			int Y1 = par3;
			int X1 = par2 + this.sideX[i];
			int Z1 = par4 + this.sideZ[i];

			// 座標が増殖可能な状態か？
			boolean flag3 = par1World.getBlock(X1, Y1 + 1, Z1).getMaterial() == Material.water;
			boolean flag4 = par1World.getBlock(X1, Y1, Z1) == Blocks.sand
					|| par1World.getBlock(X1, Y1, Z1) == DCsAppleMilk.clamSand;
			boolean flag2 = par1World.rand.nextInt(1 + chance) > par1World.rand.nextInt(1 + count);// どちらも乱数判断
			// AMTLogger.debugInfo("flag: " + flag3 + "/" + flag4 + "/" + flag2);

			String s = "Clam gen : " + X1 + "," + Y1 + "," + Z1 + ":";

			if (meta == 0)// ハマグリ
			{
				if (count < 6)// ふつうに増える。
				{
					if (flag2 && flag3 && flag4) {
						if (par1World.getBlockMetadata(X1, Y1, Z1) != 2) {
							par1World.setBlock(X1, Y1, Z1, DCsAppleMilk.clamSand);
							s += "normal";
							// AMTLogger.debugInfo(s);
						}

					}
				} else if (count < 9) {
					if (flag2 && flag3 && flag4) {
						if (par1World.getBlockMetadata(X1, Y1, Z1) != 2) {
							par1World.setBlock(X1, Y1, Z1, DCsAppleMilk.clamSand);
							s += "normal";
							// AMTLogger.debugInfo(s);
						}
					} else {
						// 自身が衰退ハマグリ砂になる
						par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
					}
				} else {
					if (flag2 && flag3 && flag4 && par1World.rand.nextInt(100) * 2 < pr)// 低確率
					{
						// プリンセス誕生
						par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 3);
						s += "princess gen";
						// AMTLogger.debugInfo(s);
					} else {
						// 自身が衰退ハマグリ砂になる
						par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
					}
				}
			} else if (meta == 1) {
				if (count < 4)// 減ってきた
				{
					// 復活する
					par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
				} else {
					// 消えてしまう
					if (!flag2)
						par1World.setBlock(par2, par3, par4, Blocks.sand);
				}
			} else if (meta == 2) {
				// 姫は無条件でハマグリを増やせる
				if (par1World.getBlockMetadata(X1, Y1, Z1) != 2 && flag3 && flag4) {
					par1World.setBlock(X1, Y1, Z1, DCsAppleMilk.clamSand);
				}
				s += "by princess";
				// AMTLogger.debugInfo(s);
			}

			int meta2 = par1World.getBlockMetadata(par2, par3, par4);// 結果のメタ
			// AMTLogger.debugInfo("after metadata: " + meta2);
		}

	}

	@Override
	public int tickRate(World par1World) {
		return 20;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		int l = par1World.getBlockMetadata(par2, par3, par4);
		Block i = par1World.getBlock(par2, par3 + 1, par2);
		double d0 = par2 + 0.5F;
		double d1 = par3 + 1.1F;
		double d2 = par4 + 0.5F;
		double d3 = 0.015D;
		double d4 = 0.27000001072883606D;

		boolean flag = par1World.getBlock(par2, par3 + 1, par4).getMaterial() == Material.water;

		int k = 0;

		if (l == 2) {
			for (int j = 1; j < 5; j++) {
				if (par1World.getBlock(par2, par3 + j, par4).getMaterial() == Material.water)
					k++;
			}

		}

		if (!DCsConfig.noRenderFoodsSteam) {
			if (l == 2) {
				EntityOrbFX cloud = new EntityOrbFX(par1World, d0, d1 + k, d2, 0.0D, d3, 0.0D);
				cloud.setParticleIcon(ParticleTex.getInstance().getIcon("orb"));
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
			}
			if (l == 0) {
				par1World.spawnParticle("bubble", d0, d1, d2, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(Item.getItemFromBlock(this), 1, 0));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		return 0x979797;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int par1) {
		return 0x979797;
	}

	@Override
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		return 0x979797;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		int l = world.getBlockMetadata(x, y, z);
		return l == 2 ? 15 : 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = Blocks.sand.getBlockTextureFromSide(1);

	}

	@Override
	public boolean onHarvest(World world, int x, int y, int z, IInventory inventory, ItemStack currentItem) {

		int meta = world.getBlockMetadata(x, y, z);

		ItemStack ret = this.getCropItem(meta);
		boolean flag = false;

		if (Util.notEmptyItem(ret) && (currentItem == null || currentItem.getItem() == ret.getItem())) {
			if (inventory instanceof InventoryPlayer) {
				InventoryPlayer playerInv = (InventoryPlayer) inventory;

				if (playerInv.addItemStackToInventory(ret)) {
					world.setBlock(x, y, z, Blocks.sand, 0, 3);
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
					world.setBlock(x, y, z, Blocks.sand, 0, 3);
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
				world.setBlock(x, y, z, Blocks.sand, 0, 3);
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isHarvestable(World world, int x, int y, int z) {
		return true;
	}

	@Override
	public ItemStack getCropItem(int blockMeta) {
		ItemStack ret = null;
		if (blockMeta == 2)
			ret = new ItemStack(DCsAppleMilk.princessClam, 1, 0);
		else
			ret = new ItemStack(DCsAppleMilk.clam, 1, 0);
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

	// ハマグリはどのメタデータでも収穫できる。暫定的に0を返す。
	@Override
	public int getGrownMetadata(World world, int x, int y, int z) {
		return 0;
	}

	@Override
	public int getInitialMetadata(World world, int x, int y, int z) {
		return 0;
	}

	@Override
	public int getGrownMetadata(int meta) {
		return 0;
	}

	@Override
	public int getInitialMetadata(int meta) {
		return 0;
	}

	@Override
	public Block getSaplingBlock(int meta) {
		return Blocks.sand;
	}

	@Override
	public int getSaplingMeta(int meta) {
		return 0;
	}
}
