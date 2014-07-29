package mods.defeatedcrow.common.item;

import java.util.Set;

import com.google.common.collect.Sets;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class ItemChalcedonyHammer extends ItemTool
{
    /** an array of the blocks this pickaxe is effective against */
    public static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[] {Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail});

    public ItemChalcedonyHammer(ToolMaterial par2EnumToolMaterial)
    {
        super(2.0F, par2EnumToolMaterial, blocksEffectiveAgainst);
    }

    @Override
    public boolean func_150897_b(Block par1Block)
    {
        return par1Block == Blocks.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (par1Block != Blocks.diamond_block && par1Block != Blocks.diamond_ore ? (par1Block != Blocks.emerald_ore && par1Block != Blocks.emerald_ore ? (par1Block != Blocks.gold_block && par1Block != Blocks.gold_ore ? (par1Block != Blocks.iron_block && par1Block != Blocks.iron_ore ? (par1Block != Blocks.lapis_block && par1Block != Blocks.lapis_ore ? (par1Block != Blocks.redstone_ore && par1Block != Blocks.lit_redstone_ore ? (par1Block.getMaterial() == Material.rock ? true : (par1Block.getMaterial() == Material.iron ? true : par1Block.getMaterial() == Material.anvil)) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2);
    }
    
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return par2Block != null && (par2Block.getMaterial() == Material.iron || par2Block.getMaterial() == Material.anvil || par2Block.getMaterial() == Material.rock) ? this.efficiencyOnProperMaterial : super.func_150893_a(par1ItemStack, par2Block);
    }
    
    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, Block par3, int par4, int par5, int par6, EntityLivingBase par7EntityLivingBase)
    {
        if (par3 != Blocks.ice)
        {
        	return super.onBlockDestroyed(par1ItemStack, par2World, par3, par4, par5, par6, par7EntityLivingBase);
        }
        else
        {
            return true;
        }
    }
    
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        Block i1 = par3World.getBlock(par4, par5, par6);
        int damage = par1ItemStack.getItemDamage();
        boolean check = false;

        if (i1 == Blocks.stone || i1 == Blocks.ice || i1 == Blocks.stonebrick)
        {
            check =  true;
        }
        else
        {
            check =  false;
        }
        
        if (check)
        {
        	
        	if (i1 == Blocks.stone)
        	{
        		if (par2EntityPlayer.isSneaking())
        		{
        			Block i2 = Blocks.stonebrick;
            		int j1 = 0;
                    
                    par3World.setBlock(par4, par5, par6, i2, j1, 3);
                    par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), i2.stepSound.getBreakSound(), (i2.stepSound.getVolume() + 1.0F) / 2.0F, i2.stepSound.getPitch() * 0.8F);
                    par1ItemStack.damageItem(2, par2EntityPlayer);
        		}
        		else
        		{
        			par3World.setBlockToAir(par4, par5, par6);
            		par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), Blocks.stone.stepSound.getBreakSound(), (Blocks.stone.stepSound.getVolume() + 1.0F) / 2.0F, Blocks.stone.stepSound.getPitch() * 0.8F);
            		
            		if (!par2EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Blocks.stone, 1, 0)))
                    {
            			par2EntityPlayer.func_146097_a(new ItemStack(Blocks.stone, 1, 0), true, false);//プレイヤーがアイテムを投げるのたぶんコレ
                    }
            		
            		par1ItemStack.damageItem(2, par2EntityPlayer);
        		}
        		return true;
        	}
        	else if (i1 == Blocks.stonebrick && par2EntityPlayer.isSneaking())
        	{
        		Block i2 = Blocks.stonebrick;
        		int j1 = 3;
                
                par3World.setBlock(par4, par5, par6, i2, j1, 3);
                par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), i2.stepSound.getBreakSound(), (i2.stepSound.getVolume() + 1.0F) / 2.0F, i2.stepSound.getPitch() * 0.8F);
                par1ItemStack.damageItem(2, par2EntityPlayer);
                return true;
        	}
        	else if (i1 == Blocks.ice)
        	{
        		par3World.setBlockToAir(par4, par5, par6);
        		par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), Blocks.ice.stepSound.getBreakSound(), (Blocks.ice.stepSound.getVolume() + 1.0F) / 2.0F, Blocks.ice.stepSound.getPitch() * 0.8F);
        		
        		if (!par2EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Blocks.ice, 1, 0)))
                {
        			par2EntityPlayer.func_146097_a(new ItemStack(Blocks.ice, 1, 0), true, false);
                }
        		
        		par1ItemStack.damageItem(2, par2EntityPlayer);
        		return true;
        	}
        	else
        	{
        		return false;
        	}
        }
        else
        {
        	return false;
        }
    }
    
    @Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister){
	this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:chalcedonyhammer");
	}
}
