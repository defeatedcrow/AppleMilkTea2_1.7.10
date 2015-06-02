package mods.defeatedcrow.client.model.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSandwich extends ModelBase {

	ModelRenderer basketU;
	ModelRenderer basketF;
	ModelRenderer basketB;
	ModelRenderer basketL;
	ModelRenderer basketR;

	ModelRenderer bread11;
	ModelRenderer bread12;
	ModelRenderer bread21;
	ModelRenderer bread22;
	ModelRenderer bread31;
	ModelRenderer bread32;

	ModelRenderer apple1;
	ModelRenderer apple2;
	ModelRenderer apple3;
	ModelRenderer egg1;
	ModelRenderer egg2;
	ModelRenderer egg3;
	ModelRenderer cassis1;
	ModelRenderer cassis2;
	ModelRenderer cassis3;

	public ModelSandwich() {
		textureWidth = 64;
		textureHeight = 32;

		basketU = new ModelRenderer(this, 0, 0);
		basketU.addBox(-6F, 0F, -4F, 12, 1, 8);
		basketU.setRotationPoint(0F, 23F, 0F);
		basketU.setTextureSize(64, 32);
		basketU.mirror = true;
		setRotation(basketU, 0F, 0F, 0F);
		basketF = new ModelRenderer(this, 0, 10);
		basketF.addBox(-6F, 0F, -4F, 12, 4, 1);
		basketF.setRotationPoint(0F, 19F, 0F);
		basketF.setTextureSize(64, 32);
		basketF.mirror = true;
		setRotation(basketF, 0F, 0F, 0F);
		basketB = new ModelRenderer(this, 0, 10);
		basketB.addBox(-6F, 0F, -4F, 12, 4, 1);
		basketB.setRotationPoint(0F, 19F, 0F);
		basketB.setTextureSize(64, 32);
		basketB.mirror = true;
		setRotation(basketB, 0F, 3.141593F, 0F);
		basketL = new ModelRenderer(this, 40, 0);
		basketL.addBox(-6F, 0F, -3F, 1, 4, 6);
		basketL.setRotationPoint(0F, 19F, 0F);
		basketL.setTextureSize(64, 32);
		basketL.mirror = true;
		setRotation(basketL, 0F, 0F, 0F);
		basketR = new ModelRenderer(this, 40, 0);
		basketR.addBox(-6F, 0F, -3F, 1, 4, 6);
		basketR.setRotationPoint(0F, 19F, 0F);
		basketR.setTextureSize(64, 32);
		basketR.mirror = true;
		setRotation(basketR, 0F, 3.141593F, 0F);
		bread11 = new ModelRenderer(this, 0, 16);
		bread11.addBox(-4.5F, 0F, -2.5F, 1, 3, 5);
		bread11.setRotationPoint(0F, 18.5F, 0F);
		bread11.setTextureSize(64, 32);
		bread11.mirror = true;
		setRotation(bread11, 0F, -0.0698132F, -0.0872665F);
		bread12 = new ModelRenderer(this, 0, 16);
		bread12.addBox(-3F, 0F, -2.5F, 1, 3, 5);
		bread12.setRotationPoint(0F, 18.5F, 0F);
		bread12.setTextureSize(64, 32);
		bread12.mirror = true;
		setRotation(bread12, 0F, -0.0698132F, -0.122173F);
		bread21 = new ModelRenderer(this, 0, 16);
		bread21.addBox(-1.5F, 0F, -2.5F, 1, 3, 5);
		bread21.setRotationPoint(0F, 19F, 0F);
		bread21.setTextureSize(64, 32);
		bread21.mirror = true;
		setRotation(bread21, 0F, 0F, -0.122173F);
		bread22 = new ModelRenderer(this, 0, 16);
		bread22.addBox(0F, 0F, -2.5F, 1, 3, 5);
		bread22.setRotationPoint(0F, 19F, 0F);
		bread22.setTextureSize(64, 32);
		bread22.mirror = true;
		setRotation(bread22, 0F, 0F, -0.122173F);
		bread31 = new ModelRenderer(this, 0, 16);
		bread31.addBox(2F, 0F, -2.5F, 1, 3, 5);
		bread31.setRotationPoint(0F, 19F, 0F);
		bread31.setTextureSize(64, 32);
		bread31.mirror = true;
		setRotation(bread31, 0F, 0F, 0.0349066F);
		bread32 = new ModelRenderer(this, 0, 16);
		bread32.addBox(3.5F, 0F, -2.5F, 1, 3, 5);
		bread32.setRotationPoint(0F, 19F, 0F);
		bread32.setTextureSize(64, 32);
		bread32.mirror = true;
		setRotation(bread32, 0F, 0F, 0F);
		apple1 = new ModelRenderer(this, 0, 24);
		apple1.addBox(-3.5F, 0F, -2F, 1, 3, 4);
		apple1.setRotationPoint(0F, 19F, 0F);
		apple1.setTextureSize(64, 32);
		apple1.mirror = true;
		setRotation(apple1, 0F, -0.0698132F, 0F);
		apple2 = new ModelRenderer(this, 0, 24);
		apple2.addBox(-0.5F, 0F, -2F, 1, 3, 4);
		apple2.setRotationPoint(0F, 19.5F, 0F);
		apple2.setTextureSize(64, 32);
		apple2.mirror = true;
		setRotation(apple2, 0F, 0F, 0F);
		apple3 = new ModelRenderer(this, 0, 24);
		apple3.addBox(3F, 0F, -2F, 1, 3, 4);
		apple3.setRotationPoint(0F, 19.5F, 0F);
		apple3.setTextureSize(64, 32);
		apple3.mirror = true;
		setRotation(apple3, 0F, 0F, 0F);
		egg1 = new ModelRenderer(this, 10, 24);
		egg1.addBox(-4F, 0F, -2F, 1, 3, 4);
		egg1.setRotationPoint(0F, 19F, 0F);
		egg1.setTextureSize(64, 32);
		egg1.mirror = true;
		setRotation(egg1, 0F, -0.0698132F, 0F);
		egg2 = new ModelRenderer(this, 10, 24);
		egg2.addBox(-1F, 0F, -2F, 1, 3, 4);
		egg2.setRotationPoint(0F, 19.5F, 0F);
		egg2.setTextureSize(64, 32);
		egg2.mirror = true;
		setRotation(egg2, 0F, 0F, 0F);
		egg3 = new ModelRenderer(this, 10, 24);
		egg3.addBox(2.5F, 0F, -2F, 1, 3, 4);
		egg3.setRotationPoint(0F, 19.5F, 0F);
		egg3.setTextureSize(64, 32);
		egg3.mirror = true;
		setRotation(egg3, 0F, 0F, 0F);
		cassis1 = new ModelRenderer(this, 20, 24);
		cassis1.addBox(-4F, 0F, -2F, 1, 3, 4);
		cassis1.setRotationPoint(0F, 19F, 0F);
		cassis1.setTextureSize(64, 32);
		cassis1.mirror = true;
		setRotation(cassis1, 0F, -0.0698132F, 0F);
		cassis2 = new ModelRenderer(this, 20, 24);
		cassis2.addBox(-1F, 0F, -2F, 1, 3, 4);
		cassis2.setRotationPoint(0F, 19.5F, 0F);
		cassis2.setTextureSize(64, 32);
		cassis2.mirror = true;
		setRotation(cassis2, 0F, 0F, 0F);
		cassis3 = new ModelRenderer(this, 20, 24);
		cassis3.addBox(2.5F, 0F, -2F, 1, 3, 4);
		cassis3.setRotationPoint(0F, 19.5F, 0F);
		cassis3.setTextureSize(64, 32);
		cassis3.mirror = true;
		setRotation(cassis3, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, byte b0) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		basketU.render(f5);
		basketF.render(f5);
		basketB.render(f5);
		basketL.render(f5);
		basketR.render(f5);
		bread11.render(f5);
		bread12.render(f5);
		bread21.render(f5);
		bread22.render(f5);
		bread31.render(f5);
		bread32.render(f5);

		if (b0 == 1) {
			egg1.render(f5);
			egg2.render(f5);
			egg3.render(f5);
		} else if (b0 == 2) {
			cassis1.render(f5);
			cassis2.render(f5);
			cassis3.render(f5);
		} else {
			apple1.render(f5);
			apple2.render(f5);
			apple3.render(f5);
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.basketU.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.basketF.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.basketB.rotateAngleY = 3.141593F + f3 / (180F / (float) Math.PI);
		this.basketR.rotateAngleY = 3.141593F + f3 / (180F / (float) Math.PI);
		this.basketL.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.bread11.rotateAngleY = -0.0698132F + f3 / (180F / (float) Math.PI);
		this.bread12.rotateAngleY = -0.0698132F + f3 / (180F / (float) Math.PI);
		this.bread21.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.bread22.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.bread31.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.bread32.rotateAngleY = f3 / (180F / (float) Math.PI);

		this.apple1.rotateAngleY = -0.0698132F + f3 / (180F / (float) Math.PI);
		this.apple2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.apple3.rotateAngleY = f3 / (180F / (float) Math.PI);

		this.egg1.rotateAngleY = -0.0698132F + f3 / (180F / (float) Math.PI);
		this.egg2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.egg3.rotateAngleY = f3 / (180F / (float) Math.PI);

		this.cassis1.rotateAngleY = -0.0698132F + f3 / (180F / (float) Math.PI);
		this.cassis2.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.cassis3.rotateAngleY = f3 / (180F / (float) Math.PI);
	}

}
