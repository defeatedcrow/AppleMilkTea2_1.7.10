package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelJawCrusher extends ModelBase {
	// fields
	ModelRenderer body;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer blade;
	ModelRenderer lod;
	ModelRenderer gear1;
	ModelRenderer gear2;
	ModelRenderer base2;
	ModelRenderer motor;
	ModelRenderer lod2;
	ModelRenderer gear3;
	ModelRenderer belt1;
	ModelRenderer belt2;
	ModelRenderer base;

	public ModelJawCrusher() {
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this, 0, 0);
		body.addBox(-5F, 0F, -1F, 10, 8, 4);
		body.setRotationPoint(0F, 15F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		body2 = new ModelRenderer(this, 28, 0);
		body2.addBox(-5F, 0F, 6F, 10, 8, 1);
		body2.setRotationPoint(0F, 15F, 0F);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0F, 0F, 0F);
		body3 = new ModelRenderer(this, 32, 21);
		body3.addBox(4F, 0F, 3F, 1, 8, 3);
		body3.setRotationPoint(0F, 15F, 0F);
		body3.setTextureSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0F, 0F, 0F);
		body4 = new ModelRenderer(this, 32, 21);
		body4.addBox(-5F, 0F, 3F, 1, 8, 3);
		body4.setRotationPoint(0F, 15F, 0F);
		body4.setTextureSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0F, 0F, 0F);
		body5 = new ModelRenderer(this, 40, 20);
		body5.addBox(-4F, 0F, -2F, 8, 8, 4);
		body5.setRotationPoint(0F, 14.5F, 0F);
		body5.setTextureSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.1396263F, 0F, 0F);
		blade = new ModelRenderer(this, 16, 15);
		blade.addBox(-4F, 0F, 3F, 8, 7, 1);
		blade.setRotationPoint(0F, 16F, 0F);
		blade.setTextureSize(64, 32);
		blade.mirror = true;
		setRotation(blade, 0.1745329F, 0F, 0F);
		lod = new ModelRenderer(this, 0, 12);
		lod.addBox(-6F, 0F, 0F, 12, 1, 1);
		lod.setRotationPoint(0F, 16F, 0F);
		lod.setTextureSize(64, 32);
		lod.mirror = true;
		setRotation(lod, 0F, 0F, 0F);
		gear1 = new ModelRenderer(this, 0, 14);
		gear1.addBox(6F, 0F, -2F, 1, 5, 5);
		gear1.setRotationPoint(0F, 14F, 0F);
		gear1.setTextureSize(64, 32);
		gear1.mirror = true;
		setRotation(gear1, 0F, 0F, 0F);
		gear2 = new ModelRenderer(this, 0, 14);
		gear2.addBox(-7F, 0F, -2F, 1, 5, 5);
		gear2.setRotationPoint(0F, 14F, 0F);
		gear2.setTextureSize(64, 32);
		gear2.mirror = true;
		setRotation(gear2, 0F, 0F, 0F);
		base2 = new ModelRenderer(this, 0, 17);
		base2.addBox(-5F, 0F, -7F, 1, 1, 14);
		base2.setRotationPoint(0F, 23F, 0F);
		base2.setTextureSize(64, 32);
		base2.mirror = true;
		setRotation(base2, 0F, 0F, 0F);
		motor = new ModelRenderer(this, 38, 13);
		motor.addBox(-5F, 0F, -7F, 10, 3, 3);
		motor.setRotationPoint(0F, 20F, 0F);
		motor.setTextureSize(64, 32);
		motor.mirror = true;
		setRotation(motor, 0F, 0F, 0F);
		lod2 = new ModelRenderer(this, 0, 12);
		lod2.addBox(5F, 0F, -6F, 1, 1, 1);
		lod2.setRotationPoint(0F, 21F, 0F);
		lod2.setTextureSize(64, 32);
		lod2.mirror = true;
		setRotation(lod2, 0F, 0F, 0F);
		gear3 = new ModelRenderer(this, 0, 24);
		gear3.addBox(6F, 0F, -7F, 1, 3, 3);
		gear3.setRotationPoint(0F, 20F, 0F);
		gear3.setTextureSize(64, 32);
		gear3.mirror = true;
		setRotation(gear3, 0F, 0F, 0F);
		belt1 = new ModelRenderer(this, 43, 4);
		belt1.addBox(6F, 0F, -10F, 1, 0, 8);
		belt1.setRotationPoint(0F, 12.5F, 0F);
		belt1.setTextureSize(64, 32);
		belt1.mirror = true;
		setRotation(belt1, 0.8726646F, 0F, 0F);
		belt2 = new ModelRenderer(this, 43, 4);
		belt2.addBox(6F, 0F, -5.5F, 1, 0, 8);
		belt2.setRotationPoint(0F, 20F, 0F);
		belt2.setTextureSize(64, 32);
		belt2.mirror = true;
		setRotation(belt2, 0.6108652F, 0F, 0F);
		base = new ModelRenderer(this, 0, 17);
		base.addBox(4F, 0F, -7F, 1, 1, 14);
		base.setRotationPoint(0F, 23F, 0F);
		base.setTextureSize(64, 32);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean active) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity, active);
		body.render(f5);
		body2.render(f5);
		body3.render(f5);
		body4.render(f5);
		body5.render(f5);
		blade.render(f5);
		lod.render(f5);
		gear1.render(f5);
		gear2.render(f5);
		base2.render(f5);
		motor.render(f5);
		lod2.render(f5);
		gear3.render(f5);
		belt1.render(f5);
		belt2.render(f5);
		base.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity, boolean b) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.body.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.body2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.body3.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.body4.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.body5.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.blade.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.lod.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.lod2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.gear1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.gear2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.gear3.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.base.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.base2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.belt2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.belt1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.motor.rotateAngleY = f3 / (180F / (float) Math.PI);

		// 以下は歯車の回転部分だが、軸の問題で思うような観点をしないため保留。要改良。
		// if (b)
		// {
		// this.gear1.rotateAngleX += 1 / (180F / (float)Math.PI);
		// this.gear2.rotateAngleX += 1 / (180F / (float)Math.PI);
		// this.gear3.rotateAngleX += 1 / (180F / (float)Math.PI);
		// }
	}

}
