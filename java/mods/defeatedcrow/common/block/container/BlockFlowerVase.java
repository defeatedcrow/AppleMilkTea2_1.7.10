package mods.defeatedcrow.common.block.container;

import java.util.List;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFlowerVase extends Block {

	private static final String[] leaves = new String[] {
			"rose",
			"peony",
			"lilac",
			"sun" };

	@SideOnly(Side.CLIENT)
	private IIcon[] baseTex;
	@SideOnly(Side.CLIENT)
	private IIcon[] leafTex;

	public BlockFlowerVase() {
		super(Material.wood);
		this.setStepSound(Block.soundTypeGrass);
		this.setHardness(0.1F);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return DCsAppleMilk.modelFlowerVase;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par2 & 3;
		if (par1 == 0) {
			return this.leafTex[i];
		} else if (par1 == 1) {
			return this.baseTex[i];
		} else {
			return this.baseTex[0];
		}

	}

	@Override
	public int damageDropped(int par1) {
		return par1 & 3;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
		par3List.add(new ItemStack(par1, 1, 3));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "hedge/hedge_base1");
		this.baseTex = new IIcon[4];
		this.leafTex = new IIcon[4];

		for (int i = 0; i < 4; ++i) {
			this.baseTex[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "flowers/flower_" + leaves[i]
					+ "_b");
		}

		for (int i = 0; i < 4; ++i) {
			this.leafTex[i] = par1IconRegister.registerIcon(Util.getTexturePassNoAlt() + "flowers/flower_" + leaves[i]
					+ "_t");
		}

	}

}
