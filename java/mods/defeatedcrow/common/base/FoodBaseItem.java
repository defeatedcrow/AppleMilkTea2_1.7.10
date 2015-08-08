package mods.defeatedcrow.common.base;

import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.common.item.edible.EdibleEntityItem2;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * AMT2のソースを利用した飲食アイテムの一例。
 */
public class FoodBaseItem extends EdibleEntityItem2 {

	public FoodBaseItem(boolean canSeeTooltip) {
		super(true, canSeeTooltip);
	}

	/** 空腹度回復量 */
	@Override
	public int[] hungerOnEaten(int meta) {
		return new int[] {
				4,
				2 };
	}

	/**
	 * 食べ物にポーション効果を付けたい場合に使用する。 <br>
	 * バニラと異なり、複数の効果を同時に付与できる。
	 */
	@Override
	public ArrayList<PotionEffect> effectOnEaten(EntityPlayer par1EntityPlayer, int meta) {
		ArrayList<PotionEffect> list = new ArrayList<PotionEffect>();
		return list;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		return this.itemIcon;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(this, 1, 0));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:appletart");
	}

	/**
	 * 対応する食べ物Entityをスポーンさせる部分。 <br>
	 * Entityが無い場合はfalseを返す。
	 */
	@Override
	protected boolean spownEntityFoods(World world, EntityPlayer player, ItemStack item, double x, double y, double z) {
		return false;
	}

	/**
	 * ポーション効果以外に飲食時の効果を付けたい場合にここに入れる
	 */
	@Override
	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player) {
		return super.onEaten(itemStack, world, player);
	}

}
