package mods.defeatedcrow.common.block.edible;

import java.util.ArrayList;
import java.util.List;

import mods.defeatedcrow.api.edibles.IEdibleItem;
import mods.defeatedcrow.api.events.EatEdiblesEvent;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.plugin.SSector.LoadSSectorPlugin;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class EdibleEntityItemBlock extends ItemBlock implements IEdibleItem {

	public boolean allowChopstacks = true;
	public boolean showTooltip = true;

	public EdibleEntityItemBlock(Block block, boolean chopsticks, boolean tip) {
		super(block);
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
				addStatus(par3EntityPlayer, h, par1ItemStack);
			}
		}

		return par1ItemStack;
	}

	protected void addStatus(EntityPlayer player, int[] heal, ItemStack food) {
		player.getFoodStats().addStats(heal[0], heal[1] * 0.1F);
	}

	/**
	 * ガリガリ咀嚼する時間
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 32;
	}

	/**
	 * 飲食時のエフェクト。
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.eat;
	}

	/**
	 * 右クリック動作時に飲食効果を呼び出すメソッド。 <br>
	 * カーソルが特定のブロックをターゲットしている時は呼び出されないので、
	 * 何もない方向を向いておく必要がある。
	 */
	@Override
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
			if (!player.worldObj.isRemote) {
				EntityItem drop = new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, ret);
				player.worldObj.spawnEntityInWorld(drop);
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
	 * 注意点として、ItemFoodのような空腹度回復効果ではなく、
	 * ポーション効果のSaturationを利用して空腹度回復を行っている。
	 */
	@Override
	public ArrayList<PotionEffect> effectOnEaten(EntityPlayer player, int meta) {

		ArrayList<PotionEffect> ret = new ArrayList<PotionEffect>();
		return ret;
	}

	@Override
	public int[] hungerOnEaten(int meta) {
		return new int[] {
				4,
				2 };
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

	// これを継承しているItemBlockは右クリックでモブにポーション効果を与えられる
	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity) {
		if (entity.worldObj.isRemote || entity == null) {
			return false;
		} else {
			ArrayList<PotionEffect> effect = this.effectOnEaten(player, itemstack.getItemDamage());
			ItemStack ret = this.getReturnContainer(itemstack.getItemDamage());

			if (effect != null) {
				for (PotionEffect p : effect) {
					entity.addPotionEffect(p);
				}
			}

			entity.worldObj.playSoundAtEntity(entity, "random.pop", 0.4F, 1.8F);

			if (!player.capabilities.isCreativeMode) {
				--itemstack.stackSize;
			}
			if (ret != null && !player.inventory.addItemStackToInventory(ret)) {
				player.entityDropItem(ret, 1);
			}
			return true;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		int l = par1ItemStack.getItemDamage();
		ArrayList<PotionEffect> effect = this.effectOnEaten(par2EntityPlayer, l);
		if (effect != null && this.showTooltip) {
			for (PotionEffect p : effect) {
				String s = StatCollector.translateToLocal(p.getEffectName()).trim();
				if (p.getAmplifier() > 0) {
					if (p.getAmplifier() < 4) {
						s = s + " " + StatCollector.translateToLocal("potion.potency." + p.getAmplifier()).trim();
					} else {
						s = s + " " + p.getAmplifier();
					}
				}

				if (p.getDuration() > 20) {
					s = s + " (" + Potion.getDurationString(p) + ")";
				}

				par3List.add(s);
			}
		}
	}

	/* ここからEntity/Blockの設置メソッド */

	@Override
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
		} else if (y == 255 && this.field_150939_a.getMaterial().isSolid()) {
			return false;
		} else if (world.canPlaceEntityOnSide(this.field_150939_a, x, y, z, false, side, player, item)) {
			if (DCsConfig.allowEdibleEntities) {
				int m = this.getMetadata(item.getItemDamage());

				if (!world.isRemote) {
					if (this.spownEntityFoods(world, player, new ItemStack(this, 1, m), x + 0.5F, y + 0.0F, z + 0.5F)) {
						world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F,
								this.field_150939_a.stepSound.func_150496_b(),
								(this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F,
								this.field_150939_a.stepSound.getPitch() * 0.8F);
						--item.stackSize;
					}
				}
				return true;
			} else {
				int i1 = this.getMetadata(item.getItemDamage());
				int j1 = this.field_150939_a.onBlockPlaced(world, x, y, z, side, p_77648_8_, p_77648_9_, p_77648_10_,
						i1);

				if (placeBlockAt(item, player, world, x, y, z, side, p_77648_8_, p_77648_9_, p_77648_10_, j1)) {
					world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, this.field_150939_a.stepSound.func_150496_b(),
							(this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F,
							this.field_150939_a.stepSound.getPitch() * 0.8F);
					--item.stackSize;
				}

				return true;
			}
		} else {
			return false;
		}
	}

	// 各Entityクラスで中身をオーバーライドすること。
	protected boolean spownEntityFoods(World world, EntityPlayer player, ItemStack item, double x, double y, double z) {
		return false;
	}

	@Override
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

		return world.canPlaceEntityOnSide(this.field_150939_a, x, y, z, false, side, (Entity) null, item);
	}

}
