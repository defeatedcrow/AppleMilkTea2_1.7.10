package mods.defeatedcrow.common.entity;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.defeatedcrow.api.edibles.IEdibleItem;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.entity.edible.PlaceableFoods;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityKinoko extends PlaceableFoods {

	public EntityKinoko(World world) {
		super(world);
	}

	public EntityKinoko(World world, boolean chops, ItemStack item) {
		super(world, chops, item);
	}

	public EntityKinoko(World world, boolean chops, ItemStack item, double x, double y, double z) {
		super(world, chops, item, x, y, z);
	}

	@Override
	protected ItemStack returnItem() {
		return new ItemStack(DCsAppleMilk.mushroomBox, 1, this.getItemMetadata());
	}

	@Override
	public void updateRiderPosition() {
		if (this.riddenByEntity != null) {
			double d0 = Math.cos((double) this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			double d1 = Math.sin((double) this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			this.riddenByEntity.setPosition(this.posX,
					this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ);
		}
	}

	@Override
	public double getMountedYOffset() {
		return (double) this.height * 0.75D;
	}

	@Override
	public boolean interactFirst(EntityPlayer par1EntityPlayer) {
		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer
				&& this.riddenByEntity != par1EntityPlayer) {
			return true;
		} else {
			if (!this.worldObj.isRemote) {
				par1EntityPlayer.mountEntity(this);
			}

			return true;
		}

	}

}
