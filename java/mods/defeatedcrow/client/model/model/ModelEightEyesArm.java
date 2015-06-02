package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEightEyesArm extends ModelBase {
	// fields
	ModelRenderer hand;
	ModelRenderer fingar1;
	ModelRenderer fingar2;
	ModelRenderer fingar3;
	ModelRenderer fingar4;
	ModelRenderer thumb1;
	ModelRenderer thumb2;
	ModelRenderer wrist;
	ModelRenderer arm1;
	ModelRenderer arm2;
	ModelRenderer armor1;
	ModelRenderer armor2;
	ModelRenderer armor3;
	ModelRenderer armor4;
	ModelRenderer armor5;
	ModelRenderer armor6;
	ModelRenderer armor7;

	public ModelEightEyesArm() {
		textureWidth = 64;
		textureHeight = 32;

		hand = new ModelRenderer(this, 0, 0);
		hand.addBox(-1.5F, 2F, -6F, 3, 4, 4);
		hand.setRotationPoint(0F, 16F, 0F);
		hand.setTextureSize(64, 32);
		hand.mirror = true;
		setRotation(hand, 0F, -0.0523599F, 0F);
		fingar1 = new ModelRenderer(this, 0, 9);
		fingar1.addBox(-4.5F, 5F, -6F, 3, 1, 3);
		fingar1.setRotationPoint(0F, 16F, 0F);
		fingar1.setTextureSize(64, 32);
		fingar1.mirror = true;
		setRotation(fingar1, 0F, -0.5235988F, 0F);
		fingar2 = new ModelRenderer(this, 0, 9);
		fingar2.addBox(-4.5F, 4F, -6F, 3, 1, 3);
		fingar2.setRotationPoint(0F, 16F, 0F);
		fingar2.setTextureSize(64, 32);
		fingar2.mirror = true;
		setRotation(fingar2, 0F, -0.5585054F, 0F);
		fingar3 = new ModelRenderer(this, 0, 9);
		fingar3.addBox(-4.5F, 3F, -6F, 3, 1, 3);
		fingar3.setRotationPoint(0F, 16F, 0F);
		fingar3.setTextureSize(64, 32);
		fingar3.mirror = true;
		setRotation(fingar3, 0F, -0.5759587F, 0F);
		fingar4 = new ModelRenderer(this, 0, 9);
		fingar4.addBox(-4.5F, 2F, -6F, 3, 1, 3);
		fingar4.setRotationPoint(0F, 16F, 0F);
		fingar4.setTextureSize(64, 32);
		fingar4.mirror = true;
		setRotation(fingar4, 0F, -0.6108652F, 0F);
		thumb1 = new ModelRenderer(this, 0, 0);
		thumb1.addBox(-1.5F, 1F, -5.5F, 2, 2, 3);
		thumb1.setRotationPoint(0F, 16F, 0F);
		thumb1.setTextureSize(64, 32);
		thumb1.mirror = true;
		setRotation(thumb1, 0F, 0.2443461F, 0F);
		thumb2 = new ModelRenderer(this, 0, 0);
		thumb2.addBox(-2.5F, 2F, -5F, 1, 3, 1);
		thumb2.setRotationPoint(0F, 16F, 0F);
		thumb2.setTextureSize(64, 32);
		thumb2.mirror = true;
		setRotation(thumb2, -0.2617994F, 0F, 0F);
		wrist = new ModelRenderer(this, 0, 15);
		wrist.addBox(-1.5F, 0F, -3F, 3, 3, 2);
		wrist.setRotationPoint(0F, 18F, 0F);
		wrist.setTextureSize(64, 32);
		wrist.mirror = true;
		setRotation(wrist, 0F, 0.0872665F, 0F);
		arm1 = new ModelRenderer(this, 0, 0);
		arm1.addBox(-1.5F, 2F, -1F, 3, 3, 4);
		arm1.setRotationPoint(0F, 16F, 0F);
		arm1.setTextureSize(64, 32);
		arm1.mirror = true;
		setRotation(arm1, 0F, 0F, 0F);
		arm2 = new ModelRenderer(this, 0, 0);
		arm2.addBox(-1F, 2F, 3F, 3, 3, 4);
		arm2.setRotationPoint(0F, 16F, 0F);
		arm2.setTextureSize(64, 32);
		arm2.mirror = true;
		setRotation(arm2, 0F, -0.1396263F, 0F);
		armor1 = new ModelRenderer(this, 32, 0);
		armor1.addBox(-1F, 1F, -2F, 4, 5, 5);
		armor1.setRotationPoint(0F, 16F, 0F);
		armor1.setTextureSize(64, 32);
		armor1.mirror = true;
		setRotation(armor1, 0.1396263F, 0.4363323F, 0F);
		armor2 = new ModelRenderer(this, 32, 11);
		armor2.addBox(-2.5F, 1.5F, 0.5F, 4, 5, 4);
		armor2.setRotationPoint(0F, 16F, 0F);
		armor2.setTextureSize(64, 32);
		armor2.mirror = true;
		setRotation(armor2, 0.1570796F, 0.4363323F, 0F);
		armor3 = new ModelRenderer(this, 32, 11);
		armor3.addBox(-3.5F, 2F, 2.5F, 4, 5, 4);
		armor3.setRotationPoint(0F, 16F, 0F);
		armor3.setTextureSize(64, 32);
		armor3.mirror = true;
		setRotation(armor3, 0.1745329F, 0.4363323F, 0F);
		armor4 = new ModelRenderer(this, 32, 21);
		armor4.addBox(-5F, 2.5F, 3F, 5, 5, 5);
		armor4.setRotationPoint(0F, 16F, 0F);
		armor4.setTextureSize(64, 32);
		armor4.mirror = true;
		setRotation(armor4, 0.1919862F, 0.4363323F, 0.0174533F);
		armor5 = new ModelRenderer(this, 0, 22);
		armor5.addBox(-2F, 1.5F, -1F, 2, 4, 2);
		armor5.setRotationPoint(0F, 16F, 0F);
		armor5.setTextureSize(64, 32);
		armor5.mirror = true;
		setRotation(armor5, 0F, -0.2094395F, 0F);
		armor6 = new ModelRenderer(this, 0, 22);
		armor6.addBox(-1.5F, 1.5F, 1F, 2, 4, 2);
		armor6.setRotationPoint(0F, 16F, 0F);
		armor6.setTextureSize(64, 32);
		armor6.mirror = true;
		setRotation(armor6, 0F, -0.3141593F, 0F);
		armor7 = new ModelRenderer(this, 0, 22);
		armor7.addBox(-1F, 1.5F, 3F, 2, 4, 2);
		armor7.setRotationPoint(0F, 16F, 0F);
		armor7.setTextureSize(64, 32);
		armor7.mirror = true;
		setRotation(armor7, 0F, -0.3141593F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		hand.render(f5);
		fingar1.render(f5);
		fingar2.render(f5);
		fingar3.render(f5);
		fingar4.render(f5);
		thumb1.render(f5);
		thumb2.render(f5);
		wrist.render(f5);
		arm1.render(f5);
		arm2.render(f5);
		armor1.render(f5);
		armor2.render(f5);
		armor3.render(f5);
		armor4.render(f5);
		armor5.render(f5);
		armor6.render(f5);
		armor7.render(f5);
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
