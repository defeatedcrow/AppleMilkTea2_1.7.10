package mods.defeatedcrow.common.block;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import mods.defeatedcrow.api.charm.EffectType;
import mods.defeatedcrow.api.charm.IIncenseEffect;
import mods.defeatedcrow.api.events.AMTBlockRightClickEvent;
import mods.defeatedcrow.client.particle.EntityDCCloudFX;
import mods.defeatedcrow.client.particle.ParticleTex;
import mods.defeatedcrow.common.AchievementRegister;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.tile.TileIncenseBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * インセンスアイテムのアップデート処理はここにある。
 */
public class BlockIncenseBase extends BlockContainer {

	public BlockIncenseBase() {
		super(Material.circuits);
		this.setStepSound(Block.soundTypeGlass);
		this.setHardness(0.2F);
		this.setResistance(3.0F);
	}

	// 右クリック処理
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		TileIncenseBase tile = (TileIncenseBase) par1World.getTileEntity(par2, par3, par4);

		AMTBlockRightClickEvent event = new AMTBlockRightClickEvent(par1World, par5EntityPlayer, itemstack, par2, par3,
				par4);
		MinecraftForge.EVENT_BUS.post(event);

		if (event.isCanceled()) {
			return true;
		}

		if (itemstack == null)// 回収動作
		{
			if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(this, 1))) {
				if (!par1World.isRemote)
					par5EntityPlayer.entityDropItem(new ItemStack(this, 1), 1);
			}

			par1World.setBlockToAir(par2, par3, par4);
			par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
			return true;
		} else if (itemstack.getItem() == Item.getItemFromBlock(this)) {
			if (!par1World.isRemote) {
				EntityItem entity = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY,
						par5EntityPlayer.posZ, new ItemStack(this, 1));
				par1World.spawnEntityInWorld(entity);
			}

			par1World.setBlockToAir(par2, par3, par4);
			par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
			return true;
		} else if (itemstack.getItem() instanceof IIncenseEffect)// お香アイテムの場合
		{
			if (tile != null && !tile.hasItem()) {
				ItemStack put = itemstack.copy();
				tile.setItemstack(put);
				if (!par5EntityPlayer.capabilities.isCreativeMode && (itemstack.stackSize - put.stackSize) <= 0) {
					par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
							(ItemStack) null);
				}
				tile.markDirty();
				par5EntityPlayer.inventory.markDirty();
				par1World.markBlockForUpdate(par2, par3, par4);
				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
				return true;
			}
			return false;
		} else if (itemstack.getItem() == Items.flint_and_steel || itemstack.getItem() == DCsAppleMilk.firestarter) {
			if (tile != null && tile.hasItem() && !tile.getActive()) {
				par5EntityPlayer.triggerAchievement(AchievementRegister.useIncense);
				itemstack.attemptDamageItem(1, par1World.rand);
				tile.setActive();
				tile.markDirty();
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
				par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate(par1World));
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	// レンダーまわり
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
		return DCsAppleMilk.modelIncenseBase;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileIncenseBase();
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
		this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.6F, 0.8F);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	// ブロックのドロップ時に中身を落とす
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileIncenseBase tile = (TileIncenseBase) world.getTileEntity(x, y, z);
		if (tile != null) {
			ItemStack drop = tile.getItemstack();
			ItemStack ash = tile.getAsh();
			if (drop != null) {
				float f = world.rand.nextFloat() * 0.8F + 0.1F;
				float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
				float f2 = world.rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1),
						(double) ((float) z + f2), drop.copy());

				if (drop.hasTagCompound()) {
					entityitem.getEntityItem().setTagCompound((NBTTagCompound) drop.getTagCompound().copy());
				}

				float f3 = 0.05F;
				entityitem.motionX = (double) ((float) world.rand.nextGaussian() * f3);
				entityitem.motionY = (double) ((float) world.rand.nextGaussian() * f3 + 0.2F);
				entityitem.motionZ = (double) ((float) world.rand.nextGaussian() * f3);
				world.spawnEntityInWorld(entityitem);
			}
			if (ash != null) {
				float f = world.rand.nextFloat() * 0.8F + 0.1F;
				float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
				float f2 = world.rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityitem2 = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1),
						(double) ((float) z + f2), ash.copy());

				if (ash.hasTagCompound()) {
					entityitem2.getEntityItem().setTagCompound((NBTTagCompound) ash.getTagCompound().copy());
				}

				float f3 = 0.05F;
				entityitem2.motionX = (double) ((float) world.rand.nextGaussian() * f3);
				entityitem2.motionY = (double) ((float) world.rand.nextGaussian() * f3 + 0.2F);
				entityitem2.motionZ = (double) ((float) world.rand.nextGaussian() * f3);
				world.spawnEntityInWorld(entityitem2);
			}
		}

		super.breakBlock(world, x, y, z, block, meta);
	}

	// パーティクルと光
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta > 0 ? 6 : 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister) {
		this.blockIcon = par1IIconRegister.registerIcon("defeatedcrow:chalcedony");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		int l = par1World.getBlockMetadata(par2, par3, par4);
		Block i = par1World.getBlock(par2, par3 - 1, par2);
		boolean b = false;

		// パーティクルの設定
		String FXName = "blink";
		float R = 1.0F;
		float G = 1.0F;
		float B = 1.0F;

		TileIncenseBase tile = (TileIncenseBase) par1World.getTileEntity(par2, par3, par4);
		if (tile != null) {
			b = tile.getActive();
			if (b) {
				if (tile.hasItem() && tile.getItemstack().getItem() instanceof IIncenseEffect) {
					IIncenseEffect incense = (IIncenseEffect) tile.getItemstack().getItem();
					FXName = incense.particleIcon();
					R = incense.particleColorR();
					G = incense.particleColorG();
					B = incense.particleColorB();
				}
			}
		}

		double d0 = (double) ((float) par2 + par5Random.nextFloat());
		double d1 = (double) ((float) par3 - 0.4F + par5Random.nextFloat());
		double d2 = (double) ((float) par4 + par5Random.nextFloat());
		double d3 = 0.0099999988079071D;
		double d4 = 0.0099999988079071D;
		double d5 = 0.0099999988079071D;

		if (!DCsConfig.noRenderFoodsSteam && b) {
			EntityDCCloudFX cloud = new EntityDCCloudFX(par1World, d0, d1, d2, 0.0D, d4, 0.0D);
			cloud.setParticleIcon(ParticleTex.getInstance().getIcon(FXName));
			cloud.setRBGColorF(R, G, B);
			cloud.setAlphaF(0.75F);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
		}
	}

	// アップデート処理
	@Override
	public int tickRate(World p_149738_1_) {
		return 20;// 1秒毎の間隔で処理を実行。
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote) {
			TileIncenseBase tile = (TileIncenseBase) world.getTileEntity(x, y, z);
			boolean active = tile.getActive();

			if (active) {
				ItemStack item = tile.getItemstack();
				if (item != null && item.getItem() instanceof IIncenseEffect) {
					IIncenseEffect effect = (IIncenseEffect) item.getItem();

					EffectType type = effect.getEffectType();
					int area = effect.effectAreaRange();

					if (type == EffectType.Player) {
						List list = this.searchPlayer(world, x, y, z, area);

						if (list != null && !list.isEmpty()) {
							Iterator iterator = list.iterator();

							while (iterator.hasNext()) {
								Entity entity = (Entity) iterator.next();

								if (entity instanceof EntityLivingBase) {
									EntityLivingBase target = (EntityLivingBase) entity;

									effect.formEffect(world, x, y, z, target, effect);
								}
							}
						}
					} else if (type == EffectType.EntityLiving) {
						List list = this.searchEntity(world, x, y, z, area);

						if (list != null && !list.isEmpty()) {
							Iterator iterator = list.iterator();

							while (iterator.hasNext()) {
								Entity entity = (Entity) iterator.next();

								if (entity instanceof EntityLivingBase) {
									EntityLivingBase target = (EntityLivingBase) entity;

									effect.formEffect(world, x, y, z, target, effect);
								}
							}
						}
					} else if (type == EffectType.Block) {
						for (int i = 0; i < effect.effectAreaRange(); i++) {
							int[] pos = this.searchBlock(world, x, y, z, area);

							if (pos != null && pos.length == 3) {
								effect.formEffect(world, pos[0], pos[1], pos[2], null, effect);
							}
						}
					}
				}

				world.scheduleBlockUpdate(x, y, z, this, this.tickRate(world));
			}
		}
	}

	// 以下はエリア内のターゲットを探すメソッド。
	public List searchPlayer(World world, int x, int y, int z, int area) {
		AxisAlignedBB AABB = AxisAlignedBB.getBoundingBox((double) ((float) x - area), (double) y - 1.0D,
				(double) ((float) z - area), (double) ((float) x + area), (double) y + 1.0D,
				(double) ((float) z + area));

		List list = null;
		list = world.getEntitiesWithinAABB(EntityPlayer.class, AABB);

		return list;
	}

	public List searchEntity(World world, int x, int y, int z, int area) {
		AxisAlignedBB AABB = AxisAlignedBB.getBoundingBox((double) ((float) x - area), (double) y - 1.0D,
				(double) ((float) z - area), (double) ((float) x + area), (double) y + 1.0D,
				(double) ((float) z + area));

		List list = null;
		list = world.getEntitiesWithinAABB(EntityLivingBase.class, AABB);

		return list;
	}

	public int[] searchBlock(World world, int x, int y, int z, int area) {
		int targetX = world.rand.nextInt(area * 2 + 1);
		int targetZ = world.rand.nextInt(area * 2 + 1);
		int targetY = world.rand.nextInt(area + 1);

		targetX += x - area;
		targetZ += z - area;
		targetY += y - area / 2;
		MathHelper.clamp_int(targetY, 1, 255);

		int[] pos = new int[3];

		pos[0] = targetX;
		pos[1] = targetY;
		pos[2] = targetZ;

		return pos;
	}

}
