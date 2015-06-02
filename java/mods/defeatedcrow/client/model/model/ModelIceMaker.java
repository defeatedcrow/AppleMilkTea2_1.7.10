package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelIceMaker extends ModelBase {
	// fields
	ModelRenderer bottom = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
	ModelRenderer leg1 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
	ModelRenderer leg2 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
	ModelRenderer leg3 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
	ModelRenderer leg4 = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 32);
	ModelRenderer boad1 = (new ModelRenderer(this, 0, 11)).setTextureSize(64, 32);
	ModelRenderer boad2 = (new ModelRenderer(this, 0, 11)).setTextureSize(64, 32);
	ModelRenderer middle = (new ModelRenderer(this, 0, 20)).setTextureSize(64, 32);
	ModelRenderer ice = (new ModelRenderer(this, 32, 0)).setTextureSize(64, 32);
	ModelRenderer axle = (new ModelRenderer(this, 32, 12)).setTextureSize(64, 32);
	ModelRenderer handle1 = (new ModelRenderer(this, 36, 12)).setTextureSize(64, 32);
	ModelRenderer handle2 = (new ModelRenderer(this, 36, 12)).setTextureSize(64, 32);
	ModelRenderer handle3 = (new ModelRenderer(this, 36, 12)).setTextureSize(64, 32);
	ModelRenderer handle4 = (new ModelRenderer(this, 36, 12)).setTextureSize(64, 32);
	ModelRenderer handle5 = (new ModelRenderer(this, 36, 12)).setTextureSize(64, 32);
	ModelRenderer handle6 = (new ModelRenderer(this, 36, 12)).setTextureSize(64, 32);

	public ModelIceMaker() {

		bottom.addBox(-5F, 0F, -5F, 10, 1, 10);
		bottom.setRotationPoint(0F, 23F, 0F);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		leg1.addBox(-4.5F, -6F, -4.5F, 1, 8, 1);
		leg1.setRotationPoint(0F, 23F, 0F);
		leg1.mirror = true;
		setRotation(leg1, -0.1396263F, 0F, 0.1396263F);
		leg2.addBox(3.5F, -6F, -4.5F, 1, 8, 1);
		leg2.setRotationPoint(0F, 23F, 0F);
		leg2.mirror = true;
		setRotation(leg2, -0.1396263F, 0F, -0.1396263F);
		leg3.addBox(-4.5F, -6F, 3.5F, 1, 8, 1);
		leg3.setRotationPoint(0F, 23F, 0F);
		leg3.mirror = true;
		setRotation(leg3, 0.1396263F, 0F, 0.1396263F);
		leg4.addBox(3.5F, -6F, 3.5F, 1, 8, 1);
		leg4.setRotationPoint(0F, 23F, 0F);
		leg4.mirror = true;
		setRotation(leg4, 0.1396263F, 0F, -0.1396263F);
		boad1.addBox(-4F, 0F, -4F, 8, 1, 8);
		boad1.setRotationPoint(0F, 16F, 0F);
		boad1.mirror = true;
		setRotation(boad1, 0F, 0F, 0F);
		boad2.addBox(-4F, 0F, -4F, 8, 1, 8);
		boad2.setRotationPoint(0F, 11F, 0F);
		boad2.mirror = true;
		setRotation(boad2, 0F, 0F, 0F);
		middle.addBox(-4F, 0F, -4F, 8, 4, 8);
		middle.setRotationPoint(0F, 12F, 0F);
		middle.mirror = true;
		setRotation(middle, 0F, 0F, 0F);
		ice.addBox(-2F, 0F, -2F, 4, 4, 4);
		ice.setRotationPoint(0F, 12F, 0F);
		ice.mirror = true;
		setRotation(ice, 0F, 0F, 0F);
		axle.addBox(-0.5F, 0F, -0.5F, 1, 5, 1);
		axle.setRotationPoint(0F, 8F, 0F);
		axle.mirror = true;
		setRotation(axle, 0F, 0F, 0F);
		handle1.addBox(-2F, 0F, -3F, 4, 1, 1);
		handle1.setRotationPoint(0F, 8F, 0F);
		handle1.mirror = true;
		setRotation(handle1, 0F, 0F, 0F);
		handle2.addBox(-2F, 0F, 2F, 4, 1, 1);
		handle2.setRotationPoint(0F, 8F, 0F);
		handle2.mirror = true;
		setRotation(handle2, 0F, 0F, 0F);
		handle3.addBox(2F, 0F, -2F, 1, 1, 4);
		handle3.setRotationPoint(0F, 8F, 0F);
		handle3.mirror = true;
		setRotation(handle3, 0F, 0F, 0F);
		handle4.addBox(-3F, 0F, -2F, 1, 1, 4);
		handle4.setRotationPoint(0F, 8F, 0F);
		handle4.mirror = true;
		setRotation(handle4, 0F, 0F, 0F);
		handle5.addBox(-0.5F, 0F, -2F, 1, 1, 4);
		handle5.setRotationPoint(0F, 8F, 0F);
		handle5.mirror = true;
		setRotation(handle5, 0F, 0F, 0F);
		handle6.addBox(-2F, 0F, -0.5F, 4, 1, 1);
		handle6.setRotationPoint(0F, 8F, 0F);
		handle6.mirror = true;
		setRotation(handle6, 0F, 0F, 0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7,
			boolean par8, boolean par9) {
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity, par9);

		this.bottom.render(par7);
		this.leg1.render(par7);
		this.leg2.render(par7);
		this.leg3.render(par7);
		this.leg4.render(par7);
		this.boad1.render(par7);
		this.boad2.render(par7);
		this.middle.render(par7);
		this.axle.render(par7);
		this.handle1.render(par7);
		this.handle2.render(par7);
		this.handle3.render(par7);
		this.handle4.render(par7);
		this.handle5.render(par7);
		this.handle6.render(par7);
		if (par8) {
			this.ice.render(par7);
		}

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity, boolean flag) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		if (flag) {
			this.ice.rotateAngleY += 1 / (180F / (float) Math.PI);
			this.axle.rotateAngleY += 1 / (180F / (float) Math.PI);
			this.handle1.rotateAngleY += 1 / (180F / (float) Math.PI);
			this.handle2.rotateAngleY += 1 / (180F / (float) Math.PI);
			this.handle3.rotateAngleY += 1 / (180F / (float) Math.PI);
			this.handle4.rotateAngleY += 1 / (180F / (float) Math.PI);
			this.handle5.rotateAngleY += 1 / (180F / (float) Math.PI);
			this.handle6.rotateAngleY += 1 / (180F / (float) Math.PI);
		}
	}
}
