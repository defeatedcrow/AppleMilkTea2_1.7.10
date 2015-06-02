package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFlowerPot extends ModelBase {
	// fields
	ModelRenderer bagF;
	ModelRenderer bagB;
	ModelRenderer bagL;
	ModelRenderer bagR;
	ModelRenderer dirt;
	ModelRenderer chain;
	ModelRenderer flower1;
	ModelRenderer flower2;
	ModelRenderer flower3;
	ModelRenderer base;

	public ModelFlowerPot() {
		textureWidth = 64;
		textureHeight = 32;

		bagF = new ModelRenderer(this, 0, 0);
		bagF.addBox(-6F, 2F, 0F, 12, 6, 1);
		bagF.setRotationPoint(0F, 16F, 0F);
		bagF.setTextureSize(64, 32);
		bagF.mirror = true;
		setRotation(bagF, 0.2617994F, 0F, 0F);
		bagB = new ModelRenderer(this, 0, 0);
		bagB.addBox(-6F, 2F, 5F, 12, 6, 1);
		bagB.setRotationPoint(0F, 16F, 0F);
		bagB.setTextureSize(64, 32);
		bagB.mirror = true;
		setRotation(bagB, 0.2617994F, 0F, 0F);
		bagL = new ModelRenderer(this, 0, 7);
		bagL.addBox(5F, 2F, 1F, 1, 6, 4);
		bagL.setRotationPoint(0F, 16F, 0F);
		bagL.setTextureSize(64, 32);
		bagL.mirror = true;
		setRotation(bagL, 0.2617994F, 0F, 0F);
		bagR = new ModelRenderer(this, 0, 7);
		bagR.addBox(-6F, 2F, 1F, 1, 6, 4);
		bagR.setRotationPoint(0F, 16F, 0F);
		bagR.setTextureSize(64, 32);
		bagR.mirror = true;
		setRotation(bagR, 0.2617994F, 0F, 0F);
		dirt = new ModelRenderer(this, 7, 13);
		dirt.addBox(-5F, 3F, 1F, 10, 4, 4);
		dirt.setRotationPoint(0F, 16F, 0F);
		dirt.setTextureSize(64, 32);
		dirt.mirror = true;
		setRotation(dirt, 0.2617994F, 0F, 0F);
		chain = new ModelRenderer(this, 0, 22);
		chain.addBox(-6.5F, 3F, -0.5F, 13, 1, 8);
		chain.setRotationPoint(0F, 16F, 0F);
		chain.setTextureSize(64, 32);
		chain.mirror = true;
		setRotation(chain, 0.2617994F, 0F, 0F);
		flower1 = new ModelRenderer(this, 36, 16);
		flower1.addBox(-7F, -8F, 3F, 14, 10, 0);
		flower1.setRotationPoint(0F, 16F, 0F);
		flower1.setTextureSize(64, 32);
		flower1.mirror = true;
		setRotation(flower1, -0.2617994F, 0F, 0F);
		flower2 = new ModelRenderer(this, 36, 16);
		flower2.addBox(-4F, -6F, 1F, 14, 10, 0);
		flower2.setRotationPoint(0F, 16F, 0F);
		flower2.setTextureSize(64, 32);
		flower2.mirror = true;
		setRotation(flower2, 0.3490659F, -0.3490659F, 0F);
		flower3 = new ModelRenderer(this, 36, 16);
		flower3.addBox(-10F, -6F, 1F, 14, 10, 0);
		flower3.setRotationPoint(0F, 16F, 0F);
		flower3.setTextureSize(64, 32);
		flower3.mirror = true;
		setRotation(flower3, 0.3490659F, 0.3490659F, 0F);
		base = new ModelRenderer(this, 32, 0);
		base.addBox(-8F, -8F, 7.9F, 16, 16, 0);
		base.setRotationPoint(0F, 16F, 0F);
		base.setTextureSize(64, 32);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bagF.render(f5);
		bagB.render(f5);
		bagL.render(f5);
		bagR.render(f5);
		dirt.render(f5);
		chain.render(f5);
		flower1.render(f5);
		flower2.render(f5);
		flower3.render(f5);
		base.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bagF.rotateAngleY = f3 / (180F / (float) Math.PI);
		bagB.rotateAngleY = f3 / (180F / (float) Math.PI);
		bagL.rotateAngleY = f3 / (180F / (float) Math.PI);
		bagR.rotateAngleY = f3 / (180F / (float) Math.PI);
		dirt.rotateAngleY = f3 / (180F / (float) Math.PI);
		chain.rotateAngleY = f3 / (180F / (float) Math.PI);
		flower1.rotateAngleY = f3 / (180F / (float) Math.PI);
		flower2.rotateAngleY = -0.3490659F + f3 / (180F / (float) Math.PI);
		flower3.rotateAngleY = 0.3490659F + f3 / (180F / (float) Math.PI);
		base.rotateAngleY = f3 / (180F / (float) Math.PI);
	}

}
