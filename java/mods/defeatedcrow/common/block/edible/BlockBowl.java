package mods.defeatedcrow.common.block.edible;

import java.util.List;
import java.util.Random;

import mods.defeatedcrow.api.events.AMTBlockRightClickEvent;
import mods.defeatedcrow.client.particle.EntityDCCloudFX;
import mods.defeatedcrow.client.particle.ParticleTex;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBowl extends Block {

	public static final String[] contents = new String[] { "_rice", "_kinoko", "_soup", "_zousui", "_kayaku", "_soi",
			"_juice", "_BLTsoup", "_misosoup", "_clamsoup" };

	@SideOnly(Side.CLIENT)
	private IIcon boxTex;
	@SideOnly(Side.CLIENT)
	private IIcon[] contentsTex;

	public BlockBowl() {
		super(Material.circuits);
		this.setStepSound(Block.soundTypeWood);
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
			if (currentMeta == 15) {
				ItemStack ret = new ItemStack(Items.bowl, 1, 0);
				if (!par1World.isRemote) {
					EntityItem entity = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY,
							par5EntityPlayer.posZ, ret);
					par1World.spawnEntityInWorld(entity);
				}
			} else {
				ItemStack ret = new ItemStack(this, 1, currentMeta);
				if (!par1World.isRemote) {
					EntityItem entity = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY,
							par5EntityPlayer.posZ, ret);
					par1World.spawnEntityInWorld(entity);
				}
			}

			par1World.setBlockToAir(par2, par3, par4);
			par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
			return true;
		} else if (itemstack.getItem() == DCsAppleMilk.chopsticks) {
			if (!par1World.isRemote) {
				par5EntityPlayer.addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 2, 2));
			}

			par1World.setBlockMetadataWithNotify(par2, par3, par4, 15, 3);
			par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
			return true;
		} else if (itemstack.getItem() == Item.getItemFromBlock(this) || itemstack.getItem() == Items.bowl) {
			if (currentMeta == 15) {
				ItemStack ret = new ItemStack(Items.bowl, 1, 0);
				if (!par1World.isRemote) {
					EntityItem entity = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY,
							par5EntityPlayer.posZ, ret);
					par1World.spawnEntityInWorld(entity);
				}
			} else {
				ItemStack ret = new ItemStack(this, 1, currentMeta);
				if (!par1World.isRemote) {
					EntityItem entity = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY,
							par5EntityPlayer.posZ, ret);
					par1World.spawnEntityInWorld(entity);
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
		return DCsAppleMilk.modelBowl;
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
		if (i > 15)
			i = 15;
		if (par1 == 1) {
			return this.boxTex;
		} else {
			return i < 10 ? this.contentsTex[i] : this.boxTex;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < 10; ++i) {
			par3List.add(new ItemStack(this, 1, i));
		}
		par3List.add(new ItemStack(this, 1, 15));
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.contentsTex = new IIcon[10];
		this.boxTex = par1IconRegister.registerIcon("defeatedcrow:porcelain");

		for (int i = 0; i < 10; ++i) {
			this.contentsTex[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "contents" + contents[i]);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		int l = par1World.getBlockMetadata(par2, par3, par4);
		Block i = par1World.getBlock(par2, par3 - 1, par2);
		double d0 = (double) ((float) par2 + 0.375F + par5Random.nextFloat() / 4);
		double d1 = (double) ((float) par3 + par5Random.nextFloat());
		double d2 = (double) ((float) par4 + 0.375F + par5Random.nextFloat() / 4);
		double d3 = 0.0059999988079071D;
		double d4 = 0.27000001072883606D;

		if (!DCsConfig.noRenderFoodsSteam && l != 15) {
			EntityDCCloudFX cloud = new EntityDCCloudFX(par1World, d0, d1, d2, 0.0D, d3, 0.0D);
			cloud.setParticleIcon(ParticleTex.getInstance().getIcon("cloud"));
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
		}
	}

}
