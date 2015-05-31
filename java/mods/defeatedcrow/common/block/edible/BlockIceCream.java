package mods.defeatedcrow.common.block.edible;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import mods.defeatedcrow.api.events.AMTBlockRightClickEvent;
import mods.defeatedcrow.client.particle.EntityBlinkFX;
import mods.defeatedcrow.client.particle.ParticleTex;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.tile.TileIceCream;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockIceCream extends BlockContainer {

	private static final String[] contents = new String[] { "_milk", "_tea_milk", "_greentea_milk", "_cocoa",
			"_cocoa_milk", "_juice", "_lemon", "_lime", "_tomato", "_berry", "_grape", "_mint", "_orange", "_soda" };

	@SideOnly(Side.CLIENT)
	private IIcon boxTex;
	@SideOnly(Side.CLIENT)
	private IIcon[] contentsTex;

	public BlockIceCream() {
		super(Material.glass);
		this.setStepSound(Block.soundTypeGlass);
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
		} else if (itemstack.getItem() == DCsAppleMilk.chopsticks) {
			if (!par1World.isRemote) {
				if (this.effectOnEaten(currentMeta) != null) {
					par5EntityPlayer.addPotionEffect(this.effectOnEaten(currentMeta));
				} else if (currentMeta == 4) {
					EntityItemTeaCup2.clearNegativePotion(par5EntityPlayer);
				} else if (currentMeta == 11) {
					this.increaseAmplifier(par5EntityPlayer);
				}
			}

			par1World.setBlockToAir(par2, par3, par4);
			par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
			return true;
		} else if (itemstack.getItem() == Item.getItemFromBlock(this)) {
			ItemStack ret = new ItemStack(this, 1, currentMeta);
			if (!par1World.isRemote) {
				EntityItem entity = new EntityItem(par1World, par5EntityPlayer.posX, par5EntityPlayer.posY,
						par5EntityPlayer.posZ, ret);
				par1World.spawnEntityInWorld(entity);
			}

			par1World.setBlockToAir(par2, par3, par4);
			par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
			return true;
		} else {
			return false;
		}
	}

	protected static void increaseAmplifier(EntityLivingBase living) {
		Iterator current = living.getActivePotionEffects().iterator();
		int increase = 1;

		while (current.hasNext()) {
			PotionEffect potioneffect = (PotionEffect) current.next();
			boolean flag = potioneffect.getPotionID() != Potion.regeneration.id && potioneffect.getAmplifier() < 10;
			if (flag)
				living.addPotionEffect(new PotionEffect(potioneffect.getPotionID(), potioneffect.getDuration(),
						potioneffect.getAmplifier() + increase));
		}
	}

	public PotionEffect effectOnEaten(int meta) {

		if (meta == 0)// milk
		{
			return new PotionEffect(Potion.fireResistance.id, 900, 0);
		} else if (meta == 1)// tea
		{
			return new PotionEffect(Potion.heal.id, 1, 0);
		} else if (meta == 2)// greentea
		{
			return new PotionEffect(Potion.digSpeed.id, 900, 0);
		} else if (meta == 3 || meta == 4)// cocoa,coffee
		{
			return new PotionEffect(Potion.nightVision.id, 900, 0);
		} else if ((meta == 5) && DCsConfig.potionIDImmunity != 0)// fruit
		{
			return new PotionEffect(DCsAppleMilk.Immunization.id, 900, 0);
		} else if ((meta == 6) && DCsConfig.potionIDImmunity != 0)// lemon
		{
			return new PotionEffect(DCsAppleMilk.Immunization.id, 900, 1);
		} else if (meta == 7)// lime
		{
			return null;
		} else if (meta == 8)// tomato
		{
			return new PotionEffect(Potion.damageBoost.id, 900, 0);
		} else if (meta == 9)// berry
		{
			return new PotionEffect(Potion.resistance.id, 900, 1);
		} else if (meta == 10 || meta == 13)// grape,soda
		{
			return new PotionEffect(Potion.moveSpeed.id, 900, 0);
		} else if (meta == 12)// orange
		{
			return new PotionEffect(Potion.jump.id, 900, 0);
		} else// mint
		{
			return null;
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
		return DCsAppleMilk.modelIceCream;
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
		float f = 0.25F;
		this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, 0.5F, 1.0F - f);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		if (par1 == 1) {
			return this.contentsTex[par2];
		} else {
			return this.boxTex;
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < 14; ++i) {
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
		this.boxTex = par1IconRegister.registerIcon("defeatedcrow:blueglass");
		this.contentsTex = new IIcon[14];
		for (int i = 0; i < 14; ++i) {
			this.contentsTex[i] = par1IconRegister.registerIcon("defeatedcrow:contents" + contents[i]);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileIceCream();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		int l = par1World.getBlockMetadata(par2, par3, par4);
		Block i = par1World.getBlock(par2, par3 - 1, par2);

		double d0 = (double) ((float) par2 + par5Random.nextFloat());
		double d1 = (double) ((float) par3 + 0.2F + par5Random.nextFloat());
		double d2 = (double) ((float) par4 + par5Random.nextFloat());
		double d3 = 0.0099999988079071D;
		double d4 = 0.0099999988079071D;
		double d5 = 0.0099999988079071D;

		if (!DCsConfig.noRenderFoodsSteam) {
			EntityBlinkFX cloud = new EntityBlinkFX(par1World, d0, d1, d2, 0.0D, d4, 0.0D);
			cloud.setParticleIcon(ParticleTex.getInstance().getIcon("blink"));
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
		}
	}
}
