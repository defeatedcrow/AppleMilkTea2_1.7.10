package mods.defeatedcrow.client.model.model;

import mods.defeatedcrow.common.entity.EntityAnchorMissile;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAnchorMissile extends ModelBase {
	// fields
	ModelRenderer head;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer body;
	ModelRenderer tail1;
	ModelRenderer tail2;
	ModelRenderer tail3;
	ModelRenderer tail4;
	ModelRenderer wingL1;
	ModelRenderer wingL2;
	ModelRenderer wingR1;
	ModelRenderer wingR2;
	ModelRenderer wing3;
	ModelRenderer burn;

	public ModelAnchorMissile() {
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-2F, -1.5F, -2F, 4, 3, 4);
		head.setRotationPoint(0F, 16F, -4F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		head2 = new ModelRenderer(this, 0, 0);
		head2.addBox(-1.5F, -1F, -4F, 3, 2, 2);
		head2.setRotationPoint(0F, 16F, -4F);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0F, 0F, 0F);
		head3 = new ModelRenderer(this, 0, 0);
		head3.addBox(-1F, -0.5F, -5F, 2, 1, 1);
		head3.setRotationPoint(0F, 16F, -4F);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0F, 0F, 0F);
		body = new ModelRenderer(this, 36, 10);
		body.addBox(-1.5F, -1F, 2F, 3, 2, 10);
		body.setRotationPoint(0F, 16F, -4F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		tail1 = new ModelRenderer(this, 28, 0);
		tail1.addBox(-2F, -1F, 12F, 4, 1, 3);
		tail1.setRotationPoint(0F, 16F, -4F);
		tail1.setTextureSize(64, 32);
		tail1.mirror = true;
		setRotation(tail1, 0.0523599F, 0F, 0F);
		tail2 = new ModelRenderer(this, 28, 0);
		tail2.addBox(-2F, 0F, 12F, 4, 1, 3);
		tail2.setRotationPoint(0F, 16F, -4F);
		tail2.setTextureSize(64, 32);
		tail2.mirror = true;
		setRotation(tail2, -0.0523599F, 0F, 0F);
		tail3 = new ModelRenderer(this, 43, 0);
		tail3.addBox(0F, -1F, 12F, 1, 2, 3);
		tail3.setRotationPoint(0F, 16F, -4F);
		tail3.setTextureSize(64, 32);
		tail3.mirror = true;
		setRotation(tail3, 0F, 0.0872665F, 0F);
		tail4 = new ModelRenderer(this, 43, 0);
		tail4.addBox(-1F, -1F, 12F, 1, 2, 3);
		tail4.setRotationPoint(0F, 16F, -4F);
		tail4.setTextureSize(64, 32);
		tail4.mirror = true;
		setRotation(tail4, 0F, -0.0872665F, 0F);
		wingL1 = new ModelRenderer(this, 0, 8);
		wingL1.addBox(-1F, 0F, 1F, 3, 1, 10);
		wingL1.setRotationPoint(0F, 16F, -4F);
		wingL1.setTextureSize(64, 32);
		wingL1.mirror = true;
		setRotation(wingL1, 0F, 0.7853982F, 0F);
		wingL2 = new ModelRenderer(this, 28, 24);
		wingL2.addBox(-2.5F, 0F, 11F, 5, 1, 2);
		wingL2.setRotationPoint(0F, 16F, -4F);
		wingL2.setTextureSize(64, 32);
		wingL2.mirror = true;
		setRotation(wingL2, 0F, 0.7853982F, 0F);
		wingR1 = new ModelRenderer(this, 0, 20);
		wingR1.addBox(-2F, 0F, 1F, 3, 1, 10);
		wingR1.setRotationPoint(0F, 16F, -4F);
		wingR1.setTextureSize(64, 32);
		wingR1.mirror = true;
		setRotation(wingR1, 0F, -0.7853982F, 0F);
		wingR2 = new ModelRenderer(this, 28, 28);
		wingR2.addBox(-2.5F, 0F, 11F, 5, 1, 2);
		wingR2.setRotationPoint(0F, 16F, -4F);
		wingR2.setTextureSize(64, 32);
		wingR2.mirror = true;
		setRotation(wingR2, 0F, -0.7853982F, 0F);
		wing3 = new ModelRenderer(this, 28, 5);
		wing3.addBox(-0.5F, 3F, 7F, 1, 2, 4);
		wing3.setRotationPoint(0F, 16F, -4F);
		wing3.setTextureSize(64, 32);
		wing3.mirror = true;
		setRotation(wing3, 0.5235988F, 0F, 0F);
		burn = new ModelRenderer(this, 52, 0);
		burn.addBox(-1.5F, -1F, 13F, 3, 2, 1);
		burn.setRotationPoint(0F, 16F, -4F);
		burn.setTextureSize(64, 32);
		burn.mirror = true;
		setRotation(burn, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean flag) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity, flag);
		head.render(f5);
		head2.render(f5);
		head3.render(f5);
		body.render(f5);
		tail1.render(f5);
		tail2.render(f5);
		tail3.render(f5);
		tail4.render(f5);
		wingL1.render(f5);
		wingL2.render(f5);
		wingR1.render(f5);
		wingR2.render(f5);
		wing3.render(f5);
		if (flag)
			burn.render(f5);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity, boolean flag) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		if (flag) {
			this.wingL1.rotateAngleY = 0.7853982F;
			this.wingL2.rotateAngleY = 0.7853982F;
			this.wingR1.rotateAngleY = -0.7853982F;
			this.wingR2.rotateAngleY = -0.7853982F;
		} else {
			this.wingL1.rotateAngleY = 0F;
			this.wingL2.rotateAngleY = 0F;
			this.wingR1.rotateAngleY = 0F;
			this.wingR2.rotateAngleY = 0F;
		}
	}

}
