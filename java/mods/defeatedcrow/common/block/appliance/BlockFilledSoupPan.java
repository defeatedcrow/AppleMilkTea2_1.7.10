package mods.defeatedcrow.common.block.appliance;

import java.util.Random;

import mods.defeatedcrow.api.appliance.SoupType;
import mods.defeatedcrow.api.recipe.IFondueRecipe;
import mods.defeatedcrow.api.recipe.IFondueSource;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.client.particle.EntityDCCloudFX;
import mods.defeatedcrow.client.particle.ParticleTex;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.tile.appliance.TileFilledSoupPan;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

public class BlockFilledSoupPan extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon[] contentsTex;

	public BlockFilledSoupPan() {
		super(Material.ground);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
		this.setTickRandomly(true);
	}

	@Override
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6,
			float par7, float par8, float par9) {
		ItemStack hold = player.inventory.getCurrentItem();
		int currentMeta = world.getBlockMetadata(par2, par3, par4);
		Block bottomBlock = world.getBlock(par2, par3 - 1, par4);
		TileEntity tile = world.getTileEntity(par2, par3, par4);
		TileFilledSoupPan pan = null;
		if (tile != null && tile instanceof TileFilledSoupPan) {
			pan = (TileFilledSoupPan) tile;
		} else {
			return false;
		}

		if (hold == null) {
			return false;
		} else {
			// old recipe api
			ItemStack chocolate = RecipeRegisterManager.chocoRecipe.getOutput(hold);
			// new recipe
			IFondueRecipe food = RecipeRegisterManager.fondueRecipe.getRecipe(hold, pan.getType());

			if (pan.getType() == SoupType.CHOCO && chocolate != null && chocolate.getItem() != null) {
				this.getRecipeFood(world, par2, par3, par4, player, hold, chocolate);
				this.reduceRemain(world, par2, par3, par4, pan);
				return true;
			} else if (food != null && food.getOutput() != null && food.getType() == pan.getType()) {
				this.getRecipeFood(world, par2, par3, par4, player, hold, food.getOutput());
				this.reduceRemain(world, par2, par3, par4, pan);
				return true;
			} else {
				if (world.isRemote)
					player.addChatMessage(new ChatComponentText(StatCollector
							.translateToLocal("dc.panMessage.noFondueRecipe")));
				return true;
			}
		}
	}

	// change soup type
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (world.isRemote || !this.onFurnace(world, x, y, z) || entity == null)
			return;
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile == null || !(tile instanceof TileFilledSoupPan))
			return;

		TileFilledSoupPan pan = (TileFilledSoupPan) tile;
		boolean flag = false;
		if (entity instanceof EntityItem) {
			ItemStack input = ((EntityItem) entity).getEntityItem();
			IFondueSource recipe = RecipeRegisterManager.fondueRecipe.getType(input);

			if (recipe != null && recipe.beforeType() == pan.getType() && recipe.matches(input)) {
				this.setSoupType(world, x, y, z, pan, recipe.afterType());
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

	public void setSoupType(World world, int x, int y, int z, TileFilledSoupPan pan, SoupType type) {
		if (pan == null)
			return;
		pan.setType(type);
		pan.setRemainByte((byte) 8);
		world.playAuxSFX(2005, x, y, z, 0);
		world.markBlockForUpdate(x, y, z);
	}

	// result
	private void getRecipeFood(World world, int x, int y, int z, EntityPlayer player, ItemStack input, ItemStack result) {
		if (input == null || input.getItem() == null)
			return;
		ItemStack container = null;
		if (FluidContainerRegistry.isFilledContainer(input)) {
			container = FluidContainerRegistry.drainFluidContainer(input);
		} else {
			container = input.getItem().getContainerItem(input);
		}

		if (!player.capabilities.isCreativeMode && --input.stackSize <= 0) {
			player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
		}

		if (!world.isRemote) {
			EntityItem entity = new EntityItem(world, player.posX, player.posY, player.posZ, result);
			world.spawnEntityInWorld(entity);
		}

		if (container != null) {
			if (!world.isRemote) {
				EntityItem entity = new EntityItem(world, player.posX, player.posY, player.posZ, container);
				world.spawnEntityInWorld(entity);
			}
		}

		world.playSoundAtEntity(player, "random.pop", 0.4F, 1.8F);
	}

	public void reduceRemain(World world, int x, int y, int z, TileFilledSoupPan pan) {
		if (!world.isRemote && pan != null) {
			int rem = pan.getRemainByte();
			if (rem > 1) {
				pan.setRemainByte((byte) (rem - 1));
			} else {
				world.removeTileEntity(x, y, z);
				world.setBlock(x, y, z, DCsAppleMilk.emptyPanGaiden, 0, 2);
			}
			world.markBlockForUpdate(x, y, z);
		}
	}

	public boolean onFurnace(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y - 1, z);
		int meta = world.getBlockMetadata(x, y - 1, z);
		if (RecipeRegisterManager.panRecipe.isHeatSource(block, meta)) {
			return true;
		}
		return false;
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
		return DCsAppleMilk.modelSoupPan;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {

		return new TileFilledSoupPan();
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		int playerFacing = MathHelper.floor_double((par5EntityLivingBase.rotationYaw * 4F) / 360F + 0.5D) & 3;

		boolean facing = false;
		if (playerFacing == 1 || playerFacing == 3) {
			facing = true;
		}

		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		if (tile instanceof TileFilledSoupPan && par6ItemStack != null
				&& par6ItemStack.getItem() == Item.getItemFromBlock(DCsAppleMilk.filledSoupPan)) {
			((TileFilledSoupPan) tile).setDirection(facing);
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
		this.thisBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	public void thisBoundingBox(int par1) {
		float f = 0.125F;
		this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, 0.5F, 1.0F - f);
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(DCsAppleMilk.emptyPanGaiden);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		int i = Math.min(meta, this.contentsTex.length - 1);
		if (side == 1) {
			return this.contentsTex[i];
		} else {
			return this.blockIcon;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = Blocks.hardened_clay.getBlockTextureFromSide(1);
		int lim = SoupType.types.length;
		this.contentsTex = new IIcon[lim];
		for (int i = 0; i < lim; i++) {
			this.contentsTex[i] = par1IconRegister.registerIcon(SoupType.getType(i).blockTexture);
		}
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

		if (!DCsConfig.noRenderFoodsSteam) {
			EntityDCCloudFX cloud = new EntityDCCloudFX(par1World, d0, d1, d2, 0.0D, d3, 0.0D);
			cloud.setParticleIcon(ParticleTex.getInstance().getIcon("cloud"));
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
		}
	}
}
