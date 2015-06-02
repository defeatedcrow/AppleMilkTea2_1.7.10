package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelIncenseBase extends ModelBase {
	// fields
	ModelRenderer base;
	ModelRenderer base2;
	ModelRenderer body;
	ModelRenderer side1;
	ModelRenderer side2;
	ModelRenderer side3;
	ModelRenderer side4;
	ModelRenderer middle1;
	ModelRenderer middle2;
	ModelRenderer middle3;
	ModelRenderer middle4;
	ModelRenderer top1;
	ModelRenderer top2;
	ModelRenderer top3;
	ModelRenderer top4;
	ModelRenderer top5;
	ModelRenderer top6;
	ModelRenderer top7;
	ModelRenderer top8;
	ModelRenderer top9;
	ModelRenderer cone1;
	ModelRenderer cone2;
	ModelRenderer glow;

	public ModelIncenseBase() {
		textureWidth = 64;
		textureHeight = 32;

		base = new ModelRenderer(this, 0, 23);
		base.addBox(-3F, 0F, -3F, 6, 1, 6);
		base.setRotationPoint(0F, 23F, 0F);
		base.setTextureSize(64, 32);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		base2 = new ModelRenderer(this, 0, 23);
		base2.addBox(-4F, 0F, -4F, 8, 1, 8);
		base2.setRotationPoint(0F, 22F, 0F);
		base2.setTextureSize(64, 32);
		base2.mirror = true;
		setRotation(base2, 0F, 0F, 0F);
		body = new ModelRenderer(this, 8, 0);
		body.addBox(-5F, 0F, -5F, 10, 1, 10);
		body.setRotationPoint(0F, 21F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		side1 = new ModelRenderer(this, 0, 12);
		side1.addBox(-5F, 0F, -5F, 10, 3, 1);
		side1.setRotationPoint(0F, 18F, 0F);
		side1.setTextureSize(64, 32);
		side1.mirror = true;
		setRotation(side1, 0F, 0F, 0F);
		side2 = new ModelRenderer(this, 0, 12);
		side2.addBox(-5F, 0F, 4F, 10, 3, 1);
		side2.setRotationPoint(0F, 18F, 0F);
		side2.setTextureSize(64, 32);
		side2.mirror = true;
		setRotation(side2, 0F, 0F, 0F);
		side3 = new ModelRenderer(this, 16, 11);
		side3.addBox(-5F, 0F, -4F, 1, 3, 8);
		side3.setRotationPoint(0F, 18F, 0F);
		side3.setTextureSize(64, 32);
		side3.mirror = true;
		setRotation(side3, 0F, 0F, 0F);
		side4 = new ModelRenderer(this, 16, 11);
		side4.addBox(4F, 0F, -4F, 1, 3, 8);
		side4.setRotationPoint(0F, 18F, 0F);
		side4.setTextureSize(64, 32);
		side4.mirror = true;
		setRotation(side4, 0F, 0F, 0F);
		middle1 = new ModelRenderer(this, 0, 0);
		middle1.addBox(-4F, 0F, -4F, 8, 1, 1);
		middle1.setRotationPoint(0F, 17F, 0F);
		middle1.setTextureSize(64, 32);
		middle1.mirror = true;
		setRotation(middle1, 0F, 0F, 0F);
		middle2 = new ModelRenderer(this, 0, 0);
		middle2.addBox(-4F, 0F, 3F, 8, 1, 1);
		middle2.setRotationPoint(0F, 17F, 0F);
		middle2.setTextureSize(64, 32);
		middle2.mirror = true;
		setRotation(middle2, 0F, 0F, 0F);
		middle3 = new ModelRenderer(this, 0, 0);
		middle3.addBox(-4F, 0F, -3F, 1, 1, 6);
		middle3.setRotationPoint(0F, 17F, 0F);
		middle3.setTextureSize(64, 32);
		middle3.mirror = true;
		setRotation(middle3, 0F, 0F, 0F);
		middle4 = new ModelRenderer(this, 0, 0);
		middle4.addBox(3F, 0F, -3F, 1, 1, 6);
		middle4.setRotationPoint(0F, 17F, 0F);
		middle4.setTextureSize(64, 32);
		middle4.mirror = true;
		setRotation(middle4, 0F, 0F, 0F);
		top1 = new ModelRenderer(this, 0, 0);
		top1.addBox(-0.5F, 0F, -4F, 1, 1, 4);
		top1.setRotationPoint(0F, 15F, 0F);
		top1.setTextureSize(64, 32);
		top1.mirror = true;
		setRotation(top1, 0.5235988F, 0F, 0F);
		top2 = new ModelRenderer(this, 0, 0);
		top2.addBox(-0.5F, 0F, -4F, 1, 1, 4);
		top2.setRotationPoint(0F, 15F, 0F);
		top2.setTextureSize(64, 32);
		top2.mirror = true;
		setRotation(top2, 0.5235988F, 1.570796F, 0F);
		top3 = new ModelRenderer(this, 0, 0);
		top3.addBox(-0.5F, 0F, -4F, 1, 1, 4);
		top3.setRotationPoint(0F, 15F, 0F);
		top3.setTextureSize(64, 32);
		top3.mirror = true;
		setRotation(top3, 0.5235988F, 3.141593F, 0F);
		top4 = new ModelRenderer(this, 0, 0);
		top4.addBox(-0.5F, 0F, -4F, 1, 1, 4);
		top4.setRotationPoint(0F, 15F, 0F);
		top4.setTextureSize(64, 32);
		top4.mirror = true;
		setRotation(top4, 0.5235988F, -1.570796F, 0F);
		top5 = new ModelRenderer(this, 0, 0);
		top5.addBox(-0.5F, 0F, -5F, 1, 1, 5);
		top5.setRotationPoint(0F, 15F, 0F);
		top5.setTextureSize(64, 32);
		top5.mirror = true;
		setRotation(top5, 0.5235988F, 0.7853982F, 0F);
		top6 = new ModelRenderer(this, 0, 0);
		top6.addBox(-0.5F, 0F, -5F, 1, 1, 5);
		top6.setRotationPoint(0F, 15F, 0F);
		top6.setTextureSize(64, 32);
		top6.mirror = true;
		setRotation(top6, 0.5235988F, 2.356194F, 0F);
		top7 = new ModelRenderer(this, 0, 0);
		top7.addBox(-0.5F, 0F, -5F, 1, 1, 5);
		top7.setRotationPoint(0F, 15F, 0F);
		top7.setTextureSize(64, 32);
		top7.mirror = true;
		setRotation(top7, 0.5235988F, -2.356194F, 0F);
		top8 = new ModelRenderer(this, 0, 0);
		top8.addBox(-0.5F, 0F, -5F, 1, 1, 5);
		top8.setRotationPoint(0F, 15F, 0F);
		top8.setTextureSize(64, 32);
		top8.mirror = true;
		setRotation(top8, 0.5235988F, -0.7853982F, 0F);
		top9 = new ModelRenderer(this, 0, 0);
		top9.addBox(-1F, 0F, -1F, 2, 1, 2);
		top9.setRotationPoint(0F, 15F, 0F);
		top9.setTextureSize(64, 32);
		top9.mirror = true;
		setRotation(top9, 0F, 0F, 0F);
		cone1 = new ModelRenderer(this, 0, 18);
		cone1.addBox(-1F, 0F, -1F, 2, 1, 2);
		cone1.setRotationPoint(0F, 20F, 0F);
		cone1.setTextureSize(64, 32);
		cone1.mirror = true;
		setRotation(cone1, 0F, 0F, 0F);
		cone2 = new ModelRenderer(this, 0, 18);
		cone2.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
		cone2.setRotationPoint(0F, 19F, 0F);
		cone2.setTextureSize(64, 32);
		cone2.mirror = true;
		setRotation(cone2, 0F, 0F, 0F);
		glow = new ModelRenderer(this, 9, 18);
		glow.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
		glow.setRotationPoint(0F, 18F, 0F);
		glow.setTextureSize(64, 32);
		glow.mirror = true;
		setRotation(glow, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		base.render(f5);
		base2.render(f5);
		body.render(f5);
		side1.render(f5);
		side2.render(f5);
		side3.render(f5);
		side4.render(f5);
		middle1.render(f5);
		middle2.render(f5);
		middle3.render(f5);
		middle4.render(f5);
		top1.render(f5);
		top2.render(f5);
		top3.render(f5);
		top4.render(f5);
		top5.render(f5);
		top6.render(f5);
		top7.render(f5);
		top8.render(f5);
		top9.render(f5);
	}

	public void renderCone(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		cone1.render(f5);
		cone2.render(f5);
	}

	public void renderGlow(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		glow.render(f5);
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
