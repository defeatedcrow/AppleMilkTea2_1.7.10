package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCharger extends ModelBase {
	// fields
	ModelRenderer bottom;
	ModelRenderer top;
	ModelRenderer back;
	ModelRenderer side1;
	ModelRenderer side2;
	ModelRenderer inner;
	ModelRenderer button1;
	ModelRenderer button2;
	ModelRenderer dial;
	ModelRenderer panel1;
	ModelRenderer panel2;

	public ModelCharger() {
		textureWidth = 128;
		textureHeight = 64;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-8F, 7F, -8F, 16, 1, 16);
		bottom.setRotationPoint(0F, 16F, 0F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		top = new ModelRenderer(this, 0, 0);
		top.addBox(-8F, -8F, -8F, 16, 1, 16);
		top.setRotationPoint(0F, 16F, 0F);
		top.setTextureSize(64, 32);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		back = new ModelRenderer(this, 64, 0);
		back.addBox(-8F, -7F, 7F, 16, 14, 1);
		back.setRotationPoint(0F, 16F, 0F);
		back.setTextureSize(64, 32);
		back.mirror = true;
		setRotation(back, 0F, 0F, 0F);
		side1 = new ModelRenderer(this, 64, 1);
		side1.addBox(-8F, -7F, -8F, 1, 14, 16);
		side1.setRotationPoint(0F, 16F, 0F);
		side1.setTextureSize(64, 32);
		side1.mirror = true;
		setRotation(side1, 0F, 0F, 0F);
		side2 = new ModelRenderer(this, 64, 1);
		side2.addBox(7F, -7F, -8F, 1, 14, 16);
		side2.setRotationPoint(0F, 16F, 0F);
		side2.setTextureSize(64, 32);
		side2.mirror = true;
		setRotation(side2, 0F, 0F, 0F);
		inner = new ModelRenderer(this, 0, 18);
		inner.addBox(-7F, -7F, -6F, 14, 14, 13);
		inner.setRotationPoint(0F, 16F, 0F);
		inner.setTextureSize(64, 32);
		inner.mirror = true;
		setRotation(inner, 0F, 0F, 0F);
		button1 = new ModelRenderer(this, 0, 48);
		button1.addBox(-5F, 1F, -7F, 1, 1, 1);
		button1.setRotationPoint(0F, 16F, 0F);
		button1.setTextureSize(64, 32);
		button1.mirror = true;
		setRotation(button1, 0F, 0F, 0F);
		button2 = new ModelRenderer(this, 4, 48);
		button2.addBox(-3F, 1F, -7F, 1, 1, 1);
		button2.setRotationPoint(0F, 16F, 0F);
		button2.setTextureSize(64, 32);
		button2.mirror = true;
		setRotation(button2, 0F, 0F, 0F);
		dial = new ModelRenderer(this, 8, 48);
		dial.addBox(3F, 0F, -7F, 2, 2, 1);
		dial.setRotationPoint(0F, 16F, 0F);
		dial.setTextureSize(64, 32);
		dial.mirror = true;
		setRotation(dial, 0F, 0F, 0F);
		panel1 = new ModelRenderer(this, 0, 51);
		panel1.addBox(-3F, -5F, -6.5F, 8, 4, 1);
		panel1.setRotationPoint(0F, 16F, 0F);
		panel1.setTextureSize(64, 32);
		panel1.mirror = true;
		setRotation(panel1, 0F, 0F, 0F);
		panel2 = new ModelRenderer(this, 0, 56);
		panel2.addBox(-6F, 4F, -7F, 12, 2, 1);
		panel2.setRotationPoint(0F, 16F, 0F);
		panel2.setTextureSize(64, 32);
		panel2.mirror = true;
		setRotation(panel2, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bottom.render(f5);
		top.render(f5);
		back.render(f5);
		side1.render(f5);
		side2.render(f5);
		inner.render(f5);
		button1.render(f5);
		button2.render(f5);
		dial.render(f5);
		panel1.render(f5);
		panel2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.bottom.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.top.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.back.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.side1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.side2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.inner.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.button1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.button2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.dial.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.panel1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.panel2.rotateAngleY = f3 / (180F / (float) Math.PI);
	}

}
