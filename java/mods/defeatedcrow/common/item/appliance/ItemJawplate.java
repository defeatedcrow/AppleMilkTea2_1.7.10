package mods.defeatedcrow.common.item.appliance;

import java.util.List;

import mods.defeatedcrow.api.appliance.IJawPlate;
import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemJawplate extends Item implements IJawPlate {

	@SideOnly(Side.CLIENT)
	private IIcon iconType[];
	private static final String[] itemType = new String[] {
			"sterilized",
			"stone",
			"chal",
			"iron",
			"steel",
			"alloy" };

	public ItemJawplate() {
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		int j = MathHelper.clamp_int(par1, 0, 5);
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
		par3List.add(new ItemStack(this, 1, 3));
		par3List.add(new ItemStack(this, 1, 4));
		par3List.add(new ItemStack(this, 1, 5));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {

		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:tools/jawplate_stone");
		this.iconType = new IIcon[6];
		for (int i = 0; i < 6; ++i) {
			this.iconType[i] = par1IconRegister.registerIcon("defeatedcrow:tools/jawplate_" + itemType[i]);
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4) {
		super.addInformation(stack, player, par3List, par4);
		if (stack != null) {
			par3List.add(EnumChatFormatting.BOLD + getTipName(stack.getItemDamage()));
			int i = this.getCount(stack);
			par3List.add(EnumChatFormatting.AQUA + "count :" + i);
			if (DCsAppleMilk.proxy.isShiftKeyDown()) { // shiftキー押下時
				par3List.add("This item is put in the slot of Jaw Crusher.");
				par3List.add("Necessary for high tier recipes.");
			} else {
				par3List.add(EnumChatFormatting.ITALIC + "LShift: Expand tooltip.");
			}
		}
	}

	private String getTipName(int meta) {
		switch (meta) {
		case -1:
			return "For Food Recipe";
		case 0:
			return "No Tier";
		case 1:
			return "Tier 1";
		case 2:
			return "Tier 2";
		case 3:
			return "Tier 2";
		case 4:
			return "Tier 3";
		case 5:
			return "Tier 3";
		default:
			return "No Tier";
		}
	}

	@Override
	public int getTier(ItemStack item) {
		if (item != null) {
			int meta = item.getItemDamage();
			if (this.getCount(item) == 0) {
				return 0;
			}
			switch (meta) {
			case 0:
				return -1;
			case 1:
				return 1;
			case 2:
				return 2;
			case 3:
				return 2;
			case 4:
				return 3;
			case 5:
				return 3;
			}
		}
		return 0;
	}

	@Override
	public ItemStack returnItem(ItemStack item) {
		if (item != null) {
			int tier = getTier(item);
			int meta = item.getItemDamage();
			NBTTagCompound tag = item.getTagCompound();
			if (tag == null)
				tag = new NBTTagCompound();

			int count = 0;
			if (tag.hasKey("dcsJawCount")) {
				count = tag.getInteger("dcsJawCount");
			}
			if (count >= maxCount(meta)) {
				return item;
			} else {
				tag.setInteger("dcsJawCount", count + 1);
				item.setTagCompound(tag);
			}
		}
		return item;
	}

	private int maxCount(int meta) {
		switch (meta) {
		case 0:
			return 64;
		case 1:
			return 256;
		case 2:
			return 64;
		case 3:
			return 512;
		case 4:
			return 1024;
		case 5:
			return 1024;
		default:
			return 64;
		}
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		int i = this.getCount(stack);
		int max = this.maxCount(stack.getItemDamage());
		return 1.0D - ((double) i / (double) max);
	}

	public int getCount(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		int meta = stack.getItemDamage();
		int count = 0;
		int max = this.maxCount(meta);
		if (nbt != null && nbt.hasKey("dcsJawCount")) {
			count = nbt.getInteger("dcsJawCount");
			count = MathHelper.clamp_int(count, 0, max);
		}

		int i = max - count;
		return i;
	}

}
