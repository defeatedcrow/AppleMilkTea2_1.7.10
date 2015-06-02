package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBarrelBase extends ModelBase {
	// fields
	ModelRenderer base1;
	ModelRenderer base2;
	ModelRenderer base3;
	ModelRenderer base4;
	ModelRenderer base5;
	ModelRenderer base6;
	ModelRenderer base7;
	ModelRenderer base8;
	ModelRenderer base9;
	ModelRenderer base10;
	ModelRenderer base11;
	ModelRenderer base12;
	ModelRenderer base13;
	ModelRenderer base14;

	public ModelBarrelBase() {
		textureWidth = 64;
		textureHeight = 32;

		base1 = new ModelRenderer(this, 0, 0);
		base1.addBox(-6F, 8.1F, 6F, 12, 1, 1);
		base1.setRotationPoint(0F, 16F, 0F);
		base1.setTextureSize(64, 32);
		base1.mirror = true;
		setRotation(base1, 0F, 1.570796F, 0F);
		base2 = new ModelRenderer(this, 0, 0);
		base2.addBox(-6F, 8.1F, -7F, 12, 1, 1);
		base2.setRotationPoint(0F, 16F, 0F);
		base2.setTextureSize(64, 32);
		base2.mirror = true;
		setRotation(base2, 0F, 1.570796F, 0F);
		base3 = new ModelRenderer(this, 0, 0);
		base3.addBox(-6F, 6F, 6F, 12, 1, 1);
		base3.setRotationPoint(0F, 16F, 0F);
		base3.setTextureSize(64, 32);
		base3.mirror = true;
		setRotation(base3, 0F, 1.570796F, 0F);
		base4 = new ModelRenderer(this, 0, 0);
		base4.addBox(-6F, 6F, -7F, 12, 1, 1);
		base4.setRotationPoint(0F, 16F, 0F);
		base4.setTextureSize(64, 32);
		base4.mirror = true;
		setRotation(base4, 0F, 1.570796F, 0F);
		base5 = new ModelRenderer(this, 0, 4);
		base5.addBox(-8F, 8.1F, -7F, 16, 1, 1);
		base5.setRotationPoint(0F, 16F, 0F);
		base5.setTextureSize(64, 32);
		base5.mirror = true;
		setRotation(base5, 0F, 0F, 0F);
		base6 = new ModelRenderer(this, 0, 4);
		base6.addBox(-8F, 8.1F, 6F, 16, 1, 1);
		base6.setRotationPoint(0F, 16F, 0F);
		base6.setTextureSize(64, 32);
		base6.mirror = true;
		setRotation(base6, 0F, 0F, 0F);
		base7 = new ModelRenderer(this, 0, 4);
		base7.addBox(-8F, 6F, -7F, 16, 1, 1);
		base7.setRotationPoint(0F, 16F, 0F);
		base7.setTextureSize(64, 32);
		base7.mirror = true;
		setRotation(base7, 0F, 0F, 0F);
		base8 = new ModelRenderer(this, 0, 4);
		base8.addBox(-8F, 6F, 6F, 16, 1, 1);
		base8.setRotationPoint(0F, 16F, 0F);
		base8.setTextureSize(64, 32);
		base8.mirror = true;
		setRotation(base8, 0F, 0F, 0F);
		base9 = new ModelRenderer(this, 36, 0);
		base9.addBox(6F, 7F, -7F, 1, 1, 1);
		base9.setRotationPoint(0F, 16F, 0F);
		base9.setTextureSize(64, 32);
		base9.mirror = true;
		setRotation(base9, 0F, 0F, 0F);
		base10 = new ModelRenderer(this, 36, 0);
		base10.addBox(-7F, 7F, -7F, 1, 1, 1);
		base10.setRotationPoint(0F, 16F, 0F);
		base10.setTextureSize(64, 32);
		base10.mirror = true;
		setRotation(base10, 0F, 0F, 0F);
		base11 = new ModelRenderer(this, 36, 0);
		base11.addBox(6F, 7F, 6F, 1, 1, 1);
		base11.setRotationPoint(0F, 16F, 0F);
		base11.setTextureSize(64, 32);
		base11.mirror = true;
		setRotation(base11, 0F, 0F, 0F);
		base12 = new ModelRenderer(this, 36, 0);
		base12.addBox(-7F, 7F, 6F, 1, 1, 1);
		base12.setRotationPoint(0F, 16F, 0F);
		base12.setTextureSize(64, 32);
		base12.mirror = true;
		setRotation(base12, 0F, 0F, 0F);
		base13 = new ModelRenderer(this, 0, 8);
		base13.addBox(-7F, 5F, 4.5F, 14, 1, 1);
		base13.setRotationPoint(0F, 16F, 0F);
		base13.setTextureSize(64, 32);
		base13.mirror = true;
		setRotation(base13, 0F, 1.570796F, 0F);
		base14 = new ModelRenderer(this, 0, 8);
		base14.addBox(-7F, 5F, -5.5F, 14, 1, 1);
		base14.setRotationPoint(0F, 16F, 0F);
		base14.setTextureSize(64, 32);
		base14.mirror = true;
		setRotation(base14, 0F, 1.570796F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		base1.render(f5);
		base2.render(f5);
		base3.render(f5);
		base4.render(f5);
		base5.render(f5);
		base6.render(f5);
		base7.render(f5);
		base8.render(f5);
		base9.render(f5);
		base10.render(f5);
		base11.render(f5);
		base12.render(f5);
		base13.render(f5);
		base14.render(f5);
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
