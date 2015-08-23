package mods.defeatedcrow.common.block.plants;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTeaSapling extends ItemBlock {

	private static final String[] type = new String[] {
			"_tea",
			"_cassis",
			"_camellia" };

	@SideOnly(Side.CLIENT)
	private IIcon iconItemType[];

	public ItemTeaSapling(Block block) {
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);

	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		if (m < 3)
			return super.getUnlocalizedName() + type[m];
		else
			return super.getUnlocalizedName() + m;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		int j = MathHelper.clamp_int(par1, 0, 2);
		return this.field_150939_a.getIcon(0, par1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.iconItemType = new IIcon[3];

		for (int i = 0; i < 3; ++i) {
			this.iconItemType[i] = par1IconRegister.registerIcon("defeatedcrow:sapling" + type[i]);
		}
	}

}
