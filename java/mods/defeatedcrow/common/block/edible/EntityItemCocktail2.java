package mods.defeatedcrow.common.block.edible;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mods.defeatedcrow.api.energy.IBattery;
import mods.defeatedcrow.common.AchievementRegister;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.block.appliance.ItemMachineBlock;
import mods.defeatedcrow.common.config.DCsConfig;
import mods.defeatedcrow.common.entity.edible.PlaceableCocktail2;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityItemCocktail2 extends EdibleEntityItemBlock2 {

	private static final String[] type = new String[] {
			"_mako_tyuhai",
			"_panache",
			"_spritzer",
			"_screw_driver",
			"_god_farther",
			"_tom_and_jerry",
			"_alexander",
			"_zoom",
			"_amaretto_milktea",
			"_snow_saronno" };

	public EntityItemCocktail2(Block block) {
		super(block, false, true);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int m = (par1ItemStack.getItemDamage());
		if (m < 10)
			return super.getUnlocalizedName() + type[m];
		else
			return super.getUnlocalizedName() + m;
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (!par2World.isRemote) {
			this.addSSMoisture(4, 3F, par3EntityPlayer);
		}
		par3EntityPlayer.triggerAchievement(AchievementRegister.drinkCocktail);

		this.formEffect(par2World, par3EntityPlayer, par1ItemStack.getItemDamage());
		return super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
	}

	@Override
	public int[] hungerOnEaten(int meta) {
		return new int[] {
				0,
				0 };
	}

	@Override
	public ArrayList<PotionEffect> effectOnEaten(EntityPlayer par1EntityPlayer, int meta) {
		PotionEffect potion = new PotionEffect(Potion.digSpeed.id, 2400, 2);
		int tick = 2400;
		boolean flag = false;

		ArrayList<PotionEffect> ret = new ArrayList<PotionEffect>();
		ret.add(new PotionEffect(Potion.hunger.id, 300, 1));

		if (meta == 0 && DCsAppleMilk.hallucinations != null)// mako
		{
			if (par1EntityPlayer.isPotionActive(DCsAppleMilk.hallucinations.id)) {
				tick = par1EntityPlayer.getActivePotionEffect(DCsAppleMilk.hallucinations).getDuration() + 1200;
				potion = new PotionEffect(DCsAppleMilk.hallucinations.id, tick, 0);
				flag = true;
			} else {
				potion = new PotionEffect(DCsAppleMilk.hallucinations.id, 1200, 0);
			}
		} else if (meta == 1)// panache
		{
			if (par1EntityPlayer.isPotionActive(Potion.jump.id)) {
				tick = par1EntityPlayer.getActivePotionEffect(Potion.jump).getDuration() + 600;
				potion = new PotionEffect(Potion.jump.id, tick, 0);
				flag = false;
			} else {
				potion = new PotionEffect(Potion.jump.id, 600, 0);
			}
		} else if (meta == 2)// spritzer
		{
			potion = null;
			flag = false;
		} else if (meta == 3)// screwdriver
		{
			if (par1EntityPlayer.isPotionActive(Potion.moveSpeed.id)) {
				tick = par1EntityPlayer.getActivePotionEffect(Potion.moveSpeed).getDuration() + 12000;
				potion = new PotionEffect(Potion.moveSpeed.id, tick, 1);
				flag = true;
			} else {
				potion = new PotionEffect(Potion.moveSpeed.id, 12000, 1);
			}
		} else if (meta == 4 && DCsAppleMilk.prvProjectile != null && DCsAppleMilk.prvExplode != null)// god
																										// farther
		{
			potion = null;
			if (par1EntityPlayer.isPotionActive(DCsConfig.potionIDPrvProjectile)) {
				tick = par1EntityPlayer.getActivePotionEffect(DCsAppleMilk.prvProjectile).getDuration() + 2400;
				ret.add(new PotionEffect(DCsConfig.potionIDPrvProjectile, tick, 0));
				flag = true;
			} else {
				ret.add(new PotionEffect(DCsConfig.potionIDPrvProjectile, 2400, 0));
			}

			if (par1EntityPlayer.isPotionActive(DCsConfig.potionIDPrvExplode)) {
				tick = par1EntityPlayer.getActivePotionEffect(DCsAppleMilk.prvExplode).getDuration() + 2400;
				ret.add(new PotionEffect(DCsConfig.potionIDPrvExplode, tick, 0));
				flag = true;
			} else {
				ret.add(new PotionEffect(DCsConfig.potionIDPrvExplode, 2400, 0));
			}
		} else if (meta == 5)// egg nog
		{
			potion = null;
			flag = false;
		} else if (meta == 6)// alexandra
		{
			if (par1EntityPlayer.isPotionActive(Potion.regeneration.id)) {
				tick = par1EntityPlayer.getActivePotionEffect(Potion.regeneration).getDuration() + 12000;
				potion = new PotionEffect(Potion.regeneration.id, tick, 1);
				flag = false;
			} else {
				potion = new PotionEffect(Potion.regeneration.id, 12000, 1);
			}
		} else if (meta == 7)// zoom
		{
			potion = null;
			flag = false;
		} else if (meta == 8 && DCsAppleMilk.Immunization != null)// amaretto milktea
		{
			if (par1EntityPlayer.isPotionActive(DCsConfig.potionIDImmunity)) {
				tick = par1EntityPlayer.getActivePotionEffect(DCsAppleMilk.Immunization).getDuration() + 12000;
				potion = new PotionEffect(DCsConfig.potionIDImmunity, tick, 1);
				flag = false;
			} else {
				potion = new PotionEffect(DCsConfig.potionIDImmunity, 12000, 1);
			}
		} else if (meta == 9)// snow saronno
		{
			potion = null;
			flag = false;
		}

		if (potion != null)
			ret.add(potion);

		if (flag) {
			ret.add(new PotionEffect(Potion.confusion.id, 300, 1));
		}

		return ret;
	}

	public void formEffect(World world, EntityPlayer player, int meta) {
		if (player == null)
			return;

		if (meta == 0)// mako
		{
			if (player.inventory != null) {
				for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
					ItemStack item = player.inventory.getStackInSlot(i);
					if (item != null && item.getItem() instanceof ItemMachineBlock) {
						NBTTagCompound nbt = item.getTagCompound();
						if (nbt != null) {
							short c = 25600;
							nbt.setShort("charge", c);
						} else {
							NBTTagCompound nbt2 = new NBTTagCompound();
							short c = 25600;
							nbt2.setShort("charge", c);
							item.setTagCompound(nbt2);
						}

					}
					if (item != null && item.getItem() instanceof IBattery) {
						IBattery bat = (IBattery) item.getItem();
						if (bat != null && !bat.isFullCharged(item)) {
							bat.charge(item, 25600, true);
						}

					} else if (item != null && item.getItem() instanceof ItemTool) {
						int max = item.getMaxDamage();
						int current = item.getItemDamage();

						if (current > 0) {
							current -= 200;
							current = Math.min(current, 0);
							item.setItemDamage(current);
						}
					}
				}
			}
		}

		if (meta == 1)// panache
		{
			player.motionY += 5.0D;
		}

		if (meta == 2)// spritzer
		{
			for (int y = -1; y < 2; y++) {
				for (int x = -7; x < 8; x++) {
					for (int z = -7; z < 8; z++) {
						int posX = MathHelper.floor_double(player.posX) + x;
						int posY = MathHelper.floor_double(player.posY) + y;
						int posZ = MathHelper.floor_double(player.posZ) + z;

						if (posY > 0 && posY < 255) {
							Block block = world.getBlock(posX, posY, posZ);
							if (!world.isAirBlock(posX, posY, posZ) && block != null
									&& (block.getMaterial() == Material.plants || block.getMaterial() == Material.vine)) {
								world.setBlockToAir(posX, posY, posZ);
							}
						}
					}
				}
			}
		}

		if (meta == 5)// egg nog
		{
			double posX = player.posX;
			double posY = player.posY;
			double posZ = player.posZ;

			AxisAlignedBB AABB = AxisAlignedBB.getBoundingBox(posX - 8.0D, posY - 1.0D, posZ - 8.0D, posX + 8.0D,
					posY + 1.0D, posZ + 8.0D);

			List list = null;
			list = world.getEntitiesWithinAABB(EntityLivingBase.class, AABB);

			if (list != null && !list.isEmpty()) {
				Iterator iterator = list.iterator();

				while (iterator.hasNext()) {
					Entity entity = (Entity) iterator.next();

					if (entity instanceof EntityMob)// 対象はバニラ敵モブであるEntityMobだけ。
					{
						entity.setDead();
					}
				}
			}
		}

		if (meta == 7)// zoom
		{
			if (!world.isRemote) {
				for (int i = 0; i < 8; i++) {
					int posX = MathHelper.floor_double(player.posX) + world.rand.nextInt(7) - 3;
					int posY = MathHelper.floor_double(player.posY) + world.rand.nextInt(3) - 1;
					int posZ = MathHelper.floor_double(player.posZ) + world.rand.nextInt(7) - 3;

					Block[] flowers = new Block[] {
							Blocks.yellow_flower,
							Blocks.red_flower };
					int type = i & 1;
					int m = world.rand.nextInt(5);

					if (world.isAirBlock(posX, posY, posZ) && world.getBlock(posX, posY - 1, posZ) == Blocks.grass) {
						world.setBlock(posX, posY, posZ, flowers[type], m, 3);
					}
				}
			}
		}

		if (meta == 9)// snow saronno
		{
			WorldInfo worldinfo = world.getWorldInfo();

			worldinfo.setRainTime(1200);
			worldinfo.setRaining(true);
			worldinfo.setThundering(false);
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.drink;
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	protected boolean spownEntityFoods(World world, EntityPlayer player, ItemStack item, double x, double y, double z) {
		PlaceableCocktail2 entity = new PlaceableCocktail2(world, item, x, y, z);
		entity.rotationYaw = player.rotationYaw - 180.0F;

		if (!world.isRemote && item != null) {
			return world.spawnEntityInWorld(entity);
		}

		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
		int l = par1ItemStack.getItemDamage();
		if (l == 0) {
			String s = "Repair the durability of the tools in your inventory.";
			par3List.add(s);
		} else if (l == 1) {
			String s = "Fly high!";
			par3List.add(s);
		} else if (l == 2) {
			String s = "Make a wind, and grass disappear.";
			par3List.add(s);
		} else if (l == 5) {
			String s = "Erase the enemies around you.";
			par3List.add(s);
		} else if (l == 7) {
			String s = "Flowers comes around you.";
			par3List.add(s);
		} else if (l == 9) {
			String s = "Rain will fall, or sometimes it's snow.";
			par3List.add(s);
		}
	}

}
