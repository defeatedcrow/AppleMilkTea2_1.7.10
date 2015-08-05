package mods.defeatedcrow.client.entity.base;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelInnerKobati extends ModelBase {
	// fields
	ModelRenderer kobathi1;
	ModelRenderer kobathi2;
	ModelRenderer kobathi3;
	ModelRenderer kobathi4;
	ModelRenderer kobathi5;
	ModelRenderer kobathi6;
	ModelRenderer kobathi7;
	ModelRenderer top1;
	ModelRenderer top2;
	ModelRenderer top3;

	public ModelInnerKobati() {
		textureWidth = 64;
		textureHeight = 32;

		kobathi1 = new ModelRenderer(this, 5, 0);
		kobathi1.addBox(0F, -1F, 0F, 1, 3, 1);
		kobathi1.setRotationPoint(0F, 20F, 0F);
		kobathi1.setTextureSize(64, 32);
		kobathi1.mirror = true;
		setRotation(kobathi1, 0.7853982F, 0.5061455F, 0F);
		kobathi2 = new ModelRenderer(this, 0, 0);
		kobathi2.addBox(-1F, -2F, -5F, 1, 3, 1);
		kobathi2.setRotationPoint(0F, 17F, 0F);
		kobathi2.setTextureSize(64, 32);
		kobathi2.mirror = true;
		setRotation(kobathi2, 1.570796F, 1.919862F, 0F);
		kobathi3 = new ModelRenderer(this, 5, 0);
		kobathi3.addBox(0.5F, -1F, -2F, 1, 3, 1);
		kobathi3.setRotationPoint(0F, 20F, 0F);
		kobathi3.setTextureSize(64, 32);
		kobathi3.mirror = true;
		setRotation(kobathi3, 1.570796F, 1.832596F, 0F);
		kobathi4 = new ModelRenderer(this, 0, 0);
		kobathi4.addBox(0F, -1F, -1F, 1, 2, 1);
		kobathi4.setRotationPoint(0F, 20F, 0F);
		kobathi4.setTextureSize(64, 32);
		kobathi4.mirror = true;
		setRotation(kobathi4, 1.570796F, 0.9948377F, 0F);
		kobathi5 = new ModelRenderer(this, 5, 0);
		kobathi5.addBox(0F, -0.5F, 0F, 1, 3, 1);
		kobathi5.setRotationPoint(0F, 20F, 0F);
		kobathi5.setTextureSize(64, 32);
		kobathi5.mirror = true;
		setRotation(kobathi5, 0.9773844F, -2.321288F, 0F);
		kobathi6 = new ModelRenderer(this, 0, 0);
		kobathi6.addBox(0.5F, -1F, -1F, 1, 3, 1);
		kobathi6.setRotationPoint(0F, 20F, 0F);
		kobathi6.setTextureSize(64, 32);
		kobathi6.mirror = true;
		setRotation(kobathi6, 1.134464F, 1.396263F, -0.418879F);
		kobathi7 = new ModelRenderer(this, 5, 0);
		kobathi7.addBox(-1.5F, 0F, 0.5F, 1, 2, 1);
		kobathi7.setRotationPoint(0F, 20F, 0F);
		kobathi7.setTextureSize(64, 32);
		kobathi7.mirror = true;
		setRotation(kobathi7, 1.047198F, 0.7853982F, -0.3839724F);
		top1 = new ModelRenderer(this, 0, 5);
		top1.addBox(-0.5F, -1.5F, -0.2F, 1, 1, 1);
		top1.setRotationPoint(0F, 20F, 0F);
		top1.setTextureSize(64, 32);
		top1.mirror = true;
		setRotation(top1, -0.3839724F, 0.1047198F, 0F);
		top2 = new ModelRenderer(this, 0, 5);
		top2.addBox(0F, -1.5F, -1F, 1, 1, 1);
		top2.setRotationPoint(0F, 20F, 0F);
		top2.setTextureSize(64, 32);
		top2.mirror = true;
		setRotation(top2, 0.6283185F, -0.1919862F, 0F);
		top3 = new ModelRenderer(this, 0, 5);
		top3.addBox(-1F, -1.3F, -1.2F, 1, 1, 1);
		top3.setRotationPoint(0F, 20F, 0F);
		top3.setTextureSize(64, 32);
		top3.mirror = true;
		setRotation(top3, -0.2443461F, -0.122173F, -0.2094395F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		kobathi1.render(f5);
		kobathi2.render(f5);
		kobathi3.render(f5);
		kobathi4.render(f5);
		kobathi5.render(f5);
		kobathi6.render(f5);
		kobathi7.render(f5);
		top1.render(f5);
		top2.render(f5);
		top3.render(f5);
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
