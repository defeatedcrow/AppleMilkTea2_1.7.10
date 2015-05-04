package mods.defeatedcrow.common.item.appliance;

import java.util.List;

import mods.defeatedcrow.api.charge.ChargeItemManager;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.PropertyHandler;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBattery extends Item {

	@SideOnly(Side.CLIENT)
	private IIcon iconType[];

	private static final String[] icon = new String[] { "disposable", "disposable_adv", "yuzu" };

	public ItemBattery() {
		super();
		this.setMaxStackSize(64);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		int j = MathHelper.clamp_int(par1, 0, 2);
		return this.iconType[j];
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
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.iconType = new IIcon[3];

		for (int i = 0; i < 3; ++i) {
			this.iconType[i] = par1IconRegister.registerIcon("defeatedcrow:battery_" + icon[i]);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		int c = 0;
		c = ChargeItemManager.chargeItem.getChargeAmount(par1ItemStack);
		if (c > 0) {
			par3List.add("Charge Amount : " + c);
			if (DCsAppleMilk.proxy.isShiftKeyDown()) { // shiftキー押下時
				int vsRF = c * PropertyHandler.rateRF();
				int vsEU = c * PropertyHandler.rateEU();
				int vsGF = c * PropertyHandler.rateGF();
				par3List.add(vsRF + " RF");
				par3List.add(vsEU + " EU");
				par3List.add(vsGF + " GF");
			} else {
				par3List.add(EnumChatFormatting.ITALIC + "LShift: Expand tooltip.");
			}
		}
	}

}
