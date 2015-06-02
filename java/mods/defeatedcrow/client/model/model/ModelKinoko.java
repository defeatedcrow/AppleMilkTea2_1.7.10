package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelKinoko extends ModelBase {
	// fields
	ModelRenderer base;
	ModelRenderer base2;
	ModelRenderer head1;
	ModelRenderer head2;

	public ModelKinoko() {
		textureWidth = 64;
		textureHeight = 32;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(-3F, 0F, -3F, 6, 2, 6);
		base.setRotationPoint(0F, 22F, 0F);
		base.setTextureSize(64, 32);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		base2 = new ModelRenderer(this, 24, 0);
		base2.addBox(-2F, 0F, -2F, 4, 5, 4);
		base2.setRotationPoint(0F, 17F, 0F);
		base2.setTextureSize(64, 32);
		base2.mirror = true;
		setRotation(base2, 0F, 0F, 0F);
		head1 = new ModelRenderer(this, 0, 17);
		head1.addBox(-6F, 0F, -6F, 12, 3, 12);
		head1.setRotationPoint(0F, 14F, 0F);
		head1.setTextureSize(64, 32);
		head1.mirror = true;
		setRotation(head1, 0F, 0F, 0F);
		head2 = new ModelRenderer(this, 4, 19);
		head2.addBox(-5F, 0F, -5F, 10, 1, 10);
		head2.setRotationPoint(0F, 13F, 0F);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		base.render(f5);
		base2.render(f5);
		head1.render(f5);
		head2.render(f5);
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
