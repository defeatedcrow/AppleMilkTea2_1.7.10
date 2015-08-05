package mods.defeatedcrow.client.entity.base;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelInnerSoup extends ModelBase {
	// fields
	ModelRenderer gu1;
	ModelRenderer gu2;
	ModelRenderer gu3;
	ModelRenderer gu4;
	ModelRenderer gu5;

	public ModelInnerSoup() {
		textureWidth = 64;
		textureHeight = 32;

		gu1 = new ModelRenderer(this, 0, 0);
		gu1.addBox(-0.5F, 0.5F, 1F, 2, 1, 1);
		gu1.setRotationPoint(0F, 18F, 0F);
		gu1.setTextureSize(64, 32);
		gu1.mirror = true;
		setRotation(gu1, 0.2094395F, 2.094395F, 0F);
		gu2 = new ModelRenderer(this, 7, 0);
		gu2.addBox(-2F, -0.5F, 0.5F, 2, 1, 2);
		gu2.setRotationPoint(0F, 18F, 0F);
		gu2.setTextureSize(64, 32);
		gu2.mirror = true;
		setRotation(gu2, -0.4363323F, -0.2094395F, -0.3490659F);
		gu3 = new ModelRenderer(this, 16, 0);
		gu3.addBox(0.5F, 1.5F, 0F, 2, 1, 1);
		gu3.setRotationPoint(0F, 18F, 0F);
		gu3.setTextureSize(64, 32);
		gu3.mirror = true;
		setRotation(gu3, 0F, -0.5235988F, -0.5235988F);
		gu4 = new ModelRenderer(this, 0, 3);
		gu4.addBox(0F, -1.2F, -2.5F, 1, 1, 1);
		gu4.setRotationPoint(0F, 18F, 0F);
		gu4.setTextureSize(64, 32);
		gu4.mirror = true;
		setRotation(gu4, 0.7853982F, 0.7853982F, 0F);
		gu5 = new ModelRenderer(this, 0, 3);
		gu5.addBox(0F, 0F, 0F, 1, 1, 1);
		gu5.setRotationPoint(0F, 19F, 0F);
		gu5.setTextureSize(64, 32);
		gu5.mirror = true;
		setRotation(gu5, 0.7853982F, 0.7853982F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		gu1.render(f5);
		gu2.render(f5);
		gu3.render(f5);
		gu4.render(f5);
		gu5.render(f5);
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
