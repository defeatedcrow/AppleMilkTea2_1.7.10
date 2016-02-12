package mods.defeatedcrow.event;

import java.util.ArrayList;
import java.util.Iterator;

import mods.defeatedcrow.api.potion.PotionImmunityBase;
import mods.defeatedcrow.api.potion.PotionLivingBase;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.item.magic.ItemPrincessClam;
import mods.defeatedcrow.handler.Coord;
import mods.defeatedcrow.handler.CoordListRegister;
import mods.defeatedcrow.network.DCsNetworkHandler;
import mods.defeatedcrow.network.MessageCharmWarp;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.living.LivingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DCsLivingEvent {

	private boolean keyDown = false;

	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
		Entity entity = event.entity;

		// やっつけ仕事なので、プレイヤーを常時監視して、抵抗力ポーションが働いているかを見張っている
		if ((entity instanceof EntityPlayer)) {
			EntityPlayer player = (EntityPlayer) event.entity;

			ArrayList<PotionEffect> immunities = new ArrayList<PotionEffect>();

			if (player != null) {
				// PotionEffectのリスト
				Iterator iterator = player.getActivePotionEffects().iterator();

				while (iterator.hasNext()) {
					PotionEffect effect = (PotionEffect) iterator.next();

					int id = effect.getPotionID();
					Potion potion = Potion.potionTypes[id];

					if (potion != null && potion instanceof PotionImmunityBase) {
						immunities.add(effect);
					}
				}

				for (PotionEffect eff : immunities) {
					Potion potion = Potion.potionTypes[eff.getPotionID()];
					int amp = eff.getAmplifier();
					int dur = eff.getDuration();

					if (potion != null && potion instanceof PotionImmunityBase) {
						PotionImmunityBase immunity = (PotionImmunityBase) potion;

						if (immunity.preventPotion(amp, immunity.id, player)) {
							AMTLogger.debugInfo("Succeeded to prevent bad status by PotionImmunity effect.");
						}
					}
				}

				if (player.worldObj.isRemote && !keyDown) {
					if (DCsAppleMilk.proxy.isWarpKeyDown()) {
						keyDown = true;
						ItemStack charm = null;
						boolean warp = false;
						int limit = -1;
						int x = 0;
						int y = 0;
						int z = 0;
						int dim = 0;

						for (int i = 0; i < 9; i++) {
							ItemStack check = player.inventory.getStackInSlot(i);
							if (check != null && check.getItem() != null
									&& check.getItem() == DCsAppleMilk.princessClam) {
								charm = check;
								break;
							}
						}

						if (charm != null) {
							if (charm.getItemDamage() == 3) {
								NBTTagCompound nbt = charm.getTagCompound();
								if (nbt != null && nbt.hasKey("DCsCharm")) {
									byte mode = nbt.getByte("DCsCharm");
									if (nbt.hasKey("DClimit")) {
										int lim = nbt.getInteger("DClimit");
									}
									if (mode == 1) {
										String name = nbt.getString("DCtargetName");
										EntityPlayer target = player.worldObj.getPlayerEntityByName(name);
										if (target != null) {
											int X = MathHelper.floor_double(target.posX);
											int Y = MathHelper.floor_double(target.posY) - 1;
											int Z = MathHelper.floor_double(target.posZ);
											String DimName = target.worldObj.provider.getDimensionName();

											for (int ix = 0; ix < 3; ix++) {
												for (int iz = 0; iz < 3; iz++) {
													if (player.worldObj.isAirBlock(X + ix - 1, Y + 1, Z + iz - 1)
															&& player.worldObj
																	.isAirBlock(X + ix - 1, Y + 2, Z + iz - 1)
															&& player.worldObj.isSideSolid(X + ix - 1, Y, Z + iz - 1,
																	ForgeDirection.UP)) {
														x = X + ix - 1;
														z = Z + iz - 1;
														y = Y;
														warp = target.worldObj.provider.getDimensionName()
																.equalsIgnoreCase(
																		player.worldObj.provider.getDimensionName());
													}
												}
											}
										}
									}
									if (mode == 2) {
										x = nbt.getInteger("DCposX");
										y = nbt.getInteger("DCposY");
										z = nbt.getInteger("DCposZ");
										dim = nbt.getInteger("DCdim");
										if (player.worldObj.provider.dimensionId == dim
												&& player.worldObj.isSideSolid(x, y, z, ForgeDirection.UP)) {
											warp = true;
										}
									}
								}

							} else if (charm.getItemDamage() == 4) {
								int X = MathHelper.floor_double(player.posX);
								int Y = MathHelper.floor_double(player.posY);
								int Z = MathHelper.floor_double(player.posZ);

								for (int i = 1; i < 128; i++) {
									if (Y + i < 1 || Y + i > 255) {
										break;
									}

									if (ItemPrincessClam.moonCanWarp(player.worldObj, X, Y + i, Z)
											&& player.worldObj.isAirBlock(X, Y + i + 2, Z)) {
										if (player.worldObj.isAirBlock(X, Y + i + 1, Z)) {
											y = Y + i;
											warp = true;
											x = X;
											z = Z;
											break;
										} else if (player.worldObj.getBlock(X, Y + i + 1, Z).getMaterial() == Material.water
												|| player.worldObj.getBlock(X, Y + i + 1, Z).getMaterial() == Material.plants
												|| player.worldObj.getBlock(X, Y + i + 1, Z).getMaterial() == Material.snow) {
											y = Y + i;
											warp = true;
											x = X;
											z = Z;
											break;
										}
									}
								}

							}

							if (warp) {
								AMTLogger.debugInfo("Warp pos : " + x + ", " + y + ", " + z);
								DCsNetworkHandler.INSTANCE.sendToServer(new MessageCharmWarp(x, y, z));
							}
						}
					}
				}

				if (keyDown && !DCsAppleMilk.proxy.isWarpKeyDown()) {
					keyDown = false;
				}
			}

		}

		// こちらはEntityLivingBaseの監視用
		if ((entity instanceof EntityLivingBase)) {
			EntityLivingBase living = (EntityLivingBase) event.entity;

			ArrayList<PotionEffect> potions = new ArrayList<PotionEffect>();

			if (living != null && !living.worldObj.isRemote) {

				boolean f = true;
				if (living instanceof EntityLiving && ((EntityLiving) living).hasCustomNameTag()) {

				} else {
					if (living instanceof IMob) {
						f = false;
					} else if (living.riddenByEntity != null && living.riddenByEntity instanceof IMob) {
						f = false;
					} else if (living.ridingEntity != null && living.ridingEntity instanceof IMob) {
						f = false;
					}
				}

				if (!f) {
					int x = MathHelper.floor_double(living.posX);
					int y = MathHelper.floor_double(living.posY);
					int z = MathHelper.floor_double(living.posZ);
					int cX = x >> 4;
					int cZ = z >> 4;
					Coord cood = new Coord(cX, cZ, living.worldObj.provider.dimensionId);
					if (CoordListRegister.isCoodIncluded(cood)) {
						if (living.riddenByEntity != null) {
							living.riddenByEntity.setDead();
						}
						if (living.ridingEntity != null) {
							living.ridingEntity.setDead();
						}
						living.setDead();
					} else {
						f = true;
					}
				}

				if (f) {
					// PotionEffectのリスト
					Iterator iterator = living.getActivePotionEffects().iterator();

					while (iterator.hasNext()) {
						PotionEffect effect = (PotionEffect) iterator.next();

						int id = effect.getPotionID();
						Potion potion = Potion.potionTypes[id];

						if (potion != null && potion instanceof PotionLivingBase) {
							potions.add(effect);
						}

						if (potion != null && potion.id == potion.jump.id) {
							living.fallDistance = 0.0F;
						}

						if (living.ridingEntity != null && living.ridingEntity instanceof EntityLivingBase) {
							EntityLivingBase riding = (EntityLivingBase) event.entity.ridingEntity;
							if (potion != null) {
								riding.addPotionEffect(effect);
							}
						}

					}

					for (PotionEffect eff : potions) {
						Potion potion = Potion.potionTypes[eff.getPotionID()];
						int amp = eff.getAmplifier();
						int dur = eff.getDuration();

						if (potion != null && potion instanceof PotionLivingBase) {
							PotionLivingBase immunity = (PotionLivingBase) potion;

							if (immunity.formPotionEffect(amp, immunity.id, living)) {
								AMTLogger.debugInfo("Succeeded to form effect of PotionLivingBase.");
							}
						}
					}
				}
			}
		}
	}
}
