package mods.defeatedcrow.common.block.appliance;


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
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.tile.TilePanHandle;
import mods.defeatedcrow.common.tile.appliance.TilePanG;
import mods.defeatedcrow.plugin.LoadBambooPlugin;
import mods.defeatedcrow.handler.Util;

@Deprecated
public class BlockFilledPan extends BlockContainer{
	
	private static final String[] contents = new String[] {"_rice", "_kinoko", "_soup", "_zousui"};
	
	@SideOnly(Side.CLIENT)
    private IIcon boxTex;
	@SideOnly(Side.CLIENT)
    private IIcon[] contentsTex;
	
	
	public BlockFilledPan ()
	{
		super(Material.ground);
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
        	if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(DCsAppleMilk.filledPan,1,currentMeta)))
        	{
        		par5EntityPlayer.entityDropItem(new ItemStack(DCsAppleMilk.filledPan,1,currentMeta), 1);
        	}
    		
    		par1World.setBlockToAir(par2, par3, par4);
    		par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
    		return true;
        }
        else if (!LoadBambooPlugin.getBasket().isEmpty() && LoadBambooPlugin.isBasketItem(itemstack) >= 0)
        {
        	this.getJPStew(par1World, par2, par3, par4, par5EntityPlayer, itemstack, currentMeta);
			this.setPanEmpty(par1World, par2, par3, par4, currentMeta);
			par5EntityPlayer.triggerAchievement(AchievementRegister.getSoup);
    		return true;
        }
        else
        {
        	Item ID = itemstack.getItem();
        	int IDm = itemstack.getItemDamage();
        	
        	if (ID == Items.bowl)
    		{
				this.getStew(par1World, par2, par3, par4, par5EntityPlayer, itemstack, currentMeta);
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
		TilePanHandle tile = (TilePanHandle) world.getTileEntity(X, Y, Z);
		byte dir = tile.getDirectionByte();
		
		if (meta < 4)
		{
			if (!world.isRemote)world.setBlock(X, Y, Z, DCsAppleMilk.emptyPanGaiden, 0, 2);
		}
		else
		{
			int i = meta - 4; 
			if (!world.isRemote)world.setBlockMetadataWithNotify(X, Y, Z, i, 3);
		}
	}
	
	private void getStew (World world, int x, int y, int z,  EntityPlayer player, ItemStack itemstack, int teaMeta)
	{
		int m = teaMeta & 3;
		
		if (!player.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
        {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
        }
		
		if (m == 0)
		{
			if (!player.inventory.addItemStackToInventory(new ItemStack(DCsAppleMilk.bowlBlock, 1, 0)))
			{
				player.entityDropItem(new ItemStack(DCsAppleMilk.bowlBlock, 1, 0), 1);
			}
		}
		else if (m == 1)
		{
			if (!player.inventory.addItemStackToInventory(new ItemStack(Items.mushroom_stew,1,0)))
			{
				player.entityDropItem(new ItemStack(Items.mushroom_stew,1,0), 1);
			}
		}
		else if (m == 2)
		{
			if (!player.inventory.addItemStackToInventory(new ItemStack(DCsAppleMilk.bowlBlock, 1, 2)))
			{
				player.entityDropItem(new ItemStack(DCsAppleMilk.bowlBlock, 1, 2), 1);
			}
		}
		else if (m == 3)
		{
			if (!player.inventory.addItemStackToInventory(new ItemStack(DCsAppleMilk.bowlBlock, 1, 3)))
			{
				player.entityDropItem(new ItemStack(DCsAppleMilk.bowlBlock, 1, 3), 1);
			}
		}
		else
		{
			if (!player.inventory.addItemStackToInventory(new ItemStack(Items.bowl, 1)))
			{
				player.entityDropItem(new ItemStack(Items.bowl, 1), 1);
			}
		}
		
		world.playSoundAtEntity(player, "random.pop", 0.4F, 1.8F);
	}
	
	private void getJPStew (World world, int x, int y, int z,  EntityPlayer player, ItemStack itemstack, int teaMeta)
	{
		int m = teaMeta & 3;
		
		if (!player.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
        {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
        }
		
		if (m == 0)
		{
			if (!player.inventory.addItemStackToInventory(new ItemStack(DCsAppleMilk.bowlJP, 1, 0)))
			{
				player.entityDropItem(new ItemStack(DCsAppleMilk.bowlJP, 1, 0), 1);
			}
		}
		else if (m == 1)
		{
			if (!player.inventory.addItemStackToInventory(new ItemStack(DCsAppleMilk.bowlJP, 1, 1)))
			{
				player.entityDropItem(new ItemStack(DCsAppleMilk.bowlJP, 1, 1), 1);
			}
		}
		else if (m == 2)
		{
			if (!player.inventory.addItemStackToInventory(new ItemStack(DCsAppleMilk.bowlJP, 1, 2)))
			{
				player.entityDropItem(new ItemStack(DCsAppleMilk.bowlJP, 1, 2), 1);
			}
		}
		else if (m == 3)
		{
			if (!player.inventory.addItemStackToInventory(new ItemStack(DCsAppleMilk.bowlJP, 1, 3)))
			{
				player.entityDropItem(new ItemStack(DCsAppleMilk.bowlJP, 1, 3), 1);
			}
		}
		else
		{
			if (!player.inventory.addItemStackToInventory(LoadBambooPlugin.getBasket().get(0)))
			{
				player.entityDropItem(LoadBambooPlugin.getBasket().get(0), 1);
			}
		}
		
		world.playSoundAtEntity(player, "random.pop", 0.4F, 1.8F);
	}
	
	public int damageDropped(int par1)
    {
        return par1;
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
		return DCsAppleMilk.modelPan;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		
		return new TilePanHandle();
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
//		this.setDefaultDirection(world, x, y, z);
	}
	
//	private void setDefaultDirection(World world, int x, int y, int z)
//	{
//		if (!DCsConfig.noUseCupDirection)
//		{
//			TilePanHandle tileHandle = (TilePanHandle)world.getTileEntity(x, y, z);
//			
//			if (!world.isRemote)
//			{
//				int var5 = world.getBlock(x, y, z - 1);
//				int var6 = world.getBlock(x, y, z + 1);
//				int var7 = world.getBlock(x - 1, y, z);
//				int var8 = world.getBlock(x + 1, y, z);
//				byte var9 = 0;
//	 
//				if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])
//				{
//					var9 = 0;
//				}
//	 
//				if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
//				{
//					var9 = 1;
//				}
//	 
//				if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])
//				{
//					var9 = 2;
//				}
//	 
//				if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])
//				{
//					var9 = 4;
//				}
//	 
//				tileHandle.setDirectionByte(var9);
//			}
//		}
//	}
 
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		int playerFacing = MathHelper.floor_double((double)((par5EntityLivingBase.rotationYaw * 4F) / 360F) + 0.5D) & 3;
 
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
			facing = 2;
		}
		if (playerFacing == 3)
		{
			facing = 4;
		}
 
		TileEntity tileEntity = par1World.getTileEntity(par2, par3, par4);
		if (tileEntity != null && tileEntity instanceof TilePanHandle)
		{
			((TilePanHandle)tileEntity).setDirectionByte(facing);
			//System.out.println("[AppleMilk]Now fasing " + facing);
			par1World.markBlockForUpdate(par2, par3, par4);
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
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    { 
		int i = (par2 & 3);
		if (par1 == 1)
        {
        	return this.boxTex;
        }
        else
        {
        	return this.contentsTex[i];
        }
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		for(int i = 0; i < 12; ++i)
		{
			par3List.add(new ItemStack(this, 1, i));
		}
    }
	
	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister)
	{
		this.contentsTex = new IIcon[4];
		this.boxTex = par1IIconRegister.registerIcon("defeatedcrow:porcelain");
		
        for (int i = 0; i < 4; ++i)
        {
            int i2 = i & 3;
        	this.contentsTex[i] = par1IIconRegister.registerIcon(Util.getTexturePassNoAlt() + "contents" + contents[i2]);
        }
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
        	cloud.setParticleIcon(ParticleTex.getInstance().getIcon("cloud"));
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
        }
    }

}
