package mods.defeatedcrow.client.model.model;

import net.minecraft.src.*;
import net.minecraft.util.MathHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMakerHandle extends ModelBase {
	// fields
	ModelRenderer handle1 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer handle2 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer handle3 = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);
	ModelRenderer spout = (new ModelRenderer(this, 0, 0)).setTextureSize(32, 32);

	public ModelMakerHandle() {
		textureWidth = 32;
		textureHeight = 32;

		handle1.addBox(-1F, 0F, -7F, 2, 1, 3);
		handle1.setRotationPoint(0F, 10F, 0F);
		setRotation(handle1, 0F, 0F, 0F);
		handle2 = new ModelRenderer(this, 0, 0);
		handle2.addBox(-1F, 0F, -7F, 2, 8, 1);
		handle2.setRotationPoint(0F, 11F, 0F);
		setRotation(handle2, 0F, 0F, 0F);
		handle3 = new ModelRenderer(this, 0, 0);
		handle3.addBox(-1F, 0F, -7F, 2, 1, 2);
		handle3.setRotationPoint(0F, 19F, 0F);
		setRotation(handle3, 0F, 0F, 0F);
		spout = new ModelRenderer(this, 0, 0);
		spout.addBox(-1.5F, 0F, 4F, 3, 1, 2);
		spout.setRotationPoint(0F, 9F, 0F);
		setRotation(spout, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		handle1.render(f5);
		handle2.render(f5);
		handle3.render(f5);
		spout.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.handle1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.handle2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.handle3.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.spout.rotateAngleY = f3 / (180F / (float) Math.PI);
	}

}
