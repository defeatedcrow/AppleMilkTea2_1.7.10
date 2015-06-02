package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAltBowl extends ModelBase {

	// fields
	ModelRenderer plate1;
	ModelRenderer plate2;
	ModelRenderer plate3;
	ModelRenderer plate4;
	ModelRenderer plate5;
	ModelRenderer plate6;
	ModelRenderer plate7;
	ModelRenderer plate8;
	ModelRenderer plate9;

	public ModelAltBowl() {
		textureWidth = 64;
		textureHeight = 32;

		plate1 = new ModelRenderer(this, 16, 8);
		plate1.addBox(-3F, -3F, 0F, 6, 6, 1);
		plate1.setRotationPoint(0F, 16F, 0F);
		plate1.setTextureSize(64, 32);
		plate1.mirror = true;
		setRotation(plate1, 0F, 0F, 0F);
		plate2 = new ModelRenderer(this, 16, 0);
		plate2.addBox(-3F, -6.5F, 0.5F, 6, 4, 1);
		plate2.setRotationPoint(0F, 16F, 0F);
		plate2.setTextureSize(64, 32);
		plate2.mirror = true;
		setRotation(plate2, 0.2617994F, 0F, 0F);
		plate3 = new ModelRenderer(this, 16, 18);
		plate3.addBox(-3F, 2.5F, 0.5F, 6, 4, 1);
		plate3.setRotationPoint(0F, 16F, 0F);
		plate3.setTextureSize(64, 32);
		plate3.mirror = true;
		setRotation(plate3, -0.2617994F, 0F, 0F);
		plate4 = new ModelRenderer(this, 0, 8);
		plate4.addBox(-6.5F, -3F, 0.5F, 4, 6, 1);
		plate4.setRotationPoint(0F, 16F, 0F);
		plate4.setTextureSize(64, 32);
		plate4.mirror = true;
		setRotation(plate4, 0F, -0.2617994F, 0F);
		plate5 = new ModelRenderer(this, 36, 8);
		plate5.addBox(2.5F, -3F, 0.5F, 4, 6, 1);
		plate5.setRotationPoint(0F, 16F, 0F);
		plate5.setTextureSize(64, 32);
		plate5.mirror = true;
		setRotation(plate5, 0F, 0.2617994F, 0F);
		plate6 = new ModelRenderer(this, 0, 0);
		plate6.addBox(-6.5F, -6.5F, 1F, 4, 4, 1);
		plate6.setRotationPoint(0F, 16F, 0F);
		plate6.setTextureSize(64, 32);
		plate6.mirror = true;
		setRotation(plate6, 0.2617994F, -0.2617994F, 0F);
		plate7 = new ModelRenderer(this, 36, 0);
		plate7.addBox(2.5F, -6.5F, 1F, 4, 4, 1);
		plate7.setRotationPoint(0F, 16F, 0F);
		plate7.setTextureSize(64, 32);
		plate7.mirror = true;
		setRotation(plate7, 0.2617994F, 0.2617994F, 0F);
		plate8 = new ModelRenderer(this, 0, 18);
		plate8.addBox(-6.5F, 2.5F, 1F, 4, 4, 1);
		plate8.setRotationPoint(0F, 16F, 0F);
		plate8.setTextureSize(64, 32);
		plate8.mirror = true;
		setRotation(plate8, -0.2617994F, -0.2617994F, 0F);
		plate9 = new ModelRenderer(this, 36, 18);
		plate9.addBox(2.5F, 2.5F, 1F, 4, 4, 1);
		plate9.setRotationPoint(0F, 16F, 0F);
		plate9.setTextureSize(64, 32);
		plate9.mirror = true;
		setRotation(plate9, -0.2617994F, 0.2617994F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		plate1.render(f5);
		plate2.render(f5);
		plate3.render(f5);
		plate4.render(f5);
		plate5.render(f5);
		plate6.render(f5);
		plate7.render(f5);
		plate8.render(f5);
		plate9.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}

}
