package mods.defeatedcrow.client.entity.base;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGlassDishB extends ModelBase {
	// fields
	ModelRenderer bottom1;
	ModelRenderer sideL;
	ModelRenderer sideR;
	ModelRenderer sideF;
	ModelRenderer sideB;

	public ModelGlassDishB() {
		textureWidth = 64;
		textureHeight = 32;

		bottom1 = new ModelRenderer(this, 0, 0);
		bottom1.addBox(0F, 0F, 0F, 4, 2, 4);
		bottom1.setRotationPoint(-2F, 22F, -2F);
		bottom1.setTextureSize(64, 32);
		bottom1.mirror = true;
		setRotation(bottom1, 0F, 0F, 0F);
		sideL = new ModelRenderer(this, 0, 10);
		sideL.addBox(3F, 0F, -3F, 1, 4, 6);
		sideL.setRotationPoint(0F, 18F, 0F);
		sideL.setTextureSize(64, 32);
		sideL.mirror = true;
		setRotation(sideL, 0F, 0F, 0.2617994F);
		sideR = new ModelRenderer(this, 14, 10);
		sideR.addBox(-4F, 0F, -3F, 1, 4, 6);
		sideR.setRotationPoint(0F, 18F, 0F);
		sideR.setTextureSize(64, 32);
		sideR.mirror = true;
		setRotation(sideR, 0F, 0F, -0.2617994F);
		sideF = new ModelRenderer(this, 0, 20);
		sideF.addBox(-3F, 0F, -4F, 6, 4, 1);
		sideF.setRotationPoint(0F, 18F, 0F);
		sideF.setTextureSize(64, 32);
		sideF.mirror = true;
		setRotation(sideF, 0.2617994F, 0F, 0F);
		sideB = new ModelRenderer(this, 0, 25);
		sideB.addBox(-3F, 0F, 3F, 6, 4, 1);
		sideB.setRotationPoint(0F, 18F, 0F);
		sideB.setTextureSize(64, 32);
		sideB.mirror = true;
		setRotation(sideB, -0.2617994F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bottom1.render(f5);
		sideL.render(f5);
		sideR.render(f5);
		sideF.render(f5);
		sideB.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
