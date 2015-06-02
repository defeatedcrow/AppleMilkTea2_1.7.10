package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAltCatorarys extends ModelBase {

	// fields
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;

	public ModelAltCatorarys() {
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(-6F, 0F, -0.5F, 10, 1, 1);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0.0523599F);
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(4F, 0F, -1.5F, 4, 1, 3);
		Shape2.setRotationPoint(0F, 0F, 0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0.0523599F);
		Shape3 = new ModelRenderer(this, 0, 0);
		Shape3.addBox(-6F, -1F, -2F, 10, 1, 1);
		Shape3.setRotationPoint(0F, 0F, 0F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, -0.0698132F, -0.0349066F);
		Shape4 = new ModelRenderer(this, 0, 0);
		Shape4.addBox(4F, -1F, -2.5F, 1, 1, 2);
		Shape4.setRotationPoint(0F, 0F, 0F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, -0.0698132F, -0.0349066F);
		Shape5 = new ModelRenderer(this, 0, 0);
		Shape5.addBox(5F, -1F, -2.7F, 3, 1, 1);
		Shape5.setRotationPoint(0F, 0F, 0F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, -0.0698132F, -0.0349066F);
		Shape6 = new ModelRenderer(this, 0, 0);
		Shape6.addBox(5F, -1F, -1.2F, 3, 1, 1);
		Shape6.setRotationPoint(0F, 0F, 0F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, -0.0698132F, -0.0349066F);
		Shape7 = new ModelRenderer(this, 0, 0);
		Shape7.addBox(2.5F, 0F, -4F, 4, 1, 3);
		Shape7.setRotationPoint(0F, 0F, 0F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0.0872665F, 0F);
		Shape8 = new ModelRenderer(this, 0, 0);
		Shape8.addBox(-7.5F, 0F, -3F, 10, 1, 1);
		Shape8.setRotationPoint(0F, 0F, 0F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0.0872665F, 0F);
		Shape9 = new ModelRenderer(this, 0, 0);
		Shape9.addBox(-1F, -0.5F, 0F, 8, 1, 2);
		Shape9.setRotationPoint(0F, 0F, 0F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, -0.0174533F, -0.0523599F);
		Shape10 = new ModelRenderer(this, 0, 0);
		Shape10.addBox(-8F, -0.5F, 1F, 7, 1, 1);
		Shape10.setRotationPoint(0F, 0F, 0F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, -0.0174533F, -0.0523599F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
		Shape10.render(f5);
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
