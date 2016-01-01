package mods.defeatedcrow.common.item;

import java.util.Set;

import mods.defeatedcrow.api.plants.IRightClickHarvestable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStem;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import com.google.common.collect.Sets;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemChalcedonyShears extends ItemTool {

	public static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[] {
			Blocks.pumpkin,
			Blocks.lit_pumpkin,
			Blocks.melon_block,
			Blocks.wool });

	public ItemChalcedonyShears(ToolMaterial par2) {
		super(3.0F, par2, blocksEffectiveAgainst);
		this.setMaxStackSize(1);
	}

	// 多少はハサミと同機能もある
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6,
			EntityLivingBase par7EntityLivingBase) {
		Block ID = par2World.getBlock(par3, par4, par5);
		if (ID != Blocks.leaves && ID != Blocks.web && ID != Blocks.tallgrass && ID != Blocks.vine
				&& ID != Blocks.tripwire && ID != Blocks.pumpkin && ID != Blocks.melon_block
				&& !(ID instanceof IShearable)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean func_150897_b(Block par1Block) {
		return par1Block == Blocks.web || par1Block == Blocks.redstone_wire || par1Block == Blocks.tripwire;
	}

	@Override
	public float func_150893_a(ItemStack par1ItemStack, Block par2Block) {
		return par2Block != Blocks.web && par2Block != Blocks.leaves ? (par2Block == Blocks.wool ? 5.0F : super
				.func_150893_a(par1ItemStack, par2Block)) : 15.0F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("defeatedcrow:tools/chalcedonyshears");
	}

	// メイン処理
	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int X, int Y, int Z, int par7,
			float par8, float par9, float par10) {
		// if (world.isRemote)return false;
		/*
		 * クリックしたブロックの周囲、半径2ブロックの立方体に対して効果を及ぼす。
		 * 一回の動作で、消費する耐久度は1。
		 * ツールのエンチャントの効果を受ける。
		 */

		// まず、アイテムのエンチャント情報やフラグの初期化など
		boolean flag = false;
		int eff = 0;
		int unb = 0;

		// エンチャントのチェック
		if (item.hasTagCompound()) {
			NBTTagList nbttaglist = item.getEnchantmentTagList();
			if (nbttaglist != null) {
				for (int i = 0; i < nbttaglist.tagCount(); ++i) {
					short short1 = nbttaglist.getCompoundTagAt(i).getShort("id");
					short short2 = nbttaglist.getCompoundTagAt(i).getShort("lvl");

					if (short1 == Enchantment.efficiency.effectId) {
						eff = short2;
					}
					if (short1 == Enchantment.unbreaking.effectId) {
						unb = short2;
					}
				}
			}
		}

		// ブロックごとのチェック
		for (int i = -1 - eff; i < 2 + eff; i++) {
			for (int k = -1 - eff; k < 2 + eff; k++) {
				for (int j = -1 - eff; j < 2 + eff; j++) {
					if (Y - j > 0 && Y - j < 255 && !world.isAirBlock(X + i, Y - j, Z + k)) {

						Block block = world.getBlock(X + i, Y - j, Z + k);
						int meta = world.getBlockMetadata(X + i, Y - j, Z + k);
						if (block != null) {
							// その1、ブロックが当MOD追加の収穫可能ブロックである
							if (block instanceof IRightClickHarvestable) {
								IRightClickHarvestable plantsAMT = (IRightClickHarvestable) block;
								// 第七引数はnullでOK
								if (plantsAMT.onHarvest(world, X + i, Y - j, Z + k, player.inventory, null)) {
									flag = true;
								}
							}
							// その2、IGrowable、これは骨粉使用不可であれば成長しきっていると判断し、破壊する。
							if (block instanceof IGrowable) {
								IGrowable crop = (IGrowable) block;
								if (!crop.func_149851_a(world, X + i, Y - j, Z + k, true)
										&& !(crop instanceof BlockStem)) {
									block.dropBlockAsItemWithChance(world, X + i, Y - j, Z + k, meta, 1.0F, 0);
									if (world.setBlockToAir(X + i, Y - j, Z + k)) {
										flag = true;
									}
								}
							}

							// その3、カボチャとスイカと蜘蛛の巣も壊せる
							if (block == Blocks.melon_block || block == Blocks.pumpkin || block == Blocks.web) {
								block.dropBlockAsItemWithChance(world, X + i, Y - j, Z + k, meta, 1.0F, 0);
								if (world.setBlockToAir(X + i, Y - j, Z + k)) {
									flag = true;
								}
							}
						}
					} else {
						continue;
					}
				}
			}
		}

		// ダメージ処理
		if (flag) {
			boolean dam = true;
			if (unb > 0) {
				if (world.rand.nextInt(unb) != 0)
					dam = false;
			}
			if (dam) {
				item.damageItem(1 + eff, player);
			}
			player.inventory.markDirty();
			world.playSoundAtEntity(player, "random.pop", 0.4F, 1.8F);
			return true;
		}

		return false;
	}

}
