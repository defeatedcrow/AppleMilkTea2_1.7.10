package mods.defeatedcrow.client.model.model;

import net.minecraft.src.*;
import net.minecraft.util.MathHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLargeBottle extends ModelBase {
	// fields
	ModelRenderer bottom;
	ModelRenderer middle1;
	ModelRenderer middle2;
	ModelRenderer middle3;
	ModelRenderer top;
	ModelRenderer cap;

	public ModelLargeBottle() {
		textureWidth = 32;
		textureHeight = 32;

		bottom = new ModelRenderer(this, 0, 7);
		bottom.addBox(-3F, 0F, -3F, 6, 7, 6);
		bottom.setRotationPoint(0F, 17F, 0F);
		bottom.setTextureSize(32, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		middle1 = new ModelRenderer(this, 0, 0);
		middle1.addBox(-2.5F, 0F, -2.5F, 5, 2, 5);
		middle1.setRotationPoint(0F, 15F, 0F);
		middle1.setTextureSize(32, 32);
		middle1.mirror = true;
		setRotation(middle1, 0F, 0F, 0F);
		middle2 = new ModelRenderer(this, 0, 0);
		middle2.addBox(-2F, 0F, -2F, 4, 1, 4);
		middle2.setRotationPoint(0F, 14F, 0F);
		middle2.setTextureSize(32, 32);
		middle2.mirror = true;
		setRotation(middle2, 0F, 0F, 0F);
		middle3 = new ModelRenderer(this, 0, 0);
		middle3.addBox(-1.5F, 0F, -1.5F, 3, 1, 3);
		middle3.setRotationPoint(0F, 13F, 0F);
		middle3.setTextureSize(32, 32);
		middle3.mirror = true;
		setRotation(middle3, 0F, 0F, 0F);
		top = new ModelRenderer(this, 0, 0);
		top.addBox(-1F, 0F, -1F, 2, 2, 2);
		top.setRotationPoint(0F, 11F, 0F);
		top.setTextureSize(32, 32);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		cap = new ModelRenderer(this, 20, 0);
		cap.addBox(-1F, 0F, -1F, 2, 2, 2);
		cap.setRotationPoint(0F, 9F, 0F);
		cap.setTextureSize(32, 32);
		cap.mirror = true;
		setRotation(cap, 0F, 0F, 0F);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		bottom.render(f5);
		middle1.render(f5);
		middle2.render(f5);
		middle3.render(f5);
		top.render(f5);
		cap.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.bottom.rotateAngleY = f3 / (180F / (float) Math.PI);
	}

}
