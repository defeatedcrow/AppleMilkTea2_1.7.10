package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBasketL extends ModelBase {
	// fields
	ModelRenderer base;
	ModelRenderer sideF;
	ModelRenderer sideB;
	ModelRenderer sideR;
	ModelRenderer sideL;

	public ModelBasketL() {
		textureWidth = 64;
		textureHeight = 64;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(-8F, 7F, -8F, 16, 1, 16);
		base.setRotationPoint(0F, 16F, 0F);
		base.setTextureSize(64, 32);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		sideF = new ModelRenderer(this, 0, 19);
		sideF.addBox(-8F, 3F, -8F, 16, 4, 1);
		sideF.setRotationPoint(0F, 16F, 0F);
		sideF.setTextureSize(64, 32);
		sideF.mirror = true;
		setRotation(sideF, 0F, 0F, 0F);
		sideB = new ModelRenderer(this, 0, 19);
		sideB.addBox(-8F, 0F, 7F, 16, 4, 1);
		sideB.setRotationPoint(0F, 19F, 0F);
		sideB.setTextureSize(64, 32);
		sideB.mirror = true;
		setRotation(sideB, 0F, 0F, 0F);
		sideR = new ModelRenderer(this, 0, 26);
		sideR.addBox(-7F, 3F, -8F, 14, 4, 1);
		sideR.setRotationPoint(0F, 16F, 0F);
		sideR.setTextureSize(64, 32);
		sideR.mirror = true;
		setRotation(sideR, 0F, 1.570796F, 0F);
		sideL = new ModelRenderer(this, 0, 26);
		sideL.addBox(-7F, 3F, 7F, 14, 4, 1);
		sideL.setRotationPoint(0F, 16F, 0F);
		sideL.setTextureSize(64, 32);
		sideL.mirror = true;
		setRotation(sideL, 0F, 1.570796F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		base.render(f5);
		sideF.render(f5);
		sideB.render(f5);
		sideR.render(f5);
		sideL.render(f5);
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
