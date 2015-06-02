package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelYuzuGatling extends ModelBase {
	// fields
	ModelRenderer muz1;
	ModelRenderer muz2;
	ModelRenderer muz3;
	ModelRenderer syl1;
	ModelRenderer syl2;
	ModelRenderer bod1;
	ModelRenderer bod2;
	ModelRenderer bod3;
	ModelRenderer she1;
	ModelRenderer base1;
	ModelRenderer base2;

	public ModelYuzuGatling() {
		textureWidth = 64;
		textureHeight = 32;

		muz1 = new ModelRenderer(this, 0, 4);
		muz1.addBox(3F, -0.5F, -0.5F, 12, 1, 1);
		muz1.setRotationPoint(0F, 16F, 0F);
		muz1.setTextureSize(64, 32);
		muz1.mirror = true;
		setRotation(muz1, 0F, 1.570796F, 0F);
		muz2 = new ModelRenderer(this, 0, 0);
		muz2.addBox(3F, -2F, 0.5F, 17, 2, 2);
		muz2.setRotationPoint(0F, 17F, 0F);
		muz2.setTextureSize(64, 32);
		muz2.mirror = true;
		setRotation(muz2, 0F, 1.570796F, 0F);
		muz3 = new ModelRenderer(this, 0, 0);
		muz3.addBox(3F, -1F, -2.5F, 17, 2, 2);
		muz3.setRotationPoint(0F, 16F, 0F);
		muz3.setTextureSize(64, 32);
		muz3.mirror = true;
		setRotation(muz3, 0F, 1.570796F, 0F);
		syl1 = new ModelRenderer(this, 0, 8);
		syl1.addBox(-1.5F, -1F, -3F, 3, 2, 2);
		syl1.setRotationPoint(0F, 16F, 0F);
		syl1.setTextureSize(64, 32);
		syl1.mirror = true;
		setRotation(syl1, 0F, 0F, 0F);
		syl2 = new ModelRenderer(this, 0, 12);
		syl2.addBox(-2.5F, -1F, -1F, 5, 3, 3);
		syl2.setRotationPoint(0F, 16F, 0F);
		syl2.setTextureSize(64, 32);
		syl2.mirror = true;
		setRotation(syl2, 0F, 0F, 0F);
		bod1 = new ModelRenderer(this, 0, 22);
		bod1.addBox(-3F, 0F, 2F, 6, 2, 8);
		bod1.setRotationPoint(0F, 16F, 0F);
		bod1.setTextureSize(64, 32);
		bod1.mirror = true;
		setRotation(bod1, 0F, 0F, 0F);
		bod2 = new ModelRenderer(this, 38, 0);
		bod2.addBox(-2F, -0.5F, 2F, 4, 1, 5);
		bod2.setRotationPoint(0F, 16F, 0F);
		bod2.setTextureSize(64, 32);
		bod2.mirror = true;
		setRotation(bod2, 0F, 0F, 0F);
		bod3 = new ModelRenderer(this, 54, 0);
		bod3.addBox(-1F, -2F, 2F, 2, 1, 2);
		bod3.setRotationPoint(0F, 17F, 0F);
		bod3.setTextureSize(64, 32);
		bod3.mirror = true;
		setRotation(bod3, 0F, 0F, 0F);
		she1 = new ModelRenderer(this, 32, 16);
		she1.addBox(-3.5F, 0F, 7F, 7, 1, 8);
		she1.setRotationPoint(0F, 16F, 0F);
		she1.setTextureSize(64, 32);
		she1.mirror = true;
		setRotation(she1, -0.2617994F, 0F, 0F);
		base1 = new ModelRenderer(this, 0, 21);
		base1.addBox(-3.5F, 4F, 1F, 7, 4, 7);
		base1.setRotationPoint(0F, 16F, 0F);
		base1.setTextureSize(64, 32);
		base1.mirror = true;
		setRotation(base1, 0F, 0F, 0F);
		base2 = new ModelRenderer(this, 32, 25);
		base2.addBox(-2F, 3F, 2F, 4, 2, 5);
		base2.setRotationPoint(0F, 15F, 0F);
		base2.setTextureSize(64, 32);
		base2.mirror = true;
		setRotation(base2, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		muz1.render(f5);
		muz2.render(f5);
		muz3.render(f5);
		syl1.render(f5);
		syl2.render(f5);
		bod1.render(f5);
		bod2.render(f5);
		bod3.render(f5);
		she1.render(f5);
		base1.render(f5);
		base2.render(f5);
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
