package mods.defeatedcrow.client.model.model;

import net.minecraft.src.*;
import net.minecraft.util.MathHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCanister extends ModelBase {
	// fields

	ModelRenderer bottomC;
	ModelRenderer side1C;
	ModelRenderer side2C;
	ModelRenderer side3C;
	ModelRenderer side4C;
	ModelRenderer top1C;
	ModelRenderer top2C;
	ModelRenderer woodcap;

	ModelRenderer contents;

	public ModelCanister() {
		textureWidth = 32;
		textureHeight = 32;

		bottomC = new ModelRenderer(this, 0, 0);
		bottomC.addBox(-5F, 0F, -5F, 10, 2, 10);
		bottomC.setRotationPoint(0F, 22F, 0F);
		bottomC.setTextureSize(64, 32);
		bottomC.mirror = true;
		setRotation(bottomC, 0F, 0F, 0F);
		side1C = new ModelRenderer(this, 0, 0);
		side1C.addBox(-5F, 0F, -5F, 10, 10, 1);
		side1C.setRotationPoint(0F, 12F, 0F);
		side1C.setTextureSize(64, 32);
		side1C.mirror = true;
		setRotation(side1C, 0F, 0F, 0F);
		side2C = new ModelRenderer(this, 0, 0);
		side2C.addBox(-5F, 0F, 4F, 10, 10, 1);
		side2C.setRotationPoint(0F, 12F, 0F);
		side2C.setTextureSize(64, 32);
		side2C.mirror = true;
		setRotation(side2C, 0F, 0F, 0F);
		side3C = new ModelRenderer(this, 0, 0);
		side3C.addBox(-5F, 0F, -4F, 1, 10, 8);
		side3C.setRotationPoint(0F, 12F, 0F);
		side3C.setTextureSize(64, 32);
		side3C.mirror = true;
		setRotation(side3C, 0F, 0F, 0F);
		side4C = new ModelRenderer(this, 0, 0);
		side4C.addBox(4F, 0F, -4F, 1, 10, 8);
		side4C.setRotationPoint(0F, 12F, 0F);
		side4C.setTextureSize(64, 32);
		side4C.mirror = true;
		setRotation(side4C, 0F, 0F, 0F);
		top1C = new ModelRenderer(this, 0, 0);
		top1C.addBox(-4F, 0F, -4F, 8, 2, 8);
		top1C.setRotationPoint(0F, 11F, 0F);
		top1C.setTextureSize(64, 32);
		top1C.mirror = true;
		setRotation(top1C, 0F, 0F, 0F);
		top2C = new ModelRenderer(this, 0, 0);
		top2C.addBox(-3F, 0F, -3F, 6, 1, 6);
		top2C.setRotationPoint(0F, 10F, 0F);
		top2C.setTextureSize(64, 32);
		top2C.mirror = true;
		setRotation(top2C, 0F, 0F, 0F);
		woodcap = new ModelRenderer(this, 0, 0);
		woodcap.addBox(-4F, 0F, -4F, 8, 1, 8);
		woodcap.setRotationPoint(0F, 9F, 0F);
		woodcap.setTextureSize(32, 32);
		woodcap.mirror = true;
		setRotation(woodcap, 0F, 0F, 0F);

		contents = new ModelRenderer(this, 0, 0);
		contents.addBox(-4F, 0F, -4F, 8, 8, 8);
		contents.setRotationPoint(0F, 14F, 0F);
		contents.setTextureSize(64, 32);
		contents.mirror = true;
		setRotation(contents, 0F, 0F, 0F);
	}

	public void renderCanister(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bottomC.render(f5);
		side1C.render(f5);
		side2C.render(f5);
		side3C.render(f5);
		side4C.render(f5);
		top1C.render(f5);
		top2C.render(f5);
	}

	public void renderContents(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		contents.render(f5);
	}

	public void renderCanisterCap(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		woodcap.render(f5);
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
