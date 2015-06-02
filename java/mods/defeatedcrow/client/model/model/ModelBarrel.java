package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBarrel extends ModelBase {
	// fields
	ModelRenderer side1;
	ModelRenderer side2;
	ModelRenderer side3;
	ModelRenderer side4;
	ModelRenderer side5;
	ModelRenderer side6;
	ModelRenderer side7;
	ModelRenderer side8;
	ModelRenderer side9;
	ModelRenderer side10;
	ModelRenderer r1;
	ModelRenderer r2;
	ModelRenderer r3;
	ModelRenderer r4;
	ModelRenderer r5;
	ModelRenderer l1;
	ModelRenderer l2;
	ModelRenderer l3;
	ModelRenderer l4;
	ModelRenderer l5;
	ModelRenderer base1;
	ModelRenderer base2;

	public ModelBarrel() {
		textureWidth = 64;
		textureHeight = 32;

		side1 = new ModelRenderer(this, 0, 20);
		side1.addBox(-8F, 6F, -2F, 16, 1, 4);
		side1.setRotationPoint(0F, 16F, 0F);
		side1.setTextureSize(64, 32);
		side1.mirror = true;
		setRotation(side1, 0F, 0F, 0F);
		side2 = new ModelRenderer(this, 0, 20);
		side2.addBox(-8F, 6F, -2F, 16, 1, 4);
		side2.setRotationPoint(0F, 16F, 0F);
		side2.setTextureSize(64, 32);
		side2.mirror = true;
		setRotation(side2, 0.6283185F, 0F, 0F);
		side3 = new ModelRenderer(this, 0, 20);
		side3.addBox(-8F, 6F, -2F, 16, 1, 4);
		side3.setRotationPoint(0F, 16F, 0F);
		side3.setTextureSize(64, 32);
		side3.mirror = true;
		setRotation(side3, 1.256637F, 0F, 0F);
		side4 = new ModelRenderer(this, 0, 20);
		side4.addBox(-8F, 6F, -2F, 16, 1, 4);
		side4.setRotationPoint(0F, 16F, 0F);
		side4.setTextureSize(64, 32);
		side4.mirror = true;
		setRotation(side4, -0.6283185F, 0F, 0F);
		side5 = new ModelRenderer(this, 0, 20);
		side5.addBox(-8F, 6F, -2F, 16, 1, 4);
		side5.setRotationPoint(0F, 16F, 0F);
		side5.setTextureSize(64, 32);
		side5.mirror = true;
		setRotation(side5, -1.256637F, 0F, 0F);
		side6 = new ModelRenderer(this, 0, 20);
		side6.addBox(-8F, -7F, -2F, 16, 1, 4);
		side6.setRotationPoint(0F, 16F, 0F);
		side6.setTextureSize(64, 32);
		side6.mirror = true;
		setRotation(side6, 1.256637F, 0F, 0F);
		side7 = new ModelRenderer(this, 0, 20);
		side7.addBox(-8F, -7F, -2F, 16, 1, 4);
		side7.setRotationPoint(0F, 16F, 0F);
		side7.setTextureSize(64, 32);
		side7.mirror = true;
		setRotation(side7, -1.256637F, 0F, 0F);
		side8 = new ModelRenderer(this, 0, 20);
		side8.addBox(-8F, -7F, -2F, 16, 1, 4);
		side8.setRotationPoint(0F, 16F, 0F);
		side8.setTextureSize(64, 32);
		side8.mirror = true;
		setRotation(side8, 0.6283185F, 0F, 0F);
		side9 = new ModelRenderer(this, 0, 20);
		side9.addBox(-8F, -7F, -2F, 16, 1, 4);
		side9.setRotationPoint(0F, 16F, 0F);
		side9.setTextureSize(64, 32);
		side9.mirror = true;
		setRotation(side9, -0.6283185F, 0F, 0F);
		side10 = new ModelRenderer(this, 0, 20);
		side10.addBox(-8F, -7F, -2F, 16, 1, 4);
		side10.setRotationPoint(0F, 16F, 0F);
		side10.setTextureSize(64, 32);
		side10.mirror = true;
		setRotation(side10, 0F, 0F, 0F);
		r1 = new ModelRenderer(this, 0, 0);
		r1.addBox(-7F, -5F, -5F, 1, 10, 10);
		r1.setRotationPoint(0F, 16F, 0F);
		r1.setTextureSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0F, 0F, 0F);
		r2 = new ModelRenderer(this, 0, 0);
		r2.addBox(-7F, -4F, -6.5F, 1, 8, 2);
		r2.setRotationPoint(0F, 16F, 0F);
		r2.setTextureSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0F, 0F, 0F);
		r3 = new ModelRenderer(this, 0, 0);
		r3.addBox(-7F, -4F, 4.5F, 1, 8, 2);
		r3.setRotationPoint(0F, 16F, 0F);
		r3.setTextureSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0F, 0F, 0F);
		r4 = new ModelRenderer(this, 14, 0);
		r4.addBox(-7F, -6F, -3.5F, 1, 1, 7);
		r4.setRotationPoint(0F, 16F, 0F);
		r4.setTextureSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0F, 0F, 0F);
		r5 = new ModelRenderer(this, 14, 0);
		r5.addBox(-7F, 5F, -3.5F, 1, 1, 7);
		r5.setRotationPoint(0F, 16F, 0F);
		r5.setTextureSize(64, 32);
		r5.mirror = true;
		setRotation(r5, 0F, 0F, 0F);
		l1 = new ModelRenderer(this, 0, 0);
		l1.addBox(6F, -5F, -5F, 1, 10, 10);
		l1.setRotationPoint(0F, 16F, 0F);
		l1.setTextureSize(64, 32);
		l1.mirror = true;
		setRotation(l1, 0F, 0F, 0F);
		l2 = new ModelRenderer(this, 0, 0);
		l2.addBox(6F, -4F, 4.5F, 1, 8, 2);
		l2.setRotationPoint(0F, 16F, 0F);
		l2.setTextureSize(64, 32);
		l2.mirror = true;
		setRotation(l2, 0F, 0F, 0F);
		l3 = new ModelRenderer(this, 0, 0);
		l3.addBox(6F, -4F, -6.5F, 1, 8, 2);
		l3.setRotationPoint(0F, 16F, 0F);
		l3.setTextureSize(64, 32);
		l3.mirror = true;
		setRotation(l3, 0F, 0F, 0F);
		l4 = new ModelRenderer(this, 14, 0);
		l4.addBox(6F, -6F, -3.5F, 1, 1, 7);
		l4.setRotationPoint(0F, 16F, 0F);
		l4.setTextureSize(64, 32);
		l4.mirror = true;
		setRotation(l4, 0F, 0F, 0F);
		l5 = new ModelRenderer(this, 14, 0);
		l5.addBox(6F, 5F, -3.5F, 1, 1, 7);
		l5.setRotationPoint(0F, 16F, 0F);
		l5.setTextureSize(64, 32);
		l5.mirror = true;
		setRotation(l5, 0F, 0F, 0F);
		base1 = new ModelRenderer(this, 24, 0);
		base1.addBox(5F, 6F, -5F, 1, 2, 10);
		base1.setRotationPoint(0F, 16F, 0F);
		base1.setTextureSize(64, 32);
		base1.mirror = true;
		setRotation(base1, 0F, 0F, 0F);
		base2 = new ModelRenderer(this, 24, 0);
		base2.addBox(-6F, 6F, -5F, 1, 2, 10);
		base2.setRotationPoint(0F, 16F, 0F);
		base2.setTextureSize(64, 32);
		base2.mirror = true;
		setRotation(base2, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		side1.render(f5);
		side2.render(f5);
		side3.render(f5);
		side4.render(f5);
		side5.render(f5);
		side6.render(f5);
		side7.render(f5);
		side8.render(f5);
		side9.render(f5);
		side10.render(f5);
		r2.render(f5);
		r3.render(f5);
		r4.render(f5);
		r5.render(f5);
		l2.render(f5);
		l3.render(f5);
		l4.render(f5);
		l5.render(f5);

	}

	public void renderSide(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		r1.render(f5);
		l1.render(f5);
	}

	public void renderBase(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		base1.render(f5);
		base2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		side1.rotateAngleY = f3 / (180F / (float) Math.PI);
		side2.rotateAngleY = f3 / (180F / (float) Math.PI);
		side3.rotateAngleY = f3 / (180F / (float) Math.PI);
		side4.rotateAngleY = f3 / (180F / (float) Math.PI);
		side5.rotateAngleY = f3 / (180F / (float) Math.PI);
		side6.rotateAngleY = f3 / (180F / (float) Math.PI);
		side7.rotateAngleY = f3 / (180F / (float) Math.PI);
		side8.rotateAngleY = f3 / (180F / (float) Math.PI);
		side9.rotateAngleY = f3 / (180F / (float) Math.PI);
		side10.rotateAngleY = f3 / (180F / (float) Math.PI);
		r1.rotateAngleY = f3 / (180F / (float) Math.PI);
		r2.rotateAngleY = f3 / (180F / (float) Math.PI);
		r3.rotateAngleY = f3 / (180F / (float) Math.PI);
		r4.rotateAngleY = f3 / (180F / (float) Math.PI);
		r5.rotateAngleY = f3 / (180F / (float) Math.PI);
		l1.rotateAngleY = f3 / (180F / (float) Math.PI);
		l2.rotateAngleY = f3 / (180F / (float) Math.PI);
		l3.rotateAngleY = f3 / (180F / (float) Math.PI);
		l4.rotateAngleY = f3 / (180F / (float) Math.PI);
		l5.rotateAngleY = f3 / (180F / (float) Math.PI);
		base1.rotateAngleY = f3 / (180F / (float) Math.PI);
		base2.rotateAngleY = f3 / (180F / (float) Math.PI);
	}

}
