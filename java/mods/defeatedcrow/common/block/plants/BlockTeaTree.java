package mods.defeatedcrow.common.block.plants;

import static net.minecraftforge.common.EnumPlantType.Plains;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mods.defeatedcrow.api.plants.IRightClickHarvestable;
import mods.defeatedcrow.api.plants.PlantsClickEvent;
import mods.defeatedcrow.common.AchievementRegister;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
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
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTeaTree extends Block implements IShearable, IPlantable, IRightClickHarvestable {

	@SideOnly(Side.CLIENT)
	private IIcon leafIcon;
	@SideOnly(Side.CLIENT)
	private IIcon newleafIcon;
	@SideOnly(Side.CLIENT)
	private IIcon logIcon;

	public BlockTeaTree() {
		super(Material.wood);
		this.setStepSound(Block.soundTypeGrass);
		this.setTickRandomly(true);
		this.setHardness(0.1F);
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if (!par1World.isRemote && par1World.rand.nextInt(6) == 0) {
			int meta = par1World.getBlockMetadata(par2, par3, par4);
			if (meta != 1 && (par1World.getBlockLightValue(par2, par3, par4) > 11)) {
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
			}
		}
	}

	public boolean fertilize(World par1World, int par2, int par3, int par4) {
		return par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
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
				par5EntityPlayer.triggerAchievement(AchievementRegister.getTeaLeaves);
				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
				return true;
			}
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
		return DCsAppleMilk.modelTeaTree;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par2;
		if (par1 == 0) {
			return this.logIcon;
		} else if (par1 == 1) {
			return this.leafIcon;
		} else {
			return this.newleafIcon;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(DCsAppleMilk.saplingTea);
	}

	@Override
	public int quantityDropped(Random random) {
		return random.nextInt(2) == 0 ? 1 : 2;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		int chance = this.getChance(metadata);
		ItemStack crop = this.getCropItem(metadata);

		ret.add(new ItemStack(this.getItemDropped(metadata, world.rand, fortune), this.quantityDropped(world.rand),
				this.damageDropped(metadata)));

		if (fortune > 0) {
			chance -= 10 << fortune;
			if (chance < 2)
				chance = 2;
		}

		if (crop != null) {
			ret.add(crop);
			if (world.rand.nextInt(chance) == 0) {
				ret.add(crop);
			}
		}

		return ret;
	}

	protected int getChance(int meta) {
		return meta == 3 ? 2 : 10;
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.leafIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "tealeaf");
		this.newleafIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "tealeaf_2");
		this.logIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "tealog");

	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {

		return Plains;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {

		return this;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {

		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public boolean onHarvest(World world, int x, int y, int z, IInventory inventory, ItemStack currentItem) {

		int meta = world.getBlockMetadata(x, y, z);
		if (meta != 1)
			return false;

		ItemStack ret = this.getCropItem(meta);
		boolean flag = false;

		if (Util.notEmptyItem(ret) && (currentItem == null || currentItem.getItem() == ret.getItem())) {
			if (inventory instanceof InventoryPlayer) {
				InventoryPlayer playerInv = (InventoryPlayer) inventory;

				if (playerInv.addItemStackToInventory(ret)) {
					world.setBlockMetadataWithNotify(x, y, z, 0, 3);
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
					world.setBlockMetadataWithNotify(x, y, z, 0, 3);
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
			EntityItem drop = new EntityItem(world, (double) ((float) x + a), (double) ((float) y + 1 + a1),
					(double) ((float) z + a2), ret);
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
		int meta = world.getBlockMetadata(x, y, z);
		return meta == 1;
	}

	@Override
	public ItemStack getCropItem(int blockMeta) {
		ItemStack ret = null;
		if (blockMeta == 1)
			ret = new ItemStack(DCsAppleMilk.leafTea, 1, 0);
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
		return 1;
	}

	@Override
	public int getInitialMetadata(World world, int x, int y, int z) {
		return 0;
	}

	@Override
	public int getGrownMetadata(int meta) {
		return 1;
	}

	@Override
	public int getInitialMetadata(int meta) {
		return 0;
	}

	@Override
	public Block getSaplingBlock(int meta) {
		return DCsAppleMilk.saplingTea;
	}

	@Override
	public int getSaplingMeta(int meta) {
		return 0;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, 0));
		return ret;
	}

}
