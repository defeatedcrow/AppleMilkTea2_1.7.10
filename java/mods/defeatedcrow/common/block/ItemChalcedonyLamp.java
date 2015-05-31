package mods.defeatedcrow.common.block;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemChalcedonyLamp extends ItemBlock {

	private static final String[] type = new String[] { "_optionBlue", "_option", "_optionWhite", "_optionBlack",
			"_yazukaBlue", "_yazuka", "_yazukaWhite", "_yazukaBlack", "_burst", "_force", "_cube", "_sword" };

	public ItemChalcedonyLamp(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);

	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (!par3EntityPlayer.capabilities.isCreativeMode) {
			--par1ItemStack.stackSize;
		}

		int i = par2World.rand.nextInt(5);
		int i2 = par2World.rand.nextInt(3);

		int meta = par1ItemStack.getItemDamage();

		if (!par2World.isRemote) {
			if (meta == 9) {
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.blindness.id, 600 * i2, 0));
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 0));
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 600 * i2, i));
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 600 * i2, i));
			}
			if (meta == 8) {
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 600 * i2, 0));
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.resistance.id, 600 * i2, i));
			}
			if (meta == 10 && DCsAppleMilk.absHeal != null) {
				par3EntityPlayer.addPotionEffect(new PotionEffect(DCsAppleMilk.absHeal.id, 600 * i2, i));
			}
			if (meta == 11 && DCsAppleMilk.prvProjectile != null) {
				par3EntityPlayer.addPotionEffect(new PotionEffect(DCsAppleMilk.prvProjectile.id, 600 * i2, 0));
			}
		}

		return par1ItemStack.stackSize <= 0 ? new ItemStack(Items.flint, 1, 0) : par1ItemStack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 32;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.eat;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		int meta = par1ItemStack.getItemDamage();
		if (meta == 4 || meta == 5 || meta == 10)
			par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
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
	public int getMetadata(int par1) {
		return par1;
	}

}
