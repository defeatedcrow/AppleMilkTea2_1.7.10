package mods.defeatedcrow.common.item.edible;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.src.*;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import mods.defeatedcrow.*;
import mods.defeatedcrow.api.potion.AMTPotionManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.plugin.SSector.LoadSSectorPlugin;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemIcyToffyApple extends ItemFood {

	@SideOnly(Side.CLIENT)
	private IIcon iconToffyType[];

	public ItemIcyToffyApple(int reco, int sat, boolean flag) {
		super(reco, sat, flag);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setMaxStackSize(64);
		this.setAlwaysEdible();

	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		int j = MathHelper.clamp_int(par1, 0, 6);
		return this.iconToffyType[j];
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		boolean alt = false;
		boolean alt2 = false;
		switch (par1ItemStack.getItemDamage()) {
		case 0:
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 600, 0));
			break;
		case 1:
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.jump.id, 600, 1));
			break;
		case 2:
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, 600, 0));
			break;
		case 3:
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 600, 0));
			break;
		case 4:
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 1, 0));
			break;
		case 5:
			alt = true;
			break;
		case 6:
			int id = AMTPotionManager.manager.AMTgetPotion("immunization").id;
			par3EntityPlayer.addPotionEffect(new PotionEffect(id, 2, 3));
			break;
		default:
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 600, 0));
			break;

		}

		if (alt) {
			Iterator current = par3EntityPlayer.getActivePotionEffects().iterator();
			int increase = 600;

			while (current.hasNext()) {
				PotionEffect potioneffect = (PotionEffect) current.next();
				boolean flag = potioneffect.getPotionID() != Potion.heal.id
						&& potioneffect.getPotionID() != Potion.harm.id
						&& potioneffect.getPotionID() != Potion.regeneration.id;
				if (flag)
					par3EntityPlayer.addPotionEffect(new PotionEffect(potioneffect.getPotionID(), potioneffect
							.getDuration() + increase, potioneffect.getAmplifier()));
			}
		}

		// if (par2World.isRemote && par1ItemStack.getItemDamage() < 4)
		// {
		// this.reduceMoisture(-1, 0.0F, par3EntityPlayer);
		// }
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
		par3List.add(new ItemStack(this, 1, 4));
		par3List.add(new ItemStack(this, 1, 5));
		par3List.add(new ItemStack(this, 1, 6));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.iconToffyType = new IIcon[7];

		for (int i = 0; i < 4; ++i) {
			this.iconToffyType[i] = par1IconRegister.registerIcon("defeatedcrow:icytoffyapple" + i);
		}
		this.iconToffyType[4] = par1IconRegister.registerIcon("defeatedcrow:candy_cassis");
		this.iconToffyType[5] = par1IconRegister.registerIcon("defeatedcrow:candy_mint");
		this.iconToffyType[6] = par1IconRegister.registerIcon("defeatedcrow:candy_yuzu");
	}

	// private void reduceMoisture(int par1, float par2, EntityPlayer par3EntityPlayer)
	// {
	// if (DCsAppleMilk.SuccessLoadSSector)
	// {
	// LoadSSectorPlugin.addStatus(par1, par2, 0, 0.0F, par3EntityPlayer);
	// }
	// }

}
