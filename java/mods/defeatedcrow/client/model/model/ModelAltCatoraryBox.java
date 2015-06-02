package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAltCatoraryBox extends ModelBase {

	// fields
	ModelRenderer bottom;
	ModelRenderer sideL;
	ModelRenderer sideR;
	ModelRenderer sideF;
	ModelRenderer sideB;

	public ModelAltCatoraryBox() {
		textureWidth = 64;
		textureHeight = 32;

		bottom = new ModelRenderer(this, 0, 21);
		bottom.addBox(-6F, 7F, -2F, 12, 1, 4);
		bottom.setRotationPoint(0F, 16F, 0F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		sideL = new ModelRenderer(this, 12, 12);
		sideL.addBox(6F, 4F, -2F, 1, 4, 4);
		sideL.setRotationPoint(0F, 16F, 0F);
		sideL.setTextureSize(64, 32);
		sideL.mirror = true;
		setRotation(sideL, 0F, 0F, 0F);
		sideR = new ModelRenderer(this, 0, 12);
		sideR.addBox(-7F, 4F, -2F, 1, 4, 4);
		sideR.setRotationPoint(0F, 16F, 0F);
		sideR.setTextureSize(64, 32);
		sideR.mirror = true;
		setRotation(sideR, 0F, 0F, 0F);
		sideF = new ModelRenderer(this, 0, 0);
		sideF.addBox(-7F, 4F, -3F, 14, 4, 1);
		sideF.setRotationPoint(0F, 16F, 0F);
		sideF.setTextureSize(64, 32);
		sideF.mirror = true;
		setRotation(sideF, 0F, 0F, 0F);
		sideB = new ModelRenderer(this, 0, 6);
		sideB.addBox(-7F, 4F, 2F, 14, 4, 1);
		sideB.setRotationPoint(0F, 16F, 0F);
		sideB.setTextureSize(64, 32);
		sideB.mirror = true;
		setRotation(sideB, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		bottom.render(f5);
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

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}

}
