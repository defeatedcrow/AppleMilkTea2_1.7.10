package mods.defeatedcrow.common.item.edible;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import mods.defeatedcrow.api.edibles.IEdibleItem;
import mods.defeatedcrow.api.events.EatEdiblesEvent;
import mods.defeatedcrow.client.entity.IEdibleRenderHandler;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.entity.edible.PlaceableFoods;
import mods.defeatedcrow.plugin.SSector.LoadSSectorPlugin;

public class EdibleEntityItem extends Item implements IEdibleItem {

	public boolean allowChopstacks = true;
	public boolean showTooltip = true;

	public EdibleEntityItem(boolean chopsticks, boolean tip) {
		super();
		this.allowChopstacks = chopsticks;
		this.showTooltip = tip;
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		int meta = par1ItemStack.getItemDamage();
		boolean flag = false;
		EatEdiblesEvent event = new EatEdiblesEvent(par2World, par3EntityPlayer, par1ItemStack);

		MinecraftForge.EVENT_BUS.post(event);

		if (event.hasResult() && event.getResult() == Result.ALLOW) {
			if (!par3EntityPlayer.capabilities.isCreativeMode) {
				--par1ItemStack.stackSize;
				this.returnItemStack(par3EntityPlayer, meta);
			}
			flag = true;
		}

		if (event.isCanceled()) {
			return par1ItemStack;
		}

		if (!flag && !par3EntityPlayer.capabilities.isCreativeMode) {
			--par1ItemStack.stackSize;
			this.returnItemStack(par3EntityPlayer, meta);
		}

		if (!par2World.isRemote) {
			if (this.effectOnEaten(par3EntityPlayer, meta) != null) {
				ArrayList<PotionEffect> potion = this.effectOnEaten(par3EntityPlayer, meta);
				if (potion != null && !potion.isEmpty()) {
					for (PotionEffect ret : potion) {
						par3EntityPlayer.addPotionEffect(ret);
					}
				}
			}

			if (this.hungerOnEaten(meta) != null) {
				int[] h = this.hungerOnEaten(meta);
				par3EntityPlayer.getFoodStats().addStats(h[0], h[1] * 0.1F);
			}
		}

		return par1ItemStack;
	}

	/**
	 * ガリガリ咀嚼する時間
	 */
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 32;
	}

	/**
	 * 飲食時のエフェクト。
	 */
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.eat;
	}

	/**
	 * 右クリック動作時に飲食効果を呼び出すメソッド。 <br>
	 * カーソルが特定のブロックをターゲットしている時は呼び出されないので、
	 * 何もない方向を向いておく必要がある。
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	/**
	 * 空容器の返却を行うメソッド。
	 */
	protected boolean returnItemStack(EntityPlayer player, int meta) {
		ItemStack ret = this.getReturnContainer(meta);
		if (ret != null) {
			if (!player.inventory.addItemStackToInventory(ret)) {
				player.entityDropItem(ret, 1);
				return true;
			}
		}
		return false;
	}

	/**
	 * 返却される空容器をメタデータ毎に定義する。
	 */
	@Override
	public ItemStack getReturnContainer(int meta) {

		return null;
	}

	/**
	 * 飲食時のポーション効果をメタデータ毎に定義する。 <br>
	 * 空腹度回復効果とは別途で発生する。
	 */
	@Override
	public ArrayList<PotionEffect> effectOnEaten(EntityPlayer player, int meta) {

		ArrayList<PotionEffect> ret = new ArrayList<PotionEffect>();
		return ret;
	}

	/**
	 * 空腹度回復量を定義する。
	 */
	@Override
	public int[] hungerOnEaten(int meta) {
		return new int[] { 4, 2 };
	}

	/**
	 * SextiarySector導入時にのみはたらくメソッド。
	 * 水分やスタミナが増減する。 <br>
	 * int i にマイナス数値が入れば自動的に減少メソッドになる。
	 */
	protected void addSSMoisture(int i, float f, EntityPlayer par3EntityPlayer) {
		if (DCsAppleMilk.SuccessLoadSSector) {
			LoadSSectorPlugin.addStatus(i, f, 0, 0, par3EntityPlayer);
		}
	}

	protected void addSSStamina(int i, float f, EntityPlayer par3EntityPlayer) {
		if (DCsAppleMilk.SuccessLoadSSector) {
			LoadSSectorPlugin.addStatus(0, 0, i, f, par3EntityPlayer);
		}
	}

	/* ここからEntity/Blockの設置メソッド */

	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side,
			float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		if (player != null && player.isSneaking()) {
			this.onItemRightClick(item, world, player);
			return false;
		}

		Block block = world.getBlock(x, y, z);

		if (block == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1) {
			side = 1;
		} else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush
				&& !block.isReplaceable(world, x, y, z)) {
			if (side == 0) {
				--y;
			}

			if (side == 1) {
				++y;
			}

			if (side == 2) {
				--z;
			}

			if (side == 3) {
				++z;
			}

			if (side == 4) {
				--x;
			}

			if (side == 5) {
				++x;
			}
		}

		if (item.stackSize == 0) {
			return false;
		} else if (!player.canPlayerEdit(x, y, z, side, item)) {
			return false;
		} else if (y >= 255) {
			return false;
		} else {
			if (DCsConfig.allowEdibleEntities) {
				int m = this.getMetadata(item.getItemDamage());

				if (!world.isRemote) {
					if (this.spownEntityFoods(world, player, new ItemStack(this, 1, m), (double) ((float) x + 0.5F),
							(double) ((float) y + 0.0F), (double) ((float) z + 0.5F))) {
						world.playSoundEffect((double) ((float) x + 0.5F), (double) ((float) y + 0.5F),
								(double) ((float) z + 0.5F), block.stepSound.func_150496_b(),
								(block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
						--item.stackSize;
					}
				}
				return true;
			} else {
				return false;
			}
		}
	}

	// 各Entityクラスで中身をオーバーライドすること。
	protected boolean spownEntityFoods(World world, EntityPlayer player, ItemStack item, double x, double y, double z) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public boolean func_150936_a(World world, int x, int y, int z, int side, EntityPlayer player, ItemStack item) {
		Block block = world.getBlock(x, y, z);

		if (block == Blocks.snow_layer) {
			side = 1;
		} else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush
				&& !block.isReplaceable(world, x, y, z)) {
			if (side == 0) {
				--y;
			}

			if (side == 1) {
				++y;
			}

			if (side == 2) {
				--z;
			}

			if (side == 3) {
				++z;
			}

			if (side == 4) {
				--x;
			}

			if (side == 5) {
				++x;
			}
		}

		return world.canPlaceEntityOnSide(block, x, y, z, false, side, (Entity) null, item);
	}

}
