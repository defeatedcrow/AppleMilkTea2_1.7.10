package mods.defeatedcrow.client.entity.base;

import net.minecraft.src.*;
import net.minecraft.util.MathHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWoodBowl extends ModelBase {
	// fields
	ModelRenderer bottom;
	ModelRenderer bottom2;
	ModelRenderer sideF;
	ModelRenderer sideB;
	ModelRenderer sideR;
	ModelRenderer sideL;

	public ModelWoodBowl() {
		textureWidth = 32;
		textureHeight = 16;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-3F, 0F, -3F, 6, 1, 6);
		bottom.setRotationPoint(0F, 23F, 0F);
		bottom.setTextureSize(32, 16);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		bottom2 = new ModelRenderer(this, 0, 0);
		bottom2.addBox(-4F, 0F, -4F, 8, 1, 8);
		bottom2.setRotationPoint(0F, 22F, 0F);
		bottom2.setTextureSize(32, 16);
		bottom2.mirror = true;
		setRotation(bottom2, 0F, 0F, 0F);
		sideF = new ModelRenderer(this, 0, 0);
		sideF.addBox(-5F, 0F, -5F, 10, 3, 1);
		sideF.setRotationPoint(0F, 19F, 0F);
		sideF.setTextureSize(32, 16);
		sideF.mirror = true;
		setRotation(sideF, 0F, 0F, 0F);
		sideB = new ModelRenderer(this, 0, 0);
		sideB.addBox(-5F, 0F, 4F, 10, 3, 1);
		sideB.setRotationPoint(0F, 19F, 0F);
		sideB.setTextureSize(32, 16);
		sideB.mirror = true;
		setRotation(sideB, 0F, 0F, 0F);
		sideR = new ModelRenderer(this, 0, 0);
		sideR.addBox(-5F, 0F, -4F, 1, 3, 8);
		sideR.setRotationPoint(0F, 19F, 0F);
		sideR.setTextureSize(32, 16);
		sideR.mirror = true;
		setRotation(sideR, 0F, 0F, 0F);
		sideL = new ModelRenderer(this, 0, 0);
		sideL.addBox(4F, 0F, -4F, 1, 3, 8);
		sideL.setRotationPoint(0F, 19F, 0F);
		sideL.setTextureSize(32, 16);
		sideL.mirror = true;
		setRotation(sideL, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bottom.render(f5);
		bottom2.render(f5);
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

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.bottom.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.bottom2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.sideF.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.sideB.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.sideR.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.sideL.rotateAngleY = f3 / (180F / (float) Math.PI);
	}

}
