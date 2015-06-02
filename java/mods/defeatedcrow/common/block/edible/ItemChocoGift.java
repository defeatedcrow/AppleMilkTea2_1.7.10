package mods.defeatedcrow.common.block.edible;

import java.util.ArrayList;

import mods.defeatedcrow.api.edibles.EdibleItemBlock;
import mods.defeatedcrow.common.AchievementRegister;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemChocoGift extends EdibleItemBlock {

	private static final String[] type = new String[] { "", "_heartfelt" };

	public ItemChocoGift(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		if (m < 2)
			return super.getUnlocalizedName() + type[m];
		else
			return super.getUnlocalizedName() + m;
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		int meta = par1ItemStack.getItemDamage();

		if (!par2World.isRemote && meta == 1) {
			if (!DCsConfig.safetyChocolate) {
				this.explode(par2World, par3EntityPlayer);
				par3EntityPlayer.triggerAchievement(AchievementRegister.eatChocoGift);
			}
		}

		return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
	}

	private void explode(World par1World, EntityPlayer par2EntityPlayer) {
		float f = 3.0F;
		par1World.createExplosion(par2EntityPlayer, par2EntityPlayer.posX, par2EntityPlayer.posY,
				par2EntityPlayer.posZ, f, false);

	}

	@Override
	public ArrayList<PotionEffect> effectOnEaten(EntityPlayer player, int meta) {
		ArrayList<PotionEffect> ret = new ArrayList<PotionEffect>();
		ret.add(new PotionEffect(Potion.heal.id, 2, 2));
		return ret;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

}
