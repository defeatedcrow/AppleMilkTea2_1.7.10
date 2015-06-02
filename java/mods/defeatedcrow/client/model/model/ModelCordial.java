package mods.defeatedcrow.client.model.model;

import net.minecraft.src.*;
import net.minecraft.util.MathHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCordial extends ModelBase {
	// fields
	ModelRenderer bottom;
	ModelRenderer side1;
	ModelRenderer side2;
	ModelRenderer side3;
	ModelRenderer side4;
	ModelRenderer top1;
	ModelRenderer top2;
	ModelRenderer cap;

	ModelRenderer inner;
	ModelRenderer drink1;
	ModelRenderer drink2;
	ModelRenderer drink3;

	public ModelCordial() {
		textureWidth = 64;
		textureHeight = 32;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-5F, 0F, -5F, 10, 1, 10);
		bottom.setRotationPoint(0F, 23F, 0F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		side1 = new ModelRenderer(this, 0, 0);
		side1.addBox(-5F, 0F, -5F, 10, 10, 1);
		side1.setRotationPoint(0F, 13F, 0F);
		side1.setTextureSize(64, 32);
		side1.mirror = true;
		setRotation(side1, 0F, 0F, 0F);
		side2 = new ModelRenderer(this, 0, 0);
		side2.addBox(-5F, 0F, 4F, 10, 10, 1);
		side2.setRotationPoint(0F, 13F, 0F);
		side2.setTextureSize(64, 32);
		side2.mirror = true;
		setRotation(side2, 0F, 0F, 0F);
		side3 = new ModelRenderer(this, 0, 0);
		side3.addBox(4F, 0F, -4F, 1, 10, 8);
		side3.setRotationPoint(0F, 13F, 0F);
		side3.setTextureSize(64, 32);
		side3.mirror = true;
		setRotation(side3, 0F, 0F, 0F);
		side4 = new ModelRenderer(this, 0, 0);
		side4.addBox(-5F, 0F, -4F, 1, 10, 8);
		side4.setRotationPoint(0F, 13F, 0F);
		side4.setTextureSize(64, 32);
		side4.mirror = true;
		setRotation(side4, 0F, 0F, 0F);
		top1 = new ModelRenderer(this, 0, 0);
		top1.addBox(-4F, -1F, -4F, 8, 1, 8);
		top1.setRotationPoint(0F, 13F, 0F);
		top1.setTextureSize(64, 32);
		top1.mirror = true;
		setRotation(top1, 0F, 0F, 0F);
		top2 = new ModelRenderer(this, 0, 0);
		top2.addBox(-3F, 0F, -3F, 6, 1, 6);
		top2.setRotationPoint(0F, 11F, 0F);
		top2.setTextureSize(64, 32);
		top2.mirror = true;
		setRotation(top2, 0F, 0F, 0F);
		cap = new ModelRenderer(this, 0, 0);
		cap.addBox(-4F, 0F, -4F, 8, 1, 8);
		cap.setRotationPoint(0F, 10F, 0F);
		cap.setTextureSize(64, 32);
		cap.mirror = true;
		setRotation(cap, 0F, 0F, 0F);

		inner = new ModelRenderer(this, 0, 0);
		inner.addBox(-3.5F, 0F, -3.5F, 7, 3, 7);
		inner.setRotationPoint(0F, 20F, 0F);
		inner.setTextureSize(64, 32);
		inner.mirror = true;
		setRotation(inner, 0F, 0F, 0F);
		drink1 = new ModelRenderer(this, 0, 0);
		drink1.addBox(-4F, 0F, -4F, 8, 9, 8);
		drink1.setRotationPoint(0F, 14F, 0F);
		drink1.setTextureSize(64, 32);
		drink1.mirror = true;
		setRotation(drink1, 0F, 0F, 0F);
		drink2 = new ModelRenderer(this, 0, 0);
		drink2.addBox(-4F, 0F, -4F, 8, 6, 8);
		drink2.setRotationPoint(0F, 17F, 0F);
		drink2.setTextureSize(64, 32);
		drink2.mirror = true;
		setRotation(drink2, 0F, 0F, 0F);
		drink3 = new ModelRenderer(this, 0, 0);
		drink3.addBox(-4F, 0F, -4F, 8, 4, 8);
		drink3.setRotationPoint(0F, 19F, 0F);
		drink3.setTextureSize(64, 32);
		drink3.mirror = true;
		setRotation(drink3, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		bottom.render(f5);
		side1.render(f5);
		side2.render(f5);
		side3.render(f5);
		side4.render(f5);
		top1.render(f5);
		top2.render(f5);
	}

	public void renderDrink(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		drink1.render(f5);
	}

	public void renderContents(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		inner.render(f5);
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
