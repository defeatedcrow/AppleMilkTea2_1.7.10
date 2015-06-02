package mods.defeatedcrow.common.item.edible;

import java.util.List;

import net.minecraft.src.*;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import mods.defeatedcrow.*;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.plugin.SSector.LoadSSectorPlugin;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemClam extends ItemFood {

	private static final String[] clamType = new String[] { "clam", "clam_cooked", "burntmeat", "blackegg" };

	@SideOnly(Side.CLIENT)
	private IIcon iconclamType[];

	public ItemClam() {
		super(5, 5, false);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setMaxStackSize(64);
	}

	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
			int par5, int par6, int par7, float par8, float par9, float par10) {
		Block i1 = par3World.getBlock(par4, par5, par6);
		Block block = Blocks.sand;
		if (i1 == Blocks.sand && par1ItemStack.getItemDamage() == 0) {
			par3World.setBlock(par4, par5, par6, DCsAppleMilk.clamSand, 0, 3);
			par3World.playSoundEffect((double) ((float) par4 + 0.5F), (double) ((float) par5 + 0.5F),
					(double) ((float) par6 + 0.5F), block.stepSound.getBreakSound(),
					(block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
			--par1ItemStack.stackSize;
			return true;
		} else {
			return false;
		}
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (par1ItemStack.getItemDamage() > 0) {
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		}

		return par1ItemStack;
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (!par2World.isRemote) {
			this.reduceMoisture(-1, 0.0F, par3EntityPlayer);
		}
		super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
		return par1ItemStack;
	}

	private void reduceMoisture(int par1, float par2, EntityPlayer par3EntityPlayer) {
		// if (DCsAppleMilk.SuccessLoadSSector)
		// {
		// LoadSSectorPlugin.addStatus(par1, par2, 0, 0.0F, par3EntityPlayer);
		// }
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		int j = MathHelper.clamp_int(par1, 0, 3);
		return this.iconclamType[j];
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName() + "_" + par1ItemStack.getItemDamage();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(this, 1, 0));
		par3List.add(new ItemStack(this, 1, 1));
		par3List.add(new ItemStack(this, 1, 2));
		par3List.add(new ItemStack(this, 1, 3));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		this.iconclamType = new IIcon[4];

		for (int i = 0; i < 4; ++i) {
			this.iconclamType[i] = par1IconRegister.registerIcon("defeatedcrow:" + clamType[i]);
		}
	}

}
