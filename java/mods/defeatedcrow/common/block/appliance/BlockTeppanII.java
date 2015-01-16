package mods.defeatedcrow.common.block.appliance;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.api.recipe.IPlateRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.AchievementRegister;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.appliance.TileTeppanII;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockTeppanII extends BlockContainer{
	
	public BlockTeppanII ()
	{
		super(Material.iron);
		this.setStepSound(Block.soundTypeMetal);
		this.setHardness(3.0F);
		this.setResistance(10.0F);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}
	
	//基礎部分
	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
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
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
	}
	
	//Entity着火
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
		if (this.getUnderMaterial(par1World, par2, par3, par4) && (par5Entity instanceof EntityLiving || par5Entity instanceof EntityPlayer))
        {
        	par5Entity.setFire(30);
        	if (par5Entity instanceof EntityPlayer) {
        		((EntityPlayer) par5Entity).triggerAchievement(AchievementRegister.burnOnTeppan);
        	}
        }
    }
	
	private boolean getUnderMaterial (World par1World, int par2, int par3, int par4)
	{
		TileEntity tep = par1World.getTileEntity(par2, par3, par4);
		if (tep instanceof TileTeppanII && !par1World.isAirBlock(par2, par3 - 1, par4))
		{
			return ((TileTeppanII)tep).isOnHeatSource();
		}
		
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("defeatedcrow:teppann");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileTeppanII();
	}
	
	/* 更新処理 */
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		boolean flag = this.isOvenMode(par1World, par2, par3, par4);
		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		if (tile instanceof TileTeppanII){
			((TileTeppanII)tile).setOvenMode(flag);
		}
		par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate(par1World));
	}
	
	@Override
	public int tickRate(World p_149738_1_)
    {
        return 20;//1秒毎の間隔で処理を実行。
    }
	
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
    {
		boolean flag = this.isOvenMode(par1World, par2, par3, par4);
		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		if (tile instanceof TileTeppanII){
			((TileTeppanII)tile).setOvenMode(flag);
		}
		par1World.scheduleBlockUpdate(par2, par3, par4, this, this.tickRate(par1World));
    }
	
	public boolean isOvenMode(World world, int x, int y, int z)
	{
		int count = 0;
		if (world.canBlockSeeTheSky(x, y, z)){
			return false;
		}
		
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
		{
			if (dir == ForgeDirection.DOWN || dir == ForgeDirection.UP) continue;
			else
			{
				int x1 = x + dir.offsetX;
				int y1 = y + dir.offsetY;
				int z1 = z + dir.offsetZ;
				if (!world.isAirBlock(x1, y1 ,z1)
						&& world.getBlock(x1, y1, z1).getMaterial() == Material.rock);
				{
					count++;
				}
			}
		}
		return count > 3;
	}
	
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
    {
		if (!par1World.isRemote)
        {
			TileEntity tile = par1World.getTileEntity(par2, par3, par4);
			if(tile instanceof TileTeppanII)
			{
				TileTeppanII tep = (TileTeppanII) tile;
				for (int i = 0 ; i < tep.getSizeInventory() ; i++)
				{
					ItemStack item = tep.plateItems[i];
					if (item != null)
	                {
	                    tep.setInventorySlotContents(i, (ItemStack)null);
	                    par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
	                    float f = 0.7F;
	                    double d0 = (double)(par1World.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
	                    double d1 = (double)(par1World.rand.nextFloat() * f) + (double)(1.0F - f) * 0.2D + 0.6D;
	                    double d2 = (double)(par1World.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
	                    ItemStack itemstack1 = item.copy();
	                    EntityItem entityitem = new EntityItem(par1World, (double)par2 + d0, (double)par3 + d1, (double)par4 + d2, itemstack1);
	                    entityitem.delayBeforeCanPickup = 10;
	                    par1World.spawnEntityInWorld(entityitem);
	                }
				}
			}
        }
    }
	
	/* 右クリック処理 */
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		ItemStack item = par5EntityPlayer.inventory.getCurrentItem();
		TileTeppanII tep = null;
		if (tile instanceof TileTeppanII){
			tep = (TileTeppanII) tile;
		}
		
		if (DCsAppleMilk.debugMode){
			AMTLogger.debugInfo("teppan info:");
			String s = "";
			if (tep.isOnHeatSource()) s += "ready to cooking,";
			if (this.isOvenMode(par1World, par2, par3, par4)) s += " oven";
			AMTLogger.debugInfo("teppan current : " + s);
			if (item != null);
				IPlateRecipe rep = RecipeRegisterManager.plateRecipe.getRecipe(item);
				if (rep != null && rep.getOutput() != null){
					AMTLogger.debugInfo("equip item output : " + rep.getOutput().getDisplayName());
			}
			if (tep.plateItems[0] != null){
				AMTLogger.debugInfo("hold item " + tep.plateItems[0].getDisplayName());
			}
			AMTLogger.debugInfo("cooking... " + tep.getCookTime());
			AMTLogger.debugInfo("cooking flag fin:" + tep.isFinishCooking() + " fail:" + tep.isFailed());
		}
		
		if (tep!= null && tep.isOnHeatSource())
		{
			if (tep.plateNoHoldingItem() && item != null)
			{
				if (tep.canSetRecipe(item))
				{
					ItemStack input = new ItemStack(item.getItem(), 1, item.getItemDamage());
					if (!par5EntityPlayer.capabilities.isCreativeMode && --item.stackSize <= 0)
                    {
                        par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, (ItemStack)null);
                    }
					par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
					
					if (!par1World.isRemote){
						tep.refreshPlate();
						tep.setRecipe(input);
						tep.updatePlate();
					}
					
					return true;
				}
			}
			if (tep.isFinishCooking() && tep.plateItems[1] != null)
			{
				ItemStack ret = tep.plateItems[1];
				if (ret != null && !par5EntityPlayer.inventory.addItemStackToInventory(ret))
				{
					if (!par1World.isRemote) par5EntityPlayer.entityDropItem(ret, 1.0F);
				}
				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
				if (!par1World.isRemote){
					tep.refreshPlate();
					tep.updatePlate();
				}
				par5EntityPlayer.inventory.markDirty();
				return true;
			}
			if (tep.isFailed() && tep.plateItems[2] != null)
			{
				ItemStack ret = tep.plateItems[2];
				if (ret != null && !par5EntityPlayer.inventory.addItemStackToInventory(ret))
				{
					if (!par1World.isRemote) par5EntityPlayer.entityDropItem(ret, 1.0F);
				}
				par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
				if (!par1World.isRemote){
					tep.refreshPlate();
					tep.updatePlate();
				}
				par5EntityPlayer.inventory.markDirty();
				return true;
			}
		}
		
		return false;
    }

}
