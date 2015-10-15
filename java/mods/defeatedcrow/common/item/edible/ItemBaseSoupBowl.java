package mods.defeatedcrow.common.item.edible;

import java.util.List;

import mods.defeatedcrow.api.appliance.SoupType;
import mods.defeatedcrow.common.entity.edible.PlaceableBaseSoup;
import mods.defeatedcrow.plugin.AddonIntegration;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * フォンデュベースのスープを汲んだもの。
 * ベースなので回復量は少ないが、クラフト素材になる。
 */
public class ItemBaseSoupBowl extends EdibleEntityItem2 {

	@SideOnly(Side.CLIENT)
	private IIcon iconType[];
	@SideOnly(Side.CLIENT)
	private IIcon innerType[];

	public final SoupType[] types = new SoupType[] {
			SoupType.WATER,
			SoupType.CHOCO,
			SoupType.OIL,
			SoupType.DASHI,
			SoupType.SHOYU,
			SoupType.TONKOTU,
			SoupType.BLOOD,
			SoupType.PURPLE,
			SoupType.CHEESE };

	public ItemBaseSoupBowl() {
		super(true, false);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setMaxStackSize(64);
		this.setContainerItem(Items.bowl);
	}

	@Override
	public ItemStack getReturnContainer(int meta) {
		return new ItemStack(Items.bowl, 1, 0);
	}

	@Override
	public int[] hungerOnEaten(int meta) {
		return meta == 0 ? new int[] {
				0,
				0 } : new int[] {
				2,
				2 };
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		int m = par1 & 15;
		int j = MathHelper.clamp_int(m, 0, types.length - 1);
		return par1 > 15 ? this.innerType[j] : this.iconType[j];
	}

	@Override
	public int getMetadata(int par1) {
		return par1 & 15;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = par1ItemStack.getItemDamage();
		return m < (types.length) ? super.getUnlocalizedName() + "_" + types[m] : super.getUnlocalizedName() + "_" + m;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(this, 1, 0));
		par3List.add(new ItemStack(this, 1, 1));
		par3List.add(new ItemStack(this, 1, 2));
		par3List.add(new ItemStack(this, 1, 8));
		if (AddonIntegration.loadedJP()) {
			par3List.add(new ItemStack(this, 1, 3));
			par3List.add(new ItemStack(this, 1, 4));
			par3List.add(new ItemStack(this, 1, 5));
		}
		if (AddonIntegration.loadedMagic()) {
			par3List.add(new ItemStack(this, 1, 6));
			par3List.add(new ItemStack(this, 1, 7));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.iconType = new IIcon[types.length];
		this.innerType = new IIcon[types.length];

		for (int i = 0; i < types.length; ++i) {
			this.iconType[i] = par1IconRegister.registerIcon("defeatedcrow:foods/basesoupitem_" + types[i]);
			this.innerType[i] = par1IconRegister.registerIcon("defeatedcrow:contents/basesoup_" + types[i]);
		}
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:foods/basesoupitem_" + types[0]);
	}

	@Override
	protected boolean spownEntityFoods(World world, EntityPlayer player, ItemStack item, double x, double y, double z) {
		PlaceableBaseSoup entity = new PlaceableBaseSoup(world, item, x, y, z);
		entity.rotationYaw = player.rotationYaw - 180.0F;

		if (!world.isRemote && item != null) {
			return world.spawnEntityInWorld(entity);
		}
		return false;
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (!par2World.isRemote) {
			this.addSSMoisture(2, 2F, par3EntityPlayer);
		}
		return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
	}
}
