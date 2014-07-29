package mods.defeatedcrow.common.block;

import java.util.List;
import java.util.Random;

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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.common.tile.TileEggs;
import mods.defeatedcrow.handler.Util;

public class BlockEggBasket extends BlockContainer{	
	
	@SideOnly(Side.CLIENT)
    private IIcon[] EggTex;
	@SideOnly(Side.CLIENT)
    private IIcon cageTex;
	
	private final String[] eggs = new String[] {"whitepanel", "teppann"};
		
	public BlockEggBasket ()
	{
		super(Material.circuits);
		this.setStepSound(Block.soundTypeWood);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
		this.setTickRandomly(true);
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
        int currentMeta = (par1World.getBlockMetadata(par2, par3, par4) & 1);
        Block bottomBlockID = par1World.getBlock(par2, par3 - 1, par4);
        
        if (itemstack == null)
        {
        	if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(DCsAppleMilk.eggBasket,1,currentMeta)))
        	{
        		par5EntityPlayer.entityDropItem(new ItemStack(DCsAppleMilk.eggBasket,1,currentMeta), 1);
        	}
    		
    		par1World.setBlockToAir(par2, par3, par4);
    		par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
    		return true;
        }
        else if (itemstack.getItem() == Item.getItemFromBlock(DCsAppleMilk.emptyCup))
        {
        	if (!par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(DCsAppleMilk.eggBasket,1,currentMeta)))
        	{
        		par5EntityPlayer.entityDropItem(new ItemStack(DCsAppleMilk.eggBasket,1,currentMeta), 1);
        	}
    		
    		par1World.setBlockToAir(par2, par3, par4);
    		par1World.playSoundAtEntity(par5EntityPlayer, "random.pop", 0.4F, 1.8F);
    		return true;
        }
        else
        {
        	return false;
        }
    }
	
	public int damageDropped(int par1)
    {
        return par1 & 1;
    }
	
	public boolean isOpaqueCube()
	{
		return false;
	}
 
	public boolean renderAsNormalBlock() 
	{
		return false;
	}
	
	//kurotamago
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (!par1World.isRemote && par1World.rand.nextInt(2) == 0)
		{
			int meta = par1World.getBlockMetadata(par2, par3, par4);
			if ((meta & 1) == 0)
			{
				this.setEggsBoiled(par1World, par2, par3, par4, meta);
			}
		}
	}
	
	private void setEggsBoiled(World par1World, int x, int y, int z, int meta)
	{
		Block underID = par1World.getBlock(x, y - 1, z);
		Material lava = par1World.getBlock(x, y - 2, z).getMaterial();
		if (!par1World.isRemote)
		{
			if (underID == Blocks.cauldron && par1World.getBlockMetadata(x, y - 1, z) > 0 && lava == Material.lava)
			{
				par1World.setBlockMetadataWithNotify(x, y, z, 3, 3);
			}
		}
	}
	
	//check block under
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        Block l = par1World.getBlock(par2, par3, par4);
        int j = par1World.getBlockMetadata(par2, par3, par4);
        int k = par6ItemStack.getItemDamage();

        if (l == Blocks.cauldron)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2 + (k & 1), 3);
        }
        else
        {
        	par1World.setBlockMetadataWithNotify(par2, par3, par4, 0 + (k & 1), 3);
        }
    }
	
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
    {
		Block l = par1World.getBlock(par2, par3 - 1, par4);
        int j = par1World.getBlockMetadata(par2, par3, par4);

        if (l == Blocks.cauldron)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2 + (j & 1), 3);
        }
        else
        {
        	par1World.setBlockMetadataWithNotify(par2, par3, par4, 0 + (j & 1), 3);
        }
    }
	
	@Override
	public int getRenderType()
	{
		return DCsAppleMilk.modelEggBasket;
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
        this.TeaMakerBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
    }
	
	public void TeaMakerBoundingBox (int par1)
	{
		float f = 0.0F;
		if (par1 < 2)
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		}
		else
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.1F, 1.0F);
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return DCsConfig.noUseCupDirection ? null : new TileEggs();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
    }
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2)
    { 
		if (par1 == 0) return this.EggTex[0];
		if (par1 == 1) return this.EggTex[1];
		if (par1 == 2) return this.cageTex;
		else return Blocks.planks.getBlockTextureFromSide(0);
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
		this.EggTex = new IIcon[2];
		this.cageTex = par1IIconRegister.registerIcon(Util.getTexturePassNoAlt() + "cage");
		
        for (int i = 0; i < 2; ++i)
        {
        	this.EggTex[i] = par1IIconRegister.registerIcon("defeatedcrow:" + eggs[i]);
        }
	}
	
}
