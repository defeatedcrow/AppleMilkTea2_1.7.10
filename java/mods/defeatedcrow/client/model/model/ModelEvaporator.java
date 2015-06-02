package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEvaporator extends ModelBase {
	// fields
	ModelRenderer glass1;
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape8b;
	ModelRenderer Shape9;
	ModelRenderer Shape9b;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;
	ModelRenderer Shape14;

	public ModelEvaporator() {
		textureWidth = 64;
		textureHeight = 32;

		glass1 = new ModelRenderer(this, 0, 0);
		glass1.addBox(0F, 1F, 0F, 4, 3, 4);
		glass1.setRotationPoint(0F, 19F, 0F);
		glass1.setTextureSize(64, 32);
		glass1.mirror = true;
		setRotation(glass1, 0.7853982F, 1.570796F, 0F);
		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0.5F, 1F, 0F, 3, 4, 3);
		Shape1.setRotationPoint(0F, 18F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.7853982F, 1.570796F, 0F);
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(1.5F, -5.5F, -1F, 1, 9, 1);
		Shape2.setRotationPoint(0F, 15F, 0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0.7853982F, 1.570796F, 0F);
		Shape3 = new ModelRenderer(this, 0, 0);
		Shape3.addBox(-6F, 0F, -4F, 4, 3, 4);
		Shape3.setRotationPoint(0F, 16F, 0F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 0);
		Shape4.addBox(-5.5F, 0F, -3.5F, 3, 4, 3);
		Shape4.setRotationPoint(0F, 15.5F, 0F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 0);
		Shape5.addBox(-5F, 0F, -3F, 2, 1, 2);
		Shape5.setRotationPoint(0F, 14.5F, 0F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 0, 0);
		Shape6.addBox(-4.5F, 0F, -2.5F, 1, 5, 1);
		Shape6.setRotationPoint(0F, 10F, 0F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 0, 0);
		Shape7.addBox(-5.5F, 0F, -3.5F, 3, 6, 3);
		Shape7.setRotationPoint(0F, 4F, 0F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 46, 0);
		Shape8.addBox(-1F, 0F, -6F, 8, 3, 1);
		Shape8.setRotationPoint(0F, 19F, 0F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape8b = new ModelRenderer(this, 46, 0);
		Shape8b.addBox(-1F, 0F, 1F, 8, 3, 1);
		Shape8b.setRotationPoint(0F, 19F, 0F);
		Shape8b.setTextureSize(64, 32);
		Shape8b.mirror = true;
		setRotation(Shape8b, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 50, 0);
		Shape9.addBox(6F, 0F, -5F, 1, 3, 6);
		Shape9.setRotationPoint(0F, 19F, 0F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape9b = new ModelRenderer(this, 50, 0);
		Shape9b.addBox(-1F, 0F, -5F, 1, 3, 6);
		Shape9b.setRotationPoint(0F, 19F, 0F);
		Shape9b.setTextureSize(64, 32);
		Shape9b.mirror = true;
		setRotation(Shape9b, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(this, 24, 8);
		Shape10.addBox(-1F, 0F, -6F, 8, 1, 8);
		Shape10.setRotationPoint(0F, 22F, 0F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 0, 0);
		Shape11.addBox(0.5F, -3F, 0F, 3, 2, 3);
		Shape11.setRotationPoint(0F, 18F, 0F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0.7853982F, 1.570796F, 0F);
		Shape12 = new ModelRenderer(this, 18, 4);
		Shape12.addBox(-1F, 0F, -1F, 1, 12, 1);
		Shape12.setRotationPoint(0F, 11F, 0F);
		Shape12.setTextureSize(64, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(this, 18, 0);
		Shape13.addBox(-2F, 0F, -1F, 3, 2, 1);
		Shape13.setRotationPoint(0F, 9F, 0F);
		Shape13.setTextureSize(64, 32);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
		Shape14 = new ModelRenderer(this, 0, 17);
		Shape14.addBox(-7F, 0F, -7F, 14, 1, 14);
		Shape14.setRotationPoint(0F, 23F, 0F);
		Shape14.setTextureSize(64, 32);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape8.render(f5);
		Shape8b.render(f5);
		Shape9.render(f5);
		Shape9b.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
		Shape12.render(f5);
		Shape13.render(f5);
		Shape14.render(f5);
	}

	public void renderGlass(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		glass1.render(f5);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
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
