package mods.defeatedcrow.client.entity.base;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelJPDishB extends ModelBase {
	// fields
	ModelRenderer dishJP1;
	ModelRenderer dishJP2;
	ModelRenderer dishJP3;

	public ModelJPDishB() {
		textureWidth = 64;
		textureHeight = 32;

		dishJP1 = new ModelRenderer(this, 0, 0);
		dishJP1.addBox(-6F, 0F, -6F, 12, 1, 12);
		dishJP1.setRotationPoint(0F, 23F, 0F);
		dishJP1.setTextureSize(64, 32);
		dishJP1.mirror = true;
		setRotation(dishJP1, 0F, 0F, 0F);
		dishJP2 = new ModelRenderer(this, 0, 13);
		dishJP2.addBox(-6F, 0F, -7F, 12, 2, 1);
		dishJP2.setRotationPoint(0F, 22F, 0F);
		dishJP2.setTextureSize(64, 32);
		dishJP2.mirror = true;
		setRotation(dishJP2, 0F, -1.570796F, 0F);
		dishJP3 = new ModelRenderer(this, 0, 13);
		dishJP3.addBox(-6F, 0F, -7F, 12, 2, 1);
		dishJP3.setRotationPoint(0F, 22F, 0F);
		dishJP3.setTextureSize(64, 32);
		dishJP3.mirror = true;
		setRotation(dishJP3, 0F, 1.570796F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		dishJP1.render(f5);
		dishJP2.render(f5);
		dishJP3.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
