package mods.defeatedcrow.event;

import mods.defeatedcrow.api.recipe.ITeaRecipe;
import mods.defeatedcrow.api.recipe.RecipeRegisterManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.TileIncenseBase;
import mods.defeatedcrow.common.tile.appliance.TileMakerNext;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class DispenserEvent {
	
	public static DispenserEvent instance = new DispenserEvent();
	
	private DispenserEvent(){}
	
	public void init()
	{
		//着火具
		BlockDispenser.dispenseBehaviorRegistry.putObject(DCsAppleMilk.firestarter, new BehaviorDefaultDispenseItem()
        {
            private boolean flag = true;
            /**
             * Dispense the specified stack, play the dispense sound and spawn particles.
             */
            protected ItemStack dispenseStack(IBlockSource block, ItemStack item)
            {
                EnumFacing enumfacing = BlockDispenser.func_149937_b(block.getBlockMetadata());
                World world = block.getWorld();
                int i = block.getXInt() + enumfacing.getFrontOffsetX();
                int j = block.getYInt() + enumfacing.getFrontOffsetY();
                int k = block.getZInt() + enumfacing.getFrontOffsetZ();

                if (world.isAirBlock(i, j, k))
                {
                    world.setBlock(i, j, k, Blocks.fire);

                    if (item.attemptDamageItem(1, world.rand))
                    {
                        item.stackSize = 0;
                    }
                }
                else if (world.getBlock(i, j, k) == Blocks.tnt)
                {
                    Blocks.tnt.onBlockDestroyedByPlayer(world, i, j, k, 1);
                    world.setBlockToAir(i, j, k);
                }
                else if (world.getBlock(i, j, k) == DCsAppleMilk.incenseBase)
                {
                	TileIncenseBase tile = (TileIncenseBase) world.getTileEntity(i, j, k);
                	if (tile != null && item != null && tile.hasItem())
                	{
                		if (!tile.getActive())
                		{
                			if (item.attemptDamageItem(1, world.rand))
                            {
                                item.stackSize = 0;
                            }
                			tile.setActive();
                    		tile.markDirty();
                    		world.setBlockMetadataWithNotify(i, j, k, 1, 3);
                    		world.scheduleBlockUpdate(i, j, k, DCsAppleMilk.incenseBase, 20);
                		}
                	}
                }
                else
                {
                    this.flag = false;
                }

                return item;
            }
            /**
             * Play the dispense sound from the specified block.
             */
            protected void playDispenseSound(IBlockSource block)
            {
                if (this.flag)
                {
                    block.getWorld().playAuxSFX(1000, block.getXInt(), block.getYInt(), block.getZInt(), 0);
                }
                else
                {
                    block.getWorld().playAuxSFX(1001, block.getXInt(), block.getYInt(), block.getZInt(), 0);
                }
            }
        });
		
	}
	
	public void registerTeaMakerEvent(ItemStack item)
	{
		//ティーメーカー動作
		BlockDispenser.dispenseBehaviorRegistry.putObject(item.getItem(), new BehaviorDefaultDispenseItem()
        {
            private boolean flag = true;
            /**
             * Dispense the specified stack, play the dispense sound and spawn particles.
             */
            protected ItemStack dispenseStack(IBlockSource block, ItemStack item)
            {
                EnumFacing enumfacing = BlockDispenser.func_149937_b(block.getBlockMetadata());
                World world = block.getWorld();
                int i = block.getXInt() + enumfacing.getFrontOffsetX();
                int j = block.getYInt() + enumfacing.getFrontOffsetY();
                int k = block.getZInt() + enumfacing.getFrontOffsetZ();

                if (world.getBlock(i, j, k) == DCsAppleMilk.teaMakerNext)
                {
                    TileMakerNext tile = (TileMakerNext) world.getTileEntity(i, j, k);
                    if (tile != null && item != null && tile.getItemStack() == null)
                    {
                    	ITeaRecipe recipe = RecipeRegisterManager.teaRecipe.getRecipe(item);
                    	if (recipe != null)
                    	{
                    		tile.setRecipe(new ItemStack(item.getItem(), 1, item.getItemDamage()));
                    		tile.setRemain((byte) 3);
                    		tile.markDirty();
                    		
                    		int meta = world.getBlockMetadata(i, j, k);
                    		world.markBlockForUpdate(i, j, k);
                    		world.notifyBlockChange(i, j, k, world.getBlock(i, j, k));
                    		
                    		--item.stackSize;
                    		
                    		flag = true;
                    	}
                    }
                }
                else
                {
                    this.flag = false;
                }

                return item;
            }
            /**
             * Play the dispense sound from the specified block.
             */
            protected void playDispenseSound(IBlockSource block)
            {
                if (this.flag)
                {
                    block.getWorld().playAuxSFX(1009, block.getXInt(), block.getYInt(), block.getZInt(), 0);
                }
                else
                {
                    block.getWorld().playAuxSFX(1001, block.getXInt(), block.getYInt(), block.getZInt(), 0);
                }
            }
        });
		
	}

}
