package mods.defeatedcrow.common.block.edible;

import java.util.List;
import java.util.Random;

import mods.defeatedcrow.api.events.AMTBlockRightClickEvent;
import mods.defeatedcrow.client.particle.EntityDCCloudFX;
import mods.defeatedcrow.client.particle.ParticleTex;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.tile.TileSteak;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFoodPlate extends BlockContainer {

	private static final String[] contents = new String[] { "_cookedbeef", "_cookedpork", "_cookedpork", "_clam" };
	private static final String[] tsukeawase = new String[] { "_tea", "_juice_milk" };

	@SideOnly(Side.CLIENT)
	private IIcon boxTex;
	@SideOnly(Side.CLIENT)
	private IIcon[] contentsTex;
	@SideOnly(Side.CLIENT)
	private IIcon[] tsukeawaseTex;

	public BlockFoodPlate() {
		super(Material.circuits);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
		this.setTickRandomly(true);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
		int currentMeta = par1World.getBlockMetadata(par2, par3, par4);
		Block bottomBlockID = par1World.getBlock(par2, par3 - 1, par4);

		AMTBlockRightClickEvent event = new AMTBlockRightClickEvent(par1World, par5EntityPlayer, itemstack, par2, par3,
				par4);
		MinecraftForge.EVENT_BUS.post(event);

		if (event.isCanceled()) {
			return true;
		}

		if (itemstack == null) {
			ItemStack ret = new ItemStack(this, 1, currentMeta);
			if (!par1World.isRemote) {
				EntityItem entity = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY,
						par5EntityPlayer.posZ, ret);
				par1World.spawnEntityInWorld(entity);
			}

			par1World.setBlockToAir(par2, par3, par4);
			par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
			return true;
		} else if (itemstack.getItem() == Item.getItemFromBlock(DCsAppleMilk.foodPlate)) {
			ItemStack ret = new ItemStack(this, 1, currentMeta);
			if (!par1World.isRemote) {
				EntityItem entity = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY,
						par5EntityPlayer.posZ, ret);
				par1World.spawnEntityInWorld(entity);
			}

			par1World.setBlockToAir(par2, par3, par4);
			par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
			return true;
		} else if (itemstack.getItem() == DCsAppleMilk.chopsticks) {
			if (!par1World.isRemote) {
				if (currentMeta == 3)
					par5EntityPlayer.addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 3, 2));
				else
					par5EntityPlayer.addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 3, 3));

				if (currentMeta < 3 && DCsAppleMilk.suffocation != null) {
					boolean flag = par5EntityPlayer.isPotionActive(DCsAppleMilk.suffocation)
							&& (par5EntityPlayer.getActivePotionEffect(DCsAppleMilk.suffocation).getDuration() < 100);

					if (flag) {
						int dur = par5EntityPlayer.getActivePotionEffect(DCsAppleMilk.suffocation).getDuration();
						par5EntityPlayer.addPotionEffect(new PotionEffect(DCsAppleMilk.suffocation.id, dur + 100, 1));
					} else {
						par5EntityPlayer.addPotionEffect(new PotionEffect(DCsAppleMilk.suffocation.id, 200, 0));
					}
				}
			}

			par1World.setBlockToAir(par2, par3, par4);
			par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
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
		return DCsAppleMilk.modelFoodPlate;
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
		this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, 0.3F, 1.0F - f);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par2;
		if (i > 3)
			i = 3;
		if (par1 == 1) {
			return this.boxTex;
		} else if (par1 == 0) {
			return Blocks.planks.getIcon(0, 0);
		} else if (par1 == 2) {
			return this.contentsTex[i];
		} else if (par1 == 3) {
			return this.tsukeawaseTex[0];
		} else {
			return this.tsukeawaseTex[1];
		}
	}

	// set direction as tileentity
	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileSteak();
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		// this.setDefaultDirection(world, x, y, z);
	}

	// private void setDefaultDirection(World world, int x, int y, int z)
	// {
	// if (!DCsConfig.noUseCupDirection)
	// {
	// TileSteak tile = (TileSteak)world.getBlockTileEntity(x, y, z);
	//
	// if (!world.isRemote)
	// {
	// int var5 = world.getBlock(x, y, z - 1);
	// int var6 = world.getBlock(x, y, z + 1);
	// int var7 = world.getBlock(x - 1, y, z);
	// int var8 = world.getBlock(x + 1, y, z);
	// byte var9 = 0;
	//
	// if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])
	// {
	// var9 = 0;
	// }
	//
	// if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
	// {
	// var9 = 1;
	// }
	//
	// if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])
	// {
	// var9 = 2;
	// }
	//
	// if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])
	// {
	// var9 = 4;
	// }
	//
	// tile.setDirectionByte(var9);
	// }
	// }
	// }

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
			facing = 4;
		}

		TileEntity tileEntity = par1World.getTileEntity(par2, par3, par4);
		if (tileEntity != null && tileEntity instanceof TileSteak) {
			((TileSteak) tileEntity).setDirectionByte(facing);
			// System.out.println("[AppleMilk]Now fasing " + facing);
			par1World.markBlockForUpdate(par2, par3, par4);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < 4; ++i) {
			par3List.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.boxTex = par1IconRegister.registerIcon("defeatedcrow:porcelain");
		this.contentsTex = new IIcon[4];
		for (int i = 0; i < 4; ++i) {
			this.contentsTex[i] = par1IconRegister.registerIcon("defeatedcrow:foodstaff" + contents[i]);
		}

		this.tsukeawaseTex = new IIcon[2];
		for (int i = 0; i < 2; ++i) {
			this.tsukeawaseTex[i] = par1IconRegister.registerIcon("defeatedcrow:contents" + tsukeawase[i]);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		int l = par1World.getBlockMetadata(par2, par3, par4);
		Block i = par1World.getBlock(par2, par3 - 1, par2);
		double d0 = (double) ((float) par2 + 0.25F + par5Random.nextFloat() / 2);
		double d1 = (double) ((float) par3 + par5Random.nextFloat());
		double d2 = (double) ((float) par4 + 0.25F + par5Random.nextFloat() / 2);
		double d3 = 0.0099999988079071D;
		double d4 = 0.27000001072883606D;

		if (!DCsConfig.noRenderFoodsSteam) {
			EntityDCCloudFX cloud = new EntityDCCloudFX(par1World, d0, d1, d2, 0.0D, d3, 0.0D);
			cloud.setParticleIcon(ParticleTex.getInstance().getIcon("cloud"));
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
		}
	}

}
