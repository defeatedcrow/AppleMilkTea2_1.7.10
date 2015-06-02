package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelProcessor extends ModelBase {
	// fields
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer base;
	ModelRenderer body;
	ModelRenderer brade1;
	ModelRenderer brade2;
	ModelRenderer glass;
	ModelRenderer top;

	public ModelProcessor() {
		textureWidth = 64;
		textureHeight = 32;

		leg1 = new ModelRenderer(this, 0, 0);
		leg1.addBox(-6F, 0F, -6F, 2, 1, 2);
		leg1.setRotationPoint(0F, 23F, 0F);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 0, 0);
		leg2.addBox(4F, 0F, -6F, 2, 1, 2);
		leg2.setRotationPoint(0F, 23F, 0F);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, 0F);
		leg3 = new ModelRenderer(this, 0, 0);
		leg3.addBox(-6F, 0F, 4F, 2, 1, 2);
		leg3.setRotationPoint(0F, 23F, 0F);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0F, 0F, 0F);
		leg4 = new ModelRenderer(this, 0, 0);
		leg4.addBox(4F, 0F, 4F, 2, 1, 2);
		leg4.setRotationPoint(0F, 23F, 0F);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0F, 0F, 0F);
		base = new ModelRenderer(this, 0, 0);
		base.addBox(-6F, 0F, -6F, 12, 2, 12);
		base.setRotationPoint(0F, 21F, 0F);
		base.setTextureSize(64, 32);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 14);
		body.addBox(-6.5F, 0F, -6.5F, 13, 5, 13);
		body.setRotationPoint(0F, 16F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		brade1 = new ModelRenderer(this, 0, 3);
		brade1.addBox(-1F, 0F, -1F, 2, 3, 2);
		brade1.setRotationPoint(0F, 13F, 0F);
		brade1.setTextureSize(64, 32);
		brade1.mirror = true;
		setRotation(brade1, 0F, 0F, 0F);
		brade2 = new ModelRenderer(this, 40, 0);
		brade2.addBox(-3F, 0F, -3F, 6, 0, 6);
		brade2.setRotationPoint(0F, 14.5F, 0F);
		brade2.setTextureSize(64, 32);
		brade2.mirror = true;
		setRotation(brade2, 0F, 0F, 0F);
		glass = new ModelRenderer(this, 0, 0);
		glass.addBox(-5F, 0F, -5F, 10, 6, 10);
		glass.setRotationPoint(0F, 10F, 0F);
		glass.setTextureSize(64, 32);
		glass.mirror = true;
		setRotation(glass, 0F, 0F, 0F);
		top = new ModelRenderer(this, 10, 23);
		top.addBox(-4F, 0F, -4F, 8, 1, 8);
		top.setRotationPoint(0F, 9F, 0F);
		top.setTextureSize(64, 32);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		base.render(f5);
		body.render(f5);
		brade1.render(f5);
		brade2.render(f5);
		top.render(f5);
	}

	public void renderGlass(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		glass.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
