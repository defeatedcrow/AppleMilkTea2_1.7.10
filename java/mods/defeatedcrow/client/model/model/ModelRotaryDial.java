package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelRotaryDial extends ModelBase {
	// fields
	ModelRenderer bottom;
	ModelRenderer middle;
	ModelRenderer back;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer handle;
	ModelRenderer handle2;
	ModelRenderer handle3;
	ModelRenderer plate;

	public ModelRotaryDial() {
		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-4.5F, 0F, -5F, 9, 4, 10);
		bottom.setRotationPoint(0F, 20F, 0F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		middle = new ModelRenderer(this, 0, 0);
		middle.addBox(-4.5F, 0F, -5F, 9, 4, 9);
		middle.setRotationPoint(0F, 18F, 0F);
		middle.setTextureSize(64, 32);
		middle.mirror = true;
		setRotation(middle, 0.418879F, 0F, 0F);
		back = new ModelRenderer(this, 0, 0);
		back.addBox(-4.5F, 0F, 3F, 9, 7, 4);
		back.setRotationPoint(0F, 17F, 0F);
		back.setTextureSize(64, 32);
		back.mirror = true;
		setRotation(back, 0F, 0F, 0F);
		leg1 = new ModelRenderer(this, 0, 0);
		leg1.addBox(2F, 0F, 4F, 1, 1, 2);
		leg1.setRotationPoint(0F, 16F, 0F);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 0, 0);
		leg2.addBox(-3F, 0F, 4F, 1, 1, 2);
		leg2.setRotationPoint(0F, 16F, 0F);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, 0F);
		handle = new ModelRenderer(this, 0, 15);
		handle.addBox(-5F, 0F, 4F, 10, 1, 2);
		handle.setRotationPoint(0F, 15F, 0F);
		handle.setTextureSize(64, 32);
		handle.mirror = true;
		setRotation(handle, 0F, 0F, 0F);
		handle2 = new ModelRenderer(this, 0, 0);
		handle2.addBox(5F, 0F, 3F, 3, 4, 4);
		handle2.setRotationPoint(0F, 15F, 0F);
		handle2.setTextureSize(64, 32);
		handle2.mirror = true;
		setRotation(handle2, 0F, 0F, 0F);
		handle3 = new ModelRenderer(this, 0, 0);
		handle3.addBox(-8F, 0F, 3F, 3, 4, 4);
		handle3.setRotationPoint(0F, 15F, 0F);
		handle3.setTextureSize(64, 32);
		handle3.mirror = true;
		setRotation(handle3, 0F, 0F, 0F);
		plate = new ModelRenderer(this, 0, 19);
		plate.addBox(-4F, 0.5F, -5F, 8, 0, 8);
		plate.setRotationPoint(0F, 17F, 0F);
		plate.setTextureSize(64, 32);
		plate.mirror = true;
		setRotation(plate, 0.418879F, 0F, 0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7,
			byte par8) {
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		this.bottom.render(par7);
		this.middle.render(par7);
		this.back.render(par7);
		this.handle.render(par7);
		this.handle2.render(par7);
		this.handle3.render(par7);
		this.leg1.render(par7);
		this.leg2.render(par7);
		this.plate.render(par7);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.bottom.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.middle.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.back.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.leg1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.leg2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.handle.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.handle2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.handle3.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.plate.rotateAngleY = f3 / (180F / (float) Math.PI);
	}
}
