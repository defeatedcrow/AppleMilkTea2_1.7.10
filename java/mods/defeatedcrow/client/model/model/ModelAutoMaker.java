package mods.defeatedcrow.client.model.model;

import net.minecraft.src.*;
import net.minecraft.util.MathHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAutoMaker extends ModelBase {
	// fields
	ModelRenderer Shape1 = (new ModelRenderer(this, 13, 16)).setTextureSize(32, 32);
	ModelRenderer Shape2 = (new ModelRenderer(this, 0, 16)).setTextureSize(32, 32);
	ModelRenderer Shape3 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer Shape4 = (new ModelRenderer(this, 0, 9)).setTextureSize(32, 32);

	public ModelAutoMaker() {

		Shape1.addBox(-1F, 0F, -1F, 2, 1, 2);
		Shape1.setRotationPoint(0F, 23F, 0F);
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2.addBox(-2F, 0F, -2F, 4, 1, 4);
		Shape2.setRotationPoint(0F, 22F, 0F);
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3.addBox(-3F, 0F, -3F, 6, 3, 6);
		Shape3.setRotationPoint(0F, 19F, 0F);
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4.addBox(-3F, 0F, -5F, 6, 1, 6);
		Shape4.setRotationPoint(0F, 18F, 2F);
		setRotation(Shape4, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.Shape4.rotateAngleX = f3 / (180F / (float) Math.PI);
	}

}
