package mods.defeatedcrow.common.block.edible;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mods.defeatedcrow.api.energy.IBattery;
import mods.defeatedcrow.common.AchievementRegister;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.config.DCsConfigCocktail;
import mods.defeatedcrow.common.entity.edible.PlaceableCocktailSP;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityItemCocktailSP extends EdibleEntityItemBlock2 {

	private static final String[] type = new String[] { "_original1", "_original2", "_original3" };

	public EntityItemCocktailSP(Block block) {
		super(block, false, true);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		if (m < 10)
			return super.getUnlocalizedName() + type[m];
		else
			return super.getUnlocalizedName() + m;
	}

	@Override
	public String getItemStackDisplayName(ItemStack item) {
		int meta = Math.min(item.getItemDamage(), 2);
		return DCsConfigCocktail.name[meta];
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (!par2World.isRemote) {
			this.addSSMoisture(4, 3F, par3EntityPlayer);
		}
		par3EntityPlayer.triggerAchievement(AchievementRegister.drinkCocktail);

		return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
	}

	@Override
	public int[] hungerOnEaten(int meta) {
		return new int[] { 0, 0 };
	}

	@Override
	public ArrayList<PotionEffect> effectOnEaten(EntityPlayer par1EntityPlayer, int meta) {
		PotionEffect potion = new PotionEffect(Potion.digSpeed.id, 2400, 2);
		int i = MathHelper.clamp_int(meta, 0, 2);
		int dur = DCsConfigCocktail.potionDur[i];
		int amp = DCsConfigCocktail.potionAmp[i];

		boolean flag = false;

		ArrayList<PotionEffect> ret = new ArrayList<PotionEffect>();
		ret.add(new PotionEffect(Potion.hunger.id, 300, 1));

		if (this.getCustomEffect(meta) != null) {
			if (par1EntityPlayer.isPotionActive(this.getCustomEffect(meta).id)) {
				dur = par1EntityPlayer.getActivePotionEffect(this.getCustomEffect(meta)).getDuration() + dur;
				potion = new PotionEffect(this.getCustomEffect(meta).id, dur, amp);
				flag = true;
			} else {
				potion = new PotionEffect(this.getCustomEffect(meta).id, dur, amp);
			}
		}

		if (potion != null)
			ret.add(potion);

		if (flag) {
			ret.add(new PotionEffect(Potion.confusion.id, 300, 1));
		}

		return ret;
	}

	private Potion getCustomEffect(int meta) {
		int i = MathHelper.clamp_int(meta, 0, 2);
		int id = DCsConfigCocktail.potionIds[i];

		if (id < Potion.potionTypes.length && Potion.potionTypes[id] != null) {
			return Potion.potionTypes[id];
		}

		return Potion.regeneration;
	}

	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.drink;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	protected boolean spownEntityFoods(World world, EntityPlayer player, ItemStack item, double x, double y, double z) {
		PlaceableCocktailSP entity = new PlaceableCocktailSP(world, item, x, y, z);
		entity.rotationYaw = player.rotationYaw - 180.0F;

		if (!world.isRemote && item != null) {
			return world.spawnEntityInWorld(entity);
		}

		return false;
	}

	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		int l = Math.min(par1ItemStack.getItemDamage(), 2);
		String message = DCsConfigCocktail.massage[l];
		if (message != null) {
			par3List.add(message);
		}
	}

}
