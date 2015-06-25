package mods.defeatedcrow.common.block.brewing;

import java.util.List;
import java.util.Random;

import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.tile.TileCordial;
import mods.defeatedcrow.handler.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/*
 * Cordialの中身はメタデータに任せている。
 * 熟成の度合い等はTileEntityにて管理。
 * 破壊時にNBTに各種データを移し替える。
 */
public class BlockCordial extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon contentsIIcon[];// 中身テクスチャ
	@SideOnly(Side.CLIENT)
	private IIcon drinkIIcon[];// 中身テクスチャ

	private static String[] type = new String[] { "apple", "tea", "cassis", "plum", "apricot" };

	public BlockCordial() {
		super(Material.glass);
		this.setStepSound(Block.soundTypeGlass);
		this.setHardness(0.2F);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {
		ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		TileCordial tile = (TileCordial) par1World.getTileEntity(par2, par3, par4);

		if (tile == null || par1World.isRemote) {
			return false;
		} else {
			int age = tile.getAgingStage();
			boolean flag = tile.getAged();
			boolean flag2 = tile.isDryBiome() || par1World.canBlockSeeTheSky(par2, par3, par4);

			String s = "Cordial bottle : ";
			if (flag) {
				s = s + "fermented";
			} else {
				s = s + "passed " + age + " days";
			}
			par5EntityPlayer.addChatMessage(new ChatComponentText(s));
			par5EntityPlayer.addChatMessage(new ChatComponentText("Type : " + this.type[meta]));
			if (flag2) {
				par5EntityPlayer.addChatMessage(new ChatComponentText(
						"This place is not suitable for creating a cordial."));
			}
			return true;
		}
	}

	// 設置
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		short l = (short) par6ItemStack.getItemDamage();

		int newMeta = l >> 2;// Blockのメタデータは、Itemのダメージ値の3桁目以降に入っている
		int rem = l & 3;
		boolean aged = (rem != 0);

		NBTTagCompound nbt = par6ItemStack.getTagCompound();
		int stage = 0;

		if (nbt != null && !aged) {
			stage = nbt.getByte("stage");
		}

		par1World.setBlockMetadataWithNotify(par2, par3, par4, newMeta, 3);
		// メタデータ自体はタイプに対応している。RenderBlockクラスでメタデータごとのテクスチャを振り分けるためだが、他にも方法はある気がする。

		TileCordial tile = (TileCordial) par1World.getTileEntity(par2, par3, par4);
		if (tile != null)
			tile.setAgingStage(stage);
	}

	// 破壊
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
		TileCordial tile = (TileCordial) par1World.getTileEntity(par2, par3, par4);

		if (tile != null) {
			// ブロックのメタデータとTileのShort値から新しいダメージ値を生成
			int rem = (tile.getAged()) ? 3 : 0;
			int l = tile.getAgingStage();
			int type = par1World.getBlockMetadata(par2, par3, par4);
			int damage = (par6 << 2) + rem;

			float f = par1World.rand.nextFloat() * 0.8F + 0.1F;
			float f1 = par1World.rand.nextFloat() * 0.8F + 0.1F;
			float f2 = par1World.rand.nextFloat() * 0.8F + 0.1F;

			// アイテム版の瓶をドロップ
			ItemStack itemstack = new ItemStack(DCsAppleMilk.itemCordial, 1, damage);

			if (rem == 0 && l > 0)// 熟成未完了
			{
				NBTTagCompound nbt = itemstack.getTagCompound();

				if (nbt == null) {
					nbt = new NBTTagCompound();
					nbt.setByte("stage", (byte) l);
					itemstack.setTagCompound(nbt);
				}
			}

			EntityItem entityitem = new EntityItem(par1World, (double) ((float) par2 + f),
					(double) ((float) par3 + f1), (double) ((float) par4 + f2), itemstack);

			float f3 = 0.05F;
			entityitem.motionX = (double) ((float) par1World.rand.nextGaussian() * f3);
			entityitem.motionY = (double) ((float) par1World.rand.nextGaussian() * f3 + 0.2F);
			entityitem.motionZ = (double) ((float) par1World.rand.nextGaussian() * f3);
			par1World.spawnEntityInWorld(entityitem);

			par1World.func_147453_f(par2, par3, par4, par5);
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		return false;
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
		return DCsAppleMilk.modelCordial;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		this.thisBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	public void thisBoundingBox(int par1) {
		float f = 0.25F;
		this.setBlockBounds(0.0F + f, 0.0F, 0.0F + f, 1.0F - f, 1.0F, 1.0F - f);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = Math.min(par2, 4);
		return par1 == 0 ? this.contentsIIcon[i] : this.drinkIIcon[i];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		// par3List.add(new ItemStack(this, 1, 0));
		// par3List.add(new ItemStack(this, 1, 1));
		// par3List.add(new ItemStack(this, 1, 2));
		// par3List.add(new ItemStack(this, 1, 3));
		// par3List.add(new ItemStack(this, 1, 4));
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IIconRegister) {
		this.blockIcon = par1IIconRegister.registerIcon(Util.getTexturePassNoAlt() + "cordial_drink");
		this.drinkIIcon = new IIcon[5];
		this.contentsIIcon = new IIcon[5];
		for (int i = 0; i < 5; ++i) {
			this.drinkIIcon[i] = par1IIconRegister.registerIcon("defeatedcrow:cordial_drink_" + this.type[i]);
			this.contentsIIcon[i] = par1IIconRegister.registerIcon("defeatedcrow:cordial_inner_" + this.type[i]);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileCordial();
	}
}
