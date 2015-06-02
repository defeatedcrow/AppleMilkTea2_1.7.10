package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFossilCannon extends ModelBase {
	// fields
	ModelRenderer muz1;
	ModelRenderer muz2;
	ModelRenderer muz3;
	ModelRenderer muz4;
	ModelRenderer syl1;
	ModelRenderer syl2;
	ModelRenderer bod1;
	ModelRenderer base1;
	ModelRenderer base2;
	ModelRenderer sca1;
	ModelRenderer sca2;
	ModelRenderer sca3;
	ModelRenderer sca4;
	ModelRenderer finbase;
	ModelRenderer fin1;
	ModelRenderer fin2;
	ModelRenderer fin3;
	ModelRenderer fin4;
	ModelRenderer fin5;
	ModelRenderer fin6;
	ModelRenderer fin7;

	public ModelFossilCannon() {
		textureWidth = 64;
		textureHeight = 32;

		muz1 = new ModelRenderer(this, 0, 0);
		muz1.addBox(3F, -1.5F, -1F, 12, 1, 2);
		muz1.setRotationPoint(0F, 16F, 0F);
		muz1.setTextureSize(64, 32);
		muz1.mirror = true;
		setRotation(muz1, 0F, 1.570796F, 0F);
		muz2 = new ModelRenderer(this, 0, 0);
		muz2.addBox(3F, -2F, 1F, 12, 3, 1);
		muz2.setRotationPoint(0F, 17F, 0F);
		muz2.setTextureSize(64, 32);
		muz2.mirror = true;
		setRotation(muz2, 0F, 1.570796F, 0F);
		muz3 = new ModelRenderer(this, 0, 0);
		muz3.addBox(3F, -1F, -2F, 12, 3, 1);
		muz3.setRotationPoint(0F, 16F, 0F);
		muz3.setTextureSize(64, 32);
		muz3.mirror = true;
		setRotation(muz3, 0F, 1.570796F, 0F);
		muz4 = new ModelRenderer(this, 0, 0);
		muz4.addBox(3F, 1.5F, -1F, 12, 1, 2);
		muz4.setRotationPoint(0F, 16F, 0F);
		muz4.setTextureSize(64, 32);
		muz4.mirror = true;
		setRotation(muz4, 0F, 1.570796F, 0F);
		syl1 = new ModelRenderer(this, 0, 0);
		syl1.addBox(-1F, -0.5F, -5F, 2, 2, 4);
		syl1.setRotationPoint(0F, 16F, 0F);
		syl1.setTextureSize(64, 32);
		syl1.mirror = true;
		setRotation(syl1, 0F, 0F, 0F);
		syl2 = new ModelRenderer(this, 0, 8);
		syl2.addBox(-1.5F, -0.5F, -1F, 3, 3, 3);
		syl2.setRotationPoint(0F, 16F, 0F);
		syl2.setTextureSize(64, 32);
		syl2.mirror = true;
		setRotation(syl2, 0F, 0F, 0F);
		bod1 = new ModelRenderer(this, 0, 24);
		bod1.addBox(-2F, 0F, 2F, 4, 2, 6);
		bod1.setRotationPoint(0F, 16F, 0F);
		bod1.setTextureSize(64, 32);
		bod1.mirror = true;
		setRotation(bod1, 0F, 0F, 0F);
		base1 = new ModelRenderer(this, 0, 21);
		base1.addBox(-3.5F, 4F, 1F, 7, 4, 7);
		base1.setRotationPoint(0F, 16F, 0F);
		base1.setTextureSize(64, 32);
		base1.mirror = true;
		setRotation(base1, 0F, 0F, 0F);
		base2 = new ModelRenderer(this, 0, 0);
		base2.addBox(-2F, 3F, 2F, 4, 2, 5);
		base2.setRotationPoint(0F, 15F, 0F);
		base2.setTextureSize(64, 32);
		base2.mirror = true;
		setRotation(base2, 0F, 0F, 0F);
		sca1 = new ModelRenderer(this, 40, 0);
		sca1.addBox(-2.5F, 0.5F, 4F, 5, 1, 6);
		sca1.setRotationPoint(0F, 16F, 0F);
		sca1.setTextureSize(64, 32);
		sca1.mirror = true;
		setRotation(sca1, 0.2617994F, 0F, 0F);
		sca2 = new ModelRenderer(this, 40, 0);
		sca2.addBox(-2.5F, -2F, 0F, 5, 1, 6);
		sca2.setRotationPoint(0F, 17F, 0F);
		sca2.setTextureSize(64, 32);
		sca2.mirror = true;
		setRotation(sca2, 0.2617994F, 0F, 0F);
		sca3 = new ModelRenderer(this, 32, 0);
		sca3.addBox(-1F, -0.5F, 4F, 2, 2, 2);
		sca3.setRotationPoint(0F, 16F, 0F);
		sca3.setTextureSize(64, 32);
		sca3.mirror = true;
		setRotation(sca3, 0.1745329F, 0F, 0F);
		sca4 = new ModelRenderer(this, 32, 0);
		sca4.addBox(-1F, -2F, -1F, 2, 2, 2);
		sca4.setRotationPoint(0F, 16F, 0F);
		sca4.setTextureSize(64, 32);
		sca4.mirror = true;
		setRotation(sca4, 0.1745329F, 0F, 0F);
		finbase = new ModelRenderer(this, 13, 8);
		finbase.addBox(-1F, 0F, 7F, 3, 2, 3);
		finbase.setRotationPoint(0F, 16F, 0F);
		finbase.setTextureSize(64, 32);
		finbase.mirror = true;
		setRotation(finbase, 0F, 0.1745329F, 0F);
		fin1 = new ModelRenderer(this, 32, 20);
		fin1.addBox(-2F, -2F, 8F, 2, 2, 10);
		fin1.setRotationPoint(0F, 16F, 0F);
		fin1.setTextureSize(64, 32);
		fin1.mirror = true;
		setRotation(fin1, -0.2617994F, 0.5235988F, 0F);
		fin2 = new ModelRenderer(this, 32, 26);
		fin2.addBox(-4F, -2F, 8F, 4, 2, 4);
		fin2.setRotationPoint(0F, 16F, 0F);
		fin2.setTextureSize(64, 32);
		fin2.mirror = true;
		setRotation(fin2, -0.2617994F, 0.3490659F, 0F);
		fin3 = new ModelRenderer(this, 32, 25);
		fin3.addBox(8F, -1F, 15F, 2, 2, 5);
		fin3.setRotationPoint(0F, 16F, 0F);
		fin3.setTextureSize(64, 32);
		fin3.mirror = true;
		setRotation(fin3, -0.2617994F, -0.0872665F, 0F);
		fin4 = new ModelRenderer(this, 32, 9);
		fin4.addBox(-2F, -2F, 11F, 3, 1, 8);
		fin4.setRotationPoint(0F, 16F, 0F);
		fin4.setTextureSize(64, 32);
		fin4.mirror = true;
		setRotation(fin4, -0.296706F, 0.3839724F, 0F);
		fin5 = new ModelRenderer(this, 33, 10);
		fin5.addBox(-1F, -2F, 11F, 3, 1, 7);
		fin5.setRotationPoint(0F, 16F, 0F);
		fin5.setTextureSize(64, 32);
		fin5.mirror = true;
		setRotation(fin5, -0.3141593F, 0.2094395F, 0F);
		fin6 = new ModelRenderer(this, 34, 11);
		fin6.addBox(0F, -2F, 11F, 3, 1, 6);
		fin6.setRotationPoint(0F, 16F, 0F);
		fin6.setTextureSize(64, 32);
		fin6.mirror = true;
		setRotation(fin6, -0.3316126F, 0.0349066F, 0F);
		fin7 = new ModelRenderer(this, 32, 22);
		fin7.addBox(0F, -3F, 9F, 2, 2, 8);
		fin7.setRotationPoint(0F, 16F, 0F);
		fin7.setTextureSize(64, 32);
		fin7.mirror = true;
		setRotation(fin7, -0.3665191F, -0.1396263F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		muz1.render(f5);
		muz2.render(f5);
		muz3.render(f5);
		muz4.render(f5);
		syl1.render(f5);
		syl2.render(f5);
		bod1.render(f5);
		base1.render(f5);
		base2.render(f5);
		sca1.render(f5);
		sca2.render(f5);
		sca3.render(f5);
		sca4.render(f5);
		finbase.render(f5);
		fin1.render(f5);
		fin2.render(f5);
		fin3.render(f5);
		fin4.render(f5);
		fin5.render(f5);
		fin6.render(f5);
		fin7.render(f5);
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
