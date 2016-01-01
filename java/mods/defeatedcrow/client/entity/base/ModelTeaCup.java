package mods.defeatedcrow.client.entity.base;

import net.minecraft.src.*;
import net.minecraft.util.MathHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTeaCup extends ModelBase {
	// fields
	ModelRenderer handle1;
	ModelRenderer handle3;
	ModelRenderer handle2;
	ModelRenderer bottom;
	ModelRenderer sideF;
	ModelRenderer sideB;
	ModelRenderer sideR;
	ModelRenderer sideL;
	ModelRenderer contents;

	public ModelTeaCup() {
		textureWidth = 64;
		textureHeight = 32;

		handle1 = new ModelRenderer(this, 0, 0);
		handle1.addBox(0F, 0F, 0F, 2, 1, 2);
		handle1.setRotationPoint(-1F, 17F, -5F);
		handle1.setTextureSize(64, 32);
		handle1.mirror = true;
		setRotation(handle1, 0F, 0F, 0F);
		handle3 = new ModelRenderer(this, 0, 0);
		handle3.addBox(0F, 0F, 0F, 2, 1, 2);
		handle3.setRotationPoint(-1F, 22F, -5F);
		handle3.setTextureSize(64, 32);
		handle3.mirror = true;
		setRotation(handle3, 0F, 0F, 0F);
		handle2 = new ModelRenderer(this, 0, 0);
		handle2.addBox(0F, 0F, 0F, 2, 6, 1);
		handle2.setRotationPoint(-1F, 17F, -6F);
		handle2.setTextureSize(64, 32);
		handle2.mirror = true;
		setRotation(handle2, 0F, 0F, 0F);
		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-3F, 0F, -3F, 6, 1, 6);
		bottom.setRotationPoint(0F, 23F, 0F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		sideF = new ModelRenderer(this, 0, 0);
		sideF.addBox(-3F, 0F, -3F, 6, 7, 1);
		sideF.setRotationPoint(0F, 16F, 0F);
		sideF.setTextureSize(64, 32);
		sideF.mirror = true;
		setRotation(sideF, 0F, 0F, 0F);
		sideB = new ModelRenderer(this, 0, 0);
		sideB.addBox(-3F, 0F, 2F, 6, 7, 1);
		sideB.setRotationPoint(0F, 16F, 0F);
		sideB.setTextureSize(64, 32);
		sideB.mirror = true;
		setRotation(sideB, 0F, 0F, 0F);
		sideR = new ModelRenderer(this, 0, 0);
		sideR.addBox(-3F, 0F, -2F, 1, 7, 4);
		sideR.setRotationPoint(0F, 16F, 0F);
		sideR.setTextureSize(64, 32);
		sideR.mirror = true;
		setRotation(sideR, 0F, 0F, 0F);
		sideL = new ModelRenderer(this, 0, 0);
		sideL.addBox(2F, 0F, -2F, 1, 7, 4);
		sideL.setRotationPoint(0F, 16F, 0F);
		sideL.setTextureSize(64, 32);
		sideL.mirror = true;
		setRotation(sideL, 0F, 0F, 0F);
		contents = new ModelRenderer(this, 0, 0);
		contents.addBox(-2F, 0F, -2F, 4, 6, 4);
		contents.setRotationPoint(0F, 17F, 0F);
		contents.setTextureSize(64, 32);
		contents.mirror = true;
		setRotation(contents, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		handle1.render(f5);
		handle3.render(f5);
		handle2.render(f5);
		bottom.render(f5);
		sideF.render(f5);
		sideB.render(f5);
		sideR.render(f5);
		sideL.render(f5);
	}

	public void renderContents(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		contents.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.handle1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.handle2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.handle3.rotateAngleY = f3 / (180F / (float) Math.PI);

		this.bottom.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.sideF.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.sideB.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.sideR.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.sideL.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.contents.rotateAngleY = f3 / (180F / (float) Math.PI);
	}

}
