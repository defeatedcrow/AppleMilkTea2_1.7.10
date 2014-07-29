package mods.defeatedcrow.common.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import mods.defeatedcrow.client.particle.EntityDCCloudFX;
import mods.defeatedcrow.client.particle.ParticleTex;
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.common.tile.TileChocoPan;
import mods.defeatedcrow.common.tile.TilePanHandle;
import mods.defeatedcrow.plugin.LoadBambooPlugin;
import mods.defeatedcrow.plugin.LoadOreDicHandler;

public class BlockFilledChocoPan extends BlockContainer{
	
	
	public BlockFilledChocoPan ()
	{
		super(Material.circuits);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
		this.setTickRandomly(true);
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
        int currentMeta = par1World.getBlockMetadata(par2, par3, par4);
        Block bottomBlockID = par1World.getBlock(par2, par3 - 1, par4);
        
        if (itemstack == null)
        {
        	if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(DCsAppleMilk.emptyPan,1,0)))
        	{
        		par5EntityPlayer.entityDropItem(new ItemStack(DCsAppleMilk.emptyPan,1,0), 1);
        	}
    		
    		par1World.setBlockToAir(par2, par3, par4);
    		par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
    		return true;
        }
        else
        {
        	ItemStack chocolate = this.checkOreDic(itemstack);
        	
        	if (chocolate != null && chocolate.getItem() != Items.stick)
    		{
				this.getChocolate(par1World, par2, par3, par4, par5EntityPlayer, itemstack, chocolate);
				this.setPanEmpty(par1World, par2, par3, par4, currentMeta);
				
        		return true;
    		}
			else
			{
				return false;
			}
        }
    }

	private void setPanEmpty(World world, int X, int Y, int Z, int meta)
	{
		TileChocoPan tile = (TileChocoPan) world.getTileEntity(X, Y, Z);
		byte dir = tile.getRemainByte();
		
		if (dir < 1)
		{
			world.setBlock(X, Y, Z, DCsAppleMilk.emptyPan, 0, 3);
			TilePanHandle tile2 = (TilePanHandle) world.getTileEntity(X, Y, Z);
			if (tile2 != null)tile2.setDirectionByte((byte)meta);
			world.markBlockForUpdate(X, Y, Z);
		}
		else
		{
			tile.setRemainByte((byte)(dir - 1));
			world.markBlockForUpdate(X, Y, Z);
		}
	}
	
	private void getChocolate (World world, int x, int y, int z,  EntityPlayer player, ItemStack itemstack, ItemStack chocolate)
	{
		
		if (!player.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
        {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
        }
		
		if (!player.inventory.addItemStackToInventory(chocolate))
		{
			player.entityDropItem(chocolate, 1.0F);
		}
		
		world.playSoundAtEntity(player, "random.pop", 0.4F, 1.8F);
	}
	
	private ItemStack checkOreDic(ItemStack itemstack)
	{
		int ore = -1;
		if (LoadOreDicHandler.isAlmond(itemstack)) ore = 0;//almond
		else if (LoadOreDicHandler.isPeanut(itemstack)) ore = 1;//peanut
		else if (LoadOreDicHandler.isNuts(itemstack)) ore = 2;//nut
		else if (LoadOreDicHandler.isStraw(itemstack)) ore = 3;//strawberry
		else if (LoadOreDicHandler.isCherry(itemstack)) ore = 4;//cherry
		else if (LoadOreDicHandler.isBerry(itemstack)) ore = 5;//berry
		else if (LoadOreDicHandler.isBanana(itemstack)) ore = 6;//banana
		else if (LoadOreDicHandler.isRice(itemstack)) ore = 7;//rice puff
		else if (itemstack.getItem() == Items.bread) ore = 8;//bread
		else if (itemstack.getItem() == Items.cookie) ore = 9;//cookie
		else if (itemstack.getItem() == DCsAppleMilk.gratedApple && itemstack.getItemDamage() == 4) ore = 10;//ganache
		else if (itemstack.getItem() == DCsAppleMilk.toffyApple) ore = 12;//candy
		else if (itemstack.getItem() == DCsAppleMilk.EXItems && itemstack.getItemDamage() == 0) ore = 11;//toffyapple
		else ore = -1;
		
		ItemStack Choco = itemstack;
		if (ore != -1) Choco = new ItemStack(DCsAppleMilk.chocolateFruits, 1, ore);
		else Choco = new ItemStack(Items.stick, 1, 0);
		
		return Choco;
	}
	
	public int damageDropped(int par1)
    {
        return 0;
    }
	
	public boolean isOpaqueCube()
	{
		return false;
	}
 
	public boolean renderAsNormalBlock() 
	{
		return false;
	}
	
	@Override
	public int getRenderType()
	{
		return DCsAppleMilk.modelChocoPan;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		
		return DCsConfig.noUseCupDirection ? null : new TileChocoPan();
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
//		this.setDefaultDirection(world, x, y, z);
	}
	
//	private void setDefaultDirection(World world, int x, int y, int z)
//	{
//		if (!world.isRemote)
//		{
//			int var5 = world.getBlock(x, y, z - 1);
//			int var6 = world.getBlock(x, y, z + 1);
//			int var7 = world.getBlock(x - 1, y, z);
//			int var8 = world.getBlock(x + 1, y, z);
//			byte var9 = 0;
// 
//			if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])
//			{
//				var9 = 0;
//			}
// 
//			if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
//			{
//				var9 = 1;
//			}
// 
//			if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])
//			{
//				var9 = 0;
//			}
// 
//			if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])
//			{
//				var9 = 1;
//			}
// 
//			world.setBlockMetadataWithNotify(x, y, z, var9, 3);
//		}
//	}
 
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		int playerFacing = MathHelper.floor_double((double)((par5EntityLivingBase.rotationYaw * 4F) / 360F) + 0.5D) & 3;
 
		if (!DCsConfig.noUseCupDirection)
		{
			byte facing = 0;
			if (playerFacing == 0)
			{
				facing = 0;
			}
			if (playerFacing == 1)
			{
				facing = 1;
			}
			if (playerFacing == 2)
			{
				facing = 0;
			}
			if (playerFacing == 3)
			{
				facing = 1;
			}
	 
			par1World.setBlockMetadataWithNotify(par2, par3, par4, facing, 3);
		}
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }
	
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }
	
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        this.thisBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
    }
	
	public void thisBoundingBox (int par1)
	{
		float f = 0.125F;
		this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, 0.5F, 1.0F - f);
	}
	
	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune)
	{
		return Item.getItemFromBlock(DCsAppleMilk.emptyPan);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.blockIcon = Blocks.hardened_clay.getBlockTextureFromSide(1);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4);
        Block i = par1World.getBlock(par2, par3 - 1, par2);
        double d0 = (double)((float)par2 + 0.25F + par5Random.nextFloat()/2);
        double d1 = (double)((float)par3 + par5Random.nextFloat());
        double d2 = (double)((float)par4 + 0.25F + par5Random.nextFloat()/2);
        double d3 = 0.0199999988079071D;
        double d4 = 0.27000001072883606D;

        if (!DCsConfig.noRenderFoodsSteam) {
        	EntityDCCloudFX cloud = new EntityDCCloudFX(par1World, d0, d1, d2, 0.0D, d3, 0.0D);
        	cloud.setParticleIcon(ParticleTex.getInstance().getIcon("defeatedcrow:particle_cloud"));
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
        }
    }

}
