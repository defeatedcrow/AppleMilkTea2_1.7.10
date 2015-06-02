package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHandleEngine extends ModelBase {
	// fields
	ModelRenderer base;
	ModelRenderer shaft1;
	ModelRenderer shaft2;
	ModelRenderer shaft3;
	ModelRenderer handle;

	public ModelHandleEngine() {
		textureWidth = 64;
		textureHeight = 32;

		base = new ModelRenderer(this, 9, 0);
		base.addBox(-2F, 7F, -2F, 4, 1, 4);
		base.setRotationPoint(0F, 16F, 0F);
		base.setTextureSize(64, 32);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		shaft1 = new ModelRenderer(this, 0, 0);
		shaft1.addBox(-0.5F, 1F, -0.5F, 1, 6, 1);
		shaft1.setRotationPoint(0F, 16F, 0F);
		shaft1.setTextureSize(64, 32);
		shaft1.mirror = true;
		setRotation(shaft1, 0F, 0F, 0F);
		shaft2 = new ModelRenderer(this, 0, 0);
		shaft2.addBox(-0.5F, 1F, 0.5F, 1, 1, 3);
		shaft2.setRotationPoint(0F, 16F, 0F);
		shaft2.setTextureSize(64, 32);
		shaft2.mirror = true;
		setRotation(shaft2, 0F, 0F, 0F);
		shaft3 = new ModelRenderer(this, 0, 0);
		shaft3.addBox(-0.5F, 0F, 3.5F, 1, 2, 1);
		shaft3.setRotationPoint(0F, 16F, 0F);
		shaft3.setTextureSize(64, 32);
		shaft3.mirror = true;
		setRotation(shaft3, 0F, 0F, 0F);
		handle = new ModelRenderer(this, 0, 8);
		handle.addBox(-1F, -4F, 3F, 2, 4, 2);
		handle.setRotationPoint(0F, 16F, 0F);
		handle.setTextureSize(64, 32);
		handle.mirror = true;
		setRotation(handle, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		handle.render(f5);
		shaft1.render(f5);
		shaft2.render(f5);
		shaft3.render(f5);
	}

	public void renderBase(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		base.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
		this.handle.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.shaft1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.shaft2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.shaft3.rotateAngleY = f3 / (180F / (float) Math.PI);
	}

}
