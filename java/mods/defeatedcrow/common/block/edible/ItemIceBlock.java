package mods.defeatedcrow.common.block.edible;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.api.edibles.EdibleItemBlock;
import mods.defeatedcrow.common.*;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class ItemIceBlock extends EdibleItemBlock {

	private static final String[] type = new String[] { "_milk", "_tea", "_greentea", "_cocoa", "_coffee", "_fruit",
			"_lemon", "_lime", "_tomato", "_berry", "_grape", "_mint" };

	public ItemIceBlock(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		if (m < 12)
			return super.getUnlocalizedName() + type[m];
		else
			return super.getUnlocalizedName() + m;
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (!par2World.isRemote) {
			if (par1ItemStack.getItemDamage() == 7)// lime
			{
				EntityItemTeaCup2.clearNegativePotion(par3EntityPlayer);
			}

			BiomeGenBase biome = Util.checkCurrentBiome(par2World, par3EntityPlayer);
			if (BiomeDictionary.isBiomeOfType(biome, Type.NETHER)) {
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 600, 0));
				par3EntityPlayer.triggerAchievement(AchievementRegister.eatIcecream);
			} else if (BiomeDictionary.isBiomeOfType(biome, Type.COLD)) {
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.hunger.id, 100, 0));
			} else if (BiomeDictionary.isBiomeOfType(biome, Type.JUNGLE)
					|| BiomeDictionary.isBiomeOfType(biome, Type.HOT)) {
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 1, 2));
			}

			if (par1ItemStack.getItemDamage() == 11)// mint
			{
				BlockIceCream.increaseAmplifier(par3EntityPlayer);
			}
		}

		return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
	}

	@Override
	public int[] hungerOnEaten(int meta) {
		return new int[] { 0, 0 };
	}

	@Override
	public ArrayList<PotionEffect> effectOnEaten(EntityPlayer player, int meta) {

		ArrayList<PotionEffect> ret = new ArrayList<PotionEffect>();
		if (this.iceEffect(meta) != null) {
			ret.add(this.iceEffect(meta));
		}

		if (ret.isEmpty()) {
			ret.add(new PotionEffect(Potion.regeneration.id, 1, 0));
		}
		return ret;
	}

	public PotionEffect iceEffect(int meta) {

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
		} else if ((meta == 5) && DCsAppleMilk.succeedAddPotion)// fruit
		{
			return new PotionEffect(DCsAppleMilk.Immunization.id, 900, 0);
		} else if ((meta == 6) && DCsAppleMilk.succeedAddPotion)// lemon
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
		} else if (meta == 10)// grape
		{
			return new PotionEffect(Potion.moveSpeed.id, 900, 0);
		} else if (meta == 11)// mint
		{
			return null;
		} else// 例外用
		{
			return new PotionEffect(Potion.heal.id, 1, 0);
		}
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity) {
		if (entity.worldObj.isRemote || entity == null) {
			return false;
		} else {
			int meta = itemstack.getItemDamage();
			PotionEffect effect = this.iceEffect(meta);
			ItemStack ret = this.getReturnContainer(meta);

			if (effect != null)
				entity.addPotionEffect(effect);
			if (meta == 7)
				entity.clearActivePotions();
			if (meta == 11)
				BlockIceCream.increaseAmplifier(entity);
			entity.worldObj.playSoundAtEntity(entity, "random.pop", 0.4F, 1.8F);

			if (!player.capabilities.isCreativeMode) {
				--itemstack.stackSize;
			}
			if (ret != null && !player.inventory.addItemStackToInventory(ret)) {
				player.entityDropItem(ret, 1);
			}
			return true;
		}
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		int l = par1ItemStack.getItemDamage();
		if (l == 7) {
			String s = "cure bad status";
			par3List.add(s);
		} else if (l == 11) {
			String s = "Increase amplifier of current potion effect. (+1)";
			par3List.add(s);
		} else {
			PotionEffect effect = this.iceEffect(l);
			String s = StatCollector.translateToLocal(effect.getEffectName()).trim();
			if (effect.getAmplifier() > 0) {
				s = s + " " + StatCollector.translateToLocal("potion.potency." + effect.getAmplifier()).trim();
			}

			if (effect.getDuration() > 20) {
				s = s + " (" + Potion.getDurationString(effect) + ")";
			}
			par3List.add(s);
		}
	}

}
